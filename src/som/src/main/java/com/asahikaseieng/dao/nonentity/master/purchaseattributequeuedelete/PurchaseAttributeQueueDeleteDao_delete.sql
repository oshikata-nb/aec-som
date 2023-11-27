/*
 * 購入品扱い属性削除用SQL
 *
 * entityName=PurchaseAttributeQueueDelete
 * packageName=purchaseattributequeuedelete
 * methodName=delete
 *
 */

DELETE
FROM PURCHASE_ATTRIBUTE_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
