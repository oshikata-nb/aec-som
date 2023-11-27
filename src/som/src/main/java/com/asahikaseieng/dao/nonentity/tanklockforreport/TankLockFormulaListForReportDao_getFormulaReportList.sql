/**
 *
 * @author t1344224
 *
 * entityName=TankLockFormulaListForReport
 * packageName=tanklockforreport
 * methodName=getFormulaReportList
 *
 */
SELECT	

	FORMULA.DIRECTION_DIVISION
,	FORMULA.DIRECTION_NO
,	FORMULA.STEP_NO
,	FORMULA.LINE_NO
,	FORMULA.SEQ
,	FORMULA.LINE_TYPE
,	FORMULA.ITEM_CD
,	ITEM_FORMULA.ITEM_NAME
,	NAME_FORMULA.NAME01
,	FORMULA.TONYU
,	FORMULA.DATAREAD
,	FORMULA.TONYUSOKUDO
,	FORMULA.QTY
,	FORMULA.STOCKPD_QTY
,	FORMULA.RESULT_QTY
,	FORMULA.SAMPLE_QTY
,	FORMULA.LOSS_QTY
,	FORMULA.DEFECT_QTY
,	FORMULA.ADJUST_QTY
,	FORMULA.COST
,	FORMULA.STEP_CONDITION
,	FORMULA.NOTES
,	FORMULA.LOCATION_CD
,	FIRST_LOCATION.LOCATION_NAME AS FIRST_LOCATION_NAME
,	FORMULA.NEXT_LOCATION_CD
,	SECOND_LOCATION.LOCATION_NAME AS SECOND_LOCATION_NAME
,	FORMULA.NEXT_AFTER_LOCATION_CD
,	THIRD_LOCATION.LOCATION_NAME AS THIRD_LOCATION_NAME
,	FORMULA.LOT_NO
,	FORMULA.MANUFACTURER_LOT_NO
,	FORMULA.FILL_QTY
,	FORMULA.FILL_RESULT_QTY
,	FORMULA.REMARK
,	FORMULA.INPUT_DATE
,	FORMULA.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	FORMULA.UPDATE_DATE
,	FORMULA.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM	DIRECTION_HEADER
,	VALID_ITEM_QUEUE
,	RECIPE_RESOUCE RECIPE_RESOUCE
,	LOGIN INPUTOR
,	LOGIN UPDATOR

,	LOCATION FIRST_LOCATION
,	LOCATION SECOND_LOCATION
,	LOCATION THIRD_LOCATION
,	DIRECTION_FORMULA FORMULA
,	ITEM ITEM_FORMULA
,	NAMES NAME_FORMULA


WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'

AND		DIRECTION_HEADER.ITEM_CD = VALID_ITEM_QUEUE.ITEM_CD(+)
AND		DIRECTION_HEADER.COMPOUND_TANK_NO = RECIPE_RESOUCE.RESOUCE_CD
AND		RECIPE_RESOUCE.ORDER_PUBLISH_FLG != 2
AND	FORMULA.DIRECTION_NO = DIRECTION_HEADER.DIRECTION_NO

AND	FORMULA.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	FORMULA.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	FORMULA.ITEM_CD = ITEM_FORMULA.ITEM_CD(+)
AND	NAME_FORMULA.NAME_DIVISION(+) = 'UNIT'
AND	NAME_FORMULA.NAME_CD(+) = ITEM_FORMULA.UNIT_OF_OPERATION_MANAGEMENT
AND	FORMULA.LOCATION_CD = FIRST_LOCATION.LOCATION_CD(+)
AND	FORMULA.NEXT_LOCATION_CD = SECOND_LOCATION.LOCATION_CD(+)
AND	FORMULA.NEXT_AFTER_LOCATION_CD = THIRD_LOCATION.LOCATION_CD(+)


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
    ,FORMULA.STEP_NO
,FORMULA.LINE_NO



