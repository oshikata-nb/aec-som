/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */

/*
 * 売上受払履歴一覧取得用SQL
 *
 * entityName=SalesDetailKeepSalesInoutRecordList
 * packageName=sales
 * methodName=getList
 *
 */
SELECT
	SALES_INOUT_RECORD.SALES_NO
,	SALES_INOUT_RECORD.SALES_ROW_NO
,	SALES_INOUT_RECORD.LOCATION_CD
,	SALES_INOUT_RECORD.LOT_NO
,	SALES_INOUT_RECORD.QTY
,	SALES_INOUT_RECORD.INPUTOR_CD
,	SALES_INOUT_RECORD.INPUT_DATE
,	SALES_INOUT_RECORD.UPDATOR_CD
,	SALES_INOUT_RECORD.UPDATE_DATE
,	LOCATION.LOCATION_NAME
,	NVL(LOT_INVENTORY.INVENTORY_QTY, 0) INVENTORY_QTY
,	NVL(LOT_INVENTORY.INSPECTION_QTY, 0) INSPECTION_QTY
FROM
	SALES_INOUT_RECORD
,	LOCATION
,	LOT_INVENTORY
WHERE
	SALES_INOUT_RECORD.SALES_NO = /*salesNo*/'UR000000213'
AND SALES_INOUT_RECORD.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND SALES_INOUT_RECORD.LOCATION_CD = LOT_INVENTORY.LOCATION_CD(+)
AND SALES_INOUT_RECORD.LOT_NO = LOT_INVENTORY.LOT_NO(+)
AND LOT_INVENTORY.ITEM_CD(+) = /*itemCd*/'09046778'
ORDER BY SALES_INOUT_RECORD.SALES_ROW_NO