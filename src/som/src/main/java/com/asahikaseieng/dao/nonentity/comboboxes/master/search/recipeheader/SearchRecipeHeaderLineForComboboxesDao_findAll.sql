/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/

/**
 * 製造指図－新規入力画面_生産工場コンボボックス用SQL
 *
 * @author tosco
 *
 * entityName=SearchRecipeHeaderLineForComboboxes
 * packageName=search.recipeheader
 * methodName=findAll
 *
 */
SELECT
	LINE.PRODUCTION_LINE AS PRODUCTION_LINE
,	LINE.PRODUCTION_LINE_NAME AS PRODUCTION_LINE_NAME
,	LINE.INPUT_DATE AS INPUT_DATE
,	LINE.INPUTOR_CD AS INPUTOR_CD
,	LINE.UPDATE_DATE AS UPDATE_DATE
,	LINE.UPDATOR_CD AS UPDATOR_CD
FROM
	LINE
ORDER BY
	LINE.PRODUCTION_LINE
