/**
 *
 * @author T1344224
 *
 * entityName=ShippingDetailResultListForReport
 * packageName=shippingresultforreport
 * methodName=getDetailSearchList
 *
 */
SELECT
	SDETAIL.SHIPPING_NO
,	SDETAIL.SHIPPING_ROW_NO
,	SDETAIL.ROW_NO
,	SDETAIL.LOT_NO
,	SDETAIL.SHIPPING_INSTRUCTION
,	SDETAIL.SHIPPING_RESULT_DATE
,	SDETAIL.SHIPPING_RESULT_QUANTITY
,	SDETAIL.SHIPPING_STATUS
,	SDETAIL.SUMMERY
,	SDETAIL.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	SDETAIL.DELIVERY_COMP
,	SDETAIL.PRODUCT_OUT_ORDER_BC
,	SDETAIL.INPUT_DATE
,	SDETAIL.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	SDETAIL.UPDATE_DATE
,	SDETAIL.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM
    SHIPPING SHEAD
,	SHIPPING_DETAIL SDETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,   	LOCATION LOCATION

WHERE
	SHEAD.SHIPPING_NO = SDETAIL.SHIPPING_NO
AND	SDETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	SDETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND 	SDETAIL.LOCATION_CD = LOCATION.LOCATION_CD(+)

/*IF (( condition.srhShippingNo != null ) && ( condition.srhShippingNo != "" ))*/
	AND	SHEAD.SHIPPING_NO LIKE /*condition.srhShippingNo*/'%'
/*END*/

/*IF (( condition.srhScheduledShippingDateFrom != null ) && ( condition.srhScheduledShippingDateFrom != "" ))*/
	AND	TO_CHAR(SHEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'%'
/*END*/

/*IF (( condition.srhScheduledShippingDateTo != null ) && ( condition.srhScheduledShippingDateTo != "" ))*/
	AND	TO_CHAR(SHEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'%'
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SHEAD.ORDER_NO >= /*condition.srhOrderNoFrom*/'%'
/*END*/

/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SHEAD.ORDER_NO <= /*condition.srhOrderNoTo*/'%'
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	SHEAD.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
/*END*/

/*IF (( condition.srhDeliveryCd != null ) && ( condition.srhDeliveryCd != "" ))*/
	AND	SHEAD.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
/*END*/

/*IF (( condition.srhShippingStatus == null ) || ( condition.srhShippingStatus == "" ) || ( condition.srhShippingStatus == "0" ))*/
	AND	SHEAD.SHIPPING_STATUS IN ('2','3','4','5')
/*END*/
/*IF (( condition.srhShippingStatus != null ) && ( condition.srhShippingStatus != "" ) && ( condition.srhShippingStatus != "0" ))*/
	AND	SHEAD.SHIPPING_STATUS = /*condition.srhShippingStatus*/'%'
/*END*/

/*IF (( condition.srhCarryCd != null ) && ( condition.srhCarryCd != "" ))*/
	/*IF ( condition.srhCarryCd != "0" )*/
		AND	SHEAD.CARRY_CD = /*condition.srhCarryCd*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
    AND SHEAD.SHIPPING_NO IN (SELECT SHIPPING_NO
                                   FROM SHIPPING_DETAIL
                                   WHERE LOCATION_CD LIKE /*condition.srhLocationCd*/'%')
/*END*/

/*IF (( condition.srhShippingResultDateFrom != null ) && ( condition.srhShippingResultDateFrom != "" ))*/
    AND SHEAD.SHIPPING_RESULT_DATE IS NOT NULL
	AND	SHEAD.SHIPPING_RESULT_DATE >= /*condition.srhShippingResultDateFrom*/'%'
/*END*/

/*IF (( condition.srhShippingResultDateTo != null ) && ( condition.srhShippingResultDateTo != "" ))*/
    AND SHEAD.SHIPPING_RESULT_DATE IS NOT NULL
	AND	SHEAD.SHIPPING_RESULT_DATE <= /*condition.srhShippingResultDateTo*/'%'
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
    AND SHEAD.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SHEAD.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%')
/*END*/

ORDER BY SDETAIL.SHIPPING_NO,SDETAIL.SHIPPING_ROW_NO


