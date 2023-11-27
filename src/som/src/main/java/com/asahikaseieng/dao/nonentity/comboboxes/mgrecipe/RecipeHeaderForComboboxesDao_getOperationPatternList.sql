/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/

/**
 * ヘッダー情報－工程パターン一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=RecipeHeader
 * packageName=mgrecipe
 * methodName=getOperationPatternList
 *
 */
SELECT
	 RECIPE_ID AS            RECIPE_ID
	,RECIPE_CD AS            RECIPE_CD
	,RECIPE_DESCRIPTION AS   RECIPE_DESCRIPTION
FROM
    RECIPE_HEADER
WHERE
    RECIPE_TYPE = 4
/*IF (( use != null ) && ( use != "" ))*/
	AND	RECIPE_USE = /*use*/
/*END*/
ORDER BY
    RECIPE_CD
