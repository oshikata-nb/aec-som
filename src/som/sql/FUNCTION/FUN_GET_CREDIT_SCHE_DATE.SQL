CREATE OR REPLACE FUNCTION AP21.FUN_GET_CREDIT_SCHE_DATE
	(
		 P_STR_VENDER_CD			IN	NVARCHAR2
		,P_STR_CREDIT_DATE			IN	NVARCHAR2
		,P_NUM_CLAIM_AMOUNT			IN	NUMBER
	)
RETURN	NVARCHAR2
IS
/*----------------------------------------------------------------------------------------------------------------
	種別		:	FUNCTION
	名称		:	FUN_GET_CREDIT_SCHE_DATE
	処理内容	:	入金予定日取得処理
	引数		:	P_STR_VENDER_CD			請求先コード					NVARCHAR2型
	引数		:	P_STR_CREDIT_DATE		請求締め日(YYYYMMDD)			NVARCHAR2型
	引数		:	P_NUM_CLAIM_AMOUNT		今回請求額(差引繰越額含まず)	NUMBER型
	戻値		:	nvCreditScheDate		入金予定日(YYYYMMDD)			NVARCHAR2型
	VERNO.		:	1.00
(history)
	date		ver		name				comments
	----------	------	------------------	----------------------------------------------------------------------
	2008.09.01	1.00	TOSCO       		creation
*/----------------------------------------------------------------------------------------------------------------

	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_CreditMonthDiv0	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 1;		-- 入金月区分(当月)
	c_CreditMonthDiv1	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 2;		-- 入金月区分(翌月)
	c_CreditMonthDiv2	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 3;		-- 入金月区分(翌々月)
	c_CreditMonthDiv3	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 4;		-- 入金月区分(３ヶ月)
	c_CreditMonthDiv4	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 5;		-- 入金月区分(４ヶ月)
	c_CreditMonthDiv5	VENDER.CREDIT_MONTH_DIVISION%TYPE		:= 6;		-- 入金月区分(５ヶ月)
	c_HolidayFlg0		VENDER.HOLIDAY_FLG%TYPE					:= 1;		-- 休日指定フラグ(前倒し)
	c_HolidayFlg1		VENDER.HOLIDAY_FLG%TYPE					:= 2;		-- 休日指定フラグ(先送り)
	c_HolidayFlg2		VENDER.HOLIDAY_FLG%TYPE					:= 3;		-- 休日指定フラグ(休日を考慮しない)
	c_WeekHolidayFlg0	CAL.CAL_HOLIDAY%TYPE					:= 0;		-- 平日休日フラグ(平日)
	c_WeekHolidayFlg1	CAL.CAL_HOLIDAY%TYPE					:= 1;		-- 平日休日フラグ(休日)

	nBoundAmount3		VENDER.BOUND_AMOUNT3%TYPE;							-- 境界額３
	nBoundAmount4		VENDER.BOUND_AMOUNT4%TYPE;							-- 境界額４
	nCreditMonthDiv		VENDER.CREDIT_MONTH_DIVISION%TYPE;					-- 入金月区分
	nCreditMonthDiv1	VENDER.CREDIT_MONTH_DIVISION1%TYPE;					-- 入金月区分1
	nCreditMonthDiv2	VENDER.CREDIT_MONTH_DIVISION2%TYPE;					-- 入金月区分2
	nCreditMonthDiv3	VENDER.CREDIT_MONTH_DIVISION3%TYPE;					-- 入金月区分3
	nCreditScheDay		VENDER.CREDIT_SCHEDULED_DATE%TYPE;					-- 入金予定日(DD)(取引先マスタ)
	nCreditScheDay1		VENDER.CREDIT_SCHEDULED_DATE1%TYPE;					-- 入金予定日1(DD)(取引先マスタ)
	nCreditScheDay2		VENDER.CREDIT_SCHEDULED_DATE2%TYPE;					-- 入金予定日2(DD)(取引先マスタ)
	nCreditScheDay3		VENDER.CREDIT_SCHEDULED_DATE3%TYPE;					-- 入金予定日3(DD)(取引先マスタ)
	vCreditScheDay		VARCHAR2(2);										-- 入金予定日(DD)(文字列)
	nHolidayFlg			VENDER.HOLIDAY_FLG%TYPE;							-- 休日指定フラグ
	daCreditDate		CLAIM_HEADER.CREDIT_DATE%TYPE;						-- 請求締め日
	nvCreditDateYM		NVARCHAR2(6);										-- 請求締め年月(YYYYMM)
	daCreditScheDate	DATE;												-- 入金予定日
	daTmpCreditScheDate	DATE;												-- 入金予定日(仮)
	daCalDate			CAL.CAL_DATE%TYPE;									-- 年月日
	nCalHoliday			CAL.CAL_HOLIDAY%TYPE;								-- 休日区分 0:平日 1:休日

BEGIN

	-- 文字型をDate型へキャスト
	daCreditDate := TO_DATE(P_STR_CREDIT_DATE, 'YYYY/MM/DD');
	-- 請求締め年月(YYYYMM)
	nvCreditDateYM := SUBSTR(P_STR_CREDIT_DATE, 1, 6);

	--------------------------------------------------------------------------------
	--★取引先マスタ検索
	--------------------------------------------------------------------------------
	SELECT	 CREDIT_MONTH_DIVISION1			-- 入金月区分１
			,CREDIT_MONTH_DIVISION2			-- 入金月区分２
			,CREDIT_MONTH_DIVISION3			-- 入金月区分３
			,CREDIT_SCHEDULED_DATE1			-- 入金予定日１
			,CREDIT_SCHEDULED_DATE2			-- 入金予定日２
			,CREDIT_SCHEDULED_DATE3			-- 入金予定日３
			,BOUND_AMOUNT3					-- 境界額３
			,BOUND_AMOUNT4					-- 境界額４
			,HOLIDAY_FLG					-- 休日指定フラグ
	INTO	 nCreditMonthDiv1
			,nCreditMonthDiv2
			,nCreditMonthDiv3
			,nCreditScheDay1
			,nCreditScheDay2
			,nCreditScheDay3
			,nBoundAmount3
			,nBoundAmount4
			,nHolidayFlg
	FROM	VENDER
	WHERE	VENDER_DIVISION		=	'TS'
	AND		VENDER_CD			=	P_STR_VENDER_CD
	;

	--------------------------------------------------------------------------------
	--金額による入金予定日、入金月区分の選定
	--------------------------------------------------------------------------------
	IF  P_NUM_CLAIM_AMOUNT < nBoundAmount3 THEN
		nCreditMonthDiv := nCreditMonthDiv1;
		nCreditScheDay := nCreditScheDay1;
	ELSIF  P_NUM_CLAIM_AMOUNT < nBoundAmount4 THEN
		nCreditMonthDiv := nCreditMonthDiv2;
		nCreditScheDay := nCreditScheDay2;
	ELSIF nBoundAmount4 <= P_NUM_CLAIM_AMOUNT THEN
		nCreditMonthDiv := nCreditMonthDiv3;
		nCreditScheDay := nCreditScheDay3;
	END IF;

	--------------------------------------------------------------------------------
	--入金予定日設定
	--------------------------------------------------------------------------------
	-- 月末指定(99)又は未登録(0)の場合
	IF nCreditScheDay = 99 OR nCreditScheDay = 0 THEN
		-- 入金予定日＝請求締め日の月末日付
		daCreditScheDate := LAST_DAY(daCreditDate);
	ELSE
		-- 入金予定日(DD2桁)
		vCreditScheDay := TO_CHAR(nCreditScheDay);
		IF LENGTH(vCreditScheDay) = 1 THEN
			vCreditScheDay := '0' || vCreditScheDay;
		END IF;

		-- 請求締め日の月末日＜入金予定日(DD2桁)
		IF TO_CHAR(LAST_DAY(daCreditDate), 'DD') < vCreditScheDay THEN
			-- 入金予定日＝請求締め日の月末日付
			daCreditScheDate := LAST_DAY(daCreditDate);
		ELSE
			-- 入金予定日＝請求締め年月＋入金予定日(DD2桁)
			daCreditScheDate := TO_DATE(nvCreditDateYM || vCreditScheDay, 'YYYY/MM/DD');
		END IF;
	END IF;

	-- 入金月区分により入金予定日設定
	IF nCreditMonthDiv = c_CreditMonthDiv0 THEN
		daCreditScheDate := daCreditScheDate;					-- 当月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv1 THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 1);	-- 翌月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv2 THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 2);	-- 翌々月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv3 THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 3);	-- ３ヶ月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv4 THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 4);	-- ４ヶ月
	ELSIF nCreditMonthDiv = c_CreditMonthDiv5 THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 5);	-- ５ヶ月
	END IF;

	-- 入金予定日＜請求締め日の場合
	IF daCreditScheDate < daCreditDate THEN
		daCreditScheDate := ADD_MONTHS(daCreditScheDate, 1);	-- ＋１ヶ月
	END IF;

	-- 休日指定フラグ＝「休日を考慮しない」場合
	IF nHolidayFlg = c_HolidayFlg2 THEN
		RETURN TO_CHAR(daCreditScheDate, 'YYYYMMDD');
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
	WHERE	VENDER.VENDER_DIVISION		=	'TS'
	AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
	AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
	AND		CAL.CAL_DATE				=	daCreditScheDate			-- 年月日
	;

	-- 平日の場合
	IF nCalHoliday = c_WeekHolidayFlg0 THEN
		RETURN TO_CHAR(daCreditScheDate, 'YYYYMMDD');
	END IF;

	--------------------------------------------------------------------------------
	-- 以降、入金予定日＝休日の場合
	--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	-- 休日指定フラグ＝「前倒し」場合
	--------------------------------------------------------------------------------
	IF nHolidayFlg = c_HolidayFlg0 THEN
		daTmpCreditScheDate := daCreditScheDate;

		LOOP
			daTmpCreditScheDate := daTmpCreditScheDate - 1;
			
			--------------------------------------------------------------------------------
			--★カレンダーマスタ再検索
			--------------------------------------------------------------------------------
			SELECT	CAL.CAL_DATE							-- 年月日
					,CAL_HOLIDAY							-- 休日
			INTO	 daCalDate
					,nCalHoliday
			FROM	VENDER
					,CAL
			WHERE	VENDER.VENDER_DIVISION		=	'TS'
			AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
			AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
			AND		CAL.CAL_DATE				=	daTmpCreditScheDate			-- 年月日
			;

			-- 平日の場合、ループ終了
			EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;

		END LOOP;

		-- 入金予定日＜請求締め日の場合
		IF daTmpCreditScheDate < daCreditDate THEN
			-- 「先送り」の処理へ
			nHolidayFlg := c_HolidayFlg1;			-- 先送り
		ELSE
			daCreditScheDate := daTmpCreditScheDate;
		END IF;

	END IF;

	--------------------------------------------------------------------------------
	-- 休日指定フラグ＝「先送り」場合
	--------------------------------------------------------------------------------
	IF nHolidayFlg = c_HolidayFlg1 THEN
		daTmpCreditScheDate := daCreditScheDate;

		LOOP
			daTmpCreditScheDate := daTmpCreditScheDate + 1;

			--------------------------------------------------------------------------------
			--★カレンダーマスタ再検索
			--------------------------------------------------------------------------------
			SELECT	CAL.CAL_DATE							-- 年月日
					,CAL_HOLIDAY							-- 休日
			INTO	 daCalDate
					,nCalHoliday
			FROM	VENDER
					,CAL
			WHERE	VENDER.VENDER_DIVISION		=	'TS'
			AND		VENDER.VENDER_CD			=	P_STR_VENDER_CD
			AND		VENDER.CALENDAR_CD			=	CAL.CAL_CD
			AND		CAL.CAL_DATE				=	daTmpCreditScheDate			-- 年月日
			;

			-- 平日の場合、ループ終了
			EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;

		END LOOP;
		daCreditScheDate := daTmpCreditScheDate;

	END IF;

	RETURN TO_CHAR(daCreditScheDate, 'YYYYMMDD');

EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN NO_DATA_FOUND THEN
		-- データ無しの場合、最初に設定した入金予定日を返す
		RETURN TO_CHAR(daCreditScheDate, 'YYYYMMDD');
	WHEN OTHERS THEN
		RAISE;

END FUN_GET_CREDIT_SCHE_DATE;
/
