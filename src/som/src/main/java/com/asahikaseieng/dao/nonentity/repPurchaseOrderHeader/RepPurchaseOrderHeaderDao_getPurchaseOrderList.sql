/*
 * 発注書帳票ヘッダ情報取得用SQL
 *
 * entityName=RepPurchaseOrderHeader
 * packageName=repPurchaseOrderHeader
 * methodName=getPurchaseOrderList
 *
 */
SELECT * FROM REP_PURCHASE_ORDER_HEADER 
WHERE ORDER_SHEET_NO IN /*directionNo*/('00000415')
AND   BUY_SUBCONTRACT_ORDER_NO IN /*buySubcontractOrderNo*/('00000415')
ORDER BY ORDER_SHEET_NO_KAO,ITEM_CD ASC





