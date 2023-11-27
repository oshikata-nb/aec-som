CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_SHORICODE
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_SHORICODE                                            */
/*      処理内容      :      工程マスタを変更した結果を計装IF.処理コードマスタに反映	       、      */
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
	CONST_SPACE 		VARCHAR(10) := ' ';		-- スペース
	TEMP_MASTER_SHORICODE	MASTER_SHORICODE%ROWTYPE;	-- 処理コードマスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_SHORICODE;
BEGIN

	-- 運送店マスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 処理コードががIF.処理コードマスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_SHORICODE WHERE TRIM(MASTER_SHORICODE.SHIORICODE) = PROC_TRRIGER.SHIORICODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.工程マスタを保持
				SELECT * INTO TEMP_MASTER_SHORICODE FROM MASTER_SHORICODE
					WHERE TRIM(MASTER_SHORICODE.SHIORICODE) = PROC_TRRIGER.SHIORICODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_SHORICODE SET 
					SHORIMEI = PROC_TRRIGER.SHORIMEI,
					SAIYONO = PROC_TRRIGER.SAIYONO
				WHERE MASTER_SHORICODE.SHIORICODE = TEMP_MASTER_SHORICODE.SHIORICODE;
		
			ELSE

				INSERT INTO MASTER_SHORICODE (SHIORICODE,SHORIMEI,SAIYONO) VALUES(
					PROC_TRRIGER.SHIORICODE,
					PROC_TRRIGER.SHORIMEI,
					PROC_TRRIGER.SAIYONO);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.運送店コードマスタを保持
				SELECT * INTO TEMP_MASTER_SHORICODE FROM MASTER_SHORICODE WHERE TRIM(MASTER_SHORICODE.SHIORICODE) = PROC_TRRIGER.SHIORICODE;
				
				DELETE FROM MASTER_SHORICODE WHERE MASTER_SHORICODE.SHIORICODE = TEMP_MASTER_SHORICODE.SHIORICODE;
			END IF;
		END IF;
		
		COMMIT;
		-- レコードを削除
		DELETE FROM IF_MASTER_SHORICODE WHERE IF_MASTER_SHORICODE.SHIORICODE = PROC_TRRIGER.SHIORICODE;
		COMMIT;
		
	END LOOP;
	
	COMMIT;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_PROC_TRRIGER','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
