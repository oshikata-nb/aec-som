/*
 * 自社マスタ一覧用SQL
 *
 * entityName=CompanyList
 * packageName=companylist
 * methodName=getList
 *
 */

SELECT 	COMPANY.COMPANY_CD
,		COMPANY.HOME_NAME1
,		COMPANY.ADDRESS1
FROM 	COMPANY

WHERE	COMPANY.COMPANY_CD IS NOT NULL

/*IF((condition.srhHomeName1 != null) && (condition.srhHomeName1 != ""))*/
AND	COMPANY.HOME_NAME1 LIKE /*condition.srhHomeName1*/'%'
/*END*/

ORDER BY COMPANY.COMPANY_CD
