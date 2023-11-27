/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/

/**
 * 基本処方－その他タグ．
 * 		レシピIDに紐づくデータを1件取得SQL
 *
 * @author tosco
 *
 * entityName=RecipeRemark
 * packageName=mgrecipe
 * methodName=getByRecipeId
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
