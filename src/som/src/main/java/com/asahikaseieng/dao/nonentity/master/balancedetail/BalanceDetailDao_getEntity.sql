/*
 * 帳合マスタ用SQL
 *
 * entityName=BalanceDetail
 * packageName=balancedetail
 * methodName=getEntity
 *
 */

SELECT BALANCE_CD, BALANCE.VENDER_CD, UPPER_BALANCE_CD, SHOP_LEVEL,
CASE SHOP_LEVEL
	WHEN 1 THEN '得意先'
    WHEN 2 THEN '二次店'
    WHEN 3 THEN '三次店'
    WHEN 4 THEN '四次店'
    WHEN 5 THEN '五次店'
    ELSE NULL
END SHOP_LEVEL_NAME,
BALANCE_TYPE, BALANCE.UPDATE_DATE, VENDER.VENDER_SHORTED_NAME AS VENDER_NAME1, VENDER.PAYMENT_INVOICE_CD, PAYMENT.VENDER_SHORTED_NAME PAYMENT_INVOICE_NAME
FROM BALANCE, VENDER, VENDER PAYMENT
WHERE BALANCE_CD = /*balanceCd*/'1'
AND VENDER.VENDER_DIVISION(+) = 'TS'
AND BALANCE.VENDER_CD = VENDER.VENDER_CD(+)
AND VENDER.VENDER_DIVISION = PAYMENT.VENDER_DIVISION(+)
AND VENDER.PAYMENT_INVOICE_CD = PAYMENT.VENDER_CD(+)
