/*
 * 
 *
 * entityName=RepTegataDetail
 * packageName=payment.paymentlistforreport
 * methodName=getListForReport
 *
 */

SELECT
	*
FROM
	REP_TEGATA_DETAIL
WHERE
	SLIP_NO IN /*slipNo*/('CRE000000000001')

ORDER BY
	BNAIBU_NO
,	PAYMENT_DATE
--,	TEGATA_NO
,	LPAD(TEGATA_NO, 12, '0')

