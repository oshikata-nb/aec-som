/**
 * 出荷帳票画面　帳票Ｅｘｃｅｌ処理
 *
 * @author t1344224
 *
 * entityName=SlipShippingListForReport
 * packageName=slipshippinglistforreport
 * methodName=getSearchList
 *
 */
SELECT	
	SHIPPING.SHIPPING_NO
,	SHIPPING.SHIPPING_INSTRUCT_DATE
,	SHIPPING.SCHEDULED_SHIPPING_DATE
,	SHIPPING.TANTO_CD
,	TANTO.TANTO_NM
,	SHIPPING.ORDER_NO
,	SHIPPING.ORDER_ROW_NO
,	SHIPPING.VENDER_DIVISION
,	SHIPPING.VENDER_CD
,	(VENDER.VENDER_NAME1 || VENDER.VENDER_NAME2) AS VENDER_NAME1
,	SHIPPING.DELIVERY_CD
,	(DELIVERY.DELIVERY_NAME1 || DELIVERY.DELIVERY_NAME2) AS DELIVERY_NAME1
,	SHIPPING.SHIPPING_STATUS
,	CASE SHIPPING.SHIPPING_STATUS
	　　WHEN 1 THEN '出荷指図未確定'
		WHEN 2 THEN '出荷指図確定済'
	　　WHEN 3 THEN '指図送信済'
		WHEN 4 THEN '実績受信中'
	　　WHEN 5 THEN '出荷完了'
		ELSE ''
	END AS SHIPPING_STATUS_NAME
,	SHIPPING.ITEM_CD
,	ITEM.ITEM_NAME
,	LOCATION.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD
,	SHIPPING.SUMMERY
,	SHIPPING.DELIVERY_COMP
,	SHIPPING.SHIPPING_RESULT_DATE
,	SHIPPING.SHIPPING_SLIP_NO
,	SHIPPING.SHIPPING_SLIP_ROW_NO
,	SHIPPING.SLIP_PUBLISH_COMP
,	SHIPPING.SLIP_PUBLISH_DATE
,	SHIPPING.SLIP_SHIPPING_ORDER_COMP
,	SHIPPING.SLIP_SHIPPING_ORDER_DATE
,	SHIPPING.SLIP_SHIPPING_SCHEDULE_COMP
,	SHIPPING.SLIP_SHIPPING_SCHEDULE_DATE
,	SHIPPING.SLIP_SHIPPING_TAG_COMP
,	SHIPPING.SLIP_SHIPPING_TAG_DATE
,	SHIPPING.SLIP_SHIPPING_REQUEST_COMP
,	SHIPPING.SLIP_SHIPPING_REQUEST_DATE
,	SHIPPING.SLIP_SHIPPING_FARE_COMP
,	SHIPPING.SLIP_SHIPPING_FARE_DATE
,	SHIPPING.SLIP_SHIPPING_DELIVELY_COMP
,	SHIPPING.SLIP_SHIPPING_DELIVELY_DATE
,	SHIPPING.SLIP_SHIPPING_NEW_TAG_COMP
,	SHIPPING.SLIP_SHIPPING_NEW_TAG_DATE
,	SHIPPING.SLIP_SHIPPING_POSTAL_COMP
,	SHIPPING.SLIP_SHIPPING_POSTAL_DATE
,	SHIPPING.CARRY_CD
,	(CARRY.CARRY_NAME1 || CARRY.CARRY_NAME2) AS CARRY_NAME1
,	CASE CARRY.LABEL_PUBLISH
		WHEN 0 THEN 'なし'
		WHEN 1 THEN '荷札'
		WHEN 2 THEN 'ペリカン荷札'
	END AS CARRY_LABEL_PUBLISH
,	SHIPPING.SUGGESTED_DELIVERLIMIT
,	SHIPPING.CARRY_FARE
,	SHIPPING.SENDING_OFF_NUMBER

,	SHIPPING.CARRY_BC

,	SHIPPING.INPUT_DATE
,	SHIPPING.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	SHIPPING.UPDATE_DATE
,	SHIPPING.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
,	TOP_SDETAIL.ORDER_QTY
,	DELIVERY.SEARCH_KANA

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
,   	(
	     	SELECT
	        	SHIPPING_NO
	     	,	MIN(SHIPPING_ROW_NO) AS SHIPPING_ROW_NO
	     	,  SUM(SHIPPING_INSTRUCTION) AS ORDER_QTY
	     	FROM SHIPPING_DETAIL
	     	GROUP BY
	        	SHIPPING_NO
    	) TOP_SDETAIL
,   	SHIPPING_DETAIL
,   	LOCATION LOCATION

WHERE
	SHIPPING.CARRY_CD = CARRY.CARRY_CD(+)
AND	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL_LOCATION.SHIPPING_NO(+)
AND	SHIPPING.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	SHIPPING.VENDER_CD = VENDER.VENDER_CD(+) 
AND 	VENDER.VENDER_DIVISION(+) = 'TS'
AND	SHIPPING.TANTO_CD = TANTO.TANTO_CD(+)
AND	SHIPPING.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	SHIPPING.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	SHIPPING.ITEM_CD = ITEM.ITEM_CD(+)

AND 	SHIPPING.SHIPPING_NO = TOP_SDETAIL.SHIPPING_NO(+)
AND 	SHIPPING_DETAIL.SHIPPING_NO(+)     = TOP_SDETAIL.SHIPPING_NO
AND 	SHIPPING_DETAIL.SHIPPING_ROW_NO(+) = TOP_SDETAIL.SHIPPING_ROW_NO
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
/*IF (( condition.srhSlipShippingRequestComp != null ) && ( condition.srhSlipShippingRequestComp != "" ))*/
	/*IF (condition.srhSlipShippingRequestComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_FARE_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipDeliveryComp != null ) && ( condition.srhShippingSlipDeliveryComp != "" ))*/
	/*IF (condition.srhShippingSlipDeliveryComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_DELIVELY_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipNewShippingTagComp != null ) && ( condition.srhShippingSlipNewShippingTagComp != "" ))*/
	/*IF (condition.srhShippingSlipNewShippingTagComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_NEW_TAG_COMP = 1
	/*END*/
/*END*/
/*IF (( condition.srhShippingSlipPostalComp != null ) && ( condition.srhShippingSlipPostalComp != "" ))*/
	/*IF (condition.srhShippingSlipPostalComp == "1")*/
		AND	SHIPPING.SLIP_SHIPPING_POSTAL_COMP = 1
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
