/*
 * 組織・役職に紐づくロール取得用SQL
 *
 * entityName=AuthorityRole
 * packageName=dao.nonentity.authority
 * methodName=getBelongRoleList
 *
 */
SELECT 	DISTINCT
 		R.ROLE_ID 				-- ロールＩＤ
FROM 	LOGIN LOGIN
		INNER JOIN
			BELONG 	BLG
			ON 		BLG.TANTO_CD 		= LOGIN.TANTO_CD
		INNER JOIN
			ORGANIZATION OGZ
			ON 		OGZ.ORGANIZATION_CD = BLG.ORGANIZATION_CD
		INNER JOIN
			POST POST
			ON 		POST.POST_ID = BLG.POST_ID
		INNER JOIN
			BELONG_ROLE BR
			ON 		BR.ORGANIZATION_CD = OGZ.ORGANIZATION_CD
			AND		BR.POST_ID = BLG.POST_ID
		INNER JOIN
			ROLE R
			ON 		R.ROLE_ID = BR.ROLE_ID
WHERE 	LOGIN.TANTO_CD = /*tantoCd*/'nona000001' -- 担当者CODE

/*IF (organizationCd != null) && (organizationCd != "")*/
AND	BLG.ORGANIZATION_CD = /*organizationCd*/'%'
/*END*/

/*IF (postId != null) && (postId != "")*/
AND	BLG.POST_ID = /*postId*/1
/*END*/

ORDER BY R.ROLE_ID
