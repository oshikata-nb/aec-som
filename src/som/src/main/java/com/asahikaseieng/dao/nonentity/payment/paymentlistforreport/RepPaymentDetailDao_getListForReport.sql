/*
 * 
 *
 * entityName=RepPaymentDetail
 * packageName=payment.paymentlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_PAYMENT_DETAIL

WHERE
	SLIP_NO IN /*slipNo*/('CRE000000000001')

ORDER BY
	SLIP_NO,ROW_NO ASC


