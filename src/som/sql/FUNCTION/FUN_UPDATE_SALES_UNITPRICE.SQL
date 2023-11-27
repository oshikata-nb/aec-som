CREATE OR REPLACE FUNCTION      FUN_UPDATE_SALES_UNITPRICE
      (
             I_TANTO_CD			IN	NVARCHAR2	-- 担当者コード
      )

RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_UPDATE_SALES_UNITPRICE                                                */
/*      処理内容      :      仮単価の売上データの単価洗替えを行う			       、      */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.17      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 		VARCHAR2(100);
	ERR_MSG 		VARCHAR2(2048);
	TEMP_BALANCE_CD		VARCHAR2(20);
	TEMP_ITEM_CD		VARCHAR2(20);
	TEMP_PROC_DATE		DATE;
	TEMP_ESTIMATE		ESTIMATE%ROWTYPE;	-- 見積単価ヘッダデータ
	TEMP_COUNT		NUMBER;
	CONST_ESTIMATE_STATUS	NUMBER := 3;		-- 有効な見積単価ステータス[3:承認済み]
	
	
	-- 処理対処をループ処理するカーソルを作成
	-- ①仮単価フラグがon
	-- ②入力区分が[1:売上入力] かつ 預り品フラグ <> [1:預り品]
	-- ソート順 ：売上番号　昇順
	CURSOR PROC_CHANGE_UNITPRICE IS 
		SELECT 
			* 
		FROM 
			SALES 
		WHERE 
			SALES.TMP_UNITPRICE_FLG = 1 
			AND (SALES.INPUT_DIVISION = 1 AND SALES.KEEP_FLAG <> 1)
		ORDER BY SALES.SALES_NO ASC;
BEGIN
	
	-- 受注明細毎に出荷指図自動作成処理
   	FOR CHANGE_PRICE IN PROC_CHANGE_UNITPRICE LOOP
	
		DBMS_OUTPUT.PUT_LINE('売上番号:' || CHANGE_PRICE.SALES_NO);
		
		-- 受注番号がある場合
		IF CHANGE_PRICE.ORDER_NO IS NOT NULL THEN
			
			-- 受注トランザクションから帳合コードと受注日を取得
			SELECT BALANCE_CD,ORDER_DATE INTO TEMP_BALANCE_CD,TEMP_PROC_DATE FROM ORDER_HEAD WHERE ORDER_NO = CHANGE_PRICE.ORDER_NO;
			DBMS_OUTPUT.PUT_LINE('受注番号有　帳合コード:' || TEMP_BALANCE_CD || ' 処理日:' || TEMP_PROC_DATE);
			
		ELSE
			-- 納入先コードと品目コードを元に販売条件マスタから帳合コードを取得
			SELECT BALANCE_CD INTO TEMP_BALANCE_CD FROM SALES_TERMS WHERE SALES_TERMS.ITEM_CD = CHANGE_PRICE.ITEM_CD AND SALES_TERMS.DELIVERY_CD = CHANGE_PRICE.DELIVERY_CD;
			
			-- 処理日付に売上日を設定
			TEMP_PROC_DATE := CHANGE_PRICE.SALES_DATE;
			DBMS_OUTPUT.PUT_LINE('受注番号なし　帳合コード:' || TEMP_BALANCE_CD || ' 処理日:' || TEMP_PROC_DATE);
			
		END IF;

		DBMS_OUTPUT.PUT_LINE('帳合コード:' || TEMP_BALANCE_CD || ' 処理日付:' || TEMP_PROC_DATE);

		-- 上位帳合コードでさかのぼって処理を行う為のループ
		WHILE (1=1) LOOP

			-- 帳合コードか日付がなかった場合
			IF TEMP_BALANCE_CD IS NULL OR TEMP_PROC_DATE IS NULL THEN 
				DBMS_OUTPUT.PUT_LINE('帳合コード,処理日付が不正です' );
				EXIT;
			END IF;
			
			-- 帳合コードと品目コードと日付と見積単価ステータスが[3:承認済]で見積り単価マスタを検索
			SELECT COUNT(*) INTO TEMP_COUNT FROM ESTIMATE WHERE ESTIMATE.BALANCE_CD = TEMP_BALANCE_CD AND ESTIMATE.ITEM_CD = CHANGE_PRICE.ITEM_CD AND ESTIMATE.ESTIMATE_VALID_DATE_FROM <= TEMP_PROC_DATE AND TEMP_PROC_DATE <= ESTIMATE.ESTIMATE_VALID_DATE_TO AND ESTIMATE.ESTIMATE_STATUS = CONST_ESTIMATE_STATUS;
			
			-- 見積単価マスタに単価があった場合
			IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN

				SELECT * INTO TEMP_ESTIMATE FROM ESTIMATE WHERE ESTIMATE.BALANCE_CD = TEMP_BALANCE_CD AND ESTIMATE.ITEM_CD = CHANGE_PRICE.ITEM_CD AND ESTIMATE.ESTIMATE_VALID_DATE_FROM <= TEMP_PROC_DATE AND TEMP_PROC_DATE <= ESTIMATE.ESTIMATE_VALID_DATE_TO AND ESTIMATE.ESTIMATE_STATUS = CONST_ESTIMATE_STATUS;
				
				DBMS_OUTPUT.PUT_LINE('単価更新 売上番号:' || CHANGE_PRICE.SALES_NO);

				-- 有効単価で売上トランザクションを更新
				UPDATE SALES SET 
					SALES_UNITPRICE = TEMP_ESTIMATE.UNITPRICE,			-- 売上単価
					STANDARD_UNITPRICE = TEMP_ESTIMATE.STANDARD_UNIT_PRICE,		-- 標準単価
					STANDARD_DISCOUNT = TEMP_ESTIMATE.STANDARD_DISCOUNT,		-- 標準値引
					SPECIAL_DISCOUNT = TEMP_ESTIMATE.SPECIAL_DISCOUNT,		-- 特別値引
					TMP_UNITPRICE_FLG = 0,						-- 仮単価フラグ = [0:正単価]
					UPDATE_DATE = SYSDATE,						-- 更新日時
					UPDATOR_CD = I_TANTO_CD						-- 更新者ID
				WHERE SALES.SALES_NO = CHANGE_PRICE.SALES_NO;
				EXIT;
			ELSE

				DBMS_OUTPUT.PUT_LINE('日付を加えた検索条件にHITしませんでした');
				
				-- 帳合コードと品目コードで見積り単価マスタを検索
				SELECT COUNT(*) INTO TEMP_COUNT FROM ESTIMATE WHERE ESTIMATE.BALANCE_CD = TEMP_BALANCE_CD AND ESTIMATE.ITEM_CD = CHANGE_PRICE.ITEM_CD;
				
				-- 検索結果がある場合有効単価なし　検索結果がない場合上位帳合コードで単価取得処理
				IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN
					
					DBMS_OUTPUT.PUT_LINE('現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価があり有効期限範囲内にない場合有効単価なし');
					-- 現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価があり有効期限範囲内にない場合有効単価なし
					EXIT;
				ELSE
					-- 現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価がない場合、処理帳合コードの上位帳合コードで単価取得処理を行う
					SELECT COUNT(*) INTO TEMP_COUNT FROM BALANCE WHERE BALANCE.BALANCE_CD = TEMP_BALANCE_CD;
					
					-- 上位帳合コードがある場合単価取得処理
					IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN

						-- 上位帳合コードを処理用帳合コード変数に設定して単価取得処理
						SELECT UPPER_BALANCE_CD INTO TEMP_BALANCE_CD FROM BALANCE WHERE BALANCE.BALANCE_CD = TEMP_BALANCE_CD;

						DBMS_OUTPUT.PUT_LINE('現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価がなく有効期限範囲内にない場合上位帳合コードで単価検索　上位帳合:' || TEMP_BALANCE_CD);
					ELSE
						DBMS_OUTPUT.PUT_LINE('現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価がなく有効期限範囲内になく、上位帳合コードもない場合単価なし ');
						-- 上位帳合コードがない場合処理終了
						EXIT;
						
					END IF;
					
				END IF;
				
			END IF;

		END LOOP;
		
	END LOOP;

	RETURN 0;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		OUTPUT_ERROR_LOG('FUN_IF_PRODUCTION_RESULT_SUB',I_TANTO_CD,ERR_CODE,ERR_MSG);
		RETURN -9;


END;
/
