/*
 * ロケーション棚卸検索用SQL
 *
 * entityName=InquiryLocationCount
 * packageName=inquirylocationcount
 * methodName=getLocationCount
 *
 */

SELECT COUNT(*) LOCATION_COUNT
FROM LOCATION
WHERE COUNT_DIVISION = /*countDivision*/'%'


