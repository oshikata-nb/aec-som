CREATE OR REPLACE function FUN_NOTEQUAL(val1 varchar2, val2 varchar2) return boolean
--■■■■■■■■■■■■■■■■■■■
--■　比較処理
--■　val1 : 比較値
--■　val2 : 比較値
--■ 値が一致、または両方ともNULLの場合は、false（一致）
--■ 上記以外は、不一致(true)とする
--■■■■■■■■■■■■■■■■■■■
is
begin
	if (val1 = val2) THEN
		return false;
	elsif (val1 is null and val2 is null) then
		return false;
	end if;
	return true;
end;
/
