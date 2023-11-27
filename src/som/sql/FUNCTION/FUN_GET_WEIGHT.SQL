CREATE OR REPLACE FUNCTION      FUN_GET_WEIGHT(itemcd VARCHAR2)
RETURN number
IS
WEIGHT number;
CT number;
BEGIN
   SELECT COUNT(*) INTO CT FROM ITEM WHERE ITEM_CD = itemcd;
   IF CT = 1 THEN
	SELECT KG_OF_FRACTION_MANAGEMENT INTO WEIGHT FROM ITEM WHERE ITEM_CD = itemcd;
   ELSE
	WEIGHT := 0.0;
   END IF;
   RETURN WEIGHT;

EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		RAISE;


END;
/
