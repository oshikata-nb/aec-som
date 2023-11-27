/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/

/**
 * 受入・仕入－仕入区分取得用SQL
 *
 * @author tosco
 *
 * entityName=AcceptStockingDivisionComboboxes
 * packageName=accept
 * methodName=findStockingDivision
 *
 */
SELECT
	CATEGORY_DIVISION ,
	DATA_TOTAL_DIVISION ,
	CATEGORY_NAME
FROM
    CLASSIFICATION
WHERE
    DATA_TYPE = '3'
AND CATEGORY_DIVISION NOT LIKE '-%'
ORDER BY
	CATEGORY_DIVISION
