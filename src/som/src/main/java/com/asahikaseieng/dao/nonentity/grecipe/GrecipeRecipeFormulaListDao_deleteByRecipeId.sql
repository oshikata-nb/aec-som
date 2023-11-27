/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 処方フォーミュラ-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=RecipeFormula
 * packageName=mgrecipe
 * methodName=deleteByRecipeId
 *
 */
DELETE
FROM
	RECIPE_FORMULA
WHERE
	RECIPE_FORMULA.RECIPE_ID = /*recipeId*/30732
