/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/

/**
 * 包装実績－フォーミュラ-仕上タブ検索用SQL
 *
 * @author tosco
 *
 * entityName=PkgRdirectionDirectionFormulaList
 * packageName=pkgrdirectin
 * methodName=getSearchFinishList
 *
 */
SELECT
    FRML.DIRECTION_DIVISION
,	FRML.DIRECTION_NO
,	FRML.STEP_NO
,	FRML.LINE_NO
,	FRML.SEQ
,	FRML.LINE_TYPE
,	FRML.ITEM_CD
,	FRML.QTY
,	FRML.FILL_QTY
,	FRML.LOT_NO
,	FRML.TONYU
,	FRML.DATAREAD
,	FRML.TONYUSOKUDO
,	FRML.STOCKPD_QTY
,	FRML.RESULT_QTY
,	FRML.SAMPLE_QTY
,	FRML.LOSS_QTY
,	FRML.DEFECT_QTY
,	FRML.ADJUST_QTY
,	FRML.COST
,	FRML.STEP_CONDITION
,	FRML.NOTES
,	FRML.LOCATION_CD
,	FRML.NEXT_LOCATION_CD
,	FRML.NEXT_AFTER_LOCATION_CD
,	FRML.MANUFACTURER_LOT_NO
,	FRML.FILL_RESULT_QTY
,	FRML.REMARK
,	FRML.INPUTOR_CD
,	FRML.INPUT_DATE
,	FRML.UPDATOR_CD
,	FRML.UPDATE_DATE
,   PROC.SEQ                          AS PROC_SEQ
,   ITEM.ITEM_NAME                    AS ITEM_NAME
,   ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
,   NAME.NAME01                       AS UNIT_NAME

FROM
    DIRECTION_FORMULA FRML,
    DIRECTION_PROCEDURE PROC,
    ITEM ITEM,
    NAMES NAME
WHERE
    FRML.DIRECTION_DIVISION = /*directionDiv*/
    AND FRML.DIRECTION_NO = /*directionNo*/
    AND FRML.LINE_TYPE != -1
    AND FRML.LINE_NO < 10001 -- 仕上実績数量明細以外
    AND PROC.DIRECTION_DIVISION(+) = FRML.DIRECTION_DIVISION
    AND PROC.DIRECTION_NO(+) = FRML.DIRECTION_NO
    AND PROC.STEP_NO(+) = FRML.STEP_NO
    AND ITEM.ITEM_CD(+) = FRML.ITEM_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
ORDER BY
    SEQ