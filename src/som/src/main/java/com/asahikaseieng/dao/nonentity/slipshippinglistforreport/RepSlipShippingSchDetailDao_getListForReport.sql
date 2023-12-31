/*
 * 
 *
 * entityName=RepSlipShippingSchDetail
 * packageName=slipshippinglistforreport
 * methodName=getListForReport
 * 2019/12/10 AECS佐藤 出荷後在庫数追加
 *
 */
SELECT
	KEY
,	ITEM_CD
,	ITEM_NAME
,	STYLE_OF_PACKING
,	TO_CHAR(SUM(ORDER_QTY))		AS SUM_ORDER_QTY
,	INVENTORY_QTY
,	SCHEDULED_SHIPPING_DATE
,	LOCATION_CD
,	NVL(INVENTORY_QTY - SUM(ORDER_QTY),0)	AS AFTER_INVENTORY_QTY

FROM 
	REP_SLIP_SHIPPING_SCH_DETAIL
WHERE 
	SHIPPING_NO IN /*shippingNo*/('SK000000001')

GROUP BY
	KEY
,	ITEM_CD
,	ITEM_NAME
,	STYLE_OF_PACKING
,	INVENTORY_QTY
,	SCHEDULED_SHIPPING_DATE
,	LOCATION_CD

ORDER BY
	SCHEDULED_SHIPPING_DATE
,	LOCATION_CD
,	ITEM_CD ASC


