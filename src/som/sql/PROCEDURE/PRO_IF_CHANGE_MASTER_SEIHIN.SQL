CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_SEIHIN
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_SEIHIN		                               */
/*      処理内容      :      品目マスタを変更した結果を計装IF.製品マスタに反映		       、      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.06.02      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	TEMP_COUNT		NUMBER;		

	CONST_INSERT 		NUMBER := 1;		-- レコード追加処理
	CONST_UPDATE 		NUMBER := 2;		-- レコード更新処理
	CONST_DELETE 		NUMBER := 3;		-- レコード削除処理
	CONST_SPACE 		VARCHAR(10) := ' ';	-- スペース
	TEMP_MASTER_SEIHIN	MASTER_SEIHIN%ROWTYPE;	-- IF.原材料マスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_SEIHIN	;
BEGIN

	-- 原材料マスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 製品コードがIF.製品マスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_SEIHIN WHERE TRIM(SEIHINCODE) = PROC_TRRIGER.SEIHINCODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.製品マスタを保持
				SELECT * INTO TEMP_MASTER_SEIHIN FROM MASTER_SEIHIN
					WHERE TRIM(TEMP_MASTER_SEIHIN.SEIHINCODE) = PROC_TRRIGER.SEIHINCODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_SEIHIN SET 
					DAISEICODE	 = PROC_TRRIGER.DAISEICODE	,	-- 代表製品コード
					DAISEIMEI	 = PROC_TRRIGER.DAISEIMEI	,	-- 代表製品名
					SEIHINCODE	 = PROC_TRRIGER.SEIHINCODE	,	-- 製品コード
					SHOHINKUBUN	 = PROC_TRRIGER.SHOHINKUBUN	,	-- 商品区分
					SEIHINMEI	 = PROC_TRRIGER.SEIHINMEI	,	-- 製品名
					SEIHINKANA	 = PROC_TRRIGER.SEIHINKANA	,	-- 製品カナ名称
					FURIKAECODE	 = PROC_TRRIGER.FURIKAECODE	,	-- 振替製品コード
					NISUGATA	 = PROC_TRRIGER.NISUGATA	,	-- 荷姿
					KANRIKUBUN	 = PROC_TRRIGER.KANRIKUBUN	,	-- 製品管理区分
					KGKANZANCHI	 = PROC_TRRIGER.KGKANZANCHI	,	-- ＫＧ換算値
					TOTALJURYO	 = PROC_TRRIGER.TOTALJURYO	,	-- 総重量
					IRISU		 = PROC_TRRIGER.IRISU		,	-- 入数
					KOJOCODE	 = PROC_TRRIGER.KOJOCODE	,	-- 工場コード
					KENSANISSU	 = PROC_TRRIGER.KENSANISSU	,	-- 検査日数
					SEIBUNRUI	 = PROC_TRRIGER.SEIBUNRUI	,	-- 製品分類
					OEMSAKICODE	 = PROC_TRRIGER.OEMSAKICODE	,	-- ＯＥＭ先コード
					KYOTENCODE	 = PROC_TRRIGER.KYOTENCODE	,	-- 納入先拠点コード
					LOTKUBUN	 = PROC_TRRIGER.LOTKUBUN	,	-- ロット区分
					CHOGOTANK1	 = PROC_TRRIGER.CHOGOTANK1	,	-- 調合タンクＮｏ．1
					CHOGOTANK2	 = PROC_TRRIGER.CHOGOTANK2	,	-- 調合タンクＮｏ．2
					CHOGOTANK3	 = PROC_TRRIGER.CHOGOTANK3	,	-- 調合タンクＮｏ．3
					HOSOLINE1	 = PROC_TRRIGER.HOSOLINE1	,	-- 最初包装ライン1
					HOSOLINE2	 = PROC_TRRIGER.HOSOLINE2	,	-- 最初包装ライン2
					HOSOLINE3	 = PROC_TRRIGER.HOSOLINE3	,	-- 最初包装ライン3
					ATOHOSOLINE	 = PROC_TRRIGER.ATOHOSOLINE	,	-- 後包装ライン
					MAXCHOGOJIKAN	 = PROC_TRRIGER.MAXCHOGOJIKAN	,	-- 最大調合時間
					MAXSEIZORYO	 = PROC_TRRIGER.MAXSEIZORYO	,	-- 最大製造量
					MINCHOGOJIKAN	 = PROC_TRRIGER.MINCHOGOJIKAN	,	-- 最小調合時間
					MINSEIZORYO	 = PROC_TRRIGER.MINSEIZORYO	,	-- 最小製造量
					KENSAJIKAN	 = PROC_TRRIGER.KENSAJIKAN	,	-- 調合検査時間
					ISOJIKAN	 = PROC_TRRIGER.ISOJIKAN	,	-- 移送時間
					MAEJIKAN	 = PROC_TRRIGER.MAEJIKAN	,	-- 前準備時間
					HOSOJIKAN	 = PROC_TRRIGER.HOSOJIKAN	,	-- 包装時間
					ATOHOSOJIKAN	 = PROC_TRRIGER.ATOHOSOJIKAN	,	-- 後包装時間
					ATOJIKAN	 = PROC_TRRIGER.ATOJIKAN	,	-- 後始末時間
					OEMFLAG		 = PROC_TRRIGER.OEMFLAG		,	-- (将来)OEM先
					PLONSEISU	 = PROC_TRRIGER.PLONSEISU	,	-- パレット上製品数
					SHUBETSU	 = PROC_TRRIGER.SHUBETSU	,	-- 種別
					IOCODE		 = PROC_TRRIGER.IOCODE		,	-- I/Oコード
					GAICHUKAKO	 = PROC_TRRIGER.GAICHUKAKO	,	-- 外注加工品
					TORIHIKICODE	 = PROC_TRRIGER.TORIHIKICODE	,	-- 外注先名
					HIJU		 = PROC_TRRIGER.HIJU		,	-- 比重
					BUDOMARI	 = PROC_TRRIGER.BUDOMARI	,	-- 歩留まり
					HOSOSABUN	 = PROC_TRRIGER.HOSOSABUN	,	-- 包装指図書分類
					DRYMAXKAISU	 = PROC_TRRIGER.DRYMAXKAISU	,	-- ミキシング回数
					AGINGTIME	 = PROC_TRRIGER.AGINGTIME	 	-- ドライエージング時間
				WHERE MASTER_SEIHIN.SEIHINCODE = TEMP_MASTER_SEIHIN.SEIHINCODE;
		
			ELSE

				INSERT INTO MASTER_SEIHIN (
					DAISEICODE	 , -- 代表製品コード
					DAISEIMEI	 , -- 代表製品名
					SEIHINCODE	 , -- 製品コード
					SHOHINKUBUN	 , -- 商品区分
					SEIHINMEI	 , -- 製品名
					SEIHINKANA	 , -- 製品カナ名称
					FURIKAECODE	 , -- 振替製品コード
					NISUGATA	 , -- 荷姿
					KANRIKUBUN	 , -- 製品管理区分
					KGKANZANCHI	 , -- ＫＧ換算値
					TOTALJURYO	 , -- 総重量
					IRISU		 , -- 入数
					KOJOCODE	 , -- 工場コード
					KENSANISSU	 , -- 検査日数
					SEIBUNRUI	 , -- 製品分類
					OEMSAKICODE	 , -- ＯＥＭ先コード
					KYOTENCODE	 , -- 納入先拠点コード
					LOTKUBUN	 , -- ロット区分
					CHOGOTANK1	 , -- 調合タンクＮｏ．1
					CHOGOTANK2	 , -- 調合タンクＮｏ．2
					CHOGOTANK3	 , -- 調合タンクＮｏ．3
					HOSOLINE1	 , -- 最初包装ライン1
					HOSOLINE2	 , -- 最初包装ライン2
					HOSOLINE3	 , -- 最初包装ライン3
					ATOHOSOLINE	 , -- 後包装ライン
					MAXCHOGOJIKAN	 , -- 最大調合時間
					MAXSEIZORYO	 , -- 最大製造量
					MINCHOGOJIKAN	 , -- 最小調合時間
					MINSEIZORYO	 , -- 最小製造量
					KENSAJIKAN	 , -- 調合検査時間
					ISOJIKAN	 , -- 移送時間
					MAEJIKAN	 , -- 前準備時間
					HOSOJIKAN	 , -- 包装時間
					ATOHOSOJIKAN	 , -- 後包装時間
					ATOJIKAN	 , -- 後始末時間
					OEMFLAG		 , -- (将来)OEM先
					PLONSEISU	 , -- パレット上製品数
					SHUBETSU	 , -- 種別
					IOCODE		 , -- I/Oコード
					GAICHUKAKO	 , -- 外注加工品
					TORIHIKICODE	 , -- 外注先名
					HIJU		 , -- 比重
					BUDOMARI	 , -- 歩留まり
					HOSOSABUN	 , -- 包装指図書分類
					DRYMAXKAISU	 , -- ミキシング回数
					AGINGTIME	   -- ドライエージング時間
				) VALUES(
					PROC_TRRIGER.DAISEICODE	,	-- 代表製品コード
					PROC_TRRIGER.DAISEIMEI	,	-- 代表製品名
					PROC_TRRIGER.SEIHINCODE	,	-- 製品コード
					PROC_TRRIGER.SHOHINKUBUN,	-- 商品区分
					PROC_TRRIGER.SEIHINMEI	,	-- 製品名
					PROC_TRRIGER.SEIHINKANA	,	-- 製品カナ名称
					PROC_TRRIGER.FURIKAECODE,	-- 振替製品コード
					PROC_TRRIGER.NISUGATA	,	-- 荷姿
					PROC_TRRIGER.KANRIKUBUN	,	-- 製品管理区分
					PROC_TRRIGER.KGKANZANCHI,	-- ＫＧ換算値
					PROC_TRRIGER.TOTALJURYO	,	-- 総重量
					PROC_TRRIGER.IRISU	,	-- 入数
					PROC_TRRIGER.KOJOCODE	,	-- 工場コード
					PROC_TRRIGER.KENSANISSU	,	-- 検査日数
					PROC_TRRIGER.SEIBUNRUI	,	-- 製品分類
					PROC_TRRIGER.OEMSAKICODE,	-- ＯＥＭ先コード
					PROC_TRRIGER.KYOTENCODE	,	-- 納入先拠点コード
					PROC_TRRIGER.LOTKUBUN	,	-- ロット区分
					PROC_TRRIGER.CHOGOTANK1	,	-- 調合タンクＮｏ．1
					PROC_TRRIGER.CHOGOTANK2	,	-- 調合タンクＮｏ．2
					PROC_TRRIGER.CHOGOTANK3	,	-- 調合タンクＮｏ．3
					PROC_TRRIGER.HOSOLINE1	,	-- 最初包装ライン1
					PROC_TRRIGER.HOSOLINE2	,	-- 最初包装ライン2
					PROC_TRRIGER.HOSOLINE3	,	-- 最初包装ライン3
					PROC_TRRIGER.ATOHOSOLINE	,	-- 後包装ライン
					PROC_TRRIGER.MAXCHOGOJIKAN	,	-- 最大調合時間
					PROC_TRRIGER.MAXSEIZORYO	,	-- 最大製造量
					PROC_TRRIGER.MINCHOGOJIKAN	,	-- 最小調合時間
					PROC_TRRIGER.MINSEIZORYO	,	-- 最小製造量
					PROC_TRRIGER.KENSAJIKAN	,	-- 調合検査時間
					PROC_TRRIGER.ISOJIKAN	,	-- 移送時間
					PROC_TRRIGER.MAEJIKAN	,	-- 前準備時間
					PROC_TRRIGER.HOSOJIKAN	,	-- 包装時間
					PROC_TRRIGER.ATOHOSOJIKAN	,	-- 後包装時間
					PROC_TRRIGER.ATOJIKAN	,	-- 後始末時間
					PROC_TRRIGER.OEMFLAG	,	-- (将来)OEM先
					PROC_TRRIGER.PLONSEISU	,	-- パレット上製品数
					PROC_TRRIGER.SHUBETSU	,	-- 種別
					PROC_TRRIGER.IOCODE	,	-- I/Oコード
					PROC_TRRIGER.GAICHUKAKO	,	-- 外注加工品
					PROC_TRRIGER.TORIHIKICODE,	-- 外注先名
					PROC_TRRIGER.HIJU	,	-- 比重
					PROC_TRRIGER.BUDOMARI	,	-- 歩留まり
					PROC_TRRIGER.HOSOSABUN	,	-- 包装指図書分類
					PROC_TRRIGER.DRYMAXKAISU,	-- ミキシング回数
					PROC_TRRIGER.AGINGTIME	 	-- ドライエージング時間
				);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.担当者マスタを保持
				SELECT * INTO TEMP_MASTER_SEIHIN FROM MASTER_SEIHIN WHERE TRIM(MASTER_SEIHIN.SEIHINCODE) = PROC_TRRIGER.SEIHINCODE;
				
				DELETE FROM MASTER_SEIHIN WHERE MASTER_SEIHIN.SEIHINCODE = TEMP_MASTER_SEIHIN.SEIHINCODE;
			END IF;
		END IF;
		
		COMMIT;
		-- 一時テーブルのレコードを削除
		DELETE FROM IF_MASTER_SEIHIN WHERE IF_MASTER_SEIHIN.SEIHINCODE = PROC_TRRIGER.SEIHINCODE;
		COMMIT;
		
	END LOOP;
	
	COMMIT;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_CHANGE_MASTER_SEIHIN','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
