/*
 * 入金トランザクション　入金番号一括削除用SQL
 *
 * entityName=Credit
 * packageName=claim.deposit
 * methodName=deleteCreditNo
 *
 */
DELETE
FROM CREDIT
WHERE CREDIT_NO = /*creditNo*/'1'
