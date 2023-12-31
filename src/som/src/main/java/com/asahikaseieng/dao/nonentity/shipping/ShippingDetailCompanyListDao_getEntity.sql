/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/

/**
 * 出荷指図詳細入力SQL
 *
 * @author tosco
 *
 * entityName=ShippingDetailOtherList
 * packageName=shipping
 * methodName=getEntity
 *
 */
SELECT
    SDETAIL.SHIPPING_NO
,   SDETAIL.SHIPPING_ROW_NO
,   SDETAIL.ROW_NO
,   SDETAIL.LOT_NO
,   SDETAIL.SHIPPING_INSTRUCTION
,   SDETAIL.SHIPPING_RESULT_DATE
,   SDETAIL.SHIPPING_RESULT_QUANTITY
,   SDETAIL.SHIPPING_STATUS
,   SDETAIL.SUMMERY
,   SDETAIL.LOCATION_CD
,   SDETAIL.DELIVERY_COMP
,   SDETAIL.INPUT_DATE
,   SDETAIL.INPUTOR_CD
,   SDETAIL.UPDATE_DATE
,   SDETAIL.UPDATOR_CD
,   LOCATION.LOCATION_NAME
,   LOT_INVENTORY.INVENTORY_QTY
,   LOT_INVENTORY.BACKORDER_QTY
,   LOT_INVENTORY.INSPECTION_QTY
,   LOT_INVENTORY.ASSIGN_QTY
,   FUN_GET_UPPDER_LOCATION(SDETAIL.LOCATION_CD,1) AS UPPER_LOCATION
FROM
    (SELECT
    	SDETAIL.*
    ,   SHEAD.ITEM_CD
     FROM
     	SHIPPING_DETAIL SDETAIL
	,   SHIPPING SHEAD
	 WHERE
		 SDETAIL.SHIPPING_NO = /*shippingNo*/
	 AND SDETAIL.SHIPPING_NO = SHEAD.SHIPPING_NO 
	) SDETAIL
,   LOCATION LOCATION
,   LOT_INVENTORY LOT_INVENTORY
WHERE
    SDETAIL.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND LOT_INVENTORY.LOCATION_CD(+) = SDETAIL.LOCATION_CD
AND LOT_INVENTORY.ITEM_CD(+) = SDETAIL.ITEM_CD
AND LOT_INVENTORY.LOT_NO(+) = SDETAIL.LOT_NO
