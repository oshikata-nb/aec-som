/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/

/**
 * 売上－入庫ロケーション(通常用)一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=List<SalesHousingLocationForComboboxes>
 * packageName=sales
 * methodName=getHousingLocationForStandardList
 *
 */
SELECT
	 LOCATION_CD
	,LOCATION_NAME
FROM
    LOCATION
WHERE
	UPPER_LOCATION_CD = 'BZ'
AND AVAILABLE_FLG = 1
ORDER BY
    LOCATION_CD
 