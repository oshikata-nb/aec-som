/*
 * 処方検査 最終LINE_NO 取得SQL
 *
 * entityName=GrecipeRecipeInspectionList
 * packageName=grecipe
 * methodName=getLastLineNo
 *
 */

SELECT NVL(MAX(RECIPE_INSPECTION.LINE_NO), 0) + 1 LAST_LINE_NO
FROM RECIPE_INSPECTION
WHERE RECIPE_INSPECTION.RECIPE_ID = /*recipeId*/
  AND RECIPE_INSPECTION.STEP_NO = /*stepNo*/
