CREATE OR REPLACE PROCEDURE AP21.PRO_CLAIM_UPDATEROLLBACK_CSM
(
	P_STR_ORGANIZATION_CD IN NVARCHAR2
   ,P_STR_VENDER_CD       IN NVARCHAR2
   ,P_STR_CREDIT_DATE     IN NVARCHAR2
   ,P_STR_INPUTOR_CD      IN NVARCHAR2
   ,P_NUM_RET             OUT NUMBER
   ,P_NUM_UPDATE_COUNT    OUT NUMBER
) IS
	/*----------------------------------------------------------------------------------------------------------------
      種別      : PROCEDURE
      名称      : PRO_CLAIM_UPDATEROLLBACK_CSM
      処理内容  : 請求更新ロールバック処理(カスタマイズ)
                  通常請求更新ロールバック処理＋消込トラン(カスタマイズ)削除処理
      引数      : P_STR_ORGANIZATION_CD 部署コード   NVARCHAR2型
      引数      : P_STR_VENDER_CD     請求先コード  NVARCHAR2型
      引数      : P_STR_CREDIT_DATE   請求締め日   NVARCHAR2型
      引数      : P_STR_INPUTOR_CD    登録者ＩＤ   NVARCHAR2型
      引数(戻値): P_NUM_RET       実行結果(0:正常終了, 99:異常終了) NUMBER型
      VERNO.    : 1.01
    (history)
      date        ver     name                comments
      ----------  ------  ------------------  ----------------------------------------------------------------------
      2008.10.09  1.00    TOSCO               creation
      2009.06.10                              SOM殿向けカスタマイズ
      2010.08.18  1.01    nanba               消込ロールバック機能追加
      2010.08.20  1.02    nanba               消込ロールバック機能改良
    ----------------------------------------------------------------------------------------------------------------*/
	------------------------------------------------------------
	--定数・変数宣言
	------------------------------------------------------------
	c_ClaimTrgDiv0            CONSTANT SALES.CLAIM_TARGET_DIVISION%TYPE := 0; -- 請求対象(未処理)
	c_ClaimUpdDiv0            CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE := 0; -- 請求更新フラグ(未処理)
	c_ClaimUpdDiv1            CONSTANT SALES.CLAIM_UPDATE_DIVISION%TYPE := 1; -- 請求更新フラグ(更新済)
	c_EraserCompleteDivision0 CONSTANT SALES.ERASER_COMPLETE_DIVISION%TYPE := 0; -- 消込完了フラグ(0:未処理)
	c_VenderDivTS             CONSTANT VENDER.VENDER_DIVISION%TYPE := 'TS'; -- 取引先区分(TS:得意先)
	daCreditDate CLAIM_HEADER.CREDIT_DATE%TYPE; -- 請求締め日
	nUpdateCount NUMBER := 0; -- 処理レコード件数

	--請求更新ロールバック対象の請求先抽出
	CURSOR curVenderCd(p_daCreditDate IN DATE) IS
		SELECT VENDER.VENDER_CD
			  ,VENDER.ORGANIZATION_CD
		FROM   VENDER
			  ,(SELECT VENDER_CD
					  ,MAX(CREDIT_DATE) MAX_CREDIT_DATE
				FROM   CLAIM_HEADER
				WHERE  ORGANIZATION_CD = P_STR_ORGANIZATION_CD
				AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
				GROUP  BY VENDER_CD) CLAIM_HEADER_MAX_REC
			  ,(SELECT SLIP_NO
					  ,INVOICE_CD AS VENDER_CD
				FROM   ERASER_CSM
				WHERE  ORGANIZATION_CD = P_STR_ORGANIZATION_CD
				AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR INVOICE_CD = P_STR_VENDER_CD)
				AND    INVOICE_UPDATE_DATE = p_daCreditDate
				AND    ERASER_AMOUNT <> 0 --消込額
				) ERASER
			  ,(SELECT CREDIT_NO
					  ,VENDER_CD
				FROM   CREDIT
				WHERE  ORGANIZATION_CD = P_STR_ORGANIZATION_CD
				AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
				AND    INVOICE_UPDATE_DATE = p_daCreditDate
				AND    ERASER_AMOUNT <> 0 --消込額
				) CREDIT
		WHERE  VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
		AND    (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
		AND    VENDER.VENDER_DIVISION = c_VenderDivTS
		AND    ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
		AND    VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
		AND    CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = p_daCreditDate
		AND    VENDER.VENDER_CD = ERASER.VENDER_CD(+)
			  --    AND ERASER.SLIP_NO IS NULL
		AND    VENDER.VENDER_CD = CREDIT.VENDER_CD(+)
			  --    AND CREDIT.CREDIT_NO IS NULL
		AND    VENDER.ACCOUNTS_CD IS NOT NULL; -- 2009/10/13 請求先定義変更
BEGIN
	-- 文字型をDate型へキャスト
	daCreditDate := TO_DATE(P_STR_CREDIT_DATE, 'YYYY/MM/DD');
	/* 請求更新ロールバック対象の請求先抽出 */
	FOR REC_MAIN IN curVenderCd(daCreditDate) LOOP
		------------------------------------------------------------
		--★売上トランザクション消込情報削除
		------------------------------------------------------------
		UPDATE SALES
		SET    ERASER_COMPLETE_DIVISION = c_EraserCompleteDivision0 -- 消込完了フラグ(0:未処理)
			  ,ERASER_COMPLETE_DATE     = NULL -- 消込完了日
			  ,UPDATE_DATE              = SYSDATE -- 更新日時
			  ,UPDATOR_CD               = P_STR_INPUTOR_CD -- 更新者ID
		WHERE  CHARGE_ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    DECODE(SALES.INVOICE_CD, NULL, SALES.VENDER_CD, SALES.INVOICE_CD) = REC_MAIN.VENDER_CD -- 請求先
		AND    ERASER_COMPLETE_DATE IN (SELECT ERASER_DATE
										FROM   ERASER_CSM
										WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
										AND    INVOICE_CD = REC_MAIN.VENDER_CD
										AND    INVOICE_UPDATE_DATE = daCreditDate);
		------------------------------------------------------------
		--★入金トランザクション消込情報削除
		------------------------------------------------------------
		UPDATE CREDIT
		SET    ERASER_AMOUNT            = 0 -- 消込額
			  ,CREDIT_ERASER_AMOUNT     = CREDIT_AMOUNT -- 入金消込残
			  ,ERASER_COMPLETE_DIVISION = c_EraserCompleteDivision0 -- 消込完了フラグ(0:未処理)
			  ,ERASER_COMPLETE_DATE     = NULL -- 消込完了日
			  ,UPDATE_DATE              = SYSDATE -- 更新日時
			  ,UPDATOR_CD               = P_STR_INPUTOR_CD -- 更新者ID
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    VENDER_CD = REC_MAIN.VENDER_CD -- 請求先
		AND    ERASER_COMPLETE_DATE IN (SELECT ERASER_DATE
										FROM   ERASER_CSM
										WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
										AND    INVOICE_CD = REC_MAIN.VENDER_CD
										AND    INVOICE_UPDATE_DATE = daCreditDate);
		------------------------------------------------------------
		--★支払トランザクション消込情報削除
		------------------------------------------------------------
		UPDATE PAYMENT
		SET    ERASER_COMPLETE_DIVISION = c_EraserCompleteDivision0 -- 消込完了フラグ(0:未処理)
			  ,ERASER_COMPLETE_DATE     = NULL -- 消込完了日
			  ,UPDATE_DATE              = SYSDATE -- 更新日時
			  ,UPDATOR_CD               = P_STR_INPUTOR_CD -- 更新者ID
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD -- 仕入先
		AND    ERASER_COMPLETE_DATE IN (SELECT ERASER_DATE
										FROM   ERASER_CSM
										WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
										AND    INVOICE_CD = REC_MAIN.VENDER_CD
										AND    INVOICE_UPDATE_DATE = daCreditDate);
		------------------------------------------------------------
		--★グループ間相殺トランザクション消込情報削除
		------------------------------------------------------------
		UPDATE OFFSET_GROUP_DATA
		SET    ERASER_AMOUNT            = 0 -- 消込額
			  ,CREDIT_ERASER_AMOUNT     = DEPOSIT_OFFSET_AMOUNT -- 入金消込残
			  ,ERASER_COMPLETE_DIVISION = c_EraserCompleteDivision0 -- 消込完了フラグ(0:未処理)
			  ,ERASER_COMPLETE_DATE     = NULL -- 消込完了日
			  ,UPDATE_DATE              = SYSDATE -- 更新日時
			  ,UPDATOR_CD               = P_STR_INPUTOR_CD -- 更新者ID
		WHERE  VENDER_CD = REC_MAIN.VENDER_CD -- 請求先
		AND    ERASER_COMPLETE_DATE IN (SELECT ERASER_DATE
										FROM   ERASER_CSM
										WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
										AND    INVOICE_CD = REC_MAIN.VENDER_CD
										AND    INVOICE_UPDATE_DATE = daCreditDate);
		------------------------------------------------------------
		--★消込トランザクション(カスタマイズ)削除
		------------------------------------------------------------
		DELETE FROM ERASER_CSM
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    INVOICE_CD = REC_MAIN.VENDER_CD
		AND    INVOICE_UPDATE_DATE = daCreditDate;
		------------------------------------------------------------
		--★売上トランザクション更新
		------------------------------------------------------------
		UPDATE SALES
		SET    CLAIM_TARGET_DIVISION = c_ClaimTrgDiv0 -- 請求対象(未処理)
			  ,CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
			  ,CLAIM_NO              = NULL -- 請求番号
			  ,INVOICE_UPDATE_DATE   = NULL -- 請求締め日
			  ,UPDATE_DATE           = SYSDATE -- 更新日時
			  ,UPDATOR_CD            = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
		AND    INVOICE_UPDATE_DATE = daCreditDate -- 請求締め日
		AND    CHARGE_ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    DECODE(SALES.INVOICE_CD, NULL, SALES.VENDER_CD, SALES.INVOICE_CD) = REC_MAIN.VENDER_CD; -- 請求先
		------------------------------------------------------------
		--★入金トランザクション更新
		------------------------------------------------------------
		UPDATE CREDIT
		SET    CLAIM_TARGET_DIVISION = c_ClaimTrgDiv0 -- 請求対象(未処理)
			  ,CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
			  ,CLAIM_NO              = NULL -- 請求番号
			  ,INVOICE_UPDATE_DATE   = NULL -- 請求締め日
			  ,UPDATE_DATE           = SYSDATE -- 更新日時
			  ,UPDATOR_CD            = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
		AND    INVOICE_UPDATE_DATE = daCreditDate -- 請求締め日
		AND    ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    VENDER_CD = REC_MAIN.VENDER_CD; -- 請求先
		------------------------------------------------------------
		--★支払トランザクション更新
		------------------------------------------------------------
		UPDATE PAYMENT
		SET    CLAIM_TARGET_DIVISION = c_ClaimTrgDiv0 -- 請求対象(未処理)
			  ,CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
			  ,CLAIM_NO              = NULL -- 請求番号
			  ,INVOICE_UPDATE_DATE   = NULL -- 請求締め日
			  ,UPDATE_DATE           = SYSDATE -- 更新日時
			  ,UPDATOR_CD            = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
		AND    INVOICE_UPDATE_DATE = daCreditDate -- 請求締め日
		AND    ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD -- 部署コード
		AND    SUPPLIER_CD = REC_MAIN.VENDER_CD; -- 仕入先
		------------------------------------------------------------
		--★グループ間相殺トランザクション更新
		------------------------------------------------------------
		UPDATE OFFSET_GROUP_DATA
		SET    CLAIM_TARGET_DIVISION = c_ClaimTrgDiv0 -- 請求対象(未処理)
			  ,CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv0 -- 請求更新フラグ(未処理)
			  ,CLAIM_NO              = NULL -- 請求番号
			  ,INVOICE_UPDATE_DATE   = NULL -- 請求締め日
			  ,UPDATE_DATE           = SYSDATE -- 更新日時
			  ,UPDATOR_CD            = P_STR_INPUTOR_CD -- 更新者ＩＤ
		WHERE  CLAIM_UPDATE_DIVISION = c_ClaimUpdDiv1 -- 請求更新フラグ(更新済)
		AND    INVOICE_UPDATE_DATE = daCreditDate -- 請求締め日
		AND    VENDER_CD = REC_MAIN.VENDER_CD; -- 請求先
		------------------------------------------------------------
		--★請求ヘッダー削除
		------------------------------------------------------------
		DELETE FROM CLAIM_HEADER
		WHERE  ORGANIZATION_CD = REC_MAIN.ORGANIZATION_CD
		AND    VENDER_CD = REC_MAIN.VENDER_CD
		AND    CREDIT_DATE = daCreditDate;
		nUpdateCount := nUpdateCount + SQL%ROWCOUNT;
	END LOOP;
	--------------------------------------------------------------------------------
	--仮締めデータ削除処理
	--------------------------------------------------------------------------------
	-------------------------------------------------
	--★仮締め請求ヘッダー削除
	-------------------------------------------------
	/*  DELETE FROM TEMPORARY_CLAIM_HEADER
      WHERE
        VENDER_CD IN (
                SELECT
                  VENDER_CD
                FROM 
                  (
                  SELECT
                    VENDER_CD
                  , DECODE(CLOSING_DATE, 99, TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)),
                      CASE WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)) < CLOSING_DATE 
                          THEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2))
                         ELSE CLOSING_DATE
                      END
                    ) CLOSING_DATE
                  , ORGANIZATION_CD
                  FROM
                    VENDER
                  WHERE
                    ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
                    AND VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                  ) VENDER
                WHERE
                  VENDER.CLOSING_DATE = TO_NUMBER(SUBSTR(TO_CHAR(daCreditDate, 'YYYYMMDD'), 7, 2))
              );
      -------------------------------------------------
      --★仮締め売上トランザクション削除
      -------------------------------------------------
      DELETE FROM TEMPORARY_CLAIM_SALES
      WHERE
        DECODE(INVOICE_CD, NULL, VENDER_CD, INVOICE_CD) IN 
        (
          SELECT
            VENDER_CD
          FROM 
            (
            SELECT
              VENDER_CD
            , DECODE(CLOSING_DATE, 99, TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)),
                CASE WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)) < CLOSING_DATE 
                    THEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2))
                   ELSE CLOSING_DATE
                END
              ) CLOSING_DATE
            , ORGANIZATION_CD
            FROM
              VENDER
            WHERE
              ORGANIZATION_CD = P_STR_ORGANIZATION_CD
            AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
              AND VENDER_DIVISION = c_VenderDivTS
            AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
            ) VENDER
          WHERE
            VENDER.CLOSING_DATE = TO_NUMBER(SUBSTR(TO_CHAR(daCreditDate, 'YYYYMMDD'), 7, 2))
        );
      -------------------------------------------------
      --★仮締め入金トランザクション削除
      -------------------------------------------------
      DELETE FROM TEMPORARY_CLAIM_CREDIT
      WHERE
        VENDER_CD IN
        (
          SELECT
            VENDER_CD
          FROM 
            (
            SELECT
              VENDER_CD
            , DECODE(CLOSING_DATE, 99, TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)),
                CASE WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)) < CLOSING_DATE 
                    THEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2))
                   ELSE CLOSING_DATE
                END
              ) CLOSING_DATE
            , ORGANIZATION_CD
            FROM
              VENDER
            WHERE
              ORGANIZATION_CD = P_STR_ORGANIZATION_CD
            AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
              AND VENDER_DIVISION = c_VenderDivTS
            AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
            ) VENDER
          WHERE
            VENDER.CLOSING_DATE = TO_NUMBER(SUBSTR(TO_CHAR(daCreditDate, 'YYYYMMDD'), 7, 2))
        );
      -------------------------------------------------
      --★仮締め支払トランザクション削除
      -------------------------------------------------
      DELETE FROM TEMPORARY_CLAIM_PAYMENT
      WHERE
        SUPPLIER_CD IN
        (
          SELECT
            VENDER_CD
          FROM 
            (
            SELECT
              VENDER_CD
            , DECODE(CLOSING_DATE, 99, TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)),
                CASE WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)) < CLOSING_DATE 
                    THEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2))
                   ELSE CLOSING_DATE
                END
              ) CLOSING_DATE
            , ORGANIZATION_CD
            FROM
              VENDER
            WHERE
              ORGANIZATION_CD = P_STR_ORGANIZATION_CD
            AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
              AND VENDER_DIVISION = c_VenderDivTS
            AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
            ) VENDER
          WHERE
            VENDER.CLOSING_DATE = TO_NUMBER(SUBSTR(TO_CHAR(daCreditDate, 'YYYYMMDD'), 7, 2))
        );
      -------------------------------------------------
      --★仮締めグループ間相殺トランザクション削除
      -------------------------------------------------
      DELETE FROM TMPCLAIM_OFFSET_GROUP_DATA
      WHERE
        VENDER_CD IN
        (
          SELECT
            VENDER_CD
          FROM 
            (
            SELECT
              VENDER_CD
            , DECODE(CLOSING_DATE, 99, TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)),
                CASE WHEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2)) < CLOSING_DATE 
                    THEN TO_NUMBER(SUBSTR(TO_CHAR(LAST_DAY(daCreditDate), 'YYYYMMDD'), 7, 2))
                   ELSE CLOSING_DATE
                END
              ) CLOSING_DATE
            , ORGANIZATION_CD
            FROM
              VENDER
            WHERE
              ORGANIZATION_CD = P_STR_ORGANIZATION_CD
            AND (PAYMENT_INVOICE_CD IS NULL OR PAYMENT_INVOICE_CD = VENDER_CD)
              AND VENDER_DIVISION = c_VenderDivTS
            AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
            ) VENDER
          WHERE
            VENDER.CLOSING_DATE = TO_NUMBER(SUBSTR(TO_CHAR(daCreditDate, 'YYYYMMDD'), 7, 2))
        );
      --------------------------------------------------------------------------------
      --カスタマイズ処理
      --------------------------------------------------------------------------------
      -------------------------------------------------
      --★消込トランザクション(カスタマイズ)削除
      -------------------------------------------------
      DELETE FROM ERASER_CSM
      WHERE
          INVOICE_UPDATE_DATE = daCreditDate
      AND   INVOICE_CD IN (
                  SELECT
                    VENDER.VENDER_CD
                  FROM 
                    VENDER
                  , (
                      SELECT
                          VENDER_CD
                        , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                      FROM CLAIM_HEADER
                      WHERE 
                        ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                      AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                      GROUP BY VENDER_CD
                    ) CLAIM_HEADER_MAX_REC
                  WHERE
                    VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                    AND VENDER.VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
                  AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
                  AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
                )
      ;
      ----------------------------------------------------------------------------------------------------------
      --各トランザクション更新処理
      ----------------------------------------------------------------------------------------------------------
      --------------------------------------------------------------------------------
      --★売上トランザクション更新
      --------------------------------------------------------------------------------
      UPDATE SALES
        SET
          CLAIM_TARGET_DIVISION   = c_ClaimTrgDiv0        -- 請求対象(未処理)
        , CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv0        -- 請求更新フラグ(未処理)
        , CLAIM_NO          = NULL            -- 請求番号
        , INVOICE_UPDATE_DATE     = NULL            -- 請求締め日
        , UPDATE_DATE         = SYSDATE           -- 更新日時
        , UPDATOR_CD          = P_STR_INPUTOR_CD      -- 更新者ＩＤ
      WHERE
          CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv1        -- 請求更新フラグ(更新済)
      AND   INVOICE_UPDATE_DATE       =   daCreditDate        -- 請求締め日
      AND   DECODE(INVOICE_CD, NULL, VENDER_CD, INVOICE_CD) IN      -- 請求先
            (
              SELECT
                VENDER.VENDER_CD
              FROM 
                VENDER
              , (
                  SELECT
                      VENDER_CD
                    , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                  FROM CLAIM_HEADER
                  WHERE 
                    ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                  GROUP BY VENDER_CD
                ) CLAIM_HEADER_MAX_REC
              WHERE
                VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
              AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                AND VENDER.VENDER_DIVISION = c_VenderDivTS
              AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
              AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
              AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
            )
      ;
      --------------------------------------------------------------------------------
      --★入金トランザクション更新
      --------------------------------------------------------------------------------
      UPDATE CREDIT
        SET
          CLAIM_TARGET_DIVISION   = c_ClaimTrgDiv0        -- 請求対象(未処理)
        , CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv0        -- 請求更新フラグ(未処理)
        , CLAIM_NO          = NULL            -- 請求番号
        , INVOICE_UPDATE_DATE     = NULL            -- 請求締め日
        , UPDATE_DATE         = SYSDATE           -- 更新日時
        , UPDATOR_CD          = P_STR_INPUTOR_CD      -- 更新者ＩＤ
      WHERE
          CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv1        -- 請求更新フラグ(更新済)
      AND   INVOICE_UPDATE_DATE       =   daCreditDate        -- 請求締め日
      AND   VENDER_CD IN (                        -- 請求先
                  SELECT
                    VENDER.VENDER_CD
                  FROM 
                    VENDER
                  , (
                      SELECT
                          VENDER_CD
                        , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                      FROM CLAIM_HEADER
                      WHERE 
                        ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                      AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                      GROUP BY VENDER_CD
                    ) CLAIM_HEADER_MAX_REC
                  WHERE
                    VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                    AND VENDER.VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
                  AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
                  AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
                )
      ;
      --------------------------------------------------------------------------------
      --★支払トランザクション更新
      --------------------------------------------------------------------------------
      UPDATE PAYMENT
        SET
          CLAIM_TARGET_DIVISION   = c_ClaimTrgDiv0        -- 請求対象(未処理)
        , CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv0        -- 請求更新フラグ(未処理)
        , CLAIM_NO          = NULL            -- 請求番号
        , INVOICE_UPDATE_DATE     = NULL            -- 請求締め日
        , UPDATE_DATE         = SYSDATE           -- 更新日時
        , UPDATOR_CD          = P_STR_INPUTOR_CD      -- 更新者ＩＤ
      WHERE
          CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv1        -- 請求更新フラグ(更新済)
      AND   INVOICE_UPDATE_DATE       =   daCreditDate        -- 請求締め日
      AND   SUPPLIER_CD IN (                      -- 仕入先
                  SELECT
                    VENDER.VENDER_CD
                  FROM 
                    VENDER
                  , (
                      SELECT
                          VENDER_CD
                        , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                      FROM CLAIM_HEADER
                      WHERE 
                        ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                      AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                      GROUP BY VENDER_CD
                    ) CLAIM_HEADER_MAX_REC
                  WHERE
                    VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                    AND VENDER.VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
                  AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
                  AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
                )
      ;
      --------------------------------------------------------------------------------
      --★グループ間相殺トランザクション更新
      --------------------------------------------------------------------------------
      UPDATE OFFSET_GROUP_DATA
        SET
          CLAIM_TARGET_DIVISION   = c_ClaimTrgDiv0        -- 請求対象(未処理)
        , CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv0        -- 請求更新フラグ(未処理)
        , CLAIM_NO          = NULL            -- 請求番号
        , INVOICE_UPDATE_DATE     = NULL            -- 請求締め日
        , UPDATE_DATE         = SYSDATE           -- 更新日時
        , UPDATOR_CD          = P_STR_INPUTOR_CD      -- 更新者ＩＤ
      WHERE
          CLAIM_UPDATE_DIVISION   = c_ClaimUpdDiv1        -- 請求更新フラグ(更新済)
      AND   INVOICE_UPDATE_DATE       =   daCreditDate        -- 請求締め日
      AND   VENDER_CD IN (                        -- 請求先
                  SELECT
                    VENDER.VENDER_CD
                  FROM 
                    VENDER
                  , (
                      SELECT
                          VENDER_CD
                        , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                      FROM CLAIM_HEADER
                      WHERE 
                        ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                      AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                      GROUP BY VENDER_CD
                    ) CLAIM_HEADER_MAX_REC
                  WHERE
                    VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                    AND VENDER.VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
                  AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
                  AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
                )
      ;
      -------------------------------------------------
      --★請求ヘッダー削除
      -------------------------------------------------
      DELETE FROM CLAIM_HEADER 
      WHERE
          CREDIT_DATE = daCreditDate
      AND   VENDER_CD IN (
                  SELECT
                    VENDER.VENDER_CD
                  FROM 
                    VENDER
                  , (
                      SELECT
                          VENDER_CD
                        , MAX(CREDIT_DATE) MAX_CREDIT_DATE
                      FROM CLAIM_HEADER
                      WHERE 
                        ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                      AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER_CD = P_STR_VENDER_CD)
                      GROUP BY VENDER_CD
                    ) CLAIM_HEADER_MAX_REC
                  WHERE
                    VENDER.ORGANIZATION_CD = P_STR_ORGANIZATION_CD
                  AND (VENDER.PAYMENT_INVOICE_CD IS NULL OR VENDER.PAYMENT_INVOICE_CD = VENDER.VENDER_CD)
                    AND VENDER.VENDER_DIVISION = c_VenderDivTS
                  AND ('ALL' = NVL(P_STR_VENDER_CD, 'ALL') OR VENDER.VENDER_CD = P_STR_VENDER_CD)
                  AND VENDER.VENDER_CD = CLAIM_HEADER_MAX_REC.VENDER_CD
                  AND CLAIM_HEADER_MAX_REC.MAX_CREDIT_DATE = daCreditDate
                )
      ;
    */
	--------------------------------------------------------------------------------
	--戻り値設定
	--------------------------------------------------------------------------------
	P_NUM_RET          := 0;
	P_NUM_UPDATE_COUNT := nUpdateCount;
EXCEPTION
	--------------------------------------------------------------------------------
	--例外処理
	--------------------------------------------------------------------------------
	WHEN OTHERS THEN
		--ロールバック
		ROLLBACK;
		--戻り値設定
		P_NUM_RET          := 99;
		P_NUM_UPDATE_COUNT := 0;
END PRO_CLAIM_UPDATEROLLBACK_CSM;
/
