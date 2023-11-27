/*
 * 
 *
 * entityName=RepLabelProductDirection
 * packageName=repLabelProductDirection
 * methodName=getLabelProductDirectionList
 *
 */
SELECT * FROM REP_LABEL_PRODUCT_DIRECTION 
WHERE DIRECTION_NO IN /*directionNo*/('H0904210006')
ORDER BY DIRECTION_NO,LOT_NO ASC




