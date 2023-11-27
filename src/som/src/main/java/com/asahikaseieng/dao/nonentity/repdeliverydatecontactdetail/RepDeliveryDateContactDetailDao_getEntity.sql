/*
 * 納期連絡表詳細用SQL 
 *
 * entityName=RepDeliveryDateContactHeader
 * packageName=repdeliverydatecontactheader
 * methodName=getEntity
 *
 */
WITH TMP AS (
    SELECT
          RDDCD.KEY
        , RDDCD.PK_NO
        , RDDCD.PK_ROW
        , RDDCD.SEQ
        , RDDCD.ITEM_CD
        , RDDCD.ITEM_NAME
        , RDDCD.STYLE_OF_PACKING
        , RDDCD.ALL_UP_WEIGHT
        , RDDCD.ORDER_QTY
        , RDDCD.UNIT_OF_OPERATION_MANAGEMENT
        , RDDCD.UOOM_NAME
        , RDDCD.ORDER_AMOUNT
        , RDDCD.ORDER_UNITPRICE
        , RDDCD.STANDARD_UNITPRICE
        , RDDCD.STANDARD_DISCOUNT
        , RDDCD.SPECIAL_DISCOUNT
        , RDDCD.MATSS
        , RDDCD.QTY
        , RDDCD.CTM_ITEM_CD
        , RDDCD.CTM_ITEM_NAME
        , RDDCD.CTM_STYLE_OF_PACKING
        , RDDCD.CTM_ORDER_QTY
        , RDDCD.CTM_CASE_NUM
        , RDDCD.CTM_ORDER_PIECE
        , RDDCD.CTM_ORDER_UNITPRICE
        , RDDCD.INPUT_DIVISION
        , RDDCD.UPDATE_DATE
        , RDDCD.CANCEL_FLG
    FROM REP_DEL_DATE_CONTACT_DETAIL RDDCD
    WHERE
        PK_NO IS NOT NULL
    AND PK_NO = /*pkNo*/'LK000000079'
) 
, LOG_NO_TMP AS (
	SELECT
	    MAX( CASE WHEN LOG_1 > LOG_2 THEN LOG_1 ELSE LOG_2 END  ) AS FRST_ORDER_LOG_NO
	FROM
	(
	    SELECT
	         MAX( CASE WHEN FRST_ORDER_HEAD_LOG.DELIVERY_EXPECTED_DATE <> FRST_ORDER_HEAD.DELIVERY_EXPECTED_DATE THEN FRST_ORDER_HEAD_LOG.FRST_ORDER_LOG_NO ELSE 0 END ) AS LOG_1
	        , MAX( CASE WHEN FRST_ORDER_DETAIL_LOG.ORDER_QTY <> FRST_ORDER_DETAIL.ORDER_QTY THEN FRST_ORDER_DETAIL_LOG.FRST_ORDER_LOG_NO ELSE 0 END ) AS LOG_2
	    FROM
	        FRST_ORDER_HEAD
	    INNER JOIN
	        FRST_ORDER_DETAIL
	    ON
	        FRST_ORDER_HEAD.FRST_ORDER_NO = FRST_ORDER_DETAIL.FRST_ORDER_NO
	    LEFT JOIN
	        FRST_ORDER_HEAD_LOG
	    ON
	        FRST_ORDER_HEAD_LOG.FRST_ORDER_NO = FRST_ORDER_HEAD.FRST_ORDER_NO
	        AND FRST_ORDER_HEAD_LOG.LOG_CLS = '20'
	    LEFT JOIN
	        FRST_ORDER_DETAIL_LOG
	    ON
	        FRST_ORDER_DETAIL_LOG.FRST_ORDER_NO = FRST_ORDER_DETAIL.FRST_ORDER_NO
	        AND FRST_ORDER_DETAIL_LOG.FRST_ORDER_ROW_NO = FRST_ORDER_DETAIL.FRST_ORDER_ROW_NO
	        AND FRST_ORDER_DETAIL_LOG.LOG_CLS = '20'
	    WHERE
	        FRST_ORDER_HEAD.FRST_ORDER_NO IN ( /*pkNo*/ )
	)
)
, LOG_TMP AS (
	-- 履歴情報から前回の告知数量を取得
	SELECT
		OI_LOG.FRST_ORDER_NO AS PK_NO
		, OI_LOG.FRST_ORDER_ROW_NO AS PK_ROW
		, RANK() OVER( PARTITION BY OI_LOG.FRST_ORDER_NO , OI_LOG.FRST_ORDER_ROW_NO ORDER BY OI_LOG.FRST_ORDER_LOG_NO DESC ) AS I_RANK
        ,  OI_LOG.ORDER_QTY + OI_LOG.MATSS AS QTY
	FROM
		FRST_ORDER_DETAIL_LOG OI_LOG
	WHERE
	    FRST_ORDER_LOG_NO IN ( SELECT FRST_ORDER_LOG_NO FROM LOG_NO_TMP )
)
SELECT
      TMP.KEY
    , TMP.PK_NO
    , TMP.PK_ROW
    , TMP.SEQ
    , TMP.ITEM_CD
    , CASE WHEN TMP.INPUT_DIVISION = 1 THEN '※' END || TMP.ITEM_NAME AS ITEM_NAME
    , TMP.STYLE_OF_PACKING
    , TMP.ALL_UP_WEIGHT
    , TMP.ORDER_QTY
    , TMP.UNIT_OF_OPERATION_MANAGEMENT
    , TMP.UOOM_NAME
    , TMP.ORDER_AMOUNT
    , TMP.ORDER_UNITPRICE
    , TMP.STANDARD_UNITPRICE
    , TMP.STANDARD_DISCOUNT
    , TMP.SPECIAL_DISCOUNT
    , TMP.MATSS
    , CASE WHEN TMP.CANCEL_FLG = 1 THEN 0 ELSE TMP.QTY END ||
          CASE 
          	WHEN TMP.CANCEL_FLG = 1 THEN CHR(13) || CHR(10) || '(前回:' ||  TMP.QTY || ')'
			WHEN LOG_TMP.QTY IS NOT NULL 
					AND LOG_TMP.QTY <> TMP.QTY THEN
                CHR(13) || CHR(10) || '(前回:' ||  LOG_TMP.QTY || ')'
            WHEN LOG_NO_TMP.FRST_ORDER_LOG_NO IS NOT NULL AND LOG_NO_TMP.FRST_ORDER_LOG_NO <> 0 AND LOG_TMP.PK_NO IS NULL 
                THEN CHR(13) || CHR(10) || '(前回:0)' 
          END AS  QTY
    , TMP.CTM_ITEM_CD
    , TMP.CTM_ITEM_NAME
    , TMP.CTM_STYLE_OF_PACKING
    , TMP.CTM_ORDER_QTY
    , TMP.CTM_CASE_NUM
    , TMP.CTM_ORDER_PIECE
    , TMP.CTM_ORDER_UNITPRICE
FROM
    TMP
LEFT JOIN
	LOG_TMP
ON
	LOG_TMP.PK_NO = TMP.PK_NO
	AND LOG_TMP.PK_ROW =  TMP.PK_ROW
	AND LOG_TMP.I_RANK = 1
LEFT JOIN
	LOG_NO_TMP
ON
	1 = 1
