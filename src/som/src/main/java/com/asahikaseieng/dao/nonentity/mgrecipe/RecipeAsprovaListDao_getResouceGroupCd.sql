/**
 * 処方ASPROVA 設備グループコード取得要SQL
 * entityName=RecipeAsprova
 * packageName=mgrecipe
 * methodName=getResouceGroupCd
 *
 */

SELECT RESOUCE_GROUP_CD
FROM RECIPE_ASPROVA
WHERE RECIPE_ID = /*recipeId*/1
AND OPERATION_GROUP_CD = /*operationGroupCd*/'%'
GROUP BY RESOUCE_GROUP_CD
