/*
 *
 *
 * entityName=RecipeFormulaForReport
 * packageName=recipeforreport
 * methodName=getFormulaList
 *
 */
SELECT 
	RECIPE_FORMULA.RECIPE_ID
,	RECIPE_FORMULA.STEP_NO
,	RECIPE_FORMULA.LINE_NO
,	RECIPE_FORMULA.SEQ
,	RECIPE_FORMULA.LINE_TYPE
,	RECIPE_FORMULA.ITEM_CD
,	ITEM_FORMULA.ITEM_NAME
,	RECIPE_FORMULA.QTY
,	RECIPE_FORMULA.COST
,	RECIPE_FORMULA.COST_UNIT
,	RECIPE_FORMULA.REMARK
,	RECIPE_FORMULA.NOTES
,	RECIPE_FORMULA.TONYU
,	RECIPE_FORMULA.TONYUSOKUDO
,	RECIPE_FORMULA.DATAREAD
,	RECIPE_FORMULA.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	RECIPE_FORMULA.INPUT_DATE
,	RECIPE_FORMULA.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATE_NAME
,	RECIPE_FORMULA.UPDATE_DATE
 FROM 
	RECIPE_HEADER
,	RECIPE_FORMULA
,	ITEM ITEM_HEADDER
,	ITEM ITEM_FORMULA
,	LOGIN INPUTOR
,	LOGIN UPDATOR
WHERE
	RECIPE_HEADER.RECIPE_ID = RECIPE_FORMULA.RECIPE_ID
AND	RECIPE_HEADER.ITEM_CD = ITEM_HEADDER.ITEM_CD(+)
AND	RECIPE_FORMULA.ITEM_CD = ITEM_FORMULA.ITEM_CD(+)
AND	RECIPE_FORMULA.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	RECIPE_FORMULA.UPDATOR_CD = UPDATOR.TANTO_CD(+)

AND	RECIPE_HEADER.RECIPE_TYPE = /*condition.recipeType*/'3'

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
	AND	(RECIPE_HEADER.ITEM_CD LIKE /*condition.product*/'' OR ITEM_HEADDER.ITEM_NAME LIKE /*condition.product*/'')
/*END*/

/*IF (( condition.recipeName != null ) && ( condition.recipeName != "" ))*/
	AND	RECIPE_HEADER.RECIPE_NAME LIKE /*condition.recipeName*/''
/*END*/

/*IF (( condition.otherCompanyCd1 != null ) && ( condition.otherCompanyCd1 != "" ))*/
	AND	ITEM_HEADDER.OTHER_COMPANY_CD1 LIKE /*condition.otherCompanyCd1*/''
/*END*/

ORDER BY
	RECIPE_HEADER.RECIPE_CD,
	RECIPE_HEADER.RECIPE_VERSION,
	RECIPE_FORMULA.STEP_NO,
	RECIPE_FORMULA.LINE_NO






