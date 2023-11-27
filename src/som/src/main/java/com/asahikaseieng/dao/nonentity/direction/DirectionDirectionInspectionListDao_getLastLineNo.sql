/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 製造指図検査 最終LINE_NO 取得SQL
 *
 * entityName=DirectionDirectionInspectionList
 * packageName=direction
 * methodName=getLastLineNo
 *
 */

SELECT NVL(MAX(DIRECTION_INSPECTION.LINE_NO), 0) + 1 LAST_LINE_NO
FROM DIRECTION_INSPECTION
WHERE 
    DIRECTION_INSPECTION.DIRECTION_DIVISION = '1'
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
AND DIRECTION_INSPECTION.STEP_NO = /*stepNo*/
