/*
 * ロケーション一覧用SQL
 *
 * entityName=SalesLocationListForAutoComplete
 * packageName=sales
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT LOCATION.LOCATION_CD, LOCATION.LOCATION_NAME
		FROM   (SELECT LOCATION_CD, LOCATION_NAME
				FROM   LOCATION
				WHERE  LOCATION.LOCATION_CD LIKE /*locationCd*/'%' OR LOCATION.LOCATION_NAME LIKE /*locationCd*/'%') LOCATION
		WHERE  LOCATION_CD IN (SELECT LOCATION_CD
							   FROM   LOT_INVENTORY
							   WHERE  LOCATION_CD = LOCATION.LOCATION_CD
							   AND    ITEM_CD = /*itemCd*/'%')
		ORDER  BY LOCATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
