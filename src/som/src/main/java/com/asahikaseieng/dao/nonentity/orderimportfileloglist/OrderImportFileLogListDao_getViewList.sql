/**
 * 受注取込ログテーブル　ログ確認用
 *
 * entityName=OrderImportFileLogList
 * packageName=orderimportfileloglist
 * methodName=getViewList
 */
SELECT
    *
FROM ORDER_IMPORT_FILE_LOG
WHERE
    FLG = 1
AND CHECK_FLG = 0
AND PROC_CD = 'PRO_MAKE_ORDER_IMPORT'
AND INPUT_DATE >= SYSDATE
AND INPUTOR_CD = /*tantoCd*/'%'
ORDER BY 
    INPUT_DATE
,   ROW_NO
,   PROC_SEQ