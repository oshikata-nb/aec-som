/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/

/**
 * データ集計区分取得用SQL
 *
 * @author tosco
 *
 * entityName=BigDecimal
 * packageName=sales
 * methodName=getDataTotalDivision
 *
 */
SELECT
    DATA_TOTAL_DIVISION
FROM
    CLASSIFICATION
WHERE
    DATA_TYPE(+) = '1'
AND CATEGORY_DIVISION = /*categoryDivision*/
AND ROWNUM <= 1
