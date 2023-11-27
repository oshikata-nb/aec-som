/*
 * 開発依頼Excel出力一覧SQL文��
 *
 * entityName=DevelopList
 * packageName=develop
 * methodName=getListExcelReport
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
,   	DEV.TANTO_DIVISION
,   	DECODE(DEV.TANTO_DIVISION, 1, '香料開発', 2, '応用試作', '') TANTO_DIVISION_NAME
,   	DEV.HOPE_FINISHED_DATE
,   	VENDER.VENDER_CD
,		VENDER.VENDER_NAME
,   	DEV.REQUEST_TANTO_CD
,   	LOGIN.TANTO_NM REQUEST_TANTO_NAME
,   	DEV.REQUEST_DATE
,   	DEV.DEVELOP_ACCEPT_TANTO_CD
,   	LOGIN2.TANTO_NM DEVELOP_ACCEPT_TANTO_NAME
,   	DEV.DEVELOP_ACCEPT_DATE
,   	DEV.REQ_APP_TANTO_CD
,   	LOGIN3.TANTO_NM REQ_APP_TANTO_NAME
,   	DEV.REQ_APP_DATE
,   	DEV.DEVELOP_APP_TANTO_CD
,   	LOGIN4.TANTO_NM DEVELOP_APP_TANTO_NAME
,   	DEV.DEVELOP_APP_DATE
,   	DEV.DEVELOPMENT_DIVISION
,		CASE
			WHEN DEV.DEVELOPMENT_DIVISION = 1 THEN '香料開発'
			WHEN DEV.DEVELOPMENT_DIVISION = 2 THEN 'アプリケーション開発'
			WHEN DEV.DEVELOPMENT_DIVISION = 3 THEN '商品開発'
			WHEN DEV.DEVELOPMENT_DIVISION = 4 THEN '香料以外の添加物開発'
			WHEN DEV.DEVELOPMENT_DIVISION = 5 THEN 'テスト'
			WHEN DEV.DEVELOPMENT_DIVISION = 6 THEN '香料選定'
			ELSE 'その他'
		END DEVELOPMENT_DIVISION_NAME
,   	DEV.APPLICATION
,   	DEV.PROPERTY
,		CASE
			WHEN DEV.PROPERTY = 1 THEN '液体'
			WHEN DEV.PROPERTY = 2 THEN '粉体'
			WHEN DEV.PROPERTY = 3 THEN 'ペースト'
			WHEN DEV.PROPERTY = 4 THEN '液体飲料'
			WHEN DEV.PROPERTY = 5 THEN '粉体飲料'
			WHEN DEV.PROPERTY = 6 THEN '菓子類'
			ELSE 'その他'
		END PROPERTY_NAME
,   	DEV.HOPE_COST_FROM
,   	DEV.HOPE_COST_TO
,   	DEV.SOLVENT_INFORMATION
,   	DEV.ALLERGY_INFORMATION
,   	DEV.DEVELOPMENT_DETAIL
,   	DEV.DEVELOPMENT_PLAN
,   	DEV.DEVELOPMENT_PLAN_DATE
,   	DEV.VERIFICATION_RESULT
,   	DEV.VERIFICATION_RESULT_DATE
,   	DEV.REVUE_RESULT
,   	DEV.REVUE_RESULT_DATE
,		DEV.FINISHED_DATE
,   	ITEM_QUEUE.ITEM_CD
,   	ITEM_QUEUE.ITEM_NAME
FROM	LOGIN LOGIN
,		LOGIN LOGIN2
,   	LOGIN LOGIN3
,   	LOGIN LOGIN4
,		DEVELOPMENT_REQUEST DEV
,		(SELECT		ITEM_CD,
					ITEM_NAME,
					DEVELOPMENT_REQUEST_NO,
					MAX(VERSION)
		 FROM		ITEM_QUEUE 
		 GROUP BY	ITEM_CD, ITEM_NAME, DEVELOPMENT_REQUEST_NO) ITEM_QUEUE
,		VENDER VENDER
WHERE	DEV.VENDER_CD = VENDER.VENDER_CD(+)

/*IF(condition.srhDevelopmentRequestNo != null) && (condition.srhDevelopmentRequestNo != "")*/
AND 	DEV.DEVELOPMENT_REQUEST_NO LIKE /*condition.srhDevelopmentRequestNo*/'T%'
/*END*/

/*IF(condition.srhDevelopmentDetail != null) && (condition.srhDevelopmentDetail != "")*/
AND		DEV.DEVELOPMENT_DETAIL LIKE /*condition.srhDevelopmentDetail*/'%%'
/*END*/

/*IF(condition.srhTantoDivision != null) && (condition.srhTantoDivision != "") && (condition.srhTantoDivision != "0")*/
AND		DEV.TANTO_DIVISION = /*condition.srhTantoDivision*/'1'
/*END*/

/*IF(condition.srhStatus != null) && (condition.srhStatus != "") && (condition.srhStatus != "0")*/
AND		DEV.STATUS = /*condition.srhStatus*/'1'
/*END*/

/*IF(condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND		DEV.VENDER_CD = /*condition.srhVenderCd*/'T10451'
/*END*/

/*IF(condition.srhRequestTantoCd != null) && (condition.srhRequestTantoCd != "")*/
AND		LOGIN.TANTO_CD = /*condition.srhRequestTantoCd*/'K0001'
/*END*/

/*IF(condition.srhDevelopAcceptTantoCd != null) && (condition.srhDevelopAcceptTantoCd != "")*/
AND		LOGIN2.TANTO_CD = /*condition.srhDevelopAcceptTantoCd*/'K0001'
/*END*/

/*IF(condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND		ITEM_QUEUE.ITEM_CD = /*condition.srhItemCd*/'PINE'
/*END*/

AND		LOGIN.TANTO_CD(+) = DEV.REQUEST_TANTO_CD
AND		LOGIN2.TANTO_CD(+) = DEV.DEVELOP_ACCEPT_TANTO_CD
AND		LOGIN3.TANTO_CD(+) = DEV.REQ_APP_TANTO_CD
AND		LOGIN4.TANTO_CD(+) = DEV.DEVELOP_APP_TANTO_CD
AND		ITEM_QUEUE.DEVELOPMENT_REQUEST_NO(+) = DEV.DEVELOPMENT_REQUEST_NO

ORDER BY DEV.DEVELOPMENT_REQUEST_NO
