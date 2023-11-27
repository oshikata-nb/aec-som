/*
 * 地区詳細用SQL
 *
 * entityName=AreaDetail
 * packageName=areadetail
 * methodName=getEntity
 *
 */

SELECT AREA_CD, AREA_NAME, UPDATE_DATE
FROM AREA
WHERE AREA_CD = /*areaCd*/'1'
