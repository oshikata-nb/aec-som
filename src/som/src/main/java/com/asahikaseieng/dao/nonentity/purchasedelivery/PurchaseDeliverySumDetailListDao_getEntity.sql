/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/

/**
 * 納期回答まとめ入力用SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDeliverySumDetailList
 * packageName=purchasedelivery
 * methodName=getEntity
 *
 */
SELECT	PURCHASE_SUBCONTRACT.PURCHASE_NO
,	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
,   PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO
,	PURCHASE_SUBCONTRACT.SI_ORDER_NO
,	PURCHASE_SUBCONTRACT.VENDER_CD
,	PURCHASE_SUBCONTRACT.ITEM_NAME
,	PURCHASE_SUBCONTRACT.ORDER_QUANTITY
,	PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY
,	PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE
,	PURCHASE_SUBCONTRACT.STATUS
,	PURCHASE_SUBCONTRACT.REPLY_CONTENTS_DIVISION
,	DECODE(PURCHASE_SUBCONTRACT.ORDER_DIVISION, 4, PURCHASE_SUBCONTRACT.ITEM_NAME
												 , ITEM.ITEM_NAME
		  ) AS ITEM_QUEUE_NAME								-- 品目名称
,	ITEM.STYLE_OF_PACKING								-- 荷姿
,	NAMES.NAME01 AS UNIT									-- 単位
,	ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV		-- 運用管理単位(区分)
,	PURCHASE_SUBCONTRACT.UPDATE_DATE
FROM	PURCHASE_SUBCONTRACT
,		ITEM
,		(SELECT NAME_CD
		 ,		NAME01
		 FROM NAMES
		 WHERE	NAME_DIVISION = 'UNIT'
		) NAMES

WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
/*IF (( orderSheetNo != null ) && ( orderSheetNo != "" ))*/
	AND	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO = /*orderSheetNo*/'00002744'
/*END*/
AND		PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4)
AND		PURCHASE_SUBCONTRACT.ITEM_CD = ITEM.ITEM_CD(+) 
AND 	ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
ORDER BY PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
,		 PURCHASE_SUBCONTRACT.ITEM_CD

