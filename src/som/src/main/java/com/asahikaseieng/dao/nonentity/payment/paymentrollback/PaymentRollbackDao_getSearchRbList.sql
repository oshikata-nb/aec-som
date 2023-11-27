/*
 * 支払更新ロールバック処理 ロールバックデータ存在チェック用SQL
 *
 * entityName=PaymentRollback
 * packageName=payment.paymentrollback
 * methodName=getSearchRbList
 *
 */
SELECT 	PAYMENT_HEADER.ORGANIZATION_CD
,		PAYMENT_HEADER.SUPPLIER_CD
,		PAYMENT_HEADER.PAYABLE_DATE
FROM 	PAYMENT_HEADER
WHERE	PAYMENT_HEADER.ORGANIZATION_CD IS NOT NULL
/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
	AND	PAYMENT_HEADER.ORGANIZATION_CD = /*organizationCd*/
/*END*/
/*IF (( venderCd != null ) && ( venderCd != "" ))*/
	AND	PAYMENT_HEADER.SUPPLIER_CD = /*venderCd*/
/*END*/
AND		PAYMENT_HEADER.PAYABLE_DATE = /*payableDate*/
