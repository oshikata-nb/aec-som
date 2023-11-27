/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */

/**
 * 売上 登録用売上トランザクションデータSQL
 *
 * @author tosco
 *
 * entityName=SalesDetailEntity
 * packageName=sales
 * methodName=getSalesForRegist
 *
 */
SELECT
		(SELECT TANTO_CD
         FROM DELIVERY
         WHERE
             DELIVERY.DELIVERY_CD = /*deliveryCd*/'N44'
        ) AS SALES_TANTO_CD		   -- 営業担当者コード
,		(CASE		-- 販売品扱い属性.売上消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
			WHEN NVL(ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION,4) = 4 THEN  -- 販売品扱い属性にデータが存在しない場合の対応
				(CASE	-- 取引先ﾏｽﾀ.消費税区分＝4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税課税区分
					WHEN VENDER.TAX_DIVISION = 4 THEN (SELECT TAX_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'0000001')
					ELSE VENDER.TAX_DIVISION	-- 取引先ﾏｽﾀ.消費税区分
				 END )
			ELSE ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION	-- 販売品扱い属性.売上消費税課税区分
		 END
		) AS TAX_DIVISION		   -- 消費税課税区分
,		VENDER.CALC_DIVISION       -- 取引先マスタ.算出区分
,		(SELECT CALC_DIVISION FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'0000001') AS COMP_CALC_DIVISION	-- 自社マスタ.消費税算出区分
,		(CASE		-- 販売品扱い属性.売上消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
			WHEN NVL(ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION,4) = 4 THEN   -- 販売品扱い属性にデータが存在しない場合の対応
				(CASE	-- 取引先ﾏｽﾀ.消費税区分＝4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税率
					WHEN VENDER.TAX_DIVISION = 4 THEN (SELECT TAX_RATIO FROM COMPANY WHERE COMPANY_CD = /*companyCd*/'0000001')
					ELSE VENDER.TAX_RATIO	-- 取引先ﾏｽﾀ.消費税率
				 END )
			ELSE VENDER.TAX_RATIO  -- 取引先ﾏｽﾀ.消費税率
		 END
		) AS TAX_RATIO             -- 消費税率
,		NVL(VENDER.PAYMENT_INVOICE_CD, /*venderCd*/'T44') AS INVOICE_CD	-- 請求先コード
,		(SELECT DATA_TOTAL_DIVISION
		 FROM CLASSIFICATION
		 WHERE DATA_TYPE = 1
		 AND   CATEGORY_DIVISION = /*categoryDivision*/1
		 AND   ROWNUM <= 1
		) AS DATA_TOTAL_DIVISION   -- ﾃﾞｰﾀ集計区分
FROM
		(SELECT VEN.VENDER_CD
		 ,		DECODE(VEN.PAYMENT_INVOICE_CD, NULL, VEN.TAX_DIVISION, INVOICE.TAX_DIVISION) AS TAX_DIVISION	-- 消費税区分(得意先or請求先)
		 ,		DECODE(VEN.PAYMENT_INVOICE_CD, NULL, VEN.CALC_DIVISION, INVOICE.CALC_DIVISION) AS CALC_DIVISION	-- 算出区分(得意先or請求先)
		 ,		DECODE(VEN.PAYMENT_INVOICE_CD, NULL, VEN.TAX_RATIO, INVOICE.TAX_RATIO) AS TAX_RATIO				-- 消費税率(得意先or請求先)
		 ,		VEN.PAYMENT_INVOICE_CD	-- 支払･請求先コード
		 FROM VENDER VEN, VENDER INVOICE
		 WHERE VEN.VENDER_DIVISION = 'TS'
         AND   VEN.VENDER_CD = /*venderCd*/'T44'
		 AND   VEN.PAYMENT_INVOICE_CD = INVOICE.VENDER_CD(+)
		 AND   INVOICE.VENDER_DIVISION(+) = 'TS'
		) VENDER
,       (SELECT ARTICLE_ATTRIBUTE_QUEUE.*
        FROM ARTICLE_ATTRIBUTE_QUEUE
        ,    ITEM
        WHERE
              ITEM.ITEM_CD = /*itemCd*/'02000154'
          AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
          AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
        ) ARTICLE_ATTRIBUTE_QUEUE		-- 販売品扱い属性キュー
