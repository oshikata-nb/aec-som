/*
 * 取引先一覧用SQL
 *
 * entityName=VenderList
 * packageName=master.venderList
 * methodName=getVenderList
 *
 */
SELECT 	VENDER_DIVISION
,       VENDER_CD
,	NVL(VENDER_NAME1,'名称がありません') VENDER_NAME1
,	NVL(VENDER_SHORTED_NAME,'略称がありません') VENDER_SHORTED_NAME

FROM 	VENDER
WHERE 	VENDER_CD IS NOT NULL
AND		((PAYMENT_INVOICE_CD IS NULL)
        OR
         (PAYMENT_INVOICE_CD = VENDER_CD))

ORDER BY VENDER_DIVISION
,        VENDER_CD
