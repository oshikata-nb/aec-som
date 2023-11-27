/*
 * 所属・ロール組合せマスタ帳票用SQL
 *
 * entityName=BelongRoleListForReport
 * packageName=belongrolelistforreport
 * methodName=getListForReport
 *
 */

SELECT BELONG_ROLE.*
, ORGANIZATION.ORGANIZATION_NAME
, POST.POST_NAME
, ROLE.ROLE_NAME

FROM BELONG_ROLE, ORGANIZATION, POST, ROLE
WHERE BELONG_ROLE.ORGANIZATION_CD IS NOT NULL

/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(BELONG_ROLE.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhPostId != null) && (condition.srhPostId != "")*/
AND	(BELONG_ROLE.POST_ID LIKE /*condition.srhPostId*/'%' OR POST_NAME LIKE /*condition.srhPostId*/'1')
/*END*/

/*IF (condition.srhRoleId != null) && (condition.srhRoleId != "")*/
AND	(BELONG_ROLE.ROLE_ID LIKE /*condition.srhRoleId*/'%' OR ROLE_NAME LIKE /*condition.srhRoleId*/'1')
/*END*/

AND BELONG_ROLE.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND BELONG_ROLE.POST_ID = POST.POST_ID(+)
AND BELONG_ROLE.ROLE_ID = ROLE.ROLE_ID(+)

ORDER BY BELONG_ROLE.ORGANIZATION_CD, BELONG_ROLE.POST_ID, BELONG_ROLE.ROLE_ID
