/*
 * 規格書一覧用SQL
 *
 * entityName=InspSheet
 * packageName=master.inspsheet
 * methodName=getDetail
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
    DEL.DELIVERY_NAME1 AS DELIVERY_NAME1
FROM
    LABEL LBL,
    DELIVERY DEL,
    VENDER VEN,
    ITEM ITM,
    INSP_SHEET INSP
WHERE
/*IF (( inspSheetCd != null ) && ( inspSheetCd != "" ))*/
    INSP.INSP_SHEET_CD = /*inspSheetCd*/'' AND
/*END*/
	ITM.ITEM_CD = INSP.ITEM_CD
AND	VEN.VENDER_DIVISION(+) = 'TS'
AND	VEN.VENDER_CD(+) = INSP.CUSTOMER_CD
AND	DEL.DELIVERY_CD(+) = INSP.DELIVERY_CD
AND	LBL.LABEL_CD(+) = INSP.LABEL_CD
