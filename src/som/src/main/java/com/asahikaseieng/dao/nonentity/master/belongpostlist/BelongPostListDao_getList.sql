/*
 * 役職マスタコンボボックス一覧用SQL
 *
 * entityName=BelongPostList
 * packageName=belongpostlist
 * methodName=getList
 *
 */

SELECT POST_ID, POST_NAME
FROM POST
WHERE POST_ID IS NOT NULL

/*IF (postId != null) && (postId != "")*/
AND	(POST_ID LIKE /*postId*/'%' OR POST_NAME LIKE /*postId*/'%')
/*END*/

ORDER BY POST_ID


