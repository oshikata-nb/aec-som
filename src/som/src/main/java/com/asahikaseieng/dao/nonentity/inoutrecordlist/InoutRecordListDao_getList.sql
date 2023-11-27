/*
 * 受払照会一覧用SQL
 *
 * entityName=InoutRecordList
 * packageName=inoutrecordlist
 * methodName=getList
 *
 */

SELECT INOUT_NO, INOUT_SOURCE_NO, NVL(ODER_NO, '') ODER_NO, ODER_LINE_NO, INOUT_DATE, NVL(INOUT_DIVISION, 0) INOUT_DIVISION, INOUT_RECORD.ITEM_CD, ITEM_NAME, STYLE_OF_PACKING, SUBSTR(ITEM_NAME, 0, 7) SHORT_ITEM_NAME, INOUT_QTY, INOUT_RECORD.LOCATION_CD, LOCATION_NAME, SUBSTR(LOCATION_NAME, 0, 12) SHORT_LOCATION_NAME, COUNT_DIVISION, UNIT_OF_OPERATION_MANAGEMENT,
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
END	INOUT_DIVISION_NAME,

DIRECTION_DIVISION, DIRECTION_STATUS, NVL(DIRECTION_NO, ODER_NO) LINK_NO

FROM INOUT_RECORD, DIRECTION_HEADER, ITEM, LOCATION, NAMES,

(SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM
GROUP BY ITEM_CD) MAX_ITEM

WHERE INOUT_RECORD.INOUT_NO IS NOT NULL

/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND (INOUT_RECORD.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhLocationCd != null) && (condition.srhLocationCd != "")*/
AND (INOUT_RECORD.LOCATION_CD LIKE /*condition.srhLocationCd*/'%' OR LOCATION_NAME LIKE /*condition.srhLocationCd*/'%')
/*END*/

/*IF (condition.srhInoutDivision != 0)*/
AND INOUT_DIVISION = /*condition.srhInoutDivision*/1
/*END*/

/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (condition.srhInoutDateFrom != null) && (condition.srhInoutDateFrom != "")*/
AND INOUT_DATE >= /*condition.srhInoutDateFrom*/'2008/04/20'
/*END*/

/*IF (condition.srhInoutDateTo != null) && (condition.srhInoutDateTo != "")*/
 AND INOUT_DATE <= /*condition.srhInoutDateTo*/'2008/04/20'
/*END*/

AND ODER_NO = DIRECTION_HEADER.DIRECTION_NO(+)
AND INOUT_RECORD.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND NAME_DIVISION(+) = 'UNIT'
AND UNIT_OF_OPERATION_MANAGEMENT = NAME_CD(+)
AND INOUT_RECORD.LOCATION_CD = LOCATION.LOCATION_CD(+)
ORDER BY INOUT_DATE, INOUT_NO


