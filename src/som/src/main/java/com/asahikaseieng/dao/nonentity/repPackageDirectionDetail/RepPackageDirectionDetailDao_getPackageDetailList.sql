/*
 * 
 *
 * entityName=RepPackageDirectionDetail
 * packageName=repPackageDirectionDetail
 * methodName=getPackageDetailList
 *
 */
SELECT * FROM REP_PACKAGE_DIRECTION_DETAIL 
WHERE DIRECTION_NO IN /*directionNo*/('H0904210006')
ORDER BY DIRECTION_NO,ITEM_CD ASC





