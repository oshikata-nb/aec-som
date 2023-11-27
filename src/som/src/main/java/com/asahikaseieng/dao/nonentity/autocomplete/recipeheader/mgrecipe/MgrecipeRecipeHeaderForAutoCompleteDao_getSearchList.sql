/*
 * 基本処方－原処方ｺｰﾄﾞAutoComplete一覧検索用SQL
 *
 * entityName=MrecipeRecipeHeaderForAutoComplete
 * packageName=recipeheader
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT RECIPE_HEADER.RECIPE_CD
		FROM   RECIPE_HEADER
		WHERE  RECIPE_HEADER.RECIPE_TYPE = 1 -- 1:General
		AND    RECIPE_HEADER.APPROVAL_STATUS = 3 -- 3:承認済み
			  
/*IF (recipeCd != null) && (recipeCd != "")*/
		AND    RECIPE_HEADER.RECIPE_CD LIKE /*recipeCd*/'%'
/*END*/
		
		GROUP  BY RECIPE_HEADER.RECIPE_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
