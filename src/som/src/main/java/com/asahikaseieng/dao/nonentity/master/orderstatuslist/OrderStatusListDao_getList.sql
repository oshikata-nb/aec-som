/*
 * 名称マスタ一覧用SQL
 *
 * entityName=OrderStatusList
 * packageName=orderstatuslist
 * methodName=getList
 *
 */

SELECT NAME_DIVISION, NAME_CD, NAME01, NAME02, NMEFLG1
FROM NAMES
WHERE NAME_DIVISION = 'ORST'

/*IF (nameCd != null) && (nameCd != "")*/
AND NAME_CD LIKE /*nameCd*/'%'
/*END*/

ORDER BY NMEFLG1 ASC


