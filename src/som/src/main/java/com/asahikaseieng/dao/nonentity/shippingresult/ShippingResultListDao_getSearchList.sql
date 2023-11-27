/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/

/**
 * 出荷実績一覧用SQL
 *
 * @author tosco
 *
 * entityName=ShippingResultList
 * packageName=shippingresult
 * methodName=getSearchList
 *
 */
SELECT
	SHEAD.SHIPPING_NO
,	SHEAD.ORDER_NO
,	DELIVERY.DELIVERY_NAME1
,	DELIVERY.SEARCH_KANA
,	SHEAD.SCHEDULED_SHIPPING_DATE
,   ITEM.ITEM_NAME
,   ITEM.STYLE_OF_PACKING
,   SDETAIL.SHIPPING_INSTRUCTION_SUM
,   SDETAIL.SHIPPING_RESULT_QUANTITY_SUM
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT    AS UNIT_DIVISION
,	SHEAD.SHIPPING_RESULT_DATE
,	SHEAD.SHIPPING_STATUS
,	SHEAD.UPDATE_DATE
,   SHEAD.VENDER_CD
FROM
    SHIPPING SHEAD
,   DELIVERY
,   (SELECT
           SHIPPING_NO
     ,     SUM(SHIPPING_INSTRUCTION)     AS SHIPPING_INSTRUCTION_SUM
     ,     SUM(SHIPPING_RESULT_QUANTITY) AS SHIPPING_RESULT_QUANTITY_SUM
    FROM
       SHIPPING_DETAIL
    GROUP BY SHIPPING_NO
    ) SDETAIL
,   VALID_ITEM_QUEUE MAX_ITEM
,   VALID_ITEM_QUEUE ITEM
WHERE
	SHEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND SHEAD.SHIPPING_NO = SDETAIL.SHIPPING_NO(+)
AND MAX_ITEM.ITEM_CD(+) = SHEAD.ITEM_CD
AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
AND ITEM.VERSION(+) = MAX_ITEM.VERSION
/*IF (( condition.srhShippingNo != null ) && ( condition.srhShippingNo != "" ))*/
	AND	SHEAD.SHIPPING_NO LIKE /*condition.srhShippingNo*/
/*END*/

/*IF (( condition.srhScheduledShippingDateFrom != null ) && ( condition.srhScheduledShippingDateFrom != "" ))*/
	AND	TO_CHAR(SHEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/
/*END*/

/*IF (( condition.srhScheduledShippingDateTo != null ) && ( condition.srhScheduledShippingDateTo != "" ))*/
	AND	TO_CHAR(SHEAD.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SHEAD.ORDER_NO >= /*condition.srhOrderNoFrom*/
/*END*/

/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SHEAD.ORDER_NO <= /*condition.srhOrderNoTo*/
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	SHEAD.VENDER_CD LIKE /*condition.srhVenderCd*/
/*END*/

/*IF (( condition.srhDeliveryCd != null ) && ( condition.srhDeliveryCd != "" ))*/
	AND	SHEAD.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/
/*END*/

/*IF (( condition.srhShippingStatus == null ) || ( condition.srhShippingStatus == "" ) || ( condition.srhShippingStatus == "0" ))*/
	AND	SHEAD.SHIPPING_STATUS IN ('2','3','4','5')
/*END*/
/*IF (( condition.srhShippingStatus != null ) && ( condition.srhShippingStatus != "" ) && ( condition.srhShippingStatus != "0" ))*/
	AND	SHEAD.SHIPPING_STATUS = /*condition.srhShippingStatus*/
/*END*/

/*IF (( condition.srhCarryCd != null ) && ( condition.srhCarryCd != "" ))*/
	/*IF ( condition.srhCarryCd != "0" )*/
		AND	SHEAD.CARRY_CD = /*condition.srhCarryCd*/
	/*END*/
/*END*/

/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
    AND SHEAD.SHIPPING_NO IN (SELECT SHIPPING_NO
                                   FROM SHIPPING_DETAIL
                                   WHERE LOCATION_CD LIKE /*condition.srhLocationCd*/)
/*END*/

/*IF (( condition.srhShippingResultDateFrom != null ) && ( condition.srhShippingResultDateFrom != "" ))*/
    AND SHEAD.SHIPPING_RESULT_DATE IS NOT NULL
	AND	SHEAD.SHIPPING_RESULT_DATE >= /*condition.srhShippingResultDateFrom*/
/*END*/

/*IF (( condition.srhShippingResultDateTo != null ) && ( condition.srhShippingResultDateTo != "" ))*/
    AND SHEAD.SHIPPING_RESULT_DATE IS NOT NULL
	AND	SHEAD.SHIPPING_RESULT_DATE <= /*condition.srhShippingResultDateTo*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
    AND SHEAD.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SHEAD.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/)
/*END*/

ORDER BY SHEAD.SHIPPING_NO
