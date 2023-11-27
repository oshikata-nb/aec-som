/*
 * 自社マスタ一覧用SQL
 *
 * ENTITYNAME=CompanyForAutocomplete
 * PACKAGENAME=company
 * METHODNAME=getSearchList
 *
*/

SELECT *
FROM   (SELECT COMPANY_CD, NVL(HOME_NAME1, '自社名称1未登録') HOME_NAME1
		FROM   COMPANY
		WHERE  COMPANY_CD IS NOT NULL
			  
/*IF(homeName1 != null) && (homeName1 != "")*/
		AND    HOME_NAME1 LIKE /*homeName1*/'%'
/*END*/
		
		ORDER  BY COMPANY_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
