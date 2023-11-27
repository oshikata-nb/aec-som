/**
 * entityName=RdirectionFormulaLotInventoryList
 * packageName=rdirection
 * methodName=getLotList
 */
SELECT
	LOT_INVENTORY.LOCATION_CD
,	LOT_INVENTORY.ITEM_CD
FROM
	LOT_INVENTORY
WHERE
	LOT_INVENTORY.LOT_NO = /*lotNo*/'12004756'


