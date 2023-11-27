/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
*/

/**
 * 仕入一覧用SQL
 *
 * @author tosco
 *
 * entityName=BuyingList
 * packageName=buying
 * methodName=getSearchList
 *
 */
SELECT 
	PSUB_MAIN.STOCKING_DATE				-- 仕入日
,	PSUB_MAIN.SLIP_NO					-- 仕入番号
,	PSUB_MAIN.STOCKING_QUANTITY			-- 仕入数量
,	PSUB_MAIN.HOUSING_UNITPRICE			-- 仕入単価
,	PSUB_MAIN.STOCKING_AMOUNT			-- 仕入金額
,	PSUB_MAIN.STATUS2					-- 仕入ステータス
,	VENDER_NAME.VENDER_NAME1			-- 仕入先名称
,	VENDER_NAME.VENDER_SHORTED_NAME		-- 仕入先略称
,	DECODE(ITEM_MAIN.SPOT_DIVISION, 2, PSUB_MAIN.ITEM_NAME, ITEM_MAIN.ITEM_NAME) AS ITEM_QUEUE_NAME	-- 品目名称(画面表示)
,	ITEM_MAIN.UNIT_OF_OPERATION_MANAGEMENT	-- 運用管理単位
,	ORGANIZATION.ORGANIZATION_NAME			-- 部署名称
,	LOGIN.TANTO_NM							-- 担当者名称
,	CATEGORY_DIVISION_NAMES.CATEGORY_NAME	-- 仕入区分名称
,	PSUB_MAIN.PAYMENT_UPDATE_DIVISION		-- 月次更新済かどうか判断で使用
,	PSUB_MAIN.PAYABLE_UPDATE_DIVISION		-- 月次更新済かどうか判断で使用
,	PSUB_MAIN.SLIP_ISSUE_DIVISION			-- 仕入伝票発行フラグ
,	PSUB_MAIN.TAX_CD						-- 消費税コード
FROM
	(
		SELECT
			PSUB.SLIP_NO				-- 仕入番号
		,	PSUB.STOCKING_DATE
		,	PSUB.CHARGE_ORGANIZATION_CD	-- 担当部署コード
		,	PSUB.ORGANIZATION_CD		-- 部署コード
		,	PSUB.TANTO_CD				-- 担当者コード
		,	PSUB.ORDER_DIVISION
		,	PSUB.VENDER_CD				-- 仕入先コード
		,	PSUB.ITEM_CD				-- 品目コード
		,	PSUB.ITEM_NAME				-- 品目名称
		,	PSUB.CATEGORY_DIVISION		-- 分類コード
		,	PSUB.STATUS					-- 購買ステータス
		,	PSUB.STATUS2				-- 仕入ステータス
		,	PSUB.HOUSING_UNITPRICE			-- 仕入単価
		,	PSUB_SUM.STOCKING_QUANTITY		-- 仕入数量
		,	PSUB_SUM.STOCKING_AMOUNT		-- 仕入金額
		,	PSUB.PAYMENT_UPDATE_DIVISION
		,	PSUB.PAYABLE_UPDATE_DIVISION
		,	PSUB.SLIP_ISSUE_DIVISION
		,	PSUB.TAX_CD						-- 消費税コード
		FROM
			(
				SELECT
					SLIP_NO							-- 仕入番号
				,	COUNT(*)  AS CNT
				,	SUM(STOCKING_QUANTITY)	AS	STOCKING_QUANTITY	-- 仕入数量
				,	SUM(STOCKING_AMOUNT)	AS	STOCKING_AMOUNT		-- 仕入金額
				FROM
					PURCHASE_SUBCONTRACT
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
				GROUP BY
					SLIP_NO
			) PSUB_MIN
			,
			PURCHASE_SUBCONTRACT	PSUB
		WHERE
			PSUB.SLIP_NO = PSUB_SUM.SLIP_NO
		AND	PSUB.SLIP_NO = PSUB_MIN.SLIP_NO
		AND	PSUB.ROW_NO = PSUB_MIN.ROW_NO
		AND	PSUB.STATUS = '6'
	) PSUB_MAIN
	LEFT JOIN (SELECT 
			VENDER_CD,VENDER_NAME1,VENDER_SHORTED_NAME
			FROM VENDER
			WHERE VENDER_DIVISION = 'SI'
		)VENDER_NAME	ON PSUB_MAIN.VENDER_CD	= VENDER_NAME.VENDER_CD
	LEFT JOIN LOGIN		ON PSUB_MAIN.TANTO_CD	= LOGIN.TANTO_CD
	LEFT JOIN ORGANIZATION	ON PSUB_MAIN.ORGANIZATION_CD	= ORGANIZATION.ORGANIZATION_CD
	LEFT JOIN (	SELECT
				    ITEM2.ITEM_CD ,
				    ITEM2.ITEM_NAME,
				    ITEM2.UNIT_OF_OPERATION_MANAGEMENT,	-- 運用管理単位
				    ITEM2.SPOT_DIVISION,
				    ITEM2.OTHER_COMPANY_CD1
				FROM
					VALID_ITEM_QUEUE ITEM2
				) ITEM_MAIN
		ON PSUB_MAIN.ITEM_CD		= ITEM_MAIN.ITEM_CD
	LEFT JOIN (SELECT
			CATEGORY_DIVISION, CATEGORY_NAME
			FROM CLASSIFICATION
			WHERE DATA_TYPE = 3
		)CATEGORY_DIVISION_NAMES ON PSUB_MAIN.CATEGORY_DIVISION = CATEGORY_DIVISION_NAMES.CATEGORY_DIVISION
WHERE
		PSUB_MAIN.STATUS = '6'			-- 購買ステータス＝６
	AND PSUB_MAIN.STATUS2 IS NOT NULL	-- 仕入ステータスNULL以外
/*IF (( condition.srhSlipNo != null ) && ( condition.srhSlipNo != "" ))*/
	AND	PSUB_MAIN.SLIP_NO LIKE /*condition.srhSlipNo*/'%'
/*END*/

/*IF ( condition.srhCategoryDivision != null )*/
	/* 仕入区分をすべてを選択した場合 */
	/*IF ( condition.srhCategoryDivision == "0") */
		/* 取消チェックボックスにチェックが入っている場合 */
		/*IF ( condition.srhCancelCheck == true) */
			/* 先頭1文字目が'-'のものを検索 */
--			AND	NOT SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION,1,1) = '-'
			AND	SUBSTR(PSUB_MAIN.CATEGORY_DIVISION,1,1) = '-'
		/*END*/
	/*END*/
	/* 仕入区分をすべて以外を選択した場合 */
	/*IF ( condition.srhCategoryDivision != "0") */
		/* 取消チェックボックスにチェックが入っている場合 */
		/*IF ( condition.srhCancelCheck == true) */
			AND	PSUB_MAIN.CATEGORY_DIVISION = /*condition.srhCancelCategoryDivision*/'%'
		/*END*/
		/* 取消チェックボックスにチェックが入っていない場合 */
		/*IF ( condition.srhCancelCheck == false) */
			AND	(PSUB_MAIN.CATEGORY_DIVISION = /*condition.srhCategoryDivision*/'%'
			OR	PSUB_MAIN.CATEGORY_DIVISION = /*condition.srhCancelCategoryDivision*/'%')
		/*END*/
	/*END*/
/*END*/

/*IF (( condition.srhStockingDateFrom != null ) && ( condition.srhStockingDateFrom != "" ))*/
	AND	PSUB_MAIN.STOCKING_DATE >= /*condition.srhStockingDateFrom*/'2009/01/01'
/*END*/
/*IF (( condition.srhStockingDateTo != null ) && ( condition.srhStockingDateTo != "" ))*/
	AND	PSUB_MAIN.STOCKING_DATE <= /*condition.srhStockingDateTo*/'2009/12/31'
/*END*/
/*IF (( condition.srhVenderCd != null ) && ( condition.srhVenderCd != "" ))*/
	AND	PSUB_MAIN.VENDER_CD LIKE /*condition.srhVenderCd*/'%'
/*END*/
/*IF (( condition.srhChargeOrganizationCd != null ) && ( condition.srhChargeOrganizationCd != "" ))*/
	AND	PSUB_MAIN.CHARGE_ORGANIZATION_CD LIKE /*condition.srhChargeOrganizationCd*/'%'
/*END*/
/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	PSUB_MAIN.ITEM_CD LIKE /*condition.srhItemCd*/'%'
/*END*/
/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_MAIN.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/
/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	PSUB_MAIN.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%'
/*END*/
/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	PSUB_MAIN.TANTO_CD LIKE /*condition.srhTantoCd*/'%'
/*END*/
-- 仕入ステータス  指定がある場合、絞込みする
/*IF (( condition.srhStockingStatus != null ) && ( condition.srhStockingStatus != "" ) && ( condition.srhStockingStatus != "0" ))*/
	AND	PSUB_MAIN.STATUS2 = /*condition.srhStockingStatus*/1
/*END*/
-- 月次更新済がチェックされている場合、月次更新済のものを表示   （買掛更新フラグ、支払更新フラグのいずれかが1のものが対象）
/*IF ( condition.srhMonthlyCheck == true )*/
	AND	(PSUB_MAIN.PAYMENT_UPDATE_DIVISION='1' 
	OR	 PSUB_MAIN.PAYABLE_UPDATE_DIVISION='1'
		)
/*END*/

/*IF ( condition.srhSlipIssueDivision == true )*/
		AND	PSUB_MAIN.SLIP_ISSUE_DIVISION = 0
/*END*/

ORDER BY
	PSUB_MAIN.STOCKING_DATE ASC,
	VENDER_NAME.VENDER_NAME1 ASC,
	PSUB_MAIN.SLIP_NO ASC
	