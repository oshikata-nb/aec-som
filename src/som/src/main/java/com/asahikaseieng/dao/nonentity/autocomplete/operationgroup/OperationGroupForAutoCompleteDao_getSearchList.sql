/*
 * 工程グループ一覧用SQL
 *
 * entityName=OperationGroupForAutoComplete
 * packageName=operationgroup
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT OPERATION_GROUP_CD, OPERATION_GROUP_NAME
		FROM   OPERATION_GROUP
		WHERE  NOT OPERATION_GROUP_CD IS NULL
			  
/*IF (operationGroupCd != null) && (operationGroupCd != "")*/
		AND    (OPERATION_GROUP_CD LIKE /*operationGroupCd*/'%' OR OPERATION_GROUP_NAME LIKE /*operationGroupCd*/'%')
/*END*/
		
		ORDER  BY OPERATION_GROUP_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/ '50'
/*END*/


