/*
 * 販売条件マスタの保存用テーブルの件数を取得
 *
 * entityName=SalestermsAndEstimateDetail
 * packageName=salestermsandestimatedetail
 * methodName=getSalesTermsSaveCount
 *
 */
SELECT
	COUNT(*) AS DATA_COUNT
FROM
	SALES_TERMS
WHERE SALES_TERMS.ITEM_CD = /*itemCd*/'TEST1'
