CREATE OR REPLACE PROCEDURE AP21.PRO_SHIPPING_AUTO_RESULT
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_SHIPPING_AUTO_RESULT						       */
/*      処理内容      :      計装IFテーブル出荷実績を定周期で監視し、  、      			       */
/*      		     出荷指図量 <= 出荷実績量となった場合出荷完了とする  、      	       */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.14      1.00        Ohga                    新規作成                                   */
/*      2009.08.19      1.01        Ohga                    計装IFの済みフラグを処理前に立て、	　     */
/*							　　失敗時にOFFするよう修正                    */
/*      2009.08.27      1.02        Ohga                    SQLServerとOracleで入出庫時刻の秒が違う件、*/
/*							　　対策 ±１秒で処理　　　  		       */
/*      2011.02.02      1.03        Ohga                    SQLServerを更新し、更新チェックでエラーが　*/
/*							　　発生した場合の対策　　　  		       */
/*-----------------------------------------------------------------------------------------------------*/
	
	TEMP_COUNT		NUMBER;
	TEMP_RET		NUMBER;			-- 売上レコード作製処理戻り値
	TEMP_RETURN_CD		VARCHAR2(100);		-- エラーコード
	TEMP_RETURN_MSG		VARCHAR2(2000);		-- エラーメッセージ
	TEMP_JISSEKI_BEFORE	DATE;			-- 1分前時刻
	TEMP_JISSEKI_AFTER	DATE;			-- 1分後時刻

	
	CONST_UPDATE_USER	VARCHAR2(20) := 'AUTO';	-- 更新ユーザー名
	-- 製品別出庫指図状況書き込み用
	-- ①製品入出庫実績.入出庫 = [2:出庫]
	-- ②製品入出庫実績.上位処理済み = [0:未処理]
	-- ③製品入出庫実績.製品出庫指図バーコードがNULLではない
	CURSOR PROC_SHIPPING_RESULT IS 
		SELECT 
			* 
		FROM 
			JISSEKI_SEIHIN_PRO 
		WHERE 
			JISSEKI_SEIHIN_PRO.NYUUSYUKKO = 2 	-- ①
			AND　JISSEKI_SEIHIN_PRO.SUMI = 0 	-- ②
			AND JISSEKI_SEIHIN_PRO.SEISHUKKOSABC IS NOT NULL	-- ③
			ORDER BY JISSEKI_SEIHIN_PRO.SEISHUKKOSABC,JISSEKI_SEIHIN_PRO.JIKOKU ASC;
BEGIN
	DBMS_OUTPUT.ENABLE(NULL);

	-- 多重起動防止の為追加
	IF FUN_LOCK_DOUBLE_START('PRO_SHIPPING_AUTO_RESULT') = FALSE THEN
		OUTPUT_ERROR_LOG('PRO_SHIPPING_AUTO_RESULT','AUTO',SQLCODE,'すでに起動しています');
		COMMIT;
		RETURN;
	END IF;
	COMMIT;

	-- IFからあがってきた製品入出庫実績毎にループ
   	FOR PROC_RESULT IN PROC_SHIPPING_RESULT LOOP

		TEMP_JISSEKI_BEFORE := PROC_RESULT.JIKOKU - 1 / 86400;	-- 一秒前
		TEMP_JISSEKI_AFTER  := PROC_RESULT.JIKOKU + 1 / 86400;	-- 一秒後

		-- 製品入出庫実績の上位処理済みフラグをON
		IF FUNC_SET_SUMI('1',PROC_RESULT.SEIHINCODE,PROC_RESULT.LOCATION,PROC_RESULT.JIKOKU,PROC_RESULT.NYUUSYUKKO,PROC_RESULT.HOSOSASHIZUNO,PROC_RESULT.NYUKABC,PROC_RESULT.SEISHUKKOSABC) THEN
			-- IFフラグON成功
			-- 出荷実績受信処理起動
			TEMP_RET := FUN_SHIPPING_AUTO_RESULT(
						PROC_RESULT,
						TEMP_RETURN_CD,
						TEMP_RETURN_MSG);
	
			DBMS_OUTPUT.PUT_LINE('戻り:' || TEMP_RET);
			IF TEMP_RET = 0 THEN
				COMMIT;
				
			ELSE
				ROLLBACK;
	
				OUTPUT_ERROR_LOG('PRO_SHIPPING_AUTO_RESULT',CONST_UPDATE_USER,TEMP_RETURN_CD,TEMP_RETURN_MSG);
				COMMIT;

				-- 製品入出庫実績の上位処理済みフラグをOFF
				IF FUNC_SET_SUMI('0',PROC_RESULT.SEIHINCODE,PROC_RESULT.LOCATION,PROC_RESULT.JIKOKU,PROC_RESULT.NYUUSYUKKO,PROC_RESULT.HOSOSASHIZUNO,PROC_RESULT.NYUKABC,PROC_RESULT.SEISHUKKOSABC) THEN
					-- IFフラグOFF成功
					NULL;
				ELSE
					-- IFフラグOFF失敗
					OUTPUT_ERROR_LOG('PRO_SHIPPING_AUTO_RESULT','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(PROC_RESULT.SEIHINCODE) || ' ロケーション:' || TRIM(PROC_RESULT.LOCATION) || ' 包装指図番号:' || PROC_RESULT.HOSOSASHIZUNO || ' 入荷ロット番号:' || PROC_RESULT.NYUKABC || ' 時刻:' || TRIM(PROC_RESULT.JIKOKU));
					COMMIT;
				END IF;

				COMMIT;
	
			END IF;
		ELSE
			-- IFフラグON失敗
			OUTPUT_ERROR_LOG('PRO_SHIPPING_AUTO_RESULT','AUTO','','製品入出庫実績の上位処理済みフラグをONに失敗しました 品目:' || TRIM(PROC_RESULT.SEIHINCODE) || ' ロケーション:' || TRIM(PROC_RESULT.LOCATION) || ' 包装指図番号:' || PROC_RESULT.HOSOSASHIZUNO || ' 入荷ロット番号:' || PROC_RESULT.NYUKABC || ' 時刻:' || TRIM(PROC_RESULT.JIKOKU));
			COMMIT;
		END IF;
	END LOOP;

	-- 多重起動防止
	DELETE FROM PROC_PARAM WHERE PROC_PARAM.PROC_CD = 'PRO_SHIPPING_AUTO_RESULT';

	COMMIT;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		IF PROC_SHIPPING_RESULT%ISOPEN = TRUE THEN
			CLOSE PROC_SHIPPING_RESULT;
		END IF;
		ROLLBACK;

		-- 多重起動防止
		DELETE FROM PROC_PARAM WHERE PROC_PARAM.PROC_CD = 'PRO_SHIPPING_AUTO_RESULT';

		-- SQLエラーコード、エラーメッセージを取得
	    	TEMP_RETURN_CD := SQLCODE;
		TEMP_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		OUTPUT_ERROR_LOG('PRO_SHIPPING_AUTO_RESULT',CONST_UPDATE_USER,TEMP_RETURN_CD,TEMP_RETURN_MSG);
		COMMIT;
END;
/
