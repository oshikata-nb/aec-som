/*
 * 納期連絡表出力ログSQL
 *
 * entityName=OrderImportLog
 * packageName=orderimportlog
 * methodName=insertHeadLog
 *
 */
INSERT INTO FRST_ORDER_HEAD_LOG
(
FRST_ORDER_LOG_NO
 , LOG_CLS
 , LOG_DATE
 , FRST_ORDER_NO
 , ORDER_NO
 , ORDER_DIVISION
 , ORDER_DATE
 , ORGANIZATION_CD
 , INPUT_TANTO_CD
 , SALES_TANTO_CD
 , VENDER_CD
 , DELIVERY_CD
 , DELIVERY_ADDRESS
 , DELIVERY_TEL_NO
 , BALANCE_CD
 , STATUS
 , CUSTOMER_ORDER_NO
 , ORDER_AMOUNT
 , SUGGESTED_DELIVERLIMIT
 , SCHEDULED_SHIPPING_DATE
 , LEAD_TIME
 , DELIVERY_EXPECTED_DATE
 , DELIVERY_EXPECTED_TIME
 , CARRY_CD
 , CARRY_FARE
 , CARRY_INVOICE_FLAG
 , ORDER_PICTURE
 , PRINT_SUMMERY
 , DELIVERY_SLIP_SUMMERY
 , ORDER_SUMMERY
 , DELIVERYDATE_CONTACT_SUMMERY  /* 20210906 Asclab Saita 納期連絡表専用備考追加対応 */
 , VENDER_GROUP_CD
 , DEL_FLG
 , CANCEL_FLG
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
	 , ORDER_NO
	 , ORDER_DIVISION
	 , ORDER_DATE
	 , ORGANIZATION_CD
	 , INPUT_TANTO_CD
	 , SALES_TANTO_CD
	 , VENDER_CD
	 , DELIVERY_CD
	 , DELIVERY_ADDRESS
	 , DELIVERY_TEL_NO
	 , BALANCE_CD
	 , STATUS
	 , CUSTOMER_ORDER_NO
	 , ORDER_AMOUNT
	 , SUGGESTED_DELIVERLIMIT
	 , SCHEDULED_SHIPPING_DATE
	 , LEAD_TIME
	 , DELIVERY_EXPECTED_DATE
	 , DELIVERY_EXPECTED_TIME
	 , CARRY_CD
	 , CARRY_FARE
	 , CARRY_INVOICE_FLAG
	 , ORDER_PICTURE
	 , PRINT_SUMMERY
	 , DELIVERY_SLIP_SUMMERY
	 , ORDER_SUMMERY
	 , DELIVERYDATE_CONTACT_SUMMERY  /* 20210906 Asclab Saita 納期連絡表専用備考追加対応 */
	 , VENDER_GROUP_CD
	 , DEL_FLG
	 , CANCEL_FLG
     , INPUT_DATE AS INPUT_DATE
     , /*tantoCd*/'aec' AS INPUTOR_CD
     , UPDATE_DATE AS UPDATE_DATE
     , /*tantoCd*/'aec' AS UPDATOR_CD
FROM
    FRST_ORDER_HEAD
WHERE
    FRST_ORDER_HEAD.FRST_ORDER_NO IS NOT NULL
AND FRST_ORDER_HEAD.FRST_ORDER_NO = /*frstOrderNo*/'PK000000001'