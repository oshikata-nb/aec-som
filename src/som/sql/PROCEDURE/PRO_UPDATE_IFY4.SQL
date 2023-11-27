CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFY4 -- ＣＡ部品構成マスタ
(
	P_STR_TARGET_MONTH  IN NVARCHAR2
   ,P_NUM_SEND_DIVISION IN NUMBER
   ,P_STR_TANTO_CD      IN NVARCHAR2
   ,P_NUM_CNT           OUT NUMBER
   ,P_NUM_RET           OUT NUMBER
)

	/************************************************************************/
	/*   PROCEDURE名 ：PRO_UPDATE_IFY4                                      */
	/*      処理内容 ：原価計算I/F 部品構成マスタ送信                       */
	/*          引数 ：対象年月                                             */
	/*               ：担当者コード                                         */
	/*               ：マスタ送信区分(1:期中 2:期首)                        */
	/*               ：処理件数(戻値)                                       */
	/*               ：戻値(0:成功 99:エラー)                               */
	/************************************************************************/
 IS
	/******************************** 宣言部 ********************************/
	-- カーソルの宣言
	CURSOR curCAY2
	(
		TARGET_MONTH CHAR
	   ,UN_TRANS     NUMBER
	   ,TRANS        NUMBER
	) IS
		SELECT DISTINCT *
		FROM   (SELECT Y2IMCODE
					  ,MAX(Y2VERSION) OVER(PARTITION BY Y2IMCODE) AS Y2MAXV
					  ,MAX(Y2STARTDATE) OVER(PARTITION BY Y2IMCODE) AS Y2MAXD
					  ,Y2STARTDATE
					  ,Y2SENDDIVISION
				FROM   SIAP_CAY2
				WHERE  Y2STARTDATE < TARGET_MONTH)
		WHERE  Y2STARTDATE = Y2MAXD
		AND    (Y2SENDDIVISION = UN_TRANS OR Y2SENDDIVISION = TRANS);

	-- 2011/06/17 UPDATE START
	--  CURSOR curCAY4 IS
	CURSOR curCAY4
	(
		UN_TRANS NUMBER
	   ,TRANS    NUMBER
	) IS
	-- 2011/06/17 UPDATE END
	/*                SELECT CAY4.*
                                                                                                FROM
                                                                                        (SELECT CAY4.*
                                                                                        FROM   SIAP_CAY4 CAY4
                                                                                        WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
                                                                                        AND    SUBSTR(Y4PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH) CAY4
                                                                                        ,(SELECT Y4COCODE,Y4HBRCODE,Y4IMCODE,MAX(Y4RECIPEVER) Y4RECIPEVER
                                                                                        FROM   SIAP_CAY4 CAY4
                                                                                        WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
                                                                                        AND    SUBSTR(Y4PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH
                                                                                                GROUP BY Y4COCODE,Y4HBRCODE,Y4IMCODE) MAX_CAY4
                                                                                                WHERE CAY4.Y4COCODE = MAX_CAY4.Y4COCODE
                                                                                                AND CAY4.Y4HBRCODE = MAX_CAY4.Y4HBRCODE
                                                                                                AND CAY4.Y4IMCODE = MAX_CAY4.Y4IMCODE
                                                                                                AND CAY4.Y4RECIPEVER = MAX_CAY4.Y4RECIPEVER;
                                                                                */
		SELECT DISTINCT CAY4.*
		FROM   SIAP_CAY4 CAY4
			   
			   --レシピバージョン高
			  ,(SELECT DISTINCT CAY4.*
							   ,FIRST_VALUE(CAY4.Y4RECIPEVER) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY, CAY4.Y4RECIPECD ORDER BY CAY4.Y4RECIPEVER) AS Y4RECIPEVER2
				FROM   SIAP_CAY4 CAY4
					   
					   --レシピコード低
					  ,(SELECT DISTINCT CAY4.*
									   ,FIRST_VALUE(CAY4.Y4RECIPECD) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY ORDER BY CAY4.Y4RECIPECD) AS Y4RECIPECD2
						FROM   SIAP_CAY4 CAY4
							   
							   --優先度高
							  ,(SELECT DISTINCT CAY4.*
											   ,FIRST_VALUE(CAY4.Y4RECIPEPRIORITY) OVER(PARTITION BY CAY4.Y4IMCODE ORDER BY CAY4.Y4RECIPEPRIORITY DESC) AS Y4RECIPEPRIORITY2
								FROM   SIAP_CAY4 CAY4
									   
									  ,(SELECT Y4COCODE
											  ,Y4HBRCODE
											  ,Y4IMCODE
											  ,Y4RECIPECD
											  ,Y4RECIPEVER
										FROM   SIAP_CAY4
										WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <=
											   P_STR_TARGET_MONTH
										AND    SUBSTR(Y4PSEDDATE, 1, 6) >=
											   P_STR_TARGET_MONTH
											  -- 2011/06/17 ADD START
											  -- 親品目が未送信の場合送信対象
										AND    ((Y4SENDDIVISION = UN_TRANS OR
												Y4SENDDIVISION = TRANS))
										-- 2011/06/17 ADD END
										GROUP  BY Y4COCODE
												 ,Y4HBRCODE
												 ,Y4IMCODE
												 ,Y4RECIPECD
												 ,Y4RECIPEVER) A
								
								WHERE  CAY4.Y4COCODE = A.Y4COCODE
								AND    CAY4.Y4HBRCODE = A.Y4HBRCODE
								AND    CAY4.Y4IMCODE = A.Y4IMCODE
								AND    CAY4.Y4RECIPECD = A.Y4RECIPECD
								AND    CAY4.Y4RECIPEVER = A.Y4RECIPEVER) B
						
						WHERE  CAY4.Y4COCODE = B.Y4COCODE
						AND    CAY4.Y4HBRCODE = B.Y4HBRCODE
						AND    CAY4.Y4IMCODE = B.Y4IMCODE
						AND    CAY4.Y4RECIPECD = B.Y4RECIPECD
						AND    CAY4.Y4RECIPEVER = B.Y4RECIPEVER
						AND    CAY4.Y4RECIPEPRIORITY = B.Y4RECIPEPRIORITY2) C
				
				WHERE  CAY4.Y4COCODE = C.Y4COCODE
				AND    CAY4.Y4HBRCODE = C.Y4HBRCODE
				AND    CAY4.Y4IMCODE = C.Y4IMCODE
				AND    CAY4.Y4RECIPEVER = C.Y4RECIPEVER
				AND    CAY4.Y4RECIPEPRIORITY = C.Y4RECIPEPRIORITY
				AND    CAY4.Y4RECIPECD = C.Y4RECIPECD2) D
		
		WHERE  CAY4.Y4COCODE = D.Y4COCODE
		AND    CAY4.Y4HBRCODE = D.Y4HBRCODE
		AND    CAY4.Y4IMCODE = D.Y4IMCODE
		AND    CAY4.Y4RECIPEPRIORITY = D.Y4RECIPEPRIORITY
		AND    CAY4.Y4RECIPECD = D.Y4RECIPECD
		AND    CAY4.Y4RECIPEVER = D.Y4RECIPEVER2;
/*
	-- 下位処方検索
	CURSOR curCAY4_2
	(
		IMCODE   NVARCHAR2
	   ,UN_TRANS NUMBER
	   ,TRANS    NUMBER
	) IS
		SELECT DISTINCT CAY4.*
		FROM   SIAP_CAY4 CAY4
			   
			   --レシピバージョン高
			  ,(SELECT DISTINCT CAY4.*
							   ,FIRST_VALUE(CAY4.Y4RECIPEVER) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY, CAY4.Y4RECIPECD ORDER BY CAY4.Y4RECIPEVER) AS Y4RECIPEVER2
				FROM   SIAP_CAY4 CAY4
					   
					   --レシピコード低
					  ,(SELECT DISTINCT CAY4.*
									   ,FIRST_VALUE(CAY4.Y4RECIPECD) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY ORDER BY CAY4.Y4RECIPECD) AS Y4RECIPECD2
						FROM   SIAP_CAY4 CAY4
							   
							   --優先度高
							  ,(SELECT DISTINCT CAY4.*
											   ,FIRST_VALUE(CAY4.Y4RECIPEPRIORITY) OVER(PARTITION BY CAY4.Y4IMCODE ORDER BY CAY4.Y4RECIPEPRIORITY DESC) AS Y4RECIPEPRIORITY2
								FROM   SIAP_CAY4 CAY4
									   
									  ,(SELECT Y4COCODE
											  ,Y4HBRCODE
											  ,Y4IMCODE
											  ,Y4RECIPECD
											  ,Y4RECIPEVER
										FROM   SIAP_CAY4
										WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <=
											   P_STR_TARGET_MONTH
										AND    SUBSTR(Y4PSEDDATE, 1, 6) >=
											   P_STR_TARGET_MONTH
										AND    Y4IMCODE = IMCODE
										GROUP  BY Y4COCODE
												 ,Y4HBRCODE
												 ,Y4IMCODE
												 ,Y4RECIPECD
												 ,Y4RECIPEVER) A
								
								WHERE  CAY4.Y4COCODE = A.Y4COCODE
								AND    CAY4.Y4HBRCODE = A.Y4HBRCODE
								AND    CAY4.Y4IMCODE = A.Y4IMCODE
								AND    CAY4.Y4RECIPECD = A.Y4RECIPECD
								AND    CAY4.Y4RECIPEVER = A.Y4RECIPEVER) B
						
						WHERE  CAY4.Y4COCODE = B.Y4COCODE
						AND    CAY4.Y4HBRCODE = B.Y4HBRCODE
						AND    CAY4.Y4IMCODE = B.Y4IMCODE
						AND    CAY4.Y4RECIPECD = B.Y4RECIPECD
						AND    CAY4.Y4RECIPEVER = B.Y4RECIPEVER
						AND    CAY4.Y4RECIPEPRIORITY = B.Y4RECIPEPRIORITY2) C
				
				WHERE  CAY4.Y4COCODE = C.Y4COCODE
				AND    CAY4.Y4HBRCODE = C.Y4HBRCODE
				AND    CAY4.Y4IMCODE = C.Y4IMCODE
				AND    CAY4.Y4RECIPEVER = C.Y4RECIPEVER
				AND    CAY4.Y4RECIPEPRIORITY = C.Y4RECIPEPRIORITY
				AND    CAY4.Y4RECIPECD = C.Y4RECIPECD2) D
		
		WHERE  CAY4.Y4COCODE = D.Y4COCODE
		AND    CAY4.Y4HBRCODE = D.Y4HBRCODE
		AND    CAY4.Y4IMCODE = D.Y4IMCODE
		AND    CAY4.Y4RECIPEPRIORITY = D.Y4RECIPEPRIORITY
		AND    CAY4.Y4RECIPECD = D.Y4RECIPECD
		AND    CAY4.Y4RECIPEVER = D.Y4RECIPEVER2;
*/
	-- カーソル変数の宣言
	rtCAY2   curCAY2%ROWTYPE;
	rtCAY4   curCAY4%ROWTYPE;
--	rtCAY4_2 curCAY4_2%ROWTYPE;

	-- 変数の宣言
	TARGET_MONTH CHAR(8) := P_STR_TARGET_MONTH || '99';
	CNT          NUMBER;
	UN_TRANS     NUMBER := 0; -- 未送信
	TRANS        NUMBER := 1; -- 送信済

	/******************************** 処理部 ********************************/
BEGIN
	-- 対象件数調査
	/*        SELECT COUNT(*)
        INTO   P_NUM_CNT
            FROM
        (SELECT CAY4.*
        FROM   SIAP_CAY4 CAY4
        WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
        AND    SUBSTR(Y4PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH) CAY4
        ,(SELECT Y4COCODE,Y4HBRCODE,Y4IMCODE,MAX(Y4RECIPEVER) Y4RECIPEVER
        FROM   SIAP_CAY4 CAY4
        WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <= P_STR_TARGET_MONTH
        AND    SUBSTR(Y4PSEDDATE, 1, 6) >= P_STR_TARGET_MONTH
            GROUP BY Y4COCODE,Y4HBRCODE,Y4IMCODE) MAX_CAY4
            WHERE CAY4.Y4COCODE = MAX_CAY4.Y4COCODE
            AND CAY4.Y4HBRCODE = MAX_CAY4.Y4HBRCODE
            AND CAY4.Y4IMCODE = MAX_CAY4.Y4IMCODE
            AND CAY4.Y4RECIPEVER = MAX_CAY4.Y4RECIPEVER;
    */
	-- 2011/06/17 ADD START
	IF P_NUM_SEND_DIVISION = 1 THEN
		-- 期中(未送信のみ対象)
		TRANS := 9; -- 9はありえないデータなので対象とならない
	END IF;
	-- 2011/06/17 ADD END

	P_NUM_CNT := 0;
	/*  SELECT COUNT(CAY4.Y4RECIPEID)
        INTO   P_NUM_CNT
        FROM   SIAP_CAY4 CAY4
               
              ,(SELECT DISTINCT CAY4.*
                FROM   SIAP_CAY4 CAY4
                       
                       --レシピバージョン高
                      ,(SELECT CAY4.*
                              ,FIRST_VALUE(CAY4.Y4RECIPEVER) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY, CAY4.Y4RECIPECD ORDER BY CAY4.Y4RECIPEVER) AS Y4RECIPEVER2
                        FROM   SIAP_CAY4 CAY4
                               
                               --レシピコード低
                              ,(SELECT CAY4.*
                                      ,FIRST_VALUE(CAY4.Y4RECIPECD) OVER(PARTITION BY CAY4.Y4IMCODE, CAY4.Y4RECIPEPRIORITY ORDER BY CAY4.Y4RECIPECD) AS Y4RECIPECD2
                                FROM   SIAP_CAY4 CAY4
                                       
                                       --優先度高
                                      ,(SELECT CAY4.*
                                              ,FIRST_VALUE(CAY4.Y4RECIPEPRIORITY) OVER(PARTITION BY CAY4.Y4IMCODE ORDER BY CAY4.Y4RECIPEPRIORITY DESC) AS Y4RECIPEPRIORITY2
                                        FROM   SIAP_CAY4 CAY4
                                               
                                              ,(SELECT Y4COCODE
                                                      ,Y4HBRCODE
                                                      ,Y4IMCODE
                                                FROM   SIAP_CAY4
                                                WHERE  SUBSTR(Y4JTSTDATE, 1, 6) <= '201004'
                                                AND    SUBSTR(Y4PSEDDATE, 1, 6) >= '201004'
                                                --        AND Y4IMCODE='13110244'
                                                GROUP  BY Y4COCODE
                                                         ,Y4HBRCODE
                                                         ,Y4IMCODE) A
                                        
                                        WHERE  CAY4.Y4COCODE = A.Y4COCODE
                                        AND    CAY4.Y4HBRCODE = A.Y4HBRCODE
                                        AND    CAY4.Y4IMCODE = A.Y4IMCODE) B
                                
                                WHERE  CAY4.Y4COCODE = B.Y4COCODE
                                AND    CAY4.Y4HBRCODE = B.Y4HBRCODE
                                AND    CAY4.Y4IMCODE = B.Y4IMCODE
                                AND    CAY4.Y4RECIPEPRIORITY = B.Y4RECIPEPRIORITY2) C
                        
                        WHERE  CAY4.Y4COCODE = C.Y4COCODE
                        AND    CAY4.Y4HBRCODE = C.Y4HBRCODE
                        AND    CAY4.Y4IMCODE = C.Y4IMCODE
                        AND    CAY4.Y4RECIPEPRIORITY = C.Y4RECIPEPRIORITY
                        AND    CAY4.Y4RECIPECD = C.Y4RECIPECD2) D
                
                WHERE  CAY4.Y4COCODE = D.Y4COCODE
                AND    CAY4.Y4HBRCODE = D.Y4HBRCODE
                AND    CAY4.Y4IMCODE = D.Y4IMCODE
                AND    CAY4.Y4RECIPEPRIORITY = D.Y4RECIPEPRIORITY
                AND    CAY4.Y4RECIPECD = D.Y4RECIPECD
                AND    CAY4.Y4RECIPEVER = D.Y4RECIPEVER2) E
        
        WHERE  CAY4.Y4RECIPEID = E.Y4RECIPEID;
    */
	-- 前回削除として送信済みのレコードを削除する。
	-- (処理フラグY4PROCFLGが9:削除のデータをDELETE)
	-- 2011/06/17 DELETE ADD
	-- マスタ系を削除されると都合が悪いので削除しない
	/*
    DELETE FROM SIAP.IFY4@SIAPSOM
    WHERE  Y4PROCFLG = 9
    AND    Y4STATUS IN (1, 2)
    AND    Y4COCODE = '001'
    AND    Y4HBRCODE = '001';
    */
	-- 2011/06/17 DELETE END

	-- 残ったレコードの処理フラグを一旦9:削除にしておく。
	-- 2011/06/17 UPDATE ADD
	/*
    UPDATE SIAP.IFY4@SIAPSOM
    SET    Y4PROCFLG = 9
          ,Y4STATUS  = 0
          ,Y4ERRMSG  = NULL
    WHERE  Y4COCODE = '001'
    AND    Y4HBRCODE = '001';
    */
	-- 残ったレコードの処理フラグを一旦1:更新にしておく。
	UPDATE SIAP.IFY4@SIAPSOM
	SET    Y4PROCFLG = 1
	WHERE  Y4COCODE = '001'
	AND    Y4HBRCODE = '001';
	-- 2011/06/17 UPDATE END

	-- 2011/06/17 UPDATE START
	--  OPEN curCAY4;
	OPEN curCAY4(UN_TRANS, TRANS);
	-- 2011/06/17 UPDATE END
	LOOP
		FETCH curCAY4
			INTO rtCAY4;
	
		IF curCAY4%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		SELECT COUNT(*)
		INTO   CNT
		FROM   SIAP.IFY4@SIAPSOM
		WHERE  Y4COCODE = rtCAY4.Y4COCODE
		AND    Y4HBRCODE = rtCAY4.Y4HBRCODE
		AND    Y4IMCODE = rtCAY4.Y4IMCODE
		AND    Y4KSEQNO = rtCAY4.Y4KSEQNO
		AND    Y4HINSEQNO = rtCAY4.Y4HINSEQNO;
	
		IF CNT = 0 THEN
			INSERT INTO SIAP.IFY4@SIAPSOM
				(Y4IMPDATE -- 登録日
				,Y4IMPTIME -- 登録時間
				,Y4PROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照)
				,Y4COCODE -- 会社コード(自社マスタ攝津製油固定)
				,Y4HBRCODE -- 本支店コード(暫定！攝津製油固定)
				,Y4IMCODE -- 品目コード
				,Y4KSEQNO -- 工程順序(0固定)
				,Y4HINSEQNO -- 品目順序(投入順序)
				,Y4KOIMCODE -- 子品目コード(20桁以内)
				,Y4SPKUBUN -- 使用数区分(固定 0)
				,Y4SPQUTY -- 使用数
				,Y4STANI -- 使用単位
				,Y4JTSTDATE -- 有効日
				,Y4PSEDDATE -- 失効日
				,Y4SIKYU -- 支給区分(0:無償支給 1:有償支給)
				,Y4SPSCRP -- 歩留率(未使用 0)
				,Y4IPDATE -- 登録日
				,Y4IPTIME -- 登録時間
				,Y4IPID -- 登録者ID
				,Y4UPDATE -- 更新日
				,Y4UPTIME -- 更新時間
				,Y4UPID) -- 更新者ID
			VALUES
				(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				,0 -- 処理フラグ(0:新規)
				,rtCAY4.Y4COCODE -- 会社コード(自社マスタ攝津製油固定)
				,rtCAY4.Y4HBRCODE -- 本支店コード(暫定！攝津製油固定)
				,rtCAY4.Y4IMCODE -- 品目コード
				,rtCAY4.Y4KSEQNO -- 工程順序(0固定)
				,rtCAY4.Y4HINSEQNO -- 品目順序(投入順序)
				,rtCAY4.Y4KOIMCODE -- 子品目コード(20桁以内)
				,rtCAY4.Y4SPKUBUN -- 使用数区分(固定 0)
				,rtCAY4.Y4SPQUTY -- 使用数
				,rtCAY4.Y4STANI -- 使用単位
				,rtCAY4.Y4JTSTDATE -- 有効日(YYYYMMDD)
				,rtCAY4.Y4PSEDDATE -- 失効日(YYYYMMDD)
				,rtCAY4.Y4SIKYU -- 支給区分(0:無償支給 1:有償支給)
				,rtCAY4.Y4SPSCRP -- 歩留率(未使用 0)
				,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				,P_STR_TANTO_CD -- 登録者ID
				,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
				,TO_CHAR(SYSDATE, 'HH24MISS') -- 更新時間(HH24MISS)
				,P_STR_TANTO_CD); -- 更新者ID
		ELSE
			UPDATE SIAP.IFY4@SIAPSOM
			SET    Y4IMPDATE  = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
				  ,Y4IMPTIME  = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
				  ,Y4PROCFLG  = 1 -- 処理フラグ
				  ,Y4KOIMCODE = rtCAY4.Y4KOIMCODE -- 子品目コード(20桁以内)
				  ,Y4SPQUTY   = rtCAY4.Y4SPQUTY -- 使用数
				  ,Y4JTSTDATE = rtCAY4.Y4JTSTDATE -- 有効日
				  ,Y4PSEDDATE = rtCAY4.Y4PSEDDATE -- 失効日
				  ,Y4SIKYU    = rtCAY4.Y4SIKYU -- 支給区分
				  ,Y4UPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
				  ,Y4UPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 更新時間(HH23MISS)
				  ,Y4UPID     = P_STR_TANTO_CD -- 更新者ID
			WHERE  Y4COCODE = rtCAY4.Y4COCODE
			AND    Y4HBRCODE = rtCAY4.Y4HBRCODE
			AND    Y4IMCODE = rtCAY4.Y4IMCODE
			AND    Y4KSEQNO = rtCAY4.Y4KSEQNO
			AND    Y4HINSEQNO = rtCAY4.Y4HINSEQNO;
		END IF;
/*	
		-- 期中 
		IF P_NUM_SEND_DIVISION = 1 THEN
            -- 未送信の子品目
			IF rtCAY4.Y4KOSENDDIVISION = 0 THEN
				-- 下位処方検索
				OPEN curCAY4_2(rtCAY4.Y4KOIMCODE, UN_TRANS, TRANS);
			
				LOOP
					FETCH curCAY4_2
						INTO rtCAY4_2;
				
					IF curCAY4_2%NOTFOUND = TRUE THEN
						EXIT;
					END IF;
				
					SELECT COUNT(*)
					INTO   CNT
					FROM   SIAP.IFY4@SIAPSOM
					WHERE  Y4COCODE = rtCAY4_2.Y4COCODE
					AND    Y4HBRCODE = rtCAY4_2.Y4HBRCODE
					AND    Y4IMCODE = rtCAY4_2.Y4IMCODE
					AND    Y4KSEQNO = rtCAY4_2.Y4KSEQNO
					AND    Y4HINSEQNO = rtCAY4_2.Y4HINSEQNO;
				
					IF CNT = 0 THEN
						INSERT INTO SIAP.IFY4@SIAPSOM
							(Y4IMPDATE -- 登録日
							,Y4IMPTIME -- 登録時間
							,Y4PROCFLG -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照)
							,Y4COCODE -- 会社コード(自社マスタ攝津製油固定)
							,Y4HBRCODE -- 本支店コード(暫定！攝津製油固定)
							,Y4IMCODE -- 品目コード
							,Y4KSEQNO -- 工程順序(0固定)
							,Y4HINSEQNO -- 品目順序(投入順序)
							,Y4KOIMCODE -- 子品目コード(20桁以内)
							,Y4SPKUBUN -- 使用数区分(固定 0)
							,Y4SPQUTY -- 使用数
							,Y4STANI -- 使用単位
							,Y4JTSTDATE -- 有効日
							,Y4PSEDDATE -- 失効日
							,Y4SIKYU -- 支給区分(0:無償支給 1:有償支給)
							,Y4SPSCRP -- 歩留率(未使用 0)
							,Y4IPDATE -- 登録日
							,Y4IPTIME -- 登録時間
							,Y4IPID -- 登録者ID
							,Y4UPDATE -- 更新日
							,Y4UPTIME -- 更新時間
							,Y4UPID) -- 更新者ID
						VALUES
							(TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
							,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
							,0 -- 処理フラグ(0:新規)
							,rtCAY4_2.Y4COCODE -- 会社コード(自社マスタ攝津製油固定)
							,rtCAY4_2.Y4HBRCODE -- 本支店コード(暫定！攝津製油固定)
							,rtCAY4_2.Y4IMCODE -- 品目コード
							,rtCAY4_2.Y4KSEQNO -- 工程順序(0固定)
							,rtCAY4_2.Y4HINSEQNO -- 品目順序(投入順序)
							,rtCAY4_2.Y4KOIMCODE -- 子品目コード(20桁以内)
							,rtCAY4_2.Y4SPKUBUN -- 使用数区分(固定 0)
							,rtCAY4_2.Y4SPQUTY -- 使用数
							,rtCAY4_2.Y4STANI -- 使用単位
							,rtCAY4_2.Y4JTSTDATE -- 有効日(YYYYMMDD)
							,rtCAY4_2.Y4PSEDDATE -- 失効日(YYYYMMDD)
							,rtCAY4_2.Y4SIKYU -- 支給区分(0:無償支給 1:有償支給)
							,rtCAY4_2.Y4SPSCRP -- 歩留率(未使用 0)
							,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
							,TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
							,P_STR_TANTO_CD -- 登録者ID
							,TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
							,TO_CHAR(SYSDATE, 'HH24MISS') -- 更新時間(HH24MISS)
							,P_STR_TANTO_CD); -- 更新者ID
					ELSE
						UPDATE SIAP.IFY4@SIAPSOM
						SET    Y4IMPDATE  = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 登録日(YYYYMMDD)
							  ,Y4IMPTIME  = TO_CHAR(SYSDATE, 'HH24MISS') -- 登録時間(HH24MISS)
							  ,Y4PROCFLG  = 1 -- 処理フラグ
							  ,Y4KOIMCODE = rtCAY4_2.Y4KOIMCODE -- 子品目コード(20桁以内)
							  ,Y4SPQUTY   = rtCAY4_2.Y4SPQUTY -- 使用数
							  ,Y4JTSTDATE = rtCAY4_2.Y4JTSTDATE -- 有効日
							  ,Y4PSEDDATE = rtCAY4_2.Y4PSEDDATE -- 失効日
							  ,Y4SIKYU    = rtCAY4_2.Y4SIKYU -- 支給区分
							  ,Y4UPDATE   = TO_CHAR(SYSDATE, 'YYYYMMDD') -- 更新日(YYYYMMDD)
							  ,Y4UPTIME   = TO_CHAR(SYSDATE, 'HH24MISS') -- 更新時間(HH23MISS)
							  ,Y4UPID     = P_STR_TANTO_CD -- 更新者ID
						WHERE  Y4COCODE = rtCAY4_2.Y4COCODE
						AND    Y4HBRCODE = rtCAY4_2.Y4HBRCODE
						AND    Y4IMCODE = rtCAY4_2.Y4IMCODE
						AND    Y4KSEQNO = rtCAY4_2.Y4KSEQNO
						AND    Y4HINSEQNO = rtCAY4_2.Y4HINSEQNO;
					END IF;
				
					P_NUM_CNT := P_NUM_CNT + 1;
				END LOOP;
			
				CLOSE curCAY4_2;
			END IF;
		END IF;
*/	
		P_NUM_CNT := P_NUM_CNT + 1;
	END LOOP;
	CLOSE curCAY4;

	OPEN curCAY2(TARGET_MONTH, UN_TRANS, TRANS);

	LOOP
		FETCH curCAY2
			INTO rtCAY2;
	
		IF curCAY2%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		-- 品目マスタ更新
		UPDATE ITEM
		SET    COST_DIVISION = 1
--			  ,UPDATOR_CD    = P_STR_TANTO_CD
--			  ,UPDATE_DATE   = SYSDATE
		WHERE  ITEM_CD = rtCAY2.Y2IMCODE
		AND    COST_DIVISION = 0;
	
		-- 品目マスタキュー更新
		UPDATE ITEM_QUEUE
		SET    COST_DIVISION = 1
--			  ,UPDATOR_CD    = P_STR_TANTO_CD
--			  ,UPDATE_DATE   = SYSDATE
		WHERE  ITEM_CD = rtCAY2.Y2IMCODE
		AND    COST_DIVISION = 0;
	END LOOP;

	CLOSE curCAY2;

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
		OUTPUT_ERROR_LOG('PRO_UPDATE_IFY4'
						,P_STR_TANTO_CD
						,SQLCODE
						,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFY4;
/
