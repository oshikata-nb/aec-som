/*
 * Created on 2009/02/28
 *
 * $copyright$
 *
*/

/**
 * 製造実績プロシージャ-工程タブ一覧検索用SQL
 *
 * @author tosco
 *
 * entityName=RdirectionDirectionProcedure
 * packageName=rdirection
 * methodName=getSearchList
 *
 */
SELECT
	DIRECTION_PROCEDURE.DIRECTION_DIVISION
,	DIRECTION_PROCEDURE.DIRECTION_NO
,	DIRECTION_PROCEDURE.STEP_NO
,	DIRECTION_PROCEDURE.SEQ
,	DIRECTION_PROCEDURE.OPERATION_CD
,	DIRECTION_PROCEDURE.CONDITION
,	DIRECTION_PROCEDURE.REMARK
,	DIRECTION_PROCEDURE.NOTES

,	DIRECTION_PROCEDURE.LEADTIME
,	DIRECTION_PROCEDURE.START_DATE
,	DIRECTION_PROCEDURE.END_DATE
,	DIRECTION_PROCEDURE.RESULT_SDATE
,	DIRECTION_PROCEDURE.RESULT_EDATE
,	DIRECTION_PROCEDURE.CONDITION_TEMP
,	DIRECTION_PROCEDURE.CONDITION_TIME
,	DIRECTION_PROCEDURE.STIR_SPEED1
,	DIRECTION_PROCEDURE.STIR_SPEED2
,	DIRECTION_PROCEDURE.WATER_WEIGHT
,	DIRECTION_PROCEDURE.MAIN_STREAM
,	DIRECTION_PROCEDURE.PH
,	DIRECTION_PROCEDURE.RESULT_CONDITION_TEMP
,	DIRECTION_PROCEDURE.RESULT_STIR_SPEED
,	DIRECTION_PROCEDURE.RESULT_PH
,	DIRECTION_PROCEDURE.FILLING_QTY
,	DIRECTION_PROCEDURE.FILLING_UNIT
,	DIRECTION_PROCEDURE.NET
,	DIRECTION_PROCEDURE.WEIGHT_MIN
,	DIRECTION_PROCEDURE.WEIGHT_MAX
,	DIRECTION_PROCEDURE.FILTER
,	DIRECTION_PROCEDURE.AUTO_CHECKER_MIN
,	DIRECTION_PROCEDURE.AUTO_CHECKER_MAX
,	DIRECTION_PROCEDURE.GROSS_CHECKER_MIN
,	DIRECTION_PROCEDURE.GROSS_CHECKER_MAX
,	DIRECTION_PROCEDURE.OPENING_TORQUE_MIN
,	DIRECTION_PROCEDURE.OPENING_TORQUE_MAX
,	DIRECTION_PROCEDURE.HOT_AIR_PRESET_TEMP
,	DIRECTION_PROCEDURE.HOT_AIR_PRESSURE
,	DIRECTION_PROCEDURE.MESH
,	DIRECTION_PROCEDURE.AUTO_CHECKER_AVE
,	DIRECTION_PROCEDURE.GROSS_CHECKER_AVE
,	DIRECTION_PROCEDURE.CLOSING_TORQUE_MIN
,	DIRECTION_PROCEDURE.CLOSING_TORQUE_MAX
,	DIRECTION_PROCEDURE.TORQUE_PRESSURE
,	DIRECTION_PROCEDURE.AIR_PRESSURE
,	DIRECTION_PROCEDURE.VCLOSE_TIME
,	DIRECTION_PROCEDURE.FIRST_HEAT_SEAL
,	DIRECTION_PROCEDURE.SECOND_HEAT_SEAL
,	DIRECTION_PROCEDURE.INPUTOR_CD
,	DIRECTION_PROCEDURE.INPUT_DATE
,	DIRECTION_PROCEDURE.UPDATOR_CD
,	DIRECTION_PROCEDURE.UPDATE_DATE
,   OPERATION.OPERATION_NAME
,   OPERATION.OPERATION_GROUP_CD
,   DECODE(FORMULA.STEP_NO, NULL, '×', '○') FORMULA_MARK
,   DECODE(INSPECTION.STEP_NO, NULL, '×', '○') INSPECTION_MARK
FROM
    DIRECTION_PROCEDURE DIRECTION_PROCEDURE
,   (SELECT
        DIRECTION_DIVISION,
        DIRECTION_NO,
        STEP_NO
    FROM
        DIRECTION_FORMULA
    WHERE
        LINE_TYPE = -1
    GROUP BY
        DIRECTION_DIVISION,
        DIRECTION_NO,
        STEP_NO
    ) FORMULA
,   (SELECT
        DIRECTION_DIVISION,
        DIRECTION_NO,
        STEP_NO
    FROM
        DIRECTION_INSPECTION
    GROUP BY
        DIRECTION_DIVISION,
        DIRECTION_NO,
        STEP_NO
    ) INSPECTION
,   OPERATION OPERATION
WHERE
    DIRECTION_PROCEDURE.DIRECTION_DIVISION = 1
AND DIRECTION_PROCEDURE.DIRECTION_NO = /*directionNo*/

AND DIRECTION_PROCEDURE.OPERATION_CD = OPERATION.OPERATION_CD(+)

AND DIRECTION_PROCEDURE.DIRECTION_DIVISION = FORMULA.DIRECTION_DIVISION(+)
AND DIRECTION_PROCEDURE.DIRECTION_NO = FORMULA.DIRECTION_NO(+)
AND DIRECTION_PROCEDURE.STEP_NO = FORMULA.STEP_NO(+)

AND DIRECTION_PROCEDURE.DIRECTION_DIVISION = INSPECTION.DIRECTION_DIVISION(+)
AND DIRECTION_PROCEDURE.DIRECTION_NO = INSPECTION.DIRECTION_NO(+)
AND DIRECTION_PROCEDURE.STEP_NO = INSPECTION.STEP_NO(+)

ORDER BY
    DIRECTION_PROCEDURE.DIRECTION_DIVISION
,   DIRECTION_PROCEDURE.DIRECTION_NO
,   DIRECTION_PROCEDURE.SEQ