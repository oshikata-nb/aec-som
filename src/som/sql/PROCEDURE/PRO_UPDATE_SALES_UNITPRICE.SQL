CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_SALES_UNITPRICE
      (
             I_TANTO_CD			IN	NVARCHAR2,	-- 担当者コード
             O_ERROR_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		OUT	NVARCHAR2	-- 戻り値
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                  */
/*      名称          :      PRO_UPDATE_SALES_UNITPRICE                                                */
/*      処理内容      :      仮単価の売上データの単価洗替えを行う			       、      */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      引数          :      O_RETURN_CD		戻り値			NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.17      1.00        Ohga                    新規作成                                   */
/*      2014.02.10      1.01        Ohga                    新消費税対応                               */
/*-----------------------------------------------------------------------------------------------------*/
	TEMP_BALANCE_CD		VARCHAR2(20);		-- 帳合コード
	TEMP_ITEM_CD		VARCHAR2(20);		-- 品目コード
	TEMP_PROC_DATE		DATE;			-- 処理日付
	TEMP_ESTIMATE		ESTIMATE%ROWTYPE;	-- 見積単価ヘッダデータ
	TEMP_COUNT		NUMBER;
	TEMP_SALES_QUANTITY	NUMBER;			-- 売上数量
	TEMP_SALES_AMOUNT	NUMBER;			-- 売上金額
	CONST_ESTIMATE_STATUS	NUMBER := 3;		-- 有効な見積単価ステータス[3:承認済み]
	TEMP_TAX_DIVISION	NUMBER;			-- 消費税課税区分
	TEMP_TAX_RATIO		NUMBER;			-- 消費税率
	TEMP_TAX_AMOUNT		NUMBER;			-- 消費税額
	TEMP_RET		NUMBER;			-- 消費税算出関数戻り値

	TEMP_SALES_UNITPRICE	NUMBER;
	TEMP_STANDARD_UNITPRICE NUMBER;
	TEMP_STANDARD_DISCOUNT	NUMBER;
	TEMP_SPECIAL_DISCOUNT	NUMBER;
	
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
			SALES.TMP_UNITPRICE_FLG = 1 AND SALES.BALANCE_CD IS NOT NULL
		ORDER BY SALES.SALES_NO ASC;
BEGIN
	O_RETURN_CD := 0;
	-- 受注明細毎に出荷指図自動作成処理
   	FOR CHANGE_PRICE IN PROC_CHANGE_UNITPRICE LOOP
	
		DBMS_OUTPUT.PUT_LINE('売上番号:' || CHANGE_PRICE.SALES_NO);
		-- 売上数量を取得
		SELECT SALES_QUANTITY INTO TEMP_SALES_QUANTITY FROM SALES WHERE SALES.SALES_NO = CHANGE_PRICE.SALES_NO;
		
		-- 受注番号がある場合
		IF CHANGE_PRICE.ORDER_NO IS NOT NULL THEN
			
			-- 受注トランザクションから受注日を取得
			SELECT ORDER_DATE INTO TEMP_PROC_DATE FROM ORDER_HEAD WHERE ORDER_NO = CHANGE_PRICE.ORDER_NO;
			
		ELSE
			-- 処理日付に売上日を設定
			TEMP_PROC_DATE := CHANGE_PRICE.SALES_DATE;
			
		END IF;
		-- 帳合コードを設定
		TEMP_BALANCE_CD := CHANGE_PRICE.BALANCE_CD;
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

				TEMP_STANDARD_UNITPRICE := FNC_CHECK_ROUND(TEMP_ESTIMATE.STANDARD_UNIT_PRICE,'URTANKA','TS',CHANGE_PRICE.VENDER_CD,NULL);		-- 標準単価
				TEMP_STANDARD_DISCOUNT := FNC_CHECK_ROUND(TEMP_ESTIMATE.STANDARD_DISCOUNT,'URTANKA','TS',CHANGE_PRICE.VENDER_CD,NULL);		-- 標準値引
				TEMP_SPECIAL_DISCOUNT := FNC_CHECK_ROUND(TEMP_ESTIMATE.SPECIAL_DISCOUNT,'URTANKA','TS',CHANGE_PRICE.VENDER_CD,NULL);		-- 特別値引
				
				TEMP_SALES_UNITPRICE := FNC_CHECK_ROUND(TEMP_ESTIMATE.UNITPRICE,'URTANKA','TS',CHANGE_PRICE.VENDER_CD,NULL);	-- 単価
				-- 売上金額 = 売上単価 * 売上数量
				TEMP_SALES_AMOUNT := TEMP_SALES_UNITPRICE * TEMP_SALES_QUANTITY;
				
				-- 売上金額算出（受注金額）
				TEMP_SALES_AMOUNT := FNC_CHECK_ROUND(TEMP_SALES_AMOUNT,'URKINGAKU','TS',CHANGE_PRICE.VENDER_CD,NULL);		-- 売上金額端数計算
				
				-- 消費税算出処理
				TEMP_RET := FNC_CALC_TAX(CHANGE_PRICE.VENDER_CD,	-- 得意先コード
							CHANGE_PRICE.ITEM_CD,		-- 品目コード
							TEMP_SALES_AMOUNT,		-- 売上金額
							I_TANTO_CD,			-- 担当者コード
							NULL,				-- 売上日 2014/2/5 新消費税対応
							CHANGE_PRICE.TAX_DIVISION,	-- 消費税区分
							TEMP_TAX_AMOUNT,		-- 売上金額
							CHANGE_PRICE.TAX_RATIO,		-- 消費税率
							O_ERROR_RETURN_CD,		-- エラーコード
							O_ERROR_RETURN_MSG);		-- エラーメッセージ
				DBMS_OUTPUT.PUT_LINE('得意先:' || CHANGE_PRICE.VENDER_CD ||' 消費税率:' || TEMP_TAX_AMOUNT || ' 売金:' || TEMP_SALES_AMOUNT ||
							'品目:' || CHANGE_PRICE.ITEM_CD);
				-- 有効単価で売上トランザクションを更新
				UPDATE SALES SET 

					SALES_UNITPRICE = TEMP_SALES_UNITPRICE,			-- 売上単価
					STANDARD_UNITPRICE = TEMP_STANDARD_UNITPRICE,		-- 標準単価
					STANDARD_DISCOUNT = TEMP_STANDARD_DISCOUNT,		-- 標準値引
					SPECIAL_DISCOUNT = TEMP_SPECIAL_DISCOUNT,		-- 特別値引
					SALES_AMOUNT = TEMP_SALES_AMOUNT,				-- 売上金額
					TAX_AMOUNT = TEMP_TAX_AMOUNT,					-- 消費税額
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
					
					DBMS_OUTPUT.PUT_LINE('現在処理を行っている帳合コード、品目コードで見積単価マスタを検索し、見積単価があり有効期限範囲内にない場合有効単価なし 品目コード:' || CHANGE_PRICE.ITEM_CD || ' 帳合コード:' || TEMP_BALANCE_CD);
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
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    	O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		O_RETURN_CD := -9;
END;
/
