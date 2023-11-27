CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYG
(
	P_STR_TARGET_MONTH IN NUMBER
   ,P_STR_TANTO_CD     IN NVARCHAR2
   ,P_NUM_CNT          OUT NUMBER
   ,P_NUM_RET          OUT NUMBER
) IS
	CURSOR curCAYG IS
		SELECT * FROM SIAP_CAYG WHERE YGNODATE = P_STR_TARGET_MONTH;
	rtCAYG curCAYG%ROWTYPE;
	CNT    NUMBER;
BEGIN
	-- 対象件数調査
	SELECT COUNT(*) INTO P_NUM_CNT FROM SIAP_CAYG WHERE YGNODATE = P_STR_TARGET_MONTH;
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYGPROCFLGが9:削除のデータをDELETE)
	--     どちらが消すのか、未定のため保留
	DELETE FROM SIAP.IFYG@SIAPSOM
	WHERE  YGPROCFLG = 9
	AND    YGSTATUS IN (1, 2)
	AND    YGNODATE = P_STR_TARGET_MONTH
	AND    YGCOCODE = '001'
	AND    YGHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYG@SIAPSOM
	SET    YGPROCFLG = 9
		  ,YGSTATUS  = 0
		  ,YGERRMSG  = NULL
	WHERE  YGNODATE = P_STR_TARGET_MONTH
	AND    YGCOCODE = '001'
	AND    YGHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	/*  MERGE INTO SIAP.IFYG@SIAPSOM YG
    USING (SELECT * FROM SIAP_CAYG WHERE YGNODATE = P_STR_TARGET_MONTH) AG
    ON (YG.YGCOCODE = AG.YGCOCODE -- 会社コード
    AND YG.YGHBRCODE = AG.YGHBRCODE -- 本支店コード
    AND YG.YGIMCODE = AG.YGIMCODE -- 品目コード(20桁以内)
    AND YG.YGNODATE = AG.YGNODATE -- 処理年月度(YYYYMM)
    )
    WHEN MATCHED THEN
        UPDATE
        SET    YG.YGIMPDATE  = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
              ,YG.YGIMPTIME  = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
              ,YG.YGPROCFLG  = 1 -- 処理フラグ
              ,YG.YGZGZENSU  = AG.YGZGZENSU -- 前月繰越在庫数
              ,YG.YGZGINQTY  = AG.YGZGINQTY -- 当月入庫数
              ,YG.YGZGOTQTY  = AG.YGZGOTQTY -- 当月出庫数
              ,YG.YGZGKURISU = AG.YGZGKURISU -- 翌月繰越在庫数
              ,YG.YGUPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD')
              ,YG.YGUPTIME   = TO_CHAR(SYSDATE, 'HH24MISS')
              ,YG.YGUPID     = P_STR_TANTO_CD
    WHEN NOT MATCHED THEN
        INSERT
            (YGIMPDATE -- 登録日(YYYYMMDD)
            ,YGIMPTIME -- 登録時間(HH24MI)
            ,YGPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
            ,YGCOCODE -- 会社コード( ◎ ※共通仕様参照。)
            ,YGHBRCODE -- 本支店コード(※共通仕様参照。)
            ,YGIMCODE -- 品目コード(20桁以内)
            ,YGNODATE -- 処理年月度(YYYYMM)
            ,YGACNO -- 勘定科目コード(未使用 NULL)
            ,YGACHOJO -- 補助科目コード(未使用 NULL)
            ,YGZGZENSU -- 前月繰越在庫数
            ,YGZGSTTANK -- 前月繰越在庫評価単価<予定>(未使用 0)
            ,YGZGJSSTTANK -- 前月繰越在庫評価単価<実際> (未使用 0)
            ,YGZGSTKIN -- 前月繰越在庫金額<予定>(未使用 0)
            ,YGZGJSSTKIN -- 前月繰越在庫金額<実際>(未使用 0)
            ,YGZGINQTY -- 当月入庫数
            ,YGZGINTANK -- 当月入庫単価<予定>(未使用 0)
            ,YGZGINKING -- 当月入庫金額<予定>(未使用 0)
            ,YGZGJSINTANK -- 当月入庫単価<実際>(未使用 0)
            ,YGZGJSINKING -- 当月入庫金額<実際>(未使用 0)
            ,YGZGOTQTY -- 当月出庫数
            ,YGZGOTTANK -- 当月出庫単価<予定>(未使用 0)
            ,YGZGOTKING -- 当月出庫金額<予定>(未使用 0)
            ,YGZGJSOTTANK -- 当月出庫単価<実際>(未使用 0)
            ,YGZGJSOTKING -- 当月出庫金額<実際>(未使用 0)
            ,YGZGKURISU -- 翌月繰越在庫数
            ,YGZGENTANK -- 翌月繰越在庫評価単価<予定>(未使用 0)
            ,YGZGJSENTANK -- 翌月繰越在庫評価単価<実際>(未使用 0)
            ,YGZGENKIN -- 翌月繰越在庫金額<予定>(未使用 0)
            ,YGZGJSENKIN -- 翌月繰越在庫金額<実際>(未使用 0)
            ,YGIPDATE -- 登録日
            ,YGIPTIME -- 登録時間
            ,YGIPID -- 登録者ＩＤ
            ,YGUPDATE -- 更新日
            ,YGUPTIME -- 更新時間
            ,YGUPID -- 更新者ＩＤ
             )
        VALUES
            (TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
            ,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):     
            ,0 -- 処理フラグ(0:新規)
            ,AG.YGCOCODE -- 会社コード( ◎ ※共通仕様参照。)
            ,AG.YGHBRCODE -- 本支店コード(※共通仕様参照。)
            ,AG.YGIMCODE -- 品目コード(20桁以内)
            ,AG.YGNODATE -- 処理年月度(YYYYMM)
            ,AG.YGACNO -- 勘定科目コード(未使用 NULL)
            ,AG.YGACHOJO -- 補助科目コード(未使用 NULL)
            ,AG.YGZGZENSU -- 前月繰越在庫数
            ,AG.YGZGSTTANK -- 前月繰越在庫評価単価<予定>(未使用 0)
            ,AG.YGZGJSSTTANK -- 前月繰越在庫評価単価<実際> (未使用 0)
            ,AG.YGZGSTKIN -- 前月繰越在庫金額<予定>(未使用 0)
            ,AG.YGZGJSSTKIN -- 前月繰越在庫金額<実際>(未使用 0)
            ,AG.YGZGINQTY -- 当月入庫数
            ,AG.YGZGINTANK -- 当月入庫単価<予定>(未使用 0)
            ,AG.YGZGINKING -- 当月入庫金額<予定>(未使用 0)
            ,AG.YGZGJSINTANK -- 当月入庫単価<実際>(未使用 0)
            ,AG.YGZGJSINKING -- 当月入庫金額<実際>(未使用 0)
            ,AG.YGZGOTQTY -- 当月出庫数
            ,AG.YGZGOTTANK -- 当月出庫単価<予定>(未使用 0)
            ,AG.YGZGOTKING -- 当月出庫金額<予定>(未使用 0)
            ,AG.YGZGJSOTTANK -- 当月出庫単価<実際>(未使用 0)
            ,AG.YGZGJSOTKING -- 当月出庫金額<実際>(未使用 0)
            ,AG.YGZGKURISU -- 翌月繰越在庫数
            ,AG.YGZGENTANK -- 翌月繰越在庫評価単価<予定>(未使用 0)
            ,AG.YGZGJSENTANK -- 翌月繰越在庫評価単価<実際>(未使用 0)
            ,AG.YGZGENKIN -- 翌月繰越在庫金額<予定>(未使用 0)
            ,AG.YGZGJSENKIN -- 翌月繰越在庫金額<実際>(未使用 0)
            ,TO_CHAR(SYSDATE, 'YYYYMMDD')
            ,TO_CHAR(SYSDATE, 'HH24MISS')
            ,P_STR_TANTO_CD
            ,TO_CHAR(SYSDATE, 'YYYYMMDD')
            ,TO_CHAR(SYSDATE, 'HH24MISS')
            ,P_STR_TANTO_CD);*/
	OPEN curCAYG;
	LOOP
		FETCH curCAYG
			INTO rtCAYG;
	
		IF curCAYG%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		SELECT COUNT(*)
		INTO   CNT
		FROM   SIAP.IFYG@SIAPSOM
		WHERE  YGCOCODE = rtCAYG.YGCOCODE
		AND    YGHBRCODE = rtCAYG.YGHBRCODE
		AND    YGIMCODE = rtCAYG.YGIMCODE
		AND    YGNODATE = rtCAYG.YGNODATE;
	
		IF CNT = 0 THEN
			INSERT INTO SIAP.IFYG@SIAPSOM
				(YGIMPDATE -- 登録日(YYYYMMDD)
				,YGIMPTIME -- 登録時間(HH24MI)
				,YGPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
				,YGCOCODE -- 会社コード( ◎ ※共通仕様参照。)
				,YGHBRCODE -- 本支店コード(※共通仕様参照。)
				,YGIMCODE -- 品目コード(20桁以内)
				,YGNODATE -- 処理年月度(YYYYMM)
				,YGACNO -- 勘定科目コード(未使用 NULL)
				,YGACHOJO -- 補助科目コード(未使用 NULL)
				,YGZGZENSU -- 前月繰越在庫数
				,YGZGSTTANK -- 前月繰越在庫評価単価<予定>(未使用 0)
				,YGZGJSSTTANK -- 前月繰越在庫評価単価<実際> (未使用 0)
				,YGZGSTKIN -- 前月繰越在庫金額<予定>(未使用 0)
				,YGZGJSSTKIN -- 前月繰越在庫金額<実際>(未使用 0)
				,YGZGINQTY -- 当月入庫数
				,YGZGINTANK -- 当月入庫単価<予定>(未使用 0)
				,YGZGINKING -- 当月入庫金額<予定>(未使用 0)
				,YGZGJSINTANK -- 当月入庫単価<実際>(未使用 0)
				,YGZGJSINKING -- 当月入庫金額<実際>(未使用 0)
				,YGZGOTQTY -- 当月出庫数
				,YGZGOTTANK -- 当月出庫単価<予定>(未使用 0)
				,YGZGOTKING -- 当月出庫金額<予定>(未使用 0)
				,YGZGJSOTTANK -- 当月出庫単価<実際>(未使用 0)
				,YGZGJSOTKING -- 当月出庫金額<実際>(未使用 0)
				,YGZGKURISU -- 翌月繰越在庫数
				,YGZGENTANK -- 翌月繰越在庫評価単価<予定>(未使用 0)
				,YGZGJSENTANK -- 翌月繰越在庫評価単価<実際>(未使用 0)
				,YGZGENKIN -- 翌月繰越在庫金額<予定>(未使用 0)
				,YGZGJSENKIN -- 翌月繰越在庫金額<実際>(未使用 0)
				,YGIPDATE -- 登録日
				,YGIPTIME -- 登録時間
				,YGIPID -- 登録者ＩＤ
				,YGUPDATE -- 更新日
				,YGUPTIME -- 更新時間
				,YGUPID -- 更新者ＩＤ
				 )
			VALUES
				(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):     
				,0 -- 処理フラグ(0:新規)
				,rtCAYG.YGCOCODE -- 会社コード( ◎ ※共通仕様参照。)
				,rtCAYG.YGHBRCODE -- 本支店コード(※共通仕様参照。)
				,rtCAYG.YGIMCODE -- 品目コード(20桁以内)
				,rtCAYG.YGNODATE -- 処理年月度(YYYYMM)
				,rtCAYG.YGACNO -- 勘定科目コード(未使用 NULL)
				,rtCAYG.YGACHOJO -- 補助科目コード(未使用 NULL)
				,rtCAYG.YGZGZENSU -- 前月繰越在庫数
				,rtCAYG.YGZGSTTANK -- 前月繰越在庫評価単価<予定>(未使用 0)
				,rtCAYG.YGZGJSSTTANK -- 前月繰越在庫評価単価<実際> (未使用 0)
				,rtCAYG.YGZGSTKIN -- 前月繰越在庫金額<予定>(未使用 0)
				,rtCAYG.YGZGJSSTKIN -- 前月繰越在庫金額<実際>(未使用 0)
				,rtCAYG.YGZGINQTY -- 当月入庫数
				,rtCAYG.YGZGINTANK -- 当月入庫単価<予定>(未使用 0)
				,rtCAYG.YGZGINKING -- 当月入庫金額<予定>(未使用 0)
				,rtCAYG.YGZGJSINTANK -- 当月入庫単価<実際>(未使用 0)
				,rtCAYG.YGZGJSINKING -- 当月入庫金額<実際>(未使用 0)
				,rtCAYG.YGZGOTQTY -- 当月出庫数
				,rtCAYG.YGZGOTTANK -- 当月出庫単価<予定>(未使用 0)
				,rtCAYG.YGZGOTKING -- 当月出庫金額<予定>(未使用 0)
				,rtCAYG.YGZGJSOTTANK -- 当月出庫単価<実際>(未使用 0)
				,rtCAYG.YGZGJSOTKING -- 当月出庫金額<実際>(未使用 0)
				,rtCAYG.YGZGKURISU -- 翌月繰越在庫数
				,rtCAYG.YGZGENTANK -- 翌月繰越在庫評価単価<予定>(未使用 0)
				,rtCAYG.YGZGJSENTANK -- 翌月繰越在庫評価単価<実際>(未使用 0)
				,rtCAYG.YGZGENKIN -- 翌月繰越在庫金額<予定>(未使用 0)
				,rtCAYG.YGZGJSENKIN -- 翌月繰越在庫金額<実際>(未使用 0)
				,TO_CHAR(SYSDATE, 'YYYYMMDD')
				,TO_CHAR(SYSDATE, 'HH24MISS')
				,P_STR_TANTO_CD
				,TO_CHAR(SYSDATE, 'YYYYMMDD')
				,TO_CHAR(SYSDATE, 'HH24MISS')
				,P_STR_TANTO_CD);
		ELSE
			UPDATE SIAP.IFYG@SIAPSOM
			SET    YGIMPDATE  = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				  ,YGIMPTIME  = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
				  ,YGPROCFLG  = 1 -- 処理フラグ
				  ,YGZGZENSU  = rtCAYG.YGZGZENSU -- 前月繰越在庫数
				  ,YGZGINQTY  = rtCAYG.YGZGINQTY -- 当月入庫数
				  ,YGZGOTQTY  = rtCAYG.YGZGOTQTY -- 当月出庫数
				  ,YGZGKURISU = rtCAYG.YGZGKURISU -- 翌月繰越在庫数
				  ,YGUPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD')
				  ,YGUPTIME   = TO_CHAR(SYSDATE, 'HH24MISS')
				  ,YGUPID     = P_STR_TANTO_CD
			WHERE  YGCOCODE = rtCAYG.YGCOCODE -- 会社コード
			AND    YGHBRCODE = rtCAYG.YGHBRCODE -- 本支店コード
			AND    YGIMCODE = rtCAYG.YGIMCODE -- 品目コード(20桁以内)
			AND    YGNODATE = rtCAYG.YGNODATE; -- 処理年月度(YYYYMM)
		END IF;
	END LOOP;
	CLOSE curCAYG;
	-- 戻り値設定
	P_NUM_RET := 0;
	COMMIT;
EXCEPTION
	-- 例外処理
	WHEN OTHERS THEN
		ROLLBACK;
		P_NUM_RET := 99;
		-- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYF', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYG;
/
