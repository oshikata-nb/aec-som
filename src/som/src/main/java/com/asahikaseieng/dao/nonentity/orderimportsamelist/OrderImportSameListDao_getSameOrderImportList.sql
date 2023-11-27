/*
 * 同じ受注グループの受注取込データのリストを取得(運賃・受注金額更新対象検索用)
 *
 * entityName=OrderImportSameList
 * packageName=orderImportsamelist
 * methodName=getSameOrderImportList
 *
 */

SELECT *
FROM FRST_ORDER_DETAIL
WHERE
    CANCEL_FLG <> 1
    AND ERROR_FLG <> 1
/*IF (orderNo != null) && (orderNo != "") && (orderNo != "NOTNULL") */
AND ORDER_NO = /*orderNo*/'%'
/*END*/
/*IF (orderNo == null) || (orderNo == "")*/
AND NVL(LENGTH(TRIM(ORDER_NO)), 0) = 0
/*END*/
/*IF orderNo == "NOTNULL" */
AND ORDER_NO IS NOT NULL
/*END*/
AND FRST_ORDER_NO = /*FrstOrderNo*/'%'