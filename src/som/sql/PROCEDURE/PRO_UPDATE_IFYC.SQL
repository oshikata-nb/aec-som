CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYC
(
	P_STR_TARGET_MONTH IN NVARCHAR2
   ,P_STR_TANTO_CD     IN NVARCHAR2
   ,P_NUM_CNT          OUT NUMBER
   ,P_NUM_RET          OUT NUMBER
) IS
	stdt NUMBER := P_STR_TARGET_MONTH || '00';
	eddt NUMBER := P_STR_TARGET_MONTH || '99';
	CURSOR curCAYC IS
		SELECT YCCOCODE
			  ,YCHBRCODE
			  ,YCKESANKI
			  ,TO_CHAR(YCODODRNO1) YCODODRNO1
			  ,YCHKDATE
			  ,TO_CHAR(YCIMCODE) YCIMCODE
			  ,TO_CHAR(YCHINNM) YCHINNM
			  ,TO_CHAR(YCGBUMONCD) YCGBUMONCD
			  ,YCTACODE
			  ,YCKSSICODE
			  ,YCSINAME011
			  ,YCSINAME021
			  ,YCODDUEDT
			  ,YCSTRINQTY
			  ,YCSTRINTANK
			  ,YCSTRKIN
			  ,YCACNO
			  ,YCACHOJO
			  ,YCLOTNO
			  ,YCODRKQTY
			  ,YCSSHRQTY
			  ,YCODUKEBI
			  ,YCODSTATUS
			  ,YCSPHCMT
			  ,YCPROJECTCD
			  ,YCSSODRNO
			  ,YCKOUTEICD
		FROM   SIAP_CAYC
		--         WHERE  YCHKDATE BETWEEN stdt AND eddt -- 作業日が当月
		WHERE  YCHKDATE BETWEEN stdt AND eddt -- 発注日が当月
		OR     YCODUKEBI BETWEEN stdt AND eddt; -- 受入完了日が当月
	rtCAYC curCAYC%ROWTYPE;
	CNT    NUMBER;
BEGIN
	-- 対象件数調査
	SELECT COUNT(*)
	INTO   P_NUM_CNT
	FROM   SIAP_CAYC --WHERE YCHKDATE BETWEEN stdt AND eddt; -- 作業日が当月
	WHERE  YCHKDATE BETWEEN stdt AND eddt -- 発注日が当月
	OR     YCODUKEBI BETWEEN stdt AND eddt; -- 受入完了日が当月
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYCPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYC@SIAPSOM
	WHERE  YCPROCFLG = 9
	AND    YCSTATUS IN (1, 2)
		  --  AND    YCHKDATE BETWEEN stdt AND eddt -- 作業日が当月
	AND    (YCHKDATE BETWEEN stdt AND eddt -- 発注日が当月
		  OR YCODUKEBI BETWEEN stdt AND eddt) -- 受入完了日が当月
	AND    YCCOCODE = '001'
	AND    YCHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYC@SIAPSOM
	SET    YCPROCFLG = 9
		  ,YCSTATUS  = 0
		  ,YCERRMSG  = NULL
	--  WHERE  YCHKDATE BETWEEN stdt AND eddt -- 作業日が当月
	WHERE  (YCHKDATE BETWEEN stdt AND eddt -- 発注日が当月
		   OR YCODUKEBI BETWEEN stdt AND eddt) -- 受入完了日が当月
	AND    YCCOCODE = '001'
	AND    YCHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	/*  MERGE INTO SIAP.IFYC@SIAPSOM YC
    USING (SELECT YCCOCODE
           ,YCHBRCODE
           ,YCKESANKI
           ,TO_CHAR(YCODODRNO1) YCODODRNO1
           ,YCHKDATE
           ,TO_CHAR(YCIMCODE) YCIMCODE
           ,TO_CHAR(YCHINNM) YCHINNM
           ,TO_CHAR(YCGBUMONCD) YCGBUMONCD
           ,YCTACODE
           ,YCKSSICODE
           ,YCSINAME011
           ,YCSINAME021
           ,YCODDUEDT
           ,YCSTRINQTY
           ,YCSTRINTANK
           ,YCSTRKIN
           ,YCACNO
           ,YCACHOJO
           ,YCLOTNO
           ,YCODRKQTY
           ,YCSSHRQTY
           ,YCODUKEBI
           ,YCODSTATUS
           ,YCSPHCMT
           ,YCPROJECTCD
           ,YCSSODRNO
           ,YCKOUTEICD
         FROM   SIAP_CAYC
         --         WHERE  YCHKDATE BETWEEN stdt AND eddt -- 作業日が当月
         WHERE  YCHKDATE BETWEEN stdt AND eddt -- 発注日が当月
         OR     YCODUKEBI BETWEEN stdt AND eddt -- 受入完了日が当月
         ) AC
    ON (YC.YCCOCODE = AC.YCCOCODE -- 会社コード
    AND YC.YCHBRCODE = AC.YCHBRCODE -- 本支店コード
    AND YC.YCODODRNO1 = AC.YCODODRNO1 -- 注文番号
    )
    WHEN MATCHED THEN
      UPDATE
      SET    YC.YCIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
          ,YC.YCIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
          ,YC.YCPROCFLG   = 1 -- 処理フラグ
          ,YC.YCHKDATE    = AC.YCHKDATE -- 発注日
          ,YC.YCIMCODE    = AC.YCIMCODE -- 品目コード
          ,YC.YCHINNM     = AC.YCHINNM -- 品目名称
          ,YC.YCGBUMONCD  = AC.YCGBUMONCD -- 原価部門コード
          ,YC.YCSTRINQTY  = AC.YCSTRINQTY -- 数量
          ,YC.YCSTRINTANK = AC.YCSTRINTANK -- 単価
          ,YC.YCSTRKIN    = AC.YCSTRKIN -- 金額
          ,YC.YCODUKEBI   = AC.YCODUKEBI -- 受入完了日
          ,YC.YCODSTATUS  = AC.YCODSTATUS -- 発注ステータス
          ,YC.YCUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
          ,YC.YCUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
          ,YC.YCUPID      = P_STR_TANTO_CD
    WHEN NOT MATCHED THEN
      INSERT
        (YCIMPDATE -- 登録日(YYYYMMDD)
        ,YCIMPTIME -- 登録時間(HH24MI)
        ,YCPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
        ,YCCOCODE -- 会社コード( ◎ ※共通仕様参照。)
        ,YCHBRCODE -- 本支店コード(※共通仕様参照。)
        ,YCKESANKI -- 決算期(※共通仕様参照。)
        ,YCODODRNO1 -- 注文番号
        ,YCHKDATE -- 発注日(YYYYMMDD)
        ,YCIMCODE -- 品目コード(20桁以内)
        ,YCHINNM -- 品目名称(未使用 NULL)
        ,YCGBUMONCD -- 原価部門コード
        ,YCTACODE -- 担当者コード(未使用 NULL)
        ,YCKSSICODE -- 仕入先コード(未使用 NULL)
        ,YCSINAME011 -- 仕入先名称１(未使用 NULL)
        ,YCSINAME021 -- 仕入先名称２(未使用 NULL)
        ,YCODDUEDT -- 希望納期(未使用 0)
        ,YCSTRINQTY -- 数量
        ,YCSTRINTANK -- 単価
        ,YCSTRKIN -- 金額
        ,YCACNO -- 勘定科目コード(未使用 NULL)
        ,YCACHOJO -- 補助科目コード(未使用 NULL)
        ,YCLOTNO -- ロット番号(未使用 NULL)
        ,YCODRKQTY -- 累計受入数(未使用 0)
        ,YCSSHRQTY -- 不良数(未使用 0)
        ,YCODUKEBI -- 受入完了日(発注ステータスが2:完納の場合、受入完了日(YYYYMMDD)その他のとき、0)
        ,YCODSTATUS -- 発注ステータス(0:注文書発行 1:分納 2:完納)
        ,YCSPHCMT -- 摘要(未使用 NULL)
        ,YCPROJECTCD -- プロジェクトコード(未使用 NULL)
        ,YCSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
        ,YCKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
        ,YCIPDATE -- 登録日
        ,YCIPTIME -- 登録時間
        ,YCIPID -- 登録者ＩＤ
        ,YCUPDATE -- 更新日
        ,YCUPTIME -- 更新時間
        ,YCUPID -- 更新者ＩＤ
         )
      VALUES
        (TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
        ,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
        ,0 -- 処理フラグ(0:新規)
        ,AC.YCCOCODE -- 会社コード( ◎ ※共通仕様参照。)
        ,AC.YCHBRCODE -- 本支店コード(※共通仕様参照。)
        ,AC.YCKESANKI -- 決算期(※共通仕様参照。)
        ,AC.YCODODRNO1 -- 注文番号
        ,AC.YCHKDATE -- 発注日(YYYYMMDD)
        ,AC.YCIMCODE -- 品目コード(20桁以内)
        ,AC.YCHINNM -- 品目名称(未使用 NULL)
        ,AC.YCGBUMONCD -- 原価部門コード
        ,AC.YCTACODE -- 担当者コード(未使用 NULL)
        ,AC.YCKSSICODE -- 仕入先コード(未使用 NULL)
        ,AC.YCSINAME011 -- 仕入先名称１(未使用 NULL)
        ,AC.YCSINAME021 -- 仕入先名称２(未使用 NULL)
        ,AC.YCODDUEDT -- 希望納期(未使用 0)
        ,AC.YCSTRINQTY -- 数量
        ,AC.YCSTRINTANK -- 単価
        ,AC.YCSTRKIN -- 金額
        ,AC.YCACNO -- 勘定科目コード(未使用 NULL)
        ,AC.YCACHOJO -- 補助科目コード(未使用 NULL)
        ,AC.YCLOTNO -- ロット番号(未使用 NULL)
        ,AC.YCODRKQTY -- 累計受入数(未使用 0)
        ,AC.YCSSHRQTY -- 不良数(未使用 0)
        ,AC.YCODUKEBI -- 受入完了日(発注ステータスが2:完納の場合、受入完了日(YYYYMMDD)その他のとき、0)
        ,AC.YCODSTATUS -- 発注ステータス(0:注文書発行 1:分納 2:完納)
        ,AC.YCSPHCMT -- 摘要(未使用 NULL)
        ,AC.YCPROJECTCD -- プロジェクトコード(未使用 NULL)
        ,AC.YCSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
        ,AC.YCKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
        ,TO_CHAR(SYSDATE, 'YYYYMMDD')
        ,TO_CHAR(SYSDATE, 'HH24MISS')
        ,P_STR_TANTO_CD
        ,TO_CHAR(SYSDATE, 'YYYYMMDD')
        ,TO_CHAR(SYSDATE, 'HH24MISS')
        ,P_STR_TANTO_CD);*/
	OPEN curCAYC;
	LOOP
		FETCH curCAYC
			INTO rtCAYC;
	
		IF curCAYC%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		SELECT COUNT(*)
		INTO   CNT
		FROM   SIAP.IFYC@SIAPSOM
		WHERE  YCCOCODE = rtCAYC.YCCOCODE -- 会社コード
		AND    YCHBRCODE = rtCAYC.YCHBRCODE -- 本支店コード
		AND    YCODODRNO1 = rtCAYC.YCODODRNO1; -- 注文番号
	
		IF CNT = 0 THEN
			INSERT INTO SIAP.IFYC@SIAPSOM
				(YCIMPDATE -- 登録日(YYYYMMDD)
				,YCIMPTIME -- 登録時間(HH24MI)
				,YCPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
				,YCCOCODE -- 会社コード( ◎ ※共通仕様参照。)
				,YCHBRCODE -- 本支店コード(※共通仕様参照。)
				,YCKESANKI -- 決算期(※共通仕様参照。)
				,YCODODRNO1 -- 注文番号
				,YCHKDATE -- 発注日(YYYYMMDD)
				,YCIMCODE -- 品目コード(20桁以内)
				,YCHINNM -- 品目名称(未使用 NULL)
				,YCGBUMONCD -- 原価部門コード
				,YCTACODE -- 担当者コード(未使用 NULL)
				,YCKSSICODE -- 仕入先コード(未使用 NULL)
				,YCSINAME011 -- 仕入先名称１(未使用 NULL)
				,YCSINAME021 -- 仕入先名称２(未使用 NULL)
				,YCODDUEDT -- 希望納期(未使用 0)
				,YCSTRINQTY -- 数量
				,YCSTRINTANK -- 単価
				,YCSTRKIN -- 金額
				,YCACNO -- 勘定科目コード(未使用 NULL)
				,YCACHOJO -- 補助科目コード(未使用 NULL)
				,YCLOTNO -- ロット番号(未使用 NULL)
				,YCODRKQTY -- 累計受入数(未使用 0)
				,YCSSHRQTY -- 不良数(未使用 0)
				,YCODUKEBI -- 受入完了日(発注ステータスが2:完納の場合、受入完了日(YYYYMMDD)その他のとき、0)
				,YCODSTATUS -- 発注ステータス(0:注文書発行 1:分納 2:完納)
				,YCSPHCMT -- 摘要(未使用 NULL)
				,YCPROJECTCD -- プロジェクトコード(未使用 NULL)
				,YCSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
				,YCKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
				,YCIPDATE -- 登録日
				,YCIPTIME -- 登録時間
				,YCIPID -- 登録者ＩＤ
				,YCUPDATE -- 更新日
				,YCUPTIME -- 更新時間
				,YCUPID) -- 更新者ＩＤ
			VALUES
				(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
				,0 -- 処理フラグ(0:新規)
				,rtCAYC.YCCOCODE -- 会社コード( ◎ ※共通仕様参照。)
				,rtCAYC.YCHBRCODE -- 本支店コード(※共通仕様参照。)
				,rtCAYC.YCKESANKI -- 決算期(※共通仕様参照。)
				,rtCAYC.YCODODRNO1 -- 注文番号
				,rtCAYC.YCHKDATE -- 発注日(YYYYMMDD)
				,rtCAYC.YCIMCODE -- 品目コード(20桁以内)
				,rtCAYC.YCHINNM -- 品目名称(未使用 NULL)
				,rtCAYC.YCGBUMONCD -- 原価部門コード
				,rtCAYC.YCTACODE -- 担当者コード(未使用 NULL)
				,rtCAYC.YCKSSICODE -- 仕入先コード(未使用 NULL)
				,rtCAYC.YCSINAME011 -- 仕入先名称１(未使用 NULL)
				,rtCAYC.YCSINAME021 -- 仕入先名称２(未使用 NULL)
				,rtCAYC.YCODDUEDT -- 希望納期(未使用 0)
				,rtCAYC.YCSTRINQTY -- 数量
				,rtCAYC.YCSTRINTANK -- 単価
				,rtCAYC.YCSTRKIN -- 金額
				,rtCAYC.YCACNO -- 勘定科目コード(未使用 NULL)
				,rtCAYC.YCACHOJO -- 補助科目コード(未使用 NULL)
				,rtCAYC.YCLOTNO -- ロット番号(未使用 NULL)
				,rtCAYC.YCODRKQTY -- 累計受入数(未使用 0)
				,rtCAYC.YCSSHRQTY -- 不良数(未使用 0)
				,rtCAYC.YCODUKEBI -- 受入完了日(発注ステータスが2:完納の場合、受入完了日(YYYYMMDD)その他のとき、0)
				 --              ,rtCAYC.YCODSTATUS -- 発注ステータス(0:注文書発行 1:分納 2:完納)
				 -- 受入完了日が指定月の場合はステータスを
				 -- 指定月でない場合は注文書発行
				,DECODE(SIGN(stdt - rtCAYC.YCODUKEBI)
					   ,-1
					   ,DECODE(SIGN(rtCAYC.YCODUKEBI - eddt)
							  ,-1
							  ,rtCAYC.YCODSTATUS
							  ,0)
					   ,0) -- 発注ステータス(0:注文書発行 1:分納 2:完納)
				,rtCAYC.YCSPHCMT -- 摘要(未使用 NULL)
				,rtCAYC.YCPROJECTCD -- プロジェクトコード(未使用 NULL)
				,rtCAYC.YCSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
				,rtCAYC.YCKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
				,TO_CHAR(SYSDATE, 'YYYYMMDD')
				,TO_CHAR(SYSDATE, 'HH24MISS')
				,P_STR_TANTO_CD
				,TO_CHAR(SYSDATE, 'YYYYMMDD')
				,TO_CHAR(SYSDATE, 'HH24MISS')
				,P_STR_TANTO_CD);
		ELSE
			UPDATE SIAP.IFYC@SIAPSOM
			SET    YCIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				  ,YCIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
				  ,YCPROCFLG   = 1 -- 処理フラグ
				  ,YCHKDATE    = rtCAYC.YCHKDATE -- 発注日
				  ,YCIMCODE    = rtCAYC.YCIMCODE -- 品目コード
				  ,YCHINNM     = rtCAYC.YCHINNM -- 品目名称
				  ,YCGBUMONCD  = rtCAYC.YCGBUMONCD -- 原価部門コード
				  ,YCSTRINQTY  = rtCAYC.YCSTRINQTY -- 数量
				  ,YCSTRINTANK = rtCAYC.YCSTRINTANK -- 単価
				  ,YCSTRKIN    = rtCAYC.YCSTRKIN -- 金額
				  ,YCODUKEBI   = rtCAYC.YCODUKEBI -- 受入完了日
				   --                ,YCODSTATUS  = rtCAYC.YCODSTATUS -- 発注ステータス
				   -- 受入完了日が指定月の場合はステータスを
				   -- 指定月でない場合は注文書発行
				  ,YCODSTATUS = DECODE(SIGN(stdt - rtCAYC.YCODUKEBI)
									  ,-1
									  ,DECODE(SIGN(rtCAYC.YCODUKEBI - eddt)
											 ,-1
											 ,rtCAYC.YCODSTATUS
											 ,0)
									  ,0) -- 発注ステータス(0:注文書発行 1:分納 2:完納)
				  ,YCUPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD')
				  ,YCUPTIME   = TO_CHAR(SYSDATE, 'HH24MISS')
				  ,YCUPID     = P_STR_TANTO_CD
			WHERE  YCCOCODE = rtCAYC.YCCOCODE -- 会社コード
			AND    YCHBRCODE = rtCAYC.YCHBRCODE -- 本支店コード
			AND    YCODODRNO1 = rtCAYC.YCODODRNO1; -- 注文番号
		END IF;
	END LOOP;
	CLOSE curCAYC;
	-- 戻り値設定
	P_NUM_RET := 0;
	COMMIT;
EXCEPTION
	-- 例外処理
	WHEN OTHERS THEN
		ROLLBACK;
		P_NUM_RET := 99;
		-- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYC'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYC;
/
