/*
 * ＦＢデータ作成 ＦＢトレーラー用SQL
 *
 * entityName=FbdataMakingTrailer
 * packageName=payment.fbdatamaking
 * methodName=getSearchTotalInfo
 *
 */
SELECT	
	8                                                   AS DATA_DIVISION,  			-- データ区分（トレーラーレコード：8固定）		
	TRIM(TO_CHAR(COUNT(*),'000000'))                    AS TRASFER_TOTAL_NUMBER,	-- 合計件数
	TRIM(TO_CHAR(SUM(PAYMENT.PAYMENT_AMOUNT),'000000000000'))	
														AS TRASFER_TOTAL_AMOUNT,	-- 合計件数(数値）
	'                                                                                                     '	
														AS DUMMY,	-- ダミー
	COUNT(*)											AS TRANSFER_TOTAL_NUMBER_N,	-- 合計件数(数値）
	SUM(PAYMENT.PAYMENT_AMOUNT)							AS TRANSFER_TOTAL_AMOUNT_N,	-- 合計件数(数値）
	PAYMENT.PAYMENT_DATE                				AS PAYMENT_DATE				-- 振込日付(YYYYMMDD)		
FROM
	VENDER,
	BANK,
	(
		SELECT	
			TO_CHAR(PAYMENT_DATE,'YYYYMMDD') AS PAYMENT_DATE, 
			SUPPLIER_CD AS SUPPLIER_CD,
			SUM(NVL(PAYMENT_AMOUNT, 0)) AS PAYMENT_AMOUNT
		FROM
			PAYMENT
		WHERE
			DATA_TYPE = 4										-- 支払
		AND	CATEGORY_DIVISION = 3								-- 振込
		AND	PAYMENT_DATE = TO_DATE(/*paymentDate*/'20090701')
		AND	APPROVAL_STATUS = 3									-- 承認済
		GROUP BY TO_CHAR(PAYMENT_DATE,'YYYYMMDD'), SUPPLIER_CD
	) PAYMENT
WHERE
	VENDER.BANK_CD = /*bankMasterCd*/'1234123'					-- 支払用銀行口座
AND	BANK.BANK_MASTER_CD = LPAD(VENDER.OTHER_BANK_CD, 7, '0')	-- 現状の0漏れ対策
AND	VENDER.VENDER_DIVISION = 'SI'
AND	VENDER.VENDER_CD = PAYMENT.SUPPLIER_CD
AND	PAYMENT.PAYMENT_AMOUNT > 0
GROUP BY
	PAYMENT.PAYMENT_DATE
