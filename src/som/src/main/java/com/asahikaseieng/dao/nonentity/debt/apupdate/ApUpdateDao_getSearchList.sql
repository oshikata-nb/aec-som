/*
 * 買掛更新処理 存在チェック用SQL
 *
 * entityName=ApUpdate
 * packageName=debt.apupdate
 * methodName=getSearch
 *
 */
SELECT 	PAYABLE_HEADER.ORGANIZATION_CD
,		PAYABLE_HEADER.PAYABLE_DATE
FROM 	PAYABLE_HEADER
WHERE	PAYABLE_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	PAYABLE_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
AND		PAYABLE_HEADER.PAYABLE_DATE >= /*payableDate*/
