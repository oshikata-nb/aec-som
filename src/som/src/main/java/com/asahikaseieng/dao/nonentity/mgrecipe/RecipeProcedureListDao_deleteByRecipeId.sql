/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/

/**
 * 処方プロシージャ-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=RecipeProcedure
 * packageName=mgrecipe
 * methodName=deleteByRecipeId
 *
 */
DELETE 
FROM
	RECIPE_PROCEDURE
WHERE
	RECIPE_PROCEDURE.RECIPE_ID = /*recipeId*/30732
