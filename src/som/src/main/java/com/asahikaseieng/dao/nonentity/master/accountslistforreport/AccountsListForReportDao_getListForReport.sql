/*
 * 勘定科目帳票用SQL
 *
 * entityName=AccountsListForReport
 * packageName=accountslistforreport
 * methodName=getListForReport
 *
 */

SELECT ACCOUNTS.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM ACCOUNTS
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE ACCOUNTS.ACCOUNTS_CD IS NOT NULL

/*IF (condition.srhAccountsCd != null) && (condition.srhAccountsCd != "")*/
AND (ACCOUNTS.ACCOUNTS_CD LIKE /*condition.srhAccountsCd*/'%' OR ACCOUNTS.ACCOUNTS_NAME LIKE /*condition.srhAccountsCd*/'%')
/*END*/

AND ACCOUNTS.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND ACCOUNTS.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY ACCOUNTS.ACCOUNTS_CD


