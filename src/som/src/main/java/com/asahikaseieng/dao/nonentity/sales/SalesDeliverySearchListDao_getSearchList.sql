/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */

/*
 * 納入先検索(ポップアップ)一覧用SQL
 *
 * entityName=SalesDeliverySearchList
 * packageName=salse
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
FROM
    DELIVERY
WHERE
	DELIVERY_CD IS NOT NULL
/*IF (condition.srhDeliveryCd != null) && (condition.srhDeliveryCd != "")*/
AND (DELIVERY_CD LIKE /*condition.srhDeliveryCd*/ OR DELIVERY_NAME1 LIKE /*condition.srhDeliveryCd*/)
/*END*/
AND DIVISION = /*condition.srhDivision*/
ORDER BY DELIVERY_CD
