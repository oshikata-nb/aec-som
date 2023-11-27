/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
*/

/**
 * 基本処方検索Popup－処方ヘッダー一覧用SQL
 *
 * @author tosco
 *
 * entityName=RecipeHeaderSearchList
 * packageName=master.search.recipeheader
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
    ,ITEM.SHIPPER_DIVISION AS      SHIPPER_DIVISION
    ,ITEM.OTHER_COMPANY_CD1 AS     OTHER_COMPANY_CD1
    ,ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIVISION
    ,NAME.NAME01 AS                UNIT_NAME
    ,LINE.PRODUCTION_LINE_NAME AS  PRODUCTION_LINE_NAME
FROM
    LINE LINE,
    NAMES NAME,
    VALID_ITEM_QUEUE ITEM,
	RECIPE_HEADER RHEAD
WHERE
        RHEAD.RECIPE_TYPE = '3'	    	--基本処方
    AND RHEAD.RECIPE_USE = 3			--製造
    AND RHEAD.RECIPE_STATUS IN (2,3)	--2:試作用　3:一般使用
    AND RHEAD.APPROVAL_STATUS = 3		--3:承認済み
    AND ITEM.ITEM_CD(+) = RHEAD.ITEM_CD
    AND LINE.PRODUCTION_LINE(+) = RHEAD.PRODUCTION_LINE
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
/*IF (( condition.recipeCd != null ) && ( condition.recipeCd != "" ))*/
	AND	RHEAD.RECIPE_CD LIKE /*condition.recipeCd*/1
/*END*/

/*IF (( condition.recipeName != null ) && ( condition.recipeName != "" ))*/
	AND	RHEAD.RECIPE_NAME LIKE /*condition.recipeName*/'%'
/*END*/

/*IF (( condition.itemCd != null ) && ( condition.itemCd != "" ))*/
	AND	(RHEAD.ITEM_CD LIKE /*condition.itemCd*/'%' OR ITEM.ITEM_NAME LIKE /*condition.itemCd*/'%')
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	RHEAD.PRODUCTION_LINE = /*condition.productionLine*/'%'
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/'%'
/*END*/

/*IF (( condition.shipperDivision != null ) && ( condition.shipperDivision != "2" ))*/
	AND	ITEM.SHIPPER_DIVISION != 1
/*END*/

/*IF (( condition.shipperDivision != null ) && ( condition.shipperDivision == "2" ))*/
	AND	ITEM.SHIPPER_DIVISION = 1
/*END*/

ORDER BY
	RHEAD.RECIPE_CD,
	RHEAD.RECIPE_VERSION
