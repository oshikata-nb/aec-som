/*
 *Asprovaから書き込んだデータで発行書を発行していないものを削除
 *する場合の削除対象レコードを取得するSQL
 *
 * entityName=AspImportPurchaseSubcontract
 * packageName=aspimport
 * methodName=getDeleteAspData
 *
 */
SELECT
	PURCHASE_NO
,	UPDATE_DATE
FROM
	PURCHASE_SUBCONTRACT
WHERE
	PURCHASE_SUBCONTRACT.STATUS = 1
AND	PURCHASE_SUBCONTRACT.ASP_ORDER_NO is NOT NULL
AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION NOT IN (2,3)