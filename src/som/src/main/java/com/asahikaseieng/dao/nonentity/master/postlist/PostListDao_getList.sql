/*
 * 役職マスタ一覧用SQL
 *
 * entityName=PostList
 * packageName=postlist
 * methodName=getList
 *
 */

SELECT POST_ID, POST_NAME, SUBSTRB(POST_NAME, 0, 88) SHORT_POST_NAME
FROM POST
WHERE POST_ID IS NOT NULL

/*IF (condition.srhPostId != null) && (condition.srhPostId != "")*/
AND	(POST_ID LIKE /*condition.srhPostId*/'%' OR POST_NAME LIKE /*condition.srhPostId*/'%')
/*END*/

ORDER BY POST_ID


