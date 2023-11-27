/*
 * 支払更新処理 支払先オートコンプでの支払締め日取得用SQL
 *
 * entityName=PaymentUpdate
 * packageName=payment.paymentupdate
 * methodName=getPayableDate
 *
 */
SELECT 	TO_CHAR(ADD_MONTHS(MAX(PAYABLE_DATE), 1), 'YYYYMMDD') AS STR_PAYABLE_DATE		--MAX(支払締め日)の翌月
FROM 	PAYMENT_HEADER
WHERE	PAYMENT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	PAYMENT_HEADER.SUPPLIER_CD = /*venderCd*/
/*END*/
