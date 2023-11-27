/*
 * ロールマスタ新規画面表示SQL
 *
 * entityName=RoleMenu
 * packageName=role
 * methodName=getMenuList
 *
 */
SELECT
	MENU_HEAD.MENU_ID			-- メニューID
,	MENU_HEAD.MENU_NAME			-- メニュー名称
,	MENU_HEAD.PARENT_MENU_ID	-- 親メニューID
,	MENU_HEAD.MENU_TYPE_ID		-- メニュータイプID
,	MENU_HEAD.SORT_NO			-- ソート順序
,	MENU_HEAD.ACTION			-- アクション
,	MTYPE.FILE_KBN			-- ファイル区分
,	MTYPE.IMG_NAME			-- 画像名
,	MTYPE.OPEN_IMG_NAME	-- OPEN画像名
,	MTYPE.CLOSE_IMG_NAME	-- CLOSE画像名
FROM
	MENU_HEAD
	INNER JOIN
	(
		SELECT MENU_ID
		FROM (
		/*IF tantoRoleIds.length > 0*/
			SELECT
				 VAUTH.MENU_ID			-- メニューID
			FROM ROLE ROLE
				 INNER JOIN
					VIEW_AUTHORITY VAUTH
					ON ROLE.ROLE_ID = VAUTH.ROLE_ID
			WHERE
				ROLE.ROLE_ID IN /*tantoRoleIds*/('1','2')
		/*END*/
		/*IF (tantoRoleIds.length > 0 && belongRoleIds.length > 0)*/
			UNION
		/*END*/
		/*IF belongRoleIds.length > 0*/
			SELECT
				 VAUTH.MENU_ID			-- メニューID
			FROM ROLE ROLE
				 INNER JOIN
					VIEW_AUTHORITY VAUTH
					ON ROLE.ROLE_ID = VAUTH.ROLE_ID
			WHERE
				ROLE.ROLE_ID IN /*belongRoleIds*/('1','2')
		/*END*/
			 )
		GROUP BY MENU_ID
		ORDER BY
			 MENU_ID
	) AUTH
		ON  MENU_HEAD.MENU_ID = AUTH.MENU_ID
	LEFT OUTER JOIN
	MENU_TYPE MTYPE
		ON MENU_HEAD.MENU_TYPE_ID = MTYPE.MENU_TYPE_ID
ORDER BY
	 MENU_HEAD.MENU_ID
