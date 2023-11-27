/*
 * コンボ用データリスト用SQL
 *
 * entityName=NamesSearch
 * packageName=master.names
 * methodName=getNamesListOrderbySeq
 *
 */
SELECT	*
FROM	V_NAMES
WHERE	NAME_DIVISION = /*nameDivision*/'UNIT'
AND		LANGUAGE_ID = /*languageId*/'ja'

/*IF (nameCd != null) && (nameCd != "")*/
AND NAME_CD = /*nameCd*/'1%'
/*END*/

/*IF (name01 != null) && (name01 != "")*/
AND NAME01 = /*name01*/'1%'
/*END*/

/*IF (name02 != null) && (name02 != "")*/
AND NAME02 = /*name02*/'1%'
/*END*/

/*IF (name03 != null) && (name03 != "")*/
AND NAME03 = /*name03*/'1%'
/*END*/

ORDER BY SEQ






