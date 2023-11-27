/*
 * 包装実績－ロット検索画面検索用SQL
 *
 * entityName=PkgRdirectionLotInventorySearchList
 * packageName=pkgrdirection
 * methodName=getList
 *
 */

SELECT
	LOT_INVENTORY.LOCATION_CD							-- ロケーションコード
,	LOT_INVENTORY.ITEM_CD								-- 品目コード
,	LOT_INVENTORY.LOT_NO								-- 入荷ロット番号/包装指図番号
,	LOT_INVENTORY.INVENTORY_QTY							-- 在庫数量
,	LOT_INVENTORY.ALIAS_LOT_NO							-- 原料ロット番号/製品ロット番号
,	LOT_INVENTORY.ISSUE_DATE
,	ITEM.ITEM_NAME										-- 品目名称
,	ITEM.UNIT_OF_OPERATION_MANAGEMENT 					-- 運用管理単位
,	LOCATION.LOCATION_NAME								-- ロケーション名称
,	NAMES.NAME01 UNIT_NAME								-- 単位名称
,	NVL(DIRECTION_FORMULA.STOCKPD_QTY, 0) STOCKPD_QTY	-- 在庫引落量
,	DIRECTION_FORMULA.RESULT_QTY						-- 実績数量
,	DIRECTION_FORMULA.SAMPLE_QTY						-- サンプル
,	DIRECTION_FORMULA.LOSS_QTY							-- ロス数量
,	DIRECTION_FORMULA.DEFECT_QTY						-- 不良
,	DIRECTION_FORMULA.ADJUST_QTY						-- 調整数量

FROM
	LOT_INVENTORY
,	ITEM
,	LOCATION
,	NAMES
,	(SELECT ITEM_CD,
			LOT_NO,
			STOCKPD_QTY,
			RESULT_QTY,
			SAMPLE_QTY,
			LOSS_QTY,
			DEFECT_QTY,
			ADJUST_QTY,
			LOCATION_CD
	   FROM DIRECTION_FORMULA
	  WHERE DIRECTION_DIVISION = /*condition.directionDivision*/'1'
		AND DIRECTION_NO = /*condition.directionNo*/'A'
		AND STEP_NO = /*condition.stepNo*/'1'
		AND LINE_NO = /*condition.lineNo*/'1' ) DIRECTION_FORMULA
WHERE
	LOT_INVENTORY.ITEM_CD = /*condition.itemCd*/'A'

/*IF (( condition.lotNo != null ) && ( condition.lotNo != "" ))*/
AND	LOT_INVENTORY.LOT_NO = /*condition.lotNo*/'A'
/*END*/
AND	LOT_INVENTORY.ITEM_CD = ITEM.ITEM_CD(+)
AND	LOT_INVENTORY.LOCATION_CD = LOCATION.LOCATION_CD(+)
AND LOCATION.STOCK_DIVISION != 3 /* 在庫区分|1:通常,2:預かり在庫,3:在庫引当対象外 */
AND NAMES.NAME_DIVISION(+) = 'UNIT'
AND NAMES.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
/*IF (( condition.lotNo != null ) && ( condition.lotNo != "" ))*/
AND LOT_INVENTORY.ITEM_CD = DIRECTION_FORMULA.ITEM_CD
AND LOT_INVENTORY.LOT_NO = DIRECTION_FORMULA.LOT_NO
AND LOT_INVENTORY.LOCATION_CD = DIRECTION_FORMULA.LOCATION_CD
/*END*/
/*IF (( condition.lotNo == null ) || ( condition.lotNo == "" ))*/
AND LOT_INVENTORY.ITEM_CD = DIRECTION_FORMULA.ITEM_CD(+)
AND LOT_INVENTORY.LOT_NO = DIRECTION_FORMULA.LOT_NO(+)
AND LOT_INVENTORY.LOCATION_CD = DIRECTION_FORMULA.LOCATION_CD(+)
AND NVL(LOT_INVENTORY.INVENTORY_QTY, 0) > 0
/*END*/
ORDER BY LOT_INVENTORY.ISSUE_DATE