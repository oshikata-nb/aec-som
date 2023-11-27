/*
 * 請求更新ロールバック処理 初期表示用SQL
 *
 * entityName=ClaimRollback
 * packageName=claim.updaterollback
 * methodName=getSearch
 *
 */
SELECT 	TO_CHAR(MAX(CREDIT_DATE), 'YYYYMMDD') AS STR_CREDIT_DATE	--MAX(売掛締め日)
FROM 	CLAIM_HEADER
WHERE	CLAIM_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	CLAIM_HEADER.VENDER_CD = /*venderCd*/
/*END*/
