/*
 * 納期連絡表出力ログ連番取得SQL 
 *
 * entityName=OrderImportLog
 * packageName=orderimportlog
 * methodName=getLogSeq
 *
 */
SELECT
    SEQ_FRST_ORDER_LOG.NextVal AS SEQ
FROM DUAL