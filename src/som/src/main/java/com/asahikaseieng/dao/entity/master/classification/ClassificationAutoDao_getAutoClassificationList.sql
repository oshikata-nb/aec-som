/*
 * 分類オートコンプリート用SQL
 *
 * entityName=Classification
 * packageName=Classification
 * methodName=getAutoClassificationList
 *
 */
SELECT CATEGORY_DIVISION
,      CATEGORY_NAME
FROM CLASSIFICATION
WHERE DATA_TYPE IS NOT NULL

/*IF (dataType != null) && (dataType != "") */
	AND DATA_TYPE = /*dataType*/
/*END*/

/*IF (dataTotalDivision != null) && (dataTotalDivision != "") */
	AND DATA_TOTAL_DIVISION = /*dataTotalDivision*/
/*END*/

/*IF (categoryDivision != null) && (categoryDivision != "")*/
	AND CATEGORY_DIVISION LIKE /*categoryDivision*/
/*END*/

ORDER BY CATEGORY_DIVISION
