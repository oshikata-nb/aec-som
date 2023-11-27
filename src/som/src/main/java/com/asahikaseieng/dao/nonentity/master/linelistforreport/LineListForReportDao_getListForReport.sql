/*
 * 生産ライン帳票用SQL
 *
 * entityName=LineListForReport
 * packageName=linelistforreport
 * methodName=getListForReport
 *
 */

SELECT LINE.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM LINE
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE LINE.PRODUCTION_LINE IS NOT NULL

/*IF (condition.srhProductionLine != null) && (condition.srhProductionLine != "")*/
AND (LINE.PRODUCTION_LINE LIKE /*condition.srhProductionLine*/'%' OR LINE.PRODUCTION_LINE_NAME LIKE /*condition.srhProductionLine*/'%')
/*END*/

AND LINE.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND LINE.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY LINE.PRODUCTION_LINE


