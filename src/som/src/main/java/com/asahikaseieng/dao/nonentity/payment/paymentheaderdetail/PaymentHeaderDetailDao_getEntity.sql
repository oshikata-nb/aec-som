/*
 * 支払ヘッダー登録用検索SQL文
 *
 * entityName=PaymentHeaderDetail
 * packageName=paymentheaderdetail
 * methodName=getEntity
 *
 */

SELECT *
FROM PAYMENT_HEADER
WHERE ORGANIZATION_CD = /*organizationCd*/'%'
AND SUPPLIER_CD = /*supplierCd*/'%'
AND CREDIT_SCHEDULED_DATE = /*paymentDate*/'2009/08/01'
