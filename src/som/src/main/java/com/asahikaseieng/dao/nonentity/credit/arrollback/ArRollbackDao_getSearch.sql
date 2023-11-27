/*
 * 売掛ロールバック処理 初期表示用SQL
 *
 * entityName=ArRollback
 * packageName=credit.arrollback
 * methodName=getSearch
 *
 */
SELECT 	TO_CHAR(MAX(CREDIT_DATE), 'YYYYMM') AS CLOSING_MONTH		--MAX(売掛締め日)の年月
FROM 	DEPOSIT_HEADER
WHERE	DEPOSIT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	DEPOSIT_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
