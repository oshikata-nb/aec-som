/*
 * 財務分類一覧用SQL
 *
 * entityName=FinancialClassForAutoComplete
 * packageName=financialclass
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT FINANCIAL_CLASS_CD, FINANCIAL_CLASS_NAME
		FROM   FINANCIAL_CLASS
		WHERE  FINANCIAL_CLASS_CD IS NOT NULL
			  
/*IF (financialClassCd != null) && (financialClassCd != "")*/
		AND    (FINANCIAL_CLASS_CD LIKE /*financialClassCd*/'%' OR FINANCIAL_CLASS_NAME LIKE /*financialClassCd*/'%')
/*END*/
		
		ORDER  BY FINANCIAL_CLASS_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
