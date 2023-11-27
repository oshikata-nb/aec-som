/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
*/

/*
 * 製造指図プロシージャ 最終STEP_NO 取得SQL
 *
 * entityName=DirectionDirectionProcedureList
 * packageName=direction
 * methodName=getLastStepNo
 *
 */

SELECT NVL(MAX(STEP_NO), 0) + 1 LAST_STEP_NO
FROM DIRECTION_PROCEDURE
WHERE
    DIRECTION_DIVISION = 1
AND DIRECTION_NO = /*directionNo*/
