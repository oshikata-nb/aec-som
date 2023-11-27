/*
 * 規格書一覧用SQL
 *
 * entityName=InspSheet
 * packageName=master.inspsheet
 * methodName=find
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
    INSP.UPDATE_DATE AS UPDATE_DATE
FROM
    INSP_SHEET INSP
WHERE
    INSP.INSP_SHEET_CD IS NOT NULL
/*IF (venderCd != null) && (venderCd != "")*/
    AND INSP.CUSTOMER_CD = /*venderCd*/'%'
/*END*/

/*IF (venderCd == null) || (venderCd == "")*/
    AND INSP.CUSTOMER_CD IS NULL
/*END*/

/*IF (deliveryCd != null) && (deliveryCd != "")*/
    AND INSP.DELIVERY_CD = /*deliveryCd*/'%'
/*END*/

/*IF (deliveryCd == null) || (deliveryCd == "")*/
    AND INSP.DELIVERY_CD IS NULL
/*END*/

/*IF (itemCd != null) && (itemCd != "")*/
    AND INSP.ITEM_CD = /*itemCd*/'%'
/*END*/

/*IF (itemCd == null) || (itemCd == "")*/
    AND INSP.ITEM_CD IS NULL
/*END*/

