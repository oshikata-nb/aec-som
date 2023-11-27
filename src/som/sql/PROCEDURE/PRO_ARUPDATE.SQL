CREATE OR REPLACE PROCEDURE PRO_ARUPDATE
(
    P_STR_ORGANIZATION_CD  IN NVARCHAR2
   ,P_STR_CREDIT_DATE      IN NVARCHAR2
   ,P_STR_TEMP_CLOSING_FLG IN NVARCHAR2
   ,P_STR_INPUTOR_CD       IN NVARCHAR2
   ,P_NUM_RET              OUT NUMBER
   ,P_ERRCD                OUT NUMBER
   ,P_ERRMSG               OUT NVARCHAR2
) IS
    /*----------------------------------------------------------------------------------------------------------------
        種別        :    PROCEDURE
        名称        :    PRO_ARUPDATE
        処理内容    :    売掛更新処理
        引数        :    P_STR_ORGANIZATION_CD   部署コード             NVARCHAR2型
        引数        :    P_STR_CREDIT_DATE       売掛締め日             NVARCHAR2型
        引数        :    P_STR_TEMP_CLOSING_FLG  仮締区分               NVARCHAR2型
        引数        :    P_STR_INPUTOR_CD        登録者ＩＤ             NVARCHAR2型
        引数(戻値)  :    P_NUM_RET               実行結果               NUMBER型
                                                 (0:正常終了, 99:異常終了)
        引数(戻値)  :    P_ERRCD                 SQLエラーコード        NUMBER型
        引数(戻値)  :    P_ERRMSG                SQLエラーメッセージ    NVARCHAR2型
        VERNO.      :    1.04
    (history)
        date          ver       name                  comments
        ----------    ------    ------------------    ----------------------------------------------------------------------
        2008.07.28    1.00      TOSCO                 creation
        2009.06.04                                    SOM殿向けカスタマイズ
        2009.08.08              okmt.tc               内税でも売上は税抜きとする。
        2015.11.09    1.03      n.s                   テーブルロック時のログを追加
        2017.04.06    1.04      aecs                  テーブルロック処理を削除
    */ ----------------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------
    --定数・変数宣言
    --------------------------------------------------------------------------------
    c_DataDivUriUriage CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 1; -- (売上)売上
    c_DataDivUriHenpin CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 2; -- (売上)返品
    c_DataDivUriNebiki CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 3; -- (売上)値引
    c_DataDivUriSonota CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 9; -- (売上)その他
    c_DataDivNyuNyukin CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 1; -- (入金)入金
    c_DataDivNyuSousai CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 2; -- (入金)相殺
    c_DataDivNyuSonota CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 9; -- (入金)その他
    c_DataDivShiSousai CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 2; -- (支払)相殺
    c_DataDivSouSousai CONSTANT SALES.DATA_TOTAL_DIVISION%TYPE := 1; -- (グループ間相殺)グループ間相殺
    c_DepositTrgDiv1   CONSTANT SALES.DEPOSIT_TARGET_DIVISION%TYPE := 1; -- 売掛対象(処理済)
    c_DepositTrgDiv9   CONSTANT SALES.DEPOSIT_TARGET_DIVISION%TYPE := 9; -- 売掛対象(対象外)
    c_DepositUpdDiv0   CONSTANT SALES.DEPOSIT_UPDATE_DIVISION%TYPE := 0; -- 売掛更新フラグ(未処理)
    c_DepositUpdDiv1   CONSTANT SALES.DEPOSIT_UPDATE_DIVISION%TYPE := 1; -- 売掛更新フラグ(更新済)
    c_ArDiv0           CONSTANT CLASSIFICATION.AR_DIVISION%TYPE := 0; -- 売掛対象(対象外)
    c_ArDiv1           CONSTANT CLASSIFICATION.AR_DIVISION%TYPE := 1; -- 売掛対象(対象)
    c_ClaimUpdDiv0     CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE := 0; -- 請求更新フラグ(未処理)
    c_ClaimUpdDiv1     CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE := 1; -- 請求更新フラグ(更新済)
    c_ApprovalSts3     CONSTANT SALES.APPROVAL_STATUS%TYPE := 3; -- 承認ステータス(承認済)
    c_TempClosingFlg1  CONSTANT VARCHAR2(1) := '1'; -- 仮締区分(仮締め)
    c_VenderDivTS      CONSTANT VENDER.VENDER_DIVISION%TYPE := 'TS'; -- 取引先区分(TS:得意先)
    c_TmpUnitPriceFlg0 CONSTANT SALES.TMP_UNITPRICE_FLG%TYPE := 0; -- 仮単価フラグ(通常)
    DEF_PROC_NAME      CONSTANT PROC_PARAM.PROC_CD%TYPE := 'PRO_ARUPDATE';

    daCreditDate DEPOSIT_HEADER.CREDIT_DATE%TYPE; -- 売掛締め日
    nvDepositNo  DEPOSIT_HEADER.DEPOSIT_NO%TYPE; -- 売掛番号
    rtRecWork    DEPOSIT_HEADER%ROWTYPE; -- 売掛ヘッダー
    nvCount      NUMBER := 0; -- カウンター
    msg          TEMP_BATCH_MSG.MSG%TYPE;

    --------------------------------------------------------------------------------
    --★カーソル宣言
    --------------------------------------------------------------------------------
    --売上データ抽出 カーソル
    CURSOR curSales(p_daCreditDate IN DATE) IS
        SELECT *
        FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,TRN.VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE + BALANCE_FORWARD_ACC +
                           BALANCE_FORWARD_EXC) AS BALANCE_FORWARD --前月繰越
                      ,SUM(SALES_AMOUNT_CRE + SALES_AMOUNT_ACC +
                           SALES_AMOUNT_EXC) AS SALES_AMOUNT --売上金額
                      ,SUM(DEPOSIT_AMOUNT_CRE + DEPOSIT_AMOUNT_ACC +
                           DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT --入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE +
                           OTHER_DEPOSIT_AMOUNT_ACC +
                           OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT --その他入金金額
                      ,SUM(RETURNED_AMOUNT_CRE + RETURNED_AMOUNT_ACC +
                           RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT --返品金額
                      ,SUM(DISCOUNT_AMOUNT_CRE + DISCOUNT_AMOUNT_ACC +
                           DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT --値引金額
                      ,SUM(OTHER_SALES_AMOUNT_CRE + OTHER_SALES_AMOUNT_ACC +
                           OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT --その他売上金額
                      ,SUM(OFFSET_AMOUNT_CRE + OFFSET_AMOUNT_ACC +
                           OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT --相殺金額
                      ,SUM(TAX_AMOUNT_CRE + TAX_AMOUNT_ACC + TAX_AMOUNT_EXC) AS TAX_AMOUNT --消費税額
                      ,SUM((BALANCE_FORWARD_CRE + BALANCE_FORWARD_ACC +
                           BALANCE_FORWARD_EXC + SALES_AMOUNT_CRE +
                           SALES_AMOUNT_ACC + SALES_AMOUNT_EXC +
                           OTHER_SALES_AMOUNT_CRE + OTHER_SALES_AMOUNT_ACC +
                           OTHER_SALES_AMOUNT_EXC + TAX_AMOUNT_CRE +
                           TAX_AMOUNT_ACC + TAX_AMOUNT_EXC) -
                           (RETURNED_AMOUNT_CRE + RETURNED_AMOUNT_ACC +
                           RETURNED_AMOUNT_EXC + DISCOUNT_AMOUNT_CRE +
                           DISCOUNT_AMOUNT_ACC + DISCOUNT_AMOUNT_EXC +
                           DEPOSIT_AMOUNT_CRE + DEPOSIT_AMOUNT_ACC +
                           DEPOSIT_AMOUNT_EXC + OTHER_DEPOSIT_AMOUNT_CRE +
                           OTHER_DEPOSIT_AMOUNT_ACC +
                           OTHER_DEPOSIT_AMOUNT_EXC + OFFSET_AMOUNT_CRE +
                           OFFSET_AMOUNT_ACC + OFFSET_AMOUNT_EXC)) AS CREDIT_AMOUNT --売掛残
                      ,SUM((BALANCE_FORWARD_CRE + SALES_AMOUNT_CRE +
                           OTHER_SALES_AMOUNT_CRE + TAX_AMOUNT_CRE) -
                           (RETURNED_AMOUNT_CRE + DISCOUNT_AMOUNT_CRE +
                           DEPOSIT_AMOUNT_CRE + OTHER_DEPOSIT_AMOUNT_CRE +
                           OFFSET_AMOUNT_CRE)) AS CREDIT_SALES_BREAKDOWN --売掛金(内訳)
                      ,SUM((BALANCE_FORWARD_ACC + SALES_AMOUNT_ACC +
                           OTHER_SALES_AMOUNT_ACC + TAX_AMOUNT_ACC) -
                           (RETURNED_AMOUNT_ACC + DISCOUNT_AMOUNT_ACC +
                           DEPOSIT_AMOUNT_ACC + OTHER_DEPOSIT_AMOUNT_ACC +
                           OFFSET_AMOUNT_ACC)) AS ACCRUED_DEBIT_BREAKDOWN --未収金(内訳)
                      ,SUM((BALANCE_FORWARD_EXC + SALES_AMOUNT_EXC +
                           OTHER_SALES_AMOUNT_EXC + TAX_AMOUNT_EXC) -
                           (RETURNED_AMOUNT_EXC + DISCOUNT_AMOUNT_EXC +
                           DEPOSIT_AMOUNT_EXC + OTHER_DEPOSIT_AMOUNT_EXC +
                           OFFSET_AMOUNT_EXC)) AS EXCEPT_BREAKDOWN --以外(内訳)
                FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,VENDER_CD AS VENDER_CD --請求先コード
                              ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                              ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                              ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                              ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                              ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                              ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                              ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                              ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                              ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                              ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                              ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                              ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                              ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                              ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                              ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                              ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                              ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                              ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                              ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                              ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                              ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                              ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                              ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                        FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                                      ,DECODE(SALES.INVOICE_CD
                                             ,NULL
                                             ,SALES.VENDER_CD
                                             ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS SALES_AMOUNT_CRE            --売上金額(内税の場合は＋消費税額)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS SALES_AMOUNT_ACC            --売上金額(内税の場合は＋消費税額)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                 END
                                       --                     END AS SALES_AMOUNT_EXC        --売上金額(内税の場合は＋消費税額)(以外)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS SALES_AMOUNT_CRE --売上金額(売上は税抜き)(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS SALES_AMOUNT_ACC --売上金額(売上は税抜き)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                              ,1
                                                              ,1)
                                                       ,'-'
                                                       ,SALES.SALES_AMOUNT * -1
                                                       ,SALES.SALES_AMOUNT)
                                            END
                                       END AS SALES_AMOUNT_EXC --売上金額(売上は税抜き)(以外)
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                 END
                                       --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT)
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT)
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SALES.TAX_DIVISION
                                                       ,'3'
                                                       ,0
                                                       ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                     ,1
                                                                     ,1)
                                                              ,'-'
                                                              ,SALES.TAX_AMOUNT * -1
                                                              ,SALES.TAX_AMOUNT))
                                            END
                                       END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                                      ,0 AS CREDIT_AMOUNT
                                FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                              ,SALES.VENDER_CD
                                              ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                              ,SALES.TAX_DIVISION
                                              ,SALES.SALES_AMOUNT
                                              ,SALES.TAX_AMOUNT
                                              ,SALES.DATA_TYPE
                                              ,SALES.DATA_TOTAL_DIVISION
                                              ,SALES.CATEGORY_DIVISION
                                              ,SALES.DEBIT_TITLE_CD
                                              ,SALES.CREDIT_TITLE_CD
                                        FROM   SALES  SALES --売上トラン
                                              ,VENDER VENDER
                                        WHERE  SALES.SALES_DATE <=
                                               p_daCreditDate
                                        AND    SALES.DATA_TOTAL_DIVISION =
                                               c_DataDivUriUriage --売上
                                        AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                               c_DepositUpdDiv0 --未処理
                                        AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                        AND    SALES.TMP_UNITPRICE_FLG =
                                               c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                        AND    SALES.VENDER_CD =
                                               VENDER.VENDER_CD
                                        AND    VENDER.VENDER_DIVISION =
                                               c_VenderDivTS) SALES
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                                WHERE  SALES.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       CLASSIFICATION.DATA_TOTAL_DIVISION
                                AND    SALES.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    SALES.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    SALES.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+)
                                UNION ALL
                                SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                                      ,DECODE(SALES.INVOICE_CD
                                             ,NULL
                                             ,SALES.VENDER_CD
                                             ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS RETURNED_AMOUNT_CRE        --返品金額(内税の場合は＋消費税額)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS RETURNED_AMOUNT_ACC        --返品金額(内税の場合は＋消費税額)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                       , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                 END
                                       --                     END AS RETURNED_AMOUNT_EXC        --返品金額(内税の場合は＋消費税額)(以外)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS RETURNED_AMOUNT_CRE --返品金額(売上は税抜き)(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS RETURNED_AMOUNT_ACC --返品金額(売上は税抜き)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                              ,1
                                                              ,1)
                                                       ,'-'
                                                       ,SALES.SALES_AMOUNT * -1
                                                       ,SALES.SALES_AMOUNT)
                                            END
                                       END AS RETURNED_AMOUNT_EXC --返品金額(売上は税抜き)(以外)
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                       --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) * -1) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                                       --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) * -1) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE (DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0) * -1)
                                       --                                 END
                                       --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT) * -1
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT) * -1
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SALES.TAX_DIVISION
                                                       ,'3'
                                                       ,0
                                                       ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                     ,1
                                                                     ,1)
                                                              ,'-'
                                                              ,SALES.TAX_AMOUNT * -1
                                                              ,SALES.TAX_AMOUNT) * -1)
                                            END
                                       END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                                      ,0 AS CREDIT_AMOUNT
                                FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                              ,SALES.VENDER_CD
                                              ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                              ,SALES.TAX_DIVISION
                                              ,SALES.SALES_AMOUNT
                                              ,SALES.TAX_AMOUNT
                                              ,SALES.DATA_TYPE
                                              ,SALES.DATA_TOTAL_DIVISION
                                              ,SALES.CATEGORY_DIVISION
                                              ,SALES.DEBIT_TITLE_CD
                                              ,SALES.CREDIT_TITLE_CD
                                        FROM   SALES  SALES --売上トラン
                                              ,VENDER VENDER
                                        WHERE  SALES.SALES_DATE <=
                                               p_daCreditDate
                                        AND    SALES.DATA_TOTAL_DIVISION =
                                               c_DataDivUriHenpin --返品
                                        AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                               c_DepositUpdDiv0 --未処理
                                        AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                        AND    SALES.TMP_UNITPRICE_FLG =
                                               c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                        AND    SALES.VENDER_CD =
                                               VENDER.VENDER_CD
                                        AND    VENDER.VENDER_DIVISION =
                                               c_VenderDivTS) SALES
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                                WHERE  SALES.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       CLASSIFICATION.DATA_TOTAL_DIVISION
                                AND    SALES.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    SALES.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    SALES.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+)
                                UNION ALL
                                SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                                      ,DECODE(SALES.INVOICE_CD
                                             ,NULL
                                             ,SALES.VENDER_CD
                                             ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS DISCOUNT_AMOUNT_CRE        --値引金額(内税の場合は＋消費税額)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS DISCOUNT_AMOUNT_ACC        --値引金額(内税の場合は＋消費税額)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                 END
                                       --                     END AS DISCOUNT_AMOUNT_EXC        --値引金額(内税の場合は＋消費税額)(以外)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS DISCOUNT_AMOUNT_CRE --値引金額(売上は税抜き)(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS DISCOUNT_AMOUNT_ACC --値引金額(売上は税抜き)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                              ,1
                                                              ,1)
                                                       ,'-'
                                                       ,SALES.SALES_AMOUNT * -1
                                                       ,SALES.SALES_AMOUNT)
                                            END
                                       END AS DISCOUNT_AMOUNT_EXC --値引金額(売上は税抜き)(以外)
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                       --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) * -1) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                                       --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) * -1) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE (DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0) * -1)
                                       --                                 END
                                       --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT) * -1
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT) * -1
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SALES.TAX_DIVISION
                                                       ,'3'
                                                       ,0
                                                       ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                     ,1
                                                                     ,1)
                                                              ,'-'
                                                              ,SALES.TAX_AMOUNT * -1
                                                              ,SALES.TAX_AMOUNT) * -1)
                                            END
                                       END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                                      ,0 AS CREDIT_AMOUNT
                                FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                              ,SALES.VENDER_CD
                                              ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                              ,SALES.TAX_DIVISION
                                              ,SALES.SALES_AMOUNT
                                              ,SALES.TAX_AMOUNT
                                              ,SALES.DATA_TYPE
                                              ,SALES.DATA_TOTAL_DIVISION
                                              ,SALES.CATEGORY_DIVISION
                                              ,SALES.DEBIT_TITLE_CD
                                              ,SALES.CREDIT_TITLE_CD
                                        FROM   SALES  SALES --売上トラン
                                              ,VENDER VENDER
                                        WHERE  SALES.SALES_DATE <=
                                               p_daCreditDate
                                        AND    SALES.DATA_TOTAL_DIVISION =
                                               c_DataDivUriNebiki --値引
                                        AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                               c_DepositUpdDiv0 --未処理
                                        AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                        AND    SALES.TMP_UNITPRICE_FLG =
                                               c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                        AND    SALES.VENDER_CD =
                                               VENDER.VENDER_CD
                                        AND    VENDER.VENDER_DIVISION =
                                               c_VenderDivTS) SALES
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                                WHERE  SALES.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       CLASSIFICATION.DATA_TOTAL_DIVISION
                                AND    SALES.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    SALES.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    SALES.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+)
                                UNION ALL
                                SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                                      ,DECODE(SALES.INVOICE_CD
                                             ,NULL
                                             ,SALES.VENDER_CD
                                             ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS OTHER_SALES_AMOUNT_CRE        --その他売上金額(内税の場合は＋消費税額)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                                                                             , 0)
                                       --                     ) AS OTHER_SALES_AMOUNT_ACC        --その他売上金額(内税の場合は＋消費税額)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                                       --                                                                       , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                                       --                                 END
                                       --                     END AS OTHER_SALES_AMOUNT_EXC        --その他売上金額(内税の場合は＋消費税額)(以外)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売上は税抜き)(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                           ,1
                                                           ,1)
                                                    ,'-'
                                                    ,SALES.SALES_AMOUNT * -1
                                                    ,SALES.SALES_AMOUNT)
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                  ,1
                                                                  ,1)
                                                           ,'-'
                                                           ,SALES.SALES_AMOUNT * -1
                                                           ,SALES.SALES_AMOUNT)
                                                    ,0)) AS OTHER_SALES_AMOUNT_ACC --その他売上金額(売上は税抜き)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                              ,1
                                                              ,1)
                                                       ,'-'
                                                       ,SALES.SALES_AMOUNT * -1
                                                       ,SALES.SALES_AMOUNT)
                                            END
                                       END AS OTHER_SALES_AMOUNT_EXC --その他売上金額(売上は税抜き)(以外)
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                                       --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                                                                             , 0)
                                       --                     ) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                                       --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                          ELSE 
                                       --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                                       --                                         ELSE DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                                       --                                 END
                                       --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT)
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                                      ,CASE
                                           WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                                AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                                SALES.TAX_DIVISION != '3' THEN
                                            DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                         ,1
                                                         ,1)
                                                  ,'-'
                                                  ,SALES.TAX_AMOUNT * -1
                                                  ,SALES.TAX_AMOUNT)
                                           ELSE
                                            0
                                       END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 DECODE(SALES.TAX_DIVISION
                                                       ,'3'
                                                       ,0
                                                       ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                                     ,1
                                                                     ,1)
                                                              ,'-'
                                                              ,SALES.TAX_AMOUNT * -1
                                                              ,SALES.TAX_AMOUNT))
                                            END
                                       END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                                      ,0 AS CREDIT_AMOUNT
                                FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                              ,SALES.VENDER_CD
                                              ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                              ,SALES.TAX_DIVISION
                                              ,SALES.SALES_AMOUNT
                                              ,SALES.TAX_AMOUNT
                                              ,SALES.DATA_TYPE
                                              ,SALES.DATA_TOTAL_DIVISION
                                              ,SALES.CATEGORY_DIVISION
                                              ,SALES.DEBIT_TITLE_CD
                                              ,SALES.CREDIT_TITLE_CD
                                        FROM   SALES  SALES --売上トラン
                                              ,VENDER VENDER
                                        WHERE  SALES.SALES_DATE <=
                                               p_daCreditDate
                                        AND    SALES.DATA_TOTAL_DIVISION =
                                               c_DataDivUriSonota --その他売上
                                        AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                               c_DepositUpdDiv0 --未処理
                                        AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                        AND    SALES.TMP_UNITPRICE_FLG =
                                               c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                        AND    SALES.VENDER_CD =
                                               VENDER.VENDER_CD
                                        AND    VENDER.VENDER_DIVISION =
                                               c_VenderDivTS) SALES
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                                WHERE  SALES.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       CLASSIFICATION.DATA_TOTAL_DIVISION
                                AND    SALES.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    SALES.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    SALES.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+))
                        GROUP  BY ORGANIZATION_CD, VENDER_CD
                        UNION ALL
                        SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,VENDER_CD AS VENDER_CD --請求先コード
                              ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                              ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                              ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                              ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                              ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                              ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                              ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                              ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                              ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                              ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                              ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                              ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                              ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                              ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                              ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                              ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                              ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                              ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                              ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                              ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                              ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                              ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                              ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                        FROM   (SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                                      ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 CREDIT.CREDIT_AMOUNT
                                            END
                                       END AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                      ,0 AS TAX_AMOUNT_CRE
                                      ,0 AS TAX_AMOUNT_ACC
                                      ,0 AS TAX_AMOUNT_EXC
                                      ,0 AS CREDIT_AMOUNT
                                FROM   CREDIT         CREDIT --入金トラン
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                                WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                                AND    CREDIT.DATA_TOTAL_DIVISION =
                                       c_DataDivNyuNyukin --入金
                                AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                                AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                                AND    CREDIT.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    CREDIT.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    CREDIT.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    CREDIT.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+)
                                UNION ALL
                                SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                                      ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_CRE --入金相殺金額(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_ACC --入金相殺金額(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 CREDIT.CREDIT_AMOUNT
                                            END
                                       END AS OFFSET_AMOUNT_EXC --入金相殺金額(以外)
                                      ,0 AS TAX_AMOUNT_CRE
                                      ,0 AS TAX_AMOUNT_ACC
                                      ,0 AS TAX_AMOUNT_EXC
                                      ,0 AS CREDIT_AMOUNT
                                FROM   CREDIT         CREDIT --入金トラン
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                                WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                                AND    CREDIT.DATA_TOTAL_DIVISION =
                                       c_DataDivNyuSousai --相殺
                                AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                                AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                                AND    CREDIT.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    CREDIT.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    CREDIT.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    CREDIT.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+)
                                UNION ALL
                                SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                                      ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,CREDIT.CREDIT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,CREDIT.CREDIT_AMOUNT
                                                    ,0)) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 CREDIT.CREDIT_AMOUNT
                                            END
                                       END AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,0 AS OFFSET_AMOUNT_CRE
                                      ,0 AS OFFSET_AMOUNT_ACC
                                      ,0 AS OFFSET_AMOUNT_EXC
                                      ,0 AS TAX_AMOUNT_CRE
                                      ,0 AS TAX_AMOUNT_ACC
                                      ,0 AS TAX_AMOUNT_EXC
                                      ,0 AS CREDIT_AMOUNT
                                FROM   CREDIT         CREDIT --入金トラン
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                                WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                                AND    CREDIT.DATA_TOTAL_DIVISION =
                                       c_DataDivNyuSonota --その他入金
                                AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                                AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                                AND    CREDIT.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    CREDIT.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    CREDIT.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    CREDIT.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+))
                        GROUP  BY ORGANIZATION_CD, VENDER_CD
                        UNION ALL
                        SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,VENDER_CD AS VENDER_CD --請求先コード
                              ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                              ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                              ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                              ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                              ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                              ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                              ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                              ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                              ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                              ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                              ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                              ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                              ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                              ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                              ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                              ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                              ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                              ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                              ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                              ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                              ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                              ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                              ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                        FROM   (SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                                      ,PAYMENT.SUPPLIER_CD AS VENDER_CD --仕入先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,PAYMENT.PAYMENT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,PAYMENT.PAYMENT_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_CRE --支払相殺金額(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,PAYMENT.PAYMENT_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,PAYMENT.PAYMENT_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_ACC --支払相殺金額(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 PAYMENT.PAYMENT_AMOUNT
                                            END
                                       END AS OFFSET_AMOUNT_EXC --支払相殺金額(以外)
                                      ,0 AS TAX_AMOUNT_CRE
                                      ,0 AS TAX_AMOUNT_ACC
                                      ,0 AS TAX_AMOUNT_EXC
                                      ,0 AS CREDIT_AMOUNT
                                FROM   PAYMENT        PAYMENT --支払トラン
                                      ,CLASSIFICATION CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                                WHERE  PAYMENT.PAYMENT_DATE <= p_daCreditDate
                                AND    PAYMENT.DATA_TOTAL_DIVISION =
                                       c_DataDivShiSousai --相殺
                                AND    PAYMENT.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    PAYMENT.DELIVERY_UPDATE_DATE IS NULL
                                AND    PAYMENT.APPROVAL_STATUS =
                                       c_ApprovalSts3 --承認済
                                AND    PAYMENT.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    PAYMENT.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    PAYMENT.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    PAYMENT.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+))
                        GROUP  BY ORGANIZATION_CD, VENDER_CD
                        UNION ALL
                        SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,VENDER_CD AS VENDER_CD --請求先コード
                              ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                              ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                              ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                              ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                              ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                              ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                              ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                              ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                              ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                              ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                              ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                              ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                              ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                              ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                              ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                              ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                              ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                              ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                              ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                              ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                              ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                              ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                              ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                        FROM   (SELECT OFFSET.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                                      ,OFFSET.VENDER_CD AS VENDER_CD --請求先コード
                                      ,0 AS BALANCE_FORWARD_CRE
                                      ,0 AS BALANCE_FORWARD_ACC
                                      ,0 AS BALANCE_FORWARD_EXC
                                      ,0 AS SALES_AMOUNT_CRE
                                      ,0 AS SALES_AMOUNT_ACC
                                      ,0 AS SALES_AMOUNT_EXC
                                      ,0 AS DEPOSIT_AMOUNT_CRE
                                      ,0 AS DEPOSIT_AMOUNT_ACC
                                      ,0 AS DEPOSIT_AMOUNT_EXC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0 AS RETURNED_AMOUNT_CRE
                                      ,0 AS RETURNED_AMOUNT_ACC
                                      ,0 AS RETURNED_AMOUNT_EXC
                                      ,0 AS DISCOUNT_AMOUNT_CRE
                                      ,0 AS DISCOUNT_AMOUNT_ACC
                                      ,0 AS DISCOUNT_AMOUNT_EXC
                                      ,0 AS OTHER_SALES_AMOUNT_CRE
                                      ,0 AS OTHER_SALES_AMOUNT_ACC
                                      ,0 AS OTHER_SALES_AMOUNT_EXC
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,10
                                             ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,10
                                                    ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_CRE --売掛相殺金額(売掛金)
                                      ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                             ,11
                                             ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                             ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                                    ,11
                                                    ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                                    ,0)) AS OFFSET_AMOUNT_ACC --売掛相殺金額(未収金)
                                      ,CASE
                                           WHEN AC_KARI.ARTICLE_ACCOUNTS IN
                                                (10, 11) THEN
                                            0
                                           ELSE
                                            CASE
                                                WHEN AC_KASI.ARTICLE_ACCOUNTS IN
                                                     (10, 11) THEN
                                                 0
                                                ELSE
                                                 OFFSET.DEPOSIT_OFFSET_AMOUNT
                                            END
                                       END AS OFFSET_AMOUNT_EXC --売掛相殺金額(以外)
                                      ,0 AS TAX_AMOUNT_CRE
                                      ,0 AS TAX_AMOUNT_ACC
                                      ,0 AS TAX_AMOUNT_EXC
                                      ,0 AS CREDIT_AMOUNT
                                FROM   OFFSET_GROUP_DATA OFFSET --グループ間トラン
                                      ,CLASSIFICATION    CLASSIFICATION --分類マスタ
                                      ,ACCOUNTS          AC_KARI --科目マスタ(借方)
                                      ,ACCOUNTS          AC_KASI --科目マスタ(貸方)
                                WHERE  OFFSET.OFFSET_DATE <= p_daCreditDate
                                AND    OFFSET.DATA_TOTAL_DIVISION =
                                       c_DataDivSouSousai --グループ間相殺
                                AND    OFFSET.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    OFFSET.DELIVERY_UPDATE_DATE IS NULL
                                AND    OFFSET.DEPOSIT_OFFSET_AMOUNT IS NOT NULL --売掛相殺金額
                                AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                                AND    OFFSET.VENDER_DIVISION = c_VenderDivTS --得意先
                                AND    OFFSET.DATA_TYPE =
                                       CLASSIFICATION.DATA_TYPE
                                AND    OFFSET.CATEGORY_DIVISION =
                                       CLASSIFICATION.CATEGORY_DIVISION
                                AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                                      --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                                      --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                                      --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                                AND    OFFSET.DEBIT_TITLE_CD =
                                       AC_KARI.ACCOUNTS_CD(+)
                                AND    OFFSET.CREDIT_TITLE_CD =
                                       AC_KASI.ACCOUNTS_CD(+))
                        GROUP  BY ORGANIZATION_CD, VENDER_CD
                        UNION ALL
                        SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,VENDER_CD AS VENDER_CD --請求先コード
                              ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                              ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                              ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                              ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                              ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                              ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                              ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                              ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                              ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                              ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                              ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                              ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                              ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                              ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                              ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                              ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                              ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                              ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                              ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                              ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                              ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                              ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                              ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                        FROM   (SELECT HEADER.ORGANIZATION_CD         AS ORGANIZATION_CD --部署コード
                                      ,HEADER.VENDER_CD               AS VENDER_CD --請求先コード
                                      ,HEADER.CREDIT_SALES_BREAKDOWN  AS BALANCE_FORWARD_CRE --売掛残(売掛金)
                                      ,HEADER.ACCRUED_DEBIT_BREAKDOWN AS BALANCE_FORWARD_ACC --売掛残(未収金)
                                      ,HEADER.EXCEPT_BREAKDOWN        AS BALANCE_FORWARD_EXC --売掛残(以外)
                                      ,0                              AS SALES_AMOUNT_CRE
                                      ,0                              AS SALES_AMOUNT_ACC
                                      ,0                              AS SALES_AMOUNT_EXC
                                      ,0                              AS DEPOSIT_AMOUNT_CRE
                                      ,0                              AS DEPOSIT_AMOUNT_ACC
                                      ,0                              AS DEPOSIT_AMOUNT_EXC
                                      ,0                              AS OTHER_DEPOSIT_AMOUNT_CRE
                                      ,0                              AS OTHER_DEPOSIT_AMOUNT_ACC
                                      ,0                              AS OTHER_DEPOSIT_AMOUNT_EXC
                                      ,0                              AS RETURNED_AMOUNT_CRE
                                      ,0                              AS RETURNED_AMOUNT_ACC
                                      ,0                              AS RETURNED_AMOUNT_EXC
                                      ,0                              AS DISCOUNT_AMOUNT_CRE
                                      ,0                              AS DISCOUNT_AMOUNT_ACC
                                      ,0                              AS DISCOUNT_AMOUNT_EXC
                                      ,0                              AS OTHER_SALES_AMOUNT_CRE
                                      ,0                              AS OTHER_SALES_AMOUNT_ACC
                                      ,0                              AS OTHER_SALES_AMOUNT_EXC
                                      ,0                              AS OFFSET_AMOUNT_CRE
                                      ,0                              AS OFFSET_AMOUNT_ACC
                                      ,0                              AS OFFSET_AMOUNT_EXC
                                      ,0                              AS TAX_AMOUNT_CRE
                                      ,0                              AS TAX_AMOUNT_ACC
                                      ,0                              AS TAX_AMOUNT_EXC
                                      ,0                              AS CREDIT_AMOUNT
                                FROM   DEPOSIT_HEADER HEADER --売掛ヘッダー
                                      ,(SELECT ORGANIZATION_CD
                                              ,VENDER_CD
                                              ,MAX(CREDIT_DATE) MAX_DATE
                                        FROM   DEPOSIT_HEADER
                                        WHERE  CREDIT_DATE < p_daCreditDate
                                        GROUP  BY ORGANIZATION_CD, VENDER_CD) MAX_HEADER
                                WHERE  HEADER.CREDIT_AMOUNT <> 0
                                AND    HEADER.ORGANIZATION_CD =
                                       MAX_HEADER.ORGANIZATION_CD
                                AND    HEADER.VENDER_CD = MAX_HEADER.VENDER_CD
                                AND    HEADER.CREDIT_DATE =
                                       MAX_HEADER.MAX_DATE)
                        GROUP  BY ORGANIZATION_CD, VENDER_CD) TRN
                      ,(SELECT VENDER_CD
                        FROM   VENDER
                        WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                               ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
                        AND    VENDER_DIVISION = c_VenderDivTS
                        AND    (PAYMENT_INVOICE_CD IS NULL OR
                              VENDER.PAYMENT_INVOICE_CD = VENDER_CD)) VENDER
                WHERE  TRN.VENDER_CD = VENDER.VENDER_CD
                GROUP  BY TRN.ORGANIZATION_CD, TRN.VENDER_CD
                ORDER  BY ORGANIZATION_CD, VENDER_CD)
        WHERE  ORGANIZATION_CD NOT IN
               (SELECT ORGANIZATION_CD
                FROM   DEPOSIT_HEADER
                WHERE  CREDIT_DATE = p_daCreditDate
                GROUP  BY ORGANIZATION_CD, VENDER_CD)
              
        AND    VENDER_CD NOT IN
               (SELECT VENDER_CD
                 FROM   DEPOSIT_HEADER
                 WHERE  CREDIT_DATE = p_daCreditDate
                 GROUP  BY ORGANIZATION_CD, VENDER_CD);
    --仮締め売掛データ抽出 カーソル
    CURSOR curTempSales(p_daCreditDate IN DATE) IS
        SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
              ,TRN.VENDER_CD AS VENDER_CD --請求先コード
              ,SUM(BALANCE_FORWARD_CRE + BALANCE_FORWARD_ACC +
                   BALANCE_FORWARD_EXC) AS BALANCE_FORWARD --前月繰越
              ,SUM(SALES_AMOUNT_CRE + SALES_AMOUNT_ACC + SALES_AMOUNT_EXC) AS SALES_AMOUNT --売上金額
              ,SUM(DEPOSIT_AMOUNT_CRE + DEPOSIT_AMOUNT_ACC +
                   DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT --入金金額
              ,SUM(OTHER_DEPOSIT_AMOUNT_CRE + OTHER_DEPOSIT_AMOUNT_ACC +
                   OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT --その他入金金額
              ,SUM(RETURNED_AMOUNT_CRE + RETURNED_AMOUNT_ACC +
                   RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT --返品金額
              ,SUM(DISCOUNT_AMOUNT_CRE + DISCOUNT_AMOUNT_ACC +
                   DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT --値引金額
              ,SUM(OTHER_SALES_AMOUNT_CRE + OTHER_SALES_AMOUNT_ACC +
                   OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT --その他売上金額
              ,SUM(OFFSET_AMOUNT_CRE + OFFSET_AMOUNT_ACC +
                   OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT --相殺金額
              ,SUM(TAX_AMOUNT_CRE + TAX_AMOUNT_ACC + TAX_AMOUNT_EXC) AS TAX_AMOUNT --消費税額
              ,SUM((BALANCE_FORWARD_CRE + BALANCE_FORWARD_ACC +
                   BALANCE_FORWARD_EXC + SALES_AMOUNT_CRE +
                   SALES_AMOUNT_ACC + SALES_AMOUNT_EXC +
                   OTHER_SALES_AMOUNT_CRE + OTHER_SALES_AMOUNT_ACC +
                   OTHER_SALES_AMOUNT_EXC + TAX_AMOUNT_CRE +
                   TAX_AMOUNT_ACC + TAX_AMOUNT_EXC) -
                   (RETURNED_AMOUNT_CRE + RETURNED_AMOUNT_ACC +
                   RETURNED_AMOUNT_EXC + DISCOUNT_AMOUNT_CRE +
                   DISCOUNT_AMOUNT_ACC + DISCOUNT_AMOUNT_EXC +
                   DEPOSIT_AMOUNT_CRE + DEPOSIT_AMOUNT_ACC +
                   DEPOSIT_AMOUNT_EXC + OTHER_DEPOSIT_AMOUNT_CRE +
                   OTHER_DEPOSIT_AMOUNT_ACC + OTHER_DEPOSIT_AMOUNT_EXC +
                   OFFSET_AMOUNT_CRE + OFFSET_AMOUNT_ACC +
                   OFFSET_AMOUNT_EXC)) AS CREDIT_AMOUNT --売掛残
              ,SUM((BALANCE_FORWARD_CRE + SALES_AMOUNT_CRE +
                   OTHER_SALES_AMOUNT_CRE + TAX_AMOUNT_CRE) -
                   (RETURNED_AMOUNT_CRE + DISCOUNT_AMOUNT_CRE +
                   DEPOSIT_AMOUNT_CRE + OTHER_DEPOSIT_AMOUNT_CRE +
                   OFFSET_AMOUNT_CRE)) AS CREDIT_SALES_BREAKDOWN --売掛金(内訳)
              ,SUM((BALANCE_FORWARD_ACC + SALES_AMOUNT_ACC +
                   OTHER_SALES_AMOUNT_ACC + TAX_AMOUNT_ACC) -
                   (RETURNED_AMOUNT_ACC + DISCOUNT_AMOUNT_ACC +
                   DEPOSIT_AMOUNT_ACC + OTHER_DEPOSIT_AMOUNT_ACC +
                   OFFSET_AMOUNT_ACC)) AS ACCRUED_DEBIT_BREAKDOWN --未収金(内訳)
              ,SUM((BALANCE_FORWARD_EXC + SALES_AMOUNT_EXC +
                   OTHER_SALES_AMOUNT_EXC + TAX_AMOUNT_EXC) -
                   (RETURNED_AMOUNT_EXC + DISCOUNT_AMOUNT_EXC +
                   DEPOSIT_AMOUNT_EXC + OTHER_DEPOSIT_AMOUNT_EXC +
                   OFFSET_AMOUNT_EXC)) AS EXCEPT_BREAKDOWN --以外(内訳)
        FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                      ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                      ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                      ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                      ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                      ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                      ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                      ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                      ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                      ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                      ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                      ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                      ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                      ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                      ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                      ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                      ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                      ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                      ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                      ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                      ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                      ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                      ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                      ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                      ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                              ,DECODE(SALES.INVOICE_CD
                                     ,NULL
                                     ,SALES.VENDER_CD
                                     ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS SALES_AMOUNT_CRE            --売上金額(内税の場合は＋消費税額)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS SALES_AMOUNT_ACC            --売上金額(内税の場合は＋消費税額)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                       , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                 END
                               --                     END AS SALES_AMOUNT_EXC        --売上金額(内税の場合は＋消費税額)(以外)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS SALES_AMOUNT_CRE --売上金額(売上は税抜き)(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS SALES_AMOUNT_ACC --売上金額(売上は税抜き)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                      ,1
                                                      ,1)
                                               ,'-'
                                               ,SALES.SALES_AMOUNT * -1
                                               ,SALES.SALES_AMOUNT)
                                    END
                               END AS SALES_AMOUNT_EXC --売上金額(売上は税抜き)(以外)
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                 END
                               --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT)
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT)
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SALES.TAX_DIVISION
                                               ,'3'
                                               ,0
                                               ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                             ,1
                                                             ,1)
                                                      ,'-'
                                                      ,SALES.TAX_AMOUNT * -1
                                                      ,SALES.TAX_AMOUNT))
                                    END
                               END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                              ,0 AS CREDIT_AMOUNT
                        FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                      ,SALES.VENDER_CD
                                      ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                      ,SALES.TAX_DIVISION
                                      ,SALES.SALES_AMOUNT
                                      ,SALES.TAX_AMOUNT
                                      ,SALES.DATA_TYPE
                                      ,SALES.DATA_TOTAL_DIVISION
                                      ,SALES.CATEGORY_DIVISION
                                      ,SALES.DEBIT_TITLE_CD
                                      ,SALES.CREDIT_TITLE_CD
                                FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                      ,VENDER                  VENDER
                                WHERE  SALES.SALES_DATE <= p_daCreditDate
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       c_DataDivUriUriage --売上
                                AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                AND    SALES.TMP_UNITPRICE_FLG =
                                       c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                AND    SALES.VENDER_CD = VENDER.VENDER_CD
                                AND    VENDER.VENDER_DIVISION = c_VenderDivTS) SALES
                              ,CLASSIFICATION CLASSIFICATION --分類マスタ
                              ,ACCOUNTS AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                        WHERE  SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    SALES.DATA_TOTAL_DIVISION =
                               CLASSIFICATION.DATA_TOTAL_DIVISION
                        AND    SALES.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    SALES.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    SALES.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+)
                        UNION ALL
                        SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                              ,DECODE(SALES.INVOICE_CD
                                     ,NULL
                                     ,SALES.VENDER_CD
                                     ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS RETURNED_AMOUNT_CRE        --返品金額(内税の場合は＋消費税額)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS RETURNED_AMOUNT_ACC        --返品金額(内税の場合は＋消費税額)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                       , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                 END
                               --                     END AS RETURNED_AMOUNT_EXC        --返品金額(内税の場合は＋消費税額)(以外)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS RETURNED_AMOUNT_CRE --返品金額(売上は税抜き)(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS RETURNED_AMOUNT_ACC --返品金額(売上は税抜き)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                      ,1
                                                      ,1)
                                               ,'-'
                                               ,SALES.SALES_AMOUNT * -1
                                               ,SALES.SALES_AMOUNT)
                                    END
                               END AS RETURNED_AMOUNT_EXC --返品金額(売上は税抜き)(以外)
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                               --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) * -1) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                               --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) * -1) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE (DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0) * -1)
                               --                                 END
                               --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT) * -1
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT) * -1
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SALES.TAX_DIVISION
                                               ,'3'
                                               ,0
                                               ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                             ,1
                                                             ,1)
                                                      ,'-'
                                                      ,SALES.TAX_AMOUNT * -1
                                                      ,SALES.TAX_AMOUNT) * -1)
                                    END
                               END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                              ,0 AS CREDIT_AMOUNT
                        FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                      ,SALES.VENDER_CD
                                      ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                      ,SALES.TAX_DIVISION
                                      ,SALES.SALES_AMOUNT
                                      ,SALES.TAX_AMOUNT
                                      ,SALES.DATA_TYPE
                                      ,SALES.DATA_TOTAL_DIVISION
                                      ,SALES.CATEGORY_DIVISION
                                      ,SALES.DEBIT_TITLE_CD
                                      ,SALES.CREDIT_TITLE_CD
                                FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                      ,VENDER                  VENDER
                                WHERE  SALES.SALES_DATE <= p_daCreditDate
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       c_DataDivUriHenpin --返品
                                AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                AND    SALES.TMP_UNITPRICE_FLG =
                                       c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                AND    SALES.VENDER_CD = VENDER.VENDER_CD
                                AND    VENDER.VENDER_DIVISION = c_VenderDivTS) SALES
                              ,CLASSIFICATION CLASSIFICATION --分類マスタ
                              ,ACCOUNTS AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                        WHERE  SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    SALES.DATA_TOTAL_DIVISION =
                               CLASSIFICATION.DATA_TOTAL_DIVISION
                        AND    SALES.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    SALES.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    SALES.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+)
                        UNION ALL
                        SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                              ,DECODE(SALES.INVOICE_CD
                                     ,NULL
                                     ,SALES.VENDER_CD
                                     ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS DISCOUNT_AMOUNT_CRE        --値引金額(内税の場合は＋消費税額)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS DISCOUNT_AMOUNT_ACC        --値引金額(内税の場合は＋消費税額)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                 END
                               --                     END AS DISCOUNT_AMOUNT_EXC        --値引金額(内税の場合は＋消費税額)(以外)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS DISCOUNT_AMOUNT_CRE --値引金額(売上は税抜き)(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS DISCOUNT_AMOUNT_ACC --値引金額(売上は税抜き)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                      ,1
                                                      ,1)
                                               ,'-'
                                               ,SALES.SALES_AMOUNT * -1
                                               ,SALES.SALES_AMOUNT)
                                    END
                               END AS DISCOUNT_AMOUNT_EXC --値引金額(売上は税抜き)(以外)
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                               --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) * -1) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                               --                    ,(DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) * -1) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE (DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0) * -1)
                               --                                 END
                               --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT) * -1
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT) * -1
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SALES.TAX_DIVISION
                                               ,'3'
                                               ,0
                                               ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                             ,1
                                                             ,1)
                                                      ,'-'
                                                      ,SALES.TAX_AMOUNT * -1
                                                      ,SALES.TAX_AMOUNT) * -1)
                                    END
                               END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                              ,0 AS CREDIT_AMOUNT
                        FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                      ,SALES.VENDER_CD
                                      ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                      ,SALES.TAX_DIVISION
                                      ,SALES.SALES_AMOUNT
                                      ,SALES.TAX_AMOUNT
                                      ,SALES.DATA_TYPE
                                      ,SALES.DATA_TOTAL_DIVISION
                                      ,SALES.CATEGORY_DIVISION
                                      ,SALES.DEBIT_TITLE_CD
                                      ,SALES.CREDIT_TITLE_CD
                                FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                      ,VENDER                  VENDER
                                WHERE  SALES.SALES_DATE <= p_daCreditDate
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       c_DataDivUriNebiki --値引
                                AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                AND    SALES.TMP_UNITPRICE_FLG =
                                       c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                AND    SALES.VENDER_CD = VENDER.VENDER_CD
                                AND    VENDER.VENDER_DIVISION = c_VenderDivTS) SALES
                              ,CLASSIFICATION CLASSIFICATION --分類マスタ
                              ,ACCOUNTS AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                        WHERE  SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    SALES.DATA_TOTAL_DIVISION =
                               CLASSIFICATION.DATA_TOTAL_DIVISION
                        AND    SALES.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    SALES.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    SALES.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+)
                        UNION ALL
                        SELECT SALES.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --担当部署コード
                              ,DECODE(SALES.INVOICE_CD
                                     ,NULL
                                     ,SALES.VENDER_CD
                                     ,SALES.INVOICE_CD) AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS OTHER_SALES_AMOUNT_CRE        --その他売上金額(内税の場合は＋消費税額)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                        , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                                                                              , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                                                                             , 0)
                               --                     ) AS OTHER_SALES_AMOUNT_ACC        --その他売上金額(内税の場合は＋消費税額)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '2', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', (SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)*-1, SALES.SALES_AMOUNT+SALES.TAX_AMOUNT)
                               --                                                                       , DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.SALES_AMOUNT*-1, SALES.SALES_AMOUNT))
                               --                                 END
                               --                     END AS OTHER_SALES_AMOUNT_EXC        --その他売上金額(内税の場合は＋消費税額)(以外)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売上は税抜き)(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                   ,1
                                                   ,1)
                                            ,'-'
                                            ,SALES.SALES_AMOUNT * -1
                                            ,SALES.SALES_AMOUNT)
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                          ,1
                                                          ,1)
                                                   ,'-'
                                                   ,SALES.SALES_AMOUNT * -1
                                                   ,SALES.SALES_AMOUNT)
                                            ,0)) AS OTHER_SALES_AMOUNT_ACC --その他売上金額(売上は税抜き)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                      ,1
                                                      ,1)
                                               ,'-'
                                               ,SALES.SALES_AMOUNT * -1
                                               ,SALES.SALES_AMOUNT)
                                    END
                               END AS OTHER_SALES_AMOUNT_EXC --その他売上金額(売上は税抜き)(以外)
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 10, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) AS TAX_AMOUNT_CRE        --消費税額(区分=外税のみ)(売掛金)
                               --                    ,DECODE(AC_KARI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                        , DECODE(AC_KASI.ARTICLE_ACCOUNTS, 11, DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                                                                             , 0)
                               --                     ) AS TAX_AMOUNT_ACC        --消費税額(区分=外税のみ)(未収金)
                               --                    ,CASE WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                          ELSE 
                               --                                 CASE WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10,11) THEN 0
                               --                                         ELSE DECODE(SALES.TAX_DIVISION, '1', DECODE(SUBSTR(SALES.CATEGORY_DIVISION, 1, 1), '-', SALES.TAX_AMOUNT*-1, SALES.TAX_AMOUNT), 0)
                               --                                 END
                               --                     END AS TAX_AMOUNT_EXC        --消費税額(区分=外税のみ)(以外)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 10 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 10) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT)
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_CRE --消費税額(区分=3非課税以外)(売掛金)
                              ,CASE
                                   WHEN (AC_KARI.ARTICLE_ACCOUNTS = 11 OR
                                        AC_KASI.ARTICLE_ACCOUNTS = 11) AND
                                        SALES.TAX_DIVISION != '3' THEN
                                    DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                 ,1
                                                 ,1)
                                          ,'-'
                                          ,SALES.TAX_AMOUNT * -1
                                          ,SALES.TAX_AMOUNT)
                                   ELSE
                                    0
                               END AS TAX_AMOUNT_ACC --消費税額(区分=3非課税以外)(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         DECODE(SALES.TAX_DIVISION
                                               ,'3'
                                               ,0
                                               ,DECODE(SUBSTR(SALES.CATEGORY_DIVISION
                                                             ,1
                                                             ,1)
                                                      ,'-'
                                                      ,SALES.TAX_AMOUNT * -1
                                                      ,SALES.TAX_AMOUNT))
                                    END
                               END AS TAX_AMOUNT_EXC --消費税額(区分=3非課税以外)(以外)
                              ,0 AS CREDIT_AMOUNT
                        FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                      ,SALES.VENDER_CD
                                      ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                                      ,SALES.TAX_DIVISION
                                      ,SALES.SALES_AMOUNT
                                      ,SALES.TAX_AMOUNT
                                      ,SALES.DATA_TYPE
                                      ,SALES.DATA_TOTAL_DIVISION
                                      ,SALES.CATEGORY_DIVISION
                                      ,SALES.DEBIT_TITLE_CD
                                      ,SALES.DEBIT_SUB_TITLE_CD
                                      ,SALES.CREDIT_TITLE_CD
                                      ,SALES.CREDIT_SUB_TITLE_CD
                                FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                      ,VENDER                  VENDER
                                WHERE  SALES.SALES_DATE <= p_daCreditDate
                                AND    SALES.DATA_TOTAL_DIVISION =
                                       c_DataDivUriSonota --その他売上
                                AND    SALES.DEPOSIT_UPDATE_DIVISION =
                                       c_DepositUpdDiv0 --未処理
                                AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                                AND    SALES.TMP_UNITPRICE_FLG =
                                       c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                                      --                      AND SALES.APPROVAL_STATUS = c_ApprovalSts3            --承認済
                                AND    SALES.VENDER_CD = VENDER.VENDER_CD
                                AND    VENDER.VENDER_DIVISION = c_VenderDivTS) SALES
                              ,CLASSIFICATION CLASSIFICATION --分類マスタ
                              ,ACCOUNTS AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS AC_KASI --科目マスタ(貸方)
                        WHERE  SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    SALES.DATA_TOTAL_DIVISION =
                               CLASSIFICATION.DATA_TOTAL_DIVISION
                        AND    SALES.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    SALES.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    SALES.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+))
                GROUP  BY ORGANIZATION_CD, VENDER_CD
                UNION ALL
                SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                      ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                      ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                      ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                      ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                      ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                      ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                      ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                      ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                      ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                      ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                      ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                      ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                      ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                      ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                      ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                      ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                      ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                      ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                      ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                      ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                      ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                      ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                      ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                      ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                FROM   (SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         CREDIT.CREDIT_AMOUNT
                                    END
                               END AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                              ,0 AS TAX_AMOUNT_CRE
                              ,0 AS TAX_AMOUNT_ACC
                              ,0 AS TAX_AMOUNT_EXC
                              ,0 AS CREDIT_AMOUNT
                        FROM   TEMPORARY_DEPOSIT_CREDIT CREDIT --仮締め売掛入金トラン
                              ,CLASSIFICATION           CLASSIFICATION --分類マスタ
                              ,ACCOUNTS                 AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS                 AC_KASI --科目マスタ(貸方)
                        WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                        AND    CREDIT.DATA_TOTAL_DIVISION =
                               c_DataDivNyuNyukin --入金
                        AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                               c_DepositUpdDiv0 --未処理
                        AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                        AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                        AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    CREDIT.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    CREDIT.CREDIT_TITLE_CD =
                               AC_KASI.ACCOUNTS_CD(+)
                        UNION ALL
                        SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_CRE --入金相殺金額(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_ACC --入金相殺金額(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         CREDIT.CREDIT_AMOUNT
                                    END
                               END AS OFFSET_AMOUNT_EXC --入金相殺金額(以外)
                              ,0 AS TAX_AMOUNT_CRE
                              ,0 AS TAX_AMOUNT_ACC
                              ,0 AS TAX_AMOUNT_EXC
                              ,0 AS CREDIT_AMOUNT
                        FROM   TEMPORARY_DEPOSIT_CREDIT CREDIT --仮締め売掛入金トラン
                              ,CLASSIFICATION           CLASSIFICATION --分類マスタ
                              ,ACCOUNTS                 AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS                 AC_KASI --科目マスタ(貸方)
                        WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                        AND    CREDIT.DATA_TOTAL_DIVISION =
                               c_DataDivNyuSousai --相殺
                        AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                               c_DepositUpdDiv0 --未処理
                        AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                        AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                        AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    CREDIT.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    CREDIT.CREDIT_TITLE_CD =
                               AC_KASI.ACCOUNTS_CD(+)
                        UNION ALL
                        SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,CREDIT.CREDIT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,CREDIT.CREDIT_AMOUNT
                                            ,0)) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         CREDIT.CREDIT_AMOUNT
                                    END
                               END AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,0 AS OFFSET_AMOUNT_CRE
                              ,0 AS OFFSET_AMOUNT_ACC
                              ,0 AS OFFSET_AMOUNT_EXC
                              ,0 AS TAX_AMOUNT_CRE
                              ,0 AS TAX_AMOUNT_ACC
                              ,0 AS TAX_AMOUNT_EXC
                              ,0 AS CREDIT_AMOUNT
                        FROM   TEMPORARY_DEPOSIT_CREDIT CREDIT --仮締め売掛入金トラン
                              ,CLASSIFICATION           CLASSIFICATION --分類マスタ
                              ,ACCOUNTS                 AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS                 AC_KASI --科目マスタ(貸方)
                        WHERE  CREDIT.CREDIT_DATE <= p_daCreditDate
                        AND    CREDIT.DATA_TOTAL_DIVISION =
                               c_DataDivNyuSonota --その他入金
                        AND    CREDIT.DEPOSIT_UPDATE_DIVISION =
                               c_DepositUpdDiv0 --未処理
                        AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
                        AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                        AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    CREDIT.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    CREDIT.CREDIT_TITLE_CD =
                               AC_KASI.ACCOUNTS_CD(+))
                GROUP  BY ORGANIZATION_CD, VENDER_CD
                UNION ALL
                SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                      ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                      ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                      ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                      ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                      ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                      ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                      ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                      ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                      ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                      ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                      ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                      ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                      ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                      ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                      ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                      ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                      ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                      ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                      ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                      ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                      ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                      ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                      ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                      ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                FROM   (SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,PAYMENT.SUPPLIER_CD AS VENDER_CD --仕入先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,PAYMENT.PAYMENT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,PAYMENT.PAYMENT_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_CRE --支払相殺金額(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,PAYMENT.PAYMENT_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,PAYMENT.PAYMENT_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_ACC --支払相殺金額(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         PAYMENT.PAYMENT_AMOUNT
                                    END
                               END AS OFFSET_AMOUNT_EXC --支払相殺金額(以外)
                              ,0 AS TAX_AMOUNT_CRE
                              ,0 AS TAX_AMOUNT_ACC
                              ,0 AS TAX_AMOUNT_EXC
                              ,0 AS CREDIT_AMOUNT
                        FROM   TEMPORARY_DEPOSIT_PAYMENT PAYMENT --仮締め売掛支払トラン
                              ,CLASSIFICATION            CLASSIFICATION --分類マスタ
                              ,ACCOUNTS                  AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS                  AC_KASI --科目マスタ(貸方)
                        WHERE  PAYMENT.PAYMENT_DATE <= p_daCreditDate
                        AND    PAYMENT.DATA_TOTAL_DIVISION =
                               c_DataDivShiSousai --相殺
                        AND    PAYMENT.DEPOSIT_UPDATE_DIVISION =
                               c_DepositUpdDiv0 --未処理
                        AND    PAYMENT.DELIVERY_UPDATE_DATE IS NULL
                        AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                        AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    PAYMENT.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    PAYMENT.DEBIT_TITLE_CD =
                               AC_KARI.ACCOUNTS_CD(+)
                        AND    PAYMENT.CREDIT_TITLE_CD =
                               AC_KASI.ACCOUNTS_CD(+))
                GROUP  BY ORGANIZATION_CD, VENDER_CD
                UNION ALL
                SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                      ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                      ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                      ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                      ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                      ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                      ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                      ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                      ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                      ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                      ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                      ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                      ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                      ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                      ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                      ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                      ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                      ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                      ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                      ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                      ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                      ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                      ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                      ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                      ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                FROM   (SELECT OFFSET.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                              ,OFFSET.VENDER_CD AS VENDER_CD --請求先コード
                              ,0 AS BALANCE_FORWARD_CRE
                              ,0 AS BALANCE_FORWARD_ACC
                              ,0 AS BALANCE_FORWARD_EXC
                              ,0 AS SALES_AMOUNT_CRE
                              ,0 AS SALES_AMOUNT_ACC
                              ,0 AS SALES_AMOUNT_EXC
                              ,0 AS DEPOSIT_AMOUNT_CRE
                              ,0 AS DEPOSIT_AMOUNT_ACC
                              ,0 AS DEPOSIT_AMOUNT_EXC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0 AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0 AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0 AS RETURNED_AMOUNT_CRE
                              ,0 AS RETURNED_AMOUNT_ACC
                              ,0 AS RETURNED_AMOUNT_EXC
                              ,0 AS DISCOUNT_AMOUNT_CRE
                              ,0 AS DISCOUNT_AMOUNT_ACC
                              ,0 AS DISCOUNT_AMOUNT_EXC
                              ,0 AS OTHER_SALES_AMOUNT_CRE
                              ,0 AS OTHER_SALES_AMOUNT_ACC
                              ,0 AS OTHER_SALES_AMOUNT_EXC
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,10
                                     ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,10
                                            ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_CRE --売掛相殺金額(売掛金)
                              ,DECODE(AC_KARI.ARTICLE_ACCOUNTS
                                     ,11
                                     ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                     ,DECODE(AC_KASI.ARTICLE_ACCOUNTS
                                            ,11
                                            ,OFFSET.DEPOSIT_OFFSET_AMOUNT
                                            ,0)) AS OFFSET_AMOUNT_ACC --売掛相殺金額(未収金)
                              ,CASE
                                   WHEN AC_KARI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                    0
                                   ELSE
                                    CASE
                                        WHEN AC_KASI.ARTICLE_ACCOUNTS IN (10, 11) THEN
                                         0
                                        ELSE
                                         OFFSET.DEPOSIT_OFFSET_AMOUNT
                                    END
                               END AS OFFSET_AMOUNT_EXC --売掛相殺金額(以外)
                              ,0 AS TAX_AMOUNT_CRE
                              ,0 AS TAX_AMOUNT_ACC
                              ,0 AS TAX_AMOUNT_EXC
                              ,0 AS CREDIT_AMOUNT
                        FROM   TMPDEPT_OFFSET_GROUP_DATA OFFSET --仮締め売掛グループ間トラン
                              ,CLASSIFICATION            CLASSIFICATION --分類マスタ
                              ,ACCOUNTS                  AC_KARI --科目マスタ(借方)
                              ,ACCOUNTS                  AC_KASI --科目マスタ(貸方)
                        WHERE  OFFSET.OFFSET_DATE <= p_daCreditDate
                        AND    OFFSET.DATA_TOTAL_DIVISION =
                               c_DataDivSouSousai --グループ間相殺
                        AND    OFFSET.DEPOSIT_UPDATE_DIVISION =
                               c_DepositUpdDiv0 --未処理
                        AND    OFFSET.DELIVERY_UPDATE_DATE IS NULL
                        AND    OFFSET.DEPOSIT_OFFSET_AMOUNT IS NOT NULL --売掛相殺金額
                        AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
                        AND    OFFSET.VENDER_DIVISION = c_VenderDivTS --得意先
                        AND    OFFSET.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                        AND    OFFSET.CATEGORY_DIVISION =
                               CLASSIFICATION.CATEGORY_DIVISION
                        AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                              --                AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD(+)),0)
                              --                AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD(+)
                              --                AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD(+)),0)
                        AND    OFFSET.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
                        AND    OFFSET.CREDIT_TITLE_CD =
                               AC_KASI.ACCOUNTS_CD(+))
                GROUP  BY ORGANIZATION_CD, VENDER_CD
                UNION ALL
                SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
                      ,VENDER_CD AS VENDER_CD --請求先コード
                      ,SUM(BALANCE_FORWARD_CRE) AS BALANCE_FORWARD_CRE --前月繰越(売掛金)
                      ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(未収金)
                      ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
                      ,SUM(SALES_AMOUNT_CRE) AS SALES_AMOUNT_CRE --売上金額(売掛金)
                      ,SUM(SALES_AMOUNT_ACC) AS SALES_AMOUNT_ACC --売上金額(未収金)
                      ,SUM(SALES_AMOUNT_EXC) AS SALES_AMOUNT_EXC --売上金額(以外)
                      ,SUM(DEPOSIT_AMOUNT_CRE) AS DEPOSIT_AMOUNT_CRE --入金金額(売掛金)
                      ,SUM(DEPOSIT_AMOUNT_ACC) AS DEPOSIT_AMOUNT_ACC --入金金額(未収金)
                      ,SUM(DEPOSIT_AMOUNT_EXC) AS DEPOSIT_AMOUNT_EXC --入金金額(以外)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_CRE) AS OTHER_DEPOSIT_AMOUNT_CRE --その他入金金額(売掛金)
                      ,SUM(OTHER_DEPOSIT_AMOUNT_ACC) AS OTHER_DEPOSIT_AMOUNT_ACC --その他入金金額
                      ,SUM(OTHER_DEPOSIT_AMOUNT_EXC) AS OTHER_DEPOSIT_AMOUNT_EXC --その他入金金額(以外)
                      ,SUM(RETURNED_AMOUNT_CRE) AS RETURNED_AMOUNT_CRE --返品金額(売掛金)
                      ,SUM(RETURNED_AMOUNT_ACC) AS RETURNED_AMOUNT_ACC --返品金額(未収金)
                      ,SUM(RETURNED_AMOUNT_EXC) AS RETURNED_AMOUNT_EXC --返品金額(以外)
                      ,SUM(DISCOUNT_AMOUNT_CRE) AS DISCOUNT_AMOUNT_CRE --値引金額(売掛金)
                      ,SUM(DISCOUNT_AMOUNT_ACC) AS DISCOUNT_AMOUNT_ACC --値引金額(未収金)
                      ,SUM(DISCOUNT_AMOUNT_EXC) AS DISCOUNT_AMOUNT_EXC --値引金額(以外)
                      ,SUM(OTHER_SALES_AMOUNT_CRE) AS OTHER_SALES_AMOUNT_CRE --その他売上金額(売掛金)
                      ,SUM(OTHER_SALES_AMOUNT_ACC) AS OTHER_SALES_AMOUNT_ACC --その他売上金額
                      ,SUM(OTHER_SALES_AMOUNT_EXC) AS OTHER_SALES_AMOUNT_EXC --その他売上金額(以外)
                      ,SUM(OFFSET_AMOUNT_CRE) AS OFFSET_AMOUNT_CRE --相殺金額(売掛金)
                      ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(未収金)
                      ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
                      ,SUM(TAX_AMOUNT_CRE) AS TAX_AMOUNT_CRE --消費税額(売掛金)
                      ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(未収金)
                      ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
                      ,SUM(CREDIT_AMOUNT) AS CREDIT_AMOUNT --売掛残
                FROM   (SELECT HEADER.ORGANIZATION_CD         AS ORGANIZATION_CD --部署コード
                              ,HEADER.VENDER_CD               AS VENDER_CD --請求先コード
                              ,HEADER.CREDIT_SALES_BREAKDOWN  AS BALANCE_FORWARD_CRE --売掛残(売掛金)
                              ,HEADER.ACCRUED_DEBIT_BREAKDOWN AS BALANCE_FORWARD_ACC --売掛残(未収金)
                              ,HEADER.EXCEPT_BREAKDOWN        AS BALANCE_FORWARD_EXC --売掛残(以外)
                              ,0                              AS SALES_AMOUNT_CRE
                              ,0                              AS SALES_AMOUNT_ACC
                              ,0                              AS SALES_AMOUNT_EXC
                              ,0                              AS DEPOSIT_AMOUNT_CRE
                              ,0                              AS DEPOSIT_AMOUNT_ACC
                              ,0                              AS DEPOSIT_AMOUNT_EXC
                              ,0                              AS OTHER_DEPOSIT_AMOUNT_CRE
                              ,0                              AS OTHER_DEPOSIT_AMOUNT_ACC
                              ,0                              AS OTHER_DEPOSIT_AMOUNT_EXC
                              ,0                              AS RETURNED_AMOUNT_CRE
                              ,0                              AS RETURNED_AMOUNT_ACC
                              ,0                              AS RETURNED_AMOUNT_EXC
                              ,0                              AS DISCOUNT_AMOUNT_CRE
                              ,0                              AS DISCOUNT_AMOUNT_ACC
                              ,0                              AS DISCOUNT_AMOUNT_EXC
                              ,0                              AS OTHER_SALES_AMOUNT_CRE
                              ,0                              AS OTHER_SALES_AMOUNT_ACC
                              ,0                              AS OTHER_SALES_AMOUNT_EXC
                              ,0                              AS OFFSET_AMOUNT_CRE
                              ,0                              AS OFFSET_AMOUNT_ACC
                              ,0                              AS OFFSET_AMOUNT_EXC
                              ,0                              AS TAX_AMOUNT_CRE
                              ,0                              AS TAX_AMOUNT_ACC
                              ,0                              AS TAX_AMOUNT_EXC
                              ,0                              AS CREDIT_AMOUNT
                        FROM   DEPOSIT_HEADER HEADER --売掛ヘッダー
                              ,(SELECT ORGANIZATION_CD
                                      ,VENDER_CD
                                      ,MAX(CREDIT_DATE) MAX_DATE
                                FROM   DEPOSIT_HEADER
                                WHERE  CREDIT_DATE < p_daCreditDate
                                GROUP  BY ORGANIZATION_CD, VENDER_CD) MAX_HEADER
                        WHERE  HEADER.CREDIT_AMOUNT <> 0
                        AND    HEADER.ORGANIZATION_CD =
                               MAX_HEADER.ORGANIZATION_CD
                        AND    HEADER.VENDER_CD = MAX_HEADER.VENDER_CD
                        AND    HEADER.CREDIT_DATE = MAX_HEADER.MAX_DATE)
                GROUP  BY ORGANIZATION_CD, VENDER_CD) TRN
              ,(SELECT VENDER_CD
                FROM   VENDER
                WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                       ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
                AND    VENDER_DIVISION = c_VenderDivTS
                AND    (PAYMENT_INVOICE_CD IS NULL OR
                      VENDER.PAYMENT_INVOICE_CD = VENDER_CD)) VENDER
        WHERE  TRN.VENDER_CD = VENDER.VENDER_CD
        GROUP  BY TRN.ORGANIZATION_CD, TRN.VENDER_CD
        ORDER  BY ORGANIZATION_CD, VENDER_CD;
BEGIN
    -- ログ出力変数初期化  
    TRNERRMAN.INIT_LOG(DEF_PROC_NAME);

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '処理開始', P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME
                    ,'初期処理：実行条件取得'
                    ,P_STR_INPUTOR_CD);

    msg := '実行条件＝';
    msg := msg || '部署コード：' || NVL(P_STR_ORGANIZATION_CD, '') || ',';
    msg := msg || '締め年月：' || P_STR_CREDIT_DATE || ',';

    IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
        msg := msg || '締め処理区分：仮締め';
    ELSE
        msg := msg || '締め処理区分：本締め';
    END IF;

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, msg, P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理開始', P_STR_INPUTOR_CD);

    -- 文字型をDate型へキャスト
    daCreditDate := TO_DATE(P_STR_CREDIT_DATE, 'YYYY/MM/DD');
    --------------------------------------------------------------------------------
    --仮締めデータ削除処理
    --------------------------------------------------------------------------------
    -- 2017/04/06 update ロック停止 -->
    --    IF TRNERRMAN.LOCK_PROC(5) = 0 THEN
    --        ROLLBACK;
    --        P_NUM_RET := 9;
    --
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
    --        -- 2015/11/09 insert ログ追加 -->
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
    --        -- <-----------------------------
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
    --       RETURN;
    --    END IF;
    -- <-----------------------------

    -------------------------------------------------
    --★仮締め売掛ヘッダー削除
    -------------------------------------------------
    DELETE FROM TEMPORARY_DEPOSIT_HEADER
    WHERE  VENDER_CD IN
           (SELECT VENDER_CD
            FROM   VENDER
            WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (PAYMENT_INVOICE_CD IS NULL OR
                  PAYMENT_INVOICE_CD = VENDER_CD)
            AND    VENDER_DIVISION = c_VenderDivTS);

    -- 2017/04/06 update ロック停止 -->
    --    IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
    --        ROLLBACK;
    --        P_NUM_RET := 9;
    --
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
    --        -- 2015/11/09 insert ログ追加 -->
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
    --        -- <-----------------------------
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
    --       RETURN;
    --    END IF;
    -- <-----------------------------

    -------------------------------------------------
    --★仮締め売掛売上トランザクション削除
    -------------------------------------------------
    DELETE FROM TEMPORARY_DEPOSIT_SALES
    WHERE  DECODE(INVOICE_CD, NULL, VENDER_CD, INVOICE_CD) IN
           (SELECT VENDER_CD
            FROM   VENDER
            WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (PAYMENT_INVOICE_CD IS NULL OR
                  PAYMENT_INVOICE_CD = VENDER_CD)
            AND    VENDER_DIVISION = c_VenderDivTS);

    -- 2017/04/06 update ロック停止 -->
    --    IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
    --        ROLLBACK;
    --        P_NUM_RET := 9;
    --
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
    --        -- 2015/11/09 insert ログ追加 -->
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
    --        -- <-----------------------------
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
    --       RETURN;
    --    END IF;
    -- <-----------------------------

    -------------------------------------------------
    --★仮締め売掛入金トランザクション削除
    -------------------------------------------------
    DELETE FROM TEMPORARY_DEPOSIT_CREDIT
    WHERE  VENDER_CD IN
           (SELECT VENDER_CD
            FROM   VENDER
            WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (PAYMENT_INVOICE_CD IS NULL OR
                  PAYMENT_INVOICE_CD = VENDER_CD)
            AND    VENDER_DIVISION = c_VenderDivTS);

    -- 2017/04/06 update ロック停止 -->
    --    IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
    --        ROLLBACK;
    --        P_NUM_RET := 9;
    --
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
    --        -- 2015/11/09 insert ログ追加 -->
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
    --        -- <-----------------------------
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
    --       RETURN;
    --    END IF;
    -- <-----------------------------

    -------------------------------------------------
    --★仮締め売掛支払トランザクション削除
    -------------------------------------------------
    DELETE FROM TEMPORARY_DEPOSIT_PAYMENT
    WHERE  SUPPLIER_CD IN
           (SELECT VENDER_CD
            FROM   VENDER
            WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (PAYMENT_INVOICE_CD IS NULL OR
                  PAYMENT_INVOICE_CD = VENDER_CD)
            AND    VENDER_DIVISION = c_VenderDivTS);

    -- 2017/04/06 update ロック停止 -->
    --    IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
    --        ROLLBACK;
    --        P_NUM_RET := 9;
    --
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
    --        -- 2015/11/09 insert ログ追加 -->
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
    --        -- <-----------------------------
    --        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
    --       RETURN;
    --    END IF;
    -- <-----------------------------

    -------------------------------------------------
    --★仮締め売掛グループ間相殺トランザクション削除
    -------------------------------------------------
    DELETE FROM TMPDEPT_OFFSET_GROUP_DATA
    WHERE  VENDER_CD IN
           (SELECT VENDER_CD
            FROM   VENDER
            WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (PAYMENT_INVOICE_CD IS NULL OR
                  PAYMENT_INVOICE_CD = VENDER_CD)
            AND    VENDER_DIVISION = c_VenderDivTS);
    --------------------------------------------------------------------------------
    --【仮締め】の場合
    --------------------------------------------------------------------------------
    IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
        -- 2017/04/06 update ロック停止 -->
        --        IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
        --            ROLLBACK;
        --            P_NUM_RET := 9;
        --
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
        --            -- 2015/11/09 insert ログ追加 -->
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
        --            -- <-----------------------------
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
        --           RETURN;
        --        END IF;
        -- <-----------------------------
    
        -------------------------------------------------
        --★仮締め売掛売上トランザクション登録
        -------------------------------------------------
        INSERT INTO TEMPORARY_DEPOSIT_SALES
            SELECT SALES.*
            FROM   (SELECT SALES.SALES_DATE
                          ,SALES.SALES_NO
                          ,SALES.CANCEL_SALES_NO
                          ,SALES.ITEM_CD
                          ,SALES.ORGANIZATION_CD
                          ,SALES.VENDER_CD
                          ,SALES.BALANCE_CD
                          ,SALES.INPUT_TANTO_CD
                          ,SALES.SALES_TANTO_CD
                          ,SALES.PRODUCT_LOTNO
                          ,SALES.ORDER_NO
                          ,SALES.ORDER_ROW_NO
                          ,SALES.SALES_QUANTITY
                          ,SALES.SALES_UNITPRICE
                          ,SALES.STANDARD_UNITPRICE
                          ,SALES.STANDARD_DISCOUNT
                          ,SALES.SPECIAL_DISCOUNT
                          ,SALES.TMP_UNITPRICE_FLG
                          ,SALES.REMARK
                          ,SALES.DISBURSEMENT_LOCATION_CD
                          ,SALES.DELIVERY_COMP
                          ,SALES.DELIVERY_UPDATE_DATE
                          ,SALES.INVOICE_UPDATE_DATE
                          ,SALES.INPUT_DIVISION
                          ,SALES.DELIVERY_CD
                          ,SALES.TAX_DIVISION
                          ,SALES.TAX_AMOUNT
                          ,VENDER.PAYMENT_INVOICE_CD AS INVOICE_CD
                          ,SALES.SALES_AMOUNT
                          ,SALES.SALES_BASIC
                          ,SALES.TAX_RATIO
                          ,SALES.UPDATE_FLG
                          ,SALES.CONFIG_EXP_RATIO
                          ,SALES.RATIO_NOTAPPLY_SALES_UNITPRICE
                          ,SALES.SHIPPING_NO
                          ,SALES.SALES_SLIP_NO
                          ,SALES.SALES_SLIP_ROW_NO
                          ,SALES.SLIP_PUBLISH_COMP
                          ,SALES.SLIP_PUBLISH_DATE
                          ,SALES.TRANSMISSION_DATE
                          ,SALES.LEDGER_POST_DATE
                          ,SALES.LEDGER_POST_NO
                          ,SALES.SALES_CONVERT_QUANTITY
                          ,SALES.ACCEPT_CONVERT_QUANTITY
                          ,SALES.DATA_TYPE
                          ,SALES.DATA_TOTAL_DIVISION
                          ,SALES.CATEGORY_DIVISION
                          ,SALES.ACCOUNT_YEARS
                          ,SALES.ACCOUNT_DEBIT_SECTION_CD
                          ,SALES.ACCOUNT_CREDIT_SECTION_CD
                          ,SALES.DEBIT_TITLE_CD
                          ,SALES.DEBIT_SUB_TITLE_CD
                          ,SALES.CREDIT_TITLE_CD
                          ,SALES.CREDIT_SUB_TITLE_CD
                          ,SALES.DEPOSIT_TARGET_DIVISION
                          ,SALES.CLAIM_TARGET_DIVISION
                          ,SALES.SUMMARY_CD
                          ,SALES.SUMMARY
                          ,SALES.DEPOSIT_UPDATE_DIVISION
                          ,SALES.DEPOSIT_NO
                          ,SALES.CLAIM_UPDATE_DIVISION
                          ,SALES.CLAIM_NO
                          ,SALES.ERASER_COMPLETE_DIVISION
                          ,SALES.ERASER_COMPLETE_DATE
                          ,SALES.APPROVAL_STATUS
                          ,SALES.APPROVEDBY
                          ,SALES.APPROVALDATE
                          ,SALES.CHARGE_ORGANIZATION_CD
                          ,SALES.KEEP_FLAG
                          ,SALES.RY_CD
                          ,SALES.HOUSING_LOCATION_CD
                          ,SALES.PACKAGE_DIRECTION_NO
                          ,SALES.INPUT_DATE
                          ,SALES.INPUTOR_CD
                          ,SALES.UPDATE_DATE
                          ,SALES.UPDATOR_CD
                    FROM   SALES SALES
                          ,(SELECT VENDER_CD, PAYMENT_INVOICE_CD
                            FROM   VENDER
                            WHERE  ('ALL' =
                                   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                                   ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
                            AND    (PAYMENT_INVOICE_CD IS NULL OR
                                  PAYMENT_INVOICE_CD = VENDER_CD)
                            AND    VENDER_DIVISION = c_VenderDivTS) VENDER
                    WHERE  SALES.SALES_DATE <= daCreditDate
                    AND    SALES.DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 --未処理
                    AND    SALES.DELIVERY_UPDATE_DATE IS NULL
                    AND    SALES.TMP_UNITPRICE_FLG = c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                    AND    DECODE(SALES.INVOICE_CD
                                 ,NULL
                                 ,SALES.VENDER_CD
                                 ,SALES.INVOICE_CD) = VENDER.VENDER_CD) SALES --売上トラン
                  ,CLASSIFICATION CLASSIFICATION --分類マスタ
                  ,ACCOUNTS AC_KARI --科目マスタ(借方)
                  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
            WHERE  SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
            AND    SALES.DATA_TOTAL_DIVISION =
                   CLASSIFICATION.DATA_TOTAL_DIVISION
            AND    SALES.CATEGORY_DIVISION =
                   CLASSIFICATION.CATEGORY_DIVISION
            AND    SALES.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD
            AND    SALES.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD;
    
        -- 2017/04/06 update ロック停止 -->
        --        IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
        --            ROLLBACK;
        --            P_NUM_RET := 9;
        --
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
        --            -- 2015/11/09 insert ログ追加 -->
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
        --            -- <-----------------------------
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
        --           RETURN;
        --        END IF;
        -- <-----------------------------
    
        -------------------------------------------------
        --★仮締め売掛入金トランザクション登録
        -------------------------------------------------
        INSERT INTO TEMPORARY_DEPOSIT_CREDIT
            SELECT CREDIT.*
            FROM   CREDIT         CREDIT --入金トラン
                  ,CLASSIFICATION CLASSIFICATION --分類マスタ
                  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                  ,VENDER         VENDER --取引先マスタ
            WHERE  CREDIT.VENDER_CD = VENDER.VENDER_CD
            AND    ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                  VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (VENDER.PAYMENT_INVOICE_CD IS NULL OR
                  VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
            AND    VENDER.VENDER_DIVISION = c_VenderDivTS
            AND    CREDIT.CREDIT_DATE <= daCreditDate
            AND    CREDIT.DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 --未処理
            AND    CREDIT.DELIVERY_UPDATE_DATE IS NULL
            AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
            AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
            AND    CREDIT.CATEGORY_DIVISION =
                   CLASSIFICATION.CATEGORY_DIVISION
            AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD
            AND    CREDIT.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD
            --            AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD),0)
            --            AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD),0)
            ;
    
        -- 2017/04/06 update ロック停止 -->
        --        IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
        --            ROLLBACK;
        --            P_NUM_RET := 9;
        --
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
        --            -- 2015/11/09 insert ログ追加 -->
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
        --            -- <-----------------------------
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
        --           RETURN;
        --        END IF;
        -- <-----------------------------
    
        -------------------------------------------------
        --★仮締め売掛支払トランザクション登録
        -------------------------------------------------
        INSERT INTO TEMPORARY_DEPOSIT_PAYMENT
            SELECT PAYMENT.*
            FROM   PAYMENT        PAYMENT --支払トラン
                  ,CLASSIFICATION CLASSIFICATION --分類マスタ
                  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
                  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
                  ,VENDER         VENDER --取引先マスタ
            WHERE  PAYMENT.SUPPLIER_CD = VENDER.VENDER_CD
            AND    ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                  VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (VENDER.PAYMENT_INVOICE_CD IS NULL OR
                  VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
            AND    VENDER.VENDER_DIVISION = c_VenderDivTS
            AND    PAYMENT.PAYMENT_DATE <= daCreditDate
            AND    PAYMENT.DATA_TOTAL_DIVISION = c_DataDivShiSousai --相殺
            AND    PAYMENT.DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 --未処理
            AND    PAYMENT.DELIVERY_UPDATE_DATE IS NULL
            AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
            AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
            AND    PAYMENT.CATEGORY_DIVISION =
                   CLASSIFICATION.CATEGORY_DIVISION
            AND    PAYMENT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD
            AND    PAYMENT.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD
            --            AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD),0)
            --            AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD),0)
            ;
    
        -- 2017/04/06 update ロック停止 -->
        --        IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
        --            ROLLBACK;
        --            P_NUM_RET := 9;
        --
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
        --            -- 2015/11/09 insert ログ追加 -->
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
        --            -- <-----------------------------
        --            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
        --           RETURN;
        --        END IF;
        -- <-----------------------------
    
        -------------------------------------------------
        --★仮締めグループ間相殺トランザクション登録
        -------------------------------------------------
        INSERT INTO TMPDEPT_OFFSET_GROUP_DATA
            SELECT OFFSET.*
            FROM   OFFSET_GROUP_DATA OFFSET --グループ間トラン
                  ,CLASSIFICATION    CLASSIFICATION --分類マスタ
                  ,ACCOUNTS          AC_KARI --科目マスタ(借方)
                  ,ACCOUNTS          AC_KASI --科目マスタ(貸方)
                  ,VENDER            VENDER --取引先マスタ
            WHERE  OFFSET.VENDER_CD = VENDER.VENDER_CD
            AND    ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
                  VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
            AND    (VENDER.PAYMENT_INVOICE_CD IS NULL OR
                  VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
            AND    VENDER.VENDER_DIVISION = c_VenderDivTS
            AND    OFFSET.OFFSET_DATE <= daCreditDate
            AND    OFFSET.DATA_TOTAL_DIVISION = c_DataDivSouSousai --グループ間相殺
            AND    OFFSET.DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 --未処理
            AND    OFFSET.DELIVERY_UPDATE_DATE IS NULL
            AND    OFFSET.DEPOSIT_OFFSET_AMOUNT IS NOT NULL --売掛相殺金額
            AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
            AND    OFFSET.VENDER_DIVISION = c_VenderDivTS --得意先
            AND    OFFSET.DATA_TYPE = CLASSIFICATION.DATA_TYPE
            AND    OFFSET.CATEGORY_DIVISION =
                   CLASSIFICATION.CATEGORY_DIVISION
            AND    OFFSET.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD
            AND    OFFSET.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD
            --            AND CLASSIFICATION.DEBIT_ACCOUNTS_CD = AC_KARI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KARI.ACCOUNTS_SUB_CD),0)
            --            AND CLASSIFICATION.CREDIT_ACCOUNTS_CD = AC_KASI.ACCOUNTS_CD
            --            AND NVL(CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD,0) = NVL(TRIM(AC_KASI.ACCOUNTS_SUB_CD),0)
            ;
    END IF;
    --------------------------------------------------------------------------------
    --【仮締め】の場合
    --------------------------------------------------------------------------------
    IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
        --------------------------------------------------------------------------------
        --仮締め売上データ抽出処理
        --------------------------------------------------------------------------------
        /* 仮締め売上データ抽出 カーソル */
        FOR REC_MAIN IN curTempSales(daCreditDate) LOOP
            -- 売掛番号仮締め用取得(FUNCTION呼出)
            nvDepositNo := FUN_GET_DEPOSIT_NO_KARI(P_STR_INPUTOR_CD);
            -- 仮締め売掛ヘッダーデータ
            rtRecWork.DEPOSIT_NO              := nvDepositNo; -- 売掛番号
            rtRecWork.ORGANIZATION_CD         := REC_MAIN.ORGANIZATION_CD; -- 部署コード
            rtRecWork.VENDER_CD               := REC_MAIN.VENDER_CD; -- 請求先コード
            rtRecWork.CREDIT_DATE             := daCreditDate; -- 売掛締め日
            rtRecWork.BALANCE_FORWARD         := REC_MAIN.BALANCE_FORWARD; -- 前月繰越
            rtRecWork.SALES_AMOUNT            := REC_MAIN.SALES_AMOUNT; -- 売上金額
            rtRecWork.DEPOSIT_AMOUNT          := REC_MAIN.DEPOSIT_AMOUNT; -- 入金金額
            rtRecWork.OTHER_DEPOSIT_AMOUNT    := REC_MAIN.OTHER_DEPOSIT_AMOUNT; -- その他入金金額
            rtRecWork.RETURNED_AMOUNT         := REC_MAIN.RETURNED_AMOUNT; -- 返品金額
            rtRecWork.DISCOUNT_AMOUNT         := REC_MAIN.DISCOUNT_AMOUNT; -- 値引金額
            rtRecWork.OTHER_SALES_AMOUNT      := REC_MAIN.OTHER_SALES_AMOUNT; -- その他売上金額
            rtRecWork.OFFSET_AMOUNT           := REC_MAIN.OFFSET_AMOUNT; -- 相殺金額
            rtRecWork.TAX_AMOUNT              := REC_MAIN.TAX_AMOUNT; -- 消費税額
            rtRecWork.CREDIT_AMOUNT           := REC_MAIN.CREDIT_AMOUNT; -- 売掛残
            rtRecWork.CREDIT_SALES_BREAKDOWN  := REC_MAIN.CREDIT_SALES_BREAKDOWN; -- 売掛金(内訳)
            rtRecWork.ACCRUED_DEBIT_BREAKDOWN := REC_MAIN.ACCRUED_DEBIT_BREAKDOWN; -- 未収金(内訳)
            rtRecWork.EXCEPT_BREAKDOWN        := REC_MAIN.EXCEPT_BREAKDOWN; -- 以外(内訳)
            rtRecWork.INPUT_DATE              := SYSDATE; -- 登録日時
            rtRecWork.INPUTOR_CD              := P_STR_INPUTOR_CD; -- 登録者ＩＤ
            rtRecWork.UPDATE_DATE             := SYSDATE; -- 更新日時
            rtRecWork.UPDATOR_CD              := P_STR_INPUTOR_CD; -- 更新者ＩＤ
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(5) = 0 THEN
            --               ROLLBACK;
            --               P_NUM_RET := 9;
            --
            --               TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --               -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --               -- <-----------------------------
            --               TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★仮締め売掛ヘッダー登録
            --------------------------------------------------------------------------------
            INSERT INTO TEMPORARY_DEPOSIT_HEADER VALUES rtRecWork;
            ----------------------------------------------------------------------------------------------------------
            --【売掛対象】【売掛対象外】の各トランザクション更新処理
            ----------------------------------------------------------------------------------------------------------
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】仮締め売上トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                   ,VENDER                  VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            -- 請求更新未処理対象
            UPDATE TEMPORARY_DEPOSIT_SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                   ,VENDER                  VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】仮締め売上トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                   ,VENDER                  VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv0 --売掛対象外
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(1) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            -- 請求更新未処理対象
            UPDATE TEMPORARY_DEPOSIT_SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   TEMPORARY_DEPOSIT_SALES SALES --仮締め売掛売上トラン
                                   ,VENDER                  VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv0 --売掛対象外
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】仮締め入金トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TEMPORARY_DEPOSIT_CREDIT.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TEMPORARY_DEPOSIT_CREDIT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TEMPORARY_DEPOSIT_CREDIT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE TEMPORARY_DEPOSIT_CREDIT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            -- NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv1                                            -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                -- 売掛番号
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             TEMPORARY_DEPOSIT_CREDIT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TEMPORARY_DEPOSIT_CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TEMPORARY_DEPOSIT_CREDIT.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TEMPORARY_DEPOSIT_CREDIT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TEMPORARY_DEPOSIT_CREDIT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】仮締め入金トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
            -- 請求更新未処理対象
            --            UPDATE TEMPORARY_DEPOSIT_CREDIT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                         DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv9                                            -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             TEMPORARY_DEPOSIT_CREDIT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(2) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TEMPORARY_DEPOSIT_CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】仮締め支払トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TEMPORARY_DEPOSIT_PAYMENT.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TEMPORARY_DEPOSIT_PAYMENT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TEMPORARY_DEPOSIT_PAYMENT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                         DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                          c_DepositTrgDiv1                                            -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                -- 売掛番号
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             TEMPORARY_DEPOSIT_PAYMENT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TEMPORARY_DEPOSIT_PAYMENT.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TEMPORARY_DEPOSIT_PAYMENT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TEMPORARY_DEPOSIT_PAYMENT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】仮締め支払トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
            -- 請求更新未処理対象
            --            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                         DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                          c_DepositTrgDiv9                                            -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             TEMPORARY_DEPOSIT_PAYMENT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(3) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TEMPORARY_DEPOSIT_PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】仮締めグループ間相殺トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TMPDEPT_OFFSET_GROUP_DATA.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TMPDEPT_OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TMPDEPT_OFFSET_GROUP_DATA.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                         DEBIT_TITLE_CD
            --                        ,DEBIT_SUB_TITLE_CD
            --                        ,CREDIT_TITLE_CD
            --                        ,CREDIT_SUB_TITLE_CD
            --                        ,DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD            -- 会計部門
            --                          NVL(MAX(DEBIT_ACCOUNTS_CD),'') AS DEBIT_ACCOUNTS_CD            -- 借方科目コード
            --                         ,NVL(MAX(DEBIT_ACCOUNTS_SUB_CD),'') AS DEBIT_ACCOUNTS_SUB_CD    -- 借方補助科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_CD),'') AS CREDIT_ACCOUNTS_CD            -- 貸方科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_SUB_CD),'') AS CREDIT_ACCOUNTS_SUB_CD    -- 貸方補助科目コード
            --                         ,c_DepositTrgDiv1                                                -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                                -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                    -- 売掛番号
            --                         ,daCreditDate                                                    -- 売掛締め日
            --                         ,SYSDATE                                                        -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                                -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD
            --                         FROM
            --                              BUMON
            --                             ,CLASSIFICATION
            --                         WHERE
            --                                 TMPDEPT_OFFSET_GROUP_DATA.ORGANIZATION_CD        =    BUMON.SECTION_CD
            --                         AND    TMPDEPT_OFFSET_GROUP_DATA.DATA_TYPE                =    CLASSIFICATION.DATA_TYPE
            --                         AND    TMPDEPT_OFFSET_GROUP_DATA.CATEGORY_DIVISION        =    CLASSIFICATION.CATEGORY_DIVISION
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  TMPDEPT_OFFSET_GROUP_DATA.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    TMPDEPT_OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    TMPDEPT_OFFSET_GROUP_DATA.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】仮締めグループ間相殺トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            ;
            -- 請求更新未処理対象
            --            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                         DEBIT_TITLE_CD
            ---                        ,DEBIT_SUB_TITLE_CD
            --                        ,CREDIT_TITLE_CD
            --                        ,CREDIT_SUB_TITLE_CD
            --                        ,DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD            -- 会計部門
            --                          NVL(MAX(DEBIT_ACCOUNTS_CD),'') AS DEBIT_ACCOUNTS_CD            -- 借方科目コード
            --                         ,NVL(MAX(DEBIT_ACCOUNTS_SUB_CD),'') AS DEBIT_ACCOUNTS_SUB_CD    -- 借方補助科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_CD),'') AS CREDIT_ACCOUNTS_CD            -- 貸方科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_SUB_CD),'') AS CREDIT_ACCOUNTS_SUB_CD    -- 貸方補助科目コード
            --                         ,c_DepositTrgDiv9                                                -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                                -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                    -- 売掛締め日
            --                         ,SYSDATE                                                        -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                                -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD
            --                         FROM
            --                              BUMON
            --                             ,CLASSIFICATION
            --                         WHERE
            --                                 TMPDEPT_OFFSET_GROUP_DATA.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         AND    TMPDEPT_OFFSET_GROUP_DATA.DATA_TYPE                =    CLASSIFICATION.DATA_TYPE
            --                         AND    TMPDEPT_OFFSET_GROUP_DATA.CATEGORY_DIVISION        =    CLASSIFICATION.CATEGORY_DIVISION
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(4) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め売掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE TMPDEPT_OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            ;
            -- カウント
            nvCount := nvCount + 1;
        END LOOP;
        --対象データなしの場合
        IF nvCount = 0 THEN
            --ロールバック
            ROLLBACK;
            --戻り値設定
            P_NUM_RET := 9;
        
            TRNERRMAN.LOGOUT(DEF_PROC_NAME
                            ,'対象データがありません'
                            ,P_STR_INPUTOR_CD);
            TRNERRMAN.LOGOUT(DEF_PROC_NAME
                            ,'売掛更新処理終了'
                            ,P_STR_INPUTOR_CD);
        
            RETURN;
        END IF;
    ELSE
        --------------------------------------------------------------------------------
        --【本締め】の場合
        --------------------------------------------------------------------------------
        --------------------------------------------------------------------------------
        --売上データ抽出処理
        --------------------------------------------------------------------------------
        /* 売上データ抽出 カーソル */
        FOR REC_MAIN IN curSales(daCreditDate) LOOP
            -- 売掛番号取得
            nvDepositNo := FUN_GET_DEPOSIT_NO(P_STR_INPUTOR_CD);
            -- 売掛ヘッダーデータ
            rtRecWork.DEPOSIT_NO              := nvDepositNo; -- 売掛番号
            rtRecWork.ORGANIZATION_CD         := REC_MAIN.ORGANIZATION_CD; -- 部署コード
            rtRecWork.VENDER_CD               := REC_MAIN.VENDER_CD; -- 請求先コード
            rtRecWork.CREDIT_DATE             := daCreditDate; -- 売掛締め日
            rtRecWork.BALANCE_FORWARD         := REC_MAIN.BALANCE_FORWARD; -- 前月繰越
            rtRecWork.SALES_AMOUNT            := REC_MAIN.SALES_AMOUNT; -- 売上金額
            rtRecWork.DEPOSIT_AMOUNT          := REC_MAIN.DEPOSIT_AMOUNT; -- 入金金額
            rtRecWork.OTHER_DEPOSIT_AMOUNT    := REC_MAIN.OTHER_DEPOSIT_AMOUNT; -- その他入金金額
            rtRecWork.RETURNED_AMOUNT         := REC_MAIN.RETURNED_AMOUNT; -- 返品金額
            rtRecWork.DISCOUNT_AMOUNT         := REC_MAIN.DISCOUNT_AMOUNT; -- 値引金額
            rtRecWork.OTHER_SALES_AMOUNT      := REC_MAIN.OTHER_SALES_AMOUNT; -- その他売上金額
            rtRecWork.OFFSET_AMOUNT           := REC_MAIN.OFFSET_AMOUNT; -- 相殺金額
            rtRecWork.TAX_AMOUNT              := REC_MAIN.TAX_AMOUNT; -- 消費税額
            rtRecWork.CREDIT_AMOUNT           := REC_MAIN.CREDIT_AMOUNT; -- 売掛残
            rtRecWork.CREDIT_SALES_BREAKDOWN  := REC_MAIN.CREDIT_SALES_BREAKDOWN; -- 売掛金(内訳)
            rtRecWork.ACCRUED_DEBIT_BREAKDOWN := REC_MAIN.ACCRUED_DEBIT_BREAKDOWN; -- 未収金(内訳)
            rtRecWork.EXCEPT_BREAKDOWN        := REC_MAIN.EXCEPT_BREAKDOWN; -- 以外(内訳)
            rtRecWork.INPUT_DATE              := SYSDATE; -- 登録日時
            rtRecWork.INPUTOR_CD              := P_STR_INPUTOR_CD; -- 登録者ＩＤ
            rtRecWork.UPDATE_DATE             := SYSDATE; -- 更新日時
            rtRecWork.UPDATOR_CD              := P_STR_INPUTOR_CD; -- 更新者ＩＤ
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(6) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★売掛ヘッダー登録
            --------------------------------------------------------------------------------
            INSERT INTO DEPOSIT_HEADER VALUES rtRecWork;
            ----------------------------------------------------------------------------------------------------------
            --【売掛対象】【売掛対象外】の各トランザクション更新処理
            ----------------------------------------------------------------------------------------------------------
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(7) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】売上トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   SALES  SALES --売上トラン
                                   ,VENDER VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(7) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            -- 請求更新未処理対象
            UPDATE SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   SALES  SALES --売上トラン
                                   ,VENDER VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv1 --売掛対象
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(7) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】売上トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   SALES  SALES --売上トラン
                                   ,VENDER VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv0 --売掛対象外
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(7) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売上トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            -- 請求更新未処理対象
            UPDATE SALES
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
                  ,INVOICE_CD             =
                   ( -- 請求先コード
                    SELECT DECODE(PAYMENT_INVOICE_CD
                                  ,NULL
                                  ,VENDER_CD
                                  ,PAYMENT_INVOICE_CD) AS INVOICE_CD
                    FROM   VENDER
                    WHERE  VENDER.VENDER_CD = REC_MAIN.VENDER_CD
                    AND    VENDER_DIVISION = c_VenderDivTS)
            WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    ROWID IN
                   (SELECT SALES.ROW_ID
                     FROM   (SELECT SALES.CHARGE_ORGANIZATION_CD
                                   ,SALES.VENDER_CD
                                   ,VENDER.PAYMENT_INVOICE_CD    AS INVOICE_CD
                                   ,SALES.DATA_TYPE
                                   ,SALES.DATA_TOTAL_DIVISION
                                   ,SALES.CATEGORY_DIVISION
                                   ,SALES.ROWID                  AS ROW_ID
                             FROM   SALES  SALES --売上トラン
                                   ,VENDER VENDER
                             WHERE  SALES.CHARGE_ORGANIZATION_CD =
                                    REC_MAIN.ORGANIZATION_CD
                             AND    SALES.SALES_DATE <= daCreditDate
                             AND    DEPOSIT_UPDATE_DIVISION =
                                    c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
                             AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
                             AND    SALES.TMP_UNITPRICE_FLG =
                                    c_TmpUnitPriceFlg0 --仮単価フラグ(0:通常のもの)
                             AND    SALES.VENDER_CD = VENDER.VENDER_CD
                             AND    VENDER_DIVISION = c_VenderDivTS) SALES
                           ,CLASSIFICATION CLASSIFICATION --分類マスタ
                     WHERE  ((SALES.VENDER_CD = REC_MAIN.VENDER_CD AND
                            SALES.INVOICE_CD IS NULL) OR
                            SALES.INVOICE_CD = REC_MAIN.VENDER_CD)
                     AND    SALES.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    SALES.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    SALES.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION
                     AND    CLASSIFICATION.AR_DIVISION = c_ArDiv0 --売掛対象外
                     );
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】入金トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    CREDIT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    CREDIT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE CREDIT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv1                                            -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                -- 売掛番号
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             CREDIT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    CREDIT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    CREDIT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】入金トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
            -- 請求更新未処理対象
            --            UPDATE CREDIT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv9                                            -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             CREDIT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE CREDIT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    CREDIT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】支払トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    PAYMENT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    PAYMENT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE PAYMENT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv1                                            -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                -- 売掛番号
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             PAYMENT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
                     AND    PAYMENT.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    PAYMENT.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】支払トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
            -- 請求更新未処理対象
            --            UPDATE PAYMENT
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD        -- 会計部門
            --                         c_DepositTrgDiv9                                            -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                            -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                -- 売掛締め日
            --                         ,SYSDATE                                                    -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                            -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                         FROM
            --                              BUMON
            --                         WHERE
            --                             PAYMENT.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE PAYMENT
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
            AND    PAYMENT_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            ;
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象】グループ間相殺トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
            -- 請求更新未処理対象
            --            UPDATE OFFSET_GROUP_DATA
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEBIT_TITLE_CD
            --                        ,DEBIT_SUB_TITLE_CD
            --                        ,CREDIT_TITLE_CD
            --                        ,CREDIT_SUB_TITLE_CD
            --                        ,DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DEPOSIT_NO
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD            -- 会計部門
            --                         NVL(MAX(DEBIT_ACCOUNTS_CD),'') AS DEBIT_ACCOUNTS_CD            -- 借方科目コード
            --                         ,NVL(MAX(DEBIT_ACCOUNTS_SUB_CD),'') AS DEBIT_ACCOUNTS_SUB_CD    -- 借方補助科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_CD),'') AS CREDIT_ACCOUNTS_CD            -- 貸方科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_SUB_CD),'') AS CREDIT_ACCOUNTS_SUB_CD    -- 貸方補助科目コード
            --                         ,c_DepositTrgDiv1                                                -- 売掛対象(処理済)
            --                         ,c_DepositUpdDiv1                                                -- 売掛更新フラグ(更新済)
            --                         ,nvDepositNo                                                    -- 売掛番号
            --                         ,daCreditDate                                                    -- 売掛締め日
            --                         ,SYSDATE                                                        -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                                -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD
            --                         FROM
            --                              BUMON
            --                             ,CLASSIFICATION
            --                         WHERE
            --                                 OFFSET_GROUP_DATA.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         AND    OFFSET_GROUP_DATA.DATA_TYPE                =    CLASSIFICATION.DATA_TYPE
            --                         AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION        =    CLASSIFICATION.CATEGORY_DIVISION
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv1 -- 売掛対象(処理済)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DEPOSIT_NO              = nvDepositNo -- 売掛番号
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            AND    c_ArDiv1 =
                   ( -- 売掛対象
                     SELECT AR_DIVISION
                     FROM   CLASSIFICATION
                     WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
                            CLASSIFICATION.DATA_TYPE
                     AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
                            CLASSIFICATION.DATA_TOTAL_DIVISION
                     AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
                            CLASSIFICATION.CATEGORY_DIVISION);
        
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            --------------------------------------------------------------------------------
            --★【売掛対象外】グループ間相殺トランザクション更新
            --------------------------------------------------------------------------------
            -- 請求更新済対象
            UPDATE OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
            AND    INVOICE_UPDATE_DATE IS NOT NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            ;
            -- 請求更新未処理対象
            --            UPDATE OFFSET_GROUP_DATA
            --                SET
            --                    (
            --ACCOUNT_SECTION_CD
            --                        DEBIT_TITLE_CD
            --                        ,DEBIT_SUB_TITLE_CD
            --                        ,CREDIT_TITLE_CD
            --                        ,CREDIT_SUB_TITLE_CD
            --                        ,DEPOSIT_TARGET_DIVISION
            --                        ,DEPOSIT_UPDATE_DIVISION
            --                        ,DELIVERY_UPDATE_DATE
            --                        ,UPDATE_DATE
            --                        ,UPDATOR_CD
            --                    )
            --                    =
            --                    (
            --                     SELECT
            --NVL(MAX(ACCOUNT_SECTION_CD),'') AS ACCOUNT_SECTION_CD            -- 会計部門
            --                          NVL(MAX(DEBIT_ACCOUNTS_CD),'') AS DEBIT_ACCOUNTS_CD            -- 借方科目コード
            --                         ,NVL(MAX(DEBIT_ACCOUNTS_SUB_CD),'') AS DEBIT_ACCOUNTS_SUB_CD    -- 借方補助科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_CD),'') AS CREDIT_ACCOUNTS_CD            -- 貸方科目コード
            --                         ,NVL(MAX(CREDIT_ACCOUNTS_SUB_CD),'') AS CREDIT_ACCOUNTS_SUB_CD    -- 貸方補助科目コード
            --                         ,c_DepositTrgDiv9                                                -- 売掛対象(対象外)
            --                         ,c_DepositUpdDiv1                                                -- 売掛更新フラグ(更新済)
            --                         ,daCreditDate                                                    -- 売掛締め日
            --                         ,SYSDATE                                                        -- 更新日時
            --                         ,P_STR_INPUTOR_CD                                                -- 更新者ＩＤ
            --                     FROM
            --                         (
            --                         SELECT
            --                              BUMON.ACCOUNT_SECTION_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.DEBIT_ACCOUNTS_SUB_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_CD
            --                             ,CLASSIFICATION.CREDIT_ACCOUNTS_SUB_CD
            --                         FROM
            --                              BUMON
            --                             ,CLASSIFICATION
            --                         WHERE
            --                                 OFFSET_GROUP_DATA.ORGANIZATION_CD            =    BUMON.SECTION_CD
            --                         AND    OFFSET_GROUP_DATA.DATA_TYPE                =    CLASSIFICATION.DATA_TYPE
            --                         AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION        =    CLASSIFICATION.CATEGORY_DIVISION
            --                         )
            --                    )
            -- 2017/04/06 update ロック停止 -->
            --            IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            --                ROLLBACK;
            --                P_NUM_RET := 9;
            --
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            --                -- 2015/11/09 insert ログ追加 -->
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            --                -- <-----------------------------
            --                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
            --               RETURN;
            --            END IF;
            -- <-----------------------------
        
            UPDATE OFFSET_GROUP_DATA
            SET    DEPOSIT_TARGET_DIVISION = c_DepositTrgDiv9 -- 売掛対象(対象外)
                  ,DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv1 -- 売掛更新フラグ(更新済)
                  ,DELIVERY_UPDATE_DATE    = daCreditDate -- 売掛締め日
                  ,UPDATE_DATE             = SYSDATE -- 更新日時
                  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
            WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
            AND    VENDER_CD = REC_MAIN.VENDER_CD
            AND    OFFSET_DATE <= daCreditDate
            AND    DEPOSIT_UPDATE_DIVISION = c_DepositUpdDiv0 -- 売掛更新フラグ(未処理)
            AND    DELIVERY_UPDATE_DATE IS NULL -- 売掛締め日
            AND    CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
            AND    INVOICE_UPDATE_DATE IS NULL -- 請求締め日
            AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
            AND    VENDER_DIVISION = c_VenderDivTS -- 得意先
            ;
            -- カウント
            nvCount := nvCount + 1;
        END LOOP;
        --対象データなしの場合
        IF nvCount = 0 THEN
            --ロールバック
            ROLLBACK;
            --戻り値設定
            P_NUM_RET := 9;
            P_ERRCD   := 0;
            P_ERRMSG  := NULL;
        
            TRNERRMAN.LOGOUT(DEF_PROC_NAME
                            ,'対象データがありません'
                            ,P_STR_INPUTOR_CD);
            TRNERRMAN.LOGOUT(DEF_PROC_NAME
                            ,'売掛更新処理終了'
                            ,P_STR_INPUTOR_CD);
        
            RETURN;
        END IF;
    END IF;
    /* 未請求額更新処理 */
    PRO_CLAIM_BALANCE_UPDATE(P_STR_ORGANIZATION_CD
                            ,NULL
                            ,NULL
                            ,P_STR_CREDIT_DATE
                            ,P_STR_TEMP_CLOSING_FLG
                            ,P_STR_INPUTOR_CD
                            ,P_NUM_RET
                            ,P_ERRCD
                            ,P_ERRMSG);

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '売掛更新処理終了', P_STR_INPUTOR_CD);
EXCEPTION
    --------------------------------------------------------------------------------
    --例外処理
    --------------------------------------------------------------------------------
    WHEN OTHERS THEN
        --ロールバック
        ROLLBACK;
        --戻り値設定
        P_NUM_RET := 99;
        P_ERRMSG  := SUBSTR(SQLERRM, 1, 2000);
        P_ERRCD   := SQLCODE;
    
        -- ログ出力
        TRNERRMAN.LOGOUT(DEF_PROC_NAME, P_ERRMSG, P_STR_INPUTOR_CD);
        TRNERRMAN.LOGOUT(DEF_PROC_NAME
                        ,'売掛更新処理終了'
                        ,P_STR_INPUTOR_CD);
END PRO_ARUPDATE;
/
