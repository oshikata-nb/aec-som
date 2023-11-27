CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFYA
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
	SELECT COUNT(*) INTO P_NUM_CNT FROM SIAP_CAYA CAYA WHERE YAWPEDDAY BETWEEN stdt AND eddt; -- 完了日が当日
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグYAPROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFYA@SIAPSOM
	WHERE  YAPROCFLG = 9
	AND    YASTATUS IN (1, 2)
	AND    YAWPEDDAY BETWEEN stdt AND eddt -- 完了日が当日
	AND    YACOCODE = '001'
	AND    YAHBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFYA@SIAPSOM
	SET    YAPROCFLG = 9
		  ,YASTATUS  = 0
		  ,YAERRMSG  = NULL
	WHERE  YAWPEDDAY BETWEEN stdt AND eddt -- 完了日が当日
	AND    YACOCODE = '001'
	AND    YAHBRCODE = '001';
	-- ap21基本処方マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	MERGE INTO SIAP.IFYA@SIAPSOM YA
	USING (SELECT CAYA.* FROM SIAP_CAYA CAYA WHERE YAWPEDDAY BETWEEN stdt AND eddt) AA -- 完了日が当日
	ON (YA.YACOCODE = AA.YACOCODE -- 会社コード
	AND YA.YAHBRCODE = AA.YAHBRCODE -- 本支店コード
	AND YA.YASSODRNO = AA.YASSODRNO -- 製造オーダー番号
	)
	WHEN MATCHED THEN
		UPDATE
		SET    YA.YAIMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,YA.YAIMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI)
			  ,YA.YAPROCFLG   = 1 -- 処理フラグ
			  ,YA.YAIMCODE    = AA.YAIMCODE -- 品目コード
			  ,YA.YAHINKBN    = AA.YAHINKBN -- 品目区分
			  ,YA.YASSHKDATE  = AA.YASSHKDATE -- 指図日
			  ,YA.YASSQTY     = AA.YASSQTY -- 製造指図数
			  ,YA.YASSSTATUS  = AA.YASSSTATUS -- 製造ステータス
			  ,YA.YAWPEDDAY   = AA.YAWPEDDAY -- 製造完了日
			  ,YA.YASBUNRUICD = AA.YASBUNRUICD -- 組コード
			  ,YA.YAUPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,YA.YAUPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,YA.YAUPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(YAIMPDATE -- 登録日(YYYYMMDD)
			,YAIMPTIME -- 登録時間(HH24MI)
			,YAPROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
			,YACOCODE -- 会社コード(自社マスタ攝津製油固定)
			,YAHBRCODE -- 本支店コード(暫定！攝津製油固定)
			,YASSODRNO -- 製造オーダー番号
			,YAIMCODE -- 品目コード(20桁以内)
			,YAHINNM -- 品目名称(未使用 NULL)
			,YASERIESCD -- 製品シリーズコード(未使用 NULL)
			,YAGNKEISANNO -- 原価計算書番号(未使用 NULL)。
			,YAHINKBN -- 品目区分(該当品目の属性に従う)
			,YALEVELCD -- ローレベルコード(未使用 0)
			,YAGKEITAI -- 原価計算形態区分(5: 工程別組別総合原価計算)
			,YAJUODRNO -- 受注番号(未使用 NULL)
			,YAGYOBAN -- 行番号(未使用 0)
			,YASSHKDATE -- 指図日（YYYYMMDD）
			,YABUMONCD -- 所属部門コード(未使用 NULL)
			,YASSQTY -- 製造指図数
			,YASSEDPDAY -- 製造完了予定日(未使用 0)
			,YASSCMT -- 製造摘要(未使用 NULL)
			,YASSSTATUS -- 製造ステータス(0:指図書発行 1:製造中 2:製造完了)
			,YAWPEDDAY -- 製造完了日(製造ステータスが2:製造完了の場合、製造完了日(YYYYMMDD)その他のとき、0)
			,YASSRKQTY -- 製造完成数(未使用 0)
			,YASSHRQTY -- 不良数(未使用 0)
			,YALOTNO -- ロット番号(未使用 0)
			,YADPROJECTCD -- 代表プロジェクトコード(未使用 NULL)
			,YAPROJECTCD -- プロジェクトコード(未使用 NULL)
			,YASODRSYUBT -- オーダー種別(SIAP/CAのオーダー種別マスタに登録した製造オーダー種別)
			,YAMTSSODRNO -- 発生元オーダー番号(未使用 NULL)
			,YASANSYONO -- 参照番号(未使用 NULL)
			,YAKOTEICD -- 工程コード(未使用 NULL)
			,YAACNO -- 製造オーダー種別-勘定科目コード('01200'製品)
			,YAACHOJO -- 製造オーダー種別-補助科目コード('0000000000')
			,YAKNBUNKB -- 製造オーダー種別-機能別分類(0..その他 1..製造原価 2..販売費 3..一般管理費)
			,YASSONKBN -- 仕損区分(未使用 0)
			,YASBUNRUICD -- 組コード(品目分類コード)
			,YAIPDATE -- 登録日
			,YAIPTIME -- 登録時間
			,YAIPID -- 登録者ＩＤ
			,YAUPDATE -- 更新日
			,YAUPTIME -- 更新時間
			,YAUPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):    
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MI):    
			,0 -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
			,AA.YACOCODE -- 会社コード(自社マスタ攝津製油固定)
			,AA.YAHBRCODE -- 本支店コード(暫定！攝津製油固定)
			,AA.YASSODRNO -- 製造オーダー番号
			,AA.YAIMCODE -- 品目コード(20桁以内)
			,AA.YAHINNM -- 品目名称(未使用 NULL)
			,AA.YASERIESCD -- 製品シリーズコード(未使用 NULL)
			,AA.YAGNKEISANNO -- 原価計算書番号(未使用 NULL)。
			,AA.YAHINKBN -- 品目区分(該当品目の属性に従う)
			,AA.YALEVELCD -- ローレベルコード(未使用 0)
			,AA.YAGKEITAI -- 原価計算形態区分(5: 工程別組別総合原価計算)
			,AA.YAJUODRNO -- 受注番号(未使用 NULL)
			,AA.YAGYOBAN -- 行番号(未使用 0)
			,AA.YASSHKDATE -- 指図日（YYYYMMDD）
			,AA.YABUMONCD -- 所属部門コード(未使用 NULL)
			,AA.YASSQTY -- 製造指図数
			,AA.YASSEDPDAY -- 製造完了予定日(未使用 0)
			,AA.YASSCMT -- 製造摘要(未使用 NULL)
			,AA.YASSSTATUS -- 製造ステータス(0:指図書発行 1:製造中 2:製造完了)
			,AA.YAWPEDDAY -- 製造完了日(製造ステータスが2:製造完了の場合、製造完了日(YYYYMMDD)その他のとき、0)
			,AA.YASSRKQTY -- 製造完成数(未使用 0)
			,AA.YASSHRQTY -- 不良数(未使用 0)
			,AA.YALOTNO -- ロット番号(未使用 0)
			,AA.YADPROJECTCD -- 代表プロジェクトコード(未使用 NULL)
			,AA.YAPROJECTCD -- プロジェクトコード(未使用 NULL)
			,AA.YASODRSYUBT -- オーダー種別(SIAP/CAのオーダー種別マスタに登録した製造オーダー種別)
			,AA.YAMTSSODRNO -- 発生元オーダー番号(未使用 NULL)
			,AA.YASANSYONO -- 参照番号(未使用 NULL)
			,AA.YAKOTEICD -- 工程コード(未使用 NULL)
			,AA.YAACNO -- 製造オーダー種別-勘定科目コード('01200'製品)
			,AA.YAACHOJO -- 製造オーダー種別-補助科目コード('0000000000')
			,AA.YAKNBUNKB -- 製造オーダー種別-機能別分類(0..その他 1..製造原価 2..販売費 3..一般管理費)
			,AA.YASSONKBN -- 仕損区分(未使用 0)
			,AA.YASBUNRUICD -- 組コード(品目分類コード)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);
	-- 指図日 > 完了予定日 の場合は指図日に完了予定日をセット
	UPDATE SIAP.IFYA@SIAPSOM
	SET    YASSHKDATE = YAWPEDDAY
	WHERE  YASSHKDATE > YAWPEDDAY
	AND    YAWPEDDAY <> 0;
	-- 戻り値設定
	P_NUM_RET := 0;
	COMMIT;
EXCEPTION
	-- 例外処理
	WHEN OTHERS THEN
		ROLLBACK;
		P_NUM_RET := 99;
		-- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFYA', P_STR_TANTO_CD, SQLCODE, SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFYA;
/
