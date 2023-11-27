/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
*/

/**
 * 出荷実績入力－出荷詳細データ取得SQL
 *
 * @author tosco
 *
 * entityName=ShippingResultDetailEntity
 * packageName=shippingresult
 * methodName=getEntity
 *
 */
SELECT
    SHEAD.SHIPPING_NO
,   SHEAD.SHIPPING_INSTRUCT_DATE
,   SHEAD.SCHEDULED_SHIPPING_DATE
,   SHEAD.TANTO_CD
,   SHEAD.ORDER_NO
,   SHEAD.ORDER_ROW_NO
,   SHEAD.VENDER_DIVISION
,   SHEAD.VENDER_CD
,   SHEAD.DELIVERY_CD
,   SHEAD.SHIPPING_STATUS
,   SHEAD.ITEM_CD
,   SHEAD.SUMMERY
,   SHEAD.DELIVERY_COMP
,   SHEAD.SHIPPING_RESULT_DATE
,   SHEAD.SHIPPING_SLIP_NO
,   SHEAD.SHIPPING_SLIP_ROW_NO
,   SHEAD.SLIP_PUBLISH_COMP
,   SHEAD.SLIP_PUBLISH_DATE
,   SHEAD.SLIP_SHIPPING_ORDER_COMP
,   SHEAD.SLIP_SHIPPING_ORDER_DATE
,   SHEAD.SLIP_SHIPPING_SCHEDULE_COMP
,   SHEAD.SLIP_SHIPPING_SCHEDULE_DATE
,   SHEAD.SLIP_SHIPPING_TAG_COMP
,   SHEAD.SLIP_SHIPPING_TAG_DATE
,   SHEAD.SLIP_SHIPPING_REQUEST_COMP
,   SHEAD.SLIP_SHIPPING_REQUEST_DATE
,   SHEAD.CARRY_CD
,   SHEAD.SUGGESTED_DELIVERLIMIT
,   SHEAD.CARRY_FARE
,   SHEAD.SENDING_OFF_NUMBER
,   SHEAD.INPUT_DATE
,   SHEAD.INPUTOR_CD
,   SHEAD.UPDATE_DATE
,   SHEAD.UPDATOR_CD
,   OHEAD.BALANCE_CD
,   OHEAD.DELIVERY_ADDRESS
,   OHEAD.UPDATE_DATE UPDATE_DATE_ORDER_HEAD
,	OHEAD.DELIVERY_EXPECTED_DATE
,   ODETAIL.ORDER_QTY
,   ODETAIL.MATSS
,   ODETAIL.UPDATE_DATE UPDATE_DATE_ORDER_DETAIL
,	DELIVERY.DELIVERY_NAME1
,	DELIVERY.SEARCH_KANA
,   ITEM.ITEM_NAME
,   ITEM.OTHER_COMPANY_CD1
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
,   ITEM.STYLE_OF_PACKING
,   CARRY.CARRY_NAME1
,   CARRY.CARRY_NAME2
,   DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 AS DELIVERY_ALL_ADDRESS
FROM
    SHIPPING SHEAD
,   ORDER_HEAD OHEAD
,   ORDER_DETAIL ODETAIL
,   DELIVERY
,   ITEM
,   CARRY
WHERE
	SHEAD.SHIPPING_NO = /*shippingNo*/
AND SHEAD.ORDER_NO = OHEAD.ORDER_NO(+)
AND SHEAD.ORDER_NO = ODETAIL.ORDER_NO(+)
AND SHEAD.ORDER_ROW_NO = ODETAIL.ROW_NO(+)
AND	SHEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND ITEM.ITEM_CD(+) = SHEAD.ITEM_CD
AND SHEAD.CARRY_CD = CARRY.CARRY_CD(+)
