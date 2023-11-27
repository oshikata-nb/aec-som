CREATE OR REPLACE FUNCTION      FUN_GET_SLIP_NO
	(
		 P_NUM_KEY				IN	NUMBER
		,P_STR_UPDATOR_CD		IN	NVARCHAR2
	)
RETURN	NVARCHAR2
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	FUNCTION
	名称		:	FUN_GET_SLIP_NO
	処理内容	:	伝票番号取得・更新処理
	引数		:	P_NUM_KEY			伝票番号管理マスタ.KEY	NUMBER型
	引数		:	P_STR_UPDATOR_CD	登録者ＩＤ				NVARCHAR2型
	戻値		:	nvSlipNo			伝票番号				NVARCHAR2型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.07.28	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--変数宣言
	--------------------------------------------------------------------------------
	nvFixedCharNm			SLIP_NUMBER.FIXED_CHAR_NM%TYPE;								-- 固定文字
	nmMinNo					SLIP_NUMBER.MIN_CONSECUTIVE_NO%TYPE;						-- MIN連番
	nmMaxNo					SLIP_NUMBER.MAX_CONSECUTIVE_NO%TYPE;						-- MAX連番
	nmCurrentNo				SLIP_NUMBER.CURRENT_CONSECUTIVE_NO%TYPE;					-- 現在連番
	nvSlipNo				NVARCHAR2(20);												-- 伝票番号
	nmEndLoopCnt			NUMBER;														-- ループ終了値
	nvZeroSuppress			NVARCHAR2(20);												-- ゼロサプレス

BEGIN
	--------------------------------------------------------------------------------
	--★伝票番号管理マスタ検索
	--------------------------------------------------------------------------------
	SELECT
		 FIXED_CHAR_NM					--固定文字
		,MIN_CONSECUTIVE_NO				--MIN連番
		,MAX_CONSECUTIVE_NO				--MAX連番
		,CURRENT_CONSECUTIVE_NO			--現在連番
	INTO
		 nvFixedCharNm
		,nmMinNo
		,nmMaxNo
		,nmCurrentNo
	FROM
		SLIP_NUMBER
	WHERE
		KEY = P_NUM_KEY					--KEY
	;

	-- 連番設定
	IF nmCurrentNo < nmMaxNo THEN
		nmCurrentNo := nmCurrentNo + 1;
	ELSE
		nmCurrentNo := nmMinNo;
	END IF;

	-- ゼロサプレス
	nmEndLoopCnt := LENGTH(nmMaxNo) - LENGTH(nmCurrentNo);
	FOR i IN 1..nmEndLoopCnt LOOP
		nvZeroSuppress := CONCAT(nvZeroSuppress, '0');
	END LOOP;

	-- 伝票番号設定(固定文字+ゼロサプレス+連番)
	nvSlipNo := CONCAT(CONCAT(nvFixedCharNm, nvZeroSuppress), nmCurrentNo);

	--------------------------------------------------------------------------------
	--★伝票番号管理マスタ更新
	--------------------------------------------------------------------------------
	UPDATE SLIP_NUMBER
		SET
			CURRENT_CONSECUTIVE_NO	=	nmCurrentNo
		,	UPDATE_DATE				=	SYSDATE
		,	UPDATOR_CD				=	P_STR_UPDATOR_CD
	WHERE
		KEY = P_NUM_KEY					--KEY
	;

	RETURN nvSlipNo;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		--ロールバック
		ROLLBACK;
		RAISE;

END FUN_GET_SLIP_NO;
/
