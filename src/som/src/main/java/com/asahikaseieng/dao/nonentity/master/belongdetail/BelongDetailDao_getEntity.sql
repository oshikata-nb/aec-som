/*
 * 所属マスタ詳細用SQL
 *
 * entityName=BelongDetail
 * packageName=belongdetail
 * methodName=getEntity
 *
 */

SELECT BELONG.ORGANIZATION_CD, ORGANIZATION_NAME, BELONG.TANTO_CD, TANTO_NM, BELONG.POST_ID, POST_NAME, BELONG_KBN
FROM BELONG, ORGANIZATION, LOGIN, POST

WHERE BELONG.ORGANIZATION_CD = /*organizationCd*/'%'
AND BELONG.TANTO_CD = /*tantoCd*/'%'
AND BELONG.POST_ID = /*postId*/'1'

/*IF (belongKbn != null) && (belongKbn != "")*/
AND BELONG.BELONG_KBN = /*belongKbn*/1
/*END*/

AND BELONG.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND BELONG.TANTO_CD = LOGIN.TANTO_CD(+)
AND BELONG.POST_ID = POST.POST_ID(+)
