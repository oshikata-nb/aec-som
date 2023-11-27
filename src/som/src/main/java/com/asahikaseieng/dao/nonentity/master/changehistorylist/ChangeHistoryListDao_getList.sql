/*
 * 更新履歴取得用SQL
 *
 * entityName=ChangeHistoryList
 * packageName=changehistorylist
 * methodName=getList
 *
 */

SELECT ITEM_CD, REASON, CHANGE_HISTORY.UPDATE_DATE, TANTO_NM
FROM CHANGE_HISTORY, LOGIN
WHERE MENU_ID = /*menuId*/1
AND ITEM_CD = /*itemCd*/'%'
AND CHANGE_HISTORY.UPDATOR_CD = LOGIN.TANTO_CD(+)
ORDER BY CHANGE_HISTORY.UPDATE_DATE DESC
