/*
 * 生産ライン一覧用SQL
 *
 * entityName=LineForAutoComplete
 * packageName=line
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT PRODUCTION_LINE, PRODUCTION_LINE_NAME
		FROM   LINE
		WHERE  PRODUCTION_LINE IS NOT NULL
			  
/*IF (productionLine != null) && (productionLine != "")*/
		AND    (PRODUCTION_LINE LIKE /*productionLine*/'%' OR PRODUCTION_LINE_NAME LIKE /*productionLine*/'%')
/*END*/
		
		ORDER  BY PRODUCTION_LINE)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
