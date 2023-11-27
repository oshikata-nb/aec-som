/*
 *
 *
 * entityName=RepSalesSlipTaxRatioListForReport
 * packageName=repsalesslipforreport
 * methodName=getTaxRatioList
 *
 */
SELECT
	*
FROM
	REP_SLIP_SALES_TAX_RATIO
WHERE
	SALES_SLIP_NO IN /*slipSalesNo*/('')
ORDER BY SALES_SLIP_NO, TO_NUMBER(TAX_RATIO) ASC