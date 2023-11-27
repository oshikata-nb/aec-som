CREATE OR REPLACE PROCEDURE AP21.PRO_UPDATE_MONTHLY_INVENTORY
(
	P_NUM_TARGET_MONTH     IN NUMBER
   ,P_STR_TANTO_CD         IN NVARCHAR2
   ,P_STR_ERROR_RETURN_CD  OUT NVARCHAR2
   ,P_STR_ERROR_RETURN_MSG OUT NVARCHAR2
   ,P_NUM_CNT              OUT NUMBER
) IS
	/*------------------------------------------------------------
      種別:PROCEDURE
      名称:PRO_UPDATE_MONTHLY_INVENTORY
      処理:原価計算（材料・製品元帳ファイル）データ取込
      引数:P_NUM_TARGET_MONTH     対象月(YYYYMM)
      引数:P_STR_TANTO_CD         登録者CD
      引数:P_STR_ERROR_RETURN_CD  エラーコード
      引数:P_STR_ERROR_RETURN_MSG エラーメッセージ
      引数:P_NUM_CNT              対象件数
    
      (history)
      date       ver   name       comments
      ---------- ----- ---------- --------------------------------
      2010.04.05 1.00  AEC        新規作成
    ------------------------------------------------------------*/

	/*------------------------------------------------------------
      カーソル宣言
    ------------------------------------------------------------*/
	CURSOR curcao2 IS
		SELECT O2IMCODE
			  ,O2ZGKURISU
			  ,O2ZGJSENTANK
			  ,O2ZGJSENKIN
		FROM   SIAP.CAO2@SIAPSOM
			  ,ITEM_QUEUE
			  ,(SELECT ITEM_CD
					  ,MAX(VERSION) VERSION
				FROM   ITEM_QUEUE
				GROUP  BY ITEM_CD) MAX_ITEM_QUEUE
		WHERE  O2COCODE = '001'
		AND    O2HBRCODE = '001'
		AND    O2NODATE = P_NUM_TARGET_MONTH
		AND    ITEM_QUEUE.ITEM_CD = MAX_ITEM_QUEUE.ITEM_CD
		AND    ITEM_QUEUE.VERSION = MAX_ITEM_QUEUE.VERSION
		AND    ITEM_QUEUE.ITEM_CD = SIAP.CAO2.O2IMCODE(+);

	/*------------------------------------------------------------
      定数・変数宣言
    --------------------------------------------------------------*/
	rtcao2       curcao2%ROWTYPE;
	o2imcode     SIAP.CAO2.O2IMCODE@SIAPSOM%TYPE;
	o2zgkurisu   SIAP.CAO2.O2ZGKURISU@SIAPSOM%TYPE;
	o2zgjsentank SIAP.CAO2.O2ZGJSENTANK@SIAPSOM%TYPE;
	o2zgjsenkin  SIAP.CAO2.O2ZGJSENKIN@SIAPSOM%TYPE;
	cnt          NUMBER := 0;
BEGIN
	P_STR_ERROR_RETURN_CD  := NULL;
	P_STR_ERROR_RETURN_MSG := NULL;
	P_NUM_CNT              := 0;

	/* 対象レコード削除 */
	DELETE MONTHLY_INVENTORY_AMOUNT WHERE TARGET_MONTH = P_NUM_TARGET_MONTH;

	OPEN curcao2;
	LOOP
		FETCH curcao2
			INTO rtcao2;
	
		IF curcao2%NOTFOUND = TRUE THEN
			EXIT;
		END IF;
	
		o2imcode     := rtcao2.O2IMCODE;
		o2zgkurisu   := rtcao2.O2ZGKURISU;
		o2zgjsentank := rtcao2.O2ZGJSENTANK;
		o2zgjsenkin  := rtcao2.O2ZGJSENKIN;
	
		SELECT COUNT(*)
		INTO   cnt
		FROM   MONTHLY_INVENTORY_AMOUNT
		WHERE  TARGET_MONTH = P_NUM_TARGET_MONTH
		AND    ITEM_CD = o2imcode;
	
		IF cnt = 0 THEN
			INSERT INTO MONTHLY_INVENTORY_AMOUNT
			VALUES
				(P_NUM_TARGET_MONTH
				,o2imcode
				,o2zgkurisu
				,o2zgjsentank
				,o2zgjsenkin
				,SYSDATE
				,P_STR_TANTO_CD
				,SYSDATE
				,P_STR_TANTO_CD);
		ELSE
			UPDATE MONTHLY_INVENTORY_AMOUNT
			SET    INVENTORY_QTY = o2zgkurisu
				  ,UNITPRICE     = o2zgjsentank
				  ,AMOUNT        = o2zgjsenkin
				  ,UPDATE_DATE   = SYSDATE
				  ,UPDATOR_CD    = P_STR_TANTO_CD
			WHERE  TARGET_MONTH = P_NUM_TARGET_MONTH
			AND    ITEM_CD = o2imcode;
		END IF;
	
		P_NUM_CNT := P_NUM_CNT + 1;
	END LOOP;

	CLOSE curcao2;
	COMMIT;
EXCEPTION
	WHEN OTHERS THEN
		ROLLBACK;
		P_STR_ERROR_RETURN_CD  := SQLCODE;
		P_STR_ERROR_RETURN_MSG := SUBSTR(SQLERRM, 1, 1024);
		OUTPUT_ERROR_LOG('PRO_UPDATE_MONTHLY_INVENTORY', P_STR_TANTO_CD, SQLCODE, P_STR_ERROR_RETURN_MSG);
END PRO_UPDATE_MONTHLY_INVENTORY;
/
