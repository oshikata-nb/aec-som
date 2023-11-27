/*
 * 作業日報詳細削除用SQL
 *
 * entityName=DailyReportDetailList
 * packageName=dailyreport
 * methodName=delete
 *
 */
DELETE FROM DAILY_REPORT
WHERE TO_CHAR(PRODUCTION_DATE,'YYYY/MM/DD') = /*bean.date*/
  AND PRODUCTION_LINE = /*bean.line*/
  AND TANTO_DIVISION = /*bean.tantoDiv*/
  AND TANTO_CD = /*bean.tantoCd*/
