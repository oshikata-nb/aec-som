/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
*/

/**
 * 処方検査-レシピインデックス、STEP_NOで削除用SQL
 *
 * @author tosco
 *
 * entityName=RecipeInspection
 * packageName=mgrecipe
 * methodName=deleteByRecipeIdStepNo
 *
 */
DELETE 
FROM
	RECIPE_INSPECTION
WHERE
	RECIPE_INSPECTION.RECIPE_ID = /*recipeId*/
AND RECIPE_INSPECTION.STEP_NO = /*stepNo*/

