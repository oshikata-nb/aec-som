/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/

/**
 * 処方検査-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=RecipeInspection
 * packageName=mgrecipe
 * methodName=deleteByRecipeId
 *
 */
DELETE 
FROM
	RECIPE_INSPECTION
WHERE
	RECIPE_INSPECTION.RECIPE_ID = /*recipeId*/30732
