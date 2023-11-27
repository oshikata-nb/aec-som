/*
 * 更新情報一覧削除 SQL
 *
 * entityName=Itemchangehistory
 * packageName=itemchangehistory
 * methodName=deleteList
 */
DELETE
FROM CHANGE_HISTORY
WHERE
	MENU_ID = /*menuId*/'60'
AND ITEM_CD = /*itemCd*/'ITEM01'

