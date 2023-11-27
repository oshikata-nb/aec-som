/*
 * 勘定科目マスタ一覧用SQL
 *
 * entityName=AccountsList
 * packageName=accountslist
 * methodName=getList
 *
 */

SELECT ACCOUNTS_CD, ACCOUNTS_NAME
FROM ACCOUNTS
WHERE ACCOUNTS_CD IS NOT NULL

/*IF (condition.srhAccountsCd != null) && (condition.srhAccountsCd != "")*/
AND (ACCOUNTS_CD LIKE /*condition.srhAccountsCd*/'%' OR ACCOUNTS_NAME LIKE /*condition.srhAccountsCd*/'%')
/*END*/

ORDER BY ACCOUNTS_CD


