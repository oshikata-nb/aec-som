/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 処方その他-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=RecipeRemark
 * packageName=mgrecipe
 * methodName=deleteByRecipeId
 *
 */
DELETE
FROM
	RECIPE_REMARK
WHERE
	RECIPE_REMARK.RECIPE_ID = /*recipeId*/30732
