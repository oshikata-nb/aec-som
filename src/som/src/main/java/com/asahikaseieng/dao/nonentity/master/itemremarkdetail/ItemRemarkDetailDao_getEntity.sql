/*
 * その他情報取得用SQL
 *
 * entityName=ItemRemarkDetail
 * packageName=itemremarkdetail
 * methodName=getEntity
 *
 */

SELECT ITEM_CD, VERSION, REMARK, UPDATE_DATE
FROM ITEM_REMARK
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
