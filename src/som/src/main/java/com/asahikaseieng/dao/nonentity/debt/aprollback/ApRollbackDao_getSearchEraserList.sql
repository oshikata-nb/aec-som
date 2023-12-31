/*
 * 買掛ロールバック処理 消込データ存在チェック用SQL
 *
 * entityName=ApRollback
 * packageName=debt.arrollback
 * methodName=getSearchEraserList
 *
 */
SELECT
	ORGANIZATION_CD
,	PAYABLE_UPDATE_DATE AS PAYABLE_DATE
FROM
	(
	SELECT 	CREDIT.ORGANIZATION_CD
	,		CREDIT.PAYABLE_UPDATE_DATE
	FROM 	CREDIT									-- 入金トラン(相殺)
			INNER JOIN
				ERASER
				ON CREDIT.CREDIT_NO = ERASER.CREDIT_NO
				AND CREDIT.ROW_NO = ERASER.CREDIT_ROW_NO
	WHERE	CREDIT.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		CREDIT.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
	AND		CREDIT.PAYABLE_UPDATE_DATE = /*payableDate*/'2008/07/31'
	UNION ALL
	SELECT 	PAYMENT.ORGANIZATION_CD
	,		PAYMENT.PAYABLE_UPDATE_DATE
	FROM 	PAYMENT									-- 支払トラン(相殺)
			INNER JOIN
				ERASER
				ON PAYMENT.SLIP_NO = ERASER.SLIP_NO
				AND PAYMENT.ROW_NO = ERASER.SLIP_ROW_NO
	WHERE	PAYMENT.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		PAYMENT.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
	AND		PAYMENT.PAYABLE_UPDATE_DATE = /*payableDate*/'2008/07/31'
	UNION ALL
	SELECT 	OFFSET_GROUP_DATA.ORGANIZATION_CD
	,		OFFSET_GROUP_DATA.PAYABLE_UPDATE_DATE
	FROM 	OFFSET_GROUP_DATA						-- グループ間相殺トラン
			INNER JOIN
				ERASER
				ON OFFSET_GROUP_DATA.OFFSET_NO = ERASER.SLIP_NO
				AND OFFSET_GROUP_DATA.VENDER_CD = ERASER.VENDER_CD
	WHERE	OFFSET_GROUP_DATA.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		OFFSET_GROUP_DATA.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
	AND		OFFSET_GROUP_DATA.PAYABLE_UPDATE_DATE = /*payableDate*/'2008/07/31'
	)
