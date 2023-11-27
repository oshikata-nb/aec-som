CREATE OR REPLACE PACKAGE BODY TRNERRMAN IS
    /************************************************************************/
    /*   PROCEDURE名 ：TRNERRMAN                                            */
    /*      処理内容 ：エラーログ出力                                       */
    /************************************************************************/

    --ログ出力変数初期化
    PROCEDURE INIT_LOG(I_PROC_CD NVARCHAR2) IS
    BEGIN
        --PROC連番取得
        BEGIN
            SELECT NVL(MAX(PROC_SEQ), 0)
            INTO   W_PROC_SEQ
            FROM   TEMP_BATCH_MSG
            WHERE  PROC_CD = I_PROC_CD
            AND    TO_CHAR(PROC_DATE, 'YYYY/MM/DD') =
                   TO_CHAR(SYSDATE, 'YYYY/MM/DD');
        
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                W_PROC_SEQ := 0;
        END;
        W_PROC_SEQ := W_PROC_SEQ + 1;
    
        --ログ行番号リセット
        W_SEQ := 0;
    EXCEPTION
        WHEN OTHERS THEN
            RAISE;
    END;

    --ログ出力
    PROCEDURE LOGOUT
    (
        I_PROC_CD  NVARCHAR2
       ,I_MSG      NVARCHAR2
       ,I_TANTO_CD NVARCHAR2
    ) IS
    BEGIN
        W_SEQ := W_SEQ + 1;
        OUTPUT_PROC_LOG(I_PROC_CD
                       ,SYSDATE
                       ,W_PROC_SEQ
                       ,W_SEQ
                       ,1
                       ,I_MSG
                       ,I_TANTO_CD);
    END;

    --ログ出力(FLG指定)
    PROCEDURE RETMSG
    (
        I_PROC_CD  NVARCHAR2
       ,I_FLG      NUMBER
       ,I_MSG      NVARCHAR2
       ,I_TANTO_CD NVARCHAR2
    ) IS
    BEGIN
        W_SEQ := W_SEQ + 1;
        OUTPUT_PROC_LOG(I_PROC_CD
                       ,SYSDATE
                       ,W_PROC_SEQ
                       ,W_SEQ
                       ,I_FLG
                       ,I_MSG
                       ,I_TANTO_CD);
    END;

    /************************************************************************/
    /*   PROCEDURE名 ：LOCK_PROC                                            */
    /*      処理内容 ：テーブルロック                                       */
    /************************************************************************/
    FUNCTION LOCK_PROC(X_MODE IN NUMBER) RETURN NUMBER IS
    BEGIN
        CASE X_MODE
            WHEN 1 THEN
                -- 仮締め売掛売上トランザクション
                LOCK TABLE TEMPORARY_DEPOSIT_SALES IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 2 THEN
                -- 仮締め売掛入金トランザクション
                LOCK TABLE TEMPORARY_DEPOSIT_CREDIT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 3 THEN
                -- 仮締め売掛支払トランザクション
                LOCK TABLE TEMPORARY_DEPOSIT_PAYMENT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 4 THEN
                -- 仮締め売掛グループ間相殺トランザクション
                LOCK TABLE TMPDEPT_OFFSET_GROUP_DATA IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 5 THEN
                -- 仮締め売掛ヘッダー
                LOCK TABLE TEMPORARY_DEPOSIT_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 6 THEN
                -- 売掛ヘッダー
                LOCK TABLE DEPOSIT_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 7 THEN
                -- 売上トランザクション
                LOCK TABLE SALES IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 8 THEN
                -- 入金トランザクション
                LOCK TABLE CREDIT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 9 THEN
                -- 支払トランザクション
                LOCK TABLE PAYMENT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 10 THEN
                -- グループ間相殺トランザクション
                LOCK TABLE OFFSET_GROUP_DATA IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 11 THEN
                -- 仮締め売上トランザクション
                LOCK TABLE TEMPORARY_CLAIM_SALES IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 12 THEN
                -- 仮締め入金トランザクション
                LOCK TABLE TEMPORARY_CLAIM_CREDIT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 13 THEN
                -- 仮締め支払トランザクション
                LOCK TABLE TEMPORARY_CLAIM_PAYMENT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 14 THEN
                -- 仮締めグループ間相殺トランザクション
                LOCK TABLE TMPCLAIM_OFFSET_GROUP_DATA IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 15 THEN
                -- 仮締め請求ヘッダー
                LOCK TABLE TEMPORARY_CLAIM_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 16 THEN
                -- 請求ヘッダー
                LOCK TABLE CLAIM_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 17 THEN
                -- 消込トランザクション
                LOCK TABLE ERASER_CSM IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 18 THEN
                -- 仮締め買掛購買外注オーダーファイル
                LOCK TABLE TMPPAY_PURCHASE_SUBCONTRACT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 19 THEN
                -- 仮締め支払入金トランザクション
                LOCK TABLE TEMPORARY_PAYABLE_CREDIT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 20 THEN
                -- 仮締め買掛支払トランザクション
                LOCK TABLE TEMPORARY_PAYABLE_PAYMENT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 21 THEN
                -- 仮締め支払グループ間相殺トランザクション
                LOCK TABLE TMPPAY_OFFSET_GROUP_DATA IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 22 THEN
                -- 仮締め買掛ヘッダー
                LOCK TABLE TEMPORARY_PAYABLE_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 23 THEN
                -- 買掛ヘッダー
                LOCK TABLE PAYABLE_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 24 THEN
                -- 購買外注オーダーファイル
                LOCK TABLE PURCHASE_SUBCONTRACT IN SHARE ROW EXCLUSIVE MODE NOWAIT;
            WHEN 25 THEN
                -- 支払ヘッダー
                LOCK TABLE PAYMENT_HEADER IN SHARE ROW EXCLUSIVE MODE NOWAIT;
        END CASE;
    
        RETURN 1;
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END;
END TRNERRMAN;
/
