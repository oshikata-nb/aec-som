/*
 * 棚卸更新一覧用SQL
 *
 * entityName=InquiryUpdateList
 * packageName=inquiryupdatelist
 * methodName=getList
 *
 */

SELECT COUNT_DATE, COUNT_DIVISION, COUNT_LOCATION, ITEM_CD, LOT_NO, NVL(COUNT_QTY, 0) COUNT_QTY, NVL(FRACTION_QTY, 0) FRACTION_QTY, NVL(INPUT_QTY, 0) INPUT_QTY, NVL(INPUTFRACTION, 0) INPUTFRACTION, COUNT_UPDATE_DATE, UPDATE_DATE
FROM INVENTORY_COUNT
WHERE COUNT_DATE = /*countDate*/'2008/03/03'
 
/*IF(countDivision != null) && (countDivision != "")*/
AND COUNT_DIVISION = /*countDivision*/'%'
/*END*/

/*IF(countLocation != null) && (countLocation != "")*/
AND COUNT_LOCATION = /*countLocation*/'%'
/*END*/

/*IF(itemCd != null) && (itemCd != "")*/
AND ITEM_CD = /*itemCd*/'%'
/*END*/

/*IF(lotNo != null) && (lotNo != "")*/
AND LOT_NO = /*lotNo*/'%'
/*END*/

AND INPUT_QTY IS NOT NULL
 
ORDER BY COUNT_DIVISION, COUNT_LOCATION, ITEM_CD, LOT_NO


