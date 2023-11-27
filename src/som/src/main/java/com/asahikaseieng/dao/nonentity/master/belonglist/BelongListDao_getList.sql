/*
 * 所属マスタ一覧用SQL
 *
 * entityName=BelongList
 * packageName=belonglist
 * methodName=getList
 *
 */

SELECT BELONG.ORGANIZATION_CD, ORGANIZATION_NAME, SUBSTRB(ORGANIZATION_NAME, 0, 18) SHORT_ORGANIZATION_NAME, BELONG.TANTO_CD, TANTO_NM, SUBSTRB(TANTO_NM, 0, 8) SHORT_TANTO_NM, BELONG.POST_ID, POST_NAME, SUBSTRB(POST_NAME, 0, 10) SHORT_POST_NAME, NVL(BELONG_KBN, 0) BELONG_KBN
FROM BELONG, ORGANIZATION, LOGIN, POST
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
