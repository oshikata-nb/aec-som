/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
*/

/**
 * 分類マスタ検索用SQL(ﾃﾞｰﾀ集計区分取得)
 * 
 * @author tosco
 *
 * packageName=accept
 * methodName=getDataTotalDivision
 *
 */
SELECT	DATA_TOTAL_DIVISION		-- ﾃﾞｰﾀ集計区分
FROM	CLASSIFICATION
WHERE	DATA_TYPE = '3'		-- 3:仕入
AND		CATEGORY_DIVISION = /*categoryDivision*/
AND 	ROWNUM <= 1
