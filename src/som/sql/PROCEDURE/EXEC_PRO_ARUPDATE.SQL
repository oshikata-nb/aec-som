CREATE OR REPLACE PROCEDURE      EXEC_PRO_ARUPDATE IS
    /************************************************************************/
    /*   PROCEDURE名 ：EXEC_PRO_ARUPDATE                                    */
    /*      処理内容 ：売掛更新処理実行                                     */
    /*          修正 ：2016/05/11 余計なパラメータ情報削除処理を削除        */
    /*               ：2017/09/19 統計情報取得追加                          */
    /*               ：2017/10/19 統計情報取得修正                          */
    /************************************************************************/

    /******************************** 宣言部 ********************************/
    -- 変数の宣言
    DEF_PROC_NAME CONSTANT PROC_PARAM.PROC_CD%TYPE := 'PRO_ARUPDATE';

    w_cnt   NUMBER := 0;
    w_param PROC_PARAM%ROWTYPE;

    w_num_ret   NUMBER := 0;
    w_error_cd  NVARCHAR2(80) := NULL;
    w_error_msg NVARCHAR2(20000) := NULL;

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
        
        --仮締め売掛テーブルの統計情報を取得
--        DBMS_STATS.GATHER_TABLE_STATS ('AP21','TEMPORARY_DEPOSIT_CREDIT',null,DBMS_STATS.AUTO_SAMPLE_SIZE);
--        DBMS_STATS.GATHER_TABLE_STATS ('AP21','TEMPORARY_DEPOSIT_HEADER',null,DBMS_STATS.AUTO_SAMPLE_SIZE);
--        DBMS_STATS.GATHER_TABLE_STATS ('AP21','TEMPORARY_DEPOSIT_PAYMENT',null,DBMS_STATS.AUTO_SAMPLE_SIZE);
--        DBMS_STATS.GATHER_TABLE_STATS ('AP21','TEMPORARY_DEPOSIT_SALES',null,DBMS_STATS.AUTO_SAMPLE_SIZE);
        
        --仮締め売掛テーブルの統計情報をテーブル単位で収集 2017/10/19渡邊修正
	    DBMS_STATS.GATHER_TABLE_STATS (
	    	OWNNAME       => 'AP21'
	    	,TABNAME      => 'TEMPORARY_DEPOSIT_CREDIT'
	    	,METHOD_OPT   => 'FOR ALL INDEXED'
	    	,CASCADE      => TRUE
	    );
	    DBMS_STATS.GATHER_TABLE_STATS (
	    	OWNNAME       => 'AP21'
	    	,TABNAME      => 'TEMPORARY_DEPOSIT_HEADER'
	    	,METHOD_OPT   => 'FOR ALL INDEXED'
	    	,CASCADE      => TRUE
	    );
	    DBMS_STATS.GATHER_TABLE_STATS (
	    	OWNNAME       => 'AP21'
	    	,TABNAME      => 'TEMPORARY_DEPOSIT_PAYMENT'
	    	,METHOD_OPT   => 'FOR ALL INDEXED'
	    	,CASCADE      => TRUE
	    );
	    DBMS_STATS.GATHER_TABLE_STATS (
	    	OWNNAME       => 'AP21'
	    	,TABNAME      => 'TEMPORARY_DEPOSIT_SALES'
	    	,METHOD_OPT   => 'FOR ALL INDEXED'
	    	,CASCADE      => TRUE
	    );
        
        w_num_ret   := 0;
        w_error_cd  := NULL;
        w_error_msg := NULL;
    
        -- 売掛更新処理実行
        PRO_ARUPDATE(w_param.PARAM1 -- 部署コード
                    ,w_param.PARAM2 -- 売掛締日
                    ,w_param.PARAM3 -- 仮締区分
                    ,w_param.INPUTOR_CD -- ログイン担当者
                    ,w_num_ret -- 戻り値
                    ,w_error_cd -- エラーコード
                    ,w_error_msg); -- エラーメッセージ
    
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
    
END EXEC_PRO_ARUPDATE;
/
