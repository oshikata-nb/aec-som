/*
 * 備考番号最大値の検索用SQL
 *
 * entityName=RemarkDetailGetMaxRemarkNo
 * packageName=remarkdetailgetmaxno
 * methodName=getMaxRemarkNo
 *
 */
SELECT 	NVL(MAX(REMARK.REMARK_NO), 0) AS MAX
FROM 	REMARK

WHERE REMARK_NO IS NOT NULL

