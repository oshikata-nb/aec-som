CREATE OR REPLACE FUNCTION      FUN_NUMBERING_DAILY_NUMBER
      (
             I_KEY		IN	NVARCHAR2,	-- 発番用KEYコード
             I_DATE		IN	DATE,		-- 発番日付
             I_TANTO_CD		IN	NVARCHAR2,	-- 担当者コード
             IO_RETURN_CD	IN OUT	NVARCHAR2,	-- エラーコードを返す
             IO_RETURN_MSG	IN OUT	NVARCHAR2	-- エラーメッセージを返す
      )
RETURN VARCHAR2 IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_NUMBERING_DAILY_NUMBER	                                               */
/*      処理内容      :      日毎発版処理							       */
/*      引数          :      I_KEY		発番用KEYコード			NVARCHAR2型            */
/*      引数          :      I_DATE		発番日付			DATE型                 */
/*      引数          :      I_TANTO_CD		担当者コード			NVARCHAR2              */
/*      引数          :      O_RETURN_CD	エラーコード			NVARCHAR2              */
/*      引数          :      O_RETURN_MSG	エラーメッセージ		NVARCHAR2              */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/

	TEMP_RETURN_NUMBER	VARCHAR2(20);
	
	CONST_DAILY_NUMBER_RETRY_COUNT	NUMBER := 2;
	
BEGIN
	
	-- リトライカウント分リトライ
	FOR i IN 1..CONST_DAILY_NUMBER_RETRY_COUNT LOOP	
		
		TEMP_RETURN_NUMBER := FUN_NUMBERING_DAILY_NUMBER_SUB(I_KEY,I_DATE,I_TANTO_CD,IO_RETURN_CD,IO_RETURN_MSG);
		
		-- 発番できた場合結果を返す
		IF TEMP_RETURN_NUMBER IS NOT NULL THEN
			DBMS_OUTPUT.PUT_LINE('バーコード:' ||TEMP_RETURN_NUMBER);
			RETURN TEMP_RETURN_NUMBER;
		END IF;
		
		-- １秒待つ
		DBMS_LOCK.SLEEP(0.2);
		
		DBMS_OUTPUT.PUT_LINE('時間:' || TO_CHAR(SYSDATE,'YYYY/MM/DD hh:mm:ss'));

	END LOOP;

	RETURN NULL;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	IO_RETURN_CD := SQLCODE;
		IO_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		RETURN NULL;
END;
/
