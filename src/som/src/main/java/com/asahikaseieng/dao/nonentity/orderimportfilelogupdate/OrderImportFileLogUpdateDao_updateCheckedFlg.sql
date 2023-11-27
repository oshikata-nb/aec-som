/**
 * 受注取込ログテーブル　確認フラグ更新用
 *
 * entityName=OrderImportFileLogUpdate
 * packageName=orderimportfilelogupdate
 * methodName=updateCheckedFlg
 *
 */
UPDATE ORDER_IMPORT_FILE_LOG
SET CHECK_FLG = 1
,   UPDATOR_CD = /*tantoCd*/'%'
WHERE CHECK_FLG = 0
