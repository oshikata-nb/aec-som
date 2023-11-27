/*
 * 処方フォーミュラ 最終SEQ 取得SQL
 *
 * entityName=GrecipeRecipeFormula
 * packageName=grecipe
 * methodName=getLastSeq
 *
 */

SELECT NVL(MAX(SEQ), 0) + 1 LAST_SEQ
FROM RECIPE_FORMULA
WHERE LINE_TYPE = -1
AND   RECIPE_ID = /*recipeId*/1
AND   STEP_NO = /*stepNo*/
