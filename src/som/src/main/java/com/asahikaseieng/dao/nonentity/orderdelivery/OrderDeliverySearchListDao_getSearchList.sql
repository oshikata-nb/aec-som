/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */

/*
 * 納入先検索(ポップアップ)一覧用SQL
 *
 * entityName=OrderDeliverySearchList
 * packageName=orderdelivery
 * methodName=getSearchList
 *
 */

SELECT
    DELIVERY_CD
,   SEARCH_KANA
,   DELIVERY_NAME1
,   DELIVERY_NAME2
,   ADDRESS1
,   ADDRESS2
,   ADDRESS3
,   TEL_NO
,	FARE_CLAIM_EXISTENCE
,	LOGIN.TANTO_CD  as EIGYO_TANTO_CD
,	LOGIN.TANTO_NM  as EIGYO_TANTO_NAME
,	LEAD_TIME
,	DELIVERY_TIME
,	CARRY_CD
FROM
    DELIVERY
,	LOGIN
WHERE
	DELIVERY_CD IS NOT NULL
AND	DELIVERY.TANTO_CD = LOGIN.TANTO_CD(+)
/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND (DELIVERY_CD LIKE /*condition.srhDeliveryCd*/ OR DELIVERY_NAME1 LIKE /*condition.srhDeliveryCd*/)
/*END*/
AND DIVISION = /*condition.srhDivision*/
ORDER BY DELIVERY_CD
