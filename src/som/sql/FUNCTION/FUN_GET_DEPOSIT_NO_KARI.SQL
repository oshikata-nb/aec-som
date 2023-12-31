CREATE OR REPLACE FUNCTION      FUN_GET_DEPOSIT_NO_KARI
	(
		 P_STR_UPDATOR_CD		IN	NVARCHAR2
	)
RETURN	NVARCHAR2
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	FUNCTION
	名称		:	FUN_GET_DEPOSIT_NO_KARI
	処理内容	:	仮締め用売掛番号取得処理
	引数		:	P_STR_UPDATOR_CD		登録者ＩＤ			NVARCHAR2型
	戻値		:	nvDepositNoKari			仮締め用売掛番号	NVARCHAR2型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.16	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_Key					CONSTANT SLIP_NUMBER.KEY%TYPE				:= 201;			-- 仮締め用売掛番号KEY

	nvDepositNoKari			DEPOSIT_HEADER.DEPOSIT_NO%TYPE;								-- 売掛番号

BEGIN
	--------------------------------------------------------------------------------
	--★売掛番号取得
	--------------------------------------------------------------------------------
	nvDepositNoKari := FUN_GET_SLIP_NO(c_Key, P_STR_UPDATOR_CD);

	RETURN nvDepositNoKari;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		RAISE;

END FUN_GET_DEPOSIT_NO_KARI;
/
