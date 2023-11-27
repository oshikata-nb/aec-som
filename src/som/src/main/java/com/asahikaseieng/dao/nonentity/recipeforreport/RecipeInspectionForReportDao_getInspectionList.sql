/*
 *
 *
 * entityName=RecipeInspectionForReport
 * packageName=recipeforreport
 * methodName=getInspectionList
 *
 */
SELECT 
	RECIPE_INSPECTION.RECIPE_ID
,	RECIPE_INSPECTION.STEP_NO
,	RECIPE_INSPECTION.LINE_NO
,	RECIPE_INSPECTION.SEQ
,	RECIPE_INSPECTION.INSPECTION_CD
,	NAMES.NAME01 AS INSPECTION_NAME
,	RECIPE_INSPECTION.DIVISION
,	RECIPE_INSPECTION.VALUE_TYPE
,	RECIPE_INSPECTION.VALUE1
,	RECIPE_INSPECTION.VALUE2
,	RECIPE_INSPECTION.CONDITION
,	RECIPE_INSPECTION.REMARK
,	RECIPE_INSPECTION.NOTES
,	RECIPE_INSPECTION.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	RECIPE_INSPECTION.INPUT_DATE
,	RECIPE_INSPECTION.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATE_NAME
,	RECIPE_INSPECTION.UPDATE_DATE
FROM 
	RECIPE_HEADER
,	RECIPE_INSPECTION
,	ITEM ITEM_HEADDER
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	NAMES
WHERE
	RECIPE_HEADER.RECIPE_ID = RECIPE_INSPECTION.RECIPE_ID
AND	RECIPE_HEADER.ITEM_CD = ITEM_HEADDER.ITEM_CD(+)
AND	RECIPE_INSPECTION.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	RECIPE_INSPECTION.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	RECIPE_INSPECTION.INSPECTION_CD = NAMES.NAME_CD(+)
AND	NAMES.NAME_DIVISION = 'STDV'

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
	RECIPE_INSPECTION.STEP_NO,
	RECIPE_INSPECTION.LINE_NO


