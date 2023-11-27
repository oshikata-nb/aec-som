/*
 * 在庫数量合計取得用SQL
 *
 * entityName=InventoryList
 * packageName=inventorylist
 * methodName=getTotalQty
 *
 */

SELECT SUM(INVENTORY_QTY) INVENTORY_QTY
FROM

(SELECT
SUM(CASE TYPE_DIVISION
    WHEN 1 THEN NVL(INVENTORY_QTY, 0)
    WHEN 3 THEN NVL(INVENTORY_QTY, 0)
    ELSE NVL(INVENTORY_QTY, 0) * NVL(KG_OF_FRACTION_MANAGEMENT, 1)
END) INVENTORY_QTY

FROM LOCATION_INVENTORY, ITEM, LOCATION, NAMES OPERATION_NAMES, NAMES FRACTION_NAMES,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE LOCATION_INVENTORY.LOCATION_CD IN (SELECT LOCATION_CD FROM LOCATION WHERE UPPER_LOCATION_CD IS NULL)
AND INVENTORY_QTY <> 0
AND LOCATION_INVENTORY.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND LOCATION_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND OPERATION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = OPERATION_NAMES.NAME_CD(+)
AND FRACTION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_FRACTION_MANAGEMENT = FRACTION_NAMES.NAME_CD(+)

GROUP BY LOCATION_INVENTORY.LOCATION_CD, LOCATION_INVENTORY.ITEM_CD)
