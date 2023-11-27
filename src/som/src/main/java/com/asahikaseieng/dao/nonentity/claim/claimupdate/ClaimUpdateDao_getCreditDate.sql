/*
 * 請求更新処理 請求先オートコンプでの請求締め日取得用SQL
 *
 * entityName=ClaimUpdate
 * packageName=claim.claimupdate
 * methodName=getCreditDate
 *
 */
SELECT 	TO_CHAR(ADD_MONTHS(MAX(CREDIT_DATE), 1), 'YYYYMMDD') AS STR_CREDIT_DATE		--MAX(請求締め日)の翌月
FROM 	CLAIM_HEADER HEADER
,		(
		 SELECT	VENDER_CD
		 ,		MAX(CREDIT_DATE) AS MAX_CREDIT_DATE		--MAX(請求締め日)
		 FROM 	CLAIM_HEADER
		 WHERE	CLAIM_HEADER.VENDER_CD IS NOT NULL
		 /*IF (( venderCd != null ) && ( venderCd != "" ))*/
			AND	CLAIM_HEADER.VENDER_CD = /*venderCd*/'TS0003'
		 /*END*/
		 GROUP BY	VENDER_CD
		) MAX_HEADER
WHERE	HEADER.VENDER_CD = MAX_HEADER.VENDER_CD
AND		HEADER.CREDIT_DATE = MAX_HEADER.MAX_CREDIT_DATE
AND		HEADER.CLAIM_AMOUNT <> 0				-- 今回請求額<>0
