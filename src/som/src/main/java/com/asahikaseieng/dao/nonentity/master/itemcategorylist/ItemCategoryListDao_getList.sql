/*
 * 品目分類マスタ一覧用SQL
 *
 * entityName=ItemCategoryList
 * packageName=itemcategorylist
 * methodName=getList
 *
 */

SELECT ITEM_CATEGORY, ITEM_CATEGORY_NAME
FROM ITEM_CATEGORY
WHERE ITEM_CATEGORY IS NOT NULL

/*IF (condition.srhItemCategory != null) && (condition.srhItemCategory != "")*/
AND (ITEM_CATEGORY LIKE /*condition.srhItemCategory*/'%' OR ITEM_CATEGORY_NAME LIKE /*condition.srhItemCategory*/'%')
/*END*/

ORDER BY ITEM_CATEGORY
