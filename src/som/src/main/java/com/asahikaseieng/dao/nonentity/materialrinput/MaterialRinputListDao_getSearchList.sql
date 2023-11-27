/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/

/**
 * 外注原材料投入実績一覧用SQL
 *
 * @author tosco
 *
 * entityName=MaterialRinputList
 * packageName=materialrinput
 * methodName=getSearchList
 *
 */
SELECT	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
,		PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
,		PURCHASE_SUBCONTRACT.ORDER_DATE
,		PURCHASE_SUBCONTRACT.ORDER_QUANTITY
,		PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY
,		PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE
,		PURCHASE_SUBCONTRACT.VENDER_CD
,		VENDER.VENDER_NAME1					-- 取引先名称1
,		VENDER.VENDER_SHORTED_NAME			-- 取引先略称
,		LOCATION.LOCATION_NAME				-- ロケーション名称
,		ITEM_QUEUE.ITEM_NAME				-- 品目名称
,		ITEM_QUEUE.STYLE_OF_PACKING			-- 荷姿
,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV	-- 運用管理単位(区分)
,		NAMES.NAME01 AS UNIT				-- 単位
FROM	
		(SELECT PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_SHEET_NO) AS ORDER_SHEET_NO
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_DATE) AS ORDER_DATE
		,		MIN(PURCHASE_SUBCONTRACT.VENDER_CD) AS VENDER_CD
		,		MIN(PURCHASE_SUBCONTRACT.LOCATION_CD) AS LOCATION_CD
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_QUANTITY) AS ORDER_QUANTITY
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY) AS ORDER_CONVERT_QUANTITY
		,		MIN(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE) AS SUGGESTED_DELIVERLIMIT_DATE
		,		MIN(PURCHASE_SUBCONTRACT.ITEM_CD) AS ITEM_CD
		FROM	PURCHASE_SUBCONTRACT
		,		VALID_ITEM_QUEUE ITEM_QUEUE
,				PURCHASE_MATE_INJECTION
		WHERE	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO IS NOT NULL
		/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
			AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/
		/*END*/
		
		/*IF (( condition.srhOrderDateFrom != null ) && ( condition.srhOrderDateFrom != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DATE >= /*condition.srhOrderDateFrom*/
		/*END*/
		
		/*IF (( condition.srhOrderDateTo != null ) && ( condition.srhOrderDateTo != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DATE <= /*condition.srhOrderDateTo*/
		/*END*/
		
		/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
			AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/
		/*END*/
		
		/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
			AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/
		/*END*/
		
		/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/
		/*END*/
		
		/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/
		/*END*/
		
		/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/
		/*END*/
		
		/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
			AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/
		/*END*/
		
		/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/
		/*END*/
		
		/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/
		/*END*/
		
		/*IF (( condition.srhOrderSheetNo != null ) && ( condition.srhOrderSheetNo != "" ))*/
			AND	PURCHASE_SUBCONTRACT.ORDER_SHEET_NO LIKE /*condition.srhOrderSheetNo*/
		/*END*/
		
		/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
			AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
		/*END*/
		AND	PURCHASE_SUBCONTRACT.STATUS >= 5				-- 購買ステータス>=5:入荷
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION IN (2, 3)	-- オーダー区分=2:外注製品(直送),3:外注製品(非直送)
		AND	PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+) 
		/*IF ((condition.notInputFlg )) */
			AND not exists (select RECIPE_ID from PURCHASE_MATE_INJECTION where BUY_SUBCONTRACT_ORDER_NO = PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO )
		/*END*/
		GROUP BY PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
		) PURCHASE_SUBCONTRACT
,		VALID_ITEM_QUEUE ITEM_QUEUE
,		(SELECT VENDER_CD
		 ,		VENDER_NAME1
		 ,		VENDER_SHORTED_NAME
		 FROM VENDER
		 WHERE VENDER_DIVISION = 'SI'
		) VENDER
,		LOCATION
,		(SELECT NAME_CD
		 ,		NAME01
		 FROM NAMES
		 WHERE	NAME_DIVISION = 'UNIT'
		) NAMES
WHERE	PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
AND		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
AND		PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
AND		PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
ORDER BY PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
,		 PURCHASE_SUBCONTRACT.ORDER_DATE
,		 PURCHASE_SUBCONTRACT.VENDER_CD
,		 PURCHASE_SUBCONTRACT.LOCATION_CD
,		 PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
