/*
 * 
 *
 * entityName=RepSalesSlipListForReport
 * packageName=repsalesslipforreport
 * methodName=getSalesList
 *
 */
SELECT 
	SALES.SALES_NO
,	SALES.SALES_SLIP_NO AS SALES_SLIP_NO
,	NVL(TO_CHAR(SALES.SALES_DATE,'YYYY/MM/DD'),'null') AS SALES_DATE
,	NVL(TO_CHAR(SHIPPING.SCHEDULED_SHIPPING_DATE,'YYYY/MM/DD'),'null') AS SCHEDULED_SHIPPING_DATE
,	NVL(SALES.VENDER_CD,'null') AS VENDER_CD
,	NVL(SALES.DELIVERY_CD,'null') AS DELIVERY_CD
,	NVL(SALES.ORDER_NO,'null') AS ORDER_NO
,	NVL(SHIPPING.CARRY_CD,'null') AS CARRY_CD
,	NVL(SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD,'null') AS UPPER_LOCATION_CD
,	NVL(PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO,'null') AS BUY_SUBCONTRACT_ORDER_NO
,	NVL(TO_CHAR(SALES.INPUT_DIVISION),'null') AS INPUT_DIVISION
,	NVL(TO_CHAR(SALES.KEEP_FLAG),'null') AS KEEP_FLAG
,	NVL(SALES.CATEGORY_DIVISION,'null') AS CATEGORY_DIVISION
,	NVL(SALES.ACCOUNT_YEARS,'null') AS ACCOUNT_YEARS
,	SALES.UPDATE_DATE
FROM
	SHIPPING
,	SALES
,	CARRY
,	(SELECT BUY_SUBCONTRACT_ORDER_NO,ORDER_NO,ORDER_ROW_NO FROM PURCHASE_SUBCONTRACT WHERE ORDER_NO IS NOT NULL GROUP BY BUY_SUBCONTRACT_ORDER_NO,ORDER_NO,ORDER_ROW_NO) PURCHASE_SUBCONTRACT
,	(SELECT
		SHIPPING_DETAIL.SHIPPING_NO
	,	MAX(UPPER_LOCATION.UPPER_LOCATION_CD) AS UPPER_LOCATION_CD
	FROM
		SHIPPING_DETAIL
	,	(SELECT
			LOCA1.LOCATION_CD
		,	NVL(LOCA2.UPPER_LOCATION_CD,LOCA2.LOCATION_CD) as UPPER_LOCATION_CD
		FROM
			LOCATION LOCA1
		,	LOCATION LOCA2
		WHERE
			NVL(LOCA1.UPPER_LOCATION_CD,LOCA1.LOCATION_CD) = LOCA2.LOCATION_CD(+)
		AND	(LOCA1.LOCATION_LEVEL = '1' OR LOCA1.LOCATION_LEVEL = '4')
		AND	LOCA1.LOCATION_CD <> 'K'
		)UPPER_LOCATION
	
	WHERE
		SHIPPING_DETAIL.LOCATION_CD = UPPER_LOCATION.LOCATION_CD
	
	GROUP BY
		SHIPPING_DETAIL.SHIPPING_NO
	)SHIPPING_DETAIL_LOCATION
WHERE
	SALES.SHIPPING_NO = SHIPPING.SHIPPING_NO(+)
AND	SHIPPING.SHIPPING_NO = SHIPPING_DETAIL_LOCATION.SHIPPING_NO(+)
AND	SALES.ORDER_NO = PURCHASE_SUBCONTRACT.ORDER_NO(+)
AND	SALES.ORDER_ROW_NO = PURCHASE_SUBCONTRACT.ORDER_ROW_NO(+)
AND	SHIPPING.CARRY_CD = CARRY.CARRY_CD(+)
AND	SALES.SALES_NO IN /*salesNo*/('')
ORDER BY 
	SALES.SALES_SLIP_NO,
	CARRY.REPOTR_OUTPUT_NUM ,
	SHIPPING.CARRY_CD,
	SALES.ORDER_NO,
	SALES.VENDER_CD,
	SHIPPING.SCHEDULED_SHIPPING_DATE,
	SALES.DELIVERY_CD,
	SALES.SALES_DATE,
	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD,
	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO,
	SALES.INPUT_DIVISION,
	SALES.KEEP_FLAG,
	SALES.CATEGORY_DIVISION,
	SALES.ACCOUNT_YEARS,
	SALES.SALES_NO ASC



