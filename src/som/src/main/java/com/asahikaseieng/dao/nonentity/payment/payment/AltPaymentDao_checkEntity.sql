/*
 * 支払登録チェックSQL文
 *
 * entityName=AltPayment
 * packageName=payment
 * methodName=checkEntity
 *
 */

SELECT *
FROM PAYMENT
WHERE PAYMENT_DATE = /*paymentDate*/'2009/10/01'
AND SUPPLIER_CD = /*supplierCd*/'%'
AND ORGANIZATION_CD = /*organizationCd*/'%'