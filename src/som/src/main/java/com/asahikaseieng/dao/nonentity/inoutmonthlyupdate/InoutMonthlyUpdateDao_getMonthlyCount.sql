/*
 * 月次更新情報件数取得用SQL
 *
 * entityName=InoutMonthlyUpdate
 * packageName=inoutmonthlyupdate
 * methodName=getMonthlyCount
 *
 */

SELECT COUNT(*) CNT
FROM MONTHLY_INOUT_RECORD MR
WHERE MR.TARGET_MONTH = /*inputDate*/'200907'
