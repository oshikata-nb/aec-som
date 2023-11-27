/**
 *
 * @author t1344224
 *
 * entityName=PurchaseDeliveryListForReport
 * packageName=purchasedeliveryforreport
 * methodName=getReportList
 *
 */
SELECT	PURCHASE.ORDER_SHEET_NO
,		PURCHASE.ORDER_DATE
,		VENDER.VENDER_SHORTED_NAME AS VENDER_NAME
,		(PURCHASE.REGIST_COUNT
		 + PURCHASE.ISSUED_COUNT
		 + PURCHASE.ADJUST_COUNT
		 + PURCHASE.FIXED_COUNT
		 + PURCHASE.NOTI_COUNT
		 + PURCHASE.ARRIVED_ACCEPTED_COUNT) AS ORDER_COUNT
		 ,		PURCHASE.ISSUED_COUNT
		 ,		PURCHASE.ADJUST_COUNT
		 ,		PURCHASE.FIXED_COUNT
		 ,		PURCHASE.ARRIVED_ACCEPTED_COUNT
		 FROM
	(
		SELECT	ORDER_SHEET_NO
	,		MAX(ORDER_DATE) AS ORDER_DATE
	,		MAX(VENDER_CD) AS VENDER_CD
	,		MAX(REGIST_COUNT) AS REGIST_COUNT
	,		MAX(ISSUED_COUNT) AS ISSUED_COUNT
	,		MAX(ADJUST_COUNT) AS ADJUST_COUNT
	,		MAX(FIXED_COUNT) AS FIXED_COUNT
	,		MAX(NOTI_COUNT) AS NOTI_COUNT
	,		SUM(ARRIVED_ACCEPTED) AS ARRIVED_ACCEPTED_COUNT
	FROM
		(
				SELECT	SUM_PURCHASE.ORDER_SHEET_NO
		,		SUM_PURCHASE.ORDER_DATE
		,		SUM_PURCHASE.VENDER_CD
		,		SUM_PURCHASE.REGIST_COUNT
		,		SUM_PURCHASE.ISSUED_COUNT
		,		SUM_PURCHASE.ADJUST_COUNT
		,		SUM_PURCHASE.FIXED_COUNT
		,		SUM_PURCHASE.NOTI_COUNT
		,		DECODE(PURCHASE_SUBCONTRACT.PURCHASE_NO, NULL, 0, 1) AS ARRIVED_ACCEPTED
		FROM
			(
						SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
			,		MAX(PURCHASE_SUBCONTRACT.ORDER_DATE) AS ORDER_DATE
			,		MAX(PURCHASE_SUBCONTRACT.VENDER_CD) AS VENDER_CD
			,		SUM(PURCHASE_SUBCONTRACT.REGIST) AS REGIST_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.ISSUED) AS ISSUED_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.ADJUST) AS ADJUST_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.FIXED) AS FIXED_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.NOTI) AS NOTI_COUNT
			FROM
				(
								SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
				,		PURCHASE_SUBCONTRACT.ORDER_DATE
				,		PURCHASE_SUBCONTRACT.VENDER_CD
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 1, 1, 0) AS REGIST
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 2, 1, 0) AS ISSUED
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 3, 1, 0) AS ADJUST
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 4, 1, 0) AS FIXED
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 7, 1, 0) AS NOTI
				FROM	PURCHASE_SUBCONTRACT
				,		(
						SELECT	ORDER_SHEET_NO
						FROM	PURCHASE_SUBCONTRACT
						,		VALID_ITEM_QUEUE ITEM_QUEUE
						WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
						/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/''
						/*END*/
						
						/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ))*/
							/*IF ( condition.srhStatus == "0" )*/
								AND	PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4)
							/*END*/
							/*IF ( condition.srhStatus != "0" )*/
								AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/''
							/*END*/
						/*END*/
						
						/*IF (( condition.srhOrderDateFrom != null ) && ( condition.srhOrderDateFrom != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_DATE >= /*condition.srhOrderDateFrom*/''
						/*END*/
						
						/*IF (( condition.srhOrderDateTo != null ) && ( condition.srhOrderDateTo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_DATE <= /*condition.srhOrderDateTo*/''
						/*END*/
						
						/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
							AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/''
						/*END*/
						
						/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
							AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/''
						/*END*/
						
						/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/''
						/*END*/
						
						/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/''
						/*END*/
						
						/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/''
						/*END*/
						
						/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
							AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/''
						/*END*/
						
						/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/''
						/*END*/
						
						/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/''
						/*END*/
						
						/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ))*/
							/*IF ( condition.srhOrderDivision != "0" )*/
								AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/''
							/*END*/
						/*END*/
						
						/*IF (( condition.srhOrderSheetNo != null ) && ( condition.srhOrderSheetNo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO LIKE /*condition.srhOrderSheetNo*/''
						/*END*/
						
						/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/''
						/*END*/
		
						AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
                        GROUP BY ORDER_SHEET_NO
						) PURCHASE_SUBCONTRACT2
				WHERE	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO IS NOT NULL
				AND		((PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO IS NULL AND PURCHASE_SUBCONTRACT.ROW_NO IN (0, 1))
						 OR
					 	(PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO = '001' AND PURCHASE_SUBCONTRACT.ROW_NO IN (0, 1))
						)
						                AND   	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO = PURCHASE_SUBCONTRACT2.ORDER_SHEET_NO
				) PURCHASE_SUBCONTRACT
			WHERE ORDER_SHEET_NO IS NOT NULL
			GROUP BY ORDER_SHEET_NO
			) SUM_PURCHASE
		,	(
			SELECT	*
			FROM	PURCHASE_SUBCONTRACT
			WHERE	PURCHASE_SUBCONTRACT.STATUS NOT IN (1, 2, 3, 4, 7)
						AND		((PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO IS NULL AND PURCHASE_SUBCONTRACT.ROW_NO IN (0, 1))
				 	OR
				 	 (PURCHASE_SUBCONTRACT.ORDER_DIVIDE_NO = '001' AND PURCHASE_SUBCONTRACT.ROW_NO IN (0, 1))
					)
								) PURCHASE_SUBCONTRACT
		WHERE	SUM_PURCHASE.ORDER_SHEET_NO = PURCHASE_SUBCONTRACT.ORDER_SHEET_NO(+)
		) COUNT_PURCHASE
	GROUP BY ORDER_SHEET_NO
	) PURCHASE
,	(SELECT VENDER_CD
	 ,		VENDER_NAME1
	 ,		VENDER_SHORTED_NAME
	 FROM VENDER
	 WHERE VENDER_DIVISION = 'SI'
	) VENDER
WHERE	PURCHASE.VENDER_CD = VENDER.VENDER_CD(+) 
ORDER BY PURCHASE.ORDER_SHEET_NO


