/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-asprovaタブ検索用SQL
 *
 * @author a7710658
 *
 * entityName=RecipeAsprova
 * packageName=mgrecipe
 * methodName=getSearchList
 *
 */
SELECT 
RECIPE_ID
,RESOUCE_GROUP_CD
,OPERATION_GROUP_CD
,RESOUCE_CD
,RESOUCE_NAME
,YOUINSU
,MAEJIKAN
,ATOJIKAN
,PROCESS_WORK_TIME1
,PROCESS_WORK_TIME2
,MACHINE_WORK_TIME1
,MACHINE_WORK_TIME2
,MAN_WORK_TIME1
,MAN_WORK_TIME2
,RECIPE_PRIORITY
,INPUTOR_CD
,INPUT_DATE
,UPDATOR_CD
,UPDATE_DATE
,STR_CHECK_FLG
FROM
(
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
)
UNION
(
SELECT 
    null                         AS RECIPE_ID
,   RESOUCE_GROUP_CD                  AS RESOUCE_GROUP_CD
,   null                AS OPERATION_GROUP_CD
,   RESOUCE_CD                        AS RESOUCE_CD
,   RESOUCE_NAME                      AS RESOUCE_NAME
,   null                           AS YOUINSU
,   0                  AS MAEJIKAN
,   0                  AS ATOJIKAN
,   0        AS PROCESS_WORK_TIME1
,   0        AS PROCESS_WORK_TIME2
,   0        AS MACHINE_WORK_TIME1
,   0        AS MACHINE_WORK_TIME2
,   0            AS MAN_WORK_TIME1
,   0            AS MAN_WORK_TIME2
,   0                   AS RECIPE_PRIORITY
,   null                        AS INPUTOR_CD
,   null                        AS INPUT_DATE
,   null                        AS UPDATOR_CD
,   null                      AS UPDATE_DATE
,   'false'                    AS STR_CHECK_FLG
FROM
    RECIPE_RESOUCE RESOUCE
WHERE
    RESOUCE.RESOUCE_GROUP_CD = /*resouceGroupCd*/'1'
AND RESOUCE.PRODUCTION_LINE = /*productionLine*/'KT'
)
ORDER BY
    RESOUCE_CD
