/*
 * 受注納入先取得用SQL
 * 作成者:AECS佐藤 2020/01/21
 *
 * entityName=BelongDetail
 * packageName=belongdetail
 * methodName=getOrderDeliveryKbn
 *
 */

SELECT ORDER_DELIVERY_KBN,TANTO_CD
FROM BELONG

WHERE BELONG.TANTO_CD = /*tantoCd*/'%'
AND BELONG.BELONG_KBN = 1