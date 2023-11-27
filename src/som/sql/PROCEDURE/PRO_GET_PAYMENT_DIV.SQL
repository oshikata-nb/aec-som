CREATE OR REPLACE PROCEDURE      PRO_GET_PAYMENT_DIV
	(
		 P_STR_VENDER_CD		IN	NVARCHAR2
		,P_NUM_PAYABLE_AMOUNT	IN	NUMBER
		,P_STR_CREDIT_DIVISION	OUT	NVARCHAR2
		,P_NUM_NOTE_SIGHT		OUT	NUMBER
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_GET_PAYMENT_DIV
	処理内容	:	支払分類、手形サイト取得処理
	引数		:	P_STR_VENDER_CD			取引先コード					NVARCHAR2型
	引数		:	P_NUM_PAYABLE_AMOUNT	支払残高(差引繰越額含まず)		NUMBER型
	引数		:	P_STR_CREDIT_DIVISION	支払分類						NVARCHAR2型
	引数		:	P_NUM_NOTE_SIGHT		手形サイト						NUMBER型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.24	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	nvCategoryDiv1			VENDER.CATEGORY_DIVISION1%TYPE;				-- 入金／支払分類１
	nvCategoryDiv2			VENDER.CATEGORY_DIVISION2%TYPE;				-- 入金／支払分類２
	nvCategoryDiv3			VENDER.CATEGORY_DIVISION3%TYPE;				-- 入金／支払分類３
	--nvCategoryDiv4			VENDER.CATEGORY_DIVISION4%TYPE;				-- 入金／支払分類４
	--nvCategoryDiv5			VENDER.CATEGORY_DIVISION5%TYPE;				-- 入金／支払分類５
	nBoundAmount1			VENDER.BOUND_AMOUNT1%TYPE;					-- 境界額１
	nBoundAmount2			VENDER.BOUND_AMOUNT2%TYPE;					-- 境界額２
	nBoundAmount3			VENDER.BOUND_AMOUNT3%TYPE;					-- 境界額３
	nBoundAmount4			VENDER.BOUND_AMOUNT4%TYPE;					-- 境界額４
	nNoteSight1				VENDER.NOTE_SIGHT1%TYPE;					-- 手形サイト１
	nNoteSight2				VENDER.NOTE_SIGHT2%TYPE;					-- 手形サイト２
	nNoteSight3				VENDER.NOTE_SIGHT3%TYPE;					-- 手形サイト３
	nNoteSight4				VENDER.NOTE_SIGHT4%TYPE;					-- 手形サイト４
	nNoteSight5				VENDER.NOTE_SIGHT5%TYPE;					-- 手形サイト５
	nNoteDiv				CLASSIFICATION.NOTE_DIVISION%TYPE;			-- 手形必須区分

BEGIN

	--------------------------------------------------------------------------------
	-- 取引先マスタ検索
	--------------------------------------------------------------------------------
	SELECT	CATEGORY_DIVISION1			-- 入金／支払分類１
		,	BOUND_AMOUNT1				-- 境界額１
		,	NOTE_SIGHT1					-- 手形サイト１
		,	CATEGORY_DIVISION2			-- 入金／支払分類２
		,	BOUND_AMOUNT2				-- 境界額２
		,	NOTE_SIGHT2					-- 手形サイト２
		,	CATEGORY_DIVISION3			-- 入金／支払分類３
		,	BOUND_AMOUNT3				-- 境界額３
		,	NOTE_SIGHT3					-- 手形サイト３
		--,	CATEGORY_DIVISION4			-- 入金／支払分類４
		,	BOUND_AMOUNT4				-- 境界額４
		,	NOTE_SIGHT4					-- 手形サイト４
		--,	CATEGORY_DIVISION5			-- 入金／支払分類５
		,	NOTE_SIGHT5					-- 手形サイト５
	INTO	nvCategoryDiv1
		,	nBoundAmount1
		,	nNoteSight1
		,	nvCategoryDiv2
		,	nBoundAmount2
		,	nNoteSight2
		,	nvCategoryDiv3
		,	nBoundAmount3
		,	nNoteSight3
		--,	nvCategoryDiv4
		,	nBoundAmount4
		,	nNoteSight4
		--,	nvCategoryDiv5
		,	nNoteSight5
	FROM	VENDER
	WHERE	VENDER_DIVISION		=	'SI'
	AND		VENDER_CD			=	P_STR_VENDER_CD
	;

	-- 支払分類、手形サイト設定
	IF P_NUM_PAYABLE_AMOUNT < nBoundAmount1 THEN
		P_STR_CREDIT_DIVISION := nvCategoryDiv1;
		P_NUM_NOTE_SIGHT := nNoteSight1;
	ELSIF  P_NUM_PAYABLE_AMOUNT < nBoundAmount2 THEN
		P_STR_CREDIT_DIVISION := nvCategoryDiv2;
		P_NUM_NOTE_SIGHT := nNoteSight2;
	ELSIF  P_NUM_PAYABLE_AMOUNT < nBoundAmount3 THEN
		P_STR_CREDIT_DIVISION := nvCategoryDiv3;
		P_NUM_NOTE_SIGHT := nNoteSight3;
	ELSIF  P_NUM_PAYABLE_AMOUNT < nBoundAmount4 THEN
	--	P_STR_CREDIT_DIVISION := nvCategoryDiv4;
		P_NUM_NOTE_SIGHT := nNoteSight4;
	ELSIF nBoundAmount4 <= P_NUM_PAYABLE_AMOUNT THEN
	--	P_STR_CREDIT_DIVISION := nvCategoryDiv5;
		P_NUM_NOTE_SIGHT := nNoteSight5;
	END IF;

	--------------------------------------------------------------------------------
	-- 分類マスタ検索
	--------------------------------------------------------------------------------
	SELECT	NOTE_DIVISION			-- 手形必須区分
	INTO	nNoteDiv
	FROM	CLASSIFICATION
	WHERE	DATA_TYPE			=	'4'		-- 支払
	AND		CATEGORY_DIVISION	=	P_STR_CREDIT_DIVISION
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

END PRO_GET_PAYMENT_DIV;
/
