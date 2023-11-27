/*
 * 売掛更新ロールバック処理 消込データ存在チェック用SQL(カスタマイズ)
 *
 * entityName=ArRollbackCsm
 * packageName=credit.arrollback
 * methodName=getSearchEraserList
 *
 */
--入金データ
SELECT
	ORGANIZATION_CD
,	VENDER_CD
,	DELIVERY_UPDATE_DATE AS CREDIT_DATE
FROM
	CREDIT
WHERE	ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	ORGANIZATION_CD = /*organizationCd*/'SC00001'
/*END*/
AND		DELIVERY_UPDATE_DATE = /*cleditDate*/'2008/07/31'	--売掛締め日
AND		ERASER_AMOUNT <> 0									--消込額
