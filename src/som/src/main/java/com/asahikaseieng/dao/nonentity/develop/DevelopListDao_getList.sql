/*
 * 開発依頼検索一覧SQL文
 *
 * entityName=DevelopList
 * packageName=develop
 * methodName=getList
 */
SELECT	DEV.DEVELOPMENT_REQUEST_NO
,		CASE 
			WHEN DEV.STATUS = 1 THEN '登録済'
			WHEN DEV.STATUS = 2 THEN '開発依頼承認依頼中'
			WHEN DEV.STATUS = 3 THEN '開発依頼承認済'
			WHEN DEV.STATUS = 4 THEN '受付済(開発中)'
			WHEN DEV.STATUS = 5 THEN '責任者承認依頼中'
			WHEN DEV.STATUS = 6 THEN '責任者承認済'
			WHEN DEV.STATUS = 7 THEN '研究開発承認済(開発完了)'
			WHEN DEV.STATUS = 8 THEN '研究開発中止'
			WHEN DEV.STATUS = 50 THEN '開発依頼否認'
			WHEN DEV.STATUS = 51 THEN '研究開発否認'
			ELSE '新規登録'
		END STATUS_NAME
,		ITEM_QUEUE.ITEM_CD
,		ITEM_QUEUE.ITEM_NAME
,		VENDER.VENDER_NAME
,		LOGIN.TANTO_NM DEVELOP_ACCEPT_TANTO_NAME
,		DEV.FINISHED_DATE
FROM	LOGIN LOGIN
,		DEVELOPMENT_REQUEST DEV
,		(
			SELECT	ITEM_TEMP.ITEM_CD
			,		ITEM_QUEUE.ITEM_NAME
			,		ITEM_TEMP.DEVELOPMENT_REQUEST_NO FROM ITEM_QUEUE
			, 		(
						SELECT	ITEM_DEV.DEVELOPMENT_REQUEST_NO
						,	ITEM_DEV.VERSION
						,	MAX(ITEM_QUEUE.ITEM_CD) ITEM_CD
						FROM	ITEM_QUEUE ,
							(
								SELECT	MAX(ITEM_QUEUE.VERSION)	VERSION
								,	ITEM_QUEUE.DEVELOPMENT_REQUEST_NO
								FROM	ITEM_QUEUE , DEVELOPMENT_REQUEST
								WHERE	ITEM_QUEUE.DEVELOPMENT_REQUEST_NO = DEVELOPMENT_REQUEST.DEVELOPMENT_REQUEST_NO
								GROUP BY ITEM_QUEUE.DEVELOPMENT_REQUEST_NO
							) ITEM_DEV
						WHERE ITEM_QUEUE.DEVELOPMENT_REQUEST_NO IS NOT NULL
						AND ITEM_QUEUE.DEVELOPMENT_REQUEST_NO = ITEM_DEV.DEVELOPMENT_REQUEST_NO
						GROUP BY ITEM_DEV.DEVELOPMENT_REQUEST_NO , ITEM_DEV.VERSION
					) ITEM_TEMP


WHERE ITEM_TEMP.ITEM_CD = ITEM_QUEUE.ITEM_CD
AND	ITEM_TEMP.VERSION = ITEM_QUEUE.VERSION

ORDER BY ITEM_TEMP.DEVELOPMENT_REQUEST_NO
) ITEM_QUEUE
,	(
		SELECT	VENDER.VENDER_CD
		FROM	VENDER
		WHERE	VENDER.VENDER_DIVISION = 'TS'
		/*IF(condition.srhVenderName != null) && (condition.srhVenderName != "")*/
		AND	VENDER.VENDER_NAME LIKE /*condition.srhVenderName*/'%'
		/*END*/
	)	VENDER_NM_CD
,	(
		SELECT	LOGIN.TANTO_CD
		FROM	LOGIN
		WHERE	LOGIN.TANTO_CD IS NOT NULL
		/*IF(condition.srhRequestTantoCd != null) && (condition.srhRequestTantoCd != "")*/
		AND	LOGIN.TANTO_CD LIKE /*condition.srhRequestTantoCd*/'%'
		/*END*/
	)	LOGIN_RQ_TANTO_CD
,	(
		SELECT	LOGIN.TANTO_CD
		FROM	LOGIN
		WHERE	LOGIN.TANTO_CD IS NOT NULL
		/*IF(condition.srhRequestTantoName != null) && (condition.srhRequestTantoName != "")*/
		AND	LOGIN.TANTO_NM LIKE /*condition.srhRequestTantoName*/'%'
		/*END*/
	)	LOGIN_RQ_TANTO_NM
,	(
		SELECT	LOGIN.TANTO_CD
		FROM	LOGIN
		WHERE	LOGIN.TANTO_CD IS NOT NULL
		/*IF(condition.srhDevelopAcceptTantoCd != null) && (condition.srhDevelopAcceptTantoCd != "")*/
		AND	LOGIN.TANTO_CD LIKE /*condition.srhDevelopAcceptTantoCd*/'%'
		/*END*/
	)	LOGIN_AC_TANTO_CD
,	(
		SELECT	LOGIN.TANTO_CD
		FROM	LOGIN
		WHERE	LOGIN.TANTO_CD IS NOT NULL
		/*IF(condition.srhDevelopAcceptTantoName != null) && (condition.srhDevelopAcceptTantoName != "")*/
		AND	LOGIN.TANTO_NM LIKE /*condition.srhDevelopAcceptTantoName*/'%'
		/*END*/
	)	LOGIN_AC_TANTO_NM
,		VENDER VENDER

WHERE	DEV.DEVELOPMENT_REQUEST_NO IS NOT NULL
AND     ITEM_QUEUE.DEVELOPMENT_REQUEST_NO(+) = DEV.DEVELOPMENT_REQUEST_NO
/*IF(condition.srhDevelopmentRequestNo != null) && (condition.srhDevelopmentRequestNo != "")*/
AND 	DEV.DEVELOPMENT_REQUEST_NO LIKE /*condition.srhDevelopmentRequestNo*/'DV00000004'
/*END*/
/*IF(condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND		ITEM_QUEUE.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/
/*IF(condition.srhItemName != null) && (condition.srhItemName != "")*/
AND		ITEM_QUEUE.ITEM_NAME LIKE /*condition.srhItemName*/'%'
/*END*/
/*IF(condition.srhDevelopmentDetail != null) && (condition.srhDevelopmentDetail != "")*/
AND		DEV.DEVELOPMENT_DETAIL LIKE /*condition.srhDevelopmentDetail*/'%'
/*END*/
/*IF(condition.srhTantoDivision != null) && (condition.srhTantoDivision != "") && (condition.srhTantoDivision != "0")*/
AND		DEV.TANTO_DIVISION = /*condition.srhTantoDivision*/'1'
/*END*/
/*IF(condition.srhStatus != null) && (condition.srhStatus != "") && (condition.srhStatus != "0")*/
AND		DEV.STATUS = /*condition.srhStatus*/'1'
/*END*/
/*IF(condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND		DEV.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
/*END*/
/*IF(condition.srhDevItem != null) && (condition.srhDevItem != "")*/
AND		DEV.DEV_ITEM = /*condition.srhDevItem*/'APP'
/*END*/
/*IF(condition.srhDevItemNo != null) && (condition.srhDevItemNo != "")*/
AND		DEV.DEV_ITEM_NO LIKE /*condition.srhDevItemNo*/'%'
/*END*/
/*IF(condition.srhDevRelation != null) && (condition.srhDevRelation != "")*/
AND		DEV.DEV_RELATION = /*condition.srhDevRelation*/'B'
/*END*/

AND		DEV.VENDER_CD = VENDER.VENDER_CD
AND		VENDER.VENDER_CD = VENDER_NM_CD.VENDER_CD

AND		DEV.DEVELOP_ACCEPT_TANTO_CD = LOGIN.TANTO_CD

AND		DEV.DEVELOP_ACCEPT_TANTO_CD = LOGIN_AC_TANTO_CD.TANTO_CD
AND		DEV.DEVELOP_ACCEPT_TANTO_CD = LOGIN_AC_TANTO_NM.TANTO_CD

AND		DEV.REQUEST_TANTO_CD = LOGIN_RQ_TANTO_CD.TANTO_CD
AND		DEV.REQUEST_TANTO_CD = LOGIN_RQ_TANTO_NM.TANTO_CD

ORDER BY DEV.DEVELOPMENT_REQUEST_NO
