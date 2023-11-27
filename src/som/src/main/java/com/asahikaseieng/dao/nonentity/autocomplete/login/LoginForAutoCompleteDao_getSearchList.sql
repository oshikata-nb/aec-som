/*
 * ユーザー一覧用SQL
 *
 * entityName=LoginForAutoComplete
 * packageName=login
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT TANTO_CD, TANTO_NM
		FROM   LOGIN
		WHERE  TANTO_CD IS NOT NULL
		AND    ACTIVE_FLG = '1'
		AND    DELETE_FLG = '0'
		AND    ADMIN_FLG <> '3' --システム管理者以外
			  
/*IF (tantoCd != null) && (tantoCd != "")*/
		AND    (TANTO_CD LIKE /*tantoCd*/'%' OR TANTO_NM LIKE FUN_RET_MASTER_STRING_USE_AC(/*tantoCd*/'%'))
/*END*/
		
		ORDER  BY TANTO_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
