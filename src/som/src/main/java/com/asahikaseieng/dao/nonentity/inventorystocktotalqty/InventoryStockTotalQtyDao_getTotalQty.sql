/*
 * 品目別予定在庫照会手持在庫数取得用SQL
 *
 * entityName=InventoryStockTotalQty
 * packageName=inventorystocktotalqty
 * methodName=getTotalQty
 *
 */

SELECT INVENTORY_QTY
FROM ITEM_INVENTORY, ITEM,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE ITEM_INVENTORY.ITEM_CD IS NOT NULL

/*IF(itemCd != null) && (itemCd != "")*/
AND (ITEM_INVENTORY.ITEM_CD = /*itemCd*/'%' OR ITEM_NAME = /*itemCd*/'%')
/*END*/

/*IF(otherCompanyCd1 != null) && (otherCompanyCd1 != "")*/
AND	ITEM.OTHER_COMPANY_CD1 = /*otherCompanyCd1*/'%'
/*END*/

AND ITEM_INVENTORY.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
