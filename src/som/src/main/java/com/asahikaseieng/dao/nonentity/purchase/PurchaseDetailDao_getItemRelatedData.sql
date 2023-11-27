/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/

/**
 * 品目入力時、検索SQL
 *
 * @author tosco
 *
 * entityName=PurchaseDetail
 * packageName=purchase
 * methodName=getItemRelatedData
 *
 */
 
SELECT
	ITEM.ITEM_NAME	AS ITEM_NAME				--品目名称(購買外注オーダーの項目名と同じにする)
,	ITEM.OTHER_COMPANY_CD1	AS OTHER_COMPANY_CD	--他社コード１
,	PAQ.VENDER_CD						--基準仕入先コード
,	PAQ.MULTI_SUPPLIER_DIVISION			--複数社発注区分
,	PAQ.SUPPLIED_GOODS_DIVISION			--支給品区分
,	ITEM.STYLE_OF_PACKING				--荷姿
,	ITEM.DEFAULT_LOCATION				--基準保管場所(オーダー区分により必要)
,	LOC.LOCATION_NAME					--ロケーション名称（基準保管場所で検索したもの）
,	DEL.DELIVERY_NAME1 AS DELIVERY_NAME	--納入先名称1（基準保管場所で検索したもの）
,	ITEM.UNIT_OF_OPERATION_MANAGEMENT	--運用管理単位(発注数量の単位名称を取得するのに必要)
,	NAME.NAME01	AS ORDER_UNIT			--発注数量の単位名称
,	ITEM.KG_OF_FRACTION_MANAGEMENT		--Kg換算係数(在庫)(重量と金額の計算で必要)
,	ITEM.TYPE_DIVISION					--種別(オーダー区分の判定で使用)
,	ITEM.SPOT_DIVISION					--スポット区分(オーダー区分の判定で使用)
,	PAQ.LORRY_DIVISION					--ローリー区分(オーダー区分の判定で使用)
,	ITEM.ORDER_LOCATION							-- 発注まとめ場所
,	LOC2.LOCATION_NAME AS ORDER_LOCATION_NAME	-- 発注まとめ場所名称
FROM	
	ITEM
,	PURCHASE_ATTRIBUTE_QUEUE PAQ
,	NAMES		NAME
,	LOCATION	LOC
,	LOCATION	LOC2
,	DELIVERY	DEL
WHERE
		ITEM.ITEM_CD = /*itemCd*/
	AND PAQ.VERSION(+) = ITEM.VERSION
	AND PAQ.ITEM_CD(+) = ITEM.ITEM_CD
	AND NAME.NAME_DIVISION(+) = 'UNIT'
	AND NAME.NAME_CD(+) = ITEM.UNIT_OF_OPERATION_MANAGEMENT
	AND LOC.LOCATION_CD(+) = ITEM.DEFAULT_LOCATION
	AND DEL.DELIVERY_CD(+) = ITEM.DEFAULT_LOCATION
    AND LOC2.LOCATION_CD(+) = ITEM.ORDER_LOCATION
	