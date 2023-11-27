/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/

/**
 * 得意先一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=List<SalesDetailKeepVenderList>
 * packageName=sales
 * methodName=getVenderList
 *
 */
SELECT ROWNUM SHOP_LEVEL
,      A.VENDER_CD
,      A.VENDER_NAME1
,      A.VENDER_SHORTED_NAME
,      CASE ROWNUM
           WHEN 1 THEN '得意先'
           WHEN 2 THEN '二次店'
           WHEN 3 THEN '三次店'
           WHEN 4 THEN '四次店'
           WHEN 5 THEN '五次店'
           ELSE NULL
       END SHOP_LEVEL_NAME
FROM

(SELECT LEVEL LV, BALANCE.VENDER_CD, VENDER_NAME1,VENDER_SHORTED_NAME
FROM BALANCE, VENDER
WHERE VENDER_DIVISION(+) = 'TS'
AND BALANCE.VENDER_CD = VENDER.VENDER_CD(+)
START WITH BALANCE_CD = /*balanceCd*/'5'
CONNECT BY PRIOR UPPER_BALANCE_CD = BALANCE_CD
ORDER BY LEVEL DESC) A

ORDER BY ROWNUM
