/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */

/**
 * 納期回答まとめ入力画面 存在チェック用SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDeliverySumDetail
 * packageName=purchasedelivery
 * methodName=getCount
 *
 */
SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
,		SUM(PURCHASE_SUBCONTRACT.COUNT) AS COUNT
FROM	(
		SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
		,		(CASE
					WHEN PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4) THEN 1
					ELSE 0
				 END ) AS COUNT
		FROM	PURCHASE_SUBCONTRACT
		WHERE	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO IS NOT NULL
		/*IF (( orderSheetNo != null ) && ( orderSheetNo != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO = /*orderSheetNo*/'00002744'
		/*END*/
		AND		PURCHASE_SUBCONTRACT.STATUS != 1 AND PURCHASE_SUBCONTRACT.STATUS != 7
		) PURCHASE_SUBCONTRACT
GROUP BY PURCHASE_SUBCONTRACT.ORDER_SHEET_NO