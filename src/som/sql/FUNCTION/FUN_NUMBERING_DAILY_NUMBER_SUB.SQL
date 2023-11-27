CREATE OR REPLACE FUNCTION      FUN_NUMBERING_DAILY_NUMBER_SUB
      (
             I_KEY		IN	NVARCHAR2,	-- 発番用KEYコード
             I_DATE		IN	DATE,		-- 発番日付
             I_TANTO_CD		IN	NVARCHAR2,	-- 担当者コード
             O_RETURN_CD	OUT	NVARCHAR2,	-- エラーコードを返す
             O_RETURN_MSG	OUT	NVARCHAR2	-- エラーメッセージを返す
      )
RETURN VARCHAR2 IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_NUMBERING_DAILY_NUMBER_SUB                                            */
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
	ERR_CODE		VARCHAR2(100);
	ERR_MSG			VARCHAR2(2048);
	TEMP_PROC_DATA		VARCHAR2(20);
	TEMP_RETURN_NUMBER	VARCHAR2(20);
	TEMP_COUNT		NUMBER;
	TEMP_MAX_CONSECUTIVE_NO	NUMBER;
	TEMP_RETURN_NO		VARCHAR2(20);
	CONST_INIT_DATA_KEY	VARCHAR2(10) := '20000101';
	TEMP_DAILY_NUMBER	DAILY_NUMBER%ROWTYPE;		-- 日ごと付番号テーブル
	
BEGIN

	-- 発番日付がNULLの場合現在時刻を取得
	IF I_DATE IS NULL THEN 
		TEMP_PROC_DATA := TO_CHAR(SYSDATE,'yyyymmdd');
	ELSE
		TEMP_PROC_DATA := TO_CHAR(I_DATE,'yyyymmdd');
	END IF;

	DBMS_OUTPUT.PUT_LINE('処理日付:' || TEMP_PROC_DATA);
	
	-- データが存在するかチェック
	SELECT COUNT(*) INTO TEMP_COUNT FROM DAILY_NUMBER WHERE DAILY_NUMBER.KEY = I_KEY AND (DAILY_NUMBER.DATEKEY = CONST_INIT_DATA_KEY OR DAILY_NUMBER.DATEKEY = TEMP_PROC_DATA) ORDER BY DAILY_NUMBER.DATEKEY DESC;

	DBMS_OUTPUT.PUT_LINE('処理日付数:' || TEMP_COUNT);

	-- データが存在しない場合NULL値を返す
	IF TEMP_COUNT IS NULL OR TEMP_COUNT = 0 THEN
		RETURN NULL;
	END IF;
	
	-- 対象日の発番レコード無し（デフォルト日付のみあり）
	IF TEMP_COUNT = 1 THEN
		
		DBMS_OUTPUT.PUT_LINE('対象日の発番レコードなし:');

		-- データが存在するので検索結果を保持
		SELECT * INTO TEMP_DAILY_NUMBER FROM DAILY_NUMBER WHERE DAILY_NUMBER.KEY = I_KEY AND  DAILY_NUMBER.DATEKEY = CONST_INIT_DATA_KEY ORDER BY DAILY_NUMBER.DATEKEY DESC FOR UPDATE NOWAIT;
		
		-- 数値部の桁数を取得
		TEMP_MAX_CONSECUTIVE_NO := LENGTH(TEMP_DAILY_NUMBER.MAX_CONSECUTIVE_NO);
	
		DBMS_OUTPUT.PUT_LINE('数値部桁数:' || TEMP_MAX_CONSECUTIVE_NO);
		
		-- 数値部を作成(新規の場合は1から)
		SELECT LPAD('1',TEMP_MAX_CONSECUTIVE_NO,'0') INTO TEMP_RETURN_NUMBER FROM DUAL;
		TEMP_RETURN_NO := TEMP_DAILY_NUMBER.FIXED_CHAR_NM || SUBSTR(TEMP_PROC_DATA,3,6) || TEMP_RETURN_NUMBER;

		-- 新規レコード追加
		INSERT INTO DAILY_NUMBER(
			KEY,				-- ＫＥＹコード
			DATEKEY,			-- 西暦８桁
			FIXED_CHAR,			-- 固定文字数
			FIXED_CHAR_NM,			-- 固定文字
			MIN_CONSECUTIVE_NO,		-- ＭＩＮ連番
			MAX_CONSECUTIVE_NO,		-- ＭＡＸ連番
			CURRENT_CONSECUTIVE_NO,		-- 現在連番
			INPUT_DATE,			-- 登録日時
			INPUTOR_CD,			-- 登録者ID
			UPDATE_DATE,			-- 更新日時
			UPDATOR_CD			-- 更新者ID
		) VALUES (
			I_KEY,					-- ＫＥＹコード
			TEMP_PROC_DATA,				-- 西暦８桁
			TEMP_DAILY_NUMBER.FIXED_CHAR, 		-- 固定文字数
			TEMP_DAILY_NUMBER.FIXED_CHAR_NM,	-- 固定文字
			TEMP_DAILY_NUMBER.MIN_CONSECUTIVE_NO,	-- ＭＩＮ連番
			TEMP_DAILY_NUMBER.MAX_CONSECUTIVE_NO,	-- ＭＡＸ連番
			TEMP_DAILY_NUMBER.CURRENT_CONSECUTIVE_NO,-- 現在連番
			SYSDATE,				-- 登録日時
			I_TANTO_CD,				-- 登録者ID
			SYSDATE,				-- 更新日時
			I_TANTO_CD				-- 更新者ID
		);
		
	ELSE

		DBMS_OUTPUT.PUT_LINE('対象日の発番レコードあり:');

		-- データが存在するので検索結果を保持
		SELECT * INTO TEMP_DAILY_NUMBER FROM DAILY_NUMBER WHERE DAILY_NUMBER.KEY = I_KEY AND  DAILY_NUMBER.DATEKEY = TEMP_PROC_DATA ORDER BY DAILY_NUMBER.DATEKEY DESC FOR UPDATE NOWAIT;
		
		-- 数値部の桁数を取得
		TEMP_MAX_CONSECUTIVE_NO := LENGTH(TEMP_DAILY_NUMBER.MAX_CONSECUTIVE_NO);
	
		DBMS_OUTPUT.PUT_LINE('数値部桁数:' || TEMP_MAX_CONSECUTIVE_NO);

		-- 数値部を作成
		SELECT LPAD(TO_CHAR(TEMP_DAILY_NUMBER.CURRENT_CONSECUTIVE_NO+1),TEMP_MAX_CONSECUTIVE_NO,'0') INTO TEMP_RETURN_NUMBER FROM DUAL;
		TEMP_RETURN_NO := TEMP_DAILY_NUMBER.FIXED_CHAR_NM || SUBSTR(TEMP_PROC_DATA,3,6) || TEMP_RETURN_NUMBER;

		UPDATE DAILY_NUMBER SET CURRENT_CONSECUTIVE_NO = TEMP_DAILY_NUMBER.CURRENT_CONSECUTIVE_NO + 1 WHERE KEY = I_KEY AND  DATEKEY = TEMP_PROC_DATA;
		
	END IF;

	DBMS_OUTPUT.PUT_LINE('戻り値:' || TEMP_RETURN_NO);
	
	RETURN TEMP_RETURN_NO;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	O_RETURN_CD := SQLCODE;
		O_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);

		RETURN NULL;
END;
/
