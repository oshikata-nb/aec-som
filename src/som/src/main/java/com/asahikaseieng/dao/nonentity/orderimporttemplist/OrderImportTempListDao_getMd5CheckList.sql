/*
 * 過去1か月間のORDER_IMPORT_TEMPテーブルMD5チェックサムリストを取得する
 *
 * entityName=OrderImportTempList
 * packageName=orderImportTempList
 * methodName=getMd5CheckList
 *
 */
SELECT
    MD5_CHECKSUM
,   MIN(IMPORT_DATE) IMPORT_DATE
,   MAX(UPDATOR_CD) IMPORT_TANTO_CD
FROM
    ORDER_IMPORT_TEMP
WHERE
    IMPORT_DATE >= ADD_MONTHS(SYSDATE, -1)
GROUP BY
    MD5_CHECKSUM
