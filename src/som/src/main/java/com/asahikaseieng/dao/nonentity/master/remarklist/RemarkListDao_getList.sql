/*
 * 備考一覧用SQL
 *
 * entityName=RemarkList
 * packageName=remarklist
 * methodName=getList
 *
 */
SELECT REMARK.REMARK_NO, REMARK.VENDER_DIVISION, REMARK.VENDER_CD, REMARK.DELIVERY_CD, REMARK.ITEM_CD,
DELIVERY_NAME1, VENDER_NAME1, ITEM_NAME
FROM REMARK, VENDER, DELIVERY, VALID_ITEM_QUEUE ITEM_QUEUE
WHERE REMARK_NO IS NOT NULL

/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	REMARK.VENDER_DIVISION = /*condition.srhVenderDivision*/'%'
AND	(REMARK.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
OR VENDER_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
OR VENDER_NAME2 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%'))
/*END*/

/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND	(REMARK.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%'
OR DELIVERY_NAME1 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%')
OR DELIVERY_NAME2 LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%'))
/*END*/

/*IF (condition.srhItemCd != "") && (condition.srhItemCd != null) */
AND	(REMARK.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != "") && (condition.srhOtherCompanyCd1 != null) */
AND	OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

AND REMARK.VENDER_DIVISION = VENDER.VENDER_DIVISION(+)
AND REMARK.VENDER_CD = VENDER.VENDER_CD(+)
AND REMARK.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND REMARK.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
ORDER BY VENDER_CD, DELIVERY_CD, ITEM_CD
