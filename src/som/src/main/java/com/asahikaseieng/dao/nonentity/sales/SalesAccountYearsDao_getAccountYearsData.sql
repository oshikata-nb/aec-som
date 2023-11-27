/**
 *
 *
 * entityName=SalesAccountYears
 * packageName=sales
 * methodName=getAccountYearsData
 *
 */
SELECT 
	FUN_GET_ACCOUNT_YEARS(/*orderNo*/'JT000000015',/*venderCd*/'04963000',/*salesDate*/NULL) AS ACCOUNT_YEARS
FROM DUAL

