/*
 * 財務分類マスタ帳票用SQL
 *
 * entityName=FinancialClassListForReport
 * packageName=financialclasslistforreport
 * methodName=getListForReport
 *
 */

SELECT FINANCIAL.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM FINANCIAL_CLASS FINANCIAL
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE FINANCIAL.FINANCIAL_CLASS_CD IS NOT NULL

/*IF (condition.srhFinancialClassCd != null) && (condition.srhFinancialClassCd != "")*/
AND (FINANCIAL.FINANCIAL_CLASS_CD LIKE /*condition.srhFinancialClassCd*/'%' OR FINANCIAL.FINANCIAL_CLASS_NAME LIKE /*condition.srhFinancialClassCd*/'%')
/*END*/

AND FINANCIAL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND FINANCIAL.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY FINANCIAL.FINANCIAL_CLASS_CD


