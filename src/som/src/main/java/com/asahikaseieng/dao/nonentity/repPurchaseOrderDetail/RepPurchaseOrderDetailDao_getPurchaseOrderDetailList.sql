/*
 * 発注書帳票明細情報取得用SQL
 *
 * entityName=RepPurchaseOrderDetail
 * packageName=repPurchaseOrderDetail
 * methodName=getPurchaseOrderDetailList
 *
 */
SELECT * FROM REP_PURCHASE_ORDER_DETAIL 
WHERE ORDER_SHEET_NO IN /*directionNo*/('00000415')
AND   BUY_SUBCONTRACT_ORDER_NO IN /*buySubcontractOrderNo*/('00000415')
ORDER BY ORDER_SHEET_NO_KAO,ITEM_CD,SUGGESTED_DELIVERLIMIT_DATE ASC





