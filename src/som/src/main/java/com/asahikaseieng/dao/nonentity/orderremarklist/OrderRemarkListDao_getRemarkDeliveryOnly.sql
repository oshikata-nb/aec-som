/**
 * 備考取得用(納入先のみ)SQL
 *
 * @author a1041072
 *
 * entityName=OrderRemarkList
 * packageName=orderremarklist
 * methodName=getRemarkDeliveryOnly
 *
 */
SELECT
	REMARK16 AS REMARK16
FROM
	REMARK
WHERE
	VENDER_CD IS NULL
AND DELIVERY_CD =/*deliveryCd*/
AND ITEM_CD IS NULL
