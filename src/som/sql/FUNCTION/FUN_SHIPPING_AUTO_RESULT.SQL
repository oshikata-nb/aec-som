CREATE OR REPLACE FUNCTION      FUN_SHIPPING_AUTO_RESULT
(
             I_JISSEKI_SEIHIN		IN	JISSEKI_SEIHIN%ROWTYPE,	-- 品目コード
             O_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
             O_RETURN_MSG		OUT	NVARCHAR2	-- エラーメッセージを返す
)
RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_SHIPPING_AUTO_RESULT						       */
/*      処理内容      :      出荷予定日を元に積出ナンバーを付番する			       、      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	
	TEMP_COUNT		NUMBER;
	TEMP_RESULT_QTY		NUMBER;
	TEMP_SHIPPING		SHIPPING%ROWTYPE;	-- 出荷ヘッダ
	TEMP_SHIPPING_DETAIL	SHIPPING_DETAIL%ROWTYPE;-- 品目明細
	TEMP_LOT_NO		VARCHAR2(20);		-- ロット番号
	TEMP_NEW_ROW_NO		NUMBER;			-- 新規行番号
	TEMP_ORDER_QTY_SUM 	NUMBER;			-- 出荷指図量
	TEMP_RESULT_QTY_SUM 	NUMBER;			-- 出荷実績量
	TEMP_RET		NUMBER;			-- 売上レコード作製処理戻り値
	TEMP_ROW_NO		NUMBER;			-- 出荷行番号
	TEMP_STOCK_RETURN	VARCHAR2(255);		-- 在庫プロシージャ戻り値
	TEMP_RETURN_CD		VARCHAR2(100);		-- エラーコード
	TEMP_RETURN_MSG		VARCHAR2(2000);		-- エラーメッセージ
	CONST_COMP		VARCHAR2(20) := 'COMPLETE';	-- 在庫プロシージャ完了戻り値
	CONST_UPDATE_USER	VARCHAR2(20) := 'AUTO';	-- 更新ユーザー名

BEGIN

	DBMS_OUTPUT.PUT_LINE('出荷実績受信処理 品目コード:' || I_JISSEKI_SEIHIN.SEIHINCODE || ' ロケーション:' || I_JISSEKI_SEIHIN.LOCATION || ' 日付:' || I_JISSEKI_SEIHIN.JIKOKU );
	
	-- 出荷詳細に製品出庫指図書バーコードがあるかチェック(出荷ステータス = 3 OR 4 かつ 出荷明細.完納フラグ = 0 )
	SELECT COUNT(*) INTO TEMP_COUNT FROM SHIPPING,SHIPPING_DETAIL WHERE SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO AND SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC = TRIM(I_JISSEKI_SEIHIN.SEISHUKKOSABC) AND (SHIPPING.SHIPPING_STATUS = 3 OR SHIPPING.SHIPPING_STATUS = 4) AND SHIPPING_DETAIL.DELIVERY_COMP = 0;
	IF TEMP_COUNT < 1 THEN	-- 出荷詳細に製品出庫指図書があり同一の製品出庫指図書バーコードがない場合

		O_RETURN_MSG := '製品出庫指図書バーコード:' || I_JISSEKI_SEIHIN.SEISHUKKOSABC || ' が出荷トランザクションにありません';
		RETURN -1;
	END IF;

	-- 実績のロットがHかP以外であがってきた場合
	IF SUBSTR(I_JISSEKI_SEIHIN.HOSOSASHIZUNO,1,1) <> 'H' AND SUBSTR(I_JISSEKI_SEIHIN.NYUKABC,1,1) <> 'P' THEN

		O_RETURN_MSG := '計装IFからの実績データの製造指図番号:' || I_JISSEKI_SEIHIN.HOSOSASHIZUNO || 'と 入荷バーコード:' || I_JISSEKI_SEIHIN.NYUKABC || ' が[H]または[P]で始まっていません。';
		RETURN -1;

	END IF;

	-- 出荷明細を保持
	SELECT * INTO TEMP_SHIPPING_DETAIL FROM SHIPPING_DETAIL WHERE ROWNUM = 1 AND SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC = TRIM(I_JISSEKI_SEIHIN.SEISHUKKOSABC);
	
	-- 出荷ヘッダのデータを保持
	SELECT * INTO TEMP_SHIPPING FROM SHIPPING WHERE SHIPPING.SHIPPING_NO = TEMP_SHIPPING_DETAIL.SHIPPING_NO;

	-- 品目コードが同一の場合のみ処理を行う
	IF TRIM(I_JISSEKI_SEIHIN.SEIHINCODE) <> TEMP_SHIPPING.ITEM_CD THEN	-- 計装IFからあがってきた実績と出荷ヘッダの品目が同じである場合のみ処理

		O_RETURN_MSG := '指図を出した品目と異なる品目の実績が上がってきました' || ' 指図:' || TEMP_SHIPPING.ITEM_CD || ' 実績:' || I_JISSEKI_SEIHIN.SEIHINCODE;
		RETURN -1;
	END IF;

	-- 製品入出庫実績.包装指図番号が'H'始まりの場合処理
	IF SUBSTR(I_JISSEKI_SEIHIN.HOSOSASHIZUNO,1,1) = 'H' THEN
		TEMP_LOT_NO := TRIM(I_JISSEKI_SEIHIN.HOSOSASHIZUNO);
		DBMS_OUTPUT.PUT_LINE('実績ロットが包装指図番号:' || TRIM(I_JISSEKI_SEIHIN.HOSOSASHIZUNO));
	ELSE
		TEMP_LOT_NO := TRIM(I_JISSEKI_SEIHIN.NYUKABC);
		DBMS_OUTPUT.PUT_LINE('実績ロットが入荷バーコード:' || TRIM(I_JISSEKI_SEIHIN.NYUKABC));
	END IF;

	-- 在庫プロシージャコール(全取消)
	ZAIKOUKEHARAI.ENTRY_SHIPPING(4,					-- 取消
				TEMP_SHIPPING.SHIPPING_NO,		-- 出荷番号
				NULL,					-- 出荷行番号
				CONST_UPDATE_USER,			-- 更新者
				TEMP_STOCK_RETURN);			-- 戻り値

	-- 在庫更新成否で分岐
	IF TEMP_STOCK_RETURN <> CONST_COMP THEN	-- 在庫更新失敗
		
		O_RETURN_MSG := '出荷番号:' || TEMP_SHIPPING_DETAIL.SHIPPING_NO || ' 出荷行番号:' || TEMP_ROW_NO || ' の出荷確定在庫確定取消プロシージャでエラー発生' || '在庫プロシージャ戻り値:' || TEMP_STOCK_RETURN;
		RETURN -1;
		
	END IF;

	-- 出荷詳細を製品入出庫実績とロケーション、ロット番号で検索し、データがあるかどうかで分岐
	SELECT COUNT(*) INTO TEMP_COUNT FROM SHIPPING_DETAIL WHERE SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC = TRIM(I_JISSEKI_SEIHIN.SEISHUKKOSABC) AND SHIPPING_DETAIL.LOT_NO = TEMP_LOT_NO AND SHIPPING_DETAIL.LOCATION_CD =  TRIM(I_JISSEKI_SEIHIN.LOCATION);
	IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN	-- 実績と指図が同様のデータがあがってきた場合
			
		-- 出荷指図を保持
		SELECT * INTO TEMP_SHIPPING_DETAIL FROM SHIPPING_DETAIL WHERE SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC = TRIM(I_JISSEKI_SEIHIN.SEISHUKKOSABC) AND SHIPPING_DETAIL.LOT_NO = TEMP_LOT_NO AND SHIPPING_DETAIL.LOCATION_CD =  TRIM(I_JISSEKI_SEIHIN.LOCATION);
		
		-- 実績量累計を保持
		TEMP_RESULT_QTY := TEMP_SHIPPING_DETAIL.SHIPPING_RESULT_QUANTITY + I_JISSEKI_SEIHIN.SUURYOU;
		
		-- 出荷指図量 <= 実績量である場合
		IF TEMP_SHIPPING_DETAIL.SHIPPING_INSTRUCTION <= TEMP_RESULT_QTY THEN	-- 出荷明細の出荷完了
			
			-- 出荷実績量累計と、出荷完了フラグをONにして更新
			UPDATE SHIPPING_DETAIL 
			SET SHIPPING_RESULT_QUANTITY = TEMP_RESULT_QTY,
			SHIPPING_RESULT_DATE = TO_CHAR(SYSDATE,'YYYY/MM/DD'),	-- 出荷実績日
			DELIVERY_COMP = 1,
			LOT_NO = TEMP_LOT_NO,
			UPDATE_DATE = SYSDATE,
			UPDATOR_CD = CONST_UPDATE_USER
	
			WHERE SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING_DETAIL.SHIPPING_NO AND SHIPPING_DETAIL.SHIPPING_ROW_NO = TEMP_SHIPPING_DETAIL.SHIPPING_ROW_NO;
		ELSE									-- 出荷明細の出荷が完了していない
			
			-- 出荷実績量を更新
			UPDATE SHIPPING_DETAIL 
			SET SHIPPING_RESULT_QUANTITY = TEMP_RESULT_QTY ,
			SHIPPING_RESULT_DATE = TO_CHAR(SYSDATE,'YYYY/MM/DD'),	-- 出荷実績日
			LOT_NO = TEMP_LOT_NO,
			UPDATE_DATE = SYSDATE,
			UPDATOR_CD = CONST_UPDATE_USER
			WHERE SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING_DETAIL.SHIPPING_NO AND SHIPPING_DETAIL.SHIPPING_ROW_NO = TEMP_SHIPPING_DETAIL.SHIPPING_ROW_NO;
			
		END IF;

		-- 新規と更新で出荷行番号が違うので保持
		TEMP_ROW_NO := TEMP_SHIPPING_DETAIL.SHIPPING_ROW_NO;
	ELSE	-- IFのロケーションと出荷詳細のロケーションが異なる
	
		DBMS_OUTPUT.PUT_LINE('実績と指図でロケーションかロットが異なる 実績ロケ:' || I_JISSEKI_SEIHIN.LOCATION || ' 指図ロケ:' || TEMP_SHIPPING_DETAIL.LOCATION_CD );
	
		-- 実績量累計を保持
		TEMP_RESULT_QTY := I_JISSEKI_SEIHIN.SUURYOU;
		
		-- 新規の場合の出荷番号を取得
		SELECT MAX(SHIPPING_ROW_NO) + 1 INTO TEMP_NEW_ROW_NO FROM SHIPPING_DETAIL WHERE SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING_DETAIL.SHIPPING_NO;
	
		INSERT INTO SHIPPING_DETAIL(						
			SHIPPING_NO,			-- 出荷番号
			SHIPPING_ROW_NO,		-- 行番号(出荷)
			ROW_NO,				-- 行番号(未使用)
			LOT_NO,				-- ロット番号
			SHIPPING_INSTRUCTION,		-- 出荷指図数量
			SHIPPING_RESULT_DATE,		-- 出荷実績日
			SHIPPING_RESULT_QUANTITY,	-- 出荷実績数
			SHIPPING_STATUS,		-- 出荷詳細ステータス|1:未出荷,2:出荷処理中,3:出荷完了
			SUMMERY,			-- 摘要
			LOCATION_CD,			-- ロケーションコード
			DELIVERY_COMP,			-- 完納区分
			PRODUCT_OUT_ORDER_BC,		-- 製品出庫指図書バーコード
			INPUT_DATE,			-- 登録日時
			INPUTOR_CD,			-- 登録者ID
			UPDATE_DATE,			-- 更新日時
			UPDATOR_CD			-- 更新者ID
		)VALUES(
			
			TEMP_SHIPPING_DETAIL.SHIPPING_NO,	-- 出荷番号
			TEMP_NEW_ROW_NO,			-- 行番号(出荷)
			TEMP_SHIPPING_DETAIL.ROW_NO,		-- 行番号(未使用)
			TEMP_LOT_NO,				-- ロット番号
			0,					-- 出荷指図数量
			TO_CHAR(SYSDATE,'YYYY/MM/DD'),		-- 出荷実績日
			TEMP_RESULT_QTY,			-- 出荷実績数
			0,					-- 出荷詳細ステータス|1:未出荷,2:出荷処理中,3:出荷完了
			TEMP_SHIPPING_DETAIL.SUMMERY,		-- 摘要
			TRIM(I_JISSEKI_SEIHIN.LOCATION),		-- ロケーションコード
			0,					-- 完納区分
			TEMP_SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC,-- 製品出庫指図書バーコード
			SYSDATE,			-- 登録日時
			CONST_UPDATE_USER,				-- 登録者ID
			SYSDATE,			-- 更新日時
			CONST_UPDATE_USER				-- 更新者ID
		);
		-- 新規と更新で出荷行番号が違うので保持
		TEMP_ROW_NO := TEMP_NEW_ROW_NO;
		
	END IF;

	-- 出荷ステータス=[3:指図送信済み]の場合
	IF TEMP_SHIPPING.SHIPPING_STATUS = 3 THEN
		
		-- 出荷ステータス=[4:実績受信済み]に変更
		UPDATE SHIPPING SET SHIPPING_STATUS = 4 WHERE SHIPPING.SHIPPING_NO = TEMP_SHIPPING.SHIPPING_NO;
		
	END IF;


	-- 在庫プロシージャコール(全確定)
	ZAIKOUKEHARAI.ENTRY_SHIPPING(3,					-- 確定
				TEMP_SHIPPING.SHIPPING_NO,		-- 出荷番号
				NULL,					-- 出荷行番号
				CONST_UPDATE_USER,			-- 更新者
				TEMP_STOCK_RETURN);			-- 戻り値
	
	-- 在庫更新成否で分岐
	IF TEMP_STOCK_RETURN <> CONST_COMP THEN	-- 在庫更新失敗
		
		O_RETURN_MSG := '出荷番号:' || TEMP_SHIPPING_DETAIL.SHIPPING_NO || ' 出荷行番号:' || TEMP_ROW_NO || ' の出荷確定在庫更新プロシージャでエラー発生' || '在庫プロシージャ戻り値:' || TEMP_STOCK_RETURN;
		RETURN -1;
		
	END IF;

	-- 出荷指図数量累計
	SELECT SUM(SHIPPING_INSTRUCTION) INTO TEMP_ORDER_QTY_SUM FROM SHIPPING_DETAIL WHERE SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING.SHIPPING_NO;
	
	-- 出荷実績数量累計
	SELECT SUM(SHIPPING_RESULT_QUANTITY) INTO TEMP_RESULT_QTY_SUM FROM SHIPPING_DETAIL WHERE SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING.SHIPPING_NO;
	
	-- 指図量累計 <= 実績量累計である場合
	IF TEMP_ORDER_QTY_SUM <= TEMP_RESULT_QTY_SUM THEN	-- 出荷完了の場合
		
		-- 出荷ヘッダ.出荷ステータス=[5:出荷完了]に変更
		UPDATE SHIPPING 
		SET 
			SHIPPING_STATUS = 5 ,		-- [5:出荷完了]
			SHIPPING_RESULT_DATE = TO_CHAR(SYSDATE,'YYYY/MM/DD'),	-- 出荷完了日
			DELIVERY_COMP = 1,		-- [1:完納]
			UPDATE_DATE = SYSDATE,
			UPDATOR_CD = CONST_UPDATE_USER
		WHERE 
			SHIPPING.SHIPPING_NO = TEMP_SHIPPING.SHIPPING_NO;
		
		-- 出荷詳細の完納フラグを全てｏｎとする
		UPDATE SHIPPING_DETAIL 
		SET 
			DELIVERY_COMP = 1,		-- [1:完納]
			SHIPPING_RESULT_DATE = TO_CHAR(SYSDATE,'YYYY/MM/DD'),	-- 出荷完了日
			UPDATE_DATE = SYSDATE,
			UPDATOR_CD = CONST_UPDATE_USER
		WHERE 
			SHIPPING_DETAIL.SHIPPING_NO = TEMP_SHIPPING.SHIPPING_NO;
		
		-- 売上トランザクションを新規に作成処理
		PRO_MAKE_SALES_RECORD(TEMP_SHIPPING.SHIPPING_NO,CONST_UPDATE_USER,TEMP_RETURN_CD,TEMP_RETURN_MSG,TEMP_RET);
		IF TEMP_RET <> 0 THEN
			O_RETURN_MSG := '出荷完了時の売上トランザクション作成処理でエラー発生 売上トランザクション作成プロシージャエラーコード:' || TEMP_RETURN_CD || ' エラーメッセージ:' || TEMP_RETURN_MSG;
			RETURN -1;
		END IF;

		-- 出荷完了在庫プロシージャコール
		ZAIKOUKEHARAI.ENTRY_SHIPPING(5,					-- 完了
					TEMP_SHIPPING.SHIPPING_NO,		-- 出荷番号
					NULL,					-- 出荷行番号
					CONST_UPDATE_USER,			-- 更新者
					TEMP_STOCK_RETURN);			-- 戻り値
		
		-- 在庫更新成否で分岐
		IF TEMP_STOCK_RETURN <> CONST_COMP THEN	-- 在庫更新失敗
			
			O_RETURN_MSG := '出荷番号:' || TEMP_SHIPPING_DETAIL.SHIPPING_NO || ' の出荷完了在庫更新プロシージャでエラー発生' || '在庫プロシージャ戻り値:' || TEMP_STOCK_RETURN;
			RETURN -1;
			
		END IF;

	END IF;
	RETURN 0;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		
		-- SQLエラーコード、エラーメッセージを取得
	    	O_RETURN_CD := SQLCODE;
		O_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		RETURN -9;
END;
/
