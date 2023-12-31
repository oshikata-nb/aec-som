/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
*/

/**
 * 設備グループ一覧用SQL
 *
 * @author tosco
 *
 * entityName=RecipeResouceGroupForComboboxes
 * packageName=mgrecipe
 * methodName=findAll
 *
 */
SELECT
	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD   AS RESOUCE_GROUP_CD
,	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_NAME AS RESOUCE_GROUP_NAME
,	RECIPE_RESOUCE_GROUP.INPUT_DATE         AS INPUT_DATE
,	RECIPE_RESOUCE_GROUP.INPUTOR_CD         AS INPUTOR_CD
,	RECIPE_RESOUCE_GROUP.UPDATE_DATE        AS UPDATE_DATE
,	RECIPE_RESOUCE_GROUP.UPDATOR_CD         AS UPDATOR_CD
FROM
	RECIPE_RESOUCE_GROUP
ORDER BY
	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD
