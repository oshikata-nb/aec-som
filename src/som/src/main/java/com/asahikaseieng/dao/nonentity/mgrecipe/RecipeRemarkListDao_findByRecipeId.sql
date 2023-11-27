/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/

/**
 * 処方その他SQL
 * 		レシピIDに紐づくデータをすべて取得SQL
 *
 * @author tosco
 *
 * entityName=RecipeRemark
 * packageName=mgrecipe
 * methodName=findByRecipeId
 *
 */
SELECT
	RECIPE_REMARK.RECIPE_ID
,	RECIPE_REMARK.GENERAL_RECIPE_REMARK
,	RECIPE_REMARK.MASTER_RECIPE_REMARK
,	RECIPE_REMARK.INPUTOR_CD
,	RECIPE_REMARK.INPUT_DATE
,	RECIPE_REMARK.UPDATOR_CD
,	RECIPE_REMARK.UPDATE_DATE
FROM
	RECIPE_REMARK

WHERE
	RECIPE_REMARK.RECIPE_ID IS NOT NULL
/*IF (( recipeId != null ) && ( recipeId != "" ))*/
	AND	RECIPE_REMARK.RECIPE_ID = /*recipeId*/
/*END*/
