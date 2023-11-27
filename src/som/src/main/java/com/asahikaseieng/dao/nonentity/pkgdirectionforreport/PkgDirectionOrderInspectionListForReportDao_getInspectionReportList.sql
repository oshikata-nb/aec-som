/**
 *
 * @author t1344224
 *
 * entityName=PkgDirectionOrderInspectionListForReport
 * packageName=pkgdirectionforreport
 * methodName=getInspectionReprotList
 *
 */
SELECT
	INSPECTION.DIRECTION_DIVISION
,	INSPECTION.DIRECTION_NO
,	INSPECTION.STEP_NO
,	INSPECTION.LINE_NO
,	INSPECTION.SEQ
,	INSPECTION.INSPECTION_CD
,	NAMES.NAME01 AS INSPECTION_NAME
,	INSPECTION.DIVISION
,	INSPECTION.VALUE_TYPE
,	INSPECTION.VALUE1
,	INSPECTION.RESULT_VALUE1
,	INSPECTION.VALUE2
,	INSPECTION.RESULT_VALUE2
,	INSPECTION.CONDITION
,	INSPECTION.NOTES
,	INSPECTION.REMARK
,	INSPECTION.INPUT_DATE
,	INSPECTION.INPUTOR_CD
,	INSPECTION.UPDATE_DATE
,	INSPECTION.UPDATOR_CD
,	INSPECTION.INPUT_DATE
,	INSPECTION.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	INSPECTION.UPDATE_DATE
,	INSPECTION.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM
    ITEM
,	ARTICLE_ATTRIBUTE_QUEUE ATRQUE
,	DIRECTION_HEADER HEAD

,	DIRECTION_INSPECTION INSPECTION
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	NAMES


WHERE (HEAD.DIRECTION_DIVISION = 2 OR HEAD.DIRECTION_DIVISION = 3)
AND (HEAD.DIRECTION_STATUS = 1 OR DIRECTION_STATUS = 2)
AND HEAD.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
AND ITEM.VERSION = ATRQUE.VERSION(+)

AND	HEAD.DIRECTION_NO = INSPECTION.DIRECTION_NO

AND	INSPECTION.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	INSPECTION.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	INSPECTION.INSPECTION_CD = NAMES.NAME_CD(+)
AND	NAMES.NAME_DIVISION = 'STDV'
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
,	INSPECTION.STEP_NO
,	INSPECTION.LINE_NO


