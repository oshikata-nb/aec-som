CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_IFY1
    (
     P_STR_TARGET_MONTH IN NVARCHAR2,
     P_STR_TANTO_CD     IN NVARCHAR2,
     P_NUM_CNT    OUT NUMBER,
     P_NUM_RET    OUT NUMBER
	)
IS
BEGIN
    -- 対象件数調査
     SELECT COUNT(*) INTO P_NUM_CNT
       FROM SIAP_CAY1
--      WHERE Y1STARTDATE < P_STR_TARGET_MONTH || '99'   
--        AND Y1ENDDATE > P_STR_TARGET_MONTH || '00'
     ;
     -- 前回削除として送信済みのレコードを削除する。
     -- (処理フラグY1PROCFLGが9:削除のデータをDELETE)
     DELETE FROM SIAP.IFY1@SIAPSOM
      WHERE Y1PROCFLG = 9
        AND Y1STATUS IN (1,2)
	AND Y1COCODE  = '001'
	AND Y1HBRCODE = '001'
     ;
     -- 残ったレコードの処理フラグを一旦9:削除にしておく。
     UPDATE SIAP.IFY1@SIAPSOM
        SET Y1PROCFLG = 9,
            Y1STATUS  = 0,
            Y1ERRMSG = NULL
	WHERE Y1COCODE  = '001'
	AND   Y1HBRCODE = '001'
    ;
     -- ap21工程グループマスタを取り込んで更新する。
     -- (存在しない場合は処理フラグ0:新規でINSERT、存在する場合は処理フラグ1:更新としてUPDATE)
     MERGE INTO SIAP.IFY1@SIAPSOM Y1
          USING SIAP_CAY1 A1
          ON (Y1.Y1COCODE = A1.Y1COCODE        -- 会社コード
           AND Y1.Y1HBRCODE = A1.Y1HBRCODE     -- 本支店コード
           AND Y1.Y1GBUMONCD = A1.Y1GBUMONCD   -- 原価部門コード
           AND Y1.Y1KOTEICD = A1.Y1KOTEICD     -- 工程コード
          )
     WHEN MATCHED THEN
           UPDATE SET 
            Y1.Y1IMPDATE = to_char(SYSDATE,'YYYYMMDD'),   -- 登録日(YYYYMMDD)
            Y1.Y1IMPTIME = to_char(SYSDATE,'HH24MISS'),     -- 登録時間(HH24MI)
            Y1.Y1PROCFLG = 1,                             -- 処理フラグ(0:新規)
            Y1.Y1KNAME = A1.Y1KNAME,
            Y1.Y1KAKOUKBN = A1.Y1KAKOUKBN,
            Y1.Y1KOTEISKBN = A1.Y1KOTEISKBN,
            Y1.Y1UPDATE = to_char(SYSDATE,'YYYYMMDD'),
            Y1.Y1UPTIME = to_char(SYSDATE,'HH24MISS'),
            Y1.Y1UPID = P_STR_TANTO_CD
     WHEN NOT MATCHED THEN
           INSERT (
            Y1IMPDATE,   -- 登録日(YYYYMMDD)
            Y1IMPTIME,   -- 登録時間(HH24MI)
            Y1PROCFLG,   -- 処理フラグ(0:新規 1:更新 9:削除※共通仕様参照。)
            Y1COCODE,    -- 会社コード(自社マスタ攝津製油固定)
            Y1HBRCODE,   -- 本支店コード(暫定！攝津製油固定)
            Y1GBUMONCD,  -- 原価部門コード
            Y1KOTEICD,   -- 工程コード
            Y1KNAME,     -- 工程名称
            Y1KAKOUKBN,  -- 製造間接費配賦基準(0:直接作業時間)
            Y1KOTEISKBN, -- 工程種別(未使用 0)
            Y1IPDATE,    -- 登録日
            Y1IPTIME,    -- 登録時間
            Y1IPID,      -- 登録者ＩＤ
            Y1UPDATE,    -- 更新日
            Y1UPTIME,    -- 更新時間
            Y1UPID       -- 更新者ＩＤ
           )
           VALUES (
            to_char(SYSDATE,'YYYYMMDD'),   -- 登録日(YYYYMMDD):		
            to_char(SYSDATE,'HH24MISS'),   -- 登録時間(HH24MI):		
            0,                  -- 処理フラグ(0:新規)
            A1.Y1COCODE,    -- 会社コード(自社マスタ攝津製油固定)
            A1.Y1HBRCODE,   -- 本支店コード(暫定！攝津製油固定)
            A1.Y1GBUMONCD,  -- 原価部門コード
            A1.Y1KOTEICD,   -- 工程コード
            A1.Y1KNAME,     -- 工程名称
            A1.Y1KAKOUKBN,  -- 製造間接費配賦基準(0:直接作業時間)
            A1.Y1KOTEISKBN,  -- 工程種別(未使用 0)
            to_char(SYSDATE,'YYYYMMDD'),   
            to_char(SYSDATE,'HH24MISS'),
            P_STR_TANTO_CD,
            to_char(SYSDATE,'YYYYMMDD'),   
            to_char(SYSDATE,'HH24MISS'),
            P_STR_TANTO_CD
           )
     ;
     -- 戻り値設定
     P_NUM_RET := 0
     ;
     COMMIT
     ;
EXCEPTION
     -- 例外処理
     WHEN OTHERS THEN
           ROLLBACK
           ;
           P_NUM_RET := 99
           ;
     -- SQLエラーコード、エラーメッセージをエラーログテーブルに出力
     OUTPUT_ERROR_LOG('PRO_UPDATE_IFY1',P_STR_TANTO_CD,SQLCODE,SUBSTR(SQLERRM, 1, 1024));
END PRO_UPDATE_IFY1
;
/
