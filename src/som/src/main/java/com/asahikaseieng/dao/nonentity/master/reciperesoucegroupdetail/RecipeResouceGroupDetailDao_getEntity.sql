/*
 * 設備グループマスタ用SQL
 *
 * entityName=RecipeResouceGroupDetail
 * packageName=reciperesoucegroupdetail
 * methodName=getEntity
 *
 */

SELECT RESOUCE_GROUP_CD, RESOUCE_GROUP_NAME, OPERATION_GROUP_CD, UPDATE_DATE
FROM RECIPE_RESOUCE_GROUP
WHERE RESOUCE_GROUP_CD = /*recipeResouceCd*/'%'


