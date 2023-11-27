/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-新規入力画面調合タンクマスタチェック検索用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipeAsprovaList
 * packageName=direction
 * methodName=getResouceCd
 *
 */
SELECT DISTINCT
    RESOUCE.RESOUCE_CD    AS RESOUCE_CD
,   RESOUCE.RESOUCE_NAME  AS RESOUCE_NAME
FROM
     RECIPE_RESOUCE RESOUCE
    ,RECIPE_ASPROVA ASPROVA
WHERE
        ASPROVA.RECIPE_ID = /*recipeId*/30818
    AND ASPROVA.RESOUCE_CD = /*resourceCd*/
    AND RESOUCE.RESOUCE_CD(+) = ASPROVA.RESOUCE_CD
ORDER BY
    RESOUCE_CD
