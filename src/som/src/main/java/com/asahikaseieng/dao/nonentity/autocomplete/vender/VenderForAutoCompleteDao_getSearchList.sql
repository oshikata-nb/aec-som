/*
 * 取引先一覧用SQL
 *
 * entityName=VenderForAutoComplete
 * packageName=vender
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT VENDER.VENDER_CD, VENDER.VENDER_SHORTED_NAME
		FROM   VENDER
		WHERE  VENDER_DIVISION = /*venderDivision*/'TS'

/*IF (venderCd != null) && (venderCd != "")*/
		AND    (VENDER_CD LIKE /*venderCd*/'%' OR VENDER_SHORTED_NAME LIKE FUN_RET_MASTER_STRING_USE_AC(/*venderCd*/'%'))
/*END*/

		ORDER  BY VENDER_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
