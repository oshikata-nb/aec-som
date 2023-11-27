/*
 * 
 *
 * entityName=RepMothlyLotInventory
 * packageName=inoutmonthlyreportforreport
 * methodName=getTargetMonth
 *
 */
SELECT DISTINCT
	TARGET_MONTH
FROM
	MONTHLY_INOUT_RECORD
WHERE
	TARGET_MONTH = TO_CHAR(TO_DATE(/*dateTo*/'2009/12','YYYY/MM'),'YYYYMM')