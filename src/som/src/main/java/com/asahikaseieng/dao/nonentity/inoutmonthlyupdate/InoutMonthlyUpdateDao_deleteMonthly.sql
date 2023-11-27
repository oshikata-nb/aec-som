/*
 * 月次更新削除用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=deletetMonthly
 *
 */

DELETE 
FROM MONTHLY_INOUT_RECORD MR
WHERE MR.TARGET_MONTH = /*inputDate*/'200907'
