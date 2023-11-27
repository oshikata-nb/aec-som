/*
 * 開発依頼完成品目SQL文
 *
 * entityName=DevelopItem
 * packageName=develop
 * methodName=getDevelopItem
 */
SELECT  ITEM_CD
,       VERSION
,       DEVELOPMENT_REQUEST_NO
FROM    ITEM_QUEUE
WHERE	ITEM_CD = /*itemCd*/'P00000001'



