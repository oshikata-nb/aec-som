/**
 * @author tosco
 *
 * entityName=ProductInspectCompProcedureListForReport
 * packageName=productinspectcompforreport
 * methodName=getProcedureReportList
 *
 */
SELECT	

	PROCEDURE.DIRECTION_DIVISION
,	PROCEDURE.DIRECTION_NO
,	PROCEDURE.STEP_NO
,	PROCEDURE.SEQ
,	PROCEDURE.OPERATION_CD
,	PROCEDURE_OPE.OPERATION_NAME
,	PROCEDURE.CONDITION
,	PROCEDURE.REMARK
,	PROCEDURE.NOTES
,	PROCEDURE.LEADTIME
,	PROCEDURE.START_DATE
,	PROCEDURE.END_DATE
,	PROCEDURE.RESULT_SDATE
,	PROCEDURE.RESULT_EDATE
,	PROCEDURE.CONDITION_TEMP
,	PROCEDURE.CONDITION_TIME
,	PROCEDURE.STIR_SPEED1
,	PROCEDURE.STIR_SPEED2
,	PROCEDURE.WATER_WEIGHT
,	PROCEDURE.MAIN_STREAM
,	PROCEDURE.PH
,	PROCEDURE.RESULT_CONDITION_TEMP
,	PROCEDURE.RESULT_STIR_SPEED
,	PROCEDURE.RESULT_PH
,	PROCEDURE.FILLING_QTY
,	PROCEDURE.FILLING_UNIT
,	PROCEDURE.NET
,	PROCEDURE.WEIGHT_MIN
,	PROCEDURE.WEIGHT_MAX
,	PROCEDURE.FILTER
,	PROCEDURE.AUTO_CHECKER_MIN
,	PROCEDURE.AUTO_CHECKER_MAX
,	PROCEDURE.GROSS_CHECKER_MIN
,	PROCEDURE.GROSS_CHECKER_MAX
,	PROCEDURE.OPENING_TORQUE_MIN
,	PROCEDURE.OPENING_TORQUE_MAX
,	PROCEDURE.HOT_AIR_PRESET_TEMP
,	PROCEDURE.HOT_AIR_PRESSURE
,	PROCEDURE.MESH
,	PROCEDURE.AUTO_CHECKER_AVE
,	PROCEDURE.GROSS_CHECKER_AVE
,	PROCEDURE.CLOSING_TORQUE_MIN
,	PROCEDURE.CLOSING_TORQUE_MAX
,	PROCEDURE.TORQUE_PRESSURE
,	PROCEDURE.AIR_PRESSURE
,	PROCEDURE.VCLOSE_TIME
,	PROCEDURE.FIRST_HEAT_SEAL
,	PROCEDURE.SECOND_HEAT_SEAL
,	PROCEDURE.INPUT_DATE
,	PROCEDURE.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	PROCEDURE.UPDATE_DATE
,	PROCEDURE.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM	DIRECTION_HEADER HEAD
,	(SELECT
	    ITEM2.ITEM_CD ,
	    ITEM2.ITEM_NAME,
	    ITEM2.OTHER_COMPANY_CD1,
	    ITEM2.UNIT_OF_OPERATION_MANAGEMENT,
            ITEM2.STYLE_OF_PACKING

	FROM
	    VALID_ITEM_QUEUE MAX_ITEM
	,   VALID_ITEM_QUEUE  ITEM2
	WHERE
	    ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD AND
	    ITEM2.VERSION = MAX_ITEM.VERSION
	) ITEM_NAMES
,	(SELECT	NAMES.NAME_CD
	,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'
	)UNIT_NAMES
,	LINE
,	OPERATION
,	RECIPE_RESOUCE
,	(SELECT ITEM_CD,
			LOT_NO, 
			SUM(NVL(INVENTORY_QTY, 0) + NVL(INSPECTION_QTY, 0)) LOT_INVENTORY_QTY
		FROM LOT_INVENTORY
		WHERE LOCATION_CD IS NOT NULL
/*IF (( condition.srhDirectionNo != null ) && ( condition.srhDirectionNo != "" ))*/
		AND	LOT_INVENTORY.LOT_NO LIKE /*condition.srhDirectionNo*/''
/*END*/
/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	LOT_INVENTORY.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/
		GROUP BY ITEM_CD, LOT_NO
     ) LOT_INVENTORY

,	DIRECTION_PROCEDURE PROCEDURE
,	OPERATION PROCEDURE_OPE

,	LOGIN INPUTOR
,	LOGIN UPDATOR


WHERE	HEAD.DIRECTION_DIVISION IN ('2','3')
AND		HEAD.DIRECTION_STATUS IN('5','6')

AND		HEAD.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND		HEAD.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND		HEAD.CURRENT_STEP_NO = OPERATION.OPERATION_CD(+)
AND		ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)
AND		HEAD.PACKAGE_LINE = RECIPE_RESOUCE.RESOUCE_CD(+)
AND		HEAD.ITEM_CD = LOT_INVENTORY.ITEM_CD(+)
AND		HEAD.DIRECTION_NO = LOT_INVENTORY.LOT_NO(+)

AND	PROCEDURE.DIRECTION_NO = HEAD.DIRECTION_NO
AND	PROCEDURE.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	PROCEDURE.UPDATOR_CD = UPDATOR.TANTO_CD(+)

AND	PROCEDURE.OPERATION_CD = PROCEDURE_OPE.OPERATION_CD(+)

/*IF (( condition.srhDirectionNo != null ) && ( condition.srhDirectionNo != "" ))*/
	AND	HEAD.DIRECTION_NO LIKE /*condition.srhDirectionNo*/''
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine != "0")*/
			AND	HEAD.PRODUCTION_LINE = /*condition.srhProductionLine*/''
	/*END*/		
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	HEAD.ITEM_CD LIKE /*condition.srhItemCd*/''
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
/*END*/

/*IF (( condition.srhResultSdateFrom != null ) && ( condition.srhResultSdateFrom != "" ))*/
	AND	HEAD.RESULT_SDATE >= /*condition.srhResultSdateFrom*/''
/*END*/

/*IF (( condition.srhResultSdateTo != null ) && ( condition.srhResultSdateTo != "" ))*/
	AND	HEAD.RESULT_SDATE <= /*condition.srhResultSdateTo*/''
/*END*/

/*IF (( condition.srhResultEdateFrom != null ) && ( condition.srhResultEdateFrom != "" ))*/
	AND	HEAD.RESULT_EDATE >= /*condition.srhResultEdateFrom*/''
/*END*/

/*IF (( condition.srhResultEdateTo != null ) && ( condition.srhResultEdateTo != "" ))*/
	AND	HEAD.RESULT_EDATE <= /*condition.srhResultEdateTo*/''
/*END*/

/*IF (( condition.srhDirectionStatus != null ) && ( condition.srhDirectionStatus != "" ))*/
	/*IF (condition.srhDirectionStatus == "0")*/
			AND	HEAD.DIRECTION_STATUS IN ('5','6')
	/*END*/
	/*IF (condition.srhDirectionStatus != "0")*/
			AND	HEAD.DIRECTION_STATUS = /*condition.srhDirectionStatus*/''
	/*END*/		
/*END*/

/*IF (( condition.srhPackageLine != null ) && ( condition.srhPackageLine != "" ))*/
	AND	HEAD.PACKAGE_LINE LIKE /*condition.srhPackageLine*/''
/*END*/

/*IF (( condition.srhCertificationDate != null ) && ( condition.srhCertificationDate != "" ))*/
	AND	TO_CHAR(HEAD.CERTIFICATION_DATE,'YYYY/MM/DD') = /*condition.srhCertificationDate*/''
/*END*/

/*IF (( condition.srhLotNo != null ) && ( condition.srhLotNo != "" ))*/
	AND	HEAD.LOT_NO LIKE /*condition.srhLotNo*/''
/*END*/

ORDER BY
	HEAD.PLANED_SDATE
,	HEAD.DIRECTION_NO
,	PROCEDURE.STEP_NO





