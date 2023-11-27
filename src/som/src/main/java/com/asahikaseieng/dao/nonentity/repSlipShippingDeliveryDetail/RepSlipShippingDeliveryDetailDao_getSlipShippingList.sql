/*
 * 
 *
 * entityName=RepSlipShippingDeliveryDetail
 * packageName=repSlipShippingDeliveryDetail
 * methodName=getSlipShippingList
 *
 */
SELECT DISTINCT
	*
FROM 
	REP_SLIP_SHIPPING_DEL_DETAIL
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
ORDER BY
	KEY
	,REPOTR_OUTPUT_NUM
	,CARRY_CD
	,SHIPPING_SLIP_NO
	,ORDER_NO
	,ORDER_ROW_NO
	,SHIPPING_NO ASC


