/*
 *
 *
 * entityName=RepProductionPlanForReport
 * packageName=repproductionplanforreport
 * methodName=getProductionPlanListForReport
 *
 */
SELECT 
	*
FROM
	REP_PRODUCTION_PLAN
WHERE 
	REP_PRODUCTION_PLAN.ITEM_CD IS NOT NULL
/*IF (( condition.srhShipperDivision != null ) && ( condition.srhShipperDivision != "" ))*/
	/*IF (condition.srhShipperDivision != "0")*/
		AND	REP_PRODUCTION_PLAN.SHIPPER_DIVISION + 1 = /*condition.srhShipperDivision*/'0'
	/*END*/		
/*END*/

/*IF (( condition.srhTypeDivision != null ) && ( condition.srhTypeDivision != "" ))*/
	/*IF (condition.srhTypeDivision == "1")*/
		AND	REP_PRODUCTION_PLAN.TYPE_DIVISION NOT IN ('6','7')
	/*END*/
	/*IF (condition.srhTypeDivision == "2")*/
		AND	REP_PRODUCTION_PLAN.TYPE_DIVISION IN ('6','7')
	/*END*/	
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
		AND	REP_PRODUCTION_PLAN.PRODUCTION_LINE = /*condition.srhProductionLine*/''
	/*END*/		
/*END*/

/*IF (( condition.srhOrderLet != null ) && ( condition.srhOrderLet != "" ))*/
	AND	REP_PRODUCTION_PLAN.HIZUKE = /*condition.srhOrderLet*/''
/*END*/

ORDER BY 
	P_KEY
,	PRODUCTION_LINE
,	PACKAGE_LINE
,	ITEM_CD


