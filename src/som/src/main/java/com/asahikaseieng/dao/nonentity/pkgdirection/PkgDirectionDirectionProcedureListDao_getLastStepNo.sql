/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/

/*
 * 包装指図－製造指図プロシージャ 最終STEP_NO 取得SQL
 *
 * entityName=PkgDirectionDirectionProcedureList
 * packageName=pkgdirection
 * methodName=getLastStepNo
 *
 */

SELECT
	NVL(MAX(STEP_NO), 0) + 1 LAST_STEP_NO
FROM
	DIRECTION_PROCEDURE
WHERE 
	DIRECTION_DIVISION = /*directionDivision*/1 AND
	DIRECTION_NO = /*directionNo*/
