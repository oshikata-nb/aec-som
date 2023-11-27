/**
 * @author t1344224
 *
 * entityName=MidInspectCompInspectionListForReport
 * packageName=midinspectcompforreport
 * methodName=getInspectionReportList
 *
 */
SELECT	
	INSPECTION.DIRECTION_DIVISION
,	INSPECTION.DIRECTION_NO
,	INSPECTION.STEP_NO
,	INSPECTION.LINE_NO
,	INSPECTION.SEQ
,	INSPECTION.INSPECTION_CD
,	NAMES.NAME01 AS INSPECTION_NAME
,	INSPECTION.DIVISION
,	INSPECTION.VALUE_TYPE
,	INSPECTION.VALUE1
,	INSPECTION.RESULT_VALUE1
,	INSPECTION.VALUE2
,	INSPECTION.RESULT_VALUE2
,	INSPECTION.CONDITION
,	INSPECTION.NOTES
,	INSPECTION.REMARK
,	INSPECTION.INPUT_DATE
,	INSPECTION.INPUTOR_CD
,	INSPECTION.UPDATE_DATE
,	INSPECTION.UPDATOR_CD
,	INSPECTION.INPUT_DATE
,	INSPECTION.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	INSPECTION.UPDATE_DATE
,	INSPECTION.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME


FROM	DIRECTION_HEADER
,	VALID_ITEM_QUEUE ITEM_NAMES
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	NAMES
,	DIRECTION_INSPECTION INSPECTION

WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'
AND	INSPECTION.DIRECTION_NO = DIRECTION_HEADER.DIRECTION_NO
AND	DIRECTION_HEADER.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	INSPECTION.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	INSPECTION.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	INSPECTION.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	INSPECTION.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	INSPECTION.INSPECTION_CD = NAMES.NAME_CD(+)
AND	NAMES.NAME_DIVISION = 'STDV'

/*IF (( condition.srhDirectionNo != null ) && ( condition.srhDirectionNo != "" ))*/
	AND	DIRECTION_HEADER.DIRECTION_NO LIKE /*condition.srhDirectionNo*/''
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/''
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	DIRECTION_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
/*END*/

/*IF (( condition.srhPlanedSdateFrom != null ) && ( condition.srhPlanedSdateFrom != "" ))*/
	AND	DIRECTION_HEADER.PLANED_SDATE >= /*condition.srhPlanedSdateFrom*/''
/*END*/

/*IF (( condition.srhPlanedSdateTo != null ) && ( condition.srhPlanedSdateTo != "" ))*/
	AND	DIRECTION_HEADER.PLANED_SDATE <= /*condition.srhPlanedSdateTo*/''
/*END*/

/*IF (( condition.srhPlanedEdateFrom != null ) && ( condition.srhPlanedEdateFrom != "" ))*/
	AND	DIRECTION_HEADER.PLANED_EDATE >= /*condition.srhPlanedEdateFrom*/''
/*END*/

/*IF (( condition.srhPlanedEdateTo != null ) && ( condition.srhPlanedEdateTo != "" ))*/
	AND	DIRECTION_HEADER.PLANED_EDATE <= /*condition.srhPlanedEdateTo*/''
/*END*/

/*IF (( condition.srhDirectionStatus != null ) && ( condition.srhDirectionStatus != "" ))*/
	/*IF (condition.srhDirectionStatus == "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS IN ('4','5')
	/*END*/
	/*IF (condition.srhDirectionStatus != "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS = /*condition.srhDirectionStatus*/''
	/*END*/		
/*END*/

ORDER BY	DIRECTION_HEADER.RESULT_SDATE
,			DIRECTION_HEADER.DIRECTION_NO
,			DIRECTION_HEADER.ITEM_CD
    ,INSPECTION.STEP_NO
,INSPECTION.LINE_NO



