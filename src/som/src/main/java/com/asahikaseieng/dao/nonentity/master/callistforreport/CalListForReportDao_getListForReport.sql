/*
 * カレンダーマスタ帳票用SQL
 *
 * entityName=CalListForReport
 * packageName=callistforreport
 * methodName=getListForReport
 *
 */

SELECT CAL.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM CAL
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE CAL_CD IS NOT NULL

/*IF (condition.srhCalCd != null && condition.srhCalCd != "")*/
AND	(CAL_CD LIKE /*condition.srhCalCd*/'%' OR CAL_NAME LIKE /*condition.srhCalCd*/'%')
/*END*/

/*IF (condition.srhCalYear != null && condition.srhCalYear != "")*/
AND	CAL_YEAR = /*condition.srhCalYear*/2009
/*END*/

AND CAL.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND CAL.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY CAL_CD, CAL_DATE
