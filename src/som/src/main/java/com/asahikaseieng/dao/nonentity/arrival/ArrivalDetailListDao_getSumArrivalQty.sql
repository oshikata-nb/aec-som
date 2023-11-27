/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/

/**
 * 入荷入力 発注番号にひもづくデータの入荷予定数量の合計値取得用SQL
 *
 * @author tosco
 *
 * entityName=ArrivalDetail
 * packageName=arrival
 * methodName=getSumArrivalQty
 *
 */
SELECT	NVL(SUM(NVL(PURCHASE_SUBCONTRACT.ARRIVAL_QUANTITY, 0)), 0)	-- 入荷予定数量の合計値
FROM	PURCHASE_SUBCONTRACT
WHERE	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO IS NOT NULL
/*IF (( buySubcontractOrderNo != null ) && ( buySubcontractOrderNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/	-- 発注番号
/*END*/
/*IF (( slipNo != null ) && ( slipNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.SLIP_NO != /*slipNo*/			-- 仕入番号
/*END*/
