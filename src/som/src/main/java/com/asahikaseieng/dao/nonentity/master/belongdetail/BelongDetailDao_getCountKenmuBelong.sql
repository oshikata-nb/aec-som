/*
 * 所属マスタ　兼務所属データ件数取得用SQL
 *
 * entityName=BelongDetail
 * packageName=master.belong
 * methodName=getCountKenmuBelong
 *
 */

SELECT COUNT(*)
FROM BELONG
WHERE TANTO_CD = /*tantoCd*/'%'
AND BELONG_KBN = '2' --兼務所属