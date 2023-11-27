/*
 * 見積単価テーブルの件数を取得する
 *
 * entityName=SalestermsAndEstimateDetail
 * packageName=salestermsandestimatedetail
 * methodName=getEstimateCount
 *
 */
SELECT
	COUNT(*) AS DATA_COUNT
FROM
	ESTIMATE
WHERE ESTIMATE.ITEM_CD = /*itemCd*/'TEST1'

