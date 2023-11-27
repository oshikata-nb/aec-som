/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/

/**
 * 分類マスタ検索用SQL(ﾃﾞｰﾀ集計区分取得)
 * 
 * @author tosco
 *
 * entityName=AcceptDetailList
 * packageName=accept
 * methodName=getDataTotalDivision
 *
 */
SELECT	DATA_TOTAL_DIVISION		-- ﾃﾞｰﾀ集計区分
FROM	CLASSIFICATION
WHERE	DATA_TYPE = '3'		-- 3:仕入
AND		CATEGORY_DIVISION = /*categoryDivision*/
AND 	ROWNUM <= 1
