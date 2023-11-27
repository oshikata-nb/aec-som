CREATE OR REPLACE PROCEDURE PRO_CTL_CARRY_BC_SEQ
      (
             I_CARRY_CD			    IN	VARCHAR2,	-- 運送会社コード
             I_MIN_VALUE		    IN	NUMBER,	-- 連番開始
             I_MAX_VALUE		    IN	NUMBER,	-- 連番終了
             I_CUR_VALUE		    IN	NUMBER,	-- 連番現在値
             I_DEL_FLG  		    IN	NUMBER,	-- 削除フラグ
             O_ERROR_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		IN OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		IN OUT	NVARCHAR2	-- 戻り値
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      CTL_CARRY_BC_SEQ                                             */
/*      処理内容      :      運送会社別のバーコード用シーケンスを管理する	       、      */
/*      引数          :      I_CARRY_CD		運送会社コード	VARCHAR2型            */
/*      引数          :      I_MIN_VALUE		連番開始		NUMBER型            */
/*      引数          :      I_MAX_VALUE			連番終了		NUMBER型            */
/*      引数          :      I_CUR_VALUE			連番現在値		NUMBER型            */
/*      引数          :      I_DEL_FLG			削除フラグ		NUMBER型            */
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
BEGIN
	O_RETURN_CD := 0;

    SEQ_NAME := FUN_GET_CARRY_BC_SEQ_NAME(I_CARRY_CD);

    -- 既存シーケンス情報の取得
    SELECT COUNT(*) INTO COUNTER
    FROM USER_SEQUENCES
    WHERE SEQUENCE_NAME = SEQ_NAME;

    -- 作成or削除の切り分け
    IF COUNTER = 0 AND I_DEL_FLG = 0 THEN
        SQL_TEXT := 'CREATE SEQUENCE ' || SEQ_NAME || ' START WITH ' || I_CUR_VALUE || ' MAXVALUE ' || I_MAX_VALUE || ' MINVALUE '  || I_MIN_VALUE || ' CYCLE NOCACHE';
    ELSIF COUNTER > 0 AND I_DEL_FLG = 1 THEN
        SQL_TEXT := 'DROP SEQUENCE ' || SEQ_NAME;
    END IF;

    -- SQL実行
    IF NVL(LENGTH(TRIM(SQL_TEXT)),0) > 0 THEN
        EXECUTE IMMEDIATE SQL_TEXT;
    END IF;


    RETURN;

	
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		OUTPUT_ERROR_LOG('CTL_CARRY_BC_SEQ','AUTO',SQL_TEXT,O_ERROR_RETURN_MSG);
		O_RETURN_CD := -9;
END;
/
