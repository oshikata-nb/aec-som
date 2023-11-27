/*
 * 銀行マスタ一覧用SQL
 *
 * entityName=BankList
 * packageName=banklist
 * methodName=getList
 *
 */

SELECT BANK_CD, BANK_KANA_NAME, BANK_NAME, BRANCH_CD, BRANCH_NAME, BANK_MASTER_CD, BANK_MASTER_NAME
FROM BANK
WHERE BANK_CD IS NOT NULL

/*IF (condition.srhBankCd != null) && (condition.srhBankCd != "")*/
AND (BANK_CD LIKE /*condition.srhBankCd*/'%' OR BANK_NAME LIKE /*condition.srhBankCd*/'%')
/*END*/

/*IF (condition.srhBranchCd != null) && (condition.srhBranchCd != "")*/
AND (BRANCH_CD LIKE /*condition.srhBranchCd*/'%' OR BRANCH_NAME LIKE /*condition.srhBranchCd*/'%')
/*END*/

/*IF (condition.srhBankMasterCd != null) && (condition.srhBankMasterCd != "")*/
AND (BANK_MASTER_CD LIKE /*condition.srhBankMasterCd*/'%' OR BANK_MASTER_NAME LIKE /*condition.srhBankMasterCd*/'%')
/*END*/

ORDER BY BANK_MASTER_CD


