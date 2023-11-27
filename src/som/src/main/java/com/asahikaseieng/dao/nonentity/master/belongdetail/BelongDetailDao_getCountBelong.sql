/*
 * 所属マスタ　所属データ件数取得用SQL
 *
 * entityName=BelongDetail
 * packageName=master.belong
 * methodName=getCountBelong
 *
 */

SELECT COUNT(*)
FROM BELONG
WHERE TANTO_CD = /*tantoCd*/'%'
AND ORGANIZATION_CD = /*organizationCd*/'%'