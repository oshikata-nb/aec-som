/*
 * コンボ用データリスト用SQL.(絞り込み条件付き）
 *
 * entityName=Names
 * packageName=names
 * methodName=getNamesListWithCondition.sql
 *
 */
SELECT	NAME_CD, NAME01
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

ORDER BY NAME_CD
