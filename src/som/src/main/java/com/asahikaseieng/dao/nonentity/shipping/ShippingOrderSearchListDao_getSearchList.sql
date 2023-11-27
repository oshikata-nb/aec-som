/*
 * 受注検索(ポップアップ)一覧用SQL
 *
 * entityName=ShippingOrderSearchList
 * packageName=shipping
 * methodName=getSearchList
 *
 */

SELECT
	OHEAD.ORDER_NO
,   OHEAD.SCHEDULED_SHIPPING_DATE
,	OHEAD.DELIVERY_CD
,	OHEAD.VENDER_CD
,	OHEAD.SUGGESTED_DELIVERLIMIT
,   OHEAD.BALANCE_CD
,   OHEAD.DELIVERY_ADDRESS
,   OHEAD.CARRY_CD
,   OHEAD.CARRY_FARE
,   OHEAD.UPDATE_DATE
,   ODETAIL.UPDATE_DATE UPDATE_DATE_DETAIL
,	DELIVERY.SEARCH_KANA AS DELIVERY_NAME1
,	OHEAD.VENDER_CD
,	VENDER.VENDER_SHORTED_NAME AS VENDER_NAME1
,	ODETAIL.ROW_NO
,	ODETAIL.ITEM_CD
,	ODETAIL.ORDER_QTY
,	ODETAIL.MATSS
,   ITEM.ITEM_NAME
,   ITEM.OTHER_COMPANY_CD1
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
,   ITEM.STYLE_OF_PACKING
,	OHEAD.DELIVERY_EXPECTED_DATE
FROM
    ORDER_HEAD OHEAD
,   ORDER_DETAIL ODETAIL
,   DELIVERY DELIVERY
,   VENDER VENDER
,   ITEM ITEM
WHERE
	OHEAD.ORDER_NO IS NOT NULL
AND OHEAD.ORDER_NO = ODETAIL.ORDER_NO
AND ODETAIL.SHIPPING_NO IS NULL
AND OHEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND OHEAD.VENDER_CD = VENDER.VENDER_CD(+)
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND ODETAIL.ITEM_CD = ITEM.ITEM_CD
/*IF (condition.srhOrderNo != null) && (condition.srhOrderNo != "")*/
AND OHEAD.ORDER_NO LIKE /*condition.srhOrderNo*/
/*END*/

/*IF (( condition.srhOrderDivision == null ) || ( condition.srhOrderDivision == "" ) || ( condition.srhOrderDivision == "0" ))*/
	AND	OHEAD.ORDER_DIVISION IN ('1', '2', '4')
/*END*/
/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
	AND	OHEAD.ORDER_DIVISION = /*condition.srhOrderDivision*/
/*END*/

/*IF (( condition.srhScheduledShippingDateFrom != null ) && ( condition.srhScheduledShippingDateFrom != "" ))*/
	AND	OHEAD.SCHEDULED_SHIPPING_DATE >= /*condition.srhScheduledShippingDateFrom*/
/*END*/

/*IF (( condition.srhScheduledShippingDateTo != null ) && ( condition.srhScheduledShippingDateTo != "" ))*/
	AND	OHEAD.SCHEDULED_SHIPPING_DATE <= /*condition.srhScheduledShippingDateTo*/
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	OHEAD.VENDER_CD LIKE /*condition.srhVenderCd*/
/*END*/

/*IF (( condition.srhDeliveryCd != null ) && ( condition.srhDeliveryCd != "" ))*/
	AND	OHEAD.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
    AND ODETAIL.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND ODETAIL.ITEM_CD IN (SELECT ITEM_CD
                            FROM   ITEM
                            WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/)
/*END*/

ORDER BY ORDER_NO, ROW_NO
