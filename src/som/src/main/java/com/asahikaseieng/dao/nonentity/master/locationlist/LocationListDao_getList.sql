/*
 * ロケーション一覧用SQL
 *
 * entityName=LocationList
 * packageName=locationlist
 * methodName=getList
 *
 */

SELECT LOCATION_CD, LOCATION_NAME
FROM LOCATION
WHERE LOCATION_CD IS NOT NULL

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND	(LOCATION_CD LIKE /*condition.srhLocationCd*/'%' OR LOCATION_NAME LIKE /*condition.srhLocationCd*/'%')
/*END*/

ORDER BY LOCATION_CD


