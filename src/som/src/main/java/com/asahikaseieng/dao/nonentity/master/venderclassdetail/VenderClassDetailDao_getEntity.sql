/*
 * 手形分類検索用SQL
 *
 * entityName=VenderClassDetail
 * packageName=venderclassdetail
 * methodName=getEntity
 *
 */

SELECT CATEGORY_DIVISION, CATEGORY_NAME, NOTE_DIVISION
FROM CLASSIFICATION
WHERE DATA_TYPE = /*dataType*/1
AND CATEGORY_DIVISION = /*categoryDivision*/'%'


