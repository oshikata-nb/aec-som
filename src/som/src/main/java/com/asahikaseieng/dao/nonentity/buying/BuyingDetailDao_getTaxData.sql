/*
 * Created on 2009/04/14
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
 * methodName=getTaxData
 *
 */
SELECT
	(CASE		-- 購入品扱い属性.消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
		WHEN NVL(PURCHASE_ATTRIBUTE_QUEUE.TAX_DIVISION,4) = 4 THEN
				(SELECT TAX_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'000001')
		ELSE PURCHASE_ATTRIBUTE_QUEUE.TAX_DIVISION	-- 購入品扱い属性.消費税課税区分
	 END
	) AS TAX_DIVISION		-- 消費税課税区分
	,(SELECT TAX_RATIO FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'000001') AS TAX_RATIO			-- 消費税率
,	(SELECT CALC_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'000001') AS COMP_CALC_DIVISION	-- 自社マスタ.消費税算出区分
FROM
	ITEM
,	PURCHASE_ATTRIBUTE_QUEUE	-- 消費税で使用
WHERE	ITEM.ITEM_CD = /*itemCd*/'39000959'
AND ITEM.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+) -- 消費税で使用
AND ITEM.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+) -- 消費税で使用
