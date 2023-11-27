/*
 * 受注詳細用SQL
 *
 * entityName=OrderDetailEntity
 * packageName=orderdetailentity
 * methodName=getEntity
 *
 */
SELECT 
	ORDER_HEAD.ORDER_NO 
,	ORDER_HEAD.ORDER_DIVISION
,	ORDER_HEAD.ORDER_DATE
,	ORDER_HEAD.SALES_TANTO_CD
,	ORDER_HEAD.DELIVERY_CD
,	DELIVERY.SEARCH_KANA AS DELIVERY_NAME1
,	ORDER_HEAD.DELIVERY_ADDRESS
,	DELIVERY.ADDRESS1 || DELIVERY.ADDRESS2 || DELIVERY.ADDRESS3 AS ADDRESS
,	(CASE WHEN ORDER_HEAD.DELIVERY_TEL_NO IS NULL
		THEN 
			DELIVERY.TEL_NO
		ELSE 
			ORDER_HEAD.DELIVERY_TEL_NO
	END) AS TEL_NO
,	ORDER_HEAD.ORDER_AMOUNT
,	VENDER.ORGANIZATION_CD
,	ORGANIZATION.ORGANIZATION_NAME
,	ORDER_HEAD.BALANCE_CD
,	ORDER_HEAD.SUGGESTED_DELIVERLIMIT
,	ORDER_HEAD.SCHEDULED_SHIPPING_DATE
,	ORDER_HEAD.LEAD_TIME
,	ORDER_HEAD.CARRY_FARE
,	ORDER_HEAD.CARRY_INVOICE_FLAG
,	ORDER_HEAD.CARRY_CD
,	ORDER_HEAD.DELIVERY_EXPECTED_DATE
,	ORDER_HEAD.DELIVERY_EXPECTED_TIME
,	ORDER_HEAD.CUSTOMER_ORDER_NO
,	ORDER_HEAD.ORDER_PICTURE
,	ORDER_HEAD.DELIVERY_SLIP_SUMMERY
,	ORDER_HEAD.ORDER_SUMMERY
,	ORDER_HEAD.PRINT_SUMMERY
,	ORDER_HEAD.UPDATE_DATE

FROM
	ORDER_HEAD
,	DELIVERY
,	VENDER
,	ORGANIZATION
WHERE 
	ORDER_HEAD.ORDER_NO IS NOT NULL
AND	ORDER_HEAD.DELIVERY_CD = DELIVERY.DELIVERY_CD(+)
AND	ORDER_HEAD.VENDER_CD = VENDER.VENDER_CD(+) AND VENDER.VENDER_DIVISION(+) = 'TS'
AND	VENDER.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)

AND	ORDER_NO = /*orderNo*/'JU00000001'

