CREATE OR REPLACE PROCEDURE AP21.PRO_JOURNAL_AP
(
	P_STR_ORGANIZATION_CD IN NVARCHAR2
   ,P_STR_TARGET_YEARS    IN NVARCHAR2
   ,P_STR_TANTO_CD        IN NVARCHAR2
   ,O_ERROR_RETURN_CD     OUT NVARCHAR2
   , -- エラーコードを返す
	O_ERROR_RETURN_MSG    OUT NVARCHAR2 -- エラーメッセージを返す
) IS
	/*--------------------------------------------------------------------------------
      会計インターフェース用プロシージャ    (債務メニュー)
      画面からの引数として、部署コード・対象年月による絞込みあり。
      引数        :   P_STR_ORGANIZATION_CD    部署コード          NVARCHAR2
      引数        :   P_STR_TARGET_YEARS       対象年月(YYYYMM)    NVARCHAR2(6)
      
    */ --------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	-- 会計システムの定数
	c_SlipDivisionAP21 CHAR(2) := '06'; -- AP-21伝票区分
	--  c_SlipNoInitSALES   CHAR(1) := '1'; -- 伝票番号頭文字（売上）
	c_SlipNoInitSTOCK CHAR(1) := '2'; -- 伝票番号頭文字（仕入）
	--  c_SlipNoInitOFFSET  CHAR(1) := '3'; -- 伝票番号頭文字（相殺）
	--  c_SlipNoInitPAYMENT CHAR(1) := '4'; -- 伝票番号頭文字（支払）
	--  c_SlipNoInitCREDIT  CHAR(1) := '5'; -- 伝票番号頭文字（入金）
	c_TaxDivisionIN  CHAR(1) := '0'; -- （内税）
	c_TaxDivisionOUT CHAR(1) := '1'; -- （外税）
	c_TaxDivisionOTR CHAR(1) := '2'; -- （別記）
	c_VenderCdVENDER CHAR(2) := '01'; -- 取引先のとき頭に01をつける
	--  c_VenderCdBANK      CHAR(2) := '02'; -- 銀行のとき頭に02をつける
	-- AP-21  分類マスタ(classification)で定義された番号
	--  c_DataTypeSALES    CHAR(1) := '1'; -- 分類マスタ番号（売上）
	--  c_DataTypeCREDIT   CHAR(1) := '2'; -- 分類マスタ番号（入金）
	c_DataTypeSTOCK CHAR(1) := '3'; -- 分類マスタ番号（仕入）
	--  c_DataTypePAYMENT  CHAR(1) := '4'; -- 分類マスタ番号（支払）
	--  c_DataTypeOFFSET   CHAR(1) := '5'; -- 分類マスタ番号（相殺）
	c_CatDivSTSTCK CHAR(1) := '1'; -- 分類マスタ集計区分（仕入：仕入）
	--  c_CatDivSTRETN     CHAR(1) := '2'; -- 分類マスタ集計区分（仕入：返品）
	--  c_CatDivSTDISC     CHAR(1) := '3'; -- 分類マスタ集計区分（仕入：値引）
	c_CatDivSTTRAN CHAR(1) := '4'; -- 分類マスタ集計区分（仕入：運賃）
	--  c_CatDivPYNOTE     CHAR(1) := '1'; -- 分類マスタ集計区分（支払：手形）
	--  c_CatDivPYOFST     CHAR(1) := '2'; -- 分類マスタ集計区分（支払：相殺）
	--  c_CatDivPYBNKT     CHAR(1) := '3'; -- 分類マスタ集計区分（支払：振込）
	--  c_CatDivPYZIPT     CHAR(1) := '4'; -- 分類マスタ集計区分（支払：郵便振替）
	--  c_CatDivPYMONY     CHAR(1) := '5'; -- 分類マスタ集計区分（支払：現金）
	--  c_CatDivPYBFEE     CHAR(1) := '6'; -- 分類マスタ集計区分（支払：手数料）
	--  c_CatDivPYSRED     CHAR(1) := '7'; -- 分類マスタ集計区分（支払：仕入割引）
	--  c_CatDivPYLOSS     CHAR(1) := '8'; -- 分類マスタ集計区分（支払：雑損失）
	--  c_CatDivPYOTHR     CHAR(1) := '9'; -- 分類マスタ集計区分（支払：その他）
	c_CreditDivON CHAR(1) := '1'; -- 分類マスタ買掛対象区分（1：対象）
	--  c_PayableUpdDivCOM CHAR(1) := '1'; -- 買掛更新処理済み
	c_Approval  CHAR(1) := '3'; -- 承認済み
	daAccountAP ACCOUNTS.ACCOUNTS_CD%TYPE; -- 買掛金勘定科目
BEGIN
	SELECT ACCOUNTS_CD
	INTO   daAccountAP
	FROM   ACCOUNTS
	WHERE  PURCHASE_ACCOUNTS = '1'; -- 買掛金勘定科目取得
	DELETE FROM TRANS_JOURNAL;
	INSERT INTO TRANS_JOURNAL
		SELECT SLIP.SLIP_DATE -- 伝票日付
			  ,SLIP.SLIP_NO -- 伝票NO
			  ,NVL(SLIP.SLIP_ROW_NO, 1) -- 行NO
			  ,c_SlipDivisionAP21 -- 伝票区分06（固定）
			  ,NVL(BU1.ACCOUNT_CD, '9999') -- 借方部門コード
			  ,NVL(SLIP.DEBIT_TITLE_CD, '99999') -- 借方科目コード
			  ,SLIP.DEBIT_VENDER_CD -- 借方取引先コード
			  ,SLIP.DEBIT_REMARK -- 借方摘要名
			  ,NULL -- 借方
			  ,SLIP.DEBIT_AMOUNT -- 借方金額
			  ,SLIP.DEBIT_TAX_AMOUNT -- 借方消費税額
			  ,NULL -- 借方課税区分
			  ,NVL(SLIP.DEBIT_TAX_DIVISION, 0) -- 借方税処理区分(0:内税1:外税2:別記)
			  ,SLIP.DEBIT_SLIP_DATE -- 借方取引日付
			  ,0 -- 借方外貨金額
			  ,0 -- 借方外貨レート
			  ,NULL -- 借方外貨区分
			  ,NULL -- 借方備忘１
			  ,NULL -- 借方備忘２
			  ,NULL -- 借方備忘３
			  ,0 -- 借方付箋区分
			  ,NULL -- 借方付箋メモ
			  ,NULL -- 借方備考１
			  ,0 -- 借方備考２
			  ,NULL -- 借方備考３
			  ,NULL -- 借方備考４
			  ,NULL -- 借方手形シリアル
			  ,NULL -- 借方消課税区分
			  ,NULL -- 借方消科目ｺｰﾄﾞ
			  ,NVL(BU2.ACCOUNT_CD, '9999') -- 貸方部門コード
			  ,NVL(SLIP.CREDIT_TITLE_CD, '99999') -- 貸方科目コード
			  ,SLIP.CREDIT_VENDER_CD -- 貸方取引先コード
			  ,SLIP.CREDIT_REMARK -- 貸方摘要名
			  ,NULL -- 貸方ﾞ
			  ,SLIP.CREDIT_AMOUNT -- 貸方金額
			  ,SLIP.CREDIT_TAX_AMOUNT -- 貸方消費税額
			  ,NULL -- 貸方課税区分
			  ,NVL(SLIP.CREDIT_TAX_DIVISION, 0) -- 貸方税処理区分
			  ,SLIP.CREDIT_SLIP_DATE -- 貸方取引日付
			  ,0 -- 貸方外貨金額
			  ,0 -- 貸方外貨レート
			  ,NULL -- 貸方外貨区分
			  ,NULL -- 貸方備忘１
			  ,NULL -- 貸方備忘２
			  ,NULL -- 貸方備忘３
			  ,0 -- 貸方付箋区分
			  ,NULL -- 貸方付箋メモ
			  ,NULL -- 貸方備考１
			  ,0 -- 貸方備考２
			  ,NULL -- 貸方備考３
			  ,NULL -- 貸方備考４
			  ,NULL -- 貸方手形シリアル
			  ,NULL -- 貸方消課税区分
			  ,NULL -- 貸方消科目コード
			  ,SYSDATE -- テスト用の記述。標準機能に合わせる。
			  ,P_STR_TANTO_CD -- テスト用の記述。標準機能に合わせる。
			  ,SYSDATE -- テスト用の記述。標準機能に合わせる。
			  ,P_STR_TANTO_CD -- テスト用の記述。標準機能に合わせる。
		FROM   (
				--  /* ３：仕入伝票 */
				SELECT to_char(ST.STOCKING_DATE, 'YYYYMMDD') AS SLIP_DATE
					   ,c_SlipNoInitSTOCK ||
						substr(ST.SLIP_NO, LENGTH(ST.SLIP_NO) - 4, 5) AS SLIP_NO
					   ,ST.ROW_NO AS SLIP_ROW_NO
					   ,ST.ACCOUNT_DEBIT_SECTION_CD AS DEBIT_SECTION_CD
					   ,substr(ST.DEBIT_TITLE_CD, 1, 7) AS DEBIT_TITLE_CD
					   ,c_VenderCdVENDER || ST.VENDER_CD AS DEBIT_VENDER_CD
					   ,substr((SELECT ITEM.ITEM_NAME
							   FROM   ITEM
							   WHERE  ST.ITEM_CD = ITEM.ITEM_CD)
							  ,1
							  ,30) AS DEBIT_REMARK
					   ,
						--      ST.STOCKING_AMOUNT AS DEBIT_AMOUNT,
						CASE
							WHEN ST.DEBIT_TITLE_CD = daAccountAP THEN -- 借方科目が買掛金のとき
							 ST.STOCKING_AMOUNT + ST.TAX_AMOUNT
						--        ELSE ST.STOCKING_AMOUNT
							ELSE
							 CASE ST.TAX_DIVISION
								 WHEN 1 THEN -- 外税
								  ST.STOCKING_AMOUNT
								 WHEN 2 THEN -- 内税
								  ST.STOCKING_AMOUNT + ST.TAX_AMOUNT
								 WHEN 3 THEN -- 非課税
								  ST.STOCKING_AMOUNT
							 END
						END AS DEBIT_AMOUNT
					   ,
						--      ST.TAX_AMOUNT AS DEBIT_TAX_AMOUNT,
						CASE
							WHEN (ST.CATEGORY_DIVISION = c_CatDivSTSTCK OR
								 ST.CATEGORY_DIVISION = c_CatDivSTTRAN) THEN
							 ST.TAX_AMOUNT
							ELSE
							 0
						END AS DEBIT_TAX_AMOUNT
					   ,
-- 2014/2/10 新消費税対応 ->
--						--      ST.TAX_DIVISION AS DEBIT_TAX_DIVISION,
--						CASE
--							WHEN ST.DEBIT_TITLE_CD = daAccountAP THEN
--							 c_TaxDivisionOTR
--							ELSE
--							 CASE
--								 WHEN ST.TAX_DIVISION IN (1, 2, 3) THEN
--								  DECODE(ST.TAX_DIVISION
--										,1
--										,c_TaxDivisionOUT
--										,2
--										,c_TaxDivisionIN
--										,3
--										,c_TaxDivisionOTR)
--								 ELSE
--								  CASE
--									  WHEN VE.TAX_DIVISION = 4 THEN
--									   DECODE(CO.TAX_DIVISION
--											 ,1
--											 ,c_TaxDivisionOUT
--											 ,2
--											 ,c_TaxDivisionIN
--											 ,3
--											 ,c_TaxDivisionOTR)
--									  ELSE
--									   DECODE(VE.TAX_DIVISION
--											 ,1
--											 ,c_TaxDivisionOUT
--											 ,2
--											 ,c_TaxDivisionIN
--											 ,3
--											 ,c_TaxDivisionOTR)
--								  END
--							 END
--						END AS DEBIT_TAX_DIVISION
						FUN_GET_TAX_PROC(ST.TAX_DIVISION,ST.TAX_RATIO) AS DEBIT_TAX_DIVISION
-- 2014/2/10 新消費税対応 <-
					   , -- 消費税区分|1:外税 2:内税 3:非課税 4:自社ﾏｽﾀ
						to_char(ST.STOCKING_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE
					   ,ST.ACCOUNT_CREDIT_SECTION_CD AS CREDIT_SECTION
					   ,substr(ST.CREDIT_TITLE_CD, 1, 7) AS CREDIT_TITLE_CD
					   ,c_VenderCdVENDER || ST.VENDER_CD AS CREDIT_VENDER_CD
					   ,substr((SELECT ITEM.ITEM_NAME
							   FROM   ITEM
							   WHERE  ST.ITEM_CD = ITEM.ITEM_CD)
							  ,1
							  ,30) AS CREDIT_REMARK
					   ,
						--      ST.STOCKING_AMOUNT AS CREDIT_AMOUNT,
						CASE
							WHEN ST.CREDIT_TITLE_CD = daAccountAP THEN -- 貸方科目が買掛金のとき
							 ST.STOCKING_AMOUNT + ST.TAX_AMOUNT
							ELSE
							 ST.STOCKING_AMOUNT
						END AS CREDIT_AMOUNT
					   ,
						--      ST.TAX_AMOUNT AS CREDIT_TAX_AMOUNT,
						CASE
							WHEN ST.CREDIT_TITLE_CD = daAccountAP THEN
							 0
							ELSE
							 ST.TAX_AMOUNT
						END AS CREDIT_TAX_AMOUNT
					   ,
-- 2014/2/10 新消費税対応 ->
--						--      ST.TAX_DIVISION AS CREDIT_TAX_DIVISION,
--						CASE
--							WHEN ST.CREDIT_TITLE_CD = daAccountAP THEN
--							 c_TaxDivisionOTR
--							ELSE
--							 CASE
--								 WHEN ST.TAX_DIVISION IN (1, 2, 3) THEN
--								  DECODE(ST.TAX_DIVISION
--										,1
--										,c_TaxDivisionOUT
--										,2
--										,c_TaxDivisionIN
--										,3
--										,c_TaxDivisionOTR)
--								 ELSE
--								  CASE
--									  WHEN VE.TAX_DIVISION = 4 THEN
--									   DECODE(CO.TAX_DIVISION
--											 ,1
--											 ,c_TaxDivisionOUT
--											 ,2
--											 ,c_TaxDivisionIN
--											 ,3
--											 ,c_TaxDivisionOTR)
--									  ELSE
--									   DECODE(VE.TAX_DIVISION
--											 ,1
--											 ,c_TaxDivisionOUT
--											 ,2
--											 ,c_TaxDivisionIN
--											 ,3
--											 ,c_TaxDivisionOTR)
--								  END
--							 END
--						END AS CREDIT_TAX_DIVISION
						FUN_GET_TAX_PROC(ST.TAX_DIVISION,ST.TAX_RATIO) AS CREDIT_TAX_DIVISION
-- 2014/2/10 新消費税対応 <-

					   , -- 消費税区分|1:外税 2:内税 3:非課税 4:自社ﾏｽﾀ
						to_char(ST.STOCKING_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE
				FROM   (SELECT STOCKING_DATE             AS STOCKING_DATE
							   ,SLIP_NO                   AS SLIP_NO
							   ,1                         AS ROW_NO
							   , -- 仕入は１しかない。
								ITEM_CD                   AS ITEM_CD
							   ,ACCOUNT_DEBIT_SECTION_CD  AS ACCOUNT_DEBIT_SECTION_CD
							   ,DEBIT_TITLE_CD            AS DEBIT_TITLE_CD
							   ,PAYEE_CD                  AS VENDER_CD
							   ,SUMMARY                   AS SUMMARY
							   ,STOCKING_AMOUNT           AS STOCKING_AMOUNT
							   ,TAX_AMOUNT                AS TAX_AMOUNT
							   ,TAX_DIVISION              AS TAX_DIVISION
							   ,ACCOUNT_CREDIT_SECTION_CD AS ACCOUNT_CREDIT_SECTION_CD
							   ,CREDIT_TITLE_CD           AS CREDIT_TITLE_CD
							   ,STATUS2                   AS APPROVAL_STATUS
							   ,CATEGORY_DIVISION         AS CATEGORY_DIVISION
							   ,CHARGE_ORGANIZATION_CD    AS ORGANIZATION_CD -- 担当部署コード
-- 2014/2/10 新消費税対応 ->
							   ,TAX_RATIO              AS TAX_RATIO
-- 2014/2/10 新消費税対応 <-
						 FROM   PURCHASE_SUBCONTRACT
						 WHERE  STATUS2 = c_Approval -- 承認済み
						 AND    PURCHASE_SUBCONTRACT.ROW_NO IN (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
						 AND    STOCKING_AMOUNT <> 0
						 AND    (NVL(P_STR_ORGANIZATION_CD, 'ALL') = 'ALL' OR
							   CHARGE_ORGANIZATION_CD =
							   P_STR_ORGANIZATION_CD)
						 AND    to_char(STOCKING_DATE, 'YYYYMM') =
								P_STR_TARGET_YEARS
						 --          AND TRANSMISSION_DATE IS NULL
						 ) ST
					   ,VENDER VE
					   ,CLASSIFICATION CL
					   ,COMPANY CO
				WHERE  ST.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
				AND    CL.DATA_TYPE = c_DataTypeSTOCK
				AND    CL.CREDIT_DIVISION = c_CreditDivON --買掛対象
				AND    ST.VENDER_CD = VE.VENDER_CD
				AND    VE.VENDER_DIVISION = 'SI'
				--      AND ST.ACCOUNT_YEARS = P_STR_TARGET_YEARS
				) SLIP
			  ,BUMON BU1
			  ,BUMON BU2
		WHERE  SLIP.DEBIT_SECTION_CD = BU1.SECTION_CD(+)
		AND    SLIP.CREDIT_SECTION = BU2.SECTION_CD(+);
	--  送信済み日付セット
	UPDATE PURCHASE_SUBCONTRACT
	SET    TRANSMISSION_DATE = SYSDATE
		  ,UPDATE_DATE       = SYSDATE
		  ,UPDATOR_CD        = P_STR_TANTO_CD
	WHERE  STATUS2 = c_Approval -- 承認済み
	AND    PURCHASE_SUBCONTRACT.ROW_NO IN (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
	AND    STOCKING_AMOUNT <> 0
	AND    (NVL(P_STR_ORGANIZATION_CD, 'ALL') = 'ALL' OR
		  CHARGE_ORGANIZATION_CD = P_STR_ORGANIZATION_CD)
	AND    to_char(STOCKING_DATE, 'YYYYMM') = P_STR_TARGET_YEARS
	AND    TRANSMISSION_DATE IS NULL;
	COMMIT;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		ROLLBACK;
		-- SQLエラーコード、エラーメッセージを取得
		O_ERROR_RETURN_CD  := SQLCODE;
		O_ERROR_RETURN_MSG := SUBSTR(SQLERRM, 1, 1024);
		OUTPUT_ERROR_LOG('PRO_JOURNAL_AP'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_JOURNAL_AP;
/
