/*
 * entityName=RepOrderSlipHeaderForReport
 * packageName=orderslipforreport
 * methodName=getHeaderListForReport
 * 20191225 PG修正 バインド変数From S.Tanaka
 */
SELECT
	DISTINCT(REP_SLIP_ORDER_HEADER.ORDER_NO)
,	REP_SLIP_ORDER_HEADER.ORDER_DIVISION
,	REP_SLIP_ORDER_HEADER.DIVISION_NAME
,	REP_SLIP_ORDER_HEADER.ORDER_DATE
,	REP_SLIP_ORDER_HEADER.ORGANIZATION_CD
,	REP_SLIP_ORDER_HEADER.ORGANIZATION_NAME
,	REP_SLIP_ORDER_HEADER.INPUT_TANTO_CD
,	REP_SLIP_ORDER_HEADER.INPUT_TANTO_NAME
,	REP_SLIP_ORDER_HEADER.SALES_TANTO_CD
,	REP_SLIP_ORDER_HEADER.SALES_TANTO_NAME
,	REP_SLIP_ORDER_HEADER.VENDER_CD
,	REP_SLIP_ORDER_HEADER.VENDER_NAME
,	REP_SLIP_ORDER_HEADER.DELIVERY_CD
,	REP_SLIP_ORDER_HEADER.DELIVERY_NAME
,	REP_SLIP_ORDER_HEADER.DELIVERY_ADDRESS

,	REP_SLIP_ORDER_HEADER.DELIVERY_ADDRESS_ALL
,	REP_SLIP_ORDER_HEADER.DELIVERY_TEL_NO

,	REP_SLIP_ORDER_HEADER.BALANCE_CD
,	REP_SLIP_ORDER_HEADER.STATUS
,	REP_SLIP_ORDER_HEADER.CUSTOMER_ORDER_NO
,	REP_SLIP_ORDER_HEADER.ORDER_AMOUNT
,	REP_SLIP_ORDER_HEADER.SUGGESTED_DELIVERLIMIT
,	REP_SLIP_ORDER_HEADER.SCHEDULED_SHIPPING_DATE
,	REP_SLIP_ORDER_HEADER.LEAD_TIME
,	REP_SLIP_ORDER_HEADER.DELIVERY_EXPECTED_DATE
,	REP_SLIP_ORDER_HEADER.DELIVERY_EXPECTED_TIME
,	REP_SLIP_ORDER_HEADER.CARRY_CD
,	REP_SLIP_ORDER_HEADER.CARRY_NAME1
,	REP_SLIP_ORDER_HEADER.CARRY_FARE
,	REP_SLIP_ORDER_HEADER.CARRY_INVOICE_FLAG
,	REP_SLIP_ORDER_HEADER.CARRY_INVOICE_STRING
,	REP_SLIP_ORDER_HEADER.ORDER_PICTURE
,	REP_SLIP_ORDER_HEADER.PRINT_SUMMERY
,	REP_SLIP_ORDER_HEADER.DELIVERY_SLIP_SUMMERY
,	REP_SLIP_ORDER_HEADER.ORDER_SUMMERY
,	REP_SLIP_ORDER_HEADER.SLIP_PUBLISH_COMP
,	REP_SLIP_ORDER_HEADER.SLIP_PUBLISH_DATE
,	REP_SLIP_ORDER_HEADER.INPUT_DATE
,	REP_SLIP_ORDER_HEADER.INPUTOR_CD
,	REP_SLIP_ORDER_HEADER.INPUTOR_NAME
,	REP_SLIP_ORDER_HEADER.UPDATE_DATE
,	REP_SLIP_ORDER_HEADER.UPDATOR_CD
,	REP_SLIP_ORDER_HEADER.UPDATOR_NAME
FROM
	REP_SLIP_ORDER_HEADER
WHERE
	REP_SLIP_ORDER_HEADER.ORDER_NO IS NOT NULL
/*IF (condition.srhOrderNoFrom != null) && (condition.srhOrderNoFrom != "")*/
AND	REP_SLIP_ORDER_HEADER.ORDER_NO >= /*condition.srhOrderNoFrom*/'JU00000001' 
/*END*/

/*IF (condition.srhOrderNoTo != null) && (condition.srhOrderNoTo != "")*/
AND 	REP_SLIP_ORDER_HEADER.ORDER_NO <= /*condition.srhOrderNoTo*/'JU999999999' 
/*END*/

/*IF (condition.srhOrderDivision != 0)*/
AND 	REP_SLIP_ORDER_HEADER.ORDER_DIVISION = /*condition.srhOrderDivision*/3
/*END*/

/*IF (condition.srhOrderDivision == 0)*/
	/*IF (condition.srhStatus !=0)*/

		/*IF (condition.srhStatus == 99)*/
			AND ((REP_SLIP_ORDER_HEADER.SHIPPING_STATUS_CD = 99 AND REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 99) OR REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 7)
		/*END*/
		
		/*IF (condition.srhStatus != 99)*/
			AND 	(REP_SLIP_ORDER_HEADER.SHIPPING_STATUS_CD = /*condition.srhStatus*/3 
	
			/*IF (condition.srhStatus == 1) */
			OR 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 7
			/*END*/
		
			/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
			OR 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD < 6
			/*END*/
			
			/*IF (condition.srhStatus == 5)*/
			OR 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 6
			/*END*/
			
			)
		/*END*/
	/*END*/
	
/*END*/

/*IF (condition.srhOrderDivision != 0) && (condition.srhOrderDivision != 3)*/
	/*IF (condition.srhStatus !=0)*/
		AND 	REP_SLIP_ORDER_HEADER.SHIPPING_STATUS_CD = /*condition.srhStatus*/3
	/*END*/
/*END*/

/*IF (condition.srhOrderDivision == 3)*/
	
	/*IF (condition.srhStatus == 1) */
	AND 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 7
	/*END*/

	/*IF (condition.srhStatus > 1) && (condition.srhStatus < 5)*/
	AND 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD > 1 AND REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD < 6
	/*END*/
	
	/*IF (condition.srhStatus == 5)*/
	AND 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 6
	/*END*/
	
	/*IF (condition.srhStatus == 99)*/
	AND 	REP_SLIP_ORDER_HEADER.PURCHASE_STATUS_CD = 7
	/*END*/
	
/*END*/

/*IF(condition.srhOrderDateFrom != null) && (condition.srhOrderDateFrom != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_HEADER.ORDER_DATE, 'YYYY/MM/DD') >= /*condition.srhOrderDateFrom*/'2000/01/01'
/*END*/
/*IF(condition.srhOrderDateTo != null) && (condition.srhOrderDateTo != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_HEADER.ORDER_DATE, 'YYYY/MM/DD') <= /*condition.srhOrderDateTo*/'2010/12/31'
/*END*/

/*IF(condition.srhScheduledShippingDateFrom != null) && (condition.srhScheduledShippingDateFrom != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_HEADER.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') >= /*condition.srhScheduledShippingDateFrom*/'2000/01/01'
/*END*/

/*IF(condition.srhScheduledShippingDateTo != null) && (condition.srhScheduledShippingDateTo != "")*/
AND 	TO_CHAR(REP_SLIP_ORDER_HEADER.SCHEDULED_SHIPPING_DATE, 'YYYY/MM/DD') <= /*condition.srhScheduledShippingDateTo*/'2010/12/31'
/*END*/

/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.DELIVERY_CD LIKE /*condition.srhDeliveryCd*/'%' OR REP_SLIP_ORDER_HEADER.DELIVERY_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhDeliveryCd*/'%'))
/*END*/

/*IF (condition.srhDeliveryAddress != null) && (condition.srhDeliveryAddress != "")*/
AND	REP_SLIP_ORDER_HEADER.DELIVERY_ADDRESS_ALL LIKE /*condition.srhDeliveryAddress*/'%'
/*END*/

/*IF (condition.srhDeliveryTelNo != null) && (condition.srhDeliveryTelNo != "")*/
AND	REP_SLIP_ORDER_HEADER.DELIVERY_TEL_NO LIKE /*condition.srhDeliveryTelNo*/'%'
/*END*/

/*IF (condition.srhVenderCd != null) && (condition.srhVenderCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.VENDER_CD LIKE /*condition.srhVenderCd*/'%' OR REP_SLIP_ORDER_HEADER.VENDER_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*condition.srhVenderCd*/'%')
	OR REP_SLIP_ORDER_HEADER.VENDER_CD2 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_HEADER.VENDER_CD3 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_HEADER.VENDER_CD4 = REPLACE (/*condition.srhVenderCd*/,'%','')
	OR REP_SLIP_ORDER_HEADER.VENDER_CD5 = REPLACE (/*condition.srhVenderCd*/,'%','')
)
/*END*/


/*IF (condition.srhOrganizationCd != null) && (condition.srhOrganizationCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR REP_SLIP_ORDER_HEADER.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (condition.srhSalesTantoCd != null) && (condition.srhSalesTantoCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.SALES_TANTO_CD LIKE /*condition.srhSalesTantoCd*/'%' OR REP_SLIP_ORDER_HEADER.SALES_TANTO_NAME LIKE /*condition.srhSalesTantoCd*/'%')
/*END*/

/*IF (condition.srhInputTantoCd != null) && (condition.srhInputTantoCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.INPUT_TANTO_CD LIKE /*condition.srhInputTantoCd*/'%' OR REP_SLIP_ORDER_HEADER.INPUT_TANTO_NAME LIKE /*condition.srhInputTantoCd*/'%')
/*END*/

/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND	(REP_SLIP_ORDER_HEADER.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR REP_SLIP_ORDER_HEADER.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND	REP_SLIP_ORDER_HEADER.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (condition.srhCarryCd != null) && (condition.srhCarryCd != "")*/
	/*IF ( condition.srhCarryCd != "0" )*/
AND	REP_SLIP_ORDER_HEADER.CARRY_CD LIKE /*condition.srhCarryCd*/'%'
	/*END*/
/*END*/


ORDER BY REP_SLIP_ORDER_HEADER.ORDER_NO ASC









