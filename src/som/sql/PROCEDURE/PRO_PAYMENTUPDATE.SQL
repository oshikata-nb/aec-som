CREATE OR REPLACE PROCEDURE PRO_PAYMENTUPDATE
(
	P_STR_ORGANIZATION_CD IN NVARCHAR2
   ,P_STR_VENDER_CD       IN NVARCHAR2
   ,P_STR_PAYABLE_DATE    IN NVARCHAR2
   ,P_STR_INPUTOR_CD      IN NVARCHAR2
   ,P_STR_COMPANY_CD      IN NVARCHAR2
   ,P_NUM_RET             OUT NUMBER
) IS
	/*----------------------------------------------------------------------------------------------------------------
        種別      : PROCEDURE
        名称      : PRO_PAYMENTUPDATE
        処理内容    : 支払更新処理
        引数      : P_STR_ORGANIZATION_CD   部署コード     NVARCHAR2型
        引数      : P_STR_VENDER_CD       支払先コード  NVARCHAR2型
        引数      : P_STR_PAYABLE_DATE        買掛締め日   NVARCHAR2型
        引数      : P_STR_INPUTOR_CD      登録者ＩＤ   NVARCHAR2型
        引数      : P_STR_COMPANY_CD      会社コード   NVARCHAR2型
        引数(戻値)  : P_NUM_RET             実行結果(0:正常終了, 99:異常終了) NUMBER型
        VERNO.      : 1.00
    (history)
        date        ver     name                comments
        ----------  ------  ------------------  ----------------------------------------------------------------------
        2008.09.24  1.00    TOSCO               creation
        2009.06.18                              SOM殿向けカスタマイズ
        2009.10.16  1.04    okmt.tc             支払残高を仕入のみの支払予定に変更。差引繰越額を残高に変更
        2010.01.18  1.05    n.s                 差引繰越額から相殺額を引くように変更
        2015.11.09  1.06    n.s                 テーブルロック時のログを追加
    */ ----------------------------------------------------------------------------------------------------------------
	--------------------------------------------------------------------------------
	--定数・変数宣言
	--------------------------------------------------------------------------------
	c_DataDivShiShiire   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (仕入)仕入
	c_DataDivShiHenpin   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (仕入)返品
	c_DataDivShiNebiki   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 3; -- (仕入)値引
	c_DataDivShiSonota   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 9; -- (仕入)その他
	c_DataDivNyuSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (入金)相殺
	c_DataDivShiShiharai CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (支払)支払
	c_DataDivShiSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 2; -- (支払)相殺
	c_DataDivShiSonota9  CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 9; -- (支払)その他
	c_DataDivSouSousai   CONSTANT CLASSIFICATION.DATA_TOTAL_DIVISION%TYPE := 1; -- (グループ間相殺)グループ間相殺
	c_PaymentTrgDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_TARGET_DIVISION%TYPE := 1; -- 支払対象(処理済)
	c_PaymentTrgDiv9     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_TARGET_DIVISION%TYPE := 9; -- 支払対象(対象外)
	c_PaymentUpdDiv0     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE := 0; -- 支払更新フラグ(未処理)
	c_PaymentUpdDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION%TYPE := 1; -- 支払更新フラグ(更新済)
	c_PaymentDiv0        CONSTANT CLASSIFICATION.PAYMENT_DIVISION%TYPE := 0; -- 支払対象(対象外)
	c_PaymentDiv1        CONSTANT CLASSIFICATION.PAYMENT_DIVISION%TYPE := 1; -- 支払対象(対象)
	c_PayableUpdDiv0     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE := 0; -- 買掛更新フラグ(未処理)
	c_PayableUpdDiv1     CONSTANT PURCHASE_SUBCONTRACT.PAYABLE_UPDATE_DIVISION%TYPE := 1; -- 買掛更新フラグ(更新済)
	c_Status2Sts3        CONSTANT PURCHASE_SUBCONTRACT.STATUS2%TYPE := 3; -- 仕入ステータス(承認済)
	c_ApprovalSts3       CONSTANT PAYMENT.APPROVAL_STATUS%TYPE := 3; -- 承認ステータス(承認済)
	c_VenderDivSI        CONSTANT VENDER.VENDER_DIVISION%TYPE := 'SI'; -- 取引先区分(SI:仕入先)
	c_TraComLoad1        CONSTANT VENDER.TRANSFER_COMMISSION_LOAD%TYPE := 1; -- 振込手数料負担(1:相手先)
	DEF_PROC_NAME        CONSTANT    PROC_PARAM.PROC_CD%TYPE := 'PRO_PAYMENTUPDATE';
	c_CreditDiv3        VENDER.CATEGORY_DIVISION3%TYPE := 3; -- 支払分類(3:振込)
	c_CreditDiv4        VENDER.CATEGORY_DIVISION3%TYPE := 4; -- 支払分類(4:郵便振替)
	c_CreditDiv7        VENDER.CATEGORY_DIVISION3%TYPE := 7; -- 支払分類(7:仕入割引)
	nvAccountYears      PURCHASE_SUBCONTRACT.ACCOUNT_YEARS%TYPE; -- 勘定年月
	daPayableDate       PAYMENT_HEADER.PAYABLE_DATE%TYPE; -- 支払締め日
	nvPaymentNo         PAYMENT_HEADER.PAYMENT_NO%TYPE; -- 支払番号
	rtRecWork           PAYMENT_HEADER%ROWTYPE; -- 支払ヘッダー
	nPayableAmount      PAYMENT_HEADER.PAYABLE_AMOUNT%TYPE; -- 支払残高(差引繰越額含まず)
	nCheckPayableAmount PAYMENT_HEADER.PAYABLE_AMOUNT%TYPE; -- 支払残高チェック用(差引繰越額含まず)
	nStockReduction     PAYMENT_HEADER.STOCK_REDUCTION%TYPE; -- 仕入割引額
	nTransferFee        PAYMENT_HEADER.TRANSFER_FEE%TYPE; -- 振込手数料
	nvCreditScheDate    NVARCHAR2(8); -- 支払予定日(YYYYMMDD)
	nvCreditDiv         VENDER.CATEGORY_DIVISION3%TYPE; -- 支払分類
	nNoteSight          VENDER.NOTE_SIGHT1%TYPE; -- 手形サイト
	nHolidayFlg         VENDER.HOLIDAY_FLG%TYPE; -- 休日指定フラグ
	nTraComLoad         VENDER.TRANSFER_COMMISSION_LOAD%TYPE; -- 振込手数料負担(1:相手先 2:自社)
	nDiscountDays       VENDER.PURCHASE_DISCOUNT_DAYS1%TYPE; -- 仕入割引日数
	nPrime              COMPANY.PRIME%TYPE; -- 短プラ+金利
	nFee                BANK.FEE%TYPE; -- 振込手数料
	nvCount             NUMBER := 0; -- カウンター
	ERR_CODE            VARCHAR2(100);
	ERR_MSG             VARCHAR2(2048);
	msg     	    TEMP_BATCH_MSG.MSG%TYPE; 
	--------------------------------------------------------------------------------
	--★カーソル宣言
	--------------------------------------------------------------------------------
	--対象データ抽出 カーソル
	CURSOR curSales
	(
		p_daPayableDate  IN DATE
	   ,p_nvAccountYears IN PURCHASE_SUBCONTRACT.ACCOUNT_YEARS%TYPE
	) IS
		SELECT *
		FROM   (SELECT TRN.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
					  ,TRN.VENDER_CD AS VENDER_CD --支払先コード
					  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
					  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
					  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
					  ,SUM(CLAIM_AMOUNT_FORWARD -
						   (CREDIT_AMOUNT_FORWARD +
						   OTHER_CREDIT_AMOUNT_FORWARD + OFFSET_AMOUNT)) AS BALANCE_FORWARD --差引繰越額
					  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
					  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
					  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
					  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
					  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
					  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
					   /*
                       ,SUM((CLAIM_AMOUNT_FORWARD + STOCKING_AMOUNT + OTHER_STOCKING_AMOUNT + TAX_AMOUNT)
                           - (STOCKING_RETURNED_AMOUNT + STOCKING_DISCOUNT_AMOUNT + CREDIT_AMOUNT_FORWARD
                               + OTHER_CREDIT_AMOUNT_FORWARD + OFFSET_AMOUNT)) AS PAYABLE_AMOUNT --支払残高
                       */
					  ,SUM((STOCKING_AMOUNT + OTHER_STOCKING_AMOUNT +
						   TAX_AMOUNT) - (STOCKING_RETURNED_AMOUNT +
						   STOCKING_DISCOUNT_AMOUNT)) AS PAYABLE_AMOUNT -- 2009/10/16 仕入のみの支払予定に変更
				FROM   (SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
							  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
							  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
							  ,SUM(BALANCE_FORWARD) AS BALANCE_FORWARD --差引繰越額
							  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
							  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
							  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
							  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
							  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
							  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --支払残高
						FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS VENDER_CD --支払先コード
									  ,0 AS CLAIM_AMOUNT_FORWARD
									  ,0 AS CREDIT_AMOUNT_FORWARD
									  ,0 AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0 AS BALANCE_FORWARD
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'2'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)) AS STOCKING_AMOUNT --今回仕入額(内税の場合は＋消費税額)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額(内税の場合は＋消費税額)
									  ,0 AS STOCKING_RETURNED_AMOUNT
									  ,0 AS STOCKING_DISCOUNT_AMOUNT
									  ,0 AS OTHER_STOCKING_AMOUNT
									  ,0 AS OFFSET_AMOUNT
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0) AS TAX_AMOUNT --消費税額(区分=外税のみ)
                                       */
									  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
											 ,3
											 ,0
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)) AS TAX_AMOUNT --消費税額(区分=外税のみ)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
											   p_nvAccountYears
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiShiire --仕入
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
											   c_PaymentUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									   (PURCHASE_SUBCONTRACT.VENDER_CD =
									   P_STR_VENDER_CD AND
									   PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
									   PURCHASE_SUBCONTRACT.PAYEE_CD =
									   P_STR_VENDER_CD)
								AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS SUPPLIER_CD --支払先コード
									  ,0 AS CLAIM_AMOUNT_FORWARD
									  ,0 AS CREDIT_AMOUNT_FORWARD
									  ,0 AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0 AS BALANCE_FORWARD
									  ,0 AS STOCKING_AMOUNT
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'2'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)) AS STOCKING_RETURNED_AMOUNT --返品金額(内税の場合は＋消費税額)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額(内税の場合は＋消費税額)
									  ,0 AS STOCKING_DISCOUNT_AMOUNT_ACC
									  ,0 AS OTHER_STOCKING_AMOUNT_ACC
									  ,0 AS OFFSET_AMOUNT_ACC
									   /*                                    ,(DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0) * -1) AS TAX_AMOUNT --消費税額(区分=外税のみ)
                                       */
									  ,(DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,3
											  ,0
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)) * -1) AS TAX_AMOUNT --消費税額(区分=外税のみ)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
											   p_nvAccountYears
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiHenpin --返品
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
											   c_PaymentUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									   (PURCHASE_SUBCONTRACT.VENDER_CD =
									   P_STR_VENDER_CD AND
									   PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
									   PURCHASE_SUBCONTRACT.PAYEE_CD =
									   P_STR_VENDER_CD)
								AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS SUPPLIER_CD --支払先コード
									  ,0 AS CLAIM_AMOUNT_FORWARD
									  ,0 AS CREDIT_AMOUNT_FORWARD
									  ,0 AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0 AS BALANCE_FORWARD
									  ,0 AS STOCKING_AMOUNT
									  ,0 AS STOCKING_RETURNED_AMOUNT
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'2'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)) AS STOCKING_DISCOUNT_AMOUNT --値引金額(内税の場合は＋消費税額)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額(内税の場合は＋消費税額)
									  ,0 AS OTHER_STOCKING_AMOUNT
									  ,0 AS OFFSET_AMOUNT
									   /*                                    ,(DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0) * -1) AS TAX_AMOUNT --消費税額(区分=外税のみ)
                                       */
									  ,(DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,3
											  ,0
											  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
															,1
															,1)
													 ,'-'
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													 ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)) * -1) AS TAX_AMOUNT --消費税額(区分=外税のみ)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
											   p_nvAccountYears
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiNebiki --値引
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
											   c_PaymentUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									   (PURCHASE_SUBCONTRACT.VENDER_CD =
									   P_STR_VENDER_CD AND
									   PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
									   PURCHASE_SUBCONTRACT.PAYEE_CD =
									   P_STR_VENDER_CD)
								AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								UNION ALL
								SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,DECODE(PURCHASE_SUBCONTRACT.PAYEE_CD
											 ,NULL
											 ,PURCHASE_SUBCONTRACT.VENDER_CD
											 ,PURCHASE_SUBCONTRACT.PAYEE_CD) AS SUPPLIER_CD --支払先コード
									  ,0 AS CLAIM_AMOUNT_FORWARD
									  ,0 AS CREDIT_AMOUNT_FORWARD
									  ,0 AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0 AS BALANCE_FORWARD
									  ,0 AS STOCKING_AMOUNT
									  ,0 AS STOCKING_RETURNED_AMOUNT
									  ,0 AS STOCKING_DISCOUNT_AMOUNT
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'2'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,(PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT) * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT + PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT)) AS OTHER_STOCKING_AMOUNT --その他仕入金額(内税の場合は＋消費税額)
                                       */
									  ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
													,1
													,1)
											 ,'-'
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT * -1
											 ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額(内税の場合は＋消費税額)
									  ,0 AS OFFSET_AMOUNT
									   /*                                    ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
                                       ,'1'
                                       ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION, 1, 1)
                                              ,'-'
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
                                              ,PURCHASE_SUBCONTRACT.TAX_AMOUNT)
                                       ,0) AS TAX_AMOUNT --消費税額(区分=外税のみ)
                                       */
									  ,DECODE(PURCHASE_SUBCONTRACT.TAX_DIVISION
											 ,3
											 ,0
											 ,DECODE(SUBSTR(PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
														   ,1
														   ,1)
													,'-'
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT * -1
													,PURCHASE_SUBCONTRACT.TAX_AMOUNT)) AS TAX_AMOUNT --消費税額(区分=外税のみ)
									  ,0 AS PAYABLE_AMOUNT
								FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
											  ,PURCHASE_SUBCONTRACT.VENDER_CD
											  ,VENDER.PAYMENT_INVOICE_CD AS PAYEE_CD
											  ,PURCHASE_SUBCONTRACT.TAX_DIVISION
											  ,PURCHASE_SUBCONTRACT.STOCKING_AMOUNT
											  ,PURCHASE_SUBCONTRACT.TAX_AMOUNT
											  ,PURCHASE_SUBCONTRACT.DATA_TYPE
											  ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
											  ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
										FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
											  ,VENDER               VENDER
										WHERE  ('ALL' = NVL(P_STR_ORGANIZATION_CD
														   ,'ALL') OR
											   PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
											   P_STR_ORGANIZATION_CD)
										AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
											   p_nvAccountYears
										AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
											   c_DataDivShiSonota --その他売上
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
											   c_PaymentUpdDiv0 --未処理
										AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL
										AND    PURCHASE_SUBCONTRACT.STATUS2 =
											   c_Status2Sts3 --承認済
										AND    PURCHASE_SUBCONTRACT.VENDER_CD =
											   VENDER.VENDER_CD
										AND    PURCHASE_SUBCONTRACT.ROW_NO IN
											   (1, 1001) --仕入番号の１行目だけに絞る（消費税が全行に入っているため）
										AND    VENDER.VENDER_DIVISION =
											   c_VenderDivSI) PURCHASE_SUBCONTRACT
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									   (PURCHASE_SUBCONTRACT.VENDER_CD =
									   P_STR_VENDER_CD AND
									   PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
									   PURCHASE_SUBCONTRACT.PAYEE_CD =
									   P_STR_VENDER_CD)
								AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								)
						GROUP  BY ORGANIZATION_CD
								 ,VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
							  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
							  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
							  ,SUM(BALANCE_FORWARD) AS BALANCE_FORWARD --差引繰越額
							  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
							  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
							  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
							  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
							  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
							  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --支払残高
						FROM   (SELECT CREDIT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,CREDIT.VENDER_CD       AS VENDER_CD --請求先コード
									  ,0                      AS CLAIM_AMOUNT_FORWARD
									  ,0                      AS CREDIT_AMOUNT_FORWARD
									  ,0                      AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0                      AS BALANCE_FORWARD
									  ,0                      AS STOCKING_AMOUNT
									  ,0                      AS STOCKING_RETURNED_AMOUNT
									  ,0                      AS STOCKING_DISCOUNT_AMOUNT
									  ,0                      AS OTHER_STOCKING_AMOUNT
									  ,CREDIT.CREDIT_AMOUNT   AS OFFSET_AMOUNT --入金相殺金額
									  ,0                      AS TAX_AMOUNT
									  ,0                      AS PAYABLE_AMOUNT
								FROM   CREDIT         CREDIT --入金トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   CREDIT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  CREDIT.VENDER_CD = P_STR_VENDER_CD)
								AND    CREDIT.CREDIT_DATE <= p_daPayableDate
								AND    CREDIT.DATA_TOTAL_DIVISION =
									   c_DataDivNyuSousai --相殺
								AND    CREDIT.PAYMENT_UPDATE_DIVISION =
									   c_PaymentUpdDiv0 --未処理
								AND    CREDIT.PAYMENT_UPDATE_DATE IS NULL
								AND    CREDIT.APPROVAL_STATUS = c_ApprovalSts3 --承認済
								AND    CREDIT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    CREDIT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    CREDIT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								)
						GROUP  BY ORGANIZATION_CD
								 ,VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
							  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
							  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
							  ,SUM(BALANCE_FORWARD) AS BALANCE_FORWARD --差引繰越額
							  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
							  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
							  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
							  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
							  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
							  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --支払残高
						FROM   (SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD     AS VENDER_CD --支払先コード
									  ,0                       AS CLAIM_AMOUNT_FORWARD
									  ,PAYMENT.PAYMENT_AMOUNT  AS CREDIT_AMOUNT_FORWARD --前回支払額
									  ,0                       AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0                       AS BALANCE_FORWARD
									  ,0                       AS STOCKING_AMOUNT
									  ,0                       AS STOCKING_RETURNED_AMOUNT
									  ,0                       AS STOCKING_DISCOUNT_AMOUNT
									  ,0                       AS OTHER_STOCKING_AMOUNT
									  ,0                       AS OFFSET_AMOUNT
									  ,0                       AS TAX_AMOUNT
									  ,0                       AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  PAYMENT.SUPPLIER_CD = P_STR_VENDER_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiShiharai --支払
								AND    PAYMENT.PAYMENT_UPDATE_DIVISION =
									   c_PaymentUpdDiv0 --未処理
								AND    PAYMENT.PAYMENT_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								UNION ALL
								SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD     AS VENDER_CD --支払先コード
									  ,0                       AS CLAIM_AMOUNT_FORWARD
									  ,0                       AS CREDIT_AMOUNT_FORWARD
									  ,0                       AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0                       AS BALANCE_FORWARD
									  ,0                       AS STOCKING_AMOUNT
									  ,0                       AS STOCKING_RETURNED_AMOUNT
									  ,0                       AS STOCKING_DISCOUNT_AMOUNT
									  ,0                       AS OTHER_STOCKING_AMOUNT
									  ,PAYMENT.PAYMENT_AMOUNT  AS OFFSET_AMOUNT --支払相殺金額
									  ,0                       AS TAX_AMOUNT
									  ,0                       AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  PAYMENT.SUPPLIER_CD = P_STR_VENDER_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiSousai --相殺
								AND    PAYMENT.PAYMENT_UPDATE_DIVISION =
									   c_PaymentUpdDiv0 --未処理
								AND    PAYMENT.PAYMENT_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								UNION ALL
								SELECT PAYMENT.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,PAYMENT.SUPPLIER_CD     AS VENDER_CD --支払先コード
									  ,0                       AS CLAIM_AMOUNT_FORWARD
									  ,0                       AS CREDIT_AMOUNT_FORWARD
									  ,PAYMENT.PAYMENT_AMOUNT  AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
									  ,0                       AS BALANCE_FORWARD
									  ,0                       AS STOCKING_AMOUNT
									  ,0                       AS STOCKING_RETURNED_AMOUNT
									  ,0                       AS STOCKING_DISCOUNT_AMOUNT
									  ,0                       AS OTHER_STOCKING_AMOUNT
									  ,0                       AS OFFSET_AMOUNT
									  ,0                       AS TAX_AMOUNT
									  ,0                       AS PAYABLE_AMOUNT
								FROM   PAYMENT        PAYMENT --支払トラン
									  ,CLASSIFICATION CLASSIFICATION --分類マスタ
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   PAYMENT.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  PAYMENT.SUPPLIER_CD = P_STR_VENDER_CD)
								AND    PAYMENT.PAYMENT_DATE <= p_daPayableDate
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   c_DataDivShiSonota9 --その他
								AND    PAYMENT.PAYMENT_UPDATE_DIVISION =
									   c_PaymentUpdDiv0 --未処理
								AND    PAYMENT.PAYMENT_UPDATE_DATE IS NULL
								AND    PAYMENT.APPROVAL_STATUS =
									   c_ApprovalSts3 --承認済
								AND    PAYMENT.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    PAYMENT.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    PAYMENT.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								)
						GROUP  BY ORGANIZATION_CD
								 ,VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,VENDER_CD AS VENDER_CD --支払先コード
							  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
							  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
							  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
							  ,SUM(BALANCE_FORWARD) AS BALANCE_FORWARD --差引繰越額
							  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
							  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
							  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
							  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
							  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
							  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --支払残高
						FROM   (SELECT OFFSET.ORGANIZATION_CD       AS ORGANIZATION_CD --部署コード
									  ,OFFSET.VENDER_CD             AS VENDER_CD --支払先コード
									  ,0                            AS CLAIM_AMOUNT_FORWARD
									  ,0                            AS CREDIT_AMOUNT_FORWARD
									  ,0                            AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0                            AS BALANCE_FORWARD
									  ,0                            AS STOCKING_AMOUNT
									  ,0                            AS STOCKING_RETURNED_AMOUNT
									  ,0                            AS STOCKING_DISCOUNT_AMOUNT
									  ,0                            AS OTHER_STOCKING_AMOUNT
									  ,OFFSET.PAYABLE_OFFSET_AMOUNT AS OFFSET_AMOUNT --買掛相殺金額
									  ,0                            AS TAX_AMOUNT
									  ,0                            AS PAYABLE_AMOUNT
								FROM   OFFSET_GROUP_DATA OFFSET --グループ間トラン
									  ,CLASSIFICATION    CLASSIFICATION --分類マスタ
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   OFFSET.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  OFFSET.VENDER_CD = P_STR_VENDER_CD)
								AND    OFFSET.OFFSET_DATE <= p_daPayableDate
								AND    OFFSET.DATA_TOTAL_DIVISION =
									   c_DataDivSouSousai --グループ間相殺
								AND    OFFSET.PAYMENT_UPDATE_DIVISION =
									   c_PaymentUpdDiv0 --未処理
								AND    OFFSET.PAYMENT_UPDATE_DATE IS NULL
								AND    OFFSET.PAYABLE_OFFSET_AMOUNT IS NOT NULL --買掛相殺金額
								AND    OFFSET.APPROVAL_STATUS = c_ApprovalSts3 --承認済
								AND    OFFSET.VENDER_DIVISION = c_VenderDivSI --仕入先
								AND    OFFSET.DATA_TYPE =
									   CLASSIFICATION.DATA_TYPE
								AND    OFFSET.DATA_TOTAL_DIVISION =
									   CLASSIFICATION.DATA_TOTAL_DIVISION
								AND    OFFSET.CATEGORY_DIVISION =
									   CLASSIFICATION.CATEGORY_DIVISION
								AND    CLASSIFICATION.PAYMENT_DIVISION =
									   c_PaymentDiv1 --支払対象
								)
						GROUP  BY ORGANIZATION_CD
								 ,VENDER_CD
						UNION ALL
						SELECT ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
							  ,SUPPLIER_CD AS SUPPLIER_CD --支払先コード
							  ,SUM(CLAIM_AMOUNT_FORWARD) AS CLAIM_AMOUNT_FORWARD --前回支払予定額
							  ,SUM(CREDIT_AMOUNT_FORWARD) AS CREDIT_AMOUNT_FORWARD --前回支払額
							  ,SUM(OTHER_CREDIT_AMOUNT_FORWARD) AS OTHER_CREDIT_AMOUNT_FORWARD --前回その他支払額
							  ,SUM(BALANCE_FORWARD) AS BALANCE_FORWARD --差引繰越額
							  ,SUM(STOCKING_AMOUNT) AS STOCKING_AMOUNT --今回仕入額
							  ,SUM(STOCKING_RETURNED_AMOUNT) AS STOCKING_RETURNED_AMOUNT --返品金額
							  ,SUM(STOCKING_DISCOUNT_AMOUNT) AS STOCKING_DISCOUNT_AMOUNT --値引金額
							  ,SUM(OTHER_STOCKING_AMOUNT) AS OTHER_STOCKING_AMOUNT --その他仕入金額
							  ,SUM(OFFSET_AMOUNT) AS OFFSET_AMOUNT --相殺金額
							  ,SUM(TAX_AMOUNT) AS TAX_AMOUNT --消費税額
							  ,SUM(PAYABLE_AMOUNT) AS PAYABLE_AMOUNT --支払残高
						FROM   (SELECT HEADER.ORGANIZATION_CD AS ORGANIZATION_CD --部署コード
									  ,HEADER.SUPPLIER_CD     AS SUPPLIER_CD --支払先コード
									  ,HEADER.PAYABLE_AMOUNT  AS CLAIM_AMOUNT_FORWARD --支払残高
									  ,0                      AS CREDIT_AMOUNT_FORWARD
									  ,0                      AS OTHER_CREDIT_AMOUNT_FORWARD
									  ,0                      AS BALANCE_FORWARD
									  ,0                      AS STOCKING_AMOUNT
									  ,0                      AS STOCKING_RETURNED_AMOUNT
									  ,0                      AS STOCKING_DISCOUNT_AMOUNT
									  ,0                      AS OTHER_STOCKING_AMOUNT
									  ,0                      AS OFFSET_AMOUNT
									  ,0                      AS TAX_AMOUNT
									  ,0                      AS PAYABLE_AMOUNT
								FROM   PAYMENT_HEADER HEADER --支払ヘッダー
									  ,(SELECT ORGANIZATION_CD
											  ,SUPPLIER_CD
											   --                      ,MAX(PAYABLE_DATE) MAX_DATE
											  ,PAYABLE_DATE MAX_DATE --   2009.11.17 複数月取得
										FROM   PAYMENT_HEADER
										WHERE 
										--                      PAYABLE_DATE < p_daPayableDate
										 to_char(CREDIT_SCHEDULED_DATE
												,'YYYYMM') = p_nvAccountYears -- 2009.10.16 残高算出対象を当月支払予定とする。
										GROUP  BY ORGANIZATION_CD
												 ,SUPPLIER_CD
												 ,PAYABLE_DATE --    2009.11.17 複数月取得
										) MAX_HEADER
								WHERE  ('ALL' =
									   NVL(P_STR_ORGANIZATION_CD, 'ALL') OR
									   HEADER.ORGANIZATION_CD =
									   P_STR_ORGANIZATION_CD)
								AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									  HEADER.SUPPLIER_CD = P_STR_VENDER_CD)
									  --              AND   HEADER.PAYABLE_AMOUNT <> 0                        --  2009.10.16 残高算出対象を当月支払予定とする。
								AND    HEADER.ORGANIZATION_CD =
									   MAX_HEADER.ORGANIZATION_CD
								AND    HEADER.SUPPLIER_CD =
									   MAX_HEADER.SUPPLIER_CD
								AND    HEADER.PAYABLE_DATE =
									   MAX_HEADER.MAX_DATE)
						GROUP  BY ORGANIZATION_CD
								 ,SUPPLIER_CD) TRN
					  ,(SELECT VENDER_CD
						FROM   (SELECT VENDER_CD
									  ,CASE
										   WHEN COMPANY.PAYMENT_UPDATE = 2 THEN
											DECODE(CLOSING_DAY
												  ,99
												  ,TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																		   ,'YYYYMMDD')
																   ,7
																   ,2))
												  ,CASE
													   WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																					,'YYYYMMDD')
																			,7
																			,2)) <
															CLOSING_DAY THEN
														TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																				,'YYYYMMDD')
																		,7
																		,2))
													   ELSE
														CLOSING_DAY
												   END)
										   ELSE
											DECODE(CLOSING_DATE
												  ,99
												  ,TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																		   ,'YYYYMMDD')
																   ,7
																   ,2))
												  ,CASE
													   WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																					,'YYYYMMDD')
																			,7
																			,2)) <
															CLOSING_DATE THEN
														TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(p_daPayableDate)
																				,'YYYYMMDD')
																		,7
																		,2))
													   ELSE
														CLOSING_DATE
												   END)
									   END CLOSING_DATE
								FROM   VENDER
									  ,(SELECT CLOSING_DAY
											  ,PAYMENT_UPDATE
										FROM   COMPANY
										WHERE  COMPANY_CD = P_STR_COMPANY_CD) COMPANY
								WHERE  ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR
									   VENDER_CD = P_STR_VENDER_CD)
								AND    VENDER_DIVISION = c_VenderDivSI
								AND    (PAYMENT_INVOICE_CD IS NULL OR
									  PAYMENT_INVOICE_CD = VENDER_CD))
						WHERE  CLOSING_DATE =
							   TO_NUMBER(SUBSTR(TO_CHAR(p_daPayableDate
													   ,'YYYYMMDD')
											   ,7
											   ,2))) VENDER -- 支払先.締日と画面の締日が一致する支払先のテーブル
				WHERE  TRN.VENDER_CD = VENDER.VENDER_CD
				GROUP  BY ORGANIZATION_CD
						 ,TRN.VENDER_CD
				ORDER  BY ORGANIZATION_CD
						 ,VENDER_CD)
		WHERE  ORGANIZATION_CD NOT IN
			   (SELECT ORGANIZATION_CD
				FROM   PAYMENT_HEADER
				WHERE  PAYABLE_DATE = p_daPayableDate
				GROUP  BY ORGANIZATION_CD
						 ,SUPPLIER_CD)
			  
		OR     VENDER_CD NOT IN (SELECT SUPPLIER_CD
								 FROM   PAYMENT_HEADER
								 WHERE  PAYABLE_DATE = p_daPayableDate
								 GROUP  BY ORGANIZATION_CD
										  ,SUPPLIER_CD);
BEGIN
    -- ログ出力変数初期化  
    TRNERRMAN.INIT_LOG(DEF_PROC_NAME);

    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '処理開始', P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '初期処理：実行条件取得', P_STR_INPUTOR_CD);

    msg := '実行条件＝';
    msg := msg || '部署コード：' || NVL(P_STR_ORGANIZATION_CD, '') || ',';
    msg := msg || '支払先コード：' || NVL(P_STR_VENDER_CD, '') || ',';
    msg := msg || '締日：' || P_STR_PAYABLE_DATE || ',';
    
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, msg, P_STR_INPUTOR_CD);
    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理開始', P_STR_INPUTOR_CD);

	-- 文字型をDate型へキャスト
	daPayableDate  := TO_DATE(P_STR_PAYABLE_DATE, 'YYYY/MM/DD');
	nvAccountYears := SUBSTR(P_STR_PAYABLE_DATE, 1, 6);
	-- 自社・銀行マスタ検索
	SELECT NVL(COMPANY.PRIME, 0) -- 短プラ+金利
		  ,NVL(BANK.FEE, 0) -- 振込手数料
	INTO   nPrime
		  ,nFee
	FROM   COMPANY
		  ,BANK
	WHERE  COMPANY.COMPANY_CD = P_STR_COMPANY_CD
	AND    COMPANY.BANK_MASTER_CD = BANK.BANK_MASTER_CD(+);
	--------------------------------------------------------------------------------
	--仕入データ抽出処理
	--------------------------------------------------------------------------------
	/* 仕入データ抽出 カーソル */
	FOR REC_MAIN IN curSales(daPayableDate, nvAccountYears) LOOP
		-- 支払番号取得
		nvPaymentNo := FUN_GET_PAYMENT_NO(P_STR_INPUTOR_CD);
		-- 支払残高(差引繰越額含まず)
		--      nPayableAmount := REC_MAIN.PAYABLE_AMOUNT - REC_MAIN.BALANCE_FORWARD;
		nPayableAmount := REC_MAIN.PAYABLE_AMOUNT; --支払予定額(差引繰越額元々含まず)
	
		/* マイナス額は\1として判定する */
		IF nPayableAmount < 0 THEN
			nCheckPayableAmount := 1;
		ELSE
			nCheckPayableAmount := nPayableAmount;
		END IF;
	
		-- 支払予定日取得(FUNCTION呼出)
		nvCreditScheDate := FUN_GET_PAYMENT_SCHE_DATE(REC_MAIN.VENDER_CD
													 ,TO_CHAR(daPayableDate
															 ,'YYYYMMDD')
													 ,nCheckPayableAmount);
		-- 支払分類、手形サイト取得(PROCEDURE呼出) 仕入割引日数取得追加
		PRO_GET_PAYMENT_DIV(REC_MAIN.VENDER_CD
						   ,nCheckPayableAmount
						   ,nvCreditDiv
						   ,nNoteSight
						   ,nDiscountDays);
		IF nvCreditDiv IS NULL THEN
			ROLLBACK;
			P_NUM_RET := 9;

			TRNERRMAN.LOGOUT(DEF_PROC_NAME, '対象データがありません', P_STR_INPUTOR_CD);
            		TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);

			RETURN;
		END IF;
		-- 取引先マスタ検索
		SELECT HOLIDAY_FLG -- 休日指定フラグ
			  ,TRANSFER_COMMISSION_LOAD -- 振込手数料負担
		--      ,   NVL(PURCHASE_DISCOUNT_DAYS1, 0)     -- 仕入割引日数1
		INTO   nHolidayFlg
			  ,nTraComLoad
		--      ,   nDiscountDays
		FROM   VENDER
		WHERE  VENDER_DIVISION = c_VenderDivSI
		AND    VENDER_CD = REC_MAIN.VENDER_CD;
		nStockReduction := 0;
		-- 支払分類=(7:仕入割引)の場合,支払分類は3:振込とみなし仕入割引額算出。
		IF nvCreditDiv = c_CreditDiv7 THEN
			nvCreditDiv := c_CreditDiv3;
			-- 仕入割引額算出    TRUNCからROUNDに変更。桁数はNUMBER_CHKDISIT(SONOTA)から取得する予定！
			nStockReduction := ROUND(nPayableAmount * (nPrime / 100) *
									 (nDiscountDays / 365));
		END IF;
		-- 振込手数料設定
		nTransferFee := 0;
		-- 振込手数料負担＝(1:相手先)の場合
		IF nTraComLoad = c_TraComLoad1 THEN
			-- 支払分類=(3:振込 4:郵便振替)の場合
			IF nvCreditDiv = c_CreditDiv3
			   OR nvCreditDiv = c_CreditDiv4 THEN
				nTransferFee := nFee;
			END IF;
		END IF;
		-- 支払ヘッダーデータ
		rtRecWork.PAYMENT_NO                  := nvPaymentNo; -- 支払番号
		rtRecWork.ORGANIZATION_CD             := REC_MAIN.ORGANIZATION_CD; -- 部署コード
		rtRecWork.SUPPLIER_CD                 := REC_MAIN.VENDER_CD; -- 支払先コード
		rtRecWork.PAYABLE_DATE                := daPayableDate; -- 支払締め日
		rtRecWork.CREDIT_SCHEDULED_DATE       := TO_DATE(nvCreditScheDate
														,'YYYY/MM/DD'); -- 支払予定日
		rtRecWork.CREDIT_DIVISION             := nvCreditDiv; -- 支払分類
		rtRecWork.NOTE_SIGHT                  := nNoteSight; -- 手形サイト
		rtRecWork.HOLIDAY_FLG                 := nHolidayFlg; -- 休日指定フラグ
		rtRecWork.CLAIM_AMOUNT_FORWARD        := REC_MAIN.CLAIM_AMOUNT_FORWARD; -- 前回支払予定額
		rtRecWork.CREDIT_AMOUNT_FORWARD       := REC_MAIN.CREDIT_AMOUNT_FORWARD; -- 前回支払額
		rtRecWork.OTHER_CREDIT_AMOUNT_FORWARD := REC_MAIN.OTHER_CREDIT_AMOUNT_FORWARD; -- 前回その他支払額
		rtRecWork.BALANCE_FORWARD             := REC_MAIN.BALANCE_FORWARD; -- 差引繰越額
		rtRecWork.STOCKING_AMOUNT             := REC_MAIN.STOCKING_AMOUNT; -- 今回仕入額
		rtRecWork.STOCKING_RETURNED_AMOUNT    := REC_MAIN.STOCKING_RETURNED_AMOUNT; -- 返品金額
		rtRecWork.STOCKING_DISCOUNT_AMOUNT    := REC_MAIN.STOCKING_DISCOUNT_AMOUNT; -- 値引金額
		rtRecWork.OTHER_STOCKING_AMOUNT       := REC_MAIN.OTHER_STOCKING_AMOUNT; -- その他仕入金額
		rtRecWork.OFFSET_AMOUNT               := REC_MAIN.OFFSET_AMOUNT; -- 相殺金額
		rtRecWork.TAX_AMOUNT                  := REC_MAIN.TAX_AMOUNT; -- 消費税額
		rtRecWork.PAYABLE_AMOUNT              := REC_MAIN.PAYABLE_AMOUNT; -- 支払残高
		rtRecWork.STOCK_REDUCTION             := nStockReduction; -- 仕入割引額
		rtRecWork.TRANSFER_FEE                := nTransferFee; -- 振込手数料
		rtRecWork.INPUT_DATE                  := SYSDATE; -- 登録日時
		rtRecWork.INPUTOR_CD                  := P_STR_INPUTOR_CD; -- 登録者ＩＤ
		rtRecWork.UPDATE_DATE                 := SYSDATE; -- 更新日時
		rtRecWork.UPDATOR_CD                  := P_STR_INPUTOR_CD; -- 更新者ＩＤ

		IF TRNERRMAN.LOCK_PROC(25) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払ヘッダーのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★支払ヘッダー登録
		--------------------------------------------------------------------------------
		INSERT INTO PAYMENT_HEADER VALUES rtRecWork;
		----------------------------------------------------------------------------------------------------------
		--【支払対象】【支払対象外】の各トランザクション更新処理
		----------------------------------------------------------------------------------------------------------

		IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象】購買外注オーダーファイル更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE PURCHASE_SUBCONTRACT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			  ,PAYEE_CD               =
			   ( -- 支払先コード
				SELECT DECODE(PAYMENT_INVOICE_CD
							  ,NULL
							  ,VENDER_CD
							  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
				FROM   VENDER
				WHERE  VENDER_CD = REC_MAIN.VENDER_CD
				AND    VENDER_DIVISION = c_VenderDivSI)
		WHERE  PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    ROWID IN
			   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
				 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
							   ,PURCHASE_SUBCONTRACT.VENDER_CD
							   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
							   ,PURCHASE_SUBCONTRACT.DATA_TYPE
							   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
							   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
							   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
						 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
							   ,VENDER               VENDER
						 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
								REC_MAIN.ORGANIZATION_CD
						 AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
								nvAccountYears
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
								c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
						 AND    PURCHASE_SUBCONTRACT.STATUS2 = c_Status2Sts3 --承認済
						 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
								VENDER.VENDER_CD
						 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
					   ,CLASSIFICATION CLASSIFICATION --分類マスタ
				 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
						REC_MAIN.VENDER_CD AND
						PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
						PURCHASE_SUBCONTRACT.PAYEE_CD = REC_MAIN.VENDER_CD)
				 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION
				 AND    CLASSIFICATION.PAYMENT_DIVISION = c_PaymentDiv1 --支払対象
				 );

		IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE PURCHASE_SUBCONTRACT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			  ,PAYEE_CD               =
			   ( -- 支払先コード
				SELECT DECODE(PAYMENT_INVOICE_CD
							  ,NULL
							  ,VENDER_CD
							  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
				FROM   VENDER
				WHERE  VENDER_CD = REC_MAIN.VENDER_CD
				AND    VENDER_DIVISION = c_VenderDivSI)
		WHERE  PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    ROWID IN
			   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
				 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
							   ,PURCHASE_SUBCONTRACT.VENDER_CD
							   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
							   ,PURCHASE_SUBCONTRACT.DATA_TYPE
							   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
							   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
							   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
						 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
							   ,VENDER               VENDER
						 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
								REC_MAIN.ORGANIZATION_CD
						 AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
								nvAccountYears
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
								c_PaymentUpdDiv0 --支払更新フラグ(未処理)
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL --支払締め日
						 AND    PURCHASE_SUBCONTRACT.STATUS2 = c_Status2Sts3 --承認済
						 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
								VENDER.VENDER_CD
						 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
					   ,CLASSIFICATION CLASSIFICATION --分類マスタ
				 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
						REC_MAIN.VENDER_CD AND
						PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
						PURCHASE_SUBCONTRACT.PAYEE_CD = REC_MAIN.VENDER_CD)
				 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION
				 AND    CLASSIFICATION.PAYMENT_DIVISION = c_PaymentDiv1 --支払対象
				 );

		IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象外】購買外注オーダーファイル更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE PURCHASE_SUBCONTRACT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			  ,PAYEE_CD               =
			   ( -- 支払先コード
				SELECT DECODE(PAYMENT_INVOICE_CD
							  ,NULL
							  ,VENDER_CD
							  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
				FROM   VENDER
				WHERE  VENDER_CD = REC_MAIN.VENDER_CD
				AND    VENDER_DIVISION = c_VenderDivSI)
		WHERE  PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    ROWID IN
			   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
				 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
							   ,PURCHASE_SUBCONTRACT.VENDER_CD
							   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
							   ,PURCHASE_SUBCONTRACT.DATA_TYPE
							   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
							   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
							   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
						 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
							   ,VENDER               VENDER
						 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
								REC_MAIN.ORGANIZATION_CD
						 AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
								nvAccountYears
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
								c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
						 AND    PURCHASE_SUBCONTRACT.STATUS2 = c_Status2Sts3 --承認済
						 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
								VENDER.VENDER_CD
						 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
					   ,CLASSIFICATION CLASSIFICATION --分類マスタ
				 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
						REC_MAIN.VENDER_CD AND
						PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
						PURCHASE_SUBCONTRACT.PAYEE_CD = REC_MAIN.VENDER_CD)
				 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION
				 AND    CLASSIFICATION.PAYMENT_DIVISION = c_PaymentDiv0 --支払対象外
				 );

		IF TRNERRMAN.LOCK_PROC(24) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '購買外注オーダーファイルのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE PURCHASE_SUBCONTRACT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
			  ,PAYEE_CD               =
			   ( -- 支払先コード
				SELECT DECODE(PAYMENT_INVOICE_CD
							  ,NULL
							  ,VENDER_CD
							  ,PAYMENT_INVOICE_CD) AS PAYEE_CD
				FROM   VENDER
				WHERE  VENDER_CD = REC_MAIN.VENDER_CD
				AND    VENDER_DIVISION = c_VenderDivSI)
		WHERE  PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    ROWID IN
			   (SELECT PURCHASE_SUBCONTRACT.ROW_ID
				 FROM   (SELECT PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD
							   ,PURCHASE_SUBCONTRACT.VENDER_CD
							   ,VENDER.PAYMENT_INVOICE_CD                   AS PAYEE_CD
							   ,PURCHASE_SUBCONTRACT.DATA_TYPE
							   ,PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION
							   ,PURCHASE_SUBCONTRACT.CATEGORY_DIVISION
							   ,PURCHASE_SUBCONTRACT.ROWID                  AS ROW_ID
						 FROM   PURCHASE_SUBCONTRACT PURCHASE_SUBCONTRACT --購買外注オーダーファイル
							   ,VENDER               VENDER
						 WHERE  PURCHASE_SUBCONTRACT.CHARGE_ORGANIZATION_CD =
								REC_MAIN.ORGANIZATION_CD
						 AND    PURCHASE_SUBCONTRACT.ACCOUNT_YEARS <=
								nvAccountYears
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DIVISION =
								c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
						 AND    PURCHASE_SUBCONTRACT.PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
						 AND    PURCHASE_SUBCONTRACT.STATUS2 = c_Status2Sts3 --承認済
						 AND    PURCHASE_SUBCONTRACT.VENDER_CD =
								VENDER.VENDER_CD
						 AND    VENDER_DIVISION = c_VenderDivSI) PURCHASE_SUBCONTRACT
					   ,CLASSIFICATION CLASSIFICATION --分類マスタ
				 WHERE  ((PURCHASE_SUBCONTRACT.VENDER_CD =
						REC_MAIN.VENDER_CD AND
						PURCHASE_SUBCONTRACT.PAYEE_CD IS NULL) OR
						PURCHASE_SUBCONTRACT.PAYEE_CD = REC_MAIN.VENDER_CD)
				 AND    PURCHASE_SUBCONTRACT.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    PURCHASE_SUBCONTRACT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PURCHASE_SUBCONTRACT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION
				 AND    CLASSIFICATION.PAYMENT_DIVISION = c_PaymentDiv0 --支払対象外
				 );

		IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象】入金トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE CREDIT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    CREDIT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
				 AND    CREDIT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    CREDIT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE CREDIT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    CREDIT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  CREDIT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
				 AND    CREDIT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    CREDIT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象外】入金トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE CREDIT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    CREDIT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		;

		IF TRNERRMAN.LOCK_PROC(8) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '入金トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE CREDIT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    CREDIT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		;

		IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象】支払トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE PAYMENT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
		AND    PAYMENT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
				 AND    PAYMENT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PAYMENT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE PAYMENT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
		AND    PAYMENT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  PAYMENT.DATA_TYPE = CLASSIFICATION.DATA_TYPE
				 AND    PAYMENT.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    PAYMENT.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象外】支払トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE PAYMENT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
		AND    PAYMENT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		;

		IF TRNERRMAN.LOCK_PROC(9) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE PAYMENT
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD
		AND    PAYMENT_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		;

		IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象】グループ間相殺トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE OFFSET_GROUP_DATA
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    OFFSET_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    PAYABLE_OFFSET_AMOUNT IS NOT NULL
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE OFFSET_GROUP_DATA
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv1 -- 支払対象(処理済)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_NO              = nvPaymentNo -- 支払番号
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    OFFSET_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    PAYABLE_OFFSET_AMOUNT IS NOT NULL
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
		AND    c_PaymentDiv1 =
			   ( -- 支払対象
				 SELECT PAYMENT_DIVISION
				 FROM   CLASSIFICATION
				 WHERE  OFFSET_GROUP_DATA.DATA_TYPE =
						CLASSIFICATION.DATA_TYPE
				 AND    OFFSET_GROUP_DATA.DATA_TOTAL_DIVISION =
						CLASSIFICATION.DATA_TOTAL_DIVISION
				 AND    OFFSET_GROUP_DATA.CATEGORY_DIVISION =
						CLASSIFICATION.CATEGORY_DIVISION);

		IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
             	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		--------------------------------------------------------------------------------
		--★【支払対象外】グループ間相殺トランザクション更新
		--------------------------------------------------------------------------------
		-- 買掛更新済対象
		UPDATE OFFSET_GROUP_DATA
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    OFFSET_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv1 -- 買掛更新フラグ(更新済)
		AND    PAYABLE_UPDATE_DATE IS NOT NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
		;

		IF TRNERRMAN.LOCK_PROC(10) = 0 THEN
            	    ROLLBACK;
            	    P_NUM_RET := 9;

            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, 'グループ間相殺トランザクションのテーブルロックに失敗しました', P_STR_INPUTOR_CD);
                  -- 2015/11/09 insert ログ追加 -->
     	            TRNERRMAN.LOGOUT(DEF_PROC_NAME, '他部署で更新処理実施中です。しばらくたってから再度実行下さい', P_STR_INPUTOR_CD);
                  -- <-----------------------------
            	    TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
           	    RETURN;
        	END IF;

		-- 買掛更新未処理対象
		UPDATE OFFSET_GROUP_DATA
		SET    PAYMENT_TARGET_DIVISION = c_PaymentTrgDiv9 -- 支払対象(対象外)
			  ,PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv1 -- 支払更新フラグ(更新済)
			  ,PAYMENT_UPDATE_DATE     = daPayableDate -- 支払締め日
			  ,UPDATE_DATE             = SYSDATE -- 更新日時
			  ,UPDATOR_CD              = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    OFFSET_DATE <= daPayableDate
		AND    PAYABLE_UPDATE_DIVISION = c_PayableUpdDiv0 -- 買掛更新フラグ(未処理)
		AND    PAYABLE_UPDATE_DATE IS NULL -- 買掛締め日
		AND    PAYMENT_UPDATE_DIVISION = c_PaymentUpdDiv0 -- 支払更新フラグ(未処理)
		AND    PAYMENT_UPDATE_DATE IS NULL -- 支払締め日
		AND    APPROVAL_STATUS = c_ApprovalSts3 -- 承認ステータス(承認済)
		AND    VENDER_DIVISION = c_VenderDivSI -- 仕入先
		;
		-- カウント
		nvCount := nvCount + 1;
	END LOOP;
	--対象データなしの場合
	IF nvCount = 0 THEN
		--ロールバック
		ROLLBACK;
		--戻り値設定
		P_NUM_RET := 9;

		TRNERRMAN.LOGOUT(DEF_PROC_NAME, '対象データがありません', P_STR_INPUTOR_CD);
            	TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);

		RETURN;
	END IF;
	--------------------------------------------------------------------------------
	--戻り値設定
	--------------------------------------------------------------------------------
	P_NUM_RET := 0;

	TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
		ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM, 1, 1024);
		--ロールバック
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_PAYMENTUPDATE', 'AUTO', ERR_CODE, ERR_MSG);
		--戻り値設定
		P_NUM_RET := 99;

		-- ログ出力
        	TRNERRMAN.LOGOUT(DEF_PROC_NAME, ERR_MSG, P_STR_INPUTOR_CD);
        	TRNERRMAN.LOGOUT(DEF_PROC_NAME, '支払更新処理終了', P_STR_INPUTOR_CD);
END PRO_PAYMENTUPDATE;
/
