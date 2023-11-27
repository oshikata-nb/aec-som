/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
*/

/**
 * 原処方検索一覧用SQL
 *
 * @author tosco
 *
 * entityName=GrecipeRecipeHeaderList
 * packageName=grecipe
 * methodName=getSearchList
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
    ,ITEM.ITEM_NAME AS             ITEM_NAME
    ,NAME.NAME01 AS                STATUS_NAME
    
    ,CASE APPROVAL_STATUS
    	WHEN 1 THEN '入力中'
    	WHEN 2 THEN '承認依頼中'
    	WHEN 3 THEN '承認済み'
    	ELSE NULL
    END APPROVAL_STATUS_NAME
FROM
	VALID_ITEM_QUEUE ITEM,
    NAMES NAME,
	RECIPE_HEADER RHEAD
WHERE
        RHEAD.RECIPE_TYPE = '1'    --原処方
    AND ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND NAME.NAME_DIVISION(+) = 'RSTS'
    AND NAME.NAME_CD(+) = RHEAD.RECIPE_STATUS
/*IF (( condition.recipeCd != null ) && ( condition.recipeCd != "" ))*/
	AND	RHEAD.RECIPE_CD LIKE /*condition.recipeCd*/1
/*END*/

/*IF (( condition.recipeVersion != null ) && ( condition.recipeVersion != "" ))*/
	AND	RHEAD.RECIPE_VERSION LIKE /*condition.recipeVersion*/1
/*END*/

/*IF (( condition.recipeStatus != null ) && ( condition.recipeStatus != "" ))*/
	AND	RHEAD.RECIPE_STATUS = /*condition.recipeStatus*/1
/*END*/

/*IF (( condition.approvalStatus != null ) && ( condition.approvalStatus != "" ))*/
	AND	RHEAD.APPROVAL_STATUS = /*condition.approvalStatus*/1
/*END*/

/*IF (( condition.recipeUse != null ) && ( condition.recipeUse != "" ))*/
	AND	RHEAD.RECIPE_USE = /*condition.recipeUse*/1
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	(RHEAD.ITEM_CD LIKE /*condition.itemCd*/'%' OR ITEM.ITEM_NAME LIKE /*condition.itemCd*/'%')
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/'%'
/*END*/
ORDER BY
	RHEAD.RECIPE_CD,
	RHEAD.RECIPE_VERSION
