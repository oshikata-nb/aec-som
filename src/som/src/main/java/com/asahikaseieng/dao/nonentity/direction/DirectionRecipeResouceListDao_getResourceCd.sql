/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/

/**
 * 設備マスタ一覧用SQL
 *
 * @author tosco
 *
 * entityName=DirectionRecipeResouceList
 * packageName=direction
 * methodName=getResourceCd
 *
 */
SELECT
	RES.RESOUCE_CD AS         RESOUCE_CD
,	RES.RESOUCE_NAME AS       RESOUCE_NAME
,	RES.SHORT_NAME AS         SHORT_NAME
,	RES.COST_MACHINE AS       COST_MACHINE
,	RES.COST AS               COST
,	RES.RESOUCE_GROUP_CD AS   RESOUCE_GROUP_CD
,	RES.PRODUCTION_LINE AS    PRODUCTION_LINE
,	RES.INPUT_DATE AS         INPUT_DATE
,	RES.INPUTOR_CD AS         INPUTOR_CD
,	RES.UPDATE_DATE AS        UPDATE_DATE
,	RES.UPDATOR_CD AS         UPDATOR_CD
FROM
	RECIPE_RESOUCE RES
WHERE
        RES.PRODUCTION_LINE = /*productionLine*/1
	AND	RES.RESOUCE_CD = /*dissolutionTankNo*/'O'
	AND RES.RESOUCE_GROUP_CD = '2' -- 2:予備溶解タンク

ORDER BY
    RES.RESOUCE_CD