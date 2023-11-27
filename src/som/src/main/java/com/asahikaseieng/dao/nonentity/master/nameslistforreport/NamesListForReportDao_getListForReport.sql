/*
 * 名称マスタ帳票用SQL
 *
 * entityName=NamesListForReport
 * packageName=nameslistforreport
 * methodName=getListForReport
 *
 */

SELECT NAMES.*
, CASE NAMES.NAME_DIVISION
      WHEN N'UNIT' THEN '単位'
      WHEN N'TANA' THEN '棚卸区分'
      WHEN N'STDV' THEN '検査項目'
      ELSE NULL
  END NAME_DIVISION_NAME
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM NAMES
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE NAMES.NAME_DIVISION = /*condition.srhNameDivision*/'%'

/*IF (condition.srhNameCd != null) && (condition.srhNameCd != "")*/
AND (NAMES.NAME_CD LIKE /*condition.srhNameCd*/'%' OR NAMES.NAME01 LIKE /*condition.srhNameCd*/'%')
/*END*/

AND NAMES.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND NAMES.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY NAMES.NAME_CD


