CREATE OR REPLACE PACKAGE TRNERRMAN IS
    /************************************************************************/
    /*   PROCEDURE名 ：TRNERRMAN                                            */
    /*      処理内容 ：エラーログ出力                                       */
    /************************************************************************/

    W_PROC_SEQ NUMBER;
    W_SEQ      NUMBER;

    --ログ出力変数初期化
    PROCEDURE INIT_LOG(I_PROC_CD NVARCHAR2);

    --ログ出力
    PROCEDURE LOGOUT
    (
        I_PROC_CD  NVARCHAR2
       ,I_MSG      NVARCHAR2
       ,I_TANTO_CD NVARCHAR2
    );

    --ログ出力(FLG指定)
    PROCEDURE RETMSG
    (
        I_PROC_CD  NVARCHAR2
       ,I_FLG      NUMBER
       ,I_MSG      NVARCHAR2
       ,I_TANTO_CD NVARCHAR2
    );

    -- テーブルロック
    FUNCTION LOCK_PROC(X_MODE IN NUMBER) RETURN NUMBER;
END TRNERRMAN;
/
