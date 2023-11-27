CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_UNSOTEN
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_UNSOTEN                                              */
/*      処理内容      :      運送会社マスタを変更した結果を計装IF.運送店マスタに反映	       、      */
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
	TEMP_MASTER_UNSOTEN	MASTER_UNSOTEN%ROWTYPE;	-- IF.運送会社マスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_UNSOTEN;
BEGIN

	-- 運送店マスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 運送店コードがIF.運送店マスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_UNSOTEN WHERE TRIM(MASTER_UNSOTEN.UNSOTENCODE) = PROC_TRRIGER.UNSOTENCODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.運送店コードマスタを保持
				SELECT * INTO TEMP_MASTER_UNSOTEN FROM MASTER_UNSOTEN 
					WHERE TRIM(MASTER_UNSOTEN.UNSOTENCODE) = PROC_TRRIGER.UNSOTENCODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_UNSOTEN SET 
					UNSOTENMEI = PROC_TRRIGER.UNSOTENMEI,
					UNSOTENKIGO = PROC_TRRIGER.UNSOTENKIGO
				WHERE MASTER_UNSOTEN.UNSOTENCODE = TEMP_MASTER_UNSOTEN.UNSOTENCODE;
		
			ELSE
				
				DBMS_OUTPUT.PUT_LINE('コード:' || PROC_TRRIGER.UNSOTENCODE || ' 名称:' || PROC_TRRIGER.UNSOTENMEI || ' FLAN:' || PROC_TRRIGER.UNSOTENKIGO);

				INSERT INTO MASTER_UNSOTEN (UNSOTENCODE,UNSOTENMEI,UNSOTENKIGO) VALUES(
					PROC_TRRIGER.UNSOTENCODE,
					PROC_TRRIGER.UNSOTENMEI,
					PROC_TRRIGER.UNSOTENKIGO);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.運送店コードマスタを保持
				SELECT * INTO TEMP_MASTER_UNSOTEN FROM MASTER_UNSOTEN WHERE TRIM(MASTER_UNSOTEN.UNSOTENCODE) = PROC_TRRIGER.UNSOTENCODE;
				
				DELETE FROM MASTER_UNSOTEN WHERE MASTER_UNSOTEN.UNSOTENCODE = TEMP_MASTER_UNSOTEN.UNSOTENCODE;
			END IF;
		END IF;
		
		COMMIT;
		-- レコードを削除
		DELETE FROM IF_MASTER_UNSOTEN WHERE IF_MASTER_UNSOTEN.UNSOTENCODE = PROC_TRRIGER.UNSOTENCODE;
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
