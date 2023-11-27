/**
 * @author t1344224
 *
 * entityName=DirectionStatusChangeDetailListForReport
 * packageName=directionstatuschangeforreport
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
	DIRECTION_HEADER
,	(SELECT
	    ITEM2.ITEM_CD ,
	    ITEM2.ITEM_NAME,
	    OTHER_COMPANY_CD1
	FROM
		VALID_ITEM_QUEUE ITEM2
	) ITEM_NAMES
,	LINE
,	DIRECTION_DETAIL DETAIL
,	LOGIN INPUTOR
,	LOGIN UPDATOR

WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'
AND	DIRECTION_HEADER.DIRECTION_NO = DETAIL.DIRECTION_NO
AND	DIRECTION_HEADER.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	DIRECTION_HEADER.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND	DETAIL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	DETAIL.UPDATOR_CD = UPDATOR.TANTO_CD(+)


/*IF (( condition.srhDirectionNo != null ) && ( condition.srhDirectionNo != "" ))*/
	AND	DIRECTION_HEADER.DIRECTION_NO LIKE /*condition.srhDirectionNo*/'%'
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
		AND	DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhProductionLine*/'%'
	/*END*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	DIRECTION_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (( condition.srhDirectionStatus != null ) && ( condition.srhDirectionStatus != "" ))*/
	/*IF (condition.srhDirectionStatus == "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS IN ('2','3','5')
	/*END*/
	/*IF (condition.srhDirectionStatus != "0")*/
			AND	DIRECTION_HEADER.DIRECTION_STATUS = /*condition.srhDirectionStatus*/1
	/*END*/		
/*END*/

ORDER BY
	DIRECTION_HEADER.DIRECTION_NO
,	DIRECTION_HEADER.ITEM_CD

