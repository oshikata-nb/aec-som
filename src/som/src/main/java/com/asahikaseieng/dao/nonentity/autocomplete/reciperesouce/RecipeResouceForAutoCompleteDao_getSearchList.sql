/*
 * 設備一覧用SQL
 *
 * entityName=RecipeResouceForAutoComplete
 * packageName=reciperesouce
 * methodName=getForAutoComplete
 *
*/

SELECT RESOUCE_CD, RESOUCE_NAME
FROM   RECIPE_RESOUCE
WHERE  NOT RESOUCE_CD IS NULL
	  
/*IF (resouceCd != null) && (resouceCd != "")*/
AND    (RESOUCE_CD LIKE /*resouceCd*/'%' OR RESOUCE_NAME LIKE /*resouceCd*/'%')
/*END*/

/*IF (rowlimit != null) && (rowlimit != "")*/
AND    ROWNUM < /*rowlimit*/'50'
/*END*/

ORDER  BY RESOUCE_CD
