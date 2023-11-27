/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 出荷帳票検索画面一覧用SQL
 *
 * @author tosco
 *
 * entityName=SlipShippingList
 * packageName=slipshippingList
 * methodName=getSearchList
 *
 */
SELECT	SHIPPING.SHIPPING_NO
,	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD as UPPER_LOCATION_CD
,	SHIPPING.SHIPPING_SLIP_NO
,	SHIPPING.ORDER_NO
,	SHIPPING.SCHEDULED_SHIPPING_DATE
,	SHIPPING.SHIPPING_STATUS
,	NVL(SHIPPING.SLIP_PUBLISH_COMP,0) AS SLIP_PUBLISH_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_ORDER_COMP,0) AS SLIP_SHIPPING_ORDER_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_SCHEDULE_COMP,0) AS SLIP_SHIPPING_SCHEDULE_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_TAG_COMP,0) AS SLIP_SHIPPING_TAG_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_REQUEST_COMP,0) AS SLIP_SHIPPING_REQUEST_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_FARE_COMP,0) AS SLIP_SHIPPING_FARE_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_DELIVELY_COMP,0) AS SLIP_SHIPPING_DELIVELY_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_NEW_TAG_COMP,0) AS SLIP_SHIPPING_NEW_TAG_COMP
,	NVL(SHIPPING.SLIP_SHIPPING_POSTAL_COMP,0) AS SLIP_SHIPPING_POSTAL_COMP
,	SHIPPING.UPDATE_DATE
,	CARRY.CARRY_CD
,	CARRY.CARRY_NAME1
,	CARRY.LABEL_PUBLISH
,	MAX_ITEM.ITEM_NAME
,	DELIVERY.SEARCH_KANA
,	SHIPPING_DETAIL_ORDER_QTY.ORDER_QTY

FROM
	SHIPPING
,	CARRY
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
,	VALID_ITEM_QUEUE MAX_ITEM
,	DELIVERY
,	(SELECT SHIPPING_NO,SUM(SHIPPING_INSTRUCTION) AS ORDER_QTY FROM SHIPPING_DETAIL GROUP BY SHIPPING_NO) SHIPPING_DETAIL_ORDER_QTY

WHERE
	SHIPPING.CARRY_CD = CARRY.CARRY_CD(+)
AND	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL_LOCATION.SHIPPING_NO(+)
AND	MAX_ITEM.ITEM_CD(+) = SHIPPING.ITEM_CD
AND	SHIPPING.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL_ORDER_QTY.SHIPPING_NO(+)

/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
	AND	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD = /*condition.srhLocationCd*/
/*END*/

/*IF (( condition.srhCarryCd != null ) && ( condition.srhCarryCd != "" ))*/
	/*IF (condition.srhCarryCd == "0")*/
			
	/*END*/
	/*IF (condition.srhCarryCd != "0")*/
		AND	SHIPPING.CARRY_CD = /*condition.srhCarryCd*/
	/*END*/
/*END*/

/*IF (( condition.srhScheduledShippingDateFrom != null ) && ( condition.srhScheduledShippingDateFrom != "" ))*/
	AND	SHIPPING.SCHEDULED_SHIPPING_DATE >= /*condition.srhScheduledShippingDateFrom*/
/*END*/
/*IF (( condition.srhScheduledShippingDateTo != null ) && ( condition.srhScheduledShippingDateTo != "" ))*/
	AND	SHIPPING.SCHEDULED_SHIPPING_DATE <= /*condition.srhScheduledShippingDateTo*/
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SHIPPING.ORDER_NO >= /*condition.srhOrderNoFrom*/
/*END*/
/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SHIPPING.ORDER_NO <= /*condition.srhOrderNoTo*/
/*END*/

/*IF (( condition.srhShippingNo != null ) && ( condition.srhShippingNo != "" ))*/
	AND	SHIPPING.SHIPPING_NO LIKE /*condition.srhShippingNo*/
/*END*/

/*IF (( condition.srhShippingStatus != null ) && ( condition.srhShippingStatus != "" ))*/
	/*IF (condition.srhShippingStatus == "0")*/
		AND	SHIPPING.SHIPPING_STATUS IN ('2','3','4','5')
	/*END*/
	/*IF (condition.srhShippingStatus != "0")*/
		AND	SHIPPING.SHIPPING_STATUS = /*condition.srhShippingStatus*/
	/*END*/
/*END*/

/*IF (( condition.srhSlipPublishComp != null ) && ( condition.srhSlipPublishComp != "" ))*/
	/*IF (condition.srhSlipPublishComp == "1")*/
		AND	NVL(SHIPPING.SLIP_PUBLISH_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingOrderComp != null ) && ( condition.srhSlipShippingOrderComp != "" ))*/
	/*IF (condition.srhSlipShippingOrderComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_ORDER_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingScheduleComp != null ) && ( condition.srhSlipShippingScheduleComp != "" ))*/
	/*IF (condition.srhSlipShippingScheduleComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_SCHEDULE_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingTagComp != null ) && ( condition.srhSlipShippingTagComp != "" ))*/
	/*IF (condition.srhSlipShippingTagComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_TAG_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingRequestComp != null ) && ( condition.srhSlipShippingRequestComp != "" ))*/
	/*IF (condition.srhSlipShippingRequestComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_REQUEST_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhSlipShippingFareComp != null ) && ( condition.srhSlipShippingFareComp != "" ))*/
	/*IF (condition.srhSlipShippingFareComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_FARE_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipDeliveryComp != null ) && ( condition.srhShippingSlipDeliveryComp != "" ))*/
	/*IF (condition.srhShippingSlipDeliveryComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_DELIVELY_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipNewShippingTagComp != null ) && ( condition.srhShippingSlipNewShippingTagComp != "" ))*/
	/*IF (condition.srhShippingSlipNewShippingTagComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_NEW_TAG_COMP,0) = 0
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipPostalComp != null ) && ( condition.srhShippingSlipPostalComp != "" ))*/
	/*IF (condition.srhShippingSlipPostalComp == "1")*/
		AND	NVL(SHIPPING.SLIP_SHIPPING_POSTAL_COMP,0) = 0
	/*END*/
/*END*/

/*IF (( condition.srhLabelPublish != null ) && ( condition.srhLabelPublish != "" ))*/
	/*IF (condition.srhLabelPublish == "0")*/
			
	/*END*/
	/*IF (condition.srhLabelPublish != "0")*/
		AND	CARRY.LABEL_PUBLISH = /*condition.srhLabelPublish*/
	/*END*/
/*END*/

/*IF (( condition.srhShippingSlipNo != null ) && ( condition.srhShippingSlipNo != "" ))*/
	AND	SHIPPING.SHIPPING_SLIP_NO LIKE /*condition.srhShippingSlipNo*/
/*END*/

/*IF (( condition.srhCarryBarcode != null ) && ( condition.srhCarryBarcode != "" ))*/
	AND	SHIPPING.CARRY_BC LIKE /*condition.srhCarryBarcode*/
/*END*/


ORDER BY
	SHIPPING.SCHEDULED_SHIPPING_DATE ASC
,	SHIPPING.ORDER_NO ASC NULLS FIRST
,	SHIPPING.SHIPPING_NO ASC
