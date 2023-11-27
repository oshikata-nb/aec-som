/*
 * 棚卸準備削除用SQL
 *
 * entityName=InventoryCountDelete
 * packageName=inventorycountdelete
 * methodName=deleteList
 *
 */

DELETE
FROM INVENTORY_COUNT
WHERE COUNT_DATE = /*countDate*/'2008/02/29'
AND COUNT_DIVISION = /*countDivision*/'%'
