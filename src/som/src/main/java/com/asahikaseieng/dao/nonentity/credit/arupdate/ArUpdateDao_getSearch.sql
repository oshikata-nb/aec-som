/*
 * 売掛更新処理 初期表示用SQL
 *
 * entityName=ArUpdate
 * packageName=credit.arupdate
 * methodName=getSearch
 *
 */
SELECT 	TO_CHAR(ADD_MONTHS(MAX(CREDIT_DATE), 1), 'YYYYMM') AS CLOSING_MONTH		--MAX(売掛締め日)の翌年月
FROM 	DEPOSIT_HEADER
WHERE	DEPOSIT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	DEPOSIT_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
