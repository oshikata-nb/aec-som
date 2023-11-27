/*
 * 分類マスタ用SQL
 *
 * entityName=CategoryDetail
 * packageName=categorydetail
 * methodName=getEntity
 *
 */
SELECT *
FROM CATEGORY
WHERE CATEGORY_CD = /*categoryCd*/'%'
AND CATEGORY_DIVISION = /*categoryDivision*/'%'


