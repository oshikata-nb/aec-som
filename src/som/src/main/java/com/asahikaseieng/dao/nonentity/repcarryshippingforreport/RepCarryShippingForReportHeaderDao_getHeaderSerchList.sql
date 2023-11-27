/**
 * �^����Еʏo�׈ꗗ�\
 *
 * @author t1344224
 *
 * entityName=RepCarryShippingForReportHeader
 * packageName=repcarryshippingforreport
 * methodName=getHeaderSerchList
 *
 */
SELECT 
	*
FROM 
	REP_SLIP_CARRY_SHIPPING_HEADER
WHERE 
	SCHEDULED_SHIPPING_DATE = /*condition.srhScheduledShippingDate*/'2013/04/11'
ORDER BY REPOTR_OUTPUT_NUM,CARRY_CD ASC


