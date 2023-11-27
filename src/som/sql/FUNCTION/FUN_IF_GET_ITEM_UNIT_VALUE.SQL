CREATE OR REPLACE FUNCTION      FUN_IF_GET_ITEM_UNIT_VALUE
      (
             I_ITEM_CD		IN	NVARCHAR2
      )
RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_IF_GET_ITEM_UNIT_VALUE                                                */
/*      処理内容      :      品目コードから在庫管理単位換算数を取得する				       */
/*      引数          :      I_ITEM_CD		品目コード		NVARCHAR2型                    */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE		VARCHAR2(100);
	ERR_MSG			VARCHAR2(2048);
	PROC_NUMBER		NUMBER;
	PROC_UNIT_VALUE		NUMBER;

BEGIN
	SELECT ITEM.UNIT_OF_STOCK_CONTROL INTO PROC_UNIT_VALUE FROM ITEM WHERE ITEM.ITEM_CD = I_ITEM_CD;
	
	IF PROC_UNIT_VALUE = 1 THEN	-- 在庫管理単位(KG)
		RETURN 1;
	ELSIF PROC_UNIT_VALUE = 2 THEN	-- 在庫管理単位(G)
		RETURN 1000;
	ELSIF PROC_UNIT_VALUE = 3 THEN	-- 在庫管理単位(MG)
		RETURN 1000000;
	ELSE
		RETURN -1;
	END IF;
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		OUTPUT_ERROR_LOG('FUN_IF_GET_ITEM_UNIT_VALUE','AUTO',ERR_CODE,ERR_MSG);

		RETURN -9;
END;
/
