/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/

/**
 * 製造指図－フォーミュラ-仕上タブ検索用SQL
 *
 * @author tosco
 *
 * entityName=DirectionDirectionFormulaList
 * packageName=directin
 * methodName=getSearchFinishList
 *
 */
SELECT
     FRML.DIRECTION_DIVISION    AS DIRECTION_DIVISION
    ,FRML.DIRECTION_NO          AS DIRECTION_NO
    ,FRML.STEP_NO               AS STEP_NO
    ,FRML.LINE_NO               AS LINE_NO
    ,FRML.SEQ                   AS SEQ
    ,FRML.LINE_TYPE             AS LINE_TYPE
    ,FRML.ITEM_CD               AS ITEM_CD
    ,FRML.QTY                   AS QTY
    ,FRML.INPUTOR_CD            AS INPUTOR_CD
    ,FRML.INPUT_DATE            AS INPUT_DATE
    ,FRML.UPDATOR_CD            AS UPDATOR_CD
    ,FRML.UPDATE_DATE           AS UPDATE_DATE
    ,PROC.SEQ                   AS PROC_SEQ
    ,ITEM.ITEM_NAME             AS ITEM_NAME
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
    ,NAME.NAME01                AS UNIT_NAME

FROM
    DIRECTION_FORMULA FRML,
    DIRECTION_PROCEDURE PROC,
    ITEM ITEM,
    NAMES NAME
WHERE
    FRML.DIRECTION_DIVISION = 1
    AND FRML.DIRECTION_NO = /*directionNo*/
    AND FRML.LINE_TYPE != -1
    AND PROC.DIRECTION_DIVISION(+) = FRML.DIRECTION_DIVISION
    AND PROC.DIRECTION_NO(+) = FRML.DIRECTION_NO
    AND PROC.STEP_NO(+) = FRML.STEP_NO
    AND ITEM.ITEM_CD(+) = FRML.ITEM_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
ORDER BY
    SEQ