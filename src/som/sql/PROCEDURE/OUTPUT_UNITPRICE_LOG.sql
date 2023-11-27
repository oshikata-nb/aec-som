CREATE OR REPLACE PROCEDURE AP21.OUTPUT_UNITPRICE_LOG
(
	I_STANDARD_DATE	IN	DATE
	,I_ESTIMATE_NO	IN	NVARCHAR2
    ,I_ITEM_CD	IN	NVARCHAR2
    ,I_LAST_STANDARD_UNIT_PRICE	IN	NUMBER
    ,I_LAST_STANDARD_DISCOUNT	IN	NUMBER
    ,I_LAST_SPECIAL_DISCOUNT	IN	NUMBER
    ,I_LAST_UNITPRICE	IN	NUMBER
    ,I_ESTIMATE_VALID_DATE_FROM	IN	DATE
    ,I_ESTIMATE_VALID_DATE_TO	IN	DATE
    ,I_STANDARD_UNIT_PRICE	IN	NUMBER
    ,I_UNITPRICE	IN	NUMBER
    ,I_MESSAGE	IN	NVARCHAR2
)
IS
/*------------------------------------------------------------------------------------------------------*/
/*	種別	:	PROCEDURE																				*/
/*	名称	:	OUTPUT_UNITPRICE_LOG																	*/
/*	処理内容:	見積単価更新用ログ出力処理																*/
/*	引数	:	I_STANDARD_DATE	変更開始基準日															*/
/*	引数	:	I_ESTIMATE_NO	見積番号																*/
/*	引数	:	I_ITEM_CD	品目コード																	*/
/*	引数	:	I_LAST_STANDARD_UNIT_PRICE	更新前標準単価												*/
/*	引数	:	I_LAST_STANDARD_DISCOUNT	更新前標準値引												*/
/*	引数	:	I_LAST_SPECIAL_DISCOUNT	更新前特別値引													*/
/*	引数	:	I_LAST_UNITPRICE	更新前単価															*/
/*	引数	:	I_ESTIMATE_VALID_DATE_FROM	開始見積有効期限											*/
/*	引数	:	I_ESTIMATE_VALID_DATE_TO	終了見積有効期限											*/
/*	引数	:	I_STANDARD_UNIT_PRICE	更新後標準単価													*/
/*	引数	:	I_UNITPRICE	更新後単価																	*/
/*																										*/
/*	VERNO.	:	1.00																					*/
/*(history)																								*/
/*	date		ver		name				comments													*/
/*	----------	------	------------------	------------------------------------------------------------*/
/*	2009.07.21	1.00	NANBA				新規作成													*/
/*	2009.08.20	1.01	NANBA				変更開始基準日追加											*/
/*------------------------------------------------------------------------------------------------------*/
BEGIN
	INSERT INTO UNITPRICE_LOG
		VALUES(
		SYSDATE,
        I_STANDARD_DATE,
        I_ESTIMATE_NO,
        I_ITEM_CD,
        I_LAST_STANDARD_UNIT_PRICE,
        I_LAST_STANDARD_DISCOUNT,
        I_LAST_SPECIAL_DISCOUNT,
        I_LAST_UNITPRICE,
        I_ESTIMATE_VALID_DATE_FROM,
        I_ESTIMATE_VALID_DATE_TO,
        I_STANDARD_UNIT_PRICE,
        I_LAST_STANDARD_DISCOUNT,
        I_LAST_SPECIAL_DISCOUNT,
        I_UNITPRICE,
        I_MESSAGE
	);
END;
/
