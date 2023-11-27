/*
 * 役職一覧用SQL
 *
 * entityName=PostForAutoComplete
 * packageName=post
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT POST_ID, POST_NAME
		FROM   POST
		WHERE  POST_ID IS NOT NULL
			  
/*IF (postId != null) && (postId != "")*/
		AND    (POST_ID LIKE /*postId*/'%' OR POST_NAME LIKE /*postId*/'%')
/*END*/
		
		ORDER  BY POST_ID)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
