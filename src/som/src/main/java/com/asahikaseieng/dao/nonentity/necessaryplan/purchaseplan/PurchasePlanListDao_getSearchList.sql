/*
 * 購買計画テーブル一覧用SQL
 *
 * entityName=PurchasePlanList
 * packageName=necessaryplan.purchaseplan
 * methodName=getSearchList
 *
 */
SELECT 	PURCHASE_PLAN.PLAN_NO
,       PURCHASE_PLAN.ITEM_CD
,       PURCHASE_PLAN.ORDER_DATE
,       PURCHASE_PLAN.DUE_DATE
,       PURCHASE_PLAN.VENDER_CD
,       PURCHASE_PLAN.PLANED_QTY
,       PURCHASE_PLAN.UNIT
,	nvl(VENDER.VENDER_NAME,'未登録') VENDER_NAME
,	nvl(ITEM.ITEM_NAME,'未登録') ITEM_NAME
,	nvl(NAMES.NAME01,'未登録') UNIT_NAME
FROM 	PURCHASE_PLAN PURCHASE_PLAN
	LEFT JOIN VENDER VENDER
		ON PURCHASE_PLAN.VENDER_CD = VENDER.VENDER_CD
		AND VENDER.VENDER_DIVISION = 'SI'
	LEFT JOIN ITEM ITEM
		ON PURCHASE_PLAN.ITEM_CD = ITEM.ITEM_CD
		AND ITEM.PURCHASE_DIVISION != 0
	LEFT JOIN NAMES NAMES
		ON PURCHASE_PLAN.UNIT = NAMES.NAME_CD
		AND NAMES.NAME_DIVISION = 'UNIT'

WHERE	PURCHASE_PLAN.PLAN_NO IS NOT NULL

/*IF (( condition.orderStartDate != null ) && ( condition.orderStartDate != "" ))*/
	AND	PURCHASE_PLAN.ORDER_DATE >= /*condition.orderStartDate*/
/*END*/

/*IF (( condition.orderEndDate != null ) && ( condition.orderEndDate != "" ))*/
	AND	PURCHASE_PLAN.ORDER_DATE <= /*condition.orderEndDate*/
/*END*/

/*IF (( condition.deadlineStartDate != null ) && ( condition.deadlineStartDate != "" ))*/
	AND	PURCHASE_PLAN.DUE_DATE >= /*condition.deadlineStartDate*/
/*END*/

/*IF (( condition.deadlineEndDate != null ) && ( condition.deadlineEndDate != "" ))*/
	AND	PURCHASE_PLAN.DUE_DATE <= /*condition.deadlineEndDate*/
/*END*/

/*IF (( condition.venderCd != null ) && ( condition.venderCd != "" ))*/
	AND	PURCHASE_PLAN.VENDER_CD LIKE /*condition.venderCd*/
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	PURCHASE_PLAN.ITEM_CD LIKE /*condition.itemCd*/
/*END*/

ORDER BY PURCHASE_PLAN.PLAN_NO
