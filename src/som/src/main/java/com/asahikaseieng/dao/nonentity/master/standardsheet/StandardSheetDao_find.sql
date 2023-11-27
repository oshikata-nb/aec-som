/*
 * 規格書一覧用SQL
 *
 * entityName=StandardSheet
 * packageName=master.standardsheet
 * methodName=find
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
    STD.UPDATE_DATE AS UPDATE_DATE
FROM
    STANDARD_SHEET STD
WHERE
    STD.STD_SHEET_CD IS NOT NULL
/*IF (venderCd != null) && (venderCd != "")*/
    AND STD.CUSTOMER_CD = /*venderCd*/'%'
/*END*/

/*IF (venderCd == null) || (venderCd == "")*/
    AND STD.CUSTOMER_CD IS NULL
/*END*/

/*IF (deliveryCd != null) && (deliveryCd != "")*/
    AND STD.DELIVERY_CD = /*deliveryCd*/'%'
/*END*/

/*IF (deliveryCd == null) || (deliveryCd == "")*/
    AND STD.DELIVERY_CD IS NULL
/*END*/

/*IF (itemCd != null) && (itemCd != "")*/
    AND STD.ITEM_CD = /*itemCd*/'%'
/*END*/

/*IF (itemCd == null) || (itemCd == "")*/
    AND STD.ITEM_CD IS NULL
/*END*/

