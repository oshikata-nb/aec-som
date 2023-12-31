/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
*/

/**
 * 仕入詳細用SQL
 *
 * @author tosco
 *
 * entityName=BuyingDetail
 * packageName=buying
 * methodName=getEntity
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
,	PSUB.ORGANIZATION_CD
,	PSUB.TANTO_CD
,	PSUB.ORDER_DIVISION
,	PSUB.VENDER_CD
,	PSUB.ITEM_CD
,	PSUB.ITEM_NAME
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
,	PSUB.STATUS
,	PSUB.ORDER_SHEET_NO
,	PSUB.ORDER_SHEE_FLAG
,	PSUB.ORDER_SHEE_DATE
,	PSUB.ORDER_SHEEL_TANTO_CD
,	PSUB.GOOD_HOUSING_SUM
,	PSUB.REPLY_CONTENTS_DIVISION
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
,	PSUB.HOUSING_LOCATION_CD
,	PSUB.ARRIVAL_QUANTITY
,	PSUB.STOCK_QUANTITY
,	PSUB.ACCEPT_QUANTITY
,	PSUB.ACCEPT_CONVERT_QUANTITY
,	PSUB.INCREASE_QUANTITY
,	PSUB_SUM.STOCKING_QUANTITY		-- 数量合計したもの
--,	PSUB.STOCKING_QUANTITY
,	PSUB.HOUSING_UNITPRICE
,	PSUB.FARE_AMOUNT
,	PSUB_SUM.STOCKING_AMOUNT		-- 金額合計したもの
--,	PSUB.STOCKING_AMOUNT
,	PSUB.ACCEPT_DATE
,	PSUB.ORDER_SHEET_REMARK2
,	PSUB.REMARK2
,	PSUB.NOTES2
,	PSUB.STATUS2
--,	PSUB.TAX_DIVISION
,	PSUB.TAX_AMOUNT
,	PSUB.PAYEE_CD
,	PSUB.ACCOUNT_DEBIT_SECTION_CD
,	PSUB.ACCOUNT_CREDIT_SECTION_CD
,	PSUB.DEBIT_TITLE_CD
,	PSUB.DEBIT_SUB_TITLE_CD
,	PSUB.CREDIT_TITLE_CD
,	PSUB.CREDIT_SUB_TITLE_CD
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
,	PSUB.LABORATORY_METHOD
,	PSUB.PROVISION_DIVISION
,	PSUB.CHECK_DATE
--,	PSUB.TAX_RATIO
,	PSUB.CHECK_QUANTITY
,	PSUB.SLIP_ISSUE_DIVISION
,	PSUB.SLIP_ISSUE_DATE
,	PSUB.APPROVAL_STATUS
,	PSUB.APPROVEDBY
,	PSUB.APPROVALDATE
,	PSUB.INPUT_DATE
,	PSUB.INPUTOR_CD
,	PSUB.UPDATE_DATE
,	PSUB.UPDATOR_CD
,	LOCATION.LOCATION_NAME					-- 入庫ロケーション名称(画面表示)
,	LOGIN.TANTO_NM
,	VENDER2.VENDER_NAME1					-- 仕入先
,	VENDER2.VENDER_SHORTED_NAME				-- 仕入先略称(画面表示)
,	UNIT_NAMES.NAME01					AS STOCKING_QUANTITY_UNIT	-- 数量の単位名称(画面表示  運用管理単位より取得)
,	ITEM.UNIT_OF_OPERATION_MANAGEMENT								-- 運用管理単位
,	ITEM.KG_OF_FRACTION_MANAGEMENT									-- Kg換算係数(在庫)(金額計算で使用)
,	DECODE(ITEM.SPOT_DIVISION, 2, PSUB.ITEM_NAME, ITEM.ITEM_NAME) AS ITEM_MASTER_NAME --品目名称(画面表示)
,	ITEM.SPOT_DIVISION						-- スポット区分(品目名称入力可／不可を設定)
,	ITEM.OTHER_COMPANY_CD1					-- 他社コード１(画面表示)
,	COST_NAMES.HOUSING_UNITPRICE_UNIT		-- 単価の単位名称(画面表示)
,	COST_NAMES.UNITPRICE_DIVISION			-- 単価の単位区分(個あたり／KGあたり  金額計算で使用)
,	ORGANIZATION_TANTO_SECTION.ORGANIZATION_NAME	AS TANTO_SECTION_NAME
,	ORGANIZATION_SECTION.ORGANIZATION_NAME			AS SECTION_NAME
,	BUMON_DEBIT_SECTION.SECTION_NAME				AS ACCOUNT_DEBIT_SECTION_NAME
,	BUMON_CREDIT_SECTION.SECTION_NAME				AS ACCOUNT_CREDIT_SECTION_NAME
,	ACCOUNTS_DEBIT_TITLE.ACCOUNTS_NAME				AS DEBIT_TITLE_NAME
,	ACCOUNTS_CREDIT_TITLE.ACCOUNTS_NAME				AS CREDIT_TITLE_NAME
,	CLASS.CATEGORY_NAME								-- 仕入区分、分類名称
-- ***************** ここから消費税 ********************
,	(CASE		-- 購入品扱い属性.消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
		WHEN PURCHASE_ATTRIBUTE_QUEUE.TAX_DIVISION = 4 THEN
			(CASE	-- 取引先ﾏｽﾀ.消費税区分＝4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税課税区分
				WHEN VENDER2.TAX_DIVISION = 4 THEN (SELECT TAX_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/)
				ELSE VENDER2.TAX_DIVISION	-- 取引先ﾏｽﾀ.消費税区分
			 END )
		ELSE PURCHASE_ATTRIBUTE_QUEUE.TAX_DIVISION	-- 購入品扱い属性.消費税課税区分
	 END
	) AS TAX_DIVISION		-- 消費税課税区分
,	(CASE		-- 販売品扱い属性.売上消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
		WHEN PURCHASE_ATTRIBUTE_QUEUE.TAX_DIVISION = 4 THEN
			(CASE	-- 取引先ﾏｽﾀ.消費税区分＝4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税率
				WHEN VENDER2.TAX_DIVISION = 4 THEN (SELECT TAX_RATIO FROM COMPANY WHERE COMPANY_CD = /*companyCd*/)
				ELSE VENDER2.TAX_RATIO	-- 取引先ﾏｽﾀ.消費税率
			 END )
		ELSE VENDER2.TAX_RATIO	-- 取引先ﾏｽﾀ.消費税率
	 END
	) AS TAX_RATIO				-- 消費税率
,	VENDER2.CALC_DIVISION		-- 取引先マスタ.算出区分
,	(SELECT CALC_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/) AS COMP_CALC_DIVISION	-- 自社マスタ.消費税算出区分
-- ***************** ここまで消費税 ********************
,   VENDER2.PAYMENT_INVOICE_CD AS PAYMENT_INVOICE_CD
, CASE
    WHEN VENDER2.SI_INVOICE_PTN = '2'
        THEN '1'
    ELSE '0'
    END REDUCED_TAX_TARGET_FLG    -- 0：免税の計算対象外 1：免税の計算対象
FROM
(
	SELECT
		SLIP_NO												-- 仕入番号
	,	COUNT(*)  AS CNT
	,	SUM(STOCKING_QUANTITY)	AS	STOCKING_QUANTITY		-- 仕入数量
	,	SUM(STOCKING_AMOUNT)	AS	STOCKING_AMOUNT			-- 仕入金額
	FROM
		PURCHASE_SUBCONTRACT
	WHERE
		SLIP_NO = /*slipNo*/
	GROUP BY
		SLIP_NO
) PSUB_SUM
,
(
	SELECT
		SLIP_NO						-- 仕入番号
	,	MIN(ROW_NO)		AS ROW_NO	-- 行番号
	FROM
		PURCHASE_SUBCONTRACT
	WHERE
		SLIP_NO = /*slipNo*/
	GROUP BY
		SLIP_NO
) PSUB_MIN
,
	PURCHASE_SUBCONTRACT	PSUB
,	(SELECT VENDER_CD
	 ,		VENDER_NAME1
	 ,		VENDER_SHORTED_NAME
	 ,		CALC_DIVISION			-- 消費税：算出区分
	 ,		TAX_DIVISION			-- 消費税：消費税区分
	 ,		TAX_RATIO				-- 消費税：消費税率
	 ,      PAYMENT_INVOICE_CD
	 ,		SI_INVOICE_PTN			-- 仕入インボイスパターン
	 FROM VENDER
	 WHERE VENDER_DIVISION = 'SI'
	)VENDER2
,	LOGIN
,	LOCATION
,	ORGANIZATION ORGANIZATION_TANTO_SECTION
,	ORGANIZATION ORGANIZATION_SECTION
,	BUMON BUMON_DEBIT_SECTION
,	BUMON BUMON_CREDIT_SECTION
,	ITEM
,	(SELECT	NAMES.NAME_CD
		,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'  --運用管理単位のため'UNIT'固定
	)UNIT_NAMES
	-- 単価の単位区分と名称を取得
,	(SELECT	UNITPRICE.VENDER_CD
		,	UNITPRICE.ITEM_CD
		,	UNITPRICE.UNITPRICE_DIVISION
		,	NAMES.NAME01 as HOUSING_UNITPRICE_UNIT
	FROM	(SELECT	VENDER_CD
				,	ITEM_CD
				,	MAX(VERSION) as VERSION
				,	CONSECUTIVE_NO
			FROM	UNITPRICE
			WHERE	VENDER_DIVISION = 'SI'	--仕入　'SI'固定
				AND CONSECUTIVE_NO = '1'  --連番 '1'固定
			GROUP BY	VENDER_CD, ITEM_CD, CONSECUTIVE_NO
		)MAX_UNITPRICE
	,	UNITPRICE
	,	NAMES
	WHERE	UNITPRICE.VENDER_CD = MAX_UNITPRICE.VENDER_CD
		AND	UNITPRICE.ITEM_CD = MAX_UNITPRICE.ITEM_CD
		AND	UNITPRICE.CONSECUTIVE_NO = MAX_UNITPRICE.CONSECUTIVE_NO
		AND	UNITPRICE.VERSION = MAX_UNITPRICE.VERSION
		AND	'COST' = NAMES.NAME_DIVISION --単価区分のため'COST'固定
		AND	UNITPRICE.UNITPRICE_DIVISION = NAMES.NAME_CD(+)
	)COST_NAMES
,	(SELECT	ACCOUNTS_CD
		,	ACCOUNTS_NAME
	FROM	ACCOUNTS
	GROUP BY	ACCOUNTS_CD, ACCOUNTS_NAME
	)ACCOUNTS_DEBIT_TITLE
,	(SELECT	ACCOUNTS_CD
		,	ACCOUNTS_NAME
	FROM	ACCOUNTS
	GROUP BY	ACCOUNTS_CD, ACCOUNTS_NAME
	)ACCOUNTS_CREDIT_TITLE
,	(SELECT CATEGORY_DIVISION, CATEGORY_NAME
	FROM CLASSIFICATION
	WHERE DATA_TYPE = '3'
	) CLASS
,	PURCHASE_ATTRIBUTE_QUEUE	-- 消費税で使用
WHERE
	PSUB.SLIP_NO = PSUB_SUM.SLIP_NO
AND	PSUB.SLIP_NO = PSUB_MIN.SLIP_NO
AND	PSUB.ROW_NO = PSUB_MIN.ROW_NO
AND	PSUB.ITEM_CD = ITEM.ITEM_CD(+)
AND	PSUB.VENDER_CD = VENDER2.VENDER_CD(+)
AND	PSUB.TANTO_CD = LOGIN.TANTO_CD(+)
AND	PSUB.HOUSING_LOCATION_CD = LOCATION.LOCATION_CD(+)
AND	ITEM.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)
AND	COST_NAMES.VENDER_CD(+)	= PSUB.VENDER_CD
AND	COST_NAMES.ITEM_CD(+)	= PSUB.ITEM_CD
AND	PSUB.CHARGE_ORGANIZATION_CD	= ORGANIZATION_TANTO_SECTION.ORGANIZATION_CD(+)
AND	PSUB.ORGANIZATION_CD		= ORGANIZATION_SECTION.ORGANIZATION_CD(+)
AND	PSUB.ACCOUNT_DEBIT_SECTION_CD	= BUMON_DEBIT_SECTION.SECTION_CD(+)
AND	PSUB.ACCOUNT_CREDIT_SECTION_CD	= BUMON_CREDIT_SECTION.SECTION_CD(+)
AND	PSUB.DEBIT_TITLE_CD	= ACCOUNTS_DEBIT_TITLE.ACCOUNTS_CD(+)
AND	PSUB.CREDIT_TITLE_CD = ACCOUNTS_CREDIT_TITLE.ACCOUNTS_CD(+)
AND PSUB.CATEGORY_DIVISION = CLASS.CATEGORY_DIVISION(+)
AND ITEM.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+) -- 消費税で使用
AND ITEM.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+) -- 消費税で使用
