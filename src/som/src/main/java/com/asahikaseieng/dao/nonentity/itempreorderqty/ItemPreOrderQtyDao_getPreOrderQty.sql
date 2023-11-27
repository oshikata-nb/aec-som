/*
 * 先付け数量取得SQL
 *
 * entityName=ItemPreOrderQty
 * packageName=itempreorderqty
 * methodName=getPreOrderQty
 *
 */
SELECT 
    SUM( NVL( ORDER_QTY , 0 ) + NVL( MATSS , 0 ) ) AS PRE_ORDER_QTY 
FROM 
    FRST_ORDER_DETAIL
WHERE  DEL_FLG = 0
AND CANCEL_FLG = 0
AND ORDER_NO IS NULL
/*IF(itemCd != null) && (itemCd != "")*/
AND (FRST_ORDER_DETAIL.ITEM_CD = /*itemCd*/'a' )
/*END*/