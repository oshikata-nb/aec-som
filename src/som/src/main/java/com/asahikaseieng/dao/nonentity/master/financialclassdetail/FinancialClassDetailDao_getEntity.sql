/*
 * 財務分類詳細用SQL
 *
 * entityName=FinancialClassDetail
 * packageName=financialclassdetail
 * methodName=getEntity
 *
 */

SELECT FINANCIAL_CLASS_CD, FINANCIAL_CLASS_NAME, UPDATE_DATE
FROM FINANCIAL_CLASS
WHERE FINANCIAL_CLASS_CD = /*financialClassCd*/'%'


