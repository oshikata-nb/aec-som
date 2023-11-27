/*
 * 棚卸準備検索用SQL
 *
 * entityName=InventoryCountDetail
 * packageName=inventorycountdetail
 * methodName=getEntity
 *
 */

SELECT COUNT_DATE
FROM INVENTORY_COUNT
WHERE COUNT_DATE = /*countDate*/'2008/02/29'
AND COUNT_DIVISION = /*countDivision*/'%'

/*IF (countUpdateDate != null) && (countUpdateDate != "")*/
AND COUNT_UPDATE_DATE IS NULL
/*END*/
