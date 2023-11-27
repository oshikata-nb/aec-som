CREATE OR REPLACE FUNCTION      FUN_ITEM_EXPIRE_MONTHS(PARA_ITEMCD VARCHAR2,PARA_DATE DATE) RETURN VARCHAR2 IS
	LNG_EXPIRE_MONTHS NUMBER := 0;
	LNG_RC NUMBER := 0;
	DTE_DATE DATE;
BEGIN
	--カウント取得
	SELECT COUNT(*) INTO LNG_RC FROM
		(
		SELECT MIN(EXPIRE_MONTHS) FROM
			(
			SELECT ITEM_CD,EXPIRE_MONTHS FROM COMMON_ATTRIBUTE_QUEUE
			)
		WHERE
			ITEM_CD = PARA_ITEMCD 
		GROUP BY
			ITEM_CD
		)
	;
	--
	IF LNG_RC = 0 THEN
		DTE_DATE := TO_DATE('2047/12/31','YYYY-MM-DD HH24:MI:SS');
		RETURN DTE_DATE;
	ELSE
		--賞味期限取得
		SELECT NVL(MIN(EXPIRE_MONTHS),0) INTO LNG_EXPIRE_MONTHS FROM
			(
			SELECT ITEM_CD,EXPIRE_MONTHS FROM COMMON_ATTRIBUTE_QUEUE
			)
		WHERE
			ITEM_CD = PARA_ITEMCD
		GROUP BY
			ITEM_CD
		;
		IF LNG_EXPIRE_MONTHS = 0 THEN
			DTE_DATE := TO_DATE('2047/12/31','YYYY-MM-DD HH24:MI:SS');
		ELSE
			DTE_DATE := PARA_DATE - 1 + LNG_EXPIRE_MONTHS ;
		END IF;

		RETURN DTE_DATE;
	END IF;

	--
END;
/
