/*
 *
 *
 * entityName=RepBillIssueHeader
 * packageName=claim.billissuelistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_TEMPORARY_BILLISSUE_HEADER

WHERE
	CLAIM_NO IN /*claimNo*/('HSE000000000007')

ORDER BY
	VENDER_CD, CREDIT_DATE ASC




