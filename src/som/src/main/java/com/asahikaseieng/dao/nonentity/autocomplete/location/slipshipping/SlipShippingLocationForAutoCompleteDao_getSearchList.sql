/*
 * 上位ロケーション一覧用SQL
 *
 * entityName=SlipShippingLocationListForAutoComplete
 * packageName=slipshipping
 * methodName=getListForAutoComplete
 *
*/

SELECT *
FROM   (SELECT LOCATION.LOCATION_CD, LOCATION.LOCATION_NAME
		
		FROM   (SELECT *
				FROM   LOCATION
				WHERE  LOCATION.LOCATION_LEVEL = '1'
				AND    LOCATION.LOCATION_CD <> 'K'
				
				UNION
				
				SELECT *
				FROM   LOCATION
				WHERE  LOCATION.LOCATION_LEVEL = '2'
				AND    (LOCATION.LOCATION_CD = 'BK' OR
					  LOCATION.LOCATION_CD = 'BA')) LOCATION
		
		WHERE  (LOCATION.LOCATION_CD LIKE /*locationCd*/'%' OR LOCATION.LOCATION_NAME LIKE /*locationCd*/'%')
		
		ORDER  BY LOCATION.LOCATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
