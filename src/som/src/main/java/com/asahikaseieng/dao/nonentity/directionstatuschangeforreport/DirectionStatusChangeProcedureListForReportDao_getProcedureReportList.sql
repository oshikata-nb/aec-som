/**
 * @author t1344224
 *
 * entityName=DirectionStatusChangeProcedureListForReport
 * packageName=directionstatuschangeforreport
 * methodName=getProcedureReportList
 *
 */
SELECT	
	PROCEDURE.DIRECTION_DIVISION
,	PROCEDURE.DIRECTION_NO
,	PROCEDURE.STEP_NO
,	PROCEDURE.SEQ
,	PROCEDURE.OPERATION_CD
,	OPERATION.OPERATION_NAME
,	PROCEDURE.CONDITION
,	PROCEDURE.REMARK
,	PROCEDURE.NOTES
,	PROCEDURE.LEADTIME
,	PROCEDURE.START_DATE
,	PROCEDURE.END_DATE
,	PROCEDURE.RESULT_SDATE
,	PROCEDURE.RESULT_EDATE
,	PROCEDURE.CONDITION_TEMP
,	PROCEDURE.CONDITION_TIME
,	PROCEDURE.STIR_SPEED1
,	PROCEDURE.STIR_SPEED2
,	PROCEDURE.WATER_WEIGHT
,	PROCEDURE.MAIN_STREAM
,	PROCEDURE.PH
,	PROCEDURE.RESULT_CONDITION_TEMP
,	PROCEDURE.RESULT_STIR_SPEED
,	PROCEDURE.RESULT_PH
,	PROCEDURE.FILLING_QTY
,	PROCEDURE.FILLING_UNIT
,	PROCEDURE.NET
,	PROCEDURE.WEIGHT_MIN
,	PROCEDURE.WEIGHT_MAX
,	PROCEDURE.FILTER
,	PROCEDURE.AUTO_CHECKER_MIN
,	PROCEDURE.AUTO_CHECKER_MAX
,	PROCEDURE.GROSS_CHECKER_MIN
,	PROCEDURE.GROSS_CHECKER_MAX
,	PROCEDURE.OPENING_TORQUE_MIN
,	PROCEDURE.OPENING_TORQUE_MAX
,	PROCEDURE.HOT_AIR_PRESET_TEMP
,	PROCEDURE.HOT_AIR_PRESSURE
,	PROCEDURE.MESH
,	PROCEDURE.AUTO_CHECKER_AVE
,	PROCEDURE.GROSS_CHECKER_AVE
,	PROCEDURE.CLOSING_TORQUE_MIN
,	PROCEDURE.CLOSING_TORQUE_MAX
,	PROCEDURE.TORQUE_PRESSURE
,	PROCEDURE.AIR_PRESSURE
,	PROCEDURE.VCLOSE_TIME
,	PROCEDURE.FIRST_HEAT_SEAL
,	PROCEDURE.SECOND_HEAT_SEAL
,	PROCEDURE.INPUT_DATE
,	PROCEDURE.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	PROCEDURE.UPDATE_DATE
,	PROCEDURE.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM
	DIRECTION_HEADER
,	(SELECT
	    ITEM2.ITEM_CD ,
	    ITEM2.ITEM_NAME,
	    OTHER_COMPANY_CD1
	FROM
		VALID_ITEM_QUEUE ITEM2
	) ITEM_NAMES
,	LINE
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	OPERATION
,	DIRECTION_PROCEDURE PROCEDURE

WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'
AND	DIRECTION_HEADER.DIRECTION_NO = PROCEDURE.DIRECTION_NO
AND	DIRECTION_HEADER.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	DIRECTION_HEADER.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND	PROCEDURE.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	PROCEDURE.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	PROCEDURE.OPERATION_CD = OPERATION.OPERATION_CD(+)


/*IF (( condition.srhDirectionNo != null ) && ( condition.srhDirectionNo != "" ))*/
	AND	DIRECTION_HEADER.DIRECTION_NO LIKE /*condition.srhDirectionNo*/'%'
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
		AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	DIRECTION_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (( condition.srhDirectionStatus != null ) && ( condition.srhDirectionStatus != "" ))*/
	/*IF (condition.srhDirectionStatus == "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS IN ('2','3','5')
	/*END*/
	/*IF (condition.srhDirectionStatus != "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS = /*condition.srhDirectionStatus*/1
	/*END*/		
/*END*/

ORDER BY
	DIRECTION_HEADER.DIRECTION_NO
,	DIRECTION_HEADER.ITEM_CD
,	PROCEDURE.STEP_NO