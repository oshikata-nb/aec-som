/*
 * 在庫入庫入力 入荷ロット番号重複チェック用SQL
 *
 * entityName=InventoryDetailLotDuplicate
 * packageName=inventorydetaillotduplicate
 * methodName=getEntity
 *
 */

SELECT LOCATION_CD, ITEM_CD, LOT_NO
FROM LOT_INVENTORY
WHERE LOT_NO = /*lotNo*/'%'


