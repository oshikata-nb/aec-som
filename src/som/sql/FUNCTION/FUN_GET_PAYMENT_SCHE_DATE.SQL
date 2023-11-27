CREATE OR REPLACE FUNCTION AP21.FUN_GET_PAYMENT_SCHE_DATE
	(
		 P_STR_VENDER_CD			IN	NVARCHAR2
		,P_STR_PAYABLE_DATE			IN	NVARCHAR2
		,P_NUM_PAYABLE_AMOUNT		IN	NUMBER
	)
RETURN	NVARCHAR2
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	FUNCTION
	名称		:	FUN_GET_PAYMENT_SCHE_DATE
	処理内容	:	支払予定日取得処理
	引数		:	P_STR_VENDER_CD			支払先コード					NVARCHAR2型
	引数		:	P_STR_PAYABLE_DATE		支払締め日(YYYYMMDD)			NVARCHAR2型
	引数		:	P_NUM_PAYABLE_AMOUNT	支払残高(差引繰越額含まず)		NUMBER型
	戻値		:	nvCreditScheDate		支払予定日(YYYYMMDD)			NVARCHAR2型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.24	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_CreditMonthDiv0		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 1;		-- 支払月区分(当月)
	c_CreditMonthDiv1		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 2;		-- 支払月区分(翌月)
	c_CreditMonthDiv2		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 3;		-- 支払月区分(翌々月)
	c_CreditMonthDiv3		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 4;		-- 支払月区分(３ヶ月)
	c_CreditMonthDiv4		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 5;		-- 支払月区分(４ヶ月)
	c_CreditMonthDiv5		VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 6;		-- 支払月区分(５ヶ月)
	c_HolidayFlg0			VENDER.HOLIDAY_FLG%TYPE					:= 1;		-- 休日指定フラグ(前倒し)
	c_HolidayFlg1			VENDER.HOLIDAY_FLG%TYPE					:= 2;		-- 休日指定フラグ(先送り)
	c_HolidayFlg2			VENDER.HOLIDAY_FLG%TYPE					:= 3;		-- 休日指定フラグ(休日を考慮しない)
	c_WeekHolidayFlg0		CAL.CAL_HOLIDAY%TYPE					:= 0;		-- 平日休日フラグ(平日)
	c_WeekHolidayFlg1		CAL.CAL_HOLIDAY%TYPE					:= 1;		-- 平日休日フラグ(休日)
	c_CatDivPYNOTE		CHAR(1)					:= '1';			-- 分類マスタ分類コード（支払：手形）
	nBoundAmount3			VENDER.BOUND_AMOUNT3%TYPE;							-- 境界額３
	nBoundAmount4			VENDER.BOUND_AMOUNT4%TYPE;							-- 境界額４
	nCreditMonthDiv			VENDER.CREDIT_MONTH_DIVISION%TYPE;					-- 支払月区分
	nCreditMonthDiv1		VENDER.CREDIT_MONTH_DIVISION1%TYPE;					-- 支払月区分1
	nCreditMonthDiv2		VENDER.CREDIT_MONTH_DIVISION2%TYPE;					-- 支払月区分2
	nCreditMonthDiv3		VENDER.CREDIT_MONTH_DIVISION3%TYPE;					-- 支払月区分3
	nCreditScheDay			VENDER.CREDIT_SCHEDULED_DATE%TYPE;					-- 支払予定日(DD)(取引先マスタ)
	nCreditScheDay1			VENDER.CREDIT_SCHEDULED_DATE1%TYPE;					-- 支払予定日1(DD)(取引先マスタ)
	nCreditScheDay2			VENDER.CREDIT_SCHEDULED_DATE2%TYPE;					-- 支払予定日2(DD)(取引先マスタ)
	nCreditScheDay3			VENDER.CREDIT_SCHEDULED_DATE3%TYPE;					-- 支払予定日3(DD)(取引先マスタ)
	vCreditScheDay			VARCHAR2(2);										-- 支払予定日(DD)(文字列)
	nHolidayFlg				VENDER.HOLIDAY_FLG%TYPE;							-- 休日指定フラグ
	daPayableDate			PAYMENT_HEADER.PAYABLE_DATE%TYPE;					-- 支払締め日
	nvPayableDateYM			NVARCHAR2(6);										-- 支払締め年月(YYYYMM)
	daPaymentScheDate		DATE;												-- 支払予定日
	daTmpPaymentScheDate	DATE;												-- 支払予定日(仮)
	daCalDate				CAL.CAL_DATE%TYPE;									-- 年月日
	nCalHoliday				CAL.CAL_HOLIDAY%TYPE;								-- 休日区分 0:平日 1:休日
	nCreditDiv3				VENDER.CREDIT_DIVISION3%TYPE;				-- 支払区分３
	nCreditDiv4				VENDER.CREDIT_DIVISION4%TYPE;				-- 支払区分４
	nCreditDiv5				VENDER.CREDIT_DIVISION5%TYPE;				-- 支払区分５
	nCreditDiv				CLASSIFICATION.CATEGORY_DIVISION%TYPE;				-- 支払区分
BEGIN
	-- 文字型をDate型へキャスト
	daPayableDate := TO_DATE(P_STR_PAYABLE_DATE, 'YYYY/MM/DD');
	-- 支払締め年月(YYYYMM)
	nvPayableDateYM := SUBSTR(P_STR_PAYABLE_DATE, 1, 6);
	--------------------------------------------------------------------------------
	--★取引先マスタ検索
	--------------------------------------------------------------------------------
	SELECT	 CREDIT_MONTH_DIVISION1			-- 支払月区分１
			,CREDIT_MONTH_DIVISION2			-- 支払月区分２
			,CREDIT_MONTH_DIVISION3			-- 支払月区分３
			,CREDIT_SCHEDULED_DATE1			-- 支払予定日１
			,CREDIT_SCHEDULED_DATE2			-- 支払予定日２
			,CREDIT_SCHEDULED_DATE3			-- 支払予定日３
			,BOUND_AMOUNT3					-- 境界額３
			,BOUND_AMOUNT4					-- 境界額４
			,HOLIDAY_FLG					-- 休日指定フラグ
			,CREDIT_DIVISION3			-- 支払区分３
			,CREDIT_DIVISION4			-- 支払区分４
			,CREDIT_DIVISION5			-- 支払区分５
	INTO	 nCreditMonthDiv1
			,nCreditMonthDiv2
			,nCreditMonthDiv3
			,nCreditScheDay1
			,nCreditScheDay2
			,nCreditScheDay3
			,nBoundAmount3
			,nBoundAmount4
			,nHolidayFlg
			,nCreditDiv3				-- 入金区分３
			,nCreditDiv4				-- 入金区分４
			,nCreditDiv5				-- 入金区分５
	FROM	VENDER
	WHERE	VENDER_DIVISION		=	'SI'
	AND		VENDER_CD			=	P_STR_VENDER_CD
	;
	--------------------------------------------------------------------------------
	--金額による支払予定日、支払月区分の選定
	--------------------------------------------------------------------------------
--	IF  P_NUM_PAYABLE_AMOUNT < nBoundAmount3 THEN
--		nCreditMonthDiv := nCreditMonthDiv1;
--		nCreditScheDay := nCreditScheDay1;
--		nCreditDiv := TO_CHAR(nCreditDiv3);
--	ELSIF  P_NUM_PAYABLE_AMOUNT < nBoundAmount4 THEN
--		nCreditMonthDiv := nCreditMonthDiv2;
--		nCreditScheDay := nCreditScheDay2;
--		nCreditDiv := TO_CHAR(nCreditDiv4);
--	ELSIF nBoundAmount4 <= P_NUM_PAYABLE_AMOUNT THEN
--		nCreditMonthDiv := nCreditMonthDiv3;
--		nCreditScheDay := nCreditScheDay3;
--		nCreditDiv := TO_CHAR(nCreditDiv5);
--	END IF;

	IF  P_NUM_PAYABLE_AMOUNT > nBoundAmount4 THEN
		nCreditMonthDiv := nCreditMonthDiv3;
		nCreditScheDay := nCreditScheDay3;
		nCreditDiv := TO_CHAR(nCreditDiv5);
	ELSIF  P_NUM_PAYABLE_AMOUNT > nBoundAmount3 THEN
		nCreditMonthDiv := nCreditMonthDiv2;
		nCreditScheDay := nCreditScheDay2;
		nCreditDiv := TO_CHAR(nCreditDiv4);
	ELSE
		nCreditMonthDiv := nCreditMonthDiv1;
		nCreditScheDay := nCreditScheDay1;
		nCreditDiv := TO_CHAR(nCreditDiv3);
	END IF;
	--------------------------------------------------------------------------------
	--支払予定日設定
	--------------------------------------------------------------------------------
	-- 月末指定(99)の場合
	IF nCreditScheDay = 99 THEN
		-- 支払予定日＝支払締め日の月末日付
		daPaymentScheDate := LAST_DAY(daPayableDate);
	ELSE
		-- 支払予定日(DD2桁)
		vCreditScheDay := TO_CHAR(nCreditScheDay);
		IF LENGTH(vCreditScheDay) = 1 THEN
			vCreditScheDay := '0' || vCreditScheDay;
		END IF;
		-- 支払締め日の月末日＜支払予定日(DD2桁)
		IF TO_CHAR(LAST_DAY(daPayableDate), 'DD') < vCreditScheDay THEN
			-- 支払予定日＝支払締め日の月末日付
			daPaymentScheDate := LAST_DAY(daPayableDate);
		ELSE
			-- 支払予定日＝支払締め年月＋支払予定日(DD2桁)
			daPaymentScheDate := TO_DATE(nvPayableDateYM || vCreditScheDay, 'YYYY/MM/DD');
		END IF;
	END IF;
	-- 支払月区分により支払予定日設定
	IF nCreditMonthDiv = c_CreditMonthDiv0 THEN
		daPaymentScheDate := daPaymentScheDate;					-- 当月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv1 THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 1);	-- 翌月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv2 THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 2);	-- 翌々月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv3 THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 3);	-- ３ヶ月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv4 THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 4);	-- ４ヶ月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv5 THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 5);	-- ５ヶ月
	END IF;
	-- 支払予定日＜支払締め日の場合
	IF daPaymentScheDate < daPayableDate THEN
		daPaymentScheDate := ADD_MONTHS(daPaymentScheDate, 1);	-- ＋１ヶ月
	END IF;
	-- 休日指定フラグ＝「休日を考慮しない」場合
	IF nHolidayFlg = c_HolidayFlg2 THEN
		RETURN TO_CHAR(daPaymentScheDate, 'YYYYMMDD');
	END IF;
	-- 支払区分が１：手形の場合、休日指定フラグを「前倒し」とする。
	IF nCreditDiv = c_CatDivPYNOTE THEN
		nHolidayFlg := c_HolidayFlg0;	-- 休日指定フラグ＝「前倒し」
	END IF;
	--------------------------------------------------------------------------------
	--★カレンダーマスタ検索
	--------------------------------------------------------------------------------
	SELECT	 CAL.CAL_DATE							-- 年月日
			,CAL_HOLIDAY							-- 休日
	INTO	 daCalDate
			,nCalHoliday
	FROM	VENDER
			,CAL
	WHERE	VENDER.VENDER_DIVISION		=	'SI'
	AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
	AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
	AND		CAL.CAL_DATE				=	daPaymentScheDate			-- 年月日
	;
	-- 平日の場合
	IF nCalHoliday = c_WeekHolidayFlg0 THEN
		RETURN TO_CHAR(daPaymentScheDate, 'YYYYMMDD');
	END IF;
	--------------------------------------------------------------------------------
	-- 以降、支払予定日＝休日の場合
	--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	-- 休日指定フラグ＝「前倒し」場合
	--------------------------------------------------------------------------------
	IF nHolidayFlg = c_HolidayFlg0 THEN
		daTmpPaymentScheDate := daPaymentScheDate;
		LOOP
			daTmpPaymentScheDate := daTmpPaymentScheDate - 1;
			--------------------------------------------------------------------------------
			--★カレンダーマスタ再検索
			--------------------------------------------------------------------------------
			SELECT	CAL.CAL_DATE							-- 年月日
					,CAL_HOLIDAY							-- 休日
			INTO	 daCalDate
					,nCalHoliday
			FROM	VENDER
					,CAL
			WHERE	VENDER.VENDER_DIVISION		=	'SI'
			AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
			AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
			AND		CAL.CAL_DATE				=	daTmpPaymentScheDate			-- 年月日
			;
			-- 平日の場合、ループ終了
			EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;
		END LOOP;
		-- 支払予定日＜支払締め日の場合
		IF daTmpPaymentScheDate < daPayableDate THEN
			-- 「先送り」の処理へ
			nHolidayFlg := c_HolidayFlg1;			-- 先送り
		ELSE
			daPaymentScheDate := daTmpPaymentScheDate;
		END IF;
	END IF;
	--------------------------------------------------------------------------------
	-- 休日指定フラグ＝「先送り」場合
	--------------------------------------------------------------------------------
	IF nHolidayFlg = c_HolidayFlg1 THEN
		daTmpPaymentScheDate := daPaymentScheDate;
		LOOP
			daTmpPaymentScheDate := daTmpPaymentScheDate + 1;
			--------------------------------------------------------------------------------
			--★カレンダーマスタ再検索
			--------------------------------------------------------------------------------
			SELECT	CAL.CAL_DATE							-- 年月日
					,CAL_HOLIDAY							-- 休日
			INTO	 daCalDate
					,nCalHoliday
			FROM	VENDER
					,CAL
			WHERE	VENDER.VENDER_DIVISION		=	'SI'
			AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
			AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
			AND		CAL.CAL_DATE				=	daTmpPaymentScheDate			-- 年月日
			;
			-- 平日の場合、ループ終了
			EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;
		END LOOP;
		daPaymentScheDate := daTmpPaymentScheDate;
	END IF;
	RETURN TO_CHAR(daPaymentScheDate, 'YYYYMMDD');
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN NO_DATA_FOUND THEN
		-- データ無しの場合、最初に設定した支払予定日を返す
		RETURN TO_CHAR(daPaymentScheDate, 'YYYYMMDD');
	WHEN OTHERS THEN
		RAISE;
END FUN_GET_PAYMENT_SCHE_DATE;
/
