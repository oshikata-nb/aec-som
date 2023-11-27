/*
 * 納期連絡表出力ログSQL 
 *
 * entityName=OrderImportLog
 * packageName=orderimportlog
 * methodName=insertDetailLog
 *
 */
INSERT INTO FRST_ORDER_DETAIL_LOG
(
	   FRST_ORDER_LOG_NO
	 , LOG_CLS
	 , LOG_DATE
	 , FRST_ORDER_NO
	 , FRST_ORDER_ROW_NO
	 , ORDER_IMP_NO
	 , ORDER_NO
	 , ROW_NO
	 , SHIPPING_NO
	 , ITEM_CD
	 , ORDER_QTY
	 , ORDER_UNITPRICE
	 , STANDARD_UNITPRICE
	 , STANDARD_DISCOUNT
	 , SPECIAL_DISCOUNT
	 , TMP_UNITPRICE_FLG
	 , MATSS
	 , ESTIMATE_STANDARD_AMOUNT
	 , ESTIMATE_MATSS
	 , INPUT_DIVISION
	 , WEIGHT
	 , CUSTOMER_ORDER_DETAIL_NO
	 , INPUT_APPROVAL_DATE
	 , INPUT_APPROVER_CD
	 , DEL_DATE_SEND_DATE
	 , DEL_DATE_SENDER_CD
	 , DELETE_DATE
	 , DEL_FLG
	 , CANCEL_QTY
	 , CANCEL_DATE
	 , CANCEL_FLG
	 , ERROR_FLG
	 , INPUT_DATE
	 , INPUTOR_CD
	 , UPDATE_DATE
	 , UPDATOR_CD
)
SELECT
      /*logSeq*/1 AS FRST_ORDER_LOG_NO
 , /*logCls*/ AS LOG_CLS
 , SYSDATE AS LOG_DATE
 , FRST_ORDER_NO
 , FRST_ORDER_ROW_NO
 , ORDER_IMP_NO
 , ORDER_NO
 , ROW_NO
 , SHIPPING_NO
 , ITEM_CD
 , ORDER_QTY
 , ORDER_UNITPRICE
 , STANDARD_UNITPRICE
 , STANDARD_DISCOUNT
 , SPECIAL_DISCOUNT
 , TMP_UNITPRICE_FLG
 , MATSS
 , ESTIMATE_STANDARD_AMOUNT
 , ESTIMATE_MATSS
 , INPUT_DIVISION
 , WEIGHT
 , CUSTOMER_ORDER_DETAIL_NO
 , INPUT_APPROVAL_DATE
 , INPUT_APPROVER_CD
 , DEL_DATE_SEND_DATE
 , DEL_DATE_SENDER_CD
 , DELETE_DATE
 , DEL_FLG
 , CANCEL_QTY
 , CANCEL_DATE
 , CANCEL_FLG
 , ERROR_FLG
 , INPUT_DATE AS INPUT_DATE
 , /*tantoCd*/'aec' AS INPUTOR_CD
 , UPDATE_DATE AS UPDATE_DATE
 , /*tantoCd*/'aec' AS UPDATOR_CD
FROM
    FRST_ORDER_DETAIL
WHERE
    FRST_ORDER_DETAIL.FRST_ORDER_NO IS NOT NULL
AND FRST_ORDER_DETAIL.FRST_ORDER_NO = /*frstOrderNo*/'PK000000001'