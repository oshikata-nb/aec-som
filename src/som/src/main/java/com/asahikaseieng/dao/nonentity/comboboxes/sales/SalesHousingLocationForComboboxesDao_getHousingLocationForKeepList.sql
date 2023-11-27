/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/

/**
 * 売上－入庫ロケーション(預り品用)一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=List<SalesHousingLocationForComboboxes>
 * packageName=sales
 * methodName=getHousingLocationForKeepList
 *
 */
SELECT
	 LOCATION.LOCATION_CD
,    LOCATION.LOCATION_NAME
FROM
     LOCATION
WHERE
     LOCATION.UPPER_LOCATION_CD = 'BA'
ORDER BY
     LOCATION_CD
 