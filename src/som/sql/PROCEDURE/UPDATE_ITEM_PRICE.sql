CREATE OR REPLACE PROCEDURE AP21.UPDATE_ITEM_PRICE(STANDARDDATE IN DATE, ITEMCD IN VARCHAR2, PRICE IN NUMBER, TANTOCD IN VARCHAR2, ERROR_RETURN_CD OUT NVARCHAR2, ERROR_RETURN_MSG OUT NVARCHAR2, UPDATE_CNT OUT NUMBER)
IS
	CURSOR curEstimate IS
	SELECT ESTIMATE_NO, CONSECUTIVE_NO, STANDARD_UNIT_PRICE, STANDARD_DISCOUNT, SPECIAL_DISCOUNT, UNITPRICE, ESTIMATE_VALID_DATE_FROM, ESTIMATE_VALID_DATE_TO
	FROM ESTIMATE
	WHERE ITEM_CD = ITEMCD
    AND (ESTIMATE_VALID_DATE_FROM <= TO_CHAR(TO_DATE(STANDARDDATE, 'YYYY/MM/DD'))
    AND ESTIMATE_VALID_DATE_TO >= TO_CHAR(TO_DATE(STANDARDDATE, 'YYYY/MM/DD'))
    OR ESTIMATE_VALID_DATE_FROM >= TO_CHAR(TO_DATE(STANDARDDATE, 'YYYY/MM/DD')))
    AND ESTIMATE_STATUS = 3
	;
	rtEstimate curEstimate%rowtype;
	ESTIMATENO NVARCHAR2(20);
	CONSECUTIVENO NUMBER;
    LAST_STANDARD_UNIT_PRICE NUMBER;
    LAST_STANDARD_DISCOUNT NUMBER;
    LAST_SPECIAL_DISCOUNT NUMBER;
    LAST_UNITPRICE NUMBER;
    ESTIMATE_VALID_DATE_FROM DATE;
    ESTIMATE_VALID_DATE_TO DATE;
    UNITPRICE NUMBER;
	CNT NUMBER;
BEGIN
   	ERROR_RETURN_CD := NULL;
	ERROR_RETURN_MSG := NULL;
    UPDATE_CNT := 0;
    
	OPEN curEstimate;
	LOOP
		FETCH curEstimate INTO rtEstimate;

		IF curEstimate%NOTFOUND = TRUE THEN
			EXIT;
		END IF;

		ESTIMATENO := rtEstimate.ESTIMATE_NO;
		CONSECUTIVENO := rtEstimate.CONSECUTIVE_NO;
		LAST_STANDARD_UNIT_PRICE := rtEstimate.STANDARD_UNIT_PRICE;
		LAST_STANDARD_DISCOUNT := rtEstimate.STANDARD_DISCOUNT;
		LAST_SPECIAL_DISCOUNT := rtEstimate.SPECIAL_DISCOUNT;
		LAST_UNITPRICE := rtEstimate.UNITPRICE;
        ESTIMATE_VALID_DATE_FROM := rtEstimate.ESTIMATE_VALID_DATE_FROM;
        ESTIMATE_VALID_DATE_TO := rtEstimate.ESTIMATE_VALID_DATE_TO;
        UNITPRICE := PRICE - rtEstimate.STANDARD_DISCOUNT - rtEstimate.SPECIAL_DISCOUNT;

		SELECT COUNT(*) INTO CNT FROM ESTIMATE WHERE ESTIMATE_NO = ESTIMATENO AND CONSECUTIVE_NO = CONSECUTIVENO;

		IF 0 < CNT THEN
			UPDATE ESTIMATE
			SET STANDARD_UNIT_PRICE = PRICE
			, UNITPRICE = PRICE - STANDARD_DISCOUNT - SPECIAL_DISCOUNT
			, UPDATE_DATE = SYSDATE
			, UPDATOR_CD = TANTOCD
			WHERE ESTIMATE_NO = ESTIMATENO
            AND CONSECUTIVE_NO = CONSECUTIVENO
            ;
			OUTPUT_UNITPRICE_LOG(STANDARDDATE, ESTIMATENO, ITEMCD, LAST_STANDARD_UNIT_PRICE, LAST_STANDARD_DISCOUNT, LAST_SPECIAL_DISCOUNT, LAST_UNITPRICE, ESTIMATE_VALID_DATE_FROM, ESTIMATE_VALID_DATE_TO, PRICE, UNITPRICE, '単価を更新しました。');
            UPDATE_CNT := UPDATE_CNT + 1;
		END IF;
	END LOOP;

	CLOSE curEstimate;
	COMMIT;
EXCEPTION
	WHEN OTHERS THEN
		ROLLBACK;
    	ERROR_RETURN_CD := SQLCODE;
		ERROR_RETURN_MSG := SUBSTR(SQLERRM, 1, 1024);
		OUTPUT_UNITPRICE_LOG(STANDARDDATE, ESTIMATENO, ITEMCD, LAST_STANDARD_UNIT_PRICE, LAST_STANDARD_DISCOUNT, LAST_SPECIAL_DISCOUNT, LAST_UNITPRICE, ESTIMATE_VALID_DATE_FROM, ESTIMATE_VALID_DATE_TO, PRICE, UNITPRICE, SUBSTR(SQLERRM, 1, 1024));
END UPDATE_ITEM_PRICE;
/