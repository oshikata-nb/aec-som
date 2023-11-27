/*
 * 取引先一覧用(入金用)SQL
 *
 * entityName=DepositVenderForAutoComplete
 * packageName=deposit
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT VENDER.VENDER_CD
			  ,VENDER.VENDER_SHORTED_NAME
			  ,VENDER.ORGANIZATION_CD
			  ,ORGANIZATION.ORGANIZATION_NAME
			  ,ADVANCE_DIVISION
		FROM   VENDER, ORGANIZATION
		WHERE  VENDER_DIVISION = /*venderDivision*/'TS'
			  
/*IF (venderCd != null) && (venderCd != "")*/
		AND    (VENDER_CD LIKE /*venderCd*/'%' OR VENDER_SHORTED_NAME LIKE /*venderCd*/'%')
/*END*/
			  
		AND    VENDER.ORGANIZATION_CD = ORGANIZATION.ORGANIZATION_CD(+)
		ORDER  BY VENDER_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
