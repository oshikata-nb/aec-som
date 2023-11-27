/*
 * 品目マスタ検索SQL
 * ITEM_QUEUE検索
 * entityName=ItemList
 * packageName=itemlist
 * methodName=getList
 *
 */

SELECT	DISTINCT ITEM_QUEUE.ITEM_CD	-- 品目コード
,	ITEM_QUEUE.ITEM_NAME	-- 品目名称
FROM	ITEM_QUEUE
WHERE ITEM_QUEUE.ITEM_CD IS NOT NULL

/*IF (condition.itemCd != null) && (condition.itemCd != "")*/
	AND ITEM_QUEUE.ITEM_CD LIKE /*condition.itemCd*/'%'
/*END*/

/*IF (condition.itemName != null) && (condition.itemName != "")*/
	AND ITEM_QUEUE.ITEM_NAME LIKE /*condition.itemName*/'%'
/*END*/

ORDER BY ITEM_QUEUE.ITEM_CD

