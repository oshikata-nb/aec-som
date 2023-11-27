CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYF
(
	P_STR_TARGET_MONTH IN NVARCHAR2
   ,P_STR_TANTO_CD     IN NVARCHAR2
   ,P_NUM_CNT          OUT NUMBER
   ,P_NUM_RET          OUT NUMBER
) IS
	/******************************** 宣言部 ********************************/
	-- カーソルの宣言
	CURSOR ifyf_cur IS
		SELECT YFDENNO
		--  FROM   IFYF@SIAPSOM IFYF3
		FROM   AP21.TEMP_IFYF IFYF3
		WHERE  EXISTS
		 (SELECT DISTINCT YFDENNO
				--      FROM   IFYF@SIAPSOM IFYF1
				FROM   AP21.TEMP_IFYF IFYF1
				WHERE  YFDENNO > (SELECT MIN(YFDENNO)
								  --                FROM   IFYF@SIAPSOM IFYF2
								  FROM   AP21.TEMP_IFYF IFYF2
								  WHERE  IFYF1.YFIMCODE = IFYF2.YFIMCODE -- 品目コード
								  AND    IFYF1.YFFUDATE = IFYF2.YFFUDATE -- 伝票日付
								  AND    IFYF1.YFGYOBAN = IFYF2.YFGYOBAN -- 行番号
								  AND    IFYF1.YFSZNYUKB = IFYF2.YFSZNYUKB -- 入出庫区分
								  AND    IFYF1.YFTWSYORI = IFYF2.YFTWSYORI -- 処理区分
								  AND    IFYF1.YFTWQTY = IFYF2.YFTWQTY -- 入出庫数
								  AND    IFYF1.YFHSMKING = IFYF2.YFHSMKING -- 発生元金額
								  AND    IFYF1.YFGYOBAN > 1)
				AND    IFYF3.YFDENNO = IFYF1.YFDENNO);

	-- 変数の宣言
	stdt NUMBER := P_STR_TARGET_MONTH || '00';
	eddt NUMBER := P_STR_TARGET_MONTH || '99';
	cnt1 NUMBER := 0;
	cnt2 NUMBER := 0;
	cnt3 NUMBER := 0;

	-- 重複レコード削除用
	TYPE denno IS TABLE OF AP21.TEMP_IFYF.YFDENNO%TYPE INDEX BY BINARY_INTEGER;
	duplication_denno denno;

	/******************************** 処理部 ********************************/
BEGIN
	-- 対象件数調査
	SELECT COUNT(*)
	INTO   cnt1
	FROM   (SELECT *
			FROM   SIAP_CAYF
			WHERE  YFFUDATE BETWEEN stdt AND eddt -- 伝票日付が当月
			AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1));

	-- 仕入直送品の件数
	-- 仕入直送品は「払い」がないので後で自動生成する為件数を余分にカウントする
	SELECT COUNT(*)
	INTO   cnt2
	FROM   (SELECT *
			FROM   SIAP_CAYF
			WHERE  YFFUDATE BETWEEN stdt AND eddt -- 伝票日付が当月
			AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)
			AND    (YFIMCODE LIKE '04%' OR YFIMCODE LIKE '14%' OR
				  YFIMCODE LIKE '24%'));

	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYFPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYF@SIAPSOM
	WHERE  YFPROCFLG = 9
	AND    YFSTATUS IN (1, 2)
	AND    YFFUDATE BETWEEN stdt AND eddt -- 伝票日付が当月
	AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)
	AND    YFCOCODE = '001'
	AND    YFHBRCODE = '001';

	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYF@SIAPSOM
	SET    YFPROCFLG = 9
		  ,YFSTATUS  = 0
		  ,YFERRMSG  = NULL
	WHERE  YFFUDATE BETWEEN stdt AND eddt -- 伝票日付が当月
	AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)
	AND    YFCOCODE = '001'
	AND    YFHBRCODE = '001';

	-- tempテーブルクリア
	DELETE FROM AP21.TEMP_IFYF;

	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	--  MERGE INTO SIAP.IFYF@SIAPSOM YF
	MERGE INTO AP21.TEMP_IFYF YF
	USING (SELECT *
		   FROM   SIAP_CAYF
		   WHERE  YFFUDATE BETWEEN stdt AND eddt -- 作業日が当月
		   AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)) AF
	ON (YF.YFCOCODE = AF.YFCOCODE -- 会社コード
	AND YF.YFHBRCODE = AF.YFHBRCODE -- 本支店コード
	AND YF.YFIMCODE = AF.YFIMCODE -- 品目コード
	AND YF.YFFUDATE = AF.YFFUDATE -- 伝票日付
	AND YF.YFDENNO = AF.YFDENNO -- 伝票番号
	AND YF.YFGYOBAN = AF.YFGYOBAN -- 行番号
	AND YF.YFSZNYUKB = AF.YFSZNYUKB -- 入出庫区分
	AND YF.YFTWSYORI = AF.YFTWSYORI -- 処理区分
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YF.YFIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YF.YFIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YF.YFPROCFLG   = 1 -- 処理フラグ
			  ,YF.YFHINKBN    = AF.YFHINKBN -- 品目区分
			  ,YF.YFGBUMONCD  = AF.YFGBUMONCD -- 原価部門コード
			  ,YF.YFTWQTY     = AF.YFTWQTY -- 入出庫数
			  ,YF.YFYTTANK    = AF.YFYTTANK -- 予定単価
			  ,YF.YFYTKING    = AF.YFYTKING -- 予定金額
			  ,YF.YFIATANK    = AF.YFIATANK -- 実際単価
			  ,YF.YFIAKING    = AF.YFIAKING -- 実際金額
			  ,YF.YFACNO      = AF.YFACNO -- 勘定科目コード
			  ,YF.YFSSODRNO   = AF.YFSSODRNO -- 製造オーダー番号
			  ,YF.YFODRKB     = AF.YFODRKB -- オーダー区分
			  ,YF.YFKOTEICD   = AF.YFKOTEICD -- 工程コード
			  ,YF.YFOHINCD    = AF.YFOHINCD -- 親品目コード
			  ,YF.YFKSEQNO    = AF.YFKSEQNO -- 工程順序
			  ,YF.YFSODRSYUBT = AF.YFSODRSYUBT -- 製造オーダー種別
			  ,YF.YFKNBUNKB   = AF.YFKNBUNKB -- 機能別分類（職能）
			  ,YF.YFRIYCD     = AF.YFRIYCD -- 理由コード
			  ,YF.YFUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YF.YFUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YF.YFUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YFIMPDATE -- 登録日(YYYYMMDD)
			,YFIMPTIME -- 登録時間(HH24MI)
			,YFPROCFLG -- 処理フラグ(0:新規)
			,YFCOCODE -- 会社コード(※共通仕様参照。)
			,YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,YFIMCODE -- 品目コード(20桁以内)
			,YFHINNM -- 品目名称(未使用 NULL)
			,YFFUDATE -- 伝票日付(YYYYMMDD)
			,YFDENNO -- 伝票番号
			,YFGYOBAN -- 行番号
			,YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,YFTWSYORI -- 処理区分
			,YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,YFGBUMONCD -- 原価部門コード
			,YFTWQTY -- 入出庫数
			,YFHSMTANK -- 発生元単価(未使用 0)
			,YFHSMKING -- 発生元金額(未使用 0)
			,YFYTTANK -- 予定単価
			,YFYTKING -- 予定金額
			,YFIATANK -- 実際単価
			,YFIAKING -- 実際金額
			,YFSTDQTY -- 標準数量(未使用 0)
			,YFSTDTANK -- 標準単価(未使用 0)
			,YFSTDKING -- 標準金額(未使用 0)
			,YFACNO -- 勘定科目コード
			,YFACHOJO -- 補助科目コード('0000000000')
			,YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,YFSPHCMT -- 摘要(未使用 NULL)
			,YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,YFSSHRQTY -- 不良数(未使用 0)
			,YFNODATE -- 処理年月度(未使用 0)
			,YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFLOTNO -- ロット番号(未使用 NULL)
			,YFSGMNTCD -- セグメントコード(未使用 NULL)
			,YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,YFSGMNTCD2 -- 組コード(未使用 NULL)
			,YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,YFSSONKBN -- 仕損区分(未使用 0)
			,YFDELFLG -- 削除フラグ(未使用 0)
			,YFHTFLG -- 単価未設定フラグ(未使用 0)
			,YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,YFSIKYU -- 支給区分(未使用 0)
			,YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,YFTBKBN -- 取引先区分(未使用 0)
			,YFTBCODE -- 取引先コード(未使用 NULL)
			,YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,YFPRGID -- プログラムＩＤ(未使用 NULL)
			,YFDSNO -- エントリ処理番号(未使用 NULL)
			,YFSYMD -- エントリ処理日(未使用 0)
			,YFHINSEQNO -- 品目順序(未使用 0)
			,YFTACODE -- 担当者コード(未使用 NULL)
			,YFSYHKBN -- 消費税区分(未使用 0)
			,YFTRSYHKIN -- 消費税額(未使用 0)
			,YFTNKBN -- ロケーション区分(未使用 0)
			,YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,YFIPDATE -- 登録日
			,YFIPTIME -- 登録時間
			,YFIPID -- 登録者ＩＤ
			,YFUPDATE -- 更新日
			,YFUPTIME -- 更新時間
			,YFUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AF.YFCOCODE -- 会社コード(※共通仕様参照。)
			,AF.YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,AF.YFIMCODE -- 品目コード(20桁以内)
			,AF.YFHINNM -- 品目名称(未使用 NULL)
			,TO_CHAR(AF.YFFUDATE) -- 伝票日付(YYYYMMDD)
			,AF.YFDENNO -- 伝票番号
			,AF.YFGYOBAN -- 行番号
			,AF.YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,AF.YFTWSYORI -- 処理区分
			,AF.YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,AF.YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,AF.YFGBUMONCD -- 原価部門コード
			,AF.YFTWQTY -- 入出庫数
			,AF.YFHSMTANK -- 発生元単価(未使用 0)
			,AF.YFHSMKING -- 発生元金額(未使用 0)
			,AF.YFYTTANK -- 予定単価
			,AF.YFYTKING -- 予定金額
			,AF.YFIATANK -- 実際単価
			,AF.YFIAKING -- 実際金額
			,AF.YFSTDQTY -- 標準数量(未使用 0)
			,AF.YFSTDTANK -- 標準単価(未使用 0)
			,AF.YFSTDKING -- 標準金額(未使用 0)
			,AF.YFACNO -- 勘定科目コード
			,AF.YFACHOJO -- 補助科目コード('0000000000')
			,AF.YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,AF.YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,AF.YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,AF.YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,AF.YFSPHCMT -- 摘要(未使用 NULL)
			,AF.YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,AF.YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,AF.YFSSHRQTY -- 不良数(未使用 0)
			,AF.YFNODATE -- 処理年月度(未使用 0)
			,AF.YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFLOTNO -- ロット番号(未使用 NULL)
			,AF.YFSGMNTCD -- セグメントコード(未使用 NULL)
			,AF.YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,AF.YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,AF.YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,AF.YFSGMNTCD2 -- 組コード(未使用 NULL)
			,AF.YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,AF.YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,AF.YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,AF.YFSSONKBN -- 仕損区分(未使用 0)
			,AF.YFDELFLG -- 削除フラグ(未使用 0)
			,AF.YFHTFLG -- 単価未設定フラグ(未使用 0)
			,AF.YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,AF.YFSIKYU -- 支給区分(未使用 0)
			,AF.YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,AF.YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AF.YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,AF.YFTBKBN -- 取引先区分(未使用 0)
			,AF.YFTBCODE -- 取引先コード(未使用 NULL)
			,AF.YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,AF.YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,AF.YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AF.YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,AF.YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,AF.YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,AF.YFPRGID -- プログラムＩＤ(未使用 NULL)
			,AF.YFDSNO -- エントリ処理番号(未使用 NULL)
			,AF.YFSYMD -- エントリ処理日(未使用 0)
			,AF.YFHINSEQNO -- 品目順序(未使用 0)
			,AF.YFTACODE -- 担当者コード(未使用 NULL)
			,AF.YFSYHKBN -- 消費税区分(未使用 0)
			,AF.YFTRSYHKIN -- 消費税額(未使用 0)
			,AF.YFTNKBN -- ロケーション区分(未使用 0)
			,AF.YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AF.YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);

	-- 仕入直送品「売上」データ生成
	--  MERGE INTO SIAP.IFYF@SIAPSOM YF
	MERGE INTO AP21.TEMP_IFYF YF
	USING (SELECT *
		   FROM   SIAP_CAYF
		   WHERE  YFFUDATE BETWEEN stdt AND eddt -- 作業日が当月
		   AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)
		   AND    (YFIMCODE LIKE '04%' OR YFIMCODE LIKE '14%' OR
				 YFIMCODE LIKE '24%')
		   AND    YFTWSYORI < 10) AF -- 仕入を対象
	ON (YF.YFCOCODE = AF.YFCOCODE -- 会社コード
	AND YF.YFHBRCODE = AF.YFHBRCODE -- 本支店コード
	AND YF.YFIMCODE = AF.YFIMCODE -- 品目コード
	AND YF.YFFUDATE = AF.YFFUDATE -- 伝票日付
	AND YF.YFDENNO = AF.YFDENNO -- 伝票番号
	AND YF.YFGYOBAN = AF.YFGYOBAN -- 行番号
	AND YF.YFSZNYUKB = 1 -- 入出庫区分(1:出庫)
	AND YF.YFTWSYORI = 2 -- 処理区分(2:売上)
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YF.YFIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YF.YFIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YF.YFPROCFLG   = 1 -- 処理フラグ
			  ,YF.YFHINKBN    = AF.YFHINKBN -- 品目区分
			  ,YF.YFGBUMONCD  = AF.YFGBUMONCD -- 原価部門コード
			  ,YF.YFTWQTY     = AF.YFTWQTY -- 入出庫数
			  ,YF.YFYTTANK    = AF.YFYTTANK -- 予定単価
			  ,YF.YFYTKING    = AF.YFYTKING -- 予定金額
			  ,YF.YFIATANK    = AF.YFIATANK -- 実際単価
			  ,YF.YFIAKING    = AF.YFIAKING -- 実際金額
			  ,YF.YFACNO      = AF.YFACNO -- 勘定科目コード
			  ,YF.YFSSODRNO   = AF.YFSSODRNO -- 製造オーダー番号
			  ,YF.YFODRKB     = AF.YFODRKB -- オーダー区分
			  ,YF.YFKOTEICD   = AF.YFKOTEICD -- 工程コード
			  ,YF.YFOHINCD    = AF.YFOHINCD -- 親品目コード
			  ,YF.YFKSEQNO    = AF.YFKSEQNO -- 工程順序
			  ,YF.YFSODRSYUBT = AF.YFSODRSYUBT -- 製造オーダー種別
			  ,YF.YFKNBUNKB   = AF.YFKNBUNKB -- 機能別分類（職能）
			  ,YF.YFRIYCD     = AF.YFRIYCD -- 理由コード
			  ,YF.YFUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YF.YFUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YF.YFUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YFIMPDATE -- 登録日(YYYYMMDD)
			,YFIMPTIME -- 登録時間(HH24MI)
			,YFPROCFLG -- 処理フラグ(0:新規)
			,YFCOCODE -- 会社コード(※共通仕様参照。)
			,YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,YFIMCODE -- 品目コード(20桁以内)
			,YFHINNM -- 品目名称(未使用 NULL)
			,YFFUDATE -- 伝票日付(YYYYMMDD)
			,YFDENNO -- 伝票番号
			,YFGYOBAN -- 行番号
			,YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,YFTWSYORI -- 処理区分
			,YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,YFGBUMONCD -- 原価部門コード
			,YFTWQTY -- 入出庫数
			,YFHSMTANK -- 発生元単価(未使用 0)
			,YFHSMKING -- 発生元金額(未使用 0)
			,YFYTTANK -- 予定単価
			,YFYTKING -- 予定金額
			,YFIATANK -- 実際単価
			,YFIAKING -- 実際金額
			,YFSTDQTY -- 標準数量(未使用 0)
			,YFSTDTANK -- 標準単価(未使用 0)
			,YFSTDKING -- 標準金額(未使用 0)
			,YFACNO -- 勘定科目コード
			,YFACHOJO -- 補助科目コード('0000000000')
			,YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,YFSPHCMT -- 摘要(未使用 NULL)
			,YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,YFSSHRQTY -- 不良数(未使用 0)
			,YFNODATE -- 処理年月度(未使用 0)
			,YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFLOTNO -- ロット番号(未使用 NULL)
			,YFSGMNTCD -- セグメントコード(未使用 NULL)
			,YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,YFSGMNTCD2 -- 組コード(未使用 NULL)
			,YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,YFSSONKBN -- 仕損区分(未使用 0)
			,YFDELFLG -- 削除フラグ(未使用 0)
			,YFHTFLG -- 単価未設定フラグ(未使用 0)
			,YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,YFSIKYU -- 支給区分(未使用 0)
			,YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,YFTBKBN -- 取引先区分(未使用 0)
			,YFTBCODE -- 取引先コード(未使用 NULL)
			,YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,YFPRGID -- プログラムＩＤ(未使用 NULL)
			,YFDSNO -- エントリ処理番号(未使用 NULL)
			,YFSYMD -- エントリ処理日(未使用 0)
			,YFHINSEQNO -- 品目順序(未使用 0)
			,YFTACODE -- 担当者コード(未使用 NULL)
			,YFSYHKBN -- 消費税区分(未使用 0)
			,YFTRSYHKIN -- 消費税額(未使用 0)
			,YFTNKBN -- ロケーション区分(未使用 0)
			,YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,YFIPDATE -- 登録日
			,YFIPTIME -- 登録時間
			,YFIPID -- 登録者ＩＤ
			,YFUPDATE -- 更新日
			,YFUPTIME -- 更新時間
			,YFUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AF.YFCOCODE -- 会社コード(※共通仕様参照。)
			,AF.YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,AF.YFIMCODE -- 品目コード(20桁以内)
			,AF.YFHINNM -- 品目名称(未使用 NULL)
			,TO_CHAR(AF.YFFUDATE) -- 伝票日付(YYYYMMDD)
			,AF.YFDENNO -- 伝票番号
			,AF.YFGYOBAN -- 行番号
			,1 -- 入出庫区分(1:出庫)
			,2 -- 処理区分(2:売上)
			,AF.YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,AF.YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,AF.YFGBUMONCD -- 原価部門コード
			,AF.YFTWQTY -- 入出庫数
			,AF.YFHSMTANK -- 発生元単価(未使用 0)
			,AF.YFHSMKING -- 発生元金額(未使用 0)
			,AF.YFYTTANK -- 予定単価
			,AF.YFYTKING -- 予定金額
			,AF.YFIATANK -- 実際単価
			,AF.YFIAKING -- 実際金額
			,AF.YFSTDQTY -- 標準数量(未使用 0)
			,AF.YFSTDTANK -- 標準単価(未使用 0)
			,AF.YFSTDKING -- 標準金額(未使用 0)
			,AF.YFACNO -- 勘定科目コード
			,AF.YFACHOJO -- 補助科目コード('0000000000')
			,AF.YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,AF.YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,AF.YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,AF.YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,AF.YFSPHCMT -- 摘要(未使用 NULL)
			,AF.YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,AF.YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,AF.YFSSHRQTY -- 不良数(未使用 0)
			,AF.YFNODATE -- 処理年月度(未使用 0)
			,AF.YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFLOTNO -- ロット番号(未使用 NULL)
			,AF.YFSGMNTCD -- セグメントコード(未使用 NULL)
			,AF.YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,AF.YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,AF.YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,AF.YFSGMNTCD2 -- 組コード(未使用 NULL)
			,AF.YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,AF.YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,AF.YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,AF.YFSSONKBN -- 仕損区分(未使用 0)
			,AF.YFDELFLG -- 削除フラグ(未使用 0)
			,AF.YFHTFLG -- 単価未設定フラグ(未使用 0)
			,AF.YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,AF.YFSIKYU -- 支給区分(未使用 0)
			,AF.YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,AF.YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AF.YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,AF.YFTBKBN -- 取引先区分(未使用 0)
			,AF.YFTBCODE -- 取引先コード(未使用 NULL)
			,AF.YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,AF.YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,AF.YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AF.YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,AF.YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,AF.YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,AF.YFPRGID -- プログラムＩＤ(未使用 NULL)
			,AF.YFDSNO -- エントリ処理番号(未使用 NULL)
			,AF.YFSYMD -- エントリ処理日(未使用 0)
			,AF.YFHINSEQNO -- 品目順序(未使用 0)
			,AF.YFTACODE -- 担当者コード(未使用 NULL)
			,AF.YFSYHKBN -- 消費税区分(未使用 0)
			,AF.YFTRSYHKIN -- 消費税額(未使用 0)
			,AF.YFTNKBN -- ロケーション区分(未使用 0)
			,AF.YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AF.YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);

	-- 仕入直送品「売上返品」データ生成
	--  MERGE INTO SIAP.IFYF@SIAPSOM YF
	MERGE INTO AP21.TEMP_IFYF YF
	USING (SELECT *
		   FROM   SIAP_CAYF
		   WHERE  YFFUDATE BETWEEN stdt AND eddt -- 作業日が当月
		   AND    ((YFTWSYORI = 1 AND YFTWQTY <> 0) OR YFTWSYORI <> 1)
		   AND    (YFIMCODE LIKE '04%' OR YFIMCODE LIKE '14%' OR
				 YFIMCODE LIKE '24%')
		   AND    YFTWSYORI >= 10) AF -- 売上返品を対象
	ON (YF.YFCOCODE = AF.YFCOCODE -- 会社コード
	AND YF.YFHBRCODE = AF.YFHBRCODE -- 本支店コード
	AND YF.YFIMCODE = AF.YFIMCODE -- 品目コード
	AND YF.YFFUDATE = AF.YFFUDATE -- 伝票日付
	AND YF.YFDENNO = AF.YFDENNO -- 伝票番号
	AND YF.YFGYOBAN = AF.YFGYOBAN -- 行番号
	AND YF.YFSZNYUKB = 1 -- 入出庫区分(1:出庫)
	AND YF.YFTWSYORI = 51 -- 処理区分(51:売上返品)
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YF.YFIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YF.YFIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YF.YFPROCFLG   = 1 -- 処理フラグ
			  ,YF.YFHINKBN    = AF.YFHINKBN -- 品目区分
			  ,YF.YFGBUMONCD  = AF.YFGBUMONCD -- 原価部門コード
			  ,YF.YFTWQTY     = AF.YFTWQTY -- 入出庫数
			  ,YF.YFYTTANK    = AF.YFYTTANK -- 予定単価
			  ,YF.YFYTKING    = AF.YFYTKING -- 予定金額
			  ,YF.YFIATANK    = AF.YFIATANK -- 実際単価
			  ,YF.YFIAKING    = AF.YFIAKING -- 実際金額
			  ,YF.YFACNO      = AF.YFACNO -- 勘定科目コード
			  ,YF.YFSSODRNO   = AF.YFSSODRNO -- 製造オーダー番号
			  ,YF.YFODRKB     = AF.YFODRKB -- オーダー区分
			  ,YF.YFKOTEICD   = AF.YFKOTEICD -- 工程コード
			  ,YF.YFOHINCD    = AF.YFOHINCD -- 親品目コード
			  ,YF.YFKSEQNO    = AF.YFKSEQNO -- 工程順序
			  ,YF.YFSODRSYUBT = AF.YFSODRSYUBT -- 製造オーダー種別
			  ,YF.YFKNBUNKB   = AF.YFKNBUNKB -- 機能別分類（職能）
			  ,YF.YFRIYCD     = AF.YFRIYCD -- 理由コード
			  ,YF.YFUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YF.YFUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YF.YFUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YFIMPDATE -- 登録日(YYYYMMDD)
			,YFIMPTIME -- 登録時間(HH24MI)
			,YFPROCFLG -- 処理フラグ(0:新規)
			,YFCOCODE -- 会社コード(※共通仕様参照。)
			,YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,YFIMCODE -- 品目コード(20桁以内)
			,YFHINNM -- 品目名称(未使用 NULL)
			,YFFUDATE -- 伝票日付(YYYYMMDD)
			,YFDENNO -- 伝票番号
			,YFGYOBAN -- 行番号
			,YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,YFTWSYORI -- 処理区分
			,YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,YFGBUMONCD -- 原価部門コード
			,YFTWQTY -- 入出庫数
			,YFHSMTANK -- 発生元単価(未使用 0)
			,YFHSMKING -- 発生元金額(未使用 0)
			,YFYTTANK -- 予定単価
			,YFYTKING -- 予定金額
			,YFIATANK -- 実際単価
			,YFIAKING -- 実際金額
			,YFSTDQTY -- 標準数量(未使用 0)
			,YFSTDTANK -- 標準単価(未使用 0)
			,YFSTDKING -- 標準金額(未使用 0)
			,YFACNO -- 勘定科目コード
			,YFACHOJO -- 補助科目コード('0000000000')
			,YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,YFSPHCMT -- 摘要(未使用 NULL)
			,YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,YFSSHRQTY -- 不良数(未使用 0)
			,YFNODATE -- 処理年月度(未使用 0)
			,YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFLOTNO -- ロット番号(未使用 NULL)
			,YFSGMNTCD -- セグメントコード(未使用 NULL)
			,YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,YFSGMNTCD2 -- 組コード(未使用 NULL)
			,YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,YFSSONKBN -- 仕損区分(未使用 0)
			,YFDELFLG -- 削除フラグ(未使用 0)
			,YFHTFLG -- 単価未設定フラグ(未使用 0)
			,YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,YFSIKYU -- 支給区分(未使用 0)
			,YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,YFTBKBN -- 取引先区分(未使用 0)
			,YFTBCODE -- 取引先コード(未使用 NULL)
			,YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,YFPRGID -- プログラムＩＤ(未使用 NULL)
			,YFDSNO -- エントリ処理番号(未使用 NULL)
			,YFSYMD -- エントリ処理日(未使用 0)
			,YFHINSEQNO -- 品目順序(未使用 0)
			,YFTACODE -- 担当者コード(未使用 NULL)
			,YFSYHKBN -- 消費税区分(未使用 0)
			,YFTRSYHKIN -- 消費税額(未使用 0)
			,YFTNKBN -- ロケーション区分(未使用 0)
			,YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,YFIPDATE -- 登録日
			,YFIPTIME -- 登録時間
			,YFIPID -- 登録者ＩＤ
			,YFUPDATE -- 更新日
			,YFUPTIME -- 更新時間
			,YFUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AF.YFCOCODE -- 会社コード(※共通仕様参照。)
			,AF.YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,AF.YFIMCODE -- 品目コード(20桁以内)
			,AF.YFHINNM -- 品目名称(未使用 NULL)
			,TO_CHAR(AF.YFFUDATE) -- 伝票日付(YYYYMMDD)
			,AF.YFDENNO -- 伝票番号
			,AF.YFGYOBAN -- 行番号
			,1 -- 入出庫区分(1:出庫)
			,51 -- 処理区分(51:売上返品)
			,AF.YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,AF.YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,AF.YFGBUMONCD -- 原価部門コード
			,AF.YFTWQTY -- 入出庫数
			,AF.YFHSMTANK -- 発生元単価(未使用 0)
			,AF.YFHSMKING -- 発生元金額(未使用 0)
			,AF.YFYTTANK -- 予定単価
			,AF.YFYTKING -- 予定金額
			,AF.YFIATANK -- 実際単価
			,AF.YFIAKING -- 実際金額
			,AF.YFSTDQTY -- 標準数量(未使用 0)
			,AF.YFSTDTANK -- 標準単価(未使用 0)
			,AF.YFSTDKING -- 標準金額(未使用 0)
			,AF.YFACNO -- 勘定科目コード
			,AF.YFACHOJO -- 補助科目コード('0000000000')
			,AF.YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,AF.YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,AF.YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,AF.YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,AF.YFSPHCMT -- 摘要(未使用 NULL)
			,AF.YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,AF.YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,AF.YFSSHRQTY -- 不良数(未使用 0)
			,AF.YFNODATE -- 処理年月度(未使用 0)
			,AF.YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFLOTNO -- ロット番号(未使用 NULL)
			,AF.YFSGMNTCD -- セグメントコード(未使用 NULL)
			,AF.YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,AF.YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,AF.YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,AF.YFSGMNTCD2 -- 組コード(未使用 NULL)
			,AF.YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,AF.YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,AF.YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,AF.YFSSONKBN -- 仕損区分(未使用 0)
			,AF.YFDELFLG -- 削除フラグ(未使用 0)
			,AF.YFHTFLG -- 単価未設定フラグ(未使用 0)
			,AF.YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,AF.YFSIKYU -- 支給区分(未使用 0)
			,AF.YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,AF.YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AF.YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,AF.YFTBKBN -- 取引先区分(未使用 0)
			,AF.YFTBCODE -- 取引先コード(未使用 NULL)
			,AF.YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,AF.YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,AF.YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AF.YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,AF.YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,AF.YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,AF.YFPRGID -- プログラムＩＤ(未使用 NULL)
			,AF.YFDSNO -- エントリ処理番号(未使用 NULL)
			,AF.YFSYMD -- エントリ処理日(未使用 0)
			,AF.YFHINSEQNO -- 品目順序(未使用 0)
			,AF.YFTACODE -- 担当者コード(未使用 NULL)
			,AF.YFSYHKBN -- 消費税区分(未使用 0)
			,AF.YFTRSYHKIN -- 消費税額(未使用 0)
			,AF.YFTNKBN -- ロケーション区分(未使用 0)
			,AF.YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AF.YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);

	-- 重複レコードの件数を取得します
	/*  SELECT COUNT(*)
        INTO   cnt3
        --  FROM   IFYF@SIAPSOM IFYF3
        FROM   AP21.TEMP_IFYF IFYF3
        WHERE  EXISTS (SELECT DISTINCT YFDENNO
                --      FROM   IFYF@SIAPSOM IFYF1
                FROM   AP21.TEMP_IFYF IFYF1
                WHERE  YFDENNO > (SELECT MIN(YFDENNO)
                                  --                FROM   IFYF@SIAPSOM IFYF2
                                  FROM   AP21.TEMP_IFYF IFYF2
                                  WHERE  IFYF1.YFIMCODE = IFYF2.YFIMCODE -- 品目コード
                                  AND    IFYF1.YFFUDATE = IFYF2.YFFUDATE -- 伝票日付
                                  AND    IFYF1.YFGYOBAN = IFYF2.YFGYOBAN -- 行番号
                                  AND    IFYF1.YFSZNYUKB = IFYF2.YFSZNYUKB -- 入出庫区分
                                  AND    IFYF1.YFTWSYORI = IFYF2.YFTWSYORI -- 処理区分
                                  AND    IFYF1.YFTWQTY = IFYF2.YFTWQTY -- 入出庫数
                                  AND    IFYF1.YFHSMKING = IFYF2.YFHSMKING -- 発生元金額
                                  AND    IFYF1.YFGYOBAN > 1)
                AND    IFYF3.YFDENNO = IFYF1.YFDENNO);
    */
	-- 重複レコードの伝票番号を取得します
	FOR ifyf_rec IN ifyf_cur() LOOP
		duplication_denno(cnt3) := ifyf_rec.YFDENNO;
		cnt3 := cnt3 + 1;
	END LOOP;

	-- tempテーブルの重複レコードを削除します
	--    DELETE FROM IFYF@SIAPSOM IFYF3
	DELETE FROM AP21.TEMP_IFYF IFYF3
	WHERE  EXISTS (SELECT DISTINCT YFDENNO
			--          FROM   IFYF@SIAPSOM IFYF1
			FROM   AP21.TEMP_IFYF IFYF1
			WHERE  YFDENNO > (SELECT MIN(YFDENNO)
							  --                    FROM   IFYF@SIAPSOM IFYF2
							  FROM   AP21.TEMP_IFYF IFYF2
							  WHERE  IFYF1.YFIMCODE = IFYF2.YFIMCODE -- 品目コード
							  AND    IFYF1.YFFUDATE = IFYF2.YFFUDATE -- 伝票日付
							  AND    IFYF1.YFGYOBAN = IFYF2.YFGYOBAN -- 行番号
							  AND    IFYF1.YFSZNYUKB = IFYF2.YFSZNYUKB -- 入出庫区分
							  AND    IFYF1.YFTWSYORI = IFYF2.YFTWSYORI -- 処理区分
							  AND    IFYF1.YFTWQTY = IFYF2.YFTWQTY -- 入出庫数
							  AND    IFYF1.YFHSMKING = IFYF2.YFHSMKING -- 発生元金額
							  AND    IFYF1.YFGYOBAN > 1)
			AND    IFYF3.YFDENNO = IFYF1.YFDENNO);

	-- tempレコードを中間テーブルへ登録
	MERGE INTO SIAP.IFYF@SIAPSOM YF
	USING (SELECT * FROM AP21.TEMP_IFYF) AF
	ON (YF.YFCOCODE = AF.YFCOCODE -- 会社コード
	AND YF.YFHBRCODE = AF.YFHBRCODE -- 本支店コード
	AND YF.YFIMCODE = AF.YFIMCODE -- 品目コード
	AND YF.YFFUDATE = AF.YFFUDATE -- 伝票日付
	AND YF.YFDENNO = AF.YFDENNO -- 伝票番号
	AND YF.YFGYOBAN = AF.YFGYOBAN -- 行番号
	AND YF.YFSZNYUKB = AF.YFSZNYUKB -- 入出庫区分
	AND YF.YFTWSYORI = AF.YFTWSYORI -- 処理区分
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YF.YFIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YF.YFIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YF.YFPROCFLG   = 1 -- 処理フラグ
			  ,YF.YFHINKBN    = AF.YFHINKBN -- 品目区分
			  ,YF.YFGBUMONCD  = AF.YFGBUMONCD -- 原価部門コード
			  ,YF.YFTWQTY     = AF.YFTWQTY -- 入出庫数
			  ,YF.YFYTTANK    = AF.YFYTTANK -- 予定単価
			  ,YF.YFYTKING    = AF.YFYTKING -- 予定金額
			  ,YF.YFIATANK    = AF.YFIATANK -- 実際単価
			  ,YF.YFIAKING    = AF.YFIAKING -- 実際金額
			  ,YF.YFACNO      = AF.YFACNO -- 勘定科目コード
			  ,YF.YFSSODRNO   = AF.YFSSODRNO -- 製造オーダー番号
			  ,YF.YFODRKB     = AF.YFODRKB -- オーダー区分
			  ,YF.YFKOTEICD   = AF.YFKOTEICD -- 工程コード
			  ,YF.YFOHINCD    = AF.YFOHINCD -- 親品目コード
			  ,YF.YFKSEQNO    = AF.YFKSEQNO -- 工程順序
			  ,YF.YFSODRSYUBT = AF.YFSODRSYUBT -- 製造オーダー種別
			  ,YF.YFKNBUNKB   = AF.YFKNBUNKB -- 機能別分類（職能）
			  ,YF.YFRIYCD     = AF.YFRIYCD -- 理由コード
			  ,YF.YFUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YF.YFUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YF.YFUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YFIMPDATE -- 登録日(YYYYMMDD)
			,YFIMPTIME -- 登録時間(HH24MI)
			,YFPROCFLG -- 処理フラグ(0:新規)
			,YFCOCODE -- 会社コード(※共通仕様参照。)
			,YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,YFIMCODE -- 品目コード(20桁以内)
			,YFHINNM -- 品目名称(未使用 NULL)
			,YFFUDATE -- 伝票日付(YYYYMMDD)
			,YFDENNO -- 伝票番号
			,YFGYOBAN -- 行番号
			,YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,YFTWSYORI -- 処理区分
			,YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,YFGBUMONCD -- 原価部門コード
			,YFTWQTY -- 入出庫数
			,YFHSMTANK -- 発生元単価(未使用 0)
			,YFHSMKING -- 発生元金額(未使用 0)
			,YFYTTANK -- 予定単価
			,YFYTKING -- 予定金額
			,YFIATANK -- 実際単価
			,YFIAKING -- 実際金額
			,YFSTDQTY -- 標準数量(未使用 0)
			,YFSTDTANK -- 標準単価(未使用 0)
			,YFSTDKING -- 標準金額(未使用 0)
			,YFACNO -- 勘定科目コード
			,YFACHOJO -- 補助科目コード('0000000000')
			,YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,YFSPHCMT -- 摘要(未使用 NULL)
			,YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,YFSSHRQTY -- 不良数(未使用 0)
			,YFNODATE -- 処理年月度(未使用 0)
			,YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,YFLOTNO -- ロット番号(未使用 NULL)
			,YFSGMNTCD -- セグメントコード(未使用 NULL)
			,YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,YFSGMNTCD2 -- 組コード(未使用 NULL)
			,YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,YFSSONKBN -- 仕損区分(未使用 0)
			,YFDELFLG -- 削除フラグ(未使用 0)
			,YFHTFLG -- 単価未設定フラグ(未使用 0)
			,YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,YFSIKYU -- 支給区分(未使用 0)
			,YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,YFTBKBN -- 取引先区分(未使用 0)
			,YFTBCODE -- 取引先コード(未使用 NULL)
			,YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,YFPRGID -- プログラムＩＤ(未使用 NULL)
			,YFDSNO -- エントリ処理番号(未使用 NULL)
			,YFSYMD -- エントリ処理日(未使用 0)
			,YFHINSEQNO -- 品目順序(未使用 0)
			,YFTACODE -- 担当者コード(未使用 NULL)
			,YFSYHKBN -- 消費税区分(未使用 0)
			,YFTRSYHKIN -- 消費税額(未使用 0)
			,YFTNKBN -- ロケーション区分(未使用 0)
			,YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,YFIPDATE -- 登録日
			,YFIPTIME -- 登録時間
			,YFIPID -- 登録者ＩＤ
			,YFUPDATE -- 更新日
			,YFUPTIME -- 更新時間
			,YFUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):        
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):        
			,0 -- 処理フラグ(0:新規)
			,AF.YFCOCODE -- 会社コード(※共通仕様参照。)
			,AF.YFHBRCODE -- 本支店コード(※共通仕様参照。)
			,AF.YFIMCODE -- 品目コード(20桁以内)
			,AF.YFHINNM -- 品目名称(未使用 NULL)
			,TO_CHAR(AF.YFFUDATE) -- 伝票日付(YYYYMMDD)
			,AF.YFDENNO -- 伝票番号
			,AF.YFGYOBAN -- 行番号
			,AF.YFSZNYUKB -- 入出庫区分(0..入庫 1..出庫)
			,AF.YFTWSYORI -- 処理区分
			,AF.YFHINKBN -- 品目区分(0..製品 1..商品 2..中間品 3..原材料 4..副資材 5..貯蔵品 6..購入部品 7..外注加工品)該当品目の属性に従う。
			,AF.YFHTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,AF.YFGBUMONCD -- 原価部門コード
			,AF.YFTWQTY -- 入出庫数
			,AF.YFHSMTANK -- 発生元単価(未使用 0)
			,AF.YFHSMKING -- 発生元金額(未使用 0)
			,AF.YFYTTANK -- 予定単価
			,AF.YFYTKING -- 予定金額
			,AF.YFIATANK -- 実際単価
			,AF.YFIAKING -- 実際金額
			,AF.YFSTDQTY -- 標準数量(未使用 0)
			,AF.YFSTDTANK -- 標準単価(未使用 0)
			,AF.YFSTDKING -- 標準金額(未使用 0)
			,AF.YFACNO -- 勘定科目コード
			,AF.YFACHOJO -- 補助科目コード('0000000000')
			,AF.YFTACNO -- 統制勘定科目コード(未使用 NULL)
			,AF.YFSSODRNO -- 製造オーダー番号(処理区分が1:仕入、4:製造のとき)
			,AF.YFGNKEISANNO -- 原価計算書番号(未使用 NULL)
			,AF.YFCKHIKBN -- 直接費・間接費区分(0..直接費 1..間接費)
			,AF.YFSPHCMT -- 摘要(未使用 NULL)
			,AF.YFODRKB -- オーダー区分(0..製造オーダー 1..購買オーダー)
			,AF.YFKOTEICD -- 工程コード(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFOHINCD -- 親品目コード(20桁以下：処理区分が4:製造のとき）
			,AF.YFSSHRQTY -- 不良数(未使用 0)
			,AF.YFNODATE -- 処理年月度(未使用 0)
			,AF.YFKSEQNO -- 工程順序(入出庫区分が1..出庫かつ処理区分が4:製造のとき)
			,AF.YFLOTNO -- ロット番号(未使用 NULL)
			,AF.YFSGMNTCD -- セグメントコード(未使用 NULL)
			,AF.YFSGMNTCDA -- セグメントコード（大分類）(未使用 NULL)
			,AF.YFSGMNTCDB -- セグメントコード（中分類）(未使用 NULL)
			,AF.YFSGMNTCDC -- セグメントコード（小分類）(未使用 NULL)
			,AF.YFSGMNTCD2 -- 組コード(未使用 NULL)
			,AF.YFSODRSYUBT -- 製造オーダー種別(処理区分が4:製造のとき、該当オーダーの種別)
			,AF.YFMTSSODRNO -- 発生元製造オーダー番号(未使用 NULL)
			,AF.YFKNBUNKB -- 機能別分類（職能）(0..その他 1..製造原価 2..販売費 3..一般管理費、処理区分が4:製造の場合は1)
			,AF.YFSSONKBN -- 仕損区分(未使用 0)
			,AF.YFDELFLG -- 削除フラグ(未使用 0)
			,AF.YFHTFLG -- 単価未設定フラグ(未使用 0)
			,AF.YFSWKSYBTCD -- 仕訳種別コード(未使用 NULL)
			,AF.YFSIKYU -- 支給区分(未使用 0)
			,AF.YFATACNO -- 相手勘定科目コード(未使用 NULL)
			,AF.YFATACHOJO -- 相手補助科目コード(未使用 NULL)
			,AF.YFATTSACNO -- 相手統制勘定科目コード(未使用 NULL)
			,AF.YFTBKBN -- 取引先区分(未使用 0)
			,AF.YFTBCODE -- 取引先コード(未使用 NULL)
			,AF.YFHMDENNO -- 発生元伝票番号(未使用 NULL)
			,AF.YFHMGYONO -- 発生元伝票行番号(未使用 0)
			,AF.YFSDENNO -- 仕訳伝票番号(未使用 NULL)
			,AF.YFSGYONO -- 仕訳伝票行番号(未使用 0)
			,AF.YFSDENCRDT -- 仕訳伝票作成日(未使用 0)
			,AF.YFIANKYMD -- 受払元帳更新日付(未使用 0)
			,AF.YFPRGID -- プログラムＩＤ(未使用 NULL)
			,AF.YFDSNO -- エントリ処理番号(未使用 NULL)
			,AF.YFSYMD -- エントリ処理日(未使用 0)
			,AF.YFHINSEQNO -- 品目順序(未使用 0)
			,AF.YFTACODE -- 担当者コード(未使用 NULL)
			,AF.YFSYHKBN -- 消費税区分(未使用 0)
			,AF.YFTRSYHKIN -- 消費税額(未使用 0)
			,AF.YFTNKBN -- ロケーション区分(未使用 0)
			,AF.YFPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AF.YFRIYCD -- 理由コード(処理区分が3:例外の場合必須)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);

	-- 中間ファイルの重複レコードを削除
	FOR i IN 0 .. cnt3 - 1 LOOP
		DELETE FROM SIAP.IFYF@SIAPSOM
		WHERE  YFCOCODE = '001'
		AND    YFHBRCODE = '001'
		AND    YFDENNO = duplication_denno(i);
	END LOOP;

	-- メモリ開放 
	duplication_denno.DELETE;

	P_NUM_CNT := cnt1 + cnt2 - cnt3; -- 処理件数

	-- 戻り値設定
	P_NUM_RET := 0;
	COMMIT;

	/****************************** 例外処理部 ******************************/
EXCEPTION
	-- 例外処理
	WHEN OTHERS THEN
		ROLLBACK;
		P_NUM_RET := 99;
		-- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYF'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYF;
/
