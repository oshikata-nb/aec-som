/*
 * 製造指図プロシージャ 工程名称 取得SQL
 *
 * entityName=DirectionDirectionProcedureList
 * packageName=direction
 * methodName=getOperationName
 *
 */

SELECT
	DIRECTION_PROCEDURE.OPERATION_CD
,	OPERATION.OPERATION_NAME
FROM
	DIRECTION_PROCEDURE
,	OPERATION
WHERE
	DIRECTION_PROCEDURE.DIRECTION_DIVISION = '1'
AND	DIRECTION_PROCEDURE.DIRECTION_NO = /*directionNo*/
AND DIRECTION_PROCEDURE.STEP_NO = /*stepNo*/
AND DIRECTION_PROCEDURE.OPERATION_CD = OPERATION.OPERATION_CD(+)
