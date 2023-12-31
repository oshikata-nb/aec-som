CREATE OR REPLACE PROCEDURE AP21.PRO_RDIRECTION_FILE_IMPORT(
	I_TEMP_NO			IN NVARCHAR2,			-- 一時取込番号
	I_TANTO_CD			IN NVARCHAR2,			-- 入力担当者コード
	O_ERROR_RETURN_CD	IN OUT	NVARCHAR2,		-- エラーコードを返す
	O_ERROR_RETURN_MSG	IN OUT	NVARCHAR2,		-- エラーメッセージを返す
	O_RETURN_CD			IN OUT	NVARCHAR2 
)

IS
/*------------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                  */
/*      名称          :      PRO_RDIRECTION_FILE_IMPORT                                                 */
/*      処理内容      :      ファイル一括機能よりアップロードした製造実績情報を一時取込テーブルから検索 */
/*                           し、製造指図ヘッダー、製造指図プロシージャ、製造指図フォーミュラを更新する */
/*      引数          :      IN    一時取込番号                                                         */
/*                           IN    入力担当者コード                                                     */
/*                           OUT   エラーコード                                                         */
/*                           OUT   エラーメッセージ                                                     */
/*                           OUT   リターンコード                                                       */
/*      VERNO.        :      1.00                                                                       */
/*(history)                                                                                             */
/*      date            ver         name                    comments                                    */
/*      ----------      ------      ------------------      --------------------------------------------*/
/*      2022.08.17      1.00        Uehara                    新規作成                                  */
/*------------------------------------------------------------------------------------------------------*/
	TEMP_ERR_MSG 		VARCHAR2(2048);
	TEMP_COUNT			NUMBER;
	TEMP_CHAR			VARCHAR2(100);
	TEMP_RESULT_FORMULA_RETURN	VARCHAR2(255);
	CONST_COMP			VARCHAR2(20) := 'COMPLETE';	-- 在庫プロシージャ完了戻り値
	R_FORMULA			DIRECTION_FORMULA%ROWTYPE;	-- フォーミュラデータ保持用
	TEMP_STOCK_DIV		NUMBER;						-- 在庫管理区分
	TEMP_LOT_DIV		NUMBER;						-- ロット管理区分
	-- 一時取込番号で製造指図ヘッダー一時取込を検索
    	CURSOR TEMP_HEAD IS SELECT * FROM DIRECTION_HEADER_TEMP WHERE TEMP_NO = I_TEMP_NO ORDER BY ROW_NUMBER;
	
	-- 一時取込番号で製造指図プロシージャ一時取込を検索
    	CURSOR TEMP_PROCEDURE IS SELECT * FROM DIRECTION_PROCEDURE_TEMP WHERE TEMP_NO = I_TEMP_NO ORDER BY ROW_NUMBER;
	
	-- 一時取込番号で製造指図フォーミュラ一時取込を検索
    	CURSOR TEMP_FORMULA IS SELECT * FROM DIRECTION_FORMULA_TEMP WHERE TEMP_NO = I_TEMP_NO ORDER BY ROW_NUMBER;

BEGIN
	DBMS_OUTPUT.ENABLE(NULL);
	O_RETURN_CD := '0';
	
	FOR PROC_TEMP_HEAD IN TEMP_HEAD LOOP
		DBMS_OUTPUT.PUT_LINE('製造指図ヘッダ更新');
		DBMS_OUTPUT.PUT_LINE('指図区分:' || PROC_TEMP_HEAD.DIRECTION_DIVISION || ' 指図番号:' || PROC_TEMP_HEAD.DIRECTION_NO );
		TEMP_ERR_MSG:= '製造ヘッダ[' ||  PROC_TEMP_HEAD.ROW_NUMBER || ']行目';
		
		-- 製造指図ヘッダの更新
		UPDATE DIRECTION_HEADER SET 
			DIRECTION_HEADER.LOT_NO = PROC_TEMP_HEAD.LOT_NO,					-- ロット番号
			DIRECTION_HEADER.RESULT_SDATE = PROC_TEMP_HEAD.RESULT_SDATE,		-- 実績開始日時
			DIRECTION_HEADER.RESULT_EDATE = PROC_TEMP_HEAD.RESULT_EDATE,		-- 実績終了日時
			DIRECTION_HEADER.STERIT_SDATE = PROC_TEMP_HEAD.STERIT_SDATE,		-- 滅菌作業開始日時
			DIRECTION_HEADER.STERIT_EDATE = PROC_TEMP_HEAD.STERIT_EDATE,		-- 滅菌作業終了日時
			DIRECTION_HEADER.REMARK = PROC_TEMP_HEAD.REMARK,		-- 摘要
			DIRECTION_HEADER.NOTES = PROC_TEMP_HEAD.NOTES,		-- 注釈
			DIRECTION_HEADER.SEIZOTANTOCODE = TRIM(PROC_TEMP_HEAD.SEIZOTANTOCODE),		-- 製造担当者
			DIRECTION_HEADER.SENJOTANTOCODE = TRIM(PROC_TEMP_HEAD.SENJOTANTOCODE),		-- 調合タンク洗浄担当者
			DIRECTION_HEADER.MEKKINTANTOCODE = TRIM(PROC_TEMP_HEAD.MEKKINTANTOCODE),		-- 調合タンク滅菌作業担当者
			DIRECTION_HEADER.UPDATE_DATE = SYSDATE,		-- 更新日付
			DIRECTION_HEADER.UPDATOR_CD = I_TANTO_CD	-- 更新者コード
		WHERE DIRECTION_HEADER.DIRECTION_NO = PROC_TEMP_HEAD.DIRECTION_NO AND DIRECTION_HEADER.DIRECTION_DIVISION = PROC_TEMP_HEAD.DIRECTION_DIVISION;
		
		-- 受払履歴の更新
		UPDATE INOUT_RECORD SET
			INOUT_RECORD.INOUT_DATE = PROC_TEMP_HEAD.RESULT_EDATE,
			INOUT_RECORD.UPDATE_DATE = SYSDATE,		-- 更新日付
			INOUT_RECORD.UPDATOR_CD = I_TANTO_CD	-- 更新者コード
		WHERE INOUT_RECORD.INOUT_DIVISION = 1 AND INOUT_RECORD.ODER_NO = PROC_TEMP_HEAD.DIRECTION_NO;
		
		UPDATE INOUT_RECORD SET
			INOUT_RECORD.INOUT_DATE = PROC_TEMP_HEAD.RESULT_SDATE,
			INOUT_RECORD.UPDATE_DATE = SYSDATE,		-- 更新日付
			INOUT_RECORD.UPDATOR_CD = I_TANTO_CD	-- 更新者コード
		WHERE INOUT_RECORD.INOUT_DIVISION = 2 AND INOUT_RECORD.ODER_NO = PROC_TEMP_HEAD.DIRECTION_NO;

	END LOOP;
	
	FOR PROC_TEMP_PROCEDURE IN TEMP_PROCEDURE LOOP
		DBMS_OUTPUT.PUT_LINE('製造指図プロシージャ更新');
		DBMS_OUTPUT.PUT_LINE('指図区分:' || PROC_TEMP_PROCEDURE.DIRECTION_DIVISION || ' 指図番号:' || PROC_TEMP_PROCEDURE.DIRECTION_NO || ' ステップNO.:' || PROC_TEMP_PROCEDURE.STEP_NO );
		TEMP_ERR_MSG:= '製造工程[' ||  PROC_TEMP_PROCEDURE.ROW_NUMBER || ']行目';
		
		UPDATE DIRECTION_PROCEDURE SET 
			DIRECTION_PROCEDURE.CONDITION = PROC_TEMP_PROCEDURE.CONDITION,		-- 工程条件:フリー入力
			DIRECTION_PROCEDURE.REMARK = PROC_TEMP_PROCEDURE.REMARK,			-- 備考
			DIRECTION_PROCEDURE.NOTES = PROC_TEMP_PROCEDURE.NOTES,				-- 注釈
			DIRECTION_PROCEDURE.RESULT_SDATE = PROC_TEMP_PROCEDURE.RESULT_SDATE,		-- 開始実績日時
			DIRECTION_PROCEDURE.RESULT_EDATE = PROC_TEMP_PROCEDURE.RESULT_EDATE,		-- 終了実績日時
			DIRECTION_PROCEDURE.CONDITION_TEMP = PROC_TEMP_PROCEDURE.CONDITION_TEMP,	-- 温度
			DIRECTION_PROCEDURE.CONDITION_TIME = PROC_TEMP_PROCEDURE.CONDITION_TIME,	-- 時間
			DIRECTION_PROCEDURE.STIR_SPEED1 = PROC_TEMP_PROCEDURE.STIR_SPEED1,		-- 攪拌速度1
			DIRECTION_PROCEDURE.STIR_SPEED2 = PROC_TEMP_PROCEDURE.STIR_SPEED2,		-- 攪拌速度2
			DIRECTION_PROCEDURE.WATER_WEIGHT = PROC_TEMP_PROCEDURE.WATER_WEIGHT,	-- 洗浄水絶対重量
			DIRECTION_PROCEDURE.MAIN_STREAM = PROC_TEMP_PROCEDURE.MAIN_STREAM,		-- 本流/予備溶解
			DIRECTION_PROCEDURE.PH = PROC_TEMP_PROCEDURE.PH,						-- ｐＨ
			DIRECTION_PROCEDURE.RESULT_CONDITION_TEMP = PROC_TEMP_PROCEDURE.RESULT_CONDITION_TEMP,	-- 実績温度
			DIRECTION_PROCEDURE.RESULT_STIR_SPEED = PROC_TEMP_PROCEDURE.RESULT_STIR_SPEED,			-- 実績攪拌速度
			DIRECTION_PROCEDURE.RESULT_PH = PROC_TEMP_PROCEDURE.RESULT_PH,							-- 実績ｐＨ
			DIRECTION_PROCEDURE.UPDATE_DATE = SYSDATE,							-- 更新日付
			DIRECTION_PROCEDURE.UPDATOR_CD = I_TANTO_CD							-- 更新者コード
		WHERE DIRECTION_PROCEDURE.DIRECTION_NO = PROC_TEMP_PROCEDURE.DIRECTION_NO AND
			DIRECTION_PROCEDURE.DIRECTION_DIVISION = PROC_TEMP_PROCEDURE.DIRECTION_DIVISION AND
			DIRECTION_PROCEDURE.STEP_NO = PROC_TEMP_PROCEDURE.STEP_NO;

	END LOOP;

	FOR PROC_TEMP_FORMULA IN TEMP_FORMULA LOOP
		DBMS_OUTPUT.PUT_LINE('製造指図フォーミュラ更新');
		DBMS_OUTPUT.PUT_LINE('指図区分:' || PROC_TEMP_FORMULA.DIRECTION_DIVISION || ' 指図番号:' || PROC_TEMP_FORMULA.DIRECTION_NO || ' ステップNO.:' || PROC_TEMP_FORMULA.STEP_NO || ' 行NO.:' || PROC_TEMP_FORMULA.LINE_NO  );
		TEMP_ERR_MSG:= '製造配合・仕上[' ||  PROC_TEMP_FORMULA.ROW_NUMBER || ']行目';
		
		-- フォーミュラレコードを保持
		SELECT COUNT(*) INTO TEMP_CHAR FROM DIRECTION_FORMULA WHERE DIRECTION_DIVISION = PROC_TEMP_FORMULA.DIRECTION_DIVISION AND DIRECTION_NO = PROC_TEMP_FORMULA.DIRECTION_NO AND STEP_NO = PROC_TEMP_FORMULA.STEP_NO AND LINE_NO = PROC_TEMP_FORMULA.LINE_NO;
		IF TEMP_CHAR IS NOT NULL AND TEMP_CHAR <> 0 THEN -- 製造指図フォーミュラにデータがある場合
			SELECT * INTO R_FORMULA FROM DIRECTION_FORMULA WHERE DIRECTION_DIVISION = PROC_TEMP_FORMULA.DIRECTION_DIVISION AND DIRECTION_NO = PROC_TEMP_FORMULA.DIRECTION_NO AND STEP_NO = PROC_TEMP_FORMULA.STEP_NO AND LINE_NO = PROC_TEMP_FORMULA.LINE_NO;
		
			-- 在庫の取消
			IF R_FORMULA.LINE_NO >= 1001 THEN
				-- 仕上品の場合
				ZAIKOUKEHARAI.RESULT_DIRECTION(2, R_FORMULA.DIRECTION_NO, R_FORMULA.DIRECTION_DIVISION, I_TANTO_CD, TEMP_RESULT_FORMULA_RETURN);
				IF TEMP_RESULT_FORMULA_RETURN <> CONST_COMP THEN
					ROLLBACK;
					O_ERROR_RETURN_MSG := '行番号[' || PROC_TEMP_FORMULA.ROW_NUMBER || ']指図区分[' || PROC_TEMP_FORMULA.DIRECTION_DIVISION || ']製造指図NO:[' || PROC_TEMP_FORMULA.DIRECTION_NO ||'] 在庫更新（仕上取消）に失敗しました。在庫更新エラーメッセージ:[' || TEMP_RESULT_FORMULA_RETURN || ']';
					O_RETURN_CD := '-1';
					OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT',I_TANTO_CD,'', O_ERROR_RETURN_MSG);
					EXIT;
				END IF;
				DBMS_OUTPUT.PUT_LINE('在庫仕上取消完了');
				
				-- ヘッダ更新
				UPDATE DIRECTION_HEADER SET 
					DIRECTION_HEADER.RESULT_QTY = PROC_TEMP_FORMULA.RESULT_QTY,		-- 実績生産量
					DIRECTION_HEADER.UPDATE_DATE = SYSDATE,		-- 更新日付
					DIRECTION_HEADER.UPDATOR_CD = I_TANTO_CD	-- 更新者コード
				WHERE DIRECTION_HEADER.DIRECTION_NO = PROC_TEMP_FORMULA.DIRECTION_NO AND DIRECTION_HEADER.DIRECTION_DIVISION = PROC_TEMP_FORMULA.DIRECTION_DIVISION;
				
			ELSE
				IF FUN_NOTEQUAL(PROC_TEMP_FORMULA.STOCKPD_QTY, R_FORMULA.STOCKPD_QTY) OR FUN_NOTEQUAL(PROC_TEMP_FORMULA.LOCATION_CD, R_FORMULA.LOCATION_CD) OR FUN_NOTEQUAL(PROC_TEMP_FORMULA.LOT_NO, R_FORMULA.LOT_NO) THEN
					ZAIKOUKEHARAI.RESULT_FORMULA(2, R_FORMULA.DIRECTION_NO, R_FORMULA.DIRECTION_DIVISION,R_FORMULA.STEP_NO,R_FORMULA.LINE_NO, I_TANTO_CD, TEMP_RESULT_FORMULA_RETURN);
					IF TEMP_RESULT_FORMULA_RETURN <> CONST_COMP THEN
						ROLLBACK;
						O_ERROR_RETURN_MSG := '行番号[' || PROC_TEMP_FORMULA.ROW_NUMBER || ']指図区分[' || PROC_TEMP_FORMULA.DIRECTION_DIVISION || ']製造指図NO:[' || PROC_TEMP_FORMULA.DIRECTION_NO ||'] 在庫更新（登録取消）に失敗しました。在庫更新エラーメッセージ:[' || TEMP_RESULT_FORMULA_RETURN || ']';
						O_RETURN_CD := '-1';
						OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT',I_TANTO_CD,'', O_ERROR_RETURN_MSG);
						EXIT;
					END IF;
					DBMS_OUTPUT.PUT_LINE('在庫取消完了');
				END IF;
			END IF;
			
			SELECT STOCK_DIVISION, LOT_DIVISION INTO TEMP_STOCK_DIV, TEMP_LOT_DIV FROM ITEM WHERE ITEM_CD = R_FORMULA.ITEM_CD;
			-- データがあるのでAP.製造指図フォーミュラを計装IF.製造実績詳細データで更新
			UPDATE DIRECTION_FORMULA SET
				DIRECTION_FORMULA.TONYU = PROC_TEMP_FORMULA.TONYU,		-- 投入方法
				DIRECTION_FORMULA.DATAREAD = PROC_TEMP_FORMULA.DATAREAD,		-- データ読み取り
				DIRECTION_FORMULA.TONYUSOKUDO = PROC_TEMP_FORMULA.TONYUSOKUDO,		-- 投入速度
				DIRECTION_FORMULA.RESULT_QTY = PROC_TEMP_FORMULA.RESULT_QTY,		-- 実績数量
				DIRECTION_FORMULA.LOSS_QTY = PROC_TEMP_FORMULA.LOSS_QTY,		-- ロス数量
				DIRECTION_FORMULA.ADJUST_QTY = PROC_TEMP_FORMULA.ADJUST_QTY,		-- 調整数量
				DIRECTION_FORMULA.STOCKPD_QTY = PROC_TEMP_FORMULA.STOCKPD_QTY,		-- 在庫引落量
				-- 入荷ロット
				DIRECTION_FORMULA.LOT_NO = CASE WHEN TEMP_STOCK_DIV <> 3 AND TEMP_LOT_DIV = 2 THEN
					 PROC_TEMP_FORMULA.LOT_NO	-- 更新あり
				ELSE DIRECTION_FORMULA.LOT_NO	-- 更新なし
				END,
				-- ロケーションコード
				DIRECTION_FORMULA.LOCATION_CD = CASE WHEN TEMP_STOCK_DIV <> 3 AND TEMP_LOT_DIV = 2 THEN
					 PROC_TEMP_FORMULA.LOCATION_CD	-- 更新あり
				ELSE DIRECTION_FORMULA.LOCATION_CD	-- 更新なし
				END,
				-- メーカーロット番号
				DIRECTION_FORMULA.MANUFACTURER_LOT_NO = CASE WHEN TEMP_STOCK_DIV <> 3 AND TEMP_LOT_DIV = 2 THEN
					 PROC_TEMP_FORMULA.MANUFACTURER_LOT_NO	-- 更新あり
				ELSE DIRECTION_FORMULA.MANUFACTURER_LOT_NO	-- 更新なし
				END,
				DIRECTION_FORMULA.STEP_CONDITION = PROC_TEMP_FORMULA.STEP_CONDITION,		-- 条件
				DIRECTION_FORMULA.NOTES = PROC_TEMP_FORMULA.NOTES,		-- 注釈
				DIRECTION_FORMULA.REMARK = PROC_TEMP_FORMULA.REMARK,		-- 備考
				DIRECTION_FORMULA.UPDATOR_CD = I_TANTO_CD,					-- 更新者コード
				DIRECTION_FORMULA.UPDATE_DATE = SYSDATE						-- 更新日
			WHERE DIRECTION_DIVISION = PROC_TEMP_FORMULA.DIRECTION_DIVISION AND DIRECTION_NO = PROC_TEMP_FORMULA.DIRECTION_NO AND STEP_NO = PROC_TEMP_FORMULA.STEP_NO AND LINE_NO = PROC_TEMP_FORMULA.LINE_NO;
			
			-- 在庫の登録
			IF R_FORMULA.LINE_NO >= 1001 THEN
				-- 仕上品の場合
				ZAIKOUKEHARAI.RESULT_DIRECTION(1, R_FORMULA.DIRECTION_NO, R_FORMULA.DIRECTION_DIVISION, I_TANTO_CD, TEMP_RESULT_FORMULA_RETURN);
				IF TEMP_RESULT_FORMULA_RETURN <> CONST_COMP THEN
					ROLLBACK;
					O_ERROR_RETURN_MSG := '行番号[' || PROC_TEMP_FORMULA.ROW_NUMBER || ']指図区分[' || PROC_TEMP_FORMULA.DIRECTION_DIVISION || ']製造指図NO:[' || PROC_TEMP_FORMULA.DIRECTION_NO ||'] 在庫更新（仕上登録）に失敗しました。在庫更新エラーメッセージ:[' || TEMP_RESULT_FORMULA_RETURN || ']';
					O_RETURN_CD := '-1';
					OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT',I_TANTO_CD,'', O_ERROR_RETURN_MSG);
					EXIT;
				END IF;
				DBMS_OUTPUT.PUT_LINE('在庫仕上登録完了');
			ELSE
				IF FUN_NOTEQUAL(PROC_TEMP_FORMULA.STOCKPD_QTY, R_FORMULA.STOCKPD_QTY) OR FUN_NOTEQUAL(PROC_TEMP_FORMULA.LOCATION_CD, R_FORMULA.LOCATION_CD) OR FUN_NOTEQUAL(PROC_TEMP_FORMULA.LOT_NO, R_FORMULA.LOT_NO) THEN
					ZAIKOUKEHARAI.RESULT_FORMULA(1, R_FORMULA.DIRECTION_NO, R_FORMULA.DIRECTION_DIVISION,R_FORMULA.STEP_NO,R_FORMULA.LINE_NO, I_TANTO_CD, TEMP_RESULT_FORMULA_RETURN);
					IF TEMP_RESULT_FORMULA_RETURN <> CONST_COMP THEN
						ROLLBACK;
						O_ERROR_RETURN_MSG := '行番号[' || PROC_TEMP_FORMULA.ROW_NUMBER || ']指図区分[' || PROC_TEMP_FORMULA.DIRECTION_DIVISION || ']製造指図NO:[' || PROC_TEMP_FORMULA.DIRECTION_NO ||'] 在庫更新（登録）に失敗しました。在庫更新エラーメッセージ:[' || TEMP_RESULT_FORMULA_RETURN || ']';
						O_RETURN_CD := '-1';
						OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT',I_TANTO_CD,'', O_ERROR_RETURN_MSG);
						EXIT;
					END IF;
					DBMS_OUTPUT.PUT_LINE('在庫登録完了');
				END IF;
			END IF;
			
		ELSE -- 製造指図フォーミュラにデータがない場合
			
			-- 工程存在チェックはExcelファイル読込時に実施済み
			
			-- 製造指図フォーミュラに新規レコード追加
			INSERT INTO DIRECTION_FORMULA (
				SELECT 
					FORMULA.DIRECTION_DIVISION,
					FORMULA.DIRECTION_NO,
					FORMULA.STEP_NO,
					FORMULA.LINE_NO,
					FORMULA.SEQ,
					FORMULA.LINE_TYPE,
					FORMULA.ITEM_CD,
					FORMULA.TONYU,
					FORMULA.DATAREAD,
					FORMULA.TONYUSOKUDO,
					FORMULA.QTY,
					FORMULA.STOCKPD_QTY,
					FORMULA.RESULT_QTY,
					FORMULA.SAMPLE_QTY,
					FORMULA.LOSS_QTY,
					FORMULA.DEFECT_QTY,
					FORMULA.ADJUST_QTY,
					FORMULA.COST,
					FORMULA.STEP_CONDITION,
					FORMULA.NOTES,
					FORMULA.LOCATION_CD,
					FORMULA.NEXT_LOCATION_CD,
					FORMULA.NEXT_AFTER_LOCATION_CD,
					FORMULA.LOT_NO,
					FORMULA.MANUFACTURER_LOT_NO,
					FORMULA.FILL_QTY,
					FORMULA.FILL_RESULT_QTY,
					FORMULA.REMARK,
					SYSDATE,
					'AUTO',
					SYSDATE,
					'AUTO'
			FROM DIRECTION_FORMULA_TEMP FORMULA WHERE TEMP_NO = I_TEMP_NO AND ROW_NUMBER = PROC_TEMP_FORMULA.ROW_NUMBER AND DIRECTION_DIVISION = PROC_TEMP_FORMULA.DIRECTION_DIVISION AND DIRECTION_NO = PROC_TEMP_FORMULA.DIRECTION_NO AND STEP_NO = PROC_TEMP_FORMULA.STEP_NO AND LINE_NO = PROC_TEMP_FORMULA.LINE_NO);
			DBMS_OUTPUT.PUT_LINE('配合追加終了');

			-- 在庫の登録
			ZAIKOUKEHARAI.RESULT_FORMULA(1, PROC_TEMP_FORMULA.DIRECTION_NO, PROC_TEMP_FORMULA.DIRECTION_DIVISION,PROC_TEMP_FORMULA.STEP_NO,PROC_TEMP_FORMULA.LINE_NO, I_TANTO_CD, TEMP_RESULT_FORMULA_RETURN);
			IF TEMP_RESULT_FORMULA_RETURN <> CONST_COMP THEN
				ROLLBACK;
				O_ERROR_RETURN_MSG := '行番号[' || PROC_TEMP_FORMULA.ROW_NUMBER || ']指図区分[' || R_FORMULA.DIRECTION_DIVISION || ']製造指図NO:[' || R_FORMULA.DIRECTION_NO ||'] 在庫更新（登録）に失敗しました。在庫更新エラーメッセージ:[' || TEMP_RESULT_FORMULA_RETURN || ']';
				O_RETURN_CD := '-1';
				OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT',I_TANTO_CD,'', O_ERROR_RETURN_MSG);
				EXIT;
			END IF;
			DBMS_OUTPUT.PUT_LINE('在庫登録完了');
			
		END IF;
	
	END LOOP;
	
	COMMIT;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := TEMP_ERR_MSG || '：' || SUBSTR(SQLERRM,1,1024);
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_RDIRECTION_FILE_IMPORT', I_TANTO_CD, O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG);
		O_RETURN_CD := '-1';
		COMMIT;
END;
/
