/*
 * 生産ラインマスタ一覧コンボボックス用SQL
 *
 * entityName=LineListForComboboxes
 * packageName=line
 * methodName=getListForComboboxes
 *
 */

SELECT PRODUCTION_LINE, PRODUCTION_LINE_NAME
FROM LINE
ORDER BY PRODUCTION_LINE


