/*
 * 受払月報TEMP削除用SQL
 *
 * entityName=RepTempInoutMonthlyReport
 * packageName=inoutmonthlyreportforreport
 * methodName=delete
 *
 */
DELETE FROM TEMP_INOUT_MONTHLY_REPORT
WHERE INPUTOR_CD = /*inputorCd*/'som'