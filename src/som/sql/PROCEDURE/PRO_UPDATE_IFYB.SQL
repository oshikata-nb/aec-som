CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYB
(
	P_STR_TARGET_MONTH IN NVARCHAR2
   ,P_STR_TANTO_CD     IN NVARCHAR2
   ,P_NUM_CNT          OUT NUMBER
   ,P_NUM_RET          OUT NUMBER
) IS
	stdt NUMBER := P_STR_TARGET_MONTH || '00';
	eddt NUMBER := P_STR_TARGET_MONTH || '99';
BEGIN
	-- 対象件数調査
	SELECT COUNT(*) INTO P_NUM_CNT FROM SIAP_CAYB CAYB WHERE YBWJSGDT BETWEEN stdt AND eddt; -- 作業日が当月
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYBPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYB@SIAPSOM
	WHERE  YBPROCFLG = 9
	AND    YBSTATUS IN (1, 2)
	AND    YBWJSGDT BETWEEN stdt AND eddt -- 作業日が当月
	AND    YBCOCODE  = '001'
	AND    YBHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYB@SIAPSOM
	SET    YBPROCFLG = 9
		  ,YBSTATUS  = 0
		  ,YBERRMSG  = NULL
	WHERE  YBWJSGDT BETWEEN stdt AND eddt -- 作業日が当月
	AND    YBCOCODE  = '001'
	AND    YBHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	MERGE INTO SIAP.IFYB@SIAPSOM YB
	USING (SELECT YBCOCODE
				 ,YBHBRCODE
				 ,YBKESANKI
				 ,YBKGETSUDO
				 ,TO_CHAR(YBSGDENNO) YBSGDENNO
				 ,YBWJSGDT
				 ,YBGYOBAN
				 ,TO_CHAR(YBTACODE) YBTACODE
				 ,YBGBUMONCD
				 ,YBACTCD
				 ,YBCKHIKBN
				 ,YBKSEQNO
				 ,YBKOTEICD
				 ,YBSETUCD
				 ,YBSSODR
				 ,YBIMCODE
				 ,YBKNBUNKB
				 ,YBACNO
				 ,YBACHOJO
				 ,YBKAKOUKBN
				 ,YBWJINQTY
				 ,YBSTJDAY
				 ,YBSTJTIM
				 ,YBEDDAY
				 ,YBEDTIM
				 ,YBRMSAJIKAN
				 ,YBRMSAJIKANH
				 ,YBWJNINZU
				 ,YBKISTDAY
				 ,YBKISTTIM
				 ,YBKIEDDAY
				 ,YBKIEDTIM
				 ,YBRMKIJIKAN
				 ,YBRMKIJIKANH
				 ,YBYCHINRITSU
				 ,YBJCHINRITSU
				 ,YBYCHINGIN
				 ,YBJCHINGIN
				 ,YBHKTANK
				 ,YBSTRKIN
				 ,YBSPHCMT
				 ,YBSDENNO
				 ,YBSGYONO
				 ,YBSDENCRDT
				 ,YBKTBUNKB
				 ,YBKNMOVEFLG
				 ,YBKNMOVECMT
				 ,YBPROJECTCD
		   FROM   SIAP_CAYB CAYB
		   WHERE  YBWJSGDT BETWEEN stdt AND eddt) AB -- 作業日が当月
	ON (YB.YBCOCODE = AB.YBCOCODE -- 会社コード
	AND YB.YBHBRCODE = AB.YBHBRCODE -- 本支店コード
	AND YB.YBSGDENNO = AB.YBSGDENNO -- 作業伝票番号
	AND YB.YBGYOBAN = AB.YBGYOBAN -- 行番号
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YB.YBIMPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YB.YBIMPTIME    = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YB.YBPROCFLG    = 1 -- 処理フラグ
			  ,YB.YBWJSGDT     = AB.YBWJSGDT -- 作業日
			  ,YB.YBGBUMONCD   = AB.YBGBUMONCD -- 原価部門コード
			  ,YB.YBCKHIKBN    = AB.YBCKHIKBN -- 直接費・間接費区分
			  ,YB.YBKSEQNO     = AB.YBKSEQNO -- 工程順序
			  ,YB.YBKOTEICD    = AB.YBKOTEICD -- 工程コード
			  ,YB.YBSSODR      = AB.YBSSODR -- 製造オーダー番号
			  ,YB.YBIMCODE     = AB.YBIMCODE -- 品目コード
			  ,YB.YBRMSAJIKANH = AB.YBRMSAJIKANH -- 作業時間（H) 
			  ,YB.YBKTBUNKB    = AB.YBKTBUNKB -- 形態別分類(0..その他 1..材料費 2..労務費 3..経費 4..外注加工費)
			  ,YB.YBUPDATE     = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YB.YBUPTIME     = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YB.YBUPID       = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YBIMPDATE -- 登録日(YYYYMMDD)
			,YBIMPTIME -- 登録時間(HH24MI)
			,YBPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
			,YBCOCODE -- 会社コード( ◎ ※共通仕様参照。)
			,YBHBRCODE -- 本支店コード(※共通仕様参照。)
			,YBKESANKI -- 決算期(※共通仕様参照。)
			,YBKGETSUDO -- 会計月度(未使用 0)
			,YBSGDENNO -- 作業伝票番号(生産ライン＋製造日＋担当区分＋担当者コード）
			,YBWJSGDT -- 作業日(YYYYMMDD)
			,YBGYOBAN -- 行番号
			,YBTACODE -- 担当者コード(未使用 NULL)
			,YBGBUMONCD -- 原価部門コード
			,YBACTCD -- 活動コード(未使用 NULL)
			,YBCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,YBKSEQNO -- 工程順序
			,YBKOTEICD -- 工程コード
			,YBSETUCD -- 設備コード(未使用 NULL)
			,YBSSODR -- 製造オーダー番号
			,YBIMCODE -- 品目コード(20桁以内)
			,YBKNBUNKB -- 機能別分類(未使用 0)
			,YBACNO -- 勘定科目コード
			,YBACHOJO -- 補助科目コード('0000000000')
			,YBKAKOUKBN -- 加工時間種別(0:直接作業時間 1:機械作業時間)
			,YBWJINQTY -- 製造数量(未使用 0)
			,YBSTJDAY -- 作業開始日(未使用 0)
			,YBSTJTIM -- 作業開始時刻(未使用 0)
			,YBEDDAY -- 作業終了日(未使用 0)
			,YBEDTIM -- 作業終了時刻(未使用 0)
			,YBRMSAJIKAN -- 作業時間(分)
			,YBRMSAJIKANH -- 作業時間（H) 
			,YBWJNINZU -- 作業人数(未使用 0)
			,YBKISTDAY -- 稼動開始日(未使用 0)
			,YBKISTTIM -- 稼動開始時刻(未使用 0)
			,YBKIEDDAY -- 稼動終了日(未使用 0)
			,YBKIEDTIM -- 稼動終了時刻(未使用 0)
			,YBRMKIJIKAN -- 稼動時間(分)
			,YBRMKIJIKANH -- 稼動時間(H) 
			,YBYCHINRITSU -- 予定賃率(未使用 0)
			,YBJCHINRITSU -- 実際賃率(未使用 0)
			,YBYCHINGIN -- 賃金予定額(未使用 0)
			,YBJCHINGIN -- 賃金実際額(未使用 0)
			,YBHKTANK -- 標準加工費単価(未使用 0)
			,YBSTRKIN -- 実際仕入金額(未使用 0)
			,YBSPHCMT -- 摘要(未使用 NULL)
			,YBSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YBSGYONO -- 仕訳伝票行番号(未使用 0)
			,YBSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YBKTBUNKB -- 形態別分類(0..その他 1..材料費 2..労務費 3..経費 4..外注加工費)
			,YBKNMOVEFLG -- 間接費移動フラグ(未使用 0)
			,YBKNMOVECMT -- 間接費移動摘要(未使用 NULL)
			,YBPROJECTCD -- プロジェクトコード(未使用 NULL
			,YBIPDATE -- 登録日
			,YBIPTIME -- 登録時間
			,YBIPID -- 登録者ＩＤ
			,YBUPDATE -- 更新日
			,YBUPTIME -- 更新時間
			,YBUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AB.YBCOCODE -- 会社コード( ◎ ※共通仕様参照。)
			,AB.YBHBRCODE -- 本支店コード(※共通仕様参照。)
			,AB.YBKESANKI -- 決算期(※共通仕様参照。)
			,AB.YBKGETSUDO -- 会計月度(未使用 0)
			,AB.YBSGDENNO -- 作業伝票番号(生産ライン＋製造日＋担当区分＋担当者コード）
			,AB.YBWJSGDT -- 作業日(YYYYMMDD)
			,AB.YBGYOBAN -- 行番号
			,AB.YBTACODE -- 担当者コード(未使用 NULL)
			,AB.YBGBUMONCD -- 原価部門コード
			,AB.YBACTCD -- 活動コード(未使用 NULL)
			,AB.YBCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,AB.YBKSEQNO -- 工程順序
			,AB.YBKOTEICD -- 工程コード
			,AB.YBSETUCD -- 設備コード(未使用 NULL)
			,AB.YBSSODR -- 製造オーダー番号
			,AB.YBIMCODE -- 品目コード(20桁以内)
			,AB.YBKNBUNKB -- 機能別分類(未使用 0)
			,AB.YBACNO -- 勘定科目コード
			,AB.YBACHOJO -- 補助科目コード('0000000000')
			,AB.YBKAKOUKBN -- 加工時間種別(0:直接作業時間 1:機械作業時間)
			,AB.YBWJINQTY -- 製造数量(未使用 0)
			,AB.YBSTJDAY -- 作業開始日(未使用 0)
			,AB.YBSTJTIM -- 作業開始時刻(未使用 0)
			,AB.YBEDDAY -- 作業終了日(未使用 0)
			,AB.YBEDTIM -- 作業終了時刻(未使用 0)
			,AB.YBRMSAJIKAN -- 作業時間(分)
			,AB.YBRMSAJIKANH -- 作業時間（H) 
			,AB.YBWJNINZU -- 作業人数(未使用 0)
			,AB.YBKISTDAY -- 稼動開始日(未使用 0)
			,AB.YBKISTTIM -- 稼動開始時刻(未使用 0)
			,AB.YBKIEDDAY -- 稼動終了日(未使用 0)
			,AB.YBKIEDTIM -- 稼動終了時刻(未使用 0)
			,AB.YBRMKIJIKAN -- 稼動時間(分)
			,AB.YBRMKIJIKANH -- 稼動時間(H) 
			,AB.YBYCHINRITSU -- 予定賃率(未使用 0)
			,AB.YBJCHINRITSU -- 実際賃率(未使用 0)
			,AB.YBYCHINGIN -- 賃金予定額(未使用 0)
			,AB.YBJCHINGIN -- 賃金実際額(未使用 0)
			,AB.YBHKTANK -- 標準加工費単価(未使用 0)
			,AB.YBSTRKIN -- 実際仕入金額(未使用 0)
			,AB.YBSPHCMT -- 摘要(未使用 NULL)
			,AB.YBSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AB.YBSGYONO -- 仕訳伝票行番号(未使用 0)
			,AB.YBSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,AB.YBKTBUNKB -- 形態別分類(0..その他 1..材料費 2..労務費 3..経費 4..外注加工費)
			,AB.YBKNMOVEFLG -- 間接費移動フラグ(未使用 0)
			,AB.YBKNMOVECMT -- 間接費移動摘要(未使用 NULL)
			,AB.YBPROJECTCD -- プロジェクトコード(未使用 NULL
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
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYB', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYB;
/
