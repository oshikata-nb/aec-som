/*
 * ＦＢデータ作成 ＦＢヘッダー用SQL
 *
 * entityName=FbdataMakingHeader
 * packageName=payment.fbdatamaking
 * methodName=getSearchBankInfo
 *
 */
SELECT 	'1'											AS DATA_DIVISION
,		'21'										AS DIVISION_CD
,		'0'											AS CATEGORY_DIVISION
,		COMPANY.TRANSFER_CLIENT_CD					AS TRANSFER_CLIENT_CD
,		COMPANY.TRANSFER_CLIENT_NAME				AS TRANSFER_CLIENT_NAME
,		TO_CHAR(TO_DATE(/*paymentDate*/),'MMDD')	AS TRASFER_DATE
,		BANK.BANK_CD								AS BANK_MASTER_CD
,		SUBSTR(BANK.BANK_KANA_NAME, 1, 15)			AS BANK_KANA_NAME
,		BANK.BRANCH_CD								AS BRANCH_CD
,		SUBSTR(BANK.BRANCH_KANA_NAME, 1, 15)		AS BRANCH_KANA_NAME
,		COMPANY.ACCOUNT_DIVISION					AS ACCOUNT_DIVISION
,		COMPANY.ACCOUNT_NO							AS ACCOUNT_NO
,		'                 '							AS DUMMY
,		TO_CHAR(TO_DATE(/*paymentDate*/),'YYYYMMDD')	AS PAYMENT_DATE
FROM 	COMPANY
,		BANK
WHERE	BANK.BANK_MASTER_CD = COMPANY.BANK_MASTER_CD