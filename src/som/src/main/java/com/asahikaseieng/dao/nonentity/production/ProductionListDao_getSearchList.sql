/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
*/

/**
 * 生産計画入力一覧用SQL
 *
 * @author tosco
 *
 * entityName=ProductionList
 * packageName=production
 * methodName=getSearchList
 *
 */
SELECT	ASP_PRODUCTION.ITEM_CD --品目コード
,	ASP_PRODUCTION.ORDER_LET --生産計画年月
,	ASP_PRODUCTION.ORDER_EXPECT_QTY --生産計画
,	ASP_PRODUCTION.ORDER_ACCEPT_QTY --生産計画

,	ITEM_NAMES.ITEM_NAME --品目名称
,	ITEM_NAMES.STYLE_OF_PACKING	-- 荷姿
,	ITEM_NAMES.SHIPPER_DIVISION --
,	ITEM_NAMES.TYPE_DIVISION --種別
,	ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT --運用管理単位

,	UNIT_NAMES.NAME01 as UNIT_NAME --単位
,	LINE.PRODUCTION_LINE_NAME --工場名
FROM
	(SELECT	ITEM_CD --品目コード
	,	MIN(ORDER_LET) AS ORDER_LET --生産計画年月
	,	SUM(ORDER_EXPECT_QTY) as ORDER_EXPECT_QTY --受注数量
	,	SUM(ORDER_ACCEPT_QTY) as ORDER_ACCEPT_QTY --見込み数量
	FROM ASP_PRODUCTION
	WHERE	ORDER_LET IS NOT NULL
	GROUP BY ITEM_CD,TO_CHAR(ORDER_LET,'yyyy/mm')
	)ASP_PRODUCTION

,	(SELECT
		ITEM2.ITEM_CD ,
		ITEM2.VERSION,
		ITEM2.ITEM_NAME,
		ITEM2.OTHER_COMPANY_CD1,
		ITEM2.STYLE_OF_PACKING,
		ITEM2.SHIPPER_DIVISION,
		ITEM2.TYPE_DIVISION,
		ITEM2.UNIT_OF_OPERATION_MANAGEMENT
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
	WHERE
		ITEM2.ITEM_CD = MAX_ITEM.ITEM_CD AND
		ITEM2.VERSION = MAX_ITEM.VERSION
	) ITEM_NAMES

,	(SELECT	NAMES.NAME_CD
	,	NAMES.NAME01
	FROM	NAMES
	WHERE	NAMES.NAME_DIVISION = 'UNIT'　--運用管理単位のため'UNIT'固定
	)UNIT_NAMES

,	PRODUCT_ATTRIBUTE_QUEUE
,	LINE
WHERE
	ASP_PRODUCTION.ITEM_CD = ITEM_NAMES.ITEM_CD(+)
AND	ITEM_NAMES.UNIT_OF_OPERATION_MANAGEMENT = UNIT_NAMES.NAME_CD(+)

AND	ITEM_NAMES.ITEM_CD = PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND	ITEM_NAMES.VERSION = PRODUCT_ATTRIBUTE_QUEUE.VERSION(+)
AND	NVL(PRODUCT_ATTRIBUTE_QUEUE.PRODUCTION_LINE,'E') = LINE.PRODUCTION_LINE(+)

/*IF (( condition.srhShipperDivision != null ) && ( condition.srhShipperDivision != "" ))*/
	/*IF (condition.srhShipperDivision == "0")*/

	/*END*/
	/*IF (condition.srhShipperDivision != "0")*/
			AND	ITEM_NAMES.SHIPPER_DIVISION + 1 = /*condition.srhShipperDivision*/
	/*END*/		
/*END*/

/*IF (( condition.srhTypeDivision != null ) && ( condition.srhTypeDivision != "" ))*/
	/*IF (condition.srhTypeDivision == "0")*/
			
	/*END*/
	/*IF (condition.srhTypeDivision == "1")*/
			AND	ITEM_NAMES.TYPE_DIVISION NOT IN ('6','7')
	/*END*/
	/*IF (condition.srhTypeDivision == "2")*/
			AND	ITEM_NAMES.TYPE_DIVISION IN ('6','7')
	/*END*/	
/*END*/

/*IF (( condition.srhProductionLine != null ) && ( condition.srhProductionLine != "" ))*/
	/*IF (condition.srhProductionLine == "0")*/
	
	/*END*/
	/*IF (condition.srhProductionLine != "0")*/
			AND	LINE.PRODUCTION_LINE = /*condition.srhProductionLine*/
	/*END*/		
/*END*/

/*IF (( condition.srhOtherCompanyCd1 != null ) && ( condition.srhOtherCompanyCd1 != "" ))*/
	AND	ITEM_NAMES.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/
/*END*/

/*IF (( condition.srhItemCd != null ) && ( condition.srhItemCd != "" ))*/
	AND	ITEM_NAMES.ITEM_CD LIKE /*condition.srhItemCd*/
/*END*/

/*IF (( condition.srhOrderLet != null ) && ( condition.srhOrderLet != "" ))*/
	AND	TO_CHAR(ASP_PRODUCTION.ORDER_LET,'yyyy/mm') = /*condition.srhOrderLet*/
/*END*/

ORDER BY
		ASP_PRODUCTION.ORDER_LET DESC --生産計画年月　降順
,		ASP_PRODUCTION.ITEM_CD ASC --品目コード　昇順