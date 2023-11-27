/*
 * ロット検索(ポップアップ)一覧用SQL
 *
 * entityName=ShippingLotSearchList
 * packageName=shipping
 * methodName=getSearchList
 *
 */

SELECT	LOT_INVENTORY.LOCATION_CD
,       LOCATION.LOCATION_NAME
,       LOT_INVENTORY.LOT_NO
,       LOT_INVENTORY.ALIAS_LOT_NO
,       LOT_INVENTORY.INVENTORY_QTY
,       LOT_INVENTORY.BACKORDER_QTY
,       LOT_INVENTORY.INSPECTION_QTY
,       LOT_INVENTORY.ASSIGN_QTY
,       LOT_INVENTORY.ISSUE_DATE
FROM
     LOT_INVENTORY
,    LOCATION
WHERE	LOT_INVENTORY.LOCATION_CD IS NOT NULL
AND LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND LOCATION.STOCK_DIVISION != 3 /* 在庫区分|1:通常,2:預かり在庫,3:在庫引当対象外 */

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND LOT_INVENTORY.LOCATION_CD LIKE /*condition.srhLocationCd*/
/*END*/

AND LOT_INVENTORY.ITEM_CD = /*condition.srhItemCd*/

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND LOT_INVENTORY.LOCATION_CD LIKE /*condition.srhLocationCd*/
/*END*/
AND LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)

/*IF (condition.srhLotNo != null) && (condition.srhLotNo != "")*/
AND LOT_INVENTORY.ALIAS_LOT_NO LIKE /*condition.srhLotNo*/
/*END*/

ORDER BY LOT_INVENTORY.ISSUE_DATE,LOT_INVENTORY.LOCATION_CD
