CREATE OR REPLACE PROCEDURE      PRO_AUTO_MAKE_SHIPPING_ORDER
      (
             I_SHIPPING_DATE_FROM	IN	NVARCHAR2,	-- 出荷予定日(FROM)
             I_SHIPPING_DATE_TO		IN	NVARCHAR2,	-- 出荷予定日(TO)
             I_TANTO_CD			IN	NVARCHAR2,	-- 担当者コード
             O_ERROR_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_MSG		OUT	NVARCHAR2	-- 戻り値
      )

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      AUTO_MAKE_SHIPPING_ORDER                                                  */
/*      処理内容      :      受注データを元に出荷指図データの自動作成を行う		       、      */
/*      引数          :      I_SHIPPING_DATE_FROM	出荷予定日(FROM)	NVARCHAR2型            */
/*      引数          :      I_SHIPPING_DATE_TO		出荷予定日(TO)		NVARCHAR2型            */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      引数          :      O_RETURN_MSG		戻り値			NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 		VARCHAR2(100);
	ERR_MSG 		VARCHAR2(2048);
	TEMP_MAX_STOCK_VALUE 	NUMBER;		-- 有効在庫量
	TEMP_SHIPPING_NUMBER	NUMBER;		-- 出荷番号数値部
	TEMP_SHIPPING_CHAR	VARCHAR2(10);	-- 出荷番号上二桁
	TEMP_SHIPPING_NO	VARCHAR2(20);	-- 出荷番号
	TEMP_COUNT		NUMBER;		-- 出荷指図行番号
	TEMP_ORDER_VALUE	NUMBER; 	-- 使用量
	TEMP_SUM_USE_VALUE	NUMBER; 	-- ロットごとの使用量を順に累積
	TEMP_USE_VALUE		NUMBER; 	-- ロットごとの使用量
	TEMP_RETURN_STOCK	VARCHAR2(255);	-- 在庫プロシージャ戻り値

	CONST_STOCK_PROC_DIVISION	NUMBER := 1;			-- 在庫プロシージャ処理区分（登録）
	CONST_COMP			VARCHAR2(20) := 'COMPLETE';	-- 在庫プロシージャ完了戻り値
	
	-- 処理対処をループ処理するカーソルを作成
	-- ①品目マスタ.預り品区分 = 1
	-- ②受注ヘッダ.受注区分 = [1:在庫引当] OR 受注ヘッダ.受注区分 = [1:受注生産]
	-- ③受注詳細.出荷番号=[NULL]
	-- ④[第1引数:出荷予定日(FROM)] <= 受注ヘッダ.出荷予定日 <= [第1引数:出荷予定日(TO)]
	-- ソート順 1: 運送会社コード,受注番号、受注号番号
	CURSOR PROC_ORDER IS 
		SELECT 
			ORDER_HEAD.ORDER_NO,			-- 受注番号
			ORDER_DETAIL.ROW_NO,			-- 受注行番号
			ORDER_HEAD.VENDER_CD,			-- 得意先コード
			ORDER_HEAD.DELIVERY_CD,			-- 納入先コード
			ORDER_DETAIL.ITEM_CD,			-- 品目コード
			ORDER_HEAD.CARRY_CD,			-- 運送会社コード
			ORDER_HEAD.SCHEDULED_SHIPPING_DATE,	-- 出荷予定日
			ORDER_HEAD.SUGGESTED_DELIVERLIMIT,	-- 希望納期
			ORDER_HEAD.CARRY_FARE,			-- 運賃
			(ORDER_DETAIL.ORDER_QTY + ORDER_DETAIL.MATSS) AS ORDER_QTY	-- 受注数量
		FROM 
			ORDER_HEAD,		-- 受注ヘッダ
			ORDER_DETAIL,		-- 受注詳細
			ITEM,			-- 品目マスタ
			ARTICLE_ATTRIBUTE_QUEUE	-- 販売品扱い属性(品目マスタメンテナンス)
		WHERE ORDER_HEAD.ORDER_NO = ORDER_DETAIL.ORDER_NO AND ORDER_DETAIL.ITEM_CD = ITEM.ITEM_CD AND ITEM.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD AND ITEM.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION 
			AND ARTICLE_ATTRIBUTE_QUEUE.KEEP_DIVISION = '1' 			-- ①
			AND (ORDER_HEAD.ORDER_DIVISION = 1 OR ORDER_HEAD.ORDER_DIVISION = 2)	-- ②
			AND ORDER_DETAIL.SHIPPING_NO IS NULL 					-- ③
			AND (ORDER_HEAD.SCHEDULED_SHIPPING_DATE >= I_SHIPPING_DATE_FROM AND ORDER_HEAD.SCHEDULED_SHIPPING_DATE <= I_SHIPPING_DATE_TO)	-- ④
			ORDER BY ORDER_HEAD.CARRY_CD,ORDER_HEAD.ORDER_NO,ORDER_DETAIL.ROW_NO ;
BEGIN

	-- 戻り値0をセット
	O_RETURN_MSG := 0;
	
	-- 出荷番号上二桁を名称マスタから取得
	SELECT NAME01 INTO TEMP_SHIPPING_CHAR FROM NAMES WHERE NAME_DIVISION = 'NUM' AND NAME_CD = 'SK';
	
	-- 受注明細毎に出荷指図自動作成処理
   	FOR MAKE_SHIPPING IN PROC_ORDER LOOP
	
		DBMS_OUTPUT.PUT_LINE('受注番号:' || MAKE_SHIPPING.ORDER_NO || ' 受注行番号:' || MAKE_SHIPPING.ROW_NO);
		TEMP_COUNT := 1;	-- 出荷行番号連番初期化
		
		-- 品目コード毎に有効在庫総計を取得
		SELECT (SUM(INVENTORY_QTY) + SUM(ASSIGN_QTY)) INTO TEMP_MAX_STOCK_VALUE 
			FROM LOT_INVENTORY 
			WHERE LOT_INVENTORY.ITEM_CD = MAKE_SHIPPING.ITEM_CD AND LOT_INVENTORY.LOCATION_CD IN (SELECT LOCATION.LOCATION_CD FROM (SELECT * FROM LOCATION WHERE UPPER_LOCATION_CD = 'BK' AND LOCATION_LEVEL = 3) TEMP_LOCATION,LOCATION WHERE LOCATION.UPPER_LOCATION_CD LIKE TEMP_LOCATION.LOCATION_CD || '%' AND LOCATION.AVAILABLE_FLG = 1 AND LOCATION.LOCATION_LEVEL = 4);

		DBMS_OUTPUT.PUT_LINE('有効在庫:' || TEMP_MAX_STOCK_VALUE || ' 受注数量:' || MAKE_SHIPPING.ORDER_QTY);
		
		-- 受注数量のほうが在庫総計より多かった場合
		IF MAKE_SHIPPING.ORDER_QTY > TEMP_MAX_STOCK_VALUE OR TEMP_MAX_STOCK_VALUE IS NULL THEN

			O_ERROR_RETURN_MSG := '受注番号:' || MAKE_SHIPPING.ORDER_NO || ' 品目:' || MAKE_SHIPPING.ITEM_CD || ' 受注数量:' || MAKE_SHIPPING.ORDER_QTY || ' 有効在庫:' || TEMP_MAX_STOCK_VALUE || '有効在庫総数 < 受注数量である受注がありました';
			O_RETURN_MSG := -1;
			RETURN;

		END IF;

		-- 出荷番号用連番を取得
		SELECT NO_KEY_SHIPPING_ORDER_SLIP_NO.NEXTVAL INTO TEMP_SHIPPING_NUMBER FROM DUAL;
		
		-- 出荷番号を作成
		TEMP_SHIPPING_NO := TEMP_SHIPPING_CHAR || LPAD(TEMP_SHIPPING_NUMBER,9,'0') ;

		-- 受注詳細に出荷番号書込み処理
		UPDATE ORDER_DETAIL SET ORDER_DETAIL.SHIPPING_NO = TEMP_SHIPPING_NO WHERE ORDER_DETAIL.ORDER_NO = MAKE_SHIPPING.ORDER_NO AND ORDER_DETAIL.ROW_NO = MAKE_SHIPPING.ROW_NO;

		-- 出荷ヘッダ追加処理
		INSERT INTO SHIPPING(
			SHIPPING_NO,SHIPPING_INSTRUCT_DATE,
			SCHEDULED_SHIPPING_DATE,
			TANTO_CD,
			ORDER_NO,
			ORDER_ROW_NO,
			VENDER_DIVISION,
			VENDER_CD,
			DELIVERY_CD,
			SHIPPING_STATUS,
			ITEM_CD,
			SUMMERY,
			DELIVERY_COMP,
			SHIPPING_RESULT_DATE,
			SHIPPING_SLIP_NO,
			SHIPPING_SLIP_ROW_NO,
			SLIP_PUBLISH_COMP,
			SLIP_PUBLISH_DATE,
			SLIP_SHIPPING_ORDER_COMP,
			SLIP_SHIPPING_ORDER_DATE,
			SLIP_SHIPPING_SCHEDULE_COMP,
			SLIP_SHIPPING_SCHEDULE_DATE,
			SLIP_SHIPPING_TAG_COMP,
			SLIP_SHIPPING_TAG_DATE,
			SLIP_SHIPPING_REQUEST_COMP,
			SLIP_SHIPPING_REQUEST_DATE,
			CARRY_CD,
			SUGGESTED_DELIVERLIMIT,
			CARRY_FARE,
			SENDING_OFF_NUMBER,
			INPUT_DATE,
			INPUTOR_CD,
			UPDATE_DATE,
			UPDATOR_CD
		) VALUES(
			TEMP_SHIPPING_NO,			--出荷番号
			TO_CHAR(SYSDATE, 'YYYY/MM/DD'),		--出荷指図日
			MAKE_SHIPPING.SCHEDULED_SHIPPING_DATE,	--出荷予定日
			I_TANTO_CD,				--出荷担当者コード
			MAKE_SHIPPING.ORDER_NO,			--受注番号
			MAKE_SHIPPING.ROW_NO,			--行番号(受注)
			NULL,					--取引先区分:不要
			MAKE_SHIPPING.VENDER_CD,		--取引先コード
			MAKE_SHIPPING.DELIVERY_CD,		--納入先コード
			2,					--出荷ステータス|1:出荷指図未確定 2:出荷指図確定済 3:指図送信済 4:実績受信中 5:出荷完了
			MAKE_SHIPPING.ITEM_CD,			--品目コード
			NULL,					--摘要:未使用
			0,					--完納区分
			NULL,					--出荷完了日
			NULL,					--出荷伝票番号
			NULL,					--出荷伝票行番号
			0,					--出荷伝票発行済区分(0:未発行 1:発行済)
			NULL,					--出荷伝票発行日
			0,					--出荷指図書発行済区分(0:未発行 1:発行済)
			NULL,					--出荷指図書発行日
			0,					--出荷予定表発行済区分(0:未発行 1:発行済)
			NULL,					--出荷予定表発行日
			0,					--出荷荷札,ペリカン伝票発行済区分(0:未発行 1:発行済)
			NULL,					--出荷荷札,ペリカン伝票発行日
			0,					--出荷依頼書発行済区分(0:未発行 1:発行済)
			NULL,					--出荷依頼書発行日
			MAKE_SHIPPING.CARRY_CD,			--運送会社コード
			MAKE_SHIPPING.SUGGESTED_DELIVERLIMIT,	--希望納期
			MAKE_SHIPPING.CARRY_FARE,		--運賃
			NULL,					--積出ナンバー
			SYSDATE,				--登録日時
			I_TANTO_CD,				--登録者ID
			SYSDATE,				--更新日時
			I_TANTO_CD				--更新者ID
		);				
		
		TEMP_ORDER_VALUE := MAKE_SHIPPING.ORDER_QTY;	-- 受注数量
		TEMP_SUM_USE_VALUE := 0;			-- 出荷数量合計

		-- ロット在庫のロット発生日が古い順に在庫の割り当て
		FOR LOT_LOOP IN	(
			SELECT LOT_INVENTORY.*
			FROM LOT_INVENTORY 
			WHERE LOT_INVENTORY.ITEM_CD = MAKE_SHIPPING.ITEM_CD AND ((LOT_INVENTORY.INVENTORY_QTY + LOT_INVENTORY.ASSIGN_QTY) > 0)
			AND LOT_INVENTORY.LOCATION_CD IN (SELECT LOCATION.LOCATION_CD FROM (SELECT * FROM LOCATION WHERE UPPER_LOCATION_CD = 'BK' AND LOCATION_LEVEL = 3) TEMP_LOCATION,LOCATION WHERE LOCATION.UPPER_LOCATION_CD LIKE TEMP_LOCATION.LOCATION_CD || '%' AND LOCATION.AVAILABLE_FLG = 1 AND LOCATION.LOCATION_LEVEL = 4)
			ORDER BY ISSUE_DATE ASC)
		LOOP

			DBMS_OUTPUT.PUT_LINE('有効在庫:' || (LOT_LOOP.INVENTORY_QTY + LOT_LOOP.ASSIGN_QTY) || ' 受注数量:' || TEMP_ORDER_VALUE);
			
			-- １レコードごとの使用量を設定
			IF TEMP_ORDER_VALUE > TEMP_SUM_USE_VALUE + (LOT_LOOP.INVENTORY_QTY + LOT_LOOP.ASSIGN_QTY) THEN	-- 受注数量 > 使用累計量 + ロット在庫の有効在庫
				TEMP_USE_VALUE := LOT_LOOP.INVENTORY_QTY + LOT_LOOP.ASSIGN_QTY;
			ELSE												-- 受注数量 <= 使用累計量 + ロット在庫の有効在庫
				TEMP_USE_VALUE := TEMP_ORDER_VALUE - TEMP_SUM_USE_VALUE;
			END IF;

			INSERT INTO SHIPPING_DETAIL(
				SHIPPING_NO,
				SHIPPING_ROW_NO,
				ROW_NO,
				LOT_NO,
				SHIPPING_INSTRUCTION,
				SHIPPING_RESULT_DATE,
				SHIPPING_RESULT_QUANTITY,
				SHIPPING_STATUS,
				SUMMERY,
				LOCATION_CD,
				DELIVERY_COMP,
				INPUT_DATE,
				INPUTOR_CD,
				UPDATE_DATE,
				UPDATOR_CD
			) VALUES(
				TEMP_SHIPPING_NO,	-- 出荷番号
				TEMP_COUNT,		-- 行番号(出荷)
				0,			-- 行番号(未使用)
				LOT_LOOP.LOT_NO,	-- ロット番号
				TEMP_USE_VALUE,		-- 出荷指図数量
				NULL,			-- 出荷実績日
				0,			-- 出荷実績数
				0,			-- 出荷詳細ステータス
				NULL,			-- 摘要
				LOT_LOOP.LOCATION_CD,	-- ロケーションコード
				0,			-- 完納区分
				SYSDATE,		--登録日時
				I_TANTO_CD,		--登録者ID
				SYSDATE,		--更新日時
				I_TANTO_CD		--更新者ID
			);
			
			TEMP_SUM_USE_VALUE := TEMP_SUM_USE_VALUE + TEMP_USE_VALUE;	-- 出荷量累計を保持
			TEMP_COUNT := TEMP_COUNT + 1;					-- 出荷行番号をカウントアップ
			
			DBMS_OUTPUT.PUT_LINE('出荷指図数量:' || TEMP_USE_VALUE || ' 出荷指図量累計:' || TEMP_SUM_USE_VALUE);
						
			-- 受注明細.受注数量まで達した場合
			IF TEMP_ORDER_VALUE <= TEMP_SUM_USE_VALUE THEN
				
				-- 在庫プロシージャをコール
				ZAIKOUKEHARAI.ENTRY_SHIPPING(CONST_STOCK_PROC_DIVISION,	-- [1:登録]（1:登録　2:登録取消　3:確定 4:確定取消　5:完了）
									TEMP_SHIPPING_NO,	-- 出荷番号
									NULL,			-- 行番号(出荷)(nullなら出荷番号全件を対象とする）
									I_TANTO_CD,		-- 担当者コード
									TEMP_RETURN_STOCK);	-- 返り：「COMPLETE」で成功。失敗ならエラーメッセージが帰る。
				
				-- 在庫プロシージャで成功した場合
				IF TEMP_RETURN_STOCK = CONST_COMP THEN
					
					EXIT;	-- 次の受注を処理
				ELSE
					O_ERROR_RETURN_MSG := '受注番号:' || MAKE_SHIPPING.ORDER_NO || '受注行番号:' || MAKE_SHIPPING.ROW_NO || ' 品目:' || MAKE_SHIPPING.ITEM_CD || ' 出荷指図数量:' || TEMP_USE_VALUE || ' 出荷予定ロケーション:' || LOT_LOOP.LOCATION_CD|| ' 出荷予定ロット:' || LOT_LOOP.LOT_NO || '在庫更新プロシージャでエラーが発生しました';
					O_RETURN_MSG := -2;
					RETURN;
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
		O_RETURN_MSG := -9;


END;
/
