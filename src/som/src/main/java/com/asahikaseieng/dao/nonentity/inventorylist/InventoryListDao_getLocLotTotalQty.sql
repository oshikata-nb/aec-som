/*
 * 在庫数量合計取得用SQL
 *
 * entityName=InventoryList
 * packageName=inventorylist
 * methodName=getLocLotTotalQty
 *
 */

SELECT SUM(INVENTORY_QTY) INVENTORY_QTY
FROM

(SELECT
CASE TYPE_DIVISION
    WHEN 1 THEN NVL(INVENTORY_QTY, 0)
    WHEN 3 THEN NVL(INVENTORY_QTY, 0)
    ELSE NVL(INVENTORY_QTY, 0) * NVL(KG_OF_FRACTION_MANAGEMENT, 1)
END INVENTORY_QTY

FROM LOT_INVENTORY, ITEM, LOCATION, NAMES OPERATION_NAMES, NAMES FRACTION_NAMES,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE INVENTORY_QTY <> 0

/*IF (locationCd != null) && (locationCd != "")*/
AND (LOT_INVENTORY.LOCATION_CD = /*locationCd*/'%' OR LOCATION_NAME = /*locationCd*/'%')
/*END*/

/*IF (lotNo != null) && (lotNo != "")*/
AND LOT_NO LIKE /*lotNo*/'%'
/*END*/

AND LOT_INVENTORY.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND OPERATION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = OPERATION_NAMES.NAME_CD(+)
AND FRACTION_NAMES.NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_FRACTION_MANAGEMENT = FRACTION_NAMES.NAME_CD(+))
