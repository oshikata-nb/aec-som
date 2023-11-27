/*
 * 名称マスタ一覧コンボボックス用SQL
 *
 * entityName=SalestermsandestimateNamesListForComboboxesDao
 * packageName=salestermsandestimate
 * methodName=getProcessDivision
 *
 */

SELECT NAME_CD, NAME01, NMQTY01
FROM NAMES
WHERE NAME_DIVISION = /*nameDivision*/'%'
ORDER BY NMQTY01


