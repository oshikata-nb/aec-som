CREATE OR REPLACE VIEW REP_BILLISSUE_DETAIL
    (CLAIM_NO,HEAD_VENDER_CD,CREDIT_DATE,TRAN_VENDER_CD,VENDER_NAME1,VENDER_NAME2,
    TRAN_DATE,CUSTOMER_ORDER_NO,CATEGORY_NAME,ITEM_CD,STYLE_OF_PACKING,
    QUANTITY,UNIT,UNITPRICE,AMOUNT,TAX_AMOUNT,REMARK,DELIVERY_ADDRESS,SLIP_NO,
    ROW_NO,SALES_SLIP_NO,SALES_SLIP_ROW_NO,ITEM_NAME,ESTIMATE_MATSS,ESTIMATE_UNIT,
    ESTIMATE_UNITPRICE,ESTIMATE_AMOUNT,ESTIMATE_TAX_AMOUNT)
AS
SELECT HEAD.CLAIM_NO --請求番号
      ,HEAD.VENDER_CD           AS HEAD_VENDER_CD --取引先
      ,HEAD.CREDIT_DATE --請求締め日
      ,TRAN.VENDER_CD           AS TRAN_VENDER_CD --届先コード
      ,TRAN.DELIVERY_NAME1      AS VENDER_NAME1 --届先名１
      ,TRAN.DELIVERY_NAME2      AS VENDER_NAME2 --届先名２
      ,TRAN.TRAN_DATE --売上日付
      ,TRAN.CUSTOMER_ORDER_NO --先方発注No
      ,TRAN.CATEGORY_NAME --区分
      ,TRAN.ITEM_CD --品目コード
      ,TRAN.STYLE_OF_PACKING --荷姿
      ,TRAN.QUANTITY --数量
      ,TRAN.UNIT --単位
      ,TRAN.UNITPRICE --単価
      ,TRAN.AMOUNT --金額
      ,TRAN.TAX_AMOUNT --消費税額
      ,TRAN.REMARK -- 備考
      ,TRAN.DELIVERY_ADDRESS -- 宛先
      ,TRAN.SLIP_NO --受注番号
      ,TRAN.ROW_NO --受注行番号
      ,TRAN.SALES_SLIP_NO --売上伝票番号
      ,TRAN.SALES_SLIP_ROW_NO --売上伝票行番号
      ,TRAN.ITEM_NAME --品目名
      ,TRAN.ESTIMATE_MATSS --増付数
      ,TRAN.ESTIMATE_UNIT --単位(増付)
      ,TRAN.ESTIMATE_UNITPRICE --単価(増付)
      ,TRAN.ESTIMATE_AMOUNT --金額(増付)
      ,TRAN.ESTIMATE_TAX_AMOUNT --消費税額(増付)

FROM   (
        --前月請求高
        SELECT CLA.CLAIM_NO --請求番号
               ,NULL AS VENDER_CD --届先コード
               ,NULL AS DELIVERY_NAME1 --届先名１
               ,NULL AS DELIVERY_NAME2 --届先名２
               ,NULL AS TRAN_DATE --売上日付
               ,NULL AS SLIP_NO --受注番号
               ,NULL AS ROW_NO --受注行番号
               ,NULL AS SALES_SLIP_NO --売上伝票番号
               ,NULL AS SALES_SLIP_ROW_NO --売上伝票行番号
               ,NULL AS CATEGORY_NAME --区分
               ,NULL AS ITEM_CD --品目コード
               ,'前月請求高' AS ITEM_NAME --品目名
               ,NULL AS STYLE_OF_PACKING --荷姿
               ,NULL AS QUANTITY --数量
               ,NULL AS UNIT --単位
               ,NULL AS UNITPRICE --単価
               ,CLA.CLAIM_AMOUNT_FORWARD AS AMOUNT --金額
               ,NULL AS TAX_AMOUNT --消費税額
               ,NULL AS CUSTOMER_ORDER_NO --先方発注No
               ,NULL REMARK -- 備考
               ,NULL DELIVERY_ADDRESS -- 宛先
               ,NULL ESTIMATE_MATSS -- 増付数
               ,NULL ESTIMATE_UNIT -- 単位(増付)
               ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
               ,NULL ESTIMATE_AMOUNT -- 金額(増付)
               ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   CLAIM_HEADER CLA
        
        UNION ALL
        
        --売上
        SELECT CLAIM_NO --請求番号
              ,SALES.VENDER_CD --届先コード
              ,DELIVERY.DELIVERY_NAME1 --届先名１
              ,DELIVERY.DELIVERY_NAME2 --届先名２
              ,SALES.SALES_DATE AS TRAN_DATE --売上日付
              ,SALES.ORDER_NO AS SLIP_NO --受注番号
              ,SALES.ORDER_ROW_NO AS ROW_NO --受注行番号
              ,SALES.SALES_SLIP_NO AS SALES_SLIP_NO --売上伝票番号
              ,SALES.SALES_SLIP_ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,TO_CHAR(SALES.ITEM_CD) AS ITEM_CD --品目コード
              ,TO_CHAR(ITEM.ITEM_NAME) AS ITEM_NAME --品目名
              ,ITEM.STYLE_OF_PACKING --荷姿
              ,CASE
                   WHEN (SALES.CATEGORY_DIVISION = 2 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 12 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 3 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = 13 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = -1 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -11 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -4 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -14 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -9 AND
                        SALES.DATA_TOTAL_DIVISION = 9) OR
                        (SALES.CATEGORY_DIVISION = -19 AND
                        SALES.DATA_TOTAL_DIVISION = 9) THEN
                    SALES.SALES_QUANTITY * (-1)
                   ELSE
                    SALES.SALES_QUANTITY
               END AS QUANTITY --数量
              ,NAMES.NAME01 AS UNIT --単位
              ,SALES.SALES_UNITPRICE AS UNITPRICE --単価
              ,CASE
                   WHEN (SALES.CATEGORY_DIVISION = 2 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 12 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 3 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = 13 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = -1 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -11 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -4 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -14 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -9 AND
                        SALES.DATA_TOTAL_DIVISION = 9) OR
                        (SALES.CATEGORY_DIVISION = -19 AND
                        SALES.DATA_TOTAL_DIVISION = 9) THEN
                    SALES.SALES_AMOUNT * (-1)
                   ELSE
                    SALES.SALES_AMOUNT
               END AS AMOUNT --金額
              ,CASE
                   WHEN (SALES.CATEGORY_DIVISION = 2 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 12 AND
                        SALES.DATA_TOTAL_DIVISION = 2) OR
                        (SALES.CATEGORY_DIVISION = 3 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = 13 AND
                        SALES.DATA_TOTAL_DIVISION = 3) OR
                        (SALES.CATEGORY_DIVISION = -1 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -11 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -4 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -14 AND
                        SALES.DATA_TOTAL_DIVISION = 1) OR
                        (SALES.CATEGORY_DIVISION = -9 AND
                        SALES.DATA_TOTAL_DIVISION = 9) OR
                        (SALES.CATEGORY_DIVISION = -19 AND
                        SALES.DATA_TOTAL_DIVISION = 9) THEN
                    SALES.TAX_AMOUNT * (-1)
                   ELSE
                    SALES.TAX_AMOUNT
               END AS TAX_AMOUNT --消費税額
              ,ORDER_HEAD.CUSTOMER_ORDER_NO --先方発注No
              ,SALES.REMARK -- 備考
              ,ORDER_HEAD.DELIVERY_ADDRESS -- 宛先
              ,NVL(ORDER_DETAIL.ESTIMATE_MATSS, 0) ESTIMATE_MATSS -- 増付数
              ,NAMES.NAME01 ESTIMATE_UNIT -- 単位(増付)
              ,0 ESTIMATE_UNITPRICE -- 単価(増付)
              ,0 ESTIMATE_AMOUNT -- 金額(増付)
              ,0 ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   SALES
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '1'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,DELIVERY
              ,ITEM
              ,NAMES
              ,ORDER_HEAD
              ,ORDER_DETAIL
        
        WHERE  SALES.SALES_NO IS NOT NULL
        AND    SALES.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = SALES.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '1'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    DELIVERY.DELIVERY_CD(+) = SALES.DELIVERY_CD
        AND    ITEM.ITEM_CD(+) = SALES.ITEM_CD
        AND    ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD
        AND    NAMES.NAME_DIVISION = 'UNIT'
        AND    ORDER_HEAD.ORDER_NO(+) = ORDER_DETAIL.ORDER_NO
        AND    ORDER_DETAIL.ORDER_NO(+) = SALES.ORDER_NO
        AND    ORDER_DETAIL.ROW_NO(+) = SALES.ORDER_ROW_NO
        
        UNION ALL
        
        --入金
        SELECT CLAIM_NO --請求番号
              ,CREDIT.VENDER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,CREDIT.CREDIT_DATE AS TRAN_DATE --売上日付
              ,CREDIT_NO AS SLIP_NO --入金番号
              ,ROW_NO AS ROW_NO --行番号
              ,CREDIT_NO AS SALES_SLIP_NO --売上伝票番号
              ,ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,TO_CHAR(CREDIT.REMARK) --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,CREDIT.CREDIT_AMOUNT * (-1) AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   CREDIT
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '2'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  CREDIT.CREDIT_NO IS NOT NULL
        AND    CREDIT.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = CREDIT.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '2'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'TS'
        AND    VENDER.VENDER_CD(+) = CREDIT.VENDER_CD
        
        UNION ALL
        
        --支払
        SELECT CLAIM_NO --請求番号
              ,PAYMENT.SUPPLIER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,PAYMENT.PAYMENT_DATE AS TRAN_DATE --売上日付
              ,SLIP_NO AS SLIP_NO --伝票番号
              ,ROW_NO AS ROW_NO --行番号
              ,SLIP_NO AS SALES_SLIP_NO --売上伝票番号
              ,ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,TO_CHAR(PAYMENT.SUMMARY) --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,PAYMENT.PAYMENT_AMOUNT AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   PAYMENT
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '4'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  PAYMENT.PAYMENT_NO IS NOT NULL
        AND    PAYMENT.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = PAYMENT.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '4'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'SI'
        AND    VENDER.VENDER_CD(+) = PAYMENT.SUPPLIER_CD
        
        UNION ALL
        
        --相殺
        SELECT OFFSET.CLAIM_NO --請求番号
              ,OFFSET.VENDER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,OFFSET.OFFSET_DATE AS TRAN_DATE --売上日付
              ,OFFSET.OFFSET_NO AS SLIP_NO --伝票番号
              ,NULL AS ROW_NO --行番号
              ,OFFSET.OFFSET_NO AS SALES_SLIP_NO --売上伝票番号
              ,NULL AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,'買掛相殺' --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,OFFSET.DEPOSIT_OFFSET_AMOUNT * (-1) AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   OFFSET_GROUP_DATA OFFSET
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '5'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  OFFSET.OFFSET_NO IS NOT NULL
        AND    OFFSET.CLAIM_NO IS NOT NULL
        AND    OFFSET.DEPOSIT_OFFSET_AMOUNT != 0
        AND    CLS_MIN.CATEGORY_DIVISION(+) = OFFSET.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '5'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'TS'
        AND    VENDER.VENDER_CD(+) = OFFSET.VENDER_CD
        
        ) TRAN
      ,CLAIM_HEADER HEAD

WHERE  HEAD.CLAIM_NO = TRAN.CLAIM_NO

UNION ALL
--仮締め
SELECT TMP_HEAD.CLAIM_NO --請求番号
      ,TMP_HEAD.VENDER_CD           AS HEAD_VENDER_CD --取引先
      ,TMP_HEAD.CREDIT_DATE --請求締め日
      ,TMP_TRAN.VENDER_CD           AS TRAN_VENDER_CD --届先コード
      ,TMP_TRAN.DELIVERY_NAME1      AS VENDER_NAME1 --届先名１
      ,TMP_TRAN.DELIVERY_NAME2      AS VENDER_NAME2 --届先名２
      ,TMP_TRAN.TRAN_DATE --売上日付
      ,TMP_TRAN.CUSTOMER_ORDER_NO --先方発注No
      ,TMP_TRAN.CATEGORY_NAME --区分
      ,TMP_TRAN.ITEM_CD --品目コード
      ,TMP_TRAN.STYLE_OF_PACKING --荷姿
      ,TMP_TRAN.QUANTITY --数量
      ,TMP_TRAN.UNIT --単位
      ,TMP_TRAN.UNITPRICE --単価
      ,TMP_TRAN.AMOUNT --金額
      ,TMP_TRAN.TAX_AMOUNT --消費税額
      ,TMP_TRAN.REMARK -- 備考
      ,TMP_TRAN.DELIVERY_ADDRESS -- 宛先
      ,TMP_TRAN.SLIP_NO --受注番号
      ,TMP_TRAN.ROW_NO --受注行番号
      ,TMP_TRAN.SALES_SLIP_NO --売上伝票番号
      ,TMP_TRAN.SALES_SLIP_ROW_NO --売上伝票行番号
      ,TMP_TRAN.ITEM_NAME --品目名
      ,TMP_TRAN.ESTIMATE_MATSS --増付数
      ,TMP_TRAN.ESTIMATE_UNIT --単位(増付)
      ,TMP_TRAN.ESTIMATE_UNITPRICE --単価(増付)
      ,TMP_TRAN.ESTIMATE_AMOUNT --金額(増付)
      ,TMP_TRAN.ESTIMATE_TAX_AMOUNT --消費税額(増付)

FROM   (
        --前月請求高
        SELECT TCLA.CLAIM_NO --請求番号
               ,NULL AS VENDER_CD --届先コード
               ,NULL AS DELIVERY_NAME1 --届先名１
               ,NULL AS DELIVERY_NAME2 --届先名２
               ,NULL AS TRAN_DATE --売上日付
               ,NULL AS SLIP_NO --受注番号
               ,NULL AS ROW_NO --受注行番号
               ,NULL AS SALES_SLIP_NO --売上伝票番号
               ,NULL AS SALES_SLIP_ROW_NO --売上伝票行番号
               ,NULL AS CATEGORY_NAME --区分
               ,NULL AS ITEM_CD --品目コード
               ,'前月請求高' AS ITEM_NAME --品目名
               ,NULL AS STYLE_OF_PACKING --荷姿
               ,NULL AS QUANTITY --数量
               ,NULL AS UNIT --単位
               ,NULL AS UNITPRICE --単価
               ,TCLA.CLAIM_AMOUNT_FORWARD AS AMOUNT --金額
               ,NULL AS TAX_AMOUNT --消費税額
               ,NULL AS CUSTOMER_ORDER_NO --先方発注No
               ,NULL REMARK -- 備考
               ,NULL DELIVERY_ADDRESS -- 宛先
               ,NULL ESTIMATE_MATSS -- 増付数
               ,NULL ESTIMATE_UNIT -- 単位(増付)
               ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
               ,NULL ESTIMATE_AMOUNT -- 金額(増付)
               ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   TEMPORARY_CLAIM_HEADER TCLA
        
        UNION ALL
        
        --売上
        SELECT CLAIM_NO --請求番号
              ,TMP_SALES.VENDER_CD --届先コード
              ,DELIVERY.DELIVERY_NAME1 --届先名１
              ,DELIVERY.DELIVERY_NAME2 --届先名２
              ,TMP_SALES.SALES_DATE AS TRAN_DATE --売上日付
              ,TMP_SALES.ORDER_NO AS SLIP_NO --受注番号
              ,TMP_SALES.ORDER_ROW_NO AS ROW_NO --受注行番号
              ,TMP_SALES.SALES_SLIP_NO AS SALES_SLIP_NO --売上伝票番号
              ,TMP_SALES.SALES_SLIP_ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,TO_CHAR(TMP_SALES.ITEM_CD) AS ITEM_CD --品目コード
              ,TO_CHAR(ITEM.ITEM_NAME) AS ITEM_NAME --品目名
              ,ITEM.STYLE_OF_PACKING --荷姿
              ,CASE
                   WHEN (TMP_SALES.CATEGORY_DIVISION = 2 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 12 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 3 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = 13 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = -1 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -11 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -4 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -14 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -9 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) OR
                        (TMP_SALES.CATEGORY_DIVISION = -19 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) THEN
                    TMP_SALES.SALES_QUANTITY * (-1)
                   ELSE
                    TMP_SALES.SALES_QUANTITY
               END AS QUANTITY --数量
              ,NAMES.NAME01 AS UNIT --単位
              ,TMP_SALES.SALES_UNITPRICE AS UNITPRICE --単価
              ,CASE
                   WHEN (TMP_SALES.CATEGORY_DIVISION = 2 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 12 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 3 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = 13 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = -1 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -11 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -4 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -14 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -9 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) OR
                        (TMP_SALES.CATEGORY_DIVISION = -19 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) THEN
                    TMP_SALES.SALES_AMOUNT * (-1)
                   ELSE
                    TMP_SALES.SALES_AMOUNT
               END AS AMOUNT --金額
              ,CASE
                   WHEN (TMP_SALES.CATEGORY_DIVISION = 2 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 12 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 2) OR
                        (TMP_SALES.CATEGORY_DIVISION = 3 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = 13 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 3) OR
                        (TMP_SALES.CATEGORY_DIVISION = -1 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -11 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -4 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -14 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 1) OR
                        (TMP_SALES.CATEGORY_DIVISION = -9 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) OR
                        (TMP_SALES.CATEGORY_DIVISION = -19 AND
                        TMP_SALES.DATA_TOTAL_DIVISION = 9) THEN
                    TMP_SALES.TAX_AMOUNT * (-1)
                   ELSE
                    TMP_SALES.TAX_AMOUNT
               END AS TAX_AMOUNT --消費税額
              ,ORDER_HEAD.CUSTOMER_ORDER_NO --先方発注No
              ,TMP_SALES.REMARK -- 備考
              ,ORDER_HEAD.DELIVERY_ADDRESS -- 宛先
              ,NVL(ORDER_DETAIL.ESTIMATE_MATSS, 0) ESTIMATE_MATSS -- 増付数
              ,NAMES.NAME01 ESTIMATE_UNIT -- 単位(増付)
              ,0 ESTIMATE_UNITPRICE -- 単価(増付)
              ,0 ESTIMATE_AMOUNT -- 金額(増付)
              ,0 ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   TEMPORARY_CLAIM_SALES TMP_SALES
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '1'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,DELIVERY
              ,ITEM
              ,NAMES
              ,ORDER_HEAD
              ,ORDER_DETAIL
        
        WHERE  TMP_SALES.SALES_NO IS NOT NULL
        AND    TMP_SALES.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = TMP_SALES.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '1'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    DELIVERY.DELIVERY_CD(+) = TMP_SALES.DELIVERY_CD
        AND    ITEM.ITEM_CD(+) = TMP_SALES.ITEM_CD
        AND    ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD
        AND    NAMES.NAME_DIVISION = 'UNIT'
        AND    ORDER_HEAD.ORDER_NO(+) = ORDER_DETAIL.ORDER_NO
        AND    ORDER_DETAIL.ORDER_NO(+) = TMP_SALES.ORDER_NO
        AND    ORDER_DETAIL.ROW_NO(+) = TMP_SALES.ORDER_ROW_NO
        
        UNION ALL
        
        --入金
        SELECT CLAIM_NO --請求番号
              ,TMP_CRE.VENDER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,TMP_CRE.CREDIT_DATE AS TRAN_DATE --売上日付
              ,CREDIT_NO AS SLIP_NO --入金番号
              ,ROW_NO AS ROW_NO --行番号
              ,CREDIT_NO AS SALES_SLIP_NO --売上伝票番号
              ,ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,TO_CHAR(TMP_CRE.REMARK) --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,TMP_CRE.CREDIT_AMOUNT * (-1) AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   TEMPORARY_CLAIM_CREDIT TMP_CRE
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '2'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  TMP_CRE.CREDIT_NO IS NOT NULL
        AND    TMP_CRE.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = TMP_CRE.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '2'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'TS'
        AND    VENDER.VENDER_CD(+) = TMP_CRE.VENDER_CD
        
        UNION ALL
        
        --支払
        SELECT CLAIM_NO --請求番号
              ,TMP_PAY.SUPPLIER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,TMP_PAY.PAYMENT_DATE AS TRAN_DATE --売上日付
              ,SLIP_NO AS SLIP_NO --伝票番号
              ,ROW_NO AS ROW_NO --行番号
              ,SLIP_NO AS SALES_SLIP_NO --売上伝票番号
              ,ROW_NO AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,TO_CHAR(TMP_PAY.SUMMARY) --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,TMP_PAY.PAYMENT_AMOUNT AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   TEMPORARY_CLAIM_PAYMENT TMP_PAY
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '4'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  TMP_PAY.PAYMENT_NO IS NOT NULL
        AND    TMP_PAY.CLAIM_NO IS NOT NULL
        AND    CLS_MIN.CATEGORY_DIVISION(+) = TMP_PAY.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '4'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'SI'
        AND    VENDER.VENDER_CD(+) = TMP_PAY.SUPPLIER_CD
        
        UNION ALL
        
        --相殺
        SELECT TMP_OFFSET.CLAIM_NO --請求番号
              ,TMP_OFFSET.VENDER_CD --届先コード
              ,NULL --届先名１
              ,NULL --届先名２
              ,TMP_OFFSET.OFFSET_DATE AS TRAN_DATE --売上日付
              ,TMP_OFFSET.OFFSET_NO AS SLIP_NO --伝票番号
              ,NULL AS ROW_NO --行番号
              ,TMP_OFFSET.OFFSET_NO AS SALES_SLIP_NO --売上伝票番号
              ,NULL AS SALES_SLIP_ROW_NO --売上伝票行番号
              ,CLS.CATEGORY_NAME --区分
              ,NULL --品目コード
              ,'買掛相殺' --品目名
              ,NULL --荷姿
              ,NULL AS QUANTITY --数量
              ,NULL AS UNIT --単位
              ,NULL AS UNITPRICE --単価
              ,TMP_OFFSET.DEPOSIT_OFFSET_AMOUNT * (-1) AS AMOUNT --金額
              ,NULL AS TAX_AMOUNT --消費税額
              ,NULL --先方発注No
              ,NULL REMARK -- 備考
              ,NULL DELIVERY_ADDRESS -- 宛先
              ,NULL ESTIMATE_MATSS -- 増付数
              ,NULL ESTIMATE_UNIT -- 単位(増付)
              ,NULL ESTIMATE_UNITPRICE -- 単価(増付)
              ,NULL ESTIMATE_AMOUNT -- 金額(増付)
              ,NULL ESTIMATE_TAX_AMOUNT -- 消費税額(増付)
        
        FROM   TMPCLAIM_OFFSET_GROUP_DATA TMP_OFFSET
              ,(SELECT CATEGORY_DIVISION
                      ,MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
                FROM   CLASSIFICATION
                WHERE  DATA_TYPE = '5'
                GROUP  BY CATEGORY_DIVISION) CLS_MIN
              ,CLASSIFICATION CLS
              ,VENDER
        
        WHERE  TMP_OFFSET.OFFSET_NO IS NOT NULL
        AND    TMP_OFFSET.CLAIM_NO IS NOT NULL
        AND    TMP_OFFSET.DEPOSIT_OFFSET_AMOUNT != 0
        AND    CLS_MIN.CATEGORY_DIVISION(+) = TMP_OFFSET.CATEGORY_DIVISION
        AND    CLS.DATA_TYPE(+) = '5'
        AND    CLS.CATEGORY_DIVISION(+) = CLS_MIN.CATEGORY_DIVISION
        AND    CLS.DATA_TOTAL_DIVISION(+) = CLS_MIN.DATA_TOTAL_DIVISION
        AND    VENDER.VENDER_DIVISION(+) = 'TS'
        AND    VENDER.VENDER_CD(+) = TMP_OFFSET.VENDER_CD
        
        ) TMP_TRAN
      ,TEMPORARY_CLAIM_HEADER TMP_HEAD

WHERE  TMP_HEAD.CLAIM_NO = TMP_TRAN.CLAIM_NO
/
COMMENT ON TABLE REP_BILLISSUE_DETAIL IS 'REP_請求書明細'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.CLAIM_NO IS '請求番号'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.HEAD_VENDER_CD IS '取引先コード'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.CREDIT_DATE IS '請求締め日'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.TRAN_VENDER_CD IS '届先コード'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.VENDER_NAME1 IS '届先名1'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.VENDER_NAME2 IS '届先名2'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.TRAN_DATE IS '日付'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.CUSTOMER_ORDER_NO IS '先方発注No.'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.CATEGORY_NAME IS '区分'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ITEM_CD IS '品目コード'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.STYLE_OF_PACKING IS '荷姿'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.QUANTITY IS '数量'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.UNIT IS '単位'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.UNITPRICE IS '単価'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.AMOUNT IS '金額'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.TAX_AMOUNT IS '消費税'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.REMARK IS '備考'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.DELIVERY_ADDRESS IS '宛先'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.SLIP_NO IS '受注番号'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ROW_NO IS '受注行番号'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ITEM_NAME IS '品名'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ESTIMATE_MATSS IS '増付数'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ESTIMATE_UNIT IS '単位(増付)'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ESTIMATE_UNITPRICE IS '単価(増付)'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ESTIMATE_AMOUNT IS '金額(増付)'
/
COMMENT ON COLUMN REP_BILLISSUE_DETAIL.ESTIMATE_TAX_AMOUNT IS '消費税(増付)'
/
