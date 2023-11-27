/*
 * 
 *
 * entityName=RepDepositDetail
 * packageName=claim.depositlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_DEPOSIT_DETAIL

WHERE
	CREDIT_NO IN /*claimNo*/('CRE000000000001')

ORDER BY
	CREDIT_NO,ROW_NO ASC


