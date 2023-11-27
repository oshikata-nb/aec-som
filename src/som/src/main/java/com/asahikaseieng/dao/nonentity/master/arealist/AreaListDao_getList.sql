/*
 * 地区マスタ一覧用SQL
 *
 * entityName=AreaList
 * packageName=arealist
 * methodName=getList
 *
 */

SELECT AREA_CD, AREA_NAME
FROM AREA
WHERE AREA_CD IS NOT NULL

/*IF (condition.srhAreaCd != null) && (condition.srhAreaCd != "")*/
AND (AREA_CD LIKE /*condition.srhAreaCd*/'%' OR AREA_NAME LIKE /*condition.srhAreaCd*/'%')
/*END*/

ORDER BY AREA_CD
