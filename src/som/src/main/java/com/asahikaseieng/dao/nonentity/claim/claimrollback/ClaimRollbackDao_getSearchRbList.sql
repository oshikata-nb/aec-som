/*
 * 請求更新ロールバック処理 ロールバックデータ存在チェック用SQL
 *
 * entityName=ClaimRollback
 * packageName=claim.updaterollback
 * methodName=getSearchRbList
 *
 */
SELECT 	CLAIM_HEADER.ORGANIZATION_CD
,		CLAIM_HEADER.VENDER_CD
,		CLAIM_HEADER.CREDIT_DATE
FROM 	CLAIM_HEADER
WHERE	CLAIM_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	CLAIM_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	CLAIM_HEADER.VENDER_CD = /*venderCd*/
/*END*/
AND		CLAIM_HEADER.CREDIT_DATE = /*cleditDate*/
