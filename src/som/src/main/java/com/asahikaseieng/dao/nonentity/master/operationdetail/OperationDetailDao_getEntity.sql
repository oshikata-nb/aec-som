/*
 * 工程詳細用SQL
 *
 * entityName=OperationDetail
 * packageName=operationdetail
 * methodName=getEntity
 *
 */

SELECT OPERATION_CD, OPERATION_NAME, RECIPE_USE, OPERATION_GROUP_CD, MEMO, UPDATE_DATE
FROM OPERATION
WHERE OPERATION_CD = /*operationCd*/'%'


