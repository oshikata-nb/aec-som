/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
*/

/**
 * 処方プロシージャ-レシピインデックスで検索用SQL
 *
 * @author tosco
 *
 * entityName=AspImportRecipeProcedureList
 * packageName=aspimport
 * methodName=getRecipeId
 *
 */
SELECT
	RECIPE_PROCEDURE.RECIPE_ID
,	RECIPE_PROCEDURE.STEP_NO
,	RECIPE_PROCEDURE.SEQ
,	RECIPE_PROCEDURE.OPERATION_CD
,	RECIPE_PROCEDURE.CONDITION
,	RECIPE_PROCEDURE.REMARK
,	RECIPE_PROCEDURE.NOTES
,	RECIPE_PROCEDURE.MACHINE_TIME
,	RECIPE_PROCEDURE.MAN_TIME
,	RECIPE_PROCEDURE.WORK_TIME
,	RECIPE_PROCEDURE.CONDITION_TEMP
,	RECIPE_PROCEDURE.CONDITION_TIME
,	RECIPE_PROCEDURE.STIR_SPEED1
,	RECIPE_PROCEDURE.STIR_SPEED2
,	RECIPE_PROCEDURE.WATER_WEIGHT
,	RECIPE_PROCEDURE.MAIN_STREAM
,	RECIPE_PROCEDURE.NET
,	RECIPE_PROCEDURE.WEIGHT_MIN
,	RECIPE_PROCEDURE.WEIGHT_MAX
,	RECIPE_PROCEDURE.FILTER
,	RECIPE_PROCEDURE.MESH
,	RECIPE_PROCEDURE.AUTO_CHECKER_MIN
,	RECIPE_PROCEDURE.AUTO_CHECKER_MAX
,	RECIPE_PROCEDURE.GROSS_CHECKER_MIN
,	RECIPE_PROCEDURE.GROSS_CHECKER_MAX
,	RECIPE_PROCEDURE.AUTO_CHECKER_AVE
,	RECIPE_PROCEDURE.GROSS_CHECKER_AVE
,	RECIPE_PROCEDURE.OPENING_TORQUE_MIN
,	RECIPE_PROCEDURE.OPENING_TORQUE_MAX
,	RECIPE_PROCEDURE.CLOSING_TORQUE_MIN
,	RECIPE_PROCEDURE.CLOSING_TORQUE_MAX
,	RECIPE_PROCEDURE.TORQUE_PRESSURE
,	RECIPE_PROCEDURE.AIR_PRESSURE
,	RECIPE_PROCEDURE.VCLOSE_TIME
,	RECIPE_PROCEDURE.HOT_AIR_PRESET_TEMP
,	RECIPE_PROCEDURE.HOT_AIR_PRESSURE
,	RECIPE_PROCEDURE.FIRST_HEAT_SEAL
,	RECIPE_PROCEDURE.SECOND_HEAT_SEAL
,	RECIPE_PROCEDURE.INPUTOR_CD
,	RECIPE_PROCEDURE.INPUT_DATE
,	RECIPE_PROCEDURE.UPDATOR_CD
,	RECIPE_PROCEDURE.UPDATE_DATE
FROM
	RECIPE_PROCEDURE

WHERE
	RECIPE_PROCEDURE.RECIPE_ID = /*recipeId*/
ORDER BY
 	 RECIPE_PROCEDURE.RECIPE_ID
 	,RECIPE_PROCEDURE.STEP_NO
