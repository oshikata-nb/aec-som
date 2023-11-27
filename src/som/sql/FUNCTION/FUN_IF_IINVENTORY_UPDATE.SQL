CREATE OR REPLACE FUNCTION      FUN_IF_IINVENTORY_UPDATE
      (
             I_LOCATION_CD		IN	NVARCHAR2,	-- ロケーションコード
             I_ITEM_CD			IN	NVARCHAR2,	-- 品目コード
             I_LOT_NO			IN	NVARCHAR2,	-- ロット番号
             I_ALIAS_LOT_NO		IN	NVARCHAR2,	-- 原料ロット番号/製品ロット番号
             I_IO_DIVISION		IN	NVARCHAR2,	-- 入出庫力区分
             I_IO_QTY			IN	NUMBER,		-- 入出出庫数量
             IO_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             IO_RETURN_MSG		IN OUT	NVARCHAR2	-- エラーメッセージを返す
      )

RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_IF_IINVENTORY_UPDATE                                                  */
/*      処理内容      :      計装IF製品在庫テーブルのデータを更新する			       、      */
/*      引数          :      I_LOCATION_CD		出荷予定日		NVARCHAR2型            */
/*      引数          :      I_SENDING_OFF_NO		積出ナンバー		NVARCHAR2型            */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      O_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	
	TEMP_COUNT		NUMBER;			
	TEMP_ITEM_CD		VARCHAR2(20);		-- 品目コード
	TEMP_OTHER_CO1		VARCHAR2(20);		-- 他社コード1
	TEMP_SHIPPER_DIVISION   VARCHAR2(20);		-- 荷主
	TEMP_ITEM_TABLE		ITEM%ROWTYPE;		-- 品目テーブル
	TEMP_INVENTORY_QTY	NUMBER;			-- 在庫数量
	TEMP_LOCATION_CD	VARCHAR2(20);		-- ロケーション
	TEMP_SYSDATE		VARCHAR2(20);		-- 時刻
	TEMP_ALIAS_LOT_NO	VARCHAR2(20);		-- 自社ロット番号
	TEMP_IMPORT_NO		VARCHAR2(20);		-- 入荷ロット番号
	TEMP_PACKAGE_NO		VARCHAR2(20);		-- 包装指図番号


	TEMP_ZAIKO_SEIHIN	ZAIKO_SEIHIN%ROWTYPE;	-- IF.製品在庫
	
	CONST_LOT		VARCHAR2(11) := '00000000000';	-- 使わないロット番号
	CONST_ORDER_NO		VARCHAR2(8) := '00000000';	-- 受注番号
	CONST_ORDER_ROW		VARCHAR2(2) := '00';		-- 受注枝番
	CONST_WHITE_SPACE	VARCHAR2(1) := ' ';		-- ホワイトスペース（データを入れない場合等に使用）
	CONST_PRODUCTION_DIVISION VARCHAR2(2) := '00';		-- 製品区分

BEGIN

	TEMP_ITEM_CD := I_ITEM_CD;
	TEMP_LOCATION_CD := I_LOCATION_CD;
	TEMP_SYSDATE := TO_CHAR(SYSDATE,'YYYY/MM/DD');
	TEMP_ALIAS_LOT_NO := I_ALIAS_LOT_NO;
	
	IF TEMP_ALIAS_LOT_NO IS NULL THEN
		TEMP_ALIAS_LOT_NO := CONST_WHITE_SPACE;
	END IF;

	-- H で始まる（包装指図番号）の場合
	IF SUBSTR(I_LOT_NO,1,1) = 'H' THEN	-- 包装指図番号
		TEMP_PACKAGE_NO := I_LOT_NO;
		TEMP_IMPORT_NO := CONST_LOT;
	ELSE					-- 
		TEMP_PACKAGE_NO := CONST_LOT;
		TEMP_IMPORT_NO := I_LOT_NO;
	END IF;
	-- 品目マスタを検索
	SELECT COUNT(*) INTO TEMP_COUNT FROM ITEM WHERE ITEM.ITEM_CD = TEMP_ITEM_CD;

	-- 指定品目コードがあるかマスタを検索
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		IO_RETURN_MSG := 'ロケ:' || TEMP_LOCATION_CD || ' 品目:' || TEMP_ITEM_CD || ' ロット番号:' || I_LOT_NO || ' データが品目マスタにありません。';
		RETURN -1;
	END IF;
	
	-- IF.製品在庫テーブルを検索
	SELECT COUNT(*) INTO TEMP_COUNT FROM ZAIKO_SEIHIN WHERE 
			TRIM(HOSOSASHIZUNO) = TEMP_PACKAGE_NO 	-- 包装指図番号
			AND TRIM(ZAIKOKUBUN) = '1' 			-- 在庫区分
			AND TRIM(SEIHINKUBUN) = CONST_PRODUCTION_DIVISION	-- 製品区分（在庫管理テーブルを参照（保留））
			AND TRIM(LOCATION) = TEMP_LOCATION_CD	-- ロケーション
			AND TRIM(NYUKABC) = TEMP_IMPORT_NO;	-- 入荷バーコード
	
	-- すでにIF.製品在庫テーブルにデータがある場合加算減算処理
	IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN

		-- 製品在庫のデータを取得
		SELECT * INTO TEMP_ZAIKO_SEIHIN FROM ZAIKO_SEIHIN WHERE 
			TRIM(HOSOSASHIZUNO) = TEMP_PACKAGE_NO 	-- 包装指図番号
			AND TRIM(ZAIKOKUBUN) = '1' 			-- 在庫区分
			AND TRIM(SEIHINKUBUN) = CONST_PRODUCTION_DIVISION	-- 製品区分（在庫管理テーブルを参照（保留））
			AND TRIM(LOCATION) = TEMP_LOCATION_CD	-- ロケーション
			AND TRIM(NYUKABC) = TEMP_IMPORT_NO;	-- 入荷バーコード

		-- 製品在庫.在庫数を取得
		IF I_IO_DIVISION = 'I' THEN		-- 入庫
			TEMP_INVENTORY_QTY := TEMP_ZAIKO_SEIHIN.ZAIKOSU + I_IO_QTY;
		ELSE					-- 出庫
			TEMP_INVENTORY_QTY := TEMP_ZAIKO_SEIHIN.ZAIKOSU - I_IO_QTY;
			
			-- 在庫が0より少なくなる場合
			IF TEMP_INVENTORY_QTY < 0 THEN
				TEMP_INVENTORY_QTY := TEMP_INVENTORY_QTY + I_IO_QTY;
				IO_RETURN_MSG := 'IF.製品在庫 ロケ:' || I_LOCATION_CD || ' 品目:' || I_ITEM_CD || ' ロット番号:' || I_LOT_NO || ' 在庫数量:' || TEMP_ZAIKO_SEIHIN.ZAIKOSU || ' 出庫数量:' || I_IO_QTY || ' 在庫数量 < 出庫数量です。';
				RETURN -1;
			END IF;
		END IF;
		
		DBMS_OUTPUT.PUT_LINE('更新開始');
		-- IF.製品在庫を検索
		UPDATE ZAIKO_SEIHIN SET ZAIKOSU = TEMP_INVENTORY_QTY WHERE 
			HOSOSASHIZUNO = TEMP_ZAIKO_SEIHIN.HOSOSASHIZUNO	-- 包装指図番号
			AND ZAIKOKUBUN = TEMP_ZAIKO_SEIHIN.ZAIKOKUBUN 	-- 在庫区分
			AND SEIHINKUBUN = TEMP_ZAIKO_SEIHIN.SEIHINKUBUN	-- 製品区分（在庫管理テーブルを参照（保留））
			AND LOCATION = TEMP_ZAIKO_SEIHIN.LOCATION	-- ロケーション
			AND NYUKABC = TEMP_ZAIKO_SEIHIN.NYUKABC;	-- 入荷バーコード
		DBMS_OUTPUT.PUT_LINE('更新完了');

	ELSIF I_IO_DIVISION = 'O' THEN	-- 出庫の場合IF.製品在庫に在庫がないのでエラー
	
		IO_RETURN_MSG := 'ロケ:' || I_LOCATION_CD || ' 品目:' || TEMP_ITEM_CD || ' ロット番号:' || I_LOT_NO || ' のデータがIF.製品在庫にありません。';
		RETURN -1;
		
	ELSIF I_IO_DIVISION = 'I' THEN	-- 入庫の場合は新規レコード追加

		TEMP_INVENTORY_QTY := I_IO_QTY;
		-- 品目マスタからデータを取得
		SELECT * INTO TEMP_ITEM_TABLE FROM ITEM WHERE ITEM.ITEM_CD = TEMP_ITEM_CD;
		
		-- 他社コード取得処理（品目マスタのデータがない場合他社コード１にホワイトスペースをセット）
		IF TEMP_ITEM_TABLE.OTHER_COMPANY_CD1 IS NULL THEN
			TEMP_OTHER_CO1 := CONST_WHITE_SPACE;
		ELSE
			TEMP_OTHER_CO1 := TEMP_ITEM_TABLE.OTHER_COMPANY_CD1;
			
			IF TEMP_OTHER_CO1 IS NULL THEN
				TEMP_OTHER_CO1 := CONST_WHITE_SPACE;
			END IF;
		END IF;
		
		-- 品目マスタの荷主からセットデータを設定
		IF TEMP_ITEM_TABLE.SHIPPER_DIVISION = 1 THEN	-- 荷主区分 = [1:花王]
			TEMP_SHIPPER_DIVISION := '2';	-- [2:花王]
		ELSE						-- 荷主区分 <> [1:花王]
			TEMP_SHIPPER_DIVISION := '1';	-- [1:自社]
		END IF;
	
		INSERT INTO ZAIKO_SEIHIN(
			SEIHINCODE,	-- 製品コード
			IOCODE,		-- IOコード
			JISHAFLAG,	-- 自社/花王 １：自社、２：花王
			SEIHINLOT,	-- 製品ロットNO 自社生産、返品
			HOSOSASHIZUNO,	-- 包装指図番号 入荷製品は　00000000000
			ZAIKOKUBUN,	-- 在庫区分 1:完成在庫、2:検査待ち、3:返品在庫、4:検品(不良)在庫
			SEIHINKUBUN,	-- 製品区分 コードは管理テーブルを参照
			LOCATION,	-- ロケーション ロケ移動中は 9999999999
			ZAIKOSU,	-- 在庫数 9
			NYUKANO,	-- 入荷NO 入荷NO＋アイテムNO
			NYUKABC,	-- 入荷バーコード PYYMMDD9999
			JUCHUNO,	-- 受注番号 ｾﾝﾀｰ移管
			JUCHUEDABAN,	-- 受注枝番 ｾﾝﾀｰ移管
			TORIHIKICODE,	-- 取引先コード 返品
			HACCHUNO,	-- 発注NO 仕入れ
			NYUKABI		-- 入荷日
		)VALUES(
			TEMP_ITEM_CD,	-- 製品コード
			TEMP_OTHER_CO1,	-- IOコード
			TEMP_SHIPPER_DIVISION,	-- 自社/花王 １：自社、２：花王
			TEMP_ALIAS_LOT_NO,	-- 製品ロットNO 自社生産、返品
			TEMP_PACKAGE_NO,	-- 包装指図番号 入荷製品は　00000000000
			'1',		-- 在庫区分 1:完成在庫、2:検査待ち、3:返品在庫、4:検品(不良)在庫
			CONST_PRODUCTION_DIVISION,		-- 製品区分 コードは管理テーブルを参照
			TEMP_LOCATION_CD,	-- ロケーション ロケ移動中は 9999999999
			TEMP_INVENTORY_QTY,	-- 在庫数 9
			CONST_WHITE_SPACE,		-- 入荷NO 入荷NO＋アイテムNO
			TEMP_IMPORT_NO,	-- 入荷バーコード PYYMMDD9999
			CONST_ORDER_NO,	-- 受注番号 ｾﾝﾀｰ移管
			CONST_ORDER_ROW,-- 受注枝番 ｾﾝﾀｰ移管
			CONST_WHITE_SPACE,		-- 取引先コード 返品
			CONST_WHITE_SPACE,		-- 発注NO 仕入れ
			TEMP_SYSDATE	-- 入荷日
		);
	
	END IF;

RETURN 0;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	IO_RETURN_CD := SQLCODE;
		IO_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		RETURN -9;
END;
/
