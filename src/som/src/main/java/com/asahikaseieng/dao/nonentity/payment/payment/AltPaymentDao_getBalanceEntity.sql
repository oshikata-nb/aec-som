/*
 * 支払残高検索SQL文
 *
 * entityName=AltPayment
 * packageName=payment
 * methodName=getBalanceEntity
 *
 */

SELECT SUM(BALANCE_FORWARD) BALANCE_FORWARD
FROM PAYMENT_HEADER HEADER
WHERE SUPPLIER_CD = /*supplierCd*/'%'
AND HEADER.CREDIT_SCHEDULED_DATE = /*strPaymentDate*/'2009/11/30'
GROUP BY ORGANIZATION_CD
, SUPPLIER_CD
