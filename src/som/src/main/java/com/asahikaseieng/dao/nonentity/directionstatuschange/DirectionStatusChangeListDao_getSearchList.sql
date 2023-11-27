/*
 * Created on 2009/05/28
 *
 * $copyright$
 *
*/

/**
 * 製造指図ステータス変更画面用SQL
 *
 * @author tosco
 *
 * entityName=DirectionStatusChangeList
 * packageName=directionstatuschange
 * methodName=getSearchList
 *
 */
SELECT	
	DIRECTION_HEADER.DIRECTION_DIVISION
,	DIRECTION_HEADER.DIRECTION_NO
,	DIRECTION_HEADER.DIRECTION_STATUS
,	DIRECTION_HEADER.PRODUCTION_LINE
,	DIRECTION_HEADER.ITEM_CD
,	DIRECTION_HEADER.UPDATE_DATE
,	OPERATION.OPERATION_NAME
,	ITEM_NAMES.ITEM_NAME
,	LINE.PRODUCTION_LINE_NAME
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
,	OPERATION

WHERE	DIRECTION_HEADER.DIRECTION_DIVISION = '1'
AND		DIRECTION_HEADER.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND		DIRECTION_HEADER.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND		DIRECTION_HEADER.CURRENT_STEP_NO = OPERATION.OPERATION_CD(+)

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