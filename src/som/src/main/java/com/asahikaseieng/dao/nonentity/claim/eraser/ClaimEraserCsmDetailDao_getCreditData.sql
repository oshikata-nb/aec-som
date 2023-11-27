/*
 * 消込入力詳細画面(カスタマイズ)
 * 消し込む前の状態に戻すための入金データ取得用SQL
 *
 * entityName=EraserCsmDetail
 * packageName=claim.eraser
 * methodName=getCreditData
 *
 */
SELECT 	CREDIT.CREDIT_DATE													--入金日付
,		CREDIT.CREDIT_NO													--入金番号
,		CREDIT.ROW_NO														--行番号
,		CREDIT.DATA_TYPE													--データ種別
,		NVL(CREDIT.CREDIT_AMOUNT,0)	AS CREDIT_AMOUNT						--入金金額
,		NVL(CREDIT.ERASER_AMOUNT,0) AS CR_ERASER_AMOUNT						--消込額
,		NVL(CREDIT.CREDIT_ERASER_AMOUNT,0) AS CREDIT_ERASER_AMOUNT			--入金消込残
FROM
	(
		SELECT CREDIT_DATE,
		       CREDIT_NO,
		       ROW_NO,
		       DATA_TYPE,
		       CREDIT_AMOUNT,
		       ERASER_AMOUNT,
		       CREDIT_ERASER_AMOUNT,
		       (SUM(ERASER_AMOUNT)
		       		OVER(ORDER BY CREDIT_DATE DESC ,CREDIT_NO DESC ,ROW_NO DESC)) AS SUM_ERASER_AMOUNT
		FROM CREDIT
		WHERE	CREDIT.ORGANIZATION_CD IS NOT NULL
		AND		CREDIT.ORGANIZATION_CD = /*organizationCd*/'%'
		AND		CREDIT.VENDER_CD = /*venderCd*/'%'
		AND		(CREDIT.DATA_TOTAL_DIVISION = '1'
					  OR CREDIT.DATA_TOTAL_DIVISION = '9')				--データ集計区分(1:入金、9:その他)
		AND		CREDIT.CLAIM_TARGET_DIVISION <> '9'						--請求対象
		AND		( (CREDIT.ERASER_AMOUNT <> 0 AND CREDIT_ERASER_AMOUNT <> 0) OR		--消込額<>０,入金消込残<>０
				  CREDIT_ERASER_AMOUNT = 0 )										--入金消込残=０
	) CREDIT
ORDER BY
	CREDIT.CREDIT_DATE DESC
,	CREDIT.CREDIT_NO DESC
,	CREDIT.ROW_NO DESC
