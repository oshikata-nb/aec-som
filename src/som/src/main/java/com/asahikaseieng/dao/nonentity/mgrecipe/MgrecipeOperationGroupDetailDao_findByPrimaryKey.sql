/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/

/**
 * 処方ASPROVA-asprova詳細検索用SQL
 *
 * @author tosco
 *
 * entityName=MgrecipeOperationGroup
 * packageName=mgrecipe
 * methodName=findByPrimaryKey
 *
 */
SELECT 
    OPERATION_GROUP_CD     AS OPERATION_GROUP_CD
,   OPERATION_GROUP_NAME   AS OPERATION_GROUP_NAME
,   MEMO                   AS MEMO
,   INPUT_DATE             AS INPUT_DATE
,   INPUTOR_CD             AS INPUTOR_CD
,   UPDATE_DATE            AS UPDATE_DATE
,   UPDATOR_CD             AS UPDATOR_CD
FROM
    OPERATION_GROUP
WHERE
    OPERATION_GROUP_CD IS NOT NULL
/*IF (operationGroupCd != null && operationGroupCd != "")*/
    AND OPERATION_GROUP_CD = /*operationGroupCd*/'30'
/*END*/
    