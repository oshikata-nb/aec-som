/*
 * 買掛元帳詳細一覧（下段）用SQL
 *
 * entityName=ApLedgerDetail
 * packageName=debt.apledger
 * methodName=getSearchDetailTable
 *
 */
/*IF (targetKbn == "0" || targetKbn == "2")*/
SELECT TRAN_DIVI
,      TRAN_DATE
,      CATEGORY_NAME
,      SLIP_NO
,      ROW_NO
,      ITEM_NAME
,      STOCKING
,      PAYMENT
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
	,      STOCKING
	,      PAYMENT
	,      SUM(BALANCE) OVER(ORDER BY ORDER_FLG,TRAN_DATE,TRAN_DIVI,SLIP_NO,ROW_NO) AS BALANCE
	FROM
		(
		SELECT  '0'                 AS ORDER_FLG		--順番
		,       '0' 				AS TRAN_DIVI		--区分
		,       null 				AS TRAN_DATE		--日付
		,       null 				AS CATEGORY_NAME	--分類
		,       null 				AS SLIP_NO			--伝票番号
		,       null 				AS ROW_NO			--行番号
		,       null 				AS ITEM_NAME		--品目／摘要／科目
		,       null 				AS STOCKING			--仕入金額
		,       null 				AS PAYMENT			--支払金額
		,       PYH.BALANCE_FORWARD AS BALANCE			--残高
			
		FROM PAYABLE_HEADER PYH
		
		WHERE PYH.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		
		UNION ALL
		
		-- 仕入データ
		SELECT 	'1'                 AS ORDER_FLG		--順番
		,       '3'					AS TRAN_DIVI		--区分
		,       TO_CHAR(PURCHASE.STOCKING_DATE,'YYYY/MM/DD')
									AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME	AS CATEGORY_NAME	--分類
		,		PURCHASE.SLIP_NO	AS SLIP_NO			--伝票番号
		,		PURCHASE.ROW_NO		AS ROW_NO			--行番号
		,		ITM.ITEM_NAME		AS ITEM_NAME		--品目／摘要／科目
		,		CASE 
					WHEN PURCHASE.STOCKING_AMOUNT < 0 THEN 0
					ELSE 
						CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
							THEN (PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * (-1)
							ELSE PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
						END
				END
					     			AS STOCKING			--仕入金額
		,		CASE 
					WHEN PURCHASE.STOCKING_AMOUNT > 0 THEN 0
					ELSE 
						CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
							THEN PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
							ELSE ((PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * -1)
						END
				END
									AS PAYMENT			--支払金額

		,		CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
					THEN (PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * (-1)
					ELSE PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
				END	AS BALANCE							--残高



		FROM PURCHASE_SUBCONTRACT PURCHASE
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '3'
				AND CLS.CATEGORY_DIVISION = PURCHASE.CATEGORY_DIVISION
				LEFT JOIN
				ITEM ITM
				ON	ITM.ITEM_CD = PURCHASE.ITEM_CD
		
		WHERE PURCHASE.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   PURCHASE.PAYEE_CD = /*condition.srhVenderCd*/'%'
		
		UNION ALL
		
		-- 入金データ
		SELECT 	'1'                 	AS ORDER_FLG		--順番
		,       '2'						AS TRAN_DIVI		--区分
		,       TO_CHAR(CRT.CREDIT_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		CRT.CREDIT_NO			AS SLIP_NO			--伝票番号
		,		CRT.ROW_NO				AS ROW_NO			--行番号
		,		CRT.REMARK				AS ITEM_NAME		--品目／摘要／科目
		,		CASE WHEN CRT.CREDIT_AMOUNT > 0 THEN 0
				ELSE (CRT.CREDIT_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE WHEN CRT.CREDIT_AMOUNT < 0 THEN 0
				ELSE CRT.CREDIT_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(CRT.CREDIT_AMOUNT * -1)
										AS BALANCE			--残高
		FROM CREDIT CRT
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '2'
				AND CLS.CATEGORY_DIVISION = CRT.CATEGORY_DIVISION
		
		WHERE CRT.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   CRT.VENDER_CD = /*condition.srhVenderCd*/'%'
		
		UNION ALL
		
		-- 支払データ
		SELECT 	'1'                 	AS ORDER_FLG		--順番
		,       '4'						AS TRAN_DIVI		--区分
		,       TO_CHAR(PYT.PAYMENT_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		PYT.SLIP_NO				AS SLIP_NO			--伝票番号
		,		PYT.ROW_NO				AS ROW_NO			--行番号
		,		PYT.SUMMARY				AS ITEM_NAME		--品目／摘要／科目
		,		CASE 
					WHEN PYT.PAYMENT_AMOUNT > 0 THEN 0
					ELSE (PYT.PAYMENT_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE
					WHEN PYT.PAYMENT_AMOUNT < 0 THEN 0
					ELSE PYT.PAYMENT_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(PYT.PAYMENT_AMOUNT * -1)
										AS BALANCE			--残高
		FROM PAYMENT PYT
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '4'
				AND CLS.CATEGORY_DIVISION = PYT.CATEGORY_DIVISION
		
		WHERE PYT.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   PYT.SUPPLIER_CD = /*condition.srhVenderCd*/'%'
		
		
		UNION ALL
		
		-- 相殺データ
		SELECT 	'1'          	        AS ORDER_FLG		--順番
		,       '5'						AS TRAN_DIVI		--区分
		,       TO_CHAR(OGD.OFFSET_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		OGD.OFFSET_NO			AS SLIP_NO			--伝票番号
		,		1						AS ROW_NO			--行番号
		,		OGH.SUMMARY				AS ITEM_NAME		--品目／摘要／科目
		,		CASE 
					WHEN OGD.PAYABLE_OFFSET_AMOUNT > 0 THEN 0
					ELSE (OGD.PAYABLE_OFFSET_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE
					WHEN OGD.PAYABLE_OFFSET_AMOUNT < 0 THEN 0
					ELSE OGD.PAYABLE_OFFSET_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(OGD.PAYABLE_OFFSET_AMOUNT * -1)
										AS BALANCE			--残高
		FROM OFFSET_GROUP_DATA OGD
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '5'
				AND CLS.CATEGORY_DIVISION = OGD.CATEGORY_DIVISION
				LEFT JOIN
				OFFSET_GROUP_HEADER OGH
				ON	OGD.OFFSET_NO =  OGH.OFFSET_NO

		
		WHERE OGD.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   OGD.VENDER_CD = /*condition.srhVenderCd*/'%'
		
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
,      ROW_NO
,      ITEM_NAME
,      STOCKING
,      PAYMENT
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
	,      STOCKING
	,      PAYMENT
	,      SUM(BALANCE) OVER(ORDER BY ORDER_FLG,TRAN_DATE,TRAN_DIVI,SLIP_NO,ROW_NO) AS BALANCE
	FROM
		(
		SELECT  '0'                 AS ORDER_FLG		--順番
		,       '0' 				AS TRAN_DIVI		--区分
		,       null 				AS TRAN_DATE		--日付
		,       null 				AS CATEGORY_NAME	--分類
		,       null 				AS SLIP_NO			--伝票番号
		,       null 				AS ROW_NO			--行番号
		,       null 				AS ITEM_NAME		--品目／摘要／科目
		,       null 				AS STOCKING			--仕入金額
		,       null 				AS PAYMENT			--支払金額
		,       PYH.BALANCE_FORWARD AS BALANCE			--残高
			
		FROM TEMPORARY_PAYABLE_HEADER PYH
		
		WHERE PYH.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		
		UNION ALL
		
		-- 仕入データ
		SELECT 	'1'                 AS ORDER_FLG		--順番
		,       '3'					AS TRAN_DIVI		--区分
		,       TO_CHAR(PURCHASE.STOCKING_DATE,'YYYY/MM/DD')
									AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME	AS CATEGORY_NAME	--分類
		,		PURCHASE.SLIP_NO	AS SLIP_NO			--伝票番号
		,		PURCHASE.ROW_NO		AS ROW_NO			--行番号
		,		ITM.ITEM_NAME		AS ITEM_NAME		--品目／摘要／科目
		,		CASE 
					WHEN PURCHASE.STOCKING_AMOUNT < 0 THEN 0
					ELSE 
						CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
							THEN (PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * (-1)
							ELSE PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
						END
				END
				
					     			AS STOCKING			--仕入金額
		,		CASE 
					WHEN PURCHASE.STOCKING_AMOUNT > 0 THEN 0
					ELSE 
						CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
							THEN PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
							ELSE ((PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * -1)
						END
				END
									AS PAYMENT			--支払金額

		,		CASE WHEN CLS.DATA_TOTAL_DIVISION IN (2, 3)
					THEN (PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT) * (-1)
					ELSE PURCHASE.STOCKING_AMOUNT + PURCHASE.TAX_AMOUNT
				END	AS BALANCE							--残高

		FROM TMPPAY_PURCHASE_SUBCONTRACT PURCHASE
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '3'
				AND CLS.CATEGORY_DIVISION = PURCHASE.CATEGORY_DIVISION
				LEFT JOIN
				ITEM ITM
				ON	ITM.ITEM_CD = PURCHASE.ITEM_CD
		
		WHERE PURCHASE.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   PURCHASE.PAYEE_CD = /*condition.srhVenderCd*/'%'
		
		UNION ALL
		
		-- 入金データ
		SELECT 	'1'                 	AS ORDER_FLG		--順番
		,       '2'						AS TRAN_DIVI		--区分
		,       TO_CHAR(CRT.CREDIT_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		CRT.CREDIT_NO			AS SLIP_NO			--伝票番号
		,		CRT.ROW_NO				AS ROW_NO			--行番号
		,		CRT.REMARK				AS ITEM_NAME		--品目／摘要／科目
		,		CASE WHEN CRT.CREDIT_AMOUNT > 0 THEN 0
				ELSE (CRT.CREDIT_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE WHEN CRT.CREDIT_AMOUNT < 0 THEN 0
				ELSE CRT.CREDIT_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(CRT.CREDIT_AMOUNT * -1)
										AS BALANCE			--残高
		FROM TEMPORARY_PAYABLE_CREDIT CRT
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '2'
				AND CLS.CATEGORY_DIVISION = CRT.CATEGORY_DIVISION
		
		WHERE CRT.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   CRT.VENDER_CD = /*condition.srhVenderCd*/'%'
		
		UNION ALL
		
		-- 支払データ
		SELECT 	'1'                 	AS ORDER_FLG		--順番
		,       '4'						AS TRAN_DIVI		--区分
		,       TO_CHAR(PYT.PAYMENT_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		PYT.SLIP_NO				AS SLIP_NO			--伝票番号
		,		PYT.ROW_NO				AS ROW_NO			--行番号
		,		PYT.SUMMARY				AS ITEM_NAME		--品目／摘要／科目
		,		CASE 
					WHEN PYT.PAYMENT_AMOUNT > 0 THEN 0
					ELSE (PYT.PAYMENT_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE
					WHEN PYT.PAYMENT_AMOUNT < 0 THEN 0
					ELSE PYT.PAYMENT_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(PYT.PAYMENT_AMOUNT * -1)
										AS BALANCE			--残高
		FROM TEMPORARY_PAYABLE_PAYMENT PYT
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '4'
				AND CLS.CATEGORY_DIVISION = PYT.CATEGORY_DIVISION
		
		WHERE PYT.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   PYT.SUPPLIER_CD = /*condition.srhVenderCd*/'%'
		
		
		UNION ALL
		
		-- 相殺データ
		SELECT 	'1'          	        AS ORDER_FLG		--順番
		,       '5'						AS TRAN_DIVI		--区分
		,       TO_CHAR(OGD.OFFSET_DATE,'YYYY/MM/DD')
										AS TRAN_DATE		--日付
		,		CLS.CATEGORY_NAME		AS CATEGORY_NAME	--分類
		,		OGD.OFFSET_NO			AS SLIP_NO			--伝票番号
		,		1						AS ROW_NO			--行番号
		,		OGH.SUMMARY				AS ITEM_NAME		--品目／摘要／科目

		,		CASE 
					WHEN OGD.PAYABLE_OFFSET_AMOUNT > 0 THEN 0
					ELSE (OGD.PAYABLE_OFFSET_AMOUNT * -1)
				END
										AS STOCKING			--仕入金額
		,		CASE
					WHEN OGD.PAYABLE_OFFSET_AMOUNT < 0 THEN 0
					ELSE OGD.PAYABLE_OFFSET_AMOUNT
				END
										AS PAYMENT			--支払金額
		,		(OGD.PAYABLE_OFFSET_AMOUNT * -1)
										AS BALANCE			--残高
		FROM TMPPAY_OFFSET_GROUP_DATA OGD
				LEFT JOIN
				CLASSIFICATION CLS
				ON	CLS.DATA_TYPE = '5'
				AND CLS.CATEGORY_DIVISION = OGD.CATEGORY_DIVISION
				LEFT JOIN
				OFFSET_GROUP_HEADER OGH
				ON	OGD.OFFSET_NO =  OGH.OFFSET_NO
		
		WHERE OGD.PAYABLE_NO = /*condition.srhPayableNo*/'%'
		AND   OGD.VENDER_CD = /*condition.srhVenderCd*/'%'
		
		)
	)

WHERE ORDER_FLG != 0

ORDER BY TRAN_DATE
,        TRAN_DIVI
,        SLIP_NO
,        ROW_NO
/*END*/
