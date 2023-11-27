/*
 * 生産ライン詳細用SQL
 *
 * entityName=LineDetail
 * packageName=linedetail
 * methodName=getEntity
 *
 */

SELECT PRODUCTION_LINE, PRODUCTION_LINE_NAME, UPDATE_DATE
FROM LINE
WHERE PRODUCTION_LINE = /*productionLine*/'1'
