/*
 * カレンダー一覧用SQL
 *
 * entityName=CalForAutoComplete
 * packageName=cal
 * methodName=getSearchList
 *
*/

SELECT *
FROM   (SELECT CAL.CAL_CD, CAL.CAL_NAME
		FROM   CAL
			  ,
			   
			   (SELECT CAL_CD, MAX(CAL_YEAR) CAL_YEAR
				FROM   CAL
				WHERE  CAL.CAL_CD IS NOT NULL
					  
/*IF (calCd != null) && (calCd != "")*/
				AND    (CAL.CAL_CD LIKE /*calCd*/'%' OR CAL.CAL_NAME LIKE /*calCd*/'%')
/*END*/
				
				GROUP  BY CAL_CD) MAX_CAL
		
		WHERE  CAL.CAL_CD = MAX_CAL.CAL_CD
		AND    CAL.CAL_YEAR = MAX_CAL.CAL_YEAR
		GROUP  BY CAL.CAL_CD, CAL.CAL_NAME
		ORDER  BY CAL.CAL_CD)

/*IF (rowlimit != null) && (rowlimit != "")*/
WHERE  ROWNUM <= /*rowlimit*/'50'
/*END*/
