/*
 *
 *
 * entityName=RecipeDetailForReport
 * packageName=recipeforreport
 * methodName=getDetailList
 *
 */
SELECT 
	RECIPE_HEADER.RECIPE_ID
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.ORIGINAL_RECIPE_ID AND LABEL.COMMON_CD = 'GRECIPE_DETAIL1') AS GRECIPE_DETAIL1
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.ORIGINAL_RECIPE_ID AND LABEL.COMMON_CD = 'GRECIPE_DETAIL2') AS GRECIPE_DETAIL2
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.ORIGINAL_RECIPE_ID AND LABEL.COMMON_CD = 'GRECIPE_DETAIL3') AS GRECIPE_DETAIL3
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.ORIGINAL_RECIPE_ID AND LABEL.COMMON_CD = 'GRECIPE_DETAIL4') AS GRECIPE_DETAIL4
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.RECIPE_ID AND LABEL.COMMON_CD = 'MRECIPE_DETAIL1') AS MRECIPE_DETAIL1
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.RECIPE_ID AND LABEL.COMMON_CD = 'MRECIPE_DETAIL2') AS MRECIPE_DETAIL2
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.RECIPE_ID AND LABEL.COMMON_CD = 'MRECIPE_DETAIL3') AS MRECIPE_DETAIL3
,	(SELECT LABEL_PATH FROM LABEL WHERE LABEL.LABEL_CD = RECIPE_HEADER.RECIPE_ID AND LABEL.COMMON_CD = 'MRECIPE_DETAIL4') AS MRECIPE_DETAIL4
	
FROM 
	RECIPE_HEADER
,	ITEM ITEM_HEADDER

WHERE
	RECIPE_HEADER.ITEM_CD = ITEM_HEADDER.ITEM_CD(+)

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


