/*
 * 品目ヘッダー用SQL
 *
 * entityName=ItemQueueHeader
 * packageName=itemqueueheader
 * methodName=getEntity
 *
 */

SELECT ITEM_QUEUE.ITEM_CD HEAD_ITEM_CD, ITEM_QUEUE.VERSION HEAD_VERSION, ITEM_QUEUE.ITEM_NAME HEAD_ITEM_NAME, SUBSTR(ITEM_QUEUE.ITEM_NAME, 0, 24) SHORT_ITEM_NAME, ITEM_QUEUE.ACTIVE_DATE HEAD_ACTIVE_DATE, NVL2(ITEM.ITEM_CD, '○', NULL) HEAD_ACTIVATE, ITEM_QUEUE.PRODUCT_DIVISION, ITEM_QUEUE.ARTICLE_DIVISION, ITEM_QUEUE.PURCHASE_DIVISION,
ITEM_QUEUE.STATUS HEADER_STATUS,

CASE ITEM_QUEUE.STATUS
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END HEAD_DETAIL_STATUS_NAME,

CASE LEAST(DECODE(NVL(ITEM_QUEUE.PRODUCT_DIVISION, 0), 0, 4, PRODUCT_ATTRIBUTE_QUEUE.STATUS), DECODE(NVL(ITEM_QUEUE.ARTICLE_DIVISION, 0), 0, 4, ARTICLE_ATTRIBUTE_QUEUE.STATUS), DECODE(NVL(ITEM_QUEUE.PURCHASE_DIVISION, 0), 0, 4, PURCHASE_ATTRIBUTE_QUEUE.STATUS))
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END HEAD_ATTRIBUTE_STATUS_NAME

FROM ITEM_QUEUE, ITEM, ARTICLE_ATTRIBUTE_QUEUE, PRODUCT_ATTRIBUTE_QUEUE, PURCHASE_ATTRIBUTE_QUEUE
WHERE ITEM_QUEUE.ITEM_CD = /*itemCd*/'%'
AND ITEM_QUEUE.VERSION = /*version*/1
AND ITEM_QUEUE.ITEM_CD = ITEM.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = ITEM.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = ARTICLE_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = ARTICLE_ATTRIBUTE_QUEUE.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PRODUCT_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PRODUCT_ATTRIBUTE_QUEUE.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PURCHASE_ATTRIBUTE_QUEUE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PURCHASE_ATTRIBUTE_QUEUE.VERSION(+)
