/*
 * ロールマスタ帳票用SQL
 *
 * entityName=RoleListForReport
 * packageName=rolelistforreport
 * methodName=getListForReport
 *
 */

SELECT *
FROM ROLE
WHERE ROLE_ID IS NOT NULL

/*IF (condition.srhRoleId != null) && (condition.srhRoleId != "")*/
AND	(ROLE_ID LIKE /*condition.srhRoleId*/'1' OR ROLE_NAME LIKE /*condition.srhRoleId*/'1')
/*END*/

ORDER BY ROLE_ID


