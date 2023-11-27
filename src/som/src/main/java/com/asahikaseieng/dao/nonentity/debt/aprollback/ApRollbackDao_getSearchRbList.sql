/*
 * 買掛ロールバック処理 ロールバックデータ存在チェック用SQL
 *
 * entityName=ApRollback
 * packageName=debt.aprollback
 * methodName=getSearchRbList
 *
 */
SELECT 	PAYABLE_HEADER.ORGANIZATION_CD
,		PAYABLE_HEADER.PAYABLE_DATE
FROM 	PAYABLE_HEADER
WHERE	PAYABLE_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	PAYABLE_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
AND		PAYABLE_HEADER.PAYABLE_DATE = /*payableDate*/
