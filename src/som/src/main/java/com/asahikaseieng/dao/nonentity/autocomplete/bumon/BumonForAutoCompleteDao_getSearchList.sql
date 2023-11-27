/*
 * 会計部門マスタ一覧用SQL
 *
 * entityName=BumonForAutoComplete
 * packageName=bumon
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT SECTION_CD, SECTION_NAME
		FROM   BUMON
		WHERE  SECTION_CD IS NOT NULL
			  
/*IF (sectionCd != null) && (sectionCd != "")*/
		AND    (SECTION_CD LIKE /*sectionCd*/'%' OR SECTION_NAME LIKE /*sectionCd*/'%')
/*END*/
		
		ORDER  BY SECTION_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
