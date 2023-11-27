/*
 *Asprovaから書き込んだデータで発行書を発行していないものを削除SQL
 *
 * entityName=AspImportPurchaseSubcontract
 * packageName=aspimport
 * methodName=deleteAspData
 *
 */
DELETE
FROM
	PURCHASE_SUBCONTRACT
WHERE
	PURCHASE_SUBCONTRACT.STATUS = 1
AND	PURCHASE_SUBCONTRACT.ASP_ORDER_NO is NOT NULL
AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION NOT IN (2,3)

