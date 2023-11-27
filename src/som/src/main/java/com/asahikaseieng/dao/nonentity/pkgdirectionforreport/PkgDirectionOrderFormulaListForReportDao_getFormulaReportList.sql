/**
 *
 * @author t1344224
 *
 * entityName=PkgDirectionOrderFormulaListForReport
 * packageName=pkgdirectionforreport
 * methodName=getFormulaReportList
 *
 */
SELECT
	FORMULA.DIRECTION_DIVISION
,	FORMULA.DIRECTION_NO
,	FORMULA.STEP_NO
,	FORMULA.LINE_NO
,	FORMULA.SEQ
,	FORMULA.LINE_TYPE
,	FORMULA.ITEM_CD
,	ITEM_FORMULA.ITEM_NAME
,	NAME_FORMULA.NAME01
,	FORMULA.TONYU
,	FORMULA.DATAREAD
,	FORMULA.TONYUSOKUDO
,	FORMULA.QTY
,	FORMULA.STOCKPD_QTY
,	FORMULA.RESULT_QTY
,	FORMULA.SAMPLE_QTY
,	FORMULA.LOSS_QTY
,	FORMULA.DEFECT_QTY
,	FORMULA.ADJUST_QTY
,	FORMULA.COST
,	FORMULA.STEP_CONDITION
,	FORMULA.NOTES
,	FORMULA.LOCATION_CD
,	FIRST_LOCATION.LOCATION_NAME AS FIRST_LOCATION_NAME
,	FORMULA.NEXT_LOCATION_CD
,	SECOND_LOCATION.LOCATION_NAME AS SECOND_LOCATION_NAME
,	FORMULA.NEXT_AFTER_LOCATION_CD
,	THIRD_LOCATION.LOCATION_NAME AS THIRD_LOCATION_NAME
,	FORMULA.LOT_NO
,	FORMULA.MANUFACTURER_LOT_NO
,	FORMULA.FILL_QTY
,	FORMULA.FILL_RESULT_QTY
,	FORMULA.REMARK
,	FORMULA.INPUT_DATE
,	FORMULA.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	FORMULA.UPDATE_DATE
,	FORMULA.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM
    ITEM
,	ARTICLE_ATTRIBUTE_QUEUE ATRQUE
,	DIRECTION_HEADER HEAD
,	DIRECTION_FORMULA FORMULA
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	ITEM ITEM_FORMULA
,	NAMES NAME_FORMULA
,	LOCATION FIRST_LOCATION
,	LOCATION SECOND_LOCATION
,	LOCATION THIRD_LOCATION


WHERE (HEAD.DIRECTION_DIVISION = 2 OR HEAD.DIRECTION_DIVISION = 3)
AND (HEAD.DIRECTION_STATUS = 1 OR DIRECTION_STATUS = 2)
AND HEAD.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
AND ITEM.VERSION = ATRQUE.VERSION(+)

AND	HEAD.DIRECTION_NO = FORMULA.DIRECTION_NO

AND	FORMULA.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	FORMULA.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	FORMULA.ITEM_CD = ITEM_FORMULA.ITEM_CD(+)
AND	NAME_FORMULA.NAME_DIVISION(+) = 'UNIT'
AND	NAME_FORMULA.NAME_CD(+) = ITEM_FORMULA.UNIT_OF_OPERATION_MANAGEMENT
AND	FORMULA.LOCATION_CD = FIRST_LOCATION.LOCATION_CD(+)
AND	FORMULA.NEXT_LOCATION_CD = SECOND_LOCATION.LOCATION_CD(+)
AND	FORMULA.NEXT_AFTER_LOCATION_CD = THIRD_LOCATION.LOCATION_CD(+)
/*IF ( condition.directionDivision != null )*/
	AND	HEAD.DIRECTION_DIVISION = /*condition.directionDivision*/''
/*END*/

/*IF (( condition.directionNo != null ) && ( condition.directionNo != "" ))*/
	AND	HEAD.DIRECTION_NO = /*condition.directionNo*/''
/*END*/

/*IF (( condition.directionStatus != null ) && ( condition.directionStatus != 0 ))*/
	AND	HEAD.DIRECTION_STATUS = /*condition.directionStatus*/''
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	HEAD.PRODUCTION_LINE = /*condition.productionLine*/''
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	HEAD.ITEM_CD LIKE /*condition.itemCd*/''
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/''
/*END*/

/*IF ( condition.planedSdateFrom != null )*/
	AND	HEAD.PLANED_SDATE >= /*condition.planedSdateFrom*/''
/*END*/

/*IF ( condition.planedSdateTo != null )*/
	AND	HEAD.PLANED_SDATE <= /*condition.planedSdateTo*/''
/*END*/

/*IF ( condition.planedEdateFrom != null )*/
	AND	HEAD.PLANED_EDATE >= /*condition.planedEdateFrom*/''
/*END*/

/*IF ( condition.planedEdateTo != null )*/
	AND	HEAD.PLANED_EDATE <= /*condition.planedEdateTo*/''
/*END*/

/*IF (( condition.aspOrderNo != null ) && ( condition.aspOrderNo != "" ))*/
	AND	HEAD.ASP_ORDER_NO LIKE /*condition.aspOrderNo*/'%'
/*END*/

/*IF (( condition.packageLine != null ) && ( condition.packageLine != "" ))*/
	AND	HEAD.PACKAGE_LINE LIKE /*condition.packageLine*/'%'
/*END*/

ORDER BY
	HEAD.PACKAGE_LINE
,	HEAD.PLANED_SDATE
,	HEAD.DIRECTION_NO
,	FORMULA.STEP_NO
,	FORMULA.LINE_NO

