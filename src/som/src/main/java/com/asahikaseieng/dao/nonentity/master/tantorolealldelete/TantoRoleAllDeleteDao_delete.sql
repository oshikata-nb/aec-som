/*
 * 担当者ロール全削除用SQL
 *
 * entityName=TantoRoleAllDelete
 * packageName=tantorolealldelete
 * methodName=delete
 *
 */

DELETE
FROM TANTO_ROLE
WHERE TANTO_CD IS NOT NULL

/*IF (( bean.tantoCd != null ) && ( bean.tantoCd != "" ))*/
AND TANTO_CD = /*bean.tantoCd*/'%'
/*END*/
