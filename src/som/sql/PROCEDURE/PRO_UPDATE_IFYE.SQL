CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYE
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
	SELECT COUNT(*) INTO P_NUM_CNT FROM SIAP_CAYE WHERE YEURSIDT BETWEEN STDT AND EDDT; -- 作業日が当月
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYEPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYE@SIAPSOM
	WHERE  YEPROCFLG = 9
	AND    YESTATUS IN (1, 2)
	AND    YEURSIDT BETWEEN STDT AND EDDT -- 作業日が当月
	AND    YECOCODE  = '001'
	AND    YEHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYE@SIAPSOM
	SET    YEPROCFLG = 9
		  ,YESTATUS  = 0
		  ,YEERRMSG  = NULL
	WHERE  YEURSIDT BETWEEN STDT AND EDDT -- 作業日が当月
	AND    YECOCODE  = '001'
	AND    YEHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	MERGE INTO SIAP.IFYE@SIAPSOM YE
	USING (SELECT YECOCODE
				 ,YEHBRCODE
				 ,YEURKUBUN
				 ,YEURSIDT
				 ,TO_CHAR(YEURIDNO) YEURIDNO
				 ,YEGYOBAN
				 ,TO_CHAR(YEIMCODE) YEIMCODE
				 ,YEHINNM
				 ,YESERIESCD
				 ,TO_CHAR(YEGBUMONCD) YEGBUMONCD
				 ,TO_CHAR(YETACODE) YETACODE
				 ,YEPLTSCODE
				 ,YETSNAME011
				 ,YETSNAME021
				 ,YEJUODRNO
				 ,YEJUGYOBAN
				 ,YESTRINQTY
				 ,YESTRINTANK
				 ,YESTRKIN
				 ,YESYHKBN
				 ,YETRSYHKIN
				 ,TO_CHAR(YEACNO) YEACNO
				 ,YEACHOJO
				 ,YESPHCMT
				 ,YETRKANNOU
				 ,YEATACNO
				 ,YEATACHOJO
				 ,YELOTNO
				 ,YESGMNTCD
				 ,YESGMNTCDA
				 ,YESGMNTCDB
				 ,YESGMNTCDC
				 ,YESGMNTCD2
				 ,YEPROJECTCD
				 ,YEHSMTDNO
				 ,YEHSMTGNO
				 ,YESDENCRDT
		   FROM   SIAP_CAYE
		   WHERE  YEURSIDT BETWEEN STDT AND EDDT -- 作業日が当月
		   ) AE
	ON (YE.YECOCODE = AE.YECOCODE -- 会社コード
	AND YE.YEHBRCODE = AE.YEHBRCODE -- 本支店コード
	AND YE.YEURIDNO = AE.YEURIDNO -- 売上伝票番号
	AND YE.YEGYOBAN = AE.YEGYOBAN -- 行番号
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YE.YEIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YE.YEIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YE.YEPROCFLG   = 1 -- 処理フラグ
			  ,YE.YEURKUBUN   = AE.YEURKUBUN -- 売上区分
			  ,YE.YEURSIDT    = AE.YEURSIDT -- 売上日付
			  ,YE.YEIMCODE    = AE.YEIMCODE -- 品目コード
			  ,YE.YESTRINQTY  = AE.YESTRINQTY -- 実際売上数量
			  ,YE.YESTRINTANK = AE.YESTRINTANK -- 実際売上単価
			  ,YE.YESTRKIN    = AE.YESTRKIN -- 実際売上金額
			  ,YE.YEUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YE.YEUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YE.YEUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YEIMPDATE -- 登録日(YYYYMMDD)
			,YEIMPTIME -- 登録時間(HH24MI)
			,YEPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
			,YECOCODE -- 会社コード(※共通仕様参照。)
			,YEHBRCODE -- 本支店コード(※共通仕様参照。)
			,YEURKUBUN -- 売上区分(0..売上 2..返品 6..値引 7..現金売上 8..現金返品)
			,YEURSIDT -- 売上日付(YYYYMMDD)
			,YEURIDNO -- 売上伝票番号
			,YEGYOBAN -- 行番号
			,YEIMCODE -- 品目コード(20桁以内)
			,YEHINNM -- 品目名称(未使用 NULL)
			,YESERIESCD -- 製品シリーズコード(未使用 NULL)
			,YEGBUMONCD -- 原価部門コード(未使用 NULL)
			,YETACODE -- 担当者コード(未使用 NULL)
			,YEPLTSCODE -- 得意先コード(未使用 NULL)
			,YETSNAME011 -- 得意先名称１(未使用 NULL)
			,YETSNAME021 -- 得意先名称２(未使用 NULL)
			,YEJUODRNO -- 受注番号(未使用 NULL)
			,YEJUGYOBAN -- 受注行番号(未使用 0)
			,YESTRINQTY -- 実際売上数量
			,YESTRINTANK -- 実際売上単価
			,YESTRKIN -- 実際売上金額
			,YESYHKBN -- 消費税区分(未使用 0)
			,YETRSYHKIN -- 消費税額(未使用 0)
			,YEACNO -- 勘定科目コード(未使用 NULL)
			,YEACHOJO -- 補助科目コード(未使用 NULL)
			,YESPHCMT -- 摘要(未使用 NULL)
			,YETRKANNOU -- 完納区分(未使用 0)
			,YEATACNO -- 相手勘定科目コード(未使用 NULL)
			,YEATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YELOTNO -- ロット番号(未使用 NULL)
			,YESGMNTCD -- セグメントコード(未使用 NULL)
			,YESGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,YESGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,YESGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,YESGMNTCD2 -- セグメントコード２(未使用 NULL)
			,YEPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YEHSMTDNO -- 発生元伝票番号(未使用 NULL)
			,YEHSMTGNO -- 発生元伝票番号行番号(未使用 0)
			,YESDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YEIPDATE -- 登録日
			,YEIPTIME -- 登録時間
			,YEIPID -- 登録者ＩＤ
			,YEUPDATE -- 更新日
			,YEUPTIME -- 更新時間
			,YEUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AE.YECOCODE -- 会社コード(※共通仕様参照。)
			,AE.YEHBRCODE -- 本支店コード(※共通仕様参照。)
			,AE.YEURKUBUN -- 売上区分(0..売上 2..返品 6..値引 7..現金売上 8..現金返品)
			,AE.YEURSIDT -- 売上日付(YYYYMMDD)
			,AE.YEURIDNO -- 売上伝票番号
			,AE.YEGYOBAN -- 行番号
			,AE.YEIMCODE -- 品目コード(20桁以内)
			,AE.YEHINNM -- 品目名称(未使用 NULL)
			,AE.YESERIESCD -- 製品シリーズコード(未使用 NULL)
			,AE.YEGBUMONCD -- 原価部門コード(未使用 NULL)
			,AE.YETACODE -- 担当者コード(未使用 NULL)
			,AE.YEPLTSCODE -- 得意先コード(未使用 NULL)
			,AE.YETSNAME011 -- 得意先名称１(未使用 NULL)
			,AE.YETSNAME021 -- 得意先名称２(未使用 NULL)
			,AE.YEJUODRNO -- 受注番号(未使用 NULL)
			,AE.YEJUGYOBAN -- 受注行番号(未使用 0)
			,AE.YESTRINQTY -- 実際売上数量
			,AE.YESTRINTANK -- 実際売上単価
			,AE.YESTRKIN -- 実際売上金額
			,AE.YESYHKBN -- 消費税区分(未使用 0)
			,AE.YETRSYHKIN -- 消費税額(未使用 0)
			,AE.YEACNO -- 勘定科目コード(未使用 NULL)
			,AE.YEACHOJO -- 補助科目コード(未使用 NULL)
			,AE.YESPHCMT -- 摘要(未使用 NULL)
			,AE.YETRKANNOU -- 完納区分(未使用 0)
			,AE.YEATACNO -- 相手勘定科目コード(未使用 NULL)
			,AE.YEATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AE.YELOTNO -- ロット番号(未使用 NULL)
			,AE.YESGMNTCD -- セグメントコード(未使用 NULL)
			,AE.YESGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,AE.YESGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,AE.YESGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,AE.YESGMNTCD2 -- セグメントコード２(未使用 NULL)
			,AE.YEPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AE.YEHSMTDNO -- 発生元伝票番号(未使用 NULL)
			,AE.YEHSMTGNO -- 発生元伝票番号行番号(未使用 0)
			,AE.YESDENCRDT -- 仕訳伝票作成日(未使用 0)
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
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYE', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYE;
/
