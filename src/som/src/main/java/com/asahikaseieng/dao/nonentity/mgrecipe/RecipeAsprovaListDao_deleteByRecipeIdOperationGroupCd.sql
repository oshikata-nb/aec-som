/*
 * Created on 2009/02/25
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-レシピインデックス、工程グループコードに紐づくデータをすべて削除
 *
 * @author tosco
 *
 * entityName=RecipeAsprova
 * packageName=mgrecipe
 * methodName=deleteByRecipeIdOperationGroupCd
 *
 */
DELETE 
FROM
	RECIPE_ASPROVA
WHERE
	RECIPE_ASPROVA.RECIPE_ID = /*recipeId*/
AND RECIPE_ASPROVA.OPERATION_GROUP_CD = /*operationGroupCd*/
	