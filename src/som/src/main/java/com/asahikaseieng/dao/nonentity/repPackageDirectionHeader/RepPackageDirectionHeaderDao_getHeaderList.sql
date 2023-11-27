/*
 * 
 *
 * entityName=RepPackageDirectionHeader
 * packageName=repPackageDirectionHeader
 * methodName=getHeaderList
 *
 */
SELECT * FROM REP_PACKAGE_DIRECTION_HEADER 
WHERE DIRECTION_NO IN /*directionNo*/('H0904210006')
ORDER BY DIRECTION_NO,ITEM_CD ASC


