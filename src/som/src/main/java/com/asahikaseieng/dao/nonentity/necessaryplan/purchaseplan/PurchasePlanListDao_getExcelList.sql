/*
 * 購買計画-Excel帳票出力用SQL
 *
 * entityName=PurchasePlanList
 * packageName=com.asahikaseieng.dao.nonentity.necessaryplan.purchaseplan
 * methodName=getExcelList
 *
 */
SELECT
    PCS.PLAN_NO AS PLAN_NO,
    PCS.ITEM_CD AS ITEM_CD,
    PCS.ORDER_DATE AS ORDER_DATE,
    PCS.DUE_DATE AS DUE_DATE,
    PCS.VENDER_CD AS VENDER_CD,
    PCS.PLANED_QTY AS PLANED_QTY,
    PCS.UNIT AS UNIT,
    PCS.INPUT_DATE AS INPUT_DATE,
    PCS.INPUTOR_CD AS INPUTOR_CD,
    PCS.UPDATE_DATE AS UPDATE_DATE,
    PCS.UPDATOR_CD AS UPDATOR_CD,
    VEN.VENDER_NAME AS VENDER_NAME,
    ITM.ITEM_NAME AS ITEM_NAME,
    NAM.NAME01 AS UNIT_NAME
FROM
	NAMES NAM,
    VENDER VEN,
    ITEM ITM,
    PURCHASE_PLAN PCS
WHERE
/*IF (( condition.orderStartDate != null ) && ( condition.orderStartDate != "" ))*/
    PCS.ORDER_DATE >= /*condition.orderStartDate*/ AND
/*END*/
/*IF (( condition.orderEndDate != null ) && ( condition.orderEndDate != "" ))*/
    PCS.ORDER_DATE <= /*condition.orderEndDate*/ AND
/*END*/
/*IF (( condition.deadlineStartDate != null ) && ( condition.deadlineStartDate != "" ))*/
    PCS.DUE_DATE >= /*condition.deadlineStartDate*/ AND
/*END*/
/*IF (( condition.deadlineEndDate != null ) && ( condition.deadlineEndDate != "" ))*/
    PCS.DUE_DATE <= /*condition.deadlineEndDate*/ AND
/*END*/
/*IF (( condition.venderCd != null ) && ( condition.venderCd != "" ))*/
    PCS.VENDER_CD LIKE /*condition.venderCd*/ AND
/*END*/
/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
    PCS.ITEM_CD LIKE /*condition.itemCd*/ AND
/*END*/
    VEN.VENDER_DIVISION(+) = 'SI' AND
    VEN.VENDER_CD(+) = PCS.VENDER_CD AND
    ITM.PURCHASE_DIVISION(+) != 0 AND 
    ITM.ITEM_CD(+) = PCS.ITEM_CD AND
    NAM.NAME_DIVISION(+) = 'UNIT' AND
	NAM.NAME_CD(+) = PCS.UNIT
ORDER BY
    VENDER_CD,
	ORDER_DATE,
    ITEM_CD
    