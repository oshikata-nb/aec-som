/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/

/**
 * 基本処方のORIGINAL_RECIPE_IDに設定されている件数を取得する
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeHeaderList
 * packageName=grecipe
 * methodName=getOriginalRecipeCount
 *
 */
SELECT
    COUNT(RECIPE_ID) as COUNT
FROM
    RECIPE_HEADER
WHERE
    ORIGINAL_RECIPE_ID  = /*recipeId*/
