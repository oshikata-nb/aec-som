/*
 * 品目基本削除用SQL
 *
 * entityName=ItemQueueDelete
 * packageName=itemqueuedelete
 * methodName=delete
 *
 */

DELETE
FROM ITEM_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
