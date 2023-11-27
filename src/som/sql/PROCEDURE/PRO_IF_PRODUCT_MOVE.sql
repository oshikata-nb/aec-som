CREATE OR REPLACE PROCEDURE AP21.PRO_IF_PRODUCT_MOVE
IS
/*-----------------------------------------------------------------------------------------------------	*/
/*      種別          :      PROCEDURE                                                                 	*/
/*      名称          :      PRO_IF_PRODUCT_MOVE                                                  	*/
/*      処理内容      :      製品ロケーション移動       			       			*/
/*      引数          :      なし                                                                      	*/
/*      VERNO.        :      1.00                                                                      	*/
/*(history)                                                                                            	*/
/*      date            ver         name                    comments                                   	*/
/*      ----------      ------      ------------------      -------------------------------------------	*/
/*      2009.06.24      1.00        ETO                     新規作成                                   	*/
/*      2009.08.17      1.01        OHGA                    UPDATE時のTRIM処理を外した			*/
/*      2009.08.19      1.02        OHGA                    計装IFの済みフラグを処理前に立て、	　      */
/*							　　失敗時にOFFするよう修正                     */
/*      2009.08.27      1.02        OHGA                    SQLServerとOracleで入出庫時刻の秒が違う件対策、*/
/*							　　±１秒で処理　　　  			*/
/*      2009.10.08      1.03        ETO                    入庫と出庫のデータがそろった場合だけ処理する、*/
/*-----------------------------------------------------------------------------------------------------	*/
	TEMP_ERR_CODE 			VARCHAR2(100);
	TEMP_ERR_MSG 			VARCHAR2(2048);
	CONST_COMP			VARCHAR2(20) := 'COMPLETE';	-- 在庫プロシージャ完了戻り値
	PRONO                           NUMBER;				-- 処理コード　9:在庫入庫　10:在庫出庫 13:棚卸調整入庫 14:棚卸調整出庫 16:返品 17:預品
	ITEMCD                          VARCHAR2(20);			-- 品目コード
	ITEMCD2                          VARCHAR2(40);			-- 品目コード
	INOUTDATE                       DATE;				-- 入出庫時刻
	LOC_CD                          VARCHAR2(20);			-- 入出庫ロケーションコード
	LOC_CD2                          VARCHAR2(40);			-- 入出庫ロケーションコード
	LOTNO                           VARCHAR2(20);			-- ロットＮＯ
	INOUT                           NUMBER;				-- 0:入庫　1:出庫（預品の場合）
	QTY                             NUMBER;				-- 数量（在庫管理単位）正常時正の値
	OUT_PARA                        VARCHAR2(1000);			-- 返り：「COMPLETE」で成功。失敗ならエラーメッセージが帰る。
	REMARK                          VARCHAR2(20);			-- 摘要
	REASON                          VARCHAR2(20);			-- 理由
	REASONCD                        NUMBER;                         -- 理由コード
	CT                              NUMBER;				-- カウンター
	ALOT                            VARCHAR2(20);			-- 代替ロットＮｏ
	COST                            NUMBER;				-- 単価
	USERCD                          VARCHAR2(20);			-- ユーザーＩＤ
	FUNC                            NUMBER;				-- 処理区分
	INOUTDATE2                      DATE;
	INOUTDATE_BEFORE		DATE;				-- 一秒前
	INOUTDATE_AFTER			DATE;				-- 一秒後
	LSUMI0     VARCHAR2(20);
	LSUMI1     VARCHAR2(20);
	LOCATION1 CHAR(30);
	JIKOKU1 DATE;
	NYUUSYUKKO1 CHAR(33);
	STR_DATE0 VARCHAR2(100);
	STR_DATE1 VARCHAR2(100);
									/** 3:例外  6:移動  8:棚卸調整  83:例外取消  86:移動取消  88:棚調取消 */
	-- 製品入出庫実績
    	CURSOR curJISSEKISEIHIN IS 
	SELECT a.SEIHINCODE as SEIHINCODE,a.NYUUSYUKKO as NYUUSYUKKO0
		,a.LOCATION as LOCATION0 ,a.SUURYOU as SUURYOU , a.HOSOSASHIZUNO as HOSOSASHIZUNO
		,a.NYUKABC as NYUKABC ,a.SUMI as SUMI0,a.JIKOKU as JIKOKU0
	FROM JISSEKI_SEIHIN_PRO a 
	WHERE  a.SUMI =0 and  RTRIM(LTRIM(a.NYUUSYUKKO)) = '4' order by a.jikoku
;
BEGIN
	FOR rtJIS IN curJISSEKISEIHIN LOOP
		DBMS_OUTPUT.PUT_LINE(rtJIS.SEIHINCODE || ':' || rtJIS.LOCATION0 || ':' );

		STR_DATE0 := TO_CHAR(rtJIS.JIKOKU0,'YYYY/MM/DD HH24:MI:SS');
		LSUMI0 := TRIM(rtJIS.SUMI0);
		SELECT COUNT(*) INTO CT
		FROM JISSEKI_SEIHIN_PRO a 
		WHERE  a.SUMI =0 and  RTRIM(LTRIM(a.NYUUSYUKKO)) = '3'
		and a.SEIHINCODE = rtJIS.SEIHINCODE and a.SUURYOU = rtJIS.SUURYOU
		and a.HOSOSASHIZUNO = rtJIS.HOSOSASHIZUNO and a.NYUKABC = rtJIS.NYUKABC
		and a.JIKOKU > rtJIS.JIKOKU0  order by a.jikoku;
		
		IF CT is NULL OR  CT < 1 then
			GOTO NEXT_DATA;
		END IF;
		
		SELECT b.LOCATION as LOCATION1,b.NYUUSYUKKO as NYUUSYUKKO1,b.JIKOKU as JIKOKU1 INTO LOCATION1,NYUUSYUKKO1,JIKOKU1 from 
		(SELECT * 
		FROM JISSEKI_SEIHIN_PRO a 
		WHERE  a.SUMI =0 and  RTRIM(LTRIM(a.NYUUSYUKKO)) = '3'
		and a.SEIHINCODE = rtJIS.SEIHINCODE and a.SUURYOU = rtJIS.SUURYOU
		and a.HOSOSASHIZUNO = rtJIS.HOSOSASHIZUNO and a.NYUKABC = rtJIS.NYUKABC
		and a.JIKOKU > rtJIS.JIKOKU0  order by a.jikoku ) b where  rownum = 1;
		STR_DATE1 := TO_CHAR(JIKOKU1,'YYYY/MM/DD HH24:MI:SS');
		DBMS_OUTPUT.PUT_LINE(STR_DATE0 || ':' || STR_DATE1  );
		LSUMI1 := '0';
		-- IF.製品入出実績の上位処理済みフラグを１にする(フラグを立て損ねて複数回形状対策によりAP処理より先にフラグON）
		--出庫側処理
		IF FUNC_SET_SUMI('1',rtJIS.SEIHINCODE,rtJIS.LOCATION0,rtJIS.JIKOKU0,rtJIS.NYUUSYUKKO0,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
			null;
		ELSE
			OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをONに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(rtJIS.LOCATION0)  || ' 時刻:' || TO_CHAR(rtJIS.JIKOKU0,'YYYY/MM/DD hh24:mi:ss'));
			COMMIT;
			GOTO NEXT_DATA;
		END IF;
		-- 入庫側
		IF FUNC_SET_SUMI('1',rtJIS.SEIHINCODE,LOCATION1,JIKOKU1,NYUUSYUKKO1,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
			null;
		ELSE
			ROLLBACK;
			OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをONに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(LOCATION1)  || ' 時刻:' || TO_CHAR(JIKOKU1,'YYYY/MM/DD hh24:mi:ss'));
			COMMIT;
			IF FUNC_SET_SUMI(LSUMI0,rtJIS.SEIHINCODE,rtJIS.LOCATION0,rtJIS.JIKOKU0,rtJIS.NYUUSYUKKO0,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
				null;
			ELSE
				OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(rtJIS.LOCATION0)  || ' 時刻:' || TO_CHAR(rtJIS.JIKOKU0,'YYYY/MM/DD hh24:mi:ss'));
				COMMIT;
				GOTO NEXT_DATA;
			END IF;
			GOTO NEXT_DATA;
		END IF;

		-- 入庫
		PRONO := 9;  --例外入庫
		INOUT := 0;  --入庫
		-- ロケーション
		LOC_CD := RTRIM(LTRIM(LOCATION1));
		INOUTDATE2 := JIKOKU1;

		-- 製品コード
		ITEMCD := RTRIM(LTRIM(rtJIS.SEIHINCODE));
		-- 数量
		QTY := rtJIS.SUURYOU;
		-- 時刻
		INOUTDATE := sysdate;
		-- ロット 
		CASE
		    WHEN SUBSTR(rtJIS.HOSOSASHIZUNO,1,1) = 'H' THEN
			LOTNO := RTRIM(LTRIM(rtJIS.HOSOSASHIZUNO));
		    WHEN SUBSTR(rtJIS.NYUKABC,1,1) = 'P' THEN
			LOTNO := RTRIM(LTRIM(rtJIS.NYUKABC));
		    ELSE
			OUT_PARA := 'ERROR : NOT FOUND LOT ' || ITEMCD || ':' || LOC_CD ;
			DBMS_OUTPUT.PUT_LINE(OUT_PARA);
			OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','ZAIKO',OUT_PARA);
			GOTO NEXT_DATA;
		END CASE;
		-- 摘要
		REMARK := null;
		-- 理由
		REASON := null;
		REASONCD := null;
		-- エリアスロット、単価　LOT_INVENTORYより採取
		SELECT COUNT(*) INTO CT FROM LOT_INVENTORY WHERE ITEM_CD = ITEMCD AND LOT_NO = LOTNO;
		IF CT > 0 THEN
			SELECT max(ALIAS_LOT_NO),max(INVENTORY_COST) INTO ALOT,COST FROM LOT_INVENTORY WHERE ITEM_CD = ITEMCD AND LOT_NO = LOTNO;
		ELSE
			ALOT := null;
			COST := null;
		END IF;
		USERCD := 'AUTO';
		FUNC := 6 ; -- 移動
		-- 在庫更新プロシージャ実行
		IF LSUMI1 = '0' THEN
			ZAIKOUKEHARAI.ENTRY_INOUT(PRONO,ITEMCD,QTY,INOUTDATE,LOC_CD,LOTNO,REMARK,REASON,REASONCD,FUNC,INOUT,ALOT,COST,USERCD,OUT_PARA);
			IF OUT_PARA = CONST_COMP THEN
				null;
			ELSE
				ROLLBACK;
				OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','ZAIKO','在庫更新に失敗しました 品目:' || TRIM(ITEMCD) || ' ロケーション:' || TRIM(LOC_CD) || ' ロット:' || LOTNO || ' 時刻:' || TO_CHAR(INOUTDATE2,'YYYY/MM/DD hh24:mi:ss') || ' 在庫プロシージャのエラーメッセージ:' || OUT_PARA);
				DBMS_OUTPUT.PUT_LINE(OUT_PARA);
				COMMIT;
				--出庫側処理
				IF FUNC_SET_SUMI(LSUMI0,rtJIS.SEIHINCODE,rtJIS.LOCATION0,rtJIS.JIKOKU0,rtJIS.NYUUSYUKKO0,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
					null;
				ELSE
					OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(rtJIS.LOCATION0)  || ' 時刻:' || TO_CHAR(rtJIS.JIKOKU0,'YYYY/MM/DD hh24:mi:ss'));
					COMMIT;
					GOTO NEXT_DATA;
				END IF;
				COMMIT;
				-- 入庫側
				IF FUNC_SET_SUMI(LSUMI0,rtJIS.SEIHINCODE,LOCATION1,JIKOKU1,NYUUSYUKKO1,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
					null;
				ELSE
					OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(LOCATION1)  || ' 時刻:' || TO_CHAR(JIKOKU1,'YYYY/MM/DD hh24:mi:ss'));
					COMMIT;
					GOTO NEXT_DATA;
				END IF;
				GOTO NEXT_DATA;
			END IF;
		END IF;
		-- 出庫
		PRONO := 10; --例外出庫
		INOUT := 1;  --出庫
		-- ロケーション
		LOC_CD := RTRIM(LTRIM(rtJIS.LOCATION0));
		INOUTDATE2 := rtJIS.JIKOKU0;
		-- 在庫更新プロシージャ実行
		IF LSUMI0 = '0' THEN
			ZAIKOUKEHARAI.ENTRY_INOUT(PRONO,ITEMCD,QTY,INOUTDATE,LOC_CD,LOTNO,REMARK,REASON,REASONCD,FUNC,INOUT,ALOT,COST,USERCD,OUT_PARA);
			IF OUT_PARA = CONST_COMP THEN
				null;
			ELSE
				ROLLBACK;
				OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','ZAIKO','在庫更新に失敗しました 品目:' || TRIM(ITEMCD) || ' ロケーション:' || TRIM(LOC_CD) || ' ロット:' || LOTNO || ' 時刻:' || TO_CHAR(INOUTDATE2,'YYYY/MM/DD hh24:mi:ss') || ' 在庫プロシージャのエラーメッセージ:' || OUT_PARA);
				DBMS_OUTPUT.PUT_LINE(OUT_PARA);
				COMMIT;
				--出庫側処理
				IF FUNC_SET_SUMI(LSUMI0,rtJIS.SEIHINCODE,rtJIS.LOCATION0,rtJIS.JIKOKU0,rtJIS.NYUUSYUKKO0,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
					null;
				ELSE
					OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(rtJIS.LOCATION0)  || ' 時刻:' || TO_CHAR(rtJIS.JIKOKU0,'YYYY/MM/DD hh24:mi:ss'));
					COMMIT;
					GOTO NEXT_DATA;
				END IF;
				-- 入庫側
				IF FUNC_SET_SUMI(LSUMI0,rtJIS.SEIHINCODE,LOCATION1,JIKOKU1,NYUUSYUKKO1,rtJIS.HOSOSASHIZUNO,rtJIS.NYUKABC) THEN
					null;
				ELSE
					OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO','','製品入出庫実績の上位処理済みフラグをOFFに失敗しました 品目:' || TRIM(rtJIS.SEIHINCODE) || ' ロケーション:' || TRIM(LOCATION1)  || ' 時刻:' || TO_CHAR(JIKOKU1,'YYYY/MM/DD hh24:mi:ss'));
					COMMIT;
					GOTO NEXT_DATA;
				END IF;
				GOTO NEXT_DATA;
			END IF;
		END IF;
		commit;
<<NEXT_DATA>>
		NULL;
	END LOOP;
-- 異常処理
EXCEPTION
	WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    	TEMP_ERR_CODE := SQLCODE;
		TEMP_ERR_MSG  := SUBSTR(SQLERRM,1,1024);
		IF curJISSEKISEIHIN%ISOPEN = TRUE THEN
			CLOSE curJISSEKISEIHIN;
		END IF;
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_PRODUCT_MOVE','AUTO',TEMP_ERR_CODE,TEMP_ERR_MSG);
		DBMS_OUTPUT.PUT_LINE(SQLCODE || ':' || SQLERRM);
		COMMIT;
END;
/
