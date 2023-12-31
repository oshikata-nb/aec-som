/*
 *
 *
 * entityName=OrderNoGoodQty
 * packageName=ordernogoodqty
 * methodName=getNoGoodQty
 *
 */
SELECT 
	LOT_INVENTORY.ITEM_CD
,	NVL(SUM(LOT_INVENTORY.INVENTORY_QTY),0) AS QTY
FROM 
	LOT_INVENTORY
,	LOCATION
WHERE
	LOCATION.LOCATION_CD = LOT_INVENTORY.LOCATION_CD
AND	LOCATION.LOCATION_LEVEL = 3
AND LOCATION.UPPER_LOCATION_CD = 'BZ'
AND	LOT_INVENTORY.ITEM_CD = /*itemCd*/'00002127'
GROUP BY LOT_INVENTORY.ITEM_CD


