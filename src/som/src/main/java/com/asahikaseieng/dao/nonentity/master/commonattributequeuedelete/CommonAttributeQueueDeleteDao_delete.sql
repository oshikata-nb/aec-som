/*
 * 共通情報削除用SQL
 *
 * entityName=CommonAttributeQueueDelete
 * packageName=commonattributequeuedelete
 * methodName=delete
 *
 */

DELETE
FROM COMMON_ATTRIBUTE_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
