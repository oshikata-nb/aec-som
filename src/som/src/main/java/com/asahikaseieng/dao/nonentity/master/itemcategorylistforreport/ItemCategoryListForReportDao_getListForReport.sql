/*
 * 品目分類帳票用SQL
 *
 * entityName=ItemCategoryListForReport
 * packageName=itemcategorylistforreport
 * methodName=getListForReport
 *
 */

SELECT CATEGORY.*
, INPUTOR.TANTO_NM INPUTOR_NAME
, UPDATOR.TANTO_NM UPDATOR_NAME

FROM ITEM_CATEGORY CATEGORY
, LOGIN INPUTOR
, LOGIN UPDATOR

WHERE CATEGORY.ITEM_CATEGORY IS NOT NULL

/*IF (condition.srhItemCategory != null) && (condition.srhItemCategory != "")*/
AND (CATEGORY.ITEM_CATEGORY LIKE /*condition.srhItemCategory*/'%' OR CATEGORY.ITEM_CATEGORY_NAME LIKE /*condition.srhItemCategory*/'%')
/*END*/

AND CATEGORY.INPUTOR_CD = INPUTOR.TANTO_CD(+)
AND CATEGORY.UPDATOR_CD = UPDATOR.TANTO_CD(+)

ORDER BY CATEGORY.ITEM_CATEGORY


