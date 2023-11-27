/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/

/**
 * 出荷指図一覧用SQL
 *
 * @author tosco
 *
 * entityName=ShippingList
 * packageName=shipping
 * methodName=getSearchList
 *
 */
SELECT
	SHEAD.SHIPPING_NO
,	SHEAD.SCHEDULED_SHIPPING_DATE
,	SHEAD.ORDER_NO
,	DELIVERY.SEARCH_KANA AS DELIVERY_NAME1
,   LOCATION.LOCATION_NAME
,   TOP_SDETAIL.SHIPPING_INSTRUCTION_SUM AS SHIPPING_INSTRUCTION
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT    AS UNIT_DIVISION
,   NAME.NAME01                          AS UNIT_NAME
,   CARRY.CARRY_NAME1||DECODE(CARRY.CARRY_NAME2,NULL,NULL,'_'||CARRY.CARRY_NAME2) AS CARRY_NAME
,	SHEAD.SHIPPING_STATUS
,	SHEAD.UPDATE_DATE
,   SHEAD.VENDER_CD
FROM
    SHIPPING SHEAD
,   DELIVERY
,   (
     SELECT
        SHIPPING_NO
     ,	MIN(SHIPPING_ROW_NO) AS SHIPPING_ROW_NO
     ,  SUM(SHIPPING_INSTRUCTION) AS SHIPPING_INSTRUCTION_SUM
     FROM SHIPPING_DETAIL
     GROUP BY
         SHIPPING_NO
    ) TOP_SDETAIL
,   SHIPPING_DETAIL SDETAIL
,   LOCATION LOCATION
,   VALID_ITEM_QUEUE MAX_ITEM
,   VALID_ITEM_QUEUE ITEM
,   NAMES NAME
,   CARRY CARRY
WHERE
	SHEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND SHEAD.SHIPPING_NO = TOP_SDETAIL.SHIPPING_NO(+)
AND SDETAIL.SHIPPING_NO(+)     = TOP_SDETAIL.SHIPPING_NO
AND SDETAIL.SHIPPING_ROW_NO(+) = TOP_SDETAIL.SHIPPING_ROW_NO
AND SDETAIL.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND MAX_ITEM.ITEM_CD(+) = SHEAD.ITEM_CD
AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
AND ITEM.VERSION(+) = MAX_ITEM.VERSION
AND NAME.NAME_DIVISION(+) = 'UNIT'
AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
AND SHEAD.CARRY_CD = CARRY.CARRY_CD
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
	AND	SHEAD.SHIPPING_STATUS IN ('1', '2')
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

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
    AND SHEAD.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SHEAD.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/)
/*END*/

ORDER BY
	SHEAD.SCHEDULED_SHIPPING_DATE ASC
,	SHEAD.ORDER_NO ASC NULLS FIRST
,	SHEAD.SHIPPING_NO ASC

