/*
 * 銀行マスタ帳票用SQL
 *
 * entityName=BankListForReport
 * packageName=banklistforreport
 * methodName=getListForReport
 *
 */

SELECT BANK.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM BANK
, LOGIN INPUTOR
, LOGIN UPDATOR

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

AND BANK.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND BANK.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY BANK_MASTER_CD


