/*
 * 部門マスタ帳票用SQL
 *
 * entityName=BumonListForReport
 * packageName=bumonlistforreport
 * methodName=getListForReport
 *
 */
SELECT BUMON.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM BUMON
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE SECTION_CD IS NOT NULL

/*IF (condition.srhSectionCd != null) && (condition.srhSectionCd != "")*/
AND (SECTION_CD LIKE /*condition.srhSectionCd*/'%' OR SECTION_NAME LIKE /*condition.srhSectionCd*/'%')
/*END*/

AND BUMON.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND BUMON.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY SECTION_CD


