/*
 * 売掛更新処理 存在チェック用SQL
 *
 * entityName=ArUpdate
 * packageName=credit.arupdate
 * methodName=getSearch
 *
 */
SELECT 	DEPOSIT_HEADER.ORGANIZATION_CD
,		DEPOSIT_HEADER.CREDIT_DATE
FROM 	DEPOSIT_HEADER
WHERE	DEPOSIT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	DEPOSIT_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
AND		DEPOSIT_HEADER.CREDIT_DATE >= /*cleditDate*/
