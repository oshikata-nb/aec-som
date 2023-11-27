/*
 * 銀行一覧用SQL
 *
 * entityName=BankForAutoComplete
 * packageName=bank
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT BANK_CD
			  ,BANK_NAME
			  ,BRANCH_CD
			  ,BRANCH_NAME
			  ,BANK_MASTER_CD
			  ,BANK_MASTER_NAME
		FROM   BANK
		WHERE  BANK_MASTER_CD IS NOT NULL
			  
/*IF (bankMasterCd != null) && (bankMasterCd != "")*/
		AND    (BANK_MASTER_CD LIKE /*bankMasterCd*/'%' OR BANK_MASTER_NAME LIKE /*bankMasterCd*/'%')
/*END*/
		
		ORDER  BY BANK_MASTER_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
