/**
 * entityName=ShippingListForReport
 * packageName=shippingforreport
 * methodName=getHeaderListForReport
 *
 */
SELECT
	SHEAD.SHIPPING_NO
,	SHEAD.SHIPPING_INSTRUCT_DATE
,	SHEAD.SCHEDULED_SHIPPING_DATE
,	SHEAD.TANTO_CD
,	TANTO.TANTO_NM
,	SHEAD.ORDER_NO
,	SHEAD.ORDER_ROW_NO
,	SHEAD.VENDER_DIVISION
,	SHEAD.VENDER_CD
,	VENDER.VENDER_SHORTED_NAME AS VENDER_NAME1
,	SHEAD.DELIVERY_CD
,	DELIVERY.SEARCH_KANA AS DELIVERY_NAME1
,	SHEAD.SHIPPING_STATUS
,	CASE SHEAD.SHIPPING_STATUS
	　　WHEN 1 THEN '出荷指図未確定'
		WHEN 2 THEN '出荷指図確定済'
	　　WHEN 3 THEN '指図送信済'
		WHEN 4 THEN '実績受信中'
	　　WHEN 5 THEN '出荷完了'
		ELSE ''
	END AS SHIPPING_STATUS_NAME
,	SHEAD.ITEM_CD
,	ITEM.ITEM_NAME
,	LOCATION.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	SHEAD.SUMMERY
,	SHEAD.DELIVERY_COMP
,	SHEAD.SHIPPING_RESULT_DATE
,	SHEAD.SHIPPING_SLIP_NO
,	SHEAD.SHIPPING_SLIP_ROW_NO
,	SHEAD.SLIP_PUBLISH_COMP
,	SHEAD.SLIP_PUBLISH_DATE
,	SHEAD.SLIP_SHIPPING_ORDER_COMP
,	SHEAD.SLIP_SHIPPING_ORDER_DATE
,	SHEAD.SLIP_SHIPPING_SCHEDULE_COMP
,	SHEAD.SLIP_SHIPPING_SCHEDULE_DATE
,	SHEAD.SLIP_SHIPPING_TAG_COMP
,	SHEAD.SLIP_SHIPPING_TAG_DATE
,	SHEAD.SLIP_SHIPPING_REQUEST_COMP
,	SHEAD.SLIP_SHIPPING_REQUEST_DATE
,	SHEAD.CARRY_CD
,	CARRY.CARRY_NAME1
,	SHEAD.SUGGESTED_DELIVERLIMIT
,	SHEAD.CARRY_FARE
,	SHEAD.SENDING_OFF_NUMBER
,	SHEAD.INPUT_DATE
,	SHEAD.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	SHEAD.UPDATE_DATE
,	SHEAD.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM
	SHIPPING SHEAD
,   	DELIVERY
,   	(
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
,   	VALID_ITEM_QUEUE ITEM
,   	NAMES NAME
,   	CARRY CARRY
,   	VENDER
,	LOGIN TANTO
,	LOGIN INPUTOR
,	LOGIN UPDATOR

WHERE
	SHEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	SHEAD.VENDER_CD = VENDER.VENDER_CD(+) 
AND	SHEAD.TANTO_CD = TANTO.TANTO_CD(+)
AND 	VENDER.VENDER_DIVISION(+) = 'TS'
AND 	SHEAD.SHIPPING_NO = TOP_SDETAIL.SHIPPING_NO(+)
AND 	SDETAIL.SHIPPING_NO(+)     = TOP_SDETAIL.SHIPPING_NO
AND 	SDETAIL.SHIPPING_ROW_NO(+) = TOP_SDETAIL.SHIPPING_ROW_NO
AND 	SDETAIL.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND	MAX_ITEM.ITEM_CD(+) = SHEAD.ITEM_CD
AND 	ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
AND 	ITEM.VERSION(+) = MAX_ITEM.VERSION
AND 	NAME.NAME_DIVISION(+) = 'UNIT'
AND 	NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
AND 	SHEAD.CARRY_CD = CARRY.CARRY_CD
AND	SHEAD.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	SHEAD.UPDATOR_CD = UPDATOR.TANTO_CD(+)
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
	AND	SHEAD.SHIPPING_STATUS IN ('1', '2')
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

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
    AND SHEAD.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SHEAD.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%')
/*END*/

ORDER BY 
	SHEAD.SCHEDULED_SHIPPING_DATE ASC
,	SHEAD.ORDER_NO ASC NULLS FIRST
,	SHEAD.SHIPPING_NO ASC

