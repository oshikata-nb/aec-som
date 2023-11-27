/*
 * 科目マスタ一覧用SQL
 *
 * entityName=Accounts
 * packageName=accounts
 * methodName=getNameList
 *
 */

SELECT *
FROM ACCOUNTS
WHERE ACCOUNTS_CD IS NOT NULL

/*IF (accountsCd != null) && (accountsCd != "")*/
AND ACCOUNTS_CD LIKE /*accountsCd*/
/*END*/

ORDER BY ACCOUNTS_CD
,        ACCOUNTS_SUB_CD
