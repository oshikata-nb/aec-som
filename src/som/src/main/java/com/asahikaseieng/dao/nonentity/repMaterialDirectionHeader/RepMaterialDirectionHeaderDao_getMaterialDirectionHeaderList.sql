/*
 * 
 *
 * entityName=RepMaterialDirectionHeader
 * packageName=repMaterialDirectionHeader
 * methodName=getMaterialDirectionHeaderList
 *
 */
SELECT * FROM REP_MATERIAL_DIRECTION_HEADER 
WHERE DIRECTION_NO IN /*directionNo*/('00000122')
ORDER BY DIRECTION_NO ASC



