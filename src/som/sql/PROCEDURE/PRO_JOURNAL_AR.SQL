CREATE OR REPLACE PROCEDURE AP21.PRO_JOURNAL_AR
	(
		P_STR_ORGANIZATION_CD	IN	NVARCHAR2,
		P_STR_TARGET_YEARS		IN	NVARCHAR2,
		P_STR_TANTO_CD			IN	NVARCHAR2,
		O_ERROR_RETURN_CD		OUT	NVARCHAR2,	-- エラーコードを返す
		O_ERROR_RETURN_MSG		OUT	NVARCHAR2	-- エラーメッセージを返す
	)
IS
/*--------------------------------------------------------------------------------
  会計インターフェース用プロシージャ  (債権メニュー)
  画面からの引数として、部署コード・対象年月による絞込みあり。
  引数        :   P_STR_ORGANIZATION_CD		部署コード			NVARCHAR2
  引数        :   P_STR_TARGET_YEARS		対象年月(YYYYMM)	NVARCHAR2(6)
  引数        :   P_STR_TANTO_CD			担当者コード		NVARCHAR2(10)
  
*/--------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
-- 会計システムの定数
	c_SlipDivisionAP21	CHAR(2)					:= '06';		-- AP-21伝票区分
	c_SlipNoInitSALES	CHAR(1)					:= '1';			-- 伝票番号頭文字（売上）
	c_SlipNoInitSTOCK	CHAR(1)					:= '2';			-- 伝票番号頭文字（仕入）
	c_SlipNoInitOFFSET	CHAR(1)					:= '3';			-- 伝票番号頭文字（相殺）
	c_SlipNoInitPAYMENT	CHAR(1)					:= '4';			-- 伝票番号頭文字（支払）
	c_SlipNoInitCREDIT	CHAR(1)					:= '5';			-- 伝票番号頭文字（入金）
	c_TaxDivisionIN 	CHAR(1)					:= '0';			-- （内税）
	c_TaxDivisionOUT	CHAR(1)					:= '1';			-- （外税）
	c_TaxDivisionOTR	CHAR(1)					:= '2';			-- （別記）
	c_VenderCdVENDER	CHAR(2)					:= '01';		-- 取引先のとき頭に01をつける
	c_VenderCdBANK		CHAR(2)					:= '02';		-- 銀行のとき頭に02をつける
-- AP-21 	分類マスタ(classification)で定義された番号
	c_DataTypeSALES		CHAR(1)					:= '1';			-- 分類マスタ番号（売上）
	c_DataTypeCREDIT	CHAR(1)					:= '2';			-- 分類マスタ番号（入金）
	c_DataTypeSTOCK		CHAR(1)					:= '3';			-- 分類マスタ番号（仕入）
	c_DataTypePAYMENT	CHAR(1)					:= '4';			-- 分類マスタ番号（支払）
	c_DataTypeOFFSET	CHAR(1)					:= '5';			-- 分類マスタ番号（相殺）
--
	c_CatDivSLSALE		CHAR(1)					:= '1';			-- 分類マスタ集計区分（売上：売上）
	c_CatDivSLRETN		CHAR(1)					:= '2';			-- 分類マスタ集計区分（売上：返品）
	c_CatDivSLDISC		CHAR(1)					:= '3';			-- 分類マスタ集計区分（売上：値引）
	c_CatDivSLTRAN		CHAR(1)					:= '4';			-- 分類マスタ集計区分（売上：運賃）
	c_CatDivCRNOTE		CHAR(1)					:= '1';			-- 分類マスタ集計区分（入金：手形）
	c_CatDivCROFST		CHAR(1)					:= '2';			-- 分類マスタ集計区分（入金：相殺）
	c_CatDivCRBNKT		CHAR(1)					:= '3';			-- 分類マスタ集計区分（入金：振込）
	c_CatDivCRZIPT		CHAR(1)					:= '4';			-- 分類マスタ集計区分（入金：郵便振替）
	c_CatDivCRMONY		CHAR(1)					:= '5';			-- 分類マスタ集計区分（入金：現金）
	c_CatDivCRBFEE		CHAR(1)					:= '6';			-- 分類マスタ集計区分（入金：手数料）
	c_CatDivCRSRED		CHAR(1)					:= '7';			-- 分類マスタ集計区分（入金：売上割引）
	c_CatDivCRLOSS		CHAR(1)					:= '8';			-- 分類マスタ集計区分（入金：雑損失）
	c_CatDivCROTHR		CHAR(1)					:= '9';			-- 分類マスタ集計区分（入金：その他）
	c_CatDivCRPREC		CHAR(2)					:= '10';		-- 分類マスタ集計区分（入金：前受金）
--
	c_CreditDivON		CHAR(1)					:= '1';			-- 分類マスタ買掛対象区分（1：対象）
	c_PayableUpdDivCOM	CHAR(1)					:= '1';			-- 買掛更新処理済み
	c_Approval			CHAR(1)					:= '3';			-- 承認済み
	daAccountAR		ACCOUNTS.ACCOUNTS_CD%TYPE;					-- 売掛金勘定科目
	daAccountAD		ACCOUNTS.ACCOUNTS_CD%TYPE;					-- 前受金勘定科目
	
BEGIN
	SELECT ACCOUNTS_CD INTO daAccountAR FROM ACCOUNTS WHERE ARTICLE_ACCOUNTS = '10';	-- 売掛金勘定科目取得
	SELECT ACCOUNTS_CD INTO daAccountAD FROM ACCOUNTS WHERE ARTICLE_ACCOUNTS = '12';	-- 前受金勘定科目取得
		DELETE FROM TRANS_JOURNAL;
		INSERT INTO TRANS_JOURNAL
		(SELECT
			SLIP.SLIP_DATE,					-- 伝票日付
			SLIP.SLIP_NO,					-- 伝票NO
			NVL(SLIP.SLIP_ROW_NO, 1),		-- 行NO
			c_SlipDivisionAP21,				-- 伝票区分06（固定）
			BU1.ACCOUNT_CD,					-- 借方部門コード
			SLIP.DEBIT_TITLE_CD,			-- 借方科目コード
			SLIP.DEBIT_VENDER_CD,			-- 借方取引先コード
			SLIP.DEBIT_REMARK,				-- 借方摘要名
			NULL,							-- 借方
			SLIP.DEBIT_AMOUNT,				-- 借方金額
			SLIP.DEBIT_TAX_AMOUNT,			-- 借方消費税額
			NULL,							-- 借方課税区分
			SLIP.DEBIT_TAX_DIVISION,		-- 借方税処理区分(0:内税1:外税2:別記)
			SLIP.DEBIT_SLIP_DATE,			-- 借方取引日付
			0,								-- 借方外貨金額
			0,								-- 借方外貨レート
			NULL,							-- 借方外貨区分
			NULL,							-- 借方備忘１
			NULL,							-- 借方備忘２
			NULL,							-- 借方備忘３
			0,								-- 借方付箋区分
			NULL,							-- 借方付箋メモ
			NULL,							-- 借方備考１
			0,								-- 借方備考２
			NULL,							-- 借方備考３
			NULL,							-- 借方備考４
			NULL,							-- 借方手形シリアル
			NULL,							-- 借方消課税区分
			NULL,							-- 借方消科目ｺｰﾄﾞ
			BU2.ACCOUNT_CD,					-- 貸方部門コード
			SLIP.CREDIT_TITLE_CD,			-- 貸方科目コード
			SLIP.CREDIT_VENDER_CD,			-- 貸方取引先コード
			SLIP.CREDIT_REMARK,				-- 貸方摘要名
			NULL,							-- 貸方ﾞ
			SLIP.CREDIT_AMOUNT,				-- 貸方金額
			SLIP.CREDIT_TAX_AMOUNT,			-- 貸方消費税額
			NULL,							-- 貸方課税区分
			SLIP.CREDIT_TAX_DIVISION,		-- 貸方税処理区分
			SLIP.CREDIT_SLIP_DATE,			-- 貸方取引日付
			0,								-- 貸方外貨金額
			0,								-- 貸方外貨レート
			NULL,							-- 貸方外貨区分
			NULL,							-- 貸方備忘１
			NULL,							-- 貸方備忘２
			NULL,							-- 貸方備忘３
			0,								-- 貸方付箋区分
			NULL,							-- 貸方付箋メモ
			NULL,							-- 貸方備考１
			0,								-- 貸方備考２
			NULL,							-- 貸方備考３
			NULL,							-- 貸方備考４
			NULL,							-- 貸方手形シリアル
			NULL,					-- 貸方消課税区分
			NULL,					-- 貸方消科目コード
			SYSDATE,				-- テスト用の記述。標準機能に合わせる。
			P_STR_TANTO_CD,			-- テスト用の記述。標準機能に合わせる。
			SYSDATE,				-- テスト用の記述。標準機能に合わせる。
			P_STR_TANTO_CD			-- テスト用の記述。標準機能に合わせる。
		FROM
		(
		--  /* １：売上伝票 */
		SELECT
			TO_CHAR(SL.SALES_DATE, 'YYYYMMDD') AS SLIP_DATE,
			c_SlipNoInitSALES || SUBSTR(SL.SALES_SLIP_NO, LENGTH(SL.SALES_SLIP_NO) - 4, 5) AS SLIP_NO,
--			SL.SALES_SLIP_ROW_NO AS SLIP_ROW_NO,
		ROW_NUMBER()																				-- 2009/09/08 伝票毎に行番号を採番
			OVER(PARTITION BY SL.SALES_SLIP_NO ORDER BY SL.SALES_SLIP_ROW_NO ) AS SLIP_ROW_NO,	-- 伝票番号行番号 2009/11/12
--			c_SlipNoInitSALES || SUBSTR(SL.SALES_NO, LENGTH(SL.SALES_NO) - 4, 5) AS SLIP_NO,    -- 伝票ができるまで暫定！
--			1 AS SLIP_ROW_NO,   						 -- 伝票ができるまで暫定！
			SL.ACCOUNT_DEBIT_SECTION_CD AS DEBIT_SECTION_CD,
			SL.ACCOUNT_CREDIT_SECTION_CD AS CREDIT_SECTION,
			SUBSTR(SL.DEBIT_TITLE_CD, 1, 7) AS DEBIT_TITLE_CD,
			SUBSTR(SL.CREDIT_TITLE_CD, 1, 7) AS CREDIT_TITLE_CD,
			c_VenderCdVENDER||SL.INVOICE_CD AS DEBIT_VENDER_CD,
			c_VenderCdVENDER||SL.INVOICE_CD AS CREDIT_VENDER_CD,
			substr((SELECT ITEM.ITEM_NAME FROM ITEM WHERE SL.ITEM_CD = ITEM.ITEM_CD),1,30) AS DEBIT_REMARK,
			substr((SELECT ITEM.ITEM_NAME FROM ITEM WHERE SL.ITEM_CD = ITEM.ITEM_CD),1,30) AS CREDIT_REMARK,
			CASE 										-- 借方科目
				WHEN SL.DEBIT_TITLE_CD = daAccountAR THEN  SL.SALES_AMOUNT + SL.TAX_AMOUNT		-- 売掛金のとき税込額
				WHEN SL.DEBIT_TITLE_CD = daAccountAD THEN  SL.SALES_AMOUNT + SL.TAX_AMOUNT		-- 前受金のとき税込額
				ELSE														-- 売上のとき
					CASE 								-- 消費税区分
						WHEN SL.TAX_DIVISION = 2 THEN	SL.SALES_AMOUNT + SL.TAX_AMOUNT			-- 内税のとき税込額
						ELSE		SL.SALES_AMOUNT							-- 外税のとき税抜額
					END
			END AS DEBIT_AMOUNT, -- 借方金額
			CASE 										-- 借方科目
				WHEN SL.CREDIT_TITLE_CD =  daAccountAR THEN  SL.SALES_AMOUNT + SL.TAX_AMOUNT		-- 売掛金のとき税込額
				WHEN SL.CREDIT_TITLE_CD =  daAccountAD THEN  SL.SALES_AMOUNT + SL.TAX_AMOUNT		-- 前受金のとき税込額
				ELSE														-- 売上のとき
					CASE 								-- 消費税区分
						WHEN SL.TAX_DIVISION = 2 THEN	SL.SALES_AMOUNT + SL.TAX_AMOUNT			-- 内税のとき税込額
						ELSE		SL.SALES_AMOUNT							-- 外税のとき税抜額
					END
			END AS CREDIT_AMOUNT, -- 貸方金額
			CASE
				WHEN SL.DEBIT_TITLE_CD = daAccountAR  THEN 0 -- 借方科目が売掛金のとき
				WHEN SL.DEBIT_TITLE_CD = daAccountAD  THEN 0 -- 借方科目が前受金のとき					 
				ELSE	NVL(SL.TAX_AMOUNT, 0)
			END AS DEBIT_TAX_AMOUNT,
			CASE
				WHEN SL.CREDIT_TITLE_CD = daAccountAR THEN 0
				WHEN SL.CREDIT_TITLE_CD = daAccountAD THEN 0
				ELSE	NVL(SL.TAX_AMOUNT, 0)
			END AS CREDIT_TAX_AMOUNT,
-- 2014/2/13 新消費税対応 ->
--			CASE
--				WHEN SL.DEBIT_TITLE_CD = daAccountAR THEN c_TaxDivisionOTR
--				WHEN SL.DEBIT_TITLE_CD = daAccountAD THEN c_TaxDivisionOTR
--				ELSE
--					CASE
--						WHEN SL.TAX_DIVISION in (1,2,3) THEN DECODE(SL.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--						ELSE
--							CASE
--								WHEN VE.TAX_DIVISION = 4 THEN DECODE(CO.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--								ELSE DECODE(VE.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--							END
--					END
--			END AS DEBIT_TAX_DIVISION,	-- 消費税区分|1:外税 2:内税 3:非課税 4:自社ﾏｽﾀ
--			CASE
--				WHEN SL.CREDIT_TITLE_CD = daAccountAR THEN c_TaxDivisionOTR
--				WHEN SL.CREDIT_TITLE_CD = daAccountAD THEN c_TaxDivisionOTR
--				ELSE
--					CASE
--						WHEN SL.TAX_DIVISION in (1,2,3) THEN DECODE(SL.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--						ELSE
--							CASE
--								WHEN VE.TAX_DIVISION = 4 THEN DECODE(CO.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--								ELSE DECODE(VE.TAX_DIVISION, 1, c_TaxDivisionOUT, 2, c_TaxDivisionIN, 3, c_TaxDivisionOTR)
--							END
--					END
--			END AS CREDIT_TAX_DIVISION,	-- 消費税区分|1:外税 2:内税 3:非課税 4:自社ﾏｽﾀ
			FUN_GET_TAX_PROC(SL.TAX_DIVISION,SL.TAX_RATIO) AS DEBIT_TAX_DIVISION,
			FUN_GET_TAX_PROC(SL.TAX_DIVISION,SL.TAX_RATIO) AS CREDIT_TAX_DIVISION,
-- 2014/2/13 新消費税対応 <-

			TO_CHAR(SL.SALES_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE,
			TO_CHAR(SL.SALES_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE
		FROM SALES SL,
			VENDER VE,
			CLASSIFICATION CL,
			COMPANY CO
		WHERE ((SL.DEPOSIT_UPDATE_DIVISION = 1) or (SL.DEBIT_TITLE_CD = daAccountAD) or (SL.CREDIT_TITLE_CD = daAccountAD))	-- 売掛更新済みまたは前受金
--			AND SL.TRANSMISSION_DATE IS NULL				-- 会計送信未処理
			AND SL.SALES_AMOUNT <>0						-- 売上金額が0ではない 2009/11/12
			AND SL.SALES_SLIP_NO IS NOT NULL				-- 売上伝票発行済み
			AND SL.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
			AND CL.DATA_TYPE = 1
			AND SL.INVOICE_CD = VE.VENDER_CD
			AND VE.VENDER_DIVISION = 'TS'
			AND (NVL(P_STR_ORGANIZATION_CD, 'ALL') = 'ALL' OR SL.CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
--			AND SL.ACCOUNT_YEARS = P_STR_TARGET_YEARS
			AND to_char(SL.SALES_DATE,'YYYYMM') = P_STR_TARGET_YEARS
		) SLIP,
		BUMON BU1,
		BUMON BU2
	WHERE SLIP.DEBIT_SECTION_CD = BU1.SECTION_CD
	AND SLIP.CREDIT_SECTION = BU2.SECTION_CD)
	;
--  送信済み日付セット
	UPDATE SALES
	   SET TRANSMISSION_DATE = SYSDATE,
		     UPDATE_DATE = SYSDATE,
		      UPDATOR_CD = P_STR_TANTO_CD
         WHERE SALES_NO in
	(
		SELECT SALES_NO
		  FROM SALES SL,
			VENDER VE,
			CLASSIFICATION CL,
			COMPANY CO
		WHERE ((SL.DEPOSIT_UPDATE_DIVISION = 1) or (SL.DEBIT_TITLE_CD = '04210'))	-- 売掛更新済みまたは前受金
--			AND SL.TRANSMISSION_DATE IS NULL
			AND SL.SALES_SLIP_NO IS NOT NULL
			AND SL.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
			AND CL.DATA_TYPE = 1
			AND SL.INVOICE_CD = VE.VENDER_CD
			AND VE.VENDER_DIVISION = 'TS'
			AND (NVL(P_STR_ORGANIZATION_CD, 'ALL') = 'ALL' OR SL.CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
--			AND SL.ACCOUNT_YEARS = P_STR_TARGET_YEARS
			AND to_char(SL.SALES_DATE,'YYYYMM') = P_STR_TARGET_YEARS
		) 
	  ;
     COMMIT
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
		OUTPUT_ERROR_LOG('PRO_JOURNAL_AR', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
--		OUTPUT_ERROR_LOG('PRO_JOURNAL_AR','PROCEDURE',SQLCODE,'SLIP:' || SLIP_NO || ' ' ||  SQLERRM);
END PRO_JOURNAL_AR;
/
