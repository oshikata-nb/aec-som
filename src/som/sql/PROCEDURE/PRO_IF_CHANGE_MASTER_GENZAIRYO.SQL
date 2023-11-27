CREATE OR REPLACE PROCEDURE      PRO_IF_CHANGE_MASTER_GENZAIRYO
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_CHANGE_MASTER_GENZAIRYO		                               */
/*      処理内容      :      品目マスタを変更した結果を計装IF.原材料マスタに反映	       、      */
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
	TEMP_MASTER_GENZAIRYO	MASTER_GENZAIRYO%ROWTYPE;	-- IF.原材料マスタ
	
	CURSOR PROC_TRRIGER_TEMP IS 
		SELECT * FROM IF_MASTER_GENZAIRYO;
BEGIN

	-- 原材料マスタの変更を計装IFテーブルに反映させる処理
   	FOR PROC_TRRIGER IN PROC_TRRIGER_TEMP LOOP

		-- 工場コードがIF.担当者マスタにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM MASTER_GENZAIRYO WHERE TRIM(GENZAICODE) = PROC_TRRIGER.GENZAICODE;

		-- 追加処理か更新処理の場合
		IF PROC_TRRIGER.PROC_DIVISION = CONST_INSERT OR PROC_TRRIGER.PROC_DIVISION  =CONST_UPDATE THEN
			DBMS_OUTPUT.PUT_LINE(PROC_TRRIGER.PROC_DIVISION);

			IF TEMP_COUNT <> 0 THEN
				-- 計装IF.原材料マスタを保持
				SELECT * INTO TEMP_MASTER_GENZAIRYO FROM MASTER_GENZAIRYO
					WHERE TRIM(TEMP_MASTER_GENZAIRYO.GENZAICODE) = PROC_TRRIGER.GENZAICODE;
				
				DBMS_OUTPUT.PUT_LINE('UPDATE');
		
				UPDATE MASTER_GENZAIRYO SET 
					DAIGENCODE	= PROC_TRRIGER.DAIGENCODE	,-- 代表原材料コード
					DAIGENMEI	= PROC_TRRIGER.DAIGENMEI	,-- 代表原材料名
					GENZAIMEI	= PROC_TRRIGER.GENZAIMEI	,-- 原材料名
					GENZAIKANA	= PROC_TRRIGER.GENZAIKANA	,-- 原材料カナ
					NISUGATA	= PROC_TRRIGER.NISUGATA		,-- 荷姿
					KGKANZANCHI	= PROC_TRRIGER.KGKANZANCHI	,-- KG換算値
					JUNDO		= PROC_TRRIGER.JUNDO		,-- 純度
					SHIIRECODE	= PROC_TRRIGER.SHIIRECODE	,-- 仕入先コード
					YUSHOMUSHO	= PROC_TRRIGER.YUSHOMUSHO	,-- 有償無償
					GENZAIZAIKOSHU	= PROC_TRRIGER.GENZAIZAIKOSHU	,-- 原材料在庫種別
					GENZAISHUBETSU	= PROC_TRRIGER.GENZAISHUBETSU	,-- 原材料種別
					GENZAIKUBUN	= PROC_TRRIGER.GENZAIKUBUN	,-- 原材料使用区分
					TASHAGENCODE	= PROC_TRRIGER.TASHAGENCODE	,-- 他社原材料コード
					HACCHUTENCODE	= PROC_TRRIGER.HACCHUTENCODE	,-- 発注扱い店コード
					GENUNIT		= PROC_TRRIGER.GENUNIT		,-- 原材料単位？
					MAKERMEI	= PROC_TRRIGER.MAKERMEI		,-- メーカー名
					KOJOCODE	= PROC_TRRIGER.KOJOCODE		,-- 工場コード
					GAICHUFLAG	= PROC_TRRIGER.GAICHUFLAG	 -- 外注フラグ
				WHERE MASTER_GENZAIRYO.GENZAICODE = TEMP_MASTER_GENZAIRYO.GENZAICODE;
		
			ELSE

				INSERT INTO MASTER_GENZAIRYO (
					DAIGENCODE,                     -- 代表原材料コード
					DAIGENMEI,                      -- 代表原材料名
					GENZAICODE,                     -- 原材料コード
					GENZAIMEI,                      -- 原材料名
					GENZAIKANA,                     -- 原材料カナ
					NISUGATA,                       -- 荷姿
					KGKANZANCHI,                    -- KG換算値
					JUNDO,                          -- 純度
					SHIIRECODE,                     -- 仕入先コード
					YUSHOMUSHO,                     -- 有償無償
					GENZAIZAIKOSHU,                 -- 原材料在庫種別
					GENZAISHUBETSU,                 -- 原材料種別
					GENZAIKUBUN,                    -- 原材料使用区分
					TASHAGENCODE,                   -- 他社原材料コード
					HACCHUTENCODE,                  -- 発注扱い店コード
					GENUNIT,                        -- 原材料単位？
					MAKERMEI,                       -- メーカー名
					KOJOCODE,                       -- 工場コード
					GAICHUFLAG                      -- 外注フラグ
				) VALUES(
					PROC_TRRIGER.DAIGENCODE	,	-- 代表原材料コード
					PROC_TRRIGER.DAIGENMEI	,	-- 代表原材料名
					PROC_TRRIGER.GENZAICODE	,	-- 原材料コード
					PROC_TRRIGER.GENZAIMEI	,	-- 原材料名
					PROC_TRRIGER.GENZAIKANA	,	-- 原材料カナ
					PROC_TRRIGER.NISUGATA		,-- 荷姿
					PROC_TRRIGER.KGKANZANCHI	,-- KG換算値
					PROC_TRRIGER.JUNDO		,-- 純度
					PROC_TRRIGER.SHIIRECODE	,	-- 仕入先コード
					PROC_TRRIGER.YUSHOMUSHO	,	-- 有償無償
					PROC_TRRIGER.GENZAIZAIKOSHU	,-- 原材料在庫種別
					PROC_TRRIGER.GENZAISHUBETSU	,-- 原材料種別
					PROC_TRRIGER.GENZAIKUBUN	,-- 原材料使用区分
					PROC_TRRIGER.TASHAGENCODE	,-- 他社原材料コード
					PROC_TRRIGER.HACCHUTENCODE	,-- 発注扱い店コード
					PROC_TRRIGER.GENUNIT		,-- 原材料単位？
					PROC_TRRIGER.MAKERMEI		,-- メーカー名
					PROC_TRRIGER.KOJOCODE		,-- 工場コード
					PROC_TRRIGER.GAICHUFLAG	 	-- 外注フラグ
				);
			END IF;
		
		-- 削除処理の場合
		ELSIF PROC_TRRIGER.PROC_DIVISION = CONST_DELETE THEN
						
			-- 削除レコードが有る場合のみ処理
			IF TEMP_COUNT <> 0 THEN
	
				DBMS_OUTPUT.PUT_LINE('DELETE');
				-- 計装IF.担当者マスタを保持
				SELECT * INTO TEMP_MASTER_GENZAIRYO FROM MASTER_GENZAIRYO WHERE TRIM(MASTER_GENZAIRYO.GENZAICODE) = PROC_TRRIGER.GENZAICODE;
				
				DELETE FROM MASTER_GENZAIRYO WHERE MASTER_GENZAIRYO.GENZAICODE = TEMP_MASTER_GENZAIRYO.GENZAICODE;
			END IF;
		END IF;
		
		COMMIT;
		-- 一時テーブルのレコードを削除
		DELETE FROM IF_MASTER_GENZAIRYO WHERE IF_MASTER_GENZAIRYO.GENZAICODE = PROC_TRRIGER.GENZAICODE;
		COMMIT;
		
	END LOOP;
	
	COMMIT;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_CHANGE_MASTER_GENZAIRYO','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
