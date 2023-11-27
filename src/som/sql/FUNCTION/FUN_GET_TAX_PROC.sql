CREATE OR REPLACE FUNCTION AP21.FUN_GET_TAX_PROC
(
	P_NUM_TAX_DIVISION IN NUMBER
   ,P_NUM_TAX_RATIO    IN NUMBER
)
RETURN NUMBER-- 戻り値のデータ型
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_GET_TAX_PROC                                                          */
/*      処理内容      :      消費税課税区分と消費税率から税処理を取得する                              */
/*      引数          :      P_NUM_TAX_DIVISION		消費税課税区分		NUMBER型               */
/*      引数          :      P_NUM_TAX_RATIO		消費税率		NUMBER型               */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2014.01.27      1.00        Ohga                    新規作成                               */
/*-----------------------------------------------------------------------------------------------------*/
	n_TaxPrc NUMBER;	-- 戻り値
BEGIN

	IF P_NUM_TAX_DIVISION = '3' THEN
		SELECT TAX_PROC INTO n_TaxPrc FROM TAX_PROC WHERE TAX_PROC.TAX_DIVISION = P_NUM_TAX_DIVISION AND ROWNUM = 1;
	ELSE
		SELECT TAX_PROC INTO n_TaxPrc FROM TAX_PROC WHERE TAX_PROC.TAX_DIVISION = P_NUM_TAX_DIVISION AND TAX_PROC.TAX_RATIO = P_NUM_TAX_RATIO AND ROWNUM = 1;
	END IF;
	
	RETURN n_TaxPrc;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
		OUTPUT_ERROR_LOG('FUN_GET_TAX_RPC'
						,'ATUTO'
						,SQLCODE
						,'税処理が取得できませんでした。消費税課税区分:'|| P_NUM_TAX_DIVISION || ' ' || '消費税率:' || P_NUM_TAX_RATIO);
	RETURN 0;

END FUN_GET_TAX_PROC;
/
