/*
 * 製造品扱い属性削除用SQL
 *
 * entityName=ProductAttributeQueueDelete
 * packageName=productattributequeuedelete
 * methodName=delete
 *
 */

DELETE
FROM PRODUCT_ATTRIBUTE_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
