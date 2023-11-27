CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_KOJO
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_KOJO		                                       */
/*      処理内容      :      生産ラインマスタを変更した結果を計装IF.工場マスタに反映	       、      */
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
	TEMP_MASTER_KOJO	MASTER_KOJO%ROWTYPE;	-- IF.工場マスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_KOJO;
BEGIN

	-- 生産ラインマスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 工場コードがIF.工場マスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_KOJO WHERE TRIM(MASTER_KOJO.KOJOCODE) = PROC_TRRIGER.KOJOCODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.工場マスタを保持
				SELECT * INTO TEMP_MASTER_KOJO FROM MASTER_KOJO
					WHERE TRIM(TEMP_MASTER_KOJO.KOJOCODE) = PROC_TRRIGER.KOJOCODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_KOJO SET 
					TATEYACODE = PROC_TRRIGER.TATEYACODE,
					AREANAME = PROC_TRRIGER.AREANAME,
					FULLNAME = PROC_TRRIGER.FULLNAME
				WHERE MASTER_KOJO.KOJOCODE = TEMP_MASTER_KOJO.KOJOCODE;
		
			ELSE

				INSERT INTO MASTER_KOJO (TATEYACODE,KOJOCODE,AREANAME,FULLNAME) VALUES(	
					PROC_TRRIGER.TATEYACODE,
					PROC_TRRIGER.KOJOCODE,
					PROC_TRRIGER.AREANAME,
					PROC_TRRIGER.FULLNAME);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.工場コードマスタを保持
				SELECT * INTO TEMP_MASTER_KOJO FROM MASTER_KOJO WHERE TRIM(MASTER_KOJO.KOJOCODE) = PROC_TRRIGER.KOJOCODE;
				
				DELETE FROM MASTER_KOJO WHERE MASTER_KOJO.KOJOCODE = TEMP_MASTER_KOJO.KOJOCODE;
			END IF;
		END IF;
		
		COMMIT;
		-- 一時テーブルのレコードを削除
		DELETE FROM IF_MASTER_KOJO WHERE IF_MASTER_KOJO.KOJOCODE = PROC_TRRIGER.KOJOCODE;
		COMMIT;
		
	END LOOP;
	
	COMMIT;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_CHANGE_MASTER_KOJO','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
