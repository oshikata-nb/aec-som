/**
 * @author t1344224
 *
 * entityName=SlipShippingDetailListForReport
 * packageName=slipshippinglistforreport
 * methodName=getDetailSearchList
 *
 */
SELECT	
	SHIPPING_DETAIL.SHIPPING_NO
,	SHIPPING_DETAIL.SHIPPING_ROW_NO
,	SHIPPING_DETAIL.ROW_NO
,	SHIPPING_DETAIL.LOT_NO
,	SHIPPING_DETAIL.SHIPPING_INSTRUCTION
,	SHIPPING_DETAIL.SHIPPING_RESULT_DATE
,	SHIPPING_DETAIL.SHIPPING_RESULT_QUANTITY
,	SHIPPING_DETAIL.SHIPPING_STATUS
,	SHIPPING_DETAIL.SUMMERY
,	SHIPPING_DETAIL.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	SHIPPING_DETAIL.DELIVERY_COMP
,	SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC
,	SHIPPING_DETAIL.INPUT_DATE
,	SHIPPING_DETAIL.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	SHIPPING_DETAIL.UPDATE_DATE
,	SHIPPING_DETAIL.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM
	SHIPPING
,	CARRY
,   	DELIVERY
,   	VENDER
,	LOGIN TANTO
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	ITEM
,	(SELECT
		SHIPPING_DETAIL.SHIPPING_NO
	,	MAX(UPPER_LOCATION.UPPER_LOCATION_CD) as UPPER_LOCATION_CD
	FROM
		SHIPPING_DETAIL
	,	(SELECT
			LOCA1.LOCATION_CD
		,	NVL(LOCA2.UPPER_LOCATION_CD,LOCA2.LOCATION_CD) as UPPER_LOCATION_CD
		FROM
			LOCATION LOCA1
		,	LOCATION LOCA2
		WHERE
			NVL(LOCA1.UPPER_LOCATION_CD,LOCA1.LOCATION_CD) = LOCA2.LOCATION_CD(+)
		AND	(LOCA1.LOCATION_LEVEL = '1' OR LOCA1.LOCATION_LEVEL = '4')
		AND	LOCA1.LOCATION_CD <> 'K'
		)UPPER_LOCATION
	
	WHERE
		SHIPPING_DETAIL.LOCATION_CD = UPPER_LOCATION.LOCATION_CD
	
	GROUP BY
		SHIPPING_DETAIL.SHIPPING_NO
	)SHIPPING_DETAIL_LOCATION
,   	SHIPPING_DETAIL
,   	LOCATION LOCATION

WHERE
	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO
AND	SHIPPING.CARRY_CD = CARRY.CARRY_CD(+)
AND	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL_LOCATION.SHIPPING_NO(+)
AND	SHIPPING.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	SHIPPING.VENDER_CD = VENDER.VENDER_CD(+) 
AND 	VENDER.VENDER_DIVISION(+) = 'TS'
AND	SHIPPING.TANTO_CD = TANTO.TANTO_CD(+)
AND	SHIPPING.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	SHIPPING.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	SHIPPING.ITEM_CD = ITEM.ITEM_CD(+)
AND 	SHIPPING_DETAIL.LOCATION_CD = LOCATION.LOCATION_CD(+)

/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
	AND	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD = /*condition.srhLocationCd*/'%'
/*END*/

/*IF (( condition.srhCarryCd != null ) && ( condition.srhCarryCd != "" ))*/
	/*IF (condition.srhCarryCd == "0")*/
			
	/*END*/
	/*IF (condition.srhCarryCd != "0")*/
		AND	SHIPPING.CARRY_CD = /*condition.srhCarryCd*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhScheduledShippingDateFrom != null ) && ( condition.srhScheduledShippingDateFrom != "" ))*/
	AND	SHIPPING.SCHEDULED_SHIPPING_DATE >= /*condition.srhScheduledShippingDateFrom*/'%'
/*END*/
/*IF (( condition.srhScheduledShippingDateTo != null ) && ( condition.srhScheduledShippingDateTo != "" ))*/
	AND	SHIPPING.SCHEDULED_SHIPPING_DATE <= /*condition.srhScheduledShippingDateTo*/'%'
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SHIPPING.ORDER_NO >= /*condition.srhOrderNoFrom*/'%'
/*END*/
/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SHIPPING.ORDER_NO <= /*condition.srhOrderNoTo*/'%'
/*END*/

/*IF (( condition.srhShippingNo != null ) && ( condition.srhShippingNo != "" ))*/
	AND	SHIPPING.SHIPPING_NO LIKE /*condition.srhShippingNo*/'%'
/*END*/

/*IF (( condition.srhShippingStatus != null ) && ( condition.srhShippingStatus != "" ))*/
	/*IF (condition.srhShippingStatus == "0")*/
		AND	SHIPPING.SHIPPING_STATUS IN ('2','3','4','5')
	/*END*/
	/*IF (condition.srhShippingStatus != "0")*/
		AND	SHIPPING.SHIPPING_STATUS = /*condition.srhShippingStatus*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhSlipPublishComp != null ) && ( condition.srhSlipPublishComp != "" ))*/
	/*IF (condition.srhSlipPublishComp == "1")*/
		AND	SHIPPING.SLIP_PUBLISH_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingOrderComp != null ) && ( condition.srhSlipShippingOrderComp != "" ))*/
	/*IF (condition.srhSlipShippingOrderComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_ORDER_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingScheduleComp != null ) && ( condition.srhSlipShippingScheduleComp != "" ))*/
	/*IF (condition.srhSlipShippingScheduleComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_SCHEDULE_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingTagComp != null ) && ( condition.srhSlipShippingTagComp != "" ))*/
	/*IF (condition.srhSlipShippingTagComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_TAG_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingRequestComp != null ) && ( condition.srhSlipShippingRequestComp != "" ))*/
	/*IF (condition.srhSlipShippingRequestComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_REQUEST_COMP = 1
	/*END*/
/*END*/		
/*IF (( condition.srhSlipShippingFareComp != null ) && ( condition.srhSlipShippingFareComp != "" ))*/
	/*IF (condition.srhSlipShippingFareComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_FARE_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhLabelPublish != null ) && ( condition.srhLabelPublish != "" ))*/
	/*IF (condition.srhLabelPublish == "0")*/
			
	/*END*/
	/*IF (condition.srhLabelPublish != "0")*/
		AND	CARRY.LABEL_PUBLISH = /*condition.srhLabelPublish*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhShippingSlipNo != null ) && ( condition.srhShippingSlipNo != "" ))*/
	AND	SHIPPING.SHIPPING_SLIP_NO LIKE /*condition.srhShippingSlipNo*/'%'
/*END*/

/*IF (( condition.srhCarryBarcode != null ) && ( condition.srhCarryBarcode != "" ))*/
	AND	SHIPPING.CARRY_BC LIKE /*condition.srhCarryBarcode*/
/*END*/

ORDER BY
	SHIPPING.SCHEDULED_SHIPPING_DATE ASC
,	SHIPPING.ORDER_NO ASC NULLS FIRST
,	SHIPPING.SHIPPING_NO ASC
,	SHIPPING_DETAIL.SHIPPING_ROW_NO ASC
