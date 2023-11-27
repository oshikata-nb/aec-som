/**
*@author t1344224
*
*entityName=PkgRdirectionHeaderListForReport
*packageName=pkgrdirectionforreport
*methodName=getHeaderReportList
*
*/
SELECT
	HEAD.DIRECTION_DIVISION
,	HEAD.DIRECTION_NO
,	HEAD.DIRECTION_DATE
,	HEAD.ASP_ORDER_NO
,	HEAD.DIRECTION_STATUS
,	CASE HEAD.DIRECTION_STATUS
		WHEN 1 THEN '未確定'
		WHEN 2 THEN '指図書発行済'
		WHEN 3 THEN '包装実績入力'
		WHEN 4 THEN '包装記録出力済'
		WHEN 5 THEN '検査待ち在庫計上'
		WHEN 6 THEN '製品検査入力済'
		WHEN 7 THEN '完了'
	END AS DIRECTION_STATUS_NAME
,	HEAD.RECIPE_ID
,	HEAD.RECIPE_CD
,	HEAD.RECIPE_VERSION
,	HEAD.PRODUCTION_LINE
,	LINE.PRODUCTION_LINE_NAME
,	HEAD.COMPOUND_TANK_NO
,	COMPOUND_TANK.RESOUCE_NAME AS COMPOUND_TANK_NAME
,	HEAD.HOLD_TANK_NO
,	HOLD_TANK.RESOUCE_NAME AS HOLD_TANK_NAME
,	HEAD.DISSOLUTION_TANK_NO
,	DISSOLUTION_TANK.RESOUCE_NAME AS DISSOLUTION_TANK_NAME
,	HEAD.FILLTANK_NO
,	FILLTANK.RESOUCE_NAME AS FILLTANK_NAME
,	HEAD.PACKAGE_LINE
,	PACKAGE_LINE.RESOUCE_NAME AS PACKAGE_LINE_NAME
,	HEAD.CURRENT_STEP_NO
,	HEAD.ITEM_CD
,	ITEM.ITEM_NAME
,	ITEM.STYLE_OF_PACKING
,	NAME.NAME01
,	HEAD.PLANED_QTY
,	HEAD.RESULT_QTY
,	HEAD.DIVIDE_FLAG
,	HEAD.NEXT_PLANED_QTY
,	HEAD.PDW_RESULT
,	HEAD.PEOCESS_LOSS
,	HEAD.LOT_NO
,	HEAD.PLANED_SDATE
,	HEAD.PLANED_EDATE
,	HEAD.RESULT_SDATE
,	HEAD.RESULT_EDATE
,	HEAD.STERIT_SDATE
,	HEAD.STERIT_EDATE
,	HEAD.MEKKIN_TANK_TEMP_MIN
,	HEAD.MEKKIN_TANK_TEMP_MAX
,	HEAD.HAISUI_CHECK
,	HEAD.STAMP_FLAG
,	HEAD.STAMP_DATE
,	HEAD.STAMP_TANTO_CD
,	STAMP_TANTO.TANTO_NM AS STAMP_TANTO_NAME
,	HEAD.PRODUCT_LABEL_FLAG
,	HEAD.PRODUCT_LABEL_DATE
,	HEAD.PRODUCT_LABEL_TANTO_CD
,	PRODUCT_LABEL.TANTO_NM AS PRODUCT_LABEL_TANTO_NAME
,	HEAD.PRODUCT_RECORD_FLAG
,	HEAD.PRODUCT_RECORD_DATE
,	HEAD.PRODUCT_RECORD_TANTO_CD
,	PRODUCT_RECORD.TANTO_NM AS PRODUCT_RECORD_TANTO_NAME
,	HEAD.STOCKDISS_LABEL_FLAG
,	HEAD.STOCKDISS_LABEL_DATE
,	HEAD.STOCKDISS_LABEL_TANTO_CD
,	STOCKDISS_LABEL.TANTO_NM AS STOCKDISS_LABEL_TANTO_NAME
,	HEAD.CERTIFICATION_FLAG
,	HEAD.CERTIFICATION_DATE
,	HEAD.REMARK
,	HEAD.NOTES
,	HEAD.DELETE_FLAG
,	HEAD.SEIZOTANTOCODE
,	SEIZOTANTO.TANTO_NM AS SEIZOTANTO_NAME
,	HEAD.SENJOTANTOCODE
,	SENJOTANTO.TANTO_NM AS SENJOTANTO_NAME
,	HEAD.MEKKINTANTOCODE
,	MEKKINTANTO.TANTO_NM AS MEKKINTANTO_NAME
,	HEAD.CHOGOTANKCONDI
,	HEAD.YOBIYOKAICONDI
,	HEAD.HOLDTANKCONDI
,	HEAD.TOTAL_JOBTIME
,	HEAD.ENPLOY_JOBTIME
,	HEAD.COOPER_JOBTIME
,	HEAD.APP_TANTO_CD
,	APP.TANTO_NM AS APP_TANTO_NAME
,	HEAD.APP_DATE
,	HEAD.LAST_APP_TANTO_CD
,	LAST_APP.TANTO_NM AS LAST_APP_TANTO_NAME
,	HEAD.LAST_APP_DATE
,	HEAD.INPUT_DATE
,	HEAD.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	HEAD.UPDATE_DATE
,	HEAD.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
FROM
LINE LINE,
NAMES NAME,
ITEM,
ARTICLE_ATTRIBUTE_QUEUE ATRQUE,
DIRECTION_HEADER HEAD
,	LOGIN STAMP_TANTO
,	LOGIN PRODUCT_LABEL
,	LOGIN PRODUCT_RECORD
,	LOGIN STOCKDISS_LABEL
,	LOGIN APP
,	LOGIN LAST_APP
,	LOGIN SEIZOTANTO
,	LOGIN MEKKINTANTO
,	LOGIN SENJOTANTO
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	RECIPE_RESOUCE COMPOUND_TANK
,	RECIPE_RESOUCE HOLD_TANK
,	RECIPE_RESOUCE DISSOLUTION_TANK
,	RECIPE_RESOUCE FILLTANK
,	RECIPE_RESOUCE PACKAGE_LINE



WHERE (HEAD.DIRECTION_DIVISION = 2 OR HEAD.DIRECTION_DIVISION = 3)
AND HEAD.DIRECTION_STATUS IN (2, 3, 4, 5, 6, 7)
AND HEAD.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM.ITEM_CD = ATRQUE.ITEM_CD(+)
AND ITEM.VERSION = ATRQUE.VERSION(+)
AND HEAD.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND NAME.NAME_DIVISION(+) = 'UNIT'
AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAME.NAME_CD(+)

AND	HEAD.STAMP_TANTO_CD = STAMP_TANTO.TANTO_CD(+)
AND	HEAD.PRODUCT_LABEL_TANTO_CD = PRODUCT_LABEL.TANTO_CD(+)
AND	HEAD.PRODUCT_RECORD_TANTO_CD = PRODUCT_RECORD.TANTO_CD(+)
AND	HEAD.STOCKDISS_LABEL_TANTO_CD = STOCKDISS_LABEL.TANTO_CD(+)
AND	HEAD.APP_TANTO_CD = APP.TANTO_CD(+)
AND	HEAD.LAST_APP_TANTO_CD = LAST_APP.TANTO_CD(+)
AND	HEAD.SEIZOTANTOCODE = SEIZOTANTO.TANTO_CD(+)
AND	HEAD.MEKKINTANTOCODE = MEKKINTANTO.TANTO_CD(+)
AND	HEAD.SENJOTANTOCODE = SENJOTANTO.TANTO_CD(+)
AND	HEAD.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	HEAD.UPDATOR_CD = UPDATOR.TANTO_CD(+)

AND	HEAD.COMPOUND_TANK_NO = COMPOUND_TANK.RESOUCE_CD(+)
AND	HEAD.HOLD_TANK_NO = HOLD_TANK.RESOUCE_CD(+)
AND	HEAD.DISSOLUTION_TANK_NO = DISSOLUTION_TANK.RESOUCE_CD(+)
AND	HEAD.FILLTANK_NO = FILLTANK.RESOUCE_CD(+)
AND	HEAD.PACKAGE_LINE = PACKAGE_LINE.RESOUCE_CD(+)

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
	HEAD.PACKAGE_LINE,
	HEAD.RESULT_SDATE,
	HEAD.DIRECTION_NO

