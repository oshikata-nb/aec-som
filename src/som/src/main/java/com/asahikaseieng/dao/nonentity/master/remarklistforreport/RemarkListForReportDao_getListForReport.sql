/*
 * 備考帳票用SQL
 *
 * entityName=RemarkListForReport
 * packageName=remarklistforreport
 * methodName=getListForReport
 *
 */
SELECT REMARK.*
, VENDER.VENDER_NAME1
, DELIVERY.DELIVERY_NAME1
, ITEM_QUEUE.ITEM_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM REMARK
, VENDER
, DELIVERY
, VALID_ITEM_QUEUE ITEM_QUEUE
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE REMARK.REMARK_NO IS NOT NULL

/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	REMARK.VENDER_DIVISION = /*condition.srhVenderDivision*/'%'
AND	(REMARK.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
OR VENDER.VENDER_NAME1 LIKE /*condition.srhVenderCd*/'%'
OR VENDER.VENDER_NAME2 LIKE /*condition.srhVenderCd*/'%')
/*END*/

/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND	(REMARK.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
OR DELIVERY.DELIVERY_NAME1 LIKE /*condition.srhDeliveryCd*/'%'
OR DELIVERY.DELIVERY_NAME2 LIKE /*condition.srhDeliveryCd*/'%')
/*END*/

/*IF (condition.srhItemCd != "") && (condition.srhItemCd != null) */
AND	(REMARK.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_QUEUE.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != "") && (condition.srhOtherCompanyCd1 != null) */
AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

AND REMARK.VENDER_DIVISION = VENDER.VENDER_DIVISION(+)
AND REMARK.VENDER_CD = VENDER.VENDER_CD(+)
AND REMARK.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND REMARK.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
AND REMARK.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND REMARK.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY REMARK.VENDER_CD, REMARK.DELIVERY_CD, REMARK.ITEM_CD
