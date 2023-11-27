/*
 * 
 *
 * entityName=RepPaymentInform
 * packageName=payment.paymentlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_PAYMENT_INFORM

WHERE
	SLIP_NO IN /*slipNo*/('SIH000000000437')

ORDER BY
	SLIP_NO ASC


