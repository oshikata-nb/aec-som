/**
 * @author t1344224
 *
 * entityName=ProductionDetailListForReport
 * packageName=productionforreport
 * methodName=getDetailListForReport
 *
 */
SELECT	
	ASP_PRODUCTION.ORDER_CD
,	ASP_PRODUCTION.ITEM_CD
,	ITEM_NAMES.ITEM_NAME
,	TO_CHAR(ASP_PRODUCTION.ORDER_LET,'YYYY/MM') AS ORDER_LET_YYYYMM
,	ASP_PRODUCTION.ORDER_LET
,	ASP_PRODUCTION.ORDER_EXPECT_QTY
,	ASP_PRODUCTION.ORDER_ACCEPT_QTY
,	ASP_PRODUCTION.ORDER_COMMENT
,	ASP_PRODUCTION.ORDER_NO
,	ASP_PRODUCTION.ORDER_ROW_NO
,	ASP_PRODUCTION.INPUT_DATE
,	ASP_PRODUCTION.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	ASP_PRODUCTION.UPDATE_DATE
,	ASP_PRODUCTION.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM
	ASP_PRODUCTION

,	(SELECT
		ITEM2.ITEM_CD ,
		ITEM2.VERSION,
		ITEM2.ITEM_NAME,
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
,	LOGIN INPUTOR
,	LOGIN UPDATOR

WHERE
	ASP_PRODUCTION.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)

AND	ITEM_NAMES.ITEM_CD = PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND	ITEM_NAMES.VERSION = PRODUCT_ATTRIBUTE_QUEUE.VERSION(+)
AND	NVL(PRODUCT_ATTRIBUTE_QUEUE.PRODUCTION_LINE,'E') = LINE.PRODUCTION_LINE(+)

AND	ASP_PRODUCTION.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	ASP_PRODUCTION.UPDATOR_CD = UPDATOR.TANTO_CD(+)

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
		ORDER_LET_YYYYMM DESC
,		ASP_PRODUCTION.ITEM_CD ASC
,		ASP_PRODUCTION.ORDER_LET ASC



