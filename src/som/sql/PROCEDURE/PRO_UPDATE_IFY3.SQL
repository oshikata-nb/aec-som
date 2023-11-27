CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFY3 -- ＣＡ作業手順マスタ
(
	P_STR_TARGET_MONTH  IN NVARCHAR2
   ,P_NUM_SEND_DIVISION IN NUMBER
   ,P_STR_TANTO_CD      IN NVARCHAR2
   ,P_NUM_CNT           OUT NUMBER
   ,P_NUM_RET           OUT NUMBER
)

	/************************************************************************/
	/*   PROCEDURE名 ：PRO_UPDATE_IFY3                                      */
	/*      処理内容 ：原価計算I/F 作業手順マスタ送信                       */
	/*          引数 ：対象年月                                             */
	/*               ：担当者コード                                         */
	/*               ：マスタ送信区分(1:期中 2:期首)                        */
	/*               ：処理件数(戻値)                                       */
	/*               ：戻値(0:成功 99:エラー)                               */
	/************************************************************************/
 IS
	/******************************** 宣言部 ********************************/
	-- カーソルの宣言
	-- 2011/06/17 UPDATE START
	--  CURSOR curCAY3() IS
	CURSOR curCAY3
	(
		UN_TRANS NUMBER
	   ,TRANS    NUMBER
	) IS
	-- 2011/06/17 UPDATE END
	/*                SELECT CAY3.*
                                                FROM
                                
                                    (SELECT CAY3.*
                                    FROM   SIAP_CAY3 CAY3
                                    WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
                                    AND    SUBSTR(Y3PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH) CAY3
                                
                                    ,(SELECT Y3COCODE,Y3HBRCODE,Y3IMCODE,MAX(Y3RECIPEVER) Y3RECIPEVER
                                    FROM   SIAP_CAY3 CAY3
                                    WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
                                    AND    SUBSTR(Y3PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH
                                                GROUP BY Y3COCODE,Y3HBRCODE,Y3IMCODE) MAX_CAY3
                                
                                                WHERE CAY3.Y3COCODE = MAX_CAY3.Y3COCODE
                                                AND CAY3.Y3HBRCODE = MAX_CAY3.Y3HBRCODE
                                                AND CAY3.Y3IMCODE = MAX_CAY3.Y3IMCODE
                                                AND CAY3.Y3RECIPEVER = MAX_CAY3.Y3RECIPEVER;
                                */
		SELECT DISTINCT CAY3.*
		FROM   SIAP_CAY3 CAY3
			   
			   --レシピバージョン高
			  ,(SELECT CAY3.*
					  ,FIRST_VALUE(CAY3.Y3RECIPEVER) OVER(PARTITION BY CAY3.Y3IMCODE, CAY3.Y3RECIPEPRIORITY, CAY3.Y3RECIPECD ORDER BY CAY3.Y3RECIPEVER) AS Y3RECIPEVER2
				FROM   SIAP_CAY3 CAY3
					   
					   --レシピコード低
					  ,(SELECT CAY3.*
							  ,FIRST_VALUE(CAY3.Y3RECIPECD) OVER(PARTITION BY CAY3.Y3IMCODE, CAY3.Y3RECIPEPRIORITY ORDER BY CAY3.Y3RECIPECD) AS Y3RECIPECD2
						FROM   SIAP_CAY3 CAY3
							   
							   --優先度高
							  ,(SELECT CAY3.*
									  ,FIRST_VALUE(CAY3.Y3RECIPEPRIORITY) OVER(PARTITION BY CAY3.Y3IMCODE ORDER BY CAY3.Y3RECIPEPRIORITY DESC) AS Y3RECIPEPRIORITY2
								FROM   SIAP_CAY3 CAY3
									   
									  ,(SELECT Y3COCODE
											  ,Y3HBRCODE
											  ,Y3IMCODE
											  ,Y3RECIPECD
											  ,Y3RECIPEVER
										FROM   SIAP_CAY3
										WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <=
											   P_STR_TARGET_MONTH
										AND    SUBSTR(Y3PSEDDATE, 1, 6) >=
											   P_STR_TARGET_MONTH
											  -- 2011/06/17 ADD START
										AND    (Y3SENDDIVISION = UN_TRANS OR
												Y3SENDDIVISION = TRANS)
										-- 2011/06/17 ADD END
										GROUP  BY Y3COCODE
												 ,Y3HBRCODE
												 ,Y3IMCODE
												 ,Y3RECIPECD
												 ,Y3RECIPEVER) A
								
								WHERE  CAY3.Y3COCODE = A.Y3COCODE
								AND    CAY3.Y3HBRCODE = A.Y3HBRCODE
								AND    CAY3.Y3IMCODE = A.Y3IMCODE
								AND    CAY3.Y3RECIPECD = A.Y3RECIPECD
								AND    CAY3.Y3RECIPEVER = A.Y3RECIPEVER) B
						
						WHERE  CAY3.Y3COCODE = B.Y3COCODE
						AND    CAY3.Y3HBRCODE = B.Y3HBRCODE
						AND    CAY3.Y3IMCODE = B.Y3IMCODE
						AND    CAY3.Y3RECIPECD = B.Y3RECIPECD
						AND    CAY3.Y3RECIPEVER = B.Y3RECIPEVER
						AND    CAY3.Y3RECIPEPRIORITY = B.Y3RECIPEPRIORITY2) C
				
				WHERE  CAY3.Y3COCODE = C.Y3COCODE
				AND    CAY3.Y3HBRCODE = C.Y3HBRCODE
				AND    CAY3.Y3IMCODE = C.Y3IMCODE
				AND    CAY3.Y3RECIPEVER = C.Y3RECIPEVER
				AND    CAY3.Y3RECIPEPRIORITY = C.Y3RECIPEPRIORITY
				AND    CAY3.Y3RECIPECD = C.Y3RECIPECD2) D
		
		WHERE  CAY3.Y3COCODE = D.Y3COCODE
		AND    CAY3.Y3HBRCODE = D.Y3HBRCODE
		AND    CAY3.Y3IMCODE = D.Y3IMCODE
		AND    CAY3.Y3RECIPEPRIORITY = D.Y3RECIPEPRIORITY
		AND    CAY3.Y3RECIPECD = D.Y3RECIPECD
		AND    CAY3.Y3RECIPEVER = D.Y3RECIPEVER2;

	-- カーソル変数の宣言
	rtCAY3 curCAY3%ROWTYPE;

	-- 変数の宣言
	CNT          NUMBER;
	UN_TRANS     NUMBER := 0; -- 未送信
	TRANS        NUMBER := 1; -- 送信済

	/******************************** 処理部 ********************************/
BEGIN
	-- 対象件数調査
	/*        SELECT COUNT(*)
      INTO   P_NUM_CNT
            FROM
      (SELECT CAY3.*
      FROM   SIAP_CAY3 CAY3
      WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
      AND    SUBSTR(Y3PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH) CAY3
      ,(SELECT Y3COCODE,Y3HBRCODE,Y3IMCODE,MAX(Y3RECIPEVER) Y3RECIPEVER
      FROM   SIAP_CAY3 CAY3
      WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
      AND    SUBSTR(Y3PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH
            GROUP BY Y3COCODE,Y3HBRCODE,Y3IMCODE) MAX_CAY3
            WHERE CAY3.Y3COCODE = MAX_CAY3.Y3COCODE
            AND CAY3.Y3HBRCODE = MAX_CAY3.Y3HBRCODE
            AND CAY3.Y3IMCODE = MAX_CAY3.Y3IMCODE
            AND CAY3.Y3RECIPEVER = MAX_CAY3.Y3RECIPEVER;
    */

	-- 2011/06/17 ADD START
	IF P_NUM_SEND_DIVISION = 1 THEN
		-- 期中(未送信のみ対象)
		TRANS := 9; -- 9はありえないデータなので対象とならない
	END IF;
	-- 2011/06/17 ADD END

	SELECT COUNT(CAY3.Y3RECIPEID)
	INTO   P_NUM_CNT
	FROM   SIAP_CAY3 CAY3
		   
		  ,(SELECT DISTINCT CAY3.*
			FROM   SIAP_CAY3 CAY3
				   
				   --レシピバージョン高
				  ,(SELECT CAY3.*
						  ,FIRST_VALUE(CAY3.Y3RECIPEVER) OVER(PARTITION BY CAY3.Y3IMCODE, CAY3.Y3RECIPEPRIORITY, CAY3.Y3RECIPECD ORDER BY CAY3.Y3RECIPEVER) AS Y3RECIPEVER2
					FROM   SIAP_CAY3 CAY3
						   
						   --レシピコード低
						  ,(SELECT CAY3.*
								  ,FIRST_VALUE(CAY3.Y3RECIPECD) OVER(PARTITION BY CAY3.Y3IMCODE, CAY3.Y3RECIPEPRIORITY ORDER BY CAY3.Y3RECIPECD) AS Y3RECIPECD2
							FROM   SIAP_CAY3 CAY3
								   
								   --優先度高
								  ,(SELECT CAY3.*
										  ,FIRST_VALUE(CAY3.Y3RECIPEPRIORITY) OVER(PARTITION BY CAY3.Y3IMCODE ORDER BY CAY3.Y3RECIPEPRIORITY DESC) AS Y3RECIPEPRIORITY2
									FROM   SIAP_CAY3 CAY3
										   
										  ,(SELECT Y3COCODE
												  ,Y3HBRCODE
												  ,Y3IMCODE
											FROM   SIAP_CAY3
											WHERE  SUBSTR(Y3JTSTDATE, 1, 6) <=
												   P_STR_TARGET_MONTH
											AND    SUBSTR(Y3PSEDDATE, 1, 6) >=
												   P_STR_TARGET_MONTH
												  -- 2011/06/17 ADD START
											AND    (Y3SENDDIVISION =
													UN_TRANS OR
													Y3SENDDIVISION = TRANS)
											-- 2011/06/17 ADD END
											GROUP  BY Y3COCODE
													 ,Y3HBRCODE
													 ,Y3IMCODE) A
									
									WHERE  CAY3.Y3COCODE = A.Y3COCODE
									AND    CAY3.Y3HBRCODE = A.Y3HBRCODE
									AND    CAY3.Y3IMCODE = A.Y3IMCODE) B
							
							WHERE  CAY3.Y3COCODE = B.Y3COCODE
							AND    CAY3.Y3HBRCODE = B.Y3HBRCODE
							AND    CAY3.Y3IMCODE = B.Y3IMCODE
							AND    CAY3.Y3RECIPEPRIORITY =
								   B.Y3RECIPEPRIORITY2) C
					
					WHERE  CAY3.Y3COCODE = C.Y3COCODE
					AND    CAY3.Y3HBRCODE = C.Y3HBRCODE
					AND    CAY3.Y3IMCODE = C.Y3IMCODE
					AND    CAY3.Y3RECIPEPRIORITY = C.Y3RECIPEPRIORITY
					AND    CAY3.Y3RECIPECD = C.Y3RECIPECD2) D
			
			WHERE  CAY3.Y3COCODE = D.Y3COCODE
			AND    CAY3.Y3HBRCODE = D.Y3HBRCODE
			AND    CAY3.Y3IMCODE = D.Y3IMCODE
			AND    CAY3.Y3RECIPEPRIORITY = D.Y3RECIPEPRIORITY
			AND    CAY3.Y3RECIPECD = D.Y3RECIPECD
			AND    CAY3.Y3RECIPEVER = D.Y3RECIPEVER2) E
	
	WHERE  CAY3.Y3RECIPEID = E.Y3RECIPEID;
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグY3PROCFLGが9:削除のデータをDELETE)
	DELETE FROM SIAP.IFY3@SIAPSOM
	WHERE  Y3PROCFLG = 9
	AND    Y3STATUS IN (1, 2)
	AND    Y3COCODE = '001'
	AND    Y3HBRCODE = '001';
	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	UPDATE SIAP.IFY3@SIAPSOM
	SET    Y3PROCFLG = 9
		  ,Y3STATUS  = 0
		  ,Y3ERRMSG  = NULL
	WHERE  Y3COCODE = '001'
	AND    Y3HBRCODE = '001';
	-- 2011/06/17 UPDATE START
	--  OPEN curCAY3();
	OPEN curCAY3(UN_TRANS, TRANS);
	-- 2011/06/17 UPDATE END
	LOOP
		FETCH curCAY3
			INTO rtCAY3;
	
		IF curCAY3%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		SELECT COUNT(*)
		INTO   CNT
		FROM   SIAP.IFY3@SIAPSOM
		WHERE  Y3COCODE = rtCAY3.Y3COCODE
		AND    Y3HBRCODE = rtCAY3.Y3HBRCODE
		AND    Y3IMCODE = rtCAY3.Y3IMCODE
		AND    Y3KSEQNO = rtCAY3.Y3KSEQNO;
	
		IF CNT = 0 THEN
			INSERT INTO SIAP.IFY3@SIAPSOM
				(Y3IMPDATE -- 登録日
				,Y3IMPTIME -- 登録時間
				,Y3PROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照)
				,Y3COCODE -- 会社コード(自社マスタ攝津製油固定)
				,Y3HBRCODE -- 本支店コード(暫定！攝津製油固定)
				,Y3IMCODE -- 品目コード
				,Y3HLOTSIZE -- 標準ロットサイズ
				,Y3TANI -- 単位
				,Y3SPFRT -- 不良率
				,Y3KSEQNO -- 工程順序
				,Y3KOTEICD -- 工程コード
				,Y3NGKBN -- 内外区分(0:社内加工)
				,Y3RMSGCODE -- 作業区分(未使用 0)
				,Y3GBUMONCD -- 原価部門コード(テーブル未設定のため追加必要)
				,Y3KAKOSYUCD -- 加工種コード(省略)
				,Y3SETUCD -- 設備コード
				,Y3RMWKNAME -- 作業名称
				,Y3RMSAJIKAN1 -- 予定作業時間(標準生産量)
				,Y3RMSEISAN -- 製造予定数
				,Y3IPDATE -- 登録日
				,Y3IPTIME -- 登録時間
				,Y3IPID -- 登録者ID
				,Y3UPDATE -- 更新日
				,Y3UPTIME -- 更新時間
				,Y3UPID) -- 更新者ID
			VALUES
				(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				,0 -- 処理フラグ(0:新規)
				,rtCAY3.Y3COCODE -- 会社コード(自社マスタ攝津製油固定)
				,rtCAY3.Y3HBRCODE -- 本支店コード(暫定！攝津製油固定)
				,rtCAY3.Y3IMCODE -- 品目コード
				,rtCAY3.Y3HLOTSIZE -- 標準ロットサイズ
				,rtCAY3.Y3TANI -- 単位
				,rtCAY3.Y3SPFRT -- 不良率
				,rtCAY3.Y3KSEQNO -- 工程順序
				,rtCAY3.Y3KOTEICD -- 工程コード
				,rtCAY3.Y3NGKBN -- 内外区分(0:社内加工)
				,rtCAY3.Y3RMSGCODE -- 作業区分(未使用 0)
				,rtCAY3.Y3GBUMONCD -- 原価部門コード(テーブル未設定のため追加必要)
				,rtCAY3.Y3KAKOSYUCD -- 加工種コード(省略)
				,rtCAY3.Y3SETUCD -- 設備コード
				,rtCAY3.Y3RMWKNAME -- 作業名称
				,rtCAY3.Y3RMSAJIKAN1 -- 予定作業時間(標準生産量)
				,rtCAY3.Y3RMSEISAN -- 製造予定数
				,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				,P_STR_TANTO_CD -- 登録者ID
				,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 更新時間(HH24MISS)
				,P_STR_TANTO_CD); -- 更新者ID
		ELSE
			UPDATE SIAP.IFY3@SIAPSOM
			SET    Y3IMPDATE    = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				  ,Y3IMPTIME    = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				  ,Y3PROCFLG    = 1 -- 処理フラグ
				  ,Y3HLOTSIZE   = rtCAY3.Y3HLOTSIZE -- 標準ロットサイズ
				  ,Y3KOTEICD    = rtCAY3.Y3KOTEICD -- 工程コード
				  ,Y3GBUMONCD   = rtCAY3.Y3GBUMONCD -- 原価部門コード(テーブル未設定のため追加必要)
				  ,Y3SETUCD     = rtCAY3.Y3SETUCD -- 設備コード
				  ,Y3RMWKNAME   = rtCAY3.Y3RMWKNAME -- 作業名称
				  ,Y3RMSAJIKAN1 = rtCAY3.Y3RMSAJIKAN1 -- 予定作業時間(標準生産量)
				  ,Y3UPDATE     = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
				  ,Y3UPTIME     = TO_CHAR(SYSDATE, 'HH24MISS') --    更新時間(HH23MISS)
				  ,Y3UPID       = P_STR_TANTO_CD -- 更新者ID
			WHERE  Y3COCODE = rtCAY3.Y3COCODE
			AND    Y3HBRCODE = rtCAY3.Y3HBRCODE
			AND    Y3IMCODE = rtCAY3.Y3IMCODE
			AND    Y3KSEQNO = rtCAY3.Y3KSEQNO;
		END IF;
	END LOOP;
	CLOSE curCAY3;
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
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFY3'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFY3;
/
