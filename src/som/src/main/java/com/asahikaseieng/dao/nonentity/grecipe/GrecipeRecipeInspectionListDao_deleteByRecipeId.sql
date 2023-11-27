/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/

/**
 * 処方検査-レシピインデックスで削除用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeInspectionList
 * packageName=grecipe
 * methodName=deleteByRecipeId
 *
 */
DELETE
FROM
	RECIPE_INSPECTION
WHERE
	RECIPE_INSPECTION.RECIPE_ID = /*recipeId*/30732
