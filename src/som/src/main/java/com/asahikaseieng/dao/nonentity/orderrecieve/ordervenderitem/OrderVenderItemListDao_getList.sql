/*
 * 品目連携マスタ一覧用SQL
 *
 * entityName=OrderVenderItemList
 * packageName=ordervenderitemlist
 * methodName=getList
 *
 */

SELECT
    OVI.VENDER_GROUP_CD
,   OVI.SOM_ITEM_CD
,   OVI.CTM_ITEM_CD_01 ITEM_CD1
,   OVI.CTM_ITEM_CD_02 ITEM_CD2
,   OVI.CTM_ITEM_CD_03 ITEM_CD3
FROM ORDER_VENDER_ITEM OVI
WHERE 
    OVI.SOM_ITEM_CD IS NOT NULL
	/*IF (( venderGroupCd != null ) && ( venderGroupCd != "" ))*/
		AND OVI.VENDER_GROUP_CD LIKE /*venderGroupCd*/'%'
	/*END*/
    /*IF (( somItemCd != null ) && ( somItemCd != "" ))*/
		AND OVI.SOM_ITEM_CD LIKE /*somItemCd*/'%'
	/*END*/
ORDER BY 
    OVI.VENDER_GROUP_CD
,   OVI.SOM_ITEM_CD
