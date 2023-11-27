CREATE OR REPLACE PROCEDURE EXEC_PRO_PAYMENTUPDATE IS
    /************************************************************************/
    /*   PROCEDURE名 ：EXEC_PRO_PAYMENTUPDATE                               */
    /*      処理内容 ：支払更新処理実行                                     */
    /*          修正 ：2016/05/11 余計なパラメータ情報削除処理を削除        */
    /************************************************************************/

    /******************************** 宣言部 ********************************/
    -- 変数の宣言
    DEF_PROC_NAME CONSTANT PROC_PARAM.PROC_CD%TYPE := 'PRO_PAYMENTUPDATE';

    w_cnt   NUMBER := 0;
    w_param PROC_PARAM%ROWTYPE;

    w_num_ret NUMBER := 0;

    /******************************** 処理部 ********************************/
BEGIN
    -- プロシージャ待ち件数を取得
    SELECT COUNT(*)
    INTO   w_cnt
    FROM   PROC_PARAM
    WHERE  PROC_CD = DEF_PROC_NAME
    AND    CHECK_FLG = '1'; -- 1:登録中;

    IF 0 < W_CNT THEN
        -- 実行ユーザを取得
        SELECT *
        INTO   w_param
        FROM   PROC_PARAM
        WHERE  PROC_CD = DEF_PROC_NAME
        AND    CHECK_FLG = '1';
    
        -- プロシージャ実行中に変更
        UPDATE PROC_PARAM
        SET    CHECK_FLG   = '2' -- 2:実行中
              ,UPDATE_DATE = SYSDATE
              ,UPDATOR_CD  = w_param.INPUTOR_CD
        WHERE  PROC_CD = DEF_PROC_NAME;
    
        COMMIT;
    
        w_num_ret := 0;
    
        -- 支払更新処理実行
        PRO_PAYMENTUPDATE(w_param.PARAM1 -- 部署コード
                         ,w_param.PARAM2 -- 支払先コード
                         ,w_param.PARAM3 -- 支払締め日
                         ,w_param.INPUTOR_CD -- ログイン担当者
                         ,w_param.PARAM4 -- 自社コード
                         ,w_num_ret); -- 戻り値
    
        -- パラメータ情報削除
        DELETE FROM PROC_PARAM WHERE PROC_CD = DEF_PROC_NAME;
    
        COMMIT;
    END IF;

    -- パラメータ情報削除
    -- 2017/05/11 delete 処理削除 -->
    --    DELETE FROM PROC_PARAM WHERE PROC_CD = DEF_PROC_NAME;
    --
    --    COMMIT;

    -- <-----------------------------
    /****************************** 例外処理部 ******************************/
EXCEPTION
    WHEN OTHERS THEN
        -- パラメータ情報削除
        DELETE FROM PROC_PARAM WHERE PROC_CD = DEF_PROC_NAME;
    
        COMMIT;
    
END EXEC_PRO_PAYMENTUPDATE;
/
