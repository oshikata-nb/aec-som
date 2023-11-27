/*
 *
 *
 * entityName=RecipeHeaderForReport
 * packageName=recipeforreport
 * methodName=getHeadList
 *
 */
SELECT 
	RECIPE_HEADER.RECIPE_ID
,	RECIPE_HEADER.RECIPE_CD
,	RECIPE_HEADER.RECIPE_VERSION
,	RECIPE_HEADER.RECIPE_TYPE
,	RECIPE_HEADER.RECIPE_DESCRIPTION
,	RECIPE_HEADER.RECIPE_MEMO
,	RECIPE_HEADER.RECIPE_STATUS
,	RECIPE_HEADER.RECIPE_USE
,	RECIPE_HEADER.RECIPE_PRIORITY
,	RECIPE_HEADER.ORIGINAL_RECIPE_ID
,	RECIPE_HEADER.PRODUCTION_LINE
,	LINE.PRODUCTION_LINE_NAME
,	RECIPE_HEADER.ITEM_CD
,	ITEM.ITEM_NAME
,	RECIPE_HEADER.MIN_QTY
,	RECIPE_HEADER.MAX_QTY
,	RECIPE_HEADER.STD_QTY
,	RECIPE_HEADER.UNIT_QTY
,	RECIPE_HEADER.COST
,	RECIPE_HEADER.PROCESS_LOSS
,	RECIPE_HEADER.START_DATE
,	RECIPE_HEADER.END_DATE
,	RECIPE_HEADER.DELETE_FLAG
,	RECIPE_HEADER.PRINT_FLAG
,	RECIPE_HEADER.PRINT_DATE
,	RECIPE_HEADER.PRINT_TANTO_CD
,	RECIPE_HEADER.APPROVAL_STATUS
,	RECIPE_HEADER.APP_TANTO_CD
,	RECIPE_HEADER.APP_DATE
,	RECIPE_HEADER.LAST_APP_TANTO_CD
,	RECIPE_HEADER.LAST_APP_DATE
,	RECIPE_HEADER.RECIPE_NAME
,	RECIPE_HEADER.SECTION_CD
,	BUMON.SECTION_NAME
,	RECIPE_HEADER.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	RECIPE_HEADER.INPUT_DATE
,	RECIPE_HEADER.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
,	RECIPE_HEADER.UPDATE_DATE
 FROM 
	RECIPE_HEADER
,	ITEM
,	LINE
,	BUMON
,	LOGIN INPUTOR
,	LOGIN UPDATOR
WHERE
	RECIPE_HEADER.ITEM_CD = ITEM.ITEM_CD(+)
AND	RECIPE_HEADER.PRODUCTION_LINE = LINE.PRODUCTION_LINE(+)
AND	RECIPE_HEADER.SECTION_CD = BUMON.SECTION_CD(+)
AND	RECIPE_HEADER.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	RECIPE_HEADER.UPDATOR_CD = UPDATOR.TANTO_CD(+)

AND	RECIPE_HEADER.RECIPE_TYPE = /*condition.recipeType*/''

/*IF (( condition.recipeCd != null ) && ( condition.recipeCd != "" ))*/
	AND	RECIPE_HEADER.RECIPE_CD LIKE /*condition.recipeCd*/''
/*END*/

/*IF (( condition.recipeVersion != null ) && ( condition.recipeVersion != "" ))*/
	AND	RECIPE_HEADER.RECIPE_VERSION LIKE /*condition.recipeVersion*/''
/*END*/

/*IF (( condition.recipeStatus != null ) && ( condition.recipeStatus != "" ))*/
	AND	RECIPE_HEADER.RECIPE_STATUS = /*condition.recipeStatus*/''
/*END*/

/*IF (( condition.approvalStatus != null ) && ( condition.approvalStatus != "" ))*/
	AND	RECIPE_HEADER.APPROVAL_STATUS = /*condition.approvalStatus*/''
/*END*/

/*IF (( condition.recipeUse != null ) && ( condition.recipeUse != "" ))*/
	AND	RECIPE_HEADER.RECIPE_USE = /*condition.recipeUse*/''
/*END*/

/*IF (( condition.productionLine != null ) && ( condition.productionLine != "" ))*/
	AND	RECIPE_HEADER.PRODUCTION_LINE = /*condition.productionLine*/''
/*END*/

/*IF (( condition.product != null ) && ( condition.product != "" ))*/
	AND	(RECIPE_HEADER.ITEM_CD LIKE /*condition.product*/'' OR ITEM.ITEM_NAME LIKE /*condition.product*/'')
/*END*/

/*IF (( condition.recipeName != null ) && ( condition.recipeName != "" ))*/
	AND	RECIPE_HEADER.RECIPE_NAME LIKE /*condition.recipeName*/''
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/''
/*END*/

ORDER BY
	RECIPE_HEADER.RECIPE_CD,
	RECIPE_HEADER.RECIPE_VERSION


