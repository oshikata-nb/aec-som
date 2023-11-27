/*
 * 生産ラインマスタ一覧用SQL
 *
 * entityName=LineList
 * packageName=linelist
 * methodName=getList
 *
 */

SELECT PRODUCTION_LINE, PRODUCTION_LINE_NAME
FROM LINE
WHERE PRODUCTION_LINE IS NOT NULL

/*IF (condition.srhProductionLine != null) && (condition.srhProductionLine != "")*/
AND (PRODUCTION_LINE LIKE /*condition.srhProductionLine*/'%' OR PRODUCTION_LINE_NAME LIKE /*condition.srhProductionLine*/'%')
/*END*/

ORDER BY PRODUCTION_LINE
