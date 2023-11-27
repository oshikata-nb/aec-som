/*
 * 補助科目一覧用SQL
 *
 * entityName=AccountsSubForAutoComplete
 * packageName=accountssub
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ACCOUNTS_SUB_CD, ACCOUNTS_SUB_NAME
		FROM   ACCOUNTS
		WHERE  ACCOUNTS_SUB_CD IS NOT NULL
			  
/*IF (accountsSubCd != null) && (accountsSubCd != "")*/
		AND    (ACCOUNTS_SUB_CD LIKE /*accountsSubCd*/'%' OR ACCOUNTS_SUB_NAME LIKE /*accountsSubCd*/'%')
/*END*/
		
		ORDER  BY ACCOUNTS_SUB_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
