/*
 * 担当者役職一覧用SQL
 *
 * entityName=LoginRoleList
 * packageName=loginrolelist
 * methodName=getList
 *
 */

SELECT 	TANTO_ROLE.ROLE_ID
FROM 	LOGIN LOGIN
		LEFT JOIN
			TANTO_ROLE  TANTO_ROLE
			ON 		LOGIN.TANTO_CD 	= TANTO_ROLE.TANTO_CD
WHERE	LOGIN.TANTO_CD IS NOT NULL
AND		LOGIN.TANTO_CD = /*tantoCd*/'1'
ORDER BY TANTO_ROLE.ROLE_ID


