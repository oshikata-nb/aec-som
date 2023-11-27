/*
 * 名称マスタ一覧(受注)用SQL
 *
 * entityName=OrderNamesList
 * packageName=ordernameslist
 * methodName=getList
 *
 */

SELECT NAME_DIVISION, NAME_CD, NAME01
FROM NAMES
WHERE NAME_DIVISION = /*nameDivision*/'%'
ORDER BY NAME_CD


