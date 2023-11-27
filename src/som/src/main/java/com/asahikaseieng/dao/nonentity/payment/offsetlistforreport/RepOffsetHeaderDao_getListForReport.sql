/*
 * 
 *
 * entityName=RepOffsetHeader
 * packageName=payment.offsetlistforreport
 * methodName=getListForReport
 *
 */
SELECT
	*
FROM
	REP_OFFSET_HEADER

WHERE
	OFFSET_NO IN /*offsetNo*/('CRE000000000001')

ORDER BY
	OFFSET_NO ASC


