/*
 * 銀行一覧用SQL
 *
 * entityName=BankForAutoComplete
 * packageName=bank
 * methodName=getBranchSearchList
 *
*/

SELECT *
FROM   (SELECT BRANCH_CD, BRANCH_NAME
		FROM   BANK
		WHERE  BRANCH_CD IS NOT NULL
			  
/*IF (bankCd != null) && (bankCd != "")*/
		AND    (BANK_CD LIKE /*bankCd*/'%' OR BANK_NAME LIKE /*bankCd*/'%')
/*END*/
			  
/*IF (branchCd != null) && (branchCd != "")*/
		AND    (BRANCH_CD LIKE /*branchCd*/'%' OR BRANCH_NAME LIKE /*branchCd*/'%')
/*END*/
		
		GROUP  BY BRANCH_CD, BRANCH_NAME
		ORDER  BY BRANCH_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
