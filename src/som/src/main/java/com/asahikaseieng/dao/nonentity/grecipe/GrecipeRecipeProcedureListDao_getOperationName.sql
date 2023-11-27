/*
 * 処方プロシージャ 工程名称 取得SQL
 *
 * entityName=GrecipeRecipeProcedureList
 * packageName=grecipe
 * methodName=getOperationName
 *
 */

SELECT
	RECIPE_PROCEDURE.OPERATION_CD
,	OPERATION.OPERATION_NAME
FROM
	RECIPE_PROCEDURE
,	OPERATION
WHERE RECIPE_PROCEDURE.RECIPE_ID = /*recipeId*/
  AND RECIPE_PROCEDURE.STEP_NO = /*stepNo*/
  AND RECIPE_PROCEDURE.OPERATION_CD = OPERATION.OPERATION_CD(+)
