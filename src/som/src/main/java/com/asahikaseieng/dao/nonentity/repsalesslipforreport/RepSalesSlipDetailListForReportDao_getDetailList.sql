/*
 *
 *
 * entityName=RepSalesSlipDetailListForReport
 * packageName=repsalesslipforreport
 * methodName=getDetailList
 *
 */
SELECT 
	*
FROM 
	REP_SLIP_SALES_DETAIL
WHERE 
	SALES_SLIP_NO IN /*slipSalesNo*/('')
AND	SALES_NO IN /*salesNo*/('')
ORDER BY SALES_SLIP_NO,ORDER_NO,ORDER_ROW_NO,SHIPPING_NO,SALES_NO ASC


