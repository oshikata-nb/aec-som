/*
 * 製造指図－フォーミュラ 最終LINE_NO 取得SQL
 *
 * entityName=DirectionDirectionFormulaList
 * packageName=direction
 * methodName=getLastLineNo
 *
 */

SELECT NVL(MAX(LINE_NO), 0) + 1 LAST_LINE_NO
FROM DIRECTION_FORMULA
WHERE
    LINE_TYPE = -1
    AND DIRECTION_DIVISION = 1
    AND DIRECTION_NO = /*directionNo*/1
    AND STEP_NO = /*stepNo*/
