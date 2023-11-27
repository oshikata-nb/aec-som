/*
 * 運送会社マスタ一覧用SQL
 *
 * entityName=CarryForAutoComplete
 * packageName=carry
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT CARRY_CD, CARRY_NAME1
		FROM   CARRY
		WHERE  CARRY_CD IS NOT NULL
			  
/*IF(carryCd != null) && (carryCd != "")*/
		AND    (CARRY_CD LIKE /*carryCd*/'%' OR CARRY_NAME1 LIKE /*carryCd*/'%')
/*END*/
		
		ORDER  BY CARRY_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
