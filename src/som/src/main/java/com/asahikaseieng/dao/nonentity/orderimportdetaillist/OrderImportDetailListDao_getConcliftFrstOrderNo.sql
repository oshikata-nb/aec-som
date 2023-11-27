/*
 * 受注取込詳細用SQL 
 *
 * entityName=OrderImportDetailList
 * packageName=orderImportDetailList
 * methodName=getConcliftFrstOrderNo
 *
 */
SELECT FRST_ORDER_HEAD.FRST_ORDER_NO
FROM
    FRST_ORDER_HEAD
WHERE
    FRST_ORDER_HEAD.DEL_FLG <> 1
    AND FRST_ORDER_HEAD.ORDER_DIVISION = /*orderDivision*/
    AND FRST_ORDER_HEAD.VENDER_CD = /*venderCd*/
    AND FRST_ORDER_HEAD.DELIVERY_CD = /*deliveryCd*/
    AND FRST_ORDER_HEAD.BALANCE_CD = /*balanceCd*/
    AND FRST_ORDER_HEAD.SCHEDULED_SHIPPING_DATE = /*scheduledShippingDate*/
    AND FRST_ORDER_HEAD.DELIVERY_EXPECTED_DATE = /*deliveryExpectedDate*/
    AND FRST_ORDER_HEAD.CARRY_CD = /*carryCd*/;
    /*IF (frstOrderNo != null) && (frstOrderNo != "")*/
    AND FRST_ORDER_HEAD.FRST_ORDER_NO <> /*frstOrderNo*/
    /*END*/
    /*IF (deliveryAddress != null) && (deliveryAddress != "")*/
    AND FRST_ORDER_HEAD.DELIVERY_ADDRESS = /*deliveryAddress*/
    /*END*/
	/*IF (deliveryAddress == null) || (deliveryAddress == "")*/
	AND FRST_ORDER_HEAD.DELIVERY_ADDRESS IS NULL
	/*END*/    
	
    /*IF (deliveryTelNo != null) && (deliveryTelNo != "")*/
    AND FRST_ORDER_HEAD.DELIVERY_TEL_NO = /*deliveryTelNo*/
    /*END*/
	/*IF (deliveryTelNo == null) || (deliveryTelNo == "")*/
	AND FRST_ORDER_HEAD.DELIVERY_TEL_NO IS NULL
	/*END*/    
	AND NOT EXISTS( SELECT * FROM ORDER_DETAIL INNER JOIN SHIPPING SHP ON ORDER_DETAIL.SHIPPING_NO = SHP.SHIPPING_NO WHERE ORDER_DETAIL.ORDER_NO = FRST_ORDER_HEAD.ORDER_NO )
	AND NOT EXISTS( SELECT * FROM PURCHASE_SUBCONTRACT WHERE PURCHASE_SUBCONTRACT.ORDER_NO =  FRST_ORDER_HEAD.ORDER_NO AND PURCHASE_SUBCONTRACT.STATUS <> 7 )
    AND NOT EXISTS( SELECT * FROM ORDER_VENDER_DELIVERY WHERE ORDER_VENDER_DELIVERY.VENDER_GROUP_CD = FRST_ORDER_HEAD.VENDER_GROUP_CD 
                                                                AND ORDER_VENDER_DELIVERY.SOM_DELIVERY_CD = FRST_ORDER_HEAD.DELIVERY_CD
                                                                AND ORDER_VENDER_DELIVERY.CTM_ORDER_SPLIT_FLG = 1 )