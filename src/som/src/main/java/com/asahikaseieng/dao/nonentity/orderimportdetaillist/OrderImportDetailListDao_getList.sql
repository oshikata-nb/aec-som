/*
 * 受注取込詳細用SQL 
 *
 * entityName=OrderImportDetailList
 * packageName=orderImportDetailList
 * methodName=getList
 *
 */
WITH TMP( BASE_BALANCE_CD , BALANCE_CD , UPPER_BALANCE_CD, VENDER_CD ,  SHOP_LEVEL , LOOP_COUNT ) AS (
SELECT
      BALANCE.BALANCE_CD AS BASE_BALANCE_CD
    , BALANCE.BALANCE_CD
    , BALANCE.UPPER_BALANCE_CD 
    , BALANCE.VENDER_CD
    , BALANCE.SHOP_LEVEL AS SHOP_LEVEL
    , 1 AS LOOP_COUNT
FROM
    BALANCE
UNION ALL
SELECT
      TMP.BASE_BALANCE_CD
    , BALANCE.BALANCE_CD
    , BALANCE.UPPER_BALANCE_CD
    , BALANCE.VENDER_CD
    , BALANCE.SHOP_LEVEL
    , TMP.LOOP_COUNT + 1 AS LOOP_COUNT
FROM
    BALANCE
INNER JOIN
    TMP
ON
    TMP.UPPER_BALANCE_CD = BALANCE.BALANCE_CD
    AND TMP.SHOP_LEVEL  = BALANCE.SHOP_LEVEL + 1
WHERE
    TMP.LOOP_COUNT < 99
   ) ,
ESTIMATE_TMP AS (
    SELECT
          TMP.BASE_BALANCE_CD AS BALANCE_CD
        , ESTIMATE.ITEM_CD
        , ESTIMATE.UNITPRICE
        , ESTIMATE.STANDARD_DISCOUNT
        , ESTIMATE.STANDARD_AMOUNT
        , ESTIMATE.MATSS
        , ESTIMATE.SPECIAL_DISCOUNT
        ,RANK() OVER( PARTITION BY BASE_BALANCE_CD 
            		  , ESTIMATE.ITEM_CD
                   ORDER BY LOOP_COUNT ASC) AS I_RANK
    FROM
        TMP
    INNER JOIN
        ESTIMATE
    ON
            ESTIMATE.BALANCE_CD = TMP.BALANCE_CD
    INNER JOIN
    	FRST_ORDER_HEAD
    ON
    	FRST_ORDER_NO = /*frstOrderNo*/'FO000000374'
        AND ESTIMATE.ESTIMATE_VALID_DATE_FROM <= FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE
	    AND ESTIMATE.ESTIMATE_VALID_DATE_TO >= FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE
	WHERE
		TMP.BASE_BALANCE_CD = FRST_ORDER_HEAD.BALANCE_CD
)
SELECT
    ESTIMATE.UNITPRICE 
,   FRST_ORDER_HEAD.FRST_ORDER_NO
,   FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
,   FRST_ORDER_HEAD.BALANCE_CD
,   FRST_ORDER_HEAD.ORDER_NO
,   FRST_ORDER_DETAIL.CUSTOMER_ORDER_DETAIL_NO AS CTM_ORDER_NO
,   FRST_ORDER_DETAIL.ROW_NO
,   FRST_ORDER_DETAIL.VIEW_SEQ
,   FRST_ORDER_DETAIL.ITEM_CD
,   ITEM.OTHER_COMPANY_CD1
,   ITEM.ITEM_NAME
,   ITEM.STYLE_OF_PACKING
,   ITEM.ALL_UP_WEIGHT
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT
,   ITEM.KG_OF_FRACTION_MANAGEMENT
,   FRST_ORDER_DETAIL.ORDER_QTY
,   FRST_ORDER_DETAIL.MATSS
,   FRST_ORDER_DETAIL.ORDER_UNITPRICE
,   NVL(FRST_ORDER_DETAIL.ORDER_QTY * FRST_ORDER_DETAIL.ORDER_UNITPRICE, 0) ORDER_AMOUNT
,   NVL(FRST_ORDER_DETAIL.ORDER_QTY + FRST_ORDER_DETAIL.MATSS, 0) QTY
,   NVL(NVL(FRST_ORDER_DETAIL.ORDER_QTY + FRST_ORDER_DETAIL.MATSS, 0) * ITEM.ALL_UP_WEIGHT, 0) WEIGHT
,   FRST_ORDER_DETAIL.STANDARD_UNITPRICE
,   FRST_ORDER_DETAIL.STANDARD_DISCOUNT
,   FRST_ORDER_DETAIL.SPECIAL_DISCOUNT
,   FRST_ORDER_DETAIL.TMP_UNITPRICE_FLG
,   ORDER_IMP_BASE.CTM_ITEM_CD_01
,   ORDER_IMP_BASE.CTM_ITEM_CD_02
,   ORDER_IMP_BASE.CTM_ITEM_CD_03
,   ORDER_IMP_BASE.CTM_JAN_CD
,   ORDER_IMP_BASE.CTM_ITEM_NAME_01
,   ORDER_IMP_BASE.CTM_ITEM_NAME_02
,   ORDER_IMP_BASE.CTM_ITEM_NAME_03
,   ORDER_IMP_BASE.CTM_STYLE_OF_PACKING
,   ORDER_IMP_BASE.CTM_ORDER_QTY
,   CASE 
	    WHEN FRST_ORDER_DETAIL.DEL_FLG = 1      THEN 90
	    WHEN FRST_ORDER_DETAIL.CANCEL_FLG = 1   THEN 90
        WHEN FRST_ORDER_HEAD.ORDER_NO IS NULL   THEN 10
        WHEN FRST_ORDER_HEAD.ORDER_DIVISION = 3 THEN PUS.STATUS
        WHEN SHP.SHIPPING_STATUS IS NOT NULL    THEN SHP.SHIPPING_STATUS
        ELSE 99
    END AS ORDER_STATUS
,   CASE
		    WHEN FRST_ORDER_DETAIL.DEL_FLG = 1      THEN TO_NCHAR('削除')
		    WHEN FRST_ORDER_DETAIL.CANCEL_FLG = 1   THEN TO_NCHAR('キャンセル')
            WHEN FRST_ORDER_HEAD.ORDER_NO IS NULL   THEN TO_NCHAR('先付受注')
            WHEN FRST_ORDER_HEAD.ORDER_DIVISION = 3 THEN PUS_NM.NAME01
            WHEN SHP_NM.NAME01 IS NOT NULL          THEN SHP_NM.NAME01
            ELSE TO_NCHAR('受注登録')
    END AS ORDER_STATUS_NAME
,   ORDER_IMP_BASE.CTM_ORDER_AMOUNT
,   ORDER_IMP_BASE.CTM_ORDER_UNITPRICE
,   ORDER_IMP_BASE.CTM_CASE_NUM
--,   FRST_ORDER_DETAIL.IMPORT_STATUS
,   ORDER_IMP_BASE.CTM_REMARK_01
,   ORDER_IMP_BASE.CTM_REMARK_02
,   ORDER_IMP_BASE.CTM_REMARK_03
,   FRST_ORDER_DETAIL.ESTIMATE_MATSS
,   FRST_ORDER_DETAIL.ESTIMATE_STANDARD_AMOUNT
,   SHP.SHIPPING_STATUS AS SHIPPING_STATUS
,   PUS.STATUS AS PURCHASE_STATUS
,   ASP_ORD.WORK_STATUS AS ASP_STATUS
,   FRST_ORDER_DETAIL.UPDATE_DATE
--,   FRST_ORDER_DETAIL.PRINT_SUMMERY
--,   FRST_ORDER_DETAIL.DELIVERY_SLIP_SUMMERY
--,   FRST_ORDER_DETAIL.ORDER_SUMMERY
   -- エラー
,     CASE WHEN FRST_ORDER_DETAIL.ORDER_QTY <= 0 AND ( ORDER_IMP_BASE.CTM_ORDER_QTY IS NULL OR ORDER_IMP_BASE.CTM_ORDER_QTY <= 0 ) THEN  '数量は0より大きい数を指定してください。' END
   || CASE WHEN FRST_ORDER_DETAIL.ITEM_CD IS NULL AND ORDER_VENDER_ITEM.SOM_ITEM_CD IS NULL THEN '変換マスタに客先品目コードを登録してください。'
           WHEN FRST_ORDER_DETAIL.ITEM_CD IS NULL THEN '変換マスタに登録されている社内品目を品目マスタに登録してください。' END
   || CASE WHEN ART.KEEP_DIVISION = 2 THEN '品目コードは預かり品以外を指定してください。' END
   || CASE WHEN SALES_TERMS.ITEM_CD IS NULL THEN '販売条件マスタを設定してください。'  END
   || CASE WHEN ESTIMATE.UNITPRICE IS NULL THEN '見積もり単価が存在しません。' END
   AS ERROR_MESSAGE
,  CASE WHEN FRST_ORDER_DETAIL.INPUT_APPROVAL_DATE IS NULL THEN 0 ELSE 1 END AS INPUT_APPROVAL_FLG
,  CASE WHEN  FRST_ORDER_DETAIL.DEL_DATE_SEND_DATE  IS NULL THEN 0 ELSE 1 END AS DEL_DATE_SEND_FLG
, LOG_TMP.ORDER_QTY AS CHECKED_ORDER_QTY
, LOG_TMP.MATSS AS CHECKED_MATSS
, FRST_ORDER_DETAIL.DEL_FLG
, FRST_ORDER_DETAIL.CANCEL_FLG
, FRST_ORDER_HEAD.DEL_FLG AS HEAD_DEL_FLG
, FRST_ORDER_HEAD.CANCEL_FLG AS HEAD_CANCEL_FLG
, ORD.UPDATE_DATE AS ORDER_DETAIL_DATE
FROM 
    FRST_ORDER_HEAD
INNER JOIN
    FRST_ORDER_DETAIL
ON
    FRST_ORDER_DETAIL.FRST_ORDER_NO = FRST_ORDER_HEAD.FRST_ORDER_NO
LEFT JOIN
    ORDER_IMP_BASE
ON
    ORDER_IMP_BASE.ORDER_IMP_NO = FRST_ORDER_DETAIL.ORDER_IMP_NO
LEFT JOIN 
    ITEM 
ON 
    FRST_ORDER_DETAIL.ITEM_CD = ITEM.ITEM_CD
LEFT JOIN 
    ITEM_INVENTORY ITEM_INV 
ON 
    FRST_ORDER_DETAIL.ITEM_CD = ITEM_INV.ITEM_CD
LEFT JOIN 
    ORDER_DETAIL ORD 
ON 
    FRST_ORDER_DETAIL.ORDER_NO = ORD.ORDER_NO 
    AND FRST_ORDER_DETAIL.ROW_NO = ORD.ROW_NO
LEFT JOIN 
    SHIPPING SHP 
ON 
    ORD.SHIPPING_NO = SHP.SHIPPING_NO
LEFT JOIN 
    (SELECT ORDER_NO,ORDER_ROW_NO,STATUS
    FROM PURCHASE_SUBCONTRACT 
    WHERE ORDER_NO IS NOT NULL 
    GROUP BY PURCHASE_SUBCONTRACT.ORDER_NO
    ,   PURCHASE_SUBCONTRACT.ORDER_ROW_NO
    ,   PURCHASE_SUBCONTRACT.STATUS) PUS 
ON 
    ORD.ORDER_NO = PUS.ORDER_NO 
    AND ORD.ROW_NO = PUS.ORDER_ROW_NO
LEFT JOIN 
    NAMES ITEM_NM 
ON 
    ITEM.STATUS = ITEM_NM.NAME_CD 
    AND ITEM_NM.NAME_DIVISION = 'ITST'
LEFT JOIN 
    NAMES SHP_NM 
ON 
    SHP.SHIPPING_STATUS = SHP_NM.NAME_CD 
    AND SHP_NM.NAME_DIVISION = 'ORST'
LEFT JOIN 
    NAMES PUS_NM 
ON 
    PUS.STATUS = PUS_NM.NAME_CD 
    AND PUS_NM.NAME_DIVISION = 'PUST'
LEFT JOIN 
    ASP_PRODUCTION ASP_PRO 
ON 
    FRST_ORDER_DETAIL.ORDER_NO = ASP_PRO.ORDER_NO 
    AND FRST_ORDER_DETAIL.ROW_NO = ASP_PRO.ORDER_ROW_NO
LEFT JOIN 
    ASP_ORDER ASP_ORD 
ON 
    ASP_PRO.ORDER_CD = ASP_ORD.ORDER_CD
LEFT JOIN
    ORDER_VENDER_ITEM
ON
    ORDER_VENDER_ITEM.VENDER_GROUP_CD = ORDER_IMP_BASE.VENDER_GROUP_CD
    AND ( ( ORDER_VENDER_ITEM.CTM_ITEM_CD_01 IS NULL AND ORDER_IMP_BASE.CTM_ITEM_CD_01  IS NULL ) OR ( ORDER_VENDER_ITEM.CTM_ITEM_CD_01 = ORDER_IMP_BASE.CTM_ITEM_CD_01 ) )
    AND ( ( ORDER_VENDER_ITEM.CTM_ITEM_CD_02 IS NULL AND ORDER_IMP_BASE.CTM_ITEM_CD_02  IS NULL ) OR ( ORDER_VENDER_ITEM.CTM_ITEM_CD_02 = ORDER_IMP_BASE.CTM_ITEM_CD_02 ) )
    AND ( ( ORDER_VENDER_ITEM.CTM_ITEM_CD_03 IS NULL AND ORDER_IMP_BASE.CTM_ITEM_CD_03  IS NULL ) OR ( ORDER_VENDER_ITEM.CTM_ITEM_CD_03 = ORDER_IMP_BASE.CTM_ITEM_CD_03 ) )
LEFT JOIN
    ESTIMATE_TMP ESTIMATE
ON
        ESTIMATE.BALANCE_CD = FRST_ORDER_HEAD.BALANCE_CD
    AND ESTIMATE.ITEM_CD = ITEM.ITEM_CD
    AND ESTIMATE.I_RANK = 1
LEFT JOIN
    SALES_TERMS
ON
    SALES_TERMS.DELIVERY_CD = FRST_ORDER_HEAD.DELIVERY_CD
    AND SALES_TERMS.ITEM_CD = ITEM.ITEM_CD
LEFT JOIN 
    ARTICLE_ATTRIBUTE_QUEUE ART 
ON 
    ITEM.ITEM_CD = ART.ITEM_CD 
    AND ITEM.VERSION = ART.VERSION
LEFT JOIN
	(
		SELECT 
		    RANK() OVER( PARTITION BY FRST_ORDER_NO , FRST_ORDER_ROW_NO ORDER BY FRST_ORDER_LOG_NO DESC ) AS I_RANK
		    , FRST_ORDER_DETAIL_LOG.FRST_ORDER_NO 
		    , FRST_ORDER_DETAIL_LOG.FRST_ORDER_ROW_NO
		    , FRST_ORDER_DETAIL_LOG.ORDER_QTY 
		    , FRST_ORDER_DETAIL_LOG.MATSS
		FROM 
		    FRST_ORDER_DETAIL_LOG
		WHERE 
		    LOG_CLS = '10'
		    AND FRST_ORDER_NO = /*frstOrderNo*/'FO000000374'
	) LOG_TMP
ON
	LOG_TMP.I_RANK = 1
	AND LOG_TMP.FRST_ORDER_NO = FRST_ORDER_DETAIL.FRST_ORDER_NO
	AND LOG_TMP.FRST_ORDER_ROW_NO = FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
WHERE
        FRST_ORDER_DETAIL.FRST_ORDER_NO IS NOT NULL
    AND FRST_ORDER_DETAIL.FRST_ORDER_NO = /*frstOrderNo*/'FO000000374'
ORDER BY FRST_ORDER_DETAIL.FRST_ORDER_NO , FRST_ORDER_DETAIL.VIEW_SEQ , FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
