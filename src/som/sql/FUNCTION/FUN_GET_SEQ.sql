CREATE OR REPLACE FUNCTION      FUN_GET_SEQ(PARA VARCHAR2) RETURN VARCHAR2 IS
--■■■■■■■■■■■■■■■■■■■
--■　受払履歴SEQ取得
--■　TABLE:
--■■■■■■■■■■■■■■■■■■■
	STR_NO VARCHAR2(255):='';
	STR_ERRORCODE VARCHAR2(255):='';
	STR_ERRORNAME VARCHAR2(255):='';
	STR_CONST VARCHAR2(255):='000000000000000000';
	STR_KBN VARCHAR2(255):='';
BEGIN
	CASE PARA
	WHEN 'INOUT_RECORD' THEN
		STR_KBN := 'IR';
		--シーケンス取得
		SELECT SEQ_INOUT_RECORD.NEXTVAL INTO STR_NO FROM DUAL;
	WHEN 'INOUT_SOURCE' THEN
		STR_KBN := 'IS';
		--シーケンス取得
		SELECT SEQ_INOUT_SOURCE.NEXTVAL INTO STR_NO FROM DUAL;
	WHEN 'ZAIKO' THEN
		STR_KBN := 'ZA';
		--シーケンス取得
		SELECT SEQ_ZAIKO.NEXTVAL INTO STR_NO FROM DUAL;
	END CASE;
	--
	STR_NO := TRIM(TO_CHAR(STR_NO,STR_CONST));
	--
	DBMS_OUTPUT.PUT_LINE(STR_KBN || STR_NO);
	RETURN STR_KBN || STR_NO;

EXCEPTION
	WHEN OTHERS THEN
	STR_ERRORCODE :='SQL コード = ' || sqlcode ;
	STR_ERRORNAME := 'エラー内容 = ' || sqlerrm(sqlcode);
	DBMS_OUTPUT.PUT_LINE(STR_ERRORCODE || ' : ' || STR_ERRORNAME);
	RETURN ' ';
END;
/
