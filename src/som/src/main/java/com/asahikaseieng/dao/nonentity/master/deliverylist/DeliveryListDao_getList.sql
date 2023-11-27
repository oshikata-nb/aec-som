/*
 * 納入先一覧用SQL
 *
 * entityName=DeliveryList
 * packageName=deliverylist
 * methodName=getList
 *
 */

SELECT DELIVERY_CD, DELIVERY_NAME1, DELIVERY_NAME2, SEARCH_KANA, ADDRESS1, TEL_NO
FROM DELIVERY
WHERE DELIVERY_CD IS NOT NULL

/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND (DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
OR DELIVERY_NAME1 LIKE  FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
OR DELIVERY_NAME2 LIKE  FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
)
/*END*/

ORDER BY DELIVERY_CD


