CREATE OR REPLACE PROCEDURE PRO_GET_CARRY_BC_NEXT_VAL
      (
             I_CARRY_CD			    IN	VARCHAR2,	-- 運送会社コード
             O_NEXT_VAL  		        IN OUT	NUMBER,	-- 連番
             O_ERROR_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		IN OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		IN OUT	NUMBER	-- 戻り値
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_GET_CARRY_BC_SEQ                                             */
/*      処理内容      :      運送会社別のバーコード用シーケンスを取得する	       、      */
/*      引数          :      I_CARRY_CD		運送会社コード	VARCHAR2型            */
/*      引数          :      O_NEXT_VAL		連番		NUMBER型            */
/*      引数          :      O_ERROR_RETURN_CD		エラーコード		NVARCHAR2型            */
/*      引数          :      O_ERROR_RETURN_MSG		エラーメッセージ	NVARCHAR2型            */
/*      引数          :      O_RETURN_CD		戻り値　		NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2020.12.08      1.00        Kiguchi                 新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	SQL_TEXT 			VARCHAR2(4000);
    SEQ_NAME            VARCHAR2(128);
    COUNTER             NUMBER;
    SEQ NUMBER;
BEGIN
	O_RETURN_CD := 0;
    SEQ := 0;

    SEQ_NAME := FUN_GET_CARRY_BC_SEQ_NAME(I_CARRY_CD);

    -- 既存シーケンス情報の取得
    SELECT COUNT(*) INTO COUNTER
    FROM USER_SEQUENCES
    WHERE SEQUENCE_NAME = SEQ_NAME;

    -- シーケンスが存在すれば、データを取得
    IF COUNTER > 0  THEN
        SQL_TEXT := 'SELECT ' || SEQ_NAME || '.NextVal FROM DUAL';

        -- SQL実行
        EXECUTE IMMEDIATE SQL_TEXT INTO SEQ;

        -- 出力値に設定
        O_NEXT_VAL := SEQ;
    ELSE
		O_RETURN_CD := -9;
    END IF;

    RETURN;
	
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		OUTPUT_ERROR_LOG('PRO_GET_CARRY_BC_SEQ','AUTO',SQL_TEXT,O_ERROR_RETURN_MSG);
		O_RETURN_CD := -9;
END;
/
