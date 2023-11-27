/*
 * 設備グループ一覧用SQL
 *
 * entityName=RecipeResouceGroupList
 * packageName=reciperesoucegrouplist
 * methodName=getList
 *
 */

SELECT RESOUCE_GROUP_CD, RESOUCE_GROUP_NAME
FROM RECIPE_RESOUCE_group
WHERE RESOUCE_GROUP_CD IS NOT NULL

/*IF (condition.srhResouceGroupCd != null) && (condition.srhResouceGroupCd != "")*/
AND (RESOUCE_GROUP_CD LIKE /*condition.srhResouceGroupCd*/'%' OR RESOUCE_GROUP_NAME LIKE /*condition.srhResouceGroupCd*/'%')
/*END*/

ORDER BY RESOUCE_GROUP_CD


