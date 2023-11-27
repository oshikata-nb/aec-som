/*
 * 見積/単価マスタ重複チェック用SQL
 *
 * entityName=EstimateDuplicateList
 * packageName=estimateduplicatelist
 * methodName=getDuplicateList
 *
 */

SELECT ESTIMATE_NO
FROM ESTIMATE
WHERE ESTIMATE_NO IS NOT NULL

/*IF(estimateNo != null) && (estimateNo != "")*/
AND ESTIMATE_NO <> /*estimateNo*/'%'
/*END*/

AND BALANCE_CD = /*balanceCd*/'%'
AND ITEM_CD = /*itemCd*/'%'
-- 2021/3/10 重複チェックが聞かないデータがあったため、対策を実施。
AND ((ESTIMATE_VALID_DATE_FROM <= /*strEstimateValidDateFrom*/'2014/05/01' AND ESTIMATE_VALID_DATE_TO >= /*strEstimateValidDateFrom*/'2014/05/01') OR
      (ESTIMATE_VALID_DATE_FROM >= /*strEstimateValidDateFrom*/'2014/05/01' AND ESTIMATE_VALID_DATE_TO <= /*strEstimateValidDateTo*/'2014/05/01') OR
      (ESTIMATE_VALID_DATE_FROM <= /*strEstimateValidDateTo*/'2014/05/01' AND ESTIMATE_VALID_DATE_TO >= /*strEstimateValidDateTo*/'2014/05/01'))
