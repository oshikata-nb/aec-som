/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
*/

/**
 * 設備グループコンボボックス作成用．
 * 工程グループコードを条件にした一覧取得SQL
 *
 * @author tosco
 *
 * entityName=RecipeResouceGroupForComboboxes
 * packageName=comboboxes.mgrecipe
 * methodName=findByOperationGroupCd
 *
 */
SELECT
	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD   AS RESOUCE_GROUP_CD
,	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_NAME AS RESOUCE_GROUP_NAME
,   RECIPE_RESOUCE_GROUP.OPERATION_GROUP_CD AS OPERATION_GROUP_CD
,	RECIPE_RESOUCE_GROUP.INPUT_DATE         AS INPUT_DATE
,	RECIPE_RESOUCE_GROUP.INPUTOR_CD         AS INPUTOR_CD
,	RECIPE_RESOUCE_GROUP.UPDATE_DATE        AS UPDATE_DATE
,	RECIPE_RESOUCE_GROUP.UPDATOR_CD         AS UPDATOR_CD
FROM
	RECIPE_RESOUCE_GROUP
WHERE
    RECIPE_RESOUCE_GROUP.OPERATION_GROUP_CD IS NOT NULL
/*IF (operationGroupCd != null && operationGroupCd != "")*/
	AND RECIPE_RESOUCE_GROUP.OPERATION_GROUP_CD = /*operationGroupCd*/
/*END*/
ORDER BY
	RECIPE_RESOUCE_GROUP.RESOUCE_GROUP_CD
