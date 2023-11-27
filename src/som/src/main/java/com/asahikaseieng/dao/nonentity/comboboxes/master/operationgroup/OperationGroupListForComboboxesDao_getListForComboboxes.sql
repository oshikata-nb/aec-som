/*
 * 工程グループマスタ一覧コンボボックス用SQL
 *
 * entityName=OperationGroupListForComboboxes
 * packageName=operationgroup
 * methodName=getListForComboboxes
 *
 */

SELECT OPERATION_GROUP_CD, OPERATION_GROUP_NAME
FROM OPERATION_GROUP
ORDER BY OPERATION_GROUP_CD


