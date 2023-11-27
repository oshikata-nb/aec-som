CREATE OR REPLACE FUNCTION AP21.FNC_CALC_TAX
      (
             I_VENDER_CD		IN	VARCHAR2,	-- 取引先コード
             I_ITEM_CD			IN	VARCHAR2,	-- 品目コード
             IO_AMOUNT			IN OUT	NUMBER,		-- 売上金額(内税の場合金額の変更があるのでoutに変更）
             I_TANTO_CD			IN	VARCHAR2,	-- 担当者コード
             I_TARGET_DATE		IN	VARCHAR2,	-- 消費税算出日(2014/1/27 新規追加)
             O_TAX_DIVISION		IN OUT	NUMBER,		-- 消費税課税区分|==1:外税 2:内税 3:非課税
             O_TAX_AMOUNT		OUT	NUMBER,		-- 消費税額
             O_TAX_RATIO		IN OUT	NUMBER,		-- 消費税率
             O_ERROR_RETURN_CD		OUT	VARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		OUT	VARCHAR2	-- エラーメッセージを返す
      )

RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_MAKE_SALES_RECORD                                                     */
/*      処理内容      :      出荷指図番号から売上トランザクションレコードを作製		       、      */
/*      引数          :      I_VENDER_CD		取引先コード		NVARCHAR2型            */
/*      引数          :      I_ITEM_CD			品目コード		NVARCHAR2型            */
/*      引数          :      I_AMOUNT			金額			NUMBER型	       */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      I_TARGET_DATE		消費税算出日		NVARCHAR2型            */
/*      引数          :      O_TAX_DIVISION		消費税課税区分		NVARCHAR2型            */
/*      引数          :      O_TAX_AMOUNT		消費税率		NVARCHAR2型            */
/*      引数          :      O_TAX_RATIO		担当者コード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.05.29      1.00        Ohga                    新規作成                                   */
/*      2014.01.27      1.00        Ohga                    新消費税対応                               */
/*-----------------------------------------------------------------------------------------------------*/
	TEMP_COUNT		NUMBER;			-- 検索結果有無用カウント変数
	TEMP_TAX_DIVISION	NUMBER;			-- 消費税課税区分
	TEMP_TAX_CALC_DIVISION	NUMBER;			-- 消費税算出区分
	TEMP_TAX_RATIO		NUMBER;			-- 消費税率
	TEMP_TAX_ROUNDUP	NUMBER;			-- 消費税端数区分
	TEMP_TAX_ROUNDUP_UNIT	NUMBER;			-- 消費税端数単位
	TEMP_TAX_AMOUNT		NUMBER;			-- 消費税額
	TEMP_TRUNC_UP		NUMBER;			-- 切り上げ処理用
	TEMP_INVOICE_CD		VARCHAR2(20);		-- 請求先コード
	TEMP_SALES_AMOUNT	NUMBER;			-- 売上金額
	CONST_COMPANY_CD     	VARCHAR2(20) := '000001';	-- 自社マスタコード

BEGIN
	
	-- 請求先コードを取得
	SELECT COUNT(*) INTO TEMP_COUNT FROM VENDER WHERE VENDER.VENDER_CD = I_VENDER_CD AND VENDER.VENDER_DIVISION = 'TS';
	IF TEMP_COUNT = 0 OR TEMP_COUNT IS NULL THEN
		OUTPUT_ERROR_LOG('FNC_CALC_TAX',I_TANTO_CD,'','得意先コード:' || I_VENDER_CD || ' が取引先マスタにありません。' );
		RETURN -9;
	ELSE
		SELECT VENDER.PAYMENT_INVOICE_CD INTO TEMP_INVOICE_CD FROM VENDER WHERE VENDER.VENDER_CD = I_VENDER_CD AND VENDER.VENDER_DIVISION = 'TS';
	END IF;
	
	DBMS_OUTPUT.PUT_LINE('売上金額:' || IO_AMOUNT);
	TEMP_SALES_AMOUNT := IO_AMOUNT;		-- 金額設定
	DBMS_OUTPUT.PUT_LINE('売上金額:' || TEMP_SALES_AMOUNT);
	-- 消費税計算値を取得
	SELECT
		(CASE -- 販売品扱い属性.売上消費税課税区分＝4:取引先ﾏｽﾀの場合は次へ
			WHEN NVL(ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION,4) = 4 THEN  -- 販売品扱い属性にデータが存在しない場合の対応
				(CASE	-- 取引先ﾏｽﾀ.消費税区分＝4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税課税区分
					WHEN VENDER.TAX_DIVISION = 4 THEN (SELECT TAX_DIVISION FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD)
					ELSE VENDER.TAX_DIVISION	-- 取引先ﾏｽﾀ.消費税区分
				 END )
			ELSE ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION	-- 販売品扱い属性.売上消費税課税区分
		 END) AS TAX_DIVISION		   -- 消費税課税区分
	,	(CASE	-- 取引先ﾏｽﾀ.消費税算出区分=4:自社ﾏｽﾀの場合は自社ﾏｽﾀ.消費税算出区分
			WHEN VENDER.CALC_DIVISION = 4 THEN (SELECT CALC_DIVISION FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD)
			ELSE VENDER.CALC_DIVISION	-- 取引先ﾏｽﾀ.消費税算出区分
		 END) AS TAX_CALC_DIVISION		   -- 消費税算出区分
	,	(CASE	-- 取引先ﾏｽﾀ.売上消費税課税区分＝4の場合自社マスタから取得
			WHEN VENDER.TAX_DIVISION = 4 THEN (SELECT TAX_RATIO FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD)
			ELSE VENDER.TAX_RATIO	-- 取引先ﾏｽﾀ.消費税率
		 END) AS TAX_RATIO    -- 消費税率
	,	(CASE	-- 取引先マスタ.消費税端数区分 = [4:自社マスタ]の場合自社マスタから取得
			WHEN VENDER.TAX_ROUNDUP = 4 THEN (SELECT TAX_ROUNDUP FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD)
			ELSE VENDER.TAX_ROUNDUP	-- 取引先ﾏｽﾀ.消費税端数区分
		 END) AS TAX_ROUNDUP    -- 消費税率
	,	(CASE	-- 取引先マスタ.消費税端数区分 = [8:自社マスタ]の場合自社マスタから取得
				WHEN VENDER.TAX_ROUNDUP_UNIT = 8 THEN (SELECT TAX_ROUNDUP_UNIT FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD)
				ELSE VENDER.TAX_ROUNDUP_UNIT	-- 取引先ﾏｽﾀ.消費税率
		 END) AS TAX_ROUNDUP_UNIT    -- 消費税端数単位
		INTO TEMP_TAX_DIVISION,TEMP_TAX_CALC_DIVISION,TEMP_TAX_RATIO,TEMP_TAX_ROUNDUP,TEMP_TAX_ROUNDUP_UNIT
	FROM
		(SELECT TAX_DIVISION,CALC_DIVISION,TAX_RATIO,TAX_ROUNDUP,TAX_ROUNDUP_UNIT FROM VENDER WHERE VENDER_DIVISION = 'TS' AND VENDER.VENDER_CD = TEMP_INVOICE_CD) VENDER
	,	(SELECT ARTICLE_ATTRIBUTE_QUEUE.* FROM ARTICLE_ATTRIBUTE_QUEUE,ITEM WHERE ITEM.ITEM_CD = I_ITEM_CD AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+) AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)) ARTICLE_ATTRIBUTE_QUEUE ;-- 販売品扱い属性キュー

-- >	2014/1/27 新消費税対応
	-- 日付がNULLの場合（正単価更新の場合)
	IF I_TARGET_DATE IS NULL THEN
		TEMP_TAX_DIVISION := O_TAX_DIVISION;
		TEMP_TAX_RATIO  := O_TAX_RATIO;
	ELSE
		-- 品目マスタから消費税課税区分を取得する
		SELECT ARTICLE_ATTRIBUTE_QUEUE.TAX_DIVISION INTO TEMP_TAX_DIVISION FROM ARTICLE_ATTRIBUTE_QUEUE,ITEM WHERE ITEM.ITEM_CD = I_ITEM_CD AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+) AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+);
		-- 消費税算出日から消費税率を取得する
		TEMP_TAX_RATIO := FUN_GET_TAX_RATIO(I_TARGET_DATE);
	END IF;

-- <	2014/1/27 新消費税対応


	DBMS_OUTPUT.PUT_LINE('売上金額:' || TEMP_SALES_AMOUNT || ' 消費税課税区分:' || TEMP_TAX_DIVISION || ' 消費税算出区分:' || TEMP_TAX_CALC_DIVISION || ' 消費税率:' || TEMP_TAX_RATIO || ' 消費税端数処理:' || TEMP_TAX_ROUNDUP || ' 消費税端数単位:' || TEMP_TAX_ROUNDUP_UNIT);

	TEMP_TAX_AMOUNT := 0;

	-- 消費税算出区分が[1:明細単位] または　消費税課税区分が[3:非課税]以外の場合消費税算出
	IF TEMP_TAX_CALC_DIVISION = 1 AND TEMP_TAX_DIVISION <> 3 THEN

		DBMS_OUTPUT.PUT_LINE('売上金額:' || TEMP_SALES_AMOUNT || ' 消費税率:' || TEMP_TAX_RATIO);
		
		-- 消費税課税区分が[1:外税]
		IF TEMP_TAX_DIVISION = 1 THEN
			TEMP_TAX_AMOUNT := TEMP_SALES_AMOUNT * TEMP_TAX_RATIO / 100;	-- 外税計算
			DBMS_OUTPUT.PUT_LINE('外税:' || TEMP_TAX_AMOUNT);

			TEMP_TAX_AMOUNT := FNC_CHECK_ROUND(TEMP_TAX_AMOUNT,'TAX_AMOUNT','TS',I_VENDER_CD,NULL);		-- 消費税端数計算
			DBMS_OUTPUT.PUT_LINE('外税:' || TEMP_TAX_AMOUNT);

		-- 消費税課税区分が[2:内税]
		ELSIF TEMP_TAX_DIVISION = 2 THEN
			DBMS_OUTPUT.PUT_LINE('内税');
			TEMP_TAX_AMOUNT := TEMP_SALES_AMOUNT * TEMP_TAX_RATIO / ( TEMP_TAX_RATIO + 100); 		-- 内税計算
			TEMP_TAX_AMOUNT := FNC_CHECK_ROUND(TEMP_TAX_AMOUNT,'TAX_AMOUNT','TS',I_VENDER_CD,NULL);		-- 消費税端数計算
			IO_AMOUNT := TEMP_SALES_AMOUNT - TEMP_TAX_AMOUNT;						-- 内税の為売上金額変更
			DBMS_OUTPUT.PUT_LINE('内税:' || TEMP_TAX_AMOUNT);

		END IF;
	END IF;
	
	O_TAX_DIVISION := TEMP_TAX_DIVISION;
	O_TAX_AMOUNT := TEMP_TAX_AMOUNT;
	O_TAX_RATIO := TEMP_TAX_RATIO;

	RETURN 0;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		OUTPUT_ERROR_LOG('FNC_CALC_TAX',I_TANTO_CD,O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG);
	RETURN -9;


END;
/
