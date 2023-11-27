CREATE OR REPLACE PROCEDURE AP21.PRO_IF_PRODUCTION_RESULT
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_PRODUCTION_RESULT                                                  */
/*      処理内容      :      製造完了時計装ifの製造実績データを取得する       			       */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*      2009.06.23      1.00        Ohga                    配合の在庫プロシージャをSUBに変更          */
/*      2011.02.02      1.01        Ohga                    出荷実績受信、IF在庫ロケ移動と処理を       */
/*							    処理をあわせる為IFを先に処理するよう修正   */
/*      2015.05.27      1.02        Ohga                    多重起動防止処理追加                       */
/*-----------------------------------------------------------------------------------------------------*/
	TEMP_ERR_CODE 			VARCHAR2(100);
	TEMP_ERR_MSG 			VARCHAR2(2048);
	TEMP_NUMBER			NUMBER;
	TEMP_COUNT			NUMBER;
	TEMP_CHAR			VARCHAR2(100);
	TEMP_CURRENT_STEP_NO		VARCHAR2(100);
	TEMP_LAST_STEP_NO		VARCHAR2(100);
	TEMP_DIRECTION_NO		VARCHAR2(100);
	TEMP_IF_DIRECTION_NO		VARCHAR2(100);
	TEMP_RESULT_FORMULA_RETURN	VARCHAR2(255);
	TEMP_RESULT_DIRECTION_RETURN	VARCHAR2(255);
	CONST_COMP			VARCHAR2(20) := 'COMPLETE';	-- 在庫プロシージャ完了戻り値
    	CURSOR LOOP_RESULT IS 
		SELECT KEIKAKU_SEIZO_PRO.SEIZOSASHIZUNO 
		FROM KEIKAKU_SEIZO_PRO,JISSEKI_SEIZO_HEAD_PRO 
		WHERE KEIKAKU_SEIZO_PRO.SEIZOSASHIZUNO = JISSEKI_SEIZO_HEAD_PRO.SEIZOSASHIZUNO AND
		KEIKAKU_SEIZO_PRO.SEIZOJOKYO = 2 AND 
		JISSEKI_SEIZO_HEAD_PRO.SUMI !=1;
BEGIN
	DBMS_OUTPUT.ENABLE(NULL);

	-- 多重起動防止の為追加
	IF FUN_LOCK_DOUBLE_START('PRO_IF_PRODUCTION_RESULT') = FALSE THEN
		OUTPUT_ERROR_LOG('PRO_IF_PRODUCTION_RESULT','AUTO',SQLCODE,'すでに起動しています');
		COMMIT;
		RETURN;
	END IF;
	COMMIT;

	-- 製造指図ヘッダー.指図ステータス=[5:中間品最終検査済]の定周期処理
   	FOR PROC_RESULT IN LOOP_RESULT LOOP
		DBMS_OUTPUT.PUT_LINE(PROC_RESULT.SEIZOSASHIZUNO);
		
		TEMP_DIRECTION_NO := PROC_RESULT.SEIZOSASHIZUNO;
		TEMP_IF_DIRECTION_NO := PROC_RESULT.SEIZOSASHIZUNO;
		
		-- 製造指図ヘッダーにあるか検索(製造ステータスがfa受信済みより小さい場合実績受信処理）
		SELECT COUNT(*) INTO TEMP_COUNT FROM DIRECTION_HEADER WHERE DIRECTION_DIVISION = 1 AND DIRECTION_NO = TRIM(TEMP_DIRECTION_NO) AND DIRECTION_STATUS < 6;
		-- 製造計画に製造指図ヘッダー.製造指図番号がある場合
		IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT = 1 THEN 
			-- 製造指図ヘッダー.製造指図番号でifテーブル製造実績詳細を検索
			SELECT COUNT(*) INTO TEMP_COUNT FROM JISSEKI_SEIZO_DETAIL_PRO WHERE TRIM(SEIZOSASHIZUNO)= TRIM(TEMP_DIRECTION_NO);
			
			-- 製造実績詳細に製造指図ヘッダー.製造指図番号がある場合
			IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN 
				DBMS_OUTPUT.PUT_LINE('製造指図番号' || TEMP_DIRECTION_NO || '処理開始');

				-- 製造計画ヘッダーの上位処理済みとする
				UPDATE JISSEKI_SEIZO_HEAD_PRO SET SUMI = 1 WHERE SEIZOSASHIZUNO = TEMP_IF_DIRECTION_NO;
				COMMIT;

				-- 製造指図番号毎に詳細処理を行い 0 より小さい場合エラーにロールバックそれ以外はコミット
				IF FUN_IF_PRODUCTION_RESULT_SUB(TRIM(TEMP_DIRECTION_NO),TEMP_ERR_CODE,TEMP_ERR_MSG) < 0 THEN
					ROLLBACK;
					-- エラーログ出力
					OUTPUT_ERROR_LOG('PRO_IF_PRODUCTION_RESULT','AUTO',TEMP_ERR_CODE,TEMP_ERR_MSG);
					
					COMMIT;
					-- 製造計画ヘッダーの上位処理済みとする
					UPDATE JISSEKI_SEIZO_HEAD_PRO SET SUMI = 0 WHERE SEIZOSASHIZUNO = TEMP_IF_DIRECTION_NO;
					COMMIT;

				ELSE	-- 正常終了
					--包装・製造指図入力
					ZAIKOUKEHARAI.RESULT_DIRECTION(1,TRIM(TEMP_DIRECTION_NO),1,'AUTO',TEMP_RESULT_DIRECTION_RETURN);
					
					-- 在庫プロシージャ成功した場合コミット　失敗したらロールバック
					IF TEMP_RESULT_DIRECTION_RETURN = CONST_COMP THEN
						COMMIT;
					ELSE
						ROLLBACK;
						OUTPUT_ERROR_LOG('PRO_IF_PRODUCTION_RESULT','AUTO','','在庫プロシージャで失敗しました 製造指図No:' || TEMP_DIRECTION_NO || ' ' || TEMP_RESULT_DIRECTION_RETURN);
						COMMIT;
						-- 製造計画ヘッダーの上位処理済みとする
						UPDATE JISSEKI_SEIZO_HEAD_PRO SET SUMI = 0 WHERE SEIZOSASHIZUNO = TEMP_IF_DIRECTION_NO;
						COMMIT;
					END IF;
				END IF;
			ELSE -- 検索結果がない場合
				
				-- エラーログ出力
				OUTPUT_ERROR_LOG('PRO_IF_PRODUCTION_RESULT','AUTO','','製造指図番号がIFテーブル.製造指図詳細にありません 製造指図番号:' || TEMP_DIRECTION_NO);
				COMMIT;
			END IF;
		END IF;
	END LOOP;
	-- 多重起動防止
	DELETE FROM PROC_PARAM WHERE PROC_PARAM.PROC_CD = 'PRO_IF_PRODUCTION_RESULT';
	COMMIT;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		--- Add 2009.07.30 
			IF LOOP_RESULT%ISOPEN = TRUE THEN
				CLOSE LOOP_RESULT;
			END IF;
		-- SQLエラーコード、エラーメッセージを取得
	    	TEMP_ERR_CODE := SQLCODE;
		TEMP_ERR_MSG  := TEMP_DIRECTION_NO || SUBSTR(SQLERRM,1,1024);
		ROLLBACK;
		-- 多重起動防止
		DELETE FROM PROC_PARAM WHERE PROC_PARAM.PROC_CD = 'PRO_IF_PRODUCTION_RESULT';
		OUTPUT_ERROR_LOG('PRO_IF_PRODUCTION_RESULT','AUTO',TEMP_ERR_CODE,TEMP_ERR_MSG);
		COMMIT;
END;
/
