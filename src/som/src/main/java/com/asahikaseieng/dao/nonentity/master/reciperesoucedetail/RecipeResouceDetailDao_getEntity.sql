/*
 * 設備詳細用SQL
 *
 * entityName=RecipeResouceDetail
 * packageName=reciperesoucedetail
 * methodName=getEntity
 *
 */

SELECT RESOUCE.RESOUCE_CD, RESOUCE.RESOUCE_NAME, RESOUCE.SHORT_NAME, RESOUCE.COST_MACHINE, RESOUCE.COST,
RESOUCE.RESOUCE_GROUP_CD, RESOUCE.PRODUCTION_LINE, ORDER_PUBLISH_FLG, RESOUCE.UPDATE_DATE,
RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_NAME, LINE.PRODUCTION_LINE_NAME
FROM RECIPE_RESOUCE RESOUCE, RECIPE_RESOUCE_GROUP, LINE
WHERE RESOUCE.RESOUCE_CD = /*resouceCd*/'%'
AND RESOUCE.RESOUCE_GROUP_CD = RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD(+)
AND RESOUCE.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)


