/*
 * 
 *
 * entityName=RepEstimateHeader
 * packageName=repEstimateHeader
 * methodName=getEstimateHeaderList
 *
 */
SELECT * FROM REP_ESTIMATE_HEADER 
WHERE ESTIMATE_NO IN /*estimateNo*/('MI000014742')
ORDER BY ESTIMATE_NO,ITEM_CD ASC


