/*
 * 販売条件マスタの該当品目有無確認用SQL
 *
 * entityName=SalestermsAndEstimateItemCheck
 * packageName=salestermsandestimateitemcheck
 * methodName=getSalestermsItemCount
 *
 */

SELECT COUNT(ITEM_CD) ITEM_COUNT
FROM SALES_TERMS
WHERE ITEM_CD = /*itemCd*/'ITEM01'