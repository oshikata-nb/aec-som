/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/

/**
 * 受入入力 発注番号にひもづくデータの受入数量の合計値取得用SQL
 *
 * @author tosco
 *
 * entityName=AcceptDetailList
 * packageName=accept
 * methodName=getSumAcceptQty
 *
 */
SELECT	NVL(SUM(NVL(PURCHASE_SUBCONTRACT.ACCEPT_QUANTITY, 0)), 0)	-- 受入数量の合計値
FROM	PURCHASE_SUBCONTRACT
WHERE	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO IS NOT NULL
/*IF (( buySubcontractOrderNo != null ) && ( buySubcontractOrderNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/	-- 発注番号
/*END*/
/*IF (( slipNo != null ) && ( slipNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.SLIP_NO != /*slipNo*/			-- 仕入番号
/*END*/
