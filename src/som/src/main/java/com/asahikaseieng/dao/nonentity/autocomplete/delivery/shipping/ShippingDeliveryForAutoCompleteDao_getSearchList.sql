/*
 * 納入先マスタ検索一覧用SQL
 *
 * entityName=ShippingDeliveryForAutoComplete
 * packageName=shipping
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT DELIVERY_CD, SEARCH_KANA, CARRY_CD
		FROM   DELIVERY
		WHERE  DELIVERY_CD IS NOT NULL

/*IF(deliveryCd != null) && (deliveryCd != "")*/
		AND    (DELIVERY_CD LIKE /*deliveryCd*/'%' OR SEARCH_KANA LIKE FUN_RET_MASTER_STRING_USE_AC(/*deliveryCd*/'%'))
/*END*/
		
		ORDER  BY DELIVERY_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
