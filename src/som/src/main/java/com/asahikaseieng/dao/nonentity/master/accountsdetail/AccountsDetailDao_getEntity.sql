/*
 * 勘定科目詳細用SQL
 *
 * entityName=AccountsDetail
 * packageName=accountsdetail
 * methodName=getEntity
 *
 */

SELECT ACCOUNTS_CD, ACCOUNTS_NAME, TAXATION_DIVISION, PURCHASE_ACCOUNTS, ARTICLE_ACCOUNTS, UPDATE_DATE
FROM ACCOUNTS
WHERE ACCOUNTS_CD = /*accountsCd*/'%'


