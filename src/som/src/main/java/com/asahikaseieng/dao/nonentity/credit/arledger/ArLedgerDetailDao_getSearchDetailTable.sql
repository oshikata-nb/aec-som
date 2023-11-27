/*
 * 売掛元帳詳細一覧（下段）用SQL
 *
 * entityName=ArBalanceDetail
 * packageName=credit.arledger
 * methodName=getSearchDetailTable
 *
 */
/*IF (targetKbn == "0" || targetKbn == "2")*/

	SELECT TRAN_DIVI
	,      TRAN_DATE
	,      CATEGORY_NAME
	,      SLIP_NO
	,      ROWNUM		AS ROW_NO
	,      ITEM_NAME
	,      SALES
	,      CREDIT
	,      BALANCE
	FROM
		(
		SELECT ORDER_FLG
		,      TRAN_DIVI
		,      TRAN_DATE
		,      CATEGORY_NAME
		,      SLIP_NO
		,      ROW_NO
		,      ITEM_NAME
		,      SALES
		,      CREDIT
		,      SUM(BALANCE) OVER(ORDER BY ORDER_FLG,TRAN_DATE,TRAN_DIVI,SLIP_NO,ROW_NO) AS BALANCE
		FROM
			(
			SELECT  '0'                 AS  ORDER_FLG		--順番
			,       '0' 				AS	TRAN_DIVI		--区分
			,       null 				AS 	TRAN_DATE		--日付
			,       null 				AS 	CATEGORY_NAME	--分類
			,       null 				AS 	SLIP_NO			--伝票番号
			,       null 				AS 	ROW_NO			--行番号
			,       null 				AS 	ITEM_NAME		--品目／摘要／科目
			,       null 				AS 	SALES			--売上金額
			,       null 				AS 	CREDIT			--入金金額
			,       DPH.BALANCE_FORWARD AS 	BALANCE			--残高
				
			FROM DEPOSIT_HEADER DPH
			
			WHERE DPH.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			
			UNION ALL
			
			-- 売上データ
			SELECT 	'1'                 AS  ORDER_FLG		--順番
			,       '1'					AS  TRAN_DIVI		--区分
			,       TO_CHAR(SLS.SALES_DATE,'YYYY/MM/DD')
										AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME	AS 	CATEGORY_NAME	--分類
			,		SLS.SALES_NO		AS 	SLIP_NO			--売上番号
			,		1					AS 	ROW_NO			--行番号
			,		ITM.ITEM_NAME		AS 	ITEM_NAME		--品目／摘要／科目
			,		CASE 
						WHEN SLS.SALES_AMOUNT < 0 THEN 0
						ELSE 
							SLS.SALES_AMOUNT + SLS.TAX_AMOUNT
					END
						     			AS 	SALES			--売上金額
			,		CASE 
						WHEN SLS.SALES_AMOUNT > 0 THEN 0
						ELSE 
							((SLS.SALES_AMOUNT + SLS.TAX_AMOUNT) * -1)
					END
										AS 	CREDIT			--入金金額
			,		SLS.SALES_AMOUNT + SLS.TAX_AMOUNT	AS 	BALANCE			--残高
			FROM SALES SLS
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '1'
					AND CLS.CATEGORY_DIVISION = SLS.CATEGORY_DIVISION
					LEFT JOIN
					ITEM ITM
					ON	ITM.ITEM_CD = SLS.ITEM_CD
			
			WHERE SLS.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   SLS.INVOICE_CD = /*condition.srhVenderCd*/'TS0002'
			
			UNION ALL
			
			-- 入金データ
			SELECT 	'1'                 	AS  ORDER_FLG		--順番
			,       '2'						AS TRAN_DIVI		--区分
			,       TO_CHAR(CRT.CREDIT_DATE,'YYYY/MM/DD')
											AS	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		CRT.CREDIT_NO			AS 	SLIP_NO			--伝票番号
			,		CRT.ROW_NO				AS 	ROW_NO			--行番号
			,		CRT.REMARK				AS 	ITEM_NAME		--品目／摘要／科目

			,		CASE WHEN CRT.CREDIT_AMOUNT > 0 THEN 0
					ELSE (CRT.CREDIT_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE WHEN CRT.CREDIT_AMOUNT < 0 THEN 0
					ELSE CRT.CREDIT_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(CRT.CREDIT_AMOUNT * -1)						AS 	BALANCE			--残高
			FROM CREDIT CRT
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '2'
					AND CLS.CATEGORY_DIVISION = CRT.CATEGORY_DIVISION
			
			WHERE CRT.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   CRT.VENDER_CD = /*condition.srhVenderCd*/'TS0002'
			
			UNION ALL
			
			-- 支払データ
			SELECT 	'1'                 	AS  ORDER_FLG		--順番
			,       '4'						AS TRAN_DIVI		--区分
			,       TO_CHAR(PYT.PAYMENT_DATE,'YYYY/MM/DD')
											AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		PYT.SLIP_NO				AS 	SLIP_NO			--伝票番号
			,		PYT.ROW_NO				AS 	ROW_NO			--行番号
			,		PYT.SUMMARY				AS 	ITEM_NAME		--品目／摘要／科目
			,		CASE 
						WHEN PYT.PAYMENT_AMOUNT > 0 THEN 0
						ELSE (PYT.PAYMENT_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE
						WHEN PYT.PAYMENT_AMOUNT < 0 THEN 0
						ELSE PYT.PAYMENT_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(PYT.PAYMENT_AMOUNT * -1)
											AS 	BALANCE			--残高
			FROM PAYMENT PYT
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '4'
					AND CLS.CATEGORY_DIVISION = PYT.CATEGORY_DIVISION
			
			WHERE PYT.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   PYT.SUPPLIER_CD = /*condition.srhVenderCd*/'TS0002'
			
			
			UNION ALL
			
			-- 相殺データ
			SELECT 	'1'          	        AS  ORDER_FLG  　   --順番
			,       '5'						AS  TRAN_DIVI		--区分
			,       TO_CHAR(OGD.OFFSET_DATE,'YYYY/MM/DD')
											AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		OGD.OFFSET_NO			AS 	SLIP_NO			--伝票番号
			,		1						AS 	ROW_NO			--行番号
			,		OGH.SUMMARY				AS 	ITEM_NAME		--品目／摘要／科目
			,		CASE 
						WHEN OGD.DEPOSIT_OFFSET_AMOUNT > 0 THEN 0
						ELSE (OGD.DEPOSIT_OFFSET_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE
						WHEN OGD.DEPOSIT_OFFSET_AMOUNT < 0 THEN 0
						ELSE OGD.DEPOSIT_OFFSET_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(OGD.DEPOSIT_OFFSET_AMOUNT * -1)
											AS 	BALANCE			--残高
			FROM OFFSET_GROUP_DATA OGD
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '5'
					AND CLS.CATEGORY_DIVISION = OGD.CATEGORY_DIVISION
					LEFT JOIN
					OFFSET_GROUP_HEADER OGH
					ON	OGD.OFFSET_NO =  OGH.OFFSET_NO
			
			WHERE OGD.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   OGD.VENDER_CD = /*condition.srhVenderCd*/'TS0002'
			
			
			)
	
		)
	
	WHERE ORDER_FLG != 0
	
	/*IF (targetKbn == "0")*/
		ORDER BY TRAN_DATE
		,        TRAN_DIVI
		,        SLIP_NO
		,        ROW_NO
	/*END*/

/*END*/

/*IF (targetKbn == "2")*/
	UNION
/*END*/

/*IF (targetKbn == "1" || targetKbn == "2")*/

	SELECT TRAN_DIVI
	,      TRAN_DATE
	,      CATEGORY_NAME
	,      SLIP_NO
	,      ROWNUM		AS ROW_NO
	,      ITEM_NAME
	,      SALES
	,      CREDIT
	,      BALANCE
	FROM
		(
		SELECT ORDER_FLG
		,      TRAN_DIVI
		,      TRAN_DATE
		,      CATEGORY_NAME
		,      SLIP_NO
		,      ROW_NO
		,      ITEM_NAME
		,      SALES
		,      CREDIT
		,      SUM(BALANCE) OVER(ORDER BY ORDER_FLG,TRAN_DATE,TRAN_DIVI,SLIP_NO,ROW_NO) AS BALANCE
		FROM
			(
			SELECT  '0'                  AS     ORDER_FLG		--順番
			,       '0' 				 AS	    TRAN_DIVI		--区分
			,       null 				 AS 	TRAN_DATE		--日付
			,       null 				 AS 	CATEGORY_NAME	--分類
			,       null 				 AS 	SLIP_NO			--伝票番号
			,       null 				 AS 	ROW_NO			--行番号
			,       null 				 AS 	ITEM_NAME		--品目／摘要／科目
			,       null 				 AS 	SALES			--売上金額
			,       null 				 AS 	CREDIT			--入金金額
			,       TDPH.BALANCE_FORWARD AS 	BALANCE			--残高
				
			FROM TEMPORARY_DEPOSIT_HEADER TDPH
			
			WHERE TDPH.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			
			UNION ALL
			
			-- 売上データ
			SELECT 	'1'                 AS  ORDER_FLG		--順番
			,       '1'					AS  TRAN_DIVI		--区分
			,       TO_CHAR(TSLS.SALES_DATE,'YYYY/MM/DD')
										AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME	AS 	CATEGORY_NAME	--分類
			,		TSLS.SALES_NO		AS 	SLIP_NO			--売上番号
			,		1					AS 	ROW_NO			--行番号
			,		ITM.ITEM_NAME		AS 	ITEM_NAME		--品目／摘要／科目
			,		CASE 
						WHEN TSLS.SALES_AMOUNT < 0 THEN 0
						ELSE 
							TSLS.SALES_AMOUNT + TSLS.TAX_AMOUNT
					END
						     			AS 	SALES			--売上金額
			,		CASE 
						WHEN TSLS.SALES_AMOUNT > 0 THEN 0
						ELSE 
							((TSLS.SALES_AMOUNT + TSLS.TAX_AMOUNT) * -1)
					END
										AS 	CREDIT			--入金金額
			,		TSLS.SALES_AMOUNT + TSLS.TAX_AMOUNT	AS 	BALANCE			--残高
			FROM TEMPORARY_DEPOSIT_SALES TSLS
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '1'
					AND CLS.CATEGORY_DIVISION = TSLS.CATEGORY_DIVISION
					LEFT JOIN
					ITEM ITM
					ON	ITM.ITEM_CD = TSLS.ITEM_CD
			
			WHERE TSLS.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   TSLS.INVOICE_CD = /*condition.srhVenderCd*/'TS0002'
			
			UNION ALL
			
			-- 入金データ
			SELECT 	'1'                 	AS  ORDER_FLG		--順番
			,       '2'						AS TRAN_DIVI		--区分
			,       TO_CHAR(TCRT.CREDIT_DATE,'YYYY/MM/DD')
											AS	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		TCRT.CREDIT_NO			AS 	SLIP_NO			--伝票番号
			,		TCRT.ROW_NO				AS 	ROW_NO			--行番号
			,		TCRT.REMARK				AS	ITEM_NAME		--品目／摘要／科目

			,		CASE WHEN TCRT.CREDIT_AMOUNT > 0 THEN 0
					ELSE (TCRT.CREDIT_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE WHEN TCRT.CREDIT_AMOUNT < 0 THEN 0
					ELSE TCRT.CREDIT_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(TCRT.CREDIT_AMOUNT * -1)						AS 	BALANCE			--残高
			FROM TEMPORARY_DEPOSIT_CREDIT TCRT
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '2'
					AND CLS.CATEGORY_DIVISION = TCRT.CATEGORY_DIVISION
			
			WHERE TCRT.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   TCRT.VENDER_CD = /*condition.srhVenderCd*/'TS0002'
			
			UNION ALL
			
			-- 支払データ
			SELECT 	'1'                 	AS  ORDER_FLG		--順番
			,       '4'						AS TRAN_DIVI		--区分
			,       TO_CHAR(TPYT.PAYMENT_DATE,'YYYY/MM/DD')
											AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		TPYT.SLIP_NO			AS 	SLIP_NO			--伝票番号
			,		TPYT.ROW_NO				AS 	ROW_NO			--行番号
			,		TPYT.SUMMARY			AS	ITEM_NAME		--品目／摘要／科目
			,		CASE 
						WHEN TPYT.PAYMENT_AMOUNT > 0 THEN 0
						ELSE (TPYT.PAYMENT_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE
						WHEN TPYT.PAYMENT_AMOUNT < 0 THEN 0
						ELSE TPYT.PAYMENT_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(TPYT.PAYMENT_AMOUNT * -1)
											AS 	BALANCE			--残高
			FROM TEMPORARY_DEPOSIT_PAYMENT TPYT
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '4'
					AND CLS.CATEGORY_DIVISION = TPYT.CATEGORY_DIVISION
			
			WHERE TPYT.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   TPYT.SUPPLIER_CD = /*condition.srhVenderCd*/'TS0002'
			
			
			UNION ALL
			
			-- 相殺データ
			SELECT 	'1'          	        AS  ORDER_FLG  　   --順番
			,       '5'						AS  TRAN_DIVI		--区分
			,       TO_CHAR(TOGD.OFFSET_DATE,'YYYY/MM/DD')
											AS 	TRAN_DATE		--日付
			,		CLS.CATEGORY_NAME		AS 	CATEGORY_NAME	--分類
			,		TOGD.OFFSET_NO			AS 	SLIP_NO			--伝票番号
			,		1						AS 	ROW_NO			--行番号
			,		OGH.SUMMARY				AS	ITEM_NAME		--品目／摘要／科目

			,		CASE 
						WHEN TOGD.DEPOSIT_OFFSET_AMOUNT > 0 THEN 0
						ELSE (TOGD.DEPOSIT_OFFSET_AMOUNT * -1)
					END
											AS 	SALES			--売上金額
			,		CASE
						WHEN TOGD.DEPOSIT_OFFSET_AMOUNT < 0 THEN 0
						ELSE TOGD.DEPOSIT_OFFSET_AMOUNT
					END
											AS 	CREDIT			--入金金額
			,		(TOGD.DEPOSIT_OFFSET_AMOUNT * -1)
											AS 	BALANCE			--残高
			FROM TMPDEPT_OFFSET_GROUP_DATA TOGD
					LEFT JOIN
					CLASSIFICATION CLS
					ON	CLS.DATA_TYPE = '5'
					AND CLS.CATEGORY_DIVISION = TOGD.CATEGORY_DIVISION
					LEFT JOIN
					OFFSET_GROUP_HEADER OGH
					ON	TOGD.OFFSET_NO =  OGH.OFFSET_NO

			
			WHERE TOGD.DEPOSIT_NO = /*condition.srhDepositNo*/'H_URI00004'
			AND   TOGD.VENDER_CD = /*condition.srhVenderCd*/'TS0002'
			
			
			)
	
		)
	
	WHERE ORDER_FLG != 0
	
	ORDER BY TRAN_DATE
	,        TRAN_DIVI
	,        SLIP_NO
	,        ROW_NO

/*END*/
