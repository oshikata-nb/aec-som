/**
 * ������ߓ�`�F�b�N�p
 *
 * @author t1344224
 *
 * entityName=SalesCancelCheckInvoiceUpdate
 * packageName=salescancelcheckinvoiceupdate
 * methodName=getMaxInvoiceUpdateDate
 *
 */
SELECT
	MAX(SALES.INVOICE_UPDATE_DATE) AS INVOICE_UPDATE_DATE
FROM
	SALES
WHERE
	SALES.INVOICE_CD = /*invoiceCd*/''
AND	SALES.INVOICE_UPDATE_DATE >= /*salesDate*/''


