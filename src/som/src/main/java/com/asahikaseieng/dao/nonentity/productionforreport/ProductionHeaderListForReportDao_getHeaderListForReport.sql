/**
 * @author t1344224
 *
 * entityName=ProductionHeaderListForReport
 * packageName=productionforreport
 * methodName=getHeaderListForReport
 *
 */
SELECT

	CASE ITEM_NAMES.SHIPPER_DIVISION
		WHEN 0 THEN '自社'
		WHEN 1 THEN '花王'
		WHEN 2 THEN 'OEM'
		WHEN 3 THEN '油脂'
	END AS SHIPPER_DIVISION
,	CASE ITEM_NAMES.TYPE_DIVISION
		WHEN 6 THEN '外注品'
		WHEN 7 THEN '外注品'
		ELSE '社内製造品'
	END AS TYPE_DIVISION
,	LINE.PRODUCTION_LINE_NAME
,	ASP_PRODUCTION.ITEM_CD
,	ITEM_NAMES.ITEM_NAME
,	ITEM_NAMES.STYLE_OF_PACKING
,	ASP_PRODUCTION.ORDER_LET
,	(NVL(ASP_PRODUCTION.ORDER_EXPECT_QTY,0) + NVL(ASP_PRODUCTION.ORDER_ACCEPT_QTY,0)) as ORDER_EXPECT_QTY
,	ASP_PRODUCTION.ORDER_ACCEPT_QTY
,	ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT
,	UNIT_NAMES.NAME01 as UNIT_NAME
FROM
	(SELECT	ITEM_CD
	,	MIN(ORDER_LET) AS ORDER_LET
	,	SUM(ORDER_EXPECT_QTY) as ORDER_EXPECT_QTY
	,	SUM(ORDER_ACCEPT_QTY) as ORDER_ACCEPT_QTY
	FROM ASP_PRODUCTION
	WHERE	ORDER_LET IS NOT NULL
	GROUP BY ITEM_CD,TO_CHAR(ORDER_LET,'yyyy/mm')
	)ASP_PRODUCTION

,	(SELECT
		ITEM2.ITEM_CD ,
		ITEM2.VERSION,
		ITEM2.ITEM_NAME,
		ITEM2.STYLE_OF_PACKING,
		ITEM2.OTHER_COMPANY_CD1,
		ITEM2.SHIPPER_DIVISION,
		ITEM2.TYPE_DIVISION,
		ITEM2.UNIT_OF_OPERATION_MANAGEMENT
	FROM
	    	(SELECT
	        	ITEM_CD,
	        	MAX(VERSION) AS VERSION
	    	FROM
	        	ITEM
	    	GROUP BY
	        	ITEM_CD
		) MAX_ITEM
	,	ITEM ITEM2
	WHERE
		ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD AND
		ITEM2.VERSION = MAX_ITEM.VERSION
	) ITEM_NAMES

,	(SELECT	NAMES.NAME_CD
	,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'
	)UNIT_NAMES

,	PRODUCT_ATTRIBUTE_QUEUE
,	LINE
WHERE
	ASP_PRODUCTION.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)

AND	ITEM_NAMES.ITEM_CD = PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND	ITEM_NAMES.VERSION = PRODUCT_ATTRIBUTE_QUEUE.VERSION(+)
AND	NVL(PRODUCT_ATTRIBUTE_QUEUE.PRODUCTION_LINE,'E') = LINE.PRODUCTION_LINE(+)

/*IF (( condition.srhShipperDivision != null ) && ( condition.srhShipperDivision != "" ))*/
	/*IF (condition.srhShipperDivision == "0")*/

	/*END*/
	/*IF (condition.srhShipperDivision != "0")*/
			AND	ITEM_NAMES.SHIPPER_DIVISION + 1 = /*condition.srhShipperDivision*/''
	/*END*/		
/*END*/

/*IF (( condition.srhTypeDivision != null ) && ( condition.srhTypeDivision != "" ))*/
	/*IF (condition.srhTypeDivision == "0")*/
			
	/*END*/
	/*IF (condition.srhTypeDivision == "1")*/
			AND	ITEM_NAMES.TYPE_DIVISION NOT IN ('6','7')
	/*END*/
	/*IF (condition.srhTypeDivision == "2")*/
			AND	ITEM_NAMES.TYPE_DIVISION IN ('6','7')
	/*END*/	
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine == "0")*/
	
	/*END*/
	/*IF (condition.srhProductionLine != "0")*/
			AND	LINE.PRODUCTION_LINE = /*condition.srhProductionLine*/''
	/*END*/		
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	ITEM_NAMES.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/

/*IF (( condition.srhOrderLet != null ) && ( condition.srhOrderLet != "" ))*/
	AND	TO_CHAR(ASP_PRODUCTION.ORDER_LET,'yyyy/mm') = /*condition.srhOrderLet*/''
/*END*/

ORDER BY
		ASP_PRODUCTION.ORDER_LET DESC
,		ASP_PRODUCTION.ITEM_CD ASC



