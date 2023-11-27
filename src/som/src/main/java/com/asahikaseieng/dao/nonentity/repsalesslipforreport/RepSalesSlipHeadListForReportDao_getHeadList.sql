/*
 *
 *
 * entityName=RepSalesSlipHeadListForReport
 * packageName=repsalesslipforreport
 * methodName=getHeadList
 *
 */
SELECT 
	* 
FROM 
	REP_SLIP_SALES_HEADER 
WHERE 
	SALES_SLIP_NO IN /*salesOrderNo*/('1')
ORDER BY SALES_SLIP_NO ASC


