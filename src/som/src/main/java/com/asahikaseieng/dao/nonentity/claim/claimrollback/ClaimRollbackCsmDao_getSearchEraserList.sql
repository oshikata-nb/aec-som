/*
 * 請求更新ロールバック処理 消込データ存在チェック用SQL(カスタマイズ)
 *
 * entityName=ClaimRollbackCsm
 * packageName=claim.updaterollback
 * methodName=getSearchEraserList
 *
 */
--消込トラン(Csm)
SELECT
	ORGANIZATION_CD
,	INVOICE_CD AS VENDER_CD
,	INVOICE_UPDATE_DATE AS CREDIT_DATE
FROM
	ERASER_CSM
WHERE	ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	INVOICE_CD = /*venderCd*/'TS0001'
/*END*/
AND		INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'	--請求締め日
AND		ERASER_AMOUNT <> 0									--消込額
UNION ALL
--入金データ
SELECT
	ORGANIZATION_CD
,	VENDER_CD
,	INVOICE_UPDATE_DATE AS CREDIT_DATE
FROM
	CREDIT
WHERE	ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	VENDER_CD = /*venderCd*/'TS0001'
/*END*/
AND		INVOICE_UPDATE_DATE = /*cleditDate*/'2008/07/31'	--請求締め日
AND		ERASER_AMOUNT <> 0									--消込額
