/*
 * 
 *
 * entityName=RepSlipShippingPerican
 * packageName=slipshippinglistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM 
	REP_SLIP_SHIPPING_PERICAN
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
ORDER BY
	REPOTR_OUTPUT_NUM
,	CARRY_CD
,	SHIPPING_SLIP_NO NULLS FIRST


