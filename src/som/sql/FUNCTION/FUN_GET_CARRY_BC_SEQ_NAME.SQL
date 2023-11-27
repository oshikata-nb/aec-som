CREATE OR REPLACE FUNCTION FUN_GET_CARRY_BC_SEQ_NAME(
             I_CARRY_CD			    IN	VARCHAR2	-- 運送会社コード
)
RETURN VARCHAR2	-- 戻り値のデータ型
IS
----------------------------------------------------------------------------------------------------------------
--  運送会社バーコードシーケンス名称を取得する
--	種別		:	FUNCTION
--	名称		:	FUN_GET_CARRY_BC_SEQ_NAME
--	処理内容	:	運送会社バーコードシーケンス名称取得
--	引数1		:	I_CARRY_CD		運送会社コード VARCHAR2型
--	戻値		:	運送会社バーコードシーケンス名称					VARCHAR2型
--	VERNO.		:	1.00
--(history)
--	date		ver		name				comments
--	----------	------	------------------	----------------------------------
--	2020.10.20	1.00	Kiguchi     		creation
-----------------------------------------------------------------------------------------------------------------
BEGIN
	
    RETURN 'SEQ_CARRY_BC_' || I_CARRY_CD;
    
-- その他例外処理のためEXCEPTIONを設定しておく
EXCEPTION

	WHEN OTHERS THEN
	RETURN '';
END;
/
