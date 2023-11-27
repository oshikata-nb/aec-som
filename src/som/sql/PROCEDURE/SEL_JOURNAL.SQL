CREATE OR REPLACE PROCEDURE AP21.SEL_JOURNAL IS
	/*------------------------------------------------------------
      カーソル宣言
    ------------------------------------------------------------*/
	CURSOR curheader IS
		SELECT OFFSET_NO
		FROM   OFFSET_GROUP_HEADER
		WHERE  APPROVAL_STATUS = 3 -- 承認済
		AND    TRANSMISSION_DATE IS NULL
		GROUP  BY OFFSET_NO
		ORDER  BY OFFSET_NO;

	CURSOR curdata(p_offset_no IN OFFSET_GROUP_HEADER.OFFSET_NO%TYPE) IS
		SELECT DATA.OFFSET_DATE
			  ,DATA.OFFSET_NO
			  ,DEBIT.DEBIT_SECTION_CD
			  ,DEBIT_SECTION.SECTION_NAME DEBIT_SECTION_NAME
			  ,CREDIT.CREDIT_SECTION_CD
			  ,CREDIT_SECTION.SECTION_NAME CREDIT_SECTION_NAME
			  ,DEBIT.DEBIT_TITLE_CD
			  ,CREDIT.CREDIT_TITLE_CD
			  ,DEBIT_SECTION.ACCOUNT_CD DEBIT_ACCOUNT_CD
			  ,CREDIT_SECTION.ACCOUNT_CD CREDIT_ACCOUNT_CD
			  ,DEBIT.VENDER_CD DEBIT_VENDER_CD
			  ,CREDIT.VENDER_CD CREDIT_VENDER_CD
			  ,HEADER.SUMMARY
			  ,NVL(PAYABLE_OFFSET_AMOUNT, 0) DEBIT_OFFSET_AMOUNT
			  ,NVL(DEPOSIT_OFFSET_AMOUNT, 0) CREDIT_OFFSET_AMOUNT
		FROM   OFFSET_GROUP_HEADER HEADER
			  ,BUMON               DEBIT_SECTION
			  ,BUMON               CREDIT_SECTION
			   
			  ,(SELECT OFFSET_NO, OFFSET_DATE, VENDER_CD
				FROM   OFFSET_GROUP_DATA DATA
				WHERE  OFFSET_NO = p_offset_no
				GROUP  BY OFFSET_NO, OFFSET_DATE, VENDER_CD) DATA
			   
			  ,(SELECT OFFSET_NO
					  ,DEBIT_SECTION_CD
					  ,DEBIT_TITLE_CD
					  ,VENDER_CD
					  ,SUM(PAYABLE_OFFSET_AMOUNT) PAYABLE_OFFSET_AMOUNT
				FROM   OFFSET_GROUP_DATA
				WHERE  VENDER_DIVISION = 'SI'
				AND    PAYABLE_OFFSET_AMOUNT <> 0
				GROUP  BY OFFSET_NO
						 ,DEBIT_SECTION_CD
						 ,DEBIT_TITLE_CD
						 ,VENDER_CD) DEBIT
			   
			  ,(SELECT OFFSET_NO
					  ,CREDIT_SECTION_CD
					  ,CREDIT_TITLE_CD
					  ,VENDER_CD
					  ,SUM(DEPOSIT_OFFSET_AMOUNT) DEPOSIT_OFFSET_AMOUNT
				FROM   OFFSET_GROUP_DATA
				WHERE  VENDER_DIVISION = 'TS'
				AND    DEPOSIT_OFFSET_AMOUNT <> 0
				GROUP  BY OFFSET_NO
						 ,CREDIT_SECTION_CD
						 ,CREDIT_TITLE_CD
						 ,VENDER_CD) CREDIT
		
		WHERE  HEADER.APPROVAL_STATUS = 3
		AND    HEADER.TRANSMISSION_DATE IS NULL
		AND    HEADER.OFFSET_NO = DATA.OFFSET_NO
		AND    DATA.OFFSET_NO = DEBIT.OFFSET_NO(+)
		AND    DATA.OFFSET_NO = CREDIT.OFFSET_NO(+)
		AND    DATA.VENDER_CD = DEBIT.VENDER_CD(+)
		AND    DATA.VENDER_CD = CREDIT.VENDER_CD(+)
		AND    DEBIT.DEBIT_SECTION_CD = DEBIT_SECTION.SECTION_CD(+)
		AND    CREDIT.CREDIT_SECTION_CD = CREDIT_SECTION.SECTION_CD(+)
		
		ORDER  BY DATA.VENDER_CD;
	/*------------------------------------------------------------
      定数・変数宣言
    --------------------------------------------------------------*/
	rtheader  curheader%ROWTYPE;
	offset_no OFFSET_GROUP_HEADER.OFFSET_NO%TYPE;
	TYPE vender_cd IS TABLE OF OFFSET_GROUP_DATA.VENDER_CD%TYPE INDEX BY BINARY_INTEGER;
	TYPE section_name IS TABLE OF BUMON.SECTION_NAME%TYPE INDEX BY BINARY_INTEGER;

	/* 借方 */
	TYPE debit_offset_amount IS TABLE OF OFFSET_GROUP_DATA.PAYABLE_OFFSET_AMOUNT%TYPE INDEX BY BINARY_INTEGER;
	TYPE debit_section_cd IS TABLE OF OFFSET_GROUP_DATA.DEBIT_SECTION_CD%TYPE INDEX BY BINARY_INTEGER;
	TYPE debit_title_cd IS TABLE OF OFFSET_GROUP_DATA.DEBIT_TITLE_CD%TYPE INDEX BY BINARY_INTEGER;
	TYPE debit_account_cd IS TABLE OF BUMON.ACCOUNT_CD%TYPE INDEX BY BINARY_INTEGER;

	/* 貸方 */
	TYPE credit_offset_amount IS TABLE OF OFFSET_GROUP_DATA.DEPOSIT_OFFSET_AMOUNT%TYPE INDEX BY BINARY_INTEGER;
	TYPE credit_section_cd IS TABLE OF OFFSET_GROUP_DATA.CREDIT_SECTION_CD%TYPE INDEX BY BINARY_INTEGER;
	TYPE credit_title_cd IS TABLE OF OFFSET_GROUP_DATA.CREDIT_TITLE_CD%TYPE INDEX BY BINARY_INTEGER;
	TYPE credit_account_cd IS TABLE OF BUMON.ACCOUNT_CD%TYPE INDEX BY BINARY_INTEGER;

	/* 集計元 */
	/* 借方 */
	origin_debit_vender_cd     vender_cd;
	origin_debit_section_cd    debit_section_cd;
	origin_debit_section_name  section_name;
	origin_debit_title_cd      debit_title_cd;
	origin_debit_account_cd    debit_account_cd;
	origin_debit_offset_amount debit_offset_amount;

	/* 貸方 */
	origin_credit_vender_cd     vender_cd;
	origin_credit_section_cd    credit_section_cd;
	origin_credit_section_name  section_name;
	origin_credit_title_cd      credit_title_cd;
	origin_credit_account_cd    credit_account_cd;
	origin_credit_offset_amount credit_offset_amount;

	/* 集計結果 */
	/* 借方 */
	result_debit_vender_cd     vender_cd;
	result_debit_section_cd    debit_section_cd;
	result_debit_section_name  section_name;
	result_debit_title_cd      debit_title_cd;
	result_debit_account_cd    debit_account_cd;
	result_debit_offset_amount debit_offset_amount;

	/* 貸方 */
	result_credit_vender_cd     vender_cd;
	result_credit_section_cd    credit_section_cd;
	result_credit_section_name  section_name;
	result_credit_title_cd      credit_title_cd;
	result_credit_account_cd    credit_account_cd;
	result_credit_offset_amount credit_offset_amount;
	cnt                         NUMBER := 0;
	k                           NUMBER := 0;
	total                       NUMBER := 0;
	flg                         NUMBER := 0;
	rec_cnt                     NUMBER := 0;
BEGIN
	-- 会計送信用テーブルクリア
	DELETE FROM TEMP_JOURNAL;

	SELECT COUNT(*)
	INTO   rec_cnt
	FROM   (
			-- /* ２：入金伝票  */
			SELECT TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS SLIP_DATE -- 伝票日付
				   ,'5' || SUBSTR(CR.CREDIT_NO, LENGTH(CR.CREDIT_NO) - 4, 5) AS SLIP_NO -- 伝票NO(固定文字「5」＋伝票番号下５桁現在は入金番号→入金伝票番号に変える)
				   ,CR.ROW_NO AS SLIP_ROW_NO -- 行NO
				   ,NVL(CR.DEBIT_SECTION_CD, '0101') AS DEBIT_SECTION_CD -- 借方部門コード(NULL のとき全社)
				   ,NVL(CR.CREDIT_SECTION_CD, '0101') AS CREDIT_SECTION -- 貸方部門コード(NULL のとき全社)
				   ,CR.DEBIT_TITLE_CD AS DEBIT_TITLE_CD -- 借方科目コード
				   ,NVL(SUBSTR(CR.CREDIT_TITLE_CD, 1, 7), '01130') AS CREDIT_TITLE_CD -- 貸方科目コード(NULL のとき売掛金)
				   ,DECODE(CR.CATEGORY_DIVISION
						  ,1
						  ,'01' || CR.VENDER_CD -- 手形のときは請求先
						  ,3
						  ,'02' || CR.BANK_CD -- 振込のときは銀行
						  ,4
						  ,'02' || CR.BANK_CD -- 郵便振替のときは銀行
						  ,13
						  ,'02' || CR.BANK_CD -- 前受金-振込のときは銀行
						  ,14
						  ,'02' || CR.BANK_CD -- 前受金-郵便振替のときは銀行
						  ,NULL) AS DEBIT_VENDER_CD -- 借方取引先コード
				   ,'01' || CR.VENDER_CD AS CREDIT_VENDER_CD -- 貸方取引先コード
				   ,SUBSTR(CR.REMARK, 1, 30) AS DEBIT_REMARK -- 借方摘要名
				   ,SUBSTR(CR.REMARK, 1, 30) AS CREDIT_REMARK -- 貸方摘要名
				   ,CR.CREDIT_AMOUNT AS DEBIT_AMOUNT -- 借方金額
				   ,CR.CREDIT_AMOUNT AS CREDIT_AMOUNT -- 貸方金額
				   ,DECODE(CR.CATEGORY_DIVISION
						  ,6
						  ,ROUND(CR.CREDIT_AMOUNT * CO.TAX_RATIO /
								 (100 + CO.TAX_RATIO)
								,0) -- 手数料のときは消費税(内税)
						  ,16
						  ,ROUND(CR.CREDIT_AMOUNT * CO.TAX_RATIO /
								 (100 + CO.TAX_RATIO)
								,0) -- 手数料のときは消費税(内税)
						  ,NULL) AS DEBIT_TAX_AMOUNT -- 借方消費税額
				   ,NULL AS CREDIT_TAX_AMOUNT -- 貸方消費税額
				   ,DECODE(CR.CATEGORY_DIVISION, 6, '0', 16, '0', '2') AS DEBIT_TAX_DIVISION -- 借方課税区分
				   ,'2' AS CREDIT_TAX_DIVISION -- 貸方税処理区分
				   ,TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE -- 借方取引日付
				   ,TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE -- 貸方取引日付
			FROM   CREDIT CR, CLASSIFICATION CL, COMPANY CO
			WHERE  CR.APPROVAL_STATUS = 3 -- 3:承認済
			AND    CR.TRANSMISSION_DATE IS NULL
			AND    CR.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
			AND    CL.DATA_TYPE = 2 -- 2:入金
			
			UNION ALL
			
			-- /* ４：支払伝票 */
			SELECT TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS SLIP_DATE -- 伝票日付
				  ,'4' || SUBSTR(PY.SLIP_NO, LENGTH(PY.SLIP_NO) - 4, 5) AS SLIP_NO -- 伝票NO
				  ,PY.ROW_NO AS SLIP_ROW_NO -- 行NO
				  ,PY.DEBIT_SECTION_CD AS DEBIT_SECTION_CD -- 借方部門コード
				  ,PY.CREDIT_SECTION_CD AS CREDIT_SECTION -- 貸方部門コード
				  ,SUBSTR(PY.DEBIT_TITLE_CD, 1, 7) AS DEBIT_TITLE_CD -- 借方科目コード
				  ,SUBSTR(PY.CREDIT_TITLE_CD, 1, 7) AS CREDIT_TITLE_CD -- 貸方科目コード
				  ,'01' || PY.SUPPLIER_CD AS DEBIT_VENDER_CD -- 借方取引先コード
				  ,DECODE(PY.CATEGORY_DIVISION
						 ,1
						 ,'01' || PY.SUPPLIER_CD -- 手形のときは支払先
						 ,3
						 ,'02' || CO.BANK_MASTER_CD -- 振込のときは自社振込用銀行
						 ,NULL) AS CREDIT_VENDER_CD -- 貸方取引先コード
				  ,SUBSTR(PY.SUMMARY, 1, 30) AS DEBIT_REMARK -- 借方摘要名
				  ,SUBSTR(PY.SUMMARY, 1, 30) AS CREDIT_REMARK -- 貸方摘要名
				  ,PY.PAYMENT_AMOUNT AS DEBIT_AMOUNT -- 借方金額
				  ,PY.PAYMENT_AMOUNT AS CREDIT_AMOUNT -- 貸方金額
				  ,NULL AS DEBIT_TAX_AMOUNT -- 借方消費税額
				  ,DECODE(PY.CATEGORY_DIVISION
						 ,6
						 ,ROUND(PY.PAYMENT_AMOUNT * CO.TAX_RATIO /
								(100 + CO.TAX_RATIO)
							   ,0) -- 手数料のときは消費税(内税)
						 ,NULL) AS CREDIT_TAX_AMOUNT -- 貸方消費税額
				  ,'2' AS DEBIT_TAX_DIVISION -- 借方課税区分
				  ,DECODE(PY.CATEGORY_DIVISION, 6, '0', '2') AS CREDIT_TAX_DIVISION -- 貸方課税区分
				  ,TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE -- 借方取引日付
				  ,TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE -- 貸方取引日付
			FROM   PAYMENT PY, CLASSIFICATION CL, COMPANY CO
			WHERE  PY.APPROVAL_STATUS = 3 -- 3:承認済
			AND    PY.TRANSMISSION_DATE IS NULL
			AND    PY.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
			AND    CL.DATA_TYPE = 4 -- 4:支払
			AND    CL.CREDIT_DIVISION = 1 -- 1:買掛対象
			AND    PY.APPROVAL_STATUS = 3 -- 3:承認済
			) SLIP
		  ,BUMON BU1
		  ,BUMON BU2
	WHERE  SLIP.DEBIT_SECTION_CD = BU1.SECTION_CD(+)
	AND    SLIP.CREDIT_SECTION = BU2.SECTION_CD(+);

	IF 0 < rec_cnt THEN
		-- 会計送信用テーブルにデータ追加
		INSERT INTO TEMP_JOURNAL
			SELECT SLIP.SLIP_DATE -- 伝票日付
				  ,SLIP.SLIP_NO -- 伝票NO
				  ,NVL(SLIP.SLIP_ROW_NO, 1) -- 行NO
				  ,'06' -- 伝票区分06（固定）
				  ,NVL(BU1.ACCOUNT_CD, '9999') -- 借方部門コード
				  ,NVL(SLIP.DEBIT_TITLE_CD, '99999') -- 借方科目コード
				  ,SLIP.DEBIT_VENDER_CD -- 借方取引先コード
				  ,SLIP.DEBIT_REMARK -- 借方摘要名
				  ,NULL -- 借方ﾌﾟﾛｼﾞｪｸﾄｺｰﾄﾞ
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
				  ,NULL -- 貸方
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
				  ,SLIP.KR_TEGATA_NO -- 借方手形番号
				  ,SLIP.KR_SHUBETU -- 借方手形種別|1:約束手形 3:為替手形
				  ,SLIP.KR_FURI_YMD -- 借方振出日
				  ,SLIP.KR_MANKI_YMD -- 借方満期日
				  ,SLIP.KR_KESSAI_YMD -- 借方決済予定日
				  ,SLIP.KS_TEGATA_NO -- 貸方手形番号
				  ,SLIP.KS_SHUBETU -- 貸方手形種別|1:約束手形 3:為替手形
				  ,SLIP.KS_FURI_YMD -- 貸方振出日
				  ,SLIP.KS_MANKI_YMD -- 貸方満期日
				  ,SLIP.KS_KESSAI_YMD -- 貸方決済予定日
				  ,SYSDATE -- 登録日時
				  ,'AUTO' -- 登録者ID
				  ,SYSDATE -- 更新日時
				  ,'AUTO' -- 更新者ID
			FROM   (
					-- /* ２：入金伝票  */
					SELECT TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS SLIP_DATE -- 伝票日付
						   ,'5' ||
							SUBSTR(CR.CREDIT_NO, LENGTH(CR.CREDIT_NO) - 4, 5) AS SLIP_NO -- 伝票NO(固定文字「5」＋伝票番号下５桁現在は入金番号→入金伝票番号に変える)
						   ,CR.ROW_NO AS SLIP_ROW_NO -- 行NO
						   ,NVL(CR.DEBIT_SECTION_CD, '0101') AS DEBIT_SECTION_CD -- 借方部門コード(NULL のとき全社)
						   ,NVL(CR.CREDIT_SECTION_CD, '0101') AS CREDIT_SECTION -- 貸方部門コード(NULL のとき全社)
						   ,CR.DEBIT_TITLE_CD AS DEBIT_TITLE_CD -- 借方科目コード
						   ,NVL(SUBSTR(CR.CREDIT_TITLE_CD, 1, 7), '01130') AS CREDIT_TITLE_CD -- 貸方科目コード(NULL のとき売掛金)
						   ,DECODE(CR.CATEGORY_DIVISION
								  ,1
								  ,'01' || CR.VENDER_CD -- 手形のときは請求先
								  ,3
								  ,'02' || CR.BANK_CD -- 振込のときは銀行
								  ,4
								  ,'02' || CR.BANK_CD -- 郵便振替のときは銀行
								  ,13
								  ,'02' || CR.BANK_CD -- 前受金-振込のときは銀行
								  ,14
								  ,'02' || CR.BANK_CD -- 前受金-郵便振替のときは銀行
								  ,NULL) AS DEBIT_VENDER_CD -- 借方取引先コード
						   ,'01' || CR.VENDER_CD AS CREDIT_VENDER_CD -- 貸方取引先コード
						   ,SUBSTR(CR.REMARK, 1, 30) AS DEBIT_REMARK -- 借方摘要名
						   ,SUBSTR(CR.REMARK, 1, 30) AS CREDIT_REMARK -- 貸方摘要名
						   ,CR.CREDIT_AMOUNT AS DEBIT_AMOUNT -- 借方金額
						   ,CR.CREDIT_AMOUNT AS CREDIT_AMOUNT -- 貸方金額
						   ,DECODE(CR.CATEGORY_DIVISION
								  ,6
-- 2014/2/24 新消費税対応 ->
--								  ,ROUND(CR.CREDIT_AMOUNT * CO.TAX_RATIO /
--										 (100 + CO.TAX_RATIO)
--										,0) -- 手数料のときは消費税(内税)
								  -- 消費税率を入金日を元に自社マスタから取得する
								  ,ROUND(CR.CREDIT_AMOUNT * FUN_GET_TAX_RATIO(CR.CREDIT_DATE) /
										 (100 + FUN_GET_TAX_RATIO(CR.CREDIT_DATE))
										,0) -- 手数料のときは消費税(内税)

-- 2014/2/24 新消費税対応 <-
								  ,16
-- 2014/2/24 新消費税対応 ->
--								  ,ROUND(CR.CREDIT_AMOUNT * CO.TAX_RATIO /
--										 (100 + CO.TAX_RATIO)
--										,0) -- 手数料のときは消費税(内税)
								  -- 消費税率を入金日を元に自社マスタから取得する
								  ,ROUND(CR.CREDIT_AMOUNT * FUN_GET_TAX_RATIO(CR.CREDIT_DATE) /
										 (100 + FUN_GET_TAX_RATIO(CR.CREDIT_DATE))
										,0) -- 手数料のときは消費税(内税)
-- 2014/2/24 新消費税対応 <-

								  ,NULL) AS DEBIT_TAX_AMOUNT -- 借方消費税額
						   ,NULL AS CREDIT_TAX_AMOUNT -- 貸方消費税額
-- 2014/2/24 新消費税対応 ->
--						   ,DECODE(CR.CATEGORY_DIVISION, 6, '0', 16, '0', '2') AS DEBIT_TAX_DIVISION -- 借方課税区分
						   -- 内税で消費税率に対応した税処理区分を取得する
						   ,DECODE(CR.CATEGORY_DIVISION, 6, TO_CHAR(FUN_GET_TAX_PROC('2',FUN_GET_TAX_RATIO(CR.CREDIT_DATE))), 16, TO_CHAR(FUN_GET_TAX_PROC('2',FUN_GET_TAX_RATIO(CR.CREDIT_DATE))), '2') AS DEBIT_TAX_DIVISION -- 借方課税区分
-- 2014/2/24 新消費税対応 <-
						   ,'2' AS CREDIT_TAX_DIVISION -- 貸方課税区分
						   ,TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE -- 借方取引日付
						   ,TO_CHAR(CR.CREDIT_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE -- 貸方取引日付
						   ,TRANS.TEGATA_NO KR_TEGATA_NO -- 借方手形番号
						   ,NULL KS_TEGATA_NO -- 貸方手形番号
						   ,TRANS.SHUBETU KR_SHUBETU -- 借方手形種別|1:約束手形 3:為替手形
						   ,NULL KS_SHUBETU -- 貸方手形種別|1:約束手形 3:為替手形
						   ,TO_CHAR(TRANS.FURI_YMD, 'YYYYMMDD') KR_FURI_YMD -- 借方振出日
						   ,NULL KS_FURI_YMD -- 貸方振出日
						   ,TO_CHAR(TRANS.MANKI_YMD, 'YYYYMMDD') KR_MANKI_YMD -- 借方満期日
						   ,NULL KS_MANKI_YMD -- 貸方満期日
						   ,TO_CHAR(TRANS.KESSAI_YMD, 'YYYYMMDD') KR_KESSAI_YMD -- 借方決済予定日
						   ,NULL KS_KESSAI_YMD -- 貸方決済予定日
					FROM   CREDIT         CR
						   ,CLASSIFICATION CL
						   ,COMPANY        CO
						   ,TRANS_NOTE     TRANS
					WHERE  CR.APPROVAL_STATUS = 3 -- 3:承認済
					AND    CR.TRANSMISSION_DATE IS NULL
					AND    CR.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
					AND    CL.DATA_TYPE = 2 -- 2:入金
					AND    CR.CREDIT_NO = TRANS.SLIP_NO(+)
					AND    CR.ROW_NO = TRANS.ROW_NO(+)
					
					UNION ALL
					
					-- /* ４：支払伝票 */
					SELECT TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS SLIP_DATE -- 伝票日付
						  ,'4' ||
						   SUBSTR(PY.SLIP_NO, LENGTH(PY.SLIP_NO) - 4, 5) AS SLIP_NO -- 伝票NO
						  ,PY.ROW_NO AS SLIP_ROW_NO -- 行NO
						  ,PY.DEBIT_SECTION_CD AS DEBIT_SECTION_CD -- 借方部門コード
						  ,PY.CREDIT_SECTION_CD AS CREDIT_SECTION -- 貸方部門コード
						  ,SUBSTR(PY.DEBIT_TITLE_CD, 1, 7) AS DEBIT_TITLE_CD -- 借方科目コード
						  ,SUBSTR(PY.CREDIT_TITLE_CD, 1, 7) AS CREDIT_TITLE_CD -- 貸方科目コード
						  ,'01' || PY.SUPPLIER_CD AS DEBIT_VENDER_CD -- 借方取引先コード
						  ,DECODE(PY.CATEGORY_DIVISION
								 ,1
								 ,'01' || PY.SUPPLIER_CD -- 手形のときは支払先
								 ,3
								 ,'02' || CO.BANK_MASTER_CD -- 振込のときは自社振込用銀行
								 ,NULL) AS CREDIT_VENDER_CD -- 貸方取引先コード
						  ,SUBSTR(PY.SUMMARY, 1, 30) AS DEBIT_REMARK -- 借方摘要名
						  ,SUBSTR(PY.SUMMARY, 1, 30) AS CREDIT_REMARK -- 貸方摘要名
						  ,PY.PAYMENT_AMOUNT AS DEBIT_AMOUNT -- 借方金額
						  ,PY.PAYMENT_AMOUNT AS CREDIT_AMOUNT -- 貸方金額
						  ,NULL AS DEBIT_TAX_AMOUNT -- 借方消費税額
						  ,DECODE(PY.CATEGORY_DIVISION
								 ,6
-- 2014/2/24 新消費税対応 ->
--								 ,ROUND(PY.PAYMENT_AMOUNT * CO.TAX_RATIO /
--										(100 + CO.TAX_RATIO)
--									   ,0) -- 手数料のときは消費税(内税)
								  -- 消費税率を入金日を元に自社マスタから取得する
								 ,ROUND(PY.PAYMENT_AMOUNT * FUN_GET_TAX_RATIO(PY.PAYMENT_DATE) /
										(100 + FUN_GET_TAX_RATIO(PY.PAYMENT_DATE))
									   ,0) -- 手数料のときは消費税(内税)
-- 2014/2/24 新消費税対応 <-
								 ,NULL) AS CREDIT_TAX_AMOUNT -- 借方消費税額
						  ,'2' AS DEBIT_TAX_DIVISION -- 借方課税区分
-- 2014/2/24 新消費税対応 ->
--						  ,DECODE(PY.CATEGORY_DIVISION, 6, '0', '2') AS CREDIT_TAX_DIVISION -- 貸方課税区分
						  ,DECODE(PY.CATEGORY_DIVISION, 6, TO_CHAR(FUN_GET_TAX_PROC('2',FUN_GET_TAX_RATIO(PY.PAYMENT_DATE))), '2') AS CREDIT_TAX_DIVISION -- 貸方課税区分
-- 2014/2/24 新消費税対応 <-

						  ,TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS DEBIT_SLIP_DATE -- 借方取引日付
						  ,TO_CHAR(PY.PAYMENT_DATE, 'YYYYMMDD') AS CREDIT_SLIP_DATE -- 貸方取引日付
						  ,NULL KR_TEGATA_NO -- 借方手形番号
						  ,TRANS.TEGATA_NO KS_TEGATA_NO -- 貸方手形番号
						  ,NULL KR_SHUBETU -- 借方手形種別|1:約束手形 3:為替手形
						  ,TRANS.SHUBETU KS_SHUBETU -- 貸方手形種別|1:約束手形 3:為替手形
						  ,NULL KR_FURI_YMD -- 借方振出日
						  ,TO_CHAR(TRANS.FURI_YMD, 'YYYYMMDD') KS_FURI_YMD -- 貸方振出日
						  ,NULL KR_MANKI_YMD -- 借方満期日
						  ,TO_CHAR(TRANS.MANKI_YMD, 'YYYYMMDD') KS_MANKI_YMD -- 貸方満期日
						  ,NULL KR_KESSAI_YMD -- 借方決済予定日
						  ,TO_CHAR(TRANS.KESSAI_YMD, 'YYYYMMDD') KS_KESSAI_YMD -- 貸方決済予定日
					FROM   PAYMENT        PY
						  ,CLASSIFICATION CL
						  ,COMPANY        CO
						  ,TRANS_NOTE     TRANS
					WHERE  PY.APPROVAL_STATUS = 3 -- 3:承認済
					AND    PY.TRANSMISSION_DATE IS NULL
					AND    PY.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
					AND    CL.DATA_TYPE = 4 -- 4:支払
					AND    CL.CREDIT_DIVISION = 1 --1:買掛対象
					AND    PY.APPROVAL_STATUS = 3 --3:承認済
					AND    PY.SLIP_NO = TRANS.SLIP_NO(+)
					AND    PY.ROW_NO = TRANS.ROW_NO(+)) SLIP
				  ,BUMON BU1
				  ,BUMON BU2
			WHERE  SLIP.DEBIT_SECTION_CD = BU1.SECTION_CD(+)
			AND    SLIP.CREDIT_SECTION = BU2.SECTION_CD(+);
	END IF;

	/* 相殺 */
	OPEN curheader;
	LOOP
		FETCH curheader
			INTO rtheader;
	
		IF curheader%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		offset_no := rtheader.OFFSET_NO;
		cnt       := 0;
		k         := 0;
		total     := 0;
	
		/* レコードを配列へ */
		FOR rtdata IN curdata(offset_no) LOOP
			/* 借方 */
			origin_debit_vender_cd(cnt) := rtdata.DEBIT_VENDER_CD;
			origin_debit_section_cd(cnt) := rtdata.DEBIT_SECTION_CD;
			origin_debit_section_name(cnt) := rtdata.DEBIT_SECTION_NAME;
			origin_debit_title_cd(cnt) := rtdata.DEBIT_TITLE_CD;
			origin_debit_account_cd(cnt) := rtdata.DEBIT_ACCOUNT_CD;
			origin_debit_offset_amount(cnt) := rtdata.DEBIT_OFFSET_AMOUNT;
		
			/* 貸方 */
			origin_credit_vender_cd(cnt) := rtdata.CREDIT_VENDER_CD;
			origin_credit_section_cd(cnt) := rtdata.CREDIT_SECTION_CD;
			origin_credit_section_name(cnt) := rtdata.CREDIT_SECTION_NAME;
			origin_credit_title_cd(cnt) := rtdata.CREDIT_TITLE_CD;
			origin_credit_account_cd(cnt) := rtdata.CREDIT_ACCOUNT_CD;
			origin_credit_offset_amount(cnt) := rtdata.CREDIT_OFFSET_AMOUNT;
			cnt := cnt + 1;
		END LOOP;
	
		/* 相殺金額合計計算 */
		FOR i IN 0 .. cnt - 1 LOOP
			total := total + origin_debit_offset_amount(i);
		END LOOP;
	
		WHILE (total <> 0) LOOP
			flg := 0;
		
			/* 買掛 */
			FOR i IN 0 .. cnt - 1 LOOP
				IF origin_debit_offset_amount(i) <> 0 THEN
					/* 売掛 */
					FOR j IN 0 .. cnt - 1 LOOP
						IF origin_credit_offset_amount(j) <> 0 THEN
							/* 相殺金額の小さい方を使う */
							IF origin_debit_offset_amount(i) <
							   origin_credit_offset_amount(j) THEN
								/* 借方 */
								result_debit_vender_cd(k) := origin_debit_vender_cd(i);
								result_debit_section_cd(k) := origin_debit_section_cd(i);
								result_debit_section_name(k) := origin_debit_section_name(i);
								result_debit_title_cd(k) := origin_debit_title_cd(i);
								result_debit_account_cd(k) := origin_debit_account_cd(i);
								result_debit_offset_amount(k) := origin_debit_offset_amount(i);
							
								/* 貸方 */
								result_credit_vender_cd(k) := origin_credit_vender_cd(j);
								result_credit_section_cd(k) := origin_credit_section_cd(j);
								result_credit_section_name(k) := origin_credit_section_name(j);
								result_credit_title_cd(k) := origin_credit_title_cd(j);
								result_credit_account_cd(k) := origin_credit_account_cd(j);
								result_credit_offset_amount(k) := origin_debit_offset_amount(i);
							
								origin_credit_offset_amount(j) := origin_credit_offset_amount(j) -
																  origin_debit_offset_amount(i);
								total := total -
										 origin_debit_offset_amount(i);
								origin_debit_offset_amount(i) := 0;
							ELSE
								/* 借方 */
								result_debit_vender_cd(k) := origin_debit_vender_cd(i);
								result_debit_section_cd(k) := origin_debit_section_cd(i);
								result_debit_section_name(k) := origin_debit_section_name(i);
								result_debit_title_cd(k) := origin_debit_title_cd(i);
								result_debit_account_cd(k) := origin_debit_account_cd(i);
								result_debit_offset_amount(k) := origin_credit_offset_amount(j);
							
								/* 貸方 */
								result_credit_vender_cd(k) := origin_credit_vender_cd(j);
								result_credit_section_cd(k) := origin_credit_section_cd(j);
								result_credit_section_name(k) := origin_credit_section_name(j);
								result_credit_title_cd(k) := origin_credit_title_cd(j);
								result_credit_account_cd(k) := origin_credit_account_cd(j);
								result_credit_offset_amount(k) := origin_credit_offset_amount(j);
							
								origin_debit_offset_amount(i) := origin_debit_offset_amount(i) -
																 origin_credit_offset_amount(j);
								total := total -
										 origin_credit_offset_amount(j);
								origin_credit_offset_amount(j) := 0;
							END IF;
						
							k   := k + 1;
							flg := 1;
							EXIT;
						END IF;
					END LOOP;
				
					IF flg = 1 THEN
						EXIT;
					END IF;
				END IF;
			END LOOP;
		END LOOP;
	
		cnt := 0;
	
		/* 配列をレコードへ */
		FOR rtdata IN curdata(offset_no) LOOP
			IF cnt <= k - 1 THEN
				/* テーブルへ追加 */
				INSERT INTO TEMP_JOURNAL
				VALUES
					(TO_CHAR(rtdata.OFFSET_DATE, 'YYYYMMDD') -- 伝票日付
					,'3' || SUBSTR(rtdata.OFFSET_NO
								  ,LENGTH(rtdata.OFFSET_NO) - 4
								  ,5) -- 伝票№
					,cnt + 1 -- 行№
					,'06' -- 伝票区分
					,NVL(result_debit_account_cd(cnt), '9999') -- 借方部門コード
					,NVL(result_debit_title_cd(cnt), '99999') -- 借方科目コード
					,'01' || result_debit_vender_cd(cnt) -- 借方取引先コード
					,SUBSTR(rtdata.SUMMARY, 1, 30) -- 借方適用名
					,NULL -- 借方
					,result_debit_offset_amount(cnt) -- 借方金額
					,NULL -- 借方消費税額
					,NULL -- 借方課税区分
					,'2' -- 借方税区分
					,TO_CHAR(rtdata.OFFSET_DATE, 'YYYYMMDD') -- 借方取引日付
					,0 -- 借方外貨金額
					,0 -- 借方外貨レート
					,NULL -- 借方外貨区分
					,NULL -- 借方備忘1
					,NULL -- 借方備忘2
					,NULL -- 借方備忘3
					,0 -- 借方付箋区分
					,NULL -- 借方付箋メモ
					,NULL -- 借方備考1
					,0 -- 借方備考2
					,NULL -- 借方備考3
					,NULL -- 借方備考4
					,NULL -- 借方手形シリアル
					,NULL -- 借方消課税区分
					,NULL -- 借方消科目コード
					,NVL(result_credit_account_cd(cnt), '9999') -- 貸方部門コード
					,NVL(result_credit_title_cd(cnt), '99999') -- 貸方科目コード
					,'01' || result_credit_vender_cd(cnt) -- 貸方取引先コード
					,SUBSTR(rtdata.SUMMARY, 1, 30) -- 貸方適用名
					,NULL -- 貸方
					,result_credit_offset_amount(cnt) -- 貸方金額
					,NULL -- 貸方消費税額
					,NULL -- 貸方課税区分
					,'2' -- 貸方税区分
					,TO_CHAR(rtdata.OFFSET_DATE, 'YYYYMMDD') -- 貸方取引日付
					,0 -- 貸方外貨金額
					,0 -- 貸方外貨レート
					,NULL -- 貸方外貨区分
					,NULL -- 貸方備忘1
					,NULL -- 貸方備忘2
					,NULL -- 貸方備忘3
					,0 -- 貸方付箋区分
					,NULL -- 貸方付箋メモ
					,NULL -- 貸方備考1
					,0 -- 貸方備考2
					,NULL -- 貸方備考3
					,NULL -- 貸方備考4
					,NULL -- 貸方手形シリアル
					,NULL -- 貸方消課税区分
					,NULL -- 貸方消科目コード
					,NULL -- 借方手形番号
					,NULL -- 借方手形種別|1:約束手形 3:為替手形
					,NULL -- 借方振出日
					,NULL -- 借方満期日
					,NULL -- 借方決済予定日
					,NULL -- 貸方手形番号
					,NULL -- 貸方手形種別|1:約束手形 3:為替手形
					,NULL -- 貸方振出日
					,NULL -- 貸方満期日
					,NULL -- 貸方決済予定日
					,SYSDATE -- 登録日時
					,'AUTO' -- 登録者ID
					,SYSDATE -- 更新日時
					,'AUTO'); -- 更新者ID
			END IF;
		
			cnt := cnt + 1;
		END LOOP;
	
		/* メモリ開放 */
		/* 借方 */
		origin_debit_vender_cd.DELETE;
		origin_debit_section_cd.DELETE;
		origin_debit_section_name.DELETE;
		origin_debit_title_cd.DELETE;
		origin_debit_account_cd.DELETE;
		origin_debit_offset_amount.DELETE;
	
		/* 貸方 */
		origin_credit_vender_cd.DELETE;
		origin_credit_section_cd.DELETE;
		origin_credit_section_name.DELETE;
		origin_credit_title_cd.DELETE;
		origin_credit_account_cd.DELETE;
		origin_credit_offset_amount.DELETE;
	
		/* 借方 */
		result_debit_vender_cd.DELETE;
		result_debit_section_cd.DELETE;
		result_debit_section_name.DELETE;
		result_debit_title_cd.DELETE;
		result_debit_account_cd.DELETE;
		result_debit_offset_amount.DELETE;
	
		/* 貸方 */
		result_credit_vender_cd.DELETE;
		result_credit_section_cd.DELETE;
		result_credit_section_name.DELETE;
		result_credit_title_cd.DELETE;
		result_credit_account_cd.DELETE;
		result_credit_offset_amount.DELETE;
	END LOOP;

	CLOSE curheader;

	-- 送信済フラグ更新
	-- 入金
	UPDATE CREDIT
	SET    TRANSMISSION_DATE = SYSDATE
		  ,UPDATOR_CD        = 'AUTO'
		  ,UPDATE_DATE       = SYSDATE
	WHERE  CREDIT_NO IN (SELECT CR.CREDIT_NO
						 FROM   CREDIT CR, CLASSIFICATION CL, COMPANY CO
						 WHERE  CR.APPROVAL_STATUS = 3 -- 3:承認済
						 AND    CR.TRANSMISSION_DATE IS NULL
						 AND    CR.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
						 AND    CL.DATA_TYPE = 2); -- 2:入金
	-- 支払
	UPDATE PAYMENT
	SET    TRANSMISSION_DATE = SYSDATE
		  ,UPDATOR_CD        = 'AUTO'
		  ,UPDATE_DATE       = SYSDATE
	WHERE  SLIP_NO IN (SELECT PY.SLIP_NO
					   FROM   PAYMENT PY, CLASSIFICATION CL, COMPANY CO
					   WHERE  PY.APPROVAL_STATUS = 3
					   AND    PY.TRANSMISSION_DATE IS NULL
					   AND    PY.CATEGORY_DIVISION = CL.CATEGORY_DIVISION
					   AND    CL.DATA_TYPE = 4 -- 4:支払
					   AND    CL.CREDIT_DIVISION = 1 --1:買掛対象
					   );
	-- 相殺
	UPDATE OFFSET_GROUP_HEADER
	SET    TRANSMISSION_DATE = SYSDATE
		  ,UPDATOR_CD        = 'AUTO'
		  ,UPDATE_DATE       = SYSDATE
	WHERE  OFFSET_NO IN
		   (SELECT OS_HEADER.OFFSET_NO
			FROM   (SELECT OFFSET_NO
						  ,OFFSET_GROUP_CD
						  ,OFFSET_DATE
						  ,VENDER_CD
						  ,PAYABLE_OFFSET_AMOUNT
						  ,DEBIT_SECTION_CD
						  ,DEBIT_TITLE_CD
					FROM   OFFSET_GROUP_DATA
					WHERE  VENDER_DIVISION = 'SI'
					AND    PAYABLE_OFFSET_AMOUNT <> 0) OS_DEBIT -- 買掛金
				  ,(SELECT OFFSET_NO
						  ,ORGANIZATION_CD
						  ,OFFSET_GROUP_CD
						  ,OFFSET_DATE
						  ,VENDER_CD
						  ,DEPOSIT_OFFSET_AMOUNT
						  ,CREDIT_SECTION_CD
						  ,CREDIT_TITLE_CD
					FROM   OFFSET_GROUP_DATA
					WHERE  VENDER_DIVISION = 'TS'
					AND    DEPOSIT_OFFSET_AMOUNT <> 0) OS_CREDIT -- 売掛金
				  ,OFFSET_GROUP_HEADER OS_HEADER -- 相殺ヘッダー
			WHERE  OS_DEBIT.OFFSET_NO = OS_CREDIT.OFFSET_NO
			AND    OS_DEBIT.OFFSET_NO = OS_HEADER.OFFSET_NO
			AND    OS_HEADER.APPROVAL_STATUS = 3 -- 3:承認済
			AND    OS_HEADER.TRANSMISSION_DATE IS NULL);
	COMMIT;
EXCEPTION
	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('SEL_JOURNAL'
						,'AUTO'
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END;
/
