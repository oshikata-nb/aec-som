/*
 * 担当者詳細用SQL
 *
 * entityName=LoginDetail
 * packageName=logindetail
 * methodName=getEntity
 *
 */

SELECT 	LOGIN.TANTO_CD
,		LOGIN.TANTO_NM
,		LOGIN.PASSWORD
,		LOGIN.ACTIVE_FLG
,		LOGIN.DELETE_FLG
,		LOGIN.ADMIN_FLG
,		LOGIN.UPDATE_PASS
,		LOGIN.UPDATE_DATE
FROM 	LOGIN LOGIN
WHERE	LOGIN.TANTO_CD = /*tantoCd*/'%'


