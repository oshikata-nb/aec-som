CREATE OR REPLACE PROCEDURE AP21.IF_DEL_SHIPPING
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      IF_DEL_SHIPPING                                                           */
/*      処理内容      :      出荷指図の出荷状況が３：出荷完了となっているデータを削除し、              */
/*      　　　　      :      同じ積出ナンバーの製品別出庫状況（複数）の　　　　　　　　　              */
/*      　　　　      :      出荷状況が３：積出完了となっているデータを削除　　　　　　　              */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*      2009.10.06      1.01        Eto                     DELETE FROM KEIKAKU_SHUKKAをloop外へ移動   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 	VARCHAR2(100);
	ERR_MSG 	VARCHAR2(2048);
	-- 出荷指図.出荷状況 = [3:出荷完了]で検索
    	CURSOR remote_delete IS SELECT TSUMINO FROM KEIKAKU_SHUKKA WHERE KEIKAKU_SHUKKA.SHUKKAJOKYO = 3;
BEGIN
	-- 削除対象の出荷指図テーブルと製品別出庫状況テーブルを削除
   	FOR rec IN remote_delete LOOP
		-- 出荷指図.出荷状況 = [3:出荷完了]の検索結果積出ナンバーと同一である製品別出庫状況.出荷状況=[3:積出完了]を削除
		DELETE FROM JOKYO_SEIHINBETSU_SHUKKO WHERE TSUMINO = rec.TSUMINO AND SHUKKAJOKYO = 3 ;
		
	END LOOP;
	-- 出荷指図.出荷状況 = [3:出荷完了]を削除
	DELETE FROM KEIKAKU_SHUKKA WHERE KEIKAKU_SHUKKA.SHUKKAJOKYO = 3;
	COMMIT;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);
		OUTPUT_ERROR_LOG('IF_DEL_SHIPPING','AUTO',ERR_CODE,ERR_MSG);
		COMMIT;
END;
/
