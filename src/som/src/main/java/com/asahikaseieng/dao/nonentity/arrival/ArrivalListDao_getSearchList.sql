/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/

/**
 * 入荷一覧用SQL
 *
 * @author tosco
 *
 * entityName=ArrivalList
 * packageName=arrival
 * methodName=getSearchList
 *
 */
SELECT	KBN
,		PURCHASE_NO
,		BUY_SUBCONTRACT_ORDER_NO
,		ORDER_DIVIDE_NO
,		ORDER_NO
,		ORDER_ROW_NO
,		ASP_ORDER_NO
,		MATERIAL_DIVISION
,		SI_ORDER_NO
,		ORDER_DATE
,		CHARGE_ORGANIZATION_CD
,		ORGANIZATION_CD
,		TANTO_CD
,		ORDER_DIVISION
,		VENDER_CD
,		ITEM_CD
,		ITEM_NAME
,		ORDER_QUANTITY
,		ORDER_CONVERT_QUANTITY
,		ORDER_UNITPRICE
,		UNITPRICE_DEFINEUNIT
,		SUPPLIER_ORD_AMOUNT
,		SUGGESTED_DELIVERLIMIT_DATE
,		ORDER_SHEET_REMARK
,		REMARK
,		NOTES
,		LOCATION_CD
,		STATUS
,		ORDER_SHEET_NO
,		ORDER_SHEE_FLAG
,		ORDER_SHEE_DATE
,		ORDER_SHEEL_TANTO_CD
,		GOOD_HOUSING_SUM
,		REPLY_CONTENTS_DIVISION
,		DELIVERY_COMP
,		NEXT_DELIVERLIMIT_DATE
,		DATA_TYPE
,		DATA_TOTAL_DIVISION
,		CATEGORY_DIVISION
,		STOCKING_DATE
,		ACCOUNT_YEARS
,		SLIP_NO
,		ROW_NO
,		CANCEL_SLIP_NO
,		CANCEL_ROW_NO
,		SUPPLIER_LOTNO
,		LOT_NO
,		STOCK_LOCATION_CD
,		HOUSING_LOCATION_CD
,		ARRIVAL_QUANTITY
,		STOCK_QUANTITY
,		ACCEPT_QUANTITY
,		ACCEPT_CONVERT_QUANTITY
,		INCREASE_QUANTITY
,		STOCKING_QUANTITY
,		HOUSING_UNITPRICE
,		FARE_AMOUNT
,		STOCKING_AMOUNT
,		ACCEPT_DATE
,		ORDER_SHEET_REMARK2
,		REMARK2
,		NOTES2
,		STATUS2
,		TAX_DIVISION
,		TAX_AMOUNT
,		PAYEE_CD
,		ACCOUNT_DEBIT_SECTION_CD
,		ACCOUNT_CREDIT_SECTION_CD
,		DEBIT_TITLE_CD
,		DEBIT_SUB_TITLE_CD
,		CREDIT_TITLE_CD
,		CREDIT_SUB_TITLE_CD
,		PAYABLE_TARGET_DIVISION
,		PAYMENT_TARGET_DIVISION
,		PAYABLE_UPDATE_DIVISION
,		PAYABLE_NO
,		PAYMENT_UPDATE_DIVISION
,		PAYMENT_NO
,		SUMMARY_CD
,		SUMMARY
,		PAYMENT_UPDATE_DATE
,		PAYABLE_UPDATE_DATE
,		TRANSMISSION_DATE
,		LABEL_FLAG
,		LABEL_DATE
,		LABEL_TANTO_CD
,		TMP_UNITPRICE_FLG
,		INSPECT_METHOD
,		MORTGAGE_DETAIL_FLG
,		INSPRECEIPT_WAIT_QUANTITY
,		BAD_QUANTITY
,		COST_ENTRY
,		ADV_PUR_NOTICE_DECIDE_DIVISION
,		ORDER_MORTGAGED_QUANTITY
,		SUM_HOUSING_CONVERT_QUANT
,		INSPECT_WAIT_CONVERT_QUANTITY
,		EXTRACTIONS_QUANTITY
,		DEFECTIVE_QUANTITY
,		CHECK_TANTO_CD
,		LABORATORY_METHOD
,		PROVISION_DIVISION
,		CHECK_DATE
,		TAX_RATIO
,		CHECK_QUANTITY
,		SLIP_ISSUE_DIVISION
,		SLIP_ISSUE_DATE
,		APPROVAL_STATUS
,		APPROVEDBY
,		APPROVALDATE
,		INPUT_DATE
,		INPUTOR_CD
,		UPDATE_DATE
,		UPDATOR_CD
,		TYPE_DIVISION				-- 種別
,		ITEM_QUEUE_NAME				-- 品目名称
,		STYLE_OF_PACKING			-- 荷姿
,		KG_OF_FRACTION_MANAGEMENT	-- Kg換算係数(在庫)
,		OTHER_COMPANY_CD1			-- 他社コード1
,		SHIPPER_DIVISION			-- 荷主
,		PALETTE_PRODUCTS			-- パレット上製品数
,		VENDER_NAME1				-- 取引先名称1
,		VENDER_SHORTED_NAME			-- 取引先略称
,		LOCATION_NAME				-- ロケーション名称
,		SUM_ARRIVAL_QUANTITY		-- 入荷予定数量合計値
,		UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV	-- 運用管理単位(区分)
FROM
	(
	-- ★原材料(自社)ローリー以外
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM.STYLE_OF_PACKING								-- 荷姿
	,		ITEM.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM.SHIPPER_DIVISION								-- 荷主
	,		ARTICLE_ATTRIBUTE_QUEUE.PALETTE_PRODUCTS				-- パレット上製品数
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		SUM_PURCHASE_SUBCONTRACT.SUM_ARRIVAL_QUANTITY			-- 入荷予定数量合計値
	,		ITEM.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		'1' AS KBN												-- 区分(原材料(自社使用))
	FROM	PURCHASE_SUBCONTRACT
	,		(SELECT BUY_SUBCONTRACT_ORDER_NO
			 ,		SUM(NVL(ARRIVAL_QUANTITY, 0)) SUM_ARRIVAL_QUANTITY
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY BUY_SUBCONTRACT_ORDER_NO
			) SUM_PURCHASE_SUBCONTRACT
	,		(SELECT ITEM.*
			 FROM ITEM
			 WHERE	ITEM.TYPE_DIVISION IN (1, 2)		-- 原材料
			) ITEM
	,		ARTICLE_ATTRIBUTE_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
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
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ))*/
		/*IF ( condition.srhStatus == "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS IN ('2', '3', '4', '5')
		/*END*/
		/*IF ( condition.srhStatus != "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/
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
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ))*/
		/*IF ( condition.srhOrderDivision == "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1
		/*END*/
		/*IF ( condition.srhOrderDivision != "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
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
	
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION != 1				-- 外注原材料区分!=1:外注(原材料(自社使用))
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM.ITEM_CD 
	AND PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = SUM_PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO(+) 
	AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
	AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
	AND ITEM.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	AND (PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION IS NULL
			 OR PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION = 1)		-- ローリー区分=1(ローリー以外)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 

	UNION

	-- ★外注製品(非直送)
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM.STYLE_OF_PACKING								-- 荷姿
	,		ITEM.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM.SHIPPER_DIVISION								-- 荷主
	,		ARTICLE_ATTRIBUTE_QUEUE.PALETTE_PRODUCTS				-- パレット上製品数
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		SUM_PURCHASE_SUBCONTRACT.SUM_ARRIVAL_QUANTITY			-- 入荷予定数量合計値
	,		ITEM.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		'2' AS KBN												-- 区分(外注製品(非直送))
	FROM	PURCHASE_SUBCONTRACT
	,		(SELECT BUY_SUBCONTRACT_ORDER_NO
			 ,		SUM(NVL(ARRIVAL_QUANTITY, 0)) SUM_ARRIVAL_QUANTITY
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY BUY_SUBCONTRACT_ORDER_NO
			) SUM_PURCHASE_SUBCONTRACT
	,		(SELECT ITEM.*
			 FROM ITEM
			 WHERE	ITEM.TYPE_DIVISION = 7		-- 外注製品(非直送)
			) ITEM
	,		ARTICLE_ATTRIBUTE_QUEUE
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
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ))*/
		/*IF ( condition.srhStatus == "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS IN ('2', '3', '4', '5')
		/*END*/
		/*IF ( condition.srhStatus != "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/
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
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ))*/
		/*IF ( condition.srhOrderDivision == "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = 3
		/*END*/
		/*IF ( condition.srhOrderDivision != "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
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
	
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM.ITEM_CD 
	AND PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = SUM_PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO(+) 
	AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
	AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 

	UNION

	-- ★仕入在庫品
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM.STYLE_OF_PACKING								-- 荷姿
	,		ITEM.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM.SHIPPER_DIVISION								-- 荷主
	,		ARTICLE_ATTRIBUTE_QUEUE.PALETTE_PRODUCTS				-- パレット上製品数
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		SUM_PURCHASE_SUBCONTRACT.SUM_ARRIVAL_QUANTITY			-- 入荷予定数量合計値
	,		ITEM.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		'3' AS KBN												-- 区分(仕入在庫品)
	FROM	PURCHASE_SUBCONTRACT
	,		(SELECT BUY_SUBCONTRACT_ORDER_NO
			 ,		SUM(NVL(ARRIVAL_QUANTITY, 0)) SUM_ARRIVAL_QUANTITY
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY BUY_SUBCONTRACT_ORDER_NO
			) SUM_PURCHASE_SUBCONTRACT
	,		(SELECT ITEM.*
			 FROM ITEM
			 WHERE	ITEM.TYPE_DIVISION = 5		-- 仕入在庫品
			) ITEM
	,		ARTICLE_ATTRIBUTE_QUEUE
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
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ))*/
		/*IF ( condition.srhStatus == "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS IN ('2', '3', '4', '5')
		/*END*/
		/*IF ( condition.srhStatus != "0" )*/
			AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/
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
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ))*/
		/*IF ( condition.srhOrderDivision == "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = 6
		/*END*/
		/*IF ( condition.srhOrderDivision != "0" )*/
			AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/
		/*END*/
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/
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
	
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM.ITEM_CD 
	AND PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = SUM_PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO(+) 
	AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
	AND ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	)
ORDER BY TO_CHAR(SUGGESTED_DELIVERLIMIT_DATE,'YYYYMMDD')
,		 ORDER_SHEET_NO
,		 ORDER_DATE
,		 VENDER_CD
,		 LOCATION_CD
,		 BUY_SUBCONTRACT_ORDER_NO
,		 ORDER_DIVIDE_NO
,		 SLIP_NO
,		 ROW_NO
