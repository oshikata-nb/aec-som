/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
*/

/**
 * 製造指図-検査タブ一覧用SQL
 *
 * @author tosco
 *
 * entityName=DirectionInspectionList
 * packageName=direction
 * methodName=getList
 *
 */
SELECT
	INSPECTION.DIRECTION_DIVISION
,	INSPECTION.DIRECTION_NO
,	INSPECTION.STEP_NO
,	INSPECTION.LINE_NO
,	INSPECTION.SEQ
,	INSPECTION.INSPECTION_CD
,	INSPECTION.DIVISION
,	INSPECTION.VALUE_TYPE
,	INSPECTION.VALUE1
,	INSPECTION.VALUE2
,	INSPECTION.RESULT_VALUE1
,	INSPECTION.RESULT_VALUE2
,	INSPECTION.CONDITION
,	INSPECTION.NOTES
,	INSPECTION.REMARK
,	INSPECTION.INPUTOR_CD
,	INSPECTION.INPUT_DATE
,	INSPECTION.UPDATOR_CD
,	INSPECTION.UPDATE_DATE
,	NAMES.NAME01 AS INSPECTION_NAME
	
FROM
    DIRECTION_INSPECTION INSPECTION
,	NAMES
    
WHERE
	INSPECTION.DIRECTION_DIVISION = '1'
AND INSPECTION.DIRECTION_NO = /*directionNo*/
AND INSPECTION.STEP_NO = /*stepNo*/
AND NAMES.NAME_DIVISION(+) = 'STDV'
AND INSPECTION.INSPECTION_CD = NAMES.NAME_CD(+)

ORDER BY
	INSPECTION.SEQ
