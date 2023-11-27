/*
 * 支払済金額検索SQL文
 *
 * entityName=AltPayment
 * packageName=payment
 * methodName=getPaidEntity
 *
 */

SELECT SUM(PAYMENT_AMOUNT) PAYMENT_AMOUNT
FROM PAYMENT
WHERE PAYMENT_DATE <> /*strPaymentDate*/'2009/09/30'
AND PAYMENT_DATE <= /*strPaymentDate*/'2009/09/30'
AND SUPPLIER_CD = /*supplierCd*/'%'
AND PAYMENT_UPDATE_DIVISION = 0 --未処理
AND APPROVAL_STATUS = 3 --承認済
GROUP BY SUPPLIER_CD
