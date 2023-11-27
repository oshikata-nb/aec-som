/*
 * ログイン情報取得用SQL
 *
 * entityName=Login
 * packageName=dao.nonentity.login
 * methodName=getEntity
 *
 */
SELECT 	LOGIN.TANTO_CD 			--担当者CODE
, 		LOGIN.TANTO_NM			--担当者名
, 		LOGIN.PASSWORD			--PASSWORD
, 		LOGIN.ACTIVE_FLG		--有効フラグ
, 		LOGIN.UPDATE_PASS		--パスワード変更日時, 		LOGIN.ADMIN_FLG			--管理者フラグ
, 		OGZ.ORGANIZATION_ID 	--所属ID
, 		OGZ.ORGANIZATION_NAME 	--所属名
, 		OGZ.SECTION_CD 	        --部門コード
, 		PST.POST_ID 			--役職ID
, 		PST.POST_NAME 			--役職名
FROM 	(
		 SELECT 	LOGIN.TANTO_CD 			--担当者CODE
			, 		LOGIN.TANTO_NM			--担当者名
			, 		LOGIN.PASSWORD			--PASSWORD
			, 		LOGIN.ACTIVE_FLG		--有効フラグ
			, 		LOGIN.UPDATE_PASS		--パスワード変更日時
			, 		LOGIN.ADMIN_FLG			--管理者フラグ
			, 		BLG.ORGANIZATION_ID		--組織ID
			, 		BLG.POST_ID				--役職ID
		 FROM
			LOGIN
			LEFT JOIN
				BELONG 	BLG
				ON 		BLG.TANTO_CD 	= LOGIN.TANTO_CD
				AND 	BLG.BELONG_KBN 	= '0' -- 主所属(一意)
		 WHERE 	LOGIN.TANTO_CD = /*tantoCd*/'nona000001' -- 担当者CODE
		 AND	LOGIN.DELETE_FLG = '0'
		 AND	ROWNUM <= 1
		) LOGIN
		LEFT JOIN
			ORGANIZATION OGZ
			ON 		OGZ.ORGANIZATION_ID = LOGIN.ORGANIZATION_ID
		LEFT JOIN
			POST 	PST
			ON 		PST.POST_ID 	= LOGIN.POST_ID
