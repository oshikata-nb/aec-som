/*
 * 販売条件一括削除用SQL
 *
 * entityName=SalesTermsDelete
 * packageName=salestermsdelete
 * methodName=deleteList
 */

DELETE
FROM SALES_TERMS
WHERE DELIVERY_CD = /*deliveryCd*/'%'
AND BALANCE_CD = /*balanceCd*/'%'
