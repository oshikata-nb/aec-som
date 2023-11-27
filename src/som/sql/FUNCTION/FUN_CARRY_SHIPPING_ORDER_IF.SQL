CREATE OR REPLACE FUNCTION      FUN_CARRY_SHIPPING_ORDER_IF
      (
             I_SHIPPING_DATE		IN	NVARCHAR2,	-- 出荷予定日
             I_SENDING_OFF_NO		IN	NVARCHAR2,	-- 積出ナンバー
             I_CARRY_CD			IN	NVARCHAR2,	-- 運送会社コード
             I_TANTO_CD			IN	NVARCHAR2,	-- 担当者コード
             IO_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             IO_RETURN_MSG		IN OUT	NVARCHAR2	-- エラーメッセージを返す
      )

RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_CARRY_SHIPPING_ORDER_SEND                                             */
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
	TEMP_ITEM_CD		VARCHAR2(20);		-- 品目コード
	TEMP_OTHER_CO1		VARCHAR2(20);		-- 他社コード1
	TEMP_SHIPPER_DIVISION   VARCHAR2(20);		-- 荷主
	TEMP_OWN_LOT		VARCHAR2(20);		-- 包装指図番号（ロット番号自社 	H 始まり）
	TEMP_OTHER_LOT		VARCHAR2(20);		-- 入荷バーコード（ロット番号外注品 	P 始まり）
	TEMP_SHIPPING_BC	VARCHAR2(20);		-- 出荷指図書バーコード
	TEMP_SHIPPING_NUM	NUMBER;			-- 出荷数量
	TEMP_SHIPPING_DATE	VARCHAR2(20);		-- 出荷予定日
	TEMP_SENDING_OFF_NO	VARCHAR2(20);		-- 積出ナンバー
	TEMP_SAVE_ITEM_CD	VARCHAR2(20);		-- 品目コード
	TEMP_LOCATION_CD	VARCHAR2(20);		-- ロケーションコード

	CONST_LOT_NO		VARCHAR2(20) := '00000000000';	-- ロット番号
	CONST_ORDER_NO		VARCHAR2(20) := '00000000';	-- 受注番号
	CONST_ORDER_ROW_NO	VARCHAR2(20) := '00';		-- 受注行番号
	CONST_WHITE_SPACE	VARCHAR2(1) := ' ';		-- ホワイトスペース（データを入れない場合等に使用）
	CONST_SHUKKARIYU	VARCHAR2(2) := '01';		-- 出荷理由デフォルト[01]

	-- 製品別出庫指図状況書き込み用
	-- ①出荷ヘッダ.出荷ステータス = [3:指図送信済み]（すでにＡＰで処理を行いステータスが3になっている）
	-- ②出荷ヘッダ.積出ナンバー = 引数[I_SENDING_OFF_NO]
	-- ③出荷ヘッダ.出荷予定日 = 引数[I_SHIPPING_DATE]
	-- ④出荷ヘッダ.運送会社コード = 引数[I_CARRY_CD]
	CURSOR PROC_SHIPPING_DETAIL IS 
		SELECT 
			P_SHIPPING.SHIPPING_NO,			-- 出荷番号
			SHIPPING_DETAIL.SHIPPING_ROW_NO,	-- 出荷行番号
			P_SHIPPING.SCHEDULED_SHIPPING_DATE,	-- 出荷予定日
			P_SHIPPING.ITEM_CD,			-- 品目コード
			SHIPPING_DETAIL.SHIPPING_INSTRUCTION,	-- 出荷指図数量
			SHIPPING_DETAIL.LOT_NO,			-- ロット番号
			SHIPPING_DETAIL.LOCATION_CD,		-- ロケーションコード
			SHIPPING_DETAIL.PRODUCT_OUT_ORDER_BC	-- 製品出庫指図書バーコード
		FROM 
			(SELECT SHIPPING.* FROM SHIPPING,ORDER_HEAD WHERE SHIPPING.ORDER_NO = ORDER_HEAD.ORDER_NO AND (ORDER_HEAD.ORDER_DIVISION = 1 OR ORDER_HEAD.ORDER_DIVISION = 2)
			UNION
			SELECT SHIPPING.* FROM SHIPPING WHERE SHIPPING.ORDER_NO IS NULL) P_SHIPPING,
			SHIPPING_DETAIL
		WHERE 
			P_SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO
			AND P_SHIPPING.SHIPPING_STATUS = 3				-- ①
			AND P_SHIPPING.SENDING_OFF_NUMBER = I_SENDING_OFF_NO		-- ②
			AND P_SHIPPING.SCHEDULED_SHIPPING_DATE = I_SHIPPING_DATE	-- ③
			AND P_SHIPPING.CARRY_CD = I_CARRY_CD				-- ④
			ORDER BY P_SHIPPING.ITEM_CD ASC;

BEGIN
	TEMP_SAVE_ITEM_CD := NULL;

	-- ロケーションごとに処理
   	FOR PROC_LOCATION IN PROC_SHIPPING_DETAIL LOOP

		DBMS_OUTPUT.PUT_LINE('品目:' || PROC_LOCATION.ITEM_CD || ' ロケーション:' || PROC_LOCATION.LOCATION_CD);

		-- 品目マスタのデータを取得
		SELECT * INTO TEMP_ITEM_TABLE FROM ITEM WHERE ITEM.ITEM_CD = PROC_LOCATION.ITEM_CD;
		
		-- 自社マスタのデータがない場合ホワイトスペースをセット
		IF TEMP_ITEM_TABLE.OTHER_COMPANY_CD1 IS NULL THEN
			TEMP_OTHER_CO1 := CONST_WHITE_SPACE;
		ELSE
			TEMP_OTHER_CO1 := TEMP_ITEM_TABLE.OTHER_COMPANY_CD1;
		END IF;
		
		-- 品目マスタの荷主からセットデータを設定
		IF TEMP_ITEM_TABLE.SHIPPER_DIVISION = 1 THEN	-- 荷主区分 = [1:花王]
			TEMP_SHIPPER_DIVISION := '2';	-- [2:花王]
		ELSE						-- 荷主区分 <> [1:花王]
			TEMP_SHIPPER_DIVISION := '1';	-- [1:自社]
		END IF;

		TEMP_SHIPPING_DATE :=I_SHIPPING_DATE;
		TEMP_SENDING_OFF_NO := I_SENDING_OFF_NO;
		TEMP_CARRY_CD := I_CARRY_CD;
		TEMP_ITEM_CD := PROC_LOCATION.ITEM_CD;

		TEMP_LOCATION_CD := PROC_LOCATION.LOCATION_CD;

		-- 品目コードが変わった場合出荷計画に書込み
		IF TEMP_SAVE_ITEM_CD IS NULL OR TEMP_SAVE_ITEM_CD <> TEMP_ITEM_CD  THEN

			-- 出荷指図量累計を取得
			SELECT NVL(SUM(SHIPPING_DETAIL.SHIPPING_INSTRUCTION),0) INTO TEMP_SHIPPING_NUM FROM SHIPPING,SHIPPING_DETAIL WHERE 
				SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO
				AND SHIPPING.ITEM_CD = TEMP_ITEM_CD
				AND SHIPPING.SHIPPING_STATUS = 3
				AND SHIPPING.SENDING_OFF_NUMBER = I_SENDING_OFF_NO
				AND SHIPPING.SCHEDULED_SHIPPING_DATE = I_SHIPPING_DATE
				AND SHIPPING.CARRY_CD = I_CARRY_CD;
			
			DBMS_OUTPUT.PUT_LINE('出荷指図量:' || TEMP_SHIPPING_NUM || ' ロケーション:' || PROC_LOCATION.LOCATION_CD);
			
			SELECT COUNT(*) INTO TEMP_COUNT FROM KEIKAKU_SHUKKA 
			WHERE NENGAPPI = TEMP_SHIPPING_DATE AND TSUMINO = TEMP_SENDING_OFF_NO AND SEIHINCODE = TEMP_ITEM_CD AND JUCHUNO = CONST_ORDER_NO AND JUCHUEDABAN = CONST_ORDER_ROW_NO AND SHUKKARIYU = CONST_SHUKKARIYU;
			
			-- すでにデータがある場合更新処理
			IF TEMP_COUNT IS NOT NULL AND TEMP_COUNT <> 0 THEN
				UPDATE KEIKAKU_SHUKKA SET
					SYSFLAG		 = CONST_WHITE_SPACE,	-- ＫＳＰ/基幹 １：ＫＳＰ、２：基幹
					KONSAI		 = CONST_WHITE_SPACE,	-- 混載 1：混載
					TODOKECODE	 = CONST_WHITE_SPACE,	-- 届先コード 基幹ｼｽﾃﾑのみ
					UNSOTENCODE	 = TEMP_CARRY_CD,	-- 運送店コード 
					SHABAN		 = CONST_WHITE_SPACE,	-- 車番 
					IOCODE		 = TEMP_OTHER_CO1,	-- IOコード 
					JISHAFLAG	 = TEMP_SHIPPER_DIVISION,-- 自社/花王 １：自社、２：花王
					KEIKAKUSU	 = TEMP_SHIPPING_NUM,	-- 計画数 9
					HAKKOSUMI	 = '1',			-- 配車別出荷指図書発行 １：発行済み
					SHUKKAJOKYO	 = '0',			-- 出荷状況 0:未出荷		1:積出準備		2:出荷中		3:出荷完了 東レで更新
					JOKYO1F		 = '0',			-- １Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					JOKYO2F		 = '0',			-- ２Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					JOKYOKIKEN	 = '0',			-- 危険物状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					SHUKKAHOHO	 = CONST_WHITE_SPACE	-- 出荷方法 
				WHERE NENGAPPI = TEMP_SHIPPING_DATE AND TSUMINO = TEMP_SENDING_OFF_NO AND SEIHINCODE = TEMP_ITEM_CD AND JUCHUNO = CONST_ORDER_NO AND JUCHUEDABAN = CONST_ORDER_ROW_NO AND SHUKKARIYU = CONST_WHITE_SPACE;
			ELSE	-- データが無い場合更新処理
				-- 出荷計画テーブルに書込み
				INSERT INTO KEIKAKU_SHUKKA(
					NENGAPPI,	-- 年月日 出荷予定日
					SYSFLAG,	-- ＫＳＰ/基幹 １：ＫＳＰ、２：基幹
					KONSAI,		-- 混載 1：混載
					TSUMINO,	-- 積出ナンバー 
					JUCHUNO,	-- 受注番号 基幹ｼｽﾃﾑのみ、その他は、00000000
					JUCHUEDABAN,	-- 受注番号枝番 基幹ｼｽﾃﾑのみ、その他は、00
					TODOKECODE,	-- 届先コード 基幹ｼｽﾃﾑのみ
					UNSOTENCODE,	-- 運送店コード 
					SHABAN,		-- 車番 
					SEIHINCODE,	-- 製品コード 
					IOCODE,		-- IOコード 
					JISHAFLAG,	-- 自社/花王 １：自社、２：花王
					SHUKKARIYU,	-- 出荷理由 
					KEIKAKUSU,	-- 計画数 9
					HAKKOSUMI,	-- 配車別出荷指図書発行 １：発行済み
					SHUKKAJOKYO,	-- 出荷状況 0:未出荷,1:積出準備,2:出荷中,3:出荷完了 東レで更新
					JOKYO1F,	-- １Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					JOKYO2F,	-- ２Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					JOKYOKIKEN,	-- 危険物状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					SHUKKAHOHO	-- 出荷方法 
				)VALUES(
					TEMP_SHIPPING_DATE,	-- 年月日 出荷予定日
					CONST_WHITE_SPACE,			-- ＫＳＰ/基幹 １：ＫＳＰ、２：基幹
					CONST_WHITE_SPACE,			-- 混載 1：混載
					TEMP_SENDING_OFF_NO,	-- 積出ナンバー 
					CONST_ORDER_NO,		-- 受注番号 基幹ｼｽﾃﾑのみ、その他は、00000000
					CONST_ORDER_ROW_NO,	-- 受注番号枝番 基幹ｼｽﾃﾑのみ、その他は、00
					CONST_WHITE_SPACE,			-- 届先コード 基幹ｼｽﾃﾑのみ
					TEMP_CARRY_CD,		-- 運送店コード 
					CONST_WHITE_SPACE,			-- 車番 
					TEMP_ITEM_CD,		-- 製品コード 
					TEMP_OTHER_CO1,		-- IOコード 
					TEMP_SHIPPER_DIVISION,	-- 自社/花王 １：自社、２：花王
					CONST_SHUKKARIYU,			-- 出荷理由 
					TEMP_SHIPPING_NUM,	-- 計画数
					'1',	-- 配車別出荷指図書発行 １：発行済み
					'0',	-- 出荷状況 0:未出荷,1:積出準備,2:出荷中,3:出荷完了 東レで更新
					'0',	-- １Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					'0',	-- ２Ｆ状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					'0',	-- 危険物状況 0:未出庫、1:ピッキング中、2:出庫完了　　東レで更新
					CONST_WHITE_SPACE	-- 出荷方法 
				);
			END IF;
			-- 品目コードを保持
			TEMP_SAVE_ITEM_CD := TEMP_ITEM_CD;
		END IF;

		-- ロット番号の上一桁で分岐
		IF SUBSTR(PROC_LOCATION.LOT_NO,1,1) = 'H' THEN
			TEMP_OWN_LOT := PROC_LOCATION.LOT_NO;
			TEMP_OTHER_LOT := CONST_LOT_NO;
		ELSE
			TEMP_OWN_LOT := CONST_LOT_NO;
			TEMP_OTHER_LOT := PROC_LOCATION.LOT_NO;
		END IF;

		DBMS_OUTPUT.PUT_LINE('日毎初版処理開始');
		
		-- 出庫指図書バーコードを取得する
		TEMP_SHIPPING_BC := PROC_LOCATION.PRODUCT_OUT_ORDER_BC;

		TEMP_SHIPPING_NUM := PROC_LOCATION.SHIPPING_INSTRUCTION;

		INSERT INTO JOKYO_SEIHINBETSU_SHUKKO(
			NENGAPPI,		-- 年月日 出荷日
			TSUMINO,		-- 積出ナンバー 
			SEIHINCODE,		-- 製品コード 
			IOCODE,			-- IOコード 
			JISHAFLAG,		-- 自社/花王 １：自社、２：花王
			SHUKKARIYU,		-- 出荷理由 
			HITSUYOSU,		-- 出庫必要数 9
			SUMISU,			-- 出庫済み 最初は=0　東レで更新
			SHUKKAJOKYO,		-- 出荷状況 0:未出庫、1:出庫中、2:Pick完了、3:積出完了
			FORKLANNO,		-- フォークＬＡＮ番号 最初は=0　東レで更新
			LOCATION,		-- ロケーション 在庫無し分はオールゼロ
			HOSOSASHIZUNO,		-- 包装指図番号 入荷製品は　00000000000
			NYUKABC,		-- 入荷バーコード PYYMMDD9999
			SEISHUKKOSABC,		-- 製品出庫指図書バーコード A0912310001
			JUCHUNO,		-- 受注番号 基幹ｼｽﾃﾑのみ、その他は、00000000
			JUCHUEDABAN		-- 受注番号枝番 基幹ｼｽﾃﾑのみ、その他は、00
		)VALUES(
			TEMP_SHIPPING_DATE,	-- 出荷予定日
			TEMP_SENDING_OFF_NO,	-- 積出ナンバー
			TEMP_ITEM_CD,		-- 品目コード
			TEMP_OTHER_CO1,		-- 他社コード
			TEMP_SHIPPER_DIVISION,	-- 荷主
			CONST_SHUKKARIYU,	-- 出荷理由
			TEMP_SHIPPING_NUM,	-- 出荷指図数量
			'0',			-- 出庫済み
			'0',			-- 出庫状況
			'0',			-- フォークラン番号
			TEMP_LOCATION_CD,	-- ロケーション
			TEMP_OWN_LOT,		-- ロット番号（自社）
			TEMP_OTHER_LOT,		-- ロット番号（外注品）
			TEMP_SHIPPING_BC,	-- 製品出庫指図書バーコード
			CONST_ORDER_NO,		-- 受注番号
			CONST_ORDER_ROW_NO			-- 受注番号枝番
		);
		
	END LOOP;

	RETURN 0;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	IO_RETURN_CD := SQLCODE;
		IO_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		RETURN -9;
END;
/
