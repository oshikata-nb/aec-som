DECLARE
/*--------------------------------------------------------------------------------
  会計インターフェース用プロシージャ    (手形ファイル)
*/--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
-- 会計システムの定数
	c_NoteClsDRAFT		CHAR(1)				:= '2';		-- 手形区分（支払手形）
	c_NoteClsRECEIVABLE	CHAR(1)				:= '1';		-- 手形区分（受取手形）
	c_InoutClsDRAFT		CHAR(2)				:= '31';	-- 異動区分（支手振出)		
	c_InoutClsRECEIVABLE	CHAR(2)				:= '11';	-- 異動区分（受取受入）
	c_JournalClsDRAFT	CHAR(2)				:= '31';	-- 自動仕訳区分（支手決済）
	c_JournalClsRECEIVABLE	CHAR(2)				:= '11';	-- 自動仕訳区分（受手決済）
	c_JounalCls 		CHAR(2)				:= '03';	-- 自動仕訳番号		
	c_VenderCdVENDER	CHAR(2)				:= '01';	-- 取引先のとき頭に01をつける
	c_VenderCdBANK		CHAR(2)				:= '02';	-- 銀行のとき頭に02をつける
-- ap21の定数
	c_WeekHolidayFlg0	CAL.CAL_HOLIDAY%TYPE		:= 0;		-- 平日休日フラグ(平日)
	c_WeekHolidayFlg1	CAL.CAL_HOLIDAY%TYPE		:= 1;		-- 平日休日フラグ(休日)
	v_SlipNo		TRANS_NOTE.SLIP_NO%TYPE;			-- 伝票番号
	v_RowNo			TRANS_NOTE.ROW_NO%TYPE;				-- 伝票番号行番号
    v_DelSlipNo		TRANS_NOTE.SLIP_NO%TYPE;		-- 削除用伝票番号
    v_DelRowNo		TRANS_NOTE.ROW_NO%TYPE;			-- 削除用行番号
	v_rCount 		NUMBER;
	daSettlementDate	DATE;						-- 決済予定日
	daTmpSettlementDate	DATE;						-- 決済予定日(仮)
	daCalDate		CAL.CAL_DATE%TYPE;				-- 年月日
	nCalHoliday		CAL.CAL_HOLIDAY%TYPE;				-- 休日区分 0:平日 1:休日
    v_Flg			NUMBER;							-- 処理フラグ(0:承認された 1:承認取消された)
BEGIN
	IF (UPDATING) THEN	-- 更新時
		IF :OLD.APPROVAL_STATUS = 2 AND :NEW.APPROVAL_STATUS = 3 AND :NEW.CATEGORY_DIVISION IN (1, 11) THEN
        	-- 入金手形が承認された場合
			v_SlipNo := :NEW.CREDIT_NO;
			v_RowNo := :NEW.ROW_NO;
            v_Flg := 0;
		ELSIF :OLD.APPROVAL_STATUS = 3 AND :NEW.APPROVAL_STATUS = 1 AND :NEW.CATEGORY_DIVISION IN (1, 11) THEN
        	-- 入金手形が承認取消された場合
			v_DelSlipNo := :OLD.CREDIT_NO;
			v_DelRowNo := :OLD.ROW_NO;
            v_Flg := 1;
        ELSE
			RETURN;
		END IF;
	ELSE
		RETURN;
	END IF;
    IF v_Flg = 0 THEN
       	-- 入金手形が承認された場合
		SELECT CAL.CAL_DATE,CAL_HOLIDAY INTO daCalDate,nCalHoliday
		FROM VENDER,CAL
		WHERE VENDER.VENDER_DIVISION =	'TS'
		AND VENDER.VENDER_CD	= :NEW.VENDER_CD
		AND VENDER.CALENDAR_CD = CAL.CAL_CD
		AND CAL.CAL_DATE = :NEW.NOTE_DATE	-- 満期日
		;
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
				WHERE VENDER.VENDER_DIVISION =	'TS'
				AND VENDER.VENDER_CD	= :NEW.VENDER_CD
				AND VENDER.CALENDAR_CD = CAL.CAL_CD
				AND CAL.CAL_DATE = daTmpSettlementDate	-- 年月日
				;
				-- 平日の場合、ループ終了
				EXIT WHEN nCalHoliday = c_WeekHolidayFlg0;
			END LOOP;
			daSettlementDate := daTmpSettlementDate;
		END IF;
		SELECT COUNT(*) INTO v_rCount
		FROM TRANS_NOTE
		WHERE SLIP_NO = v_SlipNo
		AND ROW_NO = v_RowNo;
		IF v_rCount = 0 THEN
			INSERT INTO TRANS_NOTE VALUES(
				:NEW.CREDIT_NO,                            		-- 伝票番号
				:NEW.ROW_NO,                               		-- 行番号
				:NEW.CREDIT_DATE,                          		-- 支払日付
				substr(:NEW.NOTE_NO,1,LENGTH(:NEW.NOTE_NO)-3),		-- 手形番号
				substr(:NEW.NOTE_NO,LENGTH(:NEW.NOTE_NO)-2,3),		-- 手形番号枝番
				c_NoteClsRECEIVABLE,					-- 手形区分(1:受取手形　2:支払手形)
				DECODE(:NEW.NOTE_DIVISION,0,1,1,3,0),			-- 手形種別(1約束手形　3:為替手形)
				:NEW.CREDIT_SECTION_CD ,				-- 部門コード
				:NEW.DRAWAL_DATE,					-- 振出日
				:NEW.NOTE_DATE,						-- 満期日
				daSettlementDate,					-- 決済予定日
				:NEW.CREDIT_AMOUNT,					-- 額面金額
				daSettlementDate - :NEW.DRAWAL_DATE,			-- サイト（決済予定日－振出日）
				c_VenderCdVENDER||:NEW.VENDER_CD,			-- 取引先コード(請求先）
				NULL,							-- 銀行コード
				NULL,							-- 口座番号
				NULL,							-- 預金種別
				NULL,							-- 摘要名(使用しない)
				c_InoutClsRECEIVABLE,					-- 異動区分(11:受取受入)
				c_JournalClsRECEIVABLE,					-- 自動仕訳区分(11:受手決済)	
				NULL,							-- 自動仕訳番号(未設定)	
				NULL,							-- 裏書譲渡先(使用しない)	
				NULL,							-- 振出銀行コード(未設定)	
				NULL,							-- 振出人名称(未設定)	
				NULL,							-- 会計システム転送完了フラグ
				SYSDATE,
				:NEW.APPROVEDBY,
				SYSDATE,
				:NEW.APPROVEDBY	
				);
		ELSE
			UPDATE TRANS_NOTE SET
				PAYMENT_DATE = :NEW.CREDIT_DATE,
				TEGATA_NO = substr(:NEW.NOTE_NO,1,LENGTH(:NEW.NOTE_NO)-3),
				EDABAN = substr(:NEW.NOTE_NO,LENGTH(:NEW.NOTE_NO)-2,3),
				KUBUN = c_NoteClsRECEIVABLE,
				SHUBETU = DECODE(:NEW.NOTE_DIVISION,0,1,1,3,0),
				BNAIBU_NO = :NEW.CREDIT_SECTION_CD,
				FURI_YMD = :NEW.DRAWAL_DATE,
				MANKI_YMD = :NEW.NOTE_DATE,
				KESSAI_YMD = daSettlementDate,
				KINGAKU = :NEW.CREDIT_AMOUNT,
				SAITO = daSettlementDate - :NEW.DRAWAL_DATE,
				TORI_CD = c_VenderCdVENDER||:NEW.VENDER_CD,
				IDO_KUBUN1 = c_InoutClsRECEIVABLE,				-- 異動区分(11:受取受入 31:支手振出)
				J_SIWA_KBN = c_JournalClsRECEIVABLE,				-- 自動仕訳区分（11:受手決済）
				UPDATE_DATE = SYSDATE,
				UPDATOR_CD = :NEW.APPROVEDBY
			WHERE SLIP_NO = v_SlipNo
			AND ROW_NO = v_RowNo;
 		END IF;
	ELSE
       	-- 入金手形が承認取消された場合
        DELETE
        FROM TRANS_NOTE
        WHERE SLIP_NO = v_DelSlipNo
        AND ROW_NO = v_DelRowNo;
    END IF;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
    	IF v_Flg = 0 THEN
	       	-- 入金手形が承認された場合
			OUTPUT_ERROR_LOG('TRI_NOTE_ON_CREDIT','TRIGGER',SQLCODE,'伝票番号：' || v_SlipNo || SQLERRM);
        ELSE
	       	-- 入金手形が承認取消された場合
			OUTPUT_ERROR_LOG('TRI_NOTE_ON_CREDIT','TRIGGER',SQLCODE,'伝票番号：' || v_DelSlipNo || SQLERRM);
        END IF;
END;
