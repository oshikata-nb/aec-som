/*
 * ロール一覧用SQL
 *
 * entityName=RoleForAutoComplete
 * packageName=role
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT ROLE_ID, ROLE_NAME
		FROM   ROLE
		WHERE  ROLE_ID IS NOT NULL
			  
/*IF (roleId != null) && (roleId != "")*/
		AND    (ROLE_ID LIKE /*roleId*/'%' OR ROLE_NAME LIKE /*roleId*/'%')
/*END*/
		
		ORDER  BY ROLE_ID)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
