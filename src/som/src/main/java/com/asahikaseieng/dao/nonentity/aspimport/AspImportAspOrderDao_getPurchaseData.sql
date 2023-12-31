/*
 * 購買外注作成処理　購買データ出力用　取得用SQL
 *
 * entityName=AspImportAspOrder
 * packageName=aspimport
 * methodName=getPurchaseData
 *
 */
SELECT
	ASP_ORDER.*
FROM
	ASP_ORDER
,	(SELECT ASP_ORDER.ORDER_CD,PURCHASE_SUBCONTRACT.PURCHASE_NO 
	FROM	ASP_ORDER,PURCHASE_SUBCONTRACT
	WHERE	ASP_ORDER.ORDER_CD = PURCHASE_SUBCONTRACT.ASP_ORDER_NO(+)
	)PURCHASE_SUBCONTRACT
WHERE
	ASP_ORDER.ORDER_TYPE = 'P'
AND	ASP_ORDER.ISREPLENISHMENT = '1'
AND	(ASP_ORDER.DISABLED <> '1'  OR ASP_ORDER.DISABLED IS NULL)

AND	PURCHASE_SUBCONTRACT.ORDER_CD = ASP_ORDER.ORDER_CD
AND	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NULL