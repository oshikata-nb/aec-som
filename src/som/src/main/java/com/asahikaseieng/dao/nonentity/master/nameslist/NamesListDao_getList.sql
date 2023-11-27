/*
 * 名称マスタ一覧用SQL
 *
 * entityName=NamesList
 * packageName=nameslist
 * methodName=getList
 *
 */

SELECT NAME_DIVISION, NAME_CD, NAME01, NAME02, NAME03
FROM NAMES
WHERE NAME_DIVISION = /*condition.srhNameDivision*/'%'

/*IF (condition.srhNameCd != null) && (condition.srhNameCd != "")*/
AND (NAME_CD LIKE /*condition.srhNameCd*/'%' OR NAME01 LIKE /*condition.srhNameCd*/'%')
/*END*/

ORDER BY NAME_CD


