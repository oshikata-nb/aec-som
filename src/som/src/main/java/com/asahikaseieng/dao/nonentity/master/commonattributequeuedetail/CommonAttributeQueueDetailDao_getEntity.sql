/*
 * 共通属性検索用SQL
 *
 * entityName=CommonAttributeQueueDetail
 * packageName=commonattributequeuedetail
 * methodName=getEntity
 *
 */

SELECT ITEM_CD, VERSION, EXPIRE_MONTHS, CONTRACT_MONTHS, ORDER_INFO, REMARKS, APPLICATION_LAW, UPDATE_DATE COMMON_UPDATE_DATE
FROM COMMON_ATTRIBUTE_QUEUE
WHERE ITEM_CD = /*itemCd*/'%'
AND VERSION = /*version*/1
