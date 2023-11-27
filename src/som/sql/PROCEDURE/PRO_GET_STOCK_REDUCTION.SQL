CREATE OR REPLACE PROCEDURE AP21.PRO_GET_STOCK_REDUCTION
	(
		 P_STR_VENDER_CD				IN	NVARCHAR2
		,P_NUM_PAYABLE_AMOUNT			IN	NUMBER
		,P_STR_CREDIT_DIVISION			OUT	NVARCHAR2
		,P_NUM_NOTE_SIGHT				OUT	NUMBER
		,P_NUM_DISCOUNT_DAYS			OUT	NUMBER
		,P_NUM_STOCK_REDUCTION			OUT	NUMBER
        ,P_NUM_CREDIT_MONTH_DIVISION	OUT NUMBER
		,P_STR_ERROR_RETURN_CD			OUT	NVARCHAR2
		,P_STR_ERROR_RETURN_MSG			OUT	NVARCHAR2
	)
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	PROCEDURE
	名称		:	PRO_GET_STOCK_REDUCTION
	処理内容	:	仕入割引額取得処理
	引数		:	P_STR_VENDER_CD					取引先コード					NVARCHAR2型
	引数		:	P_NUM_PAYABLE_AMOUNT			支払残高(差引繰越額含まず)		NUMBER型
	引数		:	P_STR_CREDIT_DIVISION			支払分類						NVARCHAR2型
	引数		:	P_NUM_NOTE_SIGHT				手形サイト						NUMBER型
	引数		:	P_NUM_DISCOUNT_DAYS				仕入割引日数					NUMBER型
	引数		:	P_NUM_STOCK_REDUCTION			仕入割引額						NUMBER型
	引数		:	P_NUM_CREDIT_MONTH_DIVISION		支払月区分						NUMBER型
	引数		:	P_STR_ERROR_RETURN_CD			エラーコード					NVARCHAR2型
	引数		:	P_STR_ERROR_RETURN_MSG			エラーメッセージ				NVARCHAR2型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2009.07.30	1.00	nanba       		creation
	2009.12.03	1.01	nanba       		入金(支払)月区分追加
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
	nDiscountDays1			VENDER.PURCHASE_DISCOUNT_DAYS1%TYPE;		-- 仕入割引日数１
	nDiscountDays2			VENDER.PURCHASE_DISCOUNT_DAYS2%TYPE;		-- 仕入割引日数２
	nDiscountDays3			VENDER.PURCHASE_DISCOUNT_DAYS3%TYPE;		-- 仕入割引日数３
	nCreditMonthDivision1	VENDER.CREDIT_MONTH_DIVISION1%TYPE;			-- 支払月区分1
	nCreditMonthDivision2	VENDER.CREDIT_MONTH_DIVISION2%TYPE;			-- 支払月区分2
	nCreditMonthDivision3	VENDER.CREDIT_MONTH_DIVISION3%TYPE;			-- 支払月区分3
	nNoteDiv				CLASSIFICATION.NOTE_DIVISION%TYPE;			-- 手形必須区分
	c_CatDivPYSRED			CHAR(1)					:= '7';				-- 分類マスタ集計区分（支払：仕入割引）
    nPrime					NUMBER;										-- 短プラ+金利
    nStockReduction			NUMBER;										-- 仕入割引額
BEGIN
	P_STR_CREDIT_DIVISION := NULL;
	P_NUM_NOTE_SIGHT := NULL;
	P_NUM_DISCOUNT_DAYS := 0;
	P_NUM_STOCK_REDUCTION := 0;
	P_STR_ERROR_RETURN_CD := NULL;
	P_STR_ERROR_RETURN_MSG := NULL;
	--------------------------------------------------------------------------------
	-- 自社・銀行マスタ検索
	--------------------------------------------------------------------------------
	SELECT	NVL(COMPANY.PRIME, 0)	-- 短プラ+金利
	INTO	nPrime
	FROM	COMPANY
	;
	--------------------------------------------------------------------------------
	-- 取引先マスタ検索
	--------------------------------------------------------------------------------
	SELECT	CREDIT_DIVISION3			-- 入金区分３
		,	BOUND_AMOUNT3				-- 境界額３
		,	NOTE_SIGHT3					-- 手形サイト３
		,	DECODE(CREDIT_DIVISION3,c_CatDivPYSRED,PURCHASE_DISCOUNT_DAYS1,0)	-- 仕入割引日数１
        ,	CREDIT_MONTH_DIVISION1		-- 支払月区分1
		,	CREDIT_DIVISION4			-- 入金区分４
		,	BOUND_AMOUNT4				-- 境界額４
		,	NOTE_SIGHT4					-- 手形サイト４
		,	DECODE(CREDIT_DIVISION4,c_CatDivPYSRED,PURCHASE_DISCOUNT_DAYS2,0)	-- 仕入割引日数２
        ,	CREDIT_MONTH_DIVISION2		-- 支払月区分2
		,	CREDIT_DIVISION5			-- 入金区分５
		,	NOTE_SIGHT5					-- 手形サイト５
		,	DECODE(CREDIT_DIVISION5,c_CatDivPYSRED,PURCHASE_DISCOUNT_DAYS3,0)	-- 仕入割引日数３
        ,	CREDIT_MONTH_DIVISION3		-- 支払月区分3
	INTO	nCreditDiv3
		,	nBoundAmount3
		,	nNoteSight3
		,	nDiscountDays1
        ,	nCreditMonthDivision1
		,	nCreditDiv4
		,	nBoundAmount4
		,	nNoteSight4
		,	nDiscountDays2
        ,	nCreditMonthDivision2
		,	nCreditDiv5
		,	nNoteSight5
		,	nDiscountDays3
        ,	nCreditMonthDivision3
	FROM	VENDER
	WHERE	VENDER_DIVISION		=	'SI'
	AND		VENDER_CD			=	P_STR_VENDER_CD
	;
	-- 支払分類、手形サイト、仕入割引日数、設定
	IF  P_NUM_PAYABLE_AMOUNT < nBoundAmount3 THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv3);
		P_NUM_NOTE_SIGHT := nNoteSight3;
		P_NUM_DISCOUNT_DAYS := nDiscountDays1;
        P_NUM_CREDIT_MONTH_DIVISION := nCreditMonthDivision1;
	ELSIF  P_NUM_PAYABLE_AMOUNT < nBoundAmount4 THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv4);
		P_NUM_NOTE_SIGHT := nNoteSight4;
		P_NUM_DISCOUNT_DAYS := nDiscountDays2;
        P_NUM_CREDIT_MONTH_DIVISION := nCreditMonthDivision2;
	ELSIF nBoundAmount4 <= P_NUM_PAYABLE_AMOUNT THEN
		P_STR_CREDIT_DIVISION := TO_CHAR(nCreditDiv5);
		P_NUM_NOTE_SIGHT := nNoteSight5;
		P_NUM_DISCOUNT_DAYS := nDiscountDays3;
        P_NUM_CREDIT_MONTH_DIVISION := nCreditMonthDivision3;
	END IF;
	--------------------------------------------------------------------------------
	-- 分類マスタ検索
	--------------------------------------------------------------------------------
	SELECT	NOTE_DIVISION			-- 手形必須区分
	INTO	nNoteDiv
	FROM	CLASSIFICATION
	WHERE	DATA_TYPE			=	'4'		-- 支払
	AND		CATEGORY_DIVISION	=	P_STR_CREDIT_DIVISION
	AND     ROWNUM = 1
	;
	-- 必須でない場合
	IF nNoteDiv = 0 THEN
		-- 手形サイト再設定
		P_NUM_NOTE_SIGHT := NULL;
	END IF;
	-- 仕入割引額算出
	nStockReduction := P_NUM_PAYABLE_AMOUNT * (nPrime / 100) * (P_NUM_DISCOUNT_DAYS / 365);
	P_NUM_STOCK_REDUCTION := FNC_CHECK_ROUND(nStockReduction, 'SONOTA', 'SI', P_STR_VENDER_CD, NULL);
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		P_STR_ERROR_RETURN_CD := SQLCODE;
		P_STR_ERROR_RETURN_MSG := SUBSTR(SQLERRM, 1, 1024);
END PRO_GET_STOCK_REDUCTION;
/
