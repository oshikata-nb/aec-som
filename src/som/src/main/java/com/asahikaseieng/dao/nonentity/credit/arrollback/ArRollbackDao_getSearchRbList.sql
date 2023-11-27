/*
 * 売掛ロールバック処理 ロールバックデータ存在チェック用SQL
 *
 * entityName=ArRollback
 * packageName=credit.arrollback
 * methodName=getSearchRbList
 *
 */
SELECT 	DEPOSIT_HEADER.ORGANIZATION_CD
,		DEPOSIT_HEADER.CREDIT_DATE
FROM 	DEPOSIT_HEADER
WHERE	DEPOSIT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	DEPOSIT_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
AND		DEPOSIT_HEADER.CREDIT_DATE = /*cleditDate*/
