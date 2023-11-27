/*
 * ステータス一覧用SQL.納期回答
 *
 * entityName=Names
 * packageName=names
 * methodName=getPurchaseStatusListDelivery
 *
 */
SELECT	NAME_CD, NAME01
FROM	NAMES
WHERE	NAME_DIVISION = 'PRCH'
AND	NAMES.NAME_CD > '02'
AND	NAMES.NAME_CD < '50'
ORDER BY NAME_CD
