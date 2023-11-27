/*
 * Created on 2009/03/31
 *
 * $copyright$
 *
*/

/**
 * 外注原材料投入実績入力－処方ヘッダー存在チェック用SQL
 *
 * @author tosco
 *
 * entityName=MaterialRinputRecipeFormulaList
 * packageName=materialrinput
 * methodName=getCountHeader
 *
 */
SELECT
	RHEAD.RECIPE_ID
,	RHEAD.RECIPE_CD
,	RHEAD.RECIPE_VERSION
,	RHEAD.RECIPE_NAME
FROM
	RECIPE_HEADER RHEAD
WHERE
	RHEAD.RECIPE_TYPE = 3	AND			-- 3:Master
	RHEAD.APPROVAL_STATUS = 3 AND		-- 3:承認済み
	RHEAD.RECIPE_CD = /*recipeCd*/ AND
	RHEAD.RECIPE_VERSION = /*recipeVersion*/ AND
	RHEAD.ITEM_CD = /*itemCd*/
