/*
 * 販売条件マスタ重複チェック用SQL
 *
 * entityName=SalesTermsDuplicateList
 * packageName=salestermsduplicatelist
 * methodName=getDuplicateList
 *
 */

SELECT DELIVERY_CD, BALANCE_CD, ITEM_CD
FROM SALES_TERMS
WHERE DELIVERY_CD = /*deliveryCd*/'%'
AND BALANCE_CD <> /*balanceCd*/'%'
AND ITEM_CD = /*itemCd*/'%'
