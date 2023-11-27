/*
 * 売掛残高一覧用SQL
 *
 * entityName=ArBalanceList
 * packageName=arbalance
 * methodName=getSearchList
 *
 */
/*IF (condition.srhNormalFlg)*/

	SELECT DEP_HED.DEPOSIT_NO              --売掛番号
	,      DEP_HED.ORGANIZATION_CD SECTION_CD --部署コード
	,      ORGANIZATION.ORGANIZATION_NAME SECTION_NAME --部署名称
	,      DEP_HED.VENDER_CD               --請求先コード
	,      VENDER.VENDER_NAME1 VENDER_NAME --請求先名称
	,      VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME --請求先略称
	,      DEP_HED.BALANCE_FORWARD         --前月繰越
	,      DEP_HED.SALES_AMOUNT            --売上金額
	,      DEP_HED.DEPOSIT_AMOUNT          --入金金額
	,      DEP_HED.OTHER_DEPOSIT_AMOUNT    --その他入金金額
	,      DEP_HED.RETURNED_AMOUNT         --返品金額
	,      DEP_HED.DISCOUNT_AMOUNT         --値引金額
	,      DEP_HED.OTHER_SALES_AMOUNT      --その他売上金額
	,      DEP_HED.OFFSET_AMOUNT           --相殺金額
	,      DEP_HED.TAX_AMOUNT              --消費税額
	,      DEP_HED.CREDIT_SALES_BREAKDOWN  --売掛金(内訳)
	,      DEP_HED.ACCRUED_DEBIT_BREAKDOWN --未収金(内訳)
	,      DEP_HED.CREDIT_AMOUNT           --売掛残
	,      NVL(DEP_HED.CREDIT_SALES_BREAKDOWN, 0) - NVL(DEP_HED.CLAIM_FORWARD, 0) CLAIM_BALANCE --請求残高
	,      NVL(DEP_HED.CLAIM_FORWARD, 0) CLAIM_FORWARD	--未請求残高
	,      (DEP_HED.DEPOSIT_AMOUNT
	        +DEP_HED.OTHER_DEPOSIT_AMOUNT
	        +DEP_HED.OFFSET_AMOUNT) AS OTHER_DEPOSIT
		                                   --画面用.入金・その他計
	,      (DEP_HED.RETURNED_AMOUNT
	        +DEP_HED.DISCOUNT_AMOUNT
	        +DEP_HED.OTHER_SALES_AMOUNT) AS OTHER_SALES
	                                       --画面用.その他計
	FROM   DEPOSIT_HEADER DEP_HED
		   LEFT JOIN ORGANIZATION
		   ON DEP_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON DEP_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON   VENDER.VENDER_DIVISION = 'TS'
		   AND  DEP_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  DEP_HED.ORGANIZATION_CD IS NOT NULL
	AND  TO_CHAR(DEP_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/06'
	
	/*IF (condition.srhCreditAmountDivi == "1")*/ --出力区分：売掛残高有のみ（売掛残高が0以外）
		AND	DEP_HED.CREDIT_SALES_BREAKDOWN IS NOT NULL
		AND DEP_HED.CREDIT_SALES_BREAKDOWN != '0'
	/*END*/
	
	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(DEP_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(DEP_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(DEP_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/
	
	/*IF (!condition.srhTempClosingFlg)*/
		ORDER BY DEP_HED.VENDER_CD
	/*END*/

/*END*/

/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
	UNION
/*END*/

/*IF (condition.srhTempClosingFlg)*/

	SELECT TMP_DEP_HED.DEPOSIT_NO              --売掛番号
	,      TMP_DEP_HED.ORGANIZATION_CD SECTION_CD --部署コード
	,      ORGANIZATION.ORGANIZATION_NAME SECTION_NAME --部署名称
	,      TMP_DEP_HED.VENDER_CD               --請求先コード
	,      VENDER.VENDER_NAME1 VENDER_NAME     --請求先名称
	,      VENDER.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME --請求先略称
	,      TMP_DEP_HED.BALANCE_FORWARD         --前月繰越
	,      TMP_DEP_HED.SALES_AMOUNT            --売上金額
	,      TMP_DEP_HED.DEPOSIT_AMOUNT          --入金金額
	,      TMP_DEP_HED.OTHER_DEPOSIT_AMOUNT    --その他入金金額
	,      TMP_DEP_HED.RETURNED_AMOUNT         --返品金額
	,      TMP_DEP_HED.DISCOUNT_AMOUNT         --値引金額
	,      TMP_DEP_HED.OTHER_SALES_AMOUNT      --その他売上金額
	,      TMP_DEP_HED.OFFSET_AMOUNT           --相殺金額
	,      TMP_DEP_HED.TAX_AMOUNT              --消費税額
	,      TMP_DEP_HED.CREDIT_SALES_BREAKDOWN  --売掛金(内訳)
	,      TMP_DEP_HED.ACCRUED_DEBIT_BREAKDOWN --未収金(内訳)
	,      TMP_DEP_HED.CREDIT_AMOUNT           --売掛残
	,      NVL(TMP_DEP_HED.CREDIT_SALES_BREAKDOWN, 0) - NVL(TMP_DEP_HED.CLAIM_FORWARD, 0) CLAIM_BALANCE --請求残高
	,      NVL(TMP_DEP_HED.CLAIM_FORWARD, 0) CLAIM_FORWARD	--未請求残高
	,      (TMP_DEP_HED.DEPOSIT_AMOUNT
	        +TMP_DEP_HED.OTHER_DEPOSIT_AMOUNT
	        +TMP_DEP_HED.OFFSET_AMOUNT) AS OTHER_DEPOSIT
		                                       --画面用.入金・その他計
	,      (TMP_DEP_HED.RETURNED_AMOUNT
	        +TMP_DEP_HED.DISCOUNT_AMOUNT
	        +TMP_DEP_HED.OTHER_SALES_AMOUNT) AS OTHER_SALES
	                                       --画面用.その他計
	FROM   TEMPORARY_DEPOSIT_HEADER TMP_DEP_HED
		   LEFT JOIN ORGANIZATION
		   ON TMP_DEP_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD
		   LEFT JOIN LOGIN LOGIN
		   ON TMP_DEP_HED.INPUTOR_CD = LOGIN.TANTO_CD
		   LEFT JOIN VENDER VENDER
		   ON   VENDER.VENDER_DIVISION = 'TS'
		   AND  TMP_DEP_HED.VENDER_CD = VENDER.VENDER_CD
	WHERE  TMP_DEP_HED.ORGANIZATION_CD IS NOT NULL
	AND  TO_CHAR(TMP_DEP_HED.CREDIT_DATE,'YYYY/MM') = /*condition.srhTargetMonth*/'2009/06'
	
	/*IF (condition.srhCreditAmountDivi == "1")*/ --出力区分：売掛残高有のみ（売掛残高が0以外）
		AND	TMP_DEP_HED.CREDIT_SALES_BREAKDOWN IS NOT NULL
		AND TMP_DEP_HED.CREDIT_SALES_BREAKDOWN != '0'
	/*END*/
	
	/*IF (( condition.srhSectionCd != null ) && ( condition.srhSectionCd != "" ))*/
		AND	(TMP_DEP_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(TMP_DEP_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	(TMP_DEP_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
	/*END*/
	
	ORDER BY VENDER_CD

/*END*/


