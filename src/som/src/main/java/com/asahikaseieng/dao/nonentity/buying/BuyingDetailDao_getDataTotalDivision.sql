/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
*/

/**
 * データ集計区分取得用SQL
 *
 * @author tosco
 *
 * entityName=BuyingDetail
 * packageName=buying
 * methodName=getDataTotalDivision
 *
 */
SELECT	DATA_TOTAL_DIVISION
FROM	CLASSIFICATION
WHERE	DATA_TYPE = '3'
AND		CATEGORY_DIVISION = /*categoryDivision*/