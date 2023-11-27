/*
 * entityName=SalesGetInoutData
 * packageName=salesinout
 * methodName=getEntity
 *
 */
SELECT
	INOUT_RECORD.INOUT_NO
,	INOUT_RECORD.INOUT_DIVISION
,	INOUT_RECORD.ODER_NO
,	INOUT_RECORD.ODER_LINE_NO
,	INOUT_RECORD.ITEM_CD
,	INOUT_RECORD.LOCATION_CD
,	INOUT_RECORD.LOT_NO
,	(CASE WHEN ITEM.TYPE_DIVISION = 1
		THEN
			(CASE WHEN ITEM.KG_OF_FRACTION_MANAGEMENT IS NULL OR ITEM.KG_OF_FRACTION_MANAGEMENT = 0
				THEN
					INOUT_RECORD.INOUT_QTY / 1
				ELSE 
					INOUT_RECORD.INOUT_QTY / ITEM.KG_OF_FRACTION_MANAGEMENT
			END)
		ELSE
			INOUT_RECORD.INOUT_QTY
	END) AS INOUT_QTY
,	(CASE WHEN
		ITEM.TYPE_DIVISION = 1
		THEN 
			INOUT_RECORD.INOUT_QTY
		ELSE
			(CASE WHEN ITEM.KG_OF_FRACTION_MANAGEMENT IS NULL OR ITEM.KG_OF_FRACTION_MANAGEMENT = 0
				THEN
					INOUT_RECORD.INOUT_QTY * 1
				ELSE 
					INOUT_RECORD.INOUT_QTY * ITEM.KG_OF_FRACTION_MANAGEMENT
			END)
	END) AS INOUT_WEIGHT
,	INOUT_RECORD.INOUT_PRICE
,	INOUT_RECORD.INOUT_COST
,	TO_CHAR(INOUT_RECORD.INOUT_DATE,'YYYY/MM/DD') AS INOUT_DATE
,	INOUT_RECORD.REMARK
,	INOUT_RECORD.INOUT_SOURCE_NO
,	INOUT_RECORD.SECTION_CD
,	INOUT_RECORD.ACCOUNTS_CD
,	INOUT_RECORD.ACCOUNTS_SUB_CD
,	INOUT_RECORD.ITEM_CATEGORY
,	INOUT_RECORD.PARENT_ITEM_CD
,	INOUT_RECORD.PARENT_ACCOUNTS_CD
,	INOUT_RECORD.PARENT_ACCOUNT_SUB_CD
,	INOUT_RECORD.PARENT_ITEM_CATEGORY
,	INOUT_RECORD.RY_CD
,	REASON.RY_DESCRIPTION AS RY_NAME
,	INOUT_RECORD.REASON
,	INOUT_RECORD.INOUT_UPDATE_DATE
,	INOUT_RECORD.INVENTORY_UPDATE_DATE
,	INOUT_RECORD.FUNC_DIVISION
,	INOUT_RECORD.INPUT_DATE
,	INOUT_RECORD.INPUTOR_CD
,	LOGIN.TANTO_NM AS INPUTOR_NAME
,	INOUT_RECORD.UPDATE_DATE
,	INOUT_RECORD.UPDATOR_CD
FROM	
	INOUT_RECORD
,	REASON
,	LOGIN
,	ITEM
WHERE
	INOUT_RECORD.ODER_NO = /*slipNo*/'SI000009141'
AND	INOUT_RECORD.RY_CD = REASON.RY_CD(+)
AND	INOUT_RECORD.INPUTOR_CD = LOGIN.TANTO_CD(+)
AND	INOUT_RECORD.ITEM_CD = ITEM.ITEM_CD
