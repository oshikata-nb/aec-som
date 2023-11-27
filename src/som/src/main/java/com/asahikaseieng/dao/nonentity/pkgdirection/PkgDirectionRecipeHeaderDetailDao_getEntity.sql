/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 包装指図－処方ヘッダー情報取得用SQL
 *
 * @author tosco
 *
 * entityName=PkgDirectionRecipeHeaderDetail
 * packageName=pkgdirection
 * methodName=getEntity
 *
 */
SELECT
	 RHEAD.RECIPE_ID AS            RECIPE_ID
	,RHEAD.RECIPE_CD AS            RECIPE_CD
	,RHEAD.RECIPE_VERSION AS       RECIPE_VERSION
	,RHEAD.RECIPE_TYPE AS          RECIPE_TYPE
	,RHEAD.RECIPE_DESCRIPTION AS   RECIPE_DESCRIPTION
	,RHEAD.RECIPE_MEMO AS          RECIPE_MEMO
	,RHEAD.RECIPE_STATUS AS        RECIPE_STATUS
	,RHEAD.RECIPE_USE AS           RECIPE_USE
	,RHEAD.RECIPE_PRIORITY AS      RECIPE_PRIORITY
	,RHEAD.ORIGINAL_RECIPE_ID AS   ORIGINAL_RECIPE_ID
	,RHEAD.PRODUCTION_LINE AS      PRODUCTION_LINE
	,RHEAD.ITEM_CD AS              ITEM_CD
	,RHEAD.MIN_QTY AS              MIN_QTY
	,RHEAD.MAX_QTY AS              MAX_QTY
	,RHEAD.STD_QTY AS              STD_QTY
	,RHEAD.UNIT_QTY AS             UNIT_QTY
	,RHEAD.COST AS                 COST
	,RHEAD.PROCESS_LOSS AS         PROCESS_LOSS
	,RHEAD.START_DATE AS           START_DATE
	,RHEAD.END_DATE AS             END_DATE
	,RHEAD.DELETE_FLAG AS          DELETE_FLAG
	,RHEAD.PRINT_FLAG AS           PRINT_FLAG
	,RHEAD.PRINT_DATE AS           PRINT_DATE
	,RHEAD.PRINT_TANTO_CD AS       PRINT_TANTO_CD
	,RHEAD.APPROVAL_STATUS AS      APPROVAL_STATUS
	,RHEAD.APP_TANTO_CD AS         APP_TANTO_CD
	,RHEAD.APP_DATE AS             APP_DATE
	,RHEAD.LAST_APP_TANTO_CD AS    LAST_APP_TANTO_CD
	,RHEAD.LAST_APP_DATE AS        LAST_APP_DATE
	,RHEAD.RECIPE_NAME AS          RECIPE_NAME
	,RHEAD.INPUTOR_CD AS           INPUTOR_CD
	,RHEAD.INPUT_DATE AS           INPUT_DATE
	,RHEAD.UPDATOR_CD AS           UPDATOR_CD
	,RHEAD.UPDATE_DATE AS          UPDATE_DATE
FROM
	RECIPE_HEADER RHEAD
WHERE
	RHEAD.RECIPE_TYPE = 3	AND			-- 3:Master
	RHEAD.RECIPE_STATUS IN (2,3) AND	-- 2:試作用　3:一般使用
	RHEAD.RECIPE_USE = 4 AND			-- 4:包装
	RHEAD.APPROVAL_STATUS = 3 AND		-- 3:承認済み
	RHEAD.RECIPE_CD = /*recipeCd*/ AND
	RHEAD.RECIPE_VERSION = /*recipeVersion*/
/*IF (( itemCd != null ) && ( itemCd != "" ))*/
AND	RHEAD.ITEM_CD = /*itemCd*/
/*END*/