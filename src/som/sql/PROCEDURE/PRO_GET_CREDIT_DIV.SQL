CREATE OR REPLACE PROCEDURE      PRO_GET_CREDIT_DIV
	(
		 P_STR_VENDER_CD		IN	NVARCHAR2
		,P_NUM_CLAIM_AMOUNT		IN	NUMBER
		,P_STR_CREDIT_DIVISION	OUT	NVARCHAR2
		,P_NUM_NOTE_SIGHT		OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_GET_CREDIT_DIV
	処理内容	:	入金分類(決済方法)、手形サイト取得処理
	引数		:	P_STR_VENDER_CD			取引先コード					NVARCHAR2型
	引数		:	P_NUM_CLAIM_AMOUNT		今回請求額(差引繰越額含まず)	NUMBER型
	引数		:	P_STR_CREDIT_DIVISION	入金分類（決済方法）			NVARCHAR2型
	引数		:	P_NUM_NOTE_SIGHT		手形サイト						NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.08.28	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	nCreditDiv3				VENDER.CREDIT_DIVISION3%TYPE;				-- 入金区分３
	nCreditDiv4				VENDER.CREDIT_DIVISION4%TYPE;				-- 入金区分４
	nCreditDiv5				VENDER.CREDIT_DIVISION5%TYPE;				-- 入金区分５
	nBoundAmount3			VENDER.BOUND_AMOUNT3%TYPE;					-- 境界額３
	nBoundAmount4			VENDER.BOUND_AMOUNT4%TYPE;					-- 境界額４
	nNoteSight3				VENDER.NOTE_SIGHT3%TYPE;					-- 手形サイト３
	nNoteSight4				VENDER.NOTE_SIGHT4%TYPE;					-- 手形サイト４
	nNoteSight5				VENDER.NOTE_SIGHT5%TYPE;					-- 手形サイト５
	nNoteDiv				CLASSIFICATION.NOTE_DIVISION%TYPE;			-- 手形必須区分

BEGIN

	--------------------------------------------------------------------------------
	-- 取引先マスタ検索
	--------------------------------------------------------------------------------
	SELECT	CREDIT_DIVISION3			-- 入金区分３
		,	BOUND_AMOUNT3				-- 境界額３
		,	NOTE_SIGHT3					-- 手形サイト３
		,	CREDIT_DIVISION4			-- 入金区分４
		,	BOUND_AMOUNT4				-- 境界額４
		,	NOTE_SIGHT4					-- 手形サイト４
		,	CREDIT_DIVISION5			-- 入金区分５
		,	NOTE_SIGHT5					-- 手形サイト５
	INTO	nCreditDiv3
		,	nBoundAmount3
		,	nNoteSight3
		,	nCreditDiv4
		,	nBoundAmount4
		,	nNoteSight4
		,	nCreditDiv5
		,	nNoteSight5
	FROM	VENDER
	WHERE	VENDER_DIVISION		=	'TS'
	AND		VENDER_CD			=	P_STR_VENDER_CD
	;

	-- 入金分類（決済方法）、手形サイト設定
	IF  P_NUM_CLAIM_AMOUNT < nBoundAmount3 THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv3);
		P_NUM_NOTE_SIGHT := nNoteSight3;
	ELSIF  P_NUM_CLAIM_AMOUNT < nBoundAmount4 THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv4);
		P_NUM_NOTE_SIGHT := nNoteSight4;
	ELSIF nBoundAmount4 <= P_NUM_CLAIM_AMOUNT THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv5);
		P_NUM_NOTE_SIGHT := nNoteSight5;
	END IF;

	--------------------------------------------------------------------------------
	-- 分類マスタ検索
	--------------------------------------------------------------------------------
	SELECT	NOTE_DIVISION			-- 手形必須区分
	INTO	nNoteDiv
	FROM	CLASSIFICATION
	WHERE	DATA_TYPE			=	'2'		-- 入金
	AND		CATEGORY_DIVISION	=	TO_CHAR(P_STR_CREDIT_DIVISION)
	AND     ROWNUM = 1
	;

	-- 必須でない場合
	IF nNoteDiv = 0 THEN
		-- 手形サイト再設定
		P_NUM_NOTE_SIGHT := NULL;
	END IF;

EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		P_STR_CREDIT_DIVISION := NULL;
		P_NUM_NOTE_SIGHT := NULL;

END PRO_GET_CREDIT_DIV;
/
