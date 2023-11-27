/*
 * 支払更新ロールバック処理 初期表示用SQL
 *
 * entityName=PaymentRollback
 * packageName=payment.paymentrollback
 * methodName=getPayableDate
 *
 */
SELECT 	TO_CHAR(MAX(PAYABLE_DATE), 'YYYYMMDD') AS STR_PAYABLE_DATE	--MAX(支払締め日)
FROM 	PAYMENT_HEADER
WHERE	PAYMENT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	PAYMENT_HEADER.SUPPLIER_CD = /*venderCd*/
/*END*/
