/**
 * 処方ASPROVA-オートコンプリート検索用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipeAsprovaForAutoComplete
 * packageName=autocomplete.recipeasprova.direction
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DISTINCT RESOUCE.RESOUCE_CD   AS RESOUCE_CD
					   ,RESOUCE.RESOUCE_NAME AS RESOUCE_NAME
		FROM   RECIPE_RESOUCE RESOUCE, RECIPE_ASPROVA ASPROVA
		WHERE  ASPROVA.RECIPE_ID = /*recipeId*/1
		AND    ASPROVA.RESOUCE_CD LIKE /*resourceCd*/'%'
		AND    RESOUCE.RESOUCE_CD(+) = ASPROVA.RESOUCE_CD
		ORDER  BY RESOUCE_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
