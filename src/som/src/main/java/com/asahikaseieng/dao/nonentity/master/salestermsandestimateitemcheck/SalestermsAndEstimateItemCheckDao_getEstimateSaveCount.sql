/*
 * 販売条件マスタの保存用テーブルの件数を取得する
 *
 * entityName=SalestermsAndEstimateDetail
 * packageName=salestermsandestimatedetail
 * methodName=getEstimateSaveCount
 *
 */
SELECT
	COUNT(*) AS DATA_COUNT
FROM
	ESTIMATE_SAVED
WHERE ESTIMATE_SAVED.PK_NO = /*pkNo*/'TEST1'

