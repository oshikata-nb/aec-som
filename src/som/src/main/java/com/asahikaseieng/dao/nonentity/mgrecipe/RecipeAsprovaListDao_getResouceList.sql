/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-asprovaタブ検索用SQL
 *
 * @author tosco
 *
 * entityName=RecipeAsprova
 * packageName=mgrecipe
 * methodName=getSearchList
 *
 */
SELECT 
    RESOUCE.RESOUCE_CD         AS RESOUCE_CD
,   RESOUCE.RESOUCE_NAME       AS RESOUCE_NAME
,   RESOUCE.RESOUCE_GROUP_CD   AS RESOUCE_GROUP_CD
,   'false'                    AS STR_CHECK_FLG
,   0                          AS RECIPE_PRIORITY
FROM
    RECIPE_RESOUCE RESOUCE
WHERE
    RESOUCE.RESOUCE_GROUP_CD = /*resouceGroupCd*/
AND RESOUCE.PRODUCTION_LINE = /*productionLine*/
ORDER BY
    RESOUCE_CD
