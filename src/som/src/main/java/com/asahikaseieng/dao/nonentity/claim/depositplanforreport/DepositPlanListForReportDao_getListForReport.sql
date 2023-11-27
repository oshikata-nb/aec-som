/*
 * 入金予定一覧用SQL
 *
 * entityName=DepositPlanListForReport
 * packageName=depositplanforreport
 * methodName=getListForReport
 *
 */
SELECT CLM_HED.*
, ORGANIZATION.ORGANIZATION_NAME

, NVL(CLM_HED.CLAIM_AMOUNT, 0)
- NVL(CLM_HED.BALANCE_FORWARD, 0) CLAIM_AMOUNT_BALANCE --請求額(今回請求額-差引繰越額)

, CLS.CATEGORY_NAME

, CASE CLS.BANK_DIVISION
	WHEN 0 THEN NULL
	WHEN 1 THEN CLM_HED.BANK_MASTER_CD
END USE_BANK_MASTER_CD --銀行マスタコード

, CASE CLS.BANK_DIVISION
	WHEN 0 THEN NULL
	WHEN 1 THEN CLM_HED.BANK_MASTER_NAME
END USE_BANK_MASTER_NAME --銀行マスタ名称

, CASE CLS.BANK_DIVISION
	WHEN 0 THEN ''
	WHEN 1 THEN CASE TO_CHAR(CLM_HED.ACCOUNT_DIVISION)
					WHEN '1' THEN '1:普通'
					WHEN '2' THEN '2:当座'
					WHEN '3' THEN '3:その他'
				END
	ELSE ''
END USE_ACCOUNT_DIVISION --口座区分

, CASE CLS.BANK_DIVISION
	WHEN 1 THEN CLM_HED.ACCOUNT_NO
END USE_ACCOUNT_NO --口座番号

, ISSUER.TANTO_NM ISSUER_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME
, CASE CLM_HED.INVOICE_PTN_FLG
	WHEN 0 THEN
		NULL										--インボイス対象前の場合：空欄
	WHEN 2 THEN
		SA_TAX_AMOUNT
	ELSE
		IN_TAX_AMOUNT								--インボイス対象の場合：請求書消費税額より
	END INVOICE_TAX_AMOUNT
, CASE CLM_HED.INVOICE_PTN_FLG
	WHEN 0 THEN
		NVL(CLM_HED.TAX_AMOUNT, 0) 				--インボイス対象前の場合：請求ヘッダーより
	ELSE
		MEI_TAX_AMOUNT 								--インボイス対象の場合：売上伝票消費税額より
	END SALES_TAX_AMOUNT
, CASE CLM_HED.INVOICE_PTN_FLG
	WHEN 0 THEN
		NULL 										--インボイス対象前の場合：空欄
	WHEN 2 THEN
		NVL(SA_TAX_AMOUNT - MEI_TAX_AMOUNT, 0) 		--インボイス対象の場合：消費税額差額
	ELSE
		NVL(IN_TAX_AMOUNT - MEI_TAX_AMOUNT, 0) 		--インボイス対象の場合：消費税額差額
	END TAX_DIFFERENCE

FROM
(SELECT CLAIM_HEADER.*
, VENDER.VENDER_DIVISION
, VENDER.VENDER_NAME1
, VENDER.VENDER_SHORTED_NAME
, VENDER.BANK_CD BANK_MASTER_CD
, BANK.BANK_MASTER_NAME
, VENDER.ACCOUNT_DIVISION
, VENDER.ACCOUNT_NO
, ADVANCE_DIVISION
, NVL((SELECT SUM(TAX_AMOUNT)
     FROM CLAIM_INVOICE_TAX WHERE CLAIM_NO = CLAIM_HEADER.CLAIM_NO), 0) AS IN_TAX_AMOUNT
, NVL((SELECT SUM(TAX_AMOUNT)
     FROM CLAIM_SALES_TAX WHERE CLAIM_NO = CLAIM_HEADER.CLAIM_NO), 0) AS SA_TAX_AMOUNT
, NVL((SELECT SUM(TAX_AMOUNT * CASE WHEN SALES.CATEGORY_DIVISION IN ( 2 , 3 , -1 , -4 , 12 , 13 , -11 , -14) THEN -1 ELSE 1 END )
     FROM SALES WHERE CLAIM_NO = CLAIM_HEADER.CLAIM_NO), 0) AS MEI_TAX_AMOUNT

FROM CLAIM_HEADER
, VENDER
, BANK

WHERE VENDER.VENDER_DIVISION(+) = 'TS'
AND CLAIM_HEADER.VENDER_CD = VENDER.VENDER_CD(+)
AND VENDER.BANK_CD = BANK.BANK_MASTER_CD(+)
) CLM_HED

, ORGANIZATION
, CLASSIFICATION CLS
, LOGIN ISSUER
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE CLM_HED.ORGANIZATION_CD IS NOT NULL
--AND (CLM_HED.CLAIM_AMOUNT - CLM_HED.BALANCE_FORWARD) > 0
AND ADVANCE_DIVISION = 1 -- 前受金区分 = 対象でない

/*IF (condition.srhOrganizationCd != null && condition.srhOrganizationCd != "")*/
AND	(CLM_HED.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null && condition.srhTantoCd != "")*/
AND	(CLM_HED.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhVenderCd != null && condition.srhVenderCd != "")*/
AND	(CLM_HED.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR CLM_HED.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (condition.srhCreditDateFrom != null && condition.srhCreditDateFrom != "")*/
AND	CLM_HED.CREDIT_DATE >= /*condition.srhCreditDateFrom*/'20090601'
/*END*/

/*IF (condition.srhCreditDateTo != null && condition.srhCreditDateTo != "")*/
AND	CLM_HED.CREDIT_DATE <= /*condition.srhCreditDateTo*/'20090630'
/*END*/

/*IF (condition.srhCreditScheduledDateFrom != null && condition.srhCreditScheduledDateFrom != "")*/
AND	CLM_HED.CREDIT_SCHEDULED_DATE >= /*condition.srhCreditScheduledDateFrom*/'20090601'
/*END*/

/*IF (condition.srhCreditScheduledDateTo != null && condition.srhCreditScheduledDateTo != "")*/
AND	CLM_HED.CREDIT_SCHEDULED_DATE <= /*condition.srhCreditScheduledDateTo*/'20090630'
/*END*/

/*IF (condition.srhBankMasterCd != null && condition.srhBankMasterCd != "")*/
AND	(CLM_HED.BANK_MASTER_CD LIKE /*condition.srhBankMasterCd*/'%' OR CLM_HED.BANK_MASTER_NAME LIKE /*condition.srhBankMasterName*/'%')
/*END*/

/*IF (condition.srhCreditDivision != null && condition.srhCreditDivision != "")*/
AND	CLM_HED.CREDIT_DIVISION = /*condition.srhCreditDivision*/'%'
/*END*/

AND CLM_HED.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND CLS.DATA_TYPE(+) = 2 --入金
AND CLM_HED.CREDIT_DIVISION = CLS.CATEGORY_DIVISION(+)
AND CLM_HED.ISSUER_CD = ISSUER.TANTO_CD(+)
AND CLM_HED.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND CLM_HED.UPDATOR_CD = UPDATOR.TANTO_CD(+)

/*IF (condition.srhOutputDivision == "1" )*/
--入金予定日別
ORDER BY CLM_HED.CREDIT_SCHEDULED_DATE, CLM_HED.VENDER_CD
/*END*/

/*IF (condition.srhOutputDivision == "2")*/
--取引先別
ORDER BY CLM_HED.VENDER_CD, CLM_HED.CREDIT_SCHEDULED_DATE
/*END*/

/*IF (condition.srhOutputDivision == "3")*/
--入金方法別
ORDER BY CLM_HED.CREDIT_DIVISION, CLM_HED.CREDIT_SCHEDULED_DATE, CLM_HED.VENDER_CD
/*END*/

/*IF (condition.srhOutputDivision == "4")*/
--銀行別
ORDER BY BANK_MASTER_CD, CLM_HED.CREDIT_SCHEDULED_DATE, CLM_HED.VENDER_CD
/*END*/
