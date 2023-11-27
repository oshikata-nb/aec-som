/*
 * 在庫照会品目検索用SQL
 *
 * entityName=InventoryItemQueueDetail
 * packageName=inventoryitemqueuedetail
 * methodName=getEntity
 *
 */

SELECT ITEM_QUEUE.ITEM_CD, ITEM_QUEUE.VERSION, ITEM_NAME, UNIT_OF_OPERATION_MANAGEMENT
FROM VALID_ITEM_QUEUE ITEM_QUEUE
WHERE ITEM_QUEUE.ITEM_CD = /*itemCd*/'%'
