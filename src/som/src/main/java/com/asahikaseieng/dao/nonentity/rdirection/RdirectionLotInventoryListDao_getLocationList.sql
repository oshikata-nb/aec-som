/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/

/**
 * ロット在庫取得用SQL
 *
 * @author
 *
 * entityName=RdirectionLotInventoryList
 * packageName=rdirection
 * methodName=getLocationList
 *
 */

SELECT
	LOT_INVENTORY.LOCATION_CD
,	LOT_INVENTORY.INVENTORY_QTY
,	LOT_INVENTORY.ALIAS_LOT_NO
FROM
	LOT_INVENTORY
WHERE
	LOT_INVENTORY.ITEM_CD = /*itemCd*/'12004756'
AND LOT_INVENTORY.LOT_NO = /*lotNo*/'999999'
ORDER BY LOT_INVENTORY.LAST_IN_DATE