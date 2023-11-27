CREATE OR REPLACE FUNCTION      FUN_IF_CHECK_PARENT_ITEM_CD
      (
             I_ITEM_CD_A	IN	NVARCHAR2,
             I_ITEM_CD_B	IN	NVARCHAR2
      )
RETURN NUMBER IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      FUNCTION                                                                  */
/*      名称          :      FUN_IF_CHECK_PARENT_ITEM_CD                                               */
/*      処理内容      :      品目コードAと品目コードBの親品目コードが正しいかチェック		       */
/*      引数          :      I_ITEM_CD_A		品目コードA		NVARCHAR2型            */
/*      引数          :      I_ITEM_CD_B		品目コードB		NVARCHAR2型            */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
	ERR_CODE		VARCHAR2(100);
	ERR_MSG			VARCHAR2(2048);
	PARENT_ITEM_CD_A	VARCHAR2(100);
	PARENT_ITEM_CD_B	VARCHAR2(100);
	TEMP_CHECK		VARCHAR2(100);
BEGIN
	SELECT ITEM.PARENT_ITEM_CD INTO PARENT_ITEM_CD_A FROM ITEM WHERE ITEM.ITEM_CD = I_ITEM_CD_A;
	SELECT ITEM.PARENT_ITEM_CD INTO PARENT_ITEM_CD_B FROM ITEM WHERE ITEM.ITEM_CD = I_ITEM_CD_B;
	
	-- 品目コードA,B両方とも親品目コードが存在する場合
	IF PARENT_ITEM_CD_A IS NOT NULL AND PARENT_ITEM_CD_B IS NOT NULL THEN 
		
		-- 親品目コードが正しいかチェック
		IF PARENT_ITEM_CD_A = PARENT_ITEM_CD_B THEN	-- 両方とも親品目コードが同一
			RETURN 1;
		ELSE
			RETURN -1;
		END IF;
	ELSE
		RETURN 0;

	END IF;
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN

		-- SQLエラーコード、エラーメッセージを取得
	    	ERR_CODE := SQLCODE;
		ERR_MSG  := SUBSTR(SQLERRM,1,1024);

		OUTPUT_ERROR_LOG('FUN_IF_CHECK_PARENT_ITEM_CD','AUTO',ERR_CODE,ERR_MSG);

		RETURN -9;
END;
/
