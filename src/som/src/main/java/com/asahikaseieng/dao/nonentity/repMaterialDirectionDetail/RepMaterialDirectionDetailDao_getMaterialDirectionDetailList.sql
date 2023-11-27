/*
 * 
 *
 * entityName=RepMaterialDirectionDetail
 * packageName=repMaterialDirectionDetail
 * methodName=getMaterialDirectionDetailList
 *
 */
SELECT * FROM REP_MATERIAL_DIRECTION_DETAIL 
WHERE DIRECTION_NO IN /*directionNo*/('00000122') 
ORDER BY DIRECTION_NO,ITEM_CD,INV_LOT_NO ASC





