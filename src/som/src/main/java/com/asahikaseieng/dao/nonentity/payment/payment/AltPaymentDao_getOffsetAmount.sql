/*
* 支払予定 相殺取得SQL文
*
* entityName=AltPayment
* packageName=payment
* methodName=getOffsetAmount
*
*/

SELECT NVL(SUM(PAYABLE_OFFSET_AMOUNT), 0) PAYABLE_OFFSET_AMOUNT
FROM   OFFSET_GROUP_DATA
WHERE  OFFSET_DATE <= /*offsetDate*/'2013/12/02'
AND    VENDER_DIVISION = 'SI'
AND    VENDER_CD = /*venderCd*/'%'
AND    PAYMENT_UPDATE_DIVISION = 0 -- 支払更新未処理
