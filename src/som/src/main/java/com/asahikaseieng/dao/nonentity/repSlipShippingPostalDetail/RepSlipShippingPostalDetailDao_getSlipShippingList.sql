/*
 * 
 *
 * entityName=RepSlipShippingPostalDetail
 * packageName=repSlipShippingPostalDetail
 * methodName=getSlipShippingList
 *
 */
SELECT DISTINCT
	*
FROM 
	REP_SLIP_SHIPPING_POS_DETAIL
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
AND
	POSTAL_CLIENT_CD = /*postalClientCd*/0
ORDER BY
	REPOTR_OUTPUT_NUM
	,CARRY_CD
	,SHIPPING_SLIP_NO
	,ORDER_NO
	,ORDER_ROW_NO
	,SHIPPING_NO ASC




