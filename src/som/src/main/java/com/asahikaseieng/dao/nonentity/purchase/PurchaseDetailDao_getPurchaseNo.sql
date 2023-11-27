/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/

/**
 * 新規登録時、購買NO取得SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDetail
 * packageName=purchase
 * methodName=getPurchaseNo
 *
 */
SELECT
	PURCHASE_NO
FROM
	PURCHASE_SUBCONTRACT
WHERE
	BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/