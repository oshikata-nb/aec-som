/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/

/**
 * 製造実績ヘッダー一覧用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionHeaderDetail
 * packageName=rdirection
 * methodName=getSearchList
 *
 */
SELECT
     HEAD.DIRECTION_DIVISION
    ,HEAD.DIRECTION_NO
    ,HEAD.DIRECTION_DATE
    ,HEAD.ASP_ORDER_NO
    ,HEAD.DIRECTION_STATUS
    ,HEAD.RECIPE_ID
    ,HEAD.RECIPE_CD
    ,HEAD.RECIPE_VERSION
    ,HEAD.PRODUCTION_LINE
    ,HEAD.COMPOUND_TANK_NO
    ,HEAD.HOLD_TANK_NO
    ,HEAD.DISSOLUTION_TANK_NO
    ,HEAD.FILLTANK_NO
    ,HEAD.PACKAGE_LINE
    ,HEAD.CURRENT_STEP_NO
    ,HEAD.ITEM_CD
    ,HEAD.PLANED_QTY
    ,HEAD.RESULT_QTY
    ,HEAD.DIVIDE_FLAG
    ,HEAD.NEXT_PLANED_QTY
    ,HEAD.PDW_RESULT
    ,HEAD.PEOCESS_LOSS
    ,HEAD.LOT_NO
    ,HEAD.PLANED_SDATE
    ,HEAD.PLANED_EDATE
    ,HEAD.RESULT_SDATE
    ,HEAD.RESULT_EDATE
    ,HEAD.STERIT_SDATE
    ,HEAD.STERIT_EDATE
    ,HEAD.MEKKIN_TANK_TEMP_MIN
    ,HEAD.MEKKIN_TANK_TEMP_MAX
    ,HEAD.HAISUI_CHECK
    ,HEAD.STAMP_FLAG
    ,HEAD.STAMP_DATE
    ,HEAD.STAMP_TANTO_CD
    ,HEAD.PRODUCT_LABEL_FLAG
    ,HEAD.PRODUCT_LABEL_DATE
    ,HEAD.PRODUCT_LABEL_TANTO_CD
    ,HEAD.PRODUCT_RECORD_FLAG
    ,HEAD.PRODUCT_RECORD_DATE
    ,HEAD.PRODUCT_RECORD_TANTO_CD
    ,HEAD.STOCKDISS_LABEL_FLAG
    ,HEAD.STOCKDISS_LABEL_DATE
    ,HEAD.STOCKDISS_LABEL_TANTO_CD
    ,HEAD.CERTIFICATION_FLAG
    ,HEAD.CERTIFICATION_DATE
    ,HEAD.REMARK
    ,HEAD.NOTES
    ,HEAD.DELETE_FLAG
    ,HEAD.SEIZOTANTOCODE
    ,HEAD.SENJOTANTOCODE
    ,HEAD.MEKKINTANTOCODE
    ,HEAD.CHOGOTANKCONDI
    ,HEAD.YOBIYOKAICONDI
    ,HEAD.HOLDTANKCONDI
    ,HEAD.TOTAL_JOBTIME
    ,HEAD.ENPLOY_JOBTIME
    ,HEAD.COOPER_JOBTIME
    ,HEAD.APP_TANTO_CD
    ,HEAD.APP_DATE
    ,HEAD.LAST_APP_TANTO_CD
    ,HEAD.LAST_APP_DATE
    ,HEAD.INPUT_DATE
    ,HEAD.INPUTOR_CD
    ,HEAD.UPDATE_DATE
    ,HEAD.UPDATOR_CD
    ,ITEM.ITEM_NAME AS             ITEM_NAME
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_OF_OPERATION_MANAGEMENT
    ,ITEM.SHIPPER_DIVISION AS      SHIPPER_DIVISION
    ,ITEM.OTHER_COMPANY_CD1 AS     OTHER_COMPANY_CD1
    ,LINE.PRODUCTION_LINE_NAME AS  PRODUCTION_LINE_NAME
    ,NAME.NAME01 AS                UNIT_NAME
FROM
    VALID_ITEM_QUEUE MAX_ITEM,
    LINE LINE,
    NAMES NAME,
    VALID_ITEM_QUEUE ITEM,
	DIRECTION_HEADER HEAD
WHERE
        HEAD.DIRECTION_DIVISION = '1'
    AND MAX_ITEM.ITEM_CD(+) = HEAD.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND LINE.PRODUCTION_LINE(+) = HEAD.PRODUCTION_LINE
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
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
	AND TO_CHAR(HEAD.RESULT_SDATE,'YYYY/MM/DD HH24:MI') >= /*condition.resultSdateDateFrom*/
/*END*/
/*IF (( condition.resultSdateDateTo != null ) && ( condition.resultSdateDateTo != "" ))*/
	AND TO_CHAR(HEAD.RESULT_SDATE,'YYYY/MM/DD HH24:MI') <= /*condition.resultSdateDateTo*/
/*END*/
/*IF (( condition.resultEdateDateFrom != null ) && ( condition.resultEdateDateFrom != "" ))*/
	AND TO_CHAR(HEAD.RESULT_EDATE,'YYYY/MM/DD HH24:MI') >= /*condition.resultEdateDateFrom*/
/*END*/
/*IF (( condition.resultEdateDateTo != null ) && ( condition.resultEdateDateTo != "" ))*/
	AND TO_CHAR(HEAD.RESULT_EDATE,'YYYY/MM/DD HH24:MI') <= /*condition.resultEdateDateTo*/
/*END*/
/*IF (( condition.compoundTankNo != null ) && ( condition.compoundTankNo != "" ))*/
	AND	HEAD.COMPOUND_TANK_NO LIKE /*condition.compoundTankNo*/'EE'
/*END*/


ORDER BY
     HEAD.COMPOUND_TANK_NO
    ,HEAD.RESULT_SDATE
    ,HEAD.DIRECTION_NO
    ,HEAD.ITEM_CD
