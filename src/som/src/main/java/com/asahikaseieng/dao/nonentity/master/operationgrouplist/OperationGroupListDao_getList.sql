/*
 * 工程グループマスタ検索一覧用SQL
 *
 * entityName=OperationGroupList
 * packageName=operationgrouplist
 * methodName=getList
 *
 */

SELECT OPERATION_GROUP_CD, OPERATION_GROUP_NAME
FROM OPERATION_GROUP
WHERE OPERATION_GROUP_CD IS NOT NULL

/*IF (condition.operationGroupCd != null) && (condition.operationGroupCd != "")*/
AND OPERATION_GROUP_CD LIKE /*condition.operationGroupCd*/'%'
/*END*/

/*IF (condition.operationGroupName != null) && (condition.operationGroupName != "")*/
AND OPERATION_GROUP_NAME LIKE /*condition.operationGroupName*/'%'
/*END*/

ORDER BY OPERATION_GROUP_CD
