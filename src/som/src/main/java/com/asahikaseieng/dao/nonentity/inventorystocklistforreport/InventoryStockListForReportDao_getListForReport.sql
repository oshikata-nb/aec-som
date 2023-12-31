/*
 *�pSQL
 *
 * entityName=InventoryStockListForReport
 * packageName=inventorystocklistforreport
 * methodName=getListForReport
 *
 */

SELECT 
	INOUT_SOURCE_NO
,	INOUT_DIVISION
,	CASE
    	WHEN INOUT_DIVISION = 1 THEN '製造受入'
	    WHEN INOUT_DIVISION = 2 THEN '製造払出'
	    WHEN INOUT_DIVISION = 3 THEN '入荷受入'
	    WHEN INOUT_DIVISION = 4 THEN '出荷払出'
	    WHEN INOUT_DIVISION = 10 THEN '在庫入出庫'
	    WHEN INOUT_DIVISION = 11 THEN '棚差調整'
	    WHEN INOUT_DIVISION = 12 THEN '売上返品'
	    WHEN INOUT_DIVISION = 13 THEN '売上(預り品)'
	    ELSE NULL
	END INOUT_DIVISION_NAME
,	0 POSSIBLE_QTY
,	INOUT_SOURCE.ODER_NO
,	INOUT_SOURCE.ODER_LINE_NO
,	INOUT_SOURCE.ITEM_CD
,	ITEM.ITEM_NAME
,	ITEM.OTHER_COMPANY_CD1
,	ITEM.STYLE_OF_PACKING
,	ITEM.UNIT_OF_OPERATION_MANAGEMENT
,	INOUT_SOURCE.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	INOUT_SOURCE.LOT_NO
,	INOUT_SOURCE.INOUT_QTY
,	INOUT_SOURCE.INOUT_DATE
,	INOUT_SOURCE.INPUT_DATE
,	INOUT_SOURCE.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	INOUT_SOURCE.UPDATE_DATE
,	INOUT_SOURCE.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
,	INOUT_SOURCE.ITEM_TYPE
,	INOUT_SOURCE.REFERENCE_NO
,	INOUT_SOURCE.REFERENCE_LINE_NO
,	INOUT_SOURCE.ASSIGN_FLAG
,	INOUT_SOURCE.OVER_FLG




FROM 
	INOUT_SOURCE
, 	ITEM
,	(SELECT ITEM_CD, MAX(VERSION) VERSION FROM ITEM GROUP BY ITEM_CD) MAX_ITEM 
,	LOCATION
,	LOGIN INPUTOR
,	LOGIN UPDATOR


WHERE INOUT_SOURCE.ITEM_CD IS NOT NULL

/*IF(itemCd != null) && (itemCd != "")*/
AND (INOUT_SOURCE.ITEM_CD = /*itemCd*/'11003027' OR ITEM.ITEM_NAME = /*itemCd*/'11003027')
/*END*/

/*IF(otherCompanyCd1 != null) && (otherCompanyCd1 != "")*/
AND	ITEM.OTHER_COMPANY_CD1 = /*otherCompanyCd1*/'120316'
/*END*/
AND OVER_FLG = 0
AND INOUT_QTY <> 0
AND INOUT_SOURCE.ITEM_CD = MAX_ITEM.ITEM_CD(+)
AND MAX_ITEM.ITEM_CD = ITEM.ITEM_CD(+)
AND MAX_ITEM.VERSION = ITEM.VERSION(+)
AND INOUT_SOURCE.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND	INOUT_SOURCE.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	INOUT_SOURCE.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY INOUT_DATE


