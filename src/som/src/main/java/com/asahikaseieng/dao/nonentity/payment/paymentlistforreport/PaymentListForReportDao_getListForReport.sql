/*
 * 支払入力（リスト)帳票検索用SQL
 *
 * entityName=PaymentListForReport
 * packageName=paymentlistforreport
 * methodName=getListForReport
 *
 */
SELECT PAY.*
, CLS.CATEGORY_NAME
, VEN.VENDER_NAME1 AS VENDER_NAME1
, VEN.VENDER_SHORTED_NAME AS VENDER_SHORTED_NAME
, ORG.ORGANIZATION_NAME
, BANK.BANK_MASTER_NAME
, DEBIT_BUMON.SECTION_NAME DEBIT_SECTION_NAME
, DEBIT_ACCOUNTS.ACCOUNTS_NAME DEBIT_ACCOUNTS_NAME
, CREDIT_BUMON.SECTION_NAME CREDIT_SECTION_NAME
, CREDIT_ACCOUNTS.ACCOUNTS_NAME CREDIT_ACCOUNTS_NAME
, APPROVOR.TANTO_NM APPROVOR_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

, CASE PAY.APPROVAL_STATUS
	WHEN 1 THEN '入力中'
	WHEN 2 THEN '承認依頼中'
	WHEN 3 THEN '承認済'
	ELSE NULL
END APPROVAL_STATUS_NAME

, CASE PAY.NOTE_DIVISION
	WHEN 1 THEN '約束手形'
	WHEN 2 THEN '為替手形'
	ELSE NULL
END NOTE_DIVISION_NAME

FROM CLASSIFICATION CLS
, VENDER VEN
, PAYMENT PAY
, ORGANIZATION ORG
, BANK
, BUMON DEBIT_BUMON
, ACCOUNTS DEBIT_ACCOUNTS
, BUMON CREDIT_BUMON
, ACCOUNTS CREDIT_ACCOUNTS
, LOGIN APPROVOR
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE PAY.DATA_TYPE = /*condition.dataType*/4
AND VEN.VENDER_DIVISION(+) = 'SI'
AND VEN.VENDER_CD(+) = PAY.SUPPLIER_CD
AND CLS.DATA_TYPE(+) = 4 --支払
AND CLS.CATEGORY_DIVISION(+) = PAY.CATEGORY_DIVISION
AND ORG.ORGANIZATION_CD(+) = PAY.ORGANIZATION_CD
AND PAY.BANK_CD = BANK.BANK_MASTER_CD(+)
AND PAY.DEBIT_SECTION_CD = DEBIT_BUMON.SECTION_CD(+)
AND PAY.DEBIT_TITLE_CD = DEBIT_ACCOUNTS.ACCOUNTS_CD(+)
AND PAY.CREDIT_SECTION_CD = CREDIT_BUMON.SECTION_CD(+)
AND PAY.CREDIT_TITLE_CD = CREDIT_ACCOUNTS.ACCOUNTS_CD(+)
AND PAY.APPROVEDBY = APPROVOR.TANTO_CD(+)
AND PAY.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND PAY.UPDATOR_CD = UPDATOR.TANTO_CD(+)
    
/*IF (condition.organizationCd != null && condition.organizationCd != "")*/
AND (PAY.ORGANIZATION_CD LIKE /*condition.organizationCd*/'%' OR ORG.ORGANIZATION_NAME LIKE /*condition.organizationCd*/'%')
/*END*/

/*IF (condition.tantoCd != null && condition.tantoCd != "")*/
AND (PAY.INPUTOR_CD LIKE /*condition.tantoCd*/'%' OR INPUTOR.TANTO_NM LIKE /*condition.tantoCd*/'%')
/*END*/

/*IF (condition.paymentDateFrom != null && condition.paymentDateFrom != "")*/
AND PAY.PAYMENT_DATE >= TO_DATE(/*condition.paymentDateFrom*/'2009/07/01','YYYY/MM/DD')
/*END*/

/*IF (condition.paymentDateTo != null && condition.paymentDateTo != "")*/
AND PAY.PAYMENT_DATE <= TO_DATE(/*condition.paymentDateTo*/'2009/07/31','YYYY/MM/DD')
/*END*/

/*IF (condition.supplierCd != null && condition.supplierCd != "")*/
AND (PAY.SUPPLIER_CD LIKE /*condition.supplierCd*/'%' OR VEN.VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.supplierCd*/'%'))
/*END*/

/*IF (condition.slipNoFrom != null && condition.slipNoFrom != "")*/
AND PAY.SLIP_NO >= /*condition.slipNoFrom*/'%'
/*END*/

/*IF (condition.slipNoTo != null && condition.slipNoTo != "")*/
AND PAY.SLIP_NO <= /*condition.slipNoTo*/'%'
/*END*/

/*IF (condition.categoryDivision != null && condition.categoryDivision != "")*/
AND PAY.CATEGORY_DIVISION = /*condition.categoryDivision*/'%'
/*END*/

/*IF (condition.approvalStatus != null && condition.approvalStatus != "0")*/
AND PAY.APPROVAL_STATUS = /*condition.approvalStatus*/1
/*END*/

/*IF (condition.issuedDivision != null && condition.issuedDivision != "")*/
AND PAY.ISSUED_DIVISION = /*condition.issuedDivision*/'%'
/*END*/

ORDER BY PAYMENT_DATE, SLIP_NO, ROW_NO


