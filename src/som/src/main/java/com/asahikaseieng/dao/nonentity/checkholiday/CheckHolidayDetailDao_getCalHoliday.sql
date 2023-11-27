/*
 * カレンダー休日判定用SQL
 *
 * entityName=CheckHolidayDetail
 * packageName=checkholiday
 * methodName=getCalHoliday
 *
 */

SELECT CAL_HOLIDAY
FROM CAL
WHERE CAL_CD = /*calCd*/'%'
AND CAL_DATE = /*calDate*/'2009/06/01'
