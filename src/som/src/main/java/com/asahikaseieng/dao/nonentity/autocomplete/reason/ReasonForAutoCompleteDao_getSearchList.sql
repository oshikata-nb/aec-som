/*
 * 理由マスタ一覧用SQL
 *
 * entityName=ReasonForAutoComplete
 * packageName=reason
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT RY_CD, RY_DESCRIPTION
		FROM   REASON
		WHERE  (RY_CD LIKE /*ryCd*/'%' OR RY_DESCRIPTION LIKE /*ryCd*/'%')
		ORDER  BY RY_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
