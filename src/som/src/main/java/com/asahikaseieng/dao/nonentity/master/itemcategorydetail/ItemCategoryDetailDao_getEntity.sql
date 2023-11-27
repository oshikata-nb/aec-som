/*
 * 品目分類詳細用SQL
 *
 * entityName=ItemCategoryDetail
 * packageName=itemcategorydetail
 * methodName=getEntity
 *
 */

SELECT ITEM_CATEGORY, ITEM_CATEGORY_NAME, UPDATE_DATE
FROM ITEM_CATEGORY
WHERE ITEM_CATEGORY = /*itemCategory*/'%'
