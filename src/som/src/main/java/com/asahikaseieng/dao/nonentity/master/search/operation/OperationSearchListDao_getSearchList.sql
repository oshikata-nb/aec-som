/*
 * 工程マスタ検索(ポップアップ)一覧用SQL
 *
 * entityName=OperationSearch
 * packageName=operation
 * methodName=getSearchList
 *
 */

SELECT	OPERATION_CD		-- 工程コード
,		OPERATION_NAME		-- 工程名称
FROM	OPERATION
WHERE	OPERATION_CD IS NOT NULL

/*IF (condition.operationCd != null) && (condition.operationCd != "")*/
AND OPERATION_CD LIKE /*condition.operationCd*/'%'
/*END*/

/*IF (condition.operationName != null) && (condition.operationName != "")*/
AND OPERATION_NAME LIKE /*condition.operationName*/'%'
/*END*/

/*IF (condition.recipeUse != null) && (condition.recipeUse != "")*/
AND RECIPE_USE = /*condition.recipeUse*/'3'
/*END*/

ORDER BY OPERATION_CD


