/*
 * COMPONENT SQL
 *
 * entityName=ComponentListForReport
 * packageName=componentlistforreport
 * methodName=getListForReport
 */

SELECT COMPONENT.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM COMPONENT
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE COMPONENT.COMPONENT_CD IS NOT NULL

/*IF (condition.srhComponentCd != null) && (condition.srhComponentCd != "")*/
AND (COMPONENT.COMPONENT_CD LIKE /*condition.srhComponentCd*/'%' OR COMPONENT.COMPONENT_NAME LIKE /*condition.srhComponentCd*/'%')
/*END*/

AND COMPONENT.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND COMPONENT.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY COMPONENT.COMPONENT_CD


