/*
 * カレンダーマスタ一覧用SQL
 *
 * entityName=CalList
 * packageName=callist
 * methodName=getList
 *
 */

SELECT CAL_CD, CAL_NAME, CAL_YEAR
FROM CAL
WHERE CAL_CD IS NOT NULL

/*IF (condition.srhCalCd != null && condition.srhCalCd != "")*/
AND	(CAL_CD LIKE /*condition.srhCalCd*/'%' OR CAL_NAME LIKE /*condition.srhCalCd*/'%')
/*END*/

/*IF (condition.srhCalYear != null && condition.srhCalYear != "")*/
AND	CAL_YEAR = /*condition.srhCalYear*/2009
/*END*/

GROUP BY CAL_CD, CAL_NAME, CAL_YEAR
ORDER BY CAL_CD
