/*
 * 分類マスタ詳細検索用SQL
 *
 * entityName=ClassificationDetail
 * packageName=classificationdetail
 * methodName=getEntity
 *
 */

SELECT DATA_TYPE
, CATEGORY_DIVISION
, DATA_TOTAL_DIVISION
, CATEGORY_NAME
, DEBIT_ACCOUNTS_CD
, DEBIT_ACCOUNTS_SUB_CD
, CREDIT_ACCOUNTS_CD
, CREDIT_ACCOUNTS_SUB_CD
, BANK_DIVISION
, NOTE_DIVISION
, AR_DIVISION
, CLAIM_DIVISION
, CREDIT_DIVISION
, PAYMENT_DIVISION
, SHOW_FLG
FROM CLASSIFICATION
WHERE DATA_TYPE = /*dataType*/1
AND CATEGORY_DIVISION = /*categoryDivision*/'%'

/*IF (dataTotalDivision != null) && (dataTotalDivision != "")*/
AND DATA_TOTAL_DIVISION = /*dataTotalDivision*/1
/*END*/


