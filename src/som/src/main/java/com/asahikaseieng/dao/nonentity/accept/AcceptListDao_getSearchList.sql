/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/

/**
 * 受入・仕入検索画面用SQL
 *
 * @author tosco
 *
 * entityName=AcceptList
 * packageName=accept
 * methodName=getSearchList
 *
 */
SELECT	KBN
,		NYUKA_FLG
,		PURCHASE_NO
,		BUY_SUBCONTRACT_ORDER_NO
,		ORDER_DIVIDE_NO
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
,		SUGGESTED_DELIVERLIMIT_DATE
,		LOCATION_CD
,		STATUS
-- 2012/12/02 購買修正対応（受入ボタン有効分岐処理）
,		CASE WHEN(((SUPPLIED_GOODS_DIVISION <> 2 AND STATUS = 6 AND STATUS2 = 5 AND TYPE_DIVISION <> 4) OR STATUS < 6) OR (TYPE_DIVISION = 4 AND STATUS < 6))
			THEN 1
			ELSE 0
		END AS BUTTON_STATUS

,		ORDER_SHEET_NO
,		REPLY_CONTENTS_DIVISION
,		CATEGORY_DIVISION
,		SLIP_NO
,		ROW_NO
,		ACCEPT_QUANTITY
,		ACCEPT_CONVERT_QUANTITY
,		ACCEPT_DATE
,		NVL(STATUS2,5) AS STATUS2
,		TYPE_DIVISION				-- 種別
,		ITEM_QUEUE_NAME				-- 品目名称
,		STYLE_OF_PACKING			-- 荷姿
,		KG_OF_FRACTION_MANAGEMENT	-- Kg換算係数(在庫)
,		OTHER_COMPANY_CD1			-- 他社コード1
,		UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV	-- 運用管理単位(区分)
,		VENDER_NAME1				-- 取引先名称1
,		VENDER_SHORTED_NAME			-- 取引先略称
,		LOCATION_NAME				-- ロケーション名称
,		MIN_STATUS					-- 最小購買ステータス
FROM
	(
	-- ★原材料（自社）ローリー以外
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'A' AS KBN												-- 区分(原材料（自社）ローリー以外)
	,		'1' AS NYUKA_FLG										-- 入荷処理対象フラグ(1:対象)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/
	
	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1					-- オーダー区分=1:原材料
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION != 1				-- 外注原材料区分!=1:外注(原材料(自社))
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)					-- 購買ステータス=5:入荷登録済,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	AND (PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION IS NULL
			 OR PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION = 1)		-- ローリー区分=1(ローリー以外)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	-- ★原材料（自社）ローリー
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'B' AS KBN												-- 区分(原材料（自社）ローリー)
	,		'0' AS NYUKA_FLG										-- 入荷処理対象フラグ(0:対象外)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1					-- オーダー区分=1:原材料
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION != 1				-- 外注原材料区分!=1:外注(原材料(自社))
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				-- 購買ステータス=2:発注書発行済,3:納期調整中,4:納期確定,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	AND PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION = 2				-- ローリー区分=2(ローリー)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)

	UNION

	-- ★原材料（外注）
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'C' AS KBN												-- 区分(原材料（外注）)
	,		'0' AS NYUKA_FLG										-- 入荷処理対象フラグ(0:対象外)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 1					-- オーダー区分=1:原材料
	AND PURCHASE_SUBCONTRACT.MATERIAL_DIVISION = 1				-- 外注原材料区分=1:外注
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				-- 購買ステータス=2:発注書発行済,3:納期調整中,4:納期確定,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)

	UNION

	-- ★外注製品(直送)
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'D' AS KBN												-- 区分(外注製品（直送）)
	,		'0' AS NYUKA_FLG										-- 入荷処理対象フラグ(0:対象外)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 2					-- オーダー区分=2：外注製品（直送）
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				-- 購買ステータス=2:発注書発行済,3:納期調整中,4:納期確定,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)

	UNION

	-- ★外注製品(非直送)
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'E' AS KBN												-- 区分(外注製品（非直送）)
	,		'2' AS NYUKA_FLG										-- 入荷処理対象フラグ(1:対象)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 3					-- オーダー区分=3：外注製品（非直送）
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)					-- 購買ステータス=5:入荷登録済,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)

	UNION

	-- ★仕入直送品
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		DELIVERY.DELIVERY_NAME1 AS LOCATION_NAME				-- 納入先名称1
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'F' AS KBN												-- 区分(仕入直送品)
	,		'0' AS NYUKA_FLG										-- 入荷処理対象フラグ(0:対象外)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		DELIVERY	-- 納入先
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 5					-- オーダー区分=5：仕入直送品
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				-- 購買ステータス=2:発注書発行済,3:納期調整中,4:納期確定,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = DELIVERY.DELIVERY_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)

	UNION

	-- ★スポット品
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		PURCHASE_SUBCONTRACT.ITEM_NAME AS ITEM_QUEUE_NAME		-- 品目名称(購買外注オーダより)
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		DELIVERY.DELIVERY_NAME1 AS LOCATION_NAME				-- 納入先名称1
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'G' AS KBN												-- 区分(スポット品)
	,		'0' AS NYUKA_FLG										-- 入荷処理対象フラグ(0:対象外)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		DELIVERY	-- 納入先
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 4					-- オーダー区分=4：スポット品
	AND PURCHASE_SUBCONTRACT.STATUS IN (2, 3, 4, 6)				-- 購買ステータス=2:発注書発行済,3:納期調整中,4:納期確定,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+) 
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = DELIVERY.DELIVERY_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)

	UNION

	-- ★仕入在庫品
	SELECT	PURCHASE_SUBCONTRACT.*
	,		NVL(ITEM_QUEUE.TYPE_DIVISION, '') AS TYPE_DIVISION		-- 種別
	,		ITEM_QUEUE.ITEM_NAME AS ITEM_QUEUE_NAME					-- 品目名称
	,		ITEM_QUEUE.STYLE_OF_PACKING								-- 荷姿
	,		ITEM_QUEUE.KG_OF_FRACTION_MANAGEMENT					-- Kg換算係数(在庫)
	,		ITEM_QUEUE.OTHER_COMPANY_CD1							-- 他社コード1
	,		ITEM_QUEUE.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
	,		VENDER.VENDER_NAME1										-- 取引先名称1
	,		VENDER.VENDER_SHORTED_NAME								-- 取引先略称
	,		LOCATION.LOCATION_NAME									-- ロケーション名称
	,		MIN_STATUS_TBL.MIN_STATUS								-- 最小購買ステータス
	,		'H' AS KBN												-- 区分(仕入在庫品)
	,		'2' AS NYUKA_FLG										-- 入荷処理対象フラグ(1:対象)
	,		PURCHASE_ATTRIBUTE_QUEUE.SUPPLIED_GOODS_DIVISION		-- 2012/12/2 購買修正対応で追加
	FROM	PURCHASE_SUBCONTRACT
	,		VALID_ITEM_QUEUE ITEM_QUEUE
	,		PURCHASE_ATTRIBUTE_QUEUE
	,		(SELECT VENDER_CD
			 ,		VENDER_NAME1
			 ,		VENDER_SHORTED_NAME
			 FROM VENDER
			 WHERE VENDER_DIVISION = 'SI'
			) VENDER
	,		LOCATION	-- ロケーション
	,		(SELECT SLIP_NO
			 ,		MIN(STATUS) MIN_STATUS	-- 購買ステータス
			 FROM PURCHASE_SUBCONTRACT
			 GROUP BY SLIP_NO
			) MIN_STATUS_TBL
	
	WHERE	PURCHASE_SUBCONTRACT.PURCHASE_NO IS NOT NULL
	/*IF (( condition.srhBuySubcontractOrderNo != null ) && ( condition.srhBuySubcontractOrderNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO LIKE /*condition.srhBuySubcontractOrderNo*/'%'
	/*END*/
	
	/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
	/*END*/
	
	/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ITEM_CD LIKE /*condition.srhItemCd*/'%'
	/*END*/
	
	/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
		AND	ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
	/*END*/
	
	/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateFrom != null ) && ( condition.srhSuggestedDeliverlimitDateFrom != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') >= /*condition.srhSuggestedDeliverlimitDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhSuggestedDeliverlimitDateTo != null ) && ( condition.srhSuggestedDeliverlimitDateTo != "" ))*/
		AND	TO_CHAR(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE, 'YYYY/MM/DD') <= /*condition.srhSuggestedDeliverlimitDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhAcceptDateFrom != null ) && ( condition.srhAcceptDateFrom != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE >= /*condition.srhAcceptDateFrom*/'2009/01/01'
	/*END*/
	
	/*IF (( condition.srhAcceptDateTo != null ) && ( condition.srhAcceptDateTo != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ACCEPT_DATE <= /*condition.srhAcceptDateTo*/'2009/12/31'
	/*END*/
	
	/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
	/*END*/
	
	/*IF (( condition.srhOrderDivision != null ) && ( condition.srhOrderDivision != "" ) && ( condition.srhOrderDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.ORDER_DIVISION = /*condition.srhOrderDivision*/1
	/*END*/
	
	/*IF (( condition.srhLocationCd != null ) && ( condition.srhLocationCd != "" ))*/
		AND	PURCHASE_SUBCONTRACT.LOCATION_CD LIKE /*condition.srhLocationCd*/'%'
	/*END*/
	
	/*IF (( condition.srhStatus != null ) && ( condition.srhStatus != "" ) && ( condition.srhStatus != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.STATUS = /*condition.srhStatus*/1
	/*END*/
	
	/*IF (( condition.srhStatus2 != null ) && ( condition.srhStatus2 != "" ) && ( condition.srhStatus2 != "0" ))*/
		AND	NVL(PURCHASE_SUBCONTRACT.STATUS2,5) = /*condition.srhStatus2*/1
	/*END*/
	
	/*IF (( condition.srhCategoryDivision != null ) && ( condition.srhCategoryDivision != "0" ))*/
		AND	PURCHASE_SUBCONTRACT.CATEGORY_DIVISION LIKE /*condition.srhCategoryDivision*/'%'
	/*END*/
	
	/*IF ( condition.srhMonthlyUpdCheck )*/
		AND	(PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION = '1'
				 OR PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION = '1')
	/*END*/

	AND PURCHASE_SUBCONTRACT.ORDER_DIVISION = 6					-- オーダー区分=6：仕入在庫品
	AND PURCHASE_SUBCONTRACT.STATUS IN (5, 6)					-- 購買ステータス=5:入荷登録済,6:受入登録済
	AND PURCHASE_SUBCONTRACT.ITEM_CD = ITEM_QUEUE.ITEM_CD(+)
	AND PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
	AND PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
	AND PURCHASE_SUBCONTRACT.SLIP_NO = MIN_STATUS_TBL.SLIP_NO(+)
	AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
	AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
	)
ORDER BY TO_CHAR(SUGGESTED_DELIVERLIMIT_DATE,'YYYYMMDD')
,		 ORDER_SHEET_NO
,		 VENDER_CD
,		 BUY_SUBCONTRACT_ORDER_NO
,		 ORDER_DIVIDE_NO
,		 SLIP_NO
,		 ROW_NO
