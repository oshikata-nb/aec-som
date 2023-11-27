/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/

/**
 * 製品入出庫実績取得用SQL
 *
 * @author
 *
 * entityName=StockBookingList
 * packageName=stockbooking
 * methodName=getResultList
 *
 */

SELECT
	TRIM(TRAILING FROM LOCATION) AS LOCATION
,	SUM(NVL(SUURYOU, 0)) AS SUM_SUURYOU
,	MIN(JIKOKU) JIKOKU
FROM
	JISSEKI_SEIHIN
WHERE
	JISSEKI_SEIHIN.HOSOSASHIZUNO = RPAD(/*directionNo*/'H0905290004', 11, ' ')
AND JISSEKI_SEIHIN.NYUUSYUKKO = '1' --入出庫が入庫のもの
GROUP BY LOCATION
ORDER BY JIKOKU