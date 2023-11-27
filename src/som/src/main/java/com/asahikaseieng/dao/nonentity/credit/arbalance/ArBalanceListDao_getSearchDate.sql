/*
 * 売掛残高一覧用SQL
 *
 * entityName=ArBalanceList
 * packageName=credit.arbalance
 * methodName=getSearchDate
 *
 */
SELECT 	TO_CHAR(MAX(UNION_DEPOSIT_HEADER.CREDIT_DATE), 'YYYYMM') AS CLOSING_MONTH
		--MAX(売掛締め日)の年月

FROM 	(
		 (SELECT * FROM DEPOSIT_HEADER)
		 UNION ALL
		 (SELECT * FROM TEMPORARY_DEPOSIT_HEADER)
		) UNION_DEPOSIT_HEADER

WHERE	UNION_DEPOSIT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( sectionCd != null ) && ( sectionCd != "" ))*/
	AND	UNION_DEPOSIT_HEADER.ORGANIZATION_CD = /*sectionCd*/'SC00001'
/*END*/
 