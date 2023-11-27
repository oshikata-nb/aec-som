/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
*/

/**
 * 製造実績-検査詳細用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionDirectionInspectionList
 * packageName=rdirection
 * methodName=getEntity
 *
 */
SELECT
	DIRECTION_INSPECTION.DIRECTION_DIVISION
,	DIRECTION_INSPECTION.DIRECTION_NO
,	DIRECTION_INSPECTION.STEP_NO
,	DIRECTION_INSPECTION.LINE_NO
,	DIRECTION_INSPECTION.SEQ
,	DIRECTION_INSPECTION.INSPECTION_CD
,	DIRECTION_INSPECTION.DIVISION
,	DIRECTION_INSPECTION.VALUE_TYPE
,	DIRECTION_INSPECTION.VALUE1
,	DIRECTION_INSPECTION.VALUE2
,	DIRECTION_INSPECTION.RESULT_VALUE1
,	DIRECTION_INSPECTION.RESULT_VALUE2
,	DIRECTION_INSPECTION.CONDITION
,	DIRECTION_INSPECTION.REMARK
,	DIRECTION_INSPECTION.NOTES
,	DIRECTION_INSPECTION.INPUTOR_CD
,	DIRECTION_INSPECTION.INPUT_DATE
,	DIRECTION_INSPECTION.UPDATOR_CD
,	DIRECTION_INSPECTION.UPDATE_DATE
,	NAMES.NAME01 AS INSPECTION_NAME
FROM
	DIRECTION_INSPECTION
,	NAMES
WHERE
	DIRECTION_INSPECTION.DIRECTION_NO IS NOT NULL
AND DIRECTION_INSPECTION.DIRECTION_DIVISION = '1'	
/*IF (( directionNo != null ) && ( directionNo != "" ))*/
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
/*END*/

/*IF (( stepNo != null ) && ( stepNo != "" ))*/
AND	DIRECTION_INSPECTION.STEP_NO = /*stepNo*/
/*END*/

/*IF (( lineNo != null ) && ( lineNo != "" ))*/
AND	DIRECTION_INSPECTION.LINE_NO = /*lineNo*/
/*END*/
AND NAMES.NAME_DIVISION(+) = 'STDV'
AND DIRECTION_INSPECTION.INSPECTION_CD = NAMES.NAME_CD(+)

ORDER BY
	 DIRECTION_INSPECTION.DIRECTION_NO
	,DIRECTION_INSPECTION.STEP_NO
	,DIRECTION_INSPECTION.LINE_NO