
/*
 * 見積ファイルの該当品目有無確認用SQL
 *
 * entityName=SalestermsAndEstimateItemCheck
 * packageName=salestermsandestimateitemcheck
 * methodName=getEstimateItemCount
 *
 */

SELECT COUNT(ITEM_CD) ITEM_COUNT
FROM ESTIMATE
WHERE ITEM_CD = /*itemCd*/'ITEM01'