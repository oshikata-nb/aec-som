/*
 * 銀行一覧用SQL
 *
 * entityName=BankForAutoComplete
 * packageName=bank
 * methodName=getBankSearchList
 *
*/

SELECT *
FROM   (SELECT BANK_CD, BANK_NAME, MAX(BRANCH_NAME) BRANCH_NAME
		FROM   BANK
		WHERE  BANK_CD IS NOT NULL
			  
/*IF (bankCd != null) && (bankCd != "")*/
		AND    (BANK_CD LIKE /*bankCd*/'%' OR BANK_NAME LIKE /*bankCd*/'%')
/*END*/
		
		GROUP  BY BANK_CD, BANK_NAME
		ORDER  BY BANK_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
