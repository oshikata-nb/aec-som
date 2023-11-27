/*
 * ステータス一覧用SQL
 *
 * entityName=Names
 * packageName=names
 * methodName=getRecipeStatusList
 *
 */
SELECT NAME_CD, NAME01
FROM NAMES
WHERE NAME_DIVISION = 'RSTS'

/*IF (nameCd != null) && (nameCd != "")*/
AND NAME_CD LIKE /*nameCd*/'1%'
/*END*/

ORDER BY NAME_CD
