/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-asprovaタグ一覧検索用SQL
 *
 * @author tosco
 *
 * entityName=RecipeAsprova
 * packageName=mgrecipe
 * methodName=getSearchListByPrimaryKey
 *
 */
SELECT 
    ASPROVA.RECIPE_ID                         AS RECIPE_ID
,   ASPROVA.RESOUCE_GROUP_CD                  AS RESOUCE_GROUP_CD
,   ASPROVA.OPERATION_GROUP_CD                AS OPERATION_GROUP_CD
,   ASPROVA.RESOUCE_CD                        AS RESOUCE_CD
,   RESOUCE.RESOUCE_NAME                      AS RESOUCE_NAME
,   ASPROVA.YOUINSU                           AS YOUINSU
,   NVL(ASPROVA.MAEJIKAN, 0)                  AS MAEJIKAN
,   NVL(ASPROVA.ATOJIKAN, 0)                  AS ATOJIKAN
,   NVL(ASPROVA.PROCESS_WORK_TIME1, 0)        AS PROCESS_WORK_TIME1
,   NVL(ASPROVA.PROCESS_WORK_TIME2, 0)        AS PROCESS_WORK_TIME2
,   NVL(ASPROVA.MACHINE_WORK_TIME1, 0)        AS MACHINE_WORK_TIME1
,   NVL(ASPROVA.MACHINE_WORK_TIME2, 0)        AS MACHINE_WORK_TIME2
,   NVL(ASPROVA.MAN_WORK_TIME1, 0)            AS MAN_WORK_TIME1
,   NVL(ASPROVA.MAN_WORK_TIME2, 0)            AS MAN_WORK_TIME2
,   ASPROVA.RECIPE_PRIORITY                   AS RECIPE_PRIORITY
,   ASPROVA.INPUTOR_CD                        AS INPUTOR_CD
,   ASPROVA.INPUT_DATE                        AS INPUT_DATE
,   ASPROVA.UPDATOR_CD                        AS UPDATOR_CD
,   ASPROVA.UPDATE_DATE                       AS UPDATE_DATE
,   'true'                                    AS STR_CHECK_FLG
FROM
    RECIPE_ASPROVA ASPROVA
,   RECIPE_RESOUCE RESOUCE
WHERE
	ASPROVA.RECIPE_ID IS NOT NULL
/*IF (( recipeId != null ) && ( recipeId != "" ))*/
    AND ASPROVA.RECIPE_ID = /*recipeId*/'30809'
/*END*/
/*IF (( resouceGroupCd != null ) && ( resouceGroupCd != "" ))*/
    AND ASPROVA.RESOUCE_GROUP_CD = /*resouceGroupCd*/'1'
/*END*/
/*IF (( operationGroupCd != null ) && ( operationGroupCd != "" ))*/
    AND ASPROVA.OPERATION_GROUP_CD = /*operationGroupCd*/'30'
/*END*/
/*IF (( resourceCd != null ) && ( resourceCd != "" ))*/
    AND ASPROVA.RESOUCE_CD = /*resouceCd*/'1'
/*END*/
    AND ASPROVA.RESOUCE_CD = RESOUCE.RESOUCE_CD(+)
