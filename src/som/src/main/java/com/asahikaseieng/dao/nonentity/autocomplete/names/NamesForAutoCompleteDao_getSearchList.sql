/*
 * 各種名称マスタ一覧用SQL
 *
 * entityName=NamesListForAutoComplete
 * packageName=names
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT NAME_CD, NAME01
		FROM   NAMES
		WHERE  (NAME_CD LIKE /*nameCd*/'%' OR NAME01 LIKE /*nameCd*/'%')
		AND    NAME_DIVISION = /*nameDivision*/'%'
		ORDER  BY NAME_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
