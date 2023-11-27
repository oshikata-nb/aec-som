/*
 * 品目分類マスタ一覧コンボボックス用SQL
 *
 * entityName=ItemCategoryListForComboboxes
 * packageName=itemcategory
 * methodName=getListForComboboxes
 *
 */

SELECT ITEM_CATEGORY, ITEM_CATEGORY_NAME
FROM ITEM_CATEGORY
ORDER BY ITEM_CATEGORY


