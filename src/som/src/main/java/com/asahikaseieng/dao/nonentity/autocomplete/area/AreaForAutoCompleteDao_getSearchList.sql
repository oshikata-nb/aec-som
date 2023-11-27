/*
 * 地区一覧用SQL
 *
 * entityName=AreaForAutoComplete
 * packageName=area
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT AREA_CD, AREA_NAME
		FROM   AREA
		WHERE  AREA_CD IS NOT NULL
			  
/*IF (areaCd != null) && (areaCd != "")*/
		AND    (AREA_CD LIKE /*areaCd*/'%' OR AREA_NAME LIKE /*areaCd*/'%')
/*END*/
		
		ORDER  BY AREA_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
