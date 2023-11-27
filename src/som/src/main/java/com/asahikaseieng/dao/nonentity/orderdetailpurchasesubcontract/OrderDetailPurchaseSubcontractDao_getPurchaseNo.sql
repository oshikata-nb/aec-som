/*
 * Created on 2009/05/19
 *
 * $copyright$
 *
*/

/**
 * 新規登録時、購買NO取得SQL
 *
 * @author tosco
 *
 * entityName=OrderDetailPurchaseSubcontract
 * packageName=orderdetailpurchasesubcontract
 * methodName=getPurchaseNo
 *
 */
SELECT
	PURCHASE_NO
FROM
	PURCHASE_SUBCONTRACT
WHERE
	BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/