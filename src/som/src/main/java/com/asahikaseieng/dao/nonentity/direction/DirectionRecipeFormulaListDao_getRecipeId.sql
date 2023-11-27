/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/

/**
 * 処方フォームラ一覧用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipeFormulaList
 * packageName=direction
 * methodName=getRecipeId
 *
 */
SELECT
	RECIPE_FORMULA.RECIPE_ID AS      RECIPE_ID
,	RECIPE_FORMULA.STEP_NO AS        STEP_NO
,	RECIPE_FORMULA.LINE_NO AS        LINE_NO
,	RECIPE_FORMULA.SEQ AS            SEQ
,	RECIPE_FORMULA.LINE_TYPE AS      LINE_TYPE
,	RECIPE_FORMULA.ITEM_CD AS        ITEM_CD
,	RECIPE_FORMULA.QTY AS            QTY
,	RECIPE_FORMULA.COST AS           COST
,	RECIPE_FORMULA.COST_UNIT AS      COST_UNIT
,	RECIPE_FORMULA.REMARK AS         REMARK
,	RECIPE_FORMULA.NOTES AS          NOTES
,	RECIPE_FORMULA.TONYU AS          TONYU
,	RECIPE_FORMULA.TONYUSOKUDO AS    TONYUSOKUDO
,	RECIPE_FORMULA.DATAREAD AS       DATAREAD
,	RECIPE_FORMULA.INPUTOR_CD AS     INPUTOR_CD
,	RECIPE_FORMULA.INPUT_DATE AS     INPUT_DATE
,	RECIPE_FORMULA.UPDATOR_CD AS     UPDATOR_CD
,	RECIPE_FORMULA.UPDATE_DATE AS    UPDATE_DATE
FROM
	RECIPE_FORMULA
WHERE
	RECIPE_FORMULA.RECIPE_ID = /*recipeId*/30732
ORDER BY
      RECIPE_FORMULA.RECIPE_ID
    , RECIPE_FORMULA.STEP_NO
    , RECIPE_FORMULA.LINE_NO
