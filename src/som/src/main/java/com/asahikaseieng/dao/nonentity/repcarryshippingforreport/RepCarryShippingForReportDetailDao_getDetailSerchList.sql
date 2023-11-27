/**
 * @author t1344224
 *
 * entityName=RepCarryShippingForReportDetail
 * packageName=repcarryshippingforreport
 * methodName=getDetailSerchList
 *
 */
SELECT 
	*
FROM
	REP_SLIP_CARRY_SHIPPING_DETAIL
WHERE
	SCHEDULED_SHIPPING_DATE = /*condition.srhScheduledShippingDate*/''
ORDER BY REPOTR_OUTPUT_NUM,CARRY_CD,ORDER_NO,DELIVERY_CD ASC

