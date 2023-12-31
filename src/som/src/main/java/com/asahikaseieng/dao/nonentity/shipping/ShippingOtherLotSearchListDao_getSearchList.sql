/*
 * ロット検索(ポップアップ)一覧用SQL
 *
 * entityName=ShippingOtherLotSearchList
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
,    (SELECT LOCATION.LOCATION_CD,LOCATION.LOCATION_NAME FROM (SELECT * FROM LOCATION WHERE UPPER_LOCATION_CD = 'BA' AND LOCATION_LEVEL = 3) TEMP_LOCATION,LOCATION WHERE LOCATION.UPPER_LOCATION_CD LIKE TEMP_LOCATION.LOCATION_CD || '%' AND LOCATION.AVAILABLE_FLG = 1 AND LOCATION.LOCATION_LEVEL = 4 AND LOCATION.STOCK_DIVISION != 3) LOCATION
WHERE	LOT_INVENTORY.LOCATION_CD IS NOT NULL
AND	LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD
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
