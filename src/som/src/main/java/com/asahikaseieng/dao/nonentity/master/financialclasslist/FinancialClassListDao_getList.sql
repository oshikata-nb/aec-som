/*
 * 財務分類マスタ一覧用SQL
 *
 * entityName=FinancialClassList
 * packageName=financialclasslist
 * methodName=getList
 *
 */

SELECT FINANCIAL_CLASS_CD, FINANCIAL_CLASS_NAME
FROM FINANCIAL_CLASS
WHERE FINANCIAL_CLASS_CD IS NOT NULL

/*IF (condition.srhFinancialClassCd != null) && (condition.srhFinancialClassCd != "")*/
AND (FINANCIAL_CLASS_CD LIKE /*condition.srhFinancialClassCd*/'%' OR FINANCIAL_CLASS_NAME LIKE /*condition.srhFinancialClassCd*/'%')
/*END*/

ORDER BY FINANCIAL_CLASS_CD


