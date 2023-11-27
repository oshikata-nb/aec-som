/*
 * 
 *
 * entityName=RepLabelYobiyokai
 * packageName=repLabelYobiyokai
 * methodName=getYobiyokaiList
 *
 */
SELECT * FROM REP_LABEL_YOBIYOKAI 
WHERE DIRECTION_NO IN /*directionNo*/('H0904210006')
ORDER BY DIRECTION_NO,YOBIYOKAIBCR ASC






