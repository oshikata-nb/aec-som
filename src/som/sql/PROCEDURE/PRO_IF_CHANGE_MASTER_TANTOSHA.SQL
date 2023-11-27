CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_TANTOSHA
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_TANTOSHA		                               */
/*      処理内容      :      ログインマスタを変更した結果を計装IF.担当者マスタに反映	       、      */
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
	TEMP_MASTER_TANTOSHA	MASTER_TANTOSHA%ROWTYPE;	-- IF.工場マスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_TANTOSHA;
BEGIN

	-- ログインラインマスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 工場コードがIF.担当者マスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_TANTOSHA WHERE TRIM(MASTER_TANTOSHA.TANTOCODE) = PROC_TRRIGER.TANTOCODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.担当者マスタを保持
				SELECT * INTO TEMP_MASTER_TANTOSHA FROM MASTER_TANTOSHA
					WHERE TRIM(TEMP_MASTER_TANTOSHA.TANTOCODE) = PROC_TRRIGER.TANTOCODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_TANTOSHA SET 
					TANTOSHA = PROC_TRRIGER.TANTOSHA,
					TANTOSHAKANA = PROC_TRRIGER.TANTOSHAKANA,
					TANTOKUBUN = PROC_TRRIGER.TANTOKUBUN,
					TANTOPASSWD = PROC_TRRIGER.TANTOPASSWD
				WHERE MASTER_TANTOSHA.TANTOCODE = TEMP_MASTER_TANTOSHA.TANTOCODE;
		
			ELSE

				INSERT INTO MASTER_TANTOSHA (TANTOCODE,TANTOSHA,TANTOSHAKANA,TANTOKUBUN,TANTOPASSWD) VALUES(
					PROC_TRRIGER.TANTOCODE,
					PROC_TRRIGER.TANTOSHA,
					PROC_TRRIGER.TANTOSHAKANA,
					PROC_TRRIGER.TANTOKUBUN,
					PROC_TRRIGER.TANTOPASSWD);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.担当者マスタを保持
				SELECT * INTO TEMP_MASTER_TANTOSHA FROM MASTER_TANTOSHA WHERE TRIM(MASTER_TANTOSHA.TANTOCODE) = PROC_TRRIGER.TANTOCODE;
				
				DELETE FROM MASTER_TANTOSHA WHERE MASTER_TANTOSHA.TANTOCODE = TEMP_MASTER_TANTOSHA.TANTOCODE;
			END IF;
		END IF;
		
		COMMIT;
		-- 一時テーブルのレコードを削除
		DELETE FROM IF_MASTER_TANTOSHA WHERE IF_MASTER_TANTOSHA.TANTOCODE = PROC_TRRIGER.TANTOCODE;
		COMMIT;
		
	END LOOP;
	
	COMMIT;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_CHANGE_MASTER_TANTOSHA','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
