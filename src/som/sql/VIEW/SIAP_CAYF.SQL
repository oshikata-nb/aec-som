CREATE OR REPLACE VIEW AP21.SIAP_CAYF
    (YFCOCODE,YFHBRCODE,YFIMCODE,YFHINNM,YFFUDATE,YFDENNO,YFGYOBAN,YFSZNYUKB,
    YFTWSYORI,YFHINKBN,YFHTANKAKB,YFGBUMONCD,YFTWQTY,YFHSMTANK,YFHSMKING,
    YFYTTANK,YFYTKING,YFIATANK,YFIAKING,YFSTDQTY,YFSTDTANK,YFSTDKING,YFACNO,
    YFACHOJO,YFTACNO,YFSSODRNO,YFGNKEISANNO,YFCKHIKBN,YFSPHCMT,YFODRKB,
    YFKOTEICD,YFOHINCD,YFSSHRQTY,YFNODATE,YFKSEQNO,YFLOTNO,YFSGMNTCD,YFSGMNTCDA,
    YFSGMNTCDB,YFSGMNTCDC,YFSGMNTCD2,YFSODRSYUBT,YFMTSSODRNO,YFKNBUNKB,
    YFSSONKBN,YFDELFLG,YFHTFLG,YFSWKSYBTCD,YFSIKYU,YFATACNO,YFATACHOJO,
    YFATTSACNO,YFTBKBN,YFTBCODE,YFHMDENNO,YFHMGYONO,YFSDENNO,YFSGYONO,YFSDENCRDT,
    YFIANKYMD,YFPRGID,YFDSNO,YFSYMD,YFHINSEQNO,YFTACODE,YFSYHKBN,YFTRSYHKIN,
    YFTNKBN,YFPROJECTCD,YFRIYCD,YFSTOCK_DIVISION,YFINOUT_DIVISION)
AS
SELECT DISTINCT '001' AS YFCOCODE -- 会社コード(※共通仕様参照。)
			   ,'001' AS YFHBRCODE -- 本支店コード(※共通仕様参照。)
			   ,IOREC.ITEM_CD AS YFIMCODE -- 品目コード(20桁以内)
			   ,NULL AS YFHINNM -- 品目名称(未使用 NULL)
			   ,TO_NUMBER(TO_CHAR(IOREC.INOUT_DATE, 'YYYYMMDD')) AS YFFUDATE -- 伝票日付(YYYYMMDD)
			   ,IOREC.INOUT_NO AS YFDENNO -- 伝票番号
				--               ,1 AS YFGYOBAN -- 行番号
			   ,DECODE(P.ROW_NO, NULL, 1, FLOOR(P.ROW_NO / 10) + MOD(P.ROW_NO, 10)) AS YFGYOBAN -- 行番号
				
			   ,CASE --  入出庫区分は受払数で判断
					WHEN IOREC.INOUT_QTY >= 0 THEN
					 0 -- 受払数が＋なら入庫 → 0..入庫
					ELSE
					 1 --                    → 1..出庫
				END AS YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
				
			   ,CASE IOREC.INOUT_DIVISION
					WHEN 10 THEN -- 在庫入出庫
					 CASE
						 WHEN IOREC.RY_CD >= '900'
							  AND IOREC.RY_CD <= '998' THEN
						  CASE
							  WHEN IOREC.INOUT_QTY >= 0 THEN
							   CASE IOREC.FUNC_DIVISION -- 処理区分(ap21と不一致の場合は変換)
								   WHEN 4 THEN -- 製造
									DECODE(ITEM.TYPE_DIVISION
										  ,1 -- 原料
										   -- 親品目と子品目が一致していなければ未完成品
										  ,DECODE(IOREC.ITEM_CD
												 ,CASE
													  WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
													   (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
													  ELSE
													   IOREC.PARENT_ITEM_CD
												  END
												 ,IOREC.FUNC_DIVISION
												  -- 4(製造)なのに数量がプラスの場合は84(製造取消)にする
												 ,CASE
													  WHEN NVL(IOREC.INOUT_QTY, 0) >= 0 THEN
													   84 -- 製造取消
													  ELSE
													   IOREC.FUNC_DIVISION
												  END)
										  ,IOREC.FUNC_DIVISION)
								   WHEN 5 THEN
									4 --  5:支給           → 4:製造
								   WHEN 6 THEN
									3 --  6:移動           → 3:例外
								   WHEN 7 THEN
									3 --  7:廃棄           → 3:例外
								   WHEN 81 THEN
									1 -- 81:仕入取消       → 1:仕入
								   WHEN 82 THEN
									2 -- 82:売上取消       → 2:売上
								   WHEN 83 THEN
									3 -- 83:例外取消       → 3:例外
								   WHEN 84 THEN -- 製造取消
									DECODE(ITEM.TYPE_DIVISION
										  ,1 -- 原料
										   -- 親品目と子品目が一致していなければ未完成品
										  ,DECODE(IOREC.ITEM_CD
												 ,CASE
													  WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
													   (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
													  ELSE
													   IOREC.PARENT_ITEM_CD
												  END
												 ,IOREC.FUNC_DIVISION
												  -- 84(製造取消)なのに数量がナイナスの場合は4(製造)にする
												 ,CASE
													  WHEN NVL(IOREC.INOUT_QTY, 0) < 0 THEN
													   4 -- 製造
													  ELSE
													   IOREC.FUNC_DIVISION
												  END)
										  ,IOREC.FUNC_DIVISION)
								   WHEN 85 THEN
								   --           5 -- 85:支給取消       → 5:支給
									84 -- 85:支給取消       → 84:製造取消
								   WHEN 86 THEN
									3 -- 86:移動取消       → 3:例外
								   WHEN 87 THEN
									7 -- 87:廃棄取消       → 7:廃棄
								   WHEN 88 THEN
									8 -- 88:棚調取消       → 8:棚卸
								   WHEN 89 THEN
									9 -- 89:出荷取消       → 9:出荷
								   WHEN 91 THEN
									1 -- 91:仕返取消       → 1:仕入
								   WHEN 92 THEN
									2 -- 92:売返取消       → 2:売上
								   WHEN 95 THEN
								   --           5 -- 95:支返取消       → 5:支給
									4 -- 95:支返取消       → 4:製造
								   WHEN 99 THEN
									9 -- 99:出返取消       → 9:出荷
								   ELSE
									IOREC.FUNC_DIVISION
							   END
							  ELSE
							   51 -- 仕入返品
						  END
						 ELSE
						  CASE
							  WHEN IOREC.ODER_NO LIKE 'UR%' THEN
							   CASE IOREC.FUNC_DIVISION
								   WHEN 3 THEN -- 例外
									2 -- 売上
								   WHEN 6 THEN -- 移動
									2 -- 売上
								   ELSE
									IOREC.FUNC_DIVISION
							   END
							  ELSE
							   CASE IOREC.FUNC_DIVISION -- 処理区分(ap21と不一致の場合は変換)
								   WHEN 4 THEN -- 製造
									DECODE(ITEM.TYPE_DIVISION
										  ,1 -- 原料
										   -- 親品目と子品目が一致していなければ未完成品
										  ,DECODE(IOREC.ITEM_CD
												 ,CASE
													  WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
													   (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
													  ELSE
													   IOREC.PARENT_ITEM_CD
												  END
												 ,IOREC.FUNC_DIVISION
												  -- 4(製造)なのに数量がプラスの場合は84(製造取消)にする
												 ,CASE
													  WHEN NVL(IOREC.INOUT_QTY, 0) >= 0 THEN
													   84 -- 製造取消
													  ELSE
													   IOREC.FUNC_DIVISION
												  END)
										  ,IOREC.FUNC_DIVISION)
								   WHEN 5 THEN
									4 --  5:支給           → 4:製造
								   WHEN 6 THEN
									3 --  6:移動           → 3:例外
								   WHEN 7 THEN
									3 --  7:廃棄           → 3:例外
								   WHEN 81 THEN
									1 -- 81:仕入取消       → 1:仕入
								   WHEN 82 THEN
									2 -- 82:売上取消       → 2:売上
								   WHEN 83 THEN
									3 -- 83:例外取消       → 3:例外
								   WHEN 84 THEN -- 製造取消
									DECODE(ITEM.TYPE_DIVISION
										  ,1 -- 原料
										   -- 親品目と子品目が一致していなければ未完成品
										  ,DECODE(IOREC.ITEM_CD
												 ,CASE
													  WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
													   (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
													  ELSE
													   IOREC.PARENT_ITEM_CD
												  END
												 ,IOREC.FUNC_DIVISION
												  -- 84(製造取消)なのに数量がナイナスの場合は4(製造)にする
												 ,CASE
													  WHEN NVL(IOREC.INOUT_QTY, 0) < 0 THEN
													   4 -- 製造
													  ELSE
													   IOREC.FUNC_DIVISION
												  END)
										  ,IOREC.FUNC_DIVISION)
								   WHEN 85 THEN
								   --           5 -- 85:支給取消       → 5:支給
									84 -- 85:支給取消       → 84:製造取消
								   WHEN 86 THEN
									3 -- 86:移動取消       → 3:例外
								   WHEN 87 THEN
									7 -- 87:廃棄取消       → 7:廃棄
								   WHEN 88 THEN
									8 -- 88:棚調取消       → 8:棚卸
								   WHEN 89 THEN
									9 -- 89:出荷取消       → 9:出荷
								   WHEN 91 THEN
									1 -- 91:仕返取消       → 1:仕入
								   WHEN 92 THEN
									2 -- 92:売返取消       → 2:売上
								   WHEN 95 THEN
								   --           5 -- 95:支返取消       → 5:支給
									4 -- 95:支返取消       → 4:製造
								   WHEN 99 THEN
									9 -- 99:出返取消       → 9:出荷
								   ELSE
									IOREC.FUNC_DIVISION
							   END
						  END
					 END
					ELSE
					 CASE IOREC.FUNC_DIVISION -- 処理区分(ap21と不一致の場合は変換)
						 WHEN 4 THEN -- 製造
						  DECODE(ITEM.TYPE_DIVISION
								,1 -- 原料
								 -- 親品目と子品目が一致していなければ未完成品
								,DECODE(IOREC.ITEM_CD
									   ,CASE
											WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
											 (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
											ELSE
											 IOREC.PARENT_ITEM_CD
										END
									   ,IOREC.FUNC_DIVISION
										-- 4(製造)なのに数量がプラスの場合は84(製造取消)にする
									   ,CASE
											WHEN NVL(IOREC.INOUT_QTY, 0) >= 0 THEN
											 84 -- 製造取消
											ELSE
											 IOREC.FUNC_DIVISION
										END)
								,IOREC.FUNC_DIVISION)
						 WHEN 5 THEN
						  4 --  5:支給           → 4:製造
						 WHEN 6 THEN
						  3 --  6:移動           → 3:例外
						 WHEN 7 THEN
						  3 --  7:廃棄           → 3:例外
						 WHEN 9 THEN -- 出荷
						 -- 品目が通常品の出荷レコードを売上とする
						  DECODE(IOREC.INOUT_DIVISION, 4, DECODE(ARTICLE.ITEM_CD, NULL, IOREC.FUNC_DIVISION, 2), IOREC.FUNC_DIVISION)
						 WHEN 81 THEN
						  1 -- 81:仕入取消       → 1:仕入
						 WHEN 82 THEN
						  2 -- 82:売上取消       → 2:売上
						 WHEN 83 THEN
						  3 -- 83:例外取消       → 3:例外
						 WHEN 84 THEN -- 製造取消
						  DECODE(ITEM.TYPE_DIVISION
								,1 -- 原料
								 -- 親品目と子品目が一致していなければ未完成品
								,DECODE(IOREC.ITEM_CD
									   ,CASE
											WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
											 (SELECT ITEM_CD FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO)
											ELSE
											 IOREC.PARENT_ITEM_CD
										END
									   ,IOREC.FUNC_DIVISION
										-- 84(製造取消)なのに数量がナイナスの場合は4(製造)にする
									   ,CASE
											WHEN NVL(IOREC.INOUT_QTY, 0) < 0 THEN
											 4 -- 製造
											ELSE
											 IOREC.FUNC_DIVISION
										END)
								,IOREC.FUNC_DIVISION)
						 WHEN 85 THEN
						 --           5 -- 85:支給取消       → 5:支給
						  84 -- 85:支給取消       → 84:製造取消
						 WHEN 86 THEN
						  3 -- 86:移動取消       → 3:例外
						 WHEN 87 THEN
						  7 -- 87:廃棄取消       → 7:廃棄
						 WHEN 88 THEN
						  8 -- 88:棚調取消       → 8:棚卸
						 WHEN 89 THEN
						 --                        9 -- 89:出荷取消       → 9:出荷
						 -- 品目が通常品の出荷レコードを売上とする
						  DECODE(IOREC.INOUT_DIVISION, 4, DECODE(ARTICLE.ITEM_CD, NULL, 9, 2), IOREC.FUNC_DIVISION)
						 WHEN 91 THEN
						  1 -- 91:仕返取消       → 1:仕入
						 WHEN 92 THEN
						  2 -- 92:売返取消       → 2:売上
						 WHEN 95 THEN
						 --           5 -- 95:支返取消       → 5:支給
						  4 -- 95:支返取消       → 4:製造
						 WHEN 99 THEN
						 --                        9 -- 99:出返取消       → 9:出荷
						 -- 品目が通常品の出荷レコードを売上とする
						  DECODE(IOREC.INOUT_DIVISION, 4, DECODE(ARTICLE.ITEM_CD, NULL, 9, 2), IOREC.FUNC_DIVISION)
						 ELSE
						  IOREC.FUNC_DIVISION
					 END
				END AS YFTWSYORI -- 処理区分
				--    ap21の処理区分
				--    1:仕入,2:売上,3:例外,4:製造,5:支給,6:移動,7:廃棄,8:棚卸調整,9:出荷,51:仕入返品,52:売上返品,54:製造戻し,55:支給返納,59:出荷返品,
				--    81:仕入取消,82:売上取消,83:例外取消,84:製造取消,85:支給取消,86:移動取消,87:廃棄取消,88:棚調取消,89:出荷取消,91:仕返取消,92:売返取消,95:支返取消,99:出返取消
				--    SIAPの処理区分
				--   1:仕入 2:売上 3:例外 4:製造 5:支給 8:棚卸 9:出荷 51:仕入返品 52:売上返品 54:製造戻し 55:支給返納 59:出荷返品 84:製造取消)
			   ,CASE -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 7..外注加工品 該当品目の属性に従う。)
					WHEN ITEM.TYPE_DIVISION = 0 THEN
					 0 -- 0:製品           → 0..製品
					WHEN ITEM.TYPE_DIVISION = 1 THEN
					 3 -- 1:原料           → 3..原材料
					WHEN ITEM.TYPE_DIVISION = 2 THEN
					 3 -- 2:包材           → 3..原材料
					WHEN ITEM.TYPE_DIVISION = 3 THEN
					 2 -- 3:中間品         → 2..中間品
					WHEN ITEM.TYPE_DIVISION = 4 THEN
					 1 -- 4:仕入直送品     → 1..商品
					WHEN ITEM.TYPE_DIVISION = 5 THEN
					 1 -- 5:仕入在庫品     → 1..商品
					WHEN ITEM.TYPE_DIVISION = 6 THEN
					 7 -- 6:外注品(直送)   → 7..外注加工品
					WHEN ITEM.TYPE_DIVISION = 7 THEN
					 7 -- 7:外注品(非直送) → 7..外注加工品
					ELSE
					 1 -- 9:その他         → 1..商品
				END AS YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
				
			   ,3 AS YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
				
			   ,CASE IOREC.INOUT_DIVISION -- 受払区分で原価部門コードを取得する。
					WHEN 1 THEN -- 製造受入
					 NVL((SELECT SECTION_CD
						 FROM   AP21.RECIPE_HEADER    RCPH
							   ,AP21.DIRECTION_HEADER DIRH
						 WHERE  RCPH.RECIPE_ID = DIRH.RECIPE_ID
						 AND    RCPH.RECIPE_TYPE = 3 -- 基本処方
						 AND    DIRH.DIRECTION_NO = IOREC.ODER_NO)
						,NVL((SELECT PRODUCT.SECTION_CD
							 FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE PRODUCT
								   ,AP21.ITEM
							 WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
							 AND    ITEM.ITEM_CD = PRODUCT.ITEM_CD
							 AND    ITEM.VERSION = PRODUCT.VERSION)
							,'9999'))
					WHEN 2 THEN -- 製造払出
					 CASE
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (1, 2, 4, 5) -- 原料、包材、仕入商品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.PURCHASE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                     I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,'9999')
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) = 3 -- 中間品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.RECIPE_HEADER    RCPH
									,AP21.DIRECTION_HEADER DIRH
							  WHERE  RCPH.RECIPE_ID = DIRH.RECIPE_ID
							  AND    RCPH.RECIPE_TYPE = 3 -- 基本処方
							  AND    DIRH.DIRECTION_NO = IOREC.ODER_NO)
							 ,NVL((SELECT PRODUCT.SECTION_CD
								  FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE PRODUCT
										,AP21.ITEM
								  WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
								  AND    ITEM.ITEM_CD = PRODUCT.ITEM_CD
								  AND    ITEM.VERSION = PRODUCT.VERSION)
								 ,'9999'))
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (0, 6, 7) -- 製品、外注品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                    I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,NVL((SELECT PRODUCT.SECTION_CD
								  FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE PRODUCT
										,AP21.ITEM
								  WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
								  AND    ITEM.ITEM_CD = PRODUCT.ITEM_CD
								  AND    ITEM.VERSION = PRODUCT.VERSION)
								 ,'9999'))
					 END
					WHEN 3 THEN -- 仕入受入
					 NVL((SELECT SECTION_CD
						 FROM   AP21.VENDER
						 WHERE  VENDER_DIVISION = 'SI'
						 AND    VENDER_CD = (SELECT PAYMENT_INVOICE_CD
											 FROM   AP21.VENDER
											 WHERE  VENDER_DIVISION = 'SI'
											 AND    VENDER_CD = (SELECT MAX(VENDER_CD)
																 FROM   AP21.PURCHASE_SUBCONTRACT P
																 WHERE  P.BUY_SUBCONTRACT_ORDER_NO = IOREC.ODER_NO
																 GROUP  BY P.BUY_SUBCONTRACT_ORDER_NO)))
						,'0101')
					WHEN 4 THEN -- 出荷払出
					 NVL((SELECT SECTION_CD
						 FROM   AP21.VENDER
						 WHERE  VENDER_DIVISION = 'TS'
						 AND    VENDER_CD = (SELECT PAYMENT_INVOICE_CD
											 FROM   AP21.VENDER
											 WHERE  VENDER_DIVISION = 'TS'
											 AND    VENDER_CD = (SELECT VENDER_CD FROM AP21.SHIPPING WHERE SHIPPING_NO = IOREC.ODER_NO)))
						,NVL((SELECT ARTICLE.SECTION_CD
							 FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE ARTICLE
								   ,AP21.ITEM
							 WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
							 AND    ITEM.ITEM_CD = ARTICLE.ITEM_CD
							 AND    ITEM.VERSION = ARTICLE.VERSION)
							,'9999'))
					WHEN 5 THEN -- 支給払出
					 NVL((SELECT SECTION_CD
						 FROM   AP21.PURCHASE_ATTRIBUTE_QUEUE Q
							   ,AP21.ITEM                     I
						 WHERE  I.ITEM_CD = IOREC.ITEM_CD
						 AND    I.ITEM_CD = Q.ITEM_CD
						 AND    I.VERSION = Q.VERSION)
						,'9999')
					WHEN 10 THEN -- 在庫入出庫
					 CASE
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (1, 2, 4, 5) -- 原料、包材、仕入商品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.PURCHASE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                     I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,'9999')
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) = 3 -- 中間品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.RECIPE_HEADER    RCPH
									,AP21.DIRECTION_HEADER DIRH
							  WHERE  RCPH.RECIPE_ID = DIRH.RECIPE_ID
							  AND    RCPH.RECIPE_TYPE = 3 -- 基本処方
							  AND    DIRH.DIRECTION_NO = IOREC.LOT_NO)
							 ,NVL((SELECT PRODUCT.SECTION_CD
								  FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE PRODUCT
										,AP21.ITEM
								  WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
								  AND    ITEM.ITEM_CD = PRODUCT.ITEM_CD
								  AND    ITEM.VERSION = PRODUCT.VERSION)
								 ,'9999'))
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (0, 6, 7) -- 製品、外注品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                    I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,NVL((SELECT PRODUCT.SECTION_CD
								  FROM   AP21.PRODUCT_ATTRIBUTE_QUEUE PRODUCT
										,AP21.ITEM
								  WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
								  AND    ITEM.ITEM_CD = PRODUCT.ITEM_CD
								  AND    ITEM.VERSION = PRODUCT.VERSION)
								 ,'9999'))
					 END
					WHEN 11 THEN -- 棚卸調整
					 CASE
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (1, 2, 4, 5) -- 原料、包材、仕入商品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.PURCHASE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                     I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,'9999')
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) = 3 -- 中間品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.RECIPE_HEADER    RCPH
									,AP21.DIRECTION_HEADER DIRH
							  WHERE  RCPH.RECIPE_ID = DIRH.RECIPE_ID
							  AND    RCPH.RECIPE_TYPE = 3 -- 基本処方
							  AND    DIRH.DIRECTION_NO = IOREC.LOT_NO)
							 ,'9999')
						 WHEN (SELECT TYPE_DIVISION FROM AP21.ITEM WHERE ITEM_CD = IOREC.ITEM_CD) IN (0, 6, 7) -- 製品、外注品
						  THEN
						  NVL((SELECT SECTION_CD
							  FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE Q
									,AP21.ITEM                    I
							  WHERE  I.ITEM_CD = IOREC.ITEM_CD
							  AND    I.ITEM_CD = Q.ITEM_CD
							  AND    I.VERSION = Q.VERSION)
							 ,'9999')
					 END
					WHEN 12 THEN -- 売上返品
					 NVL((SELECT SECTION_CD
						 FROM   AP21.VENDER
						 WHERE  VENDER_DIVISION = 'TS'
						 AND    VENDER_CD = (SELECT PAYMENT_INVOICE_CD
											 FROM   AP21.VENDER
											 WHERE  VENDER_DIVISION = 'TS'
											 AND    VENDER_CD = (SELECT VENDER_CD FROM AP21.SALES WHERE SALES_NO = IOREC.ODER_NO)))
						,NVL((SELECT ARTICLE.SECTION_CD
							 FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE ARTICLE
								   ,AP21.ITEM
							 WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
							 AND    ITEM.ITEM_CD = ARTICLE.ITEM_CD
							 AND    ITEM.VERSION = ARTICLE.VERSION)
							,'9999'))
					WHEN 13 THEN -- 売上（預り品）
					 NVL((SELECT SECTION_CD
						 FROM   AP21.VENDER
						 WHERE  VENDER_DIVISION = 'TS'
						 AND    VENDER_CD = (SELECT PAYMENT_INVOICE_CD
											 FROM   AP21.VENDER
											 WHERE  VENDER_DIVISION = 'TS'
											 AND    VENDER_CD = (SELECT VENDER_CD FROM AP21.SALES WHERE SALES_NO = IOREC.ODER_NO)))
						,NVL((SELECT ARTICLE.SECTION_CD
							 FROM   AP21.ARTICLE_ATTRIBUTE_QUEUE ARTICLE
								   ,AP21.ITEM
							 WHERE  ITEM.ITEM_CD = IOREC.ITEM_CD
							 AND    ITEM.ITEM_CD = ARTICLE.ITEM_CD
							 AND    ITEM.VERSION = ARTICLE.VERSION)
							,'9999'))
					ELSE
					 NVL(IOREC.SECTION_CD, 'NO')
				END AS YFGBUMONCD -- 原価部門コード
				
			   ,CASE IOREC.INOUT_DIVISION
					WHEN 3 THEN -- 入荷受入
					 ABS(NVL(P.ACCEPT_CONVERT_QUANTITY, 0))
					ELSE
					-- 包装、外注原材料の時は換算する
					 CASE
						 WHEN IOREC.ODER_NO LIKE 'HT%' THEN
						  CASE
							  WHEN IOREC.ODER_LINE_NO > 10 THEN
							  -- 外注原材料(部品)
							   ABS(NVL(IOREC.INOUT_QTY, 0))
							  ELSE
							   ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
						  END
						 ELSE
						  CASE
							  WHEN IOREC.ODER_NO LIKE 'H%' THEN
							   CASE
								   WHEN IOREC.ODER_LINE_NO > 10 THEN
								   -- 包装
									ABS(NVL(IOREC.INOUT_QTY, 0))
								   ELSE
									ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
							   END
							  ELSE
							   CASE
								   WHEN IOREC.ODER_NO LIKE 'SK%' THEN
									ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
								   ELSE
									CASE
										WHEN IOREC.ODER_NO LIKE 'SI%' THEN
										 CASE ITEM.TYPE_DIVISION
											 WHEN 0 THEN -- 製品
											 -- 要換算
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
											 WHEN 4 THEN -- 仕入直送品
											 -- 要換算
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
											 WHEN 5 THEN -- 仕入在庫品
											 -- 要換算
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
											 WHEN 6 THEN -- 外注品(直送)
											 -- 要換算
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
											 WHEN 7 THEN -- 外注品(非直送)
											 -- 要換算
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
											 ELSE
											 -- 換算不要
											  ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)), 5)
										 END
										ELSE
										 CASE
											 WHEN IOREC.ODER_NO LIKE 'S%' THEN
											 -- 製造
											  ABS(NVL(IOREC.INOUT_QTY, 0))
											 ELSE
											  CASE ITEM.TYPE_DIVISION
												  WHEN 0 THEN -- 製品
												  -- 要換算
												   ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
												  WHEN 6 THEN -- 外注品(直送)
												  -- 要換算
												   ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
												  WHEN 7 THEN -- 外注品(非直送)
												  -- 要換算
												   ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)) * NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 5)
												  ELSE
												  -- 換算不要
												   ROUND(ABS(NVL(IOREC.INOUT_QTY, 0)), 5)
											  END
										 END
									END
							   END
						  END
					 END
				END YFTWQTY -- 入出庫数
				
			   ,CASE
					WHEN RETURN_PURCHASE.INOUT_NO IS NULL THEN
					 CASE IOREC.FUNC_DIVISION
						 WHEN 1 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.ACCEPT_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 WHEN 81 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.ACCEPT_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 WHEN 91 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.ACCEPT_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 ELSE
						  CASE IOREC.INOUT_DIVISION
							  WHEN 3 THEN -- 入荷受入
							   ROUND(DECODE(NVL(P.ACCEPT_QUANTITY, 0), 0, 0, NVL(P.ACCEPT_AMOUNT, 0) / P.UNITPRICE_QUANTITY), 4)
							  ELSE
							   ROUND(NVL(IOREC.INOUT_PRICE, 0) /
									 DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
									,4)
						  END
					 END
					ELSE
					-- 仕入返品
					 ROUND(DECODE(NVL(RETURN_PURCHASE.STOCKING_QUANTITY, 0)
								 ,0
								 ,0
								 ,NVL(RETURN_PURCHASE.STOCKING_AMOUNT, 0) / NVL(RETURN_PURCHASE.STOCKING_QUANTITY, 0)) /
						   DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
						  ,4)
				END YFHSMTANK -- 発生元単価
				
			   ,CASE
					WHEN RETURN_PURCHASE.INOUT_NO IS NULL THEN
					 CASE IOREC.FUNC_DIVISION
						 WHEN 1 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 WHEN 81 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 WHEN 91 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 ELSE
						  CASE IOREC.INOUT_DIVISION
							  WHEN 3 THEN -- 入荷受入
							   NVL(P.ACCEPT_AMOUNT, 0)
							  ELSE
							   NVL(IOREC.INOUT_COST, 0)
						  END
					 END
					ELSE
					-- 仕入返品
					 NVL(RETURN_PURCHASE.STOCKING_AMOUNT, 0)
				END YFHSMKING -- 発生元金額
				
			   ,0 AS YFYTTANK -- 予定単価
			   ,0 AS YFYTKING -- 予定金額
				
			   ,CASE
					WHEN RETURN_PURCHASE.INOUT_NO IS NULL THEN
					 CASE IOREC.FUNC_DIVISION
						 WHEN 1 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.STOCKING_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 WHEN 81 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.STOCKING_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 WHEN 91 THEN -- 仕入
						  ROUND(DECODE(NVL(P.STOCKING_QUANTITY, 0), 0, 0, NVL(P.STOCKING_AMOUNT, 0) / NVL(P.STOCKING_QUANTITY, 0)) /
								DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
							   ,4)
						 ELSE
						  CASE IOREC.INOUT_DIVISION
							  WHEN 3 THEN -- 入荷受入
							   ROUND(DECODE(NVL(P.ACCEPT_QUANTITY, 0), 0, 0, NVL(P.ACCEPT_AMOUNT, 0) / P.UNITPRICE_QUANTITY), 4)
							  ELSE
							   ROUND(NVL(IOREC.INOUT_PRICE, 0) /
									 DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
									,4)
						  END
					 END
					ELSE
					-- 仕入返品
					 ROUND(DECODE(NVL(RETURN_PURCHASE.STOCKING_QUANTITY, 0)
								 ,0
								 ,0
								 ,NVL(RETURN_PURCHASE.STOCKING_AMOUNT, 0) / NVL(RETURN_PURCHASE.STOCKING_QUANTITY, 0)) /
						   DECODE(NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 0), 0, 1, NVL(ITEM.KG_OF_FRACTION_MANAGEMENT, 1))
						  ,4)
				END YFIATANK -- 実際単価
				
			   ,CASE
					WHEN RETURN_PURCHASE.INOUT_NO IS NULL THEN
					 CASE IOREC.FUNC_DIVISION
						 WHEN 1 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 WHEN 81 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 WHEN 91 THEN -- 仕入
						 --        NVL(P.STOCKING_AMOUNT, 0)
						  NVL(P.ACCEPT_AMOUNT, 0)
						 ELSE
						  CASE IOREC.INOUT_DIVISION
							  WHEN 3 THEN -- 入荷受入
							   NVL(P.ACCEPT_AMOUNT, 0)
							  ELSE
							   NVL(IOREC.INOUT_COST, 0)
						  END
					 END
					ELSE
					-- 仕入返品
					 NVL(RETURN_PURCHASE.STOCKING_AMOUNT, 0)
				END YFIAKING -- 実際金額
				
			   ,0 AS YFSTDQTY -- 標準数量(未使用 0)
			   ,0 AS YFSTDTANK -- 標準単価(未使用 0)
			   ,0 AS YFSTDKING -- 標準金額(未使用 0)
				
			   ,CASE -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 7..外注加工品 該当品目の属性に従う。)
					WHEN IOREC.ACCOUNTS_CD IS NOT NULL THEN
					 TO_CHAR(LPAD(IOREC.ACCOUNTS_CD, 5, '0'))
					ELSE
					 CASE
						 WHEN ITEM.TYPE_DIVISION = 0 THEN
						  '07010' -- 0:製品           → 売上
						 WHEN ITEM.TYPE_DIVISION = 1 THEN
						  '01220' -- 1:原料           → 貯蔵品
						 WHEN ITEM.TYPE_DIVISION = 2 THEN
						  '01220' -- 2:包材           → 貯蔵品
						 WHEN ITEM.TYPE_DIVISION = 3 THEN
						  '01200' -- 3:中間品         → 製品
						 WHEN ITEM.TYPE_DIVISION = 4 THEN
						  '07010' -- 4:仕入直送品     → 売上
						 WHEN ITEM.TYPE_DIVISION = 5 THEN
						  '07010' -- 5:仕入在庫品     → 売上
						 WHEN ITEM.TYPE_DIVISION = 6 THEN
						  '07010' -- 6:外注品(直送)   → 売上
						 WHEN ITEM.TYPE_DIVISION = 7 THEN
						  '07010' -- 7:外注品(非直送) → 売上
						 ELSE
						  '07010' -- 9:その他         → 1..商品
					 END
				END AS YFACNO -- 勘定科目コード
				
			   ,'0000000000' AS YFACHOJO -- 補助科目コード('0000000000')
			   ,NULL AS YFTACNO -- 統制勘定科目コード(未使用 NULL)
				
			   ,CASE IOREC.INOUT_DIVISION
					WHEN 10 THEN --- 在庫入出庫
					 CASE
						 WHEN IOREC.RY_CD >= '900'
							  AND IOREC.RY_CD <= '998' THEN -- 返品
						  IOREC.ODER_NO
						 ELSE
						  NULL
					 END
					ELSE
					 CASE
						 WHEN IOREC.ODER_NO LIKE 'HT%' THEN
						  DECODE(P.BUY_SUBCONTRACT_ORDER_NO
								,NULL
								,DECODE(IOREC.FUNC_DIVISION
									   ,4
									   ,IOREC.ODER_NO
									   ,5
									   ,IOREC.ODER_NO
									   ,54
									   ,IOREC.ODER_NO
									   ,84
									   ,IOREC.ODER_NO
									   ,85
									   ,IOREC.ODER_NO
									   ,95
									   ,IOREC.ODER_NO
									   ,NULL)
								,P.BUY_SUBCONTRACT_ORDER_NO)
						 ELSE
						  DECODE(IOREC.FUNC_DIVISION
								,4
								,IOREC.ODER_NO
								,5
								,IOREC.ODER_NO
								,54
								,IOREC.ODER_NO
								,84
								,IOREC.ODER_NO
								,85
								,IOREC.ODER_NO
								,95
								,IOREC.ODER_NO
								,NULL) -- 処理区分が4,5,54,84,85,95:製造、外注品の時 → 製造オーダー番号
					 END
				END YFSSODRNO
				
			   ,NULL AS YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			   ,0    AS YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			   ,NULL AS YFSPHCMT -- 摘要(未使用 NULL)
				
			   ,CASE
					WHEN IOREC.ODER_NO LIKE 'HT%' THEN
					 1
					ELSE
					 CASE
						 WHEN IOREC.FUNC_DIVISION IN (4, 54, 84) THEN
						  0 -- オーダー区分(0..製造オーダー 1..購買オーダー)
						 ELSE
						  1
					 END
				END YFODRKB
				
			   ,CASE
					WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN
					 DECODE((SELECT DIRECTION_DIVISION FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO), 1, '10', 2, '30', 3, '30', '99')
					ELSE
					 '99'
				END AS YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
				
			   ,DECODE(PARENT_INOUT.ITEM_CD, NULL, PARENT_SOURCE.ITEM_CD, PARENT_INOUT.ITEM_CD) YFOHINCD -- 親品目コード
			   ,0 AS YFSSHRQTY -- 不良数(未使用 0)
			   ,0 AS YFNODATE -- 処理年月度(未使用 0)
				
			   ,CASE
					WHEN IOREC.INOUT_DIVISION IN (1, 2) THEN -- 受払区分が1:製造受入,2:製造払出のとき
					 DECODE((SELECT DIRECTION_DIVISION FROM AP21.DIRECTION_HEADER WHERE DIRECTION_NO = IOREC.ODER_NO), 1, 10, 2, 30, 3, 30, 99)
					ELSE
					 99
				END AS YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
				
			   ,NULL AS YFLOTNO -- ロット番号(未使用 NULL)
			   ,NULL AS YFSGMNTCD -- セグメントコード(未使用 NULL)
			   ,NULL AS YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			   ,NULL AS YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			   ,NULL AS YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			   ,NULL AS YFSGMNTCD2 -- 組コード(未使用 NULL)
			   ,NVL(RECIPE_STATUS.RECIPE_STATUS, '3') AS YFSODRSYUBT -- 製造オーダー種別
			   ,NULL AS YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			   ,DECODE(IOREC.INOUT_DIVISION, 1, 1, 2, 1, 0) AS YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、受払区分が1:製造受入または2:製造払出の場合は1)
			   ,0 AS YFSSONKBN -- 仕損区分(未使用 0)
			   ,0 AS YFDELFLG -- 削除フラグ(未使用 0)
			   ,0 AS YFHTFLG -- 単価未設定フラグ(未使用 0)
			   ,NULL AS YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			   ,0 AS YFSIKYU -- 支給区分(未使用 0)
			   ,NULL AS YFATACNO -- 相手勘定科目コード(未使用 NULL)
			   ,NULL AS YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			   ,NULL AS YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			   ,0 AS YFTBKBN -- 取引先区分(未使用 0)
			   ,NULL AS YFTBCODE -- 取引先コード(未使用 NULL)
			   ,NULL AS YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			   ,0 AS YFHMGYONO -- 発生元伝票行番号(未使用 0)
			   ,NULL AS YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			   ,0 AS YFSGYONO -- 仕訳伝票行番号(未使用 0)
			   ,0 AS YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			   ,0 AS YFIANKYMD -- 受払元帳更新日付(未使用 0)
			   ,NULL AS YFPRGID -- プログラムＩＤ(未使用 NULL)
			   ,NULL AS YFDSNO -- エントリ処理番号(未使用 NULL)
			   ,0 AS YFSYMD -- エントリ処理日(未使用 0)
			   ,0 AS YFHINSEQNO -- 品目順序(未使用 0)
			   ,NULL AS YFTACODE -- 担当者コード(未使用 NULL)
			   ,0 AS YFSYHKBN -- 消費税区分(未使用 0)
			   ,0 AS YFTRSYHKIN -- 消費税額(未使用 0)
			   ,0 AS YFTNKBN -- ロケーション区分(未使用 0)
			   ,NULL AS YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			   ,DECODE(IOREC.FUNC_DIVISION, 3, NVL(IOREC.RY_CD, '-'), IOREC.RY_CD) AS YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			   ,LOCATION.STOCK_DIVISION YFSTOCK_DIVISION -- 在庫区分
			   ,IOREC.INOUT_DIVISION YFINOUT_DIVISION -- 受払区分
FROM   AP21.INOUT_RECORD IOREC -- 受払実績
	  ,(SELECT ODER_NO
			  ,ODER_LINE_NO
			  ,ITEM_CD
		FROM   INOUT_RECORD
		GROUP  BY ODER_NO
				 ,ODER_LINE_NO
				 ,ITEM_CD) PARENT_INOUT
	  ,(SELECT ODER_NO
			  ,ODER_LINE_NO
			  ,ITEM_CD
		FROM   INOUT_SOURCE
		GROUP  BY ODER_NO
				 ,ODER_LINE_NO
				 ,ITEM_CD) PARENT_SOURCE
	  ,AP21.ITEM ITEM -- 品目マスタ
	  ,AP21.ITEM PARENT_ITEM -- 親品目マスタ
	   
	   /* 在庫可能ロケ */
	  ,(SELECT LOCATION_CD
			  ,STOCK_DIVISION
		FROM   AP21.LOCATION
		WHERE  STOCK_DIVISION IN (1, 3) -- 1:通常 3:在庫引当対象外
		AND    AVAILABLE_FLG = 1) LOCATION
	   
	  ,(SELECT * FROM AP21.ARTICLE_ATTRIBUTE_QUEUE WHERE KEEP_DIVISION = 1) ARTICLE
	   
	  ,(SELECT *
		FROM   INOUT_RECORD              INOUT
			  ,AP21.PURCHASE_SUBCONTRACT PURCHASE
		WHERE  INOUT.INOUT_DIVISION = 10
		AND    INOUT.RY_CD BETWEEN '900' AND '998'
		AND    INOUT.INOUT_QTY < 0
		AND    INOUT.ODER_NO IS NOT NULL
		AND    INOUT.ODER_NO = PURCHASE.SLIP_NO) RETURN_PURCHASE
	   
	   /* 処方ステータス取得--->製造オーダー種別 */
	  ,(SELECT DIRECTION.DIRECTION_NO
			  ,RECIPE.RECIPE_STATUS
		FROM   (SELECT RECIPE_ID
					  ,RECIPE_STATUS
				FROM   AP21.RECIPE_HEADER
				WHERE  RECIPE_TYPE = 3
				AND    RECIPE_STATUS IN (2, 3)) RECIPE
			  ,AP21.DIRECTION_HEADER DIRECTION
		WHERE  DIRECTION.RECIPE_ID = RECIPE.RECIPE_ID) RECIPE_STATUS
	   
	  ,(SELECT P.BUY_SUBCONTRACT_ORDER_NO
			  ,P.ROW_NO
			  ,P.LOT_NO
			  ,DECODE(ITEM.TYPE_DIVISION, 4, ITEM.DEFAULT_LOCATION, P.HOUSING_LOCATION_CD) HOUSING_LOCATION_CD
			  ,NVL(P.ACCEPT_QUANTITY, 0) ACCEPT_QUANTITY
			  ,NVL(P.ACCEPT_CONVERT_QUANTITY, 0) ACCEPT_CONVERT_QUANTITY
			   
			  ,CASE TO_NUMBER(NVL(UNITPRICE.UNITPRICE_DIVISION, 0), 0)
				   WHEN 1 THEN -- 個あたり
				   -- 要Kg換算
					ROUND(DECODE(NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)
								,0
								,0
								,NVL(P2.STOCKING_AMOUNT, 0) * NVL(P.ACCEPT_CONVERT_QUANTITY, 0) / (NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)))
						 ,4)
				   WHEN 2 THEN -- Kgあたり
					ROUND(DECODE(NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)
								,0
								,0
								,NVL(P2.STOCKING_AMOUNT, 0) * NVL(P.ACCEPT_CONVERT_QUANTITY, 0) / (NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)))
						 ,4)
				   ELSE
					ROUND(DECODE(NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)
								,0
								,0
								,NVL(P2.STOCKING_AMOUNT, 0) * NVL(P.ACCEPT_CONVERT_QUANTITY, 0) / (NVL(P2.ACCEPT_CONVERT_QUANTITY, 0)))
						 ,4)
			   END ACCEPT_AMOUNT -- 受入金額
			   
			  ,P.STOCKING_AMOUNT -- 仕入金額
			  ,P.STOCKING_QUANTITY -- 仕入数量
			  ,NVL(P.ACCEPT_CONVERT_QUANTITY, 0) UNITPRICE_QUANTITY
		FROM   AP21.PURCHASE_SUBCONTRACT P
			  ,AP21.ITEM
			  ,AP21.UNITPRICE
			  ,(SELECT BUY_SUBCONTRACT_ORDER_NO
					  ,NVL(ORDER_DIVIDE_NO, '000') ORDER_DIVIDE_NO
					  ,SUM(NVL(ACCEPT_QUANTITY, 0)) ACCEPT_QUANTITY
					  ,SUM(NVL(ACCEPT_CONVERT_QUANTITY, 0)) ACCEPT_CONVERT_QUANTITY
					  ,SUM(NVL(STOCKING_QUANTITY, 0)) STOCKING_QUANTITY
					  ,SUM(NVL(HOUSING_UNITPRICE, 0)) HOUSING_UNITPRICE
					  ,SUM(NVL(STOCKING_AMOUNT, 0)) STOCKING_AMOUNT
				FROM   AP21.PURCHASE_SUBCONTRACT P
				GROUP  BY BUY_SUBCONTRACT_ORDER_NO
						 ,NVL(ORDER_DIVIDE_NO, '000')) P2
		WHERE  P.BUY_SUBCONTRACT_ORDER_NO = P2.BUY_SUBCONTRACT_ORDER_NO(+)
		AND    NVL(P.ORDER_DIVIDE_NO, '000') = P2.ORDER_DIVIDE_NO(+)
		AND    ITEM.TYPE_DIVISION <> 9 -- その他以外
		AND    P.ITEM_CD = ITEM.ITEM_CD
		AND    P.VENDER_CD = UNITPRICE.VENDER_CD(+)
		AND    P.ITEM_CD = UNITPRICE.ITEM_CD(+)
		AND    UNITPRICE.CONSECUTIVE_NO(+) = 1) P
WHERE  IOREC.ITEM_CD = ITEM.ITEM_CD
	  --AND    ITEM.TYPE_DIVISION <> 4 -- 仕入直送品以外
AND    ITEM.TYPE_DIVISION <> 9 -- その他以外
AND    ITEM.SHIPPER_DIVISION <> 3 -- 油脂以外
	  -- 預り在庫及び上位ロケは対象としない。
	  --AND    IOREC.LOCATION_CD IN (SELECT LOCATION_CD
	  --               FROM   AP21.LOCATION
	  --               WHERE  STOCK_DIVISION = 1
	  --               AND    AVAILABLE_FLG = 1)
AND    IOREC.LOCATION_CD = LOCATION.LOCATION_CD
AND    TO_CHAR(IOREC.INOUT_DATE, 'YYYYMMDD') > '20091000'
AND    IOREC.ODER_NO = P.BUY_SUBCONTRACT_ORDER_NO(+)
AND    IOREC.LOT_NO = P.LOT_NO(+)
AND    IOREC.LOCATION_CD = P.HOUSING_LOCATION_CD(+)
AND    IOREC.ODER_NO = PARENT_INOUT.ODER_NO(+)
AND    PARENT_INOUT.ODER_LINE_NO(+) < 10
AND    IOREC.ODER_NO = PARENT_SOURCE.ODER_NO(+)
AND    PARENT_SOURCE.ODER_LINE_NO(+) < 10
AND    PARENT_INOUT.ITEM_CD = PARENT_ITEM.ITEM_CD(+)
AND    IOREC.INOUT_NO = RETURN_PURCHASE.INOUT_NO(+)
AND    ITEM.ITEM_CD = ARTICLE.ITEM_CD(+)
AND    ITEM.VERSION = ARTICLE.VERSION(+)
AND    IOREC.ODER_NO = RECIPE_STATUS.DIRECTION_NO(+)
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFCOCODE IS '会社コード(※共通仕様参照。)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHBRCODE IS '本支店コード(※共通仕様参照。)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFIMCODE IS '品目コード(20桁以内)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHINNM IS '品目名称(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFFUDATE IS '伝票日付(YYYYMMDD)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFDENNO IS '伝票番号'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFGYOBAN IS '行番号'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSZNYUKB IS '入出庫区分(0..入庫 1..出庫)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTWSYORI IS '処理区分'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHINKBN IS '品目区分'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHTANKAKB IS '消費単価計算方法'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFGBUMONCD IS '原価部門コード'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTWQTY IS '入出庫数'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHSMTANK IS '発生元単価(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHSMKING IS '発生元金額(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFYTTANK IS '予定単価'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFYTKING IS '予定金額'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFIATANK IS '実際単価'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFIAKING IS '実際金額'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSTDQTY IS '標準数量(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSTDTANK IS '標準単価(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSTDKING IS '標準金額(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFACNO IS '勘定科目コード'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFACHOJO IS '補助科目コード'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTACNO IS '統制勘定科目コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSSODRNO IS '製造オーダー番号(処理区分が1:仕入、4:製造のとき)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFGNKEISANNO IS '原価計算書番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFCKHIKBN IS '直接費・間接費区分'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSPHCMT IS '摘要(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFODRKB IS 'オーダー区分'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFKOTEICD IS '工程コード'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFOHINCD IS '親品目コード'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSSHRQTY IS '不良数(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFNODATE IS '処理年月度(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFKSEQNO IS '工程順序'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFLOTNO IS 'ロット番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGMNTCD IS 'セグメントコード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGMNTCDA IS 'セグメントコード（大分類）(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGMNTCDB IS 'セグメントコード（中分類）(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGMNTCDC IS 'セグメントコード（小分類）(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGMNTCD2 IS '組コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSODRSYUBT IS '製造オーダー種別'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFMTSSODRNO IS '発生元製造オーダー番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFKNBUNKB IS '機能別分類（職能）'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSSONKBN IS '仕損区分(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFDELFLG IS '削除フラグ(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHTFLG IS '単価未設定フラグ(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSWKSYBTCD IS '仕訳種別コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSIKYU IS '支給区分(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFATACNO IS '相手勘定科目コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFATACHOJO IS '相手補助科目コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFATTSACNO IS '相手統制勘定科目コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTBKBN IS '取引先区分(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTBCODE IS '取引先コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHMDENNO IS '発生元伝票番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHMGYONO IS '発生元伝票行番号(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSDENNO IS '仕訳伝票番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSGYONO IS '仕訳伝票行番号(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSDENCRDT IS '仕訳伝票作成日(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFIANKYMD IS '受払元帳更新日付(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFPRGID IS 'プログラムＩＤ(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFDSNO IS 'エントリ処理番号(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSYMD IS 'エントリ処理日(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFHINSEQNO IS '品目順序(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTACODE IS '担当者コード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSYHKBN IS '消費税区分(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTRSYHKIN IS '消費税額(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFTNKBN IS 'ロケーション区分(未使用 0)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFPROJECTCD IS 'プロジェクトコード(未使用 NULL)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFRIYCD IS '理由コード(処理区分が3:例外の場合必須)'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFSTOCK_DIVISION IS '在庫区分'
/
COMMENT ON COLUMN AP21.SIAP_CAYF.YFINOUT_DIVISION IS '受払区分'
/
