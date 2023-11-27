/*
 * 成分情報キューマスタ用SQL
 *
 * entityName=ComponentInformationQueueDetail
 * packageName=componentinformationqueuedetail
 * methodName=getEntity
 *
 */

SELECT *
FROM COMPONENT_INFORMATION_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
AND INDICATE_ORDER = /*indicateOrder*/1


