CREATE OR REPLACE FUNCTION AP21.FNC_端数処理(DATAX NUMBER, MD NUMBER, KETA NUMBER) RETURN NUMBER IS
-- 端数処理
--　引き数：DATAX：計算元(NUMBER)
--　　　　：MD   ：0切り捨て 1: 切り上げ 2:四捨五入 
--　　　　：KETA ：処理後の小数点以下桁
--　戻り値：端数処理値
	INTRESULT NUMBER;
	WK NUMBER;
	INT調整 NUMBER;
	STRZERO VARCHAR2(20):='00000000000000000000';
BEGIN

	--切捨て
	IF MD = 0 THEN
		INT調整 := 0;
	END IF;
	--切り上げ
	IF MD = 1 THEN
		INT調整 := 0.9;
		INT調整 := INT調整 / TO_NUMBER((1 || SUBSTR(STRZERO,1,KETA)));
	END IF;
	--四捨五入
	IF MD = 2 THEN
		INT調整 := 0.5;
		INT調整 := INT調整 / TO_NUMBER((1 || SUBSTR(STRZERO,1,KETA)));
	END IF;

--計算結果
	--元が０の場合
	IF DATAX = 0 THEN
		INTRESULT := 0;
	END IF;
	--元がマイナスの時
	IF DATAX < 0 THEN
		INTRESULT := TRUNC(DATAX - INT調整, KETA);
	END IF;
	--元がプラスの時
	IF DATAX > 0 THEN
		INTRESULT := TRUNC(DATAX + INT調整, KETA);
	END IF;

	RETURN INTRESULT;

/*
	IF MD = 0 THEN RESULT := TRUNC(DATAX, KETA);
	ELSIF MD = 1 THEN RESULT := ROUND(DATAX, KETA);
	ELSIF MD = 2 THEN
		WK := DATAX * POWER(10,KETA);
		WK := CEIL(DATAX);
		RESULT := WK / POWER(10,KETA);
	END IF;
	RETURN RESULT;
*/
END;
/
