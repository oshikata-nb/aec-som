CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYD
(
	P_STR_TARGET_MONTH IN NVARCHAR2
   ,P_STR_TANTO_CD     IN NVARCHAR2
   ,P_NUM_CNT          OUT NUMBER
   ,P_NUM_RET          OUT NUMBER
) IS
	STDT NUMBER := P_STR_TARGET_MONTH || '00';
	EDDT NUMBER := P_STR_TARGET_MONTH || '99';
BEGIN
	-- 対象件数調査
	SELECT COUNT(*) INTO P_NUM_CNT FROM SIAP_CAYD WHERE YDTRSIDT BETWEEN STDT AND EDDT; -- 仕入日付が当月
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYDPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYD@SIAPSOM
	WHERE  YDPROCFLG = 9
	AND    YDSTATUS IN (1, 2)
	AND    YDTRSIDT BETWEEN STDT AND EDDT -- 仕入日付が当月
	AND    YDCOCODE  = '001'
	AND    YDHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYD@SIAPSOM
	SET    YDPROCFLG = 9
		  ,YDSTATUS  = 0
		  ,YDERRMSG  = NULL
	WHERE  YDTRSIDT BETWEEN STDT AND EDDT -- 仕入日付が当月
	AND    YDCOCODE  = '001'
	AND    YDHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	MERGE INTO SIAP.IFYD@SIAPSOM YD
	USING (SELECT YDCOCODE
				 ,YDHBRCODE
				 ,YDKESANKI
				 ,YDKGETSUDO
				 ,YDTRKUBUN
				 ,YDTRSIDT
				 ,TO_CHAR(YDPYTRDNO1) YDPYTRDNO1
				 ,YDGYOBAN
				 ,YDIMCODE
				 ,YDHINNM
				 ,YDGBUMONCD
				 ,TO_CHAR(YDTACODE) YDTACODE
				 ,TO_CHAR(YDKSSICODE) YDKSSICODE
				 ,YDSINAME011
				 ,YDSINAME021
				 ,YDODODRNO1
				 ,YDSTRINQTY
				 ,YDSTRINTANK
				 ,YDSTRKIN
				 ,YDSYHKBN
				 ,YDTRSYHKIN
				 ,YDODHTFLG
				 ,YDACNO
				 ,YDACHOJO
				 ,YDLOTNO
				 ,YDSPHCMT
				 ,YDTRKANNOU
				 ,YDTSKAKEDY
				 ,YDATACNO
				 ,YDATACHOJO
				 ,YDNODATE
				 ,YDSDENNO
				 ,YDHSMTDNO
				 ,YDHSMTGNO
				 ,YDPROJECTCD
				 ,YDSSODRNO
				 ,YDKOUTEICD
		   FROM   SIAP_CAYD
		   WHERE  YDTRSIDT BETWEEN STDT AND EDDT -- 仕入日付が当月
		   ) AD
	ON (YD.YDCOCODE = AD.YDCOCODE -- 会社コード
	AND YD.YDHBRCODE = AD.YDHBRCODE -- 本支店コード
	AND YD.YDPYTRDNO1 = AD.YDPYTRDNO1 -- 仕入伝票番号
	AND YD.YDGYOBAN = AD.YDGYOBAN -- 行番号
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YD.YDIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YD.YDIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YD.YDPROCFLG   = 1 -- 処理フラグ
			  ,YD.YDTRKUBUN   = AD.YDTRKUBUN -- 仕入区分
			  ,YD.YDTRSIDT    = AD.YDTRSIDT -- 仕入日付
			  ,YD.YDIMCODE    = AD.YDIMCODE -- 品目コード
			  ,YD.YDGBUMONCD  = AD.YDGBUMONCD -- 原価部門コード
			  ,YD.YDODODRNO1  = AD.YDODODRNO1 -- 注文番号
			  ,YD.YDSTRINQTY  = AD.YDSTRINQTY -- 数量
			  ,YD.YDSTRINTANK = AD.YDSTRINTANK -- 単価
			  ,YD.YDSTRKIN    = AD.YDSTRKIN -- 金額
			  ,YD.YDACNO      = AD.YDACNO -- 勘定科目コード
			  ,YD.YDTRKANNOU  = AD.YDTRKANNOU -- 完納区分(0:未完 1:完納)
			  ,YD.YDUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YD.YDUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YD.YDUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YDIMPDATE -- 登録日(YYYYMMDD)
			,YDIMPTIME -- 登録時間(HH24MI)
			,YDPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
			,YDCOCODE -- 会社コード(※共通仕様参照。)
			,YDHBRCODE -- 本支店コード(※共通仕様参照。)
			,YDKESANKI -- 決算期(※共通仕様参照。)
			,YDKGETSUDO -- 会計月度(未使用 0)
			,YDTRKUBUN -- 仕入区分(0..仕入 2..返品 4..支給 6..支給返納 7..現金仕入 8..現金返品)
			,YDTRSIDT -- 仕入日付(YYYYMMDD)
			,YDPYTRDNO1 -- 仕入伝票番号
			,YDGYOBAN -- 行番号
			,YDIMCODE -- 品目コード(20桁以内)
			,YDHINNM -- 品目名称(未使用 NULL)
			,YDGBUMONCD -- 原価部門コード
			,YDTACODE -- 担当者コード(未使用 NULL)
			,YDKSSICODE -- 仕入先コード(未使用 NULL)
			,YDSINAME011 -- 仕入先名称１(未使用 NULL)
			,YDSINAME021 -- 仕入先名称２(未使用 NULL)
			,YDODODRNO1 -- 注文番号
			,YDSTRINQTY -- 数量
			,YDSTRINTANK -- 単価
			,YDSTRKIN -- 金額
			,YDSYHKBN -- 消費税区分(未使用 0)
			,YDTRSYHKIN -- 消費税額(未使用 0)
			,YDODHTFLG -- 仮単価ＦＬＧ(未使用 0)
			,YDACNO -- 勘定科目コード
			,YDACHOJO -- 補助科目コード('0000000000')
			,YDLOTNO -- ロット番号(未使用 NULL)
			,YDSPHCMT -- 摘要(未使用 NULL)
			,YDTRKANNOU -- 完納区分(0:未完 1:完納)
			,YDTSKAKEDY -- 買掛更新日付(未使用 0)
			,YDATACNO -- 相手勘定科目コード(未使用 NULL)
			,YDATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YDNODATE -- 処理年月度(未使用 0)
			,YDSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YDHSMTDNO -- 発生元伝票番号(未使用 NULL)
			,YDHSMTGNO -- 発生元伝票番号行番号(未使用 0)
			,YDPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YDSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
			,YDKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
			,YDIPDATE -- 登録日
			,YDIPTIME -- 登録時間
			,YDIPID -- 登録者ＩＤ
			,YDUPDATE -- 更新日
			,YDUPTIME -- 更新時間
			,YDUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AD.YDCOCODE -- 会社コード(※共通仕様参照。)
			,AD.YDHBRCODE -- 本支店コード(※共通仕様参照。)
			,AD.YDKESANKI -- 決算期(※共通仕様参照。)
			,AD.YDKGETSUDO -- 会計月度(未使用 0)
			,AD.YDTRKUBUN -- 仕入区分(0..仕入 2..返品 4..支給 6..支給返納 7..現金仕入 8..現金返品)
			,AD.YDTRSIDT -- 仕入日付(YYYYMMDD)
			,AD.YDPYTRDNO1 -- 仕入伝票番号
			,AD.YDGYOBAN -- 行番号
			,AD.YDIMCODE -- 品目コード(20桁以内)
			,AD.YDHINNM -- 品目名称(未使用 NULL)
			,AD.YDGBUMONCD -- 原価部門コード
			,AD.YDTACODE -- 担当者コード(未使用 NULL)
			,AD.YDKSSICODE -- 仕入先コード(未使用 NULL)
			,AD.YDSINAME011 -- 仕入先名称１(未使用 NULL)
			,AD.YDSINAME021 -- 仕入先名称２(未使用 NULL)
			,AD.YDODODRNO1 -- 注文番号
			,AD.YDSTRINQTY -- 数量
			,AD.YDSTRINTANK -- 単価
			,AD.YDSTRKIN -- 金額
			,AD.YDSYHKBN -- 消費税区分(未使用 0)
			,AD.YDTRSYHKIN -- 消費税額(未使用 0)
			,AD.YDODHTFLG -- 仮単価ＦＬＧ(未使用 0)
			,AD.YDACNO -- 勘定科目コード
			,AD.YDACHOJO -- 補助科目コード('0000000000')
			,AD.YDLOTNO -- ロット番号(未使用 NULL)
			,AD.YDSPHCMT -- 摘要(未使用 NULL)
			,AD.YDTRKANNOU -- 完納区分(0:未完 1:完納)
			,AD.YDTSKAKEDY -- 買掛更新日付(未使用 0)
			,AD.YDATACNO -- 相手勘定科目コード(未使用 NULL)
			,AD.YDATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AD.YDNODATE -- 処理年月度(未使用 0)
			,AD.YDSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AD.YDHSMTDNO -- 発生元伝票番号(未使用 NULL)
			,AD.YDHSMTGNO -- 発生元伝票番号行番号(未使用 0)
			,AD.YDPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AD.YDSSODRNO -- 製造オーダー番号(工程内外注用　未使用 NULL)
			,AD.YDKOUTEICD -- 工程コード(工程内外注用　未使用 NULL)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);
	-- 戻り値設定
	P_NUM_RET := 0;
	COMMIT;
EXCEPTION
	-- 例外処理
	WHEN OTHERS THEN
		ROLLBACK;
		P_NUM_RET := 99;
		-- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYD', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYD;
/
