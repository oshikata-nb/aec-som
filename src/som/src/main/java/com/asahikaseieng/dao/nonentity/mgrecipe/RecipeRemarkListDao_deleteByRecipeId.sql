/*
 * Created on 2009/01/26
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
