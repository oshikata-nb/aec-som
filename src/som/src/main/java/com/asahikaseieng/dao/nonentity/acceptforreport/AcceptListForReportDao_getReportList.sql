/**
 * @author t1344224
 *
 * entityName=AcceptListForReport
 * packageName=acceptforreport
 * methodName=getReportList
 *
 */
SELECT	
	PSUB.PURCHASE_NO
,	PSUB.BUY_SUBCONTRACT_ORDER_NO
,	PSUB.ORDER_DIVIDE_NO
,	PSUB.ORDER_NO
,	PSUB.ORDER_ROW_NO
,	PSUB.ASP_ORDER_NO
,	PSUB.MATERIAL_DIVISION
,	PSUB.SI_ORDER_NO
,	PSUB.ORDER_DATE
,	PSUB.CHARGE_ORGANIZATION_CD
,	CHARGE_ORGANIZATION.ORGANIZATION_NAME AS CHARGE_ORGANIZATION_NAME
,	PSUB.ORGANIZATION_CD
,	ORGANIZATION.ORGANIZATION_NAME
,	PSUB.TANTO_CD
,	TANTO.TANTO_NM AS TANTO_NAME
,	PSUB.ORDER_DIVISION
,	PSUB.VENDER_CD
,	VEN.VENDER_NAME1
,	VEN.VENDER_SHORTED_NAME
,	PSUB.ITEM_CD
,	DECODE(PSUB.ORDER_DIVISION, 4, PSUB.ITEM_NAME, ITM.ITEM_NAME) AS ITEM_NAME
,	ITM.STYLE_OF_PACKING
,	ITM.OTHER_COMPANY_CD1
,	NAME.NAME01
,	PSUB.ORDER_QUANTITY
,	PSUB.ORDER_CONVERT_QUANTITY
,	PSUB.ORDER_UNITPRICE
,	PSUB.UNITPRICE_DEFINEUNIT
,	PSUB.SUPPLIER_ORD_AMOUNT
,	PSUB.SUGGESTED_DELIVERLIMIT_DATE
,	PSUB.ORDER_SHEET_REMARK
,	PSUB.REMARK
,	PSUB.NOTES
,	PSUB.LOCATION_CD
,	LOCATION.LOCATION_NAME
,	PSUB.STATUS
,	PSUB.ORDER_SHEET_NO
,	PSUB.ORDER_SHEE_FLAG
,	PSUB.ORDER_SHEE_DATE
,	PSUB.ORDER_SHEEL_TANTO_CD
,	ORDER_SHEEL_TANTO.TANTO_NM AS ORDER_SHEEL_TANTO_NAME
,	PSUB.GOOD_HOUSING_SUM
,	PSUB.REPLY_CONTENTS_DIVISION
,	CASE PSUB.REPLY_CONTENTS_DIVISION
		WHEN 0 THEN '無'
		WHEN 1 THEN '有'
	END AS REPLY_CONTENTS_DIVISION_NAME
,	PSUB.DELIVERY_COMP
,	PSUB.NEXT_DELIVERLIMIT_DATE
,	PSUB.DATA_TYPE
,	PSUB.DATA_TOTAL_DIVISION
,	PSUB.CATEGORY_DIVISION
,	PSUB.STOCKING_DATE
,	PSUB.ACCOUNT_YEARS
,	PSUB.SLIP_NO
,	PSUB.ROW_NO
,	PSUB.CANCEL_SLIP_NO
,	PSUB.CANCEL_ROW_NO
,	PSUB.SUPPLIER_LOTNO
,	PSUB.LOT_NO
,	PSUB.STOCK_LOCATION_CD
,	STOCK_LOCATION.LOCATION_NAME AS STOCK_LOCATION_NAME
,	PSUB.HOUSING_LOCATION_CD
,	HOUSING_LOCATION.LOCATION_NAME AS HOUSING_LOCATION_NAME
,	PSUB.ARRIVAL_QUANTITY
,	PSUB.STOCK_QUANTITY
,	PSUB.ACCEPT_QUANTITY
,	PSUB.ACCEPT_CONVERT_QUANTITY
,	PSUB.INCREASE_QUANTITY
,	PSUB.STOCKING_QUANTITY
,	PSUB.HOUSING_UNITPRICE
,	PSUB.FARE_AMOUNT
,	PSUB.STOCKING_AMOUNT
,	PSUB.ACCEPT_DATE
,	PSUB.ORDER_SHEET_REMARK2
,	PSUB.REMARK2
,	PSUB.NOTES2
,	PSUB.STATUS2
,	PSUB.TAX_DIVISION
,	PSUB.TAX_AMOUNT
,	PSUB.TAX_CD
,	PSUB.PAYEE_CD
,	PSUB.ACCOUNT_DEBIT_SECTION_CD
,	ACCOUNT_DEBIT_SECTION.SECTION_NAME AS ACCOUNT_DEBIT_SECTION_NAM
,	PSUB.ACCOUNT_CREDIT_SECTION_CD
,	ACCOUNT_CREDIT_SECTION.SECTION_NAME AS ACCOUNT_CREDIT_SECTION_NAME
,	PSUB.DEBIT_TITLE_CD
,	DEBIT_TITLE_CD.ACCOUNTS_NAME AS DEBIT_TITLE_NAME
,	PSUB.DEBIT_SUB_TITLE_CD
,	DEBIT_SUB_TITLE_CD.ACCOUNTS_NAME AS DEBIT_SUB_TITLE_NAME
,	PSUB.CREDIT_TITLE_CD
,	CREDIT_TITLE_CD.ACCOUNTS_NAME AS CREDIT_TITLE_NAME
,	PSUB.CREDIT_SUB_TITLE_CD
,	CREDIT_SUB_TITLE_CD.ACCOUNTS_NAME AS CREDIT_SUB_TITLE_NAME
,	PSUB.PAYABLE_TARGET_DIVISION
,	PSUB.PAYMENT_TARGET_DIVISION
,	PSUB.PAYABLE_UPDATE_DIVISION
,	PSUB.PAYABLE_NO
,	PSUB.PAYMENT_UPDATE_DIVISION
,	PSUB.PAYMENT_NO
,	PSUB.SUMMARY_CD
,	PSUB.SUMMARY
,	PSUB.PAYMENT_UPDATE_DATE
,	PSUB.PAYABLE_UPDATE_DATE
,	PSUB.TRANSMISSION_DATE
,	PSUB.LABEL_FLAG
,	PSUB.LABEL_DATE
,	PSUB.LABEL_TANTO_CD
,	LABEL_TANTO.TANTO_NM AS LABEL_TANTO_NAME
,	PSUB.TMP_UNITPRICE_FLG
,	PSUB.INSPECT_METHOD
,	PSUB.MORTGAGE_DETAIL_FLG
,	PSUB.INSPRECEIPT_WAIT_QUANTITY
,	PSUB.BAD_QUANTITY
,	PSUB.COST_ENTRY
,	PSUB.ADV_PUR_NOTICE_DECIDE_DIVISION
,	PSUB.ORDER_MORTGAGED_QUANTITY
,	PSUB.SUM_HOUSING_CONVERT_QUANT
,	PSUB.INSPECT_WAIT_CONVERT_QUANTITY
,	PSUB.EXTRACTIONS_QUANTITY
,	PSUB.DEFECTIVE_QUANTITY
,	PSUB.CHECK_TANTO_CD
,	CHECK_TANTO.TANTO_NM AS CHECK_TANTO_NAME
,	PSUB.LABORATORY_METHOD
,	PSUB.PROVISION_DIVISION
,	PSUB.CHECK_DATE
,	PSUB.TAX_RATIO
,	PSUB.CHECK_QUANTITY
,	PSUB.SLIP_ISSUE_DIVISION
,	PSUB.SLIP_ISSUE_DATE
,	PSUB.APPROVAL_STATUS
,	PSUB.APPROVEDBY
,	PSUB.APPROVALDATE
,	(CASE
		WHEN PSUB.ORDER_DIVISION IN (1, 2, 3, 6) THEN LOC.LOCATION_NAME
		WHEN PSUB.ORDER_DIVISION IN (4) THEN NVL(DEL.DELIVERY_NAME1, LOC.LOCATION_NAME)
		WHEN PSUB.ORDER_DIVISION IN (5) THEN DEL.DELIVERY_NAME1
		ELSE NULL
	END ) AS DELIVERY_NAME

,	PSUB.INPUT_DATE
,	PSUB.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME
,	PSUB.UPDATE_DATE
,	PSUB.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME
,	DECODE
	(
		ITM.ORDER_LOCATION, null, PSUB.LOCATION_CD, ITM.ORDER_LOCATION
	) LOCATION
FROM
	(
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'A' AS KBN						
	,		'1' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/
	
	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1				
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION != 1				
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	AND (PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION IS NULL
			 OR PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION = 1)	
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'B' AS KBN						
	,		'0' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1				
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION != 1				
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	AND PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION = 2				
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'C' AS KBN						
	,		'0' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1				
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION = 1				
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'D' AS KBN						
	,		'0' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 2				
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'E' AS KBN						
	,		'2' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 3					
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)					
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME				
	,		ITEM_QUEUE.STYLE_OF_PACKING					
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT				
	,		ITEM_QUEUE.OTHER_COMPANY_CD1					
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT				
	,		VENDER.VENDER_NAME1						
	,		VENDER.VENDER_SHORTED_NAME					
	,		DELIVERY.DELIVERY_NAME1 AS LOCATION_NAME			
	,		MIN_STATUS_TBL.MIN_STATUS					
	,		'F' AS KBN							
	,		'0' AS NYUKA_FLG						
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		DELIVERY
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 5				
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = DELIVERY.DELIVERY_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		PURCHASE_SUBCONTRACT.ITEM_NAME AS ITEM_QUEUE_NAME	
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		DELIVERY.DELIVERY_NAME1 AS LOCATION_NAME		
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'G' AS KBN						
	,		'0' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		DELIVERY
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 4				
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+) 
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = DELIVERY.DELIVERY_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION	
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME			
	,		ITEM_QUEUE.STYLE_OF_PACKING				
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT			
	,		ITEM_QUEUE.OTHER_COMPANY_CD1				
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT			
	,		VENDER.VENDER_NAME1					
	,		VENDER.VENDER_SHORTED_NAME					
	,		LOCATION.LOCATION_NAME					
	,		MIN_STATUS_TBL.MIN_STATUS				
	,		'H' AS KBN						
	,		'2' AS NYUKA_FLG					
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/''
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/''
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/''
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/''
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/''
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 6					
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)					
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	)PSUB
	, VALID_ITEM_QUEUE ITM
,	LOGIN INPUTOR
,	LOGIN UPDATOR
,	ORGANIZATION 
,	ORGANIZATION CHARGE_ORGANIZATION
,	LOGIN TANTO
,	LOCATION
,	LOGIN ORDER_SHEEL_TANTO
,	LOCATION STOCK_LOCATION
,	LOCATION HOUSING_LOCATION
,	BUMON ACCOUNT_DEBIT_SECTION
,	BUMON ACCOUNT_CREDIT_SECTION
,	ACCOUNTS DEBIT_TITLE_CD
,	ACCOUNTS DEBIT_SUB_TITLE_CD
,	ACCOUNTS CREDIT_TITLE_CD
,	ACCOUNTS CREDIT_SUB_TITLE_CD
,	LOGIN LABEL_TANTO
,	LOGIN CHECK_TANTO
,	NAMES NAME
,	VENDER VEN
,	DELIVERY DEL
,	LOCATION LOC
WHERE 
	VEN.VENDER_DIVISION(+) = 'SI'
AND 	VEN.VENDER_CD(+) = PSUB.VENDER_CD
AND	PSUB.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND 	PSUB.UPDATOR_CD = UPDATOR.TANTO_CD(+)
AND	PSUB.CHARGE_ORGANIZATION_CD = CHARGE_ORGANIZATION.ORGANIZATION_CD(+)
AND	PSUB.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
AND	PSUB.TANTO_CD = TANTO.TANTO_CD(+)
AND	PSUB.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND 	PSUB.ORDER_SHEEL_TANTO_CD = ORDER_SHEEL_TANTO.TANTO_CD(+)
AND	PSUB.STOCK_LOCATION_CD = STOCK_LOCATION.LOCATION_CD(+)
AND	PSUB.HOUSING_LOCATION_CD = HOUSING_LOCATION.LOCATION_CD(+)
AND 	PSUB.ACCOUNT_DEBIT_SECTION_CD = ACCOUNT_DEBIT_SECTION.SECTION_CD(+)
AND 	PSUB.ACCOUNT_CREDIT_SECTION_CD = ACCOUNT_CREDIT_SECTION.SECTION_CD(+)
AND 	PSUB.DEBIT_TITLE_CD = DEBIT_TITLE_CD.ACCOUNTS_CD(+)
AND 	PSUB.DEBIT_SUB_TITLE_CD = DEBIT_SUB_TITLE_CD.ACCOUNTS_CD(+)
AND 	PSUB.CREDIT_TITLE_CD = CREDIT_TITLE_CD.ACCOUNTS_CD(+)
AND 	PSUB.CREDIT_SUB_TITLE_CD = CREDIT_SUB_TITLE_CD.ACCOUNTS_CD(+)
AND	PSUB.LABEL_TANTO_CD = LABEL_TANTO.TANTO_CD(+)
AND	PSUB.CHECK_TANTO_CD = CHECK_TANTO.TANTO_CD(+)
AND 	NAME.NAME_DIVISION(+) = 'UNIT'
AND 	NAME.NAME_CD(+) = ITM.UNIT_OF_OPERATION_MANAGEMENT
AND 	ITM.ITEM_CD(+) = PSUB.ITEM_CD
AND 	DEL.DELIVERY_CD(+) = PSUB.LOCATION_CD
AND 	LOC.LOCATION_CD(+) = PSUB.LOCATION_CD

ORDER BY TO_CHAR(PSUB.SUGGESTED_DELIVERLIMIT_DATE,'YYYYMMDD')
,		 PSUB.ORDER_SHEET_NO
,		 PSUB.VENDER_CD
,		 PSUB.BUY_SUBCONTRACT_ORDER_NO
,		 PSUB.ORDER_DIVIDE_NO
,		 PSUB.SLIP_NO
,		 PSUB.ROW_NO
