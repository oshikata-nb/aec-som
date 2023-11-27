/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 包装実績検査 最終LINE_NO 取得SQL
 *
 * entityName=PkgRdirectionDirectionInspectionList
 * packageName=pkgrdirection
 * methodName=getLastLineNo
 *
 */

SELECT NVL(MAX(DIRECTION_INSPECTION.LINE_NO), 0) + 1 LAST_LINE_NO
FROM DIRECTION_INSPECTION
WHERE 
    DIRECTION_INSPECTION.DIRECTION_DIVISION = /*directionDivision*/
AND	DIRECTION_INSPECTION.DIRECTION_NO = /*directionNo*/
AND DIRECTION_INSPECTION.STEP_NO = /*stepNo*/
