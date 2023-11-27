/*
 * 処方フォーミュラ 最終LINE_NO 取得SQL
 *
 * entityName=RecipeFormula
 * packageName=mgrecipe
 * methodName=getLastLineNo
 *
 */

SELECT NVL(MAX(LINE_NO), 0) + 1 LAST_LINE_NO
FROM RECIPE_FORMULA
WHERE LINE_TYPE = -1
AND   RECIPE_ID = /*recipeId*/1
AND   STEP_NO = /*stepNo*/
