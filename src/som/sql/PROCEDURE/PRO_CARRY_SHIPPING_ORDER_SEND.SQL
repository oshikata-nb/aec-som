CREATE OR REPLACE PROCEDURE      PRO_CARRY_SHIPPING_ORDER_SEND
      (
             I_SHIPPING_DATE		IN	NVARCHAR2,	-- 出荷予定日
             I_SENDING_OFF_NO		IN	NVARCHAR2,	-- 積出ナンバー
             I_CARRY_CD			IN	NVARCHAR2,	-- 運送会社コード
             I_TANTO_CD			IN	NVARCHAR2,	-- 担当者コード
             O_ERROR_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		IN OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		IN OUT	NVARCHAR2	-- 戻り値

      )

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_CARRY_SHIPPING_ORDER_SEND                                             */
/*      処理内容      :      積出ナンバーと出荷予定日を元に計装IFに指図を送信する	       、      */
/*      引数          :      I_SHIPPING_DATE		出荷予定日		NVARCHAR2型            */
/*      引数          :      I_SENDING_OFF_NO		積出ナンバー		NVARCHAR2型            */
/*      引数          :      I_TANTO_CD			担当者コード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      引数          :      O_RETURN_CD		戻り値　		NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/

BEGIN

	O_RETURN_CD := 0;
	-- APの出荷指図トランザクションを更新
	IF FUN_CARRY_SHIPPING_ORDER_AP(I_SHIPPING_DATE,I_SENDING_OFF_NO,I_CARRY_CD,I_TANTO_CD,O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG) = 0 THEN
		COMMIT;
		
		-- 出荷トランザクションの更新に成功した場合　IFテーブルを更新処理
		IF FUN_CARRY_SHIPPING_ORDER_IF(I_SHIPPING_DATE,I_SENDING_OFF_NO,I_CARRY_CD,I_TANTO_CD,O_ERROR_RETURN_CD,O_ERROR_RETURN_MSG) = 0 THEN
			COMMIT;
		ELSE
			ROLLBACK;
			O_RETURN_CD := -9;
		END IF;
	ELSE
		ROLLBACK;
		O_RETURN_CD := -9;
	END IF;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		O_RETURN_CD := -9;
END;
/
