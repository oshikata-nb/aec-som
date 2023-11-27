/*
 * 検査一覧用SQL
 *
 * entityName=Names
 * packageName=names
 * methodName=getInspectionEntity
 *
 */

SELECT NAME_CD, NAME01
FROM NAMES
WHERE NAME_DIVISION = 'STDV'

/*IF (nameCd != null) && (nameCd != "")*/
AND NAME_CD = /*nameCd*/'%'
/*END*/

ORDER BY NAME_CD
