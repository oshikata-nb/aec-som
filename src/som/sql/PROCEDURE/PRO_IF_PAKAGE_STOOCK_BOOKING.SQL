CREATE OR REPLACE PROCEDURE AP21.PRO_IF_PAKAGE_STOOCK_BOOKING
      (
             I_PAKAGE_LOT_NO		IN	NVARCHAR2	-- 包装指図番号
            ,I_IMPORT_QTY		IN	NUMBER		-- 物流入庫実績数
            ,I_TANTO_CD			IN	NVARCHAR2	-- 担当者コード
            ,O_ERROR_RETURN_CD		OUT	NVARCHAR2	-- エラーコードを返す
            ,O_ERROR_RETURN_MSG		OUT	NVARCHAR2	-- エラーメッセージを返す
            ,O_RETURN_CD		OUT	NVARCHAR2	-- 戻り値
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_PAKAGE_STOOCK_BOOKING                                              */
/*      処理内容      :      包装　検査待ち在庫計上						       */
/*      引数          :      I_PAKAGE_LOT_NO 	包装指図番号		NVARCHAR2型                    */
/*      引数          :      I_IMPORT_QTY 	物流入庫実績数		NUMBER型                       */
/*      引数          :      I_TANTO_CD		担当者コード 		NVARCHAR2型                    */
/*      引数          :      O_ERROR_RETURN_CD 	エラーコード		NVARCHAR2型                    */
/*      引数          :      O_ERROR_RETURN_MSG	エラーメッセージ	NVARCHAR2型                    */
/*      引数          :      O_RETURN_CD	戻り値			NVARCHAR2型                    */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*      2009.09.11      1.01        Eto                     時刻比較を＋－１秒の範囲に変更。           */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE		VARCHAR2(100);
	ERR_MSG			VARCHAR2(2048);
	TEMP_NUMBER		NUMBER;
	TEMP_IMPORT_QTY		NUMBER;				-- 入庫数
	TEMP_IMPORT_QTY_SUM	NUMBER;				-- 入庫数積算
	TEMP_COUNT		NUMBER;				-- 検索結果レコード数保持用
	TEMP_RET		NUMBER;				-- IF.製品在庫更新処理戻り値
	TEMP_IMPORT_DATE	DATE;				-- 最終入庫日
	TEMP_LOCATION_CD	VARCHAR2(20);			-- 製品入出庫実績.ロケーション
	TEMP_LINE_TYPE		VARCHAR2(20);			-- 品目形式
	TEMP_RESULT_QTY		VARCHAR2(20);			-- 実績数量
	TEMP_ALIAS_LOT_NO	VARCHAR2(20);			-- 実績数量
	TEMP_DIR_FOMULA		DIRECTION_FORMULA%ROWTYPE;	-- 製造配合テーブル
	TEMP_DATE1              DATE;
	TEMP_DATE2              DATE;
	
	CONST_NYUKABC		VARCHAR2(20) := '00000000000';	-- 入荷バーコード
	CONST_IMPORT_DIVISION	VARCHAR2(1) := '1';		-- 入出庫区分[1:入庫]
	CONST_UPPER_NO_PROC	VARCHAR2(1) := '0';		-- 上位処理済み[0:上位未処理]
	CONST_UPPER_PROC_END	VARCHAR2(1) := '1';		-- 上位処理済み[1:上位処理済]
	CONST_PAKAGE_NO_PROC	VARCHAR2(1) := '0';		-- 包装状況[0:未処理]
	CONST_PAKAGE_PROC_END	VARCHAR2(1) := '2';		-- 包装状況[2:包装完了]
	CONST_DIVISION_PAKAGE	VARCHAR2(1) := '2';		-- 製造指図ヘッダー.指図区分[2:充填・包装指図,3:詰替・貼替指図]	
	CONST_DIVISION_CHANGE	VARCHAR2(1) := '3';		-- 製造指図ヘッダー.指図区分[2:充填・包装指図,3:詰替・貼替指図]	
	CONST_LINE_TYPE_PRO	VARCHAR2(1) := '1';		-- 製造指図フォーミュラ.品目タイプ[1:製品]	
	-- 該当する製品入出実績のレコードを入出庫時刻の昇順に処理
    	CURSOR LOOP_JISSEKI_SEIHIN 
		IS SELECT SEIHINCODE,LOCATION,JIKOKU,SUURYOU,HOSOSASHIZUNO,NYUKABC,JIKOKU AS IMPORT_DATE 
		FROM JISSEKI_SEIHIN 
		WHERE JISSEKI_SEIHIN.HOSOSASHIZUNO = I_PAKAGE_LOT_NO AND JISSEKI_SEIHIN.NYUUSYUKKO = CONST_IMPORT_DIVISION AND SUMI = CONST_UPPER_NO_PROC ORDER BY JISSEKI_SEIHIN.JIKOKU ASC;
	-- 該当する包装計画のレコードを検索
    	CURSOR LOOP_KEIKAKU_HOSO 
		IS SELECT HOSOSASHIZUNO 
		FROM KEIKAKU_HOSO 
		WHERE KEIKAKU_HOSO.HOSOSASHIZUNO = I_PAKAGE_LOT_NO AND KEIKAKU_HOSO.HOSOJOKYO = CONST_PAKAGE_NO_PROC;
BEGIN
	
	-- 包装指図番号が入力されていない場合エラー
	IF I_PAKAGE_LOT_NO IS NULL THEN
		O_ERROR_RETURN_MSG := '第1引数の包装指図番号がNULLです。包装指図番号:' || I_PAKAGE_LOT_NO;
		O_RETURN_CD := -1;
		RETURN;
	END IF;
	-- 物流入庫実績数が入力されていない場合エラー
	IF I_IMPORT_QTY IS NULL OR I_IMPORT_QTY = 0 THEN
		O_ERROR_RETURN_MSG := '第1引数の物流入庫実績数がNULLまたは[0]です。物流入庫実績数:' || I_IMPORT_QTY;
		O_RETURN_CD := -1;
		RETURN;
	END IF;
	-- 製造指図ヘッダー.指図番号 = 第一引数[包装指図番号] かつ 製造指図ヘッダー.指図区分 = [2:充填・包装指図]かつ仕上げのデータ（LINE_NO=1001)があるか確認
	SELECT COUNT(*) INTO TEMP_COUNT FROM DIRECTION_HEADER,DIRECTION_FORMULA 
		WHERE DIRECTION_HEADER.DIRECTION_NO = DIRECTION_FORMULA.DIRECTION_NO AND
		DIRECTION_HEADER.DIRECTION_DIVISION = DIRECTION_FORMULA.DIRECTION_DIVISION AND
		DIRECTION_HEADER.DIRECTION_NO = I_PAKAGE_LOT_NO AND 
		(DIRECTION_HEADER.DIRECTION_DIVISION = CONST_DIVISION_PAKAGE OR DIRECTION_HEADER.DIRECTION_DIVISION = CONST_DIVISION_CHANGE) AND 
		DIRECTION_FORMULA.LINE_NO = 1001 AND DIRECTION_FORMULA.STEP_NO = 1;
	-- 第1引数の包装指図番号が製造指図ヘッダーまたは製造指図フォーミュラに無かった場合
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		O_ERROR_RETURN_MSG := '第1引数の包装指図番号:' || I_PAKAGE_LOT_NO || ' 製造指図ヘッダーまたは製造指図フォーミュラにないか、製造指図フォーミュラに仕上げのデータがありません';
		O_RETURN_CD := -1;
		RETURN;
	ELSE
		-- 製造指図ヘッダー.指図番号 = 第一引数[包装指図番号] かつ 製造指図ヘッダー.指図区分 = [2:充填・包装指図]かつ仕上げのデータ（LINE_NO=1001)があるか確認
		SELECT * INTO TEMP_DIR_FOMULA FROM DIRECTION_FORMULA 
			WHERE 
			DIRECTION_FORMULA.DIRECTION_NO = I_PAKAGE_LOT_NO AND 
			(DIRECTION_FORMULA.DIRECTION_DIVISION = CONST_DIVISION_PAKAGE OR DIRECTION_FORMULA.DIRECTION_DIVISION = CONST_DIVISION_CHANGE) AND 
			DIRECTION_FORMULA.LINE_NO = 1001 AND DIRECTION_FORMULA.STEP_NO = 1;
		-- 品目タイプを保持
		SELECT LINE_TYPE INTO TEMP_LINE_TYPE FROM DIRECTION_FORMULA WHERE (DIRECTION_FORMULA.DIRECTION_DIVISION = CONST_DIVISION_PAKAGE OR DIRECTION_FORMULA.DIRECTION_DIVISION = CONST_DIVISION_CHANGE) AND 
			DIRECTION_FORMULA.DIRECTION_NO = I_PAKAGE_LOT_NO AND DIRECTION_FORMULA.STEP_NO = 1 AND DIRECTION_FORMULA.LINE_NO = 1001;
		
		-- 製造指図ヘッダの実績数量を取得
		SELECT DIRECTION_HEADER.RESULT_QTY INTO TEMP_RESULT_QTY FROM DIRECTION_HEADER WHERE (DIRECTION_HEADER.DIRECTION_DIVISION = CONST_DIVISION_PAKAGE OR DIRECTION_HEADER.DIRECTION_DIVISION = CONST_DIVISION_CHANGE) AND DIRECTION_HEADER.DIRECTION_NO = I_PAKAGE_LOT_NO;
	END IF;
	-- 包装計画.包装指図番号 = 第一引数[包装指図番号] かつ 包装計画.包装状況 = [0:未処理] データを検索
	SELECT COUNT(*) INTO TEMP_COUNT FROM KEIKAKU_HOSO WHERE HOSOSASHIZUNO = I_PAKAGE_LOT_NO AND HOSOJOKYO = CONST_PAKAGE_NO_PROC;
	-- 第1引数の包装指図番号が計装IFテーブル包装計画テーブルに無かった場合
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		O_ERROR_RETURN_MSG := '第1引数の包装指図番号:' || I_PAKAGE_LOT_NO || ' が計装IFテーブル包装計画テーブルにありません';
		O_RETURN_CD := -1;
		RETURN;
	END IF;
	-- 製品入出実績.包装指図番号 = 第一引数[包装指図番号] かつ 製品入出実績.入出庫フラグ = [1:出庫]　かつ　上位処理済みではないデータを検索
	SELECT COUNT(*) INTO TEMP_COUNT FROM JISSEKI_SEIHIN WHERE HOSOSASHIZUNO = I_PAKAGE_LOT_NO AND NYUUSYUKKO = CONST_IMPORT_DIVISION AND SUMI = CONST_UPPER_NO_PROC;
	-- 第1引数の包装指図番号が計装IFテーブル製品入出実績テーブルに無かった場合
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		O_ERROR_RETURN_MSG := '第1引数の包装指図番号:' || I_PAKAGE_LOT_NO ||  ' が計装IFテーブル製品入出実績テーブルにありません';
		O_RETURN_CD := -1;
		RETURN;
	END IF;
	-- 製品入出実績.包装指図番号 = 第一引数[包装指図番号] かつ 製品入出実績.入出庫フラグ = [1:出庫]　かつ　上位処理済みではないデータを入出庫数で集計
	SELECT SUM(SUURYOU) INTO TEMP_NUMBER FROM JISSEKI_SEIHIN WHERE JISSEKI_SEIHIN.HOSOSASHIZUNO = I_PAKAGE_LOT_NO AND JISSEKI_SEIHIN.NYUUSYUKKO = CONST_IMPORT_DIVISION AND SUMI = CONST_UPPER_NO_PROC GROUP BY JISSEKI_SEIHIN.HOSOSASHIZUNO;
	DBMS_OUTPUT.PUT_LINE(TEMP_IMPORT_QTY_SUM);
/*	計装IFから上がってきた値がおかしい場合があるのでチェックを外す 2009/5/28
	-- 該当する製品入出実績の入出庫数の合計が第二引数[物流入庫実績数]より少なかった場合
	IF TEMP_NUMBER < I_IMPORT_QTY THEN
		O_ERROR_RETURN_MSG := '製品入出実績.入出庫数:' || TEMP_NUMBER || ' 第二引数[物流入庫実績数]:' || I_IMPORT_QTY || ' 指定物流入庫実績数が異常です。';
		O_RETURN_CD := -2;
		RETURN;
	END IF;
*/	
	-- 入庫数量累計を初期化
	TEMP_IMPORT_QTY_SUM :=0;
	
	-- 製品入出実績を入出庫時刻の古い順に処理
   	FOR PROC_JISSEKI_SEIHIN IN LOOP_JISSEKI_SEIHIN LOOP
		
		-- 引数[入荷数量] > 製品入出実績.入出庫数の累計
		IF I_IMPORT_QTY > TEMP_IMPORT_QTY_SUM THEN
		
			-- 引数[入荷数量] 
			IF I_IMPORT_QTY >= TEMP_IMPORT_QTY_SUM + PROC_JISSEKI_SEIHIN.SUURYOU THEN
				TEMP_IMPORT_QTY := PROC_JISSEKI_SEIHIN.SUURYOU;			-- 入庫数量
			ELSE
				TEMP_IMPORT_QTY := I_IMPORT_QTY - TEMP_IMPORT_QTY_SUM;		-- 入庫数量
			END IF;
			TEMP_IMPORT_QTY_SUM := TEMP_IMPORT_QTY_SUM + TEMP_IMPORT_QTY;	-- 入庫数量積算
			DBMS_OUTPUT.PUT_LINE('入庫数量積算:' || TEMP_IMPORT_QTY_SUM || ' 入庫数量:' || TEMP_IMPORT_QTY);
	
/* ロット在庫のトリガーで作業を行うことになったのでコメントアウト 2009/5/28
			-- IF.製品在庫更新処理
			TEMP_RET := FUN_IF_IINVENTORY_UPDATE(TRIM(PROC_JISSEKI_SEIHIN.LOCATION),TRIM(PROC_JISSEKI_SEIHIN.SEIHINCODE),I_PAKAGE_LOT_NO,TEMP_DIR_FOMULA.LOT_NO,'I',PROC_JISSEKI_SEIHIN.SUURYOU,O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG);
			
			-- IF.製品在庫更新処理失敗
			IF TEMP_RET <> 0 THEN
				
				O_RETURN_CD := -3;
				RETURN;
			END IF;
*/
			-- 処理レコードの上位処理済みフラグを[1:上位処理済み]とする  2009/09/11 JIKOKU比較を範囲に変更。
			TEMP_DATE1 := PROC_JISSEKI_SEIHIN.JIKOKU - 1/(24*60*60);
			TEMP_DATE2 := PROC_JISSEKI_SEIHIN.JIKOKU + 1/(24*60*60);
			UPDATE JISSEKI_SEIHIN SET SUMI = CONST_UPPER_PROC_END WHERE SEIHINCODE = PROC_JISSEKI_SEIHIN.SEIHINCODE AND LOCATION = PROC_JISSEKI_SEIHIN.LOCATION AND 
			(JIKOKU >= TEMP_DATE1 AND JIKOKU <= TEMP_DATE2 );
			
			-- 最終入庫日を保持
			TEMP_IMPORT_DATE := PROC_JISSEKI_SEIHIN.IMPORT_DATE;
			DBMS_OUTPUT.PUT_LINE(TO_CHAR(TEMP_IMPORT_DATE,'YYYY/MM/DD HH:MM:SS'));
			
			-- ロケーションコードを保持
			TEMP_LOCATION_CD := TRIM(PROC_JISSEKI_SEIHIN.LOCATION);
		ELSE
			EXIT;
		END IF;
	END LOOP;
	-- 該当する包装指図番号の包装計画.包装状況を[2:包装完了]とし包装日に最終入庫時刻を入力する
   	FOR PROC_KEIKAKU_HOSO IN LOOP_KEIKAKU_HOSO LOOP
		DBMS_OUTPUT.PUT_LINE(TO_CHAR(TEMP_IMPORT_DATE,'YYYY/MM/DD HH:MM:SS'));
		DBMS_OUTPUT.PUT_LINE(I_PAKAGE_LOT_NO);
		UPDATE KEIKAKU_HOSO SET HOSOJOKYO = '2',HOSOBI = TEMP_IMPORT_DATE WHERE HOSOSASHIZUNO = PROC_KEIKAKU_HOSO.HOSOSASHIZUNO;
	END LOOP;
/*	AP21での処理となったので削除	
	-- 製造指図フォーミュラ.品目タイプが製品の場合
	IF TEMP_LINE_TYPE = CONST_LINE_TYPE_PRO THEN  
		DBMS_OUTPUT.PUT_LINE('ロケーションコード:' || TEMP_LOCATION_CD);
		-- ロケーションを包装実績仕上のロケーションへセットする（製造指図フォーミュラのロケーション更新)
		UPDATE DIRECTION_FORMULA 
			SET DIRECTION_FORMULA.LOCATION_CD = TEMP_LOCATION_CD,
				DIRECTION_FORMULA.RESULT_QTY = TEMP_RESULT_QTY,
				DIRECTION_FORMULA.UPDATE_DATE = SYSDATE,
				DIRECTION_FORMULA.UPDATOR_CD = I_TANTO_CD
			WHERE DIRECTION_FORMULA.DIRECTION_NO = I_PAKAGE_LOT_NO AND DIRECTION_FORMULA.DIRECTION_DIVISION IN CONST_DIVISION_PAKAGE AND DIRECTION_FORMULA.STEP_NO = 1 AND DIRECTION_FORMULA.LINE_NO = 1001;
	END IF;
*/
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
