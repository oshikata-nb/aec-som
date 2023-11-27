/*
 * ItemCdをキーにversionがMAXのものを取得する
 *
 * entityName=AspImportPurchaseAttributeQueue
 * packageName=aspimport
 * methodName=getEntity
 *
 */
SELECT
	*
FROM
	PURCHASE_ATTRIBUTE_QUEUE
,		(SELECT	ITEM2.ITEM_CD
	,		ITEM2.VERSION
	FROM
	    	(SELECT
	        	ITEM_CD,
	        	MAX(VERSION) AS VERSION
	    	FROM
	        	ITEM
	    	GROUP BY
	        	ITEM_CD
		) MAX_ITEM
	,	ITEM ITEM2
	WHERE	ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD
	AND		ITEM2.VERSION = MAX_ITEM.VERSION
	) ITEM

WHERE
	PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD = ITEM.ITEM_CD
AND	PURCHASE_ATTRIBUTE_QUEUE.VERSION = ITEM.VERSION
AND	PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD = /*itemCd*/