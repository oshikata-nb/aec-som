/*
 * 購入品区分一覧用SQL.納期回答
 *
 * entityName=Names
 * packageName=names
 * methodName=getPurchaseMatelialDivisonListDelivery
 *
 */
SELECT	NAME_CD, NAME01
FROM	NAMES
WHERE	NAME_DIVISION = 'PRDV'
ORDER BY NAME_CD
