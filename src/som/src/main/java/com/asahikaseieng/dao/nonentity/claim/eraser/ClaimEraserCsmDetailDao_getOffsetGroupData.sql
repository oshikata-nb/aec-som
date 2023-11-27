/*
 * 消込入力詳細画面(カスタマイズ)
 * 消し込む前の状態に戻すための相殺データ取得用SQL
 *
 * entityName=ClaimEraserCsmDetail
 * packageName=eraser
 * methodName=getOffsetGroupData
 *
 */
SELECT 	OFFSET.OFFSET_DATE CREDIT_DATE										--相殺日付
,		OFFSET.OFFSET_NO CREDIT_NO											--相殺番号
,		OFFSET.DATA_TYPE													--データ種別
,		OFFSET.VENDER_CD													--請求先コード
,		NVL(OFFSET.ERASER_AMOUNT,0) AS CR_ERASER_AMOUNT						--消込額
,		NVL(OFFSET.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--相殺消込残
FROM
	(
		SELECT OFFSET_DATE,
		       OFFSET_NO,
		       DATA_TYPE,
		       VENDER_CD,
		       ERASER_AMOUNT,
		       CREDIT_ERASER_AMOUNT,
		       (SUM(ERASER_AMOUNT)
		       		OVER(ORDER BY OFFSET_DATE DESC ,OFFSET_NO DESC)) AS SUM_ERASER_AMOUNT
		FROM OFFSET_GROUP_DATA OFFSET
		WHERE	OFFSET.ORGANIZATION_CD IS NOT NULL
		AND		OFFSET.ORGANIZATION_CD = /*organizationCd*/'%'
		AND 	OFFSET.VENDER_DIVISION = 'TS'
		AND		OFFSET.VENDER_CD = /*venderCd*/'%'
		AND		(OFFSET.DATA_TOTAL_DIVISION = '1'
					  OR OFFSET.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:相殺、9:その他)
		AND		OFFSET.CLAIM_TARGET_DIVISION <> '9'						--請求対象
		AND		( (OFFSET.ERASER_AMOUNT <> 0 AND CREDIT_ERASER_AMOUNT <> 0) OR		--消込額<>０,相殺消込残<>０
				  CREDIT_ERASER_AMOUNT = 0 )										--相殺消込残=０
	) OFFSET
ORDER BY
	OFFSET.OFFSET_DATE DESC
,	OFFSET.OFFSET_NO DESC
