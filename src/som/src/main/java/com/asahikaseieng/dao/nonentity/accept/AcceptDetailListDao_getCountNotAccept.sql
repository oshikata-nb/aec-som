/*
 * Created on 2009/02/2
 *
 * $copyright$
 *
*/

/**
 * 受入入力 未受入データ件数取得用SQL
 *
 * @author tosco
 *
 * entityName=AcceptDetailList
 * packageName=accept
 * methodName=getCountLast
 *
 */
SELECT	COUNT(*)
FROM	PURCHASE_SUBCONTRACT
WHERE	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO IS NOT NULL
/*IF (( buySubcontractOrderNo != null ) && ( buySubcontractOrderNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/	-- 発注番号
/*END*/
	AND	PURCHASE_SUBCONTRACT.STATUS = 5			-- 5:入荷登録済
