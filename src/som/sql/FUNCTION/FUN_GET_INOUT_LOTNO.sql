CREATE OR REPLACE FUNCTION      FUN_GET_INOUT_LOTNO(PARA1 VARCHAR2,PARA2 NUMBER) RETURN VARCHAR IS
--■■■■■■■■■■■■■■■■■■■
--■　受払ソースLOT番号取得
--■　TABLE:INOUT_SOURCE
--■■■■■■■■■■■■■■■■■■■
	LNGRC	NUMBER := 0;
	STRLOT	VARCHAR2(255) := '';
BEGIN
 	SELECT COUNT(*) INTO LNGRC FROM INOUT_SOURCE
	WHERE
		ODER_NO          = PARA1
		AND ODER_LINE_NO = PARA2
	;
	IF LNGRC IS NULL OR LNGRC = 0 THEN
		RETURN '';
	ELSE
		--
		SELECT LOT_NO INTO STRLOT FROM INOUT_SOURCE
		WHERE
			ODER_NO          = PARA1
			AND ODER_LINE_NO = PARA2
		;
		RETURN STRLOT;
	END IF;
END;
/
