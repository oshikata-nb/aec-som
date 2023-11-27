/*
 * 請求書帳票用SQL
 *
 * entityName=BillIssueListForReport
 * packageName=billissuelistforreport
 * methodName=getListForReport
 *
 */
SELECT *
FROM
(
/*IF (condition.srhNormalFlg)*/
SELECT CLM_HED.*
, ORGANIZATION.ORGANIZATION_NAME
, VENDER.VENDER_NAME1
, VENDER.VENDER_SHORTED_NAME

, CREDIT_AMOUNT_FORWARD
+ OTHER_CREDIT_AMOUNT_FORWARD OTHER_CREDIT_AMOUNT --画面用.入金・その他計

, CLM_HED.SALES_RETURNED_AMOUNT
+ CLM_HED.SALES_DISCOUNT_AMOUNT
+ OTHER_SALES_AMOUNT
+ OFFSET_AMOUNT OTHER_SALES --画面用.その他計

, 1 KBN --対象区分
, ISSUER.TANTO_NM ISSUER_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME
, FUN_GET_CLAIM_TAX_AMOUNT(CLM_HED.CLAIM_NO) AS FUN_TAX_AMOUNT

FROM CLAIM_HEADER CLM_HED
, ORGANIZATION
, VENDER
, LOGIN ISSUER
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE CLM_HED.ORGANIZATION_CD IS NOT NULL
AND	CLM_HED.BILL_DIVISION != '9'

/*IF (condition.srhBillIssueFlg)*/ --請求書発行区分(未発行のみ)
AND	CLM_HED.BILL_DIVISION = 0
/*END*/

/*IF !(condition.srhBillIssueFlg) */ --請求書発行区分
AND	(CLM_HED.BILL_DIVISION = 0 OR CLM_HED.BILL_DIVISION = 1)
/*END*/

/*IF (condition.srhSectionCd != null && condition.srhSectionCd != "")*/
AND	(CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND	(CLM_HED.ISSUER_CD LIKE /*condition.srhTantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
AND	(CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (condition.srhCreditDate != null && condition.srhCreditDate != "")*/
AND TO_CHAR(CLM_HED.CREDIT_DATE,'YYYY/MM/DD') = /*condition.srhCreditDate*/'2009/06/01'
/*END*/

/*IF (condition.srhClaimNoFrom != null && condition.srhClaimNoFrom != "")*/
AND	CLM_HED.CLAIM_NO >= /*condition.srhClaimNoFrom*/'%'
/*END*/

/*IF (condition.srhClaimNoTo != null && condition.srhClaimNoTo != "")*/
AND	CLM_HED.CLAIM_NO <= /*condition.srhClaimNoTo*/'%'
/*END*/

AND CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND CLM_HED.VENDER_CD = VENDER.VENDER_CD(+)
AND CLM_HED.ISSUER_CD = ISSUER.TANTO_CD(+)
AND CLM_HED.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND CLM_HED.UPDATOR_CD = UPDATOR.TANTO_CD(+)

/*END*/

/*IF (condition.srhNormalFlg && condition.srhTempClosingFlg)*/
UNION
/*END*/

/*IF (condition.srhTempClosingFlg)*/
SELECT TMP_CLM_HED.*
, ORGANIZATION.ORGANIZATION_NAME
, VENDER.VENDER_NAME1
, VENDER.VENDER_SHORTED_NAME

, CREDIT_AMOUNT_FORWARD
+ OTHER_CREDIT_AMOUNT_FORWARD OTHER_CREDIT_AMOUNT --画面用.入金・その他計

, TMP_CLM_HED.SALES_RETURNED_AMOUNT
+ TMP_CLM_HED.SALES_DISCOUNT_AMOUNT
+ OTHER_SALES_AMOUNT
+ OFFSET_AMOUNT OTHER_SALES --画面用.その他計

, 2 KBN --対象区分
, ISSUER.TANTO_NM ISSUER_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME
, FUN_GET_TEMP_CLAIM_TAX_AMOUNT(TMP_CLM_HED.CLAIM_NO) AS FUN_TAX_AMOUNT

FROM TEMPORARY_CLAIM_HEADER TMP_CLM_HED
, ORGANIZATION
, VENDER
, LOGIN ISSUER
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE TMP_CLM_HED.ORGANIZATION_CD IS NOT NULL
AND	TMP_CLM_HED.BILL_DIVISION != 9

/*IF (condition.srhBillIssueFlg)*/ --請求書発行区分(未発行のみ)
AND	TMP_CLM_HED.BILL_DIVISION = 0
/*END*/

/*IF !(condition.srhBillIssueFlg) */ --請求書発行区分
AND	(TMP_CLM_HED.BILL_DIVISION = 0 OR TMP_CLM_HED.BILL_DIVISION = 1)
/*END*/

/*IF (condition.srhSectionCd != null && condition.srhSectionCd != "")*/
AND	(TMP_CLM_HED.ORGANIZATION_CD LIKE /*condition.srhSectionCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhSectionCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND	(TMP_CLM_HED.ISSUER_CD LIKE /*condition.srhTantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
AND	(TMP_CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR VENDER.VENDER_NAME1 LIKE /*condition.srhVenderCd*/'%')
/*END*/

/*IF (condition.srhCreditDate != null && condition.srhCreditDate != "")*/
AND TO_CHAR(TMP_CLM_HED.CREDIT_DATE,'YYYY/MM/DD') = /*condition.srhCreditDate*/'2009/06/01'
/*END*/

/*IF (condition.srhClaimNoFrom != null && condition.srhClaimNoFrom != "")*/
AND	TMP_CLM_HED.CLAIM_NO >= /*condition.srhClaimNoFrom*/'%'
/*END*/

/*IF (condition.srhClaimNoTo != null && condition.srhClaimNoTo != "")*/
AND	TMP_CLM_HED.CLAIM_NO <= /*condition.srhClaimNoTo*/'%'
/*END*/

AND TMP_CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND TMP_CLM_HED.VENDER_CD = VENDER.VENDER_CD(+)
AND TMP_CLM_HED.ISSUER_CD = ISSUER.TANTO_CD(+)
AND TMP_CLM_HED.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND TMP_CLM_HED.UPDATOR_CD = UPDATOR.TANTO_CD(+)

/*END*/
)
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

ORDER BY VENDER_CD, CREDIT_DATE, CLAIM_NO
