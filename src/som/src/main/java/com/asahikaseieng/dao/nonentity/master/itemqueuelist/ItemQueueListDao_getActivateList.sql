/*
 * 品目マスタ検索一覧用SQL(未使用)
 *
 * entityName=ItemQueueList
 * packageName=itemqueuelist
 * methodName=getActivateList
 *
 */

SELECT *
FROM
(SELECT ITEM_QUEUE.ITEM_CD
, ITEM_QUEUE.VERSION
, ITEM_QUEUE.ITEM_NAME
, SUBSTR(ITEM_QUEUE.ITEM_NAME, 0, 33) SHORT_ITEM_NAME
, ITEM_QUEUE.STATUS
, ITEM_QUEUE.ACTIVE_DATE

, CASE ITEM_QUEUE.STATUS
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END STATUS_NAME

, CASE LEAST(DECODE(NVL(ITEM_QUEUE.PRODUCT_DIVISION, 0), 0, 4, PRODUCT.STATUS), DECODE(NVL(ITEM_QUEUE.ARTICLE_DIVISION, 0), 0, 4, ARTICLE.STATUS), DECODE(NVL(ITEM_QUEUE.PURCHASE_DIVISION, 0), 0, 4, PURCHASE.STATUS))
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END DETAIL_STATUS_NAME

, DECODE(ITEM_QUEUE.VERSION, ITEM.VERSION, '○', '×') ACTIVATE

FROM

(SELECT ITEM_QUEUE.ITEM_CD
, ITEM_QUEUE.VERSION
, ITEM_NAME
, STATUS
, ARTICLE_DIVISION
, PRODUCT_DIVISION
, PURCHASE_DIVISION
, ACTIVE_DATE
, OTHER_COMPANY_CD1
, PARENT_ITEM_CD

, (SELECT ITEM_NAME
FROM ITEM_QUEUE PARENT_ITEM

, (SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM_QUEUE
GROUP BY ITEM_CD) MAX_ITEM

WHERE PARENT_ITEM.ITEM_CD = MAX_ITEM.ITEM_CD
AND PARENT_ITEM.VERSION = MAX_ITEM.VERSION
AND PARENT_ITEM.ITEM_CD = ITEM_QUEUE.PARENT_ITEM_CD) PARENT_ITEM_NAME

FROM ITEM_QUEUE) ITEM_QUEUE
, ITEM
, ARTICLE_ATTRIBUTE_QUEUE ARTICLE
, PRODUCT_ATTRIBUTE_QUEUE PRODUCT
, PURCHASE_ATTRIBUTE_QUEUE PURCHASE

WHERE ITEM_QUEUE.ITEM_CD IS NOT NULL

/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND (ITEM_QUEUE.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_QUEUE.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhParentItemCd != null) && (condition.srhParentItemCd != "")*/
AND (ITEM_QUEUE.PARENT_ITEM_CD LIKE /*condition.srhParentItemCd*/'%' OR PARENT_ITEM_NAME LIKE /*condition.srhParentItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (condition.srhStatus != 0)*/
AND ITEM_QUEUE.STATUS = /*condition.srhStatus*/1
/*END*/

/*IF (condition.srhDetailStatus != 0)*/
AND (ARTICLE.STATUS = /*condition.srhDetailStatus*/1
OR PRODUCT.STATUS = /*condition.srhDetailStatus*/1
OR PURCHASE.STATUS = /*condition.srhDetailStatus*/1)
/*END*/

/*IF (condition.strSrhActiveDateFrom != null) && (condition.strSrhActiveDateFrom != "")*/
AND TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') >= /*condition.strSrhActiveDateFrom*/'2007/07/01'
/*END*/

/*IF (condition.strSrhActiveDateTo != null) && (condition.strSrhActiveDateTo != "")*/
AND TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') <= /*condition.strSrhActiveDateTo*/'2007/07/31'
/*END*/

AND ITEM_QUEUE.ITEM_CD = ITEM.ITEM_CD(+)
AND TO_CHAR(SYSDATE, 'YYYY/MM/DD') < ITEM_QUEUE.ACTIVE_DATE
AND ITEM_QUEUE.ITEM_CD = ARTICLE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = ARTICLE.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PRODUCT.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PRODUCT.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PURCHASE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PURCHASE.VERSION(+)

UNION

SELECT ITEM_QUEUE.ITEM_CD
, ITEM_QUEUE.VERSION
, ITEM_QUEUE.ITEM_NAME
, SUBSTR(ITEM_QUEUE.ITEM_NAME, 0, 33) SHORT_ITEM_NAME
, ITEM_QUEUE.STATUS
, ITEM_QUEUE.ACTIVE_DATE

, CASE ITEM_QUEUE.STATUS
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END STATUS_NAME

, CASE LEAST(DECODE(NVL(ITEM_QUEUE.PRODUCT_DIVISION, 0), 0, 4, PRODUCT.STATUS), DECODE(NVL(ITEM_QUEUE.ARTICLE_DIVISION, 0), 0, 4, ARTICLE.STATUS), DECODE(NVL(ITEM_QUEUE.PURCHASE_DIVISION, 0), 0, 4, PURCHASE.STATUS))
    WHEN 1 THEN '入力中'
    WHEN 2 THEN '承認依頼中'
    WHEN 3 THEN '承認済み'
    WHEN 4 THEN '研究用'
    ELSE NULL
END DETAIL_STATUS_NAME

, '○' ACTIVATE

FROM

(SELECT ITEM_QUEUE.ITEM_CD
, ITEM_QUEUE.VERSION
, ITEM_NAME
, STATUS
, ARTICLE_DIVISION
, PRODUCT_DIVISION
, PURCHASE_DIVISION
, ACTIVE_DATE
, OTHER_COMPANY_CD1
, PARENT_ITEM_CD

, (SELECT ITEM_NAME
FROM ITEM_QUEUE PARENT_ITEM

, (SELECT ITEM_CD, MAX(VERSION) VERSION
FROM ITEM_QUEUE
GROUP BY ITEM_CD) MAX_ITEM

WHERE PARENT_ITEM.ITEM_CD = MAX_ITEM.ITEM_CD
AND PARENT_ITEM.VERSION = MAX_ITEM.VERSION
AND PARENT_ITEM.ITEM_CD = ITEM_QUEUE.PARENT_ITEM_CD) PARENT_ITEM_NAME

FROM ITEM_QUEUE) ITEM_QUEUE
, ITEM
, ARTICLE_ATTRIBUTE_QUEUE ARTICLE
, PRODUCT_ATTRIBUTE_QUEUE PRODUCT
, PURCHASE_ATTRIBUTE_QUEUE PURCHASE

WHERE ITEM_QUEUE.ITEM_CD IS NOT NULL

/*IF (condition.srhItemCd != null) && (condition.srhItemCd != "")*/
AND (ITEM_QUEUE.ITEM_CD LIKE /*condition.srhItemCd*/'%' OR ITEM_QUEUE.ITEM_NAME LIKE /*condition.srhItemCd*/'%')
/*END*/

/*IF (condition.srhParentItemCd != null) && (condition.srhParentItemCd != "")*/
AND (ITEM_QUEUE.PARENT_ITEM_CD LIKE /*condition.srhParentItemCd*/'%' OR PARENT_ITEM_NAME LIKE /*condition.srhParentItemCd*/'%')
/*END*/

/*IF (condition.srhOtherCompanyCd1 != null) && (condition.srhOtherCompanyCd1 != "")*/
AND ITEM_QUEUE.OTHER_COMPANY_CD1 LIKE /*condition.srhOtherCompanyCd1*/'%'
/*END*/

/*IF (condition.srhStatus != 0)*/
AND ITEM_QUEUE.STATUS = /*condition.srhStatus*/1
/*END*/

/*IF (condition.srhDetailStatus != 0)*/
AND (ARTICLE.STATUS = /*condition.srhDetailStatus*/1
OR PRODUCT.STATUS = /*condition.srhDetailStatus*/1
OR PURCHASE.STATUS = /*condition.srhDetailStatus*/1)
/*END*/

/*IF (condition.strSrhActiveDateFrom != null) && (condition.strSrhActiveDateFrom != "")*/
AND TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') >= /*condition.strSrhActiveDateFrom*/'2007/07/01'
/*END*/

/*IF (condition.strSrhActiveDateTo != null) && (condition.strSrhActiveDateTo != "")*/
AND TO_CHAR(ITEM_QUEUE.ACTIVE_DATE, 'YYYY/MM/DD') <= /*condition.strSrhActiveDateTo*/'2007/07/31'
/*END*/

AND ITEM_QUEUE.ITEM_CD = ITEM.ITEM_CD
AND ITEM_QUEUE.VERSION = ITEM.VERSION
AND ITEM_QUEUE.ITEM_CD = ARTICLE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = ARTICLE.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PRODUCT.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PRODUCT.VERSION(+)
AND ITEM_QUEUE.ITEM_CD = PURCHASE.ITEM_CD(+)
AND ITEM_QUEUE.VERSION = PURCHASE.VERSION(+)) ITEM_QUEUE

ORDER BY ITEM_QUEUE.ITEM_CD, ITEM_QUEUE.VERSION
