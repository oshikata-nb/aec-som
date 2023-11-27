/*
 * Created on 2009/03/11
 *
 * $copyright$
 * 20200622 並び順にロット発生日時(昇順)を追加　NAWA_YOSHIFUMI
 */

/*
 * ロット検索(ポップアップ)一覧用SQL
 *
 * entityName=OrderDetailKeepLocationList
 * packageName=orderlotsearchlist
 * methodName=getSearchList
 *
 */
SELECT	LOT_INVENTORY.LOCATION_CD
,       LOCATION.LOCATION_NAME
,       LOT_INVENTORY.ALIAS_LOT_NO AS LOT_NO
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

ORDER BY  LOT_INVENTORY.ISSUE_DATE, LOCATION_CD	
