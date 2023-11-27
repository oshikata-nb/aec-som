/*
 * 品目分類一覧用SQL
 *
 * entityName=ItemCategoryForAutoComplete
 * packageName=itemcategory
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ITEM_CATEGORY, ITEM_CATEGORY_NAME
		FROM   ITEM_CATEGORY
		WHERE  ITEM_CATEGORY IS NOT NULL
			  
/*IF (itemCategory != null) && (itemCategory != "")*/
		AND    (ITEM_CATEGORY LIKE /*itemCategory*/'%' OR ITEM_CATEGORY_NAME LIKE /*itemCategory*/'%')
/*END*/
		
		ORDER  BY ITEM_CATEGORY)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
