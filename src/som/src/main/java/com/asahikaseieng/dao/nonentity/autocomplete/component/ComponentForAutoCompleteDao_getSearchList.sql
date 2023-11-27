/*
 * 成分マスタ一覧用SQL
 *
 * entityName=ComponentForAutoComplete
 * packageName=component
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT COMPONENT_CD, COMPONENT_NAME
		FROM   COMPONENT
		WHERE  COMPONENT_CD IS NOT NULL
			  
/*IF(componentCd != null) && (componentCd != "")*/
		AND    (COMPONENT_CD LIKE /*componentCd*/'%' OR COMPONENT_NAME LIKE /*componentCd*/'%')
/*END*/
		
		ORDER  BY COMPONENT_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
