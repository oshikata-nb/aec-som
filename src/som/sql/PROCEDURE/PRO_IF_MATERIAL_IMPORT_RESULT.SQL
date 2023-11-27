CREATE OR REPLACE PROCEDURE AP21.PRO_IF_MATERIAL_IMPORT_RESULT
      (
             I_IMPORT_LOT_NO		IN	NVARCHAR2	-- 入荷ロット番号
            ,I_IMPORT_QTY		IN	NUMBER		-- 受入数量
            ,I_IMPORT_LOCATION		IN	NVARCHAR2	-- 入庫ロケーションコード
            ,I_ORDER_DIVISION		IN	NUMBER		-- オーダ区分
            ,I_ITEM_CD			IN	NVARCHAR2	-- 品目コード
            ,I_TANTO_CD			IN	NVARCHAR2	-- 担当コード
            ,O_ERROR_RETURN_CD		OUT	NVARCHAR2	-- エラーコードを返す
            ,O_ERROR_RETURN_MSG		OUT	NVARCHAR2	-- エラーメッセージを返す
            ,O_RETURN_CD		OUT	NVARCHAR2	-- 戻り値
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      IF_MATERIAL_IMPORT_RESULT                                                 */
/*      処理内容      :      入荷ロット番号のオーダ区分、品目コードのﾛｰﾘｰ区分毎に受入処理を行う。      */
/*      引数          :      I_IMPORT_LOT_NO 	入荷ロット番号		NVARCHAR2型                    */
/*      引数          :      I_IMPORT_QTY 	受入数量		NUMBER型	               */
/*      引数          :      I_IMPORT_LOCATION	入庫ﾛｹｰｼｮﾝ		NVARCHAR2型                    */
/*      引数          :      I_ORDER_DIVISION	オーダ区分		NVARCHAR2型                    */
/*      引数          :      I_ITEM_CD		品目コード		NVARCHAR2型                    */
/*      引数          :      I_TANTO_CD		担当者コード 		NVARCHAR2型                    */
/*      引数          :      O_RETURN_CD	エラーコード		NVARCHAR2型                    */
/*      引数          :      O_ERROR_RETURN_MSG	エラーメッセージ	NVARCHAR2型                    */
/*      引数          :      O_RETURN_CD	戻り値			NVARCHAR2型                    */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*      2009.06.23      1.00        Ohga                    タンク原料の場合IF.コード入荷ロットに書込み*/
/*      2009.09.22      1.00        Eto                     受入数量ゼロでもＯＫとする                 */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE		VARCHAR2(100);
	ERR_MSG			VARCHAR2(2048);
	LORRY_DIVISION		VARCHAR2(5);
	TEMP_VALUE		VARCHAR2(5);
	TEMP_COUNT		NUMBER;
	TEMP_RET		NUMBER;
	TEMP_IMPORT_DATE	DATE;
	TEMP_IMPORT_LOT_NO	VARCHAR2(20);
	TEMP_ITEM_CD		VARCHAR2(20);
	TEMP_MATERIAL_DIVISION	NUMBER;
	TEMP_LOT_INVENTORY	LOT_INVENTORY%ROWTYPE;	-- ロット在庫テーブル
	
BEGIN
	TEMP_IMPORT_LOT_NO := I_IMPORT_LOT_NO;
	TEMP_ITEM_CD := I_ITEM_CD;
	--OUTPUT_ERROR_LOG(TEMP_ITEM_CD,TEMP_IMPORT_LOT_NO,'aa','b');
	-- 入荷ロット番号が購買トランザクションにあるかチェック
	SELECT COUNT(*) INTO TEMP_COUNT FROM PURCHASE_SUBCONTRACT WHERE LOT_NO = I_IMPORT_LOT_NO;
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		O_ERROR_RETURN_MSG := '入荷ロット番号:' || I_IMPORT_LOT_NO || ' が購買トランザクションにありません。';
		O_RETURN_CD := -1;
		RETURN;
	END IF;
	-- 物流入庫実績数が入力されていない場合エラー ゼロはエラーとしない 2009/09/22 ETO
	--IF I_IMPORT_QTY IS NULL OR I_IMPORT_QTY = 0 THEN
	IF I_IMPORT_QTY IS NULL THEN
		O_ERROR_RETURN_MSG := '受入数量がNULLです。';
		O_RETURN_CD := -4;
		RETURN;
	END IF;
	
	-- 品目コードが品目マスタにあるかチェック
	SELECT COUNT(*) INTO TEMP_COUNT FROM ITEM,PURCHASE_ATTRIBUTE_QUEUE WHERE ITEM.ITEM_CD = I_ITEM_CD AND ITEM.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD AND ITEM.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION;
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		O_ERROR_RETURN_MSG := '品目コード:' || I_ITEM_CD || ' のﾛｰﾘｰ区分が取得できませんでした。';
		O_RETURN_CD := -3;
		RETURN;
	END IF;
	
	-- 品目マスタからﾛｰﾘｰ区分を取得
	SELECT PURCHASE_ATTRIBUTE_QUEUE.LORRY_DIVISION INTO LORRY_DIVISION FROM ITEM,PURCHASE_ATTRIBUTE_QUEUE WHERE ITEM.ITEM_CD = I_ITEM_CD AND ITEM.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD AND ITEM.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION;
	-- 外注原材料区分を取得
	SELECT MATERIAL_DIVISION INTO TEMP_MATERIAL_DIVISION FROM (SELECT * FROM PURCHASE_SUBCONTRACT WHERE LOT_NO = I_IMPORT_LOT_NO AND ROWNUM = 1) WHERE LOT_NO = I_IMPORT_LOT_NO AND ROWNUM = 1;
	-- タンク原料の場合（ﾛｰﾘｰの場合）
--	IF LORRY_DIVISION = '2' THEN
--		SELECT COUNT(*) INTO TEMP_COUNT FROM CODE_NYUKALOT WHERE TRIM(NYUKALOT) = TEMP_IMPORT_LOT_NO;
--		
--		IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN
--			O_ERROR_RETURN_MSG := '指定入荷ロット番号がすでに計装IF.入荷ロット原材料コード対応に存在します。入荷ロット番号:' || TEMP_IMPORT_LOT_NO;
--			O_RETURN_CD := -1;
--			RETURN;
--		END IF;
--		INSERT INTO CODE_NYUKALOT(NYUKALOT,GENZAICODE)VALUES(TEMP_IMPORT_LOT_NO,TEMP_ITEM_CD);
--	END IF;
	-- ﾛｰﾘｰ区分=[1:ﾛｰﾘｰ以外]　かつ　オーダ区分=[1:原材料]の場合
	IF LORRY_DIVISION ='1' AND I_ORDER_DIVISION = '1' AND TEMP_MATERIAL_DIVISION <> 1 THEN
		
		DBMS_OUTPUT.PUT_LINE('原材料ラベル処理');
		
		-- 計装ifテーブル原材料ラベル発行実績を第1引数の入荷ロット番号で検索
		SELECT COUNT(NYUKALOT) INTO TEMP_COUNT FROM JISSEKI_GENZAIRYO_LABELHAKKO WHERE NYUKALOT = I_IMPORT_LOT_NO;
		
		-- 第1引数の入荷ロット番号が計装ifテーブル原材料ラベル発行実績テーブルに無かった場合
		IF TEMP_COUNT IS NULL AND TEMP_COUNT = 0 THEN
			O_ERROR_RETURN_MSG := 'IF.原材料ラベル発行実績に入荷ロット番号:' || I_IMPORT_LOT_NO || ' のデータがありません。';
			O_RETURN_CD := -1;
			RETURN;
		ELSE
			
			-- 計装ifテーブル原材料ラベル発行実績のデータのうち第1引数の入荷ロット番号で指定されているデータを削除
			DELETE FROM JISSEKI_GENZAIRYO_LABELHAKKO WHERE NYUKALOT = TEMP_IMPORT_LOT_NO;
			
		END IF;
		-- 計装ifテーブル原材料入出庫実績を第1引数の入荷ロット番号で検索
		SELECT COUNT(NYUKALOT) INTO TEMP_COUNT FROM JISSEKI_GENZAIRYO WHERE NYUKALOT = TEMP_IMPORT_LOT_NO;
		
		-- 第1引数の入荷ロット番号が計装ifテーブル原材料入出庫実績テーブルに無かった場合
		IF TEMP_COUNT IS NULL AND TEMP_COUNT = 0 THEN
			O_ERROR_RETURN_MSG := 'IF.原材料入出庫実績に入荷ロット番号:' || I_IMPORT_LOT_NO || ' のデータがありません。';
			O_RETURN_CD := -1;
			RETURN;
		ELSE
			-- 上位処理済みフラグに[1]セット
			UPDATE JISSEKI_GENZAIRYO SET SUMI = '1' WHERE NYUKALOT = TEMP_IMPORT_LOT_NO;
			
		END IF;
		
	-- オーダ区分 = [3:外注製品(非直送)] OR オーダ区分 = [6:仕入在庫品]
	ELSIF I_ORDER_DIVISION = '3' OR I_ORDER_DIVISION = '6' THEN
		
		DBMS_OUTPUT.PUT_LINE('製品入荷処理');
		
		-- 計装ifテーブル製品入出庫実績を第1引数の入荷ロット番号で検索
		SELECT COUNT(*) INTO TEMP_COUNT FROM JISSEKI_SEIHIN WHERE NYUKABC = I_IMPORT_LOT_NO;
		
		-- 第1引数の入荷ロット番号が計装ifテーブル製品入出庫実績テーブルに無かった場合
		IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
			
			O_ERROR_RETURN_MSG := 'IF.製品入出庫実績に入荷ロット番号:' || I_IMPORT_LOT_NO || ' のデータがありません。';
			O_RETURN_CD := -1;
			RETURN;
		ELSE
			
			-- 計装ifテーブル製品入出庫実績のうち第1引数の入荷ロット番号が一致するデータの上位処理済みを[1:処理済み]とする
			UPDATE JISSEKI_SEIHIN SET SUMI = '1' WHERE NYUKABC = TEMP_IMPORT_LOT_NO;
			
		END IF;
		-- 計装ifテーブル製品入庫データテーブルを第1引数の入荷ロット番号で検索
		SELECT COUNT(NYUKABC) INTO TEMP_COUNT FROM DATA_SEIHIN_NYUKA WHERE NYUKABC = I_IMPORT_LOT_NO;
		
		-- 第1引数の入荷ロット番号が計装ifテーブル製品入庫データテーブルに無かった場合
		IF TEMP_COUNT IS NULL AND TEMP_COUNT = 0 THEN
			O_ERROR_RETURN_MSG := 'IF.製品入庫データに入荷ロット番号:' || I_IMPORT_LOT_NO || ' のデータがありません。';
			O_RETURN_CD := -1;
			RETURN;
		ELSE
			-- 日付の最大値を取得
			SELECT MAX(JIKOKU) INTO TEMP_IMPORT_DATE FROM JISSEKI_SEIHIN WHERE NYUKABC = I_IMPORT_LOT_NO;
			DBMS_OUTPUT.PUT_LINE(TO_CHAR(TEMP_IMPORT_DATE,'YYYY/MM/DD HH:MM:SS'));
			
			-- 計装ifテーブル製品入庫データのうち第1引数の入荷ロット番号で指定されている入荷状況を[2:完了]とし、納入予定日に製品入出庫実績.最終入庫日をセットする
			UPDATE DATA_SEIHIN_NYUKA SET NYUKOJOKYO = '2',NONYUYOTEIBI = TEMP_IMPORT_DATE WHERE NYUKABC = TEMP_IMPORT_LOT_NO;
			
		END IF;
/* ロット在庫テーブルのトリガーで処理する為不要 2009/05/28
		-- ロット在庫を検索
		SELECT COUNT(*) INTO TEMP_COUNT FROM LOT_INVENTORY WHERE LOCATION_CD = I_IMPORT_LOCATION AND ITEM_CD = I_ITEM_CD AND LOT_NO = TEMP_IMPORT_LOT_NO;
		-- ロット在庫にデータが無い場合
		IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
			O_ERROR_RETURN_MSG := 'ロット在庫テーブルに入荷ロット番号:' || TEMP_IMPORT_LOT_NO || ' 品目:' || I_ITEM_CD || ' ロケーション:' || I_IMPORT_LOCATION || ' のデータがありません。';
			O_RETURN_CD := -2;
			RETURN;
		END IF;
		
		-- ロット在庫テーブルのデータを保持
		SELECT * INTO TEMP_LOT_INVENTORY FROM LOT_INVENTORY WHERE LOCATION_CD = I_IMPORT_LOCATION AND ITEM_CD = I_ITEM_CD AND LOT_NO = TEMP_IMPORT_LOT_NO;
		-- 計装IFの在庫登録
		TEMP_RET := FUN_IF_IINVENTORY_UPDATE(I_IMPORT_LOCATION,I_ITEM_CD,I_IMPORT_LOT_NO,TEMP_LOT_INVENTORY.ALIAS_LOT_NO,'I',I_IMPORT_QTY,O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG);
		
		-- IF.製品在庫更新処理失敗
		IF TEMP_RET <> 0 THEN
			
			O_ERROR_RETURN_MSG := 'IF.製品在庫更新失敗 ロケ:' || I_IMPORT_LOCATION || ' 品目:' || I_ITEM_CD || ' ロット:' || I_IMPORT_LOT_NO || ' 入荷数量:' || I_IMPORT_QTY || ' エラーコード:' || O_ERROR_RETURN_CD || ' エラーメッセージ:' || O_ERROR_RETURN_MSG;
			O_RETURN_CD := -2;
			RETURN;
		END IF;
*/		
	END IF;
	O_RETURN_CD := 0;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    	O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		
		O_RETURN_CD := -9;
END;
/
