/*
 * 分類マスタ一覧用SQL
 *
 * entityName=CategoryList
 * packageName=categorylist
 * methodName=getList
 *
 */
SELECT *
FROM CATEGORY
WHERE CATEGORY_DIVISION IS NOT NULL

/*IF (condition.carryCd != null) && (condition.carryCd != "")*/
    AND CATEGORY_DIVISION = /*categoryDivision*/'%'
/*END*/

ORDER BY CATEGORY_CD


