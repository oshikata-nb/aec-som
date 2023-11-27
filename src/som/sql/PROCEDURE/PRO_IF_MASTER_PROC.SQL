CREATE OR REPLACE PROCEDURE      PRO_IF_MASTER_PROC
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      PRO_IF_MASTER_PROC                                                        */
/*      処理内容      :      ＡＰマスタのトリガー処理					       、      */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.06.02      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
BEGIN
	
	-- AP運送会社マスタ変更処理
	PRO_IF_CHANGE_MASTER_UNSOTEN;
	
	-- AP工程マスタ変更処理
	PRO_IF_CHANGE_MASTER_SHORICODE;

	-- AP生産ラインマスタの変更処理
	PRO_IF_CHANGE_MASTER_KOJO;

	-- APログインマスタの変更処理
	PRO_IF_CHANGE_MASTER_TANTOSHA;

	-- AP品目マスタ変更処理（原材料）
	PRO_IF_CHANGE_MASTER_GENZAIRYO;

	-- AP品目マスタ変更処理（製品）
	PRO_IF_CHANGE_MASTER_GENZAIRYO;
	
-- 異常処理
EXCEPTION

	WHEN OTHERS THEN
		ROLLBACK;
		OUTPUT_ERROR_LOG('PRO_IF_MASTER_PROC','AUTO',SQLCODE,SQLERRM);
		COMMIT;

END;
/
