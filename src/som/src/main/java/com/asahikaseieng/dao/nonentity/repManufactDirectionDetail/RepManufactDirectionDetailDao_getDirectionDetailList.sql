/*
 * 
 *
 * entityName=RepManufactDirectionDetail
 * packageName=repManufactDirectionDetail
 * methodName=getDirectionDetailList
 *
 */
SELECT * FROM REP_MANUFACT_DIRECTION_DETAIL 
WHERE DIRECTION_NO IN /*directionNo*/('IFTEST10003')
ORDER BY DIRECTION_NO,SEQ_PROCEDURE,SEQ_FORMULA ASC




