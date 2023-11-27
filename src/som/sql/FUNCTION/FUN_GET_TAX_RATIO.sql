CREATE OR REPLACE FUNCTION AP21.FUN_GET_TAX_RATIO
      (
             I_TARGET_DATE		IN	VARCHAR2	-- 消費税算出日
      )

RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_GET_TAX_RATIO                                                         */
/*      処理内容      :      消費税算出日から消費税を取得する            		       、      */
/*      引数          :      I_TARGET_DATE		消費税算出日     	NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.05.29      1.00        Ohga                    新規作成                                   */
/*      2014.01.27      1.00        Ohga                    新消費税対応                               */
/*-----------------------------------------------------------------------------------------------------*/
	TEMP_COUNT		NUMBER;			-- 検索結果有無用カウント変数
	TEMP_TAX_RATIO		NUMBER;			-- 消費税率
	CONST_COMPANY_CD     	VARCHAR2(20) := '000001';	-- 自社マスタコード

BEGIN
	SELECT (CASE WHEN NEW_TAX_APLLY_DATE <= I_TARGET_DATE THEN NEW_TAX_RATIO ELSE TAX_RATIO END) INTO TEMP_TAX_RATIO FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD;
	RETURN TEMP_TAX_RATIO;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN	
	SELECT TAX_RATIO INTO TEMP_TAX_RATIO FROM COMPANY WHERE COMPANY_CD = CONST_COMPANY_CD;
	RETURN TEMP_TAX_RATIO;

END;
/
