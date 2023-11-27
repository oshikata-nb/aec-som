/*
 * 規格書一覧用SQL
 *
 * entityName=StandardSheet
 * packageName=master.standardsheet
 * methodName=getList
 *
 */
SELECT
    STD.STD_SHEET_CD AS STD_SHEET_CD,
    STD.STD_SHEET_NAME AS STD_SHEET_NAME,
    STD.ITEM_CD AS ITEM_CD,
    STD.CUSTOMER_CD AS CUSTOMER_CD,
    STD.DELIVERY_CD AS DELIVERY_CD,
    STD.LABEL_CD AS LABEL_CD,
    STD.LABEL_ADD_CD AS LABEL_ADD_CD,
    STD.INPUTOR_CD AS INPUTOR_CD,
    STD.INPUT_DATE AS INPUT_DATE,
    STD.UPDATOR_CD AS UPDATOR_CD,
    STD.UPDATE_DATE AS UPDATE_DATE,
    ITM.ITEM_NAME AS ITEM_NAME,
    VEN.VENDER_NAME AS VENDER_NAME,
    DEL.DELIVERY_NAME1 AS DELIVERY_NAME1,
    LBL.LABEL_PATH AS LABEL_PATH
FROM
    LABEL LBL,
    DELIVERY DEL,
    VENDER VEN,
    ITEM ITM,
    STANDARD_SHEET STD
WHERE
	ITM.ITEM_CD = STD.ITEM_CD
AND	VEN.VENDER_DIVISION = 'TS'
AND	VEN.VENDER_CD = STD.CUSTOMER_CD
AND	DEL.DELIVERY_CD = STD.DELIVERY_CD
AND	LBL.LABEL_CD(+) = STD.LABEL_CD
/*IF (( condition.venderCode != null ) && ( condition.venderCode != "" ))*/
    AND	STD.CUSTOMER_CD LIKE /*condition.venderCode*/'%'
/*END*/
/*IF (( condition.deliveryCode != null ) && ( condition.deliveryCode != "" ))*/
    AND	STD.DELIVERY_CD LIKE /*condition.deliveryCode*/'%'
/*END*/
/*IF (( condition.itemCode != null ) && ( condition.itemCode != "" ))*/
    AND	STD.ITEM_CD LIKE /*condition.itemCode*/'%'
/*END*/
/*IF (( condition.venderName != "" ) && ( condition.venderName != null )) */
    AND	VEN.VENDER_NAME	LIKE /*condition.venderName*/'%'
/*END*/
/*IF (( condition.deliveryName != "" ) && ( condition.deliveryName != null )) */
    AND	DEL.DELIVERY_NAME1 LIKE /*condition.deliveryName*/'%'
/*END*/
/*IF (( condition.itemName != "" ) && ( condition.itemName != null )) */
    AND	ITM.ITEM_NAME LIKE /*condition.itemName*/'%'
/*END*/
ORDER BY
    ITEM_CD,CUSTOMER_CD,DELIVERY_CD

