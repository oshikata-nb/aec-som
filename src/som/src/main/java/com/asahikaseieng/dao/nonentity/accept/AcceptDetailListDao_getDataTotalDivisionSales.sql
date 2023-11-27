/**
 * 分類マスタ検索用SQL(売上用ﾃﾞｰﾀ集計区分取得)
 * 
 * @author t1344224
 *
 * packageName=accept
 * methodName=getDataTotalDivisionSales
 *
 */
SELECT	DATA_TOTAL_DIVISION		-- ﾃﾞｰﾀ集計区分
FROM	CLASSIFICATION
WHERE	DATA_TYPE = '1'		-- 1:売上
AND		CATEGORY_DIVISION = /*categoryDivision*/'0'
AND 	ROWNUM <= 1
