-----------
/*
 * Created on 2023/04/06
 *
 * $copyright$
 *
*/

/**
 * 削除時、先付受注ヘッダ存在チェックSQL
 *
 * @author tosco
 *
 * entityName=BalanceDetail
 * packageName=balancedetail
 * methodName=getCountFrstOrderHead
 *
 */
SELECT
	 COUNT(*) AS CNT
FROM	FRST_ORDER_HEAD
WHERE	BALANCE_CD = /*balanceCd*/