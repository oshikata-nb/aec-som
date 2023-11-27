CREATE OR REPLACE FUNCTION AP21.FUNC_GET_SECTION_FROM_SALES(STR_CODE IN NVARCHAR2)
RETURN NVARCHAR2
IS
CT NUMBER;
SC NVARCHAR2(10);
VC NVARCHAR2(15);
BEGIN
	SELECT COUNT(*) INTO CT FROM SALES WHERE SALES_NO = STR_CODE;
	IF CT is not NULL or CT > 0 THEN
		SELECT VENDER_CD INTO VC FROM SALES WHERE SALES_NO = STR_CODE;
		SC := func_get_section_for_shipping(VC,'TS');
		RETURN SC;
	END IF;
	RETURN '';
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		return '';
	
END;
/