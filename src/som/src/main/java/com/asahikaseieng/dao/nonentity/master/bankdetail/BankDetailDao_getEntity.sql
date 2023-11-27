/*
 * 銀行マスタ詳細用SQL
 *
 * entityName=BankDetail
 * packageName=bankdetail
 * methodName=getEntity
 *
 */

SELECT BANK_CD, BANK_KANA_NAME, BANK_NAME, BRANCH_CD, BRANCH_KANA_NAME, BRANCH_NAME, BANK_MASTER_CD,
BANK_MASTER_NAME, UPDATE_DATE
FROM BANK
WHERE BANK_MASTER_CD = /*bankMasterCd*/'%'


