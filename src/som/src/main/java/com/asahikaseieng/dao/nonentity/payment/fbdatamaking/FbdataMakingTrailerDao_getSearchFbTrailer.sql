/*
 * ＦＢデータ作成 ＦＢトレーラー用SQL
 *
 * entityName=FbdataMakingTrailer
 * packageName=payment.fbdatamaking
 * methodName=getSearchTotalInfo
 *
 */
SELECT 	DATA_DIVISION
,		TRASFER_TOTAL_NUMBER
,		TRASFER_TOTAL_AMOUNT
,		DUMMY
,		TRANSFER_TOTAL_NUMBER_N
,		TRANSFER_TOTAL_AMOUNT_N
,		PAYMENT_DATE
,		INPUT_DATE
,		INPUTOR_CD
,		UPDATE_DATE
,		UPDATOR_CD
FROM 	FB_TRAILER
WHERE	PAYMENT_DATE = /*paymentDate*/