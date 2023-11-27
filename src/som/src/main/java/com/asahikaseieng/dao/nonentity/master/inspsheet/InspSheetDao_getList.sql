/*
 * 規格書一覧用SQL
 *
 * entityName=InspSheet
 * packageName=master.inspsheet
 * methodName=getList
 *
 */
SELECT
    INSP.INSP_SHEET_CD AS INSP_SHEET_CD,
    INSP.INSP_SHEET_NAME AS INSP_SHEET_NAME,
    INSP.ITEM_CD AS ITEM_CD,
    INSP.CUSTOMER_CD AS CUSTOMER_CD,
    INSP.DELIVERY_CD AS DELIVERY_CD,
    INSP.LABEL_CD AS LABEL_CD,
    INSP.INPUTOR_CD AS INPUTOR_CD,
    INSP.INPUT_DATE AS INPUT_DATE,
    INSP.UPDATOR_CD AS UPDATOR_CD,
    INSP.UPDATE_DATE AS UPDATE_DATE,
    ITM.ITEM_NAME AS ITEM_NAME,
    VEN.VENDER_NAME AS VENDER_NAME,
    DEL.DELIVERY_NAME1 AS DELIVERY_NAME1,
    LBL.LABEL_PATH AS LABEL_PATH
FROM
    LABEL LBL,
    DELIVERY DEL,
    VENDER VEN,
    ITEM ITM,
    INSP_SHEET INSP
WHERE
    ITM.ITEM_CD = INSP.ITEM_CD
AND	VEN.VENDER_DIVISION = 'TS'
AND	VEN.VENDER_CD = INSP.CUSTOMER_CD
AND	DEL.DELIVERY_CD = INSP.DELIVERY_CD
AND	LBL.LABEL_CD(+) = INSP.LABEL_CD
/*IF (( condition.venderCode != null ) && ( condition.venderCode != "" ))*/
    AND	INSP.CUSTOMER_CD LIKE /*condition.venderCode*/'%'
/*END*/
/*IF (( condition.deliveryCode != null ) && ( condition.deliveryCode != "" ))*/
    AND	INSP.DELIVERY_CD LIKE /*condition.deliveryCode*/'%'
/*END*/
/*IF (( condition.itemCode != null ) && ( condition.itemCode != "" ))*/
    AND	INSP.ITEM_CD LIKE /*condition.itemCode*/'%'
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
ORDER BY ITEM_CD , CUSTOMER_CD,DELIVERY_CD