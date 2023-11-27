/*
 * 
 *
 * entityName=RepPaymentHeader
 * packageName=payment.paymentlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_PAYMENT_HEADER

WHERE
	SLIP_NO IN /*slipNo*/('CRE000000000001')

ORDER BY
	SLIP_NO ASC


