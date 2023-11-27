/*
 * 地区帳票用SQL
 *
 * entityName=AreaListForReport
 * packageName=arealistforreport
 * methodName=getListForReport
 *
 */

SELECT AREA.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM AREA
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE AREA.AREA_CD IS NOT NULL

/*IF (condition.srhAreaCd != null) && (condition.srhAreaCd != "")*/
AND (AREA.AREA_CD LIKE /*condition.srhAreaCd*/'%' OR AREA.AREA_NAME LIKE /*condition.srhAreaCd*/'%')
/*END*/

AND AREA.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND AREA.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY AREA.AREA_CD


