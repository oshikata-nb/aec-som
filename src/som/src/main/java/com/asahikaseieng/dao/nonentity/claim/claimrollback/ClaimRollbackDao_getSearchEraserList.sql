/*
 * 請求更新ロールバック処理 消込データ存在チェック用SQL
 *
 * entityName=ClaimRollback
 * packageName=claim.updaterollback
 * methodName=getSearchEraserList
 *
 */
SELECT
	ORGANIZATION_CD
,	VENDER_CD
,	INVOICE_UPDATE_DATE AS CREDIT_DATE
FROM
	(
	SELECT 	SALES.ORGANIZATION_CD
	,		SALES.INVOICE_CD AS VENDER_CD
	,		SALES.INVOICE_UPDATE_DATE
	FROM 	SALES									-- 売上トラン
			INNER JOIN
				ERASER
				ON SALES.SLIP_NO = ERASER.SLIP_NO
				AND SALES.ROW_NO = ERASER.SLIP_ROW_NO
	WHERE	SALES.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		SALES.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND		SALES.INVOICE_CD = /*venderCd*/'TS0001'
/*END*/
	AND		SALES.INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'
	UNION ALL
	SELECT 	CREDIT.ORGANIZATION_CD
	,		CREDIT.VENDER_CD AS VENDER_CD
	,		CREDIT.INVOICE_UPDATE_DATE
	FROM 	CREDIT									-- 入金トラン(相殺)
			INNER JOIN
				ERASER
				ON CREDIT.CREDIT_NO = ERASER.CREDIT_NO
				AND CREDIT.ROW_NO = ERASER.CREDIT_ROW_NO
	WHERE	CREDIT.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		CREDIT.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND		CREDIT.VENDER_CD = /*venderCd*/'TS0001'
/*END*/
	AND		CREDIT.INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'
	UNION ALL
	SELECT 	PAYMENT.ORGANIZATION_CD
	,		PAYMENT.SUPPLIER_CD AS VENDER_CD
	,		PAYMENT.INVOICE_UPDATE_DATE
	FROM 	PAYMENT									-- 支払トラン(相殺)
			INNER JOIN
				ERASER
				ON PAYMENT.SLIP_NO = ERASER.SLIP_NO
				AND PAYMENT.ROW_NO = ERASER.SLIP_ROW_NO
	WHERE	PAYMENT.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		PAYMENT.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND		PAYMENT.SUPPLIER_CD = /*venderCd*/'TS0001'
/*END*/
	AND		PAYMENT.INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'
	UNION ALL
	SELECT 	OFFSET_GROUP_DATA.ORGANIZATION_CD
	,		OFFSET_GROUP_DATA.VENDER_CD AS VENDER_CD
	,		OFFSET_GROUP_DATA.INVOICE_UPDATE_DATE
	FROM 	OFFSET_GROUP_DATA						-- グループ間相殺トラン
			INNER JOIN
				ERASER
				ON OFFSET_GROUP_DATA.OFFSET_NO = ERASER.SLIP_NO
				AND OFFSET_GROUP_DATA.VENDER_CD = ERASER.VENDER_CD
	WHERE	OFFSET_GROUP_DATA.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		OFFSET_GROUP_DATA.ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND		OFFSET_GROUP_DATA.VENDER_CD = /*venderCd*/'TS0001'
/*END*/
	AND		OFFSET_GROUP_DATA.INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'
	UNION ALL
	SELECT	ORGANIZATION_CD
	,		VENDER_CD AS VENDER_CD
	,		INVOICE_UPDATE_DATE
	FROM	CREDIT									-- 入金データ(入金)
	WHERE	ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND		ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND		VENDER_CD = /*venderCd*/'TS0001'
/*END*/
	AND		INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'	--請求締め日
	AND		ERASER_AMOUNT <> 0									--消込額
	)
