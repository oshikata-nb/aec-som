/*
 * 下位帳合チェック用SQL
 *
 * entityName=BalanceLowerDetail
 * packageName=balancelowerdetail
 * methodName=getEntity
 *
 */

SELECT BALANCE_CD
FROM BALANCE
WHERE UPPER_BALANCE_CD = /*upperBalanceCd*/'1'


