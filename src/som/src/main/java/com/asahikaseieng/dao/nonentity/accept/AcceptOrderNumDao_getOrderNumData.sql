/**
 *
 *
 * entityName=AcceptOrderNum
 * packageName=accept
 * methodName=getOrderNumData
 *
 */
SELECT 
	COUNT(*) AS ORDER_NUM
FROM 
	PURCHASE_SUBCONTRACT 
WHERE 
	ORDER_NO = /*orderNo*/'JT000000015' 
/*IF(orderRowNo != null)*/
AND	ORDER_ROW_NO= /*orderRowNo*/'1' 
/*END*/

/*IF(status != null)*/
AND 	STATUS = /*status*/6
/*END*/


