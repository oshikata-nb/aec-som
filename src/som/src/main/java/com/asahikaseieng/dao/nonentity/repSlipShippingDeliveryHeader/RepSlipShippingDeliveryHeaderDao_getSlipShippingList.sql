/*
 * 
 *
 * entityName=RepSlipShippingDeliveryHeader
 * packageName=repSlipShippingDeliveryHeader
 * methodName=getSlipShippingList
 *
 */
SELECT
	KEY
    ,MAX(SHIPPING_NO) AS SHIPPING_NO
    ,VENDER_CD
    ,VENDER_NAME
    ,DELIVERY_NAME_ALL
    ,DELIVERY_ADDRESS_ALL
    ,DELIVERY_TEL_NO
    ,DELIVERY_CD
    ,MAX(SHIPPING_INSTRUCT_DATE) AS SHIPPING_INSTRUCT_DATE
    ,SCHEDULED_SHIPPING_DATE
    ,MAX(TANTO_CD) AS TANTO_CD
    ,DELIVERY_EXPECTED_DATE
    ,DELIVERY_EXPECTED_TIME
    ,UPPER_LOCATION_CD
    ,LOCATION_NAME
    ,CARRY_CD
    ,MAX(CARRY_NAME1) AS CARRY_NAME1
    ,MAX(CARRY_NAME2) AS CARRY_NAME2
    ,MAX(REFERENCE) AS REFERENCE
    ,CUSTOMER_ORDER_NO
    ,ORDER_NO
    ,MIN(ORDER_ROW_NO) AS ORDER_ROW_NO
    ,MIN(SHIPPING_STATUS) AS SHIPPING_STATUS
    ,MIN(ITEM_CD) AS ITEM_CD
    ,MIN(DELIVERY_COMP) AS DELIVERY_COMP
    ,MIN(SHIPPING_RESULT_DATE) AS SHIPPING_RESULT_DATE
    ,MIN(SHIPPING_SLIP_NO) AS SHIPPING_SLIP_NO
    ,MIN(SHIPPING_SLIP_ROW_NO) AS SHIPPING_SLIP_ROW_NO
    ,MIN(SLIP_PUBLISH_COMP) AS SLIP_PUBLISH_COMP
    ,MIN(SLIP_PUBLISH_DATE) AS SLIP_PUBLISH_DATE
    ,MIN(SLIP_SHIPPING_ORDER_COMP) AS SLIP_SHIPPING_ORDER_COMP
    ,MIN(SLIP_SHIPPING_ORDER_DATE) AS SLIP_SHIPPING_ORDER_DATE
    ,MIN(SLIP_SHIPPING_SCHEDULE_COMP) AS SLIP_SHIPPING_SCHEDULE_COMP
    ,MIN(SLIP_SHIPPING_SCHEDULE_DATE) AS SLIP_SHIPPING_SCHEDULE_DATE
    ,MIN(SLIP_SHIPPING_TAG_COMP) AS SLIP_SHIPPING_TAG_COMP
    ,MIN(SLIP_SHIPPING_TAG_DATE) AS SLIP_SHIPPING_TAG_DATE
    ,MIN(SLIP_SHIPPING_REQUEST_COMP) AS SLIP_SHIPPING_REQUEST_COMP
    ,MIN(SLIP_SHIPPING_REQUEST_DATE) AS SLIP_SHIPPING_REQUEST_DATE
    ,MIN(SLIP_SHIPPING_FARE_COMP) AS SLIP_SHIPPING_FARE_COMP
    ,MIN(SLIP_SHIPPING_FARE_DATE) AS SLIP_SHIPPING_FARE_DATE
    ,MIN(SLIP_SHIPPING_DELIVELY_COMP) AS SLIP_SHIPPING_DELIVELY_COMP
    ,MIN(SLIP_SHIPPING_DELIVELY_DATE) AS SLIP_SHIPPING_DELIVELY_DATE
    ,MIN(SLIP_SHIPPING_NEW_TAG_COMP) AS SLIP_SHIPPING_NEW_TAG_COMP
    ,MIN(SLIP_SHIPPING_NEW_TAG_DATE) AS SLIP_SHIPPING_NEW_TAG_DATE
    ,MIN(SLIP_SHIPPING_POSTAL_COMP) AS SLIP_SHIPPING_POSTAL_COMP
    ,MIN(SLIP_SHIPPING_POSTAL_DATE) AS SLIP_SHIPPING_POSTAL_DATE
    ,MIN(SUGGESTED_DELIVERLIMIT) AS SUGGESTED_DELIVERLIMIT
    ,MIN(CARRY_FARE) AS CARRY_FARE
    ,MIN(SENDING_OFF_NUMBER) AS SENDING_OFF_NUMBER
    ,MIN(INPUT_DATE) AS INPUT_DATE
    ,MIN(INPUTOR_CD) AS INPUTOR_CD
    ,MIN(UPDATE_DATE) AS UPDATE_DATE
    ,MIN(UPDATOR_CD) AS UPDATOR_CD
    ,MIN(FAX_NO) AS FAX_NO
    ,MIN(FAX_OUTPUT) AS FAX_OUTPUT
	,REPOTR_OUTPUT_NUM AS REPOTR_OUTPUT_NUM
	,MAX(PRINT_SUMMERY) AS PRINT_SUMMERY
	,MAX(DELIVERY_SLIP_SUMMERY) AS DELIVERY_SLIP_SUMMERY
	,MAX(ORDER_SUMMERY) AS ORDER_SUMMERY
	,MAX(CARRY_BARCODE) AS CARRY_BARCODE
	,MAX(BC_PUBLISH_DIVISION) AS BC_PUBLISH_DIVISION
	,MAX(BC_HEADER) AS BC_HEADER
	,MAX(BC_FOOTER) AS BC_FOOTER
	,MAX(BC_NUMBER_OF_DIGIT) AS BC_NUMBER_OF_DIGIT
	,MAX(BC_CHECKDIGIT_START) AS BC_CHECKDIGIT_START
	,MAX(BC_CHECKDIGIT_END) AS BC_CHECKDIGIT_END
FROM 
	REP_SLIP_SHIPPING_DEL_HEADER 
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
GROUP BY
    KEY
    ,VENDER_CD
    ,VENDER_NAME
    ,DELIVERY_NAME_ALL
    ,DELIVERY_ADDRESS_ALL
    ,DELIVERY_TEL_NO
    ,DELIVERY_CD
    ,SCHEDULED_SHIPPING_DATE
    ,DELIVERY_EXPECTED_DATE
    ,DELIVERY_EXPECTED_TIME
    ,UPPER_LOCATION_CD
    ,LOCATION_NAME
    ,CARRY_CD
    ,CUSTOMER_ORDER_NO
    ,ORDER_NO
    ,REPOTR_OUTPUT_NUM
ORDER BY
	 REPOTR_OUTPUT_NUM
	,CARRY_CD
	,KEY
	,SHIPPING_SLIP_NO ASC