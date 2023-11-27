/**
 * @author t1344224
 *
 * entityName=PkgRdirectionDetailListForReport
 * packageName=pkgrdirectionforreport
 * methodName=getDetailReportList
 *
*/
SELECT
	DETAIL.PKG_DIRECTION_NO
,	DETAIL.ROW_NO
,	DETAIL.DIRECTION_NO
,	DETAIL.INPUT_DATE
,	DETAIL.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	DETAIL.UPDATE_DATE
,	DETAIL.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM
	LINE LINE
,	NAMES NAME
,	ITEM
,	ARTICLE_ATTRIBUTE_QUEUE ATRQUE
,	DIRECTION_HEADER HEAD
,	DIRECTION_DETAIL DETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR

WHERE (HEAD.DIRECTION_DIVISION = 2 OR HEAD.DIRECTION_DIVISION = 3)
AND HEAD.DIRECTION_STATUS IN (2, 3, 4, 5, 6, 7)
AND HEAD.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
AND ITEM.VERSION = ATRQUE.VERSION(+)
AND HEAD.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND NAME.NAME_DIVISION(+) = 'UNIT'
AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)

AND	HEAD.DIRECTION_NO = DETAIL.PKG_DIRECTION_NO

AND	DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)

/*IF ( condition.directionDivision != null )*/
	AND	HEAD.DIRECTION_DIVISION = /*condition.directionDivision*/
/*END*/

/*IF (( condition.directionNo != null ) && ( condition.directionNo != "" ))*/
	AND	HEAD.DIRECTION_NO = /*condition.directionNo*/
/*END*/

/*IF (( condition.directionStatus != null ) && ( condition.directionStatus != 0 ))*/
	AND	HEAD.DIRECTION_STATUS = /*condition.directionStatus*/
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	HEAD.PRODUCTION_LINE = /*condition.productionLine*/
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	HEAD.ITEM_CD LIKE /*condition.itemCd*/
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/
/*END*/

/*IF ( condition.resultSdateFrom != null )*/
	AND	HEAD.RESULT_SDATE >= /*condition.resultSdateFrom*/
/*END*/

/*IF ( condition.resultSdateTo != null )*/
	AND	HEAD.RESULT_SDATE <= /*condition.resultSdateTo*/
/*END*/

/*IF ( condition.resultEdateFrom != null )*/
	AND	HEAD.RESULT_EDATE >= /*condition.resultEdateFrom*/
/*END*/

/*IF ( condition.resultEdateTo != null )*/
	AND	HEAD.RESULT_EDATE <= /*condition.resultEdateTo*/
/*END*/

/*IF (( condition.packageLine != null ) && ( condition.packageLine != "" ))*/
	AND	HEAD.PACKAGE_LINE LIKE /*condition.packageLine*/'%'
/*END*/

/*IF (( condition.lotNo != null ) && ( condition.lotNo != "" ))*/
	AND	HEAD.LOT_NO = /*condition.lotNo*/''
/*END*/

ORDER BY
	HEAD.PACKAGE_LINE
,	HEAD.RESULT_SDATE
,	HEAD.DIRECTION_NO
,	DETAIL.ROW_NO




