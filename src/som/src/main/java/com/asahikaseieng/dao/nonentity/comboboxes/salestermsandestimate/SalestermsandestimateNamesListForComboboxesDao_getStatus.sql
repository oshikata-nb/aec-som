/*
 * 名称マスタ一覧コンボボックス用SQL
 *
 * entityName=SalestermsandestimateNamesListForComboboxesDao
 * packageName=salestermsandestimate
 * methodName=getStatus
 *
 */

SELECT NAME_CD, NAME01
FROM NAMES
WHERE NAME_DIVISION = /*nameDivision*/'%'
ORDER BY NAME_CD

