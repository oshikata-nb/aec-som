/*
 * 設備マスタ一覧用SQL
 *
 * entityName=RecipeResouceList
 * packageName=reciperesoucelist
 * methodName=getList
 *
 */

SELECT RESOUCE_CD, RESOUCE_NAME
FROM RECIPE_RESOUCE
WHERE RESOUCE_CD IS NOT NULL

/*IF (condition.srhResouceCd != null) && (condition.srhResouceCd != "")*/
AND (RESOUCE_CD LIKE /*condition.srhResouceCd*/'%' OR RESOUCE_NAME LIKE /*condition.srhResouceCd*/'%')
/*END*/

ORDER BY RESOUCE_CD
