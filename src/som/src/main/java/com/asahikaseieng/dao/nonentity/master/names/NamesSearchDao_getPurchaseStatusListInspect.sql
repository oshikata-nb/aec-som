/*
 * ステータス一覧用SQL.受入検査
 *
 * entityName=Names
 * packageName=names
 * methodName=getPurchaseStatusListInspect
 *
 */
SELECT	NAME_CD, NAME01
FROM	NAMES
WHERE	NAME_DIVISION = 'PRCH'
AND	NAMES.NAME_CD > '04'
AND	NAMES.NAME_CD < '50'
ORDER BY NAME_CD
