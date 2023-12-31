/*
 * 受注番号単位・出荷番号単位の客先注文番号を取得する。
 *
 * entityName=RepSlipShippingDeliveryHeader
 * packageName=repSlipShippingDeliveryHeader
 * methodName=getCstOrderNo
 *
 */
SELECT
	LISTAGG(FRST_ORDER_DETAIL.CUSTOMER_ORDER_DETAIL_NO,',') WITHIN GROUP(ORDER BY NULL) CST_ORDER_NO
FROM
	SHIPPING
LEFT JOIN FRST_ORDER_DETAIL ON FRST_ORDER_DETAIL.ORDER_NO = SHIPPING.ORDER_NO AND FRST_ORDER_DETAIL.ROW_NO = SHIPPING.ORDER_ROW_NO
WHERE
		SHIPPING.SHIPPING_NO IN /*shippingNo*/('SK000768275','SK000768276')
AND	FRST_ORDER_DETAIL.ORDER_NO = /*orderNo*/''