CREATE OR REPLACE VIEW AP21.SIAP_RECIPE_STD
    (RECIPE_USE,ITEM_CD,RECIPE_ID,RECIPE_CD,RECIPE_VERSION,ITEM_STD_QTY,
    ACCOUNT_CD,START_DATE,END_DATE,RECIPE_PRIORITY)
AS
SELECT RCPH.RECIPE_USE -- ヘッダーを品目別に集約する。
	  ,RCPH.ITEM_CD
	  ,RCPH.RECIPE_ID
	  ,RCPH.RECIPE_CD
	  ,RCPH.RECIPE_VERSION
	  ,RCPH.STD_QTY AS ITEM_STD_QTY
	  ,SECT.ACCOUNT_CD
	  ,START_DATE
	  ,END_DATE
	  ,RECIPE_PRIORITY
FROM   (SELECT RECIPE_USE
			  ,ITEM_CD
			  ,RECIPE_ID
			  ,RECIPE_CD
			  ,RECIPE_VERSION
			  ,STD_QTY
			  ,RECIPE_PRIORITY
			  ,SECTION_CD
			  ,START_DATE
			  ,END_DATE
		FROM   (SELECT RECIPE_USE
					  ,ITEM_CD
					  ,RECIPE_CD
					  ,RECIPE_VERSION
					  ,RECIPE_ID
					  ,STD_QTY
					  ,RECIPE_PRIORITY
					  ,START_DATE
					  ,END_DATE
					  ,NVL(SECTION_CD
						  ,NVL((SELECT PRODUCT_ATTRIBUTE_QUEUE.SECTION_CD
							   FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE
									 ,AP21.ITEM
							   WHERE  PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD = ITEM.ITEM_CD
							   AND    PRODUCT_ATTRIBUTE_QUEUE.VERSION = ITEM.VERSION
							   AND    RECIPE_HEADER.ITEM_CD = ITEM.ITEM_CD)
							  ,'5118')) AS SECTION_CD -- 原価部門コード（基本処方になければ、製造品キュー、なければ「化成品未配賦部」
				FROM   AP21.RECIPE_HEADER
				WHERE  RECIPE_TYPE = 3 -- 3:基本処方
				AND    APPROVAL_STATUS = 3 -- 3:承認済み
				AND    RECIPE_STATUS IN (2, 3)) -- 2:試作用,3:一般使用
		) RCPH
	  ,AP21.BUMON SECT -- 部門マスタ
WHERE  RCPH.SECTION_CD = SECT.SECTION_CD(+)
/
COMMENT ON TABLE AP21.SIAP_RECIPE_STD IS '品目別標準処方'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.RECIPE_USE IS '用途　3:製造,4:包装'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.ITEM_CD IS '品目コード'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.RECIPE_ID IS '処方インデックス'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.RECIPE_CD IS '処方コード'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.RECIPE_VERSION IS '処方バージョン'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.ITEM_STD_QTY IS '標準生産量'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.ACCOUNT_CD IS '原価部門コード'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.START_DATE IS '有効開始日'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.END_DATE IS '有効終了日'
/
COMMENT ON COLUMN AP21.SIAP_RECIPE_STD.RECIPE_PRIORITY IS '処方優先度'
/
