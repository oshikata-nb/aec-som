/*
 * Created on 2009/03/2
 *
 * $copyright$
 *
*/

/**
 * 検査待ち在庫計上画面一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=StockBookingList
 * packageName=stockbooking
 * methodName=getSearchList
 *
 */
SELECT	DIRECTION_HEADER.DIRECTION_DIVISION
,	DIRECTION_HEADER.DIRECTION_NO
,	DIRECTION_HEADER.DIRECTION_DATE
,	DIRECTION_HEADER.ASP_ORDER_NO
,	DIRECTION_HEADER.DIRECTION_STATUS
,	DIRECTION_HEADER.RECIPE_ID
,	DIRECTION_HEADER.RECIPE_CD
,	DIRECTION_HEADER.RECIPE_VERSION
,	DIRECTION_HEADER.PRODUCTION_LINE
,	DIRECTION_HEADER.COMPOUND_TANK_NO
,	DIRECTION_HEADER.HOLD_TANK_NO
,	DIRECTION_HEADER.DISSOLUTION_TANK_NO
,	DIRECTION_HEADER.FILLTANK_NO
,	DIRECTION_HEADER.PACKAGE_LINE
,	DIRECTION_HEADER.CURRENT_STEP_NO
,	DIRECTION_HEADER.ITEM_CD
,	DIRECTION_HEADER.PLANED_QTY
,	DIRECTION_HEADER.RESULT_QTY
,	DIRECTION_HEADER.DIVIDE_FLAG
,	DIRECTION_HEADER.NEXT_PLANED_QTY
,	DIRECTION_HEADER.PDW_RESULT
,	DIRECTION_HEADER.PEOCESS_LOSS
,	DIRECTION_HEADER.LOT_NO
,	DIRECTION_HEADER.PLANED_SDATE
,	DIRECTION_HEADER.PLANED_EDATE
,	DIRECTION_HEADER.RESULT_SDATE
,	DIRECTION_HEADER.RESULT_EDATE
,	DIRECTION_HEADER.STERIT_SDATE
,	DIRECTION_HEADER.STERIT_EDATE
,	DIRECTION_HEADER.MEKKIN_TANK_TEMP_MIN
,	DIRECTION_HEADER.MEKKIN_TANK_TEMP_MAX
,	DIRECTION_HEADER.HAISUI_CHECK
,	DIRECTION_HEADER.STAMP_FLAG
,	DIRECTION_HEADER.STAMP_DATE
,	DIRECTION_HEADER.STAMP_TANTO_CD
,	DIRECTION_HEADER.PRODUCT_LABEL_FLAG
,	DIRECTION_HEADER.PRODUCT_LABEL_DATE
,	DIRECTION_HEADER.PRODUCT_LABEL_TANTO_CD
,	DIRECTION_HEADER.PRODUCT_RECORD_FLAG
,	DIRECTION_HEADER.PRODUCT_RECORD_DATE
,	DIRECTION_HEADER.PRODUCT_RECORD_TANTO_CD
,	DIRECTION_HEADER.STOCKDISS_LABEL_FLAG
,	DIRECTION_HEADER.STOCKDISS_LABEL_DATE
,	DIRECTION_HEADER.STOCKDISS_LABEL_TANTO_CD
,	DIRECTION_HEADER.CERTIFICATION_FLAG
,	DIRECTION_HEADER.CERTIFICATION_DATE
,	DIRECTION_HEADER.REMARK
,	DIRECTION_HEADER.NOTES
,	DIRECTION_HEADER.DELETE_FLAG
,	DIRECTION_HEADER.SEIZOTANTOCODE
,	DIRECTION_HEADER.SENJOTANTOCODE
,	DIRECTION_HEADER.MEKKINTANTOCODE
,	DIRECTION_HEADER.CHOGOTANKCONDI
,	DIRECTION_HEADER.YOBIYOKAICONDI
,	DIRECTION_HEADER.HOLDTANKCONDI
,	DIRECTION_HEADER.TOTAL_JOBTIME
,	DIRECTION_HEADER.ENPLOY_JOBTIME
,	DIRECTION_HEADER.COOPER_JOBTIME
,	DIRECTION_HEADER.APP_TANTO_CD
,	DIRECTION_HEADER.APP_DATE
,	DIRECTION_HEADER.LAST_APP_TANTO_CD
,	DIRECTION_HEADER.LAST_APP_DATE
,	DIRECTION_HEADER.INPUT_DATE
,	DIRECTION_HEADER.INPUTOR_CD
,	DIRECTION_HEADER.UPDATE_DATE
,	DIRECTION_HEADER.UPDATOR_CD

,	VALID_ITEM_QUEUE.ITEM_NAME
,	VALID_ITEM_QUEUE.STYLE_OF_PACKING
,	VALID_ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT
,	UNIT_NAMES.NAME01 as UNIT_NAME
,	NVL(SUM_JISSEKI_SEIHIN.SUM_SUURYOU ,0) as SUM_SUURYOU
,	SUM_JISSEKI_SEIHIN.LOCATION

FROM	DIRECTION_HEADER
,	VALID_ITEM_QUEUE

,	(SELECT	NAMES.NAME_CD
	,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'　--運用管理単位のため'UNIT'固定
	)UNIT_NAMES

,	(SELECT	HOSOSASHIZUNO
	,	NVL( SUM(SUURYOU), 0) as SUM_SUURYOU
	,	TRIM(TRAILING FROM MIN(LOCATION)) AS LOCATION
	
	FROM	JISSEKI_SEIHIN
	,	(SELECT	RPAD(DIRECTION_NO,11,' ') as DIRECTION_NO
		FROM	DIRECTION_HEADER
		WHERE	DIRECTION_DIVISION IN ('2','3')
		AND	DIRECTION_STATUS = 4
		--ここから画面上の実際の条件を追加する
		/*IF (( condition.srhResultEdateFrom != null ) && ( condition.srhResultEdateFrom != "" ))*/
			AND	DIRECTION_HEADER.RESULT_EDATE >= /*condition.srhResultEdateFrom*/
		/*END*/
		/*IF (( condition.srhResultEdateTo != null ) && ( condition.srhResultEdateTo != "" ))*/
			AND	DIRECTION_HEADER.RESULT_EDATE <= /*condition.srhResultEdateTo*/
		/*END*/
		/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
			/*IF (condition.srhProductionLine != "0")*/
					AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/
			/*END*/		
		/*END*/
	
		)NARROWING_DIRECTION_HEADER
	WHERE	JISSEKI_SEIHIN.HOSOSASHIZUNO = NARROWING_DIRECTION_HEADER.DIRECTION_NO
	AND 	NYUUSYUKKO = '1' --入出庫が入庫のもの
	GROUP BY
		HOSOSASHIZUNO
	)SUM_JISSEKI_SEIHIN


WHERE	DIRECTION_HEADER.DIRECTION_DIVISION IN('2','3')
AND	DIRECTION_STATUS = 4
AND	DIRECTION_HEADER.ITEM_CD = VALID_ITEM_QUEUE.ITEM_CD(+)
AND	VALID_ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)
AND	RPAD( DIRECTION_HEADER.DIRECTION_NO, 11, ' ') = SUM_JISSEKI_SEIHIN.HOSOSASHIZUNO(+)

--ここから画面上の実際の条件を追加する
/*IF (( condition.srhResultEdateFrom != null ) && ( condition.srhResultEdateFrom != "" ))*/
	AND	DIRECTION_HEADER.RESULT_EDATE >= /*condition.srhResultEdateFrom*/
/*END*/
/*IF (( condition.srhResultEdateTo != null ) && ( condition.srhResultEdateTo != "" ))*/
	AND	DIRECTION_HEADER.RESULT_EDATE <= /*condition.srhResultEdateTo*/
/*END*/
/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
			AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/
	/*END*/		
/*END*/


ORDER BY	DIRECTION_HEADER.RESULT_EDATE
,			DIRECTION_HEADER.DIRECTION_NO
