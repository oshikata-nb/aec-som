/*
 * 得意先取得用SQL
 *
 * entityName=OrderDetailVenderList
 * packageName=orderdetailvenderlist
 * methodName=getList
 *
 */

SELECT ROWNUM SHOP_LEVEL, A.VENDER_CD, A.VENDER_SHORTED_NAME AS VENDER_NAME1,A.TEL_NO AS TEL_NO,
CASE ROWNUM
	WHEN 1 THEN '得意先'
    WHEN 2 THEN '二次店'
    WHEN 3 THEN '三次店'
    WHEN 4 THEN '四次店'
    WHEN 5 THEN '五次店'
    ELSE NULL
END SHOP_LEVEL_NAME
FROM

(SELECT
	LEVEL LV
,	BALANCE.VENDER_CD
,	VENDER_SHORTED_NAME
,	TEL_NO
FROM BALANCE, VENDER
WHERE VENDER_DIVISION(+) = 'TS'
AND BALANCE.VENDER_CD = VENDER.VENDER_CD(+)
START WITH BALANCE_CD = /*balanceCd*/'5'
CONNECT BY PRIOR UPPER_BALANCE_CD = BALANCE_CD
ORDER BY LEVEL DESC) A

ORDER BY ROWNUM
