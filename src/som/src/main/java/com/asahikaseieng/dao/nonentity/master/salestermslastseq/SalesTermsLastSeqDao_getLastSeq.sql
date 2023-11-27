/*
 * 販売条件最終行番取得用SQL
 *
 * entityName=SalesTermsLastSeq
 * packageName=salestermslastseq
 * methodName=getLastSeq
 *
 */

SELECT NVL(MAX(SEQ), 0) + 1 LAST_SEQ
FROM SALES_TERMS
WHERE DELIVERY_CD = /*deliveryCd*/'%'
AND BALANCE_CD = /*balanceCd*/'%'


