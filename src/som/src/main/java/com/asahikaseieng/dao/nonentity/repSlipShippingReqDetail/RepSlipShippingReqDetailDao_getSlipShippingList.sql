/*
 * 
 *
 * entityName=RepSlipShippingReqDetail
 * packageName=repSlipShippingReqDetail
 * methodName=getSlipShippingList
 *
 */
SELECT
	*
FROM 
	REP_SLIP_SHIPPING_REQ_DETAIL
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
ORDER BY
	DELIVERY_EXPECTED_DATE
,	ORDER_NO	
,	LOCATION_CD
,	DELIVERY_CD
,	ITEM_CD ASC


