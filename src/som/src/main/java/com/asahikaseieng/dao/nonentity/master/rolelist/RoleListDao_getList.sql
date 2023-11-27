/*
 * ロールマスタ一覧用SQL
 *
 * entityName=RoleList
 * packageName=rolelist
 * methodName=getList
 *
 */

SELECT ROLE_ID, ROLE_NAME, SUBSTR(ROLE_NAME, 0, 43) SHORT_ROLE_NAME
FROM ROLE
WHERE ROLE_ID IS NOT NULL

/*IF (condition.srhRoleId != null) && (condition.srhRoleId != "")*/
AND	(ROLE_ID LIKE /*condition.srhRoleId*/'1' OR ROLE_NAME LIKE /*condition.srhRoleId*/'1')
/*END*/

ORDER BY ROLE_ID


