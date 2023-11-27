CREATE OR REPLACE PROCEDURE      IF_DEL_IMPORT

IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      IF_DEL_IMPORT                                                             */
/*      処理内容      :      製品入荷データの入庫状況が２：完了となっているデータを３：完納にし、      */
/*      　　　　      :      ３完納のデータの内、２年前のデータを削除  　　　　　　　　　              */
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

	-- 製品入荷.入庫状況 = [2:完了]を[3]に更新
	UPDATE DATA_SEIHIN_NYUKA SET NYUKOJOKYO = '3' WHERE NYUKOJOKYO = '2';

	-- 二年前の日付を作成
	PROC_DATE := ADD_MONTHS(SYSDATE, 12 * -2);

	-- 製品入荷.入庫状況 = [3]で二年以上前のデータを削除
	DELETE FROM DATA_SEIHIN_NYUKA WHERE NONYUYOTEIBI <= PROC_DATE AND NYUKOJOKYO = '3';

	COMMIT;

-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		ROLLBACK;

		OUTPUT_ERROR_LOG('IF_DEL_IMPORT','AUTO',ERR_CODE,ERR_MSG);

END;
/
