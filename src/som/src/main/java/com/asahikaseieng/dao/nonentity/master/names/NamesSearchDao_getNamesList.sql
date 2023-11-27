/*
 * コンボ用データリスト用SQL
 *
 * entityName=Names
 * packageName=names
 * methodName=getNamesList
 *
 */
SELECT	NAME_CD, NAME01, NAME03
FROM	V_NAMES
WHERE	NAME_DIVISION = /*nameDivision*/'UNIT'
AND		LANGUAGE_ID = /*languageId*/'ja'
ORDER BY RIGHT(replicate('0',10) + NAME_CD,10) 
