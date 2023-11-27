CREATE OR REPLACE PACKAGE PAK_CARRY_FILE IS

	-- 運送会社連携ファイルの作成
	PROCEDURE MAKE_CARRY_FILE(
             I_CARRY_CD			    IN	VARCHAR2,	-- 運送会社コード
             I_USER_CD			    IN	VARCHAR2,	-- ログインユーザーコード
             I_SHIPPING_NO_LIST	    IN	VARCHAR2,	-- 出荷番号リスト
             O_ERROR_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		IN OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		IN OUT	NVARCHAR2 );
END;
/
