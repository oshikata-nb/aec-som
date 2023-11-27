/**
 *
 * @author t1344224
 *
 * entityName=PkgDirectionOrderProcedureListForReport
 * packageName=pkgdirectionforreport
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
    ITEM
,	ARTICLE_ATTRIBUTE_QUEUE ATRQUE
,	DIRECTION_HEADER HEAD
,	DIRECTION_PROCEDURE PROCEDURE
,	OPERATION

,	LOGIN INPUTOR
,	LOGIN UPDATOR


WHERE (HEAD.DIRECTION_DIVISION = 2 OR HEAD.DIRECTION_DIVISION = 3)
AND (HEAD.DIRECTION_STATUS = 1 OR DIRECTION_STATUS = 2)
AND HEAD.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
AND ITEM.VERSION = ATRQUE.VERSION(+)

AND	HEAD.DIRECTION_NO = PROCEDURE.DIRECTION_NO
AND	PROCEDURE.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	PROCEDURE.UPDATOR_CD = UPDATOR.TANTO_CD(+)

AND	PROCEDURE.OPERATION_CD = OPERATION.OPERATION_CD(+)

/*IF ( condition.directionDivision != null )*/
	AND	HEAD.DIRECTION_DIVISION = /*condition.directionDivision*/''
/*END*/

/*IF (( condition.directionNo != null ) && ( condition.directionNo != "" ))*/
	AND	HEAD.DIRECTION_NO = /*condition.directionNo*/''
/*END*/

/*IF (( condition.directionStatus != null ) && ( condition.directionStatus != 0 ))*/
	AND	HEAD.DIRECTION_STATUS = /*condition.directionStatus*/''
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	HEAD.PRODUCTION_LINE = /*condition.productionLine*/''
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	HEAD.ITEM_CD LIKE /*condition.itemCd*/''
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/''
/*END*/

/*IF ( condition.planedSdateFrom != null )*/
	AND	HEAD.PLANED_SDATE >= /*condition.planedSdateFrom*/''
/*END*/

/*IF ( condition.planedSdateTo != null )*/
	AND	HEAD.PLANED_SDATE <= /*condition.planedSdateTo*/''
/*END*/

/*IF ( condition.planedEdateFrom != null )*/
	AND	HEAD.PLANED_EDATE >= /*condition.planedEdateFrom*/''
/*END*/

/*IF ( condition.planedEdateTo != null )*/
	AND	HEAD.PLANED_EDATE <= /*condition.planedEdateTo*/''
/*END*/

/*IF (( condition.aspOrderNo != null ) && ( condition.aspOrderNo != "" ))*/
	AND	HEAD.ASP_ORDER_NO LIKE /*condition.aspOrderNo*/'%'
/*END*/

/*IF (( condition.packageLine != null ) && ( condition.packageLine != "" ))*/
	AND	HEAD.PACKAGE_LINE LIKE /*condition.packageLine*/'%'
/*END*/

ORDER BY
	HEAD.PACKAGE_LINE
,	HEAD.PLANED_SDATE
,	HEAD.DIRECTION_NO
,	PROCEDURE.STEP_NO

