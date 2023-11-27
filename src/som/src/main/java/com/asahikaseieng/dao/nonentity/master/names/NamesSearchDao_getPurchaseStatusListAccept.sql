/*
 * ステータス一覧用SQL.受入
 *
 * entityName=Names
 * packageName=names
 * methodName=getPurchaseStatusListAccept
 *
 */
SELECT	NAME_CD, NAME01
FROM	NAMES
WHERE	NAME_DIVISION = 'PRCH'
AND	NAMES.NAME_CD > '03'
AND	NAMES.NAME_CD < '50'
ORDER BY NAME_CD
