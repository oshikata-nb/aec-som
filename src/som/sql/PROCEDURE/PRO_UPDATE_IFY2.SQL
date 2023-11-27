CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFY2
(
	P_STR_TARGET_MONTH  IN NVARCHAR2
   ,P_NUM_SEND_DIVISION IN NUMBER
   ,P_STR_TANTO_CD      IN NVARCHAR2
   ,P_NUM_CNT           OUT NUMBER
   ,P_NUM_RET           OUT NUMBER
)

	/************************************************************************/
	/*   PROCEDURE名 ：PRO_UPDATE_IFY2                                      */
	/*      処理内容 ：原価計算I/F 品目マスタ送信                           */
	/*          引数 ：対象年月                                             */
	/*               ：担当者コード                                         */
	/*               ：マスタ送信区分(1:期中 2:期首)                        */
	/*               ：処理件数(戻値)                                       */
	/*               ：戻値(0:成功 99:エラー)                               */
	/************************************************************************/
 IS
	/******************************** 宣言部 ********************************/
	-- 定数の宣言
	C_MODULE_CD NVARCHAR2(40) := 'PRO_UPDATE_IFY2';

	-- 変数の宣言
	TARGET_MONTH CHAR(8) := P_STR_TARGET_MONTH || '99';
	UN_TRANS     NUMBER := 0; -- 未送信
	TRANS        NUMBER := 1; -- 送信済

	/******************************** 処理部 ********************************/
BEGIN
	-- 対象件数調査

	-- 2011/06/17 ADD START
	IF P_NUM_SEND_DIVISION = 1 THEN
		-- 期中(未送信のみ対象)
		TRANS := 9; -- 9はありえないデータなので対象とならない
	END IF;
	-- 2011/06/17 ADD END

	-- 2011/06/17 UPDATE START
	/*
        SELECT COUNT(*)
        INTO   P_NUM_CNT
        FROM   (SELECT DISTINCT Y2IMCODE
                               ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
                               ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
                               ,Y2STARTDATE
                FROM   SIAP_CAY2
                WHERE  Y2STARTDATE < TARGET_MONTH)
        WHERE  Y2STARTDATE = Y2MAXD;
    */
	SELECT COUNT(*)
	INTO   P_NUM_CNT
	FROM   (SELECT DISTINCT Y2IMCODE
						   ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
						   ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
						   ,Y2STARTDATE
						   ,Y2SENDDIVISION
			FROM   SIAP_CAY2
			WHERE  Y2STARTDATE < TARGET_MONTH)
	WHERE  Y2STARTDATE = Y2MAXD
	AND    (Y2SENDDIVISION = UN_TRANS OR Y2SENDDIVISION = TRANS);
	-- 2011/06/17 UPDATE END

	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグY2PROCFLGが9:削除のデータをDELETE)
	-- 2011/06/17 DELETE ADD
	-- マスタ系を削除されると都合が悪いので削除しない
	/*
    DELETE FROM SIAP.IFY2@SIAPSOM
    WHERE  Y2PROCFLG = 9
    AND    Y2STATUS IN (1, 2)
    AND    Y2COCODE = '001'
    AND    Y2HBRCODE = '001';
    */
	-- 2011/06/17 DELETE END

	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	-- 2011/06/17 UPDATE ADD
	/*
    UPDATE SIAP.IFY2@SIAPSOM
    SET    Y2PROCFLG = 9
          ,Y2STATUS  = 0
          ,Y2ERRMSG  = NULL
    WHERE  Y2COCODE = '001'
    AND    Y2HBRCODE = '001';
    */
	-- 残ったレコードの処理フラグを一旦1:更新にしておく。
	UPDATE SIAP.IFY2@SIAPSOM
	SET    Y2PROCFLG = 1
	WHERE  Y2COCODE = '001'
	AND    Y2HBRCODE = '001';
	-- 2011/06/17 UPDATE END

	-- ap21品目マスタを取り込んで更新する。
	-- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
	MERGE INTO SIAP.IFY2@SIAPSOM Y2
	USING (SELECT CAY2.*
		   FROM   SIAP_CAY2 CAY2
				  -- 2011/06/17 UPDATE START
				  /*
                  ,(SELECT DISTINCT *
                    FROM   (SELECT Y2IMCODE
                                  ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
                                  ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
                                  ,Y2STARTDATE
                            FROM   SIAP_CAY2
                            WHERE  Y2STARTDATE < TARGET_MONTH)
                    WHERE  Y2STARTDATE = Y2MAXD) MAX_ITEM
                  */
				 ,(SELECT DISTINCT *
				   FROM   (SELECT Y2IMCODE
								 ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
								 ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
								 ,Y2STARTDATE
								 ,Y2SENDDIVISION
						   FROM   SIAP_CAY2
						   WHERE  Y2STARTDATE < TARGET_MONTH)
				   WHERE  Y2STARTDATE = Y2MAXD
				   AND    (Y2SENDDIVISION = UN_TRANS OR
						 Y2SENDDIVISION = TRANS)) MAX_ITEM
		   -- 2011/06/17 UPDATE END
		   WHERE  CAY2.Y2IMCODE = MAX_ITEM.Y2IMCODE
		   AND    CAY2.Y2VERSION = MAX_ITEM.Y2MAXV) A2
	ON (Y2.Y2COCODE = A2.Y2COCODE -- 会社コード
	AND Y2.Y2HBRCODE = A2.Y2HBRCODE -- 本支店コード
	AND Y2.Y2IMCODE = A2.Y2IMCODE -- 品目コード
	)
	WHEN MATCHED THEN
		UPDATE
		SET    Y2.Y2IMPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
			  ,Y2.Y2IMPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
			  ,Y2.Y2PROCFLG   = 1 -- 処理フラグ(1:更新)
			  ,Y2.Y2IMKANA    = A2.Y2IMKANA -- カナ名称
			  ,Y2.Y2HINNM     = A2.Y2HINNM -- 品目名称
			  ,Y2.Y2HINKBN    = A2.Y2HINKBN -- 品目区分
			  ,Y2.Y2ACNO      = A2.Y2ACNO -- 勘定科目コード
			  ,Y2.Y2UNIT      = A2.Y2UNIT -- 計算単位
			  ,Y2.Y2PHANTOM   = A2.Y2PHANTOM -- ファントム区分(0:通常 1:ファントム)
			  ,Y2.Y2STHANTK   = A2.Y2STHANTK -- 標準販売単価
			  ,Y2.Y2STSIRTK   = A2.Y2STSIRTK -- 標準仕入単価
			  ,Y2.Y2STSIQTK   = A2.Y2STSIQTK -- 標準支給単価(＝標準仕入単価)
			  ,Y2.Y2YSYOHTANK = A2.Y2YSYOHTANK -- 予定消費単価(仕入単価)
			  ,Y2.Y2HLOTSIZE  = A2.Y2HLOTSIZE -- 標準ロットサイズ(標準原価カードを計算する場合に必要)
			  ,Y2.Y2SBUNRUICD = A2.Y2SBUNRUICD -- 組コード(品目分類コード）
			  ,Y2.Y2UPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD')
			  ,Y2.Y2UPTIME    = TO_CHAR(SYSDATE, 'HH24MISS')
			  ,Y2.Y2UPID      = P_STR_TANTO_CD
	WHEN NOT MATCHED THEN
		INSERT
			(Y2IMPDATE -- 登録日(YYYYMMDD)
			,Y2IMPTIME -- 登録時間(HH24MISS)
			,Y2PROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照)
			,Y2COCODE -- 会社コード(自社マスタ攝津製油固定)
			,Y2HBRCODE -- 本支店コード(暫定！攝津製油固定)
			,Y2IMCODE -- 品目コード(20桁以内)
			,Y2IMKANA -- カナ名称
			,Y2IMTSHINCD -- 客先品目コード(20桁以内)
			,Y2HINNM -- 品目名称
			,Y2KIKAKU -- 規格(30桁以内。帳票、照会画面で表示）
			,Y2HINKBN -- 品目区分
			,Y2ACNO -- 勘定科目コード
			,Y2ACHOJO -- 補助科目コード（'0000000000')
			,Y2UNIT -- 計算単位
			,Y2LDTIME -- リードタイム(未使用　0)
			,Y2PHANTOM -- ファントム区分(0:通常 1:ファントム)
			,Y2HTKIJUN -- 発注基準(未使用　0)
			,Y2STHANTK -- 標準販売単価
			,Y2STSIRTK -- 標準仕入単価
			,Y2STSIQTK -- 標準支給単価(＝標準仕入単価)
			,Y2YSYOHTANK -- 予定消費単価(仕入単価)
			,Y2IZHKTANK -- 実際消費単価(未使用　0)
			,Y2LEVELCD -- ローレベルコード(未使用　0)
			,Y2GKEITAI -- 原価計算形態区分(4:工程別単純総合原価計算)
			,Y2HGKICHOU -- 標準原価記帳方式(2:設定なし)
			,Y2TKSCD -- 等価係数コード(設定不要 NULL)
			,Y2HLOTSIZE -- 標準ロットサイズ(標準原価カードを計算する場合に必要)
			,Y2SERIESCD -- 製品シリーズコード(10桁以内)
			,Y2SGMNTCD -- セグメントコード(10桁以内で設定してください。)
			,Y2SGMNTCDA -- セグメントコード（大分類）(未使用　NULL)
			,Y2SGMNTCDB -- セグメントコード（中分類）(未使用　NULL)
			,Y2SGMNTCDC -- セグメントコード（小分類）(未使用　NULL)
			,Y2SGMNTCD2 -- セグメントコード２(未使用　NULL)
			,Y2HTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,Y2SBUNRUICD -- 組コード(品目分類コード）
			,Y2SIKYU -- 支給区分(未使用　0)
			,Y2SKHNKKB -- 仕掛品計算方法(0:平均法)
			,Y2KTEIBKKB -- 工程別計算方法(0:累加法)
			,Y2SMDKSHO -- 進捗度計測方法(未使用　0)
			,Y2SPOTKBN -- スポット区分(未使用　0)
			,Y2ZAIKKBN -- 在庫管理区分(未使用　0)
			,Y2SYNYUKKB -- 出入記録区分(未使用　0)
			,Y2CZTNKKBN -- 購入時/消費時の単価使用法(1:購入のみ実際、消費は予定)
			,Y2TEMOQTY -- 手持在庫(未使用　0)
			,Y2SYKBN1 -- 区分１(未使用　0)
			,Y2SYKBN2 -- 区分２(未使用　0)
			,Y2SYKBN3 -- 区分３(未使用　0)
			,Y2SYKBN4 -- 区分４(未使用　0)
			,Y2SYKBN5 -- 区分５(未使用　0)
			,Y2IPDATE -- 登録日
			,Y2IPTIME -- 登録時間
			,Y2IPID -- 登録者ＩＤ
			,Y2UPDATE -- 更新日
			,Y2UPTIME -- 更新時間
			,Y2UPID -- 更新者ＩＤ
			 )
		VALUES
			(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD):    
			,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS):   
			,0 -- 処理フラグ(0:新規)
			,A2.Y2COCODE -- 会社コード(自社マスタ攝津製油固定)
			,A2.Y2HBRCODE -- 本支店コード(暫定！攝津製油固定)
			,A2.Y2IMCODE -- 品目コード(20桁以内)
			,A2.Y2IMKANA -- カナ名称
			,A2.Y2IMTSHINCD -- 客先品目コード(20桁以内)
			,A2.Y2HINNM -- 品目名称
			,A2.Y2KIKAKU -- 規格(30桁以内。帳票、照会画面で表示）
			,A2.Y2HINKBN -- 品目区分
			,A2.Y2ACNO -- 勘定科目コード
			,A2.Y2ACHOJO -- 補助科目コード（'0000000000')
			,A2.Y2UNIT -- 計算単位
			,A2.Y2LDTIME -- リードタイム(未使用　0)
			,A2.Y2PHANTOM -- ファントム区分(0:通常 1:ファントム)
			,A2.Y2HTKIJUN -- 発注基準(未使用　0)
			,A2.Y2STHANTK -- 標準販売単価
			,A2.Y2STSIRTK -- 標準仕入単価
			,A2.Y2STSIQTK -- 標準支給単価(＝標準仕入単価)
			,A2.Y2YSYOHTANK -- 予定消費単価(仕入単価)
			,A2.Y2IZHKTANK -- 実際消費単価(未使用　0)
			,A2.Y2LEVELCD -- ローレベルコード(未使用　0)
			,A2.Y2GKEITAI -- 原価計算形態区分(4:工程別単純総合原価計算)
			,A2.Y2HGKICHOU -- 標準原価記帳方式(2:設定なし)
			,A2.Y2TKSCD -- 等価係数コード(設定不要 NULL)
			,A2.Y2HLOTSIZE -- 標準ロットサイズ(標準原価カードを計算する場合に必要)
			,A2.Y2SERIESCD -- 製品シリーズコード(10桁以内)
			,A2.Y2SGMNTCD -- セグメントコード(10桁以内で設定してください。)
			,A2.Y2SGMNTCDA -- セグメントコード（大分類）(未使用　NULL)
			,A2.Y2SGMNTCDB -- セグメントコード（中分類）(未使用　NULL)
			,A2.Y2SGMNTCDC -- セグメントコード（小分類）(未使用　NULL)
			,A2.Y2SGMNTCD2 -- セグメントコード２(未使用　NULL)
			,A2.Y2HTANKAKB -- 消費単価計算方法( 3:月次総平均)
			,A2.Y2SBUNRUICD -- 組コード(品目分類コード）
			,A2.Y2SIKYU -- 支給区分(未使用　0)
			,A2.Y2SKHNKKB -- 仕掛品計算方法(0:平均法)
			,A2.Y2KTEIBKKB -- 工程別計算方法(0:累加法)
			,A2.Y2SMDKSHO -- 進捗度計測方法(未使用　0)
			,A2.Y2SPOTKBN -- スポット区分(未使用　0)
			,A2.Y2ZAIKKBN -- 在庫管理区分(未使用　0)
			,A2.Y2SYNYUKKB -- 出入記録区分(未使用　0)
			,A2.Y2CZTNKKBN -- 購入時/消費時の単価使用法(1:購入のみ実際、消費は予定)
			,A2.Y2TEMOQTY -- 手持在庫(未使用　0)
			,A2.Y2SYKBN1 -- 区分１(未使用　0)
			,A2.Y2SYKBN2 -- 区分２(未使用　0)
			,A2.Y2SYKBN3 -- 区分３(未使用　0)
			,A2.Y2SYKBN4 -- 区分４(未使用　0)
			,A2.Y2SYKBN5 -- 区分５(未使用　0)
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD
			,TO_CHAR(SYSDATE, 'YYYYMMDD')
			,TO_CHAR(SYSDATE, 'HH24MISS')
			,P_STR_TANTO_CD);

	IF P_NUM_SEND_DIVISION = 1 THEN
		-- 期中
		MERGE INTO COST_ACCOUNTING_LOG COST
		USING (SELECT CAY2.*
			   FROM   SIAP_CAY2 CAY2
					 ,(SELECT DISTINCT *
					   FROM   (SELECT Y2IMCODE
									 ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
									 ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
									 ,Y2STARTDATE
									 ,Y2SENDDIVISION
							   FROM   SIAP_CAY2
							   WHERE  Y2STARTDATE < TARGET_MONTH)
					   WHERE  Y2STARTDATE = Y2MAXD
					   AND    (Y2SENDDIVISION = UN_TRANS OR
							 Y2SENDDIVISION = TRANS)) MAX_ITEM
			   WHERE  CAY2.Y2IMCODE = MAX_ITEM.Y2IMCODE
			   AND    CAY2.Y2VERSION = MAX_ITEM.Y2MAXV) A2
		ON (COST.PROCESS_DATE = SYSDATE AND COST.ITEM_CD = A2.Y2IMCODE)
		WHEN MATCHED THEN
			UPDATE
			SET    COST.MODULE_CD = C_MODULE_CD
				  ,COST.CLIENT    = P_STR_TANTO_CD
		WHEN NOT MATCHED THEN
			INSERT VALUES (C_MODULE_CD, P_STR_TANTO_CD, SYSDATE, Y2IMCODE);
	END IF;

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
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFY2'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFY2;
/
