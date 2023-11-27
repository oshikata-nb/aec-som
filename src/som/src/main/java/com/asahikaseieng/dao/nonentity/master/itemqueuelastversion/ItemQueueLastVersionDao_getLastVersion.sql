/*
 * 品目マスタ検索一覧用SQL
 *
 * entityName=ItemQueueLastVersion
 * packageName=itemqueuelastversion
 * methodName=getLastVersion
 *
 */

SELECT ITEM.VERSION
FROM ITEM_QUEUE ITEM
WHERE ITEM.ITEM_CD = /*itemCd*/'ITEM01'
AND ITEM.VERSION = ( SELECT MAX(VERSION) FROM ITEM_QUEUE
WHERE ITEM_CD = /*itemCd*/'ITEM01'
)
