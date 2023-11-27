CREATE OR REPLACE PROCEDURE PRO_APUPDATE
(
	P_STR_ORGANIZATION_CD  IN NVARCHAR2
   ,P_STR_PAYABLE_DATE     IN NVARCHAR2
   ,P_STR_TEMP_CLOSING_FLG IN NVARCHAR2
   ,P_STR_INPUTOR_CD       IN NVARCHAR2
   ,P_NUM_RET              OUT NUMBER
) IS
	/*----------------------------------------------------------------------------------------------------------------
        種別        :    PROCEDURE
        名称        :    PRO_APUPDATE
        処理内容    :    買掛更新処理
        引数        :    P_STR_ORGANIZATION_CD    部署コード    NVARCHAR2型
        引数        :    P_STR_PAYABLE_DATE        買掛締め日    NVARCHAR2型
        引数        :    P_STR_TEMP_CLOSING_FLG    仮締区分    NVARCHAR2型
        引数        :    P_STR_INPUTOR_CD        登録者ＩＤ    NVARCHAR2型
        引数(戻値)    :    P_NUM_RET                実行結果(0:正常終了, 99:異常終了)    NUMBER型
        VERNO.        :    1.00
    (history)
        date        ver       name                comments
        ----------  ------    ------------------  ----------------------------------------------------------------------
        2008.09.08  1.00      TOSCO               creation
        2009.06.18                                SOM殿向けカスタマイズ
        2009.07.19            okmt.tc             複数ロット時の仕入消費税修正。買掛金科目による仕入区分を修正。0,1→1,2
        2010.12.07  1.01      aec                 消費税の外税・内税を同じ処理に修正
        2011.01.06  1.02      aec                 各合計値のnullチェック追加
        2015.11.09  1.03      n.s                 テーブルロック時のログを追加
    */ ----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_DataDivShiShiire   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (仕入)仕入
	c_DataDivShiHenpin   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (仕入)返品
	c_DataDivShiNebiki   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 3; -- (仕入)値引
	c_DataDivShiSonota   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 9; -- (仕入)その他
	c_DataDivNyuSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (入金)相殺
	c_DataDivShiShiharai CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (支払)支払
	c_DataDivShiSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (支払)相殺
	c_DataDivShiSonota9  CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 9; -- (支払)その他
	c_DataDivSouSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (グループ間相殺)グループ間相殺
	c_PayableTrgDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_TARGET_DIVISION%TYPE := 1; -- 買掛対象(処理済)
	c_PayableTrgDiv9     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_TARGET_DIVISION%TYPE := 9; -- 買掛対象(対象外)
	c_PayableUpdDiv0     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE := 0; -- 買掛更新フラグ(未処理)
	c_PayableUpdDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE := 1; -- 買掛更新フラグ(更新済)
	c_CreditDiv0         CONSTANT CLASSIFICATION.CREDIT_DIVISION%TYPE := 0; -- 買掛対象(対象外)
	c_CreditDiv1         CONSTANT CLASSIFICATION.CREDIT_DIVISION%TYPE := 1; -- 買掛対象(対象)
	c_PaymentUpdDiv0     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE := 0; -- 支払更新フラグ(未処理)
	c_PaymentUpdDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE := 1; -- 支払更新フラグ(更新済)
	c_Status2Sts3        CONSTANT PURCHASE_SUBCONTRACT.STATUS2%TYPE := 3; -- 仕入ステータス(承認済)
	c_ApprovalSts3       CONSTANT PURCHASE_SUBCONTRACT.APPROVAL_STATUS%TYPE := 3; -- 承認ステータス(承認済)
	c_TempClosingFlg1    CONSTANT VARCHAR2(1) := '1'; -- 仮締区分(仮締め)
	c_VenderDivSI        CONSTANT VENDER.VENDER_DIVISION%TYPE := 'SI'; -- 取引先区分(SI:仕入先)
	DEF_PROC_NAME        CONSTANT PROC_PARAM.PROC_CD%TYPE := 'PRO_APUPDATE';
	daPayableDate PAYABLE_HEADER.PAYABLE_DATE%TYPE; -- 買掛締め日
	nvPayableNo   PAYABLE_HEADER.PAYABLE_NO%TYPE; -- 買掛番号
	rtRecWork     PAYABLE_HEADER%ROWTYPE; -- 買掛ヘッダー
	c_PurchaseAcc1 CONSTANT ACCOUNTS.PURCHASE_ACCOUNTS%TYPE := 1; -- 仕入科目区分(1:買掛)
	c_PurchaseAcc2 CONSTANT ACCOUNTS.PURCHASE_ACCOUNTS%TYPE := 2; -- 仕入科目区分(2:未払金)
	ERR_CODE VARCHAR2(100);
	ERR_MSG  VARCHAR2(2048);
	nvCount  NUMBER := 0; -- カウンター
	msg     TEMP_BATCH_MSG.MSG%TYPE; 
	--------------------------------------------------------------------------------
	--★カーソル宣言
	--------------------------------------------------------------------------------
	--対象データ抽出 カーソル
	CURSOR curSales(p_daPayableDate IN DATE) IS
		SELECT *
		FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD       AS VENDER_CD --支払先コード
					   --            ,SUM(BALANCE_FORWARD_ACC + BALANCE_FORWARD_ARR + BALANCE_FORWARD_EXC) AS BALANCE_FORWARD        --前月繰越
					  ,SUM(NVL(BALANCE_FORWARD_ACC, 0) +
						   NVL(BALANCE_FORWARD_ARR, 0) +
						   NVL(BALANCE_FORWARD_EXC, 0)) AS BALANCE_FORWARD --前月繰越
					  ,SUM(NVL(STOCKING_AMOUNT_ACC, 0) +
						   NVL(STOCKING_AMOUNT_ARR, 0) +
						   NVL(STOCKING_AMOUNT_EXC, 0)) AS STOCKING_AMOUNT --仕入金額
					  ,SUM(NVL(PAYMENT_AMOUNT_ACC, 0) +
						   NVL(PAYMENT_AMOUNT_ARR, 0) +
						   NVL(PAYMENT_AMOUNT_EXC, 0)) AS PAYMENT_AMOUNT --支払金額
					  ,SUM(NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0)) AS OTHER_PAYMENT_AMOUNT --その他支払金額
					  ,SUM(NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
						   NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
						   NVL(STOCKING_RETURNED_AMOUNT_EXC, 0)) AS STOCKING_RETURNED_AMOUNT --返品金額
					  ,SUM(NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0)) AS STOCKING_DISCOUNT_AMOUNT --値引金額
					  ,SUM(NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_EXC, 0)) AS OTHER_STOCKING_AMOUNT --その他仕入金額
					  ,SUM(NVL(OFFSET_AMOUNT_ACC, 0) +
						   NVL(OFFSET_AMOUNT_ARR, 0) +
						   NVL(OFFSET_AMOUNT_EXC, 0)) AS OFFSET_AMOUNT --相殺金額
					  ,SUM(NVL(TAX_AMOUNT_ACC, 0) + NVL(TAX_AMOUNT_ARR, 0) +
						   NVL(TAX_AMOUNT_EXC, 0)) AS TAX_AMOUNT --消費税額
					  ,SUM((NVL(BALANCE_FORWARD_ACC, 0) +
						   NVL(BALANCE_FORWARD_ARR, 0) +
						   NVL(BALANCE_FORWARD_EXC, 0) +
						   NVL(STOCKING_AMOUNT_ACC, 0) +
						   NVL(STOCKING_AMOUNT_ARR, 0) +
						   NVL(STOCKING_AMOUNT_EXC, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_EXC, 0) +
						   NVL(TAX_AMOUNT_ACC, 0) + NVL(TAX_AMOUNT_ARR, 0) +
						   NVL(TAX_AMOUNT_EXC, 0)) -
						   (NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
						   NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
						   NVL(STOCKING_RETURNED_AMOUNT_EXC, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0) +
						   NVL(PAYMENT_AMOUNT_ACC, 0) +
						   NVL(PAYMENT_AMOUNT_ARR, 0) +
						   NVL(PAYMENT_AMOUNT_EXC, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0) +
						   NVL(OFFSET_AMOUNT_ACC, 0) +
						   NVL(OFFSET_AMOUNT_ARR, 0) +
						   NVL(OFFSET_AMOUNT_EXC, 0))) AS PAYABLE_AMOUNT --買掛残
					  ,SUM((NVL(BALANCE_FORWARD_ACC, 0) +
						   NVL(STOCKING_AMOUNT_ACC, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
						   NVL(TAX_AMOUNT_ACC, 0)) -
						   (NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
						   NVL(PAYMENT_AMOUNT_ACC, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
						   NVL(OFFSET_AMOUNT_ACC, 0))) AS ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
					  ,SUM((NVL(BALANCE_FORWARD_ARR, 0) +
						   NVL(STOCKING_AMOUNT_ARR, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
						   NVL(TAX_AMOUNT_ARR, 0)) -
						   (NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
						   NVL(PAYMENT_AMOUNT_ARR, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
						   NVL(OFFSET_AMOUNT_ARR, 0))) AS ARREARAGE_BREAKDOWN --未払金(内訳)
					  ,SUM((NVL(BALANCE_FORWARD_EXC, 0) +
						   NVL(STOCKING_AMOUNT_EXC, 0) +
						   NVL(OTHER_STOCKING_AMOUNT_EXC, 0) +
						   NVL(TAX_AMOUNT_EXC, 0)) -
						   (NVL(STOCKING_RETURNED_AMOUNT_EXC, 0) +
						   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0) +
						   NVL(PAYMENT_AMOUNT_EXC, 0) +
						   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0) +
						   NVL(OFFSET_AMOUNT_EXC, 0))) AS EXCEPT_BREAKDOWN --以外(内訳)
				FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
							  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
							  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
							  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
							  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
							  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
							  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
							  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
							  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
							  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
							  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
							  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
							  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
							  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
							  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
							  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                            ,'2'
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))) AS STOCKING_AMOUNT_ACC --仕入金額(内税の場合は＋消費税額)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                            ,'2'
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))) AS STOCKING_AMOUNT_ARR --仕入金額(内税の場合は＋消費税額)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'2'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											END
									   END AS STOCKING_AMOUNT_EXC --仕入金額(内税の場合は＋消費税額)(以外)
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'1'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													,0)) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'1'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													,0)) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'1'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                                                                       ,0)
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											END
									   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
											  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
											  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
											   p_daPayableDate
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiShiire --仕入
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
											   c_PayableUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
								WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+)
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                            ,'2'
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(内税の場合は＋消費税額)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                            ,'2'
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                   ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                            ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                   ,'-'
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(内税の場合は＋消費税額)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'2'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											END
									   END AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(内税の場合は＋消費税額)(以外)
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									   -- 返品の場合は、消費税をマイナス金額にする
									  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
											  ,c_PurchaseAcc1
											   /*                                            ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                               ,'1'
                                               ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                      ,'-'
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                               ,0)
                                               */
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													 ,c_PurchaseAcc1
													  /*                                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                      ,'1'
                                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                             ,'-'
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                      ,0)
                                                      */
													 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																   ,1
																   ,1)
															,'-'
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													 ,0)) * -1) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
									  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
											  ,c_PurchaseAcc2
											   /*                                            ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                               ,'1'
                                               ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                      ,'-'
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                               ,0)
                                               */
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													 ,c_PurchaseAcc2
													  /*                                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                      ,'1'
                                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                             ,'-'
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                      ,0)
                                                      */
													 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																   ,1
																   ,1)
															,'-'
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													 ,0)) * -1) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'1'
                                                                                                       ,(DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                               ,'-'
                                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
                                                                                                       ,0)
                                                */
												 (DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															   ,1
															   ,1)
														,'-'
														,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
											END
									   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
											  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
											  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
											   p_daPayableDate
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiHenpin --返品
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
											   c_PayableUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
								WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+)
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'2'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
													,0)) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(内税の場合は＋消費税額)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'2'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
													,0)) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(内税の場合は＋消費税額)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'2'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											END
									   END AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(内税の場合は＋消費税額)(以外)
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									   -- 値引の場合は、消費税をマイナス金額にする
									  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
											  ,c_PurchaseAcc1
											   /*                                            ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                               ,'1'
                                               ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                      ,'-'
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                               ,0)
                                               */
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													 ,c_PurchaseAcc1
													  /*                                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                      ,'1'
                                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                             ,'-'
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                      ,0)
                                                      */
													 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																   ,1
																   ,1)
															,'-'
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													 ,0)) * -1) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
									  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
											  ,c_PurchaseAcc2
											   /*                                            ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                               ,'1'
                                               ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                      ,'-'
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                      ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                               ,0)
                                               */
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													 ,c_PurchaseAcc2
													  /*                                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                      ,'1'
                                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                             ,'-'
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                      ,0)
                                                      */
													 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																   ,1
																   ,1)
															,'-'
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
															,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													 ,0)) * -1) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'1'
                                                                                                       ,(DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                               ,'-'
                                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
                                                                                                       ,0)
                                                */
												 (DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															   ,1
															   ,1)
														,'-'
														,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
											END
									   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
											  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
											  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
											   p_daPayableDate
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiNebiki --値引
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
											   c_PayableUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
								WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+)
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'2'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
													,0)) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(内税の場合は＋消費税額)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'2'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                     ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'2'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                            ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
													,0)) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(内税の場合は＋消費税額)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'2'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											END
									   END AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(内税の場合は＋消費税額)(以外)
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									   -- 分類コードが取消の場合は、マイナス金額にする
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'1'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													,0)) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													 /*                                                  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                     ,'1'
                                                     ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                            ,'-'
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                            ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                     ,0)
                                                     */
													,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
																  ,1
																  ,1)
														   ,'-'
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
														   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
													,0)) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												/*                                               DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                                       ,'1'
                                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                                              ,'-'
                                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                                                                       ,0)
                                                */
												 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															  ,1
															  ,1)
													   ,'-'
													   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											END
									   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
											  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
											  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
											   p_daPayableDate
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiSonota --その他売上
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
											   c_PayableUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
								WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+))
						GROUP  BY ORGANIZATION_CD, VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
							  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
							  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
							  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
							  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
							  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
							  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
							  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
							  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
							  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
							  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
							  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
							  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
							  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
							  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
							  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
						FROM   (SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											 ,CREDIT.CREDIT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													,CREDIT.CREDIT_AMOUNT
													,0)) AS OFFSET_AMOUNT_ACC --入金相殺金額(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											 ,CREDIT.CREDIT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													,CREDIT.CREDIT_AMOUNT
													,0)) AS OFFSET_AMOUNT_ARR --入金相殺金額(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												 CREDIT.CREDIT_AMOUNT
											END
									   END AS OFFSET_AMOUNT_EXC --入金相殺金額(以外)
									  ,0 AS TAX_AMOUNT_ACC
									  ,0 AS TAX_AMOUNT_ARR
									  ,0 AS TAX_AMOUNT_EXC
									  ,0 AS PAYABLE_AMOUNT
								FROM   CREDIT         CREDIT --入金トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   CREDIT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    CREDIT.CREDIT_DATE <= p_daPayableDate
								AND    CREDIT.DATA_TOTAL_DIVISION =
									   c_DataDivNyuSousai --相殺
								AND    CREDIT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    CREDIT.PAYABLE_UPDATE_DATE IS NULL
								AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
								AND    CREDIT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    CREDIT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    CREDIT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    CREDIT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    CREDIT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+))
						GROUP  BY ORGANIZATION_CD, VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
							  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
							  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
							  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
							  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
							  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
							  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
							  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
							  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
							  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
							  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
							  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
							  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
							  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
							  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
							  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
						FROM   (SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												 PAYMENT.PAYMENT_AMOUNT
											END
									   END AS PAYMENT_AMOUNT_EXC --支払金額(以外)
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									  ,0 AS TAX_AMOUNT_ACC
									  ,0 AS TAX_AMOUNT_ARR
									  ,0 AS TAX_AMOUNT_EXC
									  ,0 AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiShiharai --支払
								AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PAYMENT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PAYMENT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+)
								UNION ALL
								SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS OFFSET_AMOUNT_ACC --支払相殺金額(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS OFFSET_AMOUNT_ARR --支払相殺金額(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												 PAYMENT.PAYMENT_AMOUNT
											END
									   END AS OFFSET_AMOUNT_EXC --支払相殺金額(以外)
									  ,0 AS TAX_AMOUNT_ACC
									  ,0 AS TAX_AMOUNT_ARR
									  ,0 AS TAX_AMOUNT_EXC
									  ,0 AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiSousai --相殺
								AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PAYMENT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PAYMENT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+)
								UNION ALL
								SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											 ,PAYMENT.PAYMENT_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													,PAYMENT.PAYMENT_AMOUNT
													,0)) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												 PAYMENT.PAYMENT_AMOUNT
											END
									   END AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,0 AS OFFSET_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ARR
									  ,0 AS OFFSET_AMOUNT_EXC
									  ,0 AS TAX_AMOUNT_ACC
									  ,0 AS TAX_AMOUNT_ARR
									  ,0 AS TAX_AMOUNT_EXC
									  ,0 AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
									  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiSonota9 --その他
								AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    PAYMENT.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    PAYMENT.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+))
						GROUP  BY ORGANIZATION_CD, VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
							  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
							  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
							  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
							  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
							  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
							  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
							  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
							  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
							  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
							  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
							  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
							  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
							  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
							  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
							  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
						FROM   (SELECT OFFSET.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,OFFSET.VENDER_CD AS VENDER_CD --支払先コード
									  ,0 AS BALANCE_FORWARD_ACC
									  ,0 AS BALANCE_FORWARD_ARR
									  ,0 AS BALANCE_FORWARD_EXC
									  ,0 AS STOCKING_AMOUNT_ACC
									  ,0 AS STOCKING_AMOUNT_ARR
									  ,0 AS STOCKING_AMOUNT_EXC
									  ,0 AS PAYMENT_AMOUNT_ACC
									  ,0 AS PAYMENT_AMOUNT_ARR
									  ,0 AS PAYMENT_AMOUNT_EXC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ARR
									  ,0 AS OTHER_STOCKING_AMOUNT_EXC
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											 ,OFFSET.PAYABLE_OFFSET_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc1
													,OFFSET.PAYABLE_OFFSET_AMOUNT
													,0)) AS OFFSET_AMOUNT_ACC --買掛相殺金額(買掛金)
									  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											 ,OFFSET.PAYABLE_OFFSET_AMOUNT
											 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
													,c_PurchaseAcc2
													,OFFSET.PAYABLE_OFFSET_AMOUNT
													,0)) AS OFFSET_AMOUNT_ARR --買掛相殺金額(未払金)
									  ,CASE
										   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
												(c_PurchaseAcc1, c_PurchaseAcc2) THEN
											0
										   ELSE
											CASE
												WHEN AC_KASI.PURCHASE_ACCOUNTS IN
													 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
												 0
												ELSE
												 OFFSET.PAYABLE_OFFSET_AMOUNT
											END
									   END AS OFFSET_AMOUNT_EXC --買掛相殺金額(以外)
									  ,0 AS TAX_AMOUNT_ACC
									  ,0 AS TAX_AMOUNT_ARR
									  ,0 AS TAX_AMOUNT_EXC
									  ,0 AS PAYABLE_AMOUNT
								FROM   OFFSET_GROUP_DATA OFFSET --グループ間トラン
									  ,CLASSIFICATION    CLASSIFICATION --分類マスタ
									  ,ACCOUNTS          AC_KARI --科目マスタ(借方)
									  ,ACCOUNTS          AC_KASI --科目マスタ(貸方)
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   OFFSET.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    OFFSET.OFFSET_DATE <= p_daPayableDate
								AND    OFFSET.DATA_TOTAL_DIVISION =
									   c_DataDivSouSousai --グループ間相殺
								AND    OFFSET.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    OFFSET.PAYABLE_UPDATE_DATE IS NULL
								AND    OFFSET.PAYABLE_OFFSET_AMOUNT IS NOT NULL --買掛相殺金額
								AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
								AND    OFFSET.VENDER_DIVISION = c_VenderDivSI --仕入先
								AND    OFFSET.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    OFFSET.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    OFFSET.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.CREDIT_DIVISION =
									   c_CreditDiv1 --買掛対象
								AND    OFFSET.DEBIT_TITLE_CD =
									   AC_KARI.ACCOUNTS_CD(+)
								AND    OFFSET.CREDIT_TITLE_CD =
									   AC_KASI.ACCOUNTS_CD(+))
						GROUP  BY ORGANIZATION_CD, VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
							  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
							  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
							  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
							  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
							  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
							  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
							  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
							  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
							  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
							  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
							  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
							  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
							  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
							  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
							  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
							  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
							  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
						FROM   (SELECT HEADER.ORGANIZATION_CD           AS ORGANIZATION_CD --部署コード
									  ,HEADER.SUPPLIER_CD               AS VENDER_CD --支払先コード
									  ,HEADER.ACCOUNT_PAYABLE_BREAKDOWN AS BALANCE_FORWARD_ACC --買掛残(買掛金)
									  ,HEADER.ARREARAGE_BREAKDOWN       AS BALANCE_FORWARD_ARR --買掛残(未払金)
									  ,HEADER.EXCEPT_BREAKDOWN          AS BALANCE_FORWARD_EXC --買掛残(以外)
									  ,0                                AS STOCKING_AMOUNT_ACC
									  ,0                                AS STOCKING_AMOUNT_ARR
									  ,0                                AS STOCKING_AMOUNT_EXC
									  ,0                                AS PAYMENT_AMOUNT_ACC
									  ,0                                AS PAYMENT_AMOUNT_ARR
									  ,0                                AS PAYMENT_AMOUNT_EXC
									  ,0                                AS OTHER_PAYMENT_AMOUNT_ACC
									  ,0                                AS OTHER_PAYMENT_AMOUNT_ARR
									  ,0                                AS OTHER_PAYMENT_AMOUNT_EXC
									  ,0                                AS STOCKING_RETURNED_AMOUNT_ACC
									  ,0                                AS STOCKING_RETURNED_AMOUNT_ARR
									  ,0                                AS STOCKING_RETURNED_AMOUNT_EXC
									  ,0                                AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0                                AS STOCKING_DISCOUNT_AMOUNT_ARR
									  ,0                                AS STOCKING_DISCOUNT_AMOUNT_EXC
									  ,0                                AS OTHER_STOCKING_AMOUNT_ACC
									  ,0                                AS OTHER_STOCKING_AMOUNT_ARR
									  ,0                                AS OTHER_STOCKING_AMOUNT_EXC
									  ,0                                AS OFFSET_AMOUNT_ACC
									  ,0                                AS OFFSET_AMOUNT_ARR
									  ,0                                AS OFFSET_AMOUNT_EXC
									  ,0                                AS TAX_AMOUNT_ACC
									  ,0                                AS TAX_AMOUNT_ARR
									  ,0                                AS TAX_AMOUNT_EXC
									  ,0                                AS PAYABLE_AMOUNT
								FROM   PAYABLE_HEADER HEADER --買掛ヘッダー
									  ,(SELECT ORGANIZATION_CD
											  ,SUPPLIER_CD
											  ,MAX(PAYABLE_DATE) MAX_DATE
										FROM   PAYABLE_HEADER
										WHERE  PAYABLE_DATE < p_daPayableDate
										GROUP  BY ORGANIZATION_CD, SUPPLIER_CD) MAX_HEADER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   HEADER.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    HEADER.PAYABLE_AMOUNT <> 0
								AND    HEADER.ORGANIZATION_CD =
									   MAX_HEADER.ORGANIZATION_CD
								AND    HEADER.SUPPLIER_CD =
									   MAX_HEADER.SUPPLIER_CD
								AND    HEADER.PAYABLE_DATE =
									   MAX_HEADER.MAX_DATE)
						GROUP  BY ORGANIZATION_CD, VENDER_CD)
				GROUP  BY ORGANIZATION_CD, VENDER_CD
				ORDER  BY ORGANIZATION_CD, VENDER_CD)
		WHERE  ORGANIZATION_CD NOT IN
			   (SELECT ORGANIZATION_CD
				FROM   PAYABLE_HEADER
				WHERE  PAYABLE_DATE = p_daPayableDate
				GROUP  BY ORGANIZATION_CD, SUPPLIER_CD)
			  
		AND    VENDER_CD NOT IN
			   (SELECT SUPPLIER_CD
				 FROM   PAYABLE_HEADER
				 WHERE  PAYABLE_DATE = p_daPayableDate
				 GROUP  BY ORGANIZATION_CD, SUPPLIER_CD);
	--仮締め買掛データ抽出 カーソル
	CURSOR curTempSales(p_daPayableDate IN DATE) IS
		SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
			  ,VENDER_CD       AS VENDER_CD --支払先コード
			   --            ,SUM(BALANCE_FORWARD_ACC + BALANCE_FORWARD_ARR + BALANCE_FORWARD_EXC) AS BALANCE_FORWARD        --前月繰越
			  ,SUM(NVL(BALANCE_FORWARD_ACC, 0) +
				   NVL(BALANCE_FORWARD_ARR, 0) +
				   NVL(BALANCE_FORWARD_EXC, 0)) AS BALANCE_FORWARD --前月繰越
			  ,SUM(NVL(STOCKING_AMOUNT_ACC, 0) +
				   NVL(STOCKING_AMOUNT_ARR, 0) +
				   NVL(STOCKING_AMOUNT_EXC, 0)) AS STOCKING_AMOUNT --仕入金額
			  ,SUM(NVL(PAYMENT_AMOUNT_ACC, 0) + NVL(PAYMENT_AMOUNT_ARR, 0) +
				   NVL(PAYMENT_AMOUNT_EXC, 0)) AS PAYMENT_AMOUNT --支払金額
			  ,SUM(NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0)) AS OTHER_PAYMENT_AMOUNT --その他支払金額
			  ,SUM(NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
				   NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
				   NVL(STOCKING_RETURNED_AMOUNT_EXC, 0)) AS STOCKING_RETURNED_AMOUNT --返品金額
			  ,SUM(NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0)) AS STOCKING_DISCOUNT_AMOUNT --値引金額
			  ,SUM(NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_EXC, 0)) AS OTHER_STOCKING_AMOUNT --その他仕入金額
			  ,SUM(NVL(OFFSET_AMOUNT_ACC, 0) + NVL(OFFSET_AMOUNT_ARR, 0) +
				   NVL(OFFSET_AMOUNT_EXC, 0)) AS OFFSET_AMOUNT --相殺金額
			  ,SUM(NVL(TAX_AMOUNT_ACC, 0) + NVL(TAX_AMOUNT_ARR, 0) +
				   NVL(TAX_AMOUNT_EXC, 0)) AS TAX_AMOUNT --消費税額
			  ,SUM((NVL(BALANCE_FORWARD_ACC, 0) +
				   NVL(BALANCE_FORWARD_ARR, 0) +
				   NVL(BALANCE_FORWARD_EXC, 0) +
				   NVL(STOCKING_AMOUNT_ACC, 0) +
				   NVL(STOCKING_AMOUNT_ARR, 0) +
				   NVL(STOCKING_AMOUNT_EXC, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_EXC, 0) +
				   NVL(TAX_AMOUNT_ACC, 0) + NVL(TAX_AMOUNT_ARR, 0) +
				   NVL(TAX_AMOUNT_EXC, 0)) -
				   (NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
				   NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
				   NVL(STOCKING_RETURNED_AMOUNT_EXC, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0) +
				   NVL(PAYMENT_AMOUNT_ACC, 0) +
				   NVL(PAYMENT_AMOUNT_ARR, 0) +
				   NVL(PAYMENT_AMOUNT_EXC, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0) +
				   NVL(OFFSET_AMOUNT_ACC, 0) + NVL(OFFSET_AMOUNT_ARR, 0) +
				   NVL(OFFSET_AMOUNT_EXC, 0))) AS PAYABLE_AMOUNT --買掛残
			  ,SUM((NVL(BALANCE_FORWARD_ACC, 0) +
				   NVL(STOCKING_AMOUNT_ACC, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_ACC, 0) +
				   NVL(TAX_AMOUNT_ACC, 0)) -
				   (NVL(STOCKING_RETURNED_AMOUNT_ACC, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_ACC, 0) +
				   NVL(PAYMENT_AMOUNT_ACC, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_ACC, 0) +
				   NVL(OFFSET_AMOUNT_ACC, 0))) AS ACCOUNT_PAYABLE_BREAKDOWN --買掛金(内訳)
			  ,SUM((NVL(BALANCE_FORWARD_ARR, 0) +
				   NVL(STOCKING_AMOUNT_ARR, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_ARR, 0) +
				   NVL(TAX_AMOUNT_ARR, 0)) -
				   (NVL(STOCKING_RETURNED_AMOUNT_ARR, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_ARR, 0) +
				   NVL(PAYMENT_AMOUNT_ARR, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_ARR, 0) +
				   NVL(OFFSET_AMOUNT_ARR, 0))) AS ARREARAGE_BREAKDOWN --未払金(内訳)
			  ,SUM((NVL(BALANCE_FORWARD_EXC, 0) +
				   NVL(STOCKING_AMOUNT_EXC, 0) +
				   NVL(OTHER_STOCKING_AMOUNT_EXC, 0) +
				   NVL(TAX_AMOUNT_EXC, 0)) -
				   (NVL(STOCKING_RETURNED_AMOUNT_EXC, 0) +
				   NVL(STOCKING_DISCOUNT_AMOUNT_EXC, 0) +
				   NVL(PAYMENT_AMOUNT_EXC, 0) +
				   NVL(OTHER_PAYMENT_AMOUNT_EXC, 0) +
				   NVL(OFFSET_AMOUNT_EXC, 0))) AS EXCEPT_BREAKDOWN --以外(内訳)
		FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
					  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
					  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
					  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
					  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
					  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
					  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
					  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
					  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
					  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
					  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
					  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
					  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
					  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
					  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
					  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
					  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
					  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
					  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
				FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
									 ,NULL
									 ,PURCHASE_SUBCONTRACT.VENDER_CD
									 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_AMOUNT_ACC --仕入金額(内税の場合は＋消費税額)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_AMOUNT_ARR --仕入金額(内税の場合は＋消費税額)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'2'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									END
							   END AS STOCKING_AMOUNT_EXC --仕入金額(内税の場合は＋消費税額)(以外)
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'1'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                      ,0)
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'1'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                             ,0)
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											,0)) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'1'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                      ,0)
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'1'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                             ,0)
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											,0)) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'1'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                                                       ,0)
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									END
							   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
							  ,0 AS PAYABLE_AMOUNT
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
									  ,PURCHASE_SUBCONTRACT.VENDER_CD
									  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
									  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
									  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
									  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
									  ,PURCHASE_SUBCONTRACT.DATA_TYPE
									  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
									  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
									  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
									  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
								FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め買掛購買外注オーダーファイル
									  ,VENDER                      VENDER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									   p_daPayableDate
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   c_DataDivShiShiire --仕入
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
								AND    PURCHASE_SUBCONTRACT.STATUS2 =
									   c_Status2Sts3 --承認済
								AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									   VENDER.VENDER_CD
								AND    VENDER.VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
							  ,CLASSIFICATION CLASSIFICATION --分類マスタ
							  ,ACCOUNTS AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
						WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
							   CLASSIFICATION.DATA_TYPE
						AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+)
						UNION ALL
						SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
									 ,NULL
									 ,PURCHASE_SUBCONTRACT.VENDER_CD
									 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(内税の場合は＋消費税額)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(内税の場合は＋消費税額)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'2'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									END
							   END AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(内税の場合は＋消費税額)(以外)
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							   -- 返品の場合は、消費税をマイナス金額にする
							  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
									  ,c_PurchaseAcc1
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,0)) * -1) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
							  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
									  ,c_PurchaseAcc2
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,0)) * -1) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'1'
                                                                                       ,(DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                               ,'-'
                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
                                                                                       ,0)
                                        */
										 (DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													   ,1
													   ,1)
												,'-'
												,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
									END
							   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
							  ,0 AS PAYABLE_AMOUNT
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
									  ,PURCHASE_SUBCONTRACT.VENDER_CD
									  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
									  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
									  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
									  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
									  ,PURCHASE_SUBCONTRACT.DATA_TYPE
									  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
									  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
									  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
									  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
								FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め買掛購買外注オーダーファイル
									  ,VENDER                      VENDER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									   p_daPayableDate
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   c_DataDivShiHenpin --返品
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
								AND    PURCHASE_SUBCONTRACT.STATUS2 =
									   c_Status2Sts3 --承認済
								AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									   VENDER.VENDER_CD
								AND    VENDER.VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
							  ,CLASSIFICATION CLASSIFICATION --分類マスタ
							  ,ACCOUNTS AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
						WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
							   CLASSIFICATION.DATA_TYPE
						AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+)
						UNION ALL
						SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
									 ,NULL
									 ,PURCHASE_SUBCONTRACT.VENDER_CD
									 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(内税の場合は＋消費税額)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(内税の場合は＋消費税額)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'2'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									END
							   END AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(内税の場合は＋消費税額)(以外)
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							   -- 値引の場合は、消費税をマイナス金額にする
							  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
									  ,c_PurchaseAcc1
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc1
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,0)) * -1) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
							  ,(DECODE(AC_KARI.PURCHASE_ACCOUNTS
									  ,c_PurchaseAcc2
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									  ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											 ,c_PurchaseAcc2
											  /*                                           ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                              ,'1'
                                              ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                     ,'-'
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                     ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                              ,0)
                                              */
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											 ,0)) * -1) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'1'
                                                                                       ,(DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                               ,'-'
                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                               ,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
                                                                                       ,0)
                                        */
										 (DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													   ,1
													   ,1)
												,'-'
												,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												,PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1)
									END
							   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
							  ,0 AS PAYABLE_AMOUNT
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
									  ,PURCHASE_SUBCONTRACT.VENDER_CD
									  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
									  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
									  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
									  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
									  ,PURCHASE_SUBCONTRACT.DATA_TYPE
									  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
									  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
									  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
									  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
								FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め買掛購買外注オーダーファイル
									  ,VENDER                      VENDER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									   p_daPayableDate
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   c_DataDivShiNebiki --値引
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
								AND    PURCHASE_SUBCONTRACT.STATUS2 =
									   c_Status2Sts3 --承認済
								AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									   VENDER.VENDER_CD
								AND    VENDER.VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
							  ,CLASSIFICATION CLASSIFICATION --分類マスタ
							  ,ACCOUNTS AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
						WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
							   CLASSIFICATION.DATA_TYPE
						AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+)
						UNION ALL
						SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
									 ,NULL
									 ,PURCHASE_SUBCONTRACT.VENDER_CD
									 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(内税の場合は＋消費税額)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'2'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                             ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'2'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                    ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
											,0)) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(内税の場合は＋消費税額)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'2'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                                                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT))
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT))
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)
									END
							   END AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(内税の場合は＋消費税額)(以外)
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							   -- 分類コードが取消の場合は、マイナス金額にする
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'1'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                      ,0)
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'1'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                             ,0)
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											,0)) AS TAX_AMOUNT_ACC --消費税額(区分=外税のみ)(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									  /*                                   ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                      ,'1'
                                      ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                             ,'-'
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                             ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                      ,0)
                                      */
									 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
												   ,1
												   ,1)
											,'-'
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											 /*                                          ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                             ,'1'
                                             ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                    ,'-'
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                    ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                             ,0)
                                             */
											,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														  ,1
														  ,1)
												   ,'-'
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
												   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
											,0)) AS TAX_AMOUNT_ARR --消費税額(区分=外税のみ)(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										/*                                       DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                                                                       ,'1'
                                                                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                                                                              ,'-'
                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                                                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                                                                       ,0)
                                        */
										 DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													  ,1
													  ,1)
											   ,'-'
											   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
											   ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
									END
							   END AS TAX_AMOUNT_EXC --消費税額(区分=外税のみ)(以外)
							  ,0 AS PAYABLE_AMOUNT
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
									  ,PURCHASE_SUBCONTRACT.VENDER_CD
									  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
									  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
									  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
									  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
									  ,PURCHASE_SUBCONTRACT.DATA_TYPE
									  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
									  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
									  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
									  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
								FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め買掛購買外注オーダーファイル
									  ,VENDER                      VENDER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									   p_daPayableDate
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   c_DataDivShiSonota --その他売上
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									   c_PayableUpdDiv0 --未処理
								AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
								AND    PURCHASE_SUBCONTRACT.STATUS2 =
									   c_Status2Sts3 --承認済
								AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									   VENDER.VENDER_CD
								AND    VENDER.VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
							  ,CLASSIFICATION CLASSIFICATION --分類マスタ
							  ,ACCOUNTS AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
						WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
							   CLASSIFICATION.DATA_TYPE
						AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+))
				GROUP  BY ORGANIZATION_CD, VENDER_CD
				UNION ALL
				SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
					  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
					  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
					  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
					  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
					  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
					  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
					  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
					  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
					  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
					  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
					  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
					  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
					  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
					  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
					  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
					  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
					  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
					  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
				FROM   (SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,CREDIT.VENDER_CD AS VENDER_CD --請求先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									 ,CREDIT.CREDIT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											,CREDIT.CREDIT_AMOUNT
											,0)) AS OFFSET_AMOUNT_ACC --入金相殺金額(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									 ,CREDIT.CREDIT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											,CREDIT.CREDIT_AMOUNT
											,0)) AS OFFSET_AMOUNT_ARR --入金相殺金額(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										 CREDIT.CREDIT_AMOUNT
									END
							   END AS OFFSET_AMOUNT_EXC --入金相殺金額(以外)
							  ,0 AS TAX_AMOUNT_ACC
							  ,0 AS TAX_AMOUNT_ARR
							  ,0 AS TAX_AMOUNT_EXC
							  ,0 AS PAYABLE_AMOUNT
						FROM   TEMPORARY_PAYABLE_CREDIT CREDIT --仮締め買掛入金トラン
							  ,CLASSIFICATION           CLASSIFICATION --分類マスタ
							  ,ACCOUNTS                 AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS                 AC_KASI --科目マスタ(貸方)
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   CREDIT.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    CREDIT.CREDIT_DATE <= p_daPayableDate
						AND    CREDIT.DATA_TOTAL_DIVISION =
							   c_DataDivNyuSousai --相殺
						AND    CREDIT.PAYABLE_UPDATE_DIVISION =
							   c_PayableUpdDiv0 --未処理
						AND    CREDIT.PAYABLE_UPDATE_DATE IS NULL
						AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
						AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
						AND    CREDIT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    CREDIT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
						AND    CREDIT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+))
				GROUP  BY ORGANIZATION_CD, VENDER_CD
				UNION ALL
				SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
					  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
					  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
					  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
					  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
					  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
					  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
					  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
					  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
					  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
					  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
					  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
					  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
					  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
					  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
					  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
					  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
					  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
					  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
				FROM   (SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										 PAYMENT.PAYMENT_AMOUNT
									END
							   END AS PAYMENT_AMOUNT_EXC --支払金額(以外)
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							  ,0 AS TAX_AMOUNT_ACC
							  ,0 AS TAX_AMOUNT_ARR
							  ,0 AS TAX_AMOUNT_EXC
							  ,0 AS PAYABLE_AMOUNT
						FROM   TEMPORARY_PAYABLE_PAYMENT PAYMENT --仮締め買掛支払トラン
							  ,CLASSIFICATION            CLASSIFICATION --分類マスタ
							  ,ACCOUNTS                  AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS                  AC_KASI --科目マスタ(貸方)
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   PAYMENT.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   c_DataDivShiShiharai --支払
						AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
							   c_PayableUpdDiv0 --未処理
						AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
						AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
						AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PAYMENT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PAYMENT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PAYMENT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+)
						UNION ALL
						SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS OFFSET_AMOUNT_ACC --支払相殺金額(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS OFFSET_AMOUNT_ARR --支払相殺金額(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										 PAYMENT.PAYMENT_AMOUNT
									END
							   END AS OFFSET_AMOUNT_EXC --支払相殺金額(以外)
							  ,0 AS TAX_AMOUNT_ACC
							  ,0 AS TAX_AMOUNT_ARR
							  ,0 AS TAX_AMOUNT_EXC
							  ,0 AS PAYABLE_AMOUNT
						FROM   TEMPORARY_PAYABLE_PAYMENT PAYMENT --仮締め買掛支払トラン
							  ,CLASSIFICATION            CLASSIFICATION --分類マスタ
							  ,ACCOUNTS                  AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS                  AC_KASI --科目マスタ(貸方)
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   PAYMENT.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   c_DataDivShiSousai --相殺
						AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
							   c_PayableUpdDiv0 --未処理
						AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
						AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
						AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PAYMENT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PAYMENT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PAYMENT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+)
						UNION ALL
						SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,PAYMENT.SUPPLIER_CD AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									 ,PAYMENT.PAYMENT_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											,PAYMENT.PAYMENT_AMOUNT
											,0)) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										 PAYMENT.PAYMENT_AMOUNT
									END
							   END AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,0 AS OFFSET_AMOUNT_ACC
							  ,0 AS OFFSET_AMOUNT_ARR
							  ,0 AS OFFSET_AMOUNT_EXC
							  ,0 AS TAX_AMOUNT_ACC
							  ,0 AS TAX_AMOUNT_ARR
							  ,0 AS TAX_AMOUNT_EXC
							  ,0 AS PAYABLE_AMOUNT
						FROM   TEMPORARY_PAYABLE_PAYMENT PAYMENT --仮締め買掛支払トラン
							  ,CLASSIFICATION            CLASSIFICATION --分類マスタ
							  ,ACCOUNTS                  AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS                  AC_KASI --科目マスタ(貸方)
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   PAYMENT.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   c_DataDivShiSonota9 --その他
						AND    PAYMENT.PAYABLE_UPDATE_DIVISION =
							   c_PayableUpdDiv0 --未処理
						AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
						AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
						AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
						AND    PAYMENT.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    PAYMENT.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    PAYMENT.DEBIT_TITLE_CD =
							   AC_KARI.ACCOUNTS_CD(+)
						AND    PAYMENT.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+))
				GROUP  BY ORGANIZATION_CD, VENDER_CD
				UNION ALL
				SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
					  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
					  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
					  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
					  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
					  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
					  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
					  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
					  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
					  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
					  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
					  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
					  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
					  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
					  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
					  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
					  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
					  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
					  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
				FROM   (SELECT OFFSET.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,OFFSET.VENDER_CD AS VENDER_CD --支払先コード
							  ,0 AS BALANCE_FORWARD_ACC
							  ,0 AS BALANCE_FORWARD_ARR
							  ,0 AS BALANCE_FORWARD_EXC
							  ,0 AS STOCKING_AMOUNT_ACC
							  ,0 AS STOCKING_AMOUNT_ARR
							  ,0 AS STOCKING_AMOUNT_EXC
							  ,0 AS PAYMENT_AMOUNT_ACC
							  ,0 AS PAYMENT_AMOUNT_ARR
							  ,0 AS PAYMENT_AMOUNT_EXC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0 AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0 AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0 AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0 AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0 AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0 AS OTHER_STOCKING_AMOUNT_ACC
							  ,0 AS OTHER_STOCKING_AMOUNT_ARR
							  ,0 AS OTHER_STOCKING_AMOUNT_EXC
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc1
									 ,OFFSET.PAYABLE_OFFSET_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc1
											,OFFSET.PAYABLE_OFFSET_AMOUNT
											,0)) AS OFFSET_AMOUNT_ACC --買掛相殺金額(買掛金)
							  ,DECODE(AC_KARI.PURCHASE_ACCOUNTS
									 ,c_PurchaseAcc2
									 ,OFFSET.PAYABLE_OFFSET_AMOUNT
									 ,DECODE(AC_KASI.PURCHASE_ACCOUNTS
											,c_PurchaseAcc2
											,OFFSET.PAYABLE_OFFSET_AMOUNT
											,0)) AS OFFSET_AMOUNT_ARR --買掛相殺金額(未払金)
							  ,CASE
								   WHEN AC_KARI.PURCHASE_ACCOUNTS IN
										(c_PurchaseAcc1, c_PurchaseAcc2) THEN
									0
								   ELSE
									CASE
										WHEN AC_KASI.PURCHASE_ACCOUNTS IN
											 (c_PurchaseAcc1, c_PurchaseAcc2) THEN
										 0
										ELSE
										 OFFSET.PAYABLE_OFFSET_AMOUNT
									END
							   END AS OFFSET_AMOUNT_EXC --買掛相殺金額(以外)
							  ,0 AS TAX_AMOUNT_ACC
							  ,0 AS TAX_AMOUNT_ARR
							  ,0 AS TAX_AMOUNT_EXC
							  ,0 AS PAYABLE_AMOUNT
						FROM   TMPPAY_OFFSET_GROUP_DATA OFFSET --仮締め買掛グループ間トラン
							  ,CLASSIFICATION           CLASSIFICATION --分類マスタ
							  ,ACCOUNTS                 AC_KARI --科目マスタ(借方)
							  ,ACCOUNTS                 AC_KASI --科目マスタ(貸方)
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   OFFSET.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    OFFSET.OFFSET_DATE <= p_daPayableDate
						AND    OFFSET.DATA_TOTAL_DIVISION =
							   c_DataDivSouSousai --グループ間相殺
						AND    OFFSET.PAYABLE_UPDATE_DIVISION =
							   c_PayableUpdDiv0 --未処理
						AND    OFFSET.PAYABLE_UPDATE_DATE IS NULL
						AND    OFFSET.PAYABLE_OFFSET_AMOUNT IS NOT NULL --買掛相殺金額
						AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
						AND    OFFSET.VENDER_DIVISION = c_VenderDivSI --仕入先
						AND    OFFSET.DATA_TYPE = CLASSIFICATION.DATA_TYPE
						AND    OFFSET.DATA_TOTAL_DIVISION =
							   CLASSIFICATION.DATA_TOTAL_DIVISION
						AND    OFFSET.CATEGORY_DIVISION =
							   CLASSIFICATION.CATEGORY_DIVISION
						AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
						AND    OFFSET.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
						AND    OFFSET.CREDIT_TITLE_CD =
							   AC_KASI.ACCOUNTS_CD(+))
				GROUP  BY ORGANIZATION_CD, VENDER_CD
				UNION ALL
				SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(BALANCE_FORWARD_ACC) AS BALANCE_FORWARD_ACC --前月繰越(買掛金)
					  ,SUM(BALANCE_FORWARD_ARR) AS BALANCE_FORWARD_ARR --前月繰越(未払金)
					  ,SUM(BALANCE_FORWARD_EXC) AS BALANCE_FORWARD_EXC --前月繰越(以外)
					  ,SUM(STOCKING_AMOUNT_ACC) AS STOCKING_AMOUNT_ACC --仕入金額(買掛金)
					  ,SUM(STOCKING_AMOUNT_ARR) AS STOCKING_AMOUNT_ARR --仕入金額(未払金)
					  ,SUM(STOCKING_AMOUNT_EXC) AS STOCKING_AMOUNT_EXC --仕入金額(以外)
					  ,SUM(PAYMENT_AMOUNT_ACC) AS PAYMENT_AMOUNT_ACC --支払金額(買掛金)
					  ,SUM(PAYMENT_AMOUNT_ARR) AS PAYMENT_AMOUNT_ARR --支払金額(未払金)
					  ,SUM(PAYMENT_AMOUNT_EXC) AS PAYMENT_AMOUNT_EXC --支払金額(以外)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ACC) AS OTHER_PAYMENT_AMOUNT_ACC --その他支払金額(買掛金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_ARR) AS OTHER_PAYMENT_AMOUNT_ARR --その他支払金額(未払金)
					  ,SUM(OTHER_PAYMENT_AMOUNT_EXC) AS OTHER_PAYMENT_AMOUNT_EXC --その他支払金額(以外)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ACC) AS STOCKING_RETURNED_AMOUNT_ACC --返品金額(買掛金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_ARR) AS STOCKING_RETURNED_AMOUNT_ARR --返品金額(未払金)
					  ,SUM(STOCKING_RETURNED_AMOUNT_EXC) AS STOCKING_RETURNED_AMOUNT_EXC --返品金額(以外)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ACC) AS STOCKING_DISCOUNT_AMOUNT_ACC --値引金額(買掛金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_ARR) AS STOCKING_DISCOUNT_AMOUNT_ARR --値引金額(未払金)
					  ,SUM(STOCKING_DISCOUNT_AMOUNT_EXC) AS STOCKING_DISCOUNT_AMOUNT_EXC --値引金額(以外)
					  ,SUM(OTHER_STOCKING_AMOUNT_ACC) AS OTHER_STOCKING_AMOUNT_ACC --その他仕入金額(買掛金)
					  ,SUM(OTHER_STOCKING_AMOUNT_ARR) AS OTHER_STOCKING_AMOUNT_ARR --その他仕入金額(未払金)
					  ,SUM(OTHER_STOCKING_AMOUNT_EXC) AS OTHER_STOCKING_AMOUNT_EXC --その他仕入金額(以外)
					  ,SUM(OFFSET_AMOUNT_ACC) AS OFFSET_AMOUNT_ACC --相殺金額(買掛金)
					  ,SUM(OFFSET_AMOUNT_ARR) AS OFFSET_AMOUNT_ARR --相殺金額(未払金)
					  ,SUM(OFFSET_AMOUNT_EXC) AS OFFSET_AMOUNT_EXC --相殺金額(以外)
					  ,SUM(TAX_AMOUNT_ACC) AS TAX_AMOUNT_ACC --消費税額(買掛金)
					  ,SUM(TAX_AMOUNT_ARR) AS TAX_AMOUNT_ARR --消費税額(未払金)
					  ,SUM(TAX_AMOUNT_EXC) AS TAX_AMOUNT_EXC --消費税額(以外)
					  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --買掛残
				FROM   (SELECT HEADER.ORGANIZATION_CD           AS ORGANIZATION_CD --部署コード
							  ,HEADER.SUPPLIER_CD               AS VENDER_CD --支払先コード
							  ,HEADER.ACCOUNT_PAYABLE_BREAKDOWN AS BALANCE_FORWARD_ACC --買掛残(買掛金)
							  ,HEADER.ARREARAGE_BREAKDOWN       AS BALANCE_FORWARD_ARR --買掛残(未払金)
							  ,HEADER.EXCEPT_BREAKDOWN          AS BALANCE_FORWARD_EXC --買掛残(以外)
							  ,0                                AS STOCKING_AMOUNT_ACC
							  ,0                                AS STOCKING_AMOUNT_ARR
							  ,0                                AS STOCKING_AMOUNT_EXC
							  ,0                                AS PAYMENT_AMOUNT_ACC
							  ,0                                AS PAYMENT_AMOUNT_ARR
							  ,0                                AS PAYMENT_AMOUNT_EXC
							  ,0                                AS OTHER_PAYMENT_AMOUNT_ACC
							  ,0                                AS OTHER_PAYMENT_AMOUNT_ARR
							  ,0                                AS OTHER_PAYMENT_AMOUNT_EXC
							  ,0                                AS STOCKING_RETURNED_AMOUNT_ACC
							  ,0                                AS STOCKING_RETURNED_AMOUNT_ARR
							  ,0                                AS STOCKING_RETURNED_AMOUNT_EXC
							  ,0                                AS STOCKING_DISCOUNT_AMOUNT_ACC
							  ,0                                AS STOCKING_DISCOUNT_AMOUNT_ARR
							  ,0                                AS STOCKING_DISCOUNT_AMOUNT_EXC
							  ,0                                AS OTHER_STOCKING_AMOUNT_ACC
							  ,0                                AS OTHER_STOCKING_AMOUNT_ARR
							  ,0                                AS OTHER_STOCKING_AMOUNT_EXC
							  ,0                                AS OFFSET_AMOUNT_ACC
							  ,0                                AS OFFSET_AMOUNT_ARR
							  ,0                                AS OFFSET_AMOUNT_EXC
							  ,0                                AS TAX_AMOUNT_ACC
							  ,0                                AS TAX_AMOUNT_ARR
							  ,0                                AS TAX_AMOUNT_EXC
							  ,0                                AS PAYABLE_AMOUNT
						FROM   PAYABLE_HEADER HEADER --買掛ヘッダー
							  ,(SELECT ORGANIZATION_CD
									  ,SUPPLIER_CD
									  ,MAX(PAYABLE_DATE) MAX_DATE
								FROM   PAYABLE_HEADER
								WHERE  PAYABLE_DATE < p_daPayableDate
								GROUP  BY ORGANIZATION_CD, SUPPLIER_CD) MAX_HEADER
						WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
							   HEADER.ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						AND    HEADER.PAYABLE_AMOUNT <> 0
						AND    HEADER.ORGANIZATION_CD =
							   MAX_HEADER.ORGANIZATION_CD
						AND    HEADER.SUPPLIER_CD = MAX_HEADER.SUPPLIER_CD
						AND    HEADER.PAYABLE_DATE = MAX_HEADER.MAX_DATE)
				GROUP  BY ORGANIZATION_CD, VENDER_CD)
		GROUP  BY ORGANIZATION_CD, VENDER_CD
		ORDER  BY ORGANIZATION_CD, VENDER_CD;
BEGIN
    -- ログ出力変数初期化  
    TRNERRMAN.INIT_LOG(DEF_PROC_NAME);

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '処理開始', P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '初期処理：実行条件取得', P_STR_INPUTOR_CD);

    msg := '実行条件＝';
    msg := msg || '部署コード：' || NVL(P_STR_ORGANIZATION_CD, '') || ',';
    msg := msg || '締め年月：' || P_STR_PAYABLE_DATE || ',';
    
    IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
       msg := msg || '締め処理区分：仮締め';
    ELSE
       msg := msg || '締め処理区分：本締め';
    END IF;

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, msg, P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理開始', P_STR_INPUTOR_CD);

	-- 文字型をDate型へキャスト
	daPayableDate := TO_DATE(P_STR_PAYABLE_DATE, 'YYYY/MM/DD');
	--------------------------------------------------------------------------------
	--仮締めデータ削除処理
	--------------------------------------------------------------------------------

	IF TRNERRMAN.LOCK_PROC(22) = 0 THEN
            ROLLBACK;
            P_NUM_RET := 9;

            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            -- 2015/11/09 insert ログ追加 -->
      	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            -- <-----------------------------
            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	    RETURN;
    	END IF;

	-------------------------------------------------
	--★仮締め買掛ヘッダー削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_HEADER
	WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
		   ORGANIZATION_CD = P_STR_ORGANIZATION_CD);

	IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
            ROLLBACK;
            P_NUM_RET := 9;

            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            -- 2015/11/09 insert ログ追加 -->
      	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            -- <-----------------------------
            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	    RETURN;
    	END IF;

	-------------------------------------------------
	--★仮締め買掛購買外注オーダー削除
	-------------------------------------------------
	DELETE FROM TMPPAY_PURCHASE_SUBCONTRACT
	WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
		   CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD);

	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
            ROLLBACK;
            P_NUM_RET := 9;

            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            -- 2015/11/09 insert ログ追加 -->
      	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            -- <-----------------------------
            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	    RETURN;
    	END IF;

	-------------------------------------------------
	--★仮締め買掛入金トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_CREDIT
	WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
		   ORGANIZATION_CD = P_STR_ORGANIZATION_CD);

	IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
            ROLLBACK;
            P_NUM_RET := 9;

            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            -- 2015/11/09 insert ログ追加 -->
      	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            -- <-----------------------------
            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	    RETURN;
    	END IF;

	-------------------------------------------------
	--★仮締め買掛支払トランザクション削除
	-------------------------------------------------
	DELETE FROM TEMPORARY_PAYABLE_PAYMENT
	WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
		   ORGANIZATION_CD = P_STR_ORGANIZATION_CD);

	IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
            ROLLBACK;
            P_NUM_RET := 9;

            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
            -- 2015/11/09 insert ログ追加 -->
      	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
            -- <-----------------------------
            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	    RETURN;
    	END IF;

	-------------------------------------------------
	--★仮締め買掛グループ間相殺トランザクション削除
	-------------------------------------------------
	DELETE FROM TMPPAY_OFFSET_GROUP_DATA
	WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
		   ORGANIZATION_CD = P_STR_ORGANIZATION_CD);
	--------------------------------------------------------------------------------
	--【仮締め】の場合
	--------------------------------------------------------------------------------
	IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
    	    IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
                ROLLBACK;
                P_NUM_RET := 9;

                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                -- 2015/11/09 insert ログ追加 -->
          	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                -- <-----------------------------
                TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	        RETURN;
    	    END IF;

		-------------------------------------------------
		--★仮締め買掛購買外注オーダー登録
		-------------------------------------------------
		INSERT INTO TMPPAY_PURCHASE_SUBCONTRACT
			SELECT PURCHASE_SUBCONTRACT.*
			FROM   (SELECT PURCHASE_SUBCONTRACT.PURCHASE_NO
						  ,PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
						  ,PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO
						  ,PURCHASE_SUBCONTRACT.ORDER_NO
						  ,PURCHASE_SUBCONTRACT.ORDER_ROW_NO
						  ,PURCHASE_SUBCONTRACT.ASP_ORDER_NO
						  ,PURCHASE_SUBCONTRACT.MATERIAL_DIVISION
						  ,PURCHASE_SUBCONTRACT.SI_ORDER_NO
						  ,PURCHASE_SUBCONTRACT.ORDER_DATE
						  ,PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
						  ,PURCHASE_SUBCONTRACT.ORGANIZATION_CD
						  ,PURCHASE_SUBCONTRACT.TANTO_CD
						  ,PURCHASE_SUBCONTRACT.ORDER_DIVISION
						  ,PURCHASE_SUBCONTRACT.VENDER_CD
						  ,PURCHASE_SUBCONTRACT.ITEM_CD
						  ,PURCHASE_SUBCONTRACT.ITEM_NAME
						  ,PURCHASE_SUBCONTRACT.ORDER_QUANTITY
						  ,PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY
						  ,PURCHASE_SUBCONTRACT.ORDER_UNITPRICE
						  ,PURCHASE_SUBCONTRACT.UNITPRICE_DEFINEUNIT
						  ,PURCHASE_SUBCONTRACT.SUPPLIER_ORD_AMOUNT
						  ,PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEET_REMARK
						  ,PURCHASE_SUBCONTRACT.REMARK
						  ,PURCHASE_SUBCONTRACT.NOTES
						  ,PURCHASE_SUBCONTRACT.LOCATION_CD
						  ,PURCHASE_SUBCONTRACT.STATUS
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEE_FLAG
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEE_DATE
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEEL_TANTO_CD
						  ,PURCHASE_SUBCONTRACT.GOOD_HOUSING_SUM
						  ,PURCHASE_SUBCONTRACT.REPLY_CONTENTS_DIVISION
						  ,PURCHASE_SUBCONTRACT.DELIVERY_COMP
						  ,PURCHASE_SUBCONTRACT.NEXT_DELIVERLIMIT_DATE
						  ,PURCHASE_SUBCONTRACT.DATA_TYPE
						  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
						  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
						  ,PURCHASE_SUBCONTRACT.STOCKING_DATE
						  ,PURCHASE_SUBCONTRACT.ACCOUNT_YEARS
						  ,PURCHASE_SUBCONTRACT.SLIP_NO
						  ,PURCHASE_SUBCONTRACT.ROW_NO
						  ,PURCHASE_SUBCONTRACT.CANCEL_SLIP_NO
						  ,PURCHASE_SUBCONTRACT.CANCEL_ROW_NO
						  ,PURCHASE_SUBCONTRACT.SUPPLIER_LOTNO
						  ,PURCHASE_SUBCONTRACT.LOT_NO
						  ,PURCHASE_SUBCONTRACT.STOCK_LOCATION_CD
						  ,PURCHASE_SUBCONTRACT.HOUSING_LOCATION_CD
						  ,PURCHASE_SUBCONTRACT.ARRIVAL_QUANTITY
						  ,PURCHASE_SUBCONTRACT.STOCK_QUANTITY
						  ,PURCHASE_SUBCONTRACT.ACCEPT_QUANTITY
						  ,PURCHASE_SUBCONTRACT.ACCEPT_CONVERT_QUANTITY
						  ,PURCHASE_SUBCONTRACT.INCREASE_QUANTITY
						  ,PURCHASE_SUBCONTRACT.STOCKING_QUANTITY
						  ,PURCHASE_SUBCONTRACT.HOUSING_UNITPRICE
						  ,PURCHASE_SUBCONTRACT.FARE_AMOUNT
						  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
						  ,PURCHASE_SUBCONTRACT.ACCEPT_DATE
						  ,PURCHASE_SUBCONTRACT.ORDER_SHEET_REMARK2
						  ,PURCHASE_SUBCONTRACT.REMARK2
						  ,PURCHASE_SUBCONTRACT.NOTES2
						  ,PURCHASE_SUBCONTRACT.STATUS2
						  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
						  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
						  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
						  ,PURCHASE_SUBCONTRACT.ACCOUNT_DEBIT_SECTION_CD
						  ,PURCHASE_SUBCONTRACT.ACCOUNT_CREDIT_SECTION_CD
						  ,PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD
						  ,PURCHASE_SUBCONTRACT.DEBIT_SUB_TITLE_CD
						  ,PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD
						  ,PURCHASE_SUBCONTRACT.CREDIT_SUB_TITLE_CD
						  ,PURCHASE_SUBCONTRACT.PAYABLE_TARGET_DIVISION
						  ,PURCHASE_SUBCONTRACT.PAYMENT_TARGET_DIVISION
						  ,PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION
						  ,PURCHASE_SUBCONTRACT.PAYABLE_NO
						  ,PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION
						  ,PURCHASE_SUBCONTRACT.PAYMENT_NO
						  ,PURCHASE_SUBCONTRACT.SUMMARY_CD
						  ,PURCHASE_SUBCONTRACT.SUMMARY
						  ,PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE
						  ,PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE
						  ,PURCHASE_SUBCONTRACT.TRANSMISSION_DATE
						  ,PURCHASE_SUBCONTRACT.LABEL_FLAG
						  ,PURCHASE_SUBCONTRACT.LABEL_DATE
						  ,PURCHASE_SUBCONTRACT.LABEL_TANTO_CD
						  ,PURCHASE_SUBCONTRACT.TMP_UNITPRICE_FLG
						  ,PURCHASE_SUBCONTRACT.INSPECT_METHOD
						  ,PURCHASE_SUBCONTRACT.MORTGAGE_DETAIL_FLG
						  ,PURCHASE_SUBCONTRACT.INSPRECEIPT_WAIT_QUANTITY
						  ,PURCHASE_SUBCONTRACT.BAD_QUANTITY
						  ,PURCHASE_SUBCONTRACT.COST_ENTRY
						  ,PURCHASE_SUBCONTRACT.ADV_PUR_NOTICE_DECIDE_DIVISION
						  ,PURCHASE_SUBCONTRACT.ORDER_MORTGAGED_QUANTITY
						  ,PURCHASE_SUBCONTRACT.SUM_HOUSING_CONVERT_QUANT
						  ,PURCHASE_SUBCONTRACT.INSPECT_WAIT_CONVERT_QUANTITY
						  ,PURCHASE_SUBCONTRACT.EXTRACTIONS_QUANTITY
						  ,PURCHASE_SUBCONTRACT.DEFECTIVE_QUANTITY
						  ,PURCHASE_SUBCONTRACT.CHECK_TANTO_CD
						  ,PURCHASE_SUBCONTRACT.LABORATORY_METHOD
						  ,PURCHASE_SUBCONTRACT.PROVISION_DIVISION
						  ,PURCHASE_SUBCONTRACT.CHECK_DATE
						  ,PURCHASE_SUBCONTRACT.TAX_RATIO
						  ,PURCHASE_SUBCONTRACT.CHECK_QUANTITY
						  ,PURCHASE_SUBCONTRACT.SLIP_ISSUE_DIVISION
						  ,PURCHASE_SUBCONTRACT.SLIP_ISSUE_DATE
						  ,PURCHASE_SUBCONTRACT.APPROVAL_STATUS
						  ,PURCHASE_SUBCONTRACT.APPROVEDBY
						  ,PURCHASE_SUBCONTRACT.APPROVALDATE
						  ,PURCHASE_SUBCONTRACT.INPUT_DATE
						  ,PURCHASE_SUBCONTRACT.INPUTOR_CD
						  ,PURCHASE_SUBCONTRACT.UPDATE_DATE
						  ,PURCHASE_SUBCONTRACT.UPDATOR_CD
					FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
						  ,VENDER               VENDER
					WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
						   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
						   P_STR_ORGANIZATION_CD)
					AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
						   daPayableDate
					AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
						   c_PayableUpdDiv0 --未処理
					AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL
					AND    PURCHASE_SUBCONTRACT.STATUS2 = c_Status2Sts3 --承認済
					AND    PURCHASE_SUBCONTRACT.ROW_NO IN (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
					AND    PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD
					AND    VENDER.VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
				  ,CLASSIFICATION CLASSIFICATION --分類マスタ
				  ,ACCOUNTS AC_KARI --科目マスタ(借方)
				  ,ACCOUNTS AC_KASI --科目マスタ(貸方)
			WHERE  PURCHASE_SUBCONTRACT.DATA_TYPE =
				   CLASSIFICATION.DATA_TYPE
			AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
				   CLASSIFICATION.DATA_TOTAL_DIVISION
			AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
				   CLASSIFICATION.CATEGORY_DIVISION
			AND    PURCHASE_SUBCONTRACT.DEBIT_TITLE_CD =
				   AC_KARI.ACCOUNTS_CD(+)
			AND    PURCHASE_SUBCONTRACT.CREDIT_TITLE_CD =
				   AC_KASI.ACCOUNTS_CD(+);

    	        IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    ROLLBACK;
                    P_NUM_RET := 9;

                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                    -- 2015/11/09 insert ログ追加 -->
              	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                    -- <-----------------------------
                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            RETURN;
    	        END IF;

		-------------------------------------------------
		--★仮締め買掛入金トランザクション登録
		-------------------------------------------------
		INSERT INTO TEMPORARY_PAYABLE_CREDIT
			SELECT CREDIT.*
			FROM   CREDIT         CREDIT --入金トラン
				  ,CLASSIFICATION CLASSIFICATION --分類マスタ
				  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
				  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
			WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
				   CREDIT.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
			AND    CREDIT.CREDIT_DATE <= daPayableDate
			AND    CREDIT.DATA_TOTAL_DIVISION = c_DataDivNyuSousai --相殺
			AND    CREDIT.PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 --未処理
			AND    CREDIT.PAYABLE_UPDATE_DATE IS NULL
			AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
			AND    CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
			AND    CREDIT.DATA_TOTAL_DIVISION =
				   CLASSIFICATION.DATA_TOTAL_DIVISION
			AND    CREDIT.CATEGORY_DIVISION =
				   CLASSIFICATION.CATEGORY_DIVISION
			AND    CREDIT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
			AND    CREDIT.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+);

    	        IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
                    ROLLBACK;
                    P_NUM_RET := 9;

                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                    -- 2015/11/09 insert ログ追加 -->
              	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                    -- <-----------------------------
                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            RETURN;
    	        END IF;

		-------------------------------------------------
		--★仮締め買掛支払トランザクション登録
		-------------------------------------------------
		INSERT INTO TEMPORARY_PAYABLE_PAYMENT
			SELECT PAYMENT.*
			FROM   PAYMENT        PAYMENT --支払トラン
				  ,CLASSIFICATION CLASSIFICATION --分類マスタ
				  ,ACCOUNTS       AC_KARI --科目マスタ(借方)
				  ,ACCOUNTS       AC_KASI --科目マスタ(貸方)
			WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
				   PAYMENT.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
			AND    PAYMENT.PAYMENT_DATE <= daPayableDate
			AND    PAYMENT.PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 --未処理
			AND    PAYMENT.PAYABLE_UPDATE_DATE IS NULL
			AND    PAYMENT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
			AND    PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
			AND    PAYMENT.DATA_TOTAL_DIVISION =
				   CLASSIFICATION.DATA_TOTAL_DIVISION
			AND    PAYMENT.CATEGORY_DIVISION =
				   CLASSIFICATION.CATEGORY_DIVISION
			AND    PAYMENT.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
			AND    PAYMENT.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+);

    	        IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
                    ROLLBACK;
                    P_NUM_RET := 9;

                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                    -- 2015/11/09 insert ログ追加 -->
              	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                    -- <-----------------------------
                    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            RETURN;
    	        END IF;

		-------------------------------------------------
		--★仮締め買掛グループ間相殺トランザクション登録
		-------------------------------------------------
		INSERT INTO TMPPAY_OFFSET_GROUP_DATA
			SELECT OFFSET.*
			FROM   OFFSET_GROUP_DATA OFFSET --グループ間トラン
				  ,CLASSIFICATION    CLASSIFICATION --分類マスタ
				  ,ACCOUNTS          AC_KARI --科目マスタ(借方)
				  ,ACCOUNTS          AC_KASI --科目マスタ(貸方)
			WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
				   OFFSET.ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
			AND    OFFSET.OFFSET_DATE <= daPayableDate
			AND    OFFSET.DATA_TOTAL_DIVISION = c_DataDivSouSousai --グループ間相殺
			AND    OFFSET.PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 --未処理
			AND    OFFSET.PAYABLE_UPDATE_DATE IS NULL
			AND    OFFSET.PAYABLE_OFFSET_AMOUNT IS NOT NULL --買掛相殺金額
			AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
			AND    OFFSET.VENDER_DIVISION = c_VenderDivSI --仕入先
			AND    OFFSET.DATA_TYPE = CLASSIFICATION.DATA_TYPE
			AND    OFFSET.DATA_TOTAL_DIVISION =
				   CLASSIFICATION.DATA_TOTAL_DIVISION
			AND    OFFSET.CATEGORY_DIVISION =
				   CLASSIFICATION.CATEGORY_DIVISION
			AND    OFFSET.DEBIT_TITLE_CD = AC_KARI.ACCOUNTS_CD(+)
			AND    OFFSET.CREDIT_TITLE_CD = AC_KASI.ACCOUNTS_CD(+);
	END IF;
	--------------------------------------------------------------------------------
	--【仮締め】の場合
	--------------------------------------------------------------------------------
	IF P_STR_TEMP_CLOSING_FLG = c_TempClosingFlg1 THEN
		--------------------------------------------------------------------------------
		--仮締め仕入データ抽出処理
		--------------------------------------------------------------------------------
		/* 仮締め仕入データ抽出 カーソル */
		FOR REC_MAIN IN curTempSales(daPayableDate) LOOP
			-- 仮締め買掛番号取得
			nvPayableNo := FUN_GET_PAYABLE_NO_KARI(P_STR_INPUTOR_CD);
			-- 仮締め買掛ヘッダーデータ
			rtRecWork.PAYABLE_NO                := nvPayableNo; -- 買掛番号
			rtRecWork.ORGANIZATION_CD           := REC_MAIN.ORGANIZATION_CD; -- 部署コード
			rtRecWork.SUPPLIER_CD               := REC_MAIN.VENDER_CD; -- 支払先コード
			rtRecWork.PAYABLE_DATE              := daPayableDate; -- 買掛締め日
			rtRecWork.BALANCE_FORWARD           := REC_MAIN.BALANCE_FORWARD; -- 前月繰越
			rtRecWork.STOCKING_AMOUNT           := REC_MAIN.STOCKING_AMOUNT; -- 仕入金額
			rtRecWork.PAYMENT_AMOUNT            := REC_MAIN.PAYMENT_AMOUNT; -- 支払金額
			rtRecWork.OTHER_PAYMENT_AMOUNT      := REC_MAIN.OTHER_PAYMENT_AMOUNT; -- その他支払金額
			rtRecWork.STOCKING_RETURNED_AMOUNT  := REC_MAIN.STOCKING_RETURNED_AMOUNT; -- 返品金額
			rtRecWork.STOCKING_DISCOUNT_AMOUNT  := REC_MAIN.STOCKING_DISCOUNT_AMOUNT; -- 値引金額
			rtRecWork.OTHER_STOCKING_AMOUNT     := REC_MAIN.OTHER_STOCKING_AMOUNT; -- その他仕入金額
			rtRecWork.OFFSET_AMOUNT             := REC_MAIN.OFFSET_AMOUNT; -- 相殺金額
			rtRecWork.TAX_AMOUNT                := REC_MAIN.TAX_AMOUNT; -- 消費税額
			rtRecWork.PAYABLE_AMOUNT            := REC_MAIN.PAYABLE_AMOUNT; -- 買掛残
			rtRecWork.ACCOUNT_PAYABLE_BREAKDOWN := REC_MAIN.ACCOUNT_PAYABLE_BREAKDOWN; -- 買掛金(内訳)
			rtRecWork.ARREARAGE_BREAKDOWN       := REC_MAIN.ARREARAGE_BREAKDOWN; -- 未払金(内訳)
			rtRecWork.EXCEPT_BREAKDOWN          := REC_MAIN.EXCEPT_BREAKDOWN; -- 以外(内訳)
			rtRecWork.INPUT_DATE                := SYSDATE; -- 登録日時
			rtRecWork.INPUTOR_CD                := P_STR_INPUTOR_CD; -- 登録者ＩＤ
			rtRecWork.UPDATE_DATE               := SYSDATE; -- 更新日時
			rtRecWork.UPDATOR_CD                := P_STR_INPUTOR_CD; -- 更新者ＩＤ

    	        	IF TRNERRMAN.LOCK_PROC(22) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★仮締め買掛ヘッダー登録
			--------------------------------------------------------------------------------
			INSERT INTO TEMPORARY_PAYABLE_HEADER VALUES rtRecWork;
			----------------------------------------------------------------------------------------------------------
			--【買掛対象】【買掛対象外】の各トランザクション更新処理
			----------------------------------------------------------------------------------------------------------

    	        	IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】仮締め購買外注オーダーファイル更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TMPPAY_PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め購買外注オーダーファイル
								   ,VENDER                      VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
					 );

    	        	IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TMPPAY_PURCHASE_SUBCONTRACT ST
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め購買外注オーダーファイル
								   ,VENDER                      VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
					 );

    	        	IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】仮締め購買外注オーダーファイル更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TMPPAY_PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め購買外注オーダーファイル
								   ,VENDER                      VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv0 --買掛対象外
					 );

    	        	IF TRNERRMAN.LOCK_PROC(18) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TMPPAY_PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   TMPPAY_PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --仮締め購買外注オーダーファイル
								   ,VENDER                      VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv0 --買掛対象外
					 );

    	        	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】仮締め入金トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TEMPORARY_PAYABLE_CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TEMPORARY_PAYABLE_CREDIT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TEMPORARY_PAYABLE_CREDIT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TEMPORARY_PAYABLE_CREDIT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TEMPORARY_PAYABLE_CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TEMPORARY_PAYABLE_CREDIT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TEMPORARY_PAYABLE_CREDIT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TEMPORARY_PAYABLE_CREDIT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】仮締め入金トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TEMPORARY_PAYABLE_CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

    	        	IF TRNERRMAN.LOCK_PROC(19) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TEMPORARY_PAYABLE_CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】仮締め支払トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TEMPORARY_PAYABLE_PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TEMPORARY_PAYABLE_PAYMENT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TEMPORARY_PAYABLE_PAYMENT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TEMPORARY_PAYABLE_PAYMENT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TEMPORARY_PAYABLE_PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TEMPORARY_PAYABLE_PAYMENT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TEMPORARY_PAYABLE_PAYMENT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TEMPORARY_PAYABLE_PAYMENT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】仮締め支払トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TEMPORARY_PAYABLE_PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(20) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TEMPORARY_PAYABLE_PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】仮締めグループ間相殺トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TMPPAY_OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TMPPAY_OFFSET_GROUP_DATA.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TMPPAY_OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TMPPAY_OFFSET_GROUP_DATA.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TMPPAY_OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  TMPPAY_OFFSET_GROUP_DATA.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    TMPPAY_OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    TMPPAY_OFFSET_GROUP_DATA.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】仮締めグループ間相殺トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE TMPPAY_OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			;

    	        	IF TRNERRMAN.LOCK_PROC(21) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '仮締め買掛グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE TMPPAY_OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
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

			TRNERRMAN.LOGOUT(DEF_PROC_NAME, '対象データがありません', P_STR_INPUTOR_CD);
            		TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);

			RETURN;
		END IF;
	ELSE
		--------------------------------------------------------------------------------
		--【本締め】の場合
		--------------------------------------------------------------------------------
		--------------------------------------------------------------------------------
		--仕入データ抽出処理
		--------------------------------------------------------------------------------
		/* 仕入データ抽出 カーソル */
		FOR REC_MAIN IN curSales(daPayableDate) LOOP
			-- 買掛番号取得
			nvPayableNo := FUN_GET_PAYABLE_NO(P_STR_INPUTOR_CD);
			-- 買掛ヘッダーデータ
			rtRecWork.PAYABLE_NO                := nvPayableNo; -- 買掛番号
			rtRecWork.ORGANIZATION_CD           := REC_MAIN.ORGANIZATION_CD; -- 部署コード
			rtRecWork.SUPPLIER_CD               := REC_MAIN.VENDER_CD; -- 支払先コード
			rtRecWork.PAYABLE_DATE              := daPayableDate; -- 買掛締め日
			rtRecWork.BALANCE_FORWARD           := REC_MAIN.BALANCE_FORWARD; -- 前月繰越
			rtRecWork.STOCKING_AMOUNT           := REC_MAIN.STOCKING_AMOUNT; -- 仕入金額
			rtRecWork.PAYMENT_AMOUNT            := REC_MAIN.PAYMENT_AMOUNT; -- 支払金額
			rtRecWork.OTHER_PAYMENT_AMOUNT      := REC_MAIN.OTHER_PAYMENT_AMOUNT; -- その他支払金額
			rtRecWork.STOCKING_RETURNED_AMOUNT  := REC_MAIN.STOCKING_RETURNED_AMOUNT; -- 返品金額
			rtRecWork.STOCKING_DISCOUNT_AMOUNT  := REC_MAIN.STOCKING_DISCOUNT_AMOUNT; -- 値引金額
			rtRecWork.OTHER_STOCKING_AMOUNT     := REC_MAIN.OTHER_STOCKING_AMOUNT; -- その他仕入金額
			rtRecWork.OFFSET_AMOUNT             := REC_MAIN.OFFSET_AMOUNT; -- 相殺金額
			rtRecWork.TAX_AMOUNT                := REC_MAIN.TAX_AMOUNT; -- 消費税額
			rtRecWork.PAYABLE_AMOUNT            := REC_MAIN.PAYABLE_AMOUNT; -- 買掛残
			rtRecWork.ACCOUNT_PAYABLE_BREAKDOWN := REC_MAIN.ACCOUNT_PAYABLE_BREAKDOWN; -- 買掛金(内訳)
			rtRecWork.ARREARAGE_BREAKDOWN       := REC_MAIN.ARREARAGE_BREAKDOWN; -- 未払金(内訳)
			rtRecWork.EXCEPT_BREAKDOWN          := REC_MAIN.EXCEPT_BREAKDOWN; -- 以外(内訳)
			rtRecWork.INPUT_DATE                := SYSDATE; -- 登録日時
			rtRecWork.INPUTOR_CD                := P_STR_INPUTOR_CD; -- 登録者ＩＤ
			rtRecWork.UPDATE_DATE               := SYSDATE; -- 更新日時
			rtRecWork.UPDATOR_CD                := P_STR_INPUTOR_CD; -- 更新者ＩＤ

    	        	IF TRNERRMAN.LOCK_PROC(23) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★買掛ヘッダー登録
			--------------------------------------------------------------------------------
			INSERT INTO PAYABLE_HEADER VALUES rtRecWork;
			----------------------------------------------------------------------------------------------------------
			--【買掛対象】【買掛対象外】の各トランザクション更新処理
			----------------------------------------------------------------------------------------------------------

    	        	IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】購買外注オーダーファイル更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
								   ,VENDER               VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
					 );

    	        	IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE PURCHASE_SUBCONTRACT ST
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
								   ,VENDER               VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv1 --買掛対象
					 );

    	        	IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】購買外注オーダーファイル更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
								   ,VENDER               VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv0 --買掛対象外
					 );

    	        	IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE PURCHASE_SUBCONTRACT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
				  ,PAYEE_CD               =
				   ( -- 支払先コード
					SELECT DECODE(PAYMENT_INVOICE_CD
								  ,NULL
								  ,VENDER_CD
								  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
					FROM   VENDER
					WHERE  VENDER_CD = REC_MAIN.VENDER_CD
					AND    VENDER_DIVISION = c_VenderDivSI)
			WHERE  PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    ROWID IN
				   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
					 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
								   ,PURCHASE_SUBCONTRACT.VENDER_CD
								   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
								   ,PURCHASE_SUBCONTRACT.DATA_TYPE
								   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
								   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
								   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
							 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
								   ,VENDER               VENDER
							 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
									REC_MAIN.ORGANIZATION_CD
							 AND    PURCHASE_SUBCONTRACT.STOCKING_DATE <=
									daPayableDate
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION =
									c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
							 AND    PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
							 AND    PURCHASE_SUBCONTRACT.STATUS2 =
									c_Status2Sts3 --承認済
							 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
									VENDER.VENDER_CD
							 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
						   ,CLASSIFICATION CLASSIFICATION --分類マスタ
					 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
							REC_MAIN.VENDER_CD AND
							PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
							PURCHASE_SUBCONTRACT.PAYEE_CD =
							REC_MAIN.VENDER_CD)
					 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION
					 AND    CLASSIFICATION.CREDIT_DIVISION = c_CreditDiv0 --買掛対象外
					 );

    	        	IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】入金トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
					 AND    CREDIT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    CREDIT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
					 AND    CREDIT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    CREDIT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】入金トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE CREDIT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    CREDIT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】支払トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
					 AND    PAYMENT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PAYMENT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
					 AND    PAYMENT.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    PAYMENT.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】支払トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE PAYMENT
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
			AND    PAYMENT_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			;

    	        	IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象】グループ間相殺トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv1 -- 買掛対象(処理済)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_NO              = nvPayableNo -- 買掛番号
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    DEPOSIT_OFFSET_AMOUNT IS NOT NULL
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			AND    c_CreditDiv1 =
				   ( -- 買掛対象
					 SELECT CREDIT_DIVISION
					 FROM   CLASSIFICATION
					 WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
							CLASSIFICATION.DATA_TYPE
					 AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
							CLASSIFICATION.DATA_TOTAL_DIVISION
					 AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
							CLASSIFICATION.CATEGORY_DIVISION);

    	        	IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			--------------------------------------------------------------------------------
			--★【買掛対象外】グループ間相殺トランザクション更新
			--------------------------------------------------------------------------------
			-- 支払更新済対象(会計関連項目は支払更新でセット済のため買掛関連項目のみ更新)
			UPDATE OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			AND    PAYMENT_UPDATE_DATE IS NOT NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
			;

    	        	IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
                    	    ROLLBACK;
                    	    P_NUM_RET := 9;

                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                          -- 2015/11/09 insert ログ追加 -->
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                          -- <-----------------------------
                    	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
       	            	    RETURN;
    	        	END IF;

			-- 支払更新未処理対象(会計関連項目と買掛関連項目を更新)
			UPDATE OFFSET_GROUP_DATA
			SET    PAYABLE_TARGET_DIVISION = c_PayableTrgDiv9 -- 買掛対象(対象外)
				  ,PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
				  ,PAYABLE_UPDATE_DATE     = daPayableDate -- 買掛締め日
				  ,UPDATE_DATE             = SYSDATE -- 更新日時
				  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
			AND    VENDER_CD = REC_MAIN.VENDER_CD
			AND    OFFSET_DATE <= daPayableDate
			AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
			AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
			AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
			AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
			AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
			AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
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

			TRNERRMAN.LOGOUT(DEF_PROC_NAME, '対象データがありません', P_STR_INPUTOR_CD);
            		TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);

			RETURN;
		END IF;
	END IF;
	--------------------------------------------------------------------------------
	--戻り値設定
	--------------------------------------------------------------------------------
	P_NUM_RET := 0;

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
		ERR_CODE := nvCount || '件' || SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM, 1, 1024);
		--ロールバック
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_APUPDATE'
						,P_STR_INPUTOR_CD
						,ERR_CODE
						,ERR_MSG);
		--戻り値設定
		P_NUM_RET := 99;

        -- ログ出力
        TRNERRMAN.LOGOUT(DEF_PROC_NAME, ERR_MSG, P_STR_INPUTOR_CD);
        TRNERRMAN.LOGOUT(DEF_PROC_NAME, '買掛更新処理終了', P_STR_INPUTOR_CD);
END PRO_APUPDATE;
/
