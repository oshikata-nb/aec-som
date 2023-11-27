/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/

/**
 * 発注入力一覧用SQL
 *
 * @author tosco
 *
 * entityName=PurchaseList
 * packageName=purchase
 * methodName=getSearchList
 *
 */
SELECT
    PSUB.PURCHASE_NO
,   PSUB.BUY_SUBCONTRACT_ORDER_NO
,	PSUB.ORDER_DATE
,	PSUB.CHARGE_ORGANIZATION_CD
,	PSUB.ORGANIZATION_CD
,	PSUB.TANTO_CD
,	PSUB.VENDER_CD
,	PSUB.ITEM_CD
,	DECODE(PSUB.ORDER_DIVISION, 4, PSUB.ITEM_NAME, ITM.ITEM_NAME) AS ITEM_NAME
,	PSUB.ORDER_QUANTITY
,	PSUB.ORDER_CONVERT_QUANTITY
,	PSUB.ORDER_UNITPRICE
,	PSUB.SUPPLIER_ORD_AMOUNT
,	PSUB.SUGGESTED_DELIVERLIMIT_DATE
,	PSUB.ORDER_SHEET_REMARK
,	PSUB.REMARK
,	PSUB.LOCATION_CD
,	PSUB.STATUS
,	PSUB.ORDER_SHEET_NO
,   PSUB.CHECK_QUANTITY
,	PSUB.INPUT_DATE
,	PSUB.INPUTOR_CD
,	PSUB.UPDATE_DATE
,	PSUB.UPDATOR_CD
,	VEN.VENDER_SHORTED_NAME AS SUPPLIER_NAME
,	ITM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV
,	NAME.NAME01 AS ORDER_UNIT
,	ITM.STYLE_OF_PACKING AS LOAD_FORM
,	(CASE								--納入先名称、オーダー区分により名称取得先を変える
		WHEN PSUB.ORDER_DIVISION IN (1, 2, 3, 6) THEN LOC.LOCATION_NAME
		WHEN PSUB.ORDER_DIVISION IN (4) THEN NVL(DEL.SEARCH_KANA, LOC.LOCATION_NAME)
		WHEN PSUB.ORDER_DIVISION IN (5) THEN DEL.SEARCH_KANA
		ELSE NULL
	END ) AS DELIVERY_NAME
FROM	
	VENDER VEN
    ,VALID_ITEM_QUEUE MAX_ITEM
	,VALID_ITEM_QUEUE ITM
	,DELIVERY DEL
	,LOCATION LOC
	,NAMES NAME
	,PURCHASE_SUBCONTRACT PSUB
WHERE
		VEN.VENDER_DIVISION(+) = 'SI'
	AND VEN.VENDER_CD(+) = PSUB.VENDER_CD
    AND MAX_ITEM.ITEM_CD(+) = PSUB.ITEM_CD
    AND ITM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITM.VERSION(+) = MAX_ITEM.VERSION
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITM.UNIT_OF_OPERATION_MANAGEMENT
    AND DEL.DELIVERY_CD(+) = PSUB.LOCATION_CD
    AND LOC.LOCATION_CD(+) = PSUB.LOCATION_CD
    AND PSUB.ORDER_DIVIDE_NO IS NULL
/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
	AND	PSUB.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/1
/*END*/
/*IF (condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
    AND TO_CHAR(PSUB.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'2001/01/01'
/*END*/
/*IF (condition.srhOrderDateTo != null) && (condition.srhOrderDateTo != "")*/
    AND TO_CHAR(PSUB.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2010/01/01'
/*END*/
/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
	AND	PSUB.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
/*END*/
/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	PSUB.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/
/*END*/
/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	PSUB.TANTO_CD LIKE /*condition.srhTantoCd*/
/*END*/
/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0"))*/
	AND	PSUB.ORDER_DIVISION = /*condition.srhOrderDivision*/
/*END*/
/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	PSUB.VENDER_CD LIKE /*condition.srhVenderCd*/
/*END*/
/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	PSUB.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/
/*IF (condition.srhSuggestedDeliverlimitDateFrom != null) && (condition.srhSuggestedDeliverlimitDateFrom != "")*/
    AND TO_CHAR(PSUB.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2001/01/01'
/*END*/
/*IF (condition.srhSuggestedDeliverlimitDateTo != null) && (condition.srhSuggestedDeliverlimitDateTo != "")*/
    AND TO_CHAR(PSUB.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2010/01/01'
/*END*/
/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
	AND	PSUB.LOCATION_CD LIKE /*condition.srhLocationCd*/
/*END*/
/*IF ((condition.srhStatus == "0") || (condition.srhStatus == null) || (condition.srhStatus == ""))*/
		AND	PSUB.STATUS IN ('1','2','3','4','7')
/*END*/
/*IF ((condition.srhStatus != "0") && (condition.srhStatus != null) && (condition.srhStatus != ""))*/
		AND	PSUB.STATUS = /*condition.srhStatus*/
/*END*/
/*IF (( condition.srhOrderSheetNo != null ) && ( condition.srhOrderSheetNo != "" ))*/
	AND	PSUB.ORDER_SHEET_NO LIKE /*condition.srhOrderSheetNo*/
/*END*/
/*IF (( condition.srhTashaCd != null ) && ( condition.srhTashaCd != "" ))*/
	AND	ITM.OTHER_COMPANY_CD1 LIKE /*condition.srhTashaCd*/
/*END*/
ORDER BY
	 PSUB.ORDER_SHEET_NO NULLS FIRST
	,PSUB.SUGGESTED_DELIVERLIMIT_DATE
	,ITEM_CD
	 

	