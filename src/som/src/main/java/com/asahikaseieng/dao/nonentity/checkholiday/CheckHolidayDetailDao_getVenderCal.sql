/*
 * 取引先カレンダー取得用SQL
 *
 * entityName=CheckHolidayDetail
 * packageName=checkholiday
 * methodName=getVenderCal
 *
 */

SELECT CALENDAR_CD
FROM VENDER
WHERE VENDER_DIVISION = /*venderDivision*/'%'
AND VENDER_CD = /*venderCd*/'%'
