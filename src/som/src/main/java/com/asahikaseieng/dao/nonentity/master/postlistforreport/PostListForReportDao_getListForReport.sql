/*
 * 役職マスタ帳票用SQL
 *
 * entityName=PostListForReport
 * packageName=postlistforreport
 * methodName=getListForReport
 *
 */

SELECT *
FROM POST
WHERE POST_ID IS NOT NULL

/*IF (condition.srhPostId != null) && (condition.srhPostId != "")*/
AND	(POST_ID LIKE /*condition.srhPostId*/'%' OR POST_NAME LIKE /*condition.srhPostId*/'%')
/*END*/

ORDER BY POST_ID
