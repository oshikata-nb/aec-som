/*
 * 理由マスタ帳票用SQL
 *
 * entityName=ReasonListForReport
 * packageName=reasonlistforreport
 * methodName=getListForReport
 *
 */

SELECT REASON.*
, CASE DEFAULT_FLG
	WHEN 1 THEN '○'
	WHEN 0 THEN '×'
	ELSE NULL
END DEFAULT_FLG_NAME

, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM REASON
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE REASON.RY_CD IS NOT NULL

/*IF (condition.srhRyCd != null) && (condition.srhRyCd != "")*/
AND (REASON.RY_CD LIKE /*condition.srhRyCd*/'%' OR REASON.RY_DESCRIPTION LIKE /*condition.srhRyCd*/'%')
/*END*/

AND REASON.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND REASON.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY REASON.RY_CD


