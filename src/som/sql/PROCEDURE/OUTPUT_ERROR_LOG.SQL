CREATE OR REPLACE PROCEDURE      OUTPUT_ERROR_LOG
      (
             I_MODULE_CD		IN	NVARCHAR2
            ,I_CLIENT			IN	NVARCHAR2
            ,I_ERROR_MES		IN	NVARCHAR2
            ,I_SQL_STR			IN	NVARCHAR2
      )
IS
/*-----------------------------------------------------------------------------------------------------*/
/*      種別          :      PROCEDURE                                                                 */
/*      名称          :      OUTPUT_ERROR_LOG                                                          */
/*      処理内容      :      エラーログ出力処理。						　　　 */
/*      引数          :      I_MODULE_CD 	モジュールコード	NVARCHAR2型                    */
/*      引数          :      I_CLIENT 		クライアント名		NVARCHAR2型                    */
/*      引数          :      I_ERROR_MES	エラーコード		NVARCHAR2型                    */
/*      引数          :      I_SQL_STR		エラーメッセージ	NVARCHAR2型                    */
/*      VERNO.        :      1.00                                                                      */
/*(history)                                                                                            */
/*      date            ver         name                    comments                                   */
/*      ----------      ------      ------------------      -------------------------------------------*/
/*      2009.04.06      1.00        Ohga                    新規作成                                   */
/*-----------------------------------------------------------------------------------------------------*/
BEGIN
	INSERT INTO ERROR_LOG (
		MODULE_CD,
		CLIENT,
		ERROR_DATE,
		ERROR_MES,
		SQL_STR
	) VALUES (
		I_MODULE_CD,
		I_CLIENT,
		SYSDATE,
		I_ERROR_MES,
		I_SQL_STR
	);
END;
/
