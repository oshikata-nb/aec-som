/*
 * 部署マスタ一覧用SQL
 *
 * entityName=OrganizationList
 * packageName=organizationlist
 * methodName=getList
 *
 */

SELECT ORGANIZATION.ORGANIZATION_CD, ORGANIZATION.ORGANIZATION_NAME, SUBSTRB(ORGANIZATION_NAME, 0, 88) SHORT_ORGANIZATION_NAME, ORGANIZATION.PARENT_ORGANIZATION_CD
FROM ORGANIZATION
WHERE ORGANIZATION.ORGANIZATION_CD IS NOT NULL

/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(ORGANIZATION.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

ORDER BY ORGANIZATION.ORGANIZATION_CD


