/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/

/**
 * 生産計画入力　明細画面　ヘッダ情報取得SQL
 *
 * @author tosco
 *
 * entityName=ProductionDetail
 * packageName=production
 * methodName=getHeader
 *
 */
SELECT	ASP_PRODUCTION.ITEM_CD --品目コード
,		ASP_PRODUCTION.ORDER_LET --生産計画年月
,		ASP_PRODUCTION.SUM_EXPECT_QTY --見込み数量合計
,		ASP_PRODUCTION.SUM_ACCEPT_QTY --受注数量合計
,		ASP_PRODUCTION.SUM_ALL_ORDER_QTY　--見込み数量合計 +　受注数量合計

,		ITEM_NAMES.ITEM_NAME --品目名称
,		ITEM_NAMES.STYLE_OF_PACKING	-- 荷姿
,		ITEM_NAMES.SHIPPER_DIVISION --荷主
,		ITEM_NAMES.TYPE_DIVISION --種別
,		ITEM_NAMES.OTHER_COMPANY_CD1 --他社コード１
,		ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT --運用管理区分
,		UNIT_NAMES.NAME01 as UNIT
,		LINE.PRODUCTION_LINE_NAME --工場名
FROM
	(SELECT	ITEM_CD --品目コード
	,		MIN(ORDER_LET) AS ORDER_LET --生産計画年月
	,		SUM(ORDER_EXPECT_QTY) as SUM_EXPECT_QTY --受注数量
	,		SUM(ORDER_ACCEPT_QTY) as SUM_ACCEPT_QTY --見込み数量
	,		SUM(NVL(ASP_PRODUCTION.ORDER_EXPECT_QTY,0) + NVL(ASP_PRODUCTION.ORDER_ACCEPT_QTY,0)) as SUM_ALL_ORDER_QTY
	FROM ASP_PRODUCTION
	GROUP BY ITEM_CD,TO_CHAR(ORDER_LET,'yyyy/mm')
	)ASP_PRODUCTION

,	(SELECT	ITEM2.ITEM_CD
	,		ITEM2.VERSION
	,		ITEM2.ITEM_NAME
	,		ITEM2.STYLE_OF_PACKING
	,		ITEM2.OTHER_COMPANY_CD1
	,		ITEM2.SHIPPER_DIVISION
	,		ITEM2.TYPE_DIVISION
	,		ITEM2.UNIT_OF_OPERATION_MANAGEMENT
	FROM
	    	(SELECT
	        	ITEM_CD,
	        	MAX(VERSION) AS VERSION
	    	FROM
	        	ITEM
	    	GROUP BY
	        	ITEM_CD
		) MAX_ITEM
	,	ITEM ITEM2
	WHERE	ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD
	AND		ITEM2.VERSION = MAX_ITEM.VERSION
	) ITEM_NAMES
,	(SELECT	NAMES.NAME_CD
	,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'　--運用管理単位のため'UNIT'固定
	)UNIT_NAMES

,	PRODUCT_ATTRIBUTE_QUEUE
,	LINE
WHERE	ASP_PRODUCTION.ITEM_CD = ITEM_NAMES.ITEM_CD(+)

AND		ITEM_NAMES.ITEM_CD = PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND		ITEM_NAMES.VERSION = PRODUCT_ATTRIBUTE_QUEUE.VERSION(+)
AND		NVL(PRODUCT_ATTRIBUTE_QUEUE.PRODUCTION_LINE,'E') = LINE.PRODUCTION_LINE(+)
AND		ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)

/*IF (( orderLet != null ) && ( orderLet != "" ))*/
	AND	TO_CHAR(ASP_PRODUCTION.ORDER_LET,'yyyy/mm') =  /*orderLet*/
/*END*/

/*IF (( itemCd != null ) && ( itemCd != "" ))*/
	AND	ASP_PRODUCTION.ITEM_CD =  /*itemCd*/
/*END*/


