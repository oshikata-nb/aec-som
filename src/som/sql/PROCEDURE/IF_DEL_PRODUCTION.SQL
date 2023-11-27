CREATE OR REPLACE PROCEDURE AP21.IF_DEL_PRODUCTION
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      IF_DEL_PRODUCTION                                                         */
/*      処理内容      :      製造計画と製造指示のデータを削除（夜間バッチ）                            */
/*      引数          :      なし                                                                      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*      2009.10.06      1.01        Eto                     削除条件（製造指図＝完了）                 */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE 	VARCHAR2(100);
	ERR_MSG 	VARCHAR2(2048);
	CT NUMBER;
	-- 製造計画.製造状況 = [2:完了]で検索
    	CURSOR remote_delete IS SELECT SEIZOSASHIZUNO FROM KEIKAKU_SEIZO WHERE KEIKAKU_SEIZO.SEIZOJOKYO = '2';
BEGIN
	-- 製造指示.製造指図書No = 製造計画.製造指図番号のものを削除
   	FOR rec IN remote_delete LOOP
		SELECT COUNT(*) INTO CT FROM DIRECTION_HEADER WHERE DIRECTION_STATUS = 8 AND DIRECTION_NO = trim(rec.SEIZOSASHIZUNO);
		IF CT is null or CT < 1  THEN
			NULL;
		else
			DELETE FROM SIJI_SEIZO WHERE SEIZOSASHIZUNO = rec.SEIZOSASHIZUNO ;
			DELETE FROM KEIKAKU_SEIZO WHERE SEIZOSASHIZUNO = rec.SEIZOSASHIZUNO ;
		end if;
	END LOOP;
	COMMIT;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);
		ROLLBACK;
		OUTPUT_ERROR_LOG('IF_DEL_PRODUCTION','AUTO',ERR_CODE,ERR_MSG);
		COMMIT;
END;
/
