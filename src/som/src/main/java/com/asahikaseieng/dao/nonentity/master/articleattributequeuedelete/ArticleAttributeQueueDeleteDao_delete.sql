/*
 * 販売品扱い属性削除用SQL
 *
 * entityName=ArticleAttributeQueueDelete
 * packageName=articleattributequeuedelete
 * methodName=delete
 *
 */

DELETE
FROM ARTICLE_ATTRIBUTE_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
