/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 原処方－工程順序コンボボックス用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeProcedureSetpNoForComboboxesDao
 * packageName=grecipe
 * methodName=findByRecipeId
 *
 */
SELECT
	RECIPE_PROCEDURE.RECIPE_ID
,	RECIPE_PROCEDURE.STEP_NO
,	RECIPE_PROCEDURE.SEQ
FROM
	RECIPE_PROCEDURE
WHERE
	RECIPE_PROCEDURE.RECIPE_ID = /*recipeId*/
ORDER BY
	RECIPE_PROCEDURE.SEQ
