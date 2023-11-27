/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
*/

/**
 * 指図－フォーミュラ-配合タブ検索用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionFormulaList
 * packageName=direction
 * methodName=getSearchList
 *
 */
SELECT
     FORMULA.DIRECTION_DIVISION    AS DIRECTION_DIVISION
    ,FORMULA.DIRECTION_NO          AS DIRECTION_NO
    ,FORMULA.STEP_NO               AS STEP_NO
    ,FORMULA.LINE_NO               AS LINE_NO
    ,FORMULA.SEQ                   AS SEQ
    ,FORMULA.LINE_TYPE             AS LINE_TYPE
    ,FORMULA.ITEM_CD               AS ITEM_CD
    ,NVL(FORMULA.QTY,0)            AS QTY
    ,FORMULA.INPUTOR_CD            AS INPUTOR_CD
    ,FORMULA.INPUT_DATE            AS INPUT_DATE
    ,FORMULA.UPDATOR_CD            AS UPDATOR_CD
    ,FORMULA.UPDATE_DATE           AS UPDATE_DATE
    ,ITEM.ITEM_NAME                AS ITEM_NAME
    ,PROC.SEQ                      AS PROC_SEQ
    ,OPER.OPERATION_NAME           AS OPER_NAME

FROM
    DIRECTION_FORMULA FORMULA,
    DIRECTION_PROCEDURE PROC,
    ITEM ITEM,
    OPERATION OPER
WHERE
    LINE_TYPE = -1
    AND FORMULA.DIRECTION_DIVISION = /*directionDivision*/1
    AND FORMULA.DIRECTION_NO = /*directionNo*/'1'
    AND FORMULA.DIRECTION_NO = PROC.DIRECTION_NO
    AND FORMULA.STEP_NO = PROC.STEP_NO
    AND ITEM.ITEM_CD(+) = FORMULA.ITEM_CD
    AND PROC.OPERATION_CD = OPER.OPERATION_CD(+)
ORDER BY
    PROC_SEQ,
    SEQ
