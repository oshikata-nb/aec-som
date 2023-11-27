/*
 * 処方プロシージャ 最終STEP_NO 取得SQL
 *
 * entityName=GrecipeRecipeProcedure
 * packageName=grecipe
 * methodName=getLastStepNo
 *
 */

SELECT NVL(MAX(STEP_NO), 0) + 1 LAST_STEP_NO
FROM RECIPE_PROCEDURE
WHERE RECIPE_ID = /*recipeId*/1
