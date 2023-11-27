CREATE OR REPLACE PROCEDURE      PRO_ASP_SET_INVENTORY_PREVDAY

----------------------------------------------------------------------
-- 内容：前日在庫取得
--       Asprova の在庫インポートに使用する。
----------------------------------------------------------------------
IS

    TYPE SetteiCurTyp IS REF CURSOR;
    id   SetteiCurTyp;

    item_cd             NVARCHAR2(20);     -- 1: 品目コード
    qty                 NUMBER;            -- 2: オーダ数量
    inventory_date      DATE;              -- 3: 納期
    sqlstr              VARCHAR2(1024);    -- 4: SQL文用
    --vMsg                VARCHAR2(255);   -- メッセージ用 


BEGIN
    ---------------------------------------------------------------------------
    -- ？ 一旦、テーブル（ASP_INVENTORY_PREVDAYのデータを削除する
    sqlstr := 'DELETE FROM ASP_INVENTORY_PREVDAY';
    execute immediate sqlstr;

    ---------------------------------------------------------------------------
    -- ？ 品目在庫テーブル（ITEM_INVENTORY）を開く
    sqlstr := 'SELECT A.ITEM_CD, A.INVENTORY_QTY, A.LAST_INVENTORY_DATE';
    sqlstr :=  sqlstr || ' FROM ITEM_INVENTORY A ,  ITEM B'; 
    sqlstr :=  sqlstr || ' WHERE A.ITEM_CD = B.ITEM_CD ';                 -- 紐付け  品目
    sqlstr :=  sqlstr || ' AND A.INVENTORY_QTY <> 0';                     -- 絞込み  数量 0 は除外 
    sqlstr :=  sqlstr || ' AND B.TYPE_DIVISION IN ( 1 , 2 , 3 , 6 , 7 )'; -- 絞込み  種別
                                                                          --         0:製品,1:原料,2:包材,3:中間品,4:仕入直送品,5:仕入在庫品,6:外注品(直送),7外注品(非直送),9:その他
    OPEN id FOR sqlstr ;

    ---------------------------------------------------------------------------
    -- ？ ？を参照しテーブル（ASP_INVENTORY_PREVDAY）へ登録する
    LOOP
        FETCH id INTO item_cd, qty, inventory_date;
        EXIT WHEN id%NOTFOUND;

         -- 新規登録 
         sqlstr := 'INSERT INTO ASP_INVENTORY_PREVDAY VALUES ( :1, :2, :3, :4, sysdate, ''ASP'', sysdate, ''ASP'' )';
         execute immediate sqlstr using 
                                    'Z_' || item_cd,                                 -- オーダコード
                                 -- item_cd || '_' || to_char(sysdate, 'yyyymmdd'),  -- オーダコード
                                    item_cd,                                         -- 品目コード
                                    to_char(sysdate, 'yyyy/mm/dd ') || '00:00:00',   -- 納期 = データ退避日時
                                 -- '2009/03/31 23:55:00',                           -- 納期 TEST 固定日時
                                 -- to_char(inventory_date, 'yyyy/mm/dd hh24:mi:ss'),-- 納期
                                    qty;                                             -- オーダ数量
    END LOOP;

    CLOSE id;
    commit;

    --DBMS_OUTPUT.PUT_LINE('終了しました');

EXCEPTION
    WHEN OTHERS
        THEN
            rollback;
            -- DBMS_OUTPUT.PUT_LINE('エラー');

END;
/
