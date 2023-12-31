/*
 * 納入先帳票用SQL
 *
 * entityName=DeliveryListForReport
 * packageName=deliverylistforreport
 * methodName=getListForReport
 *
 */

SELECT DELIVERY.*
, CARRY.CARRY_NAME1
, LOGIN.TANTO_NM
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM DELIVERY
, CARRY
, LOGIN
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE DELIVERY.DELIVERY_CD IS NOT NULL

/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND (DELIVERY.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
OR DELIVERY.DELIVERY_NAME1 LIKE  FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
OR DELIVERY.DELIVERY_NAME2 LIKE  FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%'))
/*END*/

AND DELIVERY.CARRY_CD = CARRY.CARRY_CD(+)
AND DELIVERY.TANTO_CD = LOGIN.TANTO_CD(+)
AND DELIVERY.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND DELIVERY.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY DELIVERY.DELIVERY_CD
