/*
 * 担当者マスタ一覧用SQL
 *
 * entityName=LoginList
 * packageName=loginlist
 * methodName=getList
 *
 */

SELECT *
FROM
(
	SELECT	LOGIN.ORGANIZATION_CD 	--所属ID
	, 		NVL(OGZ.ORGANIZATION_NAME, '未所属') ORGANIZATION_NAME 	--所属名
	, 		LOGIN.TANTO_CD 			--担当者CODE
	, 		LOGIN.TANTO_NM			--担当者名
	, 		LOGIN.POST_ID 			--役職ID
	, 		NVL(PST.POST_NAME, '未登録') POST_NAME	--役職名

	FROM	(
			 SELECT 	LOGIN.TANTO_CD 			--担当者CODE
				, 		LOGIN.TANTO_NM			--担当者名
				, 		LOGIN.ADMIN_FLG			--管理者フラグ
				, 		BLG.ORGANIZATION_CD		--組織ID
				, 		BLG.POST_ID				--役職ID
			 FROM
				LOGIN
				LEFT JOIN
					(
					 SELECT BELONG.TANTO_CD
					 ,		BELONG.ORGANIZATION_CD
					 ,		BELONG.POST_ID
					 FROM BELONG BELONG
					 ,		(
							 SELECT BELONG.TANTO_CD
							 ,		MIN(BELONG.ORGANIZATION_CD) ORGANIZATION_CD
							 FROM
							 (
								SELECT ORGANIZATION_CD
								,	   TANTO_CD
								FROM BELONG
								WHERE BELONG_KBN = '1'
							 ) BELONG
							 GROUP BY BELONG.TANTO_CD
							) BLG_MAX
					 WHERE BELONG.ORGANIZATION_CD = BLG_MAX.ORGANIZATION_CD
					 AND   BELONG.TANTO_CD = BLG_MAX.TANTO_CD
					) BLG
					ON 		BLG.TANTO_CD 	= LOGIN.TANTO_CD
			) LOGIN
			LEFT JOIN
				ORGANIZATION OGZ
				ON 		LOGIN.ORGANIZATION_CD = OGZ.ORGANIZATION_CD
			LEFT JOIN
				POST 	PST
				ON 		LOGIN.POST_ID 	= PST.POST_ID
	WHERE 	LOGIN.TANTO_CD IS NOT NULL

	/*IF (( condition.srhLoginAdminFlg == "1" ))*/
		AND	LOGIN.TANTO_CD = /*condition.srhLoginTantoCd*/'%'
	/*END*/

	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	(LOGIN.TANTO_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	(LOGIN.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR OGZ.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
	/*END*/
	
	/*IF (( condition.srhPostId != null ) && ( condition.srhPostId != "" ))*/
		AND	(LOGIN.POST_ID LIKE /*condition.srhPostId*/1 OR PST.POST_NAME LIKE /*condition.srhPostId*/'%')
	/*END*/
	
	AND LOGIN.ADMIN_FLG <> '3'	--システム管理者以外

	ORDER BY LOGIN.TANTO_CD
	,		 LOGIN.ORGANIZATION_CD
	,		 LOGIN.POST_ID
) ALL_MST
WHERE ALL_MST.TANTO_CD IS NOT NULL

/*IF (( condition.srhNoBelong != null ) && ( condition.srhNoBelong != "" ))*/
AND ALL_MST.ORGANIZATION_NAME = /*condition.srhNoBelong*/'%'
/*END*/

GROUP BY ORGANIZATION_CD, ORGANIZATION_NAME, TANTO_CD, TANTO_NM, POST_ID, POST_NAME
ORDER BY TANTO_CD
