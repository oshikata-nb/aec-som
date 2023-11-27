/*
 * 担当者に紐づくロール取得用SQL
 *
 * entityName=AuthorityRole
 * packageName=dao.nonentity.authority
 * methodName=getTantoMenuRoleList
 *
 */
SELECT 	DISTINCT
 		MNR.MENU_ROLE_ID 				-- ロールＩＤ
FROM 	LOGIN LOGIN
		INNER JOIN
			TANTO_MENU_ROLE TMR
			ON 		TMR.TANTO_CD 		= LOGIN.TANTO_CD
		INNER JOIN
			MENU_ROLE MNR
			ON 		MNR.MENU_ROLE_ID 	= TMR.MENU_ROLE_ID
WHERE 	LOGIN.TANTO_CD = /*tantoCd*/'nona000001' -- 担当者CODE
ORDER BY MNR.MENU_ROLE_ID
