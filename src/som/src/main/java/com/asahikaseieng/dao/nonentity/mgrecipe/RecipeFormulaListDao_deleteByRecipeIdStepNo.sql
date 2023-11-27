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
	RECIPE_FORMULA
WHERE LINE_TYPE = -1
AND   RECIPE_ID = /*recipeId*/
AND   STEP_NO = /*stepNo*/
