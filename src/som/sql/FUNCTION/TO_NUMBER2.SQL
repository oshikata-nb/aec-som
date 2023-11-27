CREATE OR REPLACE function TO_NUMBER2(STR varchar2) return number
--■■■■■■■■■■■■■■■■■■■
--■　数値変換 変換できない場合、NULLを返す
--■　STR : 置換文字列
--■■■■■■■■■■■■■■■■■■■
is
begin
 return to_number(str);
exception when others then
 --dbms_output.put_line('TO_NUMBER2 error: ' || SQLERRM);
 return null;
end;
/
