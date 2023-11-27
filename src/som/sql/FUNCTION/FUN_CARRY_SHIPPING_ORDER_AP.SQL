CREATE OR REPLACE FUNCTION      FUN_CARRY_SHIPPING_ORDER_AP
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
	
	TEMP_SHIPPING_BC	VARCHAR2(20);		-- 出荷指図書バーコード

	-- 製品別出庫指図状況書き込み用
	-- ①出荷ヘッダ.出荷ステータス = [2:出荷指図確定済]
	-- ②出荷ヘッダ.積出ナンバー = NULL
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
			SHIPPING_DETAIL.LOCATION_CD		-- ロケーションコード
		FROM 
			(SELECT SHIPPING.* FROM SHIPPING,ORDER_HEAD WHERE SHIPPING.ORDER_NO = ORDER_HEAD.ORDER_NO AND (ORDER_HEAD.ORDER_DIVISION = 1 OR ORDER_HEAD.ORDER_DIVISION = 2)
			UNION
			SELECT SHIPPING.* FROM SHIPPING WHERE SHIPPING.ORDER_NO IS NULL) P_SHIPPING,
			SHIPPING_DETAIL
		WHERE 
			P_SHIPPING.SHIPPING_NO = SHIPPING_DETAIL.SHIPPING_NO
			AND P_SHIPPING.SHIPPING_STATUS = 2				-- ①
			AND P_SHIPPING.SENDING_OFF_NUMBER IS NULL 			-- ②
			AND P_SHIPPING.SCHEDULED_SHIPPING_DATE = I_SHIPPING_DATE	-- ③
			AND P_SHIPPING.CARRY_CD = I_CARRY_CD				-- ④
			ORDER BY P_SHIPPING.ITEM_CD ASC;

BEGIN
	-- ロケーションごとに処理
   	FOR PROC_LOCATION IN PROC_SHIPPING_DETAIL LOOP

		--出荷ヘッダ.ステータス = [3:指図送信済] 出荷ヘッダ.積出ナンバー 引数[I_SENDING_OFF_NO]
		UPDATE SHIPPING 
		SET 
			SHIPPING_STATUS = 3 ,
			SENDING_OFF_NUMBER = I_SENDING_OFF_NO,
			SHIPPING.UPDATE_DATE = SYSDATE,
			SHIPPING.UPDATOR_CD = I_TANTO_CD
		
		WHERE SHIPPING.SHIPPING_NO = PROC_LOCATION.SHIPPING_NO;

		-- 日毎発番処理で出庫指図書バーコードを取得する
		TEMP_SHIPPING_BC := FUN_NUMBERING_DAILY_NUMBER('SYUKOSASIZUBCD',SYSDATE,I_TANTO_CD,IO_RETURN_CD,IO_RETURN_MSG);

		-- 製品出庫指図書バーコードをセット
		UPDATE SHIPPING_DETAIL 
		SET 
			PRODUCT_OUT_ORDER_BC = TEMP_SHIPPING_BC,
			UPDATE_DATE = SYSDATE,
			UPDATOR_CD = I_TANTO_CD
		WHERE SHIPPING_DETAIL.SHIPPING_NO = PROC_LOCATION.SHIPPING_NO AND SHIPPING_DETAIL.SHIPPING_ROW_NO = PROC_LOCATION.SHIPPING_ROW_NO;
		
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
