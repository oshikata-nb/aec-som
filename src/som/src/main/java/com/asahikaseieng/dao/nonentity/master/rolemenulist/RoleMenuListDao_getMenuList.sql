/*
 * メニュー精製用SQL
 *
 * entityName=RoleMenuList
 * packageName=rolemenulist
 * methodName=getMenuList
 *
 */

SELECT
	 MENU_HEAD.KBN				-- メニュー区分(1:メニュー 2:タブ 3:操作)
	,MENU_HEAD.MENU_ID			-- メニューID
	,MENU_HEAD.MENU_NAME		-- メニュー名称
	,MENU_HEAD.PARENT_MENU_ID	-- 親メニューID
	,MENU_HEAD.MENU_TYPE_ID		-- メニュータイプID
	,MENU_HEAD.SORT_NO			-- ソート順序

	,MENU_HEAD.TAB_ID			-- タブID
	,MENU_HEAD.TAB_NAME			-- タブ名称
	,MENU_HEAD.CONTROL_ID		-- 操作ID
	,MENU_HEAD.CONTROL_NAME		-- 操作名称
	,MTYPE.FILE_KBN				-- ファイル区分

	,MTYPE.IMG_NAME				-- 画像名
	,MTYPE.OPEN_IMG_NAME		-- OPEN画像名
	,MTYPE.CLOSE_IMG_NAME		-- CLOSE画像名
FROM (
	SELECT
		 HEAD.MENU_ID AS MENU_ID
		,HEAD.MENU_NAME AS MENU_NAME
		,HEAD.PARENT_MENU_ID AS PARENT_MENU_ID
		,HEAD.MENU_TYPE_ID AS MENU_TYPE_ID
		,HEAD.SORT_NO AS SORT_NO
		,NULL AS TAB_ID
		,NULL AS TAB_NAME
		,NULL AS CONTROL_ID
		,NULL AS CONTROL_NAME
		,DECODE(HEAD.MENU_TYPE_ID, '1', '0', '1') AS KBN
	FROM MENU_HEAD HEAD
	 UNION ALL
	SELECT
		 HEAD.MENU_ID AS MENU_ID
		,HEAD.MENU_NAME AS MENU_NAME
		,HEAD.PARENT_MENU_ID AS PARENT_MENU_ID
		,HEAD.MENU_TYPE_ID AS MENU_TYPE_ID
		,HEAD.SORT_NO AS SORT_NO
		,DETAIL.TAB_ID AS TAB_ID
		,DETAIL.TAB_NAME AS TAB_NAME
		,NULL AS CONTROL_ID
		,NULL AS CONTROL_NAME
		,'2' AS KBN
	FROM MENU_HEAD HEAD
		 INNER JOIN
			MENU_DETAIL DETAIL
			ON HEAD.MENU_ID = DETAIL.MENU_ID
--			AND DETAIL.TAB_ID <> '0'
	 UNION ALL
	SELECT
		 HEAD.MENU_ID AS MENU_ID
		,HEAD.MENU_NAME AS MENU_NAME
		,HEAD.PARENT_MENU_ID AS PARENT_MENU_ID
		,HEAD.MENU_TYPE_ID AS MENU_TYPE_ID
		,HEAD.SORT_NO AS SORT_NO
		,DETAIL.TAB_ID AS TAB_ID
		,DETAIL.TAB_NAME AS TAB_NAME
		,0 AS CONTROL_ID
		,N'閲覧' AS CONTROL_NAME
		,'3' AS KBN
	FROM MENU_HEAD HEAD
		 INNER JOIN
			MENU_DETAIL DETAIL
			ON HEAD.MENU_ID = DETAIL.MENU_ID
	 UNION ALL
	SELECT
		 HEAD.MENU_ID AS MENU_ID
		,HEAD.MENU_NAME AS MENU_NAME
		,HEAD.PARENT_MENU_ID AS PARENT_MENU_ID
		,HEAD.MENU_TYPE_ID AS MENU_TYPE_ID
		,HEAD.SORT_NO AS SORT_NO
		,DETAIL.TAB_ID AS TAB_ID
		,DETAIL.TAB_NAME AS TAB_NAME
		,CTRL.CONTROL_ID AS CONTROL_ID
		,CTRL.CONTROL_NAME AS CONTROL_NAME
		,'3' AS KBN
	FROM MENU_HEAD HEAD
		 INNER JOIN
			MENU_DETAIL DETAIL
			ON HEAD.MENU_ID = DETAIL.MENU_ID
		 INNER JOIN
			CONTROL CTRL
			ON DETAIL.MENU_ID = CTRL.MENU_ID
			AND DETAIL.TAB_ID = CTRL.TAB_ID
	) MENU_HEAD
	LEFT OUTER JOIN
	MENU_TYPE MTYPE
		ON MENU_HEAD.MENU_TYPE_ID = MTYPE.MENU_TYPE_ID
ORDER BY
	 MENU_ID
	,KBN
	,TAB_ID
	,CONTROL_ID


