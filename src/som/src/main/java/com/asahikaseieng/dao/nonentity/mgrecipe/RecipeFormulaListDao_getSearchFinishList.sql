/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/

/**
 * 処方フォーミュラ-仕上タブ検索用SQL
 *
 * @author tosco
 *
 * entityName=RecipeFormula
 * packageName=mgrecipe
 * methodName=getSearchFinishList
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
    ,FRML.INPUTOR_CD            AS INPUTOR_CD
    ,FRML.INPUT_DATE            AS INPUT_DATE
    ,FRML.UPDATOR_CD            AS UPDATOR_CD
    ,FRML.UPDATE_DATE           AS UPDATE_DATE
    ,PROC.SEQ                   AS PROC_SEQ
    ,ITEM.ITEM_NAME             AS ITEM_NAME
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
    ,NAME.NAME01                AS UNIT_NAME

FROM
    RECIPE_FORMULA FRML,
    RECIPE_PROCEDURE PROC,
    VALID_ITEM_QUEUE MAX_ITEM,
    VALID_ITEM_QUEUE ITEM,
    NAMES NAME
WHERE
    FRML.LINE_TYPE != -1
    AND FRML.RECIPE_ID = /*recipeId*/
    AND FRML.RECIPE_ID = PROC.RECIPE_ID(+)
    AND FRML.STEP_NO = PROC.STEP_NO(+)
    AND MAX_ITEM.ITEM_CD(+) = FRML.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
ORDER BY
    SEQ