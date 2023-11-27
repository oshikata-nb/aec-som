/*
 *
 *
 * entityName=RecipeAsprovaForReport
 * packageName=recipeforreport
 * methodName=getAsprovaList
 *
 */
SELECT 
	RECIPE_ASPROVA.RECIPE_ID
,	RECIPE_ASPROVA.RESOUCE_GROUP_CD
,	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_NAME
,	RECIPE_ASPROVA.OPERATION_GROUP_CD
,	OPERATION_GROUP.OPERATION_GROUP_NAME
,	RECIPE_ASPROVA.RESOUCE_CD
,	RECIPE_RESOUCE.RESOUCE_NAME
,	RECIPE_ASPROVA.YOUINSU
,	RECIPE_ASPROVA.MAEJIKAN
,	RECIPE_ASPROVA.ATOJIKAN
,	RECIPE_ASPROVA.PROCESS_WORK_TIME1
,	RECIPE_ASPROVA.PROCESS_WORK_TIME2
,	RECIPE_ASPROVA.MACHINE_WORK_TIME1
,	RECIPE_ASPROVA.MACHINE_WORK_TIME2
,	RECIPE_ASPROVA.MAN_WORK_TIME1
,	RECIPE_ASPROVA.MAN_WORK_TIME2
,	RECIPE_ASPROVA.RECIPE_PRIORITY
,	RECIPE_ASPROVA.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	RECIPE_ASPROVA.INPUT_DATE
,	RECIPE_ASPROVA.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATE_NAME
,	RECIPE_ASPROVA.UPDATE_DATE

FROM 
	RECIPE_HEADER
,	RECIPE_ASPROVA
,	ITEM ITEM_HEADDER
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	RECIPE_RESOUCE_GROUP
,	OPERATION_GROUP
,	RECIPE_RESOUCE

WHERE
	RECIPE_HEADER.RECIPE_ID = RECIPE_ASPROVA.RECIPE_ID
AND	RECIPE_HEADER.ITEM_CD = ITEM_HEADDER.ITEM_CD(+)
AND	RECIPE_ASPROVA.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND	RECIPE_ASPROVA.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	RECIPE_ASPROVA.RESOUCE_GROUP_CD = RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD(+)
AND	RECIPE_ASPROVA.OPERATION_GROUP_CD = OPERATION_GROUP.OPERATION_GROUP_CD(+)
AND	RECIPE_ASPROVA.RESOUCE_CD = RECIPE_RESOUCE.RESOUCE_CD(+)


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
	RECIPE_HEADER.RECIPE_VERSION



