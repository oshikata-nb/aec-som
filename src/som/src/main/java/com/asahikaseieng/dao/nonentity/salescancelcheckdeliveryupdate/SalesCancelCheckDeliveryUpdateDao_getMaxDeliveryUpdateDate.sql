/**
 * ���|��ߓ�`�F�b�N�p
 *
 * @author t1344224
 *
 * entityName=SalesCancelCheckDeliveryUpdate
 * packageName=salescancelcheckdeliveryupdate
 * methodName=getMaxDeliveryUpdateDate
 *
 */
SELECT
	MAX(SALES.DELIVERY_UPDATE_DATE) AS DELIVERY_UPDATE_DATE
FROM
	SALES
WHERE
	SALES.INVOICE_CD = /*invoiceCd*/''
AND	SALES.DELIVERY_UPDATE_DATE >= /*salesDate*/''


