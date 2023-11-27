/**
 * @author t1344224
 *
 * entityName=StockBookingDetailListForReport
 * packageName=stockbookingforReport
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

FROM	DIRECTION_HEADER HEAD
,	VALID_ITEM_QUEUE


,	LOGIN INPUTOR
,	LOGIN UPDATOR

,	DIRECTION_DETAIL DETAIL


WHERE	HEAD.DIRECTION_DIVISION IN('2','3')
AND	HEAD.DIRECTION_STATUS = 4
AND	HEAD.ITEM_CD = VALID_ITEM_QUEUE.ITEM_CD(+)

AND	HEAD.DIRECTION_NO = DETAIL.PKG_DIRECTION_NO

AND	DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)


/*IF (( condition.srhResultSdateFrom != null ) && ( condition.srhResultSdateFrom != "" ))*/
	AND	HEAD.RESULT_SDATE >= /*condition.srhResultSdateFrom*/''
/*END*/
/*IF (( condition.srhResultSdateTo != null ) && ( condition.srhResultSdateTo != "" ))*/
	AND	HEAD.RESULT_SDATE <= /*condition.srhResultSdateTo*/''
/*END*/
/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
			AND	HEAD.PRODUCTION_LINE = /*condition.srhProductionLine*/''
	/*END*/		
/*END*/


ORDER BY	HEAD.RESULT_SDATE
,		HEAD.DIRECTION_NO
,		DETAIL.ROW_NO



