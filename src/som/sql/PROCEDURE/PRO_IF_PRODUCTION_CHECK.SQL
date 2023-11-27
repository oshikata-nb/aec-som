CREATE OR REPLACE PROCEDURE      PRO_IF_PRODUCTION_CHECK

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_PRODUCTION_CHECK                                                   */
/*      処理内容      :      製造状況の監視を行う                            			       */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 	VARCHAR2(100);
	ERR_MSG 	VARCHAR2(2048);
	TEMP_COUNT	NUMBER;
	TEMP_CHAR	VARCHAR2(100);
	TEMP_CURRENT_STEP_NO	VARCHAR2(100);
	TEMP_TANK_STEP_NO	VARCHAR2(100);
	TEMP_LAST_STEP_NO	VARCHAR2(100);

	-- 製造指図ヘッダー.指図ステータス=[2:指図書発行済]かつ製造指図ヘッダー.指図区分 = [1:バッチ製造]で検索を行い（複数ある場合有）
    	CURSOR LOOP_WAIT IS SELECT DIRECTION_NO FROM DIRECTION_HEADER WHERE DIRECTION_STATUS = 2 AND DIRECTION_DIVISION = 1;

	-- 製造指図ヘッダー.指図ステータス=[3:製造開始]または[4:中間品最終検査待]または[5:中間品最終検査済]かつ製造指図ヘッダー.指図区分 = [1:バッチ製造]で検索を行い（複数ある場合有）
    	CURSOR LOOP_RUN IS SELECT DIRECTION_NO,DIRECTION_STATUS FROM DIRECTION_HEADER WHERE DIRECTION_STATUS IN (3,4,5) AND DIRECTION_DIVISION = 1;
BEGIN

	-- 製造指図ヘッダー.指図ステータス=[2:指図書発行済]の定周期処理
   	FOR PROC_WAIT IN LOOP_WAIT LOOP

		DBMS_OUTPUT.PUT_LINE(PROC_WAIT.DIRECTION_NO);
		
		-- 製造指図ヘッダー.製造指図番号でifテーブルの製造計画を検索
		SELECT COUNT(*) INTO TEMP_CHAR FROM KEIKAKU_SEIZO WHERE TRIM(SEIZOSASHIZUNO) = PROC_WAIT.DIRECTION_NO;

		IF TEMP_CHAR IS NOT NULL AND TEMP_CHAR <> 0 THEN -- 製造計画に製造指図ヘッダー.製造指図番号がある場合

			-- 製造計画.製造状況 = [1:製造中]の場合
			SELECT SEIZOJOKYO INTO TEMP_CHAR FROM KEIKAKU_SEIZO WHERE TRIM(SEIZOSASHIZUNO) = PROC_WAIT.DIRECTION_NO;

			DBMS_OUTPUT.PUT_LINE('製造状況' || TEMP_CHAR);
			IF TEMP_CHAR = '1' THEN

				-- 製造指図ヘッダー.製造ステータス = [3:製造開始]とする
				UPDATE DIRECTION_HEADER SET DIRECTION_STATUS = 3 ,UPDATE_DATE = SYSDATE,UPDATOR_CD = 'AUTO' WHERE DIRECTION_NO = PROC_WAIT.DIRECTION_NO AND DIRECTION_DIVISION = 1;
				
			END IF;
			
		ELSE -- 検索結果がない場合
			
			-- エラーログ出力
			OUTPUT_ERROR_LOG('IF_PRODUCTION_CHECK','AUTO','','製造指図番号がIFテーブル.製造計画にありません 製造指図番号:' || PROC_WAIT.DIRECTION_NO);
			
		END IF;
		
	END LOOP;

	-- 製造指図ヘッダー.指図ステータス=[3:製造開始]の定周期処理
   	FOR PROC_RUN IN LOOP_RUN LOOP
		
		DBMS_OUTPUT.PUT_LINE(PROC_RUN.DIRECTION_NO);

		-- 製造指図ヘッダー.製造指図番号でifテーブルの調合タンク在庫と製造指図プロシージャを検索
		SELECT COUNT(DIRECTION_PROCEDURE.OPERATION_CD) INTO TEMP_CHAR FROM ZAIKO_CHOGOTANK,DIRECTION_PROCEDURE WHERE TRIM(ZAIKO_CHOGOTANK.SEIZOSASHIZUNO) = PROC_RUN.DIRECTION_NO AND TRIM(ZAIKO_CHOGOTANK.SEIZOSASHIZUNO) = DIRECTION_PROCEDURE.DIRECTION_NO AND ZAIKO_CHOGOTANK.STEPNO = DIRECTION_PROCEDURE.SEQ;

		IF TEMP_CHAR IS NOT NULL AND TEMP_CHAR <> 0 THEN -- 検索結果がある場合（工程コードが取得できる場合
			
			-- 調合タンク在庫と製造指図プロシージャから工程番号を取得
			SELECT DIRECTION_PROCEDURE.OPERATION_CD INTO TEMP_TANK_STEP_NO FROM ZAIKO_CHOGOTANK,DIRECTION_PROCEDURE WHERE TRIM(ZAIKO_CHOGOTANK.SEIZOSASHIZUNO) = PROC_RUN.DIRECTION_NO AND TRIM(ZAIKO_CHOGOTANK.SEIZOSASHIZUNO) = DIRECTION_PROCEDURE.DIRECTION_NO AND ZAIKO_CHOGOTANK.STEPNO = DIRECTION_PROCEDURE.SEQ;

			DBMS_OUTPUT.PUT_LINE('調合タンク工程コード:' || TEMP_TANK_STEP_NO);
			
			-- 製造ヘッダーの現工程を取得
			SELECT CURRENT_STEP_NO INTO TEMP_CURRENT_STEP_NO FROM DIRECTION_HEADER WHERE DIRECTION_NO = PROC_RUN.DIRECTION_NO;

			DBMS_OUTPUT.PUT_LINE('製造ヘッダ工程コード:' || TEMP_CURRENT_STEP_NO);

			-- 調合タンク在庫.工程番号と製造ヘッダーの現工程が異なる場合のみ製造指図ヘッダー.現工程の更新処理を行う。
			IF TEMP_CURRENT_STEP_NO <> TEMP_TANK_STEP_NO OR TEMP_CURRENT_STEP_NO IS NULL THEN

				-- 製造指図ヘッダー.現工程にifテーブル調合タンク在庫の工程をセット
				UPDATE DIRECTION_HEADER SET CURRENT_STEP_NO = TEMP_TANK_STEP_NO,UPDATE_DATE = SYSDATE,UPDATOR_CD = 'AUTO' WHERE DIRECTION_NO = PROC_RUN.DIRECTION_NO;
				
				-- 最終工程の工程番号を取得
				SELECT OPERATION_CD INTO TEMP_LAST_STEP_NO FROM DIRECTION_PROCEDURE WHERE DIRECTION_NO = PROC_RUN.DIRECTION_NO AND STEP_NO = (SELECT MAX(STEP_NO) FROM DIRECTION_PROCEDURE WHERE DIRECTION_NO = PROC_RUN.DIRECTION_NO);
				
				-- 現行ｽﾃｯﾌﾟ番号を変更
				TEMP_CURRENT_STEP_NO := TEMP_TANK_STEP_NO;
				
				DBMS_OUTPUT.PUT_LINE('現行工程:' || TEMP_CURRENT_STEP_NO || '最終工程:' || TEMP_LAST_STEP_NO);
				
				-- 現工程が中間品検査,中間品最終検査,最終工程の場合で製造ステータス =[3:製造開始] の場合 製造ステータス = [4:中間品検査待ち]とする。
				IF (TEMP_CURRENT_STEP_NO = '42' OR TEMP_CURRENT_STEP_NO = '44' OR TEMP_CURRENT_STEP_NO = TEMP_LAST_STEP_NO) AND PROC_RUN.DIRECTION_STATUS = 3 THEN
					
					-- 製造指図ヘッダー.製造ステータス = [4:中間品最終検査待ち]とする
					UPDATE DIRECTION_HEADER SET DIRECTION_STATUS = 4 ,UPDATE_DATE = SYSDATE,UPDATOR_CD = 'AUTO' WHERE DIRECTION_NO = PROC_RUN.DIRECTION_NO;
					
				END IF;
			END IF;
		ELSE -- 検索結果がない場合
			
			-- エラーログ出力
			OUTPUT_ERROR_LOG('IF_PRODUCTION_CHECK','AUTO','','製造指図番号がIFテーブル.調合タンク在庫にありません 製造指図番号:' || PROC_RUN.DIRECTION_NO);
			
		END IF;
	END LOOP;

	COMMIT;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		ROLLBACK;

		OUTPUT_ERROR_LOG('IF_DEL_PRODUCTION','AUTO',ERR_CODE,ERR_MSG);
		COMMIT;
END;
/
