/*
 * 担当者に紐づくロール取得用SQL
 *
 * entityName=AuthorityRole
 * packageName=dao.nonentity.authority
 * methodName=getTantoRoleList
 *
 */
SELECT 	DISTINCT
 		R.ROLE_ID 				-- ロールＩＤ
FROM 	LOGIN LOGIN
		INNER JOIN
			TANTO_ROLE TR
			ON TR.TANTO_CD = LOGIN.TANTO_CD
		INNER JOIN
			ROLE R
			ON R.ROLE_ID = TR.ROLE_ID
		INNER JOIN
			BELONG 	BLG
			ON 		BLG.TANTO_CD 		= LOGIN.TANTO_CD
WHERE 	LOGIN.TANTO_CD = /*tantoCd*/'nona000001' -- 担当者CODE

/*IF (organizationCd != null) && (organizationCd != "")*/
AND	BLG.ORGANIZATION_CD = /*organizationCd*/'%'
/*END*/

/*IF (postId != null) && (postId != "")*/
AND	BLG.POST_ID = /*postId*/1
/*END*/

ORDER BY R.ROLE_ID
