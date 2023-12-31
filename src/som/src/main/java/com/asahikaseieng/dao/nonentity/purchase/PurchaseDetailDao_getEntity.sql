/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/

/**
 * 一覧用SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDetail
 * packageName=purchase
 * methodName=getEntity
 *
 */
SELECT	PSUB.PURCHASE_NO
,	PSUB.BUY_SUBCONTRACT_ORDER_NO		--発注番号(画面表示)
,	PSUB.ORDER_DIVIDE_NO
,	PSUB.ORDER_NO
,	PSUB.ORDER_ROW_NO
,	PSUB.ASP_ORDER_NO
,	PSUB.MATERIAL_DIVISION
,	PSUB.SI_ORDER_NO
,	PSUB.ORDER_DATE						--発注日(画面表示)
,	PSUB.CHARGE_ORGANIZATION_CD			--担当部署コード(画面表示)
-- *************************************************************
-- 購買外注オーダーテーブルの部署コードがNULLの場合、ログインユーザーの所属部署コードを取得する
,	(
		CASE
			WHEN	PSUB.ORGANIZATION_CD IS NULL THEN
				(	-- ログインユーザーが所属している部署コードの取得
					SELECT	ORGANIZATION_CD
					FROM	BELONG
					WHERE	TANTO_CD = /*tantoCd*/
					AND		BELONG_KBN = '1'		-- 1:主所属
					AND		ROWNUM <= 1
				)
			WHEN	PSUB.ORGANIZATION_CD IS NOT NULL THEN
				PSUB.ORGANIZATION_CD
		END
	) ORGANIZATION_CD					-- 部署コード(画面表示)
--,	PSUB.ORGANIZATION_CD				-- 部署コード(画面表示)
-- *************************************************************
-- 購買外注オーダーテーブルの発注者コードがNULLの場合、ログインユーザーのコードを取得する
,	DECODE(PSUB.TANTO_CD, NULL, /*tantoCd*/, PSUB.TANTO_CD)	AS TANTO_CD -- 発注担当者コード(画面表示)
--,	PSUB.TANTO_CD						--発注担当者コード(画面表示)
-- *************************************************************
,	PSUB.ORDER_DIVISION					--オーダー区分
,	PSUB.VENDER_CD						--仕入先コード(画面表示)
,	PSUB.ITEM_CD						--品目コード(画面表示)
,	DECODE(PSUB.ORDER_DIVISION, 4, PSUB.ITEM_NAME, ITM.ITEM_NAME) AS ITEM_NAME --品目名称(画面表示)
,	PSUB.ORDER_QUANTITY					--発注数量(画面表示)
,	PSUB.ORDER_CONVERT_QUANTITY			--重量(画面表示)
,	PSUB.ORDER_UNITPRICE				--単価(画面表示)
--,	PSUB.UNITPRICE_DEFINEUNIT			--単価決定単位区分(単価の名称をこれから取得)(これは使わない、マスタの値を使う)
,	PSUB.SUPPLIER_ORD_AMOUNT			--発注金額(画面表示)
,	PSUB.SUGGESTED_DELIVERLIMIT_DATE	--納品希望日(画面表示)
,	PSUB.ORDER_SHEET_REMARK				--発注書備考(画面表示)
,	PSUB.REMARK							--備考(画面表示)
,	PSUB.NOTES
,	PSUB.LOCATION_CD					--納入先コード(画面表示)
,	PSUB.STATUS							--購買ステータス(画面表示)
,	PSUB.ORDER_SHEET_NO					--発注書No(画面表示)
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
,	PSUB.TAX_RATIO
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
,	VEN.VENDER_SHORTED_NAME AS SUPPLIER_NAME	-- 仕入先略称
-- 0：免税の計算対象外(申請業者) 1：免税の計算対象(未申請業者)
, 	CASE
      WHEN VEN.SI_INVOICE_PTN = '2'
        THEN '1'
      ELSE '0'
      END REDUCED_TAX_TARGET_FLG
--,	ITM.ITEM_NAME AS ITEM_NAME					-- 品目名称(購買外注オーダーの項目名と同じにしておくこと)
,	NAME.NAME01 AS ORDER_UNIT					-- 数量の単位名称
,	ITM.STYLE_OF_PACKING						-- 荷姿
,	ITM.KG_OF_FRACTION_MANAGEMENT				-- kg換算係数
,	ITM.OTHER_COMPANY_CD1 AS OTHER_COMPANY_CD	-- 他社コード１
,	(CASE										-- 納入先名称、オーダー区分により名称取得先を変える
		WHEN PSUB.ORDER_DIVISION IN (1, 2, 3, 6) THEN LOC.LOCATION_NAME
		WHEN PSUB.ORDER_DIVISION IN (4) THEN NVL(DEL.SEARCH_KANA, LOC.LOCATION_NAME)
		WHEN PSUB.ORDER_DIVISION IN (5) THEN DEL.SEARCH_KANA
		ELSE NULL
	END ) AS DELIVERY_NAME
-- *************************************************************
--,	LG.TANTO_NM									-- 発注担当者名称
-- 購買外注オーダーテーブルの発注者コードがNULLの場合、ログインユーザーのコードより名称を取得する
,	(
		CASE
			WHEN	PSUB.TANTO_CD IS NULL THEN
				(	-- ログインユーザーの名称取得
					SELECT	TANTO_NM
					FROM	LOGIN
					WHERE	TANTO_CD = /*tantoCd*/
				)
			WHEN	PSUB.TANTO_CD IS NOT NULL THEN
				LG.TANTO_NM
		END
	) TANTO_NM									-- 発注担当者名称
-- *************************************************************
,	PATT.MULTI_SUPPLIER_DIVISION				-- 複数社発注区分
,	PATT.SUPPLIED_GOODS_DIVISION				-- 支給品区分
,	COST_NAMES.UNITPRICE_DIVISION AS UNITPRICE_DEFINEUNIT	-- 単位の単位区分(購買外注オーダーテーブルの名前と同じにする。そして購買外注オーダーの値は使わない)
,	COST_NAMES.UNITPRICE_UNIT							-- 単位の単位名称
-- *************************************************************
-- 購買外注オーダーテーブルの部署コードがNULLの場合、ログインユーザーの所属部署コードより部署名称を取得する
--,	ORG.ORGANIZATION_NAME AS SECTION_NAME				-- 部署名称
,	(
		CASE
			WHEN	PSUB.ORGANIZATION_CD IS NULL THEN
				(
					SELECT	ORGANIZATION_NAME
					FROM
						(	-- ログインユーザーが所属している部署コードの取得
							SELECT	ORGANIZATION_CD
							FROM	BELONG
							WHERE	TANTO_CD = /*tantoCd*/
							AND		BELONG_KBN = '0'		-- 0:主所属
							AND		ROWNUM <= 1
						) TB1
						,ORGANIZATION	TB2
					WHERE
						TB1.ORGANIZATION_CD = TB2.ORGANIZATION_CD(+)
				)
			WHEN	PSUB.ORGANIZATION_CD IS NOT NULL THEN
				ORG.ORGANIZATION_NAME
		END
	) SECTION_NAME
-- *************************************************************
,	TANTO_ORG.ORGANIZATION_NAME AS TANTO_SECTION_NM		-- 担当部署名称
,	ITM.UNIT_OF_OPERATION_MANAGEMENT					-- 運用管理単位
,	ITM.SPOT_DIVISION									-- スポット品区分(画面表示で使用、品目名称入力可不可)
,	ITM.ORDER_LOCATION									-- 発注まとめ場所
,	LOC2.LOCATION_NAME AS ORDER_LOCATION_NAME			-- 発注まとめ場所名称
FROM
	VENDER VEN
    ,(
	    SELECT
		    	UNITPRICE.VENDER_DIVISION
			,	UNITPRICE.VENDER_CD
			,	UNITPRICE.ITEM_CD
			,	UNITPRICE.VERSION
			,	UNITPRICE.CONSECUTIVE_NO
			,	UNITPRICE.UNITPRICE_DIVISION
			,	NAMES.NAME01 AS UNITPRICE_UNIT
		FROM
			(
					SELECT	VENDER_CD
				,			ITEM_CD
				,			MAX(VERSION) AS VERSION
				,			CONSECUTIVE_NO
					FROM	UNITPRICE
					WHERE	VENDER_DIVISION = 'SI'	--仕入　'SI'固定
					AND		CONSECUTIVE_NO = '1'
					GROUP BY	VENDER_CD
					,			ITEM_CD
					,			CONSECUTIVE_NO
			)
			MAX_UNITPRICE
		,	UNITPRICE
		,	NAMES
		WHERE	UNITPRICE.VENDER_CD = MAX_UNITPRICE.VENDER_CD
			AND	UNITPRICE.ITEM_CD = MAX_UNITPRICE.ITEM_CD
			AND	UNITPRICE.CONSECUTIVE_NO = MAX_UNITPRICE.CONSECUTIVE_NO
			AND	UNITPRICE.VERSION = MAX_UNITPRICE.VERSION
			AND	'COST' = NAMES.NAME_DIVISION
			AND	UNITPRICE.UNITPRICE_DIVISION = NAMES.NAME_CD(+)
	)COST_NAMES
,	ITEM ITM
,	DELIVERY DEL
,	LOCATION LOC
,	LOCATION LOC2
,	LOGIN LG
,	PURCHASE_ATTRIBUTE_QUEUE PATT
,	NAMES NAME
,	ORGANIZATION ORG
,	ORGANIZATION TANTO_ORG
,	PURCHASE_SUBCONTRACT PSUB
WHERE
		VEN.VENDER_DIVISION(+) = 'SI'
	AND VEN.VENDER_CD(+) = PSUB.VENDER_CD
    AND ITM.ITEM_CD(+) = PSUB.ITEM_CD
    AND NAME.NAME_DIVISION(+) = 'UNIT'
    AND NAME.NAME_CD(+) = ITM.UNIT_OF_OPERATION_MANAGEMENT
    AND DEL.DELIVERY_CD(+) = PSUB.LOCATION_CD
    AND LOC.LOCATION_CD(+) = PSUB.LOCATION_CD
    AND LG.TANTO_CD(+) = PSUB.TANTO_CD
    AND ORG.ORGANIZATION_CD(+) = PSUB.ORGANIZATION_CD
    AND TANTO_ORG.ORGANIZATION_CD(+) = PSUB.CHARGE_ORGANIZATION_CD
    AND LOC2.LOCATION_CD(+) = ITM.ORDER_LOCATION
    AND PATT.ITEM_CD(+) = ITM.ITEM_CD
    AND PATT.VERSION(+) = ITM.VERSION
    AND	COST_NAMES.VENDER_CD(+) = PSUB.VENDER_CD
	AND	COST_NAMES.ITEM_CD(+) = PSUB.ITEM_CD
/*IF (( purchaseNo != null ) && ( purchaseNo != "" ))*/
	AND	PSUB.PURCHASE_NO = /*purchaseNo*/
/*END*/
ORDER BY
	PSUB.ORDER_SHEET_NO
,	PSUB.ORDER_DATE
,	PSUB.VENDER_CD
,	PSUB.LOCATION_CD
