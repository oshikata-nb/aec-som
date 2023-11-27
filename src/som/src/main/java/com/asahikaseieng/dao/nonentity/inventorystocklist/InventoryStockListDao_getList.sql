/*
 * 品目別予定在庫照会一覧用SQL
 *
 * entityName=InventoryStockList
 * packageName=inventorystocklist
 * methodName=getList
 *
 */

SELECT INOUT_SOURCE_NO, INOUT_DIVISION, INOUT_QTY, INOUT_DATE, ODER_NO, 0 POSSIBLE_QTY, ODER_LINE_NO, UNIT_OF_OPERATION_MANAGEMENT, LOCATION_CD,
CASE
    WHEN INOUT_DIVISION = 1 THEN '製造受入'
    WHEN INOUT_DIVISION = 2 THEN '製造払出'
    WHEN INOUT_DIVISION = 3 THEN '入荷受入'
    WHEN INOUT_DIVISION = 4 THEN '出荷払出'
    WHEN INOUT_DIVISION = 10 THEN '在庫入出庫'
    WHEN INOUT_DIVISION = 11 THEN '棚差調整'
    WHEN INOUT_DIVISION = 12 THEN '売上返品'
    WHEN INOUT_DIVISION = 13 THEN '売上(預り品)'
    ELSE NULL
END INOUT_DIVISION_NAME,

DIRECTION_HEADER.DIRECTION_DIVISION, DIRECTION_HEADER.DIRECTION_STATUS, NVL(DIRECTION_HEADER.DIRECTION_NO, INOUT_SOURCE.ODER_NO) LINK_NO

FROM INOUT_SOURCE, ITEM, NAMES, DIRECTION_HEADER,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE INOUT_SOURCE.ITEM_CD IS NOT NULL

/*IF(condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND (INOUT_SOURCE.ITEM_CD = /*condition.srhItemCd*/'%' OR ITEM_NAME = /*condition.srhItemCd*/'%')
/*END*/

/*IF(condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND	ITEM.OTHER_COMPANY_CD1 = /*condition.srhOtherCompanyCd1*/'%'
/*END*/
AND OVER_FLG = 0
AND INOUT_QTY <> 0
AND	INOUT_SOURCE.ODER_NO = DIRECTION_HEADER.DIRECTION_NO(+)
AND INOUT_SOURCE.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = NAME_CD(+)

ORDER BY INOUT_DATE
