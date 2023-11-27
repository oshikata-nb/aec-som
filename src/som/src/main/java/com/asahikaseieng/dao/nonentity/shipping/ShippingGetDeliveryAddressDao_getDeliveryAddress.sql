/**
*entityName=ShippingGetDeliveryAddress
*packageName=shipping
*methodName=getDeliveryAddress
*/
SELECT
	DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 AS DELIVERY_ADDRESS
FROM
	DELIVERY
WHERE
	DELIVERY.DELIVERY_CD = /*deliveryCd*/'06256000'

