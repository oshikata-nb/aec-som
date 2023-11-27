/*
 * 
 *
 * entityName=RepEstimateDetail
 * packageName=repEstimateDetail
 * methodName=getEstimateDetailList
 *
 */
SELECT * FROM REP_ESTIMATE_DETAIL 
WHERE ESTIMATE_NO IN /*estimateNo*/('MI000014742')
ORDER BY ESTIMATE_NO,ITEM_CD ASC



