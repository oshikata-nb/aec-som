/*
 * 分類マスタ一覧コンボボックス用SQL
 *
 * entityName=ClassificationListForComboboxes
 * packageName=classification
 * methodName=getListForComboboxes
 *
 */

SELECT CATEGORY_DIVISION, CATEGORY_NAME, EXTERNAL_CATEGORY_NAME
FROM CLASSIFICATION
WHERE DATA_TYPE = /*dataType*/1

/*IF (arDivision != null)*/
AND CASE WHEN AR_DIVISION IN ( 0 , 9 ) THEN  SHOW_FLG ELSE AR_DIVISION END = /*arDivision*/0
/*END*/

ORDER BY CATEGORY_DIVISION


