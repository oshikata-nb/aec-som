CREATE OR REPLACE PROCEDURE      IF_DEL_PACKAGE

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      IF_DEL_PACKAGE                                                            */
/*      処理内容      :      包装計画の包装状況が完了となっているデータの2年前を削除（夜間バッチ）     */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 	VARCHAR2(100);
	ERR_MSG 	VARCHAR2(2048);
	PROC_DATE 	VARCHAR2(20);

BEGIN

	-- 二年前の日付を作成
	PROC_DATE := ADD_MONTHS(SYSDATE, 12 * -2);

	-- 包装計画.包装状況=[2:包装完了]かつ包装日が二年以上前の場合削除
	DELETE FROM KEIKAKU_HOSO WHERE KEIKAKU_HOSO.HOSOJOKYO = '2' AND KEIKAKU_HOSO.HOSOBI <= PROC_DATE ;

	COMMIT;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		ROLLBACK;

		OUTPUT_ERROR_LOG('IF_DEL_PACKAGE','AUTO',ERR_CODE,ERR_MSG);

END;
/
