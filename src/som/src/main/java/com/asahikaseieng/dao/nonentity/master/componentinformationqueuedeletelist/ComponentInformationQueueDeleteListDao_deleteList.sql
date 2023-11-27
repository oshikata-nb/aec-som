/*
 * 成分情報キュー削除用 SQL
 *
 * entityName=ComponentInformationQueueDeleteList
 * packageName=componentinformationqueuedeletelist
 * methodName=deleteList
 */

DELETE
FROM COMPONENT_INFORMATION_QUEUE
WHERE ITEM_CD IS NOT NULL
AND ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
