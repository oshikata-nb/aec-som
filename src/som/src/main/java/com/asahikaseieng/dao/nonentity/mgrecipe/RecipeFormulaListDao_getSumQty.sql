/*
 * 配合量の合計値取得SQL
 *
 * entityName=RecipeFormula
 * packageName=mgrecipe
 * methodName=getSumQty
 *
 */
 
SELECT
     NAME.NAME01 AS                       UNIT_NAME
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
    ,FORMULA.QTY AS                       SUM_QTY

FROM
    (
        SELECT
             RECIPE_ID AS RECIPE_ID
            ,SUM(QTY) AS QTY
        FROM
            RECIPE_FORMULA
        WHERE
                RECIPE_ID = /*recipeId*/30796
		    /*IF ( procStepNo != 0 ) */
			    AND STEP_NO = /*procStepNo*/1
		    /*END*/
    	        AND LINE_TYPE = -1
        GROUP BY
            RECIPE_ID

    ) FORMULA,
    VALID_ITEM_QUEUE MAX_ITEM,
    NAMES NAME,
    VALID_ITEM_QUEUE ITEM,
	RECIPE_HEADER RHEAD
WHERE
	RHEAD.RECIPE_ID = /*recipeId*/30796
    AND MAX_ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND ITEM.ITEM_CD(+) = MAX_ITEM.ITEM_CD
    AND ITEM.VERSION(+) = MAX_ITEM.VERSION
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
    AND FORMULA.RECIPE_ID(+) = RHEAD.RECIPE_ID
