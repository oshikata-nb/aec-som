/*
 * 工程マスタ一覧用SQL
 *
 * entityName=OperationList
 * packageName=operationlist
 * methodName=getList
 *
 */

SELECT OPERATION_CD, OPERATION_NAME, SUBSTRB(OPERATION_NAME, 0, 60) SHORT_OPERATION_NAME,

CASE
	WHEN OPERATION.RECIPE_USE = 3 THEN '製造'
	WHEN OPERATION.RECIPE_USE = 4 THEN '包装'
	ELSE NULL
END RECIPE_USE_NAME

FROM OPERATION
WHERE OPERATION_CD IS NOT NULL

/*IF (condition.srhOperationCd != null) && (condition.srhOperationCd != "")*/
AND (OPERATION_CD LIKE /*condition.srhOperationCd*/'%' OR OPERATION_NAME LIKE /*condition.srhOperationCd*/'%')
/*END*/

ORDER BY RECIPE_USE, OPERATION_CD
