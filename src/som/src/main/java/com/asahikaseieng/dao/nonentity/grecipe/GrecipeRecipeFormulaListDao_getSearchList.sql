/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 処方フォーミュラ-配合タブ検索用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeFormula
 * packageName=grecipe
 * methodName=getSearchList
 *
 */
SELECT
     FRML.RECIPE_ID             AS RECIPE_ID
    ,FRML.STEP_NO               AS STEP_NO
    ,FRML.LINE_NO               AS LINE_NO
    ,FRML.SEQ                   AS SEQ
    ,FRML.LINE_TYPE             AS LINE_TYPE
    ,FRML.ITEM_CD               AS ITEM_CD
    ,FRML.QTY                   AS QTY
    ,FRML.COST                  AS COST
    ,FRML.COST_UNIT             AS COST_UNIT
    ,FRML.REMARK                AS REMARK
    ,FRML.NOTES                 AS NOTES
    ,FRML.TONYU                 AS TONYU
    ,FRML.TONYUSOKUDO           AS TONYUSOKUDO
    ,FRML.DATAREAD              AS DATAREAD
    ,FRML.INPUTOR_CD            AS INPUTOR_CD
    ,FRML.INPUT_DATE            AS INPUT_DATE
    ,FRML.UPDATOR_CD            AS UPDATOR_CD
    ,FRML.UPDATE_DATE           AS UPDATE_DATE
    ,PROC.SEQ                   AS PROC_SEQ
    ,ITEM.ITEM_NAME             AS ITEM_NAME
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT             AS UNIT_DIVISION
    ,OPER.OPERATION_NAME        AS OPER_NAME
    ,NAME.NAME01                AS UNIT_NAME

FROM
    RECIPE_FORMULA FRML,
    RECIPE_PROCEDURE PROC,
    OPERATION OPER,
    VALID_ITEM_QUEUE ITEM,
    NAMES NAME
WHERE
    FRML.LINE_TYPE = -1
    AND FRML.RECIPE_ID = /*recipeId*/30732
    /*IF ( procStepNo != 0 ) */
    AND FRML.STEP_NO = /*procStepNo*/1
    /*END*/
    AND FRML.RECIPE_ID = PROC.RECIPE_ID
    AND FRML.STEP_NO = PROC.STEP_NO
    AND PROC.OPERATION_CD = OPER.OPERATION_CD
    AND ITEM.ITEM_CD(+) = FRML.ITEM_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
ORDER BY
    PROC_SEQ,
    SEQ
