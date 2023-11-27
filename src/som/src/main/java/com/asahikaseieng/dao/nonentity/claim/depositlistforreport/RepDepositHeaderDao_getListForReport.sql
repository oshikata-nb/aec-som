/*
 * 
 *
 * entityName=RepDepositHeader
 * packageName=claim.depositlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_DEPOSIT_HEADER

WHERE
	CREDIT_NO IN /*claimNo*/('CRE000000000001')

ORDER BY
	CREDIT_NO ASC


