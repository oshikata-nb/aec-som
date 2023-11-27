/*
 * 取引先一覧取得用SQL
 * (検索条件：名称)
 *
 * entityName=VenderList
 * packageName=master.venderList
 * methodName=getSearchNameList
 *
 */
SELECT 	VENDER_DIVISION
,       VENDER_CD
,	    VENDER_NAME1

FROM 	VENDER
WHERE   VENDER_NAME1 IS NOT NULL
AND		((PAYMENT_INVOICE_CD IS NULL)
        OR
         (PAYMENT_INVOICE_CD = VENDER_CD))
 
/*IF (( venderName != null ) && ( venderName != "" ))*/
AND     VENDER_NAME1 LIKE /*venderName*/'%'
/*END*/

ORDER BY VENDER_DIVISION
,        VENDER_CD
