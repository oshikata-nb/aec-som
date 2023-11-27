CREATE OR REPLACE PACKAGE BODY PAK_CARRY_FILE IS
--
	PROCEDURE MAKE_CARRY_FILE(             
            I_CARRY_CD			    IN	VARCHAR2,	-- 運送会社コード
             I_USER_CD			    IN	VARCHAR2,	-- ログインユーザーコード
             I_SHIPPING_NO_LIST	    IN	VARCHAR2,	-- 出荷番号リスト
             O_ERROR_RETURN_CD		IN OUT	NVARCHAR2,	-- エラーコードを返す
             O_ERROR_RETURN_MSG		IN OUT	NVARCHAR2,	-- エラーメッセージを返す
             O_RETURN_CD		    IN OUT	NVARCHAR2 )
	IS
    	COL_SQL 			VARCHAR2(20000);
    	FROM_SQL 			VARCHAR2(20000);
        COUNTER             NUMBER;
        FORMAT_STR          VARCHAR2(50);
    	HEADER_SQL 			VARCHAR2(20000);
        ESCAPE_PREFIX       VARCHAR2(100);
        ESCAPE_SUFFIX       VARCHAR2(100);
	BEGIN

        -- テンポラリテーブルの削除
        DELETE FROM TEMPORARY_CARRY_FILE
        WHERE INPUTOR_CD = I_USER_CD;

        -- 変数初期化
        COL_SQL := NULL;
        FROM_SQL := NULL;
        HEADER_SQL := NULL;
        O_RETURN_CD := 0;

        ESCAPE_PREFIX := ' REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(';
        ESCAPE_SUFFIX := ', '','', ''、''), CHR(9), '' ''), CHR(13), '' ''), CHR(10),'' '') , ''"'', ''”'' ) ';

        -- 取得対象テーブルの設定
        FROM_SQL := FROM_SQL || ' FROM';
        FROM_SQL := FROM_SQL || '     SHIPPING';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     SHIPPING_DETAIL';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     SHIPPING_DETAIL.SHIPPING_NO = SHIPPING.SHIPPING_NO';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     ORDER_HEAD';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     ORDER_HEAD.ORDER_NO = SHIPPING.ORDER_NO';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     ORDER_DETAIL';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     ORDER_DETAIL.ORDER_NO = SHIPPING.ORDER_NO';
        FROM_SQL := FROM_SQL || '     AND ORDER_DETAIL.SHIPPING_NO = SHIPPING.SHIPPING_NO';
        FROM_SQL := FROM_SQL || '     AND ORDER_DETAIL.ITEM_CD = SHIPPING.ITEM_CD';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     DELIVERY';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     DELIVERY.DELIVERY_CD = SHIPPING.DELIVERY_CD';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     VENDER';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     VENDER.VENDER_DIVISION = ''TS'' ';
        FROM_SQL := FROM_SQL || '     AND VENDER.VENDER_CD = SHIPPING.VENDER_CD';
        FROM_SQL := FROM_SQL || ' LEFT JOIN';
        FROM_SQL := FROM_SQL || '     ITEM';
        FROM_SQL := FROM_SQL || ' ON';
        FROM_SQL := FROM_SQL || '     ITEM.ITEM_CD = SHIPPING.ITEM_CD';
        FROM_SQL := FROM_SQL || ' WHERE SHIPPING.SHIPPING_NO IN (' || I_SHIPPING_NO_LIST || ') ' ;
        FROM_SQL := FROM_SQL || ' ORDER BY SHIPPING.SHIPPING_NO , SHIPPING.ITEM_CD';

        -- レイアウトSelect句の作成
        FOR vRec IN(
            SELECT 
                CARRY_FILE_LAYOUT.CARRY_CD
                , CARRY_FILE_LAYOUT.SEQ
                , CARRY_FILE_LAYOUT.HEADER_FLG
                , CARRY_FILE_LAYOUT.DATA_CLS
                , CARRY_FILE_LAYOUT.TABLE_NAME
                , CARRY_FILE_LAYOUT.COLUMN_NAME
                  -- ヘッダの入力がなく、DB形式だとコメントかカラム名を使用
                , CASE WHEN NVL(LENGTH(TRIM( CARRY_FILE_LAYOUT.HEADER_NAME ) ) , 0 ) = 0 AND CARRY_FILE_LAYOUT.DATA_CLS = '00'
                        THEN NVL( TO_NCHAR( USER_COL_COMMENTS.COMMENTS ) , TO_NCHAR( USER_COL_COMMENTS.COLUMN_NAME ) )
                        WHEN NVL(LENGTH(TRIM( CARRY_FILE_LAYOUT.HEADER_NAME ) ) , 0 ) = 0 AND CARRY_FILE_LAYOUT.DATA_CLS = '01'
                        THEN TO_NCHAR('備考')
                        WHEN NVL(LENGTH(TRIM( CARRY_FILE_LAYOUT.HEADER_NAME ) ) , 0 ) = 0 AND CARRY_FILE_LAYOUT.DATA_CLS = '02'
                        THEN TO_NCHAR('当日日付')
                        ELSE TRIM( CARRY_FILE_LAYOUT.HEADER_NAME )  END AS HEADER_NAME
                , CARRY_FILE_LAYOUT.LINK_FLG
                , CARRY_FILE_LAYOUT.DESCRIPTION
            FROM 
                CARRY_FILE_LAYOUT
            LEFT JOIN
                USER_COL_COMMENTS
            ON
                USER_COL_COMMENTS.TABLE_NAME = CARRY_FILE_LAYOUT.TABLE_NAME
                AND USER_COL_COMMENTS.COLUMN_NAME = CARRY_FILE_LAYOUT.COLUMN_NAME
            WHERE
                CARRY_FILE_LAYOUT.CARRY_CD = I_CARRY_CD
            ORDER BY CARRY_FILE_LAYOUT.SEQ
        ) LOOP
            -- 2項目目以降の場合、文字列連結
            IF NVL(LENGTH(TRIM(COL_SQL)),0) > 0 THEN
                COL_SQL := COL_SQL || ' || ';

                    -- 結合フラグが1以外の場合、出力に","を追加
                IF vRec.LINK_FLG <> 1 THEN
                    COL_SQL := COL_SQL || ' ''","'' || ' ;

                    -- ヘッダ付与対象の場合、ヘッダテキストを生成
                    IF vRec.HEADER_FLG = 1 THEN
                        HEADER_SQL := HEADER_SQL || '  || ''"'' || '','' || ' ;
                        HEADER_SQL := HEADER_SQL || ' ''"'' || ' || ESCAPE_PREFIX || '''' || vRec.HEADER_NAME || '''' || ESCAPE_SUFFIX;
                    END IF;

                END IF;

            ELSE
                -- 1項目目の場合、ダブルクォートを追加
                    COL_SQL := COL_SQL || ' ''"'' || ' ;
                -- ヘッダ付与対象の場合、ヘッダテキストを生成
                IF vRec.HEADER_FLG = 1 THEN
                    HEADER_SQL := HEADER_SQL || ' ''"'' || ' || ESCAPE_PREFIX || '''' || vRec.HEADER_NAME || '''' || ESCAPE_SUFFIX;
                END IF;
            END IF;

            -- テーブル項目
            IF vRec.DATA_CLS = '00' THEN
                COL_SQL := COL_SQL || ESCAPE_PREFIX || vRec.TABLE_NAME || '.' || vRec.COLUMN_NAME || ESCAPE_SUFFIX;
            -- 文字列
            ELSIF vRec.DATA_CLS = '01' THEN
                COL_SQL := COL_SQL || ESCAPE_PREFIX || '''' || vRec.DESCRIPTION || '''' || ESCAPE_SUFFIX;
            -- 日付
            ELSIF vRec.DATA_CLS = '02' THEN
                -- yyyy/MM/ddで出力
                FORMAT_STR := 'yyyy/MM/dd';

                COL_SQL := COL_SQL || ESCAPE_PREFIX || 'TO_CHAR( SYSDATE , ''' || FORMAT_STR || ''' )' || ESCAPE_SUFFIX;
            END IF;

        END LOOP;

        -- ダブルクォート閉じる
        HEADER_SQL := HEADER_SQL || ' || ''"'' ' ;
        COL_SQL := COL_SQL || ' || ''"'' ' ;

--      DBMS_OUTPUT.PUT_LINE( HEADER_SQL );
--      DBMS_OUTPUT.PUT_LINE('INSERT INTO TEMPORARY_CARRY_FILE SELECT ''' || I_USER_CD || ''' , SYSDATE , ' || COL_SQL || FROM_SQL );

        -- ヘッダテキストがある場合、ヘッダ情報を出力
        IF NVL(LENGTH(TRIM( HEADER_SQL )),0) > 0 THEN
            EXECUTE IMMEDIATE 'INSERT INTO TEMPORARY_CARRY_FILE SELECT ''' || I_USER_CD || ''' , SYSDATE , ''00'' , 1 ,' || HEADER_SQL || ' FROM DUAL ' ;
        END IF;

        IF NVL(LENGTH(TRIM( COL_SQL )),0) > 0 THEN
            -- データを出力
            EXECUTE IMMEDIATE 'INSERT INTO TEMPORARY_CARRY_FILE SELECT ''' || I_USER_CD || ''' , SYSDATE , ''01'' , ROW_NUMBER() OVER( ORDER BY SHIPPING.SHIPPING_NO , SHIPPING.ITEM_CD )  , ' || COL_SQL || FROM_SQL ;
        END IF;
        
	EXCEPTION
		WHEN OTHERS THEN
		-- SQLエラーコード、エラーメッセージを取得
	    O_ERROR_RETURN_CD := SQLCODE;
		O_ERROR_RETURN_MSG  := SUBSTR(SQLERRM,1,1024);
		OUTPUT_ERROR_LOG('MAKE_CARRY_FILE',I_USER_CD,I_CARRY_CD, SUBSTR( O_ERROR_RETURN_MSG , 0 , 1000 ));
		O_RETURN_CD := -9;
	END;
END;
/
