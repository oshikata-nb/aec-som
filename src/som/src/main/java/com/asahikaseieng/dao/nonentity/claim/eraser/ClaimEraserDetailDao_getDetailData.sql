/*
 * 消込入力詳細画面用SQL
 *
 * entityName=EraserDetail
 * packageName=claim.eraser
 * methodName=getDetailData
 *
 */
/*IF (( eraserNo != null ) && ( eraserNo != "" ))*/
	--変更の場合
	SELECT	ERASER_HEADER.ERASER_NO					--消込番号
	,		ERASER_HEADER.SECTION_CD					--部門コード
	,		ERASER_HEADER.SECTION_NAME				--部門名称
	,		ERASER_HEADER.VENDER_CD					--請求先コード
	,		ERASER_HEADER.VENDER_NAME				--請求先名称
	,		ERASER_HEADER.ERASER_CAPA_AMOUNT		--消込可能額
	,		ERASER_HEADER.ERASER_AMOUNT				--消込額
	,		ERASER_HEADER.ERASER_BALANCE_AMOUNT		--消込残合計
	,		NVL(CREDIT_SUM.SUM_CREDIT_AMOUNT,0)	AS SUM_CREDIT_AMOUNT	--入金金額合計
	,		NVL(CREDIT_SUM.SUM_ERASER_AMOUNT,0) AS SUM_ERASER_AMOUNT	--消込額合計
	,		NVL(CREDIT_SUM.SUM_CREDIT_ERASER_AMOUNT,0) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残合計
	,		CREDIT.CREDIT_DATE						--入金日付
	,		CREDIT.CREDIT_NO						--入金番号
	,		CREDIT.ROW_NO							--行番号
	,		CREDIT.NYUKIN_CATE_NAME					--分類名称(入金分類)
	,		CREDIT.CREDIT_AMOUNT					--入金金額
	,		CREDIT.CR_ERASER_AMOUNT					--消込額
	,		CREDIT.CREDIT_ERASER_AMOUNT				--入金消込残
	FROM
		(
			--消込データに紐づく請求ヘッダーデータ
			SELECT 	ERASER_HEADER.ERASER_NO												--消込番号
			,		ERASER_HEADER.SECTION_CD											--部門コード
			,		BUMON.SECTION_NAME													--部門名称
			,		ERASER_HEADER.VENDER_CD												--請求先コード
			,		VENDER.VENDER_NAME													--請求先名称
			,		NVL(ERASER_HEADER.ERASER_AMOUNT,0) AS ERASER_CAPA_AMOUNT			--消込可能額
			,		NVL(ERASER_HEADER.ERASER_AMOUNT,0) AS ERASER_AMOUNT					--消込額
			,		0 AS ERASER_BALANCE_AMOUNT											--消込残合計
			FROM 	ERASER_HEADER ERASER_HEADER
					LEFT JOIN
						BUMON
						ON ERASER_HEADER.SECTION_CD = BUMON.SECTION_CD
					LEFT JOIN
						VENDER
						ON  ERASER_HEADER.VENDER_CD = VENDER.VENDER_CD
						AND VENDER.VENDER_DIVISION = 'TS'							--得意先
			WHERE	ERASER_HEADER.ERASER_NO IS NOT NULL
			AND		ERASER_HEADER.ERASER_NO = /*eraserNo*/'KES0000005'
		) ERASER_HEADER
		LEFT JOIN
			(
				--入金合計
				SELECT 	SECTION_CD													--部門コード
				,		VENDER_CD													--請求先コード
				,		SUM(SUM_CREDIT_AMOUNT) AS SUM_CREDIT_AMOUNT				--入金金額
				,		SUM(SUM_ERASER_AMOUNT) AS SUM_ERASER_AMOUNT				--消込額
				,		SUM(SUM_CREDIT_ERASER_AMOUNT) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残
				FROM
					(
						--消込データに紐づく入金消込残＝０のデータ
						SELECT 	ERASER_HEADER.SECTION_CD											--部門コード
						,		ERASER_HEADER.VENDER_CD												--請求先コード
						,		SUM(NVL(CREDIT.CREDIT_AMOUNT,0)) AS SUM_CREDIT_AMOUNT				--入金金額
						,		SUM(NVL(CREDIT.ERASER_AMOUNT,0)) AS SUM_ERASER_AMOUNT				--消込額
						,		SUM(NVL(CREDIT.CREDIT_ERASER_AMOUNT,0)) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残
						FROM 	ERASER_HEADER ERASER_HEADER
								INNER JOIN
									(SELECT ERASER_NO
									 ,		CREDIT_NO
									 ,		ROW_NO
									 FROM ERASER_HEADER_DETAIL
									) ERASER
									ON  ERASER_HEADER.ERASER_NO = ERASER.ERASER_NO
								INNER JOIN
									CREDIT CREDIT
									ON  ERASER.CREDIT_NO = CREDIT.CREDIT_NO
									AND ERASER.ROW_NO = CREDIT.ROW_NO
									AND (CREDIT.DATA_TOTAL_DIVISION = '1'
											  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
									AND CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
									AND CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
									AND CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
									AND CREDIT.CREDIT_ERASER_AMOUNT = 0							--入金消込残=０
						WHERE	ERASER_HEADER.ERASER_NO IS NOT NULL
						AND		ERASER_HEADER.ERASER_NO = /*eraserNo*/'KES0000005'
						GROUP BY 	ERASER_HEADER.SECTION_CD
						,			ERASER_HEADER.VENDER_CD
						UNION
						--部門・請求先に紐づく入金消込残<>０のデータ
						SELECT 	CREDIT.SECTION_CD													--部門コード
						,		CREDIT.CUSTOMER_CD AS VENDER_CD										--請求先コード
						,		SUM(NVL(CREDIT.CREDIT_AMOUNT,0)) AS SUM_CREDIT_AMOUNT				--入金金額
						,		SUM(NVL(CREDIT.ERASER_AMOUNT,0)) AS SUM_ERASER_AMOUNT				--消込額
						,		SUM(NVL(CREDIT.CREDIT_ERASER_AMOUNT,0)) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残
						FROM 	CREDIT CREDIT
						WHERE	CREDIT.SECTION_CD IS NOT NULL
						AND		CREDIT.SECTION_CD = /*sectionCd*/'SC00001'
						AND		CREDIT.CUSTOMER_CD = /*venderCd*/'TS0001'
						AND		(CREDIT.DATA_TOTAL_DIVISION = '1'
									  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
						AND		CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
						AND		CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
						AND		CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
						AND		CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
						GROUP BY 	CREDIT.SECTION_CD
						,			CREDIT.CUSTOMER_CD
					)
				GROUP BY 	SECTION_CD
				,			VENDER_CD
			) CREDIT_SUM
			ON  ERASER_HEADER.SECTION_CD = CREDIT_SUM.SECTION_CD
			AND ERASER_HEADER.VENDER_CD = CREDIT_SUM.VENDER_CD
		LEFT JOIN
			(
				--入金内訳
				SELECT 	DISTINCT
					 	SECTION_CD							--部門コード
				,		VENDER_CD							--請求先コード
				,		CREDIT_DATE							--入金日付
				,		CREDIT_NO							--入金番号
				,		ROW_NO								--行番号
				,		NYUKIN_CATE_NAME					--分類名称(入金分類)
				,		CREDIT_AMOUNT						--入金金額
				,		CR_ERASER_AMOUNT					--消込額
				,		CREDIT_ERASER_AMOUNT				--入金消込残
				FROM
					(
					--消込データに紐づく入金データ
					SELECT 	CREDIT.SECTION_CD													--部門コード
					,		CREDIT.CUSTOMER_CD AS VENDER_CD										--請求先コード
					, 		CREDIT.CREDIT_DATE													--入金日付
					,		CREDIT.CREDIT_NO													--入金番号
					,		CREDIT.ROW_NO														--行番号
					,		CL2.CATEGORY_NAME AS NYUKIN_CATE_NAME								--分類名称(入金分類)
					,		NVL(CREDIT.CREDIT_AMOUNT,0)	AS CREDIT_AMOUNT						--入金金額
					,		NVL(CREDIT.ERASER_AMOUNT,0) AS CR_ERASER_AMOUNT						--消込額
					,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--入金消込残
					FROM 	ERASER_HEADER ERASER_HEADER
							INNER JOIN
								(SELECT ERASER_NO
								 ,		CREDIT_NO
								 ,		ROW_NO
								 FROM ERASER_HEADER_DETAIL
								) ERASER
								ON  ERASER_HEADER.ERASER_NO = ERASER.ERASER_NO
							INNER JOIN
								CREDIT CREDIT
								ON  ERASER.CREDIT_NO = CREDIT.CREDIT_NO
								AND ERASER.ROW_NO = CREDIT.ROW_NO
								AND (CREDIT.DATA_TOTAL_DIVISION = '1'
										  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
								AND CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
								AND CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
								AND CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
							LEFT JOIN
								CLASSIFICATION CL2
								ON  CREDIT.DATA_TYPE = CL2.DATA_TYPE
								AND CREDIT.CATEGORY_DIVISION = CL2.CATEGORY_DIVISION
					WHERE	ERASER_HEADER.ERASER_NO IS NOT NULL
					AND		ERASER_HEADER.ERASER_NO = /*eraserNo*/'KES0000005'
					UNION ALL
					--部門・請求先に紐づく入金消込残<>０の入金データ
					SELECT 	CREDIT.SECTION_CD													--部門コード
					,		CREDIT.CUSTOMER_CD AS VENDER_CD										--請求先コード
					, 		CREDIT.CREDIT_DATE													--入金日付
					,		CREDIT.CREDIT_NO													--入金番号
					,		CREDIT.ROW_NO														--行番号
					,		CL2.CATEGORY_NAME AS NYUKIN_CATE_NAME								--分類名称(入金分類)
					,		NVL(CREDIT.CREDIT_AMOUNT,0)	AS CREDIT_AMOUNT						--入金金額
					,		NVL(CREDIT.ERASER_AMOUNT,0) AS CR_ERASER_AMOUNT						--消込額
					,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--入金消込残
					FROM 	CREDIT CREDIT
							LEFT JOIN
								CLASSIFICATION CL2
								ON  CREDIT.DATA_TYPE = CL2.DATA_TYPE
								AND CREDIT.CATEGORY_DIVISION = CL2.CATEGORY_DIVISION
					WHERE	CREDIT.SECTION_CD IS NOT NULL
					AND		CREDIT.SECTION_CD = /*sectionCd*/'SC00001'
					AND		CREDIT.CUSTOMER_CD = /*venderCd*/'TS0001'
					AND		(CREDIT.DATA_TOTAL_DIVISION = '1'
								  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
					AND		CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
					AND		CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
					AND		CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
					AND		CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
					)
			) CREDIT
			ON  ERASER_HEADER.SECTION_CD = CREDIT.SECTION_CD
			AND ERASER_HEADER.VENDER_CD = CREDIT.VENDER_CD
	ORDER BY CREDIT_DATE
	,		 ROW_NO
/*END*/
/*IF (( eraserNo == null ) || ( eraserNo == "" ))*/
	--新規の場合
	SELECT	CLAIM_HEADER.ERASER_NO					--消込番号
	,		CLAIM_HEADER.SECTION_CD					--部門コード
	,		CLAIM_HEADER.SECTION_NAME				--部門名称
	,		CLAIM_HEADER.VENDER_CD					--請求先コード
	,		CLAIM_HEADER.VENDER_NAME				--請求先名称
	,		CLAIM_HEADER.ERASER_CAPA_AMOUNT			--消込可能額
	,		CLAIM_HEADER.ERASER_AMOUNT				--消込額
	,		CLAIM_HEADER.ERASER_BALANCE_AMOUNT		--消込残合計
	,		NVL(CREDIT_SUM.SUM_CREDIT_AMOUNT,0)	AS SUM_CREDIT_AMOUNT	--入金金額合計
	,		NVL(CREDIT_SUM.SUM_ERASER_AMOUNT,0) AS SUM_ERASER_AMOUNT	--消込額合計
	,		NVL(CREDIT_SUM.SUM_CREDIT_ERASER_AMOUNT,0) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残合計
	,		CREDIT.CREDIT_DATE						--入金日付
	,		CREDIT.CREDIT_NO						--入金番号
	,		CREDIT.ROW_NO							--行番号
	,		CREDIT.NYUKIN_CATE_NAME					--分類名称(入金分類)
	,		CREDIT.CREDIT_AMOUNT					--入金金額
	,		CREDIT.CR_ERASER_AMOUNT					--消込額
	,		CREDIT.CREDIT_ERASER_AMOUNT				--入金消込残
	FROM
		(
			--請求ヘッダーデータ
			SELECT 	NULL AS ERASER_NO										--消込番号
			,		CLAIM_HEADER.SECTION_CD									--部門コード
			,		BUMON.SECTION_NAME										--部門名称
			,		CLAIM_HEADER.CUSTOMER_CD AS VENDER_CD					--請求先コード
			,		VENDER.VENDER_NAME										--請求先名称
			,		NVL(CLAIM_HEADER.AMOUNT,0) AS ERASER_CAPA_AMOUNT		--消込可能額
			,		0 AS ERASER_AMOUNT										--消込額
			,		NVL(CLAIM_HEADER.AMOUNT,0) AS ERASER_BALANCE_AMOUNT		--消込残合計
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
						BUMON
						ON CLAIM_HEADER.SECTION_CD = BUMON.SECTION_CD
					LEFT JOIN
						LOGIN
						ON CLAIM_HEADER.INPUTOR_CD = LOGIN.TANTO_CD
					LEFT JOIN
						VENDER
						ON CLAIM_HEADER.CUSTOMER_CD = VENDER.VENDER_CD
						AND VENDER.VENDER_DIVISION = 'TS'							--得意先
			WHERE	CLAIM_HEADER.SECTION_CD = /*sectionCd*/'SC00001'
			AND		CLAIM_HEADER.CUSTOMER_CD = /*venderCd*/'TS0001'
			/*IF (( tantoCd != null ) && ( tantoCd != "" ))*/
			AND		CLAIM_HEADER.INPUTOR_CD LIKE /*tantoCd*/
			/*END*/
			/*IF (( tantoNm != null ) && ( tantoNm != "" ))*/
			AND	LOGIN.TANTO_NM LIKE /*tantoNm*/
			/*END*/
		) CLAIM_HEADER
		LEFT JOIN
			(
				--入金合計
				--部門・請求先に紐づく入金消込残<>０のデータ
				SELECT 	CREDIT.SECTION_CD													--部門コード
				,		CREDIT.CUSTOMER_CD AS VENDER_CD										--請求先コード
				,		SUM(NVL(CREDIT.CREDIT_AMOUNT,0)) AS SUM_CREDIT_AMOUNT				--入金金額
				,		SUM(NVL(CREDIT.ERASER_AMOUNT,0)) AS SUM_ERASER_AMOUNT				--消込額
				,		SUM(NVL(CREDIT.CREDIT_ERASER_AMOUNT,0)) AS SUM_CREDIT_ERASER_AMOUNT	--入金消込残
				FROM 	CREDIT CREDIT
				WHERE	CREDIT.SECTION_CD IS NOT NULL
				AND		CREDIT.SECTION_CD = /*sectionCd*/'SC00001'
				AND		CREDIT.CUSTOMER_CD = /*venderCd*/'TS0001'
				AND		(CREDIT.DATA_TOTAL_DIVISION = '1'
							  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
				AND		CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
				AND		CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
				AND		CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
				AND		CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
				GROUP BY 	CREDIT.SECTION_CD
				,			CREDIT.CUSTOMER_CD
			) CREDIT_SUM
			ON  CLAIM_HEADER.SECTION_CD = CREDIT_SUM.SECTION_CD
			AND CLAIM_HEADER.VENDER_CD = CREDIT_SUM.VENDER_CD
		LEFT JOIN
			(
				--入金内訳
				--部門・請求先に紐づく入金消込残<>０の入金データ
				SELECT 	CREDIT.SECTION_CD													--部門コード
				,		CREDIT.CUSTOMER_CD AS VENDER_CD										--請求先コード
				, 		CREDIT.CREDIT_DATE													--入金日付
				,		CREDIT.CREDIT_NO													--入金番号
				,		CREDIT.ROW_NO														--行番号
				,		CL2.CATEGORY_NAME AS NYUKIN_CATE_NAME								--分類名称(入金分類)
				,		NVL(CREDIT.CREDIT_AMOUNT,0)	AS CREDIT_AMOUNT						--入金金額
				,		NVL(CREDIT.ERASER_AMOUNT,0) AS CR_ERASER_AMOUNT						--消込額
				,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--入金消込残
				FROM 	CREDIT CREDIT
						LEFT JOIN
							CLASSIFICATION CL2
							ON  CREDIT.DATA_TYPE = CL2.DATA_TYPE
							AND CREDIT.CATEGORY_DIVISION = CL2.CATEGORY_DIVISION
				WHERE	CREDIT.SECTION_CD IS NOT NULL
				AND		CREDIT.SECTION_CD = /*sectionCd*/'SC00001'
				AND		CREDIT.CUSTOMER_CD = /*venderCd*/'TS0001'
				AND		(CREDIT.DATA_TOTAL_DIVISION = '1'
							  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
				AND		CREDIT.CLAIM_TARGET_DIVISION = '1'						--請求対象
				AND		CREDIT.CLAIM_UPDATE_DIVISION = '1'						--請求更新フラグ
				AND		CREDIT.INVOICE_UPDATE_DATE IS NOT NULL					--請求締め日
				AND		CREDIT.CREDIT_ERASER_AMOUNT <> 0						--入金消込残<>０
			) CREDIT
			ON  CLAIM_HEADER.SECTION_CD = CREDIT.SECTION_CD
			AND CLAIM_HEADER.VENDER_CD = CREDIT.VENDER_CD
	ORDER BY CREDIT_DATE
	,		 ROW_NO
/*END*/
 