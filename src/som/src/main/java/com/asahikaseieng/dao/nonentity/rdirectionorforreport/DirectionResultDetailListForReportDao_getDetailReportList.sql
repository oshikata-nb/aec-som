/**
 *
 * @author t1344224
 *
 * entityName=DirectionResultDetailListForReport
 * packageName=rdirectionorderforreport
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
    VALID_ITEM_QUEUE MAX_ITEM,
	LINE LINE,
	NAMES NAME,
	VALID_ITEM_QUEUE ITEM,
	RECIPE_RESOUCE RECIPE_RESOUCE,
	DIRECTION_HEADER HEAD
,	DIRECTION_DETAIL DETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR


WHERE
        HEAD.DIRECTION_DIVISION = '1'
    AND HEAD.DIRECTION_NO = DETAIL.DIRECTION_NO
    AND MAX_ITEM.ITEM_CD(+) = HEAD.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND LINE.PRODUCTION_LINE(+) = HEAD.PRODUCTION_LINE
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
    AND HEAD.COMPOUND_TANK_NO = RECIPE_RESOUCE.RESOUCE_CD(+)

AND	DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)

/*IF (( condition.directionNo != null ) && ( condition.directionNo != "" ))*/
	AND	HEAD.DIRECTION_NO LIKE /*condition.directionNo*/'O%'
/*END*/
/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	HEAD.PRODUCTION_LINE = /*condition.productionLine*/'O%'
/*END*/
/*IF ( condition.directionStatus != 0 )*/
	AND	HEAD.DIRECTION_STATUS = /*condition.directionStatus*/'O%'
/*END*/
/*IF ( condition.directionStatus == 0 )*/
	AND	HEAD.DIRECTION_STATUS IN (6,7,8)
/*END*/
/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	(HEAD.ITEM_CD LIKE /*condition.itemCd*/'O%' OR ITEM.ITEM_NAME LIKE /*condition.itemCd*/'O%')
/*END*/
/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/'O%'
/*END*/
/*IF (( condition.resultSdateDateFrom != null ) && ( condition.resultSdateDateFrom != "" ))*/
	AND TO_CHAR(HEAD.RESULT_SDATE,'YYYY/MM/DD HH24:MI') >= /*condition.resultSdateDateFrom*/''
/*END*/
/*IF (( condition.resultSdateDateTo != null ) && ( condition.resultSdateDateTo != "" ))*/
	AND TO_CHAR(HEAD.RESULT_SDATE,'YYYY/MM/DD HH24:MI') <= /*condition.resultSdateDateTo*/''
/*END*/
/*IF (( condition.resultEdateDateFrom != null ) && ( condition.resultEdateDateFrom != "" ))*/
	AND TO_CHAR(HEAD.RESULT_EDATE,'YYYY/MM/DD HH24:MI') >= /*condition.resultEdateDateFrom*/''
/*END*/
/*IF (( condition.resultEdateDateTo != null ) && ( condition.resultEdateDateTo != "" ))*/
	AND TO_CHAR(HEAD.RESULT_EDATE,'YYYY/MM/DD HH24:MI') <= /*condition.resultEdateDateTo*/''
/*END*/
/*IF (( condition.compoundTankNo != null ) && ( condition.compoundTankNo != "" ))*/
	AND	HEAD.COMPOUND_TANK_NO LIKE /*condition.compoundTankNo*/'EE'
/*END*/

ORDER BY
     HEAD.COMPOUND_TANK_NO
    ,HEAD.RESULT_SDATE
    ,HEAD.DIRECTION_NO
    ,HEAD.ITEM_CD



