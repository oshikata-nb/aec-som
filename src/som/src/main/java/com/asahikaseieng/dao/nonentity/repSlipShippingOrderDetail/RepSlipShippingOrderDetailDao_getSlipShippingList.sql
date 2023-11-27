/*
 *
 *
 * entityName=RepSlipShippingOrderDetail
 * packageName=repSlipShippingOrderDetail
 * methodName=getSlipShippingList
 *
 */
SELECT 
	*
FROM 
	REP_SLIP_SHIPPING_ORDER_DETAIL
WHERE 
	SHIPPING_SLIP_NO IN /*slipShippingNo*/('')
AND	SHIPPING_NO IN /*shippingNo*/('')
ORDER BY REPOTR_OUTPUT_NUM,CARRY_CD,SHIPPING_SLIP_NO,ORDER_NO,ORDER_ROW_NO,SHIPPING_NO ASC


