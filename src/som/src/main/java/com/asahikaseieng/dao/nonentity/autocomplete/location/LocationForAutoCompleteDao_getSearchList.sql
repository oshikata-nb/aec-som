/*
 * ロケーション一覧用SQL
 *
 * entityName=LocationForAutoComplete
 * packageName=location
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT LOCATION.LOCATION_CD, LOCATION.LOCATION_NAME
		FROM   LOCATION
		
		WHERE  LOCATION_CD IS NOT NULL
			  
/*IF (locationCd != null) && (locationCd != "")*/
		AND    (LOCATION.LOCATION_CD LIKE /*locationCd*/'%' OR LOCATION.LOCATION_NAME LIKE /*locationCd*/'%')
/*END*/
		
		ORDER  BY LOCATION.LOCATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
