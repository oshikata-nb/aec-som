/*
 * 消込入力一覧用SQL
 *
 * entityName=EraserList
 * packageName=claim.eraser
 * methodName=getSearchList
 *
 */
SELECT  VENDER_CD				--請求先コード
,		VENDER_NAME				--請求先名称
,		ERASER_DATE				--消込日付
,		ERASER_NO				--消込番号
,		ERASER_BALANCE_AMOUNT	--消込額合計
,		ERASER_AMOUNT			--消込金額
,		CREDIT_ERASER_AMOUNT	--入金消込残
,		APPROVAL_STATUS			--承認ステータス
FROM
	(
	--データ区分='消込結果'
	/*IF ( condition.srhDataCheckEraser )*/
		SELECT 	ERASER_HEADER.VENDER_CD						--請求先コード
		,		VENDER.VENDER_NAME							--請求先名称
		,		ERASER_HEADER.ERASER_DATE					--消込日付
		,		ERASER_HEADER.ERASER_NO						--消込番号
		,		NULL AS ERASER_BALANCE_AMOUNT				--消込残合計
		,		NVL(ERASER_HEADER.ERASER_AMOUNT,0) AS ERASER_AMOUNT					--消込金額
		,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--入金消込残
		,		ERASER.APPROVAL_STATUS						--承認ステータス
		FROM 	ERASER_HEADER ERASER_HEADER
				INNER JOIN
					(SELECT ERASER_NO
					 ,		MIN(APPROVAL_STATUS) AS APPROVAL_STATUS				--承認ステータス
					 FROM ERASER
					 GROUP BY ERASER_NO
					) ERASER
					ON  ERASER_HEADER.ERASER_NO = ERASER.ERASER_NO
				LEFT JOIN
					(SELECT SECTION_CD
					 ,		CUSTOMER_CD
					 ,		SUM(NVL(CREDIT_ERASER_AMOUNT,0)) AS CREDIT_ERASER_AMOUNT	--入金消込残
					 FROM CREDIT
					 WHERE (CREDIT.DATA_TOTAL_DIVISION = '1'
							  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
					 AND   CREDIT.CLAIM_TARGET_DIVISION = '1'					--請求対象
					 AND   CREDIT.CLAIM_UPDATE_DIVISION = '1'					--請求更新フラグ
					 AND   CREDIT.INVOICE_UPDATE_DATE IS NOT NULL				--請求締め日
					 AND   CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
					 GROUP BY SECTION_CD
					 ,		  CUSTOMER_CD
					) CREDIT
					ON  ERASER_HEADER.SECTION_CD = CREDIT.SECTION_CD
					AND ERASER_HEADER.VENDER_CD = CREDIT.CUSTOMER_CD
				LEFT JOIN
					BUMON
					ON ERASER_HEADER.SECTION_CD = BUMON.SECTION_CD
				LEFT JOIN
					LOGIN
					ON ERASER_HEADER.INPUTOR_CD = LOGIN.TANTO_CD
				LEFT JOIN
					VENDER
					ON  ERASER_HEADER.VENDER_CD = VENDER.VENDER_CD
					AND VENDER.VENDER_DIVISION = 'TS'							--得意先
		WHERE	ERASER_HEADER.SECTION_CD IS NOT NULL
		/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
			AND	ERASER_HEADER.SECTION_CD LIKE /*condition.srhSectionCd*/
		/*END*/

		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	ERASER_HEADER.INPUTOR_CD LIKE /*condition.srhTantoCd*/
		/*END*/

		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	ERASER_HEADER.VENDER_CD LIKE /*condition.srhVenderCd*/
		/*END*/

		/*IF (( condition.eraserDateFrom != null ) && ( condition.eraserDateFrom != "" ))*/
			AND	ERASER_HEADER.ERASER_DATE >= /*condition.eraserDateFrom*/
		/*END*/

		/*IF (( condition.eraserDateTo != null ) && ( condition.eraserDateTo != "" ))*/
			AND	ERASER_HEADER.ERASER_DATE <= /*condition.eraserDateTo*/
		/*END*/

		/*IF (( condition.srhOutputDivision != null )
				 && ( condition.srhOutputDivision == "1" || condition.srhOutputDivision == "2" || condition.srhOutputDivision == "3" ))*/
			AND	ERASER.APPROVAL_STATUS = /*condition.srhOutputDivision*/		--出力区分='1:入力中'or'2:依頼中'or'3:承認済'
		/*END*/

		/*IF (( condition.srhSectionName != null ) && ( condition.srhSectionName != "" ))*/
			AND	BUMON.SECTION_NAME LIKE /*condition.srhSectionName*/
		/*END*/

		/*IF (( condition.srhTantoNm != null ) && ( condition.srhTantoNm != "" ))*/
			AND	LOGIN.TANTO_NM LIKE /*condition.srhTantoNm*/
		/*END*/
		
		/*IF (( condition.srhVenderName != null ) && ( condition.srhVenderName != "" ))*/
			AND	VENDER.VENDER_NAME LIKE /*condition.srhVenderName*/
		/*END*/
	/*END*/
	/*IF ( condition.srhDataCheckNew && condition.srhDataCheckEraser )*/
	 UNION
	/*END*/
	--データ区分='新規消込'
	/*IF ( condition.srhDataCheckNew )*/
		--請求データ
		SELECT 	CLAIM_HEADER.CUSTOMER_CD AS VENDER_CD						--請求先コード
		,		VENDER.VENDER_NAME											--請求先名称
		,		NULL AS ERASER_DATE											--消込日付
		,		NULL AS ERASER_NO											--消込番号
		,		NVL(CLAIM_HEADER.AMOUNT,0) AS ERASER_BALANCE_AMOUNT			--消込残
		,		NULL AS ERASER_AMOUNT										--消込金額
		,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT	--入金消込残
		,		NULL AS APPROVAL_STATUS										--承認ステータス
		FROM 	(
					SELECT MIN(ERASE_DATA.SECTION_CD) AS SECTION_CD
					      ,MIN(ERASE_DATA.CUSTOMER_CD) AS CUSTOMER_CD
					      ,SUM(ERASE_DATA.AMOUNT) AS AMOUNT
					      ,MIN(ERASE_DATA.INPUTOR_CD) AS INPUTOR_CD
					FROM (  
							SELECT
								 SALES.SECTION_CD AS SECTION_CD					--部門コード
								,SALES.INVOICE_CD AS CUSTOMER_CD				--請求先コード
								,SALES.SALES_AMOUNT+SALES.TAX_AMOUNT AS AMOUNT	--今回売上額
								,CLAIM_HEADER.INPUTOR_CD AS INPUTOR_CD			-- 請求データ登録者
							FROM  SALES SALES
							     ,CLAIM_HEADER 	
						    WHERE
							      SALES.ERASER_COMPLETE_DIVISION 	= '0'	--消込未完了 
							AND   SALES.CLAIM_UPDATE_DIVISION       = '1'	--請求更新済
							AND   SALES.CLAIM_TARGET_DIVISION       = '1'	--請求対象 
							AND   SALES.CLAIM_NO                    = CLAIM_HEADER.CLAIM_NO
							UNION ALL
							SELECT
								 CREDIT.SECTION_CD AS SECTION_CD		--部門コード
								,CREDIT.CUSTOMER_CD AS CUSTOMER_CD	    --請求先コード
								,CREDIT.CREDIT_AMOUNT*-1 AS AMOUNT 		--今回入金額(ﾏｲﾅｽ)
								,CLAIM_HEADER.INPUTOR_CD AS INPUTOR_CD	-- 請求データ登録者
							FROM  CREDIT CREDIT						    --入金トラン
							     ,CLAIM_HEADER 	
						    WHERE
								  CREDIT.DATA_TOTAL_DIVISION        = '2'   --データ集計区分(2:相殺)
							AND	  CREDIT.ERASER_COMPLETE_DIVISION   = '0'   --消込未完了 
							AND   CREDIT.CLAIM_UPDATE_DIVISION      = '1'   --請求更新済
							AND   CREDIT.CLAIM_TARGET_DIVISION      = '1'	--請求対象 
							AND   CREDIT.CLAIM_NO                   = CLAIM_HEADER.CLAIM_NO
							UNION ALL
							SELECT
								 OFFSET_GROUP_DATA.SECTION_CD AS SECTION_CD		--部門コード
								,OFFSET_GROUP_DATA.VENDER_CD AS CUSTOMER_CD		--請求先コード
								,OFFSET_GROUP_DATA.DEPOSIT_OFFSET_AMOUNT*-1 AS AMOUNT --売掛相殺額(ﾏｲﾅｽ)
								,CLAIM_HEADER.INPUTOR_CD AS INPUTOR_CD			--請求データ登録者
							FROM  OFFSET_GROUP_DATA OFFSET_GROUP_DATA			--相殺グループトラン
							     ,CLAIM_HEADER 	
						    WHERE
								  OFFSET_GROUP_DATA.ERASER_COMPLETE_DIVISION   = '0'	--消込未完了 
							AND   OFFSET_GROUP_DATA.CLAIM_UPDATE_DIVISION      = '1'	--請求更新済
							AND   OFFSET_GROUP_DATA.CLAIM_TARGET_DIVISION      = '1'	--請求対象  
							AND   OFFSET_GROUP_DATA.CLAIM_NO                    = CLAIM_HEADER.CLAIM_NO
						)  ERASE_DATA
						,VENDER
					WHERE ERASE_DATA.CUSTOMER_CD = VENDER.VENDER_CD
					AND   VENDER.VENDER_DIVISION = 'TS'							--得意先
					GROUP BY CUSTOMER_CD
					ORDER BY CUSTOMER_CD
				) CLAIM_HEADER
				LEFT JOIN
					(SELECT SECTION_CD
					 ,		CUSTOMER_CD
					 ,		SUM(NVL(CREDIT_ERASER_AMOUNT,0)) AS CREDIT_ERASER_AMOUNT	--入金消込残
					 FROM CREDIT
					 WHERE (CREDIT.DATA_TOTAL_DIVISION = '1'
							  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
					 AND   CREDIT.CLAIM_TARGET_DIVISION = '1'					--請求対象
					 AND   CREDIT.CLAIM_UPDATE_DIVISION = '1'					--請求更新フラグ
					 AND   CREDIT.INVOICE_UPDATE_DATE IS NOT NULL				--請求締め日
					 AND   CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
					 GROUP BY SECTION_CD
					 ,		  CUSTOMER_CD
					) CREDIT
					ON  CLAIM_HEADER.SECTION_CD = CREDIT.SECTION_CD
					AND CLAIM_HEADER.CUSTOMER_CD = CREDIT.CUSTOMER_CD
				LEFT JOIN
					BUMON
					ON CLAIM_HEADER.SECTION_CD = BUMON.SECTION_CD
				LEFT JOIN
					LOGIN
					ON CLAIM_HEADER.INPUTOR_CD = LOGIN.TANTO_CD
				LEFT JOIN
					VENDER
					ON CLAIM_HEADER.CUSTOMER_CD = VENDER.VENDER_CD
					AND VENDER.VENDER_DIVISION = 'TS'							--得意先
		WHERE	CLAIM_HEADER.SECTION_CD IS NOT NULL
		/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
			AND	CLAIM_HEADER.SECTION_CD LIKE /*condition.srhSectionCd*/
		/*END*/

		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	CLAIM_HEADER.INPUTOR_CD LIKE /*condition.srhTantoCd*/
		/*END*/

		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	CLAIM_HEADER.CUSTOMER_CD LIKE /*condition.srhVenderCd*/
		/*END*/

		/*IF (( condition.srhSectionName != null ) && ( condition.srhSectionName != "" ))*/
			AND	BUMON.SECTION_NAME LIKE /*condition.srhSectionName*/
		/*END*/

		/*IF (( condition.srhTantoNm != null ) && ( condition.srhTantoNm != "" ))*/
			AND	LOGIN.TANTO_NM LIKE /*condition.srhTantoNm*/
		/*END*/
		
		/*IF (( condition.srhVenderName != null ) && ( condition.srhVenderName != "" ))*/
			AND	VENDER.VENDER_NAME LIKE /*condition.srhVenderName*/
		/*END*/
	/*END*/
	)
ORDER BY VENDER_CD
,		 ERASER_NO

