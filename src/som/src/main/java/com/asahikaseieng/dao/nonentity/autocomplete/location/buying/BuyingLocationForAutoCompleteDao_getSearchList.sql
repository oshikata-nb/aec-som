/*
 * ロケーション一覧用SQL
 *
 * entityName=BuyingLocationListForAutoComplete
 * packageName=buying
 * methodName=getListForAutoComplete
 *
*/

SELECT *
FROM   (SELECT LOCATION_CD, LOCATION_NAME
		FROM   LOCATION
		WHERE  AVAILABLE_FLG = 1
		AND    (LOCATION_CD LIKE /*locationCd*/'%' OR LOCATION_NAME LIKE /*locationCd*/'%')
		ORDER  BY LOCATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
