/*
 * 
 *
 * entityName=RepSlipShippingSchHeader
 * packageName=slipshippinglistforreport
 * methodName=getListForReport
 *
 */
SELECT DISTINCT
	KEY
,	SCHEDULED_SHIPPING_DATE
,	LOCATION_CD
,	LOCATION_NAME

FROM 
	REP_SLIP_SHIPPING_SCH_HEADER 
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')
ORDER BY
	SCHEDULED_SHIPPING_DATE
,	LOCATION_CD ASC


