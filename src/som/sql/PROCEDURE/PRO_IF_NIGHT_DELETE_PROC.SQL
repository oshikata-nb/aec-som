CREATE OR REPLACE PROCEDURE      PRO_IF_NIGHT_DELETE_PROC

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_NIGHT_DELETE_PROC                                                  */
/*      処理内容      :      計装IF夜間バッチ処理                                                      */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 	VARCHAR2(100);
	ERR_MSG 	VARCHAR2(2048);

BEGIN

	-- 製品入荷データ削除処理
	IF_DEL_IMPORT;

	-- 包装計画削除処理
	IF_DEL_PACKAGE;

	-- 製造計画、製造指図削除
	IF_DEL_PRODUCTION;

	-- 出荷指図、製品別出荷状況削除
	IF_DEL_SHIPPING;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		OUTPUT_ERROR_LOG('IF_NIGHT_DELETE_PROC','AUTO',ERR_CODE,ERR_MSG);

END;
/
