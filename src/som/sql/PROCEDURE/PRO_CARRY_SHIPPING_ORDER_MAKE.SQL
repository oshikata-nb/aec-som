CREATE OR REPLACE PROCEDURE      PRO_CARRY_SHIPPING_ORDER_MAKE
      (
             I_SHIPPING_DATE		IN	NVARCHAR2,	-- 出荷予定日
             I_TANTO_CD			IN	NVARCHAR2,	-- 担当者コード
             O_ERROR_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		OUT	NVARCHAR2	-- 戻り値
      )

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_CARRY_SHIPPING_ORDER_MAKE                                             */
/*      処理内容      :      出荷予定日を元に積出ナンバーを付番する			       、      */
/*      引数          :      I_SHIPPING_DATE		出荷予定日		NVARCHAR2型            */
/*      引数          :      I_SENDING_OFF_NO		積出ナンバー		NVARCHAR2型            */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      O_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	
	TEMP_COUNT		NUMBER;			
	TEMP_FLAN_CHAR		VARCHAR2(10);		-- FLAN運送店コード
	TEMP_FLAN_NO		NUMBER;			-- FLAN運送店番号
	TEMP_CARRY_CD		VARCHAR2(20);		-- 運送会社コード
	TEMP_ITEM_TABLE		ITEM%ROWTYPE;		-- 品目テーブル
	TEMP_OTHER_CO1		VARCHAR2(20);		-- 他社コード1
	TEMP_SHIPPER_DIVISION   VARCHAR2(20);		-- 荷主

	-- 処理対処をループ処理するカーソルを作成
	-- ①出荷ヘッダ.出荷ステータス = [2:出荷指図確定済]
	-- ②出荷ヘッダ.積出ナンバー = NULL
	-- ③出荷ヘッダ.出荷予定日 = 引数[I_SHIPPING_DATE]
	-- ④ロケーション選択
	CURSOR PROC_SHIPPING IS 
		SELECT 
			DISTINCT(P_SHIPPING.CARRY_CD)
		FROM 
			(SELECT SHIPPING.* FROM SHIPPING,ORDER_HEAD WHERE SHIPPING.ORDER_NO = ORDER_HEAD.ORDER_NO AND (ORDER_HEAD.ORDER_DIVISION = 1 OR ORDER_HEAD.ORDER_DIVISION = 2)
			UNION
			SELECT SHIPPING.* FROM SHIPPING WHERE SHIPPING.ORDER_NO IS NULL) P_SHIPPING,
			SHIPPING_DETAIL ,CARRY 
		WHERE 
			P_SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO 
			AND P_SHIPPING.CARRY_CD = CARRY.CARRY_CD
			AND P_SHIPPING.SHIPPING_STATUS = 2				-- ①
			AND P_SHIPPING.SENDING_OFF_NUMBER IS NULL 			-- ②
			AND P_SHIPPING.SCHEDULED_SHIPPING_DATE = I_SHIPPING_DATE	-- ③
			AND (SHIPPING_DETAIL.LOCATION_CD IN (SELECT LOCATION.LOCATION_CD FROM (SELECT * FROM LOCATION WHERE UPPER_LOCATION_CD = 'BK' AND LOCATION_LEVEL = 3) TEMP_LOCATION,LOCATION WHERE LOCATION.UPPER_LOCATION_CD LIKE TEMP_LOCATION.LOCATION_CD || '%' AND LOCATION.AVAILABLE_FLG = 1 AND LOCATION.LOCATION_LEVEL = 4)
			OR  SHIPPING_DETAIL.LOCATION_CD IN (SELECT LOCATION.LOCATION_CD FROM (SELECT * FROM LOCATION WHERE UPPER_LOCATION_CD = 'BA' AND LOCATION_LEVEL = 3) TEMP_LOCATION,LOCATION WHERE LOCATION.UPPER_LOCATION_CD LIKE TEMP_LOCATION.LOCATION_CD || '%' AND LOCATION.AVAILABLE_FLG = 1 AND LOCATION.LOCATION_LEVEL = 4) );

BEGIN
	
	O_RETURN_CD := 0;
	-- 発行済みでは無いデータを削除
	DELETE FROM SHIPPING_TEMP WHERE SHIPPING_TEMP.SHIPPING_INSTRUCT_DATE = I_SHIPPING_DATE AND SHIPPING_TEMP.SHIPPING_ORDER_SEND_COMP_FLAG = 0;
	
	-- 品目ごとに処理
   	FOR PROC_CARRY IN PROC_SHIPPING LOOP

		DBMS_OUTPUT.PUT_LINE('運送会社コード:' || PROC_CARRY.CARRY_CD);
		
		-- FLAN運送店コードを取得
		SELECT SUBSTR(CARRY.FLAN_TRANS_SHOP_CD,1,2) INTO TEMP_FLAN_CHAR FROM CARRY WHERE CARRY.CARRY_CD = PROC_CARRY.CARRY_CD;
		
		-- FLAN運送店コードの長さをチェック
		SELECT LENGTH(TEMP_FLAN_CHAR) INTO TEMP_COUNT FROM DUAL;

		-- 二桁より短い場合エラー
		IF TEMP_COUNT IS NULL OR TEMP_COUNT < 2 THEN
			O_ERROR_RETURN_MSG := '運送会社コード:' || PROC_CARRY.CARRY_CD || ' FLAN運送店コード:' || TEMP_FLAN_CHAR || ' が異常です';
			O_RETURN_CD := -2;
			EXIT;
		END IF;
		
		-- 同一出荷予定日で同一運送店がすでにあるかチェック
		SELECT COUNT(*) INTO TEMP_COUNT FROM SHIPPING_TEMP WHERE SUBSTR(SHIPPING_TEMP.SENDING_OFF_NUMBER,1,2) = TEMP_FLAN_CHAR AND SHIPPING_TEMP.SHIPPING_INSTRUCT_DATE = I_SHIPPING_DATE AND SHIPPING_TEMP.SHIPPING_ORDER_SEND_COMP_FLAG = 1;
		IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN	-- すでにある場合
			
			-- 連番が９超えをする場合
			IF TEMP_COUNT >= 9 THEN
				O_ERROR_RETURN_MSG := '出荷予定日:' || I_SHIPPING_DATE || ' 運送会社コード:' || PROC_CARRY.CARRY_CD || ' 積出ナンバー上二桁:' || TEMP_FLAN_CHAR || ' の連番が９を超えました。';
				O_RETURN_CD := -1;
				EXIT;
			END IF;
			-- 続きから連番
			TEMP_FLAN_CHAR := TEMP_FLAN_CHAR || 'A' || TO_CHAR(TEMP_COUNT + 1) ; 
			
		ELSE							-- まだない場合
			
			-- 連番一番から開始
			TEMP_FLAN_CHAR := TEMP_FLAN_CHAR || 'A1'; 
			
		END IF;

		DBMS_OUTPUT.PUT_LINE('積出ナンバー:' || TEMP_FLAN_CHAR);
		
		-- テーブルにデータ挿入
		INSERT INTO SHIPPING_TEMP(
			SHIPPING_INSTRUCT_DATE,		-- 出荷予定日
			VENDER_CD,			-- 取引先コード
			DELIVERY_CD,			-- 納入先コード
			DELIVERY_NAME,			-- 納入先名称
			CARRY_CD,			-- 運送会社コード
			SENDING_OFF_NUMBER,		-- 積出ナンバー
			SHIPPING_ORDER_SEND_COMP_FLAG,	-- 指図送信済みフラグ 0:未発行 1:発行済み
			ITEM_CD,			-- 品目コード
			ITEM_NAME,			-- 品目名称
			ITEM_UNIT,			-- 単位
			SHIPPING_RESULT_DATE,		-- 出荷完了日
			INPUT_DATE,			-- 登録日時
			INPUTOR_CD,			-- 登録者ＩＤ
			UPDATE_DATE,			-- 更新日時
			UPDATOR_CD			-- 更新者ＩＤ
		) VALUES(
			I_SHIPPING_DATE,		-- 出荷予定日
			NULL,				-- 取引先コード
			NULL,				-- 納入先コード
			NULL,				-- 納入先名称
			PROC_CARRY.CARRY_CD,		-- 運送会社コード
			TEMP_FLAN_CHAR,			-- 積出ナンバー
			'0',				-- 指図送信済みフラグ 0:未発行 1:発行済み
			' ',				-- 品目コード
			NULL,				-- 品目名称
			NULL,				-- 単位
			NULL,				-- 出荷完了日
			SYSDATE,			-- 登録日時
			I_TANTO_CD,			-- 登録者ＩＤ
			SYSDATE,			-- 更新日時
			I_TANTO_CD			-- 更新者ＩＤ
		);
		
		
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
