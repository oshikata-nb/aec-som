/*
 * 所属マスタ帳票用SQL
 *
 * entityName=BelongListForReport
 * packageName=belonglistforreport
 * methodName=getListForReport
 *
 */

SELECT BELONG.*
, ORGANIZATION_NAME
, TANTO_NM
, POST_NAME

FROM BELONG
, ORGANIZATION
, LOGIN
, POST

WHERE BELONG.ORGANIZATION_CD IS NOT NULL

/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(BELONG.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhTantoCd != null) && (condition.srhTantoCd != "")*/
AND	(BELONG.TANTO_CD LIKE /*condition.srhTantoCd*/'%' OR TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (condition.srhPostId != null) && (condition.srhPostId != "")*/
AND	(BELONG.POST_ID LIKE /*condition.srhPostId*/'%' OR POST_NAME LIKE /*condition.srhPostId*/'1')
/*END*/

AND BELONG.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND BELONG.TANTO_CD = LOGIN.TANTO_CD(+)
AND BELONG.POST_ID = POST.POST_ID(+)

ORDER BY BELONG.ORGANIZATION_CD, BELONG.TANTO_CD, BELONG.POST_ID


