/*
 * 棚卸準備検索用SQL
 *
 * entityName=InquiryInventoryCount
 * packageName=inquiryinventorycount
 * methodName=getInventoryCount
 *
 */

SELECT COUNT(*) INVENTORY_COUNT
FROM INVENTORY_COUNT
WHERE COUNT_DATE = /*countDate*/'2008/03/27'

/*IF (countDivision != null) && (countDivision != "")*/
AND COUNT_DIVISION = /*countDivision*/'%'
/*END*/

/*IF (countLocation != null) && (countLocation != "")*/
AND COUNT_LOCATION = /*countLocation*/'%'
/*END*/

/*IF (itemCd != null) && (itemCd != "")*/
AND ITEM_CD = /*itemCd*/'%'
/*END*/

/*IF (lotNo != null) && (lotNo != "")*/
AND LOT_NO = /*lotNo*/'%'
/*END*/

/*IF (countUpdateDate != null) && (countUpdateDate != "")*/
AND COUNT_UPDATE_DATE IS NULL
/*END*/
