DECLARE
/*--------------------------------------------------------------------------------
  会計インターフェース用プロシージャ    (手形ファイル)
*/--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
-- 会計システムの定数
	c_NoteClsDRAFT		CHAR(1)				:= '2';			-- 手形区分（支払手形）
	c_NoteClsRECEIVABLE	CHAR(1)				:= '1';			-- 手形区分（受取手形）
	c_InoutClsDRAFT		CHAR(2)				:= '31';		-- 異動区分（支手振出)		
	c_InoutClsRECEIVABLE	CHAR(2)				:= '11';		-- 異動区分（受取受入）
	c_JournalClsDRAFT	CHAR(2)				:= '31';		-- 自動仕訳区分（支手決済）
	c_JournalClsRECEIVABLE	CHAR(2)				:= '11';		-- 自動仕訳区分（受手決済）
	c_JounalCls 		CHAR(2)				:= '03';		-- 自動仕訳番号		
	c_VenderCdVENDER	CHAR(2)				:= '01';		-- 取引先のとき頭に01をつける
	c_VenderCdBANK		CHAR(2)				:= '02';		-- 銀行のとき頭に02をつける
-- ap21の定数
	c_WeekHolidayFlg0	CAL.CAL_HOLIDAY%TYPE		:= 0;			-- 平日休日フラグ(平日)
	c_WeekHolidayFlg1	CAL.CAL_HOLIDAY%TYPE		:= 1;			-- 平日休日フラグ(休日)
-- ap21の変数
	v_SlipNo		TRANS_NOTE.SLIP_NO%TYPE;				-- 伝票番号
	v_RowNo			TRANS_NOTE.ROW_NO%TYPE;					-- 伝票番号行番号
    v_DelSlipNo		TRANS_NOTE.SLIP_NO%TYPE;		-- 削除用伝票番号
    v_DelRowNo		TRANS_NOTE.ROW_NO%TYPE;			-- 削除用行番号
	v_BankCD		COMPANY.BANK_MASTER_CD%TYPE;				-- 支払銀行コード
	v_AccountNO		COMPANY.ACCOUNT_NO%TYPE;				-- 支払銀行口座
	v_AccountDIV		COMPANY.ACCOUNT_DIVISION%TYPE;				-- 支払銀行口座区分
	v_rCount 		NUMBER;
	daSettlementDate	DATE;							-- 決済予定日
	daTmpSettlementDate	DATE;							-- 決済予定日(仮)
	daCalDate		CAL.CAL_DATE%TYPE;					-- 年月日
	nCalHoliday		CAL.CAL_HOLIDAY%TYPE;					-- 休日区分 0:平日 1:休日
    v_Flg			NUMBER;							-- 処理フラグ(0:承認された 1:承認取消された)
BEGIN
	IF (UPDATING) THEN	-- 更新時
		IF :OLD.APPROVAL_STATUS = 2 AND :NEW.APPROVAL_STATUS = 3 AND :NEW.CATEGORY_DIVISION = 1 THEN
        	-- 支払手形が承認された場合
			v_SlipNo := :NEW.SLIP_NO;
			v_RowNo := :NEW.ROW_NO;
            v_Flg := 0;
		ELSIF :OLD.APPROVAL_STATUS = 3 AND :NEW.APPROVAL_STATUS = 1 AND :NEW.CATEGORY_DIVISION = 1 THEN
        	-- 支払手形が承認取消された場合
			v_DelSlipNo := :OLD.SLIP_NO;
			v_DelRowNo := :OLD.ROW_NO;
            v_Flg := 1;
		ELSE
			RETURN;
		END IF;
	ELSE
		RETURN;
	END IF;
    IF v_Flg = 0 THEN
       	-- 支払手形が承認された場合
		SELECT BANK_MASTER_CD,ACCOUNT_NO,ACCOUNT_DIVISION INTO v_BankCD,v_AccountNO,v_AccountDIV
		FROM COMPANY
		WHERE COMPANY_CD ='000001';	-- 自社マスタの支払銀行・口座・口座区分取得
		SELECT CAL.CAL_DATE,CAL_HOLIDAY INTO daCalDate,nCalHoliday
		FROM VENDER,CAL
		WHERE VENDER.VENDER_DIVISION =	'SI'
		AND VENDER.VENDER_CD	= :NEW.SUPPLIER_CD
		AND VENDER.CALENDAR_CD = CAL.CAL_CD
		AND CAL.CAL_DATE = :NEW.NOTE_DATE;	-- 満期日
		-- 平日の場合
		IF nCalHoliday = c_WeekHolidayFlg0 THEN
			daSettlementDate := :NEW.NOTE_DATE;
		ELSE
			daTmpSettlementDate := :NEW.NOTE_DATE;
			LOOP
				daTmpSettlementDate := daTmpSettlementDate + 1;
				--------------------------------------------------------------------------------
				--★カレンダーマスタ再検索
				--------------------------------------------------------------------------------
				SELECT CAL.CAL_DATE,CAL_HOLIDAY INTO daCalDate,nCalHoliday
				FROM VENDER,CAL
				WHERE VENDER.VENDER_DIVISION =	'SI'
				AND VENDER.VENDER_CD	= :NEW.SUPPLIER_CD
				AND VENDER.CALENDAR_CD = CAL.CAL_CD
				AND CAL.CAL_DATE = daTmpSettlementDate;	-- 年月日
				-- 平日の場合、ループ終了
				EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;
			END LOOP;
			daSettlementDate := daTmpSettlementDate;
		END IF;
		SELECT COUNT(*) INTO v_rCount
		FROM TRANS_NOTE
		WHERE SLIP_NO = v_SlipNo
		AND ROW_NO = v_RowNo;
		IF v_rCount IS NULL OR v_rCount= 0 THEN
			INSERT INTO TRANS_NOTE VALUES(
				:NEW.SLIP_NO,
				:NEW.ROW_NO,
				:NEW.PAYMENT_DATE,
				:NEW.NOTE_NO,
				NULL,
				c_NoteClsDRAFT,
				DECODE(:NEW.NOTE_DIVISION,1,1,2,3,0),
				:NEW.DEBIT_SECTION_CD,          -- 部門
				:NEW.DRAWAL_DATE,		-- 振出日
				:NEW.NOTE_DATE,			-- 満期日
				daSettlementDate,		-- 決済予定日
				:NEW.PAYMENT_AMOUNT,
				(SELECT
					CASE
--						WHEN :NEW.PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT3 THEN VENDER2.NOTE_SIGHT3
--						WHEN :NEW.PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT4 THEN VENDER2.NOTE_SIGHT4
--						ELSE VENDER2.NOTE_SIGHT5
						WHEN :NEW.PAYMENT_AMOUNT > VENDER2.BOUND_AMOUNT4 THEN VENDER2.NOTE_SIGHT5
						WHEN :NEW.PAYMENT_AMOUNT > VENDER2.BOUND_AMOUNT3 THEN VENDER2.NOTE_SIGHT4
						ELSE VENDER2.NOTE_SIGHT3
					END AS NOTE_SIGHT
					FROM (
					SELECT	CREDIT_DIVISION3			-- 入金区分３
						,	BOUND_AMOUNT3				-- 境界額３
						,	NOTE_SIGHT3					-- 手形サイト３
						,	CREDIT_DIVISION4			-- 入金区分４
						,	BOUND_AMOUNT4				-- 境界額４
						,	NOTE_SIGHT4					-- 手形サイト４
						,	CREDIT_DIVISION5			-- 入金区分５
						,	NOTE_SIGHT5					-- 手形サイト５
						,	VENDER_CD
						,	VENDER_DIVISION
					FROM	VENDER
					) VENDER2
					WHERE	VENDER2.VENDER_DIVISION		=	'SI'
					AND		VENDER2.VENDER_CD			=	:NEW.SUPPLIER_CD
				),
				c_VenderCdVENDER||:NEW.SUPPLIER_CD,
				v_BankCD,
				v_AccountNO,
				v_AccountDIV,
				NULL,	-- 摘要名(使用しない)			c_InoutClsDRAFT AS IO_CLASS,						-- 異動区分（31:支手振出)
				c_InoutClsDRAFT,					-- 異動区分(11:受取受入 31:支手振出)		
				c_JournalClsDRAFT,					-- 自動仕訳区分（31:支手決済）
				c_JounalCls,					-- 自動仕訳番号	
				NULL,						-- 裏書譲渡先(使用しない)		
				NULL,					-- 振出銀行コード(未設定)		
				NULL,					-- 振出人名称(未設定)		
				NULL,			-- 会家計システム転送完了フラグ
				SYSDATE,		
				:NEW.APPROVEDBY,		
				SYSDATE,		
				:NEW.APPROVEDBY		
			);
		ELSE
			UPDATE TRANS_NOTE SET 
				PAYMENT_DATE = :NEW.PAYMENT_DATE,
				TEGATA_NO = :NEW.NOTE_NO,
				KUBUN = c_NoteClsDRAFT,
				SHUBETU = DECODE(:NEW.NOTE_DIVISION,1,1,2,3,0),
				BNAIBU_NO = :NEW.DEBIT_SECTION_CD,
				FURI_YMD = :NEW.DRAWAL_DATE,
				MANKI_YMD = :NEW.NOTE_DATE,
				KESSAI_YMD = daSettlementDate,
				KINGAKU = :NEW.PAYMENT_AMOUNT,
				SAITO = (SELECT
					CASE
--						WHEN :NEW.PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT3 THEN VENDER2.NOTE_SIGHT3
--						WHEN :NEW.PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT4 THEN VENDER2.NOTE_SIGHT4
--						ELSE VENDER2.NOTE_SIGHT5
						WHEN :NEW.PAYMENT_AMOUNT > VENDER2.BOUND_AMOUNT4 THEN VENDER2.NOTE_SIGHT5
						WHEN :NEW.PAYMENT_AMOUNT > VENDER2.BOUND_AMOUNT3 THEN VENDER2.NOTE_SIGHT4
						ELSE VENDER2.NOTE_SIGHT3
					END AS NOTE_SIGHT
					FROM (
					SELECT	CREDIT_DIVISION3			-- 入金区分３
						,	BOUND_AMOUNT3				-- 境界額３
						,	NOTE_SIGHT3					-- 手形サイト３
						,	CREDIT_DIVISION4			-- 入金区分４
						,	BOUND_AMOUNT4				-- 境界額４
						,	NOTE_SIGHT4					-- 手形サイト４
						,	CREDIT_DIVISION5			-- 入金区分５
						,	NOTE_SIGHT5					-- 手形サイト５
						,	VENDER_CD
						,	VENDER_DIVISION
					FROM	VENDER
					) VENDER2
					WHERE	VENDER2.VENDER_DIVISION		=	'SI'
					AND		VENDER2.VENDER_CD			=	:NEW.SUPPLIER_CD
				),
				TORI_CD = c_VenderCdVENDER||:NEW.SUPPLIER_CD,
				BANK_CD = v_BankCD,
				KOZA_NO = v_AccountNO,
				Y_SHUBETU = v_AccountDIV,
				IDO_KUBUN1 = c_InoutClsDRAFT,					-- 異動区分(11:受取受入 31:支手振出)		
				J_SIWA_KBN = c_JournalClsDRAFT,					-- 自動仕訳区分（31:支手決済）
				J_SIWA_NO = c_JounalCls,					-- 自動仕訳番号
				UPDATE_DATE = SYSDATE,
				UPDATOR_CD = :NEW.APPROVEDBY
			WHERE SLIP_NO = v_SlipNo
			AND ROW_NO = v_RowNo;
		END IF;
	ELSE
       	-- 支払手形が承認取消された場合
        DELETE
        FROM TRANS_NOTE
        WHERE SLIP_NO = v_DelSlipNo
        AND ROW_NO = v_DelRowNo;
    END IF;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
    	IF v_Flg = 0 THEN
	       	-- 支払手形が承認された場合
			OUTPUT_ERROR_LOG('TRI_NOTE_ON_PAYMENT','TRIGGER',SQLCODE,'伝票番号：' || v_SlipNo ||' '|| SQLERRM);
        ELSE
	       	-- 支払手形が承認取消された場合
			OUTPUT_ERROR_LOG('TRI_NOTE_ON_PAYMENT','TRIGGER',SQLCODE,'伝票番号：' || v_DelSlipNo ||' '|| SQLERRM);
        END IF;
END;
