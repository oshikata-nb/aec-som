/*
 * 科目一覧用SQL
 *
 * entityName=AccountsForAutoComplete
 * packageName=accounts
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ACCOUNTS_CD, ACCOUNTS_NAME
		FROM   ACCOUNTS
		WHERE  ACCOUNTS_CD IS NOT NULL
			  
/*IF (accountsCd != null) && (accountsCd != "")*/
		AND    (ACCOUNTS_CD LIKE /*accountsCd*/'%' OR ACCOUNTS_NAME LIKE /*accountsCd*/'%')
/*END*/
		ORDER  BY ACCOUNTS_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
