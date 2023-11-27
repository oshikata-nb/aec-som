/*
 * 所属マスタ　主所属データ件数取得用SQL
 *
 * entityName=BelongDetail
 * packageName=master.belong
 * methodName=getCountMainBelong
 *
 */

SELECT COUNT(*)
FROM BELONG
WHERE TANTO_CD = /*tantoCd*/'%'
AND (BELONG_KBN = '1' --主所属OR BELONG_KBN IS NULL) --通常ありえない
