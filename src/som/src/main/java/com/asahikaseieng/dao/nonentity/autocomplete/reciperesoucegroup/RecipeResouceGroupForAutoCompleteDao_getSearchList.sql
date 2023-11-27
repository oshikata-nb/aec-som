/*
 * 設備グループ一覧用SQL
 *
 * entityName=RecipeResouceGroupForAutoComplete
 * packageName=reciperesoucegroup
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT RESOUCE_GROUP_CD, RESOUCE_GROUP_NAME
		FROM   RECIPE_RESOUCE_GROUP
		WHERE  NOT RESOUCE_GROUP_CD IS NULL
			  
/*IF (resouceGroupCd != null) && (resouceGroupCd != "")*/
		AND    (RESOUCE_GROUP_CD LIKE /*resouceGroupCd*/'%' OR RESOUCE_GROUP_NAME LIKE /*resouceGroupCd*/'%')
/*END*/
		
		ORDER  BY RESOUCE_GROUP_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
