/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/

/**
 * 売上区分名称取得用SQL
 *
 * @author tosco
 *
 * entityName=String
 * packageName=sales
 * methodName=getCategoryName
 *
 */
SELECT
    CLASSIFICATION.CATEGORY_NAME
FROM
    CLASSIFICATION
WHERE
    CLASSIFICATION.DATA_TYPE(+) = '1'
AND CLASSIFICATION.CATEGORY_DIVISION = /*categoryDivision*/
AND ROWNUM <= 1
