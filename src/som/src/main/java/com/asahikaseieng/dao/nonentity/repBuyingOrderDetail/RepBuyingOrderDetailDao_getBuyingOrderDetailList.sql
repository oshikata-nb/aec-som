/*
 * 
 *
 * entityName=RepBuyingOrderDetail
 * packageName=repBuyingOrderDetail
 * methodName=getBuyingOrderDetailList
 *
 */
SELECT * FROM REP_BUYING_ORDER_DETAIL 
WHERE SLIP_NO IN 

-- 画面の検索条件
(
	SELECT 
		PSUB_MAIN.SLIP_NO					-- 仕入番号
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
			,	PSUB.TAX_CD					-- 消費税コード
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
				VENDER_CD,VENDER_NAME1
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
					    VALID_ITEM_QUEUE MAX_ITEM,
					    VALID_ITEM_QUEUE ITEM2
					WHERE
					    ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD AND
					    ITEM2.VERSION = MAX_ITEM.VERSION
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

		AND PSUB_MAIN.SLIP_NO IN /*buyingNo*/('1')

)

ORDER BY KEY,SLIP_NO ASC


