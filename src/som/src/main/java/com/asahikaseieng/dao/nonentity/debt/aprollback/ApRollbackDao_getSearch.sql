/*
 * 買掛ロールバック処理 初期表示用SQL
 *
 * entityName=ApRollback
 * packageName=debt.aprollback
 * methodName=getSearch
 *
 */
SELECT 	TO_CHAR(MAX(PAYABLE_DATE), 'YYYYMM') AS CLOSING_MONTH		--MAX(買掛締め日)の年月
FROM 	PAYABLE_HEADER
WHERE	PAYABLE_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	PAYABLE_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
