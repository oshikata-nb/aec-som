/*
 * 
 *
 * entityName=RepPaymentPlanInform
 * packageName=paymentplanlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_PAYMENT_PLAN_INFORM

WHERE
	PAYMENT_NO IN /*paymentNo*/('SIH000000001019')

ORDER BY
	PAYMENT_NO ASC


