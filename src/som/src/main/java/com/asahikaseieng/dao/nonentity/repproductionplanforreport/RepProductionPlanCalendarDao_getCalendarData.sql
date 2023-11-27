/*
 *
 *
 * entityName=RepProductionPlanCalendar
 * packageName=repproductionplanforreport
 * methodName=getCalendarData
 *
 */
SELECT
	CAL.CAL_DATE
,	CAL.CAL_WEEK
,	CAL.CAL_HOLIDAY
FROM
	CAL
WHERE 
	CAL.CAL_CD = '1100' -- �H��
AND	TO_CHAR(CAL.CAL_DATE,'yyyy/mm') = /*procDate*/'2009/04'
ORDER BY CAL.CAL_DATE


