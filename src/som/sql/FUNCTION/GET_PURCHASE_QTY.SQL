CREATE OR REPLACE FUNCTION GET_PURCHASE_QTY(itemcd VARCHAR2,kg number,ko number) RETURN number IS
CT number;
typedivision number;
BEGIN
	SELECT COUNT(*) INTO CT FROM ITEM WHERE ITEM_CD = itemcd;
   	IF CT = 1 THEN
		SELECT type_division  INTO typedivision  FROM ITEM WHERE ITEM_CD = itemcd;
   	END IF;
	IF typedivision = 1 OR typedivision  = 3 THEN
		RETURN KG;
	END IF;
   	RETURN KO;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		RAISE;



END;
/
