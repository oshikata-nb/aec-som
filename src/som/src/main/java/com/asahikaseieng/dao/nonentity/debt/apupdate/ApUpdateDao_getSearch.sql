/*
 * 買掛更新処理 初期表示用SQL
 *
 * entityName=ApUpdate
 * packageName=debt.apupdate
 * methodName=getSearch
 *
 */
SELECT 	TO_CHAR(ADD_MONTHS(MAX(PAYABLE_DATE), 1), 'YYYYMM') AS CLOSING_MONTH
		--MAX(買掛締め日)の翌年月
FROM 	PAYABLE_HEADER
WHERE	PAYABLE_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	PAYABLE_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
