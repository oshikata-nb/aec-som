/**
 *
 * @author t1344224
 *
 * entityName=TankLockDetailListForReport
 * packageName=tanklockforreport
 * methodName=getDetailReportList
 *
 */
SELECT	

	DETAIL.PKG_DIRECTION_NO
,	DETAIL.ROW_NO
,	DETAIL.DIRECTION_NO
,	DETAIL.INPUT_DATE
,	DETAIL.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	DETAIL.UPDATE_DATE
,	DETAIL.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM	DIRECTION_HEADER
,	VALID_ITEM_QUEUE
,	RECIPE_RESOUCE RECIPE_RESOUCE
,	DIRECTION_DETAIL DETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR


WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'

AND		DIRECTION_HEADER.ITEM_CD = VALID_ITEM_QUEUE.ITEM_CD(+)
AND		DIRECTION_HEADER.COMPOUND_TANK_NO = RECIPE_RESOUCE.RESOUCE_CD
AND		RECIPE_RESOUCE.ORDER_PUBLISH_FLG != 2
AND	DETAIL.DIRECTION_NO = DIRECTION_HEADER.DIRECTION_NO

AND	DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)


/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/''
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	DIRECTION_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	VALID_ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
/*END*/

/*IF (( condition.srhChogotankno != null ) && ( condition.srhChogotankno != "" ))*/
	AND	DIRECTION_HEADER.COMPOUND_TANK_NO LIKE /*condition.srhChogotankno*/''
/*END*/

/*IF (( condition.srhDirectionStatus != null ) && ( condition.srhDirectionStatus != "" ))*/
	/*IF (condition.srhDirectionStatus == "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS IN ('3','4','5')
	/*END*/
	/*IF (condition.srhDirectionStatus != "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS = /*condition.srhDirectionStatus*/''
	/*END*/		
/*END*/

ORDER BY	DIRECTION_HEADER.COMPOUND_TANK_NO
,			DIRECTION_HEADER.RESULT_SDATE
,			DIRECTION_HEADER.DIRECTION_NO




