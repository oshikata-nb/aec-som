CREATE OR REPLACE PROCEDURE OUTPUT_PROC_LOG
/************************************************************************/
    /*   PROCEDURE名 ：OUTPUT_PROC_LOG                                      */
    /*      処理内容 ：エラーログ出力                                       */
    /************************************************************************/
(
    X_PROC_CD   IN NVARCHAR2 --  メインプロシジャー名
   ,X_PROC_DATE IN DATE --  処理日
   ,X_PROC_SEQ  IN NUMBER --  連番
   ,X_SEQ       IN NUMBER --  行番号
   ,X_FLG       IN NUMBER --  フラグ
   ,X_MSG       IN NVARCHAR2 --  メッセージ
   ,X_USER_ID   IN NVARCHAR2 --  ユーザーID
) AS

    W_MSG NVARCHAR2(255);

    --ＩＮＰＵＴ情報
    IN_PROC_CD   TEMP_BATCH_MSG.PROC_CD%TYPE;
    IN_PROC_DATE TEMP_BATCH_MSG.PROC_DATE%TYPE;
    IN_PROC_SEQ  TEMP_BATCH_MSG.PROC_SEQ%TYPE;
    IN_SEQ       TEMP_BATCH_MSG.SEQ%TYPE;
    IN_FLG       TEMP_BATCH_MSG.FLG%TYPE;
    IN_MSG       TEMP_BATCH_MSG.MSG%TYPE;

    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    -- メイン処理

    -- 入力情報取得
    IN_PROC_CD   := X_PROC_CD;
    IN_PROC_DATE := TO_DATE(X_PROC_DATE, 'RRRR/MM/DD');
    IN_PROC_SEQ  := X_PROC_SEQ;
    IN_SEQ       := X_SEQ;
    IN_FLG       := X_FLG;
    IN_MSG       := X_MSG;

    -- 登録
    INSERT INTO TEMP_BATCH_MSG
        (PROC_CD
        ,PROC_DATE
        ,PROC_SEQ
        ,SEQ
        ,FLG
        ,MSG
        ,INPUT_DATE
        ,INPUTOR_CD
        ,UPDATE_DATE
        ,UPDATOR_CD)
    VALUES
        (IN_PROC_CD
        ,IN_PROC_DATE
        ,IN_PROC_SEQ
        ,IN_SEQ
        ,IN_FLG
        ,IN_MSG
        ,SYSDATE
        ,X_USER_ID
        ,SYSDATE
        ,X_USER_ID);

    COMMIT;

    -- エラー処理
EXCEPTION
    WHEN OTHERS THEN
        W_MSG := SUBSTR(SQLERRM(SQLCODE), 1, 255);

        INSERT INTO TEMP_BATCH_MSG
            (PROC_CD
            ,PROC_DATE
            ,PROC_SEQ
            ,SEQ
            ,FLG
            ,MSG
            ,INPUT_DATE
            ,INPUTOR_CD
            ,UPDATE_DATE
            ,UPDATOR_CD)
        VALUES
            (IN_PROC_CD
            ,IN_PROC_DATE
            ,IN_PROC_SEQ
            ,IN_SEQ
            ,1
            ,W_MSG
            ,SYSDATE
            ,X_USER_ID
            ,SYSDATE
            ,X_USER_ID);
    
        COMMIT;
    
END OUTPUT_PROC_LOG;
/
