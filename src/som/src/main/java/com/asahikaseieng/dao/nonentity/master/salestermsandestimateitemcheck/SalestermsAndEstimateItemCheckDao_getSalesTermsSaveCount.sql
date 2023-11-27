/*
 * 販売条件マスタの品目毎の件数を件数を取得
 *
 * entityName=SalestermsAndEstimateDetail
 * packageName=salestermsandestimatedetail
 * methodName=getSalesTermsSaveCount
 *
 */
SELECT
	COUNT(*) AS DATA_COUNT
FROM
	SALES_TERMS_SAVED
WHERE SALES_TERMS_SAVED.PK_NO = /*pkNo*/'TEST1'