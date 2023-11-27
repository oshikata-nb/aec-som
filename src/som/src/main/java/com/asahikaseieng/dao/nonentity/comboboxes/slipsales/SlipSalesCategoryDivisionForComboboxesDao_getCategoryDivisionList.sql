/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
*/

/**
 * 売上伝票出力－売上区分一覧取得用SQL
 *
 * @author tosco
 *
 * entityName=SlipSalesCategoryDivisionForComboboxes
 * packageName=slipsales
 * methodName=getCategoryDivisionList
 *
 */
SELECT
	 CATEGORY_DIVISION
	,DATA_TOTAL_DIVISION
	,CATEGORY_NAME
FROM
    CLASSIFICATION
WHERE
	DATA_TYPE = '1'
AND AR_DIVISION = 1
AND CATEGORY_DIVISION >= 1
ORDER BY
    CATEGORY_DIVISION
