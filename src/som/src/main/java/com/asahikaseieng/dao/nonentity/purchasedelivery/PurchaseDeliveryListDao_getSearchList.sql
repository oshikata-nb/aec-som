/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */

/**
 * 納期回答検索画面用SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDeliveryList
 * packageName=purchasedelivery
 * methodName=getSearchList
 *
 */
SELECT	PURCHASE.ORDER_SHEET_NO					-- 発注書NO
,		PURCHASE.ORDER_DATE						-- 発注日
,		VENDER.VENDER_SHORTED_NAME AS VENDER_NAME		-- 仕入先略称
,		(PURCHASE.REGIST_COUNT
		 + PURCHASE.ISSUED_COUNT
		 + PURCHASE.ADJUST_COUNT
		 + PURCHASE.FIXED_COUNT
		 + PURCHASE.NOTISSUE_COUNT
		 + PURCHASE.ARRIVED_ACCEPTED_COUNT) AS ORDER_COUNT	-- 全オーダー件数
,		PURCHASE.ISSUED_COUNT								-- 発注書発行済件数
,		PURCHASE.ADJUST_COUNT								-- 納期調整中件数
,		PURCHASE.FIXED_COUNT								-- 納期確定件数
,		PURCHASE.ARRIVED_ACCEPTED_COUNT						-- 入荷・受入済件数
FROM
	(	-- 入荷・受入済件数　取得
	SELECT	ORDER_SHEET_NO
	,		MAX(ORDER_DATE) AS ORDER_DATE
	,		MAX(VENDER_CD) AS VENDER_CD
	,		MAX(REGIST_COUNT) AS REGIST_COUNT
	,		MAX(ISSUED_COUNT) AS ISSUED_COUNT
	,		MAX(ADJUST_COUNT) AS ADJUST_COUNT
	,		MAX(FIXED_COUNT) AS FIXED_COUNT
	,		MAX(NOTISSUE_COUNT) AS NOTISSUE_COUNT
	,		SUM(ARRIVED_ACCEPTED) AS ARRIVED_ACCEPTED_COUNT
	FROM
		(	-- 入荷・受入済データとの紐付け
		SELECT	SUM_PURCHASE.ORDER_SHEET_NO
		,		SUM_PURCHASE.ORDER_DATE
		,		SUM_PURCHASE.VENDER_CD
		,		SUM_PURCHASE.REGIST_COUNT
		,		SUM_PURCHASE.ISSUED_COUNT
		,		SUM_PURCHASE.ADJUST_COUNT
		,		SUM_PURCHASE.FIXED_COUNT
		,		SUM_PURCHASE.NOTISSUE_COUNT
		,		DECODE(PURCHASE_SUBCONTRACT.PURCHASE_NO, NULL, 0, 1) AS ARRIVED_ACCEPTED
		FROM
			(	-- 登録済～納期確定件数　取得
			SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
			,		MAX(PURCHASE_SUBCONTRACT.ORDER_DATE) AS ORDER_DATE
			,		MAX(PURCHASE_SUBCONTRACT.VENDER_CD) AS VENDER_CD
			,		SUM(PURCHASE_SUBCONTRACT.REGIST) AS REGIST_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.ISSUED) AS ISSUED_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.ADJUST) AS ADJUST_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.FIXED) AS FIXED_COUNT
			,		SUM(PURCHASE_SUBCONTRACT.NOTISSUE) AS NOTISSUE_COUNT
			FROM
				(	-- 発注書NOに紐づくデータ取得
				SELECT	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
				,		PURCHASE_SUBCONTRACT.ORDER_DATE
				,		PURCHASE_SUBCONTRACT.VENDER_CD
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 1, 1, 0) AS REGIST
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 2, 1, 0) AS ISSUED
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 3, 1, 0) AS ADJUST
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 4, 1, 0) AS FIXED
				,		DECODE(PURCHASE_SUBCONTRACT.STATUS, 7, 1, 0) AS NOTISSUE
				FROM	PURCHASE_SUBCONTRACT
				,		(	-- 検索条件で発注書NOを絞込み
						SELECT	ORDER_SHEET_NO
						FROM	PURCHASE_SUBCONTRACT
						,		VALID_ITEM_QUEUE ITEM_QUEUE
						WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
						/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
						/*END*/
						
						/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ))*/
							/*IF ( condition.srhStatus == "0" )*/
								AND	PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4)
							/*END*/
							/*IF ( condition.srhStatus != "0" )*/
								AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/'2'
							/*END*/
						/*END*/
						
						/*IF (( condition.srhOrderDateFrom != null ) && ( condition.srhOrderDateFrom != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_DATE >= to_date(/*condition.srhOrderDateFrom*/'2000/01/01','yyyy/mm/dd')
						/*END*/
						
						/*IF (( condition.srhOrderDateTo != null ) && ( condition.srhOrderDateTo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_DATE <= to_date(/*condition.srhOrderDateTo*/'2100/01/01','yyyy/mm/dd')
						/*END*/
						
						/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
							AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= to_date(/*condition.srhSuggestedDeliverlimitDateFrom*/'2000/01/01','yyyy/mm/dd')
						/*END*/
						
						/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
							AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= to_date(/*condition.srhSuggestedDeliverlimitDateTo*/'2100/01/01','yyyy/mm/dd')
						/*END*/
						
						/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
						/*END*/
						
						/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
						/*END*/
						
						/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
						/*END*/
						
						/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
							AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
						/*END*/
						
						/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
						/*END*/
						
						/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
						/*END*/
						
						/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ))*/
							/*IF ( condition.srhOrderDivision != "0" )*/
								AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/'1'
							/*END*/
						/*END*/
						
						/*IF (( condition.srhOrderSheetNo != null ) && ( condition.srhOrderSheetNo != "" ))*/
							AND	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO LIKE /*condition.srhOrderSheetNo*/'%'
						/*END*/
						
						/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
							AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
						/*END*/
		
						AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
                        GROUP BY ORDER_SHEET_NO
						) PURCHASE_SUBCONTRACT2
				WHERE	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO IS NOT NULL
                AND   	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO = PURCHASE_SUBCONTRACT2.ORDER_SHEET_NO
				) PURCHASE_SUBCONTRACT
			WHERE ORDER_SHEET_NO IS NOT NULL
			GROUP BY ORDER_SHEET_NO
			) SUM_PURCHASE
		,	(
			SELECT	*
			FROM	PURCHASE_SUBCONTRACT
			WHERE	PURCHASE_SUBCONTRACT.STATUS NOT IN (1, 2, 3, 4,7)	-- 入荷済・受入済を対象
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
