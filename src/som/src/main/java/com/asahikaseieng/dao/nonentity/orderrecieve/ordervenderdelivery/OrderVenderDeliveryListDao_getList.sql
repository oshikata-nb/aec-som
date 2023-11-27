/*
 * 納入先連携マスタ一覧用SQL
 *
 * entityName=OrderVenderDeliveryList
 * packageName=ordervenderdeliverylist
 * methodName=getList
 *
 */

SELECT
    OVD.VENDER_GROUP_CD
,   OVD.SOM_DELIVERY_CD
,   OVD.CTM_DELIVERY_CD_01 DELIVERY_CD1
,   OVD.CTM_DELIVERY_CD_02 DELIVERY_CD2
,   OVD.CTM_DELIVERY_CD_03 DELIVERY_CD3
FROM ORDER_VENDER_DELIVERY OVD
WHERE 
    OVD.SOM_DELIVERY_CD IS NOT NULL
	/*IF (( venderGroupCd != null ) && ( venderGroupCd != "" ))*/
		AND OVD.VENDER_GROUP_CD LIKE /*venderGroupCd*/'%'
	/*END*/
    /*IF (( somDeliveryCd != null ) && ( somDeliveryCd != "" ))*/
		AND OVD.SOM_DELIVERY_CD LIKE /*somDeliveryCd*/'%'
	/*END*/
ORDER BY 
    OVD.VENDER_GROUP_CD
,   OVD.SOM_DELIVERY_CD

