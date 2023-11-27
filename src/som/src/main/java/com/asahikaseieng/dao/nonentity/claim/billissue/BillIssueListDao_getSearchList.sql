/*
 * 請求書発行用SQL
 *
 * entityName=BillIssueList
 * packageName=claim.balance
 * methodName=getSearchList
 *
 */
SELECT
    MAIN.*
    -- 前回請求額 + 今回請求額 + 消費税 - 入金・その他 - その他合計 = 請求合計
    , (CLAIM_AMOUNT_FORWARD + SALES_AMOUNT + TAX_AMOUNT - OTHER_CREDIT_AMOUNT - OTHER_SALES) AS TOTAL_CLAIM_AMOUNT
FROM
(
/*IF (condition.srhNormalFlg)*/

	SELECT CLM_HED.CLAIM_NO                      --請求番号
	,      CLM_HED.ORGANIZATION_CD SECTION_CD    --部署コード
	,      ORGANIZATION.ORGANIZATION_NAME SECTION_NAME --部署名称
	,      CLM_HED.VENDER_CD CUSTOMER_CD         --請求先コード
	,      VENDER.VENDER_NAME1 VENDER_NAME       --請求先名称
	,      VENDER.VENDER_SHORTED_NAME VENDER_SHORTED_NAME       --請求先略称
	,      TO_CHAR(CLM_HED.CREDIT_DATE,'YYYY/MM/DD') AS CREDIT_DATE
								                 --請求締め日
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
	,      FUN_GET_CLAIM_TAX_AMOUNT(CLM_HED.CLAIM_NO) AS TAX_AMOUNT
								                 --消費税額
	,      CLM_HED.CLAIM_AMOUNT                  --今回請求額
	,      (CREDIT_AMOUNT_FORWARD
	        +OTHER_CREDIT_AMOUNT_FORWARD
	        +OFFSET_AMOUNT) AS OTHER_CREDIT_AMOUNT
	                                             --画面用.入金・その他計
	,      (CLM_HED.SALES_RETURNED_AMOUNT
			+CLM_HED.SALES_DISCOUNT_AMOUNT
			+OTHER_SALES_AMOUNT) AS OTHER_SALES  --画面用.その他計
	,      1 KBN                               --対象区分
	FROM   CLAIM_HEADER CLM_HED
		   LEFT JOIN ORGANIZATION
		   ON CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON CLM_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON  VENDER.VENDER_DIVISION = 'TS'
		   AND CLM_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  CLM_HED.ORGANIZATION_CD IS NOT NULL
		AND	CLM_HED.BILL_DIVISION != '9'

	/*IF (condition.srhBillIssueFlg)*/ --請求書発行区分(未発行のみ)
		AND	CLM_HED.BILL_DIVISION = '0'
	/*END*/

	/*IF !(condition.srhBillIssueFlg) */ --請求書発行区分
		AND	((CLM_HED.BILL_DIVISION = '0') OR (CLM_HED.BILL_DIVISION = '1'))
	/*END*/

	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/

	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(CLM_HED.ISSUER_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/

	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/

	/*IF (( condition.srhCreditDate != null ) && ( condition.srhCreditDate != "" ))*/
		AND TO_CHAR(CLM_HED.CREDIT_DATE,'YYYY/MM/DD') = /*condition.srhCreditDate*/'2009/06/01'
	/*END*/

	/*IF (( condition.srhClaimNoFrom != null ) && ( condition.srhClaimNoFrom != "" ))*/
		AND	CLM_HED.CLAIM_NO >= /*condition.srhClaimNoFrom*/'%'
	/*END*/

	/*IF (( condition.srhClaimNoTo != null ) && ( condition.srhClaimNoTo != "" ))*/
		AND	CLM_HED.CLAIM_NO <= /*condition.srhClaimNoTo*/'%'
	/*END*/
/*END*/

/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
	UNION
/*END*/

/*IF (condition.srhTempClosingFlg)*/

	SELECT TMP_CLM_HED.CLAIM_NO                      --請求番号
	,      TMP_CLM_HED.ORGANIZATION_CD SECTION_CD    --部署コード
	,      ORGANIZATION.ORGANIZATION_NAME SECTION_NAME --部署名称
	,      TMP_CLM_HED.VENDER_CD CUSTOMER_CD         --請求先コード
	,      VENDER.VENDER_NAME1 VENDER_NAME           --請求先名称
	,      VENDER.VENDER_SHORTED_NAME VENDER_SHORTED_NAME       --請求先略称
	,      TO_CHAR(TMP_CLM_HED.CREDIT_DATE,'YYYY/MM/DD') AS CREDIT_DATE
								                 	 --請求締め日
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
	,      FUN_GET_TEMP_CLAIM_TAX_AMOUNT(TMP_CLM_HED.CLAIM_NO) AS TAX_AMOUNT
								                 --消費税額
	,      TMP_CLM_HED.CLAIM_AMOUNT                  --今回請求額
	,      (CREDIT_AMOUNT_FORWARD
	        +OTHER_CREDIT_AMOUNT_FORWARD
	        +OFFSET_AMOUNT) AS OTHER_CREDIT_AMOUNT
	                                                 --画面用.入金・その他計
	,      (TMP_CLM_HED.SALES_RETURNED_AMOUNT
			+TMP_CLM_HED.SALES_DISCOUNT_AMOUNT
			+OTHER_SALES_AMOUNT) AS OTHER_SALES      --画面用.その他計
	,      2 KBN                               --対象区分
	FROM   TEMPORARY_CLAIM_HEADER TMP_CLM_HED
		   LEFT JOIN ORGANIZATION
		   ON TMP_CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON TMP_CLM_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON   VENDER.VENDER_DIVISION = 'TS'
		   AND TMP_CLM_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  
		NOT EXISTS(SELECT * FROM CLAIM_HEADER WHERE TMP_CLM_HED.CLAIM_NO = CLAIM_HEADER.CLAIM_NO )
		AND TMP_CLM_HED.ORGANIZATION_CD IS NOT NULL
		AND	TMP_CLM_HED.BILL_DIVISION != '9'

	/*IF (condition.srhBillIssueFlg)*/ --請求書発行区分(未発行のみ)
		AND	TMP_CLM_HED.BILL_DIVISION = '0'
	/*END*/

	/*IF !(condition.srhBillIssueFlg) */ --請求書発行区分
		AND	((TMP_CLM_HED.BILL_DIVISION = '0') OR (TMP_CLM_HED.BILL_DIVISION = '1'))
	/*END*/

	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(TMP_CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/

	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(TMP_CLM_HED.ISSUER_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/

	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(TMP_CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/

	/*IF (( condition.srhCreditDate != null ) && ( condition.srhCreditDate != "" ))*/
		AND TO_CHAR(TMP_CLM_HED.CREDIT_DATE,'YYYY/MM/DD') = /*condition.srhCreditDate*/'2009/06/01'
	/*END*/

	/*IF (( condition.srhClaimNoFrom != null ) && ( condition.srhClaimNoFrom != "" ))*/
		AND	TMP_CLM_HED.CLAIM_NO >= /*condition.srhClaimNoFrom*/'%'
	/*END*/

	/*IF (( condition.srhClaimNoTo != null ) && ( condition.srhClaimNoTo != "" ))*/
		AND	TMP_CLM_HED.CLAIM_NO <= /*condition.srhClaimNoTo*/'%'
	/*END*/
/*END*/
)MAIN
WHERE CLAIM_NO IS NOT NULL

/*IF (condition.srhBalanceDivision)*/ --出力区分：請求残高があるもの
	AND	CLAIM_AMOUNT IS NOT NULL AND CLAIM_AMOUNT != 0 --請求合計
/*END*/

/*IF (condition.srhDealingDivision)*/ --出力区分：取引があるもの
	AND	((CLAIM_AMOUNT_FORWARD IS NOT NULL AND CLAIM_AMOUNT_FORWARD != 0) --前回請求額
	OR	(OTHER_CREDIT_AMOUNT IS NOT NULL AND OTHER_CREDIT_AMOUNT != 0) --入金・その他計
	OR	(SALES_AMOUNT IS NOT NULL AND SALES_AMOUNT != 0) --今回売上額
	OR	(OTHER_SALES IS NOT NULL AND OTHER_SALES != 0) --その他計
	OR	(TAX_AMOUNT IS NOT NULL AND TAX_AMOUNT != 0) --消費税
	OR	(CLAIM_AMOUNT IS NOT NULL AND CLAIM_AMOUNT != 0)) --請求合計
/*END*/

ORDER BY CUSTOMER_CD
,		 CREDIT_DATE
,		 CLAIM_NO
