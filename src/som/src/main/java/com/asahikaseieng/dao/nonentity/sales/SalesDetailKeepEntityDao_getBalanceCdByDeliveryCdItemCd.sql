/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/

/**
 * 帳合コード取得用SQL
 *
 * @author tosco
 *
 * entityName=String
 * packageName=sales
 * methodName=getBalanceCdByDeliveryCdItemCd
 *
 */
SELECT
      BALANCE_CD
FROM
      SALES_TERMS
WHERE
    DELIVERY_CD = /*deliveryCd*/
AND ITEM_CD     = /*itemCd*/
AND ROWNUM <= 1
