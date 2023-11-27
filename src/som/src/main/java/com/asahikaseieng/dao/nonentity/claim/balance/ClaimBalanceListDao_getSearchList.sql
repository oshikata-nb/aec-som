/*
 * 請求残高一覧用SQL
 *
 * entityName=BalanceList
 * packageName=claim.balance
 * methodName=getSearchList
 *
 */
SELECT
    MAIN.*
    -- 前回請求額 + 今回売上額 + 消費税 - 入金・その他 - その他合計 = 請求合計(今回請求額)
    , (CLAIM_AMOUNT_FORWARD + SALES_AMOUNT + TAX_AMOUNT - OTHER_CREDIT - OTHER_SALES) AS CLAIM_AMOUNT
FROM
(
/*IF (condition.srhNormalFlg)*/

	SELECT CLM_HED.CLAIM_NO                      --請求番号
	,      CLM_HED.ORGANIZATION_CD               --部門コード
	,      ORGANIZATION.ORGANIZATION_NAME        --部門名称
	,      CLM_HED.vender_CD                     --請求先コード
	,      VENDER.VENDER_NAME1 AS VENDER_NAME                   --請求先名称
	,      VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME    --請求先略称
	,      CLM_HED.CLAIM_AMOUNT_FORWARD          --前回請求額
	,      CLM_HED.CREDIT_AMOUNT_FORWARD         --前回入金額
	,      CLM_HED.OTHER_CREDIT_AMOUNT_FORWARD   --その他入金金額
	,      CLM_HED.BALANCE_FORWARD               --差額繰越
	,      CLM_HED.SALES_AMOUNT                  --今回売上額
	,      CLM_HED.SALES_RETURNED_AMOUNT         --返品金額
	,      CLM_HED.SALES_DISCOUNT_AMOUNT         --値引金額
	,      CLM_HED.OTHER_SALES_AMOUNT            --その他売上金額
	,      CLM_HED.OFFSET_AMOUNT                 --相殺金額
--	,      CLM_HED.TAX_AMOUNT                    --消費税額
	,      FUN_GET_CLAIM_TAX_AMOUNT(CLM_HED.CLAIM_NO) AS TAX_AMOUNT	--消費税額
--	,      CLM_HED.CLAIM_AMOUNT                  --今回請求額
	,      CLM_HED.CLAIM_BALANCE_AMOUNT			 --未請求額
	,      (CREDIT_AMOUNT_FORWARD
	        +OTHER_CREDIT_AMOUNT_FORWARD
	        +OFFSET_AMOUNT) AS OTHER_CREDIT			--画面用.入金・その他計
	,		(OTHER_SALES_AMOUNT
			-SALES_RETURNED_AMOUNT
			-SALES_DISCOUNT_AMOUNT) AS OTHER_SALES  --画面用.その他計
	,      VENDER.CREDIT_LIMIT_PRICE             	--与信限度額
	FROM   CLAIM_HEADER CLM_HED
		   LEFT JOIN ORGANIZATION ORGANIZATION
		   ON CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON CLM_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON  VENDER.VENDER_DIVISION = 'TS'
		   AND CLM_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  CLM_HED.ORGANIZATION_CD IS NOT NULL
	AND    TO_CHAR(CLM_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/05'

	/*IF (condition.srhOutputDivision)*/ --出力区分：残高有のみ（請求残高が0以外）
		AND	CLM_HED.CLAIM_AMOUNT IS NOT NULL
		AND CLM_HED.CLAIM_AMOUNT != 0
	/*END*/

	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/

	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(CLM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/

	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/

	/*IF (!condition.srhTempClosingFlg)*/
		ORDER BY VENDER_CD
	/*END*/

/*END*/

/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
	UNION
/*END*/

/*IF (condition.srhTempClosingFlg)*/

	SELECT TMP_CLM_HED.CLAIM_NO                      --請求番号
	,      TMP_CLM_HED.ORGANIZATION_CD               --部門コード
	,      ORGANIZATION.ORGANIZATION_NAME            --部門名称
	,      TMP_CLM_HED.VENDER_CD                     --請求先コード
	,      VENDER.VENDER_NAME1  AS VENDER_NAME       --請求先名称
	,      VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME    --請求先略称
	,      TMP_CLM_HED.CLAIM_AMOUNT_FORWARD          --前回請求額
	,      TMP_CLM_HED.CREDIT_AMOUNT_FORWARD         --前回入金額
	,      TMP_CLM_HED.OTHER_CREDIT_AMOUNT_FORWARD   --その他入金金額
	,      TMP_CLM_HED.BALANCE_FORWARD               --差額繰越
	,      TMP_CLM_HED.SALES_AMOUNT                  --今回売上額
	,      TMP_CLM_HED.SALES_RETURNED_AMOUNT         --返品金額
	,      TMP_CLM_HED.SALES_DISCOUNT_AMOUNT         --値引金額
	,      TMP_CLM_HED.OTHER_SALES_AMOUNT            --その他売上金額
	,      TMP_CLM_HED.OFFSET_AMOUNT                 --相殺金額
--	,      TMP_CLM_HED.TAX_AMOUNT                    --消費税額
	,      FUN_GET_TEMP_CLAIM_TAX_AMOUNT(TMP_CLM_HED.CLAIM_NO) AS TAX_AMOUNT	--消費税額
--	,      TMP_CLM_HED.CLAIM_AMOUNT                  --今回請求額
	,      TMP_CLM_HED.CLAIM_BALANCE_AMOUNT			 --未請求額
	,      (CREDIT_AMOUNT_FORWARD
	        +OTHER_CREDIT_AMOUNT_FORWARD
	        +OFFSET_AMOUNT) AS OTHER_CREDIT			 --画面用.入金・その他計
	,		(OTHER_SALES_AMOUNT
			-SALES_RETURNED_AMOUNT
			-SALES_DISCOUNT_AMOUNT) AS OTHER_SALES   --画面用.その他計
	,      VENDER.CREDIT_LIMIT_PRICE                 --与信限度額
	FROM   TEMPORARY_CLAIM_HEADER TMP_CLM_HED
		   LEFT JOIN ORGANIZATION ORGANIZATION
		   ON TMP_CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON TMP_CLM_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON   VENDER.VENDER_DIVISION = 'TS'
		   AND  TMP_CLM_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  TMP_CLM_HED.ORGANIZATION_CD IS NOT NULL
	AND    TO_CHAR(TMP_CLM_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/05'

	/*IF (condition.srhOutputDivision)*/
		AND	TMP_CLM_HED.CLAIM_AMOUNT IS NOT NULL
		AND TMP_CLM_HED.CLAIM_AMOUNT != 0
	/*END*/

	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(TMP_CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/

	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(TMP_CLM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/

	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(TMP_CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/

	ORDER BY VENDER_CD

/*END*/
)MAIN
