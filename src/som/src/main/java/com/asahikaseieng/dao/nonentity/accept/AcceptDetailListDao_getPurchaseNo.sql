/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */

/**
 * 新規登録時、購買NO取得SQL
 *
 * @author tosco
 *
 * entityName=AcceptDetailList
 * packageName=accept
 * methodName=getPurchaseNo
 *
 */
SELECT
	PURCHASE_NO
FROM
	PURCHASE_SUBCONTRACT
WHERE
	BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/
/*IF (( orderDivideNo != null ) && ( orderDivideNo != "" ))*/
AND	ORDER_DIVIDE_NO = /*orderDivideNo*/
/*END*/
/*IF (( rowNo != null ) && ( rowNo != "" ))*/
AND	ROW_NO = /*rowNo*/
/*END*/