/*
 * 
 *
 * entityName=RepSlipShippingFareDetail
 * packageName=repSlipShippingFareDetail
 * methodName=getSlipShippingList
 *
 */
SELECT 
	*
FROM 
	REP_SLIP_SHIPPING_FARE_DETAIL
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
ORDER BY 
	REPOTR_OUTPUT_NUM
	,CARRY_CD
	,KEY
	,SHIPPING_SLIP_NO
	,ORDER_NO
	,ORDER_ROW_NO
	,SHIPPING_NO ASC


