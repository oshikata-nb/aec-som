/*
 * Created on 2009/03/23
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
