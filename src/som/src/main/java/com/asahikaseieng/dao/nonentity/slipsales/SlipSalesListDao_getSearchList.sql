/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/

/**
 * 売上伝票出力画面用SQL
 *
 * @author tosco
 *
 * entityName=SlipSalesList
 * packageName=slipsales
 * methodName=getSearchList
 *
 */
SELECT
	SALES.SALES_NO
,	SALES.SALES_SLIP_NO
,	SALES.ORDER_NO
,	SALES.SALES_DATE
,	SALES.CATEGORY_DIVISION
,	SALES.ITEM_CD
,	SALES.VENDER_CD
,	SALES.DELIVERY_CD
,	SALES.SHIPPING_NO
,	SALES.ACCOUNT_YEARS
,	SALES.SLIP_PUBLISH_COMP
,	SALES.UPDATE_DATE

,	ITEM.STYLE_OF_PACKING
,	ITEM.OTHER_COMPANY_CD1
,	ITEM.ITEM_NAME

,	VENDER.VENDER_NAME1
,	VENDER.VENDER_NAME2
,	VENDER.VENDER_SHORTED_NAME

,	DELIVERY.DELIVERY_NAME1
,	DELIVERY.DELIVERY_NAME2
,	DELIVERY.SEARCH_KANA

,	CLASSIFICATION.CATEGORY_NAME as CATEGORY_DIVISION_NAME

,	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD as LOCATION_CD
FROM
	SALES
,	VALID_ITEM_QUEUE
,	VALID_ITEM_QUEUE ITEM
,	VENDER
,	DELIVERY
,	(SELECT
		CATEGORY_DIVISION
	,	MIN(DATA_TOTAL_DIVISION) AS DATA_TOTAL_DIVISION
	FROM	CLASSIFICATION
	WHERE	DATA_TYPE = '1'
	GROUP BY
		CATEGORY_DIVISION
	)CLASSIFICATION_MIN
,	CLASSIFICATION
,	(SELECT
		SHIPPING_DETAIL.SHIPPING_NO
	,	MAX(UPPER_LOCATION.UPPER_LOCATION_CD) as UPPER_LOCATION_CD
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
,	SHIPPING
,	ORDER_HEAD
,	SALES_EXTENDED

WHERE
	VALID_ITEM_QUEUE.ITEM_CD(+) = SALES.ITEM_CD
AND	ITEM.ITEM_CD(+) = VALID_ITEM_QUEUE.ITEM_CD
AND	ITEM.VERSION(+) = VALID_ITEM_QUEUE.VERSION

AND	VENDER.VENDER_DIVISION(+) = 'TS'
AND	VENDER.VENDER_CD(+) = SALES.VENDER_CD

AND	DELIVERY.DELIVERY_CD(+) = SALES.DELIVERY_CD

AND	CLASSIFICATION_MIN.CATEGORY_DIVISION(+) = SALES.CATEGORY_DIVISION
AND	CLASSIFICATION.DATA_TYPE(+) = '1'
AND	CLASSIFICATION.CATEGORY_DIVISION(+)   = CLASSIFICATION_MIN.CATEGORY_DIVISION
AND	CLASSIFICATION.DATA_TOTAL_DIVISION(+) = CLASSIFICATION_MIN.DATA_TOTAL_DIVISION

AND	SALES.SHIPPING_NO = SHIPPING_DETAIL_LOCATION.SHIPPING_NO(+)

AND	SALES.SHIPPING_NO = SHIPPING.SHIPPING_NO(+)

AND	SALES.ORDER_NO = ORDER_HEAD.ORDER_NO(+)

AND	SALES.SALES_NO = SALES_EXTENDED.SALES_NO(+)

/*IF (( condition.srhSalesNo != null ) && ( condition.srhSalesNo != "" ))*/
	AND	SALES.SALES_NO LIKE /*condition.srhSalesNo*/
/*END*/

/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "" ))*/
	/*IF (condition.srhCategoryDivision == "0")*/
		/*IF (condition.srhCancelFlg)*/
			AND	SALES.CATEGORY_DIVISION < 0 
		/*END*/
		/*IF (!condition.srhCancelFlg)*/
			AND	SALES.CATEGORY_DIVISION <> 0 
		/*END*/
	/*END*/
	/*IF (condition.srhCategoryDivision != "0")*/
		/*IF (condition.srhCancelFlg)*/
			/*IF (condition.srhCategoryDivision == "1")*/
				AND	(SALES.CATEGORY_DIVISION = -1 OR SALES.CATEGORY_DIVISION = -11)
			/*END*/
			/*IF (condition.srhCategoryDivision == "2")*/
				AND	(SALES.CATEGORY_DIVISION = -2 OR SALES.CATEGORY_DIVISION = -12)
			/*END*/
			/*IF (condition.srhCategoryDivision == "3")*/
				AND	(SALES.CATEGORY_DIVISION = -3 OR SALES.CATEGORY_DIVISION = -13)
			/*END*/
			/*IF (condition.srhCategoryDivision == "4")*/
				AND	(SALES.CATEGORY_DIVISION = -4 OR SALES.CATEGORY_DIVISION = -14)
			/*END*/
			/*IF (condition.srhCategoryDivision == "9")*/
				AND	(SALES.CATEGORY_DIVISION = -9 OR SALES.CATEGORY_DIVISION = -19)
			/*END*/
		/*END*/
		/*IF (!condition.srhCancelFlg)*/
			/*IF (condition.srhCategoryDivision == "1")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 1 OR ABS(SALES.CATEGORY_DIVISION) = 11)
			/*END*/
			/*IF (condition.srhCategoryDivision == "2")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 2 OR ABS(SALES.CATEGORY_DIVISION) = 12)
			/*END*/
			/*IF (condition.srhCategoryDivision == "3")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 3 OR ABS(SALES.CATEGORY_DIVISION) = 13)
			/*END*/
			/*IF (condition.srhCategoryDivision == "4")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 4 OR ABS(SALES.CATEGORY_DIVISION) = 14)
			/*END*/
			/*IF (condition.srhCategoryDivision == "9")*/
				AND	(ABS(SALES.CATEGORY_DIVISION) = 9 OR ABS(SALES.CATEGORY_DIVISION) = 19)
			/*END*/
		/*END*/
	/*END*/
/*END*/

/*IF (( condition.srhOrderNoFrom != null ) && ( condition.srhOrderNoFrom != "" ))*/
	AND	SALES.ORDER_NO >= /*condition.srhOrderNoFrom*/
/*END*/

/*IF (( condition.srhOrderNoTo != null ) && ( condition.srhOrderNoTo != "" ))*/
	AND	SALES.ORDER_NO <= /*condition.srhOrderNoTo*/
/*END*/

/*IF (( condition.srhSalesDateFrom != null ) && ( condition.srhSalesDateFrom != "" ))*/
	AND	SALES.SALES_DATE >= /*condition.srhSalesDateFrom*/
/*END*/

/*IF (( condition.srhSalesDateTo != null ) && ( condition.srhSalesDateTo != "" ))*/
	AND	SALES.SALES_DATE <= /*condition.srhSalesDateTo*/
/*END*/

/*IF (( condition.srhAccountYears != null ) && ( condition.srhAccountYears != "" ))*/
	AND	SALES.ACCOUNT_YEARS = /*condition.srhAccountYears*/
/*END*/

/*IF (condition.srhTmpUnitpriceFlg)*/
	AND	SALES.TMP_UNITPRICE_FLG = 1
/*END*/

/*IF (condition.srhKeepFlag)*/
	AND	SALES.KEEP_FLAG = 2
/*END*/

/*IF (condition.srhMonthlyUpdateFlag)*/
	AND	(SALES.DEPOSIT_UPDATE_DIVISION = 1 OR SALES.CLAIM_UPDATE_DIVISION = 1)
/*END*/

/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	SALES.VENDER_CD LIKE /*condition.srhVenderCd*/
/*END*/

/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
	AND	SALES.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	SALES.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
    AND SALES.ITEM_CD IN (SELECT ITEM_CD
                          FROM   VALID_ITEM_QUEUE
                          WHERE  OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/)
/*END*/



/*IF (( condition.srhSalesSlipNo != null ) && ( condition.srhSalesSlipNo != "" ))*/
	AND	SALES.SALES_SLIP_NO LIKE /*condition.srhSalesSlipNo*/
/*END*/

/*IF (( condition.srhScheduledShippingDate != null ) && ( condition.srhScheduledShippingDate != "" ))*/
	AND	SHIPPING.SCHEDULED_SHIPPING_DATE = /*condition.srhScheduledShippingDate*/
/*END*/

/*IF (( condition.srhDeliveryExpectedDate != null ) && ( condition.srhDeliveryExpectedDate != "" ))*/
	AND	ORDER_HEAD.DELIVERY_EXPECTED_DATE = /*condition.srhDeliveryExpectedDate*/
/*END*/

/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
	AND	SHIPPING_DETAIL_LOCATION.UPPER_LOCATION_CD = /*condition.srhLocationCd*/
/*END*/

/*IF (( condition.srhDeliveryCd != null ) && ( condition.srhDeliveryCd != "" ))*/
	AND	SALES.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/
/*END*/

/*IF (( condition.srhCarryCd != null ) && ( condition.srhCarryCd != "" ))*/
	/*IF (condition.srhCarryCd == "0")*/

	/*END*/
	/*IF (condition.srhCarryCd != "0")*/
		AND	SHIPPING.CARRY_CD = /*condition.srhCarryCd*/
	/*END*/
/*END*/

/*IF (( condition.srhCustomerOrderNo != null ) && ( condition.srhCustomerOrderNo != "" ))*/
	AND	ORDER_HEAD.CUSTOMER_ORDER_NO LIKE /*condition.srhCustomerOrderNo*/
/*END*/
/*IF (( condition.srhSlipPublishComp != null ) && ( condition.srhSlipPublishComp != "" ))*/
	/*IF (condition.srhSlipPublishComp == "1")*/
		AND	SALES.SLIP_PUBLISH_COMP = 0
	/*END*/
/*END*/

/*IF (( condition.srhSlipDateFrom != null ) && ( condition.srhSlipDateFrom != "" ))*/
	AND	TO_CHAR(SALES.SLIP_PUBLISH_DATE,'YYYY/MM/DD') >= /*condition.srhSlipDateFrom*/
/*END*/

/*IF (( condition.srhSlipDateTo != null ) && ( condition.srhSlipDateTo != "" ))*/
	AND	TO_CHAR(SALES.SLIP_PUBLISH_DATE,'YYYY/MM/DD') <= /*condition.srhSlipDateTo*/
/*END*/

/*IF (( condition.srhSlipSendComp != null ) && ( condition.srhSlipSendComp != "" ))*/
	/*IF (condition.srhSlipSendComp == "1")*/
		AND	(SALES_EXTENDED.SLIP_SEND_COMP = 0 OR SALES_EXTENDED.SLIP_SEND_COMP IS NULL)
	/*END*/
/*END*/


/*IF (condition.srhFaxOutput != 0)*/
AND 	VENDER.FAX_OUTPUT = /*condition.srhFaxOutput*/
/*END*/

/*IF (condition.srhSalesMailOutput != 0)*/
AND 	VENDER.SALES_MAIL_OUTPUT = /*condition.srhSalesMailOutput*/
/*END*/

/*IF (condition.srhSalesFaxOutput != 0)*/
AND 	VENDER.SALES_FAX_OUTPUT = /*condition.srhSalesFaxOutput*/
/*END*/
ORDER BY
	SALES.SALES_NO