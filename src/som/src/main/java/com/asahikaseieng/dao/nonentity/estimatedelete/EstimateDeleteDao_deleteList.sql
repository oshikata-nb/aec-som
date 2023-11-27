/*
 * 見積/単価一括削除用SQL
 *
 * entityName=EstimateDelete
 * packageName=estimatedelete
 * methodName=deleteList
 */

DELETE
FROM ESTIMATE
WHERE ESTIMATE_NO = /*estimateNo*/'%'

/*IF(consecutiveNo != null)*/
AND CONSECUTIVE_NO > /*consecutiveNo*/1
/*END*/
