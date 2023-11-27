/*
 * 開発依頼検索SQL文
 * 品目マスタ_関連情報にて使用
 * entityName=Develop
 * packageName=develop
 * methodName=getListForItemRelation
 */
SELECT	DISTINCT DEV.DEVELOPMENT_REQUEST_NO
,	DEV.FINISHED_DATE
FROM	DEVELOPMENT_REQUEST DEV , ITEM_QUEUE
WHERE	ITEM_QUEUE.ITEM_CD = /*itemCd*/''
AND	ITEM_QUEUE.DEVELOPMENT_REQUEST_NO = DEV.DEVELOPMENT_REQUEST_NO