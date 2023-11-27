/*
 * 部署一覧用SQL
 *
 * entityName=OrganizationForAutoComplete
 * packageName=organization
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ORGANIZATION_CD, ORGANIZATION_NAME
		FROM   ORGANIZATION
		WHERE  ORGANIZATION_CD IS NOT NULL
			  
/*IF (organizationCd != null) && (organizationCd != "")*/
		AND    (ORGANIZATION_CD LIKE /*organizationCd*/'%' OR ORGANIZATION_NAME LIKE /*organizationCd*/'%')
/*END*/
		
		ORDER  BY ORGANIZATION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
