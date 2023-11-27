CREATE OR REPLACE PROCEDURE AP21.PRO_TRANS_NOTE
	(
		P_STR_ORGANIZATION_CD	IN	NVARCHAR2,
		P_STR_TARGET_YEARS		IN	NVARCHAR2,
		P_STR_TANTO_CD			IN	NVARCHAR2,
		O_ERROR_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
		O_ERROR_RETURN_MSG		OUT	NVARCHAR2	-- エラーメッセージを返す
	)
IS
/*--------------------------------------------------------------------------------
  会計インターフェース用プロシージャ    (手形ファイル)
*/--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
-- 会計システムの定数
	c_NoteClsDRAFT		CHAR(1)					:= '2';			-- 手形区分（支払手形）
	c_NoteClsRECEIVABLE	CHAR(1)					:= '1';			-- 手形区分（受取手形）
	c_InoutClsDRAFT		CHAR(2)					:= '31';		-- 異動区分（支手振出)		
	c_InoutClsRECEIVABLE	CHAR(2)				:= '11';		-- 異動区分（受取受入）
	c_JournalClsDRAFT	CHAR(2)					:= '31';		-- 自動仕訳区分（支手決済）
	c_JournalClsRECEIVABLE	CHAR(2)				:= '11';		-- 自動仕訳区分（受手決済）
	c_JounalCls 		CHAR(2)					:= '03';		-- 自動仕訳番号		
	c_VenderCdVENDER	CHAR(2)					:= '01';		-- 取引先のとき頭に01をつける
	c_VenderCdBANK		CHAR(2)					:= '02';		-- 銀行のとき頭に02をつける
-- AP-21 	分類マスタ(classification)で定義された番号
	c_DataTypeSALES		CHAR(1)					:= '1';			-- 分類マスタ番号（売上）
	c_DataTypeCREDIT	CHAR(1)					:= '2';			-- 分類マスタ番号（入金）
	c_DataTypeSTOCK		CHAR(1)					:= '3';			-- 分類マスタ番号（仕入）
	c_DataTypePAYMENT	CHAR(1)					:= '4';			-- 分類マスタ番号（支払）
	c_DataTypeOFFSET	CHAR(1)					:= '5';			-- 分類マスタ番号（相殺）
	c_CreditDivON		CHAR(1)					:= '1';			-- 分類マスタ買掛対象区分（1：対象）
	c_PayableUpdDivCOM	CHAR(1)					:= '1';			-- 買掛更新処理済み
	c_CatDivCRNOTE		CHAR(1)					:= '1';			-- 分類マスタ集計区分（入金：手形）
	c_CatDivPYNOTE		CHAR(1)					:= '1';			-- 分類マスタ集計区分（支払：手形）
	c_Approval			CHAR(1)					:= '3';			-- 承認済み
BEGIN
		INSERT INTO TRANS_NOTE 
		SELECT
			SLIP_NO				 AS SLIP_NO,					-- 伝票番号		
			ROW_NO				 AS ROW_NO,						-- 行番号		
			SLIP_DATE			 AS PAYMENT_DATE,				-- 支払日付		
			NOTE_NO				 AS TEGATA_NO,					-- 手形番号 ＋ 枝番		
			NOTE_BRANCH			 AS EDABAN,
			NOTE_CLASS			 AS KUBUN,						-- 手形区分(1:受取手形　2:支払手形)		
			NOTE_DIVISION		 AS SHUBETU,					-- 手形種別(1約束手形　3:為替手形)		
			ORGANIZATION_CD		 AS BNAIBU_NO,					-- 部署コード		
			DRAWAL_DATE			 AS FURI_YMD,					-- 振出日		
			NOTE_DATE			 AS MANKI_YMD,					-- 満期日		
			SETTLEMENT_DATE		 AS KESSAI_YMD,					-- 決済予定日		
			NOTE_AMOUNT			 AS KINGAKU,					-- 額面金額		
			NOTE_SIGHT			 AS SAITO,						-- サイト		
			c_VenderCdVENDER||VENDER_CD			 AS TORI_CD,					-- 取引先コード		
			BANK_CD				 AS BANK_CD,					 -- 銀行コード		
			ACCOUNT_NO			 AS KOZA_NO,					-- 口座番号		
			ACCOUNT_DIVISION	 AS Y_SHUBETU,					-- 預金種別		
			NULL				 AS TEKIYO,						-- 摘要名(使用しない)		
			IO_CLASS			 AS IDO_KUBUN1,					-- 異動区分(11:受取受入 31:支手振出)		
			JOURNAL_CLASS		 AS J_SIWA_KBN,					-- 自動仕訳区分(11:受手決済 31:支手決済)		
			NULL				 AS J_SIWA_NO,					-- 自動仕訳番号(未設定)		
			NULL				 AS EITAN,						-- 裏書譲渡先(使用しない)		
			NULL				 AS FURI_BANK,					-- 振出銀行コード(未設定)		
			NULL				 AS FURI_NIN,					-- 振出人名称(未設定)		
			NULL				 AS TRANSMISSION_DATE,			-- 会家計システム転送完了フラグ
			SYSDATE,				-- テスト用の記述。標準機能に合わせる。
			P_STR_TANTO_CD,			-- テスト用の記述。標準機能に合わせる。
			SYSDATE,				-- テスト用の記述。標準機能に合わせる。
			P_STR_TANTO_CD			-- テスト用の記述。標準機能に合わせる。
		FROM
		(
		--  /* ２：入金伝票 */
		SELECT	
			CREDIT_NO AS SLIP_NO,                                 -- 伝票番号
			ROW_NO AS ROW_NO,                                     -- 行番号
			CREDIT_DATE AS SLIP_DATE,                             -- 支払日付
			substr(NOTE_NO,1,LENGTH(NOTE_NO)-3) AS NOTE_NO,			-- 手形番号
			substr(NOTE_NO,LENGTH(NOTE_NO)-2,3) AS NOTE_BRANCH,		-- 手形番号枝番
			c_NoteClsRECEIVABLE AS NOTE_CLASS,						-- 手形区分(1:受取手形　2:支払手形)
			NOTE_DIVISION AS NOTE_DIVISION,                       -- 手形種別(1約束手形　3:為替手形)
			CREDIT_SECTION_CD AS ORGANIZATION_CD,                   -- 部署コード
			to_char(DRAWAL_DATE,'YYYYMMDD') AS DRAWAL_DATE,       -- 振出日
			to_char(NOTE_DATE,'YYYYMMDD') AS NOTE_DATE,           -- 満期日
			to_char(NOTE_DATE,'YYYYMMDD') AS SETTLEMENT_DATE,     -- 決済予定日
			CREDIT_AMOUNT AS NOTE_AMOUNT,                         -- 額面金額
			NOTE_DATE - DRAWAL_DATE AS NOTE_SIGHT,                -- サイト（決済予定日－振出日）
			VENDER_CD AS VENDER_CD,                               -- 取引先コード(請求先）
			BANK_CD,                                              -- 銀行コード
			ACCOUNT_NO,                                           -- 口座番号
			ACCOUNT_DIVISION,                                     -- 預金種別
			c_InoutClsRECEIVABLE AS IO_CLASS,                     -- 異動区分(11:受取受入)		
			c_JournalClsRECEIVABLE AS JOURNAL_CLASS  --,              -- 自動仕訳区分(11:受手決済)		
--			NULL                                                 -- 自動仕訳番号(未設定)		
		FROM	CREDIT
		WHERE	APPROVAL_STATUS = c_Approval                      -- 3:承認済み
		--	AND	TRANSMISSION_DATE IS NULL
			AND	CATEGORY_DIVISION = c_CatDivCRNOTE                -- 1:手形
			AND	DATA_TYPE = c_DataTypeCREDIT                      -- 2:入金
UNION ALL
--  /* ４：支払伝票 */
		SELECT	
			SLIP_NO AS SLIP_NO,
			ROW_NO AS ROW_NO,
			PAYMENT_DATE AS SLIP_DATE,
			NOTE_NO AS NOTE_NO,
			NULL AS NOTE_BRANCH,
			c_NoteClsDRAFT AS NOTE_CLASS,
			NOTE_DIVISION AS NOTE_DIVISION,
			ORGANIZATION_CD AS ORGANIZATION_CD,
			to_char(DRAWAL_DATE,'YYYYMMDD') AS DRAWAL_DATE,
			to_char(NOTE_DATE,'YYYYMMDD') AS NOTE_DATE,
			to_char(PAYMENT_DATE,'YYYYMMDD') AS SETTLEMENT_DATE,
			PAYMENT_AMOUNT AS NOTE_AMOUNT,
			(SELECT
				CASE
					WHEN PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT3 THEN VENDER2.NOTE_SIGHT3
					WHEN PAYMENT_AMOUNT < VENDER2.BOUND_AMOUNT4 THEN VENDER2.NOTE_SIGHT4
					ELSE VENDER2.NOTE_SIGHT5
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
				AND		VENDER2.VENDER_CD			=	PAYMENT.SUPPLIER_CD
			) AS NOTE_SIGHT,
			PAYMENT.SUPPLIER_CD AS VENDER_CD,
			BANK_CD AS BANK_CD,
			ACCOUNT_NO AS ACCONT_NO,
			ACCOUNT_DIVISION AS ACCOUNT_DIVISION,
			c_InoutClsDRAFT AS IO_CLASS,						-- 異動区分（31:支手振出)
			c_JournalClsDRAFT AS JOURNAL_CLASS					-- 自動仕訳区分（31:支手決済）
		FROM	PAYMENT
		WHERE	APPROVAL_STATUS = c_Approval					-- 3:承認済み
			AND	TRANSMISSION_DATE IS NULL
			AND	CATEGORY_DIVISION = c_CatDivPYNOTE              -- 1:手形
			AND	DATA_TYPE = c_DataTypePAYMENT                   -- 4:支払
		)	
	  ;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		ROLLBACK;

		-- SQLエラーコード、エラーメッセージを取得
    	O_ERROR_RETURN_CD := SQLCODE;
	O_ERROR_RETURN_MSG := SUBSTR(SQLERRM, 1, 1024);

	OUTPUT_ERROR_LOG('PRO_TRANS_NOTE', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_TRANS_NOTE;
/
