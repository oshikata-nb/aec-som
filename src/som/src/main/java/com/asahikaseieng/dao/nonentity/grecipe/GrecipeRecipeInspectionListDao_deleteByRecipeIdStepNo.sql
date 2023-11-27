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
 * entityName=GrecipeRecipeInspectionList
 * packageName=grecipe
 * methodName=deleteByRecipeIdStepNo
 *
 */
DELETE 
FROM
	RECIPE_INSPECTION
WHERE
	RECIPE_INSPECTION.RECIPE_ID = /*recipeId*/
AND RECIPE_INSPECTION.STEP_NO = /*stepNo*/

