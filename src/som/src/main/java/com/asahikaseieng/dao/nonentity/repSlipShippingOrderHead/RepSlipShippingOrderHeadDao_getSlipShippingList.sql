/*
 * 
 *
 * entityName=RepSlipShippingOrderHead
 * packageName=repSlipShippingOrderHead
 * methodName=getSlipShippingList
 *
 */
SELECT 
	* 
FROM 
	REP_SLIP_SHIPPING_ORDER_HEADER 
WHERE 
	SHIPPING_SLIP_NO IN /*shippingOrderNo*/('1')
ORDER BY REPOTR_OUTPUT_NUM,CARRY_CD, SHIPPING_SLIP_NO ASC

