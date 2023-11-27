CREATE OR REPLACE FUNCTION      FUN_GET_ITEM_LOT_DIVISION(PARA VARCHAR2) RETURN NUMBER IS
--■■■■■■■■■■■■■■■■■■■
--■　ロット管理区分取得
--■　TABLE:ITEM
--■■■■■■■■■■■■■■■■■■■
	LNGRC	NUMBER:=0;
	LNGC	NUMBER:=0;
BEGIN
 	SELECT COUNT(*) INTO LNGRC FROM ITEM
	WHERE
		ITEM_CD = TRIM(PARA)
	;
	IF LNGRC IS NULL OR LNGRC = 0 THEN
		RETURN 9;
	ELSE
		--1:する,2:しない
		SELECT LOT_DIVISION INTO LNGC FROM ITEM
		WHERE
			ITEM_CD = TRIM(PARA)
		;
		RETURN LNGC;
	END IF;
END;
/