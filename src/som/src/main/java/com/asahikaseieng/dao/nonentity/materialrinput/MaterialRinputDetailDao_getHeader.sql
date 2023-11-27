/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/

/**
 * 外注原材料投入実績入力ヘッダ部用SQL
 *
 * @author tosco
 *
 * entityName=MaterialRinputDetail
 * packageName=materialrinput
 * methodName=getHeader
 *
 */
SELECT	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
,		PURCHASE_SUBCONTRACT.ORDER_SHEET_NO
,		PURCHASE_SUBCONTRACT.ORDER_DATE
,		PURCHASE_SUBCONTRACT.ACCEPT_DATE
,		PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE
,		PURCHASE_SUBCONTRACT.ITEM_CD
,		PURCHASE_SUBCONTRACT.VENDER_CD
,		PURCHASE_SUBCONTRACT.LOCATION_CD
,		PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
,		PURCHASE_SUBCONTRACT.ORGANIZATION_CD
,		PURCHASE_SUBCONTRACT.TANTO_CD
,		PURCHASE_SUBCONTRACT.ORDER_QUANTITY
,		PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY
,		PURCHASE_SUBCONTRACT.GOOD_HOUSING_SUM
,		VENDER.VENDER_NAME1					-- 取引先名称1
,		VENDER.VENDER_SHORTED_NAME			-- 取引先略称
,		LOCATION.LOCATION_NAME				-- ロケーション名称
,		ITEM.ITEM_NAME						-- 品目名称
,		ITEM.OTHER_COMPANY_CD1				-- 他社コード1
,		ITEM.STYLE_OF_PACKING				-- 荷姿
,		ITEM.UNIT_OF_OPERATION_MANAGEMENT AS UNIT_DIV	-- 運用管理単位(区分)
,		NAMES.NAME01 AS UNIT				-- 単位
,		ORG.ORGANIZATION_NAME									-- 部署名称
,		TANTO_ORG.ORGANIZATION_NAME AS CHARGE_ORGANIZATION_NAME	-- 担当部署名称
,		LOGIN.TANTO_NM											-- 担当者名称
FROM	
		(SELECT PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_SHEET_NO) AS ORDER_SHEET_NO
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_DATE) AS ORDER_DATE
		,		MAX(PURCHASE_SUBCONTRACT.ACCEPT_DATE) AS ACCEPT_DATE
		,		MIN(PURCHASE_SUBCONTRACT.SUGGESTED_DELIVERLIMIT_DATE) AS SUGGESTED_DELIVERLIMIT_DATE
		,		MIN(PURCHASE_SUBCONTRACT.ITEM_CD) AS ITEM_CD
		,		MIN(PURCHASE_SUBCONTRACT.VENDER_CD) AS VENDER_CD
		,		MIN(PURCHASE_SUBCONTRACT.LOCATION_CD) AS LOCATION_CD
		,		MIN(PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD) AS CHARGE_ORGANIZATION_CD
		,		MIN(PURCHASE_SUBCONTRACT.ORGANIZATION_CD) AS ORGANIZATION_CD
		,		MIN(PURCHASE_SUBCONTRACT.TANTO_CD) AS TANTO_CD
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_QUANTITY) AS ORDER_QUANTITY
		,		MIN(PURCHASE_SUBCONTRACT.ORDER_CONVERT_QUANTITY) AS ORDER_CONVERT_QUANTITY
		,		MIN(PURCHASE_SUBCONTRACT.GOOD_HOUSING_SUM) AS GOOD_HOUSING_SUM
		FROM	PURCHASE_SUBCONTRACT
		WHERE	PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO IS NOT NULL
		AND		PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO = /*buySubcontractOrderNo*/'a'
		GROUP BY PURCHASE_SUBCONTRACT.BUY_SUBCONTRACT_ORDER_NO
		) PURCHASE_SUBCONTRACT
,		ITEM
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
,		ORGANIZATION ORG
,		ORGANIZATION TANTO_ORG
,		LOGIN
WHERE	PURCHASE_SUBCONTRACT.ITEM_CD = ITEM.ITEM_CD(+)
AND		ITEM.UNIT_OF_OPERATION_MANAGEMENT = NAMES.NAME_CD(+)
AND		PURCHASE_SUBCONTRACT.VENDER_CD = VENDER.VENDER_CD(+) 
AND		PURCHASE_SUBCONTRACT.LOCATION_CD = LOCATION.LOCATION_CD(+) 
AND		PURCHASE_SUBCONTRACT.ORGANIZATION_CD = ORG.ORGANIZATION_CD(+) 
AND		PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD = TANTO_ORG.ORGANIZATION_CD(+) 
AND		PURCHASE_SUBCONTRACT.TANTO_CD = LOGIN.TANTO_CD(+) 
